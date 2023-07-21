package com.domain.joboffers.infrastructure.offer.controller;

import com.domain.joboffers.offerfacade.OfferFacade;
import com.domain.joboffers.offerfacade.dto.OfferRequestDto;
import com.domain.joboffers.offerfacade.dto.OfferResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/offers")
@AllArgsConstructor
public class OfferRestController {

    private final OfferFacade offerFacade;

    @PostMapping()
    public ResponseEntity<String> offers(@RequestBody OfferRequestDto offerRequestDto) {
        log.info(offerRequestDto);
        return ResponseEntity.ok("hehe");
    }

    @GetMapping()
    public ResponseEntity<List<OfferResponseDto>> offers() {
        return ResponseEntity.ok(offerFacade.findAllOffers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferResponseDto> offers(@PathVariable String id) {
        return ResponseEntity.ok(offerFacade.findOfferById(id));
    }


}
