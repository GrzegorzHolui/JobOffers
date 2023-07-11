package com.domain.joboffers.offerfacade;

public class OfferFacadeConfiguration {

    public OfferFacade offerFacade() {
        return new OfferFacade();
    }

    public OfferFacade offerFacadeTest(OfferRepository offerRepository, OfferModelMapper offerModelMapper) {
        OfferValidator offerValidator = new OfferValidator();
        ValidatorMessageConverter validatorMessageConverter = new ValidatorMessageConverter();
        return new OfferFacade(offerValidator, validatorMessageConverter, offerModelMapper, offerRepository);
    }
}
