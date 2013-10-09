<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>LatentFirstStageLabourReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Foetal Heart Rate</th>
      <th>Pulse</th>
      <th>BP: Systolic</th>
      <th>BP: Diastolic</th>
      <th>Temperature</th>
      <th>Urinalysis</th>
      <th>Urinalysis Albumin</th>
      <th>Urinalysis Glucose</th>
      <th>Urinalysis Acetone</th>
      <th>Contractions</th>
      <th>Contraction Freq. Per 10 Minutes</th>
      <th>Cervix Dilatation</th>
      <th>Remarks</th>
      <th>Disposition</th>
      <th>Priority of Referral</th>
      <th>Transport</th>
      </tr>
      <xsl:for-each select="list/Late">
      <tr>
         <td><xsl:value-of select="foetal_heart_rate_230"/></td>
         <td><xsl:value-of select="pulse_171"/></td>
         <td><xsl:value-of select="bp_systolic_224"/></td>
         <td><xsl:value-of select="bp_diastolic_225"/></td>
         <td><xsl:value-of select="temperature_266"/></td>
         <td><xsl:value-of select="urinalysis_240"/></td>
         <td><xsl:value-of select="urinalysis_alb_242"/></td>
         <td><xsl:value-of select="urinalysis_glu_243"/></td>
         <td><xsl:value-of select="urinalysis_ace_244"/></td>
         <td><xsl:value-of select="contractions_367"/></td>
         <td><xsl:value-of select="contraction_freq_10_mins_368"/></td>
         <td><xsl:value-of select="cervix_dilatation325"/></td>
         <td><xsl:value-of select="remarks_369"/></td>
         <td><xsl:value-of select="diagnosis"/></td>
         <td><xsl:value-of select="priority_of_referral"/></td>
         <td><xsl:value-of select="transport"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>