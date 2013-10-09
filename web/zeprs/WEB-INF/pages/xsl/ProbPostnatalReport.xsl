<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>ProbPostnatalReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Lower abdominal pains</th>
      <th>Date of Onset, Lower abdominal pains</th>
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
      <th>Shortness of breath</th>
      <th>Possible infection</th>
      <th>Backache</th>
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
      <th>Adnexa</th>
      <th>Adnexa, Abnormal Describe</th>
      <th>Varicosities</th>
      <th>Teeth</th>
      <th>Teeth, Other Describe</th>
      <th>CNS</th>
      <th>Malaria</th>
      <th>Anaemia</th>
      <th>High Blood Pressure</th>
      <th>Vaginal Bleeding</th>
      <th>Urinary Tract Infections</th>
      <th>Pneumonia</th>
      <th>TB</th>
      <th>Vaginal Thrush</th>
      <th>Oral Thrush</th>
      <th>Broken episiotomy</th>
      <th>Puerperal sepsis</th>
      <th>Breast engorgement</th>
      <th>Secondary PPH</th>
      <th>Mastitis</th>
      <th>Breast abscess</th>
      <th>Diagnosis, Other, describe</th>
      <th>Eclampsia</th>
      <th>Preeclampsia / Hypertension</th>
      <th>Sepsis</th>
      <th>Psychosis</th>
      <th>Episiotomy infection</th>
      <th>Episiotomy breakdown</th>
      <th>Anaemia</th>
      <th>Mastitis/breast absess</th>
      <th>Endometritis</th>
      <th>Date of Ultrasound</th>
      <th>Diagnosis (General Impression)</th>
      <th>Describe Abnormalities/Comment on Ultrasound</th>
      <th>Plan of Action/Disposition</th>
      <th>UTH Ward</th>
      <th>Date of Admission</th>
      <th>Priority of Referral</th>
      <th>Transport</th>
      <th>Treatment</th>
      <th>Comments</th>
      </tr>
      <xsl:for-each select="list/Prob">
      <tr>
         <td><xsl:value-of select="lower_abdominal_pains"/></td>
         <td><xsl:value-of select="lower_abdominal_pains_date_onset"/></td>
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
         <td><xsl:value-of select="shortness_of_breath"/></td>
         <td><xsl:value-of select="possible_infection"/></td>
         <td><xsl:value-of select="backache"/></td>
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
         <td><xsl:value-of select="adnexa_189"/></td>
         <td><xsl:value-of select="adnexa_abnormal_190"/></td>
         <td><xsl:value-of select="varicosities_191"/></td>
         <td><xsl:value-of select="teeth_163"/></td>
         <td><xsl:value-of select="teeth_other_164"/></td>
         <td><xsl:value-of select="cns_192"/></td>
         <td><xsl:value-of select="malaria_diag"/></td>
         <td><xsl:value-of select="anaemia"/></td>
         <td><xsl:value-of select="high_bp_diag"/></td>
         <td><xsl:value-of select="vaginal_bleeding_diag"/></td>
         <td><xsl:value-of select="uti_diag"/></td>
         <td><xsl:value-of select="pneumonia_diag"/></td>
         <td><xsl:value-of select="tb_diag"/></td>
         <td><xsl:value-of select="vaginal_thrush_diag"/></td>
         <td><xsl:value-of select="oral_thrush_diag"/></td>
         <td><xsl:value-of select="broken_episiotomy"/></td>
         <td><xsl:value-of select="puerperal_sepsis"/></td>
         <td><xsl:value-of select="breast_engorgement"/></td>
         <td><xsl:value-of select="secondary_pph"/></td>
         <td><xsl:value-of select="mastitis"/></td>
         <td><xsl:value-of select="breast_abscess"/></td>
         <td><xsl:value-of select="diag_other"/></td>
         <td><xsl:value-of select="eclampsia"/></td>
         <td><xsl:value-of select="preeclamp_hypert_1265"/></td>
         <td><xsl:value-of select="sepsis"/></td>
         <td><xsl:value-of select="psychosis"/></td>
         <td><xsl:value-of select="episiotomy_infection"/></td>
         <td><xsl:value-of select="episiotomy_breakdown"/></td>
         <td><xsl:value-of select="anaemia_complication"/></td>
         <td><xsl:value-of select="mastitis_breast_absess"/></td>
         <td><xsl:value-of select="endometritis"/></td>
         <td><xsl:value-of select="date_of_ultrasound_1212"/></td>
         <td><xsl:value-of select="diagnosis_ultrasound"/></td>
         <td><xsl:value-of select="describe_abnormalities"/></td>
         <td><xsl:value-of select="disposition"/></td>
         <td><xsl:value-of select="uth_ward"/></td>
         <td><xsl:value-of select="date_of_admission"/></td>
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