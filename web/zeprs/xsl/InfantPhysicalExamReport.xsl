<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>InfantPhysicalExamReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Skull</th>
      <th>Eyes</th>
      <th>Eyes, Other, Describe</th>
      <th>Ears</th>
      <th>Ears, Other, Describe</th>
      <th>Mouth</th>
      <th>Mouth, Other, Describe</th>
      <th>Sucking</th>
      <th>Neck</th>
      <th>Neck Other Describe</th>
      <th>Breasts</th>
      <th>Chest</th>
      <th>Respiratory Effort</th>
      <th>Respiratory Effort, Other, Describe</th>
      <th>Abdomen - Liver</th>
      <th>Sole Creases</th>
      <th>Genitalia</th>
      <th>Genitalia, Other, Describe</th>
      <th>Spleen</th>
      <th>Kidney</th>
      <th>Fetal Cord Complication</th>
      <th>No. of Arteries</th>
      <th>Neonatal reflex - Grasp</th>
      <th>Symmetrical Moro Reaction</th>
      <th>Upper Extremities</th>
      <th>Upper Extremities, Other Describe</th>
      <th>Ortolani Sign</th>
      <th>Lower Extremities</th>
      <th>Lower Extremities, Other Describe</th>
      <th>Anomalies: CNS (Central Nervous System)</th>
      <th>Anomalies: CNS (Central Nervous System), Other, Describe</th>
      <th>Anomalies: Chromosomal</th>
      <th>Anomalies: Chromosomal, Other, Describe</th>
      <th>Anomalies: Cardiovascular</th>
      <th>Anomalies: Cardiovascular, Other Describe</th>
      <th>Anomalies: Genitourinary</th>
      <th>Anomalies: Genitourinary, Other, Describe</th>
      <th>Anomalies: Other</th>
      <th>Anomalies: Other, describe</th>
      <th>Injuries/Trauma</th>
      <th>Clinical age</th>
      <th>Hypoxic Ischaemic Encephalopathy (HIE)</th>
      <th>HIE Irritability</th>
      <th>HIE Hypontonia</th>
      <th>HIE Feeding</th>
      <th>HIE Seizures</th>
      <th>HIE Total Score</th>
      <th>Weight on Admission</th>
      <th>Temperature</th>
      <th>Head Circumference</th>
      <th>Pallor (Anemia)</th>
      <th>Cyanosis</th>
      <th>Jaundice</th>
      <th>Heart Rate</th>
      <th>Birth Weight</th>
      <th>Reason for Admission</th>
      <th>Reason for Admission, Other, Describe</th>
      </tr>
      <xsl:for-each select="list/Infa">
      <tr>
         <td><xsl:value-of select="skull_771"/></td>
         <td><xsl:value-of select="eyes_523"/></td>
         <td><xsl:value-of select="eyes_other_524"/></td>
         <td><xsl:value-of select="ears_686"/></td>
         <td><xsl:value-of select="ears_other_687"/></td>
         <td><xsl:value-of select="mouth_526"/></td>
         <td><xsl:value-of select="mouth_other_527"/></td>
         <td><xsl:value-of select="sucking_528"/></td>
         <td><xsl:value-of select="neck_690"/></td>
         <td><xsl:value-of select="neck_other_d_691"/></td>
         <td><xsl:value-of select="breasts_infant_780"/></td>
         <td><xsl:value-of select="chest_infant_781"/></td>
         <td><xsl:value-of select="respiratory_effort_782"/></td>
         <td><xsl:value-of select="respiratory_effort_other_783"/></td>
         <td><xsl:value-of select="abdomen_liver_784"/></td>
         <td><xsl:value-of select="sole_creases_785"/></td>
         <td><xsl:value-of select="genitalia_529"/></td>
         <td><xsl:value-of select="genitalia_other_697"/></td>
         <td><xsl:value-of select="spleen_infant_787"/></td>
         <td><xsl:value-of select="kidney_infant_788"/></td>
         <td><xsl:value-of select="cord_complic_789"/></td>
         <td><xsl:value-of select="no_of_arteries_790"/></td>
         <td><xsl:value-of select="neonatal_reflex_791"/></td>
         <td><xsl:value-of select="symmetrical_moro_522"/></td>
         <td><xsl:value-of select="upper_extrem_538"/></td>
         <td><xsl:value-of select="upper_extrem_desc_539"/></td>
         <td><xsl:value-of select="ortolani_sign_540"/></td>
         <td><xsl:value-of select="lower_extrem_541"/></td>
         <td><xsl:value-of select="lower_extrem_desc_542"/></td>
         <td><xsl:value-of select="anomalies_cns_543"/></td>
         <td><xsl:value-of select="anomalies_cns_desc_544"/></td>
         <td><xsl:value-of select="anomalies_chromo_545"/></td>
         <td><xsl:value-of select="anomalies_chromo_desc_546"/></td>
         <td><xsl:value-of select="anomalies_cardio_547"/></td>
         <td><xsl:value-of select="anomalies_cardio_desc_548"/></td>
         <td><xsl:value-of select="anomalies_genitour_549"/></td>
         <td><xsl:value-of select="anomalies_genitour_desc_550"/></td>
         <td><xsl:value-of select="anomalies_other_551"/></td>
         <td><xsl:value-of select="anomalies_ohter_1189"/></td>
         <td><xsl:value-of select="injuries_trauma_807"/></td>
         <td><xsl:value-of select="clinical_age_808"/></td>
         <td><xsl:value-of select="hypoxic_isch_hie_809"/></td>
         <td><xsl:value-of select="hie_irritabi_810"/></td>
         <td><xsl:value-of select="hie_hyponton_811"/></td>
         <td><xsl:value-of select="hie_feeding_812"/></td>
         <td><xsl:value-of select="hie_seizures_813"/></td>
         <td><xsl:value-of select="hie_total_sc_814"/></td>
         <td><xsl:value-of select="weight_on_admission_763"/></td>
         <td><xsl:value-of select="temperature_infant_680"/></td>
         <td><xsl:value-of select="head_circumf_530"/></td>
         <td><xsl:value-of select="pallor_766"/></td>
         <td><xsl:value-of select="cyanosis_767"/></td>
         <td><xsl:value-of select="jaundice_519"/></td>
         <td><xsl:value-of select="heart_rate_769"/></td>
         <td><xsl:value-of select="birth_weight_758"/></td>
         <td><xsl:value-of select="reason_for_admission_759"/></td>
         <td><xsl:value-of select="reason_for_admission_other_761"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>