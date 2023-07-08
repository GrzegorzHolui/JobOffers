package com.domain.joboffers.loginandregisterfacade;

import com.domain.joboffers.offerfacade.Earnings;
import com.domain.joboffers.offerfacade.Offer;
import com.domain.joboffers.offerfacade.OfferFacade;
import com.domain.joboffers.offerfacade.dto.EarningsDto;
import com.domain.joboffers.offerfacade.dto.OfferDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


class LoginAndRegisterFacadeTest {

    OfferFacade offerFacade;

    @BeforeEach
    void initializeOfferFacade() {
        offerFacade = new OfferFacade();
    }

    @Test
    void saveOffer_thenShouldReturnThatOfferHasBeenCreatedProperly() {
        Earnings earnings = new Earnings(BigDecimal.valueOf(2000), BigDecimal.valueOf(3000));
        Offer offer = new Offer("https:examplelink", "jobname",
                "nameOfCompany", earnings);

        //when
        OfferDto offerResult = offerFacade.saveOFfer(offer);

        //then
        EarningsDto earnings = new Earnings(BigDecimal.valueOf(2000), BigDecimal.valueOf(3000));
        OfferDto expectedOffer = new OfferDto("https:examplelink", "jobname",
                "nameOfCompany", earnings);

        assertThat(offerResult).isEqualTo(expectedOffer);

    }
}