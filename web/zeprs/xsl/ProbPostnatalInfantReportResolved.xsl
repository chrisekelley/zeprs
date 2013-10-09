<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>ProbPostnatalInfantReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Postnatal Visit</th>
      <th>Infant Status</th>
      <th>Reasons for Infant Death</th>
      <th>Reasons for Infant Death: Other, Describe</th>
      <th>Weight (infant)</th>
      <th>Temperature</th>
      <th>Head</th>
      <th>Head, Other, Describe</th>
      <th>Head Circumference</th>
      <th>Eyes</th>
      <th>Eyes, Other, Describe</th>
      <th>Ears</th>
      <th>Ears, Other, Describe</th>
      <th>Mouth</th>
      <th>Mouth, Other, Describe</th>
      <th>Neck</th>
      <th>Neck Other Describe</th>
      <th>Abdomen</th>
      <th>Abdomen, Other Describe</th>
      <th>Cord at Followup</th>
      <th>Cord at Followup, Other, Describe</th>
      <th>Genitalia</th>
      <th>Genitalia, Other, Describe</th>
      <th>Anus</th>
      <th>Anus, Other Describe</th>
      <th>Skin</th>
      <th>Skin, Other Describe</th>
      <th>Upper Limbs</th>
      <th>Upper Limbs, Other Describe</th>
      <th>Lower Limbs</th>
      <th>Lower Limbs, Other Describe</th>
      <th>Back</th>
      <th>Back, Other, Describe</th>
      <th>Neurological Examination</th>
      <th>Neurological Examination, Other, Describe</th>
      <th>OPV1 Given at Week 6</th>
      <th>DPT 1 given at Week 6</th>
      <th>BCG Given this visit?</th>
      <th>Feeding Quality</th>
      <th>Feeding Type</th>
      <th>Sleeping pattern of the infant</th>
      <th>Passing Urine</th>
      <th>Passing Stool</th>
      <th>Infant Immunizations Given</th>
      <th>Infant Immunization 1</th>
      <th>Infant Immunization 2</th>
      <th>Infant Immunization 3</th>
      <th>Infant Immunization 4</th>
      <th>Infant Immunization 5</th>
      <th>Jaundice</th>
      <th>Fever</th>
      <th>Skin rashes</th>
      <th>Eye discharge</th>
      <th>Abdominal distension</th>
      <th>Excessive crying</th>
      <th>Cord problem</th>
      <th>Reasons: Other</th>
      <th>Reasons: Other, Describe</th>
      <th>Bowel obstruction</th>
      <th>Indigestion</th>
      <th>Opthalmia neonatorum</th>
      <th>Dehydration</th>
      <th>Umbilical infection</th>
      <th>Diarrhoea</th>
      <th>Disposition</th>
      <th>UTH Ward</th>
      <th>Priority of Referral</th>
      <th>Transport</th>
      </tr>
      <xsl:for-each select="list/Prob">
      <tr>
         <td><xsl:value-of select="postnatal__visit__601R"/></td>
         <td><xsl:value-of select="infant__statusR"/></td>
         <td><xsl:value-of select="reasons__death__infantR"/></td>
         <td><xsl:value-of select="other__reasons__death__infantR"/></td>
         <td><xsl:value-of select="weight__679R"/></td>
         <td><xsl:value-of select="temperature__infant__680R"/></td>
         <td><xsl:value-of select="head__681R"/></td>
         <td><xsl:value-of select="head__other__682R"/></td>
         <td><xsl:value-of select="head__circumf__530R"/></td>
         <td><xsl:value-of select="eyes__523R"/></td>
         <td><xsl:value-of select="eyes__other__524R"/></td>
         <td><xsl:value-of select="ears__686R"/></td>
         <td><xsl:value-of select="ears__other__687R"/></td>
         <td><xsl:value-of select="mouth__526R"/></td>
         <td><xsl:value-of select="mouth__other__527R"/></td>
         <td><xsl:value-of select="neck__690R"/></td>
         <td><xsl:value-of select="neck__other__d__691R"/></td>
         <td><xsl:value-of select="abdomen__692R"/></td>
         <td><xsl:value-of select="abdomen__oth__693R"/></td>
         <td><xsl:value-of select="cord__at__followup__694R"/></td>
         <td><xsl:value-of select="cord__at__foll__desc695R"/></td>
         <td><xsl:value-of select="genitalia__529R"/></td>
         <td><xsl:value-of select="genitalia__other__697R"/></td>
         <td><xsl:value-of select="anus__698R"/></td>
         <td><xsl:value-of select="anus__other__699R"/></td>
         <td><xsl:value-of select="skin__700R"/></td>
         <td><xsl:value-of select="skin__other__701R"/></td>
         <td><xsl:value-of select="upper__limbs__702R"/></td>
         <td><xsl:value-of select="upper__limbs__other__703R"/></td>
         <td><xsl:value-of select="lower__limbs__704R"/></td>
         <td><xsl:value-of select="lower__limbs__other__705R"/></td>
         <td><xsl:value-of select="back__536R"/></td>
         <td><xsl:value-of select="back__other__537R"/></td>
         <td><xsl:value-of select="neurological__708R"/></td>
         <td><xsl:value-of select="neurological__other__709R"/></td>
         <td><xsl:value-of select="opv1__given__week__6__710R"/></td>
         <td><xsl:value-of select="dpt__1__given__week__6__711R"/></td>
         <td><xsl:value-of select="bcg__given__712R"/></td>
         <td><xsl:value-of select="feedingR"/></td>
         <td><xsl:value-of select="feeding__typeR"/></td>
         <td><xsl:value-of select="infant__sleeping__patternR"/></td>
         <td><xsl:value-of select="passing__urineR"/></td>
         <td><xsl:value-of select="passing__stoolR"/></td>
         <td><xsl:value-of select="immunization__554R"/></td>
         <td><xsl:value-of select="immunization__1R"/></td>
         <td><xsl:value-of select="immunization__2R"/></td>
         <td><xsl:value-of select="immunization__3R"/></td>
         <td><xsl:value-of select="immunization__4R"/></td>
         <td><xsl:value-of select="immunization__5R"/></td>
         <td><xsl:value-of select="jaundiceR"/></td>
         <td><xsl:value-of select="feverR"/></td>
         <td><xsl:value-of select="skin__rashesR"/></td>
         <td><xsl:value-of select="eye__dischargeR"/></td>
         <td><xsl:value-of select="abdominal__distensionR"/></td>
         <td><xsl:value-of select="excessive__cryingR"/></td>
         <td><xsl:value-of select="cord__problemR"/></td>
         <td><xsl:value-of select="other__reasonsR"/></td>
         <td><xsl:value-of select="reasons__other__describeR"/></td>
         <td><xsl:value-of select="bowel__obstructionR"/></td>
         <td><xsl:value-of select="indigestionR"/></td>
         <td><xsl:value-of select="opthalmia__neonatorumR"/></td>
         <td><xsl:value-of select="dehydrationR"/></td>
         <td><xsl:value-of select="umbilical__infectionR"/></td>
         <td><xsl:value-of select="diarrhoeaR"/></td>
         <td><xsl:value-of select="dispositionR"/></td>
         <td><xsl:value-of select="uth__wardR"/></td>
         <td><xsl:value-of select="priority__of__referralR"/></td>
         <td><xsl:value-of select="transportR"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>