/*
 * All Rights Reserved.
 *
 */
package design.builder.design;

import java.math.BigDecimal;

/**
 * Matter.
 * @author Carl, 2024-01-04 13:27
 */
public interface Matter {
    // 场景；地板、地砖、涂料、吊顶
    String scene();
    // 品牌
    String brand();
    // 型号
    String model();
    // 价格
    BigDecimal price();
    // 描述
    String desc();
}
