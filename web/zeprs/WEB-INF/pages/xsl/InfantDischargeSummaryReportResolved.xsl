<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>InfantDischargeSummaryReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Tactile</th>
      <th>Tactile Results</th>
      <th>Suction</th>
      <th>Suction Results</th>
      <th>Oxygen</th>
      <th>Oxygen Results</th>
      <th>Intubation</th>
      <th>Intubation Results</th>
      <th>Ephinephrine</th>
      <th>Ephinephrine Results</th>
      <th>Naxolone</th>
      <th>Naxolone Results</th>
      <th>Atropine</th>
      <th>Atropine Results</th>
      <th>Bag & Mask</th>
      <th>Bag & Mask Results</th>
      <th>Sodium Bicarbonate</th>
      <th>Sodium Bicarbonate Results</th>
      <th>Other Drugs</th>
      <th>Other Drugs, Results</th>
      <th>Feeding Quality</th>
      <th>Feeding Type</th>
      <th>Jaundice</th>
      <th>Good Head Control</th>
      <th>Good Grasp Reflex</th>
      <th>Symmetrical Moro Reaction</th>
      <th>Eyes</th>
      <th>Eyes, Other, Describe</th>
      <th>If Eyes Abnormal, Treatment</th>
      <th>Mouth</th>
      <th>Mouth, Other, Describe</th>
      <th>Sucking</th>
      <th>Genitalia</th>
      <th>Genitalia, Other, Describe</th>
      <th>Head Circumference</th>
      <th>Chest Circumference</th>
      <th>Crown-Heel Length</th>
      <th>Urine Passed</th>
      <th>Bowel Movement</th>
      <th>Back</th>
      <th>Back, Other, Describe</th>
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
      <th>Weight on Third Day</th>
      <th>Infant Immunizations Given</th>
      <th>Infant Immunization 1</th>
      <th>Infant Immunization 2</th>
      <th>Infant Immunization 3</th>
      <th>Infant Immunization 4</th>
      <th>Infant Immunization 5</th>
      <th>Immunisations, Other, Describe</th>
      <th>Did baby receive ARV treatment?</th>
      <th>Given Nevirapine Initial Dose?</th>
      <th>Regimen - baby dose</th>
      <th>Amount of dosage</th>
      <th>Infant receives Vitamin K</th>
      <th>Infant receives tetracycline</th>
      <th>Birth Record Given</th>
      <th>Treatment on Discharge</th>
      <th>Date for 1st Postnatal Visit</th>
      <th>Place of 1st Postnatal Visit</th>
      <th>Date of Discharge</th>
      <th>Comments/Problems</th>
      </tr>
      <xsl:for-each select="list/Infa">
      <tr>
         <td><xsl:value-of select="tactileR"/></td>
         <td><xsl:value-of select="tactile__resultsR"/></td>
         <td><xsl:value-of select="suctionR"/></td>
         <td><xsl:value-of select="suction__resultsR"/></td>
         <td><xsl:value-of select="oxygenR"/></td>
         <td><xsl:value-of select="oxygen__resultsR"/></td>
         <td><xsl:value-of select="intubationR"/></td>
         <td><xsl:value-of select="intubation__resultsR"/></td>
         <td><xsl:value-of select="ephinephrineR"/></td>
         <td><xsl:value-of select="ephinephrine__resultsR"/></td>
         <td><xsl:value-of select="naxoloneR"/></td>
         <td><xsl:value-of select="naxolone__resultsR"/></td>
         <td><xsl:value-of select="atropineR"/></td>
         <td><xsl:value-of select="atropine__resultsR"/></td>
         <td><xsl:value-of select="bag__and__maskR"/></td>
         <td><xsl:value-of select="bag__and__mask__resultsR"/></td>
         <td><xsl:value-of select="sodium__bcarbonateR"/></td>
         <td><xsl:value-of select="sodium__bcarbonate__resultsR"/></td>
         <td><xsl:value-of select="other__drugsR"/></td>
         <td><xsl:value-of select="other__drugs__resultsR"/></td>
         <td><xsl:value-of select="feedingR"/></td>
         <td><xsl:value-of select="feeding__typeR"/></td>
         <td><xsl:value-of select="jaundice__519R"/></td>
         <td><xsl:value-of select="good__head__control__520R"/></td>
         <td><xsl:value-of select="good__grasp__reflex__521R"/></td>
         <td><xsl:value-of select="symmetrical__moro__522R"/></td>
         <td><xsl:value-of select="eyes__523R"/></td>
         <td><xsl:value-of select="eyes__other__524R"/></td>
         <td><xsl:value-of select="if__eyes__abnormal__treatment__525R"/></td>
         <td><xsl:value-of select="mouth__526R"/></td>
         <td><xsl:value-of select="mouth__other__527R"/></td>
         <td><xsl:value-of select="sucking__528R"/></td>
         <td><xsl:value-of select="genitalia__529R"/></td>
         <td><xsl:value-of select="genitalia__other__697R"/></td>
         <td><xsl:value-of select="head__circumf__530R"/></td>
         <td><xsl:value-of select="chest__circum__531R"/></td>
         <td><xsl:value-of select="crown__heel__length__532R"/></td>
         <td><xsl:value-of select="urine__passed__1181R"/></td>
         <td><xsl:value-of select="bowel__movement__535R"/></td>
         <td><xsl:value-of select="back__536R"/></td>
         <td><xsl:value-of select="back__other__537R"/></td>
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
         <td><xsl:value-of select="weight__on__3rd__day__558R"/></td>
         <td><xsl:value-of select="immunization__554R"/></td>
         <td><xsl:value-of select="immunization__1R"/></td>
         <td><xsl:value-of select="immunization__2R"/></td>
         <td><xsl:value-of select="immunization__3R"/></td>
         <td><xsl:value-of select="immunization__4R"/></td>
         <td><xsl:value-of select="immunization__5R"/></td>
         <td><xsl:value-of select="immunisation__desc__556R"/></td>
         <td><xsl:value-of select="baby__received__arvR"/></td>
         <td><xsl:value-of select="initial__nevirapine__doseR"/></td>
         <td><xsl:value-of select="rbd__home__regimenR"/></td>
         <td><xsl:value-of select="rbd__home__dosageR"/></td>
         <td><xsl:value-of select="receives__vitamine__kR"/></td>
         <td><xsl:value-of select="receives__tetracyclineR"/></td>
         <td><xsl:value-of select="birth__record__given__561R"/></td>
         <td><xsl:value-of select="treatment__on__discharge__562R"/></td>
         <td><xsl:value-of select="first__postnatal__visit__date__564R"/></td>
         <td><xsl:value-of select="first__postnatal__visit__place__565R"/></td>
         <td><xsl:value-of select="date__of__discharge__1268R"/></td>
         <td><xsl:value-of select="problems__comments__557R"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>