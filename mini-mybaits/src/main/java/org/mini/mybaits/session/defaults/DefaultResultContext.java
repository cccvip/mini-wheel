/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.session.defaults;


import org.mini.mybaits.session.ResultContext;

/**
 * DefaultResultContext.
 * 
 * @author Carl, 2023-11-22 10:52
 */
public class DefaultResultContext implements ResultContext {

    private Object resultObject;
    private int resultCount;

    public DefaultResultContext() {
        this.resultObject = null;
        this.resultCount = 0;
    }

    @Override
    public Object getResultObject() {
        return resultObject;
    }

    @Override
    public int getResultCount() {
        return resultCount;
    }

    //移动下标
    public void nextResultObject(Object resultObject) {
        resultCount++;
        this.resultObject = resultObject;
    }

}
