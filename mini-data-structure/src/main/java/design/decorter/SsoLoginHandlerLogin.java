package design.decorter;

public class SsoLoginHandlerLogin implements HandlerLogin{
    @Override
    public void preHandle(String userName) {
        System.out.println(userName+"SSO LOGIN");
    }
}
