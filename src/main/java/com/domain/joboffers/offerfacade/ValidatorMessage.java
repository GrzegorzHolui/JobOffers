package com.domain.joboffers.offerfacade;

enum ValidatorMessage {
    MIN_SALARY_OR_MAX_SALARY_UNDER_ZERO("The min or max salary is under 0"),
    MIN_SALARY_HAS_BIGGER_VALUE_THAN_MAX_SALARY("The min value is bigger than max value"),

    MAX_SALARY_IS_ZERO("MAX_SALARY_IS_ZERO"),
    LINK_IS_INVALID("The link is invalid");

    final String message;

    ValidatorMessage(String message) {
        this.message = message;
    }
}
