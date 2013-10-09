<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>PostnatalInfantReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Postnatal Visit</th>
      <th>Infant Status</th>
      <th>Reasons for Infant Death</th>
      <th>Reasons for Infant Death: Other, Describe</th>
      <th>Weight (infant)</th>
      <th>Temperature</th>
      <th>Head</th>
      <th>Head, Other, Describe</th>
      <th>Head Circumference</th>
      <th>Eyes</th>
      <th>Eyes, Other, Describe</th>
      <th>Ears</th>
      <th>Ears, Other, Describe</th>
      <th>Mouth</th>
      <th>Mouth, Other, Describe</th>
      <th>Neck</th>
      <th>Neck Other Describe</th>
      <th>Abdomen</th>
      <th>Abdomen, Other Describe</th>
      <th>Cord at Followup</th>
      <th>Cord at Followup, Other, Describe</th>
      <th>Genitalia</th>
      <th>Genitalia, Other, Describe</th>
      <th>Anus</th>
      <th>Anus, Other Describe</th>
      <th>Skin</th>
      <th>Skin, Other Describe</th>
      <th>Upper Limbs</th>
      <th>Upper Limbs, Other Describe</th>
      <th>Lower Limbs</th>
      <th>Lower Limbs, Other Describe</th>
      <th>Back</th>
      <th>Back, Other, Describe</th>
      <th>Neurological Examination</th>
      <th>Neurological Examination, Other, Describe</th>
      <th>OPV1 Given at Week 6</th>
      <th>DPT 1 given at Week 6</th>
      <th>BCG Given this visit?</th>
      <th>Feeding Quality</th>
      <th>Feeding Type</th>
      <th>Infant Immunizations Given</th>
      <th>Infant Immunization 1</th>
      <th>Infant Immunization 2</th>
      <th>Infant Immunization 3</th>
      <th>Infant Immunization 4</th>
      <th>Infant Immunization 5</th>
      <th>Did Patient receive ARV treatment?</th>
      <th>Problem?</th>
      <th>Bowel obstruction</th>
      <th>Indigestion</th>
      <th>Opthalmia neonatorum</th>
      <th>Dehydration</th>
      <th>Umbilical infection</th>
      <th>Diarrhoea</th>
      <th>Disposition</th>
      <th>Date of 2nd Postnatal Visit</th>
      </tr>
      <xsl:for-each select="list/Post">
      <tr>
         <td><xsl:value-of select="postnatal_visit_601"/></td>
         <td><xsl:value-of select="infant_status"/></td>
         <td><xsl:value-of select="reasons_death_infant"/></td>
         <td><xsl:value-of select="other_reasons_death_infant"/></td>
         <td><xsl:value-of select="weight_679"/></td>
         <td><xsl:value-of select="temperature_infant_680"/></td>
         <td><xsl:value-of select="head_681"/></td>
         <td><xsl:value-of select="head_other_682"/></td>
         <td><xsl:value-of select="head_circumf_530"/></td>
         <td><xsl:value-of select="eyes_523"/></td>
         <td><xsl:value-of select="eyes_other_524"/></td>
         <td><xsl:value-of select="ears_686"/></td>
         <td><xsl:value-of select="ears_other_687"/></td>
         <td><xsl:value-of select="mouth_526"/></td>
         <td><xsl:value-of select="mouth_other_527"/></td>
         <td><xsl:value-of select="neck_690"/></td>
         <td><xsl:value-of select="neck_other_d_691"/></td>
         <td><xsl:value-of select="abdomen_692"/></td>
         <td><xsl:value-of select="abdomen_oth_693"/></td>
         <td><xsl:value-of select="cord_at_followup_694"/></td>
         <td><xsl:value-of select="cord_at_foll_desc695"/></td>
         <td><xsl:value-of select="genitalia_529"/></td>
         <td><xsl:value-of select="genitalia_other_697"/></td>
         <td><xsl:value-of select="anus_698"/></td>
         <td><xsl:value-of select="anus_other_699"/></td>
         <td><xsl:value-of select="skin_700"/></td>
         <td><xsl:value-of select="skin_other_701"/></td>
         <td><xsl:value-of select="upper_limbs_702"/></td>
         <td><xsl:value-of select="upper_limbs_other_703"/></td>
         <td><xsl:value-of select="lower_limbs_704"/></td>
         <td><xsl:value-of select="lower_limbs_other_705"/></td>
         <td><xsl:value-of select="back_536"/></td>
         <td><xsl:value-of select="back_other_537"/></td>
         <td><xsl:value-of select="neurological_708"/></td>
         <td><xsl:value-of select="neurological_other_709"/></td>
         <td><xsl:value-of select="opv1_given_week_6_710"/></td>
         <td><xsl:value-of select="dpt_1_given_week_6_711"/></td>
         <td><xsl:value-of select="bcg_given_712"/></td>
         <td><xsl:value-of select="feeding"/></td>
         <td><xsl:value-of select="feeding_type"/></td>
         <td><xsl:value-of select="immunization_554"/></td>
         <td><xsl:value-of select="immunization_1"/></td>
         <td><xsl:value-of select="immunization_2"/></td>
         <td><xsl:value-of select="immunization_3"/></td>
         <td><xsl:value-of select="immunization_4"/></td>
         <td><xsl:value-of select="immunization_5"/></td>
         <td><xsl:value-of select="patient_received_arv"/></td>
         <td><xsl:value-of select="is_problem"/></td>
         <td><xsl:value-of select="bowel_obstruction"/></td>
         <td><xsl:value-of select="indigestion"/></td>
         <td><xsl:value-of select="opthalmia_neonatorum"/></td>
         <td><xsl:value-of select="dehydration"/></td>
         <td><xsl:value-of select="umbilical_infection"/></td>
         <td><xsl:value-of select="diarrhoea"/></td>
         <td><xsl:value-of select="disposition"/></td>
         <td><xsl:value-of select="second_postnatal_visit_date"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>