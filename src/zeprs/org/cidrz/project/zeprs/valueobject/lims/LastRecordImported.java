/**
 *
 */
package org.cidrz.project.zeprs.valueobject.lims;

import java.sql.Timestamp;
import java.sql.Date;

/**
 * @author ckelley
 * Logs data when last record imported and ids.
 */
public class LastRecordImported {

	private String info;
	private String informationId;
	private String observationId;
	private Timestamp observationDate;
	private Timestamp dateCompletedImport;


	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}
	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
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
	 * @return the dateCompletedImport
	 */
	public Timestamp getDateCompletedImport() {
		return dateCompletedImport;
	}
	/**
	 * @param dateCompletedImport the dateCompletedImport to set
	 */
	public void setDateCompletedImport(Timestamp dateCompletedImport) {
		this.dateCompletedImport = dateCompletedImport;
	}

}
