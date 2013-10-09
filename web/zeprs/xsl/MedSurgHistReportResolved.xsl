<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>MedSurgHistReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Mental Illness</th>
      <th>Epilepsy</th>
      <th>Thyroid</th>
      <th>Diabetes</th>
      <th>Diabetes, Years with</th>
      <th>Hypertension</th>
      <th>Heart Disease</th>
      <th>Rheumatic Heart Disease</th>
      <th>Tuberculosis</th>
      <th>Asthma</th>
      <th>Pneumonia</th>
      <th>Kidney Disease</th>
      <th>Pyelonephritis</th>
      <th>Liver Disease</th>
      <th>DVT/Clots</th>
      <th>Malaria</th>
      <th>Anaemia</th>
      <th>Sickle Cell Disease</th>
      <th>Genital Herpes</th>
      <th>GC</th>
      <th>Chlamydia</th>
      <th>HPV</th>
      <th>Hepatitis B</th>
      <th>Syphilis</th>
      <th>HIV</th>
      <th>Drug Allergies</th>
      <th>Drug Allergy Other Describe:</th>
      <th>Comments</th>
      <th>Currently taking medicine</th>
      <th>Prior Operations</th>
      <th>Appendicectomy</th>
      <th>If Appendectomy, Year</th>
      <th>C/S</th>
      <th>If C/S, Year</th>
      <th>Pelvic Operation</th>
      <th>If Pelvic Operation, Year</th>
      <th>If Pelvic Operation, Describe</th>
      <th>Transfusions</th>
      <th>Other Surgery, Incident 1</th>
      <th>Incident 1, Year</th>
      <th>Incident 1, Describe</th>
      <th>Other Surgery, Incident 2</th>
      <th>Incident 2, Year</th>
      <th>Incident 2, Describe</th>
      <th>Other Surgery, Incident 3</th>
      <th>Incident 3, Year</th>
      <th>Incident 3, Describe</th>
      <th>Other Surgery, Incident 4</th>
      <th>Incident 4, Year</th>
      <th>Incident 4, Describe</th>
      <th>Other Surgery, Incident 5</th>
      <th>Incident 5, Year</th>
      <th>Incident 5, Describe</th>
      </tr>
      <xsl:for-each select="list/MedS">
      <tr>
         <td><xsl:value-of select="mental__illness__69R"/></td>
         <td><xsl:value-of select="epilepsy__70R"/></td>
         <td><xsl:value-of select="thyroid__71R"/></td>
         <td><xsl:value-of select="diabetes__72R"/></td>
         <td><xsl:value-of select="diabetes__years__73R"/></td>
         <td><xsl:value-of select="hypertension__74R"/></td>
         <td><xsl:value-of select="heart__disease__75R"/></td>
         <td><xsl:value-of select="rheumatic__heart__disease__76R"/></td>
         <td><xsl:value-of select="tuberculosis__78R"/></td>
         <td><xsl:value-of select="asthma__79R"/></td>
         <td><xsl:value-of select="pneumonia__80R"/></td>
         <td><xsl:value-of select="kidney__disease__81R"/></td>
         <td><xsl:value-of select="pyelonephritis__82R"/></td>
         <td><xsl:value-of select="liver__disease__83R"/></td>
         <td><xsl:value-of select="dvt__clots__84R"/></td>
         <td><xsl:value-of select="malaria__85R"/></td>
         <td><xsl:value-of select="anaemia__1548R"/></td>
         <td><xsl:value-of select="sickle__cell__disease__87R"/></td>
         <td><xsl:value-of select="genital__herpes__89R"/></td>
         <td><xsl:value-of select="gc__90R"/></td>
         <td><xsl:value-of select="chlamydia__91R"/></td>
         <td><xsl:value-of select="hpv__92R"/></td>
         <td><xsl:value-of select="hepatitis__b__93R"/></td>
         <td><xsl:value-of select="syphilis__94R"/></td>
         <td><xsl:value-of select="hivR"/></td>
         <td><xsl:value-of select="drug__allergies__98R"/></td>
         <td><xsl:value-of select="drug__allergy__99R"/></td>
         <td><xsl:value-of select="comments__1249R"/></td>
         <td><xsl:value-of select="currently__taking__meds__95R"/></td>
         <td><xsl:value-of select="prior__operationsR"/></td>
         <td><xsl:value-of select="appendicectomy__116R"/></td>
         <td><xsl:value-of select="if__appendectomy__117R"/></td>
         <td><xsl:value-of select="csR"/></td>
         <td><xsl:value-of select="cs__yearR"/></td>
         <td><xsl:value-of select="pelvic__operation__118R"/></td>
         <td><xsl:value-of select="if__pelvic__op__year__119R"/></td>
         <td><xsl:value-of select="if__pelvic__op__descr__120R"/></td>
         <td><xsl:value-of select="transfusions__121R"/></td>
         <td><xsl:value-of select="other__surgery__1__1147R"/></td>
         <td><xsl:value-of select="incident__1__year__122R"/></td>
         <td><xsl:value-of select="incident__1__desc__123R"/></td>
         <td><xsl:value-of select="other__surgery__2__1148R"/></td>
         <td><xsl:value-of select="incident__2__year__1149R"/></td>
         <td><xsl:value-of select="incident__2__desc__1150R"/></td>
         <td><xsl:value-of select="other__surgery__3__1151R"/></td>
         <td><xsl:value-of select="incident__3__year__1152R"/></td>
         <td><xsl:value-of select="incident__3__desc__1153R"/></td>
         <td><xsl:value-of select="other__surger__1154R"/></td>
         <td><xsl:value-of select="incident__4__year__1155R"/></td>
         <td><xsl:value-of select="incident__4__desc__1156R"/></td>
         <td><xsl:value-of select="other__surgery__5__1157R"/></td>
         <td><xsl:value-of select="incident__5__year__1158R"/></td>
         <td><xsl:value-of select="incident__5__desc__1159R"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>