/**
 *
 */
package org.rti.zcore.dynasite.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.valueobject.FormField;
import org.cidrz.webapp.dynasite.valueobject.PageItem;
import org.cidrz.webapp.dynasite.utils.StringManipulation;
import org.cidrz.webapp.dynasite.utils.sort.DisplayOrderComparator;

/**
 * @author chrisk
 *
 */
public class FormUtils {

	 /**
     * Commons Logging instance.
     */

    private static Log log = LogFactory.getFactory().getInstance(FormUtils.class);

	/**
	 * Creates pageItems for display in a bridge table form.
	 * Be careful - if the form is a bridge table *and* encounterForm.getRecordsPerEncounter() == 0, then all pageItems will be lost.
	 * @param encounterForm
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static void createBridgeTablePageItems(Form encounterForm)
			throws IllegalAccessException, InstantiationException,
			InvocationTargetException, NoSuchMethodException {
		int i = 0;
		int countPageItems = encounterForm.getPageItems().size();
		Set pageItems = encounterForm.getPageItems();
		Set newPageItems = new TreeSet(new DisplayOrderComparator());
		int recordsPerEncounter = encounterForm.getRecordsPerEncounter();
		for (int j = 1; j <= recordsPerEncounter; j++) {
			//log.debug("j: " + j);
			for (Iterator iterator = pageItems.iterator(); iterator.hasNext();) {
				PageItem pageItem = (PageItem) iterator.next();
				PageItem newPageItem = (PageItem) BeanUtils.cloneBean(pageItem);
				newPageItem.setDisplayOrder(countPageItems);
				newPageItem.setCurrentFieldNameIdentifier("PBF" + j + "_");
				countPageItems++;
				newPageItems.add(newPageItem);
			}
		}
		pageItems.clear();
		pageItems.addAll(newPageItems);
		encounterForm.setResizedForPatientBridge(true);
	}

	/**
	 * Assigns a form field identifier.
	 * @param formField
	 * @return
	 */
	public static String assignIdentifier(FormField formField) {
		String identifier;
		try {
			identifier = StringManipulation.firstCharToLowerCase(formField.getStarSchemaName());
		} catch (StringIndexOutOfBoundsException e) {
			identifier = "field" + String.valueOf(formField.getId());
		} catch (Exception e) {
			identifier = "field" + String.valueOf(formField.getId());
		}
		return identifier;
	}



}
