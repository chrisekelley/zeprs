<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>SafeMotherhoodCareReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Prior HIV Testing?</th>
      <th>Date</th>
      <th>HIV Result</th>
      <th>Regimen - baby dose</th>
      <th>Amount of dosage</th>
      <th>Regimen Delivery</th>
      <th>Syringe given</th>
      <th>Did Mother Administer Baby dose at Home?</th>
      <th>Received baby dose?</th>
      <th>Does patient sleep under an ITN?</th>
      <th>TT1 done?</th>
      <th>TT1 Date</th>
      <th>TT2 (1 mo.) Done?</th>
      <th>TT2 Date</th>
      <th>TT3 (7 mos.) Done?</th>
      <th>TT3 Date</th>
      <th>TT4 (19 mos.) Done?</th>
      <th>TT4 Date</th>
      <th>TT5 (31 mos.) Done?</th>
      <th>TT5 Date</th>
      <th>Maternal Childhood DPT/TT Immunisations</th>
      </tr>
      <xsl:for-each select="list/Safe">
      <tr>
         <td><xsl:value-of select="prior__hiv__testingR"/></td>
         <td><xsl:value-of select="prior__hiv__testing__dateR"/></td>
         <td><xsl:value-of select="hiv__test__resultR"/></td>
         <td><xsl:value-of select="rbd__home__regimenR"/></td>
         <td><xsl:value-of select="rbd__home__dosageR"/></td>
         <td><xsl:value-of select="regimen__deliveryR"/></td>
         <td><xsl:value-of select="rbd__homeR"/></td>
         <td><xsl:value-of select="rbd__home__administerredR"/></td>
         <td><xsl:value-of select="rbd__ldR"/></td>
         <td><xsl:value-of select="patient__sleep__ITNR"/></td>
         <td><xsl:value-of select="tt1__doneR"/></td>
         <td><xsl:value-of select="tt1__110R"/></td>
         <td><xsl:value-of select="tt2__doneR"/></td>
         <td><xsl:value-of select="tt2__111R"/></td>
         <td><xsl:value-of select="tt3__doneR"/></td>
         <td><xsl:value-of select="tt3__112R"/></td>
         <td><xsl:value-of select="tt4__doneR"/></td>
         <td><xsl:value-of select="tt4__113R"/></td>
         <td><xsl:value-of select="tt5__doneR"/></td>
         <td><xsl:value-of select="tt5__114R"/></td>
         <td><xsl:value-of select="childhood__dp__immun__107R"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>