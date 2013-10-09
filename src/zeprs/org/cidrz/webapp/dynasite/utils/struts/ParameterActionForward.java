package org.cidrz.webapp.dynasite.utils.struts;

import org.apache.struts.action.ActionForward;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Add parameter to a forward.
 * I don't think this would work on forwards that didn't have redirect set to true
 * Usage: ParameterActionForward forward = new ParameterActionForward(mapping.findForward("problem"));
 * forward.addParameter("patientId",patientId);
 */
public class ParameterActionForward extends ActionForward {
    private Map requestParams = new HashMap();

    public ParameterActionForward(ActionForward forward) {
        super(forward.getName(), forward.getPath(), forward.getRedirect(), forward.getContextRelative());
    }

    public void addParameter(String paramName, Object paramValue) {
        if (paramName != null && paramValue != null) {
            requestParams.put(paramName, paramValue.toString());
        }
    }

    public void addParameters(Map params) {
        requestParams.putAll(params);
    }

    public String getPath() {
        StringBuffer path = new StringBuffer(super.getPath());
        Iterator iter = requestParams.entrySet().iterator();
        if (iter.hasNext()) {
            //add first parameter, if avaliable
            Map.Entry entry = (Map.Entry) iter.next();
            path.append("?" + entry.getKey() + "=" + entry.getValue());
            //add other parameters
            while (iter.hasNext()) {
                entry = (Map.Entry) iter.next();
                path.append("&" + entry.getKey() + "=" + entry.getValue());
            }
        }
        return path.toString();
    }

    public void freeze() {
        //sourceForward.freeze();
    }

    public void replaceInPath(String replaceThis, String withThis) {
        String path = this.getPath();
        path.replaceAll(replaceThis, withThis);
        this.setPath(path);
    }

}
