/**
 * 
 */
package org.cidrz.webapp.dynasite.struts.action.admin;


import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;
import org.cidrz.webapp.dynasite.dao.FormDisplayDAO;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.DateUtils;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.valueobject.Identifiable;
import org.cidrz.webapp.dynasite.valueobject.PageItem;
import org.cidrz.webapp.dynasite.valueobject.SessionPatient;

/**
 * @author chrisk
 *
 */
public class PreviewFormAction extends BaseAction {
	
	/**
     * Commons Logging instance.
     */
    private Log log = LogFactory.getFactory().getInstance(this.getClass().getName());

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //Class clazz = getMappedClass(mapping);
        //DynaActionForm dynaForm = (DynaActionForm) form;
     // Cast form to DynaBean
        DynaBean dynaForm = (DynaBean)form;
        Identifiable subject = null;
        Long id = null;
        Long formId = null;
        //formId = (Long) request.getAttribute("id");

       /* try {
            id = (Long) dynaForm.get("id");
        } catch (IllegalArgumentException e) {*/
           // id = Long.valueOf(request.getParameter("id"));
          //  id = (Long) request.getAttribute("id");
            id = (Long) dynaForm.get("id");
    //    }
        
        	//Long formId = (Long) dynaForm.get("id");
        	//dynaForm.set("id", id);
        	
            List drugs = DynaSiteObjects.getDrugs();
            request.setAttribute("drugs", drugs);
            List sites = DynaSiteObjects.getClinics();
            request.setAttribute("sites", sites);
            Connection conn = DatabaseUtils.getAdminConnection();
            subject = FormDisplayDAO.getFormDisplayGraph(conn, id);
            conn.close();
            Form encounterForm = (Form) subject;
            Set pageItems = encounterForm.getPageItems();
            for (Iterator iterator = pageItems.iterator(); iterator.hasNext();) {
				PageItem pageItem = (PageItem) iterator.next();
				String name = "field" + pageItem.getForm_field().getId();
				dynaForm.set(name, "");	
			}                        
            request.setAttribute("encounterForm", subject);
            request.setAttribute("preview", "1");
            List yearList = DateUtils.getYearList();
            // yearlist is used to render the year tag - with dates in reverse order.
            request.setAttribute("yearList", yearList);
            String formName = "form" + id.toString();
        	String formType = "org.apache.commons.beanutils.LazyDynaBean";
            //FormBeanConfig formConfig = mapping.getModuleConfig().findFormBeanConfig(formName);           
            //if (formConfig == null) {
            	DynaValidatorForm  instaForm = null;
            	DynaBean instaForm2 = (DynaBean)form;
            	instaForm2.set("name", formName);
            	for (Iterator iterator = pageItems.iterator(); iterator.hasNext();) {
    				PageItem pageItem = (PageItem) iterator.next();
    				String name = "field" + pageItem.getForm_field().getId();
    				instaForm2.set(name, "");	
    			}  
            	request.setAttribute(formName, instaForm2);
        		/*FormBeanConfig config = null;
        		FormPropertyConfig prop = null;

        		config = new FormBeanConfig();
        		config.setName(formName);
        		config.setType(formType);
        		config.setDynamic(true);
        		//pageItems = encounterForm.getPageItems();
                for (Iterator iterator = pageItems.iterator(); iterator.hasNext();) {
                	prop = new FormPropertyConfig();
    				PageItem pageItem = (PageItem) iterator.next();
    				String name = "field" + pageItem.getForm_field().getId();
    				prop.setName(name);	
    				prop.setType("java.lang.String");
    				prop.setInitial("");
    				prop.freeze();
    				config.addFormPropertyConfig(prop);
    			}*/

        		

        		//config.freeze();
        		
        		/*try {
        			//instaForm = (DynaValidatorForm) DynaActionFormClass.createDynaActionFormClass(config).newInstance();
        			request.setAttribute(formName, instaForm2);
        		} catch (Throwable t) {
        			log.error("Exception creating blank form: " + t.toString(), t);
        		}*/

           /* } else {
            	request.setAttribute(formName, formConfig);
            }*/
            
            request.setAttribute("instaBean", "1");
            SessionPatient sessionPatient = new SessionPatient();
            sessionPatient.setId(new Long(1));
            SessionUtil sessionUtil = SessionUtil.getInstance(request.getSession());
            sessionUtil.setSessionPatient(sessionPatient);
            return mapping.findForward("success");
        
    }
	
	protected Class getMappedClass(ActionMapping mapping) throws Exception {

        String classname = mapping.getParameter();
        try {
            return Class.forName(classname);
        } catch (ClassNotFoundException e) {
            log.error("bad class for this mapping. Mapping path=" + mapping.getPath() + ", Class=" + classname);
            throw new Exception("bad mapping, no class--see log");
        }
    }
}
