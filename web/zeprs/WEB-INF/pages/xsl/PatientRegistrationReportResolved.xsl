<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>PatientRegistrationReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Surname</th>
      <th>Forenames</th>
      <th>NRC No.</th>
      <th>Safe Motherhood Number</th>
      <th>District</th>
      <th>Site</th>
      <th>Patient ID number</th>
      <th>Age at First Attendance</th>
      <th>Birth Date</th>
      <th>Education Status</th>
      <th>Residential Address</th>
      <th>Community or Compound</th>
      <th>Date of Residence Change</th>
      <th>Marital Status</th>
      <th>Occupation</th>
      <th>Occupation (Other, describe)</th>
      <th>Nearby Place of Worship</th>
      <th>Religion</th>
      <th>Religion (Other, describe)</th>
      <th>Surname of Patient's Father</th>
      <th>Forenames of Patient's Father</th>
      <th>Surname of Husband/Partner</th>
      <th>Forenames of Husband/Partner</th>
      <th>Occupation of Husband/Partner</th>
      <th>Tel. No. of Husband/Partner</th>
      <th>Surname of Guardian</th>
      <th>Forenames of Guardian</th>
      <th>Surname of Emergency Contact If Different From Above</th>
      <th>Forenames of Emergency Contact</th>
      <th>Address of Emergency Contact</th>
      <th>Tel. No. of Emergency Contact</th>
      </tr>
      <xsl:for-each select="list/Pati">
      <tr>
         <td><xsl:value-of select="surname__6R"/></td>
         <td><xsl:value-of select="forenames__7R"/></td>
         <td><xsl:value-of select="nrc__no__9R"/></td>
         <td><xsl:value-of select="obstetric__record__number__1134R"/></td>
         <td><xsl:value-of select="district__pat__13R"/></td>
         <td><xsl:value-of select="new__patient__site__idR"/></td>
         <td><xsl:value-of select="patient__id__numberR"/></td>
         <td><xsl:value-of select="age__at__first__attendence__1135R"/></td>
         <td><xsl:value-of select="birth__date__17R"/></td>
         <td><xsl:value-of select="education__st__11R"/></td>
         <td><xsl:value-of select="residential__19R"/></td>
         <td><xsl:value-of select="residential__20R"/></td>
         <td><xsl:value-of select="date__of__resi__21R"/></td>
         <td><xsl:value-of select="marital__stat__10R"/></td>
         <td><xsl:value-of select="occupation__12R"/></td>
         <td><xsl:value-of select="occupation__otherR"/></td>
         <td><xsl:value-of select="nearby__place__worship__39R"/></td>
         <td><xsl:value-of select="religion__1239R"/></td>
         <td><xsl:value-of select="religion__other__1240R"/></td>
         <td><xsl:value-of select="surname__p__father__24R"/></td>
         <td><xsl:value-of select="forenames__p__father__25R"/></td>
         <td><xsl:value-of select="surname__husband__26R"/></td>
         <td><xsl:value-of select="forenames__husband__27R"/></td>
         <td><xsl:value-of select="occupation__husband__28R"/></td>
         <td><xsl:value-of select="tel__no__husband__32R"/></td>
         <td><xsl:value-of select="surname__guardian__33R"/></td>
         <td><xsl:value-of select="forenames__guardian__34R"/></td>
         <td><xsl:value-of select="surname__emerg__contact__35R"/></td>
         <td><xsl:value-of select="forenames__emerg__contact__36R"/></td>
         <td><xsl:value-of select="address__emerg__contact__37R"/></td>
         <td><xsl:value-of select="tel__no__emerg__contact__38R"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>