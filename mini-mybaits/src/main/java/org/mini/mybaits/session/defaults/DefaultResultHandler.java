/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.session.defaults;


import org.mini.mybaits.reflection.factory.ObjectFactory;
import org.mini.mybaits.session.ResultContext;
import org.mini.mybaits.session.ResultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * ResultHandler.
 * 
 * @author Carl, 2023-11-22 10:49
 */
public class DefaultResultHandler implements ResultHandler {

    private final List<Object> list;

    public DefaultResultHandler() {
        this.list = new ArrayList<>();
    }

    public DefaultResultHandler(ObjectFactory objectFactory) {
        this.list = objectFactory.create(List.class);
    }

    @Override
    public void handleResult(ResultContext context) {
        list.add(context.getResultObject());
    }

    public List<Object> getResultList() {
        return list;
    }

}
