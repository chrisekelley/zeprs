<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>CreateReferralReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Priority of Referral</th>
      <th>Comment</th>
      <th>Referring Encounter</th>
      </tr>
      <xsl:for-each select="list/Crea">
      <tr>
         <td><xsl:value-of select="priority_of_referral"/></td>
         <td><xsl:value-of select="comment"/></td>
         <td><xsl:value-of select="referring_encounter"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>