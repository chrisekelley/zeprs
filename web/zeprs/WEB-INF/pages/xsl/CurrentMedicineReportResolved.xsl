<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>CurrentMedicineReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Drug 1</th>
      <th>Drug 2</th>
      <th>Drug 3</th>
      <th>Drug 4</th>
      <th>Drug 5</th>
      <th>Drug 6</th>
      <th>Drug 7</th>
      <th>Drug 8</th>
      <th>Drug 9</th>
      <th>Drug 10</th>
      <th>Current Medicine, Other, Describe</th>
      </tr>
      <xsl:for-each select="list/Curr">
      <tr>
         <td><xsl:value-of select="drug__1__1136R"/></td>
         <td><xsl:value-of select="drug__2__1137R"/></td>
         <td><xsl:value-of select="drug__3__1138R"/></td>
         <td><xsl:value-of select="drug__4__1139R"/></td>
         <td><xsl:value-of select="drug__5__1140R"/></td>
         <td><xsl:value-of select="drug__6__1141R"/></td>
         <td><xsl:value-of select="drug__7__1142R"/></td>
         <td><xsl:value-of select="drug__8__1143R"/></td>
         <td><xsl:value-of select="drug__9__1144R"/></td>
         <td><xsl:value-of select="drug__10__1145R"/></td>
         <td><xsl:value-of select="current__medicine__1146R"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>