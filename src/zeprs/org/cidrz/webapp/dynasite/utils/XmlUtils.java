/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;

import org.apache.commons.dbutils.QueryLoader;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.project.zeprs.valueobject.gen.LatentFirstStageLabour;
import org.cidrz.project.zeprs.valueobject.gen.PatientRegistration;
import org.cidrz.project.zeprs.valueobject.gen.PrevPregnancies;
import org.cidrz.project.zeprs.valueobject.gen.ProbLabor;
import org.cidrz.project.zeprs.valueobject.report.PatientStatusReport;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.dao.CommentDAO;
import org.cidrz.webapp.dynasite.dao.DistrictDAO;
import org.cidrz.webapp.dynasite.dao.DrugsDAO;
import org.cidrz.webapp.dynasite.dao.EncounterArchiveDAO;
import org.cidrz.webapp.dynasite.dao.EncounterValueArchiveDAO;
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.dao.FieldEnumerationDAO;
import org.cidrz.webapp.dynasite.dao.FlowDAO;
import org.cidrz.webapp.dynasite.dao.FormDAO;
import org.cidrz.webapp.dynasite.dao.FormDisplayDAO;
import org.cidrz.webapp.dynasite.dao.FormFieldDAO;
import org.cidrz.webapp.dynasite.dao.FormTypeDAO;
import org.cidrz.webapp.dynasite.dao.ImmunizationDAO;
import org.cidrz.webapp.dynasite.dao.OutcomeArchiveDAO;
import org.cidrz.webapp.dynasite.dao.OutcomeDAO;
import org.cidrz.webapp.dynasite.dao.PatientDAO;
import org.cidrz.webapp.dynasite.dao.PatientStatusDAO;
import org.cidrz.webapp.dynasite.dao.PregnancyDAO;
import org.cidrz.webapp.dynasite.dao.ProblemArchiveDAO;
import org.cidrz.webapp.dynasite.dao.ProblemDAO;
import org.cidrz.webapp.dynasite.dao.RuleDefinitionDAO;
import org.cidrz.webapp.dynasite.dao.SessionPatientDAO;
import org.cidrz.webapp.dynasite.dao.SiteDAO;
import org.cidrz.webapp.dynasite.dao.partograph.PartographDAO;
import org.cidrz.webapp.dynasite.dao.reports.ReportDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.rules.Outcome;
import org.cidrz.webapp.dynasite.rules.impl.EncounterOutcome;
import org.cidrz.webapp.dynasite.rules.impl.InfoOutcome;
import org.cidrz.webapp.dynasite.rules.impl.OutcomeImpl;
import org.cidrz.webapp.dynasite.rules.impl.ReferralOutcome;
import org.cidrz.webapp.dynasite.utils.admin.Rss;
import org.cidrz.webapp.dynasite.utils.sort.DateVisitOrderComparator;
import org.cidrz.webapp.dynasite.valueobject.Comment;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.EncounterValueArchive;
import org.cidrz.webapp.dynasite.valueobject.FieldEnumeration;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.valueobject.PageItem;
import org.cidrz.webapp.dynasite.valueobject.Partograph;
import org.cidrz.webapp.dynasite.valueobject.Patient;
import org.cidrz.webapp.dynasite.valueobject.Pregnancy;
import org.cidrz.webapp.dynasite.valueobject.Problem;
import org.cidrz.webapp.dynasite.valueobject.Server;
import org.cidrz.webapp.dynasite.valueobject.SessionPatient;
import org.codehaus.jackson.map.DeserializationProblemHandler;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.util.DTDEntityResolver;
import org.rti.zcore.sync.SyncFormat;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.alias.CannotResolveClassException;
import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.mapper.MapperWrapper;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.DeserializationProblemHandler;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
//import org.codehaus.jackson.map.annotate.JsonSerialize;

//import org.rti.zcore.utils.encryption.FileEncryption;


/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jul 7, 2005
 *         Time: 7:45:15 AM
 */
public class XmlUtils {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(XmlUtils.class);

    /**
     * Fetches an xml file. Custom wrapper code ignores fields that are not in the Object
     * This is useful when the client has an older version of the Object.
     * Custom MapperWrapper code kudos to http://pvoss.wordpress.com/2009/01/08/xstream/
     * @param fileName
     * @return
     * @throws IOException
     */
    public static Object getOne(String fileName) throws IOException, FileNotFoundException {
        //XStream xstream = new XStream();
        XStream xstream = new XStream() {
        	  @Override
        	  protected MapperWrapper wrapMapper(MapperWrapper next) {
        	    return new MapperWrapper(next) {

        	      @Override
        	      public boolean shouldSerializeMember(Class definedIn,
        	              String fieldName) {
        	        if (definedIn == Object.class) {
        	          return false;
        	        } else {
        	        	log.debug("Newer version of this field: " + fieldName+ " in class " + definedIn.toString() + " detected when deserializing XML.");
        	        }
        	        return super.shouldSerializeMember(definedIn, fieldName);
        	      }
        	    };
        	  }
        	};
        Reader reader = new BufferedReader((new FileReader(fileName)));
        Object xmlObject = null;
        try {
            xmlObject = xstream.fromXML(reader);
        } catch (ConversionException e) {
            log.error(e);
        }
        reader.close();
        return xmlObject;
    }
    
