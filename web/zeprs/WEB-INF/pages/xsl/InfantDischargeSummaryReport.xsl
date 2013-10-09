<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>InfantDischargeSummaryReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Tactile</th>
      <th>Tactile Results</th>
      <th>Suction</th>
      <th>Suction Results</th>
      <th>Oxygen</th>
      <th>Oxygen Results</th>
      <th>Intubation</th>
      <th>Intubation Results</th>
      <th>Ephinephrine</th>
      <th>Ephinephrine Results</th>
      <th>Naxolone</th>
      <th>Naxolone Results</th>
      <th>Atropine</th>
      <th>Atropine Results</th>
      <th>Bag & Mask</th>
      <th>Bag & Mask Results</th>
      <th>Sodium Bicarbonate</th>
      <th>Sodium Bicarbonate Results</th>
      <th>Other Drugs</th>
      <th>Other Drugs, Results</th>
      <th>Feeding Quality</th>
      <th>Feeding Type</th>
      <th>Jaundice</th>
      <th>Good Head Control</th>
      <th>Good Grasp Reflex</th>
      <th>Symmetrical Moro Reaction</th>
      <th>Eyes</th>
      <th>Eyes, Other, Describe</th>
      <th>If Eyes Abnormal, Treatment</th>
      <th>Mouth</th>
      <th>Mouth, Other, Describe</th>
      <th>Sucking</th>
      <th>Genitalia</th>
      <th>Genitalia, Other, Describe</th>
      <th>Head Circumference</th>
      <th>Chest Circumference</th>
      <th>Crown-Heel Length</th>
      <th>Urine Passed</th>
      <th>Bowel Movement</th>
      <th>Back</th>
      <th>Back, Other, Describe</th>
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
      <th>Weight on Third Day</th>
      <th>Infant Immunizations Given</th>
      <th>Infant Immunization 1</th>
      <th>Infant Immunization 2</th>
      <th>Infant Immunization 3</th>
      <th>Infant Immunization 4</th>
      <th>Infant Immunization 5</th>
      <th>Immunisations, Other, Describe</th>
      <th>Did baby receive ARV treatment?</th>
      <th>Given Nevirapine Initial Dose?</th>
      <th>Regimen - baby dose</th>
      <th>Amount of dosage</th>
      <th>Infant receives Vitamin K</th>
      <th>Infant receives tetracycline</th>
      <th>Birth Record Given</th>
      <th>Treatment on Discharge</th>
      <th>Date for 1st Postnatal Visit</th>
      <th>Place of 1st Postnatal Visit</th>
      <th>Date of Discharge</th>
      <th>Comments/Problems</th>
      </tr>
      <xsl:for-each select="list/Infa">
      <tr>
         <td><xsl:value-of select="tactile"/></td>
         <td><xsl:value-of select="tactile_results"/></td>
         <td><xsl:value-of select="suction"/></td>
         <td><xsl:value-of select="suction_results"/></td>
         <td><xsl:value-of select="oxygen"/></td>
         <td><xsl:value-of select="oxygen_results"/></td>
         <td><xsl:value-of select="intubation"/></td>
         <td><xsl:value-of select="intubation_results"/></td>
         <td><xsl:value-of select="ephinephrine"/></td>
         <td><xsl:value-of select="ephinephrine_results"/></td>
         <td><xsl:value-of select="naxolone"/></td>
         <td><xsl:value-of select="naxolone_results"/></td>
         <td><xsl:value-of select="atropine"/></td>
         <td><xsl:value-of select="atropine_results"/></td>
         <td><xsl:value-of select="bag_and_mask"/></td>
         <td><xsl:value-of select="bag_and_mask_results"/></td>
         <td><xsl:value-of select="sodium_bcarbonate"/></td>
         <td><xsl:value-of select="sodium_bcarbonate_results"/></td>
         <td><xsl:value-of select="other_drugs"/></td>
         <td><xsl:value-of select="other_drugs_results"/></td>
         <td><xsl:value-of select="feeding"/></td>
         <td><xsl:value-of select="feeding_type"/></td>
         <td><xsl:value-of select="jaundice_519"/></td>
         <td><xsl:value-of select="good_head_control_520"/></td>
         <td><xsl:value-of select="good_grasp_reflex_521"/></td>
         <td><xsl:value-of select="symmetrical_moro_522"/></td>
         <td><xsl:value-of select="eyes_523"/></td>
         <td><xsl:value-of select="eyes_other_524"/></td>
         <td><xsl:value-of select="if_eyes_abnormal_treatment_525"/></td>
         <td><xsl:value-of select="mouth_526"/></td>
         <td><xsl:value-of select="mouth_other_527"/></td>
         <td><xsl:value-of select="sucking_528"/></td>
         <td><xsl:value-of select="genitalia_529"/></td>
         <td><xsl:value-of select="genitalia_other_697"/></td>
         <td><xsl:value-of select="head_circumf_530"/></td>
         <td><xsl:value-of select="chest_circum_531"/></td>
         <td><xsl:value-of select="crown_heel_length_532"/></td>
         <td><xsl:value-of select="urine_passed_1181"/></td>
         <td><xsl:value-of select="bowel_movement_535"/></td>
         <td><xsl:value-of select="back_536"/></td>
         <td><xsl:value-of select="back_other_537"/></td>
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
         <td><xsl:value-of select="weight_on_3rd_day_558"/></td>
         <td><xsl:value-of select="immunization_554"/></td>
         <td><xsl:value-of select="immunization_1"/></td>
         <td><xsl:value-of select="immunization_2"/></td>
         <td><xsl:value-of select="immunization_3"/></td>
         <td><xsl:value-of select="immunization_4"/></td>
         <td><xsl:value-of select="immunization_5"/></td>
         <td><xsl:value-of select="immunisation_desc_556"/></td>
         <td><xsl:value-of select="baby_received_arv"/></td>
         <td><xsl:value-of select="initial_nevirapine_dose"/></td>
         <td><xsl:value-of select="rbd_home_regimen"/></td>
         <td><xsl:value-of select="rbd_home_dosage"/></td>
         <td><xsl:value-of select="receives_vitamine_k"/></td>
         <td><xsl:value-of select="receives_tetracycline"/></td>
         <td><xsl:value-of select="birth_record_given_561"/></td>
         <td><xsl:value-of select="treatment_on_discharge_562"/></td>
         <td><xsl:value-of select="first_postnatal_visit_date_564"/></td>
         <td><xsl:value-of select="first_postnatal_visit_place_565"/></td>
         <td><xsl:value-of select="date_of_discharge_1268"/></td>
         <td><xsl:value-of select="problems_comments_557"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>