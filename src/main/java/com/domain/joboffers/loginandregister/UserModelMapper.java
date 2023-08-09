package com.domain.joboffers.loginandregister;

import com.domain.joboffers.loginandregister.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface UserModelMapper {
    UserDto mapUserToUserDto(User user);
}
