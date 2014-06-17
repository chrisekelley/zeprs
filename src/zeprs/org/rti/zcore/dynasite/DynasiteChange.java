/**
 *
 */
package org.rti.zcore.dynasite;



/**
 * Track changes to Forms managed by Dynasite
 * These are persisted as an XML file with every change of tracked objects.
 * This logs updates that should be initiated on another zcore instance.
 *
 * If it is a form change, the form id is stored in the id field. When dynasite starts up, the form is re-processed.
 * @see DynasiteChangesList
 * Also processes changes to Flow and FormType.
 *
 * @author chrisk
 *
 */
public class DynasiteChange {

	private Long id;
	private String changeType;	// what type of change - form,site, etc

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getChangeType() {
		return changeType;
	}
	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

}
