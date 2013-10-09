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
         <td><xsl:value-of select="feeding_type"/></td>
         <td><xsl:value-of select="bonding_well_577"/></td>
         <td><xsl:value-of select="cs"/></td>
         <td><xsl:value-of select="involution_uterus_578"/></td>
         <td><xsl:value-of select="postpartum_i_66"/></td>
         <td><xsl:value-of select="postpartum_complications_584"/></td>
         <td><xsl:value-of select="mother_receive_vit_a_585"/></td>
         <td><xsl:value-of select="patient_received_arv"/></td>
         <td><xsl:value-of select="medication_586"/></td>
         <td><xsl:value-of select="medication_other_587"/></td>
         <td><xsl:value-of select="family_planning_discussed_594"/></td>
         <td><xsl:value-of select="treatment_on_discharge_595"/></td>
         <td><xsl:value-of select="general_condition_260"/></td>
         <td><xsl:value-of select="comments_maternal_discharge_597"/></td>
         <td><xsl:value-of select="maternal_summary_discharge"/></td>
         <td><xsl:value-of select="priority_of_referral"/></td>
         <td><xsl:value-of select="transport"/></td>
         <td><xsl:value-of select="reason_for_referral_592"/></td>
         <td><xsl:value-of select="reason_for_referral_1202"/></td>
         <td><xsl:value-of select="date_followup_visit"/></td>
         <td><xsl:value-of select="place_followup_visit"/></td>
         <td><xsl:value-of select="autopsy_requested_598"/></td>
         <td><xsl:value-of select="autopsy_consent_599"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>