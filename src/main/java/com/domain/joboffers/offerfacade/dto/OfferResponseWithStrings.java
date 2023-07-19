package com.domain.joboffers.offerfacade.dto;

import lombok.Builder;

@Builder
public record OfferResponseWithStrings(String title, String company, String salary, String offerUrl) {

}
