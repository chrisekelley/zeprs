<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>PregnancyDatingReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Planned Pregnancy</th>
      <th>If Unplanned, Contraception Practiced?</th>
      <th>Contraceptive Choice</th>
      <th>Contraceptive, Other, Describe</th>
      <th>Reliability of LMP</th>
      <th>LMP</th>
      <th>EDD</th>
      <th>EGA</th>
      <th>Quickening</th>
      <th>Menstrual History</th>
      <th>Cycle in Days</th>
      <th>Uterus, Size in Days</th>
      <th>Dating Method</th>
      </tr>
      <xsl:for-each select="list/Preg">
      <tr>
         <td><xsl:value-of select="planned_preg_135"/></td>
         <td><xsl:value-of select="contracept_practiced_136"/></td>
         <td><xsl:value-of select="contraceptive_choice_137"/></td>
         <td><xsl:value-of select="contraceptive_other_138"/></td>
         <td><xsl:value-of select="lmp_reliability_126"/></td>
         <td><xsl:value-of select="lmp_127"/></td>
         <td><xsl:value-of select="edd_128"/></td>
         <td><xsl:value-of select="ega_129"/></td>
         <td><xsl:value-of select="quickening_130"/></td>
         <td><xsl:value-of select="menstrual_history_131"/></td>
         <td><xsl:value-of select="cycle_in_days_132"/></td>
         <td><xsl:value-of select="uterus_size_in_days_188"/></td>
         <td><xsl:value-of select="dating_method"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>