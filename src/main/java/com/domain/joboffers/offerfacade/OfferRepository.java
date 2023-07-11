package com.domain.joboffers.offerfacade;

import java.util.List;
import java.util.Optional;

interface OfferRepository {
    Offer save(Offer offer);

    Optional<Offer> findOfferById(String id);

    List<Offer> findAllOffers();
}
