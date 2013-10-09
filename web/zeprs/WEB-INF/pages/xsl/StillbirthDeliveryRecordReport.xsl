<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>StillbirthDeliveryRecordReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Date of Birth (infant)</th>
      <th>Time of Birth</th>
      <th>Weight at Stillbirth</th>
      <th>Estimate?</th>
      <th>EGA</th>
      <th>Last Reported Foetal Movement</th>
      <th>Date of Last Reported Foetal Movement</th>
      <th>Time of Day of Last Reported Foetal Movement</th>
      <th>Last Documented Foetal Heart Tone</th>
      <th>Date of Last Documented Foetal Heart Tone</th>
      <th>Time of Last Documented Foetal Heart Tone</th>
      <th>Foetal Death Time</th>
      <th>Foetal Death Estimated Date if Known</th>
      <th>Foetal Death Estimated Time if Known</th>
      <th>Type of Still Birth</th>
      <th>Physical Features</th>
      <th>Upper Extremities</th>
      <th>Upper Extremities, Other Describe</th>
      <th>Lower Extremities</th>
      <th>Lower Extremities, Other Describe</th>
      <th>Anomalies: CNS (Central Nervous System)</th>
      <th>Anomalies: CNS (Central Nervous System), Other, Describe</th>
      <th>Anomalies: Chromosomal</th>
      <th>Anomalies: Chromosomal, Other, Describe</th>
      <th>Anomalies: Genitourinary</th>
      <th>Anomalies: Genitourinary, Other, Describe</th>
      <th>Anomalies: Other</th>
      <th>Anomalies: Other, describe</th>
      <th>Injuries/Trauma</th>
      <th>Number of Vessels</th>
      <th>Fetal Cord Complication</th>
      <th>Cord Length Estimate</th>
      <th>Cord Prolapsed</th>
      <th>Velamentous Insertion</th>
      <th>Placenta Appearance</th>
      <th>Placenta Other Describe:</th>
      <th>Amniotic Fluid Volume</th>
      <th>Meconium Staining</th>
      <th>Foul Smell</th>
      <th>Provisional Cause of Death</th>
      <th>Provisional Cause of Death, Other, Describe</th>
      <th>Autopsy Requested</th>
      <th>Autopsy Consent</th>
      <th>Additional findings</th>
      </tr>
      <xsl:for-each select="list/Stil">
      <tr>
         <td><xsl:value-of select="date_of_birt_844"/></td>
         <td><xsl:value-of select="time_of_birth_1514"/></td>
         <td><xsl:value-of select="weight_at_stillbirth_846"/></td>
         <td><xsl:value-of select="estimate_weight"/></td>
         <td><xsl:value-of select="ega_129"/></td>
         <td><xsl:value-of select="last_reported_foetal_movement_849"/></td>
         <td><xsl:value-of select="date_of_lrfm_850"/></td>
         <td><xsl:value-of select="time_of_lrfm_851"/></td>
         <td><xsl:value-of select="last_foetal_heart_tone_852"/></td>
         <td><xsl:value-of select="date_last_foetal_heart_tone_853"/></td>
         <td><xsl:value-of select="time_last_foetal_heart_tone_854"/></td>
         <td><xsl:value-of select="time_foetal_death_855"/></td>
         <td><xsl:value-of select="est_date_foetal_death_856"/></td>
         <td><xsl:value-of select="est_time_foetal_death_857"/></td>
         <td><xsl:value-of select="type_of_still_birth_858"/></td>
         <td><xsl:value-of select="physical_features_859"/></td>
         <td><xsl:value-of select="upper_extrem_538"/></td>
         <td><xsl:value-of select="upper_extrem_desc_539"/></td>
         <td><xsl:value-of select="lower_extrem_541"/></td>
         <td><xsl:value-of select="lower_extrem_desc_542"/></td>
         <td><xsl:value-of select="anomalies_cns_543"/></td>
         <td><xsl:value-of select="anomalies_cns_desc_544"/></td>
         <td><xsl:value-of select="anomalies_chromo_545"/></td>
         <td><xsl:value-of select="anomalies_chromo_desc_546"/></td>
         <td><xsl:value-of select="anomalies_genitour_549"/></td>
         <td><xsl:value-of select="anomalies_genitour_desc_550"/></td>
         <td><xsl:value-of select="anomalies_other_551"/></td>
         <td><xsl:value-of select="anomalies_ohter_1189"/></td>
         <td><xsl:value-of select="injuries_trauma_807"/></td>
         <td><xsl:value-of select="number_of_vessels_874"/></td>
         <td><xsl:value-of select="cord_complic_789"/></td>
         <td><xsl:value-of select="cord_length_est_876"/></td>
         <td><xsl:value-of select="cord_prolapsed_877"/></td>
         <td><xsl:value-of select="velamentous_insert_878"/></td>
         <td><xsl:value-of select="placenta_appear_879"/></td>
         <td><xsl:value-of select="placenta_other_880"/></td>
         <td><xsl:value-of select="amniotic_fluid_vol_881"/></td>
         <td><xsl:value-of select="meconium_staining_882"/></td>
         <td><xsl:value-of select="foul_smell_883"/></td>
         <td><xsl:value-of select="provisional_cause_death_886"/></td>
         <td><xsl:value-of select="provisional_cause_death_other_887"/></td>
         <td><xsl:value-of select="autopsy_requested_598"/></td>
         <td><xsl:value-of select="autopsy_consent_599"/></td>
         <td><xsl:value-of select="additional_findings"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>