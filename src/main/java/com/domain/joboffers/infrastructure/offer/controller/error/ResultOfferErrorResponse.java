package com.domain.joboffers.infrastructure.offer.controller.error;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ResultOfferErrorResponse(List<String> message, HttpStatus httpStatus) {

}
