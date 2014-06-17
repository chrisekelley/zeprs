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

import org.cidrz.webapp.dynasite.utils.sort.FlowOrderComparator;
import org.cidrz.webapp.dynasite.valueobject.Identifiable;

import java.util.SortedSet;
import java.util.TreeSet;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Jul 7, 2004
 * Time: 2:58:51 PM
 * To change this template use File | Settings | File Templates.
 */

public class Flow implements Identifiable, Serializable {
    private Long id;
    private String name;
    private Long flowOrder;
    private String createdBy;
    private SortedSet forms = new TreeSet(new FlowOrderComparator());
    private String globalIdentifierName;


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

    public Long getFlowOrder() {
        return flowOrder;
    }

    public void setFlowOrder(Long flowOrder) {
        this.flowOrder = flowOrder;
    }

    public SortedSet getForms() {
        return forms;
    }

    public void setForms(SortedSet forms) {
        this.forms = forms;
    }

	public String getGlobalIdentifierName() {
		return globalIdentifierName;
	}

	public void setGlobalIdentifierName(String globalIdentifierName) {
		this.globalIdentifierName = globalIdentifierName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}
