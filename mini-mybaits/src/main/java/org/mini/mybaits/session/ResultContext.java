/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.session;


/**
 * ResultContext.
 * 
 * @author Carl, 2023-11-22 10:48
 */
public interface ResultContext {
    /**
     * 获取结果
     */
    Object getResultObject();

    /**
     * 获取记录数
     */
    int getResultCount();

}
