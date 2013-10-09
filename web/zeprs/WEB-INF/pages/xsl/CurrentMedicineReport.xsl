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
         <td><xsl:value-of select="drug_1_1136"/></td>
         <td><xsl:value-of select="drug_2_1137"/></td>
         <td><xsl:value-of select="drug_3_1138"/></td>
         <td><xsl:value-of select="drug_4_1139"/></td>
         <td><xsl:value-of select="drug_5_1140"/></td>
         <td><xsl:value-of select="drug_6_1141"/></td>
         <td><xsl:value-of select="drug_7_1142"/></td>
         <td><xsl:value-of select="drug_8_1143"/></td>
         <td><xsl:value-of select="drug_9_1144"/></td>
         <td><xsl:value-of select="drug_10_1145"/></td>
         <td><xsl:value-of select="current_medicine_1146"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>