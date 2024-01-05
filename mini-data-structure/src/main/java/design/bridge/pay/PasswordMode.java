package design.bridge.pay;

import design.bridge.IPayMode;

public class PasswordMode implements IPayMode {

    @Override
    public void payCheck() {
        System.out.println("密码识别");
    }
}
