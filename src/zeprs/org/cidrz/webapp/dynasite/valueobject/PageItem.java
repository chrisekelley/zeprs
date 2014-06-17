/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.valueobject;

import org.cidrz.webapp.dynasite.valueobject.AuditInfo;

import java.util.HashMap;
import java.io.Serializable;


/**
 * @hibernate.class table="page_item"  lazy="false"
 * mutable="true"
 * @hibernate.cache usage="read-write"
 */
public class PageItem implements Identifiable, org.cidrz.webapp.dynasite.valueobject.Auditable, Configuration, DisplayOrderable, Serializable {
    private Long id;
    private FormField form_field = new FormField();
    private Long formFieldId;
    private Integer displayOrder;
    //= new Integer(Integer.MAX_VALUE);
    private String inputType;
    private boolean closeRow;
    private Integer columnNumber;
    private Integer size;
    private Integer maxlength;
    private Integer rows;
    private Integer cols;
    private boolean visible;
    private String visibleEnumIdTrigger1;
    private String visibleDependencies1;
    private String visibleEnumIdTrigger2;
    private Long selectedEnum;
    private String visibleDependencies2;
    private AuditInfo auditInfo;
    // private Set forms;
    private Form form;
    private Long formId;
    private Integer colspan;
    private HashMap dempMap1;
    private HashMap dempMap2;
    private boolean highlightCell;
    private Long recordCount;	// count of records that use this field.
    private String dropdownTable;
    private String dropdownColumn;
    private String dropdownConstraint;
    private String dropdownOrderByClause;
    private String currentFieldNameIdentifier;	// used when dynamically rendering fields for Patient Bridge tables.
    private String roles;
    private Long importId;
    private String fkIdentifier;	// id or uuid - which identifier is used for foreign keys.

    public PageItem() {
    }

    public PageItem(Long id, FormField form_field, Long formFieldId, Integer displayOrder, String inputType, boolean closeRow, Integer columnNumber, Integer size, Integer maxlength, Integer rows, Integer cols, boolean visible, String visibleEnumIdTrigger1, String visibleDependencies1, String visibleEnumIdTrigger2, Long selectedEnum, String visibleDependencies2, AuditInfo auditInfo, Form form, Long formId, Integer colspan, HashMap dempMap1, HashMap dempMap2) {
        this.id = id;
        this.form_field = form_field;
        this.formFieldId = formFieldId;
        this.displayOrder = displayOrder;
        this.inputType = inputType;
        this.closeRow = closeRow;
        this.columnNumber = columnNumber;
        this.size = size;
        this.maxlength = maxlength;
        this.rows = rows;
        this.cols = cols;
        this.visible = visible;
        this.visibleEnumIdTrigger1 = visibleEnumIdTrigger1;
        this.visibleDependencies1 = visibleDependencies1;
        this.visibleEnumIdTrigger2 = visibleEnumIdTrigger2;
        this.selectedEnum = selectedEnum;
        this.visibleDependencies2 = visibleDependencies2;
        this.auditInfo = auditInfo;
        this.form = form;
        this.formId = formId;
        this.colspan = colspan;
        this.dempMap1 = dempMap1;
        this.dempMap2 = dempMap2;
    }

    /**
     * @return
     * @hibernate.id unsaved-value="0"
     * generator-class="identity"
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return
     * @hibernate.many-to-one column="form_field_id"
     * cascade="save-update"
     * class="org.cidrz.webapp.dynasite.valueobject.FormField"
     * outer-join="true"
     */


    public FormField getForm_field() {
        return form_field;
    }

    public void setForm_field(FormField form_field) {
        this.form_field = form_field;
    }

    public Long getFormFieldId() {
        return formFieldId;
    }

    public void setFormFieldId(Long formFieldId) {
        this.formFieldId = formFieldId;
    }

    /**
     * @return
     * @hibernate.property column="display_order"
     * not-null="false"
     */
    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    /**
     * @return
     * @hibernate.property column="input_type"
     * not-null="true"
     */
    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    /**
     * @return
     * @hibernate.property column="close_row"
     * not-null="false"
     */
    public boolean isCloseRow() {
        return closeRow;
    }

    public void setCloseRow(boolean closeRow) {
        this.closeRow = closeRow;
    }

    /**
     * @return
     * @hibernate.property column="column_number"
     * not-null="false"
     */
    public Integer getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(Integer columnNumber) {
        this.columnNumber = columnNumber;
    }

    /**
     * @hibernate.component
     * class="org.cidrz.webapp.dynasite.valueobject.AuditInfo"
     */
    public AuditInfo getAuditInfo() {
        if (auditInfo == null) {
            auditInfo = new AuditInfo();
        }
        return auditInfo;
    }

    public void setAuditInfo(AuditInfo auditInfo) {
        this.auditInfo = auditInfo;
    }

