package com.domain.joboffers.offerfacade;

import lombok.Builder;

import java.math.BigDecimal;


@Builder
record Earnings(BigDecimal minSalary,
                BigDecimal maxSalary) {
}
