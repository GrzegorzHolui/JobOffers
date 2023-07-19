package com.domain.joboffers;

import com.domain.joboffers.offerfacade.OfferFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JobOffersApplication implements ApplicationRunner {
    @Autowired
    OfferFacade offerFacade;

    public static void main(String[] args) {
        SpringApplication.run(JobOffersApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        offerFacade.fetchAllOffersAndSaveAllIfNotExists();
    }
}
