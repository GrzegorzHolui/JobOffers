package com.domain.joboffers.loginandregister;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginAndRegisterFacadeConfiguration {


    @Bean
    public LoginAndRegisterFacade loginAndRegisterFacade(LoginRepository loginRepository, UserModelMapper userModelMapper) {
        return new LoginAndRegisterFacade(loginRepository, userModelMapper);
    }


    public LoginAndRegisterFacade loginAndRegisterFacadeTest(LoginRepository loginRepository) {
        UserModelMapper userModelMapper = new UserModelMapperImpl();
        return new LoginAndRegisterFacade(loginRepository, userModelMapper);
    }


}