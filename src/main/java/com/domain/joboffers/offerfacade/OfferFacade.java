package com.domain.joboffers.offerfacade;

import com.domain.joboffers.offerfacade.dto.OfferDto;
import com.domain.joboffers.offerfacade.dto.OfferFacadeResultDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
public class OfferFacade {

    OfferValidator offerValidator;
    ValidatorMessageConverter validatorMessageConverter;
    OfferModelMapper offerModelMapper;
    OfferReposiotry offerReposiotry;


    public OfferFacadeResultDto saveOffer(Offer offer) {
        List<ValidatorMessage> validatorMessages = offerValidator.validateData(offer);
        List<String> validatorMessage = validatorMessageConverter.convertMessagesToString(validatorMessages);
        if (validatorMessage.isEmpty()) {
//            offerReposiotry.save(offer);
            OfferDto offerDto = offerModelMapper.mapOfferToOfferDto(offer);
            return new OfferFacadeResultDto(validatorMessage, offerDto);
        }
        return new OfferFacadeResultDto(validatorMessage, null);
    }

}