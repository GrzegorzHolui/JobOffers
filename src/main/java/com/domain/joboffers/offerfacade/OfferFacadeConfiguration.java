package com.domain.joboffers.offerfacade;

public class OfferFacadeConfiguration {

    public OfferFacade offerFacade() {
        return new OfferFacade();
    }

    public OfferFacade offerFacadeTest(OfferRepository offerRepository, OfferModelMapper offerModelMapper,
                                       OfferFetchable offerFetchable) {
        OfferValidator offerValidator = new OfferValidator();
        ValidatorMessageConverter validatorMessageConverter = new ValidatorMessageConverter();
        OfferService offerService = new OfferService(offerFetchable, offerModelMapper, offerRepository);
        return new OfferFacade(offerValidator, validatorMessageConverter, offerModelMapper, offerRepository,
                offerService);
    }
}
