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

import java.sql.Timestamp;

/**
 * This class manages information about a Server with which this application interacts.
 * These settings are stored in publisher.xml.
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Mar 20, 2006
 *         Time: 1:37:06 PM
 */
public class Server {

    private Long siteId;
    private Site site;
    private Timestamp dateModified;
    private String url;
    private Boolean redirectLocal;
    private Boolean master;		// Is this server acting as master?
    private String username;
    private String password;
    private String location;	// IP address or name of server
    private String name;


	public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }


    public Timestamp getDateModified() {
        return dateModified;
    }

    public void setDateModified(Timestamp dateModified) {
        this.dateModified = dateModified;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getRedirectLocal() {
        return redirectLocal;
    }

    public void setRedirectLocal(Boolean redirectLocal) {
        this.redirectLocal = redirectLocal;
    }

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}


    /**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the master
	 */
	public Boolean getMaster() {
		return master;
	}

	/**
	 * @param master the master to set
	 */
	public void setMaster(Boolean master) {
		this.master = master;
	}

}
