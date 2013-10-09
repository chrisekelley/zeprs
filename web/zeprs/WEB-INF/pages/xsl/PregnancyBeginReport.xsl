<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>PregnancyBeginReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Date Pregnancy Began</th>
      <th>Comments</th>
      </tr>
      <xsl:for-each select="list/Preg">
      <tr>
         <td><xsl:value-of select="date_pregnancy_began"/></td>
         <td><xsl:value-of select="comments_new_preg_1371"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>