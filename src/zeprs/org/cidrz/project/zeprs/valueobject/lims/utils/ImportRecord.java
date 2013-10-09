package org.cidrz.project.zeprs.valueobject.lims.utils;

import java.sql.Date;
import java.sql.Timestamp;

import org.cidrz.project.zeprs.valueobject.lims.Observation;
import org.cidrz.project.zeprs.valueobject.lims.ObservationMetaData;

/**
 * Used for importing of LIMS records into ZEPRS
 * @author ckelley
 *
 */
public class ImportRecord implements Observation, ObservationMetaData {

	// ObservationMetaData
	private String id;
	private String natID;
	private String firstName;
	private String surname;
	private String clinic;
	private String study;
	private Date dob;
	private String sex;
	private Date collection;
	private Date reception;
	private String observationRequest;

	// Observation
	private String observationIdentifier;
	private String observationText;
	private String abnormalFlags;
	private String observationValue;
	private String units;
	private String referencesRange;
	private String observationResultStatus;
	private Timestamp observationDate;	//this is the datetime value we'll use for last import.
	private String informationId;
	private String observationId;

	private String comments;	// Merge of some of the fields in ObservationMetaData


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the natID
	 */
	public String getNatID() {
		return natID;
	}
	/**
	 * @param natID the natID to set
	 */
	public void setNatID(String natID) {
		this.natID = natID;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}
	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	/**
	 * @return the clinic
	 */
	public String getClinic() {
		return clinic;
	}
	/**
	 * @param clinic the clinic to set
	 */
	public void setClinic(String clinic) {
		this.clinic = clinic;
	}
	/**
	 * @return the study
	 */
	public String getStudy() {
		return study;
	}
	/**
	 * @param study the study to set
	 */
	public void setStudy(String study) {
		this.study = study;
	}
	/**
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}
	/**
	 * @param dob the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}
	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return the collection
	 */
	public Date getCollection() {
		return collection;
	}
	/**
	 * @param collection the collection to set
	 */
	public void setCollection(Date collection) {
		this.collection = collection;
	}
	/**
	 * @return the reception
	 */
	public Date getReception() {
		return reception;
	}
	/**
	 * @param reception the reception to set
	 */
	public void setReception(Date reception) {
		this.reception = reception;
	}
	/**
	 * @return the observationRequest
	 */
	public String getObservationRequest() {
		return observationRequest;
	}
	/**
	 * @param observationRequest the observationRequest to set
	 */
	public void setObservationRequest(String observationRequest) {
		this.observationRequest = observationRequest;
	}
	/**
	 * @return the observationIdentifier
	 */
	public String getObservationIdentifier() {
		return observationIdentifier;
	}
	/**
	 * @param observationIdentifier the observationIdentifier to set
	 */
	public void setObservationIdentifier(String observationIdentifier) {
		this.observationIdentifier = observationIdentifier;
	}
	/**
	 * @return the observationText
	 */
	public String getObservationText() {
		return observationText;
	}
	/**
	 * @param observationText the observationText to set
	 */
	public void setObservationText(String observationText) {
		this.observationText = observationText;
	}
	/**
	 * @return the abnormalFlags
	 */
	public String getAbnormalFlags() {
		return abnormalFlags;
	}
	/**
	 * @param abnormalFlags the abnormalFlags to set
	 */
	public void setAbnormalFlags(String abnormalFlags) {
		this.abnormalFlags = abnormalFlags;
	}
	/**
	 * @return the observationValue
	 */
	public String getObservationValue() {
		return observationValue;
	}
	/**
	 * @param observationValue the observationValue to set
	 */
	public void setObservationValue(String observationValue) {
		this.observationValue = observationValue;
	}
	/**
	 * @return the units
	 */
	public String getUnits() {
		return units;
	}
	/**
	 * @param units the units to set
	 */
	public void setUnits(String units) {
		this.units = units;
	}
	/**
	 * @return the referencesRange
	 */
	public String getReferencesRange() {
		return referencesRange;
	}
	/**
	 * @param referencesRange the referencesRange to set
	 */
	public void setReferencesRange(String referencesRange) {
		this.referencesRange = referencesRange;
	}
	/**
	 * @return the observationResultStatus
	 */
	public String getObservationResultStatus() {
		return observationResultStatus;
	}
	/**
	 * @param observationResultStatus the observationResultStatus to set
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
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return the informationId
	 */
	public String getInformationId() {
		return informationId;
	}
	/**
	 * @param informationId the informationId to set
	 */
	public void setInformationId(String informationId) {
		this.informationId = informationId;
	}
	/**
	 * @return the observationId
	 */
	public String getObservationId() {
		return observationId;
	}
	/**
	 * @param observationId the observationId to set
	 */
	public void setObservationId(String observationId) {
		this.observationId = observationId;
	}


}
