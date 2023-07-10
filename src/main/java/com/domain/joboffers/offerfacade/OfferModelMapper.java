package com.domain.joboffers.offerfacade;

import com.domain.joboffers.offerfacade.dto.OfferDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OfferModelMapper {
    OfferDto mapOfferToOfferDto(Offer offer);
}
