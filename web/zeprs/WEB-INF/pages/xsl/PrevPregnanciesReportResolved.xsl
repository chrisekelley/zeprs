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
         <td><xsl:value-of select="zeprs__pregnancy__idR"/></td>
         <td><xsl:value-of select="year__of__delivery__51R"/></td>
         <td><xsl:value-of select="month__of__deliveryR"/></td>
         <td><xsl:value-of select="place__of__delivery__50R"/></td>
         <td><xsl:value-of select="place__delivery__otherR"/></td>
         <td><xsl:value-of select="pregnancy__course__52R"/></td>
         <td><xsl:value-of select="outcome__of__pregnancy__53R"/></td>
         <td><xsl:value-of select="if__died__before__5__years__54R"/></td>
         <td><xsl:value-of select="other__cause__death__55R"/></td>
         <td><xsl:value-of select="if__died__before__5__hiv__56R"/></td>
         <td><xsl:value-of select="if__tested__result__57R"/></td>
         <td><xsl:value-of select="mode__of__delivery__447R"/></td>
         <td><xsl:value-of select="type__of__labourR"/></td>
         <td><xsl:value-of select="indication__CS__forcepts__60R"/></td>
         <td><xsl:value-of select="duration__of__labour__62R"/></td>
         <td><xsl:value-of select="postpartum__i__66R"/></td>
         <td><xsl:value-of select="number__of__fetuses__63R"/></td>
         <td><xsl:value-of select="birth__weight__infant1__65R"/></td>
         <td><xsl:value-of select="sex__infant1R"/></td>
         <td><xsl:value-of select="birth__weight__infant__2__1244R"/></td>
         <td><xsl:value-of select="sex__infant2R"/></td>
         <td><xsl:value-of select="birth__weight__infant__3__1245R"/></td>
         <td><xsl:value-of select="sex__infant3R"/></td>
         <td><xsl:value-of select="eclampsiaR"/></td>
         <td><xsl:value-of select="pphR"/></td>
         <td><xsl:value-of select="aphR"/></td>
         <td><xsl:value-of select="commentsR"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>