package com.domain.joboffers.offerfacade;

import com.domain.joboffers.offerfacade.dto.OfferRequestDto;
import com.domain.joboffers.offerfacade.dto.OfferResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface OfferModelMapper {
    OfferResponseDto mapOfferResponseDtoToOffer(Offer offer);

    Offer mapOfferRequestDtoToOffer(OfferRequestDto OfferRequestDto);

}
