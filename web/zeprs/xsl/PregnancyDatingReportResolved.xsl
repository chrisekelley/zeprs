<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>PregnancyDatingReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Planned Pregnancy</th>
      <th>If Unplanned, Contraception Practiced?</th>
      <th>Contraceptive Choice</th>
      <th>Contraceptive, Other, Describe</th>
      <th>Reliability of LMP</th>
      <th>LMP</th>
      <th>EDD</th>
      <th>EGA</th>
      <th>Quickening</th>
      <th>Menstrual History</th>
      <th>Cycle in Days</th>
      <th>Uterus, Size in Days</th>
      <th>Dating Method</th>
      </tr>
      <xsl:for-each select="list/Preg">
      <tr>
         <td><xsl:value-of select="planned__preg__135R"/></td>
         <td><xsl:value-of select="contracept__practiced__136R"/></td>
         <td><xsl:value-of select="contraceptive__choice__137R"/></td>
         <td><xsl:value-of select="contraceptive__other__138R"/></td>
         <td><xsl:value-of select="lmp__reliability__126R"/></td>
         <td><xsl:value-of select="lmp__127R"/></td>
         <td><xsl:value-of select="edd__128R"/></td>
         <td><xsl:value-of select="ega__129R"/></td>
         <td><xsl:value-of select="quickening__130R"/></td>
         <td><xsl:value-of select="menstrual__history__131R"/></td>
         <td><xsl:value-of select="cycle__in__days__132R"/></td>
         <td><xsl:value-of select="uterus__size__in__days__188R"/></td>
         <td><xsl:value-of select="dating__methodR"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>