package design.factory.service;

import design.factory.impl.CouponCommodityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CardService {
    private Logger logger = LoggerFactory.getLogger(CouponCommodityService.class);
    public void grantToken(String uId, String bizId) {
        logger.info("兑换奖品 {}",uId);
    }
}
