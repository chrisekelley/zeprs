<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>PrevPregnanciesReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>ZEPRS Records</th>
      <th>Year of Delivery</th>
      <th>Month of Delivery</th>
      <th>Place of Delivery</th>
      <th>Place of Delivery, Other, describe</th>
      <th>Pregnancy Course</th>
      <th>Outcome of Pregnancy</th>
      <th>If Died Before 5 Years, Cause</th>
      <th>Other Cause of Death: Describe</th>
      <th>If Died < 5 Yrs, baby HIV tested?</th>
      <th>If tested, result of test</th>
      <th>Mode of Delivery</th>
      <th>Type of Labor</th>
      <th>Indication for C/S  Forceps/Vacuum</th>
      <th>Duration of Labor</th>
      <th>Postpartum Infection</th>
      <th>Num. Fetuses this pregnancy</th>
      <th>Birth Weight Infant 1</th>
      <th>Sex Infant 1</th>
      <th>Birth Weight Infant 2</th>
      <th>Sex Infant 2</th>
      <th>Birth Weight Infant 3</th>
      <th>Sex Infant 3</th>
      <th>Eclampsia</th>
      <th>PPH</th>
      <th>APH</th>
      <th>Comments</th>
      </tr>
      <xsl:for-each select="list/Prev">
      <tr>
         <td><xsl:value-of select="zeprs_pregnancy_id"/></td>
         <td><xsl:value-of select="year_of_delivery_51"/></td>
         <td><xsl:value-of select="month_of_delivery"/></td>
         <td><xsl:value-of select="place_of_delivery_50"/></td>
         <td><xsl:value-of select="place_delivery_other"/></td>
         <td><xsl:value-of select="pregnancy_course_52"/></td>
         <td><xsl:value-of select="outcome_of_pregnancy_53"/></td>
         <td><xsl:value-of select="if_died_before_5_years_54"/></td>
         <td><xsl:value-of select="other_cause_death_55"/></td>
         <td><xsl:value-of select="if_died_before_5_hiv_56"/></td>
         <td><xsl:value-of select="if_tested_result_57"/></td>
         <td><xsl:value-of select="mode_of_delivery_447"/></td>
         <td><xsl:value-of select="type_of_labour"/></td>
         <td><xsl:value-of select="indication_CS_forcepts_60"/></td>
         <td><xsl:value-of select="duration_of_labour_62"/></td>
         <td><xsl:value-of select="postpartum_i_66"/></td>
         <td><xsl:value-of select="number_of_fetuses_63"/></td>
         <td><xsl:value-of select="birth_weight_infant1_65"/></td>
         <td><xsl:value-of select="sex_infant1"/></td>
         <td><xsl:value-of select="birth_weight_infant_2_1244"/></td>
         <td><xsl:value-of select="sex_infant2"/></td>
         <td><xsl:value-of select="birth_weight_infant_3_1245"/></td>
         <td><xsl:value-of select="sex_infant3"/></td>
         <td><xsl:value-of select="eclampsia"/></td>
         <td><xsl:value-of select="pph"/></td>
         <td><xsl:value-of select="aph"/></td>
         <td><xsl:value-of select="comments"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>