package com.domain.joboffers.offerfacade;

public class OfferFacadeConfiguration {

    public OfferFacade offerFacade() {
        return new OfferFacade();
    }

    public OfferFacade offerFacadeTest(String a) {
        return new OfferFacade();
    }
}
