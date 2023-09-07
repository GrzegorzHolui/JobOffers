package com.domain.joboffers.infrastructure.offer.http;

import com.domain.joboffers.offerfacade.OfferFetchable;
import com.domain.joboffers.offerfacade.dto.EarningsRequestDto;
import com.domain.joboffers.offerfacade.dto.OfferResponseDto;
import com.domain.joboffers.offerfacade.dto.OfferResponseWithStrings;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Log4j2
public class OfferHttpClient implements OfferFetchable {

    private final RestTemplate restTemplate;
    private final String uri;
    private final int port;

    //    http://ec2-3-120-147-150.eu-central-1.compute.amazonaws.com:5057/offers
    @Override
    public List<OfferResponseDto> fetchOffers() {
        log.info("Started fetching offers using http client");
        HttpHeaders headers = new HttpHeaders();
        final HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(headers);
        try {
            String urlForService = getUrlForService("/offers");
            final String url = UriComponentsBuilder.fromHttpUrl(urlForService).toUriString();
            ResponseEntity<List<OfferResponseWithStrings>> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
                    new ParameterizedTypeReference<>() {
                    });
            final List<OfferResponseWithStrings> body = response.getBody();
            if (body == null) {
                log.error("Response Body was null");
                throw new ResponseStatusException(HttpStatus.NO_CONTENT);
            }
            log.info("Success Response Body Returned: " + body);
            List<OfferResponseDto> offerResponseDtos = mapOfferResponseWithStringsToOfferResponse(body);
            return offerResponseDtos;
        } catch (ResourceAccessException e) {
            log.error("Error while fetching offers using http client: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String getUrlForService(String service) {
        return uri + ":" + port + service;
    }

    private List<OfferResponseDto> mapOfferResponseWithStringsToOfferResponse(List<OfferResponseWithStrings> body) {
        return body.stream().map(offerResponseWithStrings -> OfferResponseDto.builder()
                .linkToOffer(offerResponseWithStrings.offerUrl())
                .nameOfCompany(offerResponseWithStrings.company())
                .jobName(offerResponseWithStrings.title())
                .earnings(changeStringSalaryToEarningsReuquestDto(offerResponseWithStrings.salary()))
                .build()).toList();
    }

    private EarningsRequestDto changeStringSalaryToEarningsReuquestDto(String salary) {
        String[] split = salary.split(" – ");
        BigDecimal minSalary = BigDecimal.valueOf(Integer.parseInt(split[0].replace(" ", "")
                .replaceAll("[^0-9]", "")));
        BigDecimal maxSalary = BigDecimal.valueOf(Integer.parseInt(split[1].replace(" ", "")
                .replaceAll("[^0-9]", "")));
        return new EarningsRequestDto(minSalary, maxSalary);
    }

}
