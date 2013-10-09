<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>RprReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Date of RPR Screen</th>
      <th>RPR Result</th>
      <th>RPR Treatment Date</th>
      <th>RPR Drug</th>
      <th>RPR Dosage</th>
      <th>RPR Comments</th>
      </tr>
      <xsl:for-each select="list/RprR">
      <tr>
         <td><xsl:value-of select="rpr__dateR"/></td>
         <td><xsl:value-of select="rpr__resultR"/></td>
         <td><xsl:value-of select="rpr__treatment__dateR"/></td>
         <td><xsl:value-of select="rpr__drugR"/></td>
         <td><xsl:value-of select="rpr__dosageR"/></td>
         <td><xsl:value-of select="rpr__commentsR"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>