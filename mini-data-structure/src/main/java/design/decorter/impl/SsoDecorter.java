package design.decorter.impl;

import design.decorter.HandlerLogin;

//装饰器模式
public abstract class SsoDecorter implements HandlerLogin {
    HandlerLogin handlerLogin;

    public SsoDecorter(HandlerLogin handlerLogin){
        this.handlerLogin = handlerLogin;
    }

    public void ssoHandlerLogin(String userName){
        handlerLogin.preHandle(userName);
    }
}
