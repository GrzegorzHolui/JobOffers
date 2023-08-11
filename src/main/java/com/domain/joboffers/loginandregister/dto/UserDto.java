package com.domain.joboffers.loginandregister.dto;

import lombok.Builder;

@Builder
public record UserDto(String id, String username, String password) {

}