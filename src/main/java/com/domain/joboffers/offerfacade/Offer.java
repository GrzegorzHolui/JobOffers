package com.domain.joboffers.offerfacade;

import lombok.Builder;

@Builder
public
record Offer(String id,
             String linkToOffer,
             String jobName,
             String nameOfCompany,
             Earnings earnings) {
}
