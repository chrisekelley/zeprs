<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>UthNeonatalRecordReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Time of Admission</th>
      <th>Date of Birth</th>
      <th>Time of Birth</th>
      <th>Infant Age</th>
      <th>Sex</th>
      <th>Age of Mother</th>
      <th>Age of Father</th>
      <th>Parity</th>
      <th>Mother's LMP</th>
      <th>EGA</th>
      <th>ABO Blood Group (Mother)</th>
      <th>Rhesus (Mother)</th>
      <th>VDRL (mother)</th>
      <th>Attending ANC</th>
      <th>Occupation</th>
      <th>Intrapartum History</th>
      <th>Mode of Delivery</th>
      <th>Indication for C/S  Forceps/Vacuum</th>
      <th>Place of Delivery</th>
      <th>Place of Delivery, Other, describe</th>
      <th>Rupture of Membranes Date</th>
      <th>Rupture of Membranes Time</th>
      <th>Duration of Labour 1st stage</th>
      <th>Duration of Labour 2nd stage</th>
      <th>Placenta Type</th>
      <th>Weight of Placenta</th>
      <th>Num. Fetuses this pregnancy</th>
      <th>If Multiple Birth</th>
      <th>Maternal Risk Factors</th>
      <th>Maternal Risk Factor 1</th>
      <th>Maternal Risk Factor 2</th>
      <th>Maternal Risk Factor 3</th>
      <th>Maternal Risk Factor 4</th>
      <th>Maternal Risk Factor 5</th>
      <th>Maternal Risk Factors, Other Describe:</th>
      </tr>
      <xsl:for-each select="list/UthN">
      <tr>
         <td><xsl:value-of select="time__of__admission__727R"/></td>
         <td><xsl:value-of select="date__of__birth__728R"/></td>
         <td><xsl:value-of select="time__of__birth__1514R"/></td>
         <td><xsl:value-of select="infant__age__732R"/></td>
         <td><xsl:value-of select="sex__490R"/></td>
         <td><xsl:value-of select="age__of__mother__730R"/></td>
         <td><xsl:value-of select="age__of__father__731R"/></td>
         <td><xsl:value-of select="parity__734R"/></td>
         <td><xsl:value-of select="mothers__lmp__735R"/></td>
         <td><xsl:value-of select="ega__129R"/></td>
         <td><xsl:value-of select="abo__blood__group__mother__738R"/></td>
         <td><xsl:value-of select="rhesus__mother__739R"/></td>
         <td><xsl:value-of select="vdrl__mother__740R"/></td>
         <td><xsl:value-of select="attending__anc__741R"/></td>
         <td><xsl:value-of select="occupation__12R"/></td>
         <td><xsl:value-of select="intrapartum__history__743R"/></td>
         <td><xsl:value-of select="mode__of__delivery__447R"/></td>
         <td><xsl:value-of select="indication__CS__forcepts__60R"/></td>
         <td><xsl:value-of select="place__of__delivery__50R"/></td>
         <td><xsl:value-of select="place__delivery__otherR"/></td>
         <td><xsl:value-of select="rupture__of__membranes__date__328R"/></td>
         <td><xsl:value-of select="rupture__of__membranes__time__329R"/></td>
         <td><xsl:value-of select="duration__of__labour__1st__748R"/></td>
         <td><xsl:value-of select="duration__of__labour__2nd__749R"/></td>
         <td><xsl:value-of select="placenta__type__440R"/></td>
         <td><xsl:value-of select="weight__of__placenta__441R"/></td>
         <td><xsl:value-of select="number__of__fetuses__63R"/></td>
         <td><xsl:value-of select="if__multiple__753R"/></td>
         <td><xsl:value-of select="maternal__risk__factors__754R"/></td>
         <td><xsl:value-of select="maternal__risk__factor__1R"/></td>
         <td><xsl:value-of select="maternal__risk__factor__2R"/></td>
         <td><xsl:value-of select="maternal__risk__factor__3R"/></td>
         <td><xsl:value-of select="maternal__risk__factor__4R"/></td>
         <td><xsl:value-of select="maternal__risk__factor__5R"/></td>
         <td><xsl:value-of select="maternal__risk__other__755R"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>