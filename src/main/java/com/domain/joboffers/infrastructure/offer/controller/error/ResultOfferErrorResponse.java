package com.domain.joboffers.infrastructure.offer.controller.error;

import org.springframework.http.HttpStatus;

public record ResultOfferErrorResponse(String message, HttpStatus httpStatus) {
}
