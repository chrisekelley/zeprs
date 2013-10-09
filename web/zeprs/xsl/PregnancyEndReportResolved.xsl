<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>PregnancyEndReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Pregnancy has ended</th>
      <th>Date Pregnancy Ended</th>
      <th>Comments</th>
      </tr>
      <xsl:for-each select="list/Preg">
      <tr>
         <td><xsl:value-of select="is__terminated__pregnancyR"/></td>
         <td><xsl:value-of select="date__pregnancy__endedR"/></td>
         <td><xsl:value-of select="comments__preg__conclusion__1368R"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>