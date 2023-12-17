package com.domain.joboffers.loginandregister;

import com.domain.joboffers.loginandregister.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
// todo zmienic to new w LoginAndRegisterFacadeConfiguration
class UserModelMapperImpl implements UserModelMapper{
    @Override
    public UserDto mapUserToUserDto(User user) {
        return UserDto.builder()
                .id(user.id())
                .username(user.username())
                .password(user.password())
                .build();
    }
}
