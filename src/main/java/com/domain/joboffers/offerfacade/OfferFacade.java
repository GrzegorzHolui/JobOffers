package com.domain.joboffers.offerfacade;

import com.domain.joboffers.offerfacade.dto.OfferFacadeResultDto;
import com.domain.joboffers.offerfacade.dto.OfferRequestDto;
import com.domain.joboffers.offerfacade.dto.OfferResponseDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@NoArgsConstructor
public class OfferFacade {

    OfferValidator offerValidator;
    ValidatorMessageConverter validatorMessageConverter;
    OfferModelMapper offerModelMapper;
    OfferRepository offerRepository;


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

    public OfferResponseDto findOfferById(String id) {
        Optional<Offer> offerById = offerRepository.findOfferById(id);
        return offerById.map(offer -> offerModelMapper.mapOfferResponseDtoToOffer(offer))
                .orElseThrow(() -> new OfferNotFoundException(id));
    }

}