/**
 *
 */
package org.rti.zcore.utils.struts.i18n;

import org.apache.struts.util.MessageResources;
import org.apache.struts.util.PropertyMessageResourcesFactory;

/**
 * @author ckelley
 * @date   Sep 2, 2009
 */
public class ZcoreMessageResourcesFactory extends PropertyMessageResourcesFactory {

	  /**
     *
     */
    public ZcoreMessageResourcesFactory() {
        super();
    }

    /* (non-Javadoc)
     * @see org.apache.struts.util.MessageResourcesFactory#createResources(java.lang.String)
     */
    public MessageResources createResources(String config) {
        return new ZcorePropertyMessageResources(this, config, returnNull);
    }


}
