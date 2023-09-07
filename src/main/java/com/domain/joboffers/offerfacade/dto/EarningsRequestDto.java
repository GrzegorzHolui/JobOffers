package com.domain.joboffers.offerfacade.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

public record EarningsRequestDto(
        @NotEmpty(message = "salary should not be empty") @NotNull(message = "salary should not be null") BigDecimal minSalary,
        @NotEmpty(message = "salary should not be empty") @NotNull(message = "salary should not be null") BigDecimal maxSalary) implements Serializable {
}
