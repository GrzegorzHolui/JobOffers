package com.domain.joboffers.infrastructure.loginandregister.controller.error;

import org.springframework.http.HttpStatus;

public record TokenErrorResponse(String credential, HttpStatus status) {
}
