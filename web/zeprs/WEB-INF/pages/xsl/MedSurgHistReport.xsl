<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>MedSurgHistReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Mental Illness</th>
      <th>Epilepsy</th>
      <th>Thyroid</th>
      <th>Diabetes</th>
      <th>Diabetes, Years with</th>
      <th>Hypertension</th>
      <th>Heart Disease</th>
      <th>Rheumatic Heart Disease</th>
      <th>Tuberculosis</th>
      <th>Asthma</th>
      <th>Pneumonia</th>
      <th>Kidney Disease</th>
      <th>Pyelonephritis</th>
      <th>Liver Disease</th>
      <th>DVT/Clots</th>
      <th>Malaria</th>
      <th>Anaemia</th>
      <th>Sickle Cell Disease</th>
      <th>Genital Herpes</th>
      <th>GC</th>
      <th>Chlamydia</th>
      <th>HPV</th>
      <th>Hepatitis B</th>
      <th>Syphilis</th>
      <th>HIV</th>
      <th>Drug Allergies</th>
      <th>Drug Allergy Other Describe:</th>
      <th>Comments</th>
      <th>Currently taking medicine</th>
      <th>Prior Operations</th>
      <th>Appendicectomy</th>
      <th>If Appendectomy, Year</th>
      <th>C/S</th>
      <th>If C/S, Year</th>
      <th>Pelvic Operation</th>
      <th>If Pelvic Operation, Year</th>
      <th>If Pelvic Operation, Describe</th>
      <th>Transfusions</th>
      <th>Other Surgery, Incident 1</th>
      <th>Incident 1, Year</th>
      <th>Incident 1, Describe</th>
      <th>Other Surgery, Incident 2</th>
      <th>Incident 2, Year</th>
      <th>Incident 2, Describe</th>
      <th>Other Surgery, Incident 3</th>
      <th>Incident 3, Year</th>
      <th>Incident 3, Describe</th>
      <th>Other Surgery, Incident 4</th>
      <th>Incident 4, Year</th>
      <th>Incident 4, Describe</th>
      <th>Other Surgery, Incident 5</th>
      <th>Incident 5, Year</th>
      <th>Incident 5, Describe</th>
      </tr>
      <xsl:for-each select="list/MedS">
      <tr>
         <td><xsl:value-of select="mental_illness_69"/></td>
         <td><xsl:value-of select="epilepsy_70"/></td>
         <td><xsl:value-of select="thyroid_71"/></td>
         <td><xsl:value-of select="diabetes_72"/></td>
         <td><xsl:value-of select="diabetes_years_73"/></td>
         <td><xsl:value-of select="hypertension_74"/></td>
         <td><xsl:value-of select="heart_disease_75"/></td>
         <td><xsl:value-of select="rheumatic_heart_disease_76"/></td>
         <td><xsl:value-of select="tuberculosis_78"/></td>
         <td><xsl:value-of select="asthma_79"/></td>
         <td><xsl:value-of select="pneumonia_80"/></td>
         <td><xsl:value-of select="kidney_disease_81"/></td>
         <td><xsl:value-of select="pyelonephritis_82"/></td>
         <td><xsl:value-of select="liver_disease_83"/></td>
         <td><xsl:value-of select="dvt_clots_84"/></td>
         <td><xsl:value-of select="malaria_85"/></td>
         <td><xsl:value-of select="anaemia_1548"/></td>
         <td><xsl:value-of select="sickle_cell_disease_87"/></td>
         <td><xsl:value-of select="genital_herpes_89"/></td>
         <td><xsl:value-of select="gc_90"/></td>
         <td><xsl:value-of select="chlamydia_91"/></td>
         <td><xsl:value-of select="hpv_92"/></td>
         <td><xsl:value-of select="hepatitis_b_93"/></td>
         <td><xsl:value-of select="syphilis_94"/></td>
         <td><xsl:value-of select="hiv"/></td>
         <td><xsl:value-of select="drug_allergies_98"/></td>
         <td><xsl:value-of select="drug_allergy_99"/></td>
         <td><xsl:value-of select="comments_1249"/></td>
         <td><xsl:value-of select="currently_taking_meds_95"/></td>
         <td><xsl:value-of select="prior_operations"/></td>
         <td><xsl:value-of select="appendicectomy_116"/></td>
         <td><xsl:value-of select="if_appendectomy_117"/></td>
         <td><xsl:value-of select="cs"/></td>
         <td><xsl:value-of select="cs_year"/></td>
         <td><xsl:value-of select="pelvic_operation_118"/></td>
         <td><xsl:value-of select="if_pelvic_op_year_119"/></td>
         <td><xsl:value-of select="if_pelvic_op_descr_120"/></td>
         <td><xsl:value-of select="transfusions_121"/></td>
         <td><xsl:value-of select="other_surgery_1_1147"/></td>
         <td><xsl:value-of select="incident_1_year_122"/></td>
         <td><xsl:value-of select="incident_1_desc_123"/></td>
         <td><xsl:value-of select="other_surgery_2_1148"/></td>
         <td><xsl:value-of select="incident_2_year_1149"/></td>
         <td><xsl:value-of select="incident_2_desc_1150"/></td>
         <td><xsl:value-of select="other_surgery_3_1151"/></td>
         <td><xsl:value-of select="incident_3_year_1152"/></td>
         <td><xsl:value-of select="incident_3_desc_1153"/></td>
         <td><xsl:value-of select="other_surger_1154"/></td>
         <td><xsl:value-of select="incident_4_year_1155"/></td>
         <td><xsl:value-of select="incident_4_desc_1156"/></td>
         <td><xsl:value-of select="other_surgery_5_1157"/></td>
         <td><xsl:value-of select="incident_5_year_1158"/></td>
         <td><xsl:value-of select="incident_5_desc_1159"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>