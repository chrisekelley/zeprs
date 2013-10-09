/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.project.zeprs.valueobject;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.webapp.dynasite.rules.OutcomeException;
import org.cidrz.webapp.dynasite.rules.Rule;
import org.cidrz.webapp.dynasite.rules.RuleSubject;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.valueobject.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

/**
 * @hibernate.class table="encounter"
 * lazy="true"
 * mutable="true"
 */
public class EncounterData implements BaseEncounter, Identifiable, RuleSubject, Auditable, Recordable, SessionPatientRecord, DateVisitOrderable {
    /**
     * Commons Logging instance.
     */
    private transient Log log = LogFactory.getFactory().getInstance(this.getClass().getName());

    private Long id;
    private Long patientId;
    private Long formId;
    private AuditInfo auditInfo;
    private Long flowId;
    private Date dateVisit;
    private Long pregnancyId;
    private Pregnancy pregnancy;
    //private transient Map encounterMap = new LinkedHashMap();
    private transient Map encounterMap;
    private String formName;
    // recordables
    private Timestamp lastModified;
    private Timestamp created;
    private String lastModifiedBy;
    private String createdBy;
    private Long siteId;
    private String zeprsId;     // used in reports
    private String surname;     // used in reports
    private String firstName;   // used in reports
    private Long parentId;        // used in reports
    private List babies;     // used in reports
    private String staffName;    // used in reports
    private Long referralId;    // discharge
    private String siteName;
    private String createdByName;
    private String lastModifiedByName;
    private SessionPatient sessionPatient;  // used in rule processing
    private Long createdSiteId;
    private Long importEncounterId;
    private List outcomes;  // used in xml record generation when deleting an encounter.
    private String uuid;


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
     * @hibernate.property column="patient_id"
     */
    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }


    /**
     * @return
     * @hibernate.property column="form_id"
     */
    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }


    public EncounterData() {
        super();
    }


    public Long getFormFieldId() {
        return null;
    }


    /**
     * @hibernate.component class="org.cidrz.webapp.dynasite.valueobject.AuditInfo"
     */
    public AuditInfo getAuditInfo() {
        if (auditInfo == null) {
            auditInfo = new org.cidrz.webapp.dynasite.valueobject.AuditInfo();
        }
        return auditInfo;
    }

    public void setAuditInfo(org.cidrz.webapp.dynasite.valueobject.AuditInfo auditInfo) {
        this.auditInfo = auditInfo;
    }

    /**
     * @return
     * @hibernate.property column="flow_id"
     * not-null="true"
     */

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    /**
     * @return
     * @hibernate.property column="date_visit"
     * not-null="true"
     */

    public Date getDateVisit() {
        return dateVisit;
    }

    public void setDateVisit(Date dateVisit) {
        this.dateVisit = dateVisit;
    }

    /**
     * @return
     * @hibernate.property column="pregnancy_id"
     */

    public Long getPregnancyId() {
        return pregnancyId;
    }

    public void setPregnancyId(Long pregnancyId) {
        this.pregnancyId = pregnancyId;
    }

    public Pregnancy getPregnancy() {
		return pregnancy;
	}

	public void setPregnancy(Pregnancy pregnancy) {
		this.pregnancy = pregnancy;
	}

	/**
     * This is useful for storing the results of getEncounterMap
     *
     * @return map of resolved values
     */

    public Map getEncounterMap() {
        return encounterMap;
    }

    public void setEncounterMap(Map encounterMap) {
        this.encounterMap = encounterMap;
    }

    /**
     * Use this when displaying patient record
     *
     * @return formName
     */
    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    // Recordables
    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getZeprsId() {
        return zeprsId;
    }

    public void setZeprsId(String zeprsId) {
        this.zeprsId = zeprsId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List getBabies() {
        return babies;
    }

    public void setBabies(List babies) {
        this.babies = babies;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    /**
     * @return
     * @hibernate.property column="referral_id"
     */

    public Long getReferralId() {
        return referralId;
    }

    public void setReferralId(Long referralId) {
        this.referralId = referralId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getLastModifiedByName() {
        return lastModifiedByName;
    }

    public void setLastModifiedByName(String lastModifiedByName) {
        this.lastModifiedByName = lastModifiedByName;
    }

    public SessionPatient getSessionPatient() {
        return sessionPatient;
    }

    public void setSessionPatient(SessionPatient sessionPatient) {
        this.sessionPatient = sessionPatient;
    }

    /**
     * @return
     * @hibernate.property column="created_site_id"
     */
    public Long getCreatedSiteId() {
        return createdSiteId;
    }

    public void setCreatedSiteId(Long createdSiteId) {
        this.createdSiteId = createdSiteId;
    }

    /**
     * @return
     * @hibernate.property column="import_encounter_id"
     */
    public Long getImportEncounterId() {
        return importEncounterId;
    }

    public void setImportEncounterId(Long importEncounterId) {
        this.importEncounterId = importEncounterId;
    }

    /**
     *  This list is used for xml record generation, when deleting the record.
     * @return list of outcomes for this encounter.
     */
    public List getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(List outcomes) {
        this.outcomes = outcomes;
    }

    public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void evaluate(Form formDef) throws OutcomeException {
        OutcomeException outcomeEx = null;
        /*
        RuleProvider provider = (RuleProvider) this.getForm();
        List rules = provider.getRules();
        Rule rule;
        RuleDefinition ruleDef;
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
        }*/

        // create a map of field names (field1, field2, etc.) in this encounter - this becomes the ruleSubject.
        Map encounterData = new HashMap();
        try {
            encounterData = PropertyUtils.describe(this);
        } catch (NoSuchMethodException e) {
            log.error(e);
        } catch (IllegalAccessException e) {
            log.error(e);
        } catch (InvocationTargetException e) {
            log.error(e);
        }

        // set up the encounter map for Script rules
        Map encMap = PatientRecordUtils.getEncounterMap(formDef, this, "rules");
        this.setEncounterMap(encMap);
        // extract the form_field rules - these are in each pageItem's formField.
        // put these in a map
        // get a map of all of the form fields in this form
        Long formId = formDef.getId();
        Map pageItems = (Map) DynaSiteObjects.getFieldToPageItem().get(formId);
        // fetch a list of fields on this page that have rules.
        List fieldsWithRules = (List) DynaSiteObjects.getRulesToForms().get(formId);
        for (int i = 0; i < fieldsWithRules.size(); i++) {
            Long fielsIdWithRule = (Long) fieldsWithRules.get(i);
            Long pageItemId =  (Long) pageItems.get(fielsIdWithRule);
            PageItem pageItem = (PageItem) DynaSiteObjects.getPageItems().get(pageItemId);
            List ruleList = pageItem.getForm_field().getRules();
            // now evaluate and nested RuleSubjects...
                // loop through the ruleList and see if this field needs rule processing.
                for (int j = 0; j < ruleList.size(); j++) {
                    Rule rule = (Rule) ruleList.get(j);
                    String ruleSubject = null;
                    Object value = encounterData.get("field" + fielsIdWithRule);
                    if (value != null) {
                        ruleSubject = String.valueOf(value);
                            try {
                                evaluateValue(ruleSubject, value, pageItem, this, rule);
                            } catch (OutcomeException e) {
                                if (outcomeEx == null) {
                                    outcomeEx = new OutcomeException();
                                }
                                outcomeEx.add(e);
                            }
                    }
                }
        }
        if (outcomeEx != null) {
            throw outcomeEx;
        }
    }

    /**
     * Evaluates each EncounterValue with the supplied rule.
     *
     * @param ruleSubject
     * @param o
     * @param pi
     * @param encounter
     * @param rule
     * @throws OutcomeException
     */
	public void evaluateValue(String ruleSubject, Object o, PageItem pi, EncounterData encounter, Rule rule) throws OutcomeException {
		RuleDefinition ruleDef = rule.getRuleDefinition();
		OutcomeException outcomeEx = null;
		EncounterValue ev = new EncounterValue();
		String value = "";
		if (ruleDef.isEnabled() == true) {
			if (o != null) {
				value = o.toString();
			}
			ev.setValue(value);
			ev.setPageItem(pi);
			ev.setPatientId(this.getPatientId());
			ev.setPregnancyId(this.getPregnancyId());
			ev.setEncounter(encounter);
			try {
				rule.evaluate(ev, ruleDef);
			} catch (OutcomeException e) {
				if (outcomeEx == null) {
					outcomeEx = new OutcomeException();
				}
				outcomeEx.add(e);
			}
			if (outcomeEx != null) {
				throw outcomeEx;
			}
		}
	}

    public Object getEvaluationValue() {
        return this;
    }

    public boolean isMotherHivPositive() {
        if (sessionPatient != null) {
            return sessionPatient.isMotherHivPositive();
        } else {
            return false;
        }
    }


    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("EncounterData");
        sb.append("{id=").append(id);
        sb.append(", patientId=").append(patientId);
        sb.append(", formId=").append(formId);
        sb.append(", auditInfo=").append(auditInfo);
        sb.append(", flowId=").append(flowId);
        sb.append(", dateVisit=").append(dateVisit);
        sb.append(", pregnancyId=").append(pregnancyId);
        sb.append(", encounterMap=").append(encounterMap);
        sb.append(", formName='").append(formName).append('\'');
        sb.append(", lastModified=").append(lastModified);
        sb.append(", created=").append(created);
        sb.append(", lastModifiedBy='").append(lastModifiedBy).append('\'');
        sb.append(", createdBy='").append(createdBy).append('\'');
        sb.append(", siteId=").append(siteId);
        sb.append(", zeprsId='").append(zeprsId).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", parentId=").append(parentId);
        sb.append(", babies=").append(babies);
        sb.append(", staffName='").append(staffName).append('\'');
        sb.append(", referralId=").append(referralId);
        sb.append(", siteName='").append(siteName).append('\'');
        sb.append(", createdByName='").append(createdByName).append('\'');
        sb.append(", lastModifiedByName='").append(lastModifiedByName).append('\'');
        sb.append(", sessionPatient=").append(sessionPatient);
        sb.append(", createdSiteId=").append(createdSiteId);
        sb.append(", importEncounterId=").append(importEncounterId);
        sb.append(", outcomes=").append(outcomes);
        sb.append('}');
        return sb.toString();
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((auditInfo == null) ? 0 : auditInfo.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdByName == null) ? 0 : createdByName.hashCode());
		result = prime * result
				+ ((createdSiteId == null) ? 0 : createdSiteId.hashCode());
		result = prime * result
				+ ((dateVisit == null) ? 0 : dateVisit.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((flowId == null) ? 0 : flowId.hashCode());
		result = prime * result + ((formId == null) ? 0 : formId.hashCode());
		result = prime * result
				+ ((formName == null) ? 0 : formName.hashCode());
		result = prime * result
				+ ((lastModified == null) ? 0 : lastModified.hashCode());
		result = prime * result
				+ ((lastModifiedBy == null) ? 0 : lastModifiedBy.hashCode());
		result = prime
				* result
				+ ((lastModifiedByName == null) ? 0 : lastModifiedByName
						.hashCode());
		result = prime * result
				+ ((outcomes == null) ? 0 : outcomes.hashCode());
		result = prime * result
				+ ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result
				+ ((patientId == null) ? 0 : patientId.hashCode());
		result = prime * result
				+ ((pregnancy == null) ? 0 : pregnancy.hashCode());
		result = prime * result
				+ ((pregnancyId == null) ? 0 : pregnancyId.hashCode());
		result = prime * result
				+ ((referralId == null) ? 0 : referralId.hashCode());
		result = prime * result + ((siteId == null) ? 0 : siteId.hashCode());
		result = prime * result
				+ ((siteName == null) ? 0 : siteName.hashCode());
		result = prime * result
				+ ((staffName == null) ? 0 : staffName.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		result = prime * result + ((zeprsId == null) ? 0 : zeprsId.hashCode());
		return result;
	}


	@Override
	/**
	 * This is used to compare an imported EncounterData with a local one.
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final EncounterData other = (EncounterData) obj;
		if (auditInfo == null) {
			if (other.auditInfo != null)
				return false;
		} else if (!auditInfo.equals(other.auditInfo))
			return false;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (createdByName == null) {
			if (other.createdByName != null)
				return false;
		} else if (!createdByName.equals(other.createdByName))
			return false;
		if (createdSiteId == null) {
			if (other.createdSiteId != null)
				return false;
		} else if (!createdSiteId.equals(other.createdSiteId))
			return false;
		if (dateVisit == null) {
			if (other.dateVisit != null)
				return false;
		} else if (!dateVisit.equals(other.dateVisit))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (flowId == null) {
			if (other.flowId != null)
				return false;
		} else if (!flowId.equals(other.flowId))
			return false;
		if (formId == null) {
			if (other.formId != null)
				return false;
		} else if (!formId.equals(other.formId))
			return false;
		if (formName == null) {
			if (other.formName != null)
				return false;
		} else if (!formName.equals(other.formName))
			return false;
		if (lastModified == null) {
			if (other.lastModified != null)
				return false;
		} else if (!lastModified.equals(other.lastModified))
			return false;
		if (lastModifiedBy == null) {
			if (other.lastModifiedBy != null)
				return false;
		} else if (!lastModifiedBy.equals(other.lastModifiedBy))
			return false;
		if (lastModifiedByName == null) {
			if (other.lastModifiedByName != null)
				return false;
		} else if (!lastModifiedByName.equals(other.lastModifiedByName))
			return false;
		if (outcomes == null) {
			if (other.outcomes != null)
				return false;
		} else if (!outcomes.equals(other.outcomes))
			return false;
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		if (patientId == null) {
			if (other.patientId != null)
				return false;
		} else if (!patientId.equals(other.patientId))
			return false;
		if (pregnancy == null) {
			if (other.pregnancy != null)
				return false;
		} else if (!pregnancy.equals(other.pregnancy))
			return false;
		if (pregnancyId == null) {
			if (other.pregnancyId != null)
				return false;
		} else if (!pregnancyId.equals(other.pregnancyId))
			return false;
		if (referralId == null) {
			if (other.referralId != null)
				return false;
		} else if (!referralId.equals(other.referralId))
			return false;
		if (siteId == null) {
			if (other.siteId != null)
				return false;
		} else if (!siteId.equals(other.siteId))
			return false;
		if (siteName == null) {
			if (other.siteName != null)
				return false;
		} else if (!siteName.equals(other.siteName))
			return false;
		if (staffName == null) {
			if (other.staffName != null)
				return false;
		} else if (!staffName.equals(other.staffName))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		if (zeprsId == null) {
			if (other.zeprsId != null)
				return false;
		} else if (!zeprsId.equals(other.zeprsId))
			return false;
		return true;
	}




}