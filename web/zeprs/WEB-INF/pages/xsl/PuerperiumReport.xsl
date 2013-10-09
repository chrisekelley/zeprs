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
         <td><xsl:value-of select="time_of_exam"/></td>
         <td><xsl:value-of select="bp_systolic_224"/></td>
         <td><xsl:value-of select="bp_diastolic_225"/></td>
         <td><xsl:value-of select="temperature_266"/></td>
         <td><xsl:value-of select="perineum_intact"/></td>
         <td><xsl:value-of select="perineum_swollen"/></td>
         <td><xsl:value-of select="perineum_hematoma"/></td>
         <td><xsl:value-of select="lochia_579"/></td>
         <td><xsl:value-of select="bowels_639"/></td>
         <td><xsl:value-of select="micturition_641"/></td>
         <td><xsl:value-of select="breasts_166"/></td>
         <td><xsl:value-of select="wound_643"/></td>
         <td><xsl:value-of select="hb_235"/></td>
         <td><xsl:value-of select="anti_d_given_621"/></td>
         <td><xsl:value-of select="general_condition_260"/></td>
         <td><xsl:value-of select="bleeding"/></td>
         <td><xsl:value-of select="pallor_193"/></td>
         <td><xsl:value-of select="uterus_187"/></td>
         <td><xsl:value-of select="bladder_emptied_437"/></td>
         <td><xsl:value-of select="is_problem"/></td>
         <td><xsl:value-of select="postpartum_complications_584"/></td>
         <td><xsl:value-of select="disposition"/></td>
         <td><xsl:value-of select="comments"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>