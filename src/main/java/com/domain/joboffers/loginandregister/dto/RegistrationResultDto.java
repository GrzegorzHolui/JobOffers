package com.domain.joboffers.loginandregister.dto;

import lombok.Builder;

@Builder
public record RegistrationResultDto(String id, String username, boolean created) {

}