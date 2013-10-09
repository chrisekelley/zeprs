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

import org.cidrz.webapp.dynasite.rules.OutcomeException;
import org.cidrz.webapp.dynasite.rules.Rule;
import org.cidrz.webapp.dynasite.rules.RuleSubject;
import org.cidrz.webapp.dynasite.rules.RuleProvider;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.project.zeprs.valueobject.EncounterData;

import java.sql.Date;
import java.util.List;

/**
 * Convenient container for values and value metadata.  Used in rules processing and enumeration processing.
 */
public class EncounterValue implements Identifiable, RuleSubject, DisplayOrderable {
    private Long id;
    private EncounterData encounter;
    private PageItem pageItem;
    private String value;
    private Patient patient;
    private Long fieldId;
    private Date visitDate;
    private Site site;
    private Pregnancy pregnancy;
    private Long patientId;
    private Long pregnancyId;
    private SessionPatient sessionPatient;  // used in rule processing
    private String zeprsId;

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
     * @hibernate.many-to-one column="encounter_id"
     * cascade="none"
     */
    public EncounterData getEncounter() {
        return encounter;
    }

    public void setEncounter(EncounterData encounter) {
        this.encounter = encounter;
    }

    /**
     * @return
     * @hibernate.many-to-one column="page_item_id"
     * cascade="none"
     */
    public PageItem getPageItem() {
        return pageItem;
    }

    public void setPageItem(PageItem pageItem) {
        this.pageItem = pageItem;
    }

    /**
     * @return
     * @hibernate.property column="value"
     * not-null="true"
     */
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return
     * @hibernate.many-to-one column="patient_id"
     * cascade="none"
     * lazy"true"
     * not-null="true"
     */
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * @return
     * @hibernate.property column="field_id"
     * not-null="true"
     */

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }

    /**
     * @return
     * @hibernate.property column="visit_date"
     * not-null="true"
     */

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    /**
     * @return
     * @hibernate.many-to-one column="site_id"
     * cascade="none"
     * lazy"true"
     */

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }


    /**
     * @return
     * @hibernate.many-to-one column="pregnancy_id"
     * cascade="none"
     * lazy="true"
     */

    public Pregnancy getPregnancy() {
        return pregnancy;
    }

    public void setPregnancy(Pregnancy pregnancy) {
        this.pregnancy = pregnancy;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getPregnancyId() {
        return pregnancyId;
    }

    public void setPregnancyId(Long pregnancyId) {
        this.pregnancyId = pregnancyId;
    }

    public SessionPatient getSessionPatient() {
        return sessionPatient;
    }

    public void setSessionPatient(SessionPatient sessionPatient) {
        this.sessionPatient = sessionPatient;
    }

    /**
	 * @return the zeprsId
	 */
	public String getZeprsId() {
		return zeprsId;
	}

	/**
	 * @param zeprsId the zeprsId to set
	 */
	public void setZeprsId(String zeprsId) {
		this.zeprsId = zeprsId;
	}

	public void evaluate(Form form) throws OutcomeException {
        RuleProvider provider = (RuleProvider) this.getPageItem().getForm_field();
        List rules = provider.getRules();
        Rule rule;
        RuleDefinition ruleDef;
        OutcomeException outcomeEx = null;
        for (int i = 0; i < rules.size(); i++) {
            Object item = rules.get(i);
            ruleDef = (RuleDefinition) item;
            rule = (Rule) item;
            try {
                rule.evaluate(this, ruleDef);
            } catch (OutcomeException e) {
                if (outcomeEx == null) {
                    outcomeEx = new OutcomeException();
                }
                outcomeEx.add(e);
            }
        }
        if (outcomeEx != null) {
            throw outcomeEx;
        }
    }


    public Object getEvaluationValue() {
        String value = "";
        String fieldType =  this.getPageItem().getForm_field().getType();
        String pageItemType = this.getPageItem().getInputType();
        String inputValue = this.value;
        value = PatientRecordUtils.resolveValue(inputValue, fieldType, pageItemType);
        return value;
    }


    public Long getFormFieldId() {
        return this.getPageItem().getForm_field().getId();
    }

    public Long getRuleDefinitionId(Long id) {
        return id;
    }


    public Integer getDisplayOrder() {
        if (this.getPageItem() != null) {
            return getPageItem().getDisplayOrder();
        }
        return new Integer(0);
    }

    public void setDisplayOrder(Integer displayOrder) {
        // not supported.
    }

    /**
     * Instances must always return a non-null instance of AuditInfo
     */
    /*public AuditInfo getAuditInfo()
    {
        return getEncounter().getAuditInfo();
    }
*/

}