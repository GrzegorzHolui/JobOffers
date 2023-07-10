package com.domain.joboffers.loginandregisterfacade;

import com.domain.joboffers.offerfacade.*;
import com.domain.joboffers.offerfacade.dto.EarningsDto;
import com.domain.joboffers.offerfacade.dto.OfferDto;
import com.domain.joboffers.offerfacade.dto.OfferFacadeResultDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;


class LoginAndRegisterFacadeTest {

    private OfferFacadeConfiguration offerFacadeConfiguration;
    private OfferModelMapper offerModelMapper = Mappers.getMapper(OfferModelMapper.class);
    private OfferFacade offerFacade;


    @BeforeEach
    void initializeOfferFacade() {
        OfferReposiotry offerReposiotry = new OfferReposiotryTestImpl();
        offerFacadeConfiguration = new OfferFacadeConfiguration();
        offerFacade = offerFacadeConfiguration.offerFacadeTest(offerReposiotry, offerModelMapper);
    }

    @Test
    @DisplayName("OfferFacade_saveOffer")
    void saveOffer_thenShouldReturnThatOfferHasBeenCreatedProperly() {
        Earnings earnings = new Earnings(BigDecimal.valueOf(2000), BigDecimal.valueOf(3000));
        Offer offer = new Offer("https://www.youtube.com", "jobname",
                "nameOfCompany", earnings);

        //when
        OfferFacadeResultDto offerResult = offerFacade.saveOffer(offer);

        //then
        EarningsDto expectedEarnings = new EarningsDto(BigDecimal.valueOf(2000), BigDecimal.valueOf(3000));
        OfferDto expectedOffer = new OfferDto("https://www.youtube.com", "jobname",
                "nameOfCompany", expectedEarnings);
        OfferFacadeResultDto offerFacadeResultDto = new OfferFacadeResultDto(Collections.emptyList(), expectedOffer);

        assertThat(offerResult).isEqualTo(offerFacadeResultDto);

    }
}