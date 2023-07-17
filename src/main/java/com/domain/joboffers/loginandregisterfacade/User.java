package com.domain.joboffers.loginandregisterfacade;

import lombok.Builder;

@Builder
record User(String id, String userName, String password) {

}
