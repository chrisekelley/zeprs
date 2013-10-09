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
         <td><xsl:value-of select="dateLabRequest"/></td>
         <td><xsl:value-of select="labType"/></td>
         <td><xsl:value-of select="dateLabResults"/></td>
         <td><xsl:value-of select="results"/></td>
         <td><xsl:value-of select="resultsNumeric"/></td>
         <td><xsl:value-of select="reason_not_treating"/></td>
         <td><xsl:value-of select="treatment"/></td>
         <td><xsl:value-of select="comments"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>