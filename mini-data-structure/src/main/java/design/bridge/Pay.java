package design.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

//定义桥接点
public abstract class Pay {
    protected Logger logger = LoggerFactory.getLogger(Pay.class);

    protected IPayMode payMode;

    public Pay(IPayMode payMode) {
        this.payMode = payMode;
    }

    //抽象支付方式
    public abstract String transfer(String uId, String tradeId, BigDecimal amount);
}
