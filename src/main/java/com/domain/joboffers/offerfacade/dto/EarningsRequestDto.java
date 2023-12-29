package com.domain.joboffers.offerfacade.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;


import java.io.Serializable;
import java.math.BigDecimal;

@Builder
public record EarningsRequestDto(
        @NotEmpty(message = "salary should not be empty") @NotNull(message = "salary should not be null") BigDecimal minSalary,
        @NotEmpty(message = "salary should not be empty") @NotNull(message = "salary should not be null") BigDecimal maxSalary) implements Serializable {
}
