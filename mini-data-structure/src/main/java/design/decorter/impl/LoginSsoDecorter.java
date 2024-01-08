package design.decorter.impl;

import design.decorter.HandlerLogin;

public class LoginSsoDecorter extends SsoDecorter{

    public LoginSsoDecorter(HandlerLogin handlerLogin) {
        super(handlerLogin);
    }

    @Override
    public void preHandle(String userName) {
        handlerLogin.preHandle(userName);
        //DO 业务逻辑
    }
}
