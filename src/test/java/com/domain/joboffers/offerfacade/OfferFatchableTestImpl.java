package com.domain.joboffers.offerfacade;

import com.domain.joboffers.offerfacade.dto.EarningsRequestDto;
import com.domain.joboffers.offerfacade.dto.OfferResponseDto;

import java.math.BigDecimal;
import java.util.List;

class OfferFatchableTestImpl implements OfferFetchable {
    @Override
    public List<OfferResponseDto> fetchOffers() {
        EarningsRequestDto earnings = new EarningsRequestDto(BigDecimal.valueOf(1000), BigDecimal.valueOf(3000));
        return List.of(
                new OfferResponseDto("id", "id", "asds", "1", earnings),
                new OfferResponseDto("assd", "id/1", "asds", "2", earnings),
                new OfferResponseDto("asddd", "id/2", "asds", "3", earnings),
                new OfferResponseDto("asfd", "id/3", "asds", "4", earnings),
                new OfferResponseDto("Junior", "Comarch", "1000", "https://someurl.pl/5", earnings),
                new OfferResponseDto("Mid", "Finanteq", "2000", "https://someother.pl/6", earnings)
        );
    }
}
