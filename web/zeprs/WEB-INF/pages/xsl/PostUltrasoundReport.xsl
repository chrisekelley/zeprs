<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>PostUltrasoundReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Date of Ultrasound</th>
      <th>Diagnosis (General Impression)</th>
      <th>Describe Abnormalities/Comment on Ultrasound</th>
      </tr>
      <xsl:for-each select="list/Post">
      <tr>
         <td><xsl:value-of select="date_of_ultrasound_1212"/></td>
         <td><xsl:value-of select="diagnosis_ultrasound"/></td>
         <td><xsl:value-of select="describe_abnormalities"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>