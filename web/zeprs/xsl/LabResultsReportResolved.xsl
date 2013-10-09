<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>LabResultsReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>ABO Group</th>
      <th>If ABO Group not done, Reason</th>
      <th>If ABO Group not done, Reason, Describe</th>
      <th>Rhesus</th>
      <th>If Rhesus not done, Reason</th>
      <th>If Rhesus not done, Reason, Describe</th>
      <th>RPR Result</th>
      <th>If RPR not done, Reason</th>
      <th>If RPR not done, Reason, Describe</th>
      <th>If Reactive, Treatment</th>
      <th>Treatment, Other, Describe</th>
      <th>Hb</th>
      <th>If Hb not done, Reason</th>
      <th>If Hb not done, Reason, Describe</th>
      <th>Cervical Smear</th>
      <th>If Cervical Smear not done, Reason</th>
      <th>If Cervical Smear not done, Reason, Describe</th>
      <th>Accepts HIV Test Today</th>
      <th>HIV Result</th>
      <th>Sickle Cell Test</th>
      <th>Sickle Cell Screen</th>
      <th>Malaria Test</th>
      <th>Malaria Test Results</th>
      </tr>
      <xsl:for-each select="list/LabR">
      <tr>
         <td><xsl:value-of select="abo__group__193R"/></td>
         <td><xsl:value-of select="no__abo__group__reason__194R"/></td>
         <td><xsl:value-of select="no__abo__group__reason__desc__195R"/></td>
         <td><xsl:value-of select="rhesus__196R"/></td>
         <td><xsl:value-of select="no__rhesus__reason__197R"/></td>
         <td><xsl:value-of select="no__rhesus__reason__desc__198R"/></td>
         <td><xsl:value-of select="rpr__result__199R"/></td>
         <td><xsl:value-of select="no__rpr__reason__200R"/></td>
         <td><xsl:value-of select="no__rpr__reason__desc__201R"/></td>
         <td><xsl:value-of select="if__reactive__treatment__202R"/></td>
         <td><xsl:value-of select="treatment__other__203R"/></td>
         <td><xsl:value-of select="hb__235R"/></td>
         <td><xsl:value-of select="no__hb__reason__205R"/></td>
         <td><xsl:value-of select="no__hb__reason__desc__206R"/></td>
         <td><xsl:value-of select="cervical__smell__207R"/></td>
         <td><xsl:value-of select="no__cervical__reason__208R"/></td>
         <td><xsl:value-of select="no__cervical__reason__desc__209R"/></td>
         <td><xsl:value-of select="accepts__hiv__today__210R"/></td>
         <td><xsl:value-of select="hiv__result__211R"/></td>
         <td><xsl:value-of select="sickle__cell__testR"/></td>
         <td><xsl:value-of select="sickle__cell__screenR"/></td>
         <td><xsl:value-of select="malaria__testR"/></td>
         <td><xsl:value-of select="malaria__test__resultsR"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>