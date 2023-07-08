package com.domain.joboffers.offerfacade;

import com.domain.joboffers.offerfacade.dto.OfferDto;
import org.springframework.stereotype.Component;


public class OfferFacade {

    OfferReposiotry offerReposiotry;

//    public List<Offer> findAllOffers(){}

//    public Offer findOfferById()

    public OfferDto saveOFfer(Offer offer) {
        Offer save = offerReposiotry.save(offer);
        return null;
    }

//    public void fetchAllOffersAndSaveAllIfnotExists();

}
