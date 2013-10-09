/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.struts.action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InactivateOutcomeAction extends BaseAction {
    protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        /*try {
            Long outcomeId = new Long(request.getParameter("id"));
            Patient p = SessionUtil.getInstance(request.getSession()).getPatient();
            Outcome o;
            Set outcomes = p.getOutcomes();
            HttpSession session = request.getSession();
            Site site = SessionUtil.getInstance(session).getClientSettings().getSite();
            for (Iterator iterator = outcomes.iterator(); iterator.hasNext();) {
                o = (Outcome) iterator.next();
                if (o.getId().equals(outcomeId)) {
                    o.setActive(false);
                    PersistenceManagerFactory.getInstance(InactivateOutcomeAction.class).save(o, request.getUserPrincipal(), site);
                    break;
                }
            }
        } catch (Exception e) {
            //todo: log?
        }*/
        request.setAttribute("exception", "This has not be implemented");
        return mapping.findForward("error");
    }
}
