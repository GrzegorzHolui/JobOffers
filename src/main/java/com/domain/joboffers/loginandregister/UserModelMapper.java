package com.domain.joboffers.loginandregister;

import com.domain.joboffers.loginandregister.dto.UserDto;

interface UserModelMapper {
    UserDto mapUserToUserDto(User user);
}
