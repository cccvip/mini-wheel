/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.dao;


import org.mini.mybaits.po.User;

/**
 * IUserDao.
 *
 * @author Carl, 2023-08-30 10:23
 */
public interface IUserDao {
    User queryUserName(long id);

    String queryByUser(User user);
}
