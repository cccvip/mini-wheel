/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.reflection.property;


import java.util.Iterator;

/**
 * PropertyTokenizer.
 *
 * @author Carl, 2023-11-02 14:09
 * @version CrisisGo v1.0
 */
public class PropertyTokenizer implements Iterable<PropertyTokenizer>, Iterator<PropertyTokenizer> {

    //student[0].name
    private String name;

    private String indexedName;

    private String index;

    private String children;

    public PropertyTokenizer(String fullName) {
        int point = fullName.indexOf(".");
        if (point > -1) {
            name = fullName.substring(0, point);
            children = fullName.substring(point + 1);
        } else {
            // 找不到.的话，取默认视作不包含children
            name = fullName;
            children = null;
        }
        indexedName = name;
        // 把中括号里的数字给解析出来
        point = name.indexOf('[');
        if (point > -1) {
            index = name.substring(point + 1, name.length() - 1);
            name = name.substring(0, point);
        }
    }
    @Override
    public Iterator<PropertyTokenizer> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return children != null;
    }

    @Override
    public PropertyTokenizer next() {
        return new PropertyTokenizer(children);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndexedName() {
        return indexedName;
    }

    public void setIndexedName(String indexedName) {
        this.indexedName = indexedName;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }
}
