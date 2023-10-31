/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.executor;


import org.mini.mybaits.mapping.BoundSql;
import org.mini.mybaits.mapping.MappingStatement;
import sun.plugin2.main.server.ResultHandler;

import java.util.List;

/**
 * Executor.
 * 抽象执行器
 *
 * @author Carl, 2023-10-31 13:41
 */
public interface Executor {

    <E> List<E> query(MappingStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql);

}
