package com.domain.joboffers.offerfacade;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DuplicateKeyException;

class OfferRepositoryTestImpl implements OfferRepository {
    Map<String, Offer> dataBase = new HashMap<>();

    @Override
    public Offer save(Offer entity) {
        if (dataBase.values().stream().anyMatch(offer -> offer.linkToOffer().equals(offer.linkToOffer()))) {
            throw new DuplicateKeyException(String.format("Offer with offerUrl [%s] already exists", entity.linkToOffer()));
        }
        dataBase.put(entity.id(), entity);
        return entity;
    }
}
