/**
 *
 */
package org.rti.zcore;

/**
 * @author ckelley@rti.org
 *
 */
public class DropdownItem {

	private String dropdownId;
	private String dropdownValue;
	private String dropdownUuid;

	public String getDropdownId() {
		return dropdownId;
	}
	public void setDropdownId(String dropdownId) {
		this.dropdownId = dropdownId;
	}
	public String getDropdownValue() {
		return dropdownValue;
	}
	public void setDropdownValue(String dropdownValue) {
		this.dropdownValue = dropdownValue;
	}
	/**
	 * @return the dropdownUuid
	 */
	public String getDropdownUuid() {
		return dropdownUuid;
	}
	/**
	 * @param dropdownUuid the dropdownUuid to set
	 */
	public void setDropdownUuid(String dropdownUuid) {
		this.dropdownUuid = dropdownUuid;
	}



}
