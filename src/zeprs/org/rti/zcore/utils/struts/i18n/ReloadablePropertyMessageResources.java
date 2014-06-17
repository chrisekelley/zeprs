package org.rti.zcore.utils.struts.i18n;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.PropertyMessageResources;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;

/**
 * kudos:http://www.coderanch.com/t/420301/Struts/message-resources-reload
 * ckelley: Added code to check DynasiteObjects instead of hitting the filesystem every 5 secs.
 * Automatisch neu ladbare MessageResources.
 *
 * @author boeckmic - Michael Bockling
 * @since Mar 26, 2008
 */
public class ReloadablePropertyMessageResources extends PropertyMessageResources {

	private static final long serialVersionUID = 4344652868862075298L;

	//private final Logger logger = Logger.getLogger(ReloadablePropertyMessageResources.class);

	/**
	 * Commons Logging instance.
	 */

	private static Log log = LogFactory.getFactory().getInstance(ReloadablePropertyMessageResources.class);

	/** The last time the file was checked for changes. */
	protected long lastChecked;

	/** The minimum delay in milliseconds between checks. */
	protected long refreshDelay = DEFAULT_REFRESH_DELAY;

	/** Constant for the default refresh delay. */
	private static final int DEFAULT_REFRESH_DELAY = 2000;

	private final ConcurrentHashMap<String, File> fileMap = new ConcurrentHashMap<String, File>();

	private final ConcurrentHashMap<File, Long> timestampMap = new ConcurrentHashMap<File, Long>();

	private boolean loadPropertiesFromFilesystem;

	/**
	 * Konstruktor aus Superklasse.
	 *
	 * @param factory Factory
	 * @param config Name der Konfigurationsdatei
	 */
	public ReloadablePropertyMessageResources(final ReloadablePropertyMessageResourcesFactory factory, final String config) {
		super(factory, config);
	}

	/**
	 * Konstruktor aus Superklasse.
	 *
	 * @param factory Factory
	 * @param config Name der Konfigurationsdatei
	 * @param returnNull Fehler werfen bei nicht vorhandenem Key?
	 */
	public ReloadablePropertyMessageResources(final ReloadablePropertyMessageResourcesFactory factory, final String config, final boolean returnNull) {
		super(factory, config, returnNull);
	}

	/**
	 * Loads the most recent version of a translation, if possible.
	 *
	 * {@inheritDoc}
	 *
	 * @see org.apache.struts.util.PropertyMessageResources#getMessage(java.util.Locale,
	 *      java.lang.String)
	 */
	@Override
	public String getMessage(final Locale locale, final String key) {
		// reload(locale);
		String message = super.getMessage(locale, key);
		// log.debug("Getting message for Locale: " + locale + " and key: " + key + " ; Message: " + message);
		String dynaMessage = DynaSiteObjects.getLocaleMessageMap().get(key);
		if ((message != null) && (dynaMessage != null) && (!message.equals(dynaMessage)))  {
			log.debug("Message outdated; using message from DynaSiteObjects.getLocaleMessageMap(); key: " + key + " dynaMessage: " + dynaMessage);
			message = dynaMessage;
		}
		if (message == null || message.equals("???" + messageKey(locale, key) + "???")) {
			if (dynaMessage == null) {
				message = "???" + messageKey(locale, key) + "???";
			} else {
				log.debug("Null message; Getting message from DynaSiteObjects.getLocaleMessageMap(); key: " + key + " dynaMessage: " + dynaMessage);
				message = dynaMessage;
			}
		}
		return message;
	}

	/**
	 * Pr?ft ob ein Reload der Properties notwendig ist.
	 *
	 * {@inheritDoc}
	 *
	 * @see org.apache.struts.util.MessageResources#getMessage(java.util.Locale, java.lang.String,
	 *      java.lang.Object[])
	 */
	@Override
	public String getMessage(final Locale locale, final String key, final Object[] args) {
		// reload(locale);
		String message = super.getMessage(locale, key, args);
		// log.debug("Getting message for Locale: " + locale + " and key: " + key + " ; Message: " + message);
		String dynaMessage = DynaSiteObjects.getLocaleMessageMap().get(key);
		if ((message != null) && (dynaMessage != null) && (!message.equals(dynaMessage)))  {
			log.debug("Message outdated; using message from DynaSiteObjects.getLocaleMessageMap(); key: " + key + " dynaMessage: " + dynaMessage);
			message = dynaMessage;
		}
		if (message == null || message.equals("???" + messageKey(locale, key) + "???")) {
			if (dynaMessage == null) {
				message = "???" + messageKey(locale, key) + "???";
			} else {
				log.debug("Null message; Getting message from DynaSiteObjects.getLocaleMessageMap(); key: " + key + " dynaMessage: " + dynaMessage);
				message = dynaMessage;
			}
		}
		return message;
	}

