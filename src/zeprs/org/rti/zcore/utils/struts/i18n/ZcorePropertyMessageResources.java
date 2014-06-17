package org.rti.zcore.utils.struts.i18n;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import org.apache.struts.util.MessageResourcesFactory;
import org.apache.struts.util.PropertyMessageResources;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;

public class ZcorePropertyMessageResources extends PropertyMessageResources {


	   // ----------------------------------------------------------- Constructors


    /**
     * Construct a new PropertyMessageResources according to the
     * specified parameters.
     *
     * @param factory The MessageResourcesFactory that created us
     * @param config The configuration parameter for this MessageResources
     */
    public ZcorePropertyMessageResources(MessageResourcesFactory factory,
                                    String config) {

        super(factory, config);
        log.trace("Initializing, config='" + config + "'");

    }


    /**
     * Construct a new PropertyMessageResources according to the
     * specified parameters.
     *
     * @param factory The MessageResourcesFactory that created us
     * @param config The configuration parameter for this MessageResources
     * @param returnNull The returnNull property we should initialize with
     */
    public ZcorePropertyMessageResources(MessageResourcesFactory factory,
                                    String config, boolean returnNull) {

        super(factory, config, returnNull);
        log.trace("Initializing, config='" + config +
                 "', returnNull=" + returnNull);

    }

	/**
	 * @return the messages
	 */
	public HashMap getMessages() {
		return messages;
	}

	/**
	 * @param messages the messages to set
	 */
	public void setMessages(HashMap messages) {
		this.messages = messages;
	}


	/**
	 * @return the locales
	 */
	public HashMap getLocales() {
		return locales;
	}


	/**
	 * @param locales the locales to set
	 */
	public void setLocales(HashMap locales) {
		this.locales = locales;
	}


	public synchronized void loadLocale(String localeKey) {
		//super.loadLocale(localeKey);
		if (log.isTraceEnabled()) {
            log.trace("loadLocale(" + localeKey + ")");
        }

        // Have we already attempted to load messages for this locale?
        if (locales.get(localeKey) != null) {
            return;
        }

        locales.put(localeKey, localeKey);

        // Set up to load the property resource for this locale key, if we can
        String name = config.replace('.', '/');
        if (localeKey.length() > 0) {
            name += "_" + localeKey;
        }

        name += ".properties";
        InputStream is = null;
        Properties props = new Properties();

        // Load the specified property resource
        if (log.isTraceEnabled()) {
            log.trace("  Loading resource '" + name + "'");
        }

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            classLoader = this.getClass().getClassLoader();
        }

        is = classLoader.getResourceAsStream(name);
        if (is != null) {
            try {
                props.load(is);

            } catch (IOException e) {
                log.error("loadLocale()", e);
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error("loadLocale()", e);
                }
            }
        }

        if (log.isTraceEnabled()) {
            log.trace("  Loading resource completed");
        }

        // Copy the corresponding values into our cache
        if (props.size() < 1) {
            return;
        }

        synchronized (messages) {
            Iterator names = props.keySet().iterator();
            while (names.hasNext()) {
                String key = (String) names.next();
                if (log.isTraceEnabled()) {
                    log.trace("  Saving message key '" + messageKey(localeKey, key));
                }
                messages.put(messageKey(localeKey, key), props.getProperty(key));
            }
            HashMap localeMessageMap = DynaSiteObjects.getLocaleMessageMap();
            if (localeMessageMap == null) {
            	localeMessageMap = new HashMap<String,String>();
            }
            DynaSiteObjects.getLocaleMessageMap().putAll(messages);
        }
	}


}
