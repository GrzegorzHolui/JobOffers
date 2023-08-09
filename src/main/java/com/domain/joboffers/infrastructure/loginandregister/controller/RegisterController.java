package com.domain.joboffers.infrastructure.loginandregister.controller;

import com.domain.joboffers.loginandregister.LoginAndRegisterFacade;
import com.domain.joboffers.loginandregister.dto.RegisterUserDto;
import com.domain.joboffers.loginandregister.dto.RegistrationResultDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RegisterController {

    private final LoginAndRegisterFacade loginAndRegisterFacade;
    private final PasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResultDto> register(@RequestBody RegisterUserDto registerUserDto) {
        String encodedPassword = bCryptPasswordEncoder.encode(registerUserDto.password());
        RegistrationResultDto registrationResult = loginAndRegisterFacade.register(new RegisterUserDto
                (registerUserDto.username(), encodedPassword));
        return ResponseEntity.status(HttpStatus.CREATED).body(registrationResult);
    }
}
