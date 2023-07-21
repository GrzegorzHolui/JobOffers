package com.domain.joboffers.offerfacade.dto;

import lombok.Builder;

@Builder
public record OfferRequestDto(String linkToOffer, String jobName, String nameOfCompany, EarningsRequestDto earnings) {
}
