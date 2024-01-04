/*
 * All Rights Reserved.
 *
 */
package design.builder.design.coating;


import design.builder.design.Matter;

import java.math.BigDecimal;

/**
 * PreBrick.
 *
 * @author Carl, 2024-01-04 14:09
 */
public class PafCoating implements Matter {

    @Override
    public String scene() {
        return "Paf 涂料";
    }

    @Override
    public String brand() {
        return "国内一线品牌";
    }

    @Override
    public String model() {
        return "Paf 涂料 model";
    }

    @Override
    public BigDecimal price() {
        return new BigDecimal(11.5);
    }

    @Override
    public String desc() {
        return "Paf 涂料";
    }
}
