package com.domain.joboffers.offerfacade;


import com.domain.joboffers.offerfacade.dto.OfferResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;


public interface OfferFetchable {
    List<OfferResponseDto> fetchOffers();
}
