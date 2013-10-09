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

import java.io.Serializable;


/**
 * @hibernate.class table="site"  lazy="false"
 * mutable="true"
 */
public class Site implements Identifiable, Serializable {
    private Long id;
    private String siteAlphaId;
    private String districtId;
    private String abbreviation;
    private String name;
    private Integer siteTypeId;
    private Integer inactive;
    // private AuditInfo auditInfo;

    public Site() {
    }

    public Site(Long id, String siteAlphaId, String districtId, String abbreviation, String name, Integer siteTypeId, Integer inactive) {
        this.id = id;
        this.siteAlphaId = siteAlphaId;
        this.districtId = districtId;
        this.abbreviation = abbreviation;
        this.name = name;
        this.siteTypeId = siteTypeId;
        this.inactive = inactive;
    }

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
     * @hibernate.property column="site_alpha_id"
     * not-null="true"
     */

    public String getSiteAlphaId() {
        return siteAlphaId;
    }

    public void setSiteAlphaId(String siteAlphaId) {
        this.siteAlphaId = siteAlphaId;
    }

    /**
     * @return
     * @hibernate.property column="district_id"
     * not-null="true"
     */
    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    /**
     * @return
     * @hibernate.property column="abbrev"
     * not-null="true"
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    /**
     * @return
     * @hibernate.property column="site_name"
     * not-null="true"
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return
     * @hibernate.property column="site_type_id"
     * not-null="true"
     */
    public Integer getSiteTypeId() {
        return siteTypeId;
    }

    public void setSiteTypeId(Integer siteTypeId) {
        this.siteTypeId = siteTypeId;
    }

    /**
     * @return
     * @hibernate.property column="inactive"
     * not-null="true"
     */
    public Integer getInactive() {
        return inactive;
    }

    public void setInactive(Integer inactive) {
        this.inactive = inactive;
    }
}
