<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>ArvRegimenReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Date of Regimen Visit</th>
      <th>Received Regimen</th>
      <th>Regimen</th>
      <th># Days of treatment</th>
      <th>Phase of Pregnancy</th>
      </tr>
      <xsl:for-each select="list/ArvR">
      <tr>
         <td><xsl:value-of select="regimen__visit__dateR"/></td>
         <td><xsl:value-of select="receivedRegimenR"/></td>
         <td><xsl:value-of select="regimenR"/></td>
         <td><xsl:value-of select="days__of__treatmentR"/></td>
         <td><xsl:value-of select="phaseOfPregnancyR"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>