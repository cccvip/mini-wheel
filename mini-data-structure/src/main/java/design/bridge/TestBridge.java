package design.bridge;

import design.bridge.impl.WxPay;
import design.bridge.pay.FaceMode;

import java.math.BigDecimal;

public class TestBridge {
    public static void main(String[] args) {
        Pay pay =   new WxPay(new FaceMode());
        System.out.println(pay.transfer("10001","10000",new BigDecimal(1000)));
    }

}
