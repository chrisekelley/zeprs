package org.rti.zcore.utils.struts.i18n;

import org.apache.struts.util.MessageResources;
import org.apache.struts.util.PropertyMessageResourcesFactory;

public class ReloadablePropertyMessageResourcesFactory extends PropertyMessageResourcesFactory {
	private static final long serialVersionUID = -5026832102361479917L;

	/**
	 * Konstruktor.
	 */
	public ReloadablePropertyMessageResourcesFactory() {
		super();
		setFactoryClass(this.getClass().getCanonicalName());
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.apache.struts.util.PropertyMessageResourcesFactory#createResources(java.lang.String)
	 */
	@Override
	public MessageResources createResources(final String config) {
		return new ReloadablePropertyMessageResources(this, config, this.getReturnNull());
	}

}
