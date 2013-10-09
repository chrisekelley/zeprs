<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>RoutineAnteReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>EGA</th>
      <th>Fundal Height</th>
      <th>Lie</th>
      <th>Presentation</th>
      <th>Engaged</th>
      <th>Foetal Heart Rate</th>
      <th>Foetal Heart Rhythm</th>
      <th>BP: Systolic</th>
      <th>BP: Diastolic</th>
      <th>Oedema</th>
      <th>Weight</th>
      <th>Urinalysis Acetone</th>
      <th>Urinalysis Albumin</th>
      <th>Urinalysis Glucose</th>
      <th>Date of Next Appointment</th>
      <th>Problem?</th>
      </tr>
      <xsl:for-each select="list/Rout">
      <tr>
         <td><xsl:value-of select="ega__129R"/></td>
         <td><xsl:value-of select="fundal__height__232R"/></td>
         <td><xsl:value-of select="lie__313R"/></td>
         <td><xsl:value-of select="presentation__314R"/></td>
         <td><xsl:value-of select="engaged__234R"/></td>
         <td><xsl:value-of select="foetal__heart__rate__230R"/></td>
         <td><xsl:value-of select="foetal__heart__rhythm__229R"/></td>
         <td><xsl:value-of select="bp__systolic__224R"/></td>
         <td><xsl:value-of select="bp__diastolic__225R"/></td>
         <td><xsl:value-of select="oedema__231R"/></td>
         <td><xsl:value-of select="weight__228R"/></td>
         <td><xsl:value-of select="urinalysis__ace__244R"/></td>
         <td><xsl:value-of select="urinalysis__alb__242R"/></td>
         <td><xsl:value-of select="urinalysis__glu__243R"/></td>
         <td><xsl:value-of select="date__next__apptR"/></td>
         <td><xsl:value-of select="is__problemR"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>