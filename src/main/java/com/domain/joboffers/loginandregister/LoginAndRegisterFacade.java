package com.domain.joboffers.loginandregister;

import com.domain.joboffers.loginandregister.dto.RegisterUserDto;
import com.domain.joboffers.loginandregister.dto.RegistrationResultDto;
import com.domain.joboffers.loginandregister.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;


@RequiredArgsConstructor
@AllArgsConstructor
public class LoginAndRegisterFacade {
    private static final String USER_NOT_FOUND = "User not found";

    private LoginRepository loginRepository;
    private UserModelMapper userModelMapper;

    public UserDto findByUserName(String username) {
        User user = loginRepository.findByUsername(username)
                .orElseThrow(() ->
                new BadCredentialsException(USER_NOT_FOUND));
        UserDto userDto = userModelMapper.mapUserToUserDto(user);
        return userDto;
    }

    public RegistrationResultDto register(RegisterUserDto registerUserDto) {
        User user = User.builder()
                .username(registerUserDto.username())
                .password(registerUserDto.password())
                .build();
        User savedUser = loginRepository.save(user);
        return new RegistrationResultDto(savedUser.id(), savedUser.username(), true);
    }
}

// Compare this snippet from src/main/java/com/domain/joboffers/loginandregister/LoginRepository.java: