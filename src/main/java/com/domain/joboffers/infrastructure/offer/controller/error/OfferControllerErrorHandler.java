package com.domain.joboffers.infrastructure.offer.controller.error;

import com.domain.joboffers.offerfacade.OfferNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
@Log4j2
public class OfferControllerErrorHandler {

    @ExceptionHandler(OfferNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResultOfferErrorResponse handleOfferResultNotFound(OfferNotFoundException offerNotFoundException) {
        List<String> message = List.of(offerNotFoundException.getMessage());
        log.error(message);
        return new ResultOfferErrorResponse(message, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResultOfferErrorResponse offerDuplicate() {
        List<String> message = List.of("Offer url already exists");
        log.warn(message);
        return new ResultOfferErrorResponse(message, HttpStatus.CONFLICT);
    }
}
