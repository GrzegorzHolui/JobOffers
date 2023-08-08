package com.domain.joboffers.http;

import com.domain.joboffers.infrastructure.offer.http.OfferHttpConfig;
import com.domain.joboffers.offerfacade.OfferFetchable;
import org.springframework.web.client.RestTemplate;

public class OfferFetcherIntegrationTestConfig extends OfferHttpConfig {
    public OfferFetchable remoteOfferFetcher(int port, int connectionTimeout,int readTimeout) {
        RestTemplate restTemplate = restTemplate(connectionTimeout, readTimeout, restTemplateResponseErrorHandler());
        return offerFetchable(restTemplate, "http://localhost", port);
    }
}
