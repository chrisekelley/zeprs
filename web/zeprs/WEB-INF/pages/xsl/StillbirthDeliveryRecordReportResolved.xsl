<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>StillbirthDeliveryRecordReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Date of Birth (infant)</th>
      <th>Time of Birth</th>
      <th>Weight at Stillbirth</th>
      <th>Estimate?</th>
      <th>EGA</th>
      <th>Last Reported Foetal Movement</th>
      <th>Date of Last Reported Foetal Movement</th>
      <th>Time of Day of Last Reported Foetal Movement</th>
      <th>Last Documented Foetal Heart Tone</th>
      <th>Date of Last Documented Foetal Heart Tone</th>
      <th>Time of Last Documented Foetal Heart Tone</th>
      <th>Foetal Death Time</th>
      <th>Foetal Death Estimated Date if Known</th>
      <th>Foetal Death Estimated Time if Known</th>
      <th>Type of Still Birth</th>
      <th>Physical Features</th>
      <th>Upper Extremities</th>
      <th>Upper Extremities, Other Describe</th>
      <th>Lower Extremities</th>
      <th>Lower Extremities, Other Describe</th>
      <th>Anomalies: CNS (Central Nervous System)</th>
      <th>Anomalies: CNS (Central Nervous System), Other, Describe</th>
      <th>Anomalies: Chromosomal</th>
      <th>Anomalies: Chromosomal, Other, Describe</th>
      <th>Anomalies: Genitourinary</th>
      <th>Anomalies: Genitourinary, Other, Describe</th>
      <th>Anomalies: Other</th>
      <th>Anomalies: Other, describe</th>
      <th>Injuries/Trauma</th>
      <th>Number of Vessels</th>
      <th>Fetal Cord Complication</th>
      <th>Cord Length Estimate</th>
      <th>Cord Prolapsed</th>
      <th>Velamentous Insertion</th>
      <th>Placenta Appearance</th>
      <th>Placenta Other Describe:</th>
      <th>Amniotic Fluid Volume</th>
      <th>Meconium Staining</th>
      <th>Foul Smell</th>
      <th>Provisional Cause of Death</th>
      <th>Provisional Cause of Death, Other, Describe</th>
      <th>Autopsy Requested</th>
      <th>Autopsy Consent</th>
      <th>Additional findings</th>
      </tr>
      <xsl:for-each select="list/Stil">
      <tr>
         <td><xsl:value-of select="date__of__birt__844R"/></td>
         <td><xsl:value-of select="time__of__birth__1514R"/></td>
         <td><xsl:value-of select="weight__at__stillbirth__846R"/></td>
         <td><xsl:value-of select="estimate__weightR"/></td>
         <td><xsl:value-of select="ega__129R"/></td>
         <td><xsl:value-of select="last__reported__foetal__movement__849R"/></td>
         <td><xsl:value-of select="date__of__lrfm__850R"/></td>
         <td><xsl:value-of select="time__of__lrfm__851R"/></td>
         <td><xsl:value-of select="last__foetal__heart__tone__852R"/></td>
         <td><xsl:value-of select="date__last__foetal__heart__tone__853R"/></td>
         <td><xsl:value-of select="time__last__foetal__heart__tone__854R"/></td>
         <td><xsl:value-of select="time__foetal__death__855R"/></td>
         <td><xsl:value-of select="est__date__foetal__death__856R"/></td>
         <td><xsl:value-of select="est__time__foetal__death__857R"/></td>
         <td><xsl:value-of select="type__of__still__birth__858R"/></td>
         <td><xsl:value-of select="physical__features__859R"/></td>
         <td><xsl:value-of select="upper__extrem__538R"/></td>
         <td><xsl:value-of select="upper__extrem__desc__539R"/></td>
         <td><xsl:value-of select="lower__extrem__541R"/></td>
         <td><xsl:value-of select="lower__extrem__desc__542R"/></td>
         <td><xsl:value-of select="anomalies__cns__543R"/></td>
         <td><xsl:value-of select="anomalies__cns__desc__544R"/></td>
         <td><xsl:value-of select="anomalies__chromo__545R"/></td>
         <td><xsl:value-of select="anomalies__chromo__desc__546R"/></td>
         <td><xsl:value-of select="anomalies__genitour__549R"/></td>
         <td><xsl:value-of select="anomalies__genitour__desc__550R"/></td>
         <td><xsl:value-of select="anomalies__other__551R"/></td>
         <td><xsl:value-of select="anomalies__ohter__1189R"/></td>
         <td><xsl:value-of select="injuries__trauma__807R"/></td>
         <td><xsl:value-of select="number__of__vessels__874R"/></td>
         <td><xsl:value-of select="cord__complic__789R"/></td>
         <td><xsl:value-of select="cord__length__est__876R"/></td>
         <td><xsl:value-of select="cord__prolapsed__877R"/></td>
         <td><xsl:value-of select="velamentous__insert__878R"/></td>
         <td><xsl:value-of select="placenta__appear__879R"/></td>
         <td><xsl:value-of select="placenta__other__880R"/></td>
         <td><xsl:value-of select="amniotic__fluid__vol__881R"/></td>
         <td><xsl:value-of select="meconium__staining__882R"/></td>
         <td><xsl:value-of select="foul__smell__883R"/></td>
         <td><xsl:value-of select="provisional__cause__death__886R"/></td>
         <td><xsl:value-of select="provisional__cause__death__other__887R"/></td>
         <td><xsl:value-of select="autopsy__requested__598R"/></td>
         <td><xsl:value-of select="autopsy__consent__599R"/></td>
         <td><xsl:value-of select="additional__findingsR"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>