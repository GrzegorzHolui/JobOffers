package com.domain.joboffers.offerfacade;

import java.util.*;
import java.util.function.Function;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

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
    public boolean existsByLinkToOffer(String linkToOffer) {
        return false;
    }

    public boolean existsByOfferUrl(String offerUrl) {
        long count = dataBase.values()
                .stream()
                .filter(offer -> offer.linkToOffer().equals(offerUrl))
                .count();
        return count == 1;
    }


    @Override
    public <S extends Offer> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends Offer> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends Offer> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Offer> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Offer> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Offer> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Offer> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Offer> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Offer, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Offer> List<S> saveAll(Iterable<S> entities) {
        for (S entity : entities) {
            save(entity);
        }
        return (List<S>) entities;
    }

    @Override
    public Optional<Offer> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<Offer> findAll() {
        return dataBase.values().stream().toList();
    }

    @Override
    public List<Offer> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Offer entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Offer> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Offer> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Offer> findAll(Pageable pageable) {
        return null;
    }
}