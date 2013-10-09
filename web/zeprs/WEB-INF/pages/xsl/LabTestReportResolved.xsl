<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>LabTestReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Date of Lab Request</th>
      <th>Type of Lab</th>
      <th>Date of Lab Results</th>
      <th>Results</th>
      <th>Results</th>
      <th>Reasons for not treating</th>
      <th>Treatment</th>
      <th>Comments</th>
      </tr>
      <xsl:for-each select="list/LabT">
      <tr>
         <td><xsl:value-of select="dateLabRequestR"/></td>
         <td><xsl:value-of select="labTypeR"/></td>
         <td><xsl:value-of select="dateLabResultsR"/></td>
         <td><xsl:value-of select="resultsR"/></td>
         <td><xsl:value-of select="resultsNumericR"/></td>
         <td><xsl:value-of select="reason__not__treatingR"/></td>
         <td><xsl:value-of select="treatmentR"/></td>
         <td><xsl:value-of select="commentsR"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>