package com.domain.joboffers.offerfacade;

import com.domain.joboffers.offerfacade.dto.OfferResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
class OfferService {

    OfferFetchable offerFatchable;
    OfferModelMapper offerModelMapper;
    OfferRepository offerRepository;

    public List<Offer> fetchAllOffersAndSaveAllIfNotExists() {
        List<Offer> offerResponses = fetchOffers();
        List<Offer> offersResult = filterNotExistingOffers(offerResponses);
        return offerRepository.saveAll(offersResult);
    }

    private List<Offer> fetchOffers() {
        return offerFatchable.fetchOffers()
                .stream().map(offerResponseDto -> offerModelMapper.mapOfferResponseDtoToOffer(offerResponseDto))
                .toList();
    }

    private List<Offer> filterNotExistingOffers(List<Offer> jobOffers) {
        return jobOffers.stream()
                .filter(offerDto -> !offerDto.linkToOffer().isEmpty())
                .filter(offerDto -> !offerRepository.existsByOfferUrl(offerDto.linkToOffer()))
                .collect(Collectors.toList());
    }
}
