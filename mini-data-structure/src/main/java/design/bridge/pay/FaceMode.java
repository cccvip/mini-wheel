package design.bridge.pay;

import design.bridge.IPayMode;

public class FaceMode implements IPayMode {

    @Override
    public void payCheck() {
        System.out.println("人臉識別");
    }
}
