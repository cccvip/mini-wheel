package design.factory.impl;

import design.factory.ICommodity;
import design.factory.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class CardCommodityService implements ICommodity {

    private Logger logger = LoggerFactory.getLogger(CouponCommodityService.class);

    CardService cardService=new CardService();

    @Override
    public void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap) throws Exception {
        cardService.grantToken(uId, bizId);
    }
}
