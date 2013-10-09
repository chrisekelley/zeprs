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
         <td><xsl:value-of select="foetal__heart__rate__230R"/></td>
         <td><xsl:value-of select="pulse__171R"/></td>
         <td><xsl:value-of select="bp__systolic__224R"/></td>
         <td><xsl:value-of select="bp__diastolic__225R"/></td>
         <td><xsl:value-of select="temperature__266R"/></td>
         <td><xsl:value-of select="urinalysis__240R"/></td>
         <td><xsl:value-of select="urinalysis__alb__242R"/></td>
         <td><xsl:value-of select="urinalysis__glu__243R"/></td>
         <td><xsl:value-of select="urinalysis__ace__244R"/></td>
         <td><xsl:value-of select="contractions__367R"/></td>
         <td><xsl:value-of select="contraction__freq__10__mins__368R"/></td>
         <td><xsl:value-of select="cervix__dilatation325R"/></td>
         <td><xsl:value-of select="remarks__369R"/></td>
         <td><xsl:value-of select="diagnosisR"/></td>
         <td><xsl:value-of select="priority__of__referralR"/></td>
         <td><xsl:value-of select="transportR"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>