	/**
	 * The resetting of the maps leads to reloading the properties.
	 *
	 * @param locale Locale
	 */
	public synchronized void reload(final Locale locale) {
		if (reloadingRequired(locale)) {
			//logger.info("Reloading resources " + getFilename(locale));
			locales.clear();
			messages.clear();
			formats.clear();

			// reload manually to escape the caching by the classloader

			//manualReload(locale);

		}
	}

	/**
	 * Manually clears the locales, messages, and formats.
	 *
	 * @param locale Locale
	 */
	public synchronized void forceReload(final Locale locale) {
			//logger.info("Reloading resources " + getFilename(locale));
			locales.clear();
			messages.clear();
			formats.clear();
	}

	/**
	 * Does not work - the Classloader in PropertyMessageResources.loadLocale overrides this population of messages.
	 * @param locale
	 */
	private void manualReload(final Locale locale) {
		Properties props = new Properties();
		String pathName = null;
		String deployPathname = null;
		Boolean dev = DynaSiteObjects.getDev();

		if (dev == true) {
			pathName = Constants.LOCAL_CLASSES_PATH;
			deployPathname = Constants.DEPLOY_CLASSES_PATH;
		} else {
			pathName = Constants.DEPLOY_CLASSES_PATH;
		}

		String name = config.replace('.', '/');
		/*String key = name.replace("resources/forms/", "");
		if (name.equals("resources/ApplicationResources") || name.equals("resources/TemplateResources")) {
			key = name.replace("resources/", "");
		}*/
		String localeKey = null;
		if (locale != null) {
			localeKey = locale.toString();
			int underscore = localeKey.lastIndexOf("_");
			if (underscore > 0) {
				localeKey = localeKey.substring(0, underscore);
			}
		}

		if (localeKey.length() > 0) {
			name = name + "_" + localeKey + ".properties";
		}

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(pathName + name);
			if (fis != null) {
		        try {
		        	props.load(fis);

		        } catch (IOException e) {
		            log.error("loadLocale()", e);
		        }
		    }
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
		    try {
		        fis.close();
		    } catch (IOException e) {
		        log.error("loadLocale()", e);
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
		}
	}

	/**
	 * Pr?ft ob es notwendig ist die Properties neu zu laden. Pr?ft nur nach Ablauf des
	 * {@link #refreshDelay}, um eine ?berm??ige Beanspruchung des Dateisystems zu vermeiden.
	 *
	 * @param locale Locale
	 * @return <code>true</code> falls neu geladen werden muss
	 */
	public boolean reloadingRequired(final Locale locale) {
		boolean reloading = false;

		long now = System.currentTimeMillis();
		if (now > lastChecked + refreshDelay) {
			lastChecked = now;
			/*if (hasChanged(locale)) {
				reloading = true;
			}*/

			String name = config.replace('.', '/');
			String key = name.replace("resources/forms/", "");
			if (name.equals("resources/ApplicationResources") || name.equals("resources/TemplateResources")) {
				key = name.replace("resources/", "");
			}
			String localeKey = null;
			String reloadKey = key;
			if (locale != null) {
				localeKey = locale.toString();
				int underscore = localeKey.lastIndexOf("_");
				if (underscore > 0) {
					localeKey = localeKey.substring(0, underscore);
				}
				if (localeKey.length() > 0) {
					reloadKey = key + "_" + localeKey + ".properties";
				}
			} else {
				// log.debug("locale is null");
			}

			if (name.equals("resources/ApplicationResources") || name.equals("resources/TemplateResources")) {
				String simpleKey = key +  ".properties";
				if (locale != null) {
					//log.debug("For locale: " + locale.toString() + "; Checking if simpleKey in PropertyFileReloadMap: " + simpleKey);
				} else {
					//log.debug("Checking if simpleKey in PropertyFileReloadMap: " + simpleKey);
				}
				if (DynaSiteObjects.getPropertyFileReloadMap().get(simpleKey) != null) {
					boolean dynaSiteSaysToReload = DynaSiteObjects.getPropertyFileReloadMap().get(simpleKey);
					if (dynaSiteSaysToReload == true) {
						reloading = true;
						// log.debug("Reloading: " + reloading);
						DynaSiteObjects.getPropertyFileReloadMap().remove(simpleKey);
					}
				}
			}

			//log.debug("Checking if reloadKey in PropertyFileReloadMap: " + reloadKey);
			if (DynaSiteObjects.getPropertyFileReloadMap().get(reloadKey) != null) {
				boolean dynaSiteSaysToReload = DynaSiteObjects.getPropertyFileReloadMap().get(reloadKey);
				if (dynaSiteSaysToReload == true) {
					reloading = true;
					log.debug("Reloading: " + reloading);
					DynaSiteObjects.getPropertyFileReloadMap().remove(reloadKey);
				}
			}
		}

		return reloading;
	}

