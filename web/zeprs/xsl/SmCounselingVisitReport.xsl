<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>SmCounselingVisitReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Date of Couseling</th>
      <th>Counselled</th>
      <th>HIV Tested</th>
      <th>Date of Test</th>
      <th>HIV Result</th>
      <th>Patient received results</th>
      </tr>
      <xsl:for-each select="list/SmCo">
      <tr>
         <td><xsl:value-of select="counseling_date"/></td>
         <td><xsl:value-of select="counselled"/></td>
         <td><xsl:value-of select="hiv_tested"/></td>
         <td><xsl:value-of select="testDate"/></td>
         <td><xsl:value-of select="hiv_test_result"/></td>
         <td><xsl:value-of select="patient_received_results"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>