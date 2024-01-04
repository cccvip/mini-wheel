/*
 * All Rights Reserved.
 *
 */
package design.builder.design.floor;


import design.builder.design.Matter;

import java.math.BigDecimal;

/**
 * PreBrick.
 *
 * @author Carl, 2024-01-04 14:09
 */
public class PreFloor implements Matter {

    @Override
    public String scene() {
        return "Pre 地板";
    }

    @Override
    public String brand() {
        return "国内二线品牌";
    }

    @Override
    public String model() {
        return "pre model";
    }

    @Override
    public BigDecimal price() {
        return new BigDecimal(2.5);
    }

    @Override
    public String desc() {
        return "pre 地板";
    }
}
