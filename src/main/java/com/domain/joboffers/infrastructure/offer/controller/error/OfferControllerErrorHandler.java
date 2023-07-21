package com.domain.joboffers.infrastructure.offer.controller.error;

import com.domain.joboffers.offerfacade.OfferNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Log4j2
public class OfferControllerErrorHandler {

    @ExceptionHandler(OfferNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResultOfferErrorResponse handleOfferResultNotFound(OfferNotFoundException offerNotFoundException) {
        String message = offerNotFoundException.getMessage();
        log.error(message);
        return new ResultOfferErrorResponse(message, HttpStatus.NOT_FOUND);
    }
}
