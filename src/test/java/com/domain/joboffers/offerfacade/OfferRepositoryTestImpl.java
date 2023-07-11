package com.domain.joboffers.offerfacade;

import java.util.ArrayList;
import java.util.List;

class OfferRepositoryTestImpl implements OfferRepository {
    List<Offer> offers = new ArrayList<>();

    @Override
    public Offer save(Offer offer) {
        offers.add(offer);
        return offer;
    }
}
