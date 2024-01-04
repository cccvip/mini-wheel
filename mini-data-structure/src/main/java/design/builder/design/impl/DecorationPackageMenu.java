/*
 * All Rights Reserved.
 *
 */
package design.builder.design.impl;


import design.builder.design.IMenu;
import design.builder.design.Matter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * DecorationPackageMenu.
 *
 * @author Carl, 2024-01-04 13:37
 */
public class DecorationPackageMenu implements IMenu {

    List<Matter> matterList = new ArrayList<>();

    // 装修价格
    private BigDecimal price = BigDecimal.ZERO;

    // 面积
    private BigDecimal area;

    // 装修等级；豪华欧式、轻奢田园、现代简约
    private String grade;

    public DecorationPackageMenu() {
    }

    public DecorationPackageMenu(BigDecimal area, String grade) {
        this.area = area;
        this.grade = grade;
    }

    @Override
    public IMenu appendCeiling(Matter matter) {
        matterList.add(matter);
        price = price.add(area.multiply(new BigDecimal("0.2")).multiply(matter.price()));
        return this;
    }

    @Override
    public IMenu appendCoat(Matter matter) {
        matterList.add(matter);
        price = price.add(area.multiply(new BigDecimal("1.4")).multiply(matter.price()));
        return this;
    }

    @Override
    public IMenu appendFloor(Matter matter) {
        matterList.add(matter);
        price = price.add(area.multiply(matter.price()));
        return this;
    }

    @Override
    public IMenu appendTile(Matter matter) {
        matterList.add(matter);
        price = price.add(area.multiply(matter.price()));
        return this;
    }

    @Override
    public String getDetail() {
        StringBuilder detail = new StringBuilder("\r\n-------------------------------------------------------\r\n" +
                "装修清单" + "\r\n" +
                "套餐等级：" + grade + "\r\n" +
                "套餐价格：" + price.setScale(2, BigDecimal.ROUND_HALF_UP) + " 元\r\n" +
                "房屋面积：" + area.doubleValue() + " 平米\r\n" +
                "材料清单：\r\n");
        for (Matter matter : matterList) {
            detail.append(matter.scene()).append("：").append(matter.brand()).append("、")
                    .append(matter.model())
                    .append("、平米价格：")
                    .append(matter.price())
                    .append(" 元。\n");
        }
        return detail.toString();
    }
}
