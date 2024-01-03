package design.factory.service;

import design.factory.dto.CouponResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CouponService {
    private Logger logger = LoggerFactory.getLogger(CouponService.class);
    public CouponResult sendCoupon(String uId, String commodityId, String bizId) {
        return new CouponResult("0000","SUCCESS");
    }
}
