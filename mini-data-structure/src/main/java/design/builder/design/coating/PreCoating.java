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
public class PreCoating implements Matter {

    @Override
    public String scene() {
        return "Pre 涂料";
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
        return new BigDecimal(9.5);
    }

    @Override
    public String desc() {
        return "pre应用层涂料";
    }
}
