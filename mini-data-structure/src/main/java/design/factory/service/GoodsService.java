package design.factory.service;

import design.factory.impl.CouponCommodityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoodsService {
    private Logger logger = LoggerFactory.getLogger(CouponCommodityService.class);

    public Boolean deliverGoods(String uId, String commodityId, String bizId) {
        return true;
    }
}
