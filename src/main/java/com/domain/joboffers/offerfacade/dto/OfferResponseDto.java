package com.domain.joboffers.offerfacade.dto;


import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Builder
public record OfferResponseDto(String id, String linkToOffer, String jobName, String nameOfCompany,
                               EarningsRequestDto earnings) {

}
