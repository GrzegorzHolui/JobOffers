package com.domain.joboffers.offerfacade;

import com.domain.joboffers.offerfacade.dto.OfferFacadeResultDto;
import com.domain.joboffers.offerfacade.dto.OfferRequestDto;
import com.domain.joboffers.offerfacade.dto.OfferResponseDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
public class OfferFacade {

    private OfferValidator offerValidator;
    private ValidatorMessageConverter validatorMessageConverter;
    private OfferModelMapper offerModelMapper;
    private OfferRepository offerRepository;
    private OfferService offerService;


    public OfferFacadeResultDto saveOffer(OfferRequestDto offerRequestDto) {
        Offer offer = offerModelMapper.mapOfferRequestDtoToOffer(offerRequestDto);
        List<ValidatorMessage> validatorMessages = offerValidator.validateData(offer);
        List<String> validatorMessage = validatorMessageConverter.convertMessagesToString(validatorMessages);
        if (validatorMessage.isEmpty()) {
            Offer save = offerRepository.save(offer);
            OfferResponseDto offerResponseDto = offerModelMapper.mapOfferResponseDtoToOffer(save);
            return new OfferFacadeResultDto(validatorMessage, offerResponseDto);
        }
        return new OfferFacadeResultDto(validatorMessage, null);
    }

    public List<OfferResponseDto> fetchAllOffersAndSaveAllIfNotExists() {
        var list = offerService.fetchAllOffersAndSaveAllIfNotExists();
        return list.stream()
                .map(offer -> offerModelMapper.mapOfferResponseDtoToOffer(offer))
                .toList();
    }

    public OfferResponseDto findOfferById(String id) {
        Optional<Offer> offerById = offerRepository.findOfferById(id);
        return offerById.map(offer -> offerModelMapper.mapOfferResponseDtoToOffer(offer))
                .orElseThrow(() -> new OfferNotFoundException(id));
    }

    public List<OfferResponseDto> findAllOffers() {
        List<Offer> allOffers = offerRepository.findAll();
        List<OfferResponseDto> result = allOffers.stream().map(offer -> offerModelMapper.mapOfferResponseDtoToOffer(offer))
                .toList();
        return result;
    }

}