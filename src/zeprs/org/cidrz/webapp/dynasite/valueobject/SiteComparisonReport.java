package org.cidrz.webapp.dynasite.valueobject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.cidrz.project.zeprs.valueobject.EncounterData;

public class SiteComparisonReport {
	private String localSiteAbbrev;
	private String remoteSiteAbbrev	;
	private int localSize;
	private int remoteSize;
	//private HashMap<String,Patient> missingLocalPatientMap = new HashMap<String,Patient>();
	private HashMap<String,Patient> missingRemotePatientMap = new HashMap<String,Patient>();
	//private HashMap<String,String> missingLocalPregnancyMap = new HashMap<String,String>();
	private HashMap<String,String> missingRemotePregnancyMap = new HashMap<String,String>();
	//private HashMap<String,EncounterData> missingLocalPregnancyEncounterMap = new HashMap<String,EncounterData>();
	private HashMap<String,EncounterData> missingRemotePregnancyEncounterMap = new HashMap<String,EncounterData>();
	//private HashMap<String,EncounterData> incorrectLocalPregnancyEncounterMap = new HashMap<String,EncounterData>();
	private HashMap<String,EncounterData> incorrectRemotePregnancyEncounterMap = new HashMap<String,EncounterData>();
	//private HashMap<String,ArrayList> incorrectLocalPregnancyEncounterResultsMap = new HashMap<String,ArrayList>();
	private HashMap<String,ArrayList> incorrectRemotePregnancyEncounterResultsMap = new HashMap<String,ArrayList>();
	private List<String> patientSyncList;
	private Date reportDate;
	/**
	 * @return the localSiteAbbrev
	 */
	public String getLocalSiteAbbrev() {
		return localSiteAbbrev;
	}
	/**
	 * @param localSiteAbbrev the localSiteAbbrev to set
	 */
	public void setLocalSiteAbbrev(String localSiteAbbrev) {
		this.localSiteAbbrev = localSiteAbbrev;
	}
	/**
	 * @return the remoteSiteAbbrev
	 */
	public String getRemoteSiteAbbrev() {
		return remoteSiteAbbrev;
	}
	/**
	 * @param remoteSiteAbbrev the remoteSiteAbbrev to set
	 */
	public void setRemoteSiteAbbrev(String remoteSiteAbbrev) {
		this.remoteSiteAbbrev = remoteSiteAbbrev;
	}
	/**
	 * @return the localSize
	 */
	public int getLocalSize() {
		return localSize;
	}
	/**
	 * @param localSize the localSize to set
	 */
	public void setLocalSize(int localSize) {
		this.localSize = localSize;
	}
	/**
	 * @return the remoteSize
	 */
	public int getRemoteSize() {
		return remoteSize;
	}
	/**
	 * @param remoteSize the remoteSize to set
	 */
	public void setRemoteSize(int remoteSize) {
		this.remoteSize = remoteSize;
	}
	/**
	 * @return the missingRemotePatientMap
	 */
	public HashMap<String, Patient> getMissingRemotePatientMap() {
		return missingRemotePatientMap;
	}
	/**
	 * @param missingRemotePatientMap the missingRemotePatientMap to set
	 */
	public void setMissingRemotePatientMap(HashMap<String, Patient> missingRemotePatientMap) {
		this.missingRemotePatientMap = missingRemotePatientMap;
	}
	/**
	 * @return the missingRemotePregnancyMap
	 */
	public HashMap<String, String> getMissingRemotePregnancyMap() {
		return missingRemotePregnancyMap;
	}
	/**
	 * @param missingRemotePregnancyMap the missingRemotePregnancyMap to set
	 */
	public void setMissingRemotePregnancyMap(HashMap<String, String> missingRemotePregnancyMap) {
		this.missingRemotePregnancyMap = missingRemotePregnancyMap;
	}
	/**
	 * @return the missingRemotePregnancyEncounterMap
	 */
	public HashMap<String, EncounterData> getMissingRemotePregnancyEncounterMap() {
		return missingRemotePregnancyEncounterMap;
	}
	/**
	 * @param missingRemotePregnancyEncounterMap the missingRemotePregnancyEncounterMap to set
	 */
	public void setMissingRemotePregnancyEncounterMap(HashMap<String, EncounterData> missingRemotePregnancyEncounterMap) {
		this.missingRemotePregnancyEncounterMap = missingRemotePregnancyEncounterMap;
	}
	/**
	 * @return the incorrectRemotePregnancyEncounterMap
	 */
	public HashMap<String, EncounterData> getIncorrectRemotePregnancyEncounterMap() {
		return incorrectRemotePregnancyEncounterMap;
	}
	/**
	 * @param incorrectRemotePregnancyEncounterMap the incorrectRemotePregnancyEncounterMap to set
	 */
	public void setIncorrectRemotePregnancyEncounterMap(HashMap<String, EncounterData> incorrectRemotePregnancyEncounterMap) {
		this.incorrectRemotePregnancyEncounterMap = incorrectRemotePregnancyEncounterMap;
	}
	/**
	 * @return the incorrectRemotePregnancyEncounterResultsMap
	 */
	public HashMap<String, ArrayList> getIncorrectRemotePregnancyEncounterResultsMap() {
		return incorrectRemotePregnancyEncounterResultsMap;
	}
	/**
	 * @param incorrectRemotePregnancyEncounterResultsMap the incorrectRemotePregnancyEncounterResultsMap to set
	 */
	public void setIncorrectRemotePregnancyEncounterResultsMap(
			HashMap<String, ArrayList> incorrectRemotePregnancyEncounterResultsMap) {
		this.incorrectRemotePregnancyEncounterResultsMap = incorrectRemotePregnancyEncounterResultsMap;
	}
	/**
	 * @return the reportDate
	 */
	public Date getReportDate() {
		return reportDate;
	}
	/**
	 * @param reportDate the reportDate to set
	 */
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	/**
	 * @return the patientSyncList
	 */
	public List<String> getPatientSyncList() {
		return patientSyncList;
	}
	/**
	 * @param patientSyncList the patientSyncList to set
	 */
	public void setPatientSyncList(List<String> patientSyncList) {
		this.patientSyncList = patientSyncList;
	}



}
