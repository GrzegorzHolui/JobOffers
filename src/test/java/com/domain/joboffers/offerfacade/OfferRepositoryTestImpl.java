package com.domain.joboffers.offerfacade;

import java.util.*;
import java.util.stream.StreamSupport;

import org.springframework.dao.DuplicateKeyException;

class OfferRepositoryTestImpl implements OfferRepository {
    Map<String, Offer> dataBase = new HashMap<>();

    @Override
    public Offer save(Offer entity) {
        if (dataBase.values().stream().anyMatch(offer -> offer.linkToOffer().equals(entity.linkToOffer()))) {
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

    @Override
    public List<Offer> findAllOffers() {
        return dataBase.values().stream().toList();
    }

    @Override
    public boolean existsByOfferUrl(String offerUrl) {
        long count = dataBase.values()
                .stream()
                .filter(offer -> offer.linkToOffer().equals(offerUrl))
                .count();
        return count == 1;
    }

    @Override
    public List<Offer> saveAll(List<Offer> offersResult) {
        offersResult.forEach(this::save);
        return offersResult;
    }


}