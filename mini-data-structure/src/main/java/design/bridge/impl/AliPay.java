package design.bridge.impl;

import design.bridge.IPayMode;
import design.bridge.Pay;

import java.math.BigDecimal;

public class AliPay extends Pay {

    public AliPay(IPayMode payMode) {
        super(payMode);
    }

    @Override
    public String transfer(String uId, String tradeId, BigDecimal amount) {
        payMode.payCheck();
        return "ali transfer";
    }
}
