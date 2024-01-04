/*
 * All Rights Reserved.
 *
 */
package design.builder.design.brick;


import design.builder.design.Matter;

import java.math.BigDecimal;

/**
 * PreBrick.
 *
 * @author Carl, 2024-01-04 14:09
 */
public class PafBrick implements Matter {

    @Override
    public String scene() {
        return "Paf 地砖";
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
        return new BigDecimal(2.5);
    }

    @Override
    public String desc() {
        return "Paf 高品质地砖";
    }
}
