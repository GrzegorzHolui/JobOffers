package com.domain.joboffers.infrastructure.loginandregister.controller;

//import com.domain.joboffers.infrastructure.security.jwt.JwtAuthenticatorFacade;

import com.domain.joboffers.infrastructure.loginandregister.controller.dto.JwtResponseDto;
import com.domain.joboffers.infrastructure.loginandregister.controller.dto.TokenRequestDto;
import com.domain.joboffers.infrastructure.security.jwt.JwtAuthenticatorFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/token")
public class TokenController {

    private final JwtAuthenticatorFacade jwtAuthenticatorFacade;

    @PostMapping()
    public ResponseEntity<JwtResponseDto> authenticateAndGenerateToken(@RequestBody TokenRequestDto tokenRequestDto) {
        final JwtResponseDto jwtResponseDto = jwtAuthenticatorFacade.authenticateAndGenerateToken(tokenRequestDto);
        return ResponseEntity.ok(jwtResponseDto);
    }
}
