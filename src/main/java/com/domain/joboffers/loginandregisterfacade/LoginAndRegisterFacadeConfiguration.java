package com.domain.joboffers.loginandregisterfacade;

public class LoginAndRegisterFacadeConfiguration {

    public LoginAndRegisterFacade loginAndRegisterFacade() {
        return new LoginAndRegisterFacade();
    }


    public LoginAndRegisterFacade loginAndRegisterFacadeTest(LoginRepository loginRepository) {
        UserModelMapper userModelMapper = new UserModelMapperImpl();
        return new LoginAndRegisterFacade(loginRepository, userModelMapper);
    }


}