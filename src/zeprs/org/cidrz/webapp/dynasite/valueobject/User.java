/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.valueobject;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Feb 6, 2005
 * Time: 7:45:25 PM
 */

/**
 * @hibernate.class table="user_group_membership"
 * lazy="false"
 * mutable="true"
 */

public class User {

    private String id;
    private Long groupId;
    private String prefix;
    private String firstName;
    private String lastName;

    /**
     * @return
     * @hibernate.id column="id"
     * generator-class="assigned"
     */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return
     * @hibernate.property column="group_id"
     */

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    /**
     * @return
     * @hibernate.property column="prefix"
     */
    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * @return
     * @hibernate.property column="firstname"
     */
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return
     * @hibernate.property column="lastname"
     */
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
