package com.domain.joboffers.offerfacade;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class OfferFacadeConfiguration {

    @Bean
    public OfferRepository offerRepository() {
        return new OfferRepository() {
            @Override
            public Offer save(Offer offer) {
                return null;
            }

            @Override
            public Optional<Offer> findOfferById(String id) {
                return Optional.empty();
            }

            @Override
            public List<Offer> findAllOffers() {
                return null;
            }

            @Override
            public boolean existsByOfferUrl(String s) {
                return false;
            }

            @Override
            public List<Offer> saveAll(List<Offer> offersResult) {
                return null;
            }
        };
    }

    @Bean
    public OfferFacade offerFacade(OfferModelMapper offerModelMapper, OfferFetchable offerFetchable
            , OfferRepository offerRepository) {
        OfferValidator offerValidator = new OfferValidator();
        ValidatorMessageConverter validatorMessageConverter = new ValidatorMessageConverter();
        OfferService offerService = new OfferService(offerFetchable, offerModelMapper, offerRepository);
        return new OfferFacade(offerValidator, validatorMessageConverter, offerModelMapper,
                offerRepository, offerService);
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
