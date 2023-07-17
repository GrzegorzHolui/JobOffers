package com.domain.joboffers.loginandregisterfacade;

import com.domain.joboffers.loginandregisterfacade.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface UserModelMapper {
    UserDto mapUserToUserDto(User user);
}
