/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.executor;


import org.mini.mybaits.mapping.BoundSql;
import org.mini.mybaits.mapping.MappingStatement;
import org.mini.mybaits.session.Configuration;
import org.mini.mybaits.session.ResultSessionHandler;
import org.mini.mybaits.transaction.Transaction;

import java.util.List;

/**
 * SimpleExecutor.
 * 抽象模板模式
 *
 * @author Carl, 2023-10-31 13:43
 */
public abstract class BaseExecutor implements Executor {

    protected Configuration configuration;
    protected Transaction transaction;
    protected Executor wrapper;

    protected BaseExecutor(Configuration configuration, Transaction transaction) {
        this.configuration = configuration;
        this.transaction = transaction;
        this.wrapper = this;
    }

    @Override
    public <E> List<E> query(MappingStatement ms, Object parameter, ResultSessionHandler resultHandler, BoundSql boundSql) {
        return doQuery(ms, parameter, resultHandler, boundSql);
    }

    protected abstract <E> List<E> doQuery(MappingStatement ms, Object parameter, ResultSessionHandler resultHandler, BoundSql boundSql);

}
