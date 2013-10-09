/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.project.zeprs.report.valueobject;

import java.sql.Date;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Feb 2, 2006
 *         Time: 11:32:54 AM
 */
public class DrugsDispensed {

	private Long id;
    private Date dateDispensed;
    private boolean folic;
    private boolean iron;
    private boolean deworming;
    private String malariaSp;
    private String other;

    public Date getDateDispensed() {
        return dateDispensed;
    }

    public void setDateDispensed(Date dateDispensed) {
        this.dateDispensed = dateDispensed;
    }

    public boolean isFolic() {
        return folic;
    }

    public void setFolic(boolean folic) {
        this.folic = folic;
    }

    public boolean isIron() {
        return iron;
    }

    public void setIron(boolean iron) {
        this.iron = iron;
    }

    public boolean isDeworming() {
        return deworming;
    }

    public void setDeworming(boolean deworming) {
        this.deworming = deworming;
    }

    public String getMalariaSp() {
        return malariaSp;
    }

    public void setMalariaSp(String malariaSp) {
        this.malariaSp = malariaSp;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
