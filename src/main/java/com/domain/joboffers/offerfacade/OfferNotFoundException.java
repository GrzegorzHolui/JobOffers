package com.domain.joboffers.offerfacade;

import lombok.Getter;

@Getter
public class OfferNotFoundException extends RuntimeException {

    public OfferNotFoundException(String offerId) {
        super(String.format("Offer with id %s not found", offerId));
    }
}
