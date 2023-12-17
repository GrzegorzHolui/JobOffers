package com.domain.joboffers.offerfacade.dto;

import lombok.Builder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
public record EarningsRequestDto(
        @NotEmpty(message = "salary should not be empty") @NotNull(message = "salary should not be null") BigDecimal minSalary,
        @NotEmpty(message = "salary should not be empty") @NotNull(message = "salary should not be null") BigDecimal maxSalary) {
}
