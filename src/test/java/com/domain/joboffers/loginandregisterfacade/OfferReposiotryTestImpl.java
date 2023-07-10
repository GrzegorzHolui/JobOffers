package com.domain.joboffers.loginandregisterfacade;

import com.domain.joboffers.offerfacade.Offer;
import com.domain.joboffers.offerfacade.OfferReposiotry;

import java.util.ArrayList;
import java.util.List;

public class OfferReposiotryTestImpl implements OfferReposiotry {
    List<Offer> offers = new ArrayList<>();

    @Override
    public Offer save(Offer offer) {
        offers.add(offer);
        return offer;
    }
}
