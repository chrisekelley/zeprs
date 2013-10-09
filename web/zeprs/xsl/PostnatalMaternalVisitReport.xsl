<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>PostnatalMaternalVisitReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Postnatal Visit</th>
      <th>Pulse</th>
      <th>BP: Systolic</th>
      <th>BP: Diastolic</th>
      <th>Hb</th>
      <th>Urinalysis</th>
      <th>Urinalysis Albumin</th>
      <th>Urinalysis Glucose</th>
      <th>Urinalysis Acetone</th>
      <th>Anti-D Given</th>
      <th>Other Complaints Describe</th>
      <th>Hair</th>
      <th>Eyes</th>
      <th>Eyes, Other, Describe</th>
      <th>Mouth</th>
      <th>Mouth: Other, Describe</th>
      <th>Teeth</th>
      <th>Teeth, Other Describe</th>
      <th>Thyroid</th>
      <th>Breasts</th>
      <th>Nipples</th>
      <th>Lymphadenopathy</th>
      <th>Lymphadenopathy: Yes, Describe</th>
      <th>Uterus</th>
      <th>Perineum</th>
      <th>Perineum : other, describe</th>
      <th>Perineum: infection, describe</th>
      <th>Anus</th>
      <th>Bowels</th>
      <th>Bowels, Abnormal, Describe</th>
      <th>Micturition</th>
      <th>Micturition, Abnormal, Describe</th>
      <th>Wound</th>
      <th>Wound, Abnormal, Describe</th>
      <th>Lochia Flow</th>
      <th>Lochia Colour</th>
      <th>Lochia Odor</th>
      <th>Legs</th>
      <th>Cervix Per Speculum</th>
      <th>Cervix per Speculum Result</th>
      <th>Did Patient receive ARV treatment?</th>
      <th>Problem?</th>
      <th>Contraceptive Advice Given</th>
      <th>Using Contraception</th>
      <th>Contraceptive Choice</th>
      <th>Contraceptive, Other, Describe</th>
      <th>Health Education Discussed</th>
      <th>Education1</th>
      <th>Education2</th>
      <th>Education3</th>
      <th>Education4</th>
      <th>Education5</th>
      <th>Education6</th>
      <th>Education7</th>
      <th>Health Education Discussed, Other, Describe</th>
      <th>Comments</th>
      </tr>
      <xsl:for-each select="list/Post">
      <tr>
         <td><xsl:value-of select="postnatal_visit_601"/></td>
         <td><xsl:value-of select="pulse_171"/></td>
         <td><xsl:value-of select="bp_systolic_224"/></td>
         <td><xsl:value-of select="bp_diastolic_225"/></td>
         <td><xsl:value-of select="hb_235"/></td>
         <td><xsl:value-of select="urinalysis_240"/></td>
         <td><xsl:value-of select="urinalysis_alb_242"/></td>
         <td><xsl:value-of select="urinalysis_glu_243"/></td>
         <td><xsl:value-of select="urinalysis_ace_244"/></td>
         <td><xsl:value-of select="anti_d_given_621"/></td>
         <td><xsl:value-of select="other_complaints_622"/></td>
         <td><xsl:value-of select="hair_625"/></td>
         <td><xsl:value-of select="eyes_626"/></td>
         <td><xsl:value-of select="eyes_other_627"/></td>
         <td><xsl:value-of select="mouth_628"/></td>
         <td><xsl:value-of select="mouth_other_1191"/></td>
         <td><xsl:value-of select="teeth_163"/></td>
         <td><xsl:value-of select="teeth_other_164"/></td>
         <td><xsl:value-of select="thyroid_165"/></td>
         <td><xsl:value-of select="breasts_166"/></td>
         <td><xsl:value-of select="nipples_633"/></td>
         <td><xsl:value-of select="lymphadenopa_1208"/></td>
         <td><xsl:value-of select="lymphadenopa_desc_1209"/></td>
         <td><xsl:value-of select="uterus_187"/></td>
         <td><xsl:value-of select="perineum_580"/></td>
         <td><xsl:value-of select="perineum_other_1199"/></td>
         <td><xsl:value-of select="perineum_infect_desc_1198"/></td>
         <td><xsl:value-of select="anus_638"/></td>
         <td><xsl:value-of select="bowels_639"/></td>
         <td><xsl:value-of select="bowels_abno_640"/></td>
         <td><xsl:value-of select="micturition_641"/></td>
         <td><xsl:value-of select="micturition_desc_642"/></td>
         <td><xsl:value-of select="wound_643"/></td>
         <td><xsl:value-of select="wound_abnor_644"/></td>
         <td><xsl:value-of select="lochia_flow_645"/></td>
         <td><xsl:value-of select="lochia_colou_646"/></td>
         <td><xsl:value-of select="lochia_odor_647"/></td>
         <td><xsl:value-of select="legs_649"/></td>
         <td><xsl:value-of select="cervix_per_spec_666"/></td>
         <td><xsl:value-of select="cervix_per_spec_result_667"/></td>
         <td><xsl:value-of select="patient_received_arv"/></td>
         <td><xsl:value-of select="is_problem"/></td>
         <td><xsl:value-of select="contraceptive_advice_669"/></td>
         <td><xsl:value-of select="using_contraception_670"/></td>
         <td><xsl:value-of select="contraceptive_choice_137"/></td>
         <td><xsl:value-of select="contraceptive_other_138"/></td>
         <td><xsl:value-of select="health_educa_discussed673"/></td>
         <td><xsl:value-of select="education1"/></td>
         <td><xsl:value-of select="education2"/></td>
         <td><xsl:value-of select="education3"/></td>
         <td><xsl:value-of select="education4"/></td>
         <td><xsl:value-of select="education5"/></td>
         <td><xsl:value-of select="education6"/></td>
         <td><xsl:value-of select="education7"/></td>
         <td><xsl:value-of select="health_educa_discussed_other_674"/></td>
         <td><xsl:value-of select="postnatal_comments"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>