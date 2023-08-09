package com.domain.joboffers.loginandregister;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface LoginRepository extends MongoRepository<User, String> {
    User save(User user);

    Optional<User> findByUserName(String username);
}
