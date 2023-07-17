package com.domain.joboffers.loginandregisterfacade.dto;

import lombok.Builder;

@Builder
public record RegistrationResultDto(String id, String username, boolean created) {

}