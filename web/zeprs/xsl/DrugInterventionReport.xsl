<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>DrugInterventionReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Date of Drug Intervention</th>
      <th>Type of Drug</th>
      <th>Dispensed?</th>
      <th>Reason Not Dispensed</th>
      <th>Drug Name</th>
      <th>Comments</th>
      </tr>
      <xsl:for-each select="list/Drug">
      <tr>
         <td><xsl:value-of select="dateDrugIntervention"/></td>
         <td><xsl:value-of select="drugType"/></td>
         <td><xsl:value-of select="dispensed"/></td>
         <td><xsl:value-of select="reason_not_dispensed"/></td>
         <td><xsl:value-of select="drugName"/></td>
         <td><xsl:value-of select="comments"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>