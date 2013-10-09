<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>PartographStatusReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Start Partograph?</th>
      <th>Finish Partograph</th>
      <th>Time of Birth</th>
      </tr>
      <xsl:for-each select="list/Part">
      <tr>
         <td><xsl:value-of select="startedR"/></td>
         <td><xsl:value-of select="date__completedR"/></td>
         <td><xsl:value-of select="time__of__birthR"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>