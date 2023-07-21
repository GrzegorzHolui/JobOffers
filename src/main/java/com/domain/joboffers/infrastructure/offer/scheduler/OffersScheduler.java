package com.domain.joboffers.infrastructure.offer.scheduler;

import com.domain.joboffers.offerfacade.OfferFacade;
import com.domain.joboffers.offerfacade.dto.OfferResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Log4j2
public class OffersScheduler {

    private final OfferFacade offerFacade;

    @Scheduled(fixedDelayString = "${offer.scheduler.request.delay}")
    public List<OfferResponseDto> fetchAllOffersAndSaveAllIfNotExists() {
        log.info("started fetching offers");
        List<OfferResponseDto> offerResponseDtos = offerFacade.fetchAllOffersAndSaveAllIfNotExists();
        return offerResponseDtos;
    }
}
