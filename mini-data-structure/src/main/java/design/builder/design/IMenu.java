/*
 * All Rights Reserved.
 *
 */
package design.builder.design;


/**
 * IMenu.
 * @author Carl, 2024-01-04 13:36
 */
public interface IMenu {
    IMenu appendCeiling(Matter matter); // 吊顶

    IMenu appendCoat(Matter matter);    // 涂料

    IMenu appendFloor(Matter matter);   // 地板

    IMenu appendTile(Matter matter);    // 地砖

    String getDetail();                 // 明细
}
