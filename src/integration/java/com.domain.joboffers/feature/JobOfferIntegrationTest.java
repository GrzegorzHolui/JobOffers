package com.domain.joboffers.feature;


import com.domain.joboffers.BaseIntegrationTest;
import com.domain.joboffers.SampleJobOfferResponse;
import com.domain.joboffers.infrastructure.offer.scheduler.OffersScheduler;
import com.domain.joboffers.offerfacade.OfferFetchable;
import com.domain.joboffers.offerfacade.dto.OfferResponseDto;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class JobOfferIntegrationTest extends BaseIntegrationTest implements SampleJobOfferResponse {

    @Autowired
    OfferFetchable offerFetchable;

    @Autowired
    OffersScheduler offersScheduler;

    @Test
    public void f() throws Exception {
//   typical path: user want to see offers but have to be logged in and external server shouldtri have some offers
//        step 1: there are no offers in external HTTP server (http://ec2-3-120-147-150.eu-central-1.compute.amazonaws.com:5057/offers)
        // given && when && then
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(threeJobOffer())));

//        step 2: scheduler ran 1st time and made GET to external server and system added 0 offers to database
        //given && when
        List<OfferResponseDto> offerResponseDtos = offersScheduler.fetchAllOffersAndSaveAllIfNotExists();

        //then
        assertThat(offerResponseDtos).hasSize(3);


//        post
//        OfferRequestDto offerRequestDto = OfferRequestDto
//                .builder()
//                .linkToOffer("")
//                .jobName("")
//                .nameOfCompany("")
//                .earnings(new EarningsRequestDto(BigDecimal.ZERO, BigDecimal.ONE))
//                .build();
//
//        MvcResult mvcResult = mockMvc.perform(post("/inputOffer")
//                        .content(new ObjectMapper().writeValueAsString(offerRequestDto))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andReturn();
//
//
//        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);


//        step 3: user tried to get JWT token by requesting POST /token with username=someUser, password=somePassword and system returned UNAUTHORIZED(401)

//        step 4: user made GET /offers with no jwt token and system returned UNAUTHORIZED(401)


//        step 5: user made POST /register with username=someUser, password=somePassword and system registered user with status OK(200)
//        step 6: user tried to get JWT token by requesting POST /token with username=someUser, password=somePassword and system returned OK(200) and jwttoken=AAAA.BBBB.CCC


//        step 7: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 0 offers

        //given
        MvcResult mvcResult = mockMvc.perform(get("/offers")).andReturn();

        //when
        List<OfferResponseDto> requestDtos = Arrays.stream(objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                OfferResponseDto[].class)).toList();

        //then
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
        assertThat(requestDtos).hasSize(3);

//        step 8: there are 2 new offers in external HTTP server
//        step 9: scheduler ran 2nd time and made GET to external server and system added 2 new offers with ids: 1000 and 2000 to database
//        step 10: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 2 offers with ids: 1000 and 2000
//        step 11: user made GET /offers/9999 and system returned NOT_FOUND(404) with message “Offer with id 9999 not found”
        //given && when
        MvcResult getResultWithParameter = mockMvc.perform(get("/offers/" + "9999")).andReturn();
//        OfferResponseDto offerResponseDto = objectMapper.readValue(getResultWithParameter.getResponse()
//                .getContentAsString(), OfferResponseDto.class);

        //then
        assertThat(getResultWithParameter.getResponse().getStatus()).isEqualTo(404);


//        step 12: user made GET /offers/1000 and system returned OK(200) with offer
//        step 13: there are 2 new offers in external HTTP server
//        step 14: scheduler ran 3rd time and made GET to external server and system added 2 new offers with ids: 3000 and 4000 to database
//        step 15: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 4 offers with ids: 1000,2000, 3000 and 4000
//        step 16: user made POST /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and offer and system returned CREATED(201) with saved offer
//        step 17: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 1 offer

    }

}