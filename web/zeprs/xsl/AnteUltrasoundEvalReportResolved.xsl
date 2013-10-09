<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>AnteUltrasoundEvalReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>exam_sequence_number</th>
      <th>Date of Ultrasound</th>
      <th>Placenta Maturity Grading</th>
      <th>Placenta Localisation: Lower Segment</th>
      <th>Liquor Volume</th>
      <th>Gest. Age from Ultrasound</th>
      <th>General Impression</th>
      <th>General Impression, Abnormality, Describe</th>
      <th>Other findings on Ultrasound, describe</th>
      </tr>
      <xsl:for-each select="list/Ante">
      <tr>
         <td><xsl:value-of select="exam__sequence__numberR"/></td>
         <td><xsl:value-of select="date__of__ultrasound__1212R"/></td>
         <td><xsl:value-of select="placenta__maturity__958R"/></td>
         <td><xsl:value-of select="placenta__loc__lower__960R"/></td>
         <td><xsl:value-of select="liquor__volume__961R"/></td>
         <td><xsl:value-of select="ega__ultrasoundR"/></td>
         <td><xsl:value-of select="general__impression__966R"/></td>
         <td><xsl:value-of select="general__impression__abnormal__967R"/></td>
         <td><xsl:value-of select="other__findings__ultrasound__1197R"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>