    /**
     * Fetches an xml file. Custom wrapper code ignores fields that are not in the Object
     * This is useful when the client has an older version of the Object.
     * Custom MapperWrapper code kudos to http://pvoss.wordpress.com/2009/01/08/xstream/
     * @param fileName
     * @param driverName - Specify json ; otherwise xml if null. Also checks if Constants.PATIENT_RECORD_OUTPUT.equals("json")
     * @param clazz TODO
     * @return
     * @throws IOException
     */
    public static Object getOne(String fileName, String driverName, Class clazz) throws IOException, FileNotFoundException {
    	Object object = null;
    	if (clazz != null) {
    		String syncFormat = null;
    		Object bean = null;
			try {
				bean = clazz.newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (bean instanceof SyncFormat) {
    			SyncFormat syncFormattable = (SyncFormat) bean;
				syncFormat = syncFormattable.getSyncFormat();
    			if (syncFormat != null) {
    				if (syncFormat.equals("json")) {
    					object = XmlUtils.getOneJackson(fileName, clazz);
    				}
    			}
    		} else {
        		object = getOneXStream(fileName, object, driverName);
    		}
    	}  else {
    		object = getOneXStream(fileName, object, driverName);
    	}
    	return object;

    }

	/**
	 * @param fileName
	 * @param object
	 * @param driverName TODO
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static Object getOneXStream(String fileName, Object object, String driverName) throws FileNotFoundException, IOException {
		XStream xstream = null;
		if (driverName != null || fileName.endsWith(".json")) {
			xstream = new XStream(new JettisonMappedXmlDriver());
		} else {
			xstream = new XStream() {
				@Override
				protected MapperWrapper wrapMapper(MapperWrapper next) {
					return new MapperWrapper(next) {

						@Override
						public boolean shouldSerializeMember(Class definedIn,
								String fieldName) {
							if (definedIn == Object.class) {
								return false;
							} else {
								log.debug("Newer version of this field: " + fieldName+ " in class " + definedIn.toString() + " detected when deserializing XML.");
							}
							return super.shouldSerializeMember(definedIn, fieldName);
						}
					};
				}
			};
		}
		FileReader fr = (new FileReader(fileName));
		Reader reader = new BufferedReader(fr);
		try {
			object = xstream.fromXML(reader);
		} catch (ConversionException e) {
			log.error(e);
			e.printStackTrace();
		} finally {
			reader.close();
			fr.close();
		}
		return object;
	}
	
	 /**
     * Gets a JSON file using Jackson lib. Decrypts it if possible.
     * TODO: Check if necessary file handles are being closed.
     * @param fileName
     * @param clazz
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static Object getOneJackson(String fileName, Class clazz) throws IOException, FileNotFoundException {
    	Boolean useFileEncryption = null;
    	Object bean = null;
		try {
			bean = clazz.newInstance();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//        if (bean instanceof Encryptable) {
//        	Encryptable encryptable = (Encryptable) bean;
//        	useFileEncryption = encryptable.getEncryptable();
//        }
    	ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
        mapper.getDeserializationConfig().addHandler(new JacksonProblemHandler());
    	//mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    	Object object = null;

    	// commented out for ZEPRS
    	
//    	if (useFileEncryption != null && useFileEncryption == true) {
//    		File encryptedFile = new File(fileName);
//			//File unencryptedFile = new File(fileName+".tmp");
//			// InputStream unencryptedStream = new FileInputStream(fileName+".tmp");
//			if (encryptedFile.exists()) {
//				FileEncryption secure;
//	    		try {
//	    			secure = new FileEncryption();
//	    			File encryptedKeyFile = new File(Constants.getPathToCatalinaHome() + "resources" + File.separator + "aes.key");
//	    			File privateKeyFile = new File(Constants.getPathToCatalinaHome() + "resources" + File.separator + "private.der");
//	    			secure.loadKey(encryptedKeyFile, privateKeyFile);
//	    			ByteArrayOutputStream os = secure.decrypt(encryptedFile);
//	    			ByteArrayInputStream bis = new ByteArrayInputStream(os.toByteArray());
//	        		try {
//	        			object = mapper.readValue(bis, clazz);
//	        		} catch (JsonMappingException e) {
//	        			log.debug("Probem deserialising " + fileName);
//	        			e.printStackTrace();
//	        		}
//	    			//unencryptedFile.delete();
//	    		} catch (GeneralSecurityException e) {
//	    			e.printStackTrace();
//	    		} catch (FileNotFoundException e) {
//	    			throw new FileNotFoundException();
//	    		} catch (IOException e) {
//	    			e.printStackTrace();
//
//	    		}
//			} else {
//				throw new FileNotFoundException("File not found at: " + fileName);
//			}
//    	} else {
    		try {
    			object = mapper.readValue(new File(fileName), clazz);
    		} catch (JsonMappingException e) {
    			log.debug("Probem deserialising " + fileName + " Error: " + e);
    			e.printStackTrace();
    		} catch (JsonParseException e) {
    			throw new IOException(e);
    		}
//    	}
    	return object;
    }

    public static Object getOneJackson(InputStream inputStream, Class clazz) throws IOException, FileNotFoundException {
    	ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
    	mapper.getDeserializationConfig().addHandler(new JacksonProblemHandler());
    	//mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    	Object object = null;
    	try {
    		object = mapper.readValue(inputStream, clazz);
    	} catch (JsonMappingException e) {
    		log.debug("Probem deserialising " + clazz.toString());
			e.printStackTrace();    	}
    	return object;
    }

    final static class JacksonProblemHandler extends DeserializationProblemHandler
    {
    	public boolean handleUnknownProperty(DeserializationContext ctxt, JsonDeserializer<?> deserializer,
    			Object bean, String propertyName)
    	throws IOException, JsonProcessingException
    	{
    		JsonParser jp = ctxt.getParser();

    		// very simple, just to verify that we do see correct token type
    		log.debug("Unknown property: " + propertyName+":"+jp.getCurrentToken().toString() + " in class: " + bean.getClass().getSimpleName() );
    		// Yup, we are good to go; must skip whatever value we'd have:
    		jp.skipChildren();
    		return true;
    	}
    }

    /**
     * Fetches an xml file. Custom wrapper code ignores fields that are not in the Object
     * This is useful when the client has an older version of the Object.
     * Custom MapperWrapper code kudos to http://pvoss.wordpress.com/2009/01/08/xstream/
     * @param inputStream InputStream
     * @return
     * @throws IOException
     */
    public static Object getOne(InputStream inputStream) throws IOException, ConversionException {
    	//XStream xstream = new XStream();
    	XStream xstream = new XStream() {
    		@Override
    		protected MapperWrapper wrapMapper(MapperWrapper next) {
    			return new MapperWrapper(next) {

    				@Override
    				public boolean shouldSerializeMember(Class definedIn,
    						String fieldName) {
    					if (definedIn == Object.class) {
    						return false;
    					} else {
    						String message = "Newer version of this field: " + fieldName+ " in class " + definedIn.toString() + " detected when deserializing XML.";
    						log.debug(message);
    					}
    					return super.shouldSerializeMember(definedIn, fieldName);
    				}
    			};
    		}
    	};
    	Object xmlObject = null;
    	try {
    		xmlObject = xstream.fromXML(inputStream);
    	} catch (ConversionException e) {
    		throw new ConversionException(e);
    	}
    	return xmlObject;
    }

    /**
     * Fetches an xml file. Custom wrapper code ignores fields that are not in the Object
     * This is useful when the client has an older version of the Object.
     * Custom MapperWrapper code kudos to http://pvoss.wordpress.com/2009/01/08/xstream/
     * @param fileName
     * @param driverName - Specify json ; otherwise xml if null.
     * @return
     * @throws IOException
     */
    public static Object getOne(String fileName, String driverName) throws IOException, FileNotFoundException {
    	//XStream xstream = new XStream();
    	//HierarchicalStreamDriver driver= null;
    	XStream xstream = null;
    	if (driverName != null) {
    		xstream = new XStream(new JettisonMappedXmlDriver());
    	} else {
    		xstream = new XStream() {
    			@Override
    			protected MapperWrapper wrapMapper(MapperWrapper next) {
    				return new MapperWrapper(next) {

    					@Override
    					public boolean shouldSerializeMember(Class definedIn,
    							String fieldName) {
    						if (definedIn == Object.class) {
    							return false;
    						} else {
    							log.debug("Newer version of this field: " + fieldName+ " in class " + definedIn.toString() + " detected when deserializing XML.");
    						}
    						return super.shouldSerializeMember(definedIn, fieldName);
    					}
    				};
    			}
    		};
    	}

    	Reader reader = new BufferedReader((new FileReader(fileName)));
    	Object xmlObject = null;
    	try {
    		xmlObject = xstream.fromXML(reader);
    	} catch (Exception e) {
    		log.error(e);
    	}
    	reader.close();
    	return xmlObject;
    }

    /**
     * Saves an xml file.
     *
     * @param bean
     * @param fileName
     * @throws IOException
     */
    public static void save(Object bean, String fileName) throws IOException {
        XStream xstream = new XStream();
        xstream.alias("log", org.apache.commons.logging.impl.Log4JLogger.class);
    	xstream.setMode(XStream.NO_REFERENCES);
        Writer writer = new BufferedWriter(new FileWriter(fileName));
        writer.write("<?xml version=\"1.0\"?>\n");
        xstream.toXML(bean, writer);
        writer.flush();
        writer.close();
    }

    /**
     * Saves as JSON object.
     * @param bean
     * @param fileName
     * @throws IOException
     */
    public static void saveJson(Object bean, String fileName) throws IOException {
    	XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
        xstream.alias("log", org.apache.commons.logging.impl.Log4JLogger.class);
    	xstream.setMode(XStream.NO_REFERENCES);
    	//xstream.addImplicitCollection(Patient.class, "pregnancyList", Pregnancy.class);
    	Writer writer = new BufferedWriter(new FileWriter(fileName));
    	xstream.toXML(bean, writer);
    	writer.flush();
    	writer.close();
    }

    /**
     * Saves a file and copies it to another location.
     * @param bean
     * @param fileName1
     * @param fileName2
     * @throws IOException
     */
    public static void saveAndCopy(Object bean, String fileName1, String fileName2) throws IOException {
        XStream xstream = new XStream();
        Writer writer = new BufferedWriter(new FileWriter(fileName1));
        writer.write("<?xml version=\"1.0\"?>\n");
        xstream.toXML(bean, writer);
        writer.flush();
        writer.close();
        try {
            FileUtils.copyQuick(fileName1, fileName2);
        } catch (Exception e) {
            log.error(e);
        }
    }

    /**
     * This pretties up the output a bit, using aliases.
     *
     * @param bean
     * @param fileName
     * @param abbrev
     * @param clazz
     * @throws IOException
     */
    public static void save(Object bean, String fileName, String abbrev, Class clazz) throws IOException {
        XStream xstream = new XStream();
        xstream.alias(abbrev, clazz);
        xstream.alias("log", org.apache.commons.logging.impl.Log4JLogger.class);
        Writer writer = new BufferedWriter(new FileWriter(fileName));
        writer.write("<?xml version=\"1.0\"?>\n");
        xstream.toXML(bean, writer);
        writer.flush();
        writer.close();
    }

    public static void saveDynasiteObjects(Object bean, String fileName, String xslFileName, String abbrev, Class clazz, Long formId, String className) throws IOException, NoSuchFieldException {
        XStream xstream = new XStream();
        Form form = (Form) DynaSiteObjects.getForms().get(formId);
        Set pageItems = form.getPageItems();
        for (Iterator iterator = pageItems.iterator(); iterator.hasNext();) {
            PageItem pageItem = (PageItem) iterator.next();
            xstream.aliasField(pageItem.getForm_field().getStarSchemaName(), clazz, "field" + pageItem.getForm_field().getId());
        }
        xstream.alias(abbrev, clazz);
        xstream.alias("log", org.apache.commons.logging.impl.Log4JLogger.class);
        Writer writer = new BufferedWriter(new FileWriter(fileName));
        writer.write("<?xml version=\"1.0\"?>\n");
        writer.write("<?xml-stylesheet type=\"text/xsl\" href=\"../xsl/" + className + ".xsl\" ?>\n");
        xstream.toXML(bean, writer);
        writer.flush();
        writer.close();
        writer = new BufferedWriter(new FileWriter(xslFileName));
        writer.write("<?xml version='1.0'?>\n" +
                "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n" +
                "<xsl:template match=\"/\">\n" +
                "  <html>\n" +
                "  <head><title>" + className + "</title>\n" +
                "   <link rel=\"stylesheet\" href=\"/zeprs/css/styles-moz.css\" charset=\"ISO-8859-1\" type=\"text/css\"/>" +
                "   </head>" +
                "  <body>\n" +
                "    <table class=\"enhancedtable\">\n" +
                "      <tr>\n");
        for (Iterator iterator = pageItems.iterator(); iterator.hasNext();) {
            PageItem pageItem = (PageItem) iterator.next();
            if (pageItem.getForm_field().isEnabled() && !pageItem.getForm_field().getType().equals("Display")) {
                writer.write("      <th>" + pageItem.getForm_field().getLabel() + "</th>\n");
            }
        }
        writer.write("      </tr>\n");
        writer.write("      <xsl:for-each select=\"list/" + abbrev + "\">\n" +
                "      <tr>\n");
        for (Iterator iterator = pageItems.iterator(); iterator.hasNext();) {
            PageItem pageItem = (PageItem) iterator.next();
            if (pageItem.getForm_field().isEnabled() && !pageItem.getForm_field().getType().equals("Display")) {
                writer.write("         <td><xsl:value-of select=\"" + pageItem.getForm_field().getStarSchemaName() + "\"/></td>\n");
            }
        }
        writer.write("      </tr>\n" +
                "      </xsl:for-each>\n" +
                "    </table>\n" +
                "  </body>\n" +
                "  </html>\n" +
                "</xsl:template>\n" +
                "</xsl:stylesheet>");
        writer.flush();
        writer.close();
    }

    public static void saveDynasiteObjectReports(Object bean, String fileName, String xslFileName, String abbrev, Class clazz, Long formId, String className) throws IOException, NoSuchFieldException {
        XStream xstream = new XStream();
        Form form = (Form) DynaSiteObjects.getForms().get(formId);
        Set pageItems = form.getPageItems();
        xstream.alias(abbrev, clazz);
        xstream.alias("log", org.apache.commons.logging.impl.Log4JLogger.class);
        Writer writer = new BufferedWriter(new FileWriter(fileName));
        writer.write("<?xml version=\"1.0\"?>\n");
        writer.write("<?xml-stylesheet type=\"text/xsl\" href=\"../xsl/" + className + "Resolved.xsl\" ?>\n");
        xstream.toXML(bean, writer);
        writer.flush();
        writer.close();
        writer = new BufferedWriter(new FileWriter(xslFileName));
        writer.write("<?xml version='1.0'?>\n" +
                "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n" +
                "<xsl:template match=\"/\">\n" +
                "  <html>\n" +
                "  <head><title>" + className + "</title>\n" +
                "   <link rel=\"stylesheet\" href=\"/zeprs/css/styles-moz.css\" charset=\"ISO-8859-1\" type=\"text/css\"/>\n" +
                "   </head>\n" +
                "  <body>\n" +
                "    <table class=\"enhancedtable\">\n" +
                "      <tr>\n");
        for (Iterator iterator = pageItems.iterator(); iterator.hasNext();) {
            PageItem pageItem = (PageItem) iterator.next();
            if (pageItem.getForm_field().isEnabled() && !pageItem.getForm_field().getType().equals("Display")) {
                writer.write("      <th>" + pageItem.getForm_field().getLabel() + "</th>\n");
            }
        }
        writer.write("      </tr>\n");
        writer.write("      <xsl:for-each select=\"list/" + abbrev + "\">\n" +
                "      <tr>\n");
        for (Iterator iterator = pageItems.iterator(); iterator.hasNext();) {
            PageItem pageItem = (PageItem) iterator.next();
            if (pageItem.getForm_field().isEnabled() && !pageItem.getForm_field().getType().equals("Display")) {
                writer.write("         <td><xsl:value-of select=\"" + pageItem.getForm_field().getStarSchemaName().replace("_", "__") + "R\"/></td>\n");
            }
        }
        writer.write("      </tr>\n" +
                "      </xsl:for-each>\n" +
                "    </table>\n" +
                "  </body>\n" +
                "  </html>\n" +
                "</xsl:template>\n" +
                "</xsl:stylesheet>");
        writer.flush();
        writer.close();
    }

    public static String prepareDynasiteObject(Object bean, String abbrev, Class clazz, Long formId) throws IOException, NoSuchFieldException {
        XStream xstream = new XStream();
        Form form = (Form) DynaSiteObjects.getForms().get(formId);
        Set pageItems = form.getPageItems();
        for (Iterator iterator = pageItems.iterator(); iterator.hasNext();) {
            PageItem pageItem = (PageItem) iterator.next();
            xstream.aliasField(pageItem.getForm_field().getStarSchemaName(), clazz, "field" + pageItem.getForm_field().getId());
        }
        xstream.alias(abbrev, clazz);
        xstream.alias("log", org.apache.commons.logging.impl.Log4JLogger.class);
        String xml = xstream.toXML(bean);
        return xml;
    }

    /**
     * Outputs forms to XML, which is used to render forms.
     * @param dev - if dev output to both DEV_XML_PATH amd DYNASITE_XML_PATH
     * @param conn
     * @param formId - used when rendering a single form; otherwise render all forms.
     * @return output messages from rendering process
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     * @throws ObjectNotFoundException
     */
    public static String outputForms(Boolean dev, Connection conn, Long formId) throws SQLException, ServletException, IOException, ObjectNotFoundException {
        String path = null;
        List<Form> forms = null;
        if (dev == true) {
            path = Constants.DEV_XML_PATH;
        } else {
            path = Constants.DYNASITE_XML_PATH;
        }
        StringBuffer sbuf = new StringBuffer();
        // log.debug("XML Forms Path: " + path);
        sbuf.append("XML Forms : " + path + "\n");
        // reset the rules in case rules were edited in the db
        HashMap rules = RuleDefinitionDAO.getAll(conn);
        DynaSiteObjects.setRules(rules);
        // reset the enumerations
        List fieldEnumList = FieldEnumerationDAO.getAll(conn);
        HashMap fieldEnumerations = new HashMap();
        for (int i = 0; i < fieldEnumList.size(); i++) {
            FieldEnumeration fieldEnum = (FieldEnumeration) fieldEnumList.get(i);
            fieldEnumerations.put(fieldEnum.getId(), fieldEnum);
        }
        DynaSiteObjects.setFieldEnumerations(fieldEnumerations);
        DynaSiteObjects.setFieldEnumerationsByField(null);
        DynaSiteObjects.getFieldEnumerationsByField();
        if (formId != null) {
        	forms = new ArrayList<Form>();
        	Form form = new Form();
        	form.setId(formId);
        	forms.add(form);
        } else {
        	forms = FormDisplayDAO.getAllFormIds(conn);
        }
        sbuf.append("Reset DynaSiteObjects.getForms\n");
        DynaSiteObjects.getForms().clear();
        sbuf.append("Forms output to xml:\n");

        for (int i = 0; i < forms.size(); i++) {
            Form simpleForm = (Form) forms.get(i);
            Form formObj = null;
			try {
				formObj = FormDisplayDAO.getFormGraph(conn, simpleForm.getId());
				String classname = StringManipulation.fixClassname(formObj.getName());
	            DynaSiteObjects.getForms().put(formObj.getId(), formObj);
	            if (dev == true) {
	                path = Constants.DEV_XML_PATH;
	                String path2 = Constants.DYNASITE_XML_PATH;
	                XmlUtils.saveAndCopy(formObj, path + classname + ".xml", path2 + classname + ".xml");
	            } else {
	                path = Constants.DYNASITE_XML_PATH;
	                XmlUtils.save(formObj, path + classname + ".xml");
	            }
	            // XmlUtils.save(formObj, path + classname + ".xml");
	            sbuf.append(" - added " + classname + "\n");
			} catch (ObjectNotFoundException e) {
				log.debug("Form not found - id: " + simpleForm.getId());
			}
        }
        sbuf.append("Done!\n");
        sbuf.append("DynaSiteObjects.getForms size: " + DynaSiteObjects.getForms().size() + "\n");
        String message = sbuf.toString();
        return message;
    }

    /**
     * Renders many of the xml files used by Dynasite - Fields.xml, Rules.xml, Forms.xml, etc.
     * @param dev - if dev output to both DEV_XML_PATH amd DYNASITE_XML_PATH
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     */
    public static String outputDynaSiteConfig(Boolean dev) throws SQLException, ServletException, IOException {
        Connection conn = DatabaseUtils.getAdminConnection();
        String path = null;
        String path2 = null;
        if (dev == true) {
            path = Constants.DEV_XML_PATH;
            path2 = Constants.DYNASITE_XML_PATH;
        } else {
            path = Constants.DYNASITE_XML_PATH;
        }
        StringBuffer sbuf = new StringBuffer();
        sbuf.append("Running outputDynaSiteConfig:\n");
        // log.debug("Path: " + path + "\n");
        sbuf.append("Path: " + path + "\n");
        List fieldEnumList = FieldEnumerationDAO.getAll(conn);
        if (dev == true) {
            XmlUtils.saveAndCopy(fieldEnumList, path + "Fields.xml", path2 + "Fields.xml");
            sbuf.append("Copied to Path: " + path2 + "\n");
        } else {
            XmlUtils.save(fieldEnumList, path + "Fields.xml");
        }
        sbuf.append(" - Fields output to xml.\n");
        HashMap rules = RuleDefinitionDAO.getAll(conn);
        if (dev == true) {
            XmlUtils.saveAndCopy(rules, path + "Rules.xml", path2 + "Rules.xml");
        } else {
            XmlUtils.save(rules, path + "Rules.xml");
        }
        sbuf.append(" - Rules output to xml.\n");
        List formList = FormDisplayDAO.getAllFormNames(conn);
        if (dev == true) {
            XmlUtils.saveAndCopy(formList, path + "Forms.xml", path2 + "Forms.xml");
        } else {
            XmlUtils.save(formList, path + "Forms.xml");
        }
        sbuf.append(" - Forms output to xml.\n");
        List activeFields = FormFieldDAO.getAllEnabledFields(conn);
        if (dev == true) {
            XmlUtils.saveAndCopy(activeFields, path + "ActiveFields.xml", path2 + "ActiveFields.xml");
        } else {
            XmlUtils.save(activeFields, path + "ActiveFields.xml");
        }
        sbuf.append(" - ActiveFields output to xml.\n");
        List drugs = DrugsDAO.getAll(conn);
        if (dev == true) {
            XmlUtils.saveAndCopy(drugs, path + "Drugs.xml", path2 + "Drugs.xml");
        } else {
            XmlUtils.save(drugs, path + "Drugs.xml");
        }
        sbuf.append(" - Drugs output to xml.\n");
        outputClinics(dev, conn, path, path2, sbuf);
        sbuf.append(" - Clinics output to xml.\n");
        outputSites(dev, conn, path, path2, sbuf);
        sbuf.append(" - Sites output to xml.\n");
        List districts = DistrictDAO.getAll(conn, "districtId");
        if (dev == true) {
            XmlUtils.saveAndCopy(districts, path + "Districts.xml", path2 + "Districts.xml");
        } else {
            XmlUtils.save(districts, path + "Districts.xml");
        }
        sbuf.append(" - Districts output to xml.\n");
        List immunizations = ImmunizationDAO.getAll(conn);
        if (dev == true) {
            XmlUtils.saveAndCopy(immunizations, path + "Immunizations.xml", path2 + "Immunizations.xml");
        } else {
            XmlUtils.save(immunizations, path + "Immunizations.xml");
        }
        sbuf.append(" - Immunizations output to xml.\n");
        outputFlow(dev, conn, path, path2, sbuf);
        sbuf.append(" - Flows output to xml.\n");
        sbuf.append("Done!\n");
        String message = sbuf.toString();
        return message;
    }

    /**
     * Outputs all sites to xml from database
     * @param dev
     * @param conn
     * @param path
     * @param path2
     * @param sbuf
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     */
    public static void outputSites(Boolean dev, Connection conn, String path, String path2, StringBuffer sbuf)
			throws SQLException, ServletException, IOException {
		List siteList = SiteDAO.getAll(conn, "name");
        if (dev == true) {
            XmlUtils.saveAndCopy(siteList, path + "Sites.xml", path2 + "Sites.xml");
        } else {
            XmlUtils.save(siteList, path + "Sites.xml");
        }
        sbuf.append(" - Sites output to xml.\n");
	}

    /**
     * Outputs clinics (excluding zeprs master) to xml from database
     * @param dev
     * @param conn
     * @param path
     * @param path2
     * @param sbuf
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     */
	public static void outputClinics(Boolean dev, Connection conn, String path, String path2, StringBuffer sbuf)
			throws SQLException, ServletException, IOException {
		List clinics = SiteDAO.getClinics(conn);
        if (dev == true) {
            XmlUtils.saveAndCopy(clinics, path + "Clinics.xml", path2 + "Clinics.xml");
        } else {
            XmlUtils.save(clinics, path + "Clinics.xml");
        }
        sbuf.append(" - Clinics output to xml.\n");
	}

	/**
	 * Outputs flows to xml from database
	 * @param dev
	 * @param conn
	 * @param path
	 * @param path2
	 * @param sbuf
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void outputFlow(Boolean dev, Connection conn, String path, String path2, StringBuffer sbuf)
	throws SQLException, ServletException, IOException {
		List flows = FlowDAO.getAll(conn);
        if (dev == true) {
            XmlUtils.saveAndCopy(flows, path + "Flows.xml", path2 + "Flows.xml");
        } else {
            XmlUtils.save(flows, path + "Flows.xml");
        }
		sbuf.append(" - Flows output to xml.\n");
	}
	
	/**
	 * Outputs formTypes to xml from database
	 * @param dev
	 * @param conn
	 * @param path
	 * @param path2
	 * @param sbuf
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void outputFormTypes(Boolean dev, Connection conn, String path, String path2, StringBuffer sbuf)
	throws SQLException, ServletException, IOException {
		String dynasiteOutputFormat = Constants.DYNASITE_FORMAT;
	    String dynasiteOutputFormatExtension = Constants.DYNASITE_FORMAT_EXTENSION;
		List formTypes = FormTypeDAO.getAll(conn);
		if (dev == true) {
			XmlUtils.saveAndCopy(formTypes, path + "FormTypes"  + dynasiteOutputFormatExtension, path2 + "FormTypes"  + dynasiteOutputFormatExtension);
		} else {
			XmlUtils.save(formTypes, path + "FormTypes"  + dynasiteOutputFormatExtension);
		}
		sbuf.append(" - FormTypes output to " + dynasiteOutputFormat + ".\n");
	}
	

    /**
     * Used in DynaSiteObjects for in-memory access of partograph table names
     *
     * @param list
     * @param path
     * @param ext
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static HashMap getClassTableNames(List list, String path, String ext) throws IOException, DocumentException {
        HashMap mappedObjects = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            String item = (String) list.get(i);
            // add (path + "BloodPressure" + ext);
            String className = path + item + ext;
            HashMap object = getClassTableName(className);
            mappedObjects.putAll(object);
        }
        return mappedObjects;
    }

    public static HashMap getClassTableName(String fileName) throws MalformedURLException, DocumentException {
        HashMap map = new HashMap();
        SAXReader reader = new SAXReader();
        reader.setValidation(false);
        reader.setEntityResolver(new DTDEntityResolver());
        File file = new File(fileName);
        Document document = reader.read(file);
        Element rootElement = document.getRootElement();
        Iterator elementIterator = rootElement.elementIterator();
        while (elementIterator.hasNext()) {
            Element element = (Element) elementIterator.next();
            map.put(element.attribute("name").getValue(), element.attribute("table").getValue());
        }
        return map;
    }

    public static HashMap getPropertyColumnNames(List list, String path, String ext) throws IOException, DocumentException {
        HashMap mappedObjects = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            String item = (String) list.get(i);
            Object object = getPropertyColumnName(path + item + ext);
            mappedObjects.put(item, object);
        }
        return mappedObjects;
    }

    public static HashMap getPropertyColumnName(String fileName) throws MalformedURLException, DocumentException {
        HashMap map = new HashMap();
        SAXReader reader = new SAXReader();
        reader.setValidation(false);
        reader.setEntityResolver(new DTDEntityResolver());
        File file = new File(fileName);
        Document document = reader.read(file);
        Element rootElement = document.getRootElement();
        Iterator elementIterator = rootElement.elementIterator();
        while (elementIterator.hasNext()) {
            Element element = (Element) elementIterator.next();
            List list = element.content();
            for (int i = 0; i < list.size(); i++) {
                Element item = null;
                try {
                    item = (Element) list.get(i);
                } catch (Exception e) {
                    // it's ok
                }
                try {
                    map.put(item.attribute("name").getValue(), item.attribute("column").getValue());
                } catch (Exception e) {
                    // it's ok
                }
            }
        }
        return map;
    }

    public static String generateReportXML(Connection conn) {
        HashMap forms = DynaSiteObjects.getForms();
        Set formSet = forms.entrySet();
        String message = null;
        for (Iterator iterator = formSet.iterator(); iterator.hasNext();) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Form dynaform = (Form) entry.getValue();
            if (dynaform.isEnabled()) {
                String fileName = StringManipulation.fixClassname(dynaform.getName()) + "Report";
                String className = "org.cidrz.project.zeprs.valueobject.gen." + StringManipulation.fixClassname(dynaform.getName());
                Class clazz = null;
                try {
                    clazz = Class.forName(className);
                } catch (ClassNotFoundException e) {
                    log.error(e);
                }
                try {
                    generateEncounterFile(conn, fileName, dynaform.getId(), clazz);
                    message = "Success!";
                } catch (Exception e) {
                    message = "Error: " + e;
                    log.error(e);
                }
            }
        }
        return message;
    }

    /**
     * Imports patient from XML files
     *
     * @param patient
      * @param conn
      * @param comments
      * @param reload
      * @param view
      * @return converted patient is not duplicate; otherwise, returns null patient
     * @throws Exception
     * @throws ServletException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Patient importPatientRecord(Patient patient, Connection conn, StringBuffer comments, Long reload, Boolean view) throws ClassNotFoundException, SQLException, ServletException, Exception {
    	Map queries = QueryLoader.instance().load("/" + Constants.SQL_PATIENT_PROPERTIES);
    	Long formId = new Long("1");
    	Form formDef = (Form) DynaSiteObjects.getForms().get(formId);
    	EncounterData vo = null;
    	Long patientId = null;
    	Long pregnancyId = null;
    	Long siteId = null;
    	String username = null;
    	String progress = null;
    	// Check if this patient already exists in the system - get patient id from uuid.
        String uuid = patient.getUuid();
        //String districtId = registration.getField1513();
        Long duplicateId = null;
        try {
			duplicateId = (Long) PatientDAO.getIdFromUuid(conn, uuid);
		} catch (SQLException e2) {
			log.error(e2);
		} catch (ServletException e2) {
			log.error(e2);
		}
		SessionPatient sessionPatient = patient.getSessionPatient();
    	if (duplicateId != null) {
    		Long importPatientId = patient.getImportPatientId();
    		Timestamp lastModified = null;
    		if (sessionPatient != null) {
    			lastModified = sessionPatient.getLastModified();
    		} else {
    			lastModified = patient.getPatientStatusreport().getLastModified();
    		}
    		SessionPatient dupSP = null;
    		try {
    			dupSP = SessionPatientDAO.getSessionPatient(conn, duplicateId, (long) 0);
    		} catch (ServletException e1) {
    			log.debug(e1);
    		} catch (SQLException e1) {
    			log.debug(e1);
    		}
    		Timestamp lastModifiedDupSp = dupSP.getLastModified();
    		if ((reload != null) || (view) || (lastModified.getTime() > lastModifiedDupSp.getTime())) {
    			// if ((reload != null) || (lastModified.getTime() > lastModifiedDupSp.getTime())) {
    			// importPatientId == null when this patient record was created at remote site - it wasn't imported from the master.
    			log.debug("Reload: " + reload + " Updating patient " + patient.getDistrictPatientid() + " importPatientId: " + importPatientId + " local id: " + dupSP.getId() + " local lastModified: " + lastModified + " remote last mod: " + lastModifiedDupSp);
    			try {
    				progress = "Updating patient record";
    				SyncUtils.updatePatientRecord(conn, dupSP, patient, duplicateId, comments, view);
    				// patient = new Patient();
    	    		// set some values for patient that will be used when importing
    	    		patient.setId(duplicateId);
    	    		patient.setPregnancyId(dupSP.getCurrentPregnancyId());
    			} catch (Exception e) {
    				log.debug(e + " Progress: " + progress);
    				e.printStackTrace();
    			}
    		} else {
    			// if the patient record is not being updated, set it to null so that it will not be archived to xml later.
    			patient = null;
    		}
    	} else {    // new patient
    		if (view) {
    			log.debug("Need to create new patient record.");
    		} else {
    			// check if this zeprs id has already been assigned - may have duplicate zeprs id's: different patients, same zeprs id
    			Patient localPatient = null;
    			try {
					localPatient = PatientDAO.getOneFromZeprsId(conn, patient.getDistrictPatientid());
					log.debug("Error: Patient with the zeprs id: " + patient.getDistrictPatientid() + " and uuid: " + uuid + " is already in the system. ");
				} catch (ObjectNotFoundException e3) {
					// it's ok - this really looks like a new patient
				}
				if (localPatient == null) {
					// Fetch the patient registration. This will create the new patient record as well as the Patient Registration record.
	    			PatientRegistration registration = patient.getPatientRegistration();
	    			// Attached the SessionPatient to the Registration encounter - we'll extract the uuid's from this when saving the patient record.
	    			registration.setSessionPatient(sessionPatient);
	    			//registration.setPregnancy(pregnancy);
	    			siteId = registration.getSiteId();
	    			String zeprsId = patient.getDistrictPatientid();
	    			// Next line is zeprs-only
	    			// Save patient id in patientId table.
	    			try {
	    				PatientDAO.importPatientId(zeprsId, conn);
	    			} catch (SQLException e1) {
	    				log.debug(e1);
	    			}
	    			registration.setImportEncounterId(registration.getId());
	    			Long encounterId = registration.getId();
	    			registration.setImportEncounterId(encounterId);
	    			//java.sql.Date dateVisit = registration.getDateVisit();
	    			username = registration.getCreatedBy();
					Pregnancy currentPregnancy = sessionPatient.getPregnancy();
	    			if (patient.getParentId() != null) {    // infant
	    				progress = "Creating patient record";
	    				patient.setDistrictPatientid(zeprsId);
	    				patient.setImportPatientId(patient.getId());
	    				String parentUuid = patient.getParentUuid();
	    				// fetch the current id of the mother
	    				Patient mother = null;
	    				try {
	    					try {
								mother = PatientDAO.getOne(conn, parentUuid);
								Long motherId = mother.getId();
		    					patient.setParentId(motherId);
		    					/*PatientStatusReport ps = PatientStatusDAO.getOne(conn, motherId);
		    					pregnancyId = ps.getCurrentPregnancyId();
		    					patient.setPregnancyId(pregnancyId);*/

		    					// You don't want to use the same pregnancyId as the mother,
		    					// because this infant may have been born in a previous pregnancy.
		    					String currentPregnancyUuid = currentPregnancy.getUuid();
								try {
									Pregnancy pregnancy = PregnancyDAO.getOne(conn, currentPregnancyUuid);
			    					patient.setPregnancyId(pregnancy.getId());
			    					pregnancyId = pregnancy.getId();
								} catch (ObjectNotFoundException e2) {
									// this simply should not happen.
									log.debug("While importing an infant: " + zeprsId + " , unable to find currentPregnancyUuid: " + currentPregnancyUuid);
								}
							} catch (ObjectNotFoundException e2) {
								// create a placeholder mother
	    						mother = new Patient();
	    						// create a placeholder pregnancy
	    						pregnancyId = PregnancyDAO.importPregnancy(conn, queries, currentPregnancy, null);
		    					patient.setPregnancyId(pregnancyId);
		    					patient.setParentId(null);
							}

