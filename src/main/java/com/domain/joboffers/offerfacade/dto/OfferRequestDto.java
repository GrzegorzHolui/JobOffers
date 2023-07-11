package com.domain.joboffers.offerfacade.dto;

public record OfferRequestDto(String id,String linkToOffer, String jobName, String nameOfCompany, EarningsRequestDto earnings) {
}
