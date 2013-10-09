package org.cidrz.webapp.dynasite.utils.struts;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.GenericTypeValidator;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.validator.Resources;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jul 5, 2005
 *         Time: 2:59:00 PM
 */
public class StrutsUtils {
    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(StrutsUtils.class);

    /**
     * Convenience method for removing the obsolete form bean.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param request The HTTP request we are processing
     */
    public static void removeFormBean(ActionMapping mapping, HttpServletRequest request) {
        // Remove the obsolete form bean
        if (mapping.getAttribute() != null) {
            if ("request".equals(mapping.getScope())) {
                request.removeAttribute(mapping.getAttribute());
            } else {
                HttpSession session = request.getSession();
                session.removeAttribute(mapping.getAttribute());
            }
        }
    }

    /**
     * Validates value submitted from our time widget.
     * Kudos: http://sourceforge.net/mailarchive/forum.php?thread_id=7588614&forum_id=29080
     * @param bean
     * @param va
     * @param field
     * @param errors
     * @param request
     * @return false if it fails validation.
     */
    public static boolean validateTime(Object bean, ValidatorAction va, Field field, ActionMessages errors, Validator validator, HttpServletRequest request) {
        boolean bValid = true;
        String value;
        if (isString(bean)) {
            value = (String) bean;
        } else {
            value = ValidatorUtils.getValueAsString(bean, field.getProperty());
        }
        if (GenericValidator.isBlankOrNull(value)) {
            return Boolean.TRUE;
        }
        final String timePattern = "HH:mm:ss";

        try {
            final java.text.DateFormat timeFormatter = new java.text.SimpleDateFormat(timePattern);
            timeFormatter.parse(value);

        }
        catch (Exception exception) {
        	if (field != null) {
        		// errors.add(field.getKey(), getActionMessage(request, va, field));
        		errors.add(field.getKey(), Resources.getActionMessage(validator, request, va, field));
        		//errors.add(field.getKey(), "{0} is not a time value.");
        	} else {
        		if (va != null) {
        			log.debug("Null field for " + va.getName());
        		} else {
        			log.debug("Null field when trying to validate time.");
        		}
        	}
            bValid = false;
        }
        return bValid;
    }

    /**
     * Return <code>true</code> if the specified object is a String or a <code>null</code>
     * value.
     *
     * @param o Object to be tested
     * @return The string value
     */
    protected static boolean isString(Object o) {
        return (o == null) ? true : String.class.isInstance(o);
    }

    public static ActionMessage getActionMessage(
            HttpServletRequest request,
            ValidatorAction va,
            Field field) {

    	MessageResources resources = Resources.getMessageResources(request);

            String args[] =
            	Resources.getArgs(
                    va.getName(),
                    resources,
                    RequestUtils.getUserLocale(request, null),
                    field);

            String msg =
                field.getMsg(va.getName()) != null
                    ? field.getMsg(va.getName())
                    : va.getMsg();


            return new ActionMessage(msg, args);
        }

    public static Object validateInteger(Object bean,
    		ValidatorAction va, Field field,
    		ActionMessages errors,
    		Validator validator,
    		HttpServletRequest request) {
    	Object result = null;
    	String value = null;
    	if (isString(bean)) {
    		value = (String) bean;
    	} else {
    		value = ValidatorUtils.getValueAsString(bean, field.getProperty());
    	}

    	if (GenericValidator.isBlankOrNull(value)) {
    		return Boolean.TRUE;
    	}

    	result = GenericTypeValidator.formatInt(value);

    	if (result == null) {
    		errors.add(field.getKey(), Resources.getActionMessage(validator, request, va, field));
    	}

    	return result == null ? Boolean.FALSE : result;
    }

}
