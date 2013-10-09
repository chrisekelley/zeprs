<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>DeliverySumReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Latent phase duration</th>
      <th>Labour Onset</th>
      <th>If Induced: Mode of Induction</th>
      <th>Indication for Induction</th>
      <th>Indication for Induction, Other Describe</th>
      <th>Membranes Ruptured</th>
      <th>Rupture of Membranes Date</th>
      <th>Rupture of Membranes Time</th>
      <th>Liquor</th>
      <th>Foul Smell</th>
      <th>Did mother receive ARV treatment?</th>
      <th>Given NVP Tablets</th>
      <th>NVP Amount Given</th>
      <th>1st Stage Began - Date</th>
      <th>1st Stage Began - Time</th>
      <th>Complete Dilitation - Date</th>
      <th>Complete Dilitation - Time</th>
      <th>Abnormality at Dilitation</th>
      <th>Abnormality at Delivery</th>
      <th>Labour Augmented</th>
      <th>Bladder Emptied</th>
      <th>Placenta Delivered - Date</th>
      <th>Placenta Delivered - Time</th>
      <th>Placenta Delivery Method</th>
      <th>Mode of Delivery</th>
      <th>Mode of Delivery of C/S</th>
      <th>Indication for C/S  Forceps/Vacuum</th>
      <th>Indication for C/S..Other, describe</th>
      <th>Anesthesia for Delivery</th>
      <th>Episiotomy Performed</th>
      <th>Genital Laceration</th>
      <th>Episiotomy Extension / Genital Laceration</th>
      <th>Anterior Laceration Depth</th>
      <th>Anterior Laceration Sutured</th>
      <th>Posterior Laceration Depth</th>
      <th>Posterior Laceration Sutured</th>
      <th>Cervical Lacerations</th>
      <th>Cervical Lacerations Sutured?</th>
      <th>PPH</th>
      <th>Blood Loss Estimated</th>
      <th>Postpartum Haemorrhage (PPH) Treatment</th>
      <th>If Blood Transfusion selected for PPH Treatment</th>
      <th>Drugs or IV Fluids used in PPH Treatment</th>
      <th>If IV Fluid, Volume</th>
      <th>Treatment Medical Other Describe:</th>
      <th>Urine passed postpartum</th>
      <th>Bowel Movement postpartum</th>
      <th>Complications</th>
      <th>If Complications, Describe</th>
      <th>Colour of Placenta</th>
      <th>Presence of Infarcts</th>
      <th>Presence of Clots</th>
      <th>Extension of Blood Vessels into Membranes</th>
      <th>Other Placenta Abnormalities</th>
      <th>Haemorrhage in Relation to Placenta</th>
      <th>Placenta Type</th>
      <th>State of Placenta</th>
      <th>Weight of Placenta</th>
      <th>Mode of Cord Insertion</th>
      <th>Blood Vessels in Cord</th>
      </tr>
      <xsl:for-each select="list/Deli">
      <tr>
         <td><xsl:value-of select="latent__phase__duration__421R"/></td>
         <td><xsl:value-of select="labour__onset__422R"/></td>
         <td><xsl:value-of select="if__induced__mode__423R"/></td>
         <td><xsl:value-of select="indication__induction__424R"/></td>
         <td><xsl:value-of select="indication__induction__desc__425R"/></td>
         <td><xsl:value-of select="membranes__re__330R"/></td>
         <td><xsl:value-of select="rupture__of__membranes__date__328R"/></td>
         <td><xsl:value-of select="rupture__of__membranes__time__329R"/></td>
         <td><xsl:value-of select="liquor__331R"/></td>
         <td><xsl:value-of select="foul__smell__278R"/></td>
         <td><xsl:value-of select="mother__received__arvR"/></td>
         <td><xsl:value-of select="given__nvp__tablets__1223R"/></td>
         <td><xsl:value-of select="nvp__amount__1224R"/></td>
         <td><xsl:value-of select="date__first__stage__beganR"/></td>
         <td><xsl:value-of select="first__stage__began__431R"/></td>
         <td><xsl:value-of select="date__complete__dilitationR"/></td>
         <td><xsl:value-of select="complete__dilitation__432R"/></td>
         <td><xsl:value-of select="abnormality__at__dilatation__433R"/></td>
         <td><xsl:value-of select="abnormality__at__delivery__435R"/></td>
         <td><xsl:value-of select="labour__augmented__436R"/></td>
         <td><xsl:value-of select="bladder__emptied__437R"/></td>
         <td><xsl:value-of select="date__placenta__deliveredR"/></td>
         <td><xsl:value-of select="placenta__delivered__438R"/></td>
         <td><xsl:value-of select="placenta__delivery__method__439R"/></td>
         <td><xsl:value-of select="mode__of__delivery__447R"/></td>
         <td><xsl:value-of select="mode__of__delivery__cs__448R"/></td>
         <td><xsl:value-of select="indication__CS__forcepts__60R"/></td>
         <td><xsl:value-of select="indication__CS__forcepts__desc__61R"/></td>
         <td><xsl:value-of select="anesthesia__delivery__451R"/></td>
         <td><xsl:value-of select="episiotomy__performed__452R"/></td>
         <td><xsl:value-of select="genital__laceration__453R"/></td>
         <td><xsl:value-of select="episiotomy__extension__454R"/></td>
         <td><xsl:value-of select="anterior__laceration__depth__455R"/></td>
         <td><xsl:value-of select="anterior__laceration__sutured__456R"/></td>
         <td><xsl:value-of select="posterior__laceration__depth__457R"/></td>
         <td><xsl:value-of select="posterior__laceration__sutured__458R"/></td>
         <td><xsl:value-of select="cervical__laceration__459R"/></td>
         <td><xsl:value-of select="cervical__laceration__sutured__460R"/></td>
         <td><xsl:value-of select="pphR"/></td>
         <td><xsl:value-of select="blood__loss__est__462R"/></td>
         <td><xsl:value-of select="pph__treatment__463R"/></td>
         <td><xsl:value-of select="if__blood__transfusion__1177R"/></td>
         <td><xsl:value-of select="drugs__or__iv__fluids__1178R"/></td>
         <td><xsl:value-of select="iv__fluid__volume__1179R"/></td>
         <td><xsl:value-of select="treatment__method__desc__464R"/></td>
         <td><xsl:value-of select="urine__passed__465R"/></td>
         <td><xsl:value-of select="bowel__movement__postpartum__466R"/></td>
         <td><xsl:value-of select="complications__467R"/></td>
         <td><xsl:value-of select="if__complications__desc__468R"/></td>
         <td><xsl:value-of select="colour__of__placenta__470R"/></td>
         <td><xsl:value-of select="presence__of__infarcts__471R"/></td>
         <td><xsl:value-of select="presence__of__clots__472R"/></td>
         <td><xsl:value-of select="extension__of__vessels__473R"/></td>
         <td><xsl:value-of select="other__placenta__abnormal__474R"/></td>
         <td><xsl:value-of select="haemorrhage__irt__placenta__475R"/></td>
         <td><xsl:value-of select="placenta__type__440R"/></td>
         <td><xsl:value-of select="state__of__placenta__1204R"/></td>
         <td><xsl:value-of select="weight__of__placenta__441R"/></td>
         <td><xsl:value-of select="mode__of__cord__insert__442R"/></td>
         <td><xsl:value-of select="blood__vessels__in__cordR"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>