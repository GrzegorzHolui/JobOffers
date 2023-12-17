package com.domain.joboffers.loginandregister;

import com.domain.joboffers.loginandregister.dto.UserDto;
import org.mapstruct.Mapper;

interface UserModelMapper {
    UserDto mapUserToUserDto(User user);
}
