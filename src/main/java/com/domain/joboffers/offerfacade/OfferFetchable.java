package com.domain.joboffers.offerfacade;



import com.domain.joboffers.offerfacade.dto.OfferResponseDto;

import java.util.List;

 interface OfferFetchable {
    List<OfferResponseDto> fetchOffers();
}
