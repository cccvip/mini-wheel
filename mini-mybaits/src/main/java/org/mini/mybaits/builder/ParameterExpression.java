/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.builder;


import java.util.HashMap;

/**
 * ParameterExpression.
 *
 * @author Carl, 2023-11-20 14:15
 */
public class ParameterExpression extends HashMap<String, String> {

    public ParameterExpression(String expression) {
        parse(expression);
    }

    // #{property,javaType=int,jdbcType=NUMERIC}
    private void parse(String expression) {
        int p = skip(expression, 0);
        if (expression.charAt(p) == '(') {
            //处理表达式
        } else {
            //处理属性
            property(expression, p);
        }
    }

    // 首先去除空白,返回的p是第一个不是空白的字符位置
    private int skip(String expression, int p) {
        for (int i = p; i < expression.length(); i++) {
            if (expression.charAt(i) > 0x20) {
                return i;
            }
        }
        return expression.length();
    }


    private void property(String expression, int left) {
        // #{property,javaType=int,jdbcType=NUMERIC}
        // property:VARCHAR
        if (left < expression.length()) {
            //首先，得到逗号或者冒号之前的字符串，加入到property
            int right = skipUntil(expression, left, ",:");
            put("property", trimmedStr(expression, left, right));
            // 第二，处理javaType，jdbcType
            jdbcTypeOpt(expression, right);
        }
    }


    private int skipUntil(String expression, int p, final String endChars) {
        for (int i = p; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (endChars.indexOf(c) > -1) {
                return i;
            }
        }
        return expression.length();
    }

    //使用双指针,同时移动左右指针,查找第一个非空数据
    private String trimmedStr(String str, int start, int end) {
        while (str.charAt(start) <= 0x20) {
            start++;
        }
        while (str.charAt(end - 1) <= 0x20) {
            end--;
        }
        return start >= end ? "" : str.substring(start, end);
    }


    private void jdbcTypeOpt(String expression, int p) {
        // #{property,javaType=int,jdbcType=NUMERIC}
        // property:VARCHAR
        // 首先去除空白,返回的p是第一个不是空白的字符位置
        p = skip(expression, p);
        if (p < expression.length()) {
            //第一个property解析完有两种情况，逗号和冒号
            if (expression.charAt(p) == ':') {
                jdbcType(expression, p + 1);
            } else if (expression.charAt(p) == ',') {
                option(expression, p + 1);
            } else {
                throw new RuntimeException("Parsing error in {" + new String(expression) + "} in position " + p);
            }
        }
    }

    private void jdbcType(String expression, int p) {
        // property:VARCHAR
        int left = skip(expression, p);
        int right = skipUntil(expression, left, ",");
        if (right > left) {
            put("jdbcType", trimmedStr(expression, left, right));
        } else {
            throw new RuntimeException("Parsing error in {" + new String(expression) + "} in position " + p);
        }
        option(expression, right + 1);
    }

    private void option(String expression, int p) {
        // #{property,javaType=int,jdbcType=NUMERIC}
        int left = skip(expression, p);
        if (left < expression.length()) {
            int right = skipUntil(expression, left, "=");
            String name = trimmedStr(expression, left, right);
            left = right + 1;
            right = skipUntil(expression, left, ",");
            String value = trimmedStr(expression, left, right);
            put(name, value);
            // 递归调用option，进行逗号后面一个属性的解析
            option(expression, right + 1);
        }
    }

}
