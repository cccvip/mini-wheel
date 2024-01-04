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
public class PafFloor implements Matter {

    @Override
    public String scene() {
        return "Paf 地板";
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
        return new BigDecimal(6.5);
    }

    @Override
    public String desc() {
        return "Paf 地板";
    }
}
