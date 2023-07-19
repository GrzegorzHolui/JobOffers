package com.domain.joboffers.loginandregisterfacade;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class LoginAndRegisterFacadeConfiguration {

    @Bean
    public LoginRepository loginRepository() {
        return new LoginRepository() {
            @Override
            public User save(User user) {
                return null;
            }

            @Override
            public Optional<User> findByUserName(String username) {
                return Optional.empty();
            }
        };
    }

    @Bean
    public LoginAndRegisterFacade loginAndRegisterFacade(LoginRepository loginRepository, UserModelMapper userModelMapper) {
        return new LoginAndRegisterFacade(loginRepository, userModelMapper);
    }


    @Bean
    public LoginAndRegisterFacade loginAndRegisterFacadeTest(LoginRepository loginRepository) {
        UserModelMapper userModelMapper = new UserModelMapperImpl();
        return new LoginAndRegisterFacade(loginRepository, userModelMapper);
    }


}