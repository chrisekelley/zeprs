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
 * Date: Nov 5, 2004
 * Time: 2:22:51 PM
 */

/**
 * @hibernate.class table="appointment_type"
 * mutable="true"
 */

public class AppointmentType implements Identifiable {

    private Long id;
    private String appt_type;

    /**
     * @return
     * @hibernate.id unsaved-value="0"
     * generator-class="identity"
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return
     * @hibernate.property column="appointment_name"
     */

    public String getAppt_type() {
        return appt_type;
    }

    public void setAppt_type(String appt_type) {
        this.appt_type = appt_type;
    }
}
