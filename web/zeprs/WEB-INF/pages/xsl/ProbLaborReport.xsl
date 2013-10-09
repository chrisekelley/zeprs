<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>ProbLaborReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Contractions</th>
      <th>Contractions, Date of onset</th>
      <th>Lower abdominal pains</th>
      <th>Date of Onset, Lower abdominal pains</th>
      <th>Decreased fetal movement</th>
      <th>Date of onset, Decreased fetal movement</th>
      <th>Vaginal Bleeding</th>
      <th>Date of onset, Bleeding</th>
      <th>Vaginal discharge</th>
      <th>Date of onset, Vaginal discharge</th>
      <th>Fever</th>
      <th>Date of Onset, Fever</th>
      <th>Elevated blood pressure</th>
      <th>Swelling/Edema</th>
      <th>Trauma</th>
      <th>Nausea/Vomiting</th>
      <th>Diarrhea</th>
      <th>Date of Onset, Diarrhea</th>
      <th>Possible rupture of membranes</th>
      <th>Shortness of breath</th>
      <th>Possible infection</th>
      <th>Backache</th>
      <th>Headache</th>
      <th>Date of Onset, Headache</th>
      <th>Fatigue/Dizziness</th>
      <th>Lack of Foetal Movement</th>
      <th>Cough</th>
      <th>Reasons: Other</th>
      <th>Reasons: Other, Describe</th>
      <th>Comments</th>
      <th>Respiratory System</th>
      <th>Respiratory System, Other, Describe</th>
      <th>Respiration Rate</th>
      <th>BP: Systolic</th>
      <th>BP: Diastolic</th>
      <th>Pulse</th>
      <th>Temperature</th>
      <th>Height</th>
      <th>Weight</th>
      <th>HE/ENT</th>
      <th>HE/ENT Abnormal, Describe</th>
      <th>Thyroid</th>
      <th>Breasts</th>
      <th>Heart</th>
      <th>Heart, Other,Describe</th>
      <th>Abdomen</th>
      <th>Abdomen, Abnormal Describe</th>
      <th>Skin</th>
      <th>Skin, Abnormal Describe</th>
      <th>Extremities</th>
      <th>Extremities, Abnormal Describe</th>
      <th>Lymph Nodes</th>
      <th>Rectum</th>
      <th>Rectum, Abnormal Describe</th>
      <th>Vulva</th>
      <th>Vulva, Abnormal Describe</th>
      <th>Vagina</th>
      <th>Vagina, Abnormal Describe</th>
      <th>Cervix</th>
      <th>Cervix, Abnormal Describe</th>
      <th>Uterus</th>
      <th>Uterus, Size in Days</th>
      <th>Adnexa</th>
      <th>Adnexa, Abnormal Describe</th>
      <th>Varicosities</th>
      <th>Teeth</th>
      <th>Teeth, Other Describe</th>
      <th>CNS</th>
      <th>Time of Exam</th>
      <th>Fundal Height</th>
      <th>Lie</th>
      <th>Presentation</th>
      <th>Presentation: Other, Describe</th>
      <th>Descent</th>
      <th>Contraction Strength</th>
      <th>Contraction Frequency Per 10 Minutes at abdominal palpation</th>
      <th>Foetal Heart Rate at Abdominal Palpation</th>
      <th>Time</th>
      <th>Rupture of Membranes Date</th>
      <th>Rupture of Membranes Time</th>
      <th>Presentation Obtained by</th>
      <th>Condition of Vulva</th>
      <th>Condition of Vulva, Other, Describe</th>
      <th>Condition of Vagina</th>
      <th>Condition of Vagina, Other, Describe</th>
      <th>Cervix Dilatation</th>
      <th>Cervix Effacement</th>
      <th>Cervix Consistency</th>
      <th>Membranes Ruptured</th>
      <th>Liquor</th>
      <th>Station of PP</th>
      <th>Moulding</th>
      <th>Caput</th>
      <th>Cord at Vaginal Exam</th>
      <th>Rupture of Membranes</th>
      <th>Diagonal Conjugate (Promontorium)</th>
      <th>Diagonal Conjugate (Promontorium) length</th>
      <th>Ishcial Spines</th>
      <th>Sub-pubic Arch</th>
      <th>Curvature of Sacrum</th>
      <th>Intertuberous Diameter</th>
      <th>Adequacy of Pelvic</th>
      <th>Pubic Arch-Angle</th>
      <th>False labour</th>
      <th>True labor</th>
      <th>Rupture of Membranes</th>
      <th>Preeclampsia / Hypertension</th>
      <th>Premature Labor</th>
      <th>Malaria</th>
      <th>Anaemia</th>
      <th>High Blood Pressure</th>
      <th>Vaginal Bleeding</th>
      <th>Intrauterine Death</th>
      <th>Urinary Tract Infections</th>
      <th>Pneumonia</th>
      <th>TB</th>
      <th>Vaginal Thrush</th>
      <th>Oral Thrush</th>
      <th>Eclampsia</th>
      <th>Abruptio Placenta</th>
      <th>Miscarriage</th>
      <th>Latent Labour</th>
      <th>CNS normal</th>
      <th>Diagnosis, Other, describe</th>
      <th>Phase of Pregnancy</th>
      <th>Disposition (Intrapartum)</th>
      <th>Disposition (Antepartum)</th>
      <th>Type of Labor</th>
      <th>UTH Ward</th>
      <th>Priority of Referral</th>
      <th>Transport</th>
      <th>Treatment</th>
      <th>Comments</th>
      </tr>
      <xsl:for-each select="list/Prob">
      <tr>
         <td><xsl:value-of select="contractions_1250"/></td>
         <td><xsl:value-of select="contractions_date_1251"/></td>
         <td><xsl:value-of select="lower_abdominal_pains"/></td>
         <td><xsl:value-of select="lower_abdominal_pains_date_onset"/></td>
         <td><xsl:value-of select="decreased_fetal_movement_1256"/></td>
         <td><xsl:value-of select="deacreased_fetal_mvmt_date_onset"/></td>
         <td><xsl:value-of select="vag_bleeding_1254"/></td>
         <td><xsl:value-of select="bleeding_date_of_onset_1255"/></td>
         <td><xsl:value-of select="vag_discharge_1252"/></td>
         <td><xsl:value-of select="vag_discharge_date_of_onset_1253"/></td>
         <td><xsl:value-of select="fever"/></td>
         <td><xsl:value-of select="fever_date_onset"/></td>
         <td><xsl:value-of select="elevated_blood_pressure"/></td>
         <td><xsl:value-of select="swelling_edema"/></td>
         <td><xsl:value-of select="trauma"/></td>
         <td><xsl:value-of select="nausea_vomiting"/></td>
         <td><xsl:value-of select="diarrhea"/></td>
         <td><xsl:value-of select="diarrhea_date_onset"/></td>
         <td><xsl:value-of select="possible_reputure_membranes"/></td>
         <td><xsl:value-of select="shortness_of_breath"/></td>
         <td><xsl:value-of select="possible_infection"/></td>
         <td><xsl:value-of select="backache"/></td>
         <td><xsl:value-of select="headache"/></td>
         <td><xsl:value-of select="date_onset_headache"/></td>
         <td><xsl:value-of select="fatigue_dizziness"/></td>
         <td><xsl:value-of select="lack_of_foetal_movement"/></td>
         <td><xsl:value-of select="cough"/></td>
         <td><xsl:value-of select="other_reasons"/></td>
         <td><xsl:value-of select="reasons_other_describe"/></td>
         <td><xsl:value-of select="comments_reasons_for_eval"/></td>
         <td><xsl:value-of select="respiratory_system_167"/></td>
         <td><xsl:value-of select="respiratory_system_other"/></td>
         <td><xsl:value-of select="respiration_rate_269"/></td>
         <td><xsl:value-of select="bp_systolic_224"/></td>
         <td><xsl:value-of select="bp_diastolic_225"/></td>
         <td><xsl:value-of select="pulse_171"/></td>
         <td><xsl:value-of select="temperature_266"/></td>
         <td><xsl:value-of select="height_159"/></td>
         <td><xsl:value-of select="weight_228"/></td>
         <td><xsl:value-of select="heent_161"/></td>
         <td><xsl:value-of select="heent_abnorm_162"/></td>
         <td><xsl:value-of select="thyroid_165"/></td>
         <td><xsl:value-of select="breasts_166"/></td>
         <td><xsl:value-of select="heart_169"/></td>
         <td><xsl:value-of select="heart_other_170"/></td>
         <td><xsl:value-of select="abdomen_172"/></td>
         <td><xsl:value-of select="abdomen_abnormal_173"/></td>
         <td><xsl:value-of select="skin_176"/></td>
         <td><xsl:value-of select="skin_abnorm_177"/></td>
         <td><xsl:value-of select="extremities_174"/></td>
         <td><xsl:value-of select="extremities_abnormal_175"/></td>
         <td><xsl:value-of select="lymph_nodes_178"/></td>
         <td><xsl:value-of select="rectum_179"/></td>
         <td><xsl:value-of select="rectum_abnormal_180"/></td>
         <td><xsl:value-of select="vulva_181"/></td>
         <td><xsl:value-of select="vulva_abnormal_182"/></td>
         <td><xsl:value-of select="vagina_183"/></td>
         <td><xsl:value-of select="vagina_abnormal_184"/></td>
         <td><xsl:value-of select="cervix_185"/></td>
         <td><xsl:value-of select="cervix_abnormal_186"/></td>
         <td><xsl:value-of select="uterus_187"/></td>
         <td><xsl:value-of select="uterus_size_in_days_188"/></td>
         <td><xsl:value-of select="adnexa_189"/></td>
         <td><xsl:value-of select="adnexa_abnormal_190"/></td>
         <td><xsl:value-of select="varicosities_191"/></td>
         <td><xsl:value-of select="teeth_163"/></td>
         <td><xsl:value-of select="teeth_other_164"/></td>
         <td><xsl:value-of select="cns_192"/></td>
         <td><xsl:value-of select="time_of_exam_1175"/></td>
         <td><xsl:value-of select="fundal_height_232"/></td>
         <td><xsl:value-of select="lie_313"/></td>
         <td><xsl:value-of select="presentation_314"/></td>
         <td><xsl:value-of select="presentation_other"/></td>
         <td><xsl:value-of select="descent_315"/></td>
         <td><xsl:value-of select="contraction_strength_316"/></td>
         <td><xsl:value-of select="contraction_freq_10_abd_palp_317"/></td>
         <td><xsl:value-of select="foetal_heart_rate_abd_palp_318"/></td>
         <td><xsl:value-of select="time_320"/></td>
         <td><xsl:value-of select="rupture_of_membranes_date_328"/></td>
         <td><xsl:value-of select="rupture_of_membranes_time_329"/></td>
         <td><xsl:value-of select="presentation_obtained_by_335"/></td>
         <td><xsl:value-of select="condition_of_vulva_321"/></td>
         <td><xsl:value-of select="condition_of_vulva_desc_322"/></td>
         <td><xsl:value-of select="condition_of_vagina_323"/></td>
         <td><xsl:value-of select="condition_of_vagina_other_324"/></td>
         <td><xsl:value-of select="cervix_dilatation325"/></td>
         <td><xsl:value-of select="cervix_effacement_326"/></td>
         <td><xsl:value-of select="cervix_consistency_327"/></td>
         <td><xsl:value-of select="membranes_re_330"/></td>
         <td><xsl:value-of select="liquor_331"/></td>
         <td><xsl:value-of select="station_of_pp_336"/></td>
         <td><xsl:value-of select="moulding_338"/></td>
         <td><xsl:value-of select="caput_339"/></td>
         <td><xsl:value-of select="cord_at_vaginal_exam_340"/></td>
         <td><xsl:value-of select="rupture_of_membranes_1221"/></td>
         <td><xsl:value-of select="diagonal_conjugate_342"/></td>
         <td><xsl:value-of select="diagonal_conjugate_length_343"/></td>
         <td><xsl:value-of select="ishcial_spines_344"/></td>
         <td><xsl:value-of select="sub_pubic_arch_345"/></td>
         <td><xsl:value-of select="curvature_of_sacrum_346"/></td>
         <td><xsl:value-of select="intertuberous_diameter_347"/></td>
         <td><xsl:value-of select="adequacy_of_pelvic_348"/></td>
         <td><xsl:value-of select="pubic_arch_angle_349"/></td>
         <td><xsl:value-of select="false_labour"/></td>
         <td><xsl:value-of select="true_labor"/></td>
         <td><xsl:value-of select="rupture_of_membranes"/></td>
         <td><xsl:value-of select="preeclamp_hypert_1265"/></td>
         <td><xsl:value-of select="premature_labour"/></td>
         <td><xsl:value-of select="malaria_diag"/></td>
         <td><xsl:value-of select="anaemia"/></td>
         <td><xsl:value-of select="high_bp_diag"/></td>
         <td><xsl:value-of select="vaginal_bleeding_diag"/></td>
         <td><xsl:value-of select="intrauterine_death"/></td>
         <td><xsl:value-of select="uti_diag"/></td>
         <td><xsl:value-of select="pneumonia_diag"/></td>
         <td><xsl:value-of select="tb_diag"/></td>
         <td><xsl:value-of select="vaginal_thrush_diag"/></td>
         <td><xsl:value-of select="oral_thrush_diag"/></td>
         <td><xsl:value-of select="eclampsia"/></td>
         <td><xsl:value-of select="abruptia_placenta"/></td>
         <td><xsl:value-of select="miscarriage"/></td>
         <td><xsl:value-of select="latent_labour"/></td>
         <td><xsl:value-of select="cns_normal"/></td>
         <td><xsl:value-of select="diag_other"/></td>
         <td><xsl:value-of select="phase"/></td>
         <td><xsl:value-of select="disposition_labor"/></td>
         <td><xsl:value-of select="disp_ante"/></td>
         <td><xsl:value-of select="type_of_labour"/></td>
         <td><xsl:value-of select="uth_ward"/></td>
         <td><xsl:value-of select="priority_of_referral"/></td>
         <td><xsl:value-of select="transport"/></td>
         <td><xsl:value-of select="treatment_1463"/></td>
         <td><xsl:value-of select="comments_ante_prob_1464"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>