package com.domain.joboffers.controller.error;

import com.domain.joboffers.BaseIntegrationTest;
import com.domain.joboffers.offerfacade.dto.EarningsRequestDto;
import com.domain.joboffers.offerfacade.dto.OfferRequestDto;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class OfferUrlDuplicateErrorIntegrationTest extends BaseIntegrationTest {

    @DynamicPropertySource
    public static void propertyOverride(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    public void should_return_duplicate_key_exception_when_added_second_offer_with_same_offer_url() throws Exception {


        OfferRequestDto offerRequestDto = OfferRequestDto
                .builder()
                .linkToOffer("https://kariera.ifirma.pl/junior-java-developer")
                .jobName("junior-java-developer")
                .nameOfCompany("IFirma")
                .earnings(new EarningsRequestDto(BigDecimal.ZERO, BigDecimal.ONE))
                .build();


//        log.info();

        //when
        MvcResult mvcResultIfirma1 = mockMvc.perform(post("/offers")
                        .content(objectMapper.writeValueAsString(offerRequestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();


        assertThat(mvcResultIfirma1.getResponse().getStatus()).isEqualTo(201);


        MvcResult mvcResultIfirma2 = mockMvc.perform(post("/offers")
                        .content(objectMapper.writeValueAsString(offerRequestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertThat(mvcResultIfirma2.getResponse().getStatus()).isEqualTo(409);


    }
}
