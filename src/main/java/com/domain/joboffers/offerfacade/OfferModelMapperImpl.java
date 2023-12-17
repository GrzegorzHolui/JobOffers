package com.domain.joboffers.offerfacade;

import com.domain.joboffers.offerfacade.dto.EarningsRequestDto;
import com.domain.joboffers.offerfacade.dto.OfferRequestDto;
import com.domain.joboffers.offerfacade.dto.OfferResponseDto;
import org.springframework.stereotype.Component;

@Component
// todo zmienic to new w OfferFacadeConfiguration
class OfferModelMapperImpl implements OfferModelMapper {
    @Override
    public OfferResponseDto mapOfferResponseDtoToOffer(Offer offer) {
        return OfferResponseDto.builder()
                .id(offer.id())
                .linkToOffer(offer.linkToOffer())
                .earnings(EarningsRequestDto.builder()
                        .maxSalary(offer.earnings().maxSalary())
                        .minSalary(offer.earnings().minSalary())
                        .build())
                .jobName(offer.jobName())
                .nameOfCompany(offer.nameOfCompany())
                .build();
    }

    @Override
    public Offer mapOfferRequestDtoToOffer(OfferRequestDto OfferRequestDto) {
        return Offer.builder()
                .linkToOffer(OfferRequestDto.linkToOffer())
                .earnings(Earnings.builder()
                        .maxSalary(OfferRequestDto.earnings().maxSalary())
                        .minSalary(OfferRequestDto.earnings().minSalary())
                        .build())
                .jobName(OfferRequestDto.jobName())
                .nameOfCompany(OfferRequestDto.nameOfCompany())
                .build();
    }

    @Override
    public Offer mapOfferResponseDtoToOffer(OfferResponseDto offerResponseDto) {
        return Offer.builder()
                .id(offerResponseDto.id())
                .linkToOffer(offerResponseDto.linkToOffer())
                .earnings(Earnings.builder()
                        .maxSalary(offerResponseDto.earnings().maxSalary())
                        .minSalary(offerResponseDto.earnings().minSalary())
                        .build())
                .jobName(offerResponseDto.jobName())
                .nameOfCompany(offerResponseDto.nameOfCompany())
                .build();
    }
}
