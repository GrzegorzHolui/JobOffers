package com.domain.joboffers.loginandregisterfacade;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

class LoginRepositoryTestImpl implements LoginRepository {
    Map<String, User> dataBase = new HashMap<>();

    @Override
    public User save(User user) {
        String id = UUID.randomUUID().toString();
        User saveUser = User.builder()
                .id(id)
                .userName(user.userName())
                .password(user.password())
                .build();
        dataBase.put(saveUser.id(), saveUser);
        return saveUser;
    }

    @Override
    public Optional<User> findByUserName(String username) {
        return dataBase.values().stream().filter(user -> user.userName().equals(username)).findFirst();
    }
}
