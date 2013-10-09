<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>PostnatalMaternalVisitReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Postnatal Visit</th>
      <th>Pulse</th>
      <th>BP: Systolic</th>
      <th>BP: Diastolic</th>
      <th>Hb</th>
      <th>Urinalysis</th>
      <th>Urinalysis Albumin</th>
      <th>Urinalysis Glucose</th>
      <th>Urinalysis Acetone</th>
      <th>Anti-D Given</th>
      <th>Other Complaints Describe</th>
      <th>Hair</th>
      <th>Eyes</th>
      <th>Eyes, Other, Describe</th>
      <th>Mouth</th>
      <th>Mouth: Other, Describe</th>
      <th>Teeth</th>
      <th>Teeth, Other Describe</th>
      <th>Thyroid</th>
      <th>Breasts</th>
      <th>Nipples</th>
      <th>Lymphadenopathy</th>
      <th>Lymphadenopathy: Yes, Describe</th>
      <th>Uterus</th>
      <th>Perineum</th>
      <th>Perineum : other, describe</th>
      <th>Perineum: infection, describe</th>
      <th>Anus</th>
      <th>Bowels</th>
      <th>Bowels, Abnormal, Describe</th>
      <th>Micturition</th>
      <th>Micturition, Abnormal, Describe</th>
      <th>Wound</th>
      <th>Wound, Abnormal, Describe</th>
      <th>Lochia Flow</th>
      <th>Lochia Colour</th>
      <th>Lochia Odor</th>
      <th>Legs</th>
      <th>Cervix Per Speculum</th>
      <th>Cervix per Speculum Result</th>
      <th>Did Patient receive ARV treatment?</th>
      <th>Problem?</th>
      <th>Contraceptive Advice Given</th>
      <th>Using Contraception</th>
      <th>Contraceptive Choice</th>
      <th>Contraceptive, Other, Describe</th>
      <th>Health Education Discussed</th>
      <th>Education1</th>
      <th>Education2</th>
      <th>Education3</th>
      <th>Education4</th>
      <th>Education5</th>
      <th>Education6</th>
      <th>Education7</th>
      <th>Health Education Discussed, Other, Describe</th>
      <th>Comments</th>
      </tr>
      <xsl:for-each select="list/Post">
      <tr>
         <td><xsl:value-of select="postnatal__visit__601R"/></td>
         <td><xsl:value-of select="pulse__171R"/></td>
         <td><xsl:value-of select="bp__systolic__224R"/></td>
         <td><xsl:value-of select="bp__diastolic__225R"/></td>
         <td><xsl:value-of select="hb__235R"/></td>
         <td><xsl:value-of select="urinalysis__240R"/></td>
         <td><xsl:value-of select="urinalysis__alb__242R"/></td>
         <td><xsl:value-of select="urinalysis__glu__243R"/></td>
         <td><xsl:value-of select="urinalysis__ace__244R"/></td>
         <td><xsl:value-of select="anti__d__given__621R"/></td>
         <td><xsl:value-of select="other__complaints__622R"/></td>
         <td><xsl:value-of select="hair__625R"/></td>
         <td><xsl:value-of select="eyes__626R"/></td>
         <td><xsl:value-of select="eyes__other__627R"/></td>
         <td><xsl:value-of select="mouth__628R"/></td>
         <td><xsl:value-of select="mouth__other__1191R"/></td>
         <td><xsl:value-of select="teeth__163R"/></td>
         <td><xsl:value-of select="teeth__other__164R"/></td>
         <td><xsl:value-of select="thyroid__165R"/></td>
         <td><xsl:value-of select="breasts__166R"/></td>
         <td><xsl:value-of select="nipples__633R"/></td>
         <td><xsl:value-of select="lymphadenopa__1208R"/></td>
         <td><xsl:value-of select="lymphadenopa__desc__1209R"/></td>
         <td><xsl:value-of select="uterus__187R"/></td>
         <td><xsl:value-of select="perineum__580R"/></td>
         <td><xsl:value-of select="perineum__other__1199R"/></td>
         <td><xsl:value-of select="perineum__infect__desc__1198R"/></td>
         <td><xsl:value-of select="anus__638R"/></td>
         <td><xsl:value-of select="bowels__639R"/></td>
         <td><xsl:value-of select="bowels__abno__640R"/></td>
         <td><xsl:value-of select="micturition__641R"/></td>
         <td><xsl:value-of select="micturition__desc__642R"/></td>
         <td><xsl:value-of select="wound__643R"/></td>
         <td><xsl:value-of select="wound__abnor__644R"/></td>
         <td><xsl:value-of select="lochia__flow__645R"/></td>
         <td><xsl:value-of select="lochia__colou__646R"/></td>
         <td><xsl:value-of select="lochia__odor__647R"/></td>
         <td><xsl:value-of select="legs__649R"/></td>
         <td><xsl:value-of select="cervix__per__spec__666R"/></td>
         <td><xsl:value-of select="cervix__per__spec__result__667R"/></td>
         <td><xsl:value-of select="patient__received__arvR"/></td>
         <td><xsl:value-of select="is__problemR"/></td>
         <td><xsl:value-of select="contraceptive__advice__669R"/></td>
         <td><xsl:value-of select="using__contraception__670R"/></td>
         <td><xsl:value-of select="contraceptive__choice__137R"/></td>
         <td><xsl:value-of select="contraceptive__other__138R"/></td>
         <td><xsl:value-of select="health__educa__discussed673R"/></td>
         <td><xsl:value-of select="education1R"/></td>
         <td><xsl:value-of select="education2R"/></td>
         <td><xsl:value-of select="education3R"/></td>
         <td><xsl:value-of select="education4R"/></td>
         <td><xsl:value-of select="education5R"/></td>
         <td><xsl:value-of select="education6R"/></td>
         <td><xsl:value-of select="education7R"/></td>
         <td><xsl:value-of select="health__educa__discussed__other__674R"/></td>
         <td><xsl:value-of select="postnatal__commentsR"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>