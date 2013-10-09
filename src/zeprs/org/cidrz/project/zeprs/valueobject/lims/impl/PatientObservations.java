
package org.cidrz.project.zeprs.valueobject.lims.impl;

import java.sql.Date;
import java.sql.Timestamp;

import org.cidrz.project.zeprs.valueobject.lims.Observation;


/**
 * @author ckelley
 * The classes in this package are for connecting to the SQL Server instance
 * which stores data from the LIMS lab system.
 */

/**
 * ----fixme--hibernate.class table="PatientObservations"
 * lazy="true"
 * mutable="true"
 */
public class PatientObservations implements Observation {

	private String natID;
	private String observationIdentifier;
	private String observationText;
	private String abnormalFlags;
	private String observationValue;
	private String units;
	private String referencesRange;
	private String observationResultStatus;
	private Timestamp observationDate;
	private String informationId;

	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.impl.Observation#getNatID()
	 */
	public String getNatID() {
		return natID;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.impl.Observation#setNatID(java.lang.String)
	 */
	public void setNatID(String natID) {
		this.natID = natID;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.impl.Observation#getObservationIdentifier()
	 */
	public String getObservationIdentifier() {
		return observationIdentifier;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.impl.Observation#setObservationIdentifier(java.lang.String)
	 */
	public void setObservationIdentifier(String observationIdentifier) {
		this.observationIdentifier = observationIdentifier;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.impl.Observation#getObservationText()
	 */
	public String getObservationText() {
		return observationText;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.impl.Observation#setObservationText(java.lang.String)
	 */
	public void setObservationText(String observationText) {
		this.observationText = observationText;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.impl.Observation#getAbnormalFlags()
	 */
	public String getAbnormalFlags() {
		return abnormalFlags;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.impl.Observation#setAbnormalFlags(java.lang.String)
	 */
	public void setAbnormalFlags(String abnormalFlags) {
		this.abnormalFlags = abnormalFlags;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.impl.Observation#getObservationValue()
	 */
	public String getObservationValue() {
		return observationValue;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.impl.Observation#setObservationValue(java.lang.String)
	 */
	public void setObservationValue(String observationValue) {
		this.observationValue = observationValue;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.impl.Observation#getUnits()
	 */
	public String getUnits() {
		return units;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.impl.Observation#setUnits(java.lang.String)
	 */
	public void setUnits(String units) {
		this.units = units;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.impl.Observation#getReferencesRange()
	 */
	public String getReferencesRange() {
		return referencesRange;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.impl.Observation#setReferencesRange(java.lang.String)
	 */
	public void setReferencesRange(String referencesRange) {
		this.referencesRange = referencesRange;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.impl.Observation#getObservationResultStatus()
	 */
	public String getObservationResultStatus() {
		return observationResultStatus;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.impl.Observation#setObservationResultStatus(java.lang.String)
	 */
	public void setObservationResultStatus(String observationResultStatus) {
		this.observationResultStatus = observationResultStatus;
	}
	/**
	 * @return the observationDate
	 */
	public Timestamp getObservationDate() {
		return observationDate;
	}
	/**
	 * @param observationDate the observationDate to set
	 */
	public void setObservationDate(Timestamp observationDate) {
		this.observationDate = observationDate;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.impl.Observation#getInformationId()
	 */
	public String getInformationId() {
		return informationId;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.impl.Observation#setInformationId(java.lang.Long)
	 */
	public void setInformationId(String informationId) {
		this.informationId = informationId;
	}
	@Override
	public String getObservationId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setObservationId(String observationId) {
		// TODO Auto-generated method stub

	}





}
