<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>DeliverySumReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Latent phase duration</th>
      <th>Labour Onset</th>
      <th>If Induced: Mode of Induction</th>
      <th>Indication for Induction</th>
      <th>Indication for Induction, Other Describe</th>
      <th>Membranes Ruptured</th>
      <th>Rupture of Membranes Date</th>
      <th>Rupture of Membranes Time</th>
      <th>Liquor</th>
      <th>Foul Smell</th>
      <th>Did mother receive ARV treatment?</th>
      <th>Given NVP Tablets</th>
      <th>NVP Amount Given</th>
      <th>1st Stage Began - Date</th>
      <th>1st Stage Began - Time</th>
      <th>Complete Dilitation - Date</th>
      <th>Complete Dilitation - Time</th>
      <th>Abnormality at Dilitation</th>
      <th>Abnormality at Delivery</th>
      <th>Labour Augmented</th>
      <th>Bladder Emptied</th>
      <th>Placenta Delivered - Date</th>
      <th>Placenta Delivered - Time</th>
      <th>Placenta Delivery Method</th>
      <th>Mode of Delivery</th>
      <th>Mode of Delivery of C/S</th>
      <th>Indication for C/S  Forceps/Vacuum</th>
      <th>Indication for C/S..Other, describe</th>
      <th>Anesthesia for Delivery</th>
      <th>Episiotomy Performed</th>
      <th>Genital Laceration</th>
      <th>Episiotomy Extension / Genital Laceration</th>
      <th>Anterior Laceration Depth</th>
      <th>Anterior Laceration Sutured</th>
      <th>Posterior Laceration Depth</th>
      <th>Posterior Laceration Sutured</th>
      <th>Cervical Lacerations</th>
      <th>Cervical Lacerations Sutured?</th>
      <th>PPH</th>
      <th>Blood Loss Estimated</th>
      <th>Postpartum Haemorrhage (PPH) Treatment</th>
      <th>If Blood Transfusion selected for PPH Treatment</th>
      <th>Drugs or IV Fluids used in PPH Treatment</th>
      <th>If IV Fluid, Volume</th>
      <th>Treatment Medical Other Describe:</th>
      <th>Urine passed postpartum</th>
      <th>Bowel Movement postpartum</th>
      <th>Complications</th>
      <th>If Complications, Describe</th>
      <th>Colour of Placenta</th>
      <th>Presence of Infarcts</th>
      <th>Presence of Clots</th>
      <th>Extension of Blood Vessels into Membranes</th>
      <th>Other Placenta Abnormalities</th>
      <th>Haemorrhage in Relation to Placenta</th>
      <th>Placenta Type</th>
      <th>State of Placenta</th>
      <th>Weight of Placenta</th>
      <th>Mode of Cord Insertion</th>
      <th>Blood Vessels in Cord</th>
      </tr>
      <xsl:for-each select="list/Deli">
      <tr>
         <td><xsl:value-of select="latent_phase_duration_421"/></td>
         <td><xsl:value-of select="labour_onset_422"/></td>
         <td><xsl:value-of select="if_induced_mode_423"/></td>
         <td><xsl:value-of select="indication_induction_424"/></td>
         <td><xsl:value-of select="indication_induction_desc_425"/></td>
         <td><xsl:value-of select="membranes_re_330"/></td>
         <td><xsl:value-of select="rupture_of_membranes_date_328"/></td>
         <td><xsl:value-of select="rupture_of_membranes_time_329"/></td>
         <td><xsl:value-of select="liquor_331"/></td>
         <td><xsl:value-of select="foul_smell_278"/></td>
         <td><xsl:value-of select="mother_received_arv"/></td>
         <td><xsl:value-of select="given_nvp_tablets_1223"/></td>
         <td><xsl:value-of select="nvp_amount_1224"/></td>
         <td><xsl:value-of select="date_first_stage_began"/></td>
         <td><xsl:value-of select="first_stage_began_431"/></td>
         <td><xsl:value-of select="date_complete_dilitation"/></td>
         <td><xsl:value-of select="complete_dilitation_432"/></td>
         <td><xsl:value-of select="abnormality_at_dilatation_433"/></td>
         <td><xsl:value-of select="abnormality_at_delivery_435"/></td>
         <td><xsl:value-of select="labour_augmented_436"/></td>
         <td><xsl:value-of select="bladder_emptied_437"/></td>
         <td><xsl:value-of select="date_placenta_delivered"/></td>
         <td><xsl:value-of select="placenta_delivered_438"/></td>
         <td><xsl:value-of select="placenta_delivery_method_439"/></td>
         <td><xsl:value-of select="mode_of_delivery_447"/></td>
         <td><xsl:value-of select="mode_of_delivery_cs_448"/></td>
         <td><xsl:value-of select="indication_CS_forcepts_60"/></td>
         <td><xsl:value-of select="indication_CS_forcepts_desc_61"/></td>
         <td><xsl:value-of select="anesthesia_delivery_451"/></td>
         <td><xsl:value-of select="episiotomy_performed_452"/></td>
         <td><xsl:value-of select="genital_laceration_453"/></td>
         <td><xsl:value-of select="episiotomy_extension_454"/></td>
         <td><xsl:value-of select="anterior_laceration_depth_455"/></td>
         <td><xsl:value-of select="anterior_laceration_sutured_456"/></td>
         <td><xsl:value-of select="posterior_laceration_depth_457"/></td>
         <td><xsl:value-of select="posterior_laceration_sutured_458"/></td>
         <td><xsl:value-of select="cervical_laceration_459"/></td>
         <td><xsl:value-of select="cervical_laceration_sutured_460"/></td>
         <td><xsl:value-of select="pph"/></td>
         <td><xsl:value-of select="blood_loss_est_462"/></td>
         <td><xsl:value-of select="pph_treatment_463"/></td>
         <td><xsl:value-of select="if_blood_transfusion_1177"/></td>
         <td><xsl:value-of select="drugs_or_iv_fluids_1178"/></td>
         <td><xsl:value-of select="iv_fluid_volume_1179"/></td>
         <td><xsl:value-of select="treatment_method_desc_464"/></td>
         <td><xsl:value-of select="urine_passed_465"/></td>
         <td><xsl:value-of select="bowel_movement_postpartum_466"/></td>
         <td><xsl:value-of select="complications_467"/></td>
         <td><xsl:value-of select="if_complications_desc_468"/></td>
         <td><xsl:value-of select="colour_of_placenta_470"/></td>
         <td><xsl:value-of select="presence_of_infarcts_471"/></td>
         <td><xsl:value-of select="presence_of_clots_472"/></td>
         <td><xsl:value-of select="extension_of_vessels_473"/></td>
         <td><xsl:value-of select="other_placenta_abnormal_474"/></td>
         <td><xsl:value-of select="haemorrhage_irt_placenta_475"/></td>
         <td><xsl:value-of select="placenta_type_440"/></td>
         <td><xsl:value-of select="state_of_placenta_1204"/></td>
         <td><xsl:value-of select="weight_of_placenta_441"/></td>
         <td><xsl:value-of select="mode_of_cord_insert_442"/></td>
         <td><xsl:value-of select="blood_vessels_in_cord"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>