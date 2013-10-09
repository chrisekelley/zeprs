package org.cidrz.project.zeprs.valueobject.lims;

import java.sql.Date;

public interface ObservationMetaData {

	/**
	 * @return the id
	 */
	public abstract String getId();

	/**
	 * @param id the id to set
	 */
	public abstract void setId(String id);

	/**
	 * @return the natID
	 */
	public abstract String getNatID();

	/**
	 * @param natID the natID to set
	 */
	public abstract void setNatID(String natID);

	/**
	 * @return the firstName
	 */
	public abstract String getFirstName();

	/**
	 * @param firstName the firstName to set
	 */
	public abstract void setFirstName(String firstName);

	/**
	 * @return the surname
	 */
	public abstract String getSurname();

	/**
	 * @param surname the surname to set
	 */
	public abstract void setSurname(String surname);

	/**
	 * @return the clinic
	 */
	public abstract String getClinic();

	/**
	 * @param clinic the clinic to set
	 */
	public abstract void setClinic(String clinic);

	/**
	 * @return the study
	 */
	public abstract String getStudy();

	/**
	 * @param study the study to set
	 */
	public abstract void setStudy(String study);

	/**
	 * @return the dob
	 */
	public abstract Date getDob();

	/**
	 * @param dob the dob to set
	 */
	public abstract void setDob(Date dob);

	/**
	 * @return the sex
	 */
	public abstract String getSex();

	/**
	 * @param sex the sex to set
	 */
	public abstract void setSex(String sex);

	/**
	 * @return the collection
	 */
	public abstract Date getCollection();

	/**
	 * @param collection the collection to set
	 */
	public abstract void setCollection(Date collection);

	/**
	 * @return the reception
	 */
	public abstract Date getReception();

	/**
	 * @param reception the reception to set
	 */
	public abstract void setReception(Date reception);

	/**
	 * @return the observationRequest
	 */
	public abstract String getObservationRequest();

	/**
	 * @param observationRequest the observationRequest to set
	 */
	public abstract void setObservationRequest(String observationRequest);

}