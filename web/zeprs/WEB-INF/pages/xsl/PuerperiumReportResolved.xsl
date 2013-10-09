<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>PuerperiumReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Time</th>
      <th>BP: Systolic</th>
      <th>BP: Diastolic</th>
      <th>Temperature</th>
      <th>Perineum: Intact</th>
      <th>Perineum: Swollen</th>
      <th>Perineum: Hematoma</th>
      <th>Lochia</th>
      <th>Bowels</th>
      <th>Micturition</th>
      <th>Breasts</th>
      <th>Wound</th>
      <th>Hb</th>
      <th>Anti-D Given</th>
      <th>General Condition</th>
      <th>Bleeding</th>
      <th>Pallor (Anemia)</th>
      <th>Uterus</th>
      <th>Bladder Emptied</th>
      <th>Problem?</th>
      <th>Postpartum Complications</th>
      <th>Disposition</th>
      <th>Comments</th>
      </tr>
      <xsl:for-each select="list/Puer">
      <tr>
         <td><xsl:value-of select="time__of__examR"/></td>
         <td><xsl:value-of select="bp__systolic__224R"/></td>
         <td><xsl:value-of select="bp__diastolic__225R"/></td>
         <td><xsl:value-of select="temperature__266R"/></td>
         <td><xsl:value-of select="perineum__intactR"/></td>
         <td><xsl:value-of select="perineum__swollenR"/></td>
         <td><xsl:value-of select="perineum__hematomaR"/></td>
         <td><xsl:value-of select="lochia__579R"/></td>
         <td><xsl:value-of select="bowels__639R"/></td>
         <td><xsl:value-of select="micturition__641R"/></td>
         <td><xsl:value-of select="breasts__166R"/></td>
         <td><xsl:value-of select="wound__643R"/></td>
         <td><xsl:value-of select="hb__235R"/></td>
         <td><xsl:value-of select="anti__d__given__621R"/></td>
         <td><xsl:value-of select="general__condition__260R"/></td>
         <td><xsl:value-of select="bleedingR"/></td>
         <td><xsl:value-of select="pallor__193R"/></td>
         <td><xsl:value-of select="uterus__187R"/></td>
         <td><xsl:value-of select="bladder__emptied__437R"/></td>
         <td><xsl:value-of select="is__problemR"/></td>
         <td><xsl:value-of select="postpartum__complications__584R"/></td>
         <td><xsl:value-of select="dispositionR"/></td>
         <td><xsl:value-of select="commentsR"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>