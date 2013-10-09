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
         <td><xsl:value-of select="prior_hiv_testing"/></td>
         <td><xsl:value-of select="prior_hiv_testing_date"/></td>
         <td><xsl:value-of select="hiv_test_result"/></td>
         <td><xsl:value-of select="rbd_home_regimen"/></td>
         <td><xsl:value-of select="rbd_home_dosage"/></td>
         <td><xsl:value-of select="regimen_delivery"/></td>
         <td><xsl:value-of select="rbd_home"/></td>
         <td><xsl:value-of select="rbd_home_administerred"/></td>
         <td><xsl:value-of select="rbd_ld"/></td>
         <td><xsl:value-of select="patient_sleep_ITN"/></td>
         <td><xsl:value-of select="tt1_done"/></td>
         <td><xsl:value-of select="tt1_110"/></td>
         <td><xsl:value-of select="tt2_done"/></td>
         <td><xsl:value-of select="tt2_111"/></td>
         <td><xsl:value-of select="tt3_done"/></td>
         <td><xsl:value-of select="tt3_112"/></td>
         <td><xsl:value-of select="tt4_done"/></td>
         <td><xsl:value-of select="tt4_113"/></td>
         <td><xsl:value-of select="tt5_done"/></td>
         <td><xsl:value-of select="tt5_114"/></td>
         <td><xsl:value-of select="childhood_dp_immun_107"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>