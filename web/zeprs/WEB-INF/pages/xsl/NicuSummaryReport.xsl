<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>NicuSummaryReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Investigations</th>
      <th>Oxygen - not ventilated</th>
      <th>Mechanical Ventilation</th>
      <th>Antibiotics (list Antibiotics used below)</th>
      <th>Nevirapine</th>
      <th>Pressors</th>
      <th>Intravenous Fluids</th>
      <th>Umbilical Artery Catheter</th>
      <th>XS Transfusion</th>
      <th>Phototherapy</th>
      <th>Vitamin K</th>
      <th>Treatment: Other, Describe</th>
      <th>Treatment: Other, Descrbe</th>
      <th>Enter Antibiotics Used</th>
      <th>Did Patient receive ARV treatment?</th>
      <th>Infant Outcome</th>
      <th>If Death, Probable Cause</th>
      <th>Other Death Cause, Describe:</th>
      <th>If Death from prematurity, number of weeks.</th>
      <th>Autopsy Consent</th>
      <th>Discharge</th>
      <th>Infant is discharged to</th>
      <th>X-Ray</th>
      <th>X-Ray, Describe</th>
      <th>Baby Ultrasound</th>
      <th>Baby Ultrasound, Describe</th>
      <th>Echo-cardiogram</th>
      <th>Echo-cardiogram, Describe</th>
      <th>Diagnosis at Discharge</th>
      <th>Diagnosis at Discharge, Other, Describe</th>
      <th>Follow Up Comments</th>
      <th>Birth Record Given</th>
      <th>Treatment on Discharge</th>
      <th>Comments/Problems</th>
      <th>Date for 1st Postnatal Visit</th>
      <th>Place of 1st Postnatal Visit</th>
      <th>Date of Discharge</th>
      <th>Place of Next Visit</th>
      <th>Infant receives Vitamin K</th>
      <th>Infant receives tetracycline</th>
      <th>Referring encounter id</th>
      </tr>
      <xsl:for-each select="list/Nicu">
      <tr>
         <td><xsl:value-of select="investigations"/></td>
         <td><xsl:value-of select="oxygen_not_ventilated"/></td>
         <td><xsl:value-of select="mechanical_ventilation"/></td>
         <td><xsl:value-of select="antibiotics"/></td>
         <td><xsl:value-of select="nevirapine"/></td>
         <td><xsl:value-of select="pressors"/></td>
         <td><xsl:value-of select="intravenous_fluids"/></td>
         <td><xsl:value-of select="umbilical_artery_catheter"/></td>
         <td><xsl:value-of select="xs_transfusion"/></td>
         <td><xsl:value-of select="phototherapy"/></td>
         <td><xsl:value-of select="vitamin_k"/></td>
         <td><xsl:value-of select="treatment_other"/></td>
         <td><xsl:value-of select="treatment_other_desc_818"/></td>
         <td><xsl:value-of select="antibics_used_1184"/></td>
         <td><xsl:value-of select="patient_received_arv"/></td>
         <td><xsl:value-of select="infant_outcome_820"/></td>
         <td><xsl:value-of select="if_death_probable_cause_821"/></td>
         <td><xsl:value-of select="other_death_823"/></td>
         <td><xsl:value-of select="if_death_premature_weeks_822"/></td>
         <td><xsl:value-of select="autopsy_consent_599"/></td>
         <td><xsl:value-of select="discharge_826"/></td>
         <td><xsl:value-of select="place_infant_discharged"/></td>
         <td><xsl:value-of select="x_ray"/></td>
         <td><xsl:value-of select="x_ray_desc"/></td>
         <td><xsl:value-of select="baby_ultrasound"/></td>
         <td><xsl:value-of select="baby_ultrasound_describe"/></td>
         <td><xsl:value-of select="echo_cardiogram"/></td>
         <td><xsl:value-of select="echo_cardiogram_describe"/></td>
         <td><xsl:value-of select="diagnosis_at_discharge_827"/></td>
         <td><xsl:value-of select="diagnosis_at_discharge_1185"/></td>
         <td><xsl:value-of select="follow_up_co_829"/></td>
         <td><xsl:value-of select="birth_record_given_561"/></td>
         <td><xsl:value-of select="treatment_on_discharge_562"/></td>
         <td><xsl:value-of select="problems_comments_557"/></td>
         <td><xsl:value-of select="first_postnatal_visit_date_564"/></td>
         <td><xsl:value-of select="first_postnatal_visit_place_565"/></td>
         <td><xsl:value-of select="date_of_discharge_1268"/></td>
         <td><xsl:value-of select="place_of_next_visit_1213"/></td>
         <td><xsl:value-of select="receives_vitamine_k"/></td>
         <td><xsl:value-of select="receives_tetracycline"/></td>
         <td><xsl:value-of select="referring_encounter_id"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>