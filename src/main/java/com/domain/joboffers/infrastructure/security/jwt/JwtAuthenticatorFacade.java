package com.domain.joboffers.infrastructure.security.jwt;

import com.domain.joboffers.infrastructure.loginandregister.controller.dto.JwtResponseDto;
import com.domain.joboffers.infrastructure.loginandregister.controller.dto.TokenRequestDto;
import lombok.AllArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class JwtAuthenticatorFacade {

    private AuthenticationManager authenticationManager;

    public JwtResponseDto authenticateAndGenerateToken(TokenRequestDto loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
        User user = (User) authenticate.getPrincipal();
//        String token = createToken(user);
        String username = user.getUsername();
        return JwtResponseDto.builder()
//                .token(token)
//                .username(username)
                .build();
    }
}