	/**
	 * Pr?ft ob sich die Konfiguration seit dem letzten Aufruf ge?ndert hat.
	 *
	 * @param locale Locale
	 * @return <code>true</code> falls sich die Datei ge?ndert hat
	 */
	protected boolean hasChanged(final Locale locale) {
		File file = getFile(locale);

		if (file == null || !file.exists()) {
			return false;
		}

		Long timestamp = timestampMap.get(file);
		if (timestamp == null) {
			timestampMap.putIfAbsent(file, Long.valueOf(file.lastModified()));
			return false;
		}

		if (file.lastModified() > timestamp.longValue()) {
			timestampMap.put(file, Long.valueOf(file.lastModified()));
			return true;
		}

		return false;
	}

	/**
	 * Gibt die Datei zur Locale zur?ck.
	 *
	 * @param locale Locale
	 * @return (gecachte) File
	 */
	private File getFile(final Locale locale) {

		String filename = getFilename(locale);

		File f = fileMap.get(filename);
		if (f == null) {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			if (classLoader == null) {
				classLoader = getClass().getClassLoader();
			}

			URL fileUrl = classLoader.getResource(filename);

			// no bundle for this locale found, so try default bundle
			if (fileUrl == null) {
				fileUrl = classLoader.getResource(getFilename(null));
			}

			try {
				f = new File(fileUrl.toURI());
			} catch (URISyntaxException e) {
				throw new IllegalArgumentException("The properties file "
						+ fileUrl.toExternalForm() + " could not be loaded.", e);
			}
			fileMap.putIfAbsent(filename, f);
		}

		return f;
	}

	/**
	 * Berechnet den Dateinamen f?r die Locale.
	 *
	 * @param locale Locale
	 * @return Dateinamen
	 */
	private String getFilename(final Locale locale) {
		String name = config.replace('.', '/');
		String localeKey = "";

		if (locale != null) {
			localeKey = locale.toString();
			int underscore = localeKey.lastIndexOf("_");
			if (underscore > 0) {
				localeKey = localeKey.substring(0, underscore);
			}
		}

		if (localeKey.length() > 0) {
			name = name + "_" + localeKey;
		}

		return name + ".properties";
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

		Properties props = new Properties();

		// Load the specified property resource
		if (log.isTraceEnabled()) {
			log.trace("  Loading resource '" + name + "'");
		}

		Boolean dev = DynaSiteObjects.getDev();
		String pathName = null;
		if (dev == true) {
			pathName = Constants.LOCAL_CLASSES_PATH;
		} else {
			pathName = Constants.DEPLOY_CLASSES_PATH;
		}

		//String name = config.replace('.', '/');

		if (loadPropertiesFromFilesystem) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(pathName + name);
				if (fis != null) {
					try {
						props.load(fis);

					} catch (IOException e) {
						log.error("loadLocale()", e);
					}
				}
			} catch (FileNotFoundException e1) {
				log.debug(e1.getMessage());
			} finally {
				try {
					if (fis != null) {
						fis.close();
					}
				} catch (IOException e) {
					log.error("loadLocale()", e);
				}
			}
		} else {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			if (classLoader == null) {
				classLoader = this.getClass().getClassLoader();
			}

			InputStream is = null;
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
		}
		if (log.isTraceEnabled()) {
			log.trace("  Loading resource completed");
		}

		// Copy the corresponding values into our cache
		if (props.size() < 1) {
			return;
		}

		synchronized (messages) {
			//log.debug("Saving messages for localeKey: " + localeKey );
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

	public boolean isLoadPropertiesFromFilesystem() {
		return loadPropertiesFromFilesystem;
	}

	public void setLoadPropertiesFromFilesystem(boolean loadPropertiesFromFilesystem) {
		this.loadPropertiesFromFilesystem = loadPropertiesFromFilesystem;
	}
}



