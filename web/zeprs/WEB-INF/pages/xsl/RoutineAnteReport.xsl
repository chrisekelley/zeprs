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
         <td><xsl:value-of select="ega_129"/></td>
         <td><xsl:value-of select="fundal_height_232"/></td>
         <td><xsl:value-of select="lie_313"/></td>
         <td><xsl:value-of select="presentation_314"/></td>
         <td><xsl:value-of select="engaged_234"/></td>
         <td><xsl:value-of select="foetal_heart_rate_230"/></td>
         <td><xsl:value-of select="foetal_heart_rhythm_229"/></td>
         <td><xsl:value-of select="bp_systolic_224"/></td>
         <td><xsl:value-of select="bp_diastolic_225"/></td>
         <td><xsl:value-of select="oedema_231"/></td>
         <td><xsl:value-of select="weight_228"/></td>
         <td><xsl:value-of select="urinalysis_ace_244"/></td>
         <td><xsl:value-of select="urinalysis_alb_242"/></td>
         <td><xsl:value-of select="urinalysis_glu_243"/></td>
         <td><xsl:value-of select="date_next_appt"/></td>
         <td><xsl:value-of select="is_problem"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>