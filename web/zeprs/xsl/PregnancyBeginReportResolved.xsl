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
         <td><xsl:value-of select="date__pregnancy__beganR"/></td>
         <td><xsl:value-of select="comments__new__preg__1371R"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>