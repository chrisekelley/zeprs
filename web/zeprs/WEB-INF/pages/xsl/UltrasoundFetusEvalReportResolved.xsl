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
         <td><xsl:value-of select="exam__sequence__numberR"/></td>
         <td><xsl:value-of select="sequence__number__fetusR"/></td>
         <td><xsl:value-of select="condition__of__foetus__964R"/></td>
         <td><xsl:value-of select="lie__313R"/></td>
         <td><xsl:value-of select="presentation__314R"/></td>
         <td><xsl:value-of select="presentation__otherR"/></td>
         <td><xsl:value-of select="biparietal__diameter__955R"/></td>
         <td><xsl:value-of select="femur__length__956R"/></td>
         <td><xsl:value-of select="fetal__abdomi__957R"/></td>
         <td><xsl:value-of select="weightR"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>