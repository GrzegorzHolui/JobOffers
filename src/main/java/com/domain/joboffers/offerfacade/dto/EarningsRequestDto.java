package com.domain.joboffers.offerfacade.dto;

import java.math.BigDecimal;

public record EarningsRequestDto(BigDecimal minSalary, BigDecimal maxSalary) {
}
