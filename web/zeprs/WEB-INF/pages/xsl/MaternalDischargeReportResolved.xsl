<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>MaternalDischargeReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Feeding Type</th>
      <th>Bonding Well</th>
      <th>C/S</th>
      <th>Involution of Uterus</th>
      <th>Postpartum Infection</th>
      <th>Postpartum Complications</th>
      <th>Did mother receive vitamin A</th>
      <th>Did Patient receive ARV treatment?</th>
      <th>Medication</th>
      <th>Medication Other Describe:</th>
      <th>Family Planning Discussed</th>
      <th>Treatment on Discharge</th>
      <th>General Condition</th>
      <th>Comments</th>
      <th>Disposition</th>
      <th>Priority of Referral</th>
      <th>Transport</th>
      <th>Reason for Referring Postpartum Mother</th>
      <th>Reason for Referring Postpartum Mother: Other, Describe</th>
      <th>Date for Followup Visit</th>
      <th>Place of Followup Visit</th>
      <th>Autopsy Requested</th>
      <th>Autopsy Consent</th>
      </tr>
      <xsl:for-each select="list/Mate">
      <tr>
         <td><xsl:value-of select="feeding__typeR"/></td>
         <td><xsl:value-of select="bonding__well__577R"/></td>
         <td><xsl:value-of select="csR"/></td>
         <td><xsl:value-of select="involution__uterus__578R"/></td>
         <td><xsl:value-of select="postpartum__i__66R"/></td>
         <td><xsl:value-of select="postpartum__complications__584R"/></td>
         <td><xsl:value-of select="mother__receive__vit__a__585R"/></td>
         <td><xsl:value-of select="patient__received__arvR"/></td>
         <td><xsl:value-of select="medication__586R"/></td>
         <td><xsl:value-of select="medication__other__587R"/></td>
         <td><xsl:value-of select="family__planning__discussed__594R"/></td>
         <td><xsl:value-of select="treatment__on__discharge__595R"/></td>
         <td><xsl:value-of select="general__condition__260R"/></td>
         <td><xsl:value-of select="comments__maternal__discharge__597R"/></td>
         <td><xsl:value-of select="maternal__summary__dischargeR"/></td>
         <td><xsl:value-of select="priority__of__referralR"/></td>
         <td><xsl:value-of select="transportR"/></td>
         <td><xsl:value-of select="reason__for__referral__592R"/></td>
         <td><xsl:value-of select="reason__for__referral__1202R"/></td>
         <td><xsl:value-of select="date__followup__visitR"/></td>
         <td><xsl:value-of select="place__followup__visitR"/></td>
         <td><xsl:value-of select="autopsy__requested__598R"/></td>
         <td><xsl:value-of select="autopsy__consent__599R"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>