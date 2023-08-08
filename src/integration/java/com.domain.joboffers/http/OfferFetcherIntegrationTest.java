package com.domain.joboffers.http;

import com.domain.joboffers.SampleJobOfferResponse;
import com.domain.joboffers.offerfacade.OfferFetchable;
import com.domain.joboffers.offerfacade.dto.OfferResponseDto;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.http.Fault;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.web.server.ResponseStatusException;
import wiremock.org.apache.hc.core5.http.HttpStatus;

import java.util.List;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class OfferFetcherIntegrationTest implements SampleJobOfferResponse {

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";

    @RegisterExtension
    public static WireMockExtension wireMockServer = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort())
            .build();

    OfferFetchable offerFetchable = new OfferFetcherIntegrationTestConfig()
            .remoteOfferFetcher(wireMockServer.getPort(), 1000, 1000);

    @Test
    void shouldReturn_200_ok_and_zero_offers() {

        wireMockServer.stubFor(WireMock.get("/offers").willReturn(WireMock.aResponse().withStatus(HttpStatus.SC_OK)
                .withHeader(CONTENT_TYPE, APPLICATION_JSON).withBody(zeroJobOffer())
        ));

        // step 2: scheduler ran 1st time and made GET to external server and system added 0 offers to database

        // given && when
        List<OfferResponseDto> offerResponseDtos = offerFetchable.fetchOffers();

        // then
        assertThat(offerResponseDtos).hasSize(0);
    }

    @Test
    void should_return_INTERNAL_SERVER_ERROR_when_CONNECTION_RESET_BY_PEER() {

        wireMockServer.stubFor(WireMock.get("/offers").willReturn(WireMock.aResponse().withStatus(HttpStatus.SC_OK)
                .withHeader(CONTENT_TYPE, APPLICATION_JSON).withBody(zeroJobOffer())
                .withFault(Fault.CONNECTION_RESET_BY_PEER)));

        // step 2: scheduler ran 1st time and made GET to external server and system added 0 offers to database

        // given && when
        Throwable thrown = catchThrowable(() -> offerFetchable.fetchOffers());
        // then
        assertThat(thrown).isInstanceOf(ResponseStatusException.class);
        assertThat(thrown.getMessage()).isEqualTo("500 INTERNAL_SERVER_ERROR");
    }

    @Test
    void should_return_INTERNAL_SERVER_ERROR_when_empty_response() {

        wireMockServer.stubFor(WireMock.get("/offers").willReturn(WireMock.aResponse()
                .withStatus(HttpStatus.SC_OK)
                .withHeader(CONTENT_TYPE, APPLICATION_JSON)
                .withFault(Fault.EMPTY_RESPONSE)));

        // step 2: scheduler ran 1st time and made GET to external server and system added 0 offers to database

        // given && when
        Throwable thrown = catchThrowable(() -> offerFetchable.fetchOffers());
        // then
        assertThat(thrown).isInstanceOf(ResponseStatusException.class);
        assertThat(thrown.getMessage()).isEqualTo("500 INTERNAL_SERVER_ERROR");
    }

    @Test
    void should_return_INTERNAL_SERVER_ERROR_when_malformed_response_chunk() {

        wireMockServer.stubFor(WireMock.get("/offers").willReturn(WireMock.aResponse()
                .withStatus(HttpStatus.SC_OK)
                .withHeader(CONTENT_TYPE, APPLICATION_JSON)
                .withFault(Fault.MALFORMED_RESPONSE_CHUNK)));

        // step 2: scheduler ran 1st time and made GET to external server and system added 0 offers to database

        // given && when
        Throwable thrown = catchThrowable(() -> offerFetchable.fetchOffers());

        // then
        assertThat(thrown).isInstanceOf(ResponseStatusException.class);
        assertThat(thrown.getMessage()).isEqualTo("500 INTERNAL_SERVER_ERROR");
    }

    @Test
    void should_return_INTERNAL_SERVER_ERROR_when_random_data_then_close() {

        wireMockServer.stubFor(WireMock.get("/offers").willReturn(WireMock.aResponse()
                .withStatus(HttpStatus.SC_OK)
                .withHeader(CONTENT_TYPE, APPLICATION_JSON)
                .withFault(Fault.RANDOM_DATA_THEN_CLOSE)));

        // step 2: scheduler ran 1st time and made GET to external server and system added 0 offers to database

        // given && when
        Throwable thrown = catchThrowable(() -> offerFetchable.fetchOffers());

        // then
        assertThat(thrown).isInstanceOf(ResponseStatusException.class);
        assertThat(thrown.getMessage()).isEqualTo("500 INTERNAL_SERVER_ERROR");
    }

    @Test
    void shouldReturn_0_offers_when_status_is_204_no_content() {

        wireMockServer.stubFor(WireMock.get("/offers").willReturn(WireMock.aResponse()
                .withStatus(HttpStatus.SC_NO_CONTENT)
                .withHeader(CONTENT_TYPE, APPLICATION_JSON)
                .withBody(zeroJobOffer())
        ));

        // step 2: scheduler ran 1st time and made GET to external server and system added 0 offers to database

        // given && when
        Throwable thrown = catchThrowable(() -> offerFetchable.fetchOffers());

        // then
        assertThat(thrown).isInstanceOf(ResponseStatusException.class);
        assertThat(thrown.getMessage()).isEqualTo("204 NO_CONTENT");
    }

    @Test
    void shouldReturn_0_offers_when_response_delay_is_1500() {

        int readTimeout = 1000;
        int connectionTimeout = 1500;

        OfferFetchable offerFetchable = new OfferFetcherIntegrationTestConfig()
                .remoteOfferFetcher(wireMockServer.getPort(), connectionTimeout, readTimeout);


        wireMockServer.stubFor(WireMock.get("/offers").willReturn(WireMock.aResponse()
                .withStatus(HttpStatus.SC_NO_CONTENT)
                .withHeader(CONTENT_TYPE, APPLICATION_JSON)
                .withBody(zeroJobOffer())
                .withFixedDelay(5000)
        ));

        // step 2: scheduler ran 1st time and made GET to external server and system added 0 offers to database

        // given && when
        Throwable thrown = catchThrowable(() -> offerFetchable.fetchOffers());

        // then
        assertThat(thrown).isInstanceOf(ResponseStatusException.class);
        assertThat(thrown.getMessage()).isEqualTo("500 INTERNAL_SERVER_ERROR");
    }

    @Test
    void shouldReturn_401_UNAUTHORIZED_when_server_return_unauthorized_status() {

        wireMockServer.stubFor(WireMock.get("/offers").willReturn(WireMock.aResponse()
                .withStatus(HttpStatus.SC_UNAUTHORIZED)
                .withHeader(CONTENT_TYPE, APPLICATION_JSON)
        ));

        // step 2: scheduler ran 1st time and made GET to external server and system added 0 offers to database

        // given && when
        Throwable thrown = catchThrowable(() -> offerFetchable.fetchOffers());

        // then
        assertThat(thrown).isInstanceOf(ResponseStatusException.class);
        assertThat(thrown.getMessage()).isEqualTo("401 UNAUTHORIZED");
    }


}