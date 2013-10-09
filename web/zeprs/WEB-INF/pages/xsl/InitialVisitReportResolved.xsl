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
         <td><xsl:value-of select="height__159R"/></td>
         <td><xsl:value-of select="temperature__266R"/></td>
         <td><xsl:value-of select="heent__161R"/></td>
         <td><xsl:value-of select="heent__abnorm__162R"/></td>
         <td><xsl:value-of select="teeth__163R"/></td>
         <td><xsl:value-of select="teeth__other__164R"/></td>
         <td><xsl:value-of select="thyroid__165R"/></td>
         <td><xsl:value-of select="breasts__166R"/></td>
         <td><xsl:value-of select="respiratory__system__167R"/></td>
         <td><xsl:value-of select="respiratory__system__otherR"/></td>
         <td><xsl:value-of select="heart__169R"/></td>
         <td><xsl:value-of select="heart__other__170R"/></td>
         <td><xsl:value-of select="pulse__171R"/></td>
         <td><xsl:value-of select="abdomen__172R"/></td>
         <td><xsl:value-of select="abdomen__abnormal__173R"/></td>
         <td><xsl:value-of select="extremities__174R"/></td>
         <td><xsl:value-of select="extremities__abnormal__175R"/></td>
         <td><xsl:value-of select="skin__176R"/></td>
         <td><xsl:value-of select="skin__abnorm__177R"/></td>
         <td><xsl:value-of select="lymph__nodes__178R"/></td>
         <td><xsl:value-of select="rectum__179R"/></td>
         <td><xsl:value-of select="rectum__abnormal__180R"/></td>
         <td><xsl:value-of select="vulva__181R"/></td>
         <td><xsl:value-of select="vulva__abnormal__182R"/></td>
         <td><xsl:value-of select="vagina__183R"/></td>
         <td><xsl:value-of select="vagina__abnormal__184R"/></td>
         <td><xsl:value-of select="cervix__185R"/></td>
         <td><xsl:value-of select="cervix__abnormal__186R"/></td>
         <td><xsl:value-of select="uterus__187R"/></td>
         <td><xsl:value-of select="adnexa__189R"/></td>
         <td><xsl:value-of select="adnexa__abnormal__190R"/></td>
         <td><xsl:value-of select="varicosities__191R"/></td>
         <td><xsl:value-of select="pallor__193R"/></td>
         <td><xsl:value-of select="cns__192R"/></td>
         <td><xsl:value-of select="commentsR"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>