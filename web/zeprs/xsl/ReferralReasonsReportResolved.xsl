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
         <td><xsl:value-of select="encounter__idR"/></td>
         <td><xsl:value-of select="false__labourR"/></td>
         <td><xsl:value-of select="true__laborR"/></td>
         <td><xsl:value-of select="rupture__of__membranesR"/></td>
         <td><xsl:value-of select="intact__membranesR"/></td>
         <td><xsl:value-of select="preeclamp__hypert__1265R"/></td>
         <td><xsl:value-of select="premature__labourR"/></td>
         <td><xsl:value-of select="malaria__diagR"/></td>
         <td><xsl:value-of select="anaemiaR"/></td>
         <td><xsl:value-of select="high__bp__diagR"/></td>
         <td><xsl:value-of select="vaginal__bleeding__diagR"/></td>
         <td><xsl:value-of select="intrauterine__deathR"/></td>
         <td><xsl:value-of select="uti__diagR"/></td>
         <td><xsl:value-of select="pneumonia__diagR"/></td>
         <td><xsl:value-of select="tb__diagR"/></td>
         <td><xsl:value-of select="vaginal__thrush__diagR"/></td>
         <td><xsl:value-of select="oral__thrush__diagR"/></td>
         <td><xsl:value-of select="eclampsiaR"/></td>
         <td><xsl:value-of select="abruptia__placentaR"/></td>
         <td><xsl:value-of select="miscarriageR"/></td>
         <td><xsl:value-of select="diag__otherR"/></td>
         <td><xsl:value-of select="broken__episiotomyR"/></td>
         <td><xsl:value-of select="puerperal__sepsisR"/></td>
         <td><xsl:value-of select="breast__engorgementR"/></td>
         <td><xsl:value-of select="secondary__pphR"/></td>
         <td><xsl:value-of select="mastitisR"/></td>
         <td><xsl:value-of select="breast__abscessR"/></td>
         <td><xsl:value-of select="bowel__obstructionR"/></td>
         <td><xsl:value-of select="indigestionR"/></td>
         <td><xsl:value-of select="opthalmia__neonatorumR"/></td>
         <td><xsl:value-of select="dehydrationR"/></td>
         <td><xsl:value-of select="umbilical__infectionR"/></td>
         <td><xsl:value-of select="diarrhoeaR"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>