/*
 * All Rights Reserved.
 *
 */
package design.builder.design.builder;


import com.alibaba.fastjson2.JSON;
import design.builder.design.IMenu;
import design.builder.design.brick.PafBrick;
import design.builder.design.brick.PreBrick;
import design.builder.design.ceiling.PafCeiling;
import design.builder.design.ceiling.PreCeiling;
import design.builder.design.coating.PafCoating;
import design.builder.design.coating.PreCoating;
import design.builder.design.floor.PafFloor;
import design.builder.design.floor.PreFloor;
import design.builder.design.impl.DecorationPackageMenu;
import design.factory.impl.CouponCommodityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * DecorationBuilder.
 *
 * @author Carl, 2024-01-04 13:45
 */
public class DecorationBuilder {

    private static Logger logger = LoggerFactory.getLogger(DecorationBuilder.class);

    public IMenu levelOne(Double area) {
        return new DecorationPackageMenu(new BigDecimal(area), "1").appendCeiling(new PafCeiling())
                .appendCoat(new PafCoating()).appendFloor(new PafFloor()).appendTile(new PafBrick());
    }

    public IMenu levelTwo(Double area) {
        return new DecorationPackageMenu(new BigDecimal(area), "2").appendCeiling(new PreCeiling())
                .appendCoat(new PreCoating()).appendFloor(new PreFloor()).appendTile(new PreBrick());
    }


    public static void main(String[] args) {
        DecorationBuilder decorationBuilder = new DecorationBuilder();
        IMenu iMenu = decorationBuilder.levelOne(100.0D);
        logger.info("{}", iMenu.getDetail());
    }


}
