package com.domain.joboffers.controller.error;

import com.domain.joboffers.BaseIntegrationTest;
import com.domain.joboffers.offerfacade.OfferRepository;
import com.domain.joboffers.offerfacade.dto.EarningsRequestDto;
import com.domain.joboffers.offerfacade.dto.OfferRequestDto;
import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.head;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Log4j2
public class OfferUrlDuplicateErrorIntegrationTest extends BaseIntegrationTest {

    @DynamicPropertySource
    public static void propertyOverride(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    OfferRepository offerRepository;

    @Test
    @WithMockUser
    public void should_return_duplicate_key_exception_when_added_second_offer_with_same_offer_url() throws Exception {


        wireMockServer.stubFor(head(urlEqualTo("/pl/job/junior-fullstack-java-developer-it-touch-warsaw-4"))
                .willReturn(aResponse()
                        .withStatus(200)));

        OfferRequestDto offerRequestDto = OfferRequestDto
                .builder()
                .linkToOffer("http://localhost:" + wireMockServer.getPort() + "/pl/job/junior-fullstack-java-developer-it-touch-warsaw-4")
                .jobName("junior-java-developer")
                .nameOfCompany("IFirma")
                .earnings(new EarningsRequestDto(BigDecimal.ZERO, BigDecimal.ONE))
                .build();

        //when
        MvcResult mvcResultIfirma1 = mockMvc.perform(post("/offers")
                        .content(objectMapper.writeValueAsString(offerRequestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        log.info(offerRepository.findAll());
        assertThat(mvcResultIfirma1.getResponse().getStatus()).isEqualTo(201);

        MvcResult mvcResultIfirma2 = mockMvc.perform(post("/offers")
                        .content(objectMapper.writeValueAsString(offerRequestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        log.info(offerRepository.findAll());
        assertThat(mvcResultIfirma2.getResponse().getStatus()).isEqualTo(409);
    }
}
