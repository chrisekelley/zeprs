<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>NicuSummaryReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Investigations</th>
      <th>Oxygen - not ventilated</th>
      <th>Mechanical Ventilation</th>
      <th>Antibiotics (list Antibiotics used below)</th>
      <th>Nevirapine</th>
      <th>Pressors</th>
      <th>Intravenous Fluids</th>
      <th>Umbilical Artery Catheter</th>
      <th>XS Transfusion</th>
      <th>Phototherapy</th>
      <th>Vitamin K</th>
      <th>Treatment: Other, Describe</th>
      <th>Treatment: Other, Descrbe</th>
      <th>Enter Antibiotics Used</th>
      <th>Did Patient receive ARV treatment?</th>
      <th>Infant Outcome</th>
      <th>If Death, Probable Cause</th>
      <th>Other Death Cause, Describe:</th>
      <th>If Death from prematurity, number of weeks.</th>
      <th>Autopsy Consent</th>
      <th>Discharge</th>
      <th>Infant is discharged to</th>
      <th>X-Ray</th>
      <th>X-Ray, Describe</th>
      <th>Baby Ultrasound</th>
      <th>Baby Ultrasound, Describe</th>
      <th>Echo-cardiogram</th>
      <th>Echo-cardiogram, Describe</th>
      <th>Diagnosis at Discharge</th>
      <th>Diagnosis at Discharge, Other, Describe</th>
      <th>Follow Up Comments</th>
      <th>Birth Record Given</th>
      <th>Treatment on Discharge</th>
      <th>Comments/Problems</th>
      <th>Date for 1st Postnatal Visit</th>
      <th>Place of 1st Postnatal Visit</th>
      <th>Date of Discharge</th>
      <th>Place of Next Visit</th>
      <th>Infant receives Vitamin K</th>
      <th>Infant receives tetracycline</th>
      <th>Referring encounter id</th>
      </tr>
      <xsl:for-each select="list/Nicu">
      <tr>
         <td><xsl:value-of select="investigationsR"/></td>
         <td><xsl:value-of select="oxygen__not__ventilatedR"/></td>
         <td><xsl:value-of select="mechanical__ventilationR"/></td>
         <td><xsl:value-of select="antibioticsR"/></td>
         <td><xsl:value-of select="nevirapineR"/></td>
         <td><xsl:value-of select="pressorsR"/></td>
         <td><xsl:value-of select="intravenous__fluidsR"/></td>
         <td><xsl:value-of select="umbilical__artery__catheterR"/></td>
         <td><xsl:value-of select="xs__transfusionR"/></td>
         <td><xsl:value-of select="phototherapyR"/></td>
         <td><xsl:value-of select="vitamin__kR"/></td>
         <td><xsl:value-of select="treatment__otherR"/></td>
         <td><xsl:value-of select="treatment__other__desc__818R"/></td>
         <td><xsl:value-of select="antibics__used__1184R"/></td>
         <td><xsl:value-of select="patient__received__arvR"/></td>
         <td><xsl:value-of select="infant__outcome__820R"/></td>
         <td><xsl:value-of select="if__death__probable__cause__821R"/></td>
         <td><xsl:value-of select="other__death__823R"/></td>
         <td><xsl:value-of select="if__death__premature__weeks__822R"/></td>
         <td><xsl:value-of select="autopsy__consent__599R"/></td>
         <td><xsl:value-of select="discharge__826R"/></td>
         <td><xsl:value-of select="place__infant__dischargedR"/></td>
         <td><xsl:value-of select="x__rayR"/></td>
         <td><xsl:value-of select="x__ray__descR"/></td>
         <td><xsl:value-of select="baby__ultrasoundR"/></td>
         <td><xsl:value-of select="baby__ultrasound__describeR"/></td>
         <td><xsl:value-of select="echo__cardiogramR"/></td>
         <td><xsl:value-of select="echo__cardiogram__describeR"/></td>
         <td><xsl:value-of select="diagnosis__at__discharge__827R"/></td>
         <td><xsl:value-of select="diagnosis__at__discharge__1185R"/></td>
         <td><xsl:value-of select="follow__up__co__829R"/></td>
         <td><xsl:value-of select="birth__record__given__561R"/></td>
         <td><xsl:value-of select="treatment__on__discharge__562R"/></td>
         <td><xsl:value-of select="problems__comments__557R"/></td>
         <td><xsl:value-of select="first__postnatal__visit__date__564R"/></td>
         <td><xsl:value-of select="first__postnatal__visit__place__565R"/></td>
         <td><xsl:value-of select="date__of__discharge__1268R"/></td>
         <td><xsl:value-of select="place__of__next__visit__1213R"/></td>
         <td><xsl:value-of select="receives__vitamine__kR"/></td>
         <td><xsl:value-of select="receives__tetracyclineR"/></td>
         <td><xsl:value-of select="referring__encounter__idR"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>