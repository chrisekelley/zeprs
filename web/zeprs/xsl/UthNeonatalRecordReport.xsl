<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>UthNeonatalRecordReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Time of Admission</th>
      <th>Date of Birth</th>
      <th>Time of Birth</th>
      <th>Infant Age</th>
      <th>Sex</th>
      <th>Age of Mother</th>
      <th>Age of Father</th>
      <th>Parity</th>
      <th>Mother's LMP</th>
      <th>EGA</th>
      <th>ABO Blood Group (Mother)</th>
      <th>Rhesus (Mother)</th>
      <th>VDRL (mother)</th>
      <th>Attending ANC</th>
      <th>Occupation</th>
      <th>Intrapartum History</th>
      <th>Mode of Delivery</th>
      <th>Indication for C/S  Forceps/Vacuum</th>
      <th>Place of Delivery</th>
      <th>Place of Delivery, Other, describe</th>
      <th>Rupture of Membranes Date</th>
      <th>Rupture of Membranes Time</th>
      <th>Duration of Labour 1st stage</th>
      <th>Duration of Labour 2nd stage</th>
      <th>Placenta Type</th>
      <th>Weight of Placenta</th>
      <th>Num. Fetuses this pregnancy</th>
      <th>If Multiple Birth</th>
      <th>Maternal Risk Factors</th>
      <th>Maternal Risk Factor 1</th>
      <th>Maternal Risk Factor 2</th>
      <th>Maternal Risk Factor 3</th>
      <th>Maternal Risk Factor 4</th>
      <th>Maternal Risk Factor 5</th>
      <th>Maternal Risk Factors, Other Describe:</th>
      </tr>
      <xsl:for-each select="list/UthN">
      <tr>
         <td><xsl:value-of select="time_of_admission_727"/></td>
         <td><xsl:value-of select="date_of_birth_728"/></td>
         <td><xsl:value-of select="time_of_birth_1514"/></td>
         <td><xsl:value-of select="infant_age_732"/></td>
         <td><xsl:value-of select="sex_490"/></td>
         <td><xsl:value-of select="age_of_mother_730"/></td>
         <td><xsl:value-of select="age_of_father_731"/></td>
         <td><xsl:value-of select="parity_734"/></td>
         <td><xsl:value-of select="mothers_lmp_735"/></td>
         <td><xsl:value-of select="ega_129"/></td>
         <td><xsl:value-of select="abo_blood_group_mother_738"/></td>
         <td><xsl:value-of select="rhesus_mother_739"/></td>
         <td><xsl:value-of select="vdrl_mother_740"/></td>
         <td><xsl:value-of select="attending_anc_741"/></td>
         <td><xsl:value-of select="occupation_12"/></td>
         <td><xsl:value-of select="intrapartum_history_743"/></td>
         <td><xsl:value-of select="mode_of_delivery_447"/></td>
         <td><xsl:value-of select="indication_CS_forcepts_60"/></td>
         <td><xsl:value-of select="place_of_delivery_50"/></td>
         <td><xsl:value-of select="place_delivery_other"/></td>
         <td><xsl:value-of select="rupture_of_membranes_date_328"/></td>
         <td><xsl:value-of select="rupture_of_membranes_time_329"/></td>
         <td><xsl:value-of select="duration_of_labour_1st_748"/></td>
         <td><xsl:value-of select="duration_of_labour_2nd_749"/></td>
         <td><xsl:value-of select="placenta_type_440"/></td>
         <td><xsl:value-of select="weight_of_placenta_441"/></td>
         <td><xsl:value-of select="number_of_fetuses_63"/></td>
         <td><xsl:value-of select="if_multiple_753"/></td>
         <td><xsl:value-of select="maternal_risk_factors_754"/></td>
         <td><xsl:value-of select="maternal_risk_factor_1"/></td>
         <td><xsl:value-of select="maternal_risk_factor_2"/></td>
         <td><xsl:value-of select="maternal_risk_factor_3"/></td>
         <td><xsl:value-of select="maternal_risk_factor_4"/></td>
         <td><xsl:value-of select="maternal_risk_factor_5"/></td>
         <td><xsl:value-of select="maternal_risk_other_755"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>