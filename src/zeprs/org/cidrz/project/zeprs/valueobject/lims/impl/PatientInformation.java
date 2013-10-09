
package org.cidrz.project.zeprs.valueobject.lims.impl;

import java.sql.Date;

import org.cidrz.project.zeprs.valueobject.lims.ObservationMetaData;

/**
 * @author ckelley
 * The classes in this package are for connecting to the SQL Server instance
 * which stores data from the LIMS lab system.
 */

/**
 * ---fixme-hibernate.class table="PatientInformation"
 * lazy="true"
 * mutable="true"
 */
public class PatientInformation implements ObservationMetaData {

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



	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.LimsMetaData#getId()
	 */
	public String getId() {
		return id;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.LimsMetaData#setId(java.lang.Long)
	 */
	public void setId(String id) {
		this.id = id;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.LimsMetaData#getNatID()
	 */
	public String getNatID() {
		return natID;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.LimsMetaData#setNatID(java.lang.String)
	 */
	public void setNatID(String natID) {
		this.natID = natID;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.LimsMetaData#getFirstName()
	 */
	public String getFirstName() {
		return firstName;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.LimsMetaData#setFirstName(java.lang.String)
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.LimsMetaData#getSurname()
	 */
	public String getSurname() {
		return surname;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.LimsMetaData#setSurname(java.lang.String)
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.LimsMetaData#getClinic()
	 */
	public String getClinic() {
		return clinic;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.LimsMetaData#setClinic(java.lang.String)
	 */
	public void setClinic(String clinic) {
		this.clinic = clinic;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.LimsMetaData#getStudy()
	 */
	public String getStudy() {
		return study;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.LimsMetaData#setStudy(java.lang.String)
	 */
	public void setStudy(String study) {
		this.study = study;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.LimsMetaData#getDob()
	 */
	public Date getDob() {
		return dob;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.LimsMetaData#setDob(java.sql.Date)
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.LimsMetaData#getSex()
	 */
	public String getSex() {
		return sex;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.LimsMetaData#setSex(java.lang.String)
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.LimsMetaData#getCollection()
	 */
	public Date getCollection() {
		return collection;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.LimsMetaData#setCollection(java.sql.Date)
	 */
	public void setCollection(Date collection) {
		this.collection = collection;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.LimsMetaData#getReception()
	 */
	public Date getReception() {
		return reception;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.LimsMetaData#setReception(java.sql.Date)
	 */
	public void setReception(Date reception) {
		this.reception = reception;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.LimsMetaData#getObservationRequest()
	 */
	public String getObservationRequest() {
		return observationRequest;
	}
	/* (non-Javadoc)
	 * @see org.cidrz.project.zeprs.valueobject.lims.LimsMetaData#setObservationRequest(java.lang.String)
	 */
	public void setObservationRequest(String observationRequest) {
		this.observationRequest = observationRequest;
	}



}
