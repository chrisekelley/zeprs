<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>InfantPhysicalExamReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Skull</th>
      <th>Eyes</th>
      <th>Eyes, Other, Describe</th>
      <th>Ears</th>
      <th>Ears, Other, Describe</th>
      <th>Mouth</th>
      <th>Mouth, Other, Describe</th>
      <th>Sucking</th>
      <th>Neck</th>
      <th>Neck Other Describe</th>
      <th>Breasts</th>
      <th>Chest</th>
      <th>Respiratory Effort</th>
      <th>Respiratory Effort, Other, Describe</th>
      <th>Abdomen - Liver</th>
      <th>Sole Creases</th>
      <th>Genitalia</th>
      <th>Genitalia, Other, Describe</th>
      <th>Spleen</th>
      <th>Kidney</th>
      <th>Fetal Cord Complication</th>
      <th>No. of Arteries</th>
      <th>Neonatal reflex - Grasp</th>
      <th>Symmetrical Moro Reaction</th>
      <th>Upper Extremities</th>
      <th>Upper Extremities, Other Describe</th>
      <th>Ortolani Sign</th>
      <th>Lower Extremities</th>
      <th>Lower Extremities, Other Describe</th>
      <th>Anomalies: CNS (Central Nervous System)</th>
      <th>Anomalies: CNS (Central Nervous System), Other, Describe</th>
      <th>Anomalies: Chromosomal</th>
      <th>Anomalies: Chromosomal, Other, Describe</th>
      <th>Anomalies: Cardiovascular</th>
      <th>Anomalies: Cardiovascular, Other Describe</th>
      <th>Anomalies: Genitourinary</th>
      <th>Anomalies: Genitourinary, Other, Describe</th>
      <th>Anomalies: Other</th>
      <th>Anomalies: Other, describe</th>
      <th>Injuries/Trauma</th>
      <th>Clinical age</th>
      <th>Hypoxic Ischaemic Encephalopathy (HIE)</th>
      <th>HIE Irritability</th>
      <th>HIE Hypontonia</th>
      <th>HIE Feeding</th>
      <th>HIE Seizures</th>
      <th>HIE Total Score</th>
      <th>Weight on Admission</th>
      <th>Temperature</th>
      <th>Head Circumference</th>
      <th>Pallor (Anemia)</th>
      <th>Cyanosis</th>
      <th>Jaundice</th>
      <th>Heart Rate</th>
      <th>Birth Weight</th>
      <th>Reason for Admission</th>
      <th>Reason for Admission, Other, Describe</th>
      </tr>
      <xsl:for-each select="list/Infa">
      <tr>
         <td><xsl:value-of select="skull__771R"/></td>
         <td><xsl:value-of select="eyes__523R"/></td>
         <td><xsl:value-of select="eyes__other__524R"/></td>
         <td><xsl:value-of select="ears__686R"/></td>
         <td><xsl:value-of select="ears__other__687R"/></td>
         <td><xsl:value-of select="mouth__526R"/></td>
         <td><xsl:value-of select="mouth__other__527R"/></td>
         <td><xsl:value-of select="sucking__528R"/></td>
         <td><xsl:value-of select="neck__690R"/></td>
         <td><xsl:value-of select="neck__other__d__691R"/></td>
         <td><xsl:value-of select="breasts__infant__780R"/></td>
         <td><xsl:value-of select="chest__infant__781R"/></td>
         <td><xsl:value-of select="respiratory__effort__782R"/></td>
         <td><xsl:value-of select="respiratory__effort__other__783R"/></td>
         <td><xsl:value-of select="abdomen__liver__784R"/></td>
         <td><xsl:value-of select="sole__creases__785R"/></td>
         <td><xsl:value-of select="genitalia__529R"/></td>
         <td><xsl:value-of select="genitalia__other__697R"/></td>
         <td><xsl:value-of select="spleen__infant__787R"/></td>
         <td><xsl:value-of select="kidney__infant__788R"/></td>
         <td><xsl:value-of select="cord__complic__789R"/></td>
         <td><xsl:value-of select="no__of__arteries__790R"/></td>
         <td><xsl:value-of select="neonatal__reflex__791R"/></td>
         <td><xsl:value-of select="symmetrical__moro__522R"/></td>
         <td><xsl:value-of select="upper__extrem__538R"/></td>
         <td><xsl:value-of select="upper__extrem__desc__539R"/></td>
         <td><xsl:value-of select="ortolani__sign__540R"/></td>
         <td><xsl:value-of select="lower__extrem__541R"/></td>
         <td><xsl:value-of select="lower__extrem__desc__542R"/></td>
         <td><xsl:value-of select="anomalies__cns__543R"/></td>
         <td><xsl:value-of select="anomalies__cns__desc__544R"/></td>
         <td><xsl:value-of select="anomalies__chromo__545R"/></td>
         <td><xsl:value-of select="anomalies__chromo__desc__546R"/></td>
         <td><xsl:value-of select="anomalies__cardio__547R"/></td>
         <td><xsl:value-of select="anomalies__cardio__desc__548R"/></td>
         <td><xsl:value-of select="anomalies__genitour__549R"/></td>
         <td><xsl:value-of select="anomalies__genitour__desc__550R"/></td>
         <td><xsl:value-of select="anomalies__other__551R"/></td>
         <td><xsl:value-of select="anomalies__ohter__1189R"/></td>
         <td><xsl:value-of select="injuries__trauma__807R"/></td>
         <td><xsl:value-of select="clinical__age__808R"/></td>
         <td><xsl:value-of select="hypoxic__isch__hie__809R"/></td>
         <td><xsl:value-of select="hie__irritabi__810R"/></td>
         <td><xsl:value-of select="hie__hyponton__811R"/></td>
         <td><xsl:value-of select="hie__feeding__812R"/></td>
         <td><xsl:value-of select="hie__seizures__813R"/></td>
         <td><xsl:value-of select="hie__total__sc__814R"/></td>
         <td><xsl:value-of select="weight__on__admission__763R"/></td>
         <td><xsl:value-of select="temperature__infant__680R"/></td>
         <td><xsl:value-of select="head__circumf__530R"/></td>
         <td><xsl:value-of select="pallor__766R"/></td>
         <td><xsl:value-of select="cyanosis__767R"/></td>
         <td><xsl:value-of select="jaundice__519R"/></td>
         <td><xsl:value-of select="heart__rate__769R"/></td>
         <td><xsl:value-of select="birth__weight__758R"/></td>
         <td><xsl:value-of select="reason__for__admission__759R"/></td>
         <td><xsl:value-of select="reason__for__admission__other__761R"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>