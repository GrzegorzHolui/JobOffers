package com.domain.joboffers.offerfacade.dto;


import lombok.Builder;

@Builder
public record OfferResponseDto(String id, String linkToOffer, String jobName, String nameOfCompany,
                               EarningsRequestDto earnings) {

}
