package com.domain.joboffers.offerfacade.dto;


import lombok.Builder;

import java.io.Serializable;

@Builder
public record OfferResponseDto(String id, String linkToOffer, String jobName, String nameOfCompany,
                               EarningsRequestDto earnings) implements Serializable
{
}