	    					// create the patient record
	    					try {
	    						patientId = PatientDAO.save(conn, patient);
	    					} catch (Exception e1) {
	    						log.error(e1);
	    						throw new Exception("Unable to create patient record.");
	    					}
	    					comments.append("Added Infant ").append(patient.getDistrictPatientid()).append(" ");
	    					registration.setPatientId(patientId);
	    					registration.setPregnancyId(pregnancyId);
	    			        Map genQueries = QueryLoader.instance().load("/" + Constants.SQL_DEMO_PROPERTIES);
	    					try {
	    						encounterId = FormDAO.createEncounter(conn, genQueries, registration, formDef, username, siteId, null);
	    					} catch (Exception e1) {
	    						log.debug(e1);
	    					}
	    					registration.setId(encounterId);
	    					// create the entry in patient_status table
	    					Long currentFlowId = new Long("9");
	    					progress = "Saving patient registration";
	    					try {
	    						PatientStatusDAO.save(queries, patientId, currentFlowId, registration, username, siteId, conn);
	    					} catch (SQLException e) {
	    						log.debug(e + " Progress: " + progress);
	    					}
	    				} catch (SQLException e) {
	    					log.debug(e);
	    		            throw new SQLException(e);
	    				} catch (ServletException e) {
	    					log.debug(e);
	    				}
	    			} else {    // mother
	    				Pregnancy regPregnancy = registration.getPregnancy();
	    				String regUuid = null;
    					if (regPregnancy != null) {
        					regUuid = regPregnancy.getUuid();
    					} else {
    						log.debug("Patient Registration form's Pregnancy has no uuid. Patient: " + zeprsId);
    					}
	    				List pregList = patient.getPregnancyList();
	    				for (int i = 0; i < pregList.size(); i++) {
	    					Pregnancy pregnancy = (Pregnancy) pregList.get(i);
	    					if (pregnancy.getUuid() != null) {
	    						if (pregnancy.getUuid().equals(regUuid)) {
	    							// see if this pregnancy already is in the system
	    							try {
	    								Pregnancy existingPregnancy = PregnancyDAO.getOne(conn, pregnancy.getUuid());
	    								// if infant was imported before the mother, must update the patientId
	    								if (existingPregnancy.getPatientId() == null) {
	    									// update the pregnancy w/ the patientId in case the pregnancy was created from the infant record.
	    									// nope - patientId is not available yet - must persist the patient first.
	    		    						/*log.debug("Updating pregnancy; uuid: " + pregnancy.getUuid() + " patientId: " + patientId);
	    			                    	PregnancyDAO.updatePatientId(conn, pregnancy.getUuid(), patientId);*/
	    								}
	    							} catch (ObjectNotFoundException e) {
	    								pregnancyId = PregnancyDAO.importPregnancy(conn, queries, pregnancy, null);
	    								registration.setPregnancy(pregnancy);
	    								pregnancy.setId(pregnancyId);
	    							}
	    							break;
	    						}
	    					}
	    				}
	    				try {
	    					vo = PopulatePatientRecord.importForm(conn, registration, formDef, siteId, username);
	    				} catch (ClassNotFoundException e1) {
	    					log.debug(e1);
	    				} catch (SQLException e1) {
	    					log.debug(e1);
	    		            throw new SQLException(e1);
	    				} catch (ServletException e1) {
	    					log.debug(e1);
	    				} catch (Exception e1) {
	    					log.debug(e1);
	    				}
	    				patientId = vo.getPatientId();
	    				pregnancyId = vo.getPregnancyId();
	    				// update parent_id for infants that have already been imported.
	    				// get a list of children
	    				List children = PatientDAO.getChildren(conn, uuid, pregnancyId);
	    				// update parent_id
	    				for (Iterator iterator = children.iterator(); iterator.hasNext();) {
							Patient child = (Patient) iterator.next();
	        				PatientDAO.updateParentId(conn, patientId, child.getUuid());
						}
	    				// end of mother processing
	    			}
	    			List pregList = patient.getPregnancyList();
	    			HashMap newIdMap = new HashMap();
	    			for (int i = 0; i < pregList.size(); i++) {
	    				Pregnancy pregnancy = (Pregnancy) pregList.get(i);
	    				try {
							Pregnancy existingPregnancy = PregnancyDAO.getOne(conn, pregnancy.getUuid());
							if (existingPregnancy.getImportPregnancyId() != null) {
								pregnancy.setImportPregnancyId(existingPregnancy.getImportPregnancyId());
							}
							pregnancy.setId(existingPregnancy.getId());
							// if infant was imported before the mother, must update the patientId
							if (existingPregnancy.getPatientId() == null) {
								// update the pregnancy w/ the patientId in case the pregnancy was created from the infant record.
	    						log.debug("Updating pregnancy; uuid: " + pregnancy.getUuid() + " patientId: " + patientId);
		                    	PregnancyDAO.updatePatientId(conn, pregnancy.getUuid(), patientId);
							}
						} catch (ObjectNotFoundException e) {
							pregnancyId = PregnancyDAO.importPregnancy(conn, queries, pregnancy, patientId);
							registration.setPregnancy(pregnancy);
							pregnancy.setId(pregnancyId);
						}
	    				List encounters = pregnancy.getEncounters();
	    				DateVisitOrderComparator doc = new DateVisitOrderComparator();
	    		        Collections.sort(encounters, doc);
	    				for (Iterator iterator = encounters.iterator(); iterator.hasNext();) {
	    					EncounterData encounter = (EncounterData) iterator.next();
	    					encounter.setPregnancyId(pregnancyId);
	    					encounter.setPregnancy(pregnancy);
	    					progress = "Importing encounter id" + encounter.getId();
	    					if (encounter.getFormId() == 2) {
	    						// need to resolve the pregnancy id for imported fields
	    		        		PrevPregnancies prevPreg = (PrevPregnancies) encounter;
	    		        		if (prevPreg.getField1920() != null) {
	    		        			Long id;
	    							try {
	    								id = PregnancyDAO.getOneByImportedId(conn, prevPreg.getField1920(), patientId);
	    								prevPreg.setField1920(id);
	    							} catch (ObjectNotFoundException e) {
	    								// pregnancy not imported yet...too bad.
	    							}
	    		        		}
	    					}
	    					String encounterUuid = encounter.getUuid();
	    					Long encounterIdCheck = null;
	    					if (encounterUuid != null) {
	    						try {
									encounterIdCheck = EncountersDAO.checkEncounterUuid(conn, encounterUuid);
									//This is unusual - this encounter should not already be in the system, unless it is patient reg, which has already been processed
									if (encounter.getFormId() != 1) {
										log.debug("Rejected the import of an encounter with the same uuid as one already in the system. Uuid: " + encounterUuid + " encounter Id: " + encounterIdCheck + " patient uuid: " + uuid );
									}
								} catch (ObjectNotFoundException e) {
									// this is good.
		    						SyncUtils.importEncounter(encounter, patientId, pregnancyId, conn, username, newIdMap);
								}
	    					}
	    				}
	    				// import active/inactive problems
	    				try {
	    					List outcomes = pregnancy.getActiveProblems();
	    					XmlUtils.importProblems(outcomes, patientId, pregnancyId, conn, newIdMap);
	    					outcomes = pregnancy.getInActiveProblems();
	    					XmlUtils.importProblems(outcomes, patientId, pregnancyId, conn, newIdMap);
	    				} catch (Exception e) {
	    					log.debug(e + " Progress: " + progress);
	    				}
	    				if (patient.getParentId() == null) {
	    					progress = "Importing partograph";
	    					Partograph partograph = pregnancy.getPartograph();
	    					if (partograph != null) {
	    						try {
	    							PartographDAO.save(conn, patientId, pregnancyId, partograph);
	    						} catch (Exception e) {
	    							log.debug(e + " Progress: " + progress);
	    						}
	    					}
	    				}
	    			}
	    			// Loop through the imported eva list
	    			List<EncounterValueArchive> encounterValueArchiveList = patient.getEncounterRecordChanges();
	    			for (EncounterValueArchive eva : encounterValueArchiveList) {
	    				String encounterUuid = eva.getEncounterUuid();
	    				String evaUuid = eva.getUuid();
	    				// Check if this eva already exists - eva may have been imported by the child.
	    				try {
	    					EncounterValueArchiveDAO.getOne(conn, evaUuid);
	    				} catch (Exception e1) {
	    					// it's ok
	    					EncounterData encounter = null;
	    					try {
	    						encounter = (EncounterData) EncountersDAO.getOneByUuid(conn, encounterUuid);
	    					} catch (ObjectNotFoundException e) {
	    						// This may be a Delivery Summary record, which can be modified in both the mother and infant records
	    						// but it is only saved in the mother record, which may not be available.
	    						log.debug("Unable to fetch encounter for EncounterValueArchive. evaUuid: " + encounterUuid + " Form id: " + eva.getFormId());
	    						encounter = new EncounterData();
	    						encounter.setCreated(eva.getCreated());
	    						encounter.setCreatedBy(eva.getCreatedBy());
	    						encounter.setUuid(encounterUuid);
	    						encounter.setPatientId(patientId);
		    					encounter.setPregnancyId(pregnancyId);
	    					}
	    					EncounterValueArchiveDAO.save(conn, eva.getLastModifiedBy(), encounter, eva.getSiteId(), eva.getId(), eva.getPageItemId(), eva.getValue(), eva.getPreviousValue(), encounter.getPatientId(), encounter.getPregnancyId(), eva.getFieldId(), eva.getLastModified(), eva.getUuid());
	    				}
	    			}
	    			try {
	    				PatientStatusDAO.update(patient, newIdMap, pregnancyId, conn, queries, patientId);
	    			} catch (Exception e) {
	    				log.debug(e + " Progress: " + progress);
	    			}
	    			// need to reset some encounterId's in patient
	    			patient.setAddress(null);   // a placeholder - we are not setting this value anyways
	    			if (patient.getParentId() != null) {
	    				// log.debug("patient: " + patient.getId() + "encounterIdDeath: " + patient.getEncounterIdDeath());
	    			}
	    			Long encounterIdDeath = patient.getEncounterIdDeath();
	    			if (encounterIdDeath != null) {
	    				patient.setEncounterIdDeath((Long) newIdMap.get(encounterIdDeath));
	    			}
	    			patient.setId(patientId);
	    			patient.setPregnancyId(pregnancyId);
				}
    		}
    	}
    	return patient;
    }

    /**
     * Saves an imported comment
     * Used in importing patient records
     * @param comment
     * @param conn
     * @param comments
     * @throws SQLException
     * @throws ServletException
     */
    public static void saveImportedComment(Comment comment, Connection conn, StringBuffer comments) throws SQLException, ServletException {
        try {
            if (comment.getProblemId() != null) {
                Problem problem = ProblemDAO.getOneImported(conn, comment.getProblemId());
                comment.setProblemId(problem.getId());
                comment.setPatientId(problem.getPatientId());
                comment.setPregnancyId(problem.getPregnancyId());
                Long id = CommentDAO.save(conn, comment, comment.getCreatedBy(), comment.getSiteId());
                comments.append(" new comment id ").append(id);
            } else {
                OutcomeImpl outcome = (OutcomeImpl) OutcomeDAO.getOneImported(conn, comment.getOutcomeId());
                comment.setOutcomeId(outcome.getId());
                comment.setPatientId(outcome.getPatientId());
                comment.setPregnancyId(outcome.getPregnancyId());
                Long id = CommentDAO.save(conn, comment, comment.getCreatedBy(), comment.getSiteId());
                comments.append(" new comment id ").append(id);
            }
        } catch (ObjectNotFoundException e) {
            // it's ok - need to insert new problem then
            comments.append(" Problem/Outcome not imported yet. ");
        }
    }

    /**
     * Loops through patient.getEncounterRecordChanges and process all of the changes to the patient record.
     *
     * @param conn
     * @param duplicateId
     * @param patient
     * @param dupSP
     * @param view
     * @return
     * @throws ServletException
     * @throws SQLException
     */
    public static int updateEncounterValues(Connection conn, Long duplicateId, Patient patient, SessionPatient dupSP, Boolean view) throws ServletException, SQLException {
    	int updatesNum = 0;
    	List<EncounterValueArchive> newEncounterChanges = patient.getEncounterRecordChanges();
    	int newSize = 0;
    	if (newEncounterChanges != null) {
    		newSize = newEncounterChanges.size();
    	}
    	if (view) {
    		if (newEncounterChanges != null) {
    			log.debug("newEncounterChanges: " + newEncounterChanges.size());
    		}
    	}
    	if (!view) {
    		if (newSize > 0) {
    			for (int i = 0; i < newSize; i++) {
    				EncounterValueArchive updateEva = (EncounterValueArchive) newEncounterChanges.get(i);
    				String uuid = updateEva.getUuid();
    				try {
    					EncounterValueArchiveDAO.getOne(conn, uuid);
    				} catch (ObjectNotFoundException e1) {
    					try {
    						EncounterData importedEncounter = EncountersDAO.getNewEncounterData(conn, updateEva.getEncounterId());
							EncountersDAO.update(conn, updateEva.getValue(), updateEva.getPageItemId(), importedEncounter.getFormId(), importedEncounter.getId(), updateEva.getSiteId(), updateEva.getLastModifiedBy(), duplicateId, importedEncounter.getPregnancyId(), dupSP, updateEva.getLastModified(), uuid);
    						updatesNum++;
    					} catch (ObjectNotFoundException e) {
    						log.debug("Imported encounter not found - id: " + updateEva.getEncounterId() + " patient id: " + duplicateId);
    					} catch (NullPointerException e) {
    						log.debug("NPE: " + updateEva.getEncounterId() + " patient id: " + duplicateId + " value: " + updateEva.getValue());
    					} catch (PersistenceException e) {
    						log.debug(e);
    					}
    				}
    			}
    		}
    	}
    	return updatesNum;
    }

    /**
     * Imports a list of problems
     * @param outcomes
     * @param patientId
     * @param pregnancyId
     * @param conn
     * @param newIdMap
     */
    public static void importProblems(List outcomes, Long patientId, Long pregnancyId, Connection conn, HashMap newIdMap) {
        for (int j = 0; j < outcomes.size(); j++) {
            Object problem = outcomes.get(j);
            if (problem.getClass().equals(Problem.class)) {
                Problem thisProblem = (Problem) problem;
                thisProblem.setPatientId(patientId);
                thisProblem.setPregnancyId(pregnancyId);
                if (thisProblem.getId() != null) {
                    thisProblem.setImportProblemId(thisProblem.getId());
                }
                Long problemId = null;
                try {
                    problemId = ProblemDAO.save(conn, thisProblem, thisProblem.getCreatedBy(), thisProblem.getSiteId(), thisProblem.getUuid(), thisProblem.getPatientUuid(), thisProblem.getPregnancyUuid());
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ServletException e) {
                    e.printStackTrace();
                }
                //Set comments = thisProblem.getComments();
                List comments = thisProblem.getComments();
                for (int i = 0; i < comments.size(); i++) {
                    Comment comment = (Comment) comments.get(i);
                    comment.setImportCommentId(comment.getId());
                    comment.setPatientId(patientId);
                    comment.setPregnancyId(pregnancyId);
                    comment.setProblemId(problemId);
                    try {
                        CommentDAO.save(conn, comment, comment.getCreatedBy(), comment.getSiteId());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ServletException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                Outcome outcome = (Outcome) problem;
                outcome.setPatientId(patientId);
                outcome.setPregnancyId(pregnancyId);
                // grab the new id and replace the orginal id
                if (outcome.getEncounterId() != null) {
                    outcome.setImportOutcomeId(outcome.getId());
                    outcome.setImportEncounterId(outcome.getEncounterId());
                    Long newEncounterId = (Long) newIdMap.get(outcome.getEncounterId());
                    outcome.setEncounterId(newEncounterId);
                }
                Long outcomeId = null;
                try {
                    outcomeId = OutcomeDAO.save(conn, outcome, outcome.getCreatedBy(), outcome.getSiteId());
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ServletException e) {
                    e.printStackTrace();
                }
                List comments = outcome.getComments();
                for (int i = 0; i < comments.size(); i++) {
                    Comment comment = (Comment) comments.get(i);
                    comment.setImportCommentId(comment.getId());
                    comment.setPatientId(patientId);
                    comment.setPregnancyId(pregnancyId);
                    comment.setOutcomeId(outcomeId);
                    try {
                        CommentDAO.save(conn, comment, comment.getCreatedBy(), comment.getSiteId());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ServletException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Generates patient record.
     * If it is a mother, a list of infants in current pregnancy is included.
     * @param conn
     * @param patientId
     * @param niceFieldNames
     * @return TODO
     * @throws java.sql.SQLException
     * @throws org.cidrz.webapp.dynasite.exception.ObjectNotFoundException
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws org.cidrz.webapp.dynasite.exception.PersistenceException
     * @throws java.lang.reflect.InvocationTargetException
     */
    public static Patient generatePatient(Connection conn, Long patientId, boolean niceFieldNames) throws SQLException, ObjectNotFoundException, ServletException, IOException, ClassNotFoundException, IllegalAccessException, PersistenceException, InvocationTargetException {

        Patient patient = PatientDAO.getWholePatient(conn, patientId);
        PatientStatusReport psr = PatientStatusDAO.getOne(conn, patientId);
        patient.setPatientStatusreport(psr);
        List problems = ProblemDAO.getAll(conn, patient.getId());
        patient.setProblems(problems);
        List comments = CommentDAO.getAll(conn, patient.getId());
        patient.setComments(comments);
        Long currentPregnancyId = psr.getCurrentPregnancyId();
        String uuid = patient.getUuid();
        List pregnancies = null;
        SessionPatient sessionPatient = SessionPatientDAO.getSessionPatient(conn, patientId, new Long("0"));
        Pregnancy currentPregnancy = null;
		try {
			currentPregnancy = PregnancyDAO.getOne(conn, currentPregnancyId);
		} catch (ObjectNotFoundException e) {
			// log.debug("Pregnancy not found for this patient id: " + patientId + " pregnancy id: " + currentPregnancyId + " Creating a pregnancy stub. ");
    		currentPregnancy = new Pregnancy();
    		if (currentPregnancyId != null) {
    			currentPregnancy.setId(currentPregnancyId);
        		currentPregnancy.setUuid(psr.getCurrentPregnancyUuid());
    		} else {
    			// In some circumstances, the pregnancy id is missing. Can you find it?
    			try {
					EncounterData enc = EncountersDAO.getEncounterWithPregnancyId(conn, patientId);
					currentPregnancyId = enc.getPregnancyId();
					currentPregnancy.setId(currentPregnancyId);
					try {
						currentPregnancy = PregnancyDAO.getOne(conn, currentPregnancyId);
		        		currentPregnancy.setUuid(currentPregnancy.getUuid());
					} catch (ObjectNotFoundException e1) {
						log.debug("Pregnancy still not found for patient " + patient.getDistrictPatientid() + " - id: " + patientId + " pregnancy id: " + currentPregnancyId);
					}
				} catch (ObjectNotFoundException e1) {
			        Map queries = QueryLoader.instance().load("/" + Constants.SQL_PATIENT_PROPERTIES);
			        Date dateVisit = null;
			        if (patient.getCreated() != null) {
				        dateVisit =  new java.sql.Date(patient.getCreated().getTime());
			        }
			        EncounterData vo = new EncounterData();
			        currentPregnancyId = PregnancyDAO.createPregnancy(conn, queries, vo, patientId, dateVisit, "zepadmin", patient.getSiteId());
	    			currentPregnancy.setId(currentPregnancyId);
	    			currentPregnancy = PregnancyDAO.getOne(conn, currentPregnancyId);
	        		currentPregnancy.setUuid(currentPregnancy.getUuid());
					log.debug("Created a replacement Pregnancy record for patient id: " + patientId + " new pregnancy id: " + currentPregnancyId);
					List<EncounterData> encounters = EncountersDAO.getAllNullPregnancyId(conn, patientId);
					for (EncounterData encounterData : encounters) {
						Long id = encounterData.getId();
						EncountersDAO.updatePregEncounterTable(conn, id, currentPregnancyId);
					}
					log.debug("Updated pregnancy id's in encounters for patient id: " + patientId + " new pregnancy id: " + currentPregnancyId);
				}
    		}
		}
        sessionPatient.setPregnancy(currentPregnancy);
        patient.setSessionPatient(sessionPatient);
        List encounterDeletions = EncounterArchiveDAO.getAll(conn, patientId);
        patient.setEncounterDeletions(encounterDeletions);
        List outcomeDeletions = OutcomeArchiveDAO.getAll(conn, patientId);
        patient.setOutcomeDeletions(outcomeDeletions);
        List problemDeletions = ProblemArchiveDAO.getAll(conn, patientId);
        patient.setProblemDeletions(problemDeletions);
        //xstream.alias("log", org.apache.commons.logging.impl.Log4JLogger.class);
        int numMotherEncounters = 0;
        Timestamp lastCreatedEncounterMother = null;
        Long parentId = patient.getParentId();
        String parentUuid = patient.getParentUuid();
        if (parentId != null || parentUuid != null) {
            pregnancies = new ArrayList();
        	pregnancies.add(currentPregnancy);
            getCompletePregnancyRecord(conn, patient, currentPregnancyId, currentPregnancy, niceFieldNames);
        } else {
            pregnancies = PregnancyDAO.getPatientPregnancies(conn, patientId);
            for (int i = 0; i < pregnancies.size(); i++) {
                Pregnancy pregnancy = (Pregnancy) pregnancies.get(i);
                Long pregnancyId = pregnancy.getId();
                if (currentPregnancyId == null && i == pregnancies.size() - 1) {
                    currentPregnancyId = pregnancy.getId();
                }
                getCompletePregnancyRecord(conn, patient, pregnancyId, pregnancy, niceFieldNames);
                if (pregnancy.getEncounters() != null) {
                    numMotherEncounters =+ pregnancy.getEncounters().size();
                }
                List infants = PatientDAO.getChildren(conn, uuid, pregnancyId);
                pregnancy.getInfants().addAll(infants);
            }
        }
        if (!niceFieldNames) {
            if (pregnancies.size() > 0) {
                Pregnancy preg1 = (Pregnancy) pregnancies.get(0);
                List encounters = preg1.getEncounters();
                for (int i = 0; i < encounters.size(); i++) {
                    EncounterData encounter = (EncounterData) encounters.get(i);
                    if (encounter.getFormId().intValue() == 1) {
                        patient.setPatientRegistration((PatientRegistration) encounter);
                    }
                    if (lastCreatedEncounterMother == null || encounter.getCreated().getTime() > lastCreatedEncounterMother.getTime())
                    {
                        lastCreatedEncounterMother = encounter.getCreated();
                    }
                }
            }
        }
        patient.setTotalEncounters(Integer.valueOf(numMotherEncounters));
        patient.setLastCreatedEncounter(lastCreatedEncounterMother);
        patient.setPregnancyList(pregnancies);
        List encounterChanges = EncounterValueArchiveDAO.getAll(conn, patientId);
        patient.setEncounterRecordChanges(encounterChanges);
        return patient;
    }

    /**
     * Adds all encounters, problems, and outcomes to the pregnancy.
     * Used for xml output.
     * @param conn
     * @param patient TODO
     * @param pregnancyId
     * @param pregnancy
     * @param niceFieldNames
     * @throws ClassNotFoundException
     * @throws java.io.IOException
     * @throws javax.servlet.ServletException
     * @throws java.sql.SQLException
     * @throws org.cidrz.webapp.dynasite.exception.ObjectNotFoundException
     * @throws org.cidrz.webapp.dynasite.exception.PersistenceException
     * @throws IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     */
    public static void getCompletePregnancyRecord(Connection conn, Patient patient, Long pregnancyId, Pregnancy pregnancy, boolean niceFieldNames) throws ClassNotFoundException, IOException, ServletException, SQLException, ObjectNotFoundException, PersistenceException, IllegalAccessException, InvocationTargetException {
        Long patientId = patient.getId();
        Long parentId = patient.getParentId();
    	// get data from encounters
        List<EncounterData> encounterList = new ArrayList();
        HashMap forms = DynaSiteObjects.getForms();
        Set formSet = forms.keySet();
        for (Iterator iterator = formSet.iterator(); iterator.hasNext();) {
            Long formId = (Long) iterator.next();
            Form form = (Form) forms.get(formId);
            String tableName = StringManipulation.fixClassname(form.getName());
            List<EncounterData> encList = null;
            if (niceFieldNames == true) {
                String classname = "org.cidrz.project.zeprs.valueobject.report.gen." + tableName + "Report";
                Class encClazz = Class.forName(classname);
                encList = EncountersDAO.getAllReport(conn, patientId, pregnancyId, formId, encClazz);
            } else {
                String classname = "org.cidrz.project.zeprs.valueobject.gen." + tableName;
                Class encClazz = Class.forName(classname);
                encList = EncountersDAO.getAll(conn, patientId, pregnancyId, formId, encClazz);
            }
            if (encList != null && encList.size() > 0) {
                encounterList.addAll(encList);
                //   xstream.alias(tableName, encClazz);
            }
        }
        DateVisitOrderComparator doc = new DateVisitOrderComparator();
        Collections.sort(encounterList, doc);
        pregnancy.setEncounters(encounterList);
     // No need for the following code - pregnancy LabourAdmissionEncounterId and LabourAdmissionEncounterUuid is already populated...
        //Long labourAdmissionEncounterId = pregnancy.getLabourAdmissionEncounterId();
        /*String labourAdmissionEncounterUuid = pregnancy.getLabourAdmissionEncounterUuid();
        if (labourAdmissionEncounterUuid != null) {
        	// it can be either ProbLabor or latentFirstStageLabour
        	EncounterData enc = null;
			try {
				enc = (EncounterData) EncountersDAO.getOneByUuid(conn, labourAdmissionEncounterUuid);
				if (enc != null) {
	        		int formId = enc.getFormId().intValue();
	        		switch (formId) {
					case 17:
						LatentFirstStageLabour latentFirstStageLabourEnc;
							try {
								latentFirstStageLabourEnc = (LatentFirstStageLabour) EncountersDAO.getOneById(conn, enc.getId(), Long.valueOf("17"), LatentFirstStageLabour.class);
								pregnancy.setLabourAdmissionEncounterId(latentFirstStageLabourEnc.getId());
					            pregnancy.setLabourAdmissionEncounterUuid(latentFirstStageLabourEnc.getUuid());
					            } catch (ObjectNotFoundException e) {
					            	log.debug("XML import: LatentFirstStageLabour encounter not found. labourAdmissionEncounterId: " + enc.getId() + " labourAdmissionEncounterUuid: " + labourAdmissionEncounterUuid);
							}
						break;
					case 65:
						try {
								ProbLabor labourAdmissionEncounter = (ProbLabor) EncountersDAO.getOneById(conn, enc.getId(), Long.valueOf("65"), ProbLabor.class);
								pregnancy.setLabourAdmissionEncounterId(labourAdmissionEncounter.getId());
					            pregnancy.setLabourAdmissionEncounterUuid(labourAdmissionEncounter.getUuid());
					            } catch (ObjectNotFoundException e) {
					            	log.debug("XML import: labourAdmissionEncounter encounter not found. labourAdmissionEncounter: " + enc.getId() + " labourAdmissionEncounterUuid: " + labourAdmissionEncounterUuid);
							}
						break;
					default:
						break;
					}
	        	}
			} catch (ObjectNotFoundException e1) {
				 log.error("active Labour Encounter not found for patient: " + patientId + " pregnancyId: " + pregnancyId + " labourAdmissionEncounterUuid: " + labourAdmissionEncounterUuid);
			}
        }*/

        // loop through encounters and create a map
        Map encounterDateMap = new HashMap();
        // create a basic Pregnancy record that provides a bit more info than pregnancyId
        // This will be helpful in creating a new pregnancy record when an infant record is imported before the mother.
        Pregnancy basicPregnancy = new Pregnancy();
        basicPregnancy.setId(pregnancyId);
        basicPregnancy.setUuid(pregnancy.getUuid());
        basicPregnancy.setDatePregnancyBegin(pregnancy.getDatePregnancyBegin());
        for (int i = 0; i < encounterList.size(); i++) {
            EncounterData encounterData = (EncounterData) encounterList.get(i);
            encounterData.setPregnancy(basicPregnancy);
            Long encounterId = null;
            if (encounterData.getImportEncounterId() != null) {
                encounterId = encounterData.getImportEncounterId();
            } else {
                encounterId = encounterData.getId();
            }
            encounterDateMap.put(encounterId, encounterData.getLastModified());
        }
        pregnancy.setEncounterDateMap(encounterDateMap);

        List activeProblems = PatientRecordUtils.assembleProblemList(conn, patientId, pregnancyId, Boolean.TRUE);
        for (int j = 0; j < activeProblems.size(); j++) {
            Object problem = activeProblems.get(j);
            LastModifiedComparator c = new LastModifiedComparator();
            if (problem.getClass().equals(Problem.class)) {
                Problem thisProblem = (Problem) problem;
                List comments = CommentDAO.getAllforProblemFullname(conn, patientId, thisProblem.getId());
                thisProblem.setComments(comments);
            } else if (problem.getClass().equals(InfoOutcome.class)) {
                InfoOutcome thisProblem = (InfoOutcome) problem;
                List comments = CommentDAO.getAllforOutcome(conn, thisProblem.getId());
                thisProblem.setComments(comments);
                //Set commentsSet = DataStructureUtils.listToSet(comments, c);
                //thisProblem.setComments(commentsSet);
            } else if (problem.getClass().equals(ReferralOutcome.class)) {
                ReferralOutcome thisProblem = (ReferralOutcome) problem;
                List comments = CommentDAO.getAllforOutcome(conn, thisProblem.getId());
                thisProblem.setComments(comments);
                //Set commentsSet = DataStructureUtils.listToSet(comments, c);
                //thisProblem.setComments(commentsSet);
            } else if (problem.getClass().equals(EncounterOutcome.class)) {
                EncounterOutcome thisProblem = (EncounterOutcome) problem;
                List comments = CommentDAO.getAllforOutcome(conn, thisProblem.getId());
                thisProblem.setComments(comments);
                //Set commentsSet = DataStructureUtils.listToSet(comments, c);
                //thisProblem.setComments(commentsSet);
            }
        }
        pregnancy.setActiveProblems(activeProblems);
        List inActiveProblems = PatientRecordUtils.assembleProblemList(conn, patientId, pregnancyId, Boolean.FALSE);
        for (int j = 0; j < inActiveProblems.size(); j++) {
            Object problem = inActiveProblems.get(j);
            LastModifiedComparator c = new LastModifiedComparator();
            if (problem.getClass().equals(Problem.class)) {
                Problem thisProblem = (Problem) problem;
                List comments = CommentDAO.getAllforProblemFullname(conn, patientId, thisProblem.getId());
                //Set commentsSet = DataStructureUtils.listToSet(comments, c);
                thisProblem.setComments(comments);
            } else if (problem.getClass().equals(InfoOutcome.class)) {
                InfoOutcome thisProblem = (InfoOutcome) problem;
                List comments = CommentDAO.getAllforOutcome(conn, thisProblem.getId());
                thisProblem.setComments(comments);
                //Set commentsSet = DataStructureUtils.listToSet(comments, c);
                //thisProblem.setComments(commentsSet);
            } else if (problem.getClass().equals(ReferralOutcome.class)) {
                ReferralOutcome thisProblem = (ReferralOutcome) problem;
                List comments = CommentDAO.getAllforOutcome(conn, thisProblem.getId());
                thisProblem.setComments(comments);
                //Set commentsSet = DataStructureUtils.listToSet(comments, c);
                //thisProblem.setComments(commentsSet);
            } else if (problem.getClass().equals(EncounterOutcome.class)) {
                EncounterOutcome thisProblem = (EncounterOutcome) problem;
                List comments = CommentDAO.getAllforOutcome(conn, thisProblem.getId());
                thisProblem.setComments(comments);
                //Set commentsSet = DataStructureUtils.listToSet(comments, c);
                //thisProblem.setComments(commentsSet);
            }
        }
        pregnancy.setInActiveProblems(inActiveProblems);
        Partograph partograph = PartographDAO.getPartograph(conn, patientId, pregnancyId);
        pregnancy.setPartograph(partograph);
        List edits = EncounterValueArchiveDAO.getAll(conn, patientId);
        pregnancy.setModifiedValues(edits);
    }

    /**
     * XML patient record generation
     * @param conn
     * @param fileName
     * @param formId
     * @param clazz
     */
    public static void generateEncounterFile(Connection conn, String fileName, Long formId, Class clazz) {
        List encounters = null;
        List resolvedEncounters = null;
        try {
            encounters = EncountersDAO.getAll(conn, formId, clazz);
            String abbrev = fileName;
            if (fileName.length() >= 4) {
                abbrev = fileName.substring(0, 4);
            }
            saveDynasiteObjects(encounters, Constants.REPORTS_XML_PATH + fileName + ".xml", Constants.REPORTS_XSL_PATH + fileName + ".xsl", abbrev, clazz, formId, fileName);
            // log.debug("Listing for form id: " + formId + " created at " + org.cidrz.webapp.dynasite.Constants.REPORTS_XML_PATH + fileName + ".xml");
            String className = "org.cidrz.project.zeprs.valueobject.report.gen." + StringManipulation.fixClassname(fileName);
                Class reportClazz = null;
                try {
                    reportClazz = Class.forName(className);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            java.sql.Date beginDate = java.sql.Date.valueOf("2005-10-01");
            java.sql.Date endDate = java.sql.Date.valueOf(DateUtils.getNow().toString());
            resolvedEncounters = ReportDAO.getAll(conn, beginDate, endDate, 0, formId.intValue(), reportClazz);
            saveDynasiteObjectReports(resolvedEncounters, Constants.REPORTS_XML_PATH + fileName + "Resolved.xml", Constants.REPORTS_XSL_PATH + fileName + "Resolved.xsl", abbrev, reportClazz, formId, fileName);

        } catch (IOException e) {
            log.error(e);
        } catch (ServletException e) {
            log.error(e);
        } catch (SQLException e) {
            log.error(e);
        } catch (NoSuchFieldException e) {
            log.error(e);
        }
    }

	/**
	 * Download and de-serialize patient record from master server.
	 * @param zeprsId
	 * @return
	 * @throws IOException
	 * @throws ConversionException
	 * @throws CannotResolveClassException
	 */
	public static Patient downloadPatientRecord(String zeprsId) throws IOException, ConversionException, CannotResolveClassException {
		Patient patient = null;
		String masterFile = Constants.ARCHIVE_PATH_SERVERS + "master.xml";
		Server server = null;
		try {
			server = (Server) getOne(masterFile, null);
		} catch (FileNotFoundException e) {
			// we cannot proceed.
			//request.setAttribute("exception", "Must setup Master server info.");
			//return mapping.findForward("error");
		}
		String location = server.getLocation();
		String masterUrl = server.getUrl();
		String username = server.getUsername();
		String password = server.getPassword();
		String authString = "?j_username="+username+"&j_password="+password;
		String jsessionidString = "jsessionid=";
		String jsessionid = null;
		String homeUrl = "http://" + location + "/zeprs";

		// first get  a session
		// System.out.println("Target URL: " + homeUrl);
		HttpState initialState = new HttpState();
		HttpClient httpclient = new HttpClient();
		httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(30000);
		httpclient.setState(initialState);

		GetMethod httpget = new GetMethod(homeUrl);
		httpget.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
		int result = httpclient.executeMethod(httpget);
		//log.debug("Response status code: " + result);
		String returnedPath = httpget.getPath();
		//log.debug("returnedPath " + returnedPath);
		jsessionid = returnedPath.replace("/zeprs/home.do;jsessionid=", "");

		// now login w/ credentials in the querystring
		String loginUrl = "http://" + location + "/zeprs/j_security_check" + ";" + jsessionidString + jsessionid + authString;
		//log.debug("loginUrl " + loginUrl);
		httpget = new GetMethod(loginUrl);
		httpget.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
		result = httpclient.executeMethod(httpget);
		//log.debug("Response status code: " + result);
		returnedPath = httpget.getPath();
		//log.debug("returnedPath " + returnedPath);

		// now go to the app
		String importPatientUrl = "http://" + location + masterUrl + ";" + jsessionidString + jsessionid + "?zeprsId=" + zeprsId ;
		//log.debug("importPatientUrl: " + importPatientUrl);
		httpget = new GetMethod(importPatientUrl);
		httpget.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
		result = httpclient.executeMethod(httpget);
		//log.debug("Response status code: " + result);
		returnedPath = httpget.getPath();
		if (httpget.getStatusCode() == 500) {
			log.debug("Unable to fetch record. importPatientUrl: " + importPatientUrl );
		}
		//log.debug("returnedPath " + returnedPath);
		//int statuscode = httpget.getStatusCode();
		//log.debug("statuscode: " + statuscode);
		String importPath = Constants.PATIENTS_IMPORTED_PATH;
		InputStream inputStream = httpget.getResponseBodyAsStream();

		// de-serialise the string to create the patient object.
		XStream xstream = new XStream();
		//try {
		patient = (Patient) xstream.fromXML(inputStream);
		String surname = patient.getSurname();
		Long patientId = patient.getId();
		// once you have the patient object, you can construct the filename.
		String patientRecordFileName = Rss.getPatientFilename(zeprsId, null, null);
		// Create file
		OutputStream out = new FileOutputStream(importPath + patientRecordFileName);
		byte[] buf = new byte[1024];
		int len;
		while ((len = inputStream.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		out.close();
		FileUtils.log.debug("Importing patient file to " + importPath + patientRecordFileName);
		/*
		 * } catch (ConversionException e) {
	        log.error(e);
	    } catch (CannotResolveClassException e) {
	    	log.error(e);
	    	//request.setAttribute("exception", "Unable to retrieve patient record. Error: " + e);
	    	//return mapping.findForward("error");
	    }*/
		return patient;
	}

	/**
	 * Download and de-serialize patient record from the specified server.
	 * @param zeprsId
	 * @param urlString
	 * @return
	 * @throws IOException
	 * @throws ConversionException
	 * @throws CannotResolveClassException
	 */
	public static Patient downloadPatientRecord(String zeprsId, Server server) throws IOException, ConversionException, CannotResolveClassException {
		Patient patient = null;

		String location = server.getLocation();
		String url = server.getUrl();
		String username = server.getUsername();
		String password = server.getPassword();
		String authString = "?j_username="+username+"&j_password="+password;
		String jsessionidString = "jsessionid=";
		String jsessionid = null;
		String homeUrl = "http://" + location + "/zeprs";
		//String homeUrl = "http://" + location + "/zeprs/home.do;jsessionid=x";

		// first get  a session
		// System.out.println("Target URL: " + homeUrl);
		HttpState initialState = new HttpState();
		HttpClient httpclient = new HttpClient();
		httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(30000);
		httpclient.setState(initialState);

		GetMethod httpget = new GetMethod(homeUrl);
		httpget.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
		int result = httpclient.executeMethod(httpget);
		//log.debug("Response status code: " + result);
		String returnedPath = httpget.getPath();
		//log.debug("returnedPath " + returnedPath);
		jsessionid = returnedPath.replace("/zeprs/home.do;jsessionid=", "");

		// now login w/ credentials in the querystring
		String loginUrl = "http://" + location + "/zeprs/j_security_check" + ";" + jsessionidString + jsessionid + authString;
		//log.debug("loginUrl " + loginUrl);
		httpget = new GetMethod(loginUrl);
		httpget.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
		result = httpclient.executeMethod(httpget);
		//log.debug("Response status code: " + result);
		returnedPath = httpget.getPath();
		//log.debug("returnedPath " + returnedPath);

		// now go to the app
		String importPatientUrl = "http://" + location + url + ";" + jsessionidString + jsessionid + "?zeprsId=" + zeprsId ;
		//log.debug("importPatientUrl: " + importPatientUrl);
		httpget = new GetMethod(importPatientUrl);
		httpget.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
		result = httpclient.executeMethod(httpget);
		//log.debug("Response status code: " + result);
		returnedPath = httpget.getPath();
		//log.debug("returnedPath " + returnedPath);
		//int statuscode = httpget.getStatusCode();
		//log.debug("statuscode: " + statuscode);
		String importPath = Constants.PATIENTS_IMPORTED_PATH;
		InputStream inputStream = httpget.getResponseBodyAsStream();
		try {
			patient = (Patient) XmlUtils.getOne(inputStream);
		} catch (ConversionException e) {
			log.debug("Error Converting fields from record: " + zeprsId);
		}
		//xstream.fromXML(inputStream);
		return patient;
	}


}
