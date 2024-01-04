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
public class PreBrick implements Matter {

    @Override
    public String scene() {
        return "Pre 地砖";
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
        return new BigDecimal(1.5);
    }

    @Override
    public String desc() {
        return "pre应用层地砖";
    }
}
