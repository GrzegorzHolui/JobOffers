package com.domain.joboffers.offerfacade.dto;


import lombok.Builder;

import java.util.Objects;

@Builder
public record OfferResponseDto(String id, String linkToOffer, String jobName, String nameOfCompany,
                               EarningsRequestDto earnings) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfferResponseDto that = (OfferResponseDto) o;
        return Objects.equals(id, that.id) && Objects.equals(linkToOffer, that.linkToOffer) && Objects.equals(jobName, that.jobName) && Objects.equals(nameOfCompany, that.nameOfCompany) && Objects.equals(earnings, that.earnings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, linkToOffer, jobName, nameOfCompany, earnings);
    }
}
