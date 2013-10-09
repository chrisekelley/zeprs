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
         <td><xsl:value-of select="abo_group_193"/></td>
         <td><xsl:value-of select="no_abo_group_reason_194"/></td>
         <td><xsl:value-of select="no_abo_group_reason_desc_195"/></td>
         <td><xsl:value-of select="rhesus_196"/></td>
         <td><xsl:value-of select="no_rhesus_reason_197"/></td>
         <td><xsl:value-of select="no_rhesus_reason_desc_198"/></td>
         <td><xsl:value-of select="rpr_result_199"/></td>
         <td><xsl:value-of select="no_rpr_reason_200"/></td>
         <td><xsl:value-of select="no_rpr_reason_desc_201"/></td>
         <td><xsl:value-of select="if_reactive_treatment_202"/></td>
         <td><xsl:value-of select="treatment_other_203"/></td>
         <td><xsl:value-of select="hb_235"/></td>
         <td><xsl:value-of select="no_hb_reason_205"/></td>
         <td><xsl:value-of select="no_hb_reason_desc_206"/></td>
         <td><xsl:value-of select="cervical_smell_207"/></td>
         <td><xsl:value-of select="no_cervical_reason_208"/></td>
         <td><xsl:value-of select="no_cervical_reason_desc_209"/></td>
         <td><xsl:value-of select="accepts_hiv_today_210"/></td>
         <td><xsl:value-of select="hiv_result_211"/></td>
         <td><xsl:value-of select="sickle_cell_test"/></td>
         <td><xsl:value-of select="sickle_cell_screen"/></td>
         <td><xsl:value-of select="malaria_test"/></td>
         <td><xsl:value-of select="malaria_test_results"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>