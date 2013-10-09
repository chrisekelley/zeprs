/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.remote;

import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.dao.FlowDAO;
import org.cidrz.webapp.dynasite.dao.FormTypeDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.WidgetUtils;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.PageItem;
import uk.ltd.getahead.dwr.WebContext;
import uk.ltd.getahead.dwr.WebContextFactory;

import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jul 12, 2005
 *         Time: 8:10:27 AM
 */
public class WidgetDisplay {
    public String dispatch(String value, Long pageItemId, Long fieldId, Long formId, Long encounterId) {
        String result = null;
        WebContext exec = WebContextFactory.get();
        String username = null;
        try {
            username = exec.getHttpServletRequest().getUserPrincipal().getName();
        } catch (NullPointerException e) {
            // unit testing - it's ok...
            username = "demo";
        }
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);
            String widget = null;

            if (value.equals("")) {
                value = " ";
            }
            PageItem pageItem = (PageItem) DynaSiteObjects.getPageItems().get(pageItemId);
            widget = WidgetUtils.getOne(conn, pageItem, value, formId, encounterId, "Form");
            result = fieldId + "=" + widget;
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

    public String dispatchChart(String value, Long pageItemId, Long fieldId, Long formId, Long encounterId) {
        String widget = null;
        String result = null;
        PageItem pageItem = (PageItem) DynaSiteObjects.getPageItems().get(pageItemId);
        WebContext exec = WebContextFactory.get();
        String username = null;
        try {
            username = exec.getHttpServletRequest().getUserPrincipal().getName();
        } catch (NullPointerException e) {
            // unit testing - it's ok...
            username = "demo";
        }
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);
            widget = WidgetUtils.getOne(conn, pageItem, value, formId, encounterId, "Chart");
            result = encounterId + "." + fieldId + "=" + widget;
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

    public String dispatchChartLabs(String value, Long pageItemId, Long fieldId, Long formId, Long encounterId, Long displayField) {
        String widget = null;
        String result = null;
        PageItem pageItem = (PageItem) DynaSiteObjects.getPageItems().get(pageItemId);
        WebContext exec = WebContextFactory.get();
        String username = null;
        try {
            username = exec.getHttpServletRequest().getUserPrincipal().getName();
        } catch (NullPointerException e) {
            // unit testing - it's ok...
            username = "demo";
        }
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);
            widget = WidgetUtils.getOne(conn, pageItem, value, formId, encounterId, "Chart");
            result = encounterId + "." + displayField + "=" + widget;
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

    public String dispatchGroups(String username) {
        String widget = null;
        String result = null;
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(null);
            try {
                widget = WidgetUtils.getGroups(conn, username);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (ObjectNotFoundException e) {
                e.printStackTrace();
            }
            result = username + "=" + widget;
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

    public List dispatchFlows(String username) {
        List flows = null;
        String result = null;
        Connection conn = null;
        try {
            conn = DatabaseUtils.getAdminConnection();
            try {
                flows = FlowDAO.getAll(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flows;
    }

    public List dispatchFormTypes(String username) {
        List flows = null;
        String result = null;
        Connection conn = null;
        try {
            conn = DatabaseUtils.getAdminConnection();
            try {
                flows = FormTypeDAO.getAll(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flows;
    }

    /**
     * Request widget for encounter record metadata such as site id
     * todo: remove hard-coding of SiteId by adding another param for widget type.
     * @param value
     * @param item
     * @param encounterId
     * @return
     */
    public String dispatchMetaData(String value, String item, Long encounterId) {
        String result = null;
        WebContext exec = WebContextFactory.get();
        String username = null;
        try {
            username = exec.getHttpServletRequest().getUserPrincipal().getName();
        } catch (NullPointerException e) {
            // unit testing - it's ok...
            username = "demo";
        }
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);
            String widget = null;

            if (value.equals("")) {
                value = " ";
            }
            PageItem pageItem = new PageItem();
            pageItem.setId(Long.valueOf("0"));
            pageItem.setInputType(item);
            Long formId = null;
            try {
				EncounterData encounter = (EncounterData) EncountersDAO.getOneById(conn, encounterId);
				formId = encounter.getFormId();
			} catch (ObjectNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            widget = WidgetUtils.getOne(conn, pageItem, value, formId, encounterId, "Metadata");
            result = item + "=" + widget;
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
}
