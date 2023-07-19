package com.domain.joboffers.infrastructure.scheduler;

import com.domain.joboffers.offerfacade.OfferFacade;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Log4j2
public class OffersScheduler {

    private final OfferFacade offerFacade;

//    @Scheduled(cron = "*/1 * * * * *")
    public void fetchOffers() {
        log.info("started fetching offers");
        offerFacade.fetchAllOffersAndSaveAllIfNotExists();
    }
}
