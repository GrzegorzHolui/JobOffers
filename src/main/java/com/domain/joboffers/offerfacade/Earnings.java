package com.domain.joboffers.offerfacade;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;

@AllArgsConstructor
@Getter
public class Earnings {

    BigDecimal minSalary;
    BigDecimal maxSalary;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Earnings earnings = (Earnings) o;
        return Objects.equals(minSalary, earnings.minSalary) && Objects.equals(maxSalary, earnings.maxSalary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(minSalary, maxSalary);
    }
}
