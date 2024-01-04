/*
 * All Rights Reserved.
 *
 */
package design.builder.design.ceiling;


import design.builder.design.Matter;

import java.math.BigDecimal;

/**
 * PreBrick.
 *
 * @author Carl, 2024-01-04 14:09
 */
public class PafCeiling implements Matter {

    @Override
    public String scene() {
        return "Paf 吊顶";
    }

    @Override
    public String brand() {
        return "国内一线品牌";
    }

    @Override
    public String model() {
        return "Paf model";
    }

    @Override
    public BigDecimal price() {
        return new BigDecimal(10.5);
    }

    @Override
    public String desc() {
        return "Paf 吊顶";
    }
}
