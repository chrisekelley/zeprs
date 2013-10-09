package org.cidrz.project.zeprs.valueobject.lims;

import java.sql.Date;
import java.sql.Timestamp;

public interface Observation {

	/**
	 * @return the natID
	 */
	public abstract String getNatID();

	/**
	 * @param natID the natID to set
	 */
	public abstract void setNatID(String natID);

	/**
	 * @return the observationIdentifier
	 */
	public abstract String getObservationIdentifier();

	/**
	 * @param observationIdentifier the observationIdentifier to set
	 */
	public abstract void setObservationIdentifier(String observationIdentifier);

	/**
	 * @return the observationText
	 */
	public abstract String getObservationText();

	/**
	 * @param observationText the observationText to set
	 */
	public abstract void setObservationText(String observationText);

	/**
	 * @return the abnormalFlags
	 */
	public abstract String getAbnormalFlags();

	/**
	 * @param abnormalFlags the abnormalFlags to set
	 */
	public abstract void setAbnormalFlags(String abnormalFlags);

	/**
	 * @return the observationValue
	 */
	public abstract String getObservationValue();

	/**
	 * @param observationValue the observationValue to set
	 */
	public abstract void setObservationValue(String observationValue);

	/**
	 * @return the units
	 */
	public abstract String getUnits();

	/**
	 * @param units the units to set
	 */
	public abstract void setUnits(String units);

	/**
	 * @return the referencesRange
	 */
	public abstract String getReferencesRange();

	/**
	 * @param referencesRange the referencesRange to set
	 */
	public abstract void setReferencesRange(String referencesRange);

	/**
	 * @return the observationResultStatus
	 */
	public abstract String getObservationResultStatus();

	/**
	 * @param observationResultStatus the observationResultStatus to set
	 */
	public abstract void setObservationResultStatus(
			String observationResultStatus);

	/**
	 * @return the observationDate
	 */
	public abstract Timestamp getObservationDate();

	/**
	 * @param observationDate the observationDate to set
	 */
	public abstract void setObservationDate(Timestamp observationDate);

	/**
	 * @return the informationId
	 */
	public abstract String getInformationId();

	/**
	 * @param informationId the informationId to set
	 */
	public abstract void setInformationId(String informationId);

	/**
	 * @return the informationId
	 */
	public abstract String getObservationId();

	/**
	 * @param informationId the informationId to set
	 */
	public abstract void setObservationId(String observationId);



}