package com.domain.joboffers.loginandregister;

import com.domain.joboffers.loginandregister.dto.RegisterUserDto;
import com.domain.joboffers.loginandregister.dto.RegistrationResultDto;
import com.domain.joboffers.loginandregister.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.BadCredentialsException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LoginAndRegisterFacadeTest {

    LoginAndRegisterFacade loginAndRegisterFacade;

    @BeforeEach
    void initializeLoginRegisterFacade() {
        LoginAndRegisterFacadeConfiguration loginAndRegisterFacadeConfiguration =
                new LoginAndRegisterFacadeConfiguration();
        loginAndRegisterFacade = loginAndRegisterFacadeConfiguration
                .loginAndRegisterFacadeTest(new LoginRepositoryTestImpl());
    }

    @Test
    void shouldReturnThatUserHasBeenRegistered() {
        //given
        RegisterUserDto registerUserDto = new RegisterUserDto("name", "password");
        //when
        RegistrationResultDto resultRegister = loginAndRegisterFacade.register(registerUserDto);
        //then
        RegistrationResultDto expectedRegister =
                RegistrationResultDto.builder()
                        .id(resultRegister.id())
                        .username("name")
                        .created(true)
                        .build();
        assertThat(resultRegister).isEqualTo(expectedRegister);
    }

    @Test
    void shouldReturnThatUserHasBeenFoundByUserName() {
        //given
        RegisterUserDto registerUserDto = new RegisterUserDto("name", "password");
        RegistrationResultDto resultRegister = loginAndRegisterFacade.register(registerUserDto);
        String username = registerUserDto.username();
        //when
        UserDto resultUserName = loginAndRegisterFacade.findByUserName(username);
        //then
        UserDto expectedUserDto = UserDto.builder()
                .id(resultRegister.id())
                .username(registerUserDto.username())
                .password(registerUserDto.password())
                .build();

        assertThat(resultUserName).isEqualTo(expectedUserDto);
    }

    @Test
    void shouldReturnThatUserHasNotBeenFoundByUserName() {
        //given
        RegisterUserDto registerUserDto = new RegisterUserDto("name", "password");
        RegistrationResultDto resultRegister = loginAndRegisterFacade.register(registerUserDto);
        String username = registerUserDto.username();
//        when then
        assertThrows(BadCredentialsException.class,
                () -> loginAndRegisterFacade.findByUserName("username"),"User not found");
    }
}