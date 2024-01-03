package design.factory;

import java.util.Map;

/**
 * 发送奖品抽象接口
 */
public interface ICommodity {
    void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap) throws Exception;
}
