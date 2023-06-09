package com.domain.joboffers.offerfacade;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.DuplicateKeyException;

class OfferRepositoryTestImpl implements OfferRepository {
    Map<String, Offer> dataBase = new HashMap<>();

    @Override
    public Offer save(Offer entity) {
        if (dataBase.values().stream().anyMatch(offer -> offer.linkToOffer().equals(offer.linkToOffer()))) {
            throw new DuplicateKeyException(String.format("Offer with offerUrl [%s] already exists", entity.linkToOffer()));
        }
        String id = UUID.randomUUID().toString();
        Offer offer = Offer.builder()
                .id(id)
                .linkToOffer(entity.linkToOffer())
                .jobName(entity.jobName())
                .nameOfCompany(entity.nameOfCompany())
                .earnings(entity.earnings())
                .build();

        dataBase.put(offer.id(), offer);
        return offer;
    }

    @Override
    public Optional<Offer> findOfferById(String id) {
        return Optional.ofNullable(dataBase.get(id));
    }
}