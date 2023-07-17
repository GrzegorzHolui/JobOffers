package com.domain.joboffers.loginandregisterfacade.dto;

import lombok.Builder;

@Builder
public record UserDto(String id, String userName, String password) {

}