<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>ReferralReasonsReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Encounter id</th>
      <th>False labour</th>
      <th>True labor</th>
      <th>Rupture of Membranes</th>
      <th>Intact membranes</th>
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
      <th>Diagnosis, Other, describe</th>
      <th>Broken episiotomy</th>
      <th>Puerperal sepsis</th>
      <th>Breast engorgement</th>
      <th>Secondary PPH</th>
      <th>Mastitis</th>
      <th>Breast abscess</th>
      <th>Bowel obstruction</th>
      <th>Indigestion</th>
      <th>Opthalmia neonatorum</th>
      <th>Dehydration</th>
      <th>Umbilical infection</th>
      <th>Diarrhoea</th>
      </tr>
      <xsl:for-each select="list/Refe">
      <tr>
         <td><xsl:value-of select="encounter_id"/></td>
         <td><xsl:value-of select="false_labour"/></td>
         <td><xsl:value-of select="true_labor"/></td>
         <td><xsl:value-of select="rupture_of_membranes"/></td>
         <td><xsl:value-of select="intact_membranes"/></td>
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
         <td><xsl:value-of select="diag_other"/></td>
         <td><xsl:value-of select="broken_episiotomy"/></td>
         <td><xsl:value-of select="puerperal_sepsis"/></td>
         <td><xsl:value-of select="breast_engorgement"/></td>
         <td><xsl:value-of select="secondary_pph"/></td>
         <td><xsl:value-of select="mastitis"/></td>
         <td><xsl:value-of select="breast_abscess"/></td>
         <td><xsl:value-of select="bowel_obstruction"/></td>
         <td><xsl:value-of select="indigestion"/></td>
         <td><xsl:value-of select="opthalmia_neonatorum"/></td>
         <td><xsl:value-of select="dehydration"/></td>
         <td><xsl:value-of select="umbilical_infection"/></td>
         <td><xsl:value-of select="diarrhoea"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>