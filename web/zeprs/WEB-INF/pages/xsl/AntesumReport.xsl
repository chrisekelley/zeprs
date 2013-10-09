<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>AntesumReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Date of Discharge</th>
      <th>Hospital Ward</th>
      <th>Anaemia</th>
      <th>Anaemia: Specify</th>
      <th>Cardiac disease</th>
      <th>Diabetes: Gestational</th>
      <th>Diabetes: Pregestational</th>
      <th>Dysentary</th>
      <th>Epilepsy</th>
      <th>Gastroenteritis</th>
      <th>Hemoglobinopathy: Sickle cell disease</th>
      <th>Hemoglobinopathy: Thallasemia</th>
      <th>Hepatitis</th>
      <th>Malaria suspected</th>
      <th>Missed Abortion</th>
      <th>Polyarthritis</th>
      <th>Psychosis</th>
      <th>Tuberculosis</th>
      <th>Preterm Labour</th>
      <th>PROM</th>
      <th>Breech Presentation</th>
      <th>APH</th>
      <th>UTI</th>
      <th>Antepartum Hemhorrhage</th>
      <th>Draining</th>
      <th>Malpresentations</th>
      <th>Previous C/S</th>
      <th>Wrong Dates</th>
      <th>Hypertensive Disorders</th>
      <th>Miscarriage</th>
      <th>Diagnosis, Other, describe</th>
      <th>Drug 1</th>
      <th>Drug 2</th>
      <th>Drug 3</th>
      <th>Drug 4</th>
      <th>Drug 5</th>
      <th>Drug 6</th>
      <th>Drug 7</th>
      <th>Drug 8</th>
      <th>Drug 9</th>
      <th>Drug 10</th>
      <th>Medical Treatments: Other, Describe</th>
      <th>MVA</th>
      <th>Dilatation and Curettage</th>
      <th>Laparotomy</th>
      <th>Hysterectomy</th>
      <th>Salpingostomy</th>
      <th>Cervical Suture</th>
      <th>Surgical Treatments: Other, Describe</th>
      <th>Medications on Discharge</th>
      <th>Temperature</th>
      <th>Pulse</th>
      <th>Respiratory System</th>
      <th>Respiratory System, Other, Describe</th>
      <th>Respiration Rate</th>
      <th>BP: Systolic</th>
      <th>BP: Diastolic</th>
      <th>Scheduled Follow-up</th>
      <th>Place of Next Visit</th>
      <th>Referring encounter id</th>
      </tr>
      <xsl:for-each select="list/Ante">
      <tr>
         <td><xsl:value-of select="date_of_discharge_1268"/></td>
         <td><xsl:value-of select="hospital_ward_1269"/></td>
         <td><xsl:value-of select="anaemia"/></td>
         <td><xsl:value-of select="anaemia_measurement"/></td>
         <td><xsl:value-of select="cardiac_disease"/></td>
         <td><xsl:value-of select="diabetes_gestational"/></td>
         <td><xsl:value-of select="diabetes_pregestational"/></td>
         <td><xsl:value-of select="dysentary"/></td>
         <td><xsl:value-of select="epilepsy"/></td>
         <td><xsl:value-of select="gastroenteritis"/></td>
         <td><xsl:value-of select="hemoglobinopathy_sickle"/></td>
         <td><xsl:value-of select="hemoglobinopathy_thallasemia"/></td>
         <td><xsl:value-of select="hepatitis"/></td>
         <td><xsl:value-of select="malaria_suspected"/></td>
         <td><xsl:value-of select="missed_abortion"/></td>
         <td><xsl:value-of select="polyarthritis"/></td>
         <td><xsl:value-of select="psychosis"/></td>
         <td><xsl:value-of select="tuberculosis"/></td>
         <td><xsl:value-of select="preterm_labour"/></td>
         <td><xsl:value-of select="prom"/></td>
         <td><xsl:value-of select="breech_presentation"/></td>
         <td><xsl:value-of select="aph"/></td>
         <td><xsl:value-of select="uti"/></td>
         <td><xsl:value-of select="antepartum_hemhorrhage"/></td>
         <td><xsl:value-of select="draining"/></td>
         <td><xsl:value-of select="malpresentations"/></td>
         <td><xsl:value-of select="previous_c_s"/></td>
         <td><xsl:value-of select="wrong_dates"/></td>
         <td><xsl:value-of select="Hypertensive_disorders"/></td>
         <td><xsl:value-of select="miscarriage"/></td>
         <td><xsl:value-of select="diag_other"/></td>
         <td><xsl:value-of select="drug_1_1136"/></td>
         <td><xsl:value-of select="drug_2_1137"/></td>
         <td><xsl:value-of select="drug_3_1138"/></td>
         <td><xsl:value-of select="drug_4_1139"/></td>
         <td><xsl:value-of select="drug_5_1140"/></td>
         <td><xsl:value-of select="drug_6_1141"/></td>
         <td><xsl:value-of select="drug_7_1142"/></td>
         <td><xsl:value-of select="drug_8_1143"/></td>
         <td><xsl:value-of select="drug_9_1144"/></td>
         <td><xsl:value-of select="drug_10_1145"/></td>
         <td><xsl:value-of select="med_treatments_other_desc"/></td>
         <td><xsl:value-of select="mva"/></td>
         <td><xsl:value-of select="dilatation_and_curettage"/></td>
         <td><xsl:value-of select="laparotomy"/></td>
         <td><xsl:value-of select="hysterectomy"/></td>
         <td><xsl:value-of select="salpingostomy"/></td>
         <td><xsl:value-of select="cervical_suture"/></td>
         <td><xsl:value-of select="surg_treat_other_desc"/></td>
         <td><xsl:value-of select="medications_discharge"/></td>
         <td><xsl:value-of select="temperature_266"/></td>
         <td><xsl:value-of select="pulse_171"/></td>
         <td><xsl:value-of select="respiratory_system_167"/></td>
         <td><xsl:value-of select="respiratory_system_other"/></td>
         <td><xsl:value-of select="respiration_rate_269"/></td>
         <td><xsl:value-of select="bp_systolic_224"/></td>
         <td><xsl:value-of select="bp_diastolic_225"/></td>
         <td><xsl:value-of select="scheduled_followup_1293"/></td>
         <td><xsl:value-of select="place_of_next_visit_1213"/></td>
         <td><xsl:value-of select="referring_encounter_id"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>