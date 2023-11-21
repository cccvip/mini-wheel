/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.po;


/**
 * User.
 *
 * @author Carl, 2023-09-14 10:44
 */
public class User {

    public User() {

    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
