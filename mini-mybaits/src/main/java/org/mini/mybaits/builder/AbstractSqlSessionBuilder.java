/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.builder;


import org.mini.mybaits.session.Configuration;

/**
 * AbstraceSqlSessionBuilder.
 *
 * @author Carl, 2023-09-14 9:49
 */
public abstract class AbstractSqlSessionBuilder implements SqlSessionBuilder {

    protected Configuration configuration;

    public AbstractSqlSessionBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