    /**
     * @return
     * @hibernate.property column="size"
     * not-null="false"
     */
    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * @return
     * @hibernate.property column="maxlength"
     * not-null="false"
     */
    public Integer getMaxlength() {
        return maxlength;
    }

    public void setMaxlength(Integer maxlength) {
        this.maxlength = maxlength;
    }

    /**
     * @return
     * @hibernate.property column="rows"
     * not-null="false"
     */
    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    /**
     * @return
     * @hibernate.property column="cols"
     * not-null="false"
     */
    public Integer getCols() {
        return cols;
    }

    public void setCols(Integer cols) {
        this.cols = cols;
    }

    /**
     * @return
     * @hibernate.property column="visible" not-null="false"
     */
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * @return
     * @hibernate.property column="visible_enum_id_trigger1" not-null="false"
     */
    public String getVisibleEnumIdTrigger1() {
        return visibleEnumIdTrigger1;
    }

    public void setVisibleEnumIdTrigger1(String visibleEnumIdTrigger1) {
        this.visibleEnumIdTrigger1 = visibleEnumIdTrigger1;
    }


    /**
     * @return
     * @hibernate.property column="visible_dependencies1" not-null="false"
     */

    public String getVisibleDependencies1() {
        return visibleDependencies1;
    }

    public void setVisibleDependencies1(String visibleDependencies1) {
        this.visibleDependencies1 = visibleDependencies1;
    }

    /**
     * @return
     * @hibernate.property column="visible_enum_id_trigger2" not-null="false"
     */
    public String getVisibleEnumIdTrigger2() {
        return visibleEnumIdTrigger2;
    }

    public void setVisibleEnumIdTrigger2(String visibleEnumIdTrigger2) {
        this.visibleEnumIdTrigger2 = visibleEnumIdTrigger2;
    }

    /**
     * @return
     * @hibernate.property column="visible_dependencies2" not-null="false"
     */
    public String getVisibleDependencies2() {
        return visibleDependencies2;
    }

    public void setVisibleDependencies2(String visibleDependencies2) {
        this.visibleDependencies2 = visibleDependencies2;
    }

     /**
     * @return
     * @hibernate.property column="selected_enum" not-null="false"
     */
    public Long getSelectedEnum() {
        return selectedEnum;
    }

    public void setSelectedEnum(Long selectedEnum) {
        this.selectedEnum = selectedEnum;
    }

    /**
     * @return
     * @hibernate.many-to-one column="form_id"
     * lazy="false proxy"
     * cascade="none"
     */


    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    /**
     * @return
     * @hibernate.property column="colspan" not-null="false"
     */

    public Integer getColspan() {
        return colspan;
    }

    public void setColspan(Integer colspan) {
        this.colspan = colspan;
    }

    // Speed up resolution of dependencies
    public HashMap getDempMap1() {
        return dempMap1;
    }

    public void setDempMap1(HashMap dempMap1) {
        this.dempMap1 = dempMap1;
    }

    public HashMap getDempMap2() {
        return dempMap2;
    }

    public void setDempMap2(HashMap dempMap2) {
        this.dempMap2 = dempMap2;
    }

    public boolean isHighlightCell() {
        return highlightCell;
    }

    public void setHighlightCell(boolean highlightCell) {
        this.highlightCell = highlightCell;
    }

	public Long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Long recordCount) {
		this.recordCount = recordCount;
	}

    /**
     * @return comma-delimited list of roles that may view this pageItem.
     * @hibernate.property column="roles" not-null="false"
     */
	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getDropdownTable() {
		return dropdownTable;
	}

	public void setDropdownTable(String dropdownTable) {
		this.dropdownTable = dropdownTable;
	}

	public String getDropdownColumn() {
		return dropdownColumn;
	}

	public void setDropdownColumn(String dropdownColumn) {
		this.dropdownColumn = dropdownColumn;
	}

	public String getDropdownConstraint() {
		return dropdownConstraint;
	}

	public void setDropdownConstraint(String dropdownConstraint) {
		this.dropdownConstraint = dropdownConstraint;
	}

	public String getDropdownOrderByClause() {
		return dropdownOrderByClause;
	}

	public void setDropdownOrderByClause(String dropdownOrderByClause) {
		this.dropdownOrderByClause = dropdownOrderByClause;
	}

	public String getCurrentFieldNameIdentifier() {
		return currentFieldNameIdentifier;
	}

	public void setCurrentFieldNameIdentifier(String currentFieldNameIdentifier) {
		this.currentFieldNameIdentifier = currentFieldNameIdentifier;
	}

	 /**
     * @return
     * @hibernate.property column="import_id"
     */
	public Long getImportId() {
		return importId;
	}

	public void setImportId(Long importId) {
		this.importId = importId;
	}

	public String getFkIdentifier() {
		return fkIdentifier;
	}

	public void setFkIdentifier(String fkIdentifier) {
		this.fkIdentifier = fkIdentifier;
	}



}
