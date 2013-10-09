package org.zeprs.unittest.persistence;

import org.cidrz.webapp.dynasite.dao.FormDisplayDAO;
import org.cidrz.webapp.dynasite.valueobject.FieldEnumeration;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.valueobject.FormField;
import org.cidrz.webapp.dynasite.valueobject.PageItem;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.valueobject.RuleDefinition;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.logic.PersistenceManagerFactory;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.zeprs.unittest.ZeprsTest;

import javax.servlet.ServletException;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.*;

/**
 * Tests the Form object graph to ensure proper mapping.
 * This test currently relies on a known DB state.
 * TODO: remove this dependency
 */
public class FormGraphTest extends ZeprsTest {
    /**
     * tests the form object graph, as used when getting all forms for the
     * purpose of initializing the application (DynasiteConfigServlet)
     */

    public void testFormPopulation() {
        Connection conn = DatabaseUtils.getAdminConnection();
        List forms = new ArrayList();
        List formIds = null;
        try {
            formIds = FormDisplayDAO.getAllFormIds(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            fail(e.getMessage());
        } catch (ServletException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull("form id list was null", formIds);

        for (Iterator iterator = formIds.iterator(); iterator.hasNext();) {
            Form form = (Form) iterator.next();
            Form wholeForm = null;
            try {
                wholeForm = FormDisplayDAO.getFormGraph(conn, form.getId());
            } catch (SQLException e) {
                e.printStackTrace();
                fail(e.getMessage());
            } catch (ServletException e) {
                e.printStackTrace();
                fail(e.getMessage());
            } catch (ObjectNotFoundException e) {
                e.printStackTrace();
                fail(e.getMessage());
            }
            forms.add(wholeForm);
        }
        assertNotNull("form list was null", forms);
    }

    public void testGraph() {
        Connection conn = DatabaseUtils.getAdminConnection();
        List forms = new ArrayList();
        List formIds = null;
        try {
            formIds = FormDisplayDAO.getAllFormIds(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            fail(e.getMessage());
        } catch (ServletException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull("form id list was null", formIds);

        for (Iterator iterator = formIds.iterator(); iterator.hasNext();) {
            Form form = (Form) iterator.next();
            Form wholeForm = null;
            try {
                wholeForm = (Form) FormDisplayDAO.getFormGraph(conn, form.getId());
            } catch (SQLException e) {
                e.printStackTrace();
                fail(e.getMessage());
            } catch (ServletException e) {
                e.printStackTrace();
                fail(e.getMessage());
            } catch (ObjectNotFoundException e) {
                e.printStackTrace();
                fail(e.getMessage());
            }
            forms.add(wholeForm);
        }
        assertNotNull("form list was null", forms);

        Form form;
        for (int i = 0; i < forms.size(); i++) {
            form = (Form) forms.get(i);
            Iterator pageItems = form.getPageItems().iterator();
            assertNotNull("pageItems list is null", pageItems);
            PageItem pageItem;
            while (pageItems.hasNext()) {
                pageItem = (PageItem) pageItems.next();
                validateField(pageItem.getForm_field());
            }
            List rules = form.getRuleDefinitions();
            validateRuleDefs(rules);
        }

    }

    private void validateField(FormField field) {
        assertNotNull("field is null", field);
        // assertNotNull("displayHint is null", field.);
        List rules = field.getRuleDefinitions();
        validateRuleDefs(rules);
        Set enumerations = field.getEnumerations();
        assertNotNull("FieldEnumerations collection is null", enumerations);
        validateEnumerations(enumerations, field.getId());
    }

    /**
     * @param enumerations
     * @param fieldId
     */
    private void validateEnumerations(Set enumerations, Long fieldId) {
        FieldEnumeration fenum;
        Iterator iterator = enumerations.iterator();
        while (iterator.hasNext()) {
            fenum = (FieldEnumeration) iterator.next();
            // assertEquals("enumeration form_id is not equal", fieldId, fenum.getField().getId());
            assertEquals("enumeration fieldId is not equal", fieldId, fenum.getFieldId());
        }
    }

    private void validateRuleDefs(List ruleDefs) {
        assertNotNull("rule definitions collection is null", ruleDefs);
        RuleDefinition rule;
        for (int i = 0; i < ruleDefs.size(); i++) {
            rule = (RuleDefinition) ruleDefs.get(i);
            assertNotNull("rule class is null", rule.getRuleClass());
            assertNotNull("outcome class is null", rule.getOutcomeClass());
            Map params = rule.getRuleParams();
            assertNotNull("rule params collection is null", params);
            assertFalse("rule params collection is empty", params.isEmpty());
            Map outcomeParams = rule.getOutcomeParams();
            assertNotNull("rule params collection is null", outcomeParams);
            assertFalse("rule params collection is empty", outcomeParams.isEmpty());
        }
    }

    public void testGraphCompare() {
        Connection conn = DatabaseUtils.getAdminConnection();
        List forms = new ArrayList();
        List forms2 = new ArrayList();
        List formIds = null;
        try {
            formIds = FormDisplayDAO.getAllFormIds(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            fail(e.getMessage());
        } catch (ServletException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull("form id list was null", formIds);

        for (Iterator iterator = formIds.iterator(); iterator.hasNext();) {
            Form form = (Form) iterator.next();
            Form wholeForm = null;
            Form wholeForm2 = null;
            try {
                wholeForm = FormDisplayDAO.getFormGraph(conn, form.getId());
                wholeForm2 = (Form) PersistenceManagerFactory.getInstance(Form.class).getOneForm(form.getId());
            } catch (SQLException e) {
                e.printStackTrace();
                fail(e.getMessage());
            } catch (ServletException e) {
                e.printStackTrace();
                fail(e.getMessage());
            } catch (ObjectNotFoundException e) {
                e.printStackTrace();
            } catch (PersistenceException e) {
                e.printStackTrace();
            }
            forms.add(wholeForm);
            forms2.add(wholeForm2);
        }
        assertNotNull("form list was null", forms);
        assertNotNull("form list was null", forms2);

        Form form;
        Form form2;
        for (int i = 0; i < forms.size(); i++) {
            form = (Form) forms.get(i);
            form2 = (Form) forms2.get(i);
            Iterator pageItems = form.getPageItems().iterator();
            Iterator pageItems2 = form2.getPageItems().iterator();
            assertNotNull("pageItems list is null", pageItems);
            assertNotNull("pageItems2 list is null", pageItems2);
            PageItem pageItem;
            PageItem pageItem2;
            while (pageItems2.hasNext()) {
                pageItem = (PageItem) pageItems.next();
                pageItem2 = (PageItem) pageItems2.next();
                compareFields(pageItem, pageItem2);
            }
            /*List rules = form.getRuleDefinitions();
            validateRuleDefs(rules);*/
        }

    }

    private void compareFields(PageItem pageItem, PageItem pageItem2) {
        FormField field = pageItem.getForm_field();
        FormField field2 = pageItem2.getForm_field();
        assertNotNull("field is null", field);
        assertNotNull("field is null", field2);
        assertEquals("PageItem id's are not equal. FormId: " + pageItem.getFormId() + " do1: " + pageItem.getDisplayOrder() + " do2: " + pageItem2.getDisplayOrder(), pageItem.getId(), pageItem2.getId());
        assertEquals("Field id's are not equal", field.getId(), field2.getId());
        // assertNotNull("displayHint is null", field.);
        /*List rules = field.getRuleDefinitions();
        validateRuleDefs(rules);*/
        Set enumerations = field.getEnumerations();
        Set enumerations2 = field2.getEnumerations();
        assertNotNull("FieldEnumerations collection is null", enumerations);
        assertNotNull("FieldEnumerations collection is null", enumerations2);
        if ((enumerations.size() != 0) & (enumerations2.size() != 0)) {
            assertEquals("FieldEnumerations are not equal", enumerations.size(), enumerations2.size());
        }

        // validateEnumerations(enumerations, field.getId());
    }

    /**
     * Don't use this - it will muddy up the forms....
     * @deprecated
     */
    /*public void testCreateForm() {
        Form form = new Form();
        form.setName(this.getClass().getName() + System.currentTimeMillis());
        form.setLabel(form.getName());
        form.setRequirePatient(false);
        form.setRequireReauth(false);
        form.setEnabled(true);
        Site site = new Site();
        site.setId(new Long("1"));
        try {
            form = (Form) PersistenceManagerFactory.getInstance(Form.class).save(form, getPrincipal(), site);
        } catch (PersistenceException e) {
            fail(e.getMessage());
        }
    }*/
}
