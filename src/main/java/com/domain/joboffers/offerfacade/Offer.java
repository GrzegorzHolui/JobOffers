package com.domain.joboffers.offerfacade;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter

public class Offer {

    String linkToOffer;
    String jobName;
    String nameOfCompany;
    Earnings earnings;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return Objects.equals(linkToOffer, offer.linkToOffer) && Objects.equals(jobName, offer.jobName) && Objects.equals(nameOfCompany, offer.nameOfCompany) && Objects.equals(earnings, offer.earnings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(linkToOffer, jobName, nameOfCompany, earnings);
    }
}
