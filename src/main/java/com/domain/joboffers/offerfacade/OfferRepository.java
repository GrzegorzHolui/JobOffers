package com.domain.joboffers.offerfacade;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface OfferRepository extends MongoRepository<Offer, String> {

    Optional<Offer> findOfferById(String id);

    boolean existsByLinkToOffer(String linkToOffer);

}
