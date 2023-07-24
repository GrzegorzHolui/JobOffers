package com.domain.joboffers.apivalidationerror;

import com.domain.joboffers.BaseIntegrationTest;
import com.domain.joboffers.infrastructure.apivalidation.ApiValidationErrorDto;
import com.domain.joboffers.offerfacade.dto.EarningsRequestDto;
import com.domain.joboffers.offerfacade.dto.OfferRequestDto;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class ApiValidationFailedIntegrationTest extends BaseIntegrationTest {

    @Test
    public void should_return_400_bad_request_and_validation_message_when_request_is_empty() throws Exception {
        //given

        //when
        MvcResult mvcResult = mockMvc.perform(post("/offers")
                        .content("""
                                {}
                                """.trim())
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        //then
        String json = mvcResult.getResponse().getContentAsString();
        ApiValidationErrorDto resultOfferErrorResponse = objectMapper
                .readValue(json, ApiValidationErrorDto.class);

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(400);
//        assertThat(resultOfferErrorResponse.message()).containsExactlyInAnyOrder();

    }

    @Test
    public void should_return_400_bad_request_and_validation_message_when_request_is_null() throws Exception {
        //given
        OfferRequestDto offerRequestDto =
                new OfferRequestDto("", "", "", new EarningsRequestDto(null, null));

        //when
        MvcResult mvcResult = mockMvc.perform(post("/offers")
                        .content(objectMapper.writeValueAsString(offerRequestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        //thenE
        String json = mvcResult.getResponse().getContentAsString();
        ApiValidationErrorDto resultOfferErrorResponse = objectMapper.readValue(json, new TypeReference<>() {
        });

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(400);
        assertThat(resultOfferErrorResponse.errors()).containsExactlyInAnyOrder(
                "linkToOffer should not be empty",
                "nameOfCompany should not be empty",
                "jobName should not be empty");

    }

}