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
         <td><xsl:value-of select="exam_sequence_number"/></td>
         <td><xsl:value-of select="date_of_ultrasound_1212"/></td>
         <td><xsl:value-of select="placenta_maturity_958"/></td>
         <td><xsl:value-of select="placenta_loc_lower_960"/></td>
         <td><xsl:value-of select="liquor_volume_961"/></td>
         <td><xsl:value-of select="ega_ultrasound"/></td>
         <td><xsl:value-of select="general_impression_966"/></td>
         <td><xsl:value-of select="general_impression_abnormal_967"/></td>
         <td><xsl:value-of select="other_findings_ultrasound_1197"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>