package com.domain.joboffers.offerfacade.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.NonNull;

//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Builder
public record OfferRequestDto(
        @NotNull(message = "linkToOffer should not be null")
        @NotEmpty(message = "linkToOffer should not be empty") String linkToOffer,
        @NotNull(message = "jobName should not be null")
        @NotEmpty(message = "jobName should not be empty") String jobName,
        @NotNull(message = "nameOfCompany should not be null")
        @NotEmpty(message = "nameOfCompany should not be empty") String nameOfCompany,

          EarningsRequestDto earnings) {
}
