package com.domain.joboffers.offerfacade;

import com.domain.joboffers.offerfacade.dto.OfferRequestDto;
import com.domain.joboffers.offerfacade.dto.OfferResponseDto;
import com.domain.joboffers.offerfacade.dto.OfferResponseWithStrings;
import org.mapstruct.Mapper;


interface OfferModelMapper {
    OfferResponseDto mapOfferResponseDtoToOffer(Offer offer);

    Offer mapOfferRequestDtoToOffer(OfferRequestDto OfferRequestDto);

    Offer mapOfferResponseDtoToOffer(OfferResponseDto offerResponseDto);

}
