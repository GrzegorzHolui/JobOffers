package com.domain.joboffers.offerfacade;

import com.domain.joboffers.offerfacade.dto.EarningsRequestDto;
import com.domain.joboffers.offerfacade.dto.OfferFacadeResultDto;
import com.domain.joboffers.offerfacade.dto.OfferRequestDto;
import com.domain.joboffers.offerfacade.dto.OfferResponseDto;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.dao.DuplicateKeyException;

import java.math.BigDecimal;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class OfferFacadeTest {

    private OfferFacadeConfiguration offerFacadeConfiguration;
    private OfferModelMapper offerModelMapper = Mappers.getMapper(OfferModelMapper.class);
    private OfferFacade offerFacade;


    @BeforeEach
    void initializeOfferFacade() {
        OfferRepository offerRepository = new OfferRepositoryTestImpl();
        offerFacadeConfiguration = new OfferFacadeConfiguration();
        offerFacade = offerFacadeConfiguration.offerFacadeTest(offerRepository, offerModelMapper);
    }

    @Test
    @DisplayName("OfferFacade_saveOffer")
    void saveOffer_thenShouldReturnThatOfferHasBeenCreatedProperly() {
        EarningsRequestDto earnings = new EarningsRequestDto(BigDecimal.valueOf(2000), BigDecimal.valueOf(3000));
        OfferRequestDto offer = new OfferRequestDto("https://www.youtube.com", "jobname",
                "nameOfCompany", earnings);

        //when
        OfferFacadeResultDto offerResult = offerFacade.saveOffer(offer);

        //then
        String id = offerResult.offerFacade().id();
        EarningsRequestDto expectedEarnings = earnings;
        OfferResponseDto expectedOffer = new OfferResponseDto(id, "https://www.youtube.com", "jobname",
                "nameOfCompany", expectedEarnings);
        OfferFacadeResultDto offerFacadeResultDto = new OfferFacadeResultDto(Collections.emptyList(), expectedOffer);

        assertThat(offerResult).isEqualTo(offerFacadeResultDto);
    }

    @Test
    @DisplayName("OfferFacade_saveOfferThrowException")
    void saveOffer_thenShouldThrowDuplicateKeyException() {
        EarningsRequestDto earnings = new EarningsRequestDto(BigDecimal.valueOf(2000), BigDecimal.valueOf(3000));
        OfferRequestDto offer = new OfferRequestDto("https://www.youtube.com", "jobname",
                "nameOfCompany", earnings);

        //when
        offerFacade.saveOffer(offer);
        Throwable thrown = catchThrowable(() -> offerFacade.saveOffer(offer));

        //then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(DuplicateKeyException.class)
                .hasMessage("Offer with offerUrl [https://www.youtube.com] already exists");
    }

    @Test
    @DisplayName("OfferFacade_findOfferById")
    void findOfferById_ThenShouldReturnOffer() {
        //given
        EarningsRequestDto earnings = new EarningsRequestDto(BigDecimal.valueOf(2000), BigDecimal.valueOf(3000));
        OfferRequestDto offer = new OfferRequestDto("https://www.youtube.com", "jobname",
                "nameOfCompany", earnings);

        OfferResponseDto offerFacadeResultDto = offerFacade.saveOffer(offer).offerFacade();

        //when
        OfferResponseDto offerById = offerFacade.findOfferById(offerFacadeResultDto.id());

        //then
        OfferResponseDto expectedOffer = OfferResponseDto.builder()
                .id(offerFacadeResultDto.id())
                .linkToOffer(offerFacadeResultDto.linkToOffer())
                .jobName(offerFacadeResultDto.jobName())
                .nameOfCompany(offerFacadeResultDto.nameOfCompany())
                .earnings(offerFacadeResultDto.earnings())
                .build();

        assertThat(offerById).isEqualTo(expectedOffer);
    }


    @Test
    @DisplayName("OfferFacade_findOfferByIdThrowException")
    void findOfferById_ThenShouldReturnOfferNotFoundException() {
        //given
        EarningsRequestDto earnings = new EarningsRequestDto(BigDecimal.valueOf(2000), BigDecimal.valueOf(3000));
        OfferRequestDto offer = new OfferRequestDto("https://www.youtube.com", "jobname",
                "nameOfCompany", earnings);

        //when
        Throwable thrown = catchThrowable(() -> offerFacade.findOfferById("cos"));

        //then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(OfferNotFoundException.class)
                .hasMessage("Offer with id cos not found");

    }
}