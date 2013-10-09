/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.remote.admin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.webapp.dynasite.dao.*;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.utils.DataStructureUtils;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.WidgetUtils;
import org.cidrz.webapp.dynasite.utils.sort.EnumNumericOrderComparator;
import org.cidrz.webapp.dynasite.valueobject.*;

import uk.ltd.getahead.dwr.WebContext;
import uk.ltd.getahead.dwr.WebContextFactory;

import javax.servlet.ServletException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Oct 12, 2005
 *         Time: 3:31:15 PM
 */
public class Dynasite {

    /**
     * Commons Logging instance.
     */
    public static Log log = LogFactory.getFactory().getInstance(Dynasite.class);

    public static PageItem getPageItem(Long pageItemId) {
        PageItem pageItem = null;
        Connection conn = null;
        try {
            conn = DatabaseUtils.getAdminConnection();
            WebContext exec = WebContextFactory.get();
            Long siteId = null;
            SessionUtil zeprs_session = null;
            try {
                zeprs_session = (SessionUtil) exec.getSession().getAttribute("zeprs_session");
            } catch (Exception e) {
                // unit testing - it's ok...
            }

            try {
                ClientSettings clientSettings = zeprs_session.getClientSettings();
                siteId = clientSettings.getSiteId();
            } catch (SessionUtil.AttributeNotFoundException e) {
                log.error(e);
            } catch (NullPointerException e) {
                // it's ok - unit testing
                siteId = new Long("1");
            }

            // PageItem pageItem = (PageItem) DynaSiteObjects.getPageItems().get(pageItemId);
            pageItem = null;
            try {
                pageItem = PageItemDAO.getPageItemGraph(conn, pageItemId);
            } catch (SQLException e) {
                log.error(e);
            } catch (ServletException e) {
                log.error(e);
            } catch (ObjectNotFoundException e) {
                log.error(e);
            }
            if (pageItem.getId().intValue() == 3861) {    // lab result enums are gathered from several fields
                List labResultEnums = null;
                // labResultEnums = FieldEnumerationDAO.getLabEnums();
                labResultEnums = WidgetUtils.getDynaSiteLabEnums();
                FormField formField = pageItem.getForm_field();
                EnumNumericOrderComparator c = new EnumNumericOrderComparator();
                Set labResultSet = DataStructureUtils.listToSet(labResultEnums, c);
                formField.setEnumerations(labResultSet);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return pageItem;
    }

    public static FieldEnumeration getFieldEnumeration(Long id) {
        FieldEnumeration fieldEnum = null;
        Connection conn = null;
        try {
            conn = DatabaseUtils.getAdminConnection();
            WebContext exec = WebContextFactory.get();
            Long siteId = null;
            SessionUtil zeprs_session = null;
            try {
                zeprs_session = (SessionUtil) exec.getSession().getAttribute("zeprs_session");
            } catch (Exception e) {
                // unit testing - it's ok...
            }

            try {
                ClientSettings clientSettings = zeprs_session.getClientSettings();
                siteId = clientSettings.getSiteId();
            } catch (SessionUtil.AttributeNotFoundException e) {
                log.error(e);
            } catch (NullPointerException e) {
                // it's ok - unit testing
                siteId = new Long("1");
            }
            try {
                fieldEnum = FieldEnumerationDAO.getOne(conn, id);
            } catch (SQLException e) {
                log.error(e);
            } catch (ServletException e) {
                log.error(e);
            } catch (ObjectNotFoundException e) {
                log.error(e);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return fieldEnum;
    }

    public static String updatePageItem(PageItem pageItem) {
        Connection conn = DatabaseUtils.getAdminConnection();
        String result = "";
        WebContext exec = WebContextFactory.get();
        Long siteId = null;
        SessionUtil zeprs_session = null;
        try {
            zeprs_session = (SessionUtil) exec.getSession().getAttribute("zeprs_session");
        } catch (Exception e) {
            // unit testing - it's ok...
        }

        try {
            ClientSettings clientSettings = zeprs_session.getClientSettings();
            siteId = clientSettings.getSiteId();
        } catch (SessionUtil.AttributeNotFoundException e) {
            log.error(e);
        } catch (NullPointerException e) {
            // it's ok - unit testing
            siteId = new Long("1");
        }

        Long formId = pageItem.getFormId();
        try {
            FormFieldDAO.updateFormfield(conn, pageItem.getForm_field());
            PageItemDAO.update(conn, pageItem);
            result = "Field updated.";
            pageItem = PageItemDAO.getPageItemGraph(conn, pageItem.getId());
            DynaSiteObjects.getPageItems().put(pageItem.getId(), pageItem);
            // Now add this form to the FormChanges list.
            FormChanges.makeDirty(formId);
        } catch (SQLException e) {
            log.error(e);
            result = "Database Error.";
        } catch (ServletException e) {
            log.error(e);
            result = "Server Error.";
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
			e.printStackTrace();
		}
        return result;
    }

    public static Collection getRules(Long pageItemId) {
        Connection conn = DatabaseUtils.getAdminConnection();
        WebContext exec = WebContextFactory.get();
        Long siteId = null;
        SessionUtil zeprs_session = null;
        try {
            zeprs_session = (SessionUtil) exec.getSession().getAttribute("zeprs_session");
        } catch (Exception e) {
            // unit testing - it's ok...
        }

        try {
            ClientSettings clientSettings = zeprs_session.getClientSettings();
            siteId = clientSettings.getSiteId();
        } catch (SessionUtil.AttributeNotFoundException e) {
            log.error(e);
        } catch (NullPointerException e) {
            // it's ok - unit testing
            siteId = new Long("1");
        }

        // PageItem pageItem = (PageItem) DynaSiteObjects.getPageItems().get(pageItemId);
        PageItem pageItem = null;
        try {
            pageItem = PageItemDAO.getPageItemGraph(conn, pageItemId);
        } catch (SQLException e) {
            log.error(e);
        } catch (ServletException e) {
            log.error(e);
        } catch (ObjectNotFoundException e) {
            log.error(e);
        }
        Long formFieldId = pageItem.getFormFieldId();
        // List ruleList = (List) DynaSiteObjects.getRules().get(formFieldId);
        List ruleList = null;
        try {
            ruleList = RuleDefinitionDAO.getAll(conn, formFieldId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return ruleList;
    }

    public static String updateRule(Long id, String element, String value) {
        Connection conn = DatabaseUtils.getAdminConnection();
        String result = "";
        WebContext exec = WebContextFactory.get();
        Long siteId = null;
        SessionUtil zeprs_session = null;
        try {
            zeprs_session = (SessionUtil) exec.getSession().getAttribute("zeprs_session");
        } catch (Exception e) {
            // unit testing - it's ok...
        }

        try {
            ClientSettings clientSettings = zeprs_session.getClientSettings();
            siteId = clientSettings.getSiteId();
        } catch (SessionUtil.AttributeNotFoundException e) {
            log.error(e);
        } catch (NullPointerException e) {
            // it's ok - unit testing
            siteId = new Long("1");
        }

        try {
            RuleDefinitionDAO.updateRule(conn, id, element, value);
            RuleDefinition rule = (RuleDefinition) DynaSiteObjects.getRuleMap().get(id);
            Long formFieldId = rule.getFieldId();
            List<Long> forms = (List<Long>) DynaSiteObjects.getFieldToForms().get(formFieldId);
            for (Long formId : forms) {
            	// Now add this form to the FormChanges list.
                FormChanges.makeDirty(formId);
			}
            result = "Rule updated.";
        } catch (SQLException e) {
            log.error(e);
            result = "Database Error.";
        } catch (ServletException e) {
            log.error(e);
            result = "Server Error.";
        } catch (IOException e) {
        	log.error(e);
		}

        // refresh dynaSite
        // PageItem pageItem = PageItemDAO.getPageItemGraph()
        return result;
    }

    /**
     * Updates admin database schema
     * @param widgetLocation
     * @param name
     * @param type
     * @param column
     * @param id
     * @param value
     * @param text
     * @return
     */
    public static String updateElement(String widgetLocation, String name, String type, String column, String id, String value, String text) {
        String result = null;
        Connection conn = null;
        try {
            conn = DatabaseUtils.getAdminConnection();
            FormDisplayDAO.updateFormElement(conn, column, value, Long.valueOf(id));
            // Now add this form to the FormChanges list.
            FormChanges.makeDirty(Long.valueOf(id));
            if (type.equals("select")) {
                result = widgetLocation + "=" + text;
            } else if (type.equals("checkbox")) {
                if (value.equals("0")) {
                    result = widgetLocation + "=false";
                } else {
                    result = widgetLocation + "=true";
                }
            } else {
                result = widgetLocation + "=" + value;
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
			e.printStackTrace();
		} finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Updates displayOrder in pageItems.
     *
     * @param displayOrder
     * @param id
     * @return result
     */
    public static String updateDisplayOrder(String displayOrder, String id) {
        String result = null;
        Connection conn = null;
        try {
            conn = DatabaseUtils.getAdminConnection();
            PageItemDAO.updateDisplayOrder(conn, Long.valueOf(displayOrder), Long.valueOf(id));
            result = "Order Updated.";
            // Get the formId
            PageItem pageItem = PageItemDAO.getFormId(conn,Long.valueOf(id));
            Long formId = pageItem.getFormId();
            // Now add this form to the FormChanges list.
            FormChanges.makeDirty(Long.valueOf(formId));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
			e.printStackTrace();
		} catch (ObjectNotFoundException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * creates new enum
     *
     * @param fieldId
     * @param value
     * @param numericValue
     * @return result string
     */
    public static String createFieldEnum(Long fieldId, String value, Integer numericValue) {
        String result = null;
        Connection conn = null;
        try {
            conn = DatabaseUtils.getAdminConnection();
            FieldEnumerationDAO.create(conn, fieldId, value, numericValue);
            result = "Field created.";
            List<Long> forms = (List<Long>) DynaSiteObjects.getFieldToForms().get(fieldId);
            for (Long formId : forms) {
            	// Now add this form to the FormChanges list.
                FormChanges.makeDirty(formId);
			}
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
			e.printStackTrace();
		} finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Create enums from a list
     * @param fieldId
     * @param list
     * @return
     */
    public static String createEnums(Long fieldId, String list) {
        String result = null;
        Connection conn = null;
        try {
            conn = DatabaseUtils.getAdminConnection();
            // get the highest value for the enabled enums
            Integer maxValueEnabled = FieldEnumerationDAO.getMaxDisplayOrder(conn, fieldId, 1);
            // get the highest value for the enabled enums
            Integer maxValueDisabled = FieldEnumerationDAO.getMaxDisplayOrder(conn, fieldId, 0);
            if (maxValueEnabled > 0) {
            	maxValueEnabled++;
            }
            if (maxValueDisabled > 0) {
            	maxValueDisabled++;
            }
            List<Form> forms = (List<Form>) FormFieldDAO.getFormIdList(conn, fieldId);
            if (forms == null) {
            	return "This field is not in the field list.";
            }
            for (Form form : forms) {
            	Long formId = form.getId();
            	FormChanges.makeDirty(formId);
			}
            FieldEnumerationDAO.createEnums(conn, fieldId, list, maxValueEnabled, maxValueDisabled);
            List enums = FieldEnumerationDAO.getAll(conn, fieldId);
            for (int i = 0; i < enums.size(); i++) {
                FieldEnumeration fieldEnum = (FieldEnumeration) enums.get(i);
                DynaSiteObjects.getFieldEnumerations().put(fieldEnum.getId(), fieldEnum);
            }
            result = "Enumerations created.";
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
			e.printStackTrace();
		} finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Fetch list of enumerations for a field.
     * @param fieldId
     * @return
     */
    public static List getEnums(Long fieldId) {
    	List currentEnums = null;
    	Connection conn = null;
    	try {
    		conn = DatabaseUtils.getAdminConnection();
    		// get a list of current enums, if any
    		currentEnums = (ArrayList) FieldEnumerationDAO.getAll(conn, fieldId);
    	} catch (NumberFormatException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} catch (ServletException e) {
    		e.printStackTrace();
    	} finally {
    		try {
    			if (conn != null && !conn.isClosed()) {
    				conn.close();
    			}
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	return currentEnums;
    }

    public static String updateEnum(Long id, String enumValue, int numericValue, int displayOrder, boolean enabled) {
        String result = null;
        Connection conn = null;
        try {
            conn = DatabaseUtils.getAdminConnection();
            int isEnabled = 0;
            if (enabled) {
                isEnabled = 1;
            }
            FieldEnumerationDAO.updateEnum(conn, id, enumValue, numericValue, displayOrder, isEnabled);
            result = "Enumeration updated.";

            FieldEnumeration fieldEnumeration = (FieldEnumeration) DynaSiteObjects.getFieldEnumerations().get(id);
            Long fieldId = fieldEnumeration.getFieldId();
            List<Form> forms = (List<Form>) FormFieldDAO.getFormIdList(conn, fieldId);
            if (forms == null) {
            	return "This field is not in the field list.";
            }
            for (Form form : forms) {
            	Long formId = form.getId();
            	FormChanges.makeDirty(formId);
			}
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Save shared field.
     *
     * @param numFields
     * @param numFields
     * @return result
     * @deprecated Uses obsolete verson of FormFieldDAO.createColumn
     */
    public static String saveSharedField(int numFields, String fieldId, String formId) {
        String result = null;
        Connection conn = null;
        WebContext exec = WebContextFactory.get();
        Long siteId = null;
        SessionUtil zeprs_session = null;
        try {
            zeprs_session = (SessionUtil) exec.getSession().getAttribute("zeprs_session");
        } catch (Exception e) {
            // unit testing - it's ok...
        }

        try {
            ClientSettings clientSettings = zeprs_session.getClientSettings();
            siteId = clientSettings.getSiteId();
        } catch (SessionUtil.AttributeNotFoundException e) {
            log.error(e);
        } catch (NullPointerException e) {
            // it's ok - unit testing
            siteId = new Long("1");
        }
        try {
            conn = DatabaseUtils.getAdminConnection();
            // Long pageItemId = (Long) PageItemDAO.updateDisplayOrder(conn, Long.valueOf(numFields), Long.valueOf(id));
            PageItemDAO.saveSharedfield(conn, "admin", new Long(formId), new Long(fieldId), "zepadmin", siteId, numFields);
            FormField formField = FormFieldDAO.getOne(conn, new Long(fieldId));
            // FormFieldDAO.createColumn(conn, formField, new Long(formId));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}

