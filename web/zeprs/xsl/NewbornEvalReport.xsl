<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>NewbornEvalReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Date of Birth</th>
      <th>Time of Birth</th>
      <th>Sequence Number of this Newborn</th>
      <th>If Premature, Number of  wks Since Gestation</th>
      <th>Sex</th>
      <th>Weight at Birth</th>
      <th>Estimate?</th>
      <th>Trauma/Injuries</th>
      <th>Trauma: Other, describe</th>
      <th>Alive/SB (Still Born)</th>
      <th>If still born, cause</th>
      <th>Neonatal Death</th>
      <th>If Neonatal Death, Age in hours</th>
      <th>if Neonatal Death, Cause</th>
      <th>if Neonatal Death, Cause, Other, Describe</th>
      <th>Baby born at Home?</th>
      <th>Cord Evaluation</th>
      <th>Apgar Score 1 min</th>
      <th>Apgar Score 5 min</th>
      <th>Apgar Score 10 min</th>
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
      <th>EGA</th>
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
      <th>Comments/Problems</th>
      <th>Place of Next Visit</th>
      </tr>
      <xsl:for-each select="list/Newb">
      <tr>
         <td><xsl:value-of select="date_of_birth"/></td>
         <td><xsl:value-of select="time_of_birth_1514"/></td>
         <td><xsl:value-of select="sequence_num_489"/></td>
         <td><xsl:value-of select="if_premature_num_weeks_gest_488"/></td>
         <td><xsl:value-of select="sex_490"/></td>
         <td><xsl:value-of select="weight_at_birth_491"/></td>
         <td><xsl:value-of select="estimate_weight"/></td>
         <td><xsl:value-of select="trauma_492"/></td>
         <td><xsl:value-of select="trauma_other_1192"/></td>
         <td><xsl:value-of select="alive_sb_493"/></td>
         <td><xsl:value-of select="if_sb_cause_494"/></td>
         <td><xsl:value-of select="neonatal_dea_1180"/></td>
         <td><xsl:value-of select="neonatal_death_age_hours_497"/></td>
         <td><xsl:value-of select="neonatal_death_cause_495"/></td>
         <td><xsl:value-of select="neonatal_death_cause_desc_496"/></td>
         <td><xsl:value-of select="born_at_home"/></td>
         <td><xsl:value-of select="cord_evaluation"/></td>
         <td><xsl:value-of select="apgar_score_1_min_498"/></td>
         <td><xsl:value-of select="apgar_score_5_min_504"/></td>
         <td><xsl:value-of select="apgar_score_10_min_510"/></td>
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
         <td><xsl:value-of select="ega_129"/></td>
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
         <td><xsl:value-of select="problems_comments_557"/></td>
         <td><xsl:value-of select="place_of_next_visit_1213"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>