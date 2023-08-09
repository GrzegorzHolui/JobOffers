package com.domain.joboffers.feature;

import com.domain.joboffers.BaseIntegrationTest;
import com.domain.joboffers.SampleJobOfferResponse;
import com.domain.joboffers.infrastructure.offer.scheduler.OffersScheduler;
import com.domain.joboffers.infrastructure.loginandregister.controller.dto.TokenRequestDto;
import com.domain.joboffers.loginandregister.dto.RegisterUserDto;
import com.domain.joboffers.loginandregister.dto.RegistrationResultDto;
import com.domain.joboffers.offerfacade.OfferFetchable;
import com.domain.joboffers.offerfacade.dto.EarningsRequestDto;
import com.domain.joboffers.offerfacade.dto.OfferFacadeResultDto;
import com.domain.joboffers.offerfacade.dto.OfferRequestDto;
import com.domain.joboffers.offerfacade.dto.OfferResponseDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class JobOfferIntegrationTest extends BaseIntegrationTest implements SampleJobOfferResponse {

    @Autowired
    OfferFetchable offerFetchable;

    @Autowired
    OffersScheduler offersScheduler;

    @Test
    public void f() throws Exception {
        // step 1: there are no offers in external HTTP server (http://ec2-3-120-147-150.eu-central-1.compute.amazonaws.com:5057/offers)

        // given && when && then
        wireMockServer.stubFor(WireMock.get("/offers").willReturn(WireMock.aResponse().withStatus(HttpStatus.OK.value())
                .withHeader("Content-Type", "application/json").withBody(zeroJobOffer())));

        // step 2: scheduler ran 1st time and made GET to external server and system added 0 offers to database

        // given && when

        List<OfferResponseDto> offerResponseDtos = offersScheduler.fetchAllOffersAndSaveAllIfNotExists();

        // then
        assertThat(offerResponseDtos).hasSize(0);

        // step 3:user tried to get JWT token by requesting POST / token with username = someUser, password = somePassword
        // and system returned UNAUTHORIZED (401)
        // given & when
        TokenRequestDto tokenRequestDto = new TokenRequestDto("someUser", "somePassword");
        MvcResult postToken = mockMvc.perform(post("/token")
                .content(objectMapper.writeValueAsString(tokenRequestDto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        // then
        assertThat(postToken.getResponse().getStatus()).isEqualTo(HttpStatus.UNAUTHORIZED.value());

        // step 4: user made GET /offers with no jwt token and system returned UNAUTHORIZED(401)
        MvcResult getToken = mockMvc.perform(get("/offers")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        assertThat(getToken.getResponse().getStatus()).isEqualTo(HttpStatus.FORBIDDEN.value());

        // step 5: user made POST /register with username=someUser, password=somePassword and system registered user with status OK(200)
        // given && when
        RegisterUserDto register = new RegisterUserDto("someUser", "somePassword");
        MvcResult postRegister = mockMvc.perform(post("/register")
                .content(objectMapper.writeValueAsString(register))
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();


        //then
        RegistrationResultDto registrationResultDto =
                objectMapper.readValue(postRegister.getResponse().getContentAsString(), RegistrationResultDto.class);
        assertAll(
                () -> assertThat(postRegister.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value()),
                () -> assertThat(registrationResultDto.username()).isEqualTo("someUser"),
                () -> assertThat(registrationResultDto.id()).isNotNull(),
                () -> assertThat(registrationResultDto.created()).isTrue());


        // step 6: user tried to get JWT token by requesting POST /token with username=someUser, password=somePassword and system returned OK(200) and jwttoken=AAAA.BBBB.CCC


        // step 7: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 0 offers

        // given && when

        MvcResult mvcResult = mockMvc.perform(get("/offers")).andReturn();
        List<OfferResponseDto> requestDtos = Arrays.stream(objectMapper.readValue
                (mvcResult.getResponse().getContentAsString(), OfferResponseDto[].class)).toList();

        // then

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
        assertThat(requestDtos).hasSize(0);

        // step 8: there are 2 new offers in external HTTP server

        // given && when

        wireMockServer.stubFor(WireMock.get("/offers").willReturn(WireMock.aResponse().withStatus(HttpStatus.OK.value())
                .withHeader("Content-Type", "application/json").withBody(twoJobOffer())));


        // step 9: scheduler ran 2nd time and made GET to external server and system added 2 new offers with ids: 1000 and 2000 to database

        // given && when

        List<OfferResponseDto> schedulerResponse = offersScheduler.fetchAllOffersAndSaveAllIfNotExists();

        // then

        assertThat(schedulerResponse).hasSize(2);

        // step 10: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200)
        // with 2 offers with ids: 1000 and 2000

        // given && when
        MvcResult mvcResult2nd = mockMvc.perform(get("/offers")).andReturn();
        List<OfferResponseDto> requestDtos2nd = Arrays.stream(objectMapper.readValue
                (mvcResult2nd.getResponse().getContentAsString(), OfferResponseDto[].class)).toList();

        // then
        assertThat(schedulerResponse.get(0)).isEqualTo(requestDtos2nd.get(0));
        assertThat(schedulerResponse.get(1)).isEqualTo(requestDtos2nd.get(1));
        assertThat(mvcResult2nd.getResponse().getStatus()).isEqualTo(200);
        assertThat(requestDtos2nd).hasSize(2);

        // step 11: user made GET /offers/9999 and system returned NOT_FOUND(404) with message “Offer with id 9999 not found”

        //given && when

        MvcResult getResultWithParameter = mockMvc.perform(get("/offers/" + "9999")).andReturn();

        OfferResponseDto offerResponseDto = objectMapper.readValue(getResultWithParameter.getResponse()
                .getContentAsString(), OfferResponseDto.class);

        //then
        assertThat(getResultWithParameter.getResponse().getStatus()).isEqualTo(404);

        //        step 12: user made GET /offers/1000 and system returned OK(200) with offer

        // given && when
        MvcResult getResultWithParameterOfScheduler = mockMvc.perform(get("/offers/" +
                schedulerResponse.get(0).id())).andReturn();

        // then
        assertThat(getResultWithParameterOfScheduler.getResponse().getStatus()).isEqualTo(200);


        //        step 13: there are 2 new offers in external HTTP server

        wireMockServer.stubFor(WireMock.get("/offers").willReturn(WireMock.aResponse().withStatus(HttpStatus.OK.value())
                .withHeader("Content-Type", "application/json").withBody(fourJobOffer())));

        //        step 14: scheduler ran 3rd time and made GET to external server and system added 2 new
        // offers with ids: 3000 and 4000 to database

        List<OfferResponseDto> offerSchedulerResponse = offersScheduler.fetchAllOffersAndSaveAllIfNotExists();

        // then
        assertThat(offerSchedulerResponse).hasSize(2);


        //        step 15: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC ” and
        // system returned OK(200) with 4 offers with ids: 1000  ,2000, 3000 and 4000

        // given && when
        MvcResult getResult = mockMvc.perform(get("/offers")).andReturn();
        List<OfferResponseDto> result = Arrays.stream(objectMapper.readValue
                (getResult.getResponse().getContentAsString(), OfferResponseDto[].class)).toList();

        // then
        assertThat(getResult.getResponse().getStatus()).isEqualTo(200);
        assertThat(result).hasSize(4);


        //        step 16: user made POST /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and
        // offer and system returned CREATED(201) with saved offer

        // given
        OfferRequestDto offerRequestDto = OfferRequestDto.builder()
                .linkToOffer("https://kariera" + ".ifirma" + ".pl/junir-java-developer")
                .jobName("junior-java-developer")
                .nameOfCompany("IFirma")
                .earnings(new EarningsRequestDto(BigDecimal.ZERO, BigDecimal.ONE)).build();

        // when
        MvcResult mvcResultIfirma = mockMvc.perform(post("/offers")
                .content(objectMapper.writeValueAsString(offerRequestDto))
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        OfferFacadeResultDto resultOfferFacade = objectMapper.readValue(mvcResultIfirma.getResponse()
                .getContentAsString(), new TypeReference<>() {
        });

        assertAll(() -> assertThat(mvcResultIfirma.getResponse().getStatus()).isEqualTo(201),
                () -> assertThat(resultOfferFacade.offerFacade().linkToOffer()).isEqualTo(
                        "https://kariera.ifirma.pl/junir-java-developer"),
                () -> assertThat(resultOfferFacade.offerFacade().jobName()).isEqualTo("junior-java-developer"),
                () -> assertThat(resultOfferFacade.offerFacade().nameOfCompany()).isEqualTo("IFirma"),
                () -> assertThat(resultOfferFacade.offerFacade().id()).isNotNull());

        //        step 17: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and
        // system returned OK(200) with 1 offer

    }
}
