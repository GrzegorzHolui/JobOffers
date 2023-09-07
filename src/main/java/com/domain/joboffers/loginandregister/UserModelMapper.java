package com.domain.joboffers.loginandregister;

import com.domain.joboffers.loginandregister.dto.UserDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface UserModelMapper {
    UserDto mapUserToUserDto(User user);
}
