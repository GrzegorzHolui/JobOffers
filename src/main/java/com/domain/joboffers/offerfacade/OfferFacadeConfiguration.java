package com.domain.joboffers.offerfacade;
import org.mapstruct.factory.Mappers;

public class OfferFacadeConfiguration {

    public OfferFacade offerFacade() {
        return new OfferFacade();
    }

    public OfferFacade offerFacadeTest(OfferReposiotry offerReposiotry, OfferModelMapper offerModelMapper) {
        OfferValidator offerValidator = new OfferValidator();
        ValidatorMessageConverter validatorMessageConverter = new ValidatorMessageConverter();
        return new OfferFacade(offerValidator, validatorMessageConverter, offerModelMapper, offerReposiotry);
    }
}
