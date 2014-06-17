package org.rti.zcore;

import java.util.ArrayList;

public class ApplicationDefinition {

	private Long id;
	private String uuid;
	private String appName;
	private String appTitle;
	private String url;
	private String formListFilename;
	private String flowListFilename;
	private ArrayList<String> localList;
	private String defaultLocale;
	private Boolean problemListEnabled;
	private Boolean useFileEncryption;

	public ApplicationDefinition(Long id, String uuid, String appName, String appTitle, String url,
			String forms, String flows, ArrayList<String> localeList, String defaultLocale, Boolean problemListEnabled, Boolean useFileEncryption) {
		super();
		this.id = id;
		this.uuid = uuid;
		this.appName = appName;
		this.appTitle = appTitle;
		this.url = url;
		this.formListFilename = forms;
		this.flowListFilename = flows;
		this.localList = localeList;
		this.defaultLocale = defaultLocale;
		this.problemListEnabled = problemListEnabled;
		this.useFileEncryption = useFileEncryption;
	}

	public ApplicationDefinition() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}
	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	/**
	 * @return the appName
	 */
	public String getAppName() {
		return appName;
	}
	/**
	 * @param appName the appName to set
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}
	/**
	 * @return the appTitle
	 */
	public String getAppTitle() {
		return appTitle;
	}
	/**
	 * @param appTitle the appTitle to set
	 */
	public void setAppTitle(String appTitle) {
		this.appTitle = appTitle;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the forms
	 */
	public String getForms() {
		return formListFilename;
	}
	/**
	 * @param forms the forms to set
	 */
	public void setForms(String forms) {
		this.formListFilename = forms;
	}
	/**
	 * @return the flows
	 */
	public String getFlows() {
		return flowListFilename;
	}
	/**
	 * @param flows the flows to set
	 */
	public void setFlows(String flows) {
		this.flowListFilename = flows;
	}

	public ArrayList<String> getLocalList() {
		return localList;
	}

	public void setLocalList(ArrayList<String> localList) {
		this.localList = localList;
	}

	/**
	 * @return the defaultLocale
	 */
	public String getDefaultLocale() {
		return defaultLocale;
	}

	/**
	 * @param defaultLocale the defaultLocale to set
	 */
	public void setDefaultLocale(String defaultLocale) {
		this.defaultLocale = defaultLocale;
	}

	/**
	 * @return the problemListEnabled
	 */
	public Boolean getProblemListEnabled() {
		return problemListEnabled;
	}

	/**
	 * @param problemListEnabled the problemListEnabled to set
	 */
	public void setProblemListEnabled(Boolean problemListEnabled) {
		this.problemListEnabled = problemListEnabled;
	}

	/**
	 * @return the useFileEncryption
	 */
	public Boolean getUseFileEncryption() {
		return useFileEncryption;
	}

	/**
	 * @param useFileEncryption the useFileEncryption to set
	 */
	public void setUseFileEncryption(Boolean useFileEncryption) {
		this.useFileEncryption = useFileEncryption;
	}

}
