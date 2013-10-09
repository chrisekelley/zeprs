There is a Tomcat 7 bundle available [Zcore_Tomcat 7.0.zip](http://www.ictedge.org/downloads#31August2011) 
that has been lightly tested in Windows 7/32 bit. 
If you would like to test Zcore on 64 bit Windows, install the 64 bit version of Tomcat 7 and replace the bin directory with the 64 bit version.

The following notes are based on the author's modifications that were necessary to get Zcore running in Tomcat7.

If you are trying to configure ZEPRS on Tomcat 7, please skip to the Tomcat 7 Configuration section

## Security Extensions
Be sure to install security extensions at 
jre6 - [http://www.oracle.com/technetwork/java/javase/downloads/jce-6-download-429243.html]
jre7 - [http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html]

install to C:\Program Files\Java\jdk1.7.0\jre\lib\security

## Installing Zcore_Tomcat 7.0.zip

Copy C:\Program Files\Zcore\Zcore-0.95c-noJRE\webapps\zcore and archive to tomcat webapps dir

Copy C:\Program Files\Zcore\Zcore-0.95c-noJRE\conf\Catalina\localhost\archive.xml to 
C:\Program Files\Apache Software Foundation\Tomcat 7.0\conf\Catalina\localhost

Copy the following files from C:\Program Files\Zcore\Zcore-0.95c-noJRE\lib to C:\Program Files\Apache Software Foundation\Tomcat 7.0\lib:
- all commons-*.jar
- ecj-3.7.jar
- all derby*.jar
- log4j-1.2.13.jar
- log4j.properties
- org.rti.tools.dbcp-2010.09.20.1.jar
- tomcat-jdbc.jar
- tomcat-utiljar
- zcore-*.jar - except for zcore-tomcat-realm.jar - see next line.

Also add the new zcore-tomcat-realm-tomcat7.jar to C:\Program Files\Apache Software Foundation\Tomcat 7.0\lib:

Copy the following directories to C:\Program Files\Apache Software Foundation\Tomcat 7.0:
 - C:\Program Files\Zcore\Zcore-0.95c-noJRE\resources
 - C:\Program Files\Zcore\Zcore-0.95c-noJRE\databases 
 - C:\Program Files\Zcore\Zcore-0.95c-noJRE\backup 
 - C:\Program Files\Zcore\Zcore-0.95c-noJRE\updates

## Tomcat 7 Configuration
 
Tomcat 7 has changed how it processes EL syntax in JSP's. You must change some of the Tomcat 7 system properties to enable Zcore to work. (For reference, here is a page with the Tomcat 7 properties. http://tomcat.apache.org/tomcat-7.0-doc/config/systemprops.html )

Add the following lines to C:\Program Files\Apache Software Foundation\Tomcat 7.0\conf\catalina.properties:
# zcore old jsp conpatability
org.apache.jasper.compiler.Parser.STRICT_QUOTE_ESCAPING=false
org.apache.jasper.compiler.Parser.STRICT_WHITESPACE=false
org.apache.el.parser.SKIP_IDENTIFIER_CHECK=true

Tomcat has changed how it processes logins. Open C:\Program Files\Apache Software Foundation\Tomcat 7.0\webapps\zcore\META-INF\context.xml and change cookies="false" to cookie ="true". Here is the corrected line:

		<Context path="zcore" docBase="zcore" debug="0" reloadable="false" crossContext="true" privileged="true" useNaming="true" cookies="true">

If you intend to view the app in Google Chrome, you need to ensure that the mozilla css is read for Chrome. Add the following to all of the templates at C:\Program Files\Apache Software Foundation\Tomcat 7.0\webapps\zcore\WEB-INF\templates\zcore:
Change 

		if (browser.isGecko}

to

		if (browser.isGecko || browser.isSafari)

Access the app from http://localhost:8080/zcore/ - The original Zcore app runs on port 8088. This can be changed if necessary.