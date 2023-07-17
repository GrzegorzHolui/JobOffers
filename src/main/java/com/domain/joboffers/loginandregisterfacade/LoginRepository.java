package com.domain.joboffers.loginandregisterfacade;

import java.util.Optional;

interface LoginRepository {
    User save(User user);

    Optional<User> findByUserName(String username);
}
