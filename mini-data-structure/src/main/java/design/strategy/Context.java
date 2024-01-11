package design.strategy;

import java.math.BigDecimal;

//策略控制器
public class Context<T> {
    private ICouponDiscount<T> couponDiscount ;

    public Context(ICouponDiscount iCouponDiscount){
        couponDiscount = iCouponDiscount;
    }
    public BigDecimal discountAmount(T couponInfo, BigDecimal skuPrice) {
        return couponDiscount.discountAmount(couponInfo, skuPrice);
    }
}
