package design.factory.impl;

import com.alibaba.fastjson2.JSON;
import design.factory.ICommodity;
import design.factory.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class GoodsCommodityService implements ICommodity {

    private Logger logger = LoggerFactory.getLogger(GoodsCommodityService.class);

    private GoodsService goodsService = new GoodsService();

    @Override
    public void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap) throws Exception {
        logger.info("send goods {}",uId);
        Boolean isSuccess = goodsService.deliverGoods(uId,commodityId,bizId);
        if (!isSuccess) {
            throw new RuntimeException("实物商品发放失败");
        }
    }
}
