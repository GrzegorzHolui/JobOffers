package com.domain.joboffers.offerfacade;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;


public record Earnings(BigDecimal minSalary,
                       BigDecimal maxSalary) {
}
