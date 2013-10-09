<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>InitialVisitReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Height</th>
      <th>Temperature</th>
      <th>HE/ENT</th>
      <th>HE/ENT Abnormal, Describe</th>
      <th>Teeth</th>
      <th>Teeth, Other Describe</th>
      <th>Thyroid</th>
      <th>Breasts</th>
      <th>Respiratory System</th>
      <th>Respiratory System, Other, Describe</th>
      <th>Heart</th>
      <th>Heart, Other,Describe</th>
      <th>Pulse</th>
      <th>Abdomen</th>
      <th>Abdomen, Abnormal Describe</th>
      <th>Extremities</th>
      <th>Extremities, Abnormal Describe</th>
      <th>Skin</th>
      <th>Skin, Abnormal Describe</th>
      <th>Lymph Nodes</th>
      <th>Rectum</th>
      <th>Rectum, Abnormal Describe</th>
      <th>Vulva</th>
      <th>Vulva, Abnormal Describe</th>
      <th>Vagina</th>
      <th>Vagina, Abnormal Describe</th>
      <th>Cervix</th>
      <th>Cervix, Abnormal Describe</th>
      <th>Uterus</th>
      <th>Adnexa</th>
      <th>Adnexa, Abnormal Describe</th>
      <th>Varicosities</th>
      <th>Pallor (Anemia)</th>
      <th>CNS</th>
      <th>Comments</th>
      </tr>
      <xsl:for-each select="list/Init">
      <tr>
         <td><xsl:value-of select="height_159"/></td>
         <td><xsl:value-of select="temperature_266"/></td>
         <td><xsl:value-of select="heent_161"/></td>
         <td><xsl:value-of select="heent_abnorm_162"/></td>
         <td><xsl:value-of select="teeth_163"/></td>
         <td><xsl:value-of select="teeth_other_164"/></td>
         <td><xsl:value-of select="thyroid_165"/></td>
         <td><xsl:value-of select="breasts_166"/></td>
         <td><xsl:value-of select="respiratory_system_167"/></td>
         <td><xsl:value-of select="respiratory_system_other"/></td>
         <td><xsl:value-of select="heart_169"/></td>
         <td><xsl:value-of select="heart_other_170"/></td>
         <td><xsl:value-of select="pulse_171"/></td>
         <td><xsl:value-of select="abdomen_172"/></td>
         <td><xsl:value-of select="abdomen_abnormal_173"/></td>
         <td><xsl:value-of select="extremities_174"/></td>
         <td><xsl:value-of select="extremities_abnormal_175"/></td>
         <td><xsl:value-of select="skin_176"/></td>
         <td><xsl:value-of select="skin_abnorm_177"/></td>
         <td><xsl:value-of select="lymph_nodes_178"/></td>
         <td><xsl:value-of select="rectum_179"/></td>
         <td><xsl:value-of select="rectum_abnormal_180"/></td>
         <td><xsl:value-of select="vulva_181"/></td>
         <td><xsl:value-of select="vulva_abnormal_182"/></td>
         <td><xsl:value-of select="vagina_183"/></td>
         <td><xsl:value-of select="vagina_abnormal_184"/></td>
         <td><xsl:value-of select="cervix_185"/></td>
         <td><xsl:value-of select="cervix_abnormal_186"/></td>
         <td><xsl:value-of select="uterus_187"/></td>
         <td><xsl:value-of select="adnexa_189"/></td>
         <td><xsl:value-of select="adnexa_abnormal_190"/></td>
         <td><xsl:value-of select="varicosities_191"/></td>
         <td><xsl:value-of select="pallor_193"/></td>
         <td><xsl:value-of select="cns_192"/></td>
         <td><xsl:value-of select="comments"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>