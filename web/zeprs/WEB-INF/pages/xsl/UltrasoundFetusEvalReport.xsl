<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>UltrasoundFetusEvalReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>exam_sequence_number</th>
      <th>Sequence Number of Fetus</th>
      <th>Condition of Foetus</th>
      <th>Lie</th>
      <th>Presentation</th>
      <th>Presentation: Other, Describe</th>
      <th>Biparietal Diameter BPD</th>
      <th>Femur Length</th>
      <th>Fetal Abdominal Circumference</th>
      <th>Weight (Fetus)</th>
      </tr>
      <xsl:for-each select="list/Ultr">
      <tr>
         <td><xsl:value-of select="exam_sequence_number"/></td>
         <td><xsl:value-of select="sequence_number_fetus"/></td>
         <td><xsl:value-of select="condition_of_foetus_964"/></td>
         <td><xsl:value-of select="lie_313"/></td>
         <td><xsl:value-of select="presentation_314"/></td>
         <td><xsl:value-of select="presentation_other"/></td>
         <td><xsl:value-of select="biparietal_diameter_955"/></td>
         <td><xsl:value-of select="femur_length_956"/></td>
         <td><xsl:value-of select="fetal_abdomi_957"/></td>
         <td><xsl:value-of select="weight"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>