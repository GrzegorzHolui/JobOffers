package com.domain.joboffers.offerfacade.dto;


public record OfferResponseDto(String id, String linkToOffer, String jobName, String nameOfCompany,
                               EarningsRequestDto earnings) {
}
