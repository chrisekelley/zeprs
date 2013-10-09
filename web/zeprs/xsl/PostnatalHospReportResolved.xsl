<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>PostnatalHospReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Date of Discharge</th>
      <th>Hospital Ward</th>
      <th>Anaemia</th>
      <th>Anaemia: Specify</th>
      <th>Cardiac disease</th>
      <th>Diabetes: Gestational</th>
      <th>Diabetes: Pregestational</th>
      <th>Dysentary</th>
      <th>Epilepsy</th>
      <th>Gastroenteritis</th>
      <th>Hemoglobinopathy: Sickle cell disease</th>
      <th>Hemoglobinopathy: Thallasemia</th>
      <th>Hepatitis</th>
      <th>Hypertensive disorder</th>
      <th>Malaria suspected</th>
      <th>Polyarthritis</th>
      <th>Psychosis</th>
      <th>Tuberculosis</th>
      <th>Draining</th>
      <th>Prolonged first stage</th>
      <th>CPD</th>
      <th>PPH</th>
      <th>Ruptured placenta</th>
      <th>Sepsis</th>
      <th>Prolonged labor</th>
      <th>Ruptured uterus</th>
      <th>Cervical tears</th>
      <th>Multiple pregnancy</th>
      <th>Malpresentations</th>
      <th>Intrapartum foetal distress</th>
      <th>Previous C/S</th>
      <th>Broken episotum</th>
      <th>Dizziness</th>
      <th>Fever</th>
      <th>Psychosis</th>
      <th>Hypertensive Disorders</th>
      <th>Diagnosis, Other, describe</th>
      <th>Drug 1</th>
      <th>Drug 2</th>
      <th>Drug 3</th>
      <th>Drug 4</th>
      <th>Drug 5</th>
      <th>Drug 6</th>
      <th>Drug 7</th>
      <th>Drug 8</th>
      <th>Drug 9</th>
      <th>Drug 10</th>
      <th>Medical Treatments: Other, Describe</th>
      <th>MVA</th>
      <th>Dilatation and Curettage</th>
      <th>Laparotomy</th>
      <th>Hysterectomy</th>
      <th>Salpingostomy</th>
      <th>Episiotomy suture</th>
      <th>C/S</th>
      <th>BTL</th>
      <th>Instrumental delivery</th>
      <th>Blood transfusion</th>
      <th>Surgical Treatments: Other, Describe</th>
      <th>Medications on Discharge</th>
      <th>Temperature</th>
      <th>Pulse</th>
      <th>Respiratory System</th>
      <th>Respiratory System, Other, Describe</th>
      <th>Respiration Rate</th>
      <th>BP: Systolic</th>
      <th>BP: Diastolic</th>
      <th>Scheduled Follow-up</th>
      <th>Place of Next Visit</th>
      <th>Referring encounter id</th>
      </tr>
      <xsl:for-each select="list/Post">
      <tr>
         <td><xsl:value-of select="date__of__discharge__1268R"/></td>
         <td><xsl:value-of select="hospital__ward__1269R"/></td>
         <td><xsl:value-of select="anaemiaR"/></td>
         <td><xsl:value-of select="anaemia__measurementR"/></td>
         <td><xsl:value-of select="cardiac__diseaseR"/></td>
         <td><xsl:value-of select="diabetes__gestationalR"/></td>
         <td><xsl:value-of select="diabetes__pregestationalR"/></td>
         <td><xsl:value-of select="dysentaryR"/></td>
         <td><xsl:value-of select="epilepsyR"/></td>
         <td><xsl:value-of select="gastroenteritisR"/></td>
         <td><xsl:value-of select="hemoglobinopathy__sickleR"/></td>
         <td><xsl:value-of select="hemoglobinopathy__thallasemiaR"/></td>
         <td><xsl:value-of select="hepatitisR"/></td>
         <td><xsl:value-of select="hypertensive__disorderR"/></td>
         <td><xsl:value-of select="malaria__suspectedR"/></td>
         <td><xsl:value-of select="polyarthritisR"/></td>
         <td><xsl:value-of select="psychosisR"/></td>
         <td><xsl:value-of select="tuberculosisR"/></td>
         <td><xsl:value-of select="drainingR"/></td>
         <td><xsl:value-of select="prolonged__first__stageR"/></td>
         <td><xsl:value-of select="cpdR"/></td>
         <td><xsl:value-of select="pphR"/></td>
         <td><xsl:value-of select="ruptured__placentaR"/></td>
         <td><xsl:value-of select="sepsisR"/></td>
         <td><xsl:value-of select="prolonged__laborR"/></td>
         <td><xsl:value-of select="ruptured__uterusR"/></td>
         <td><xsl:value-of select="cervical__tearsR"/></td>
         <td><xsl:value-of select="multiple__pregnancyR"/></td>
         <td><xsl:value-of select="malpresentationsR"/></td>
         <td><xsl:value-of select="intrapartum__foetal__distressR"/></td>
         <td><xsl:value-of select="previous__c__sR"/></td>
         <td><xsl:value-of select="broken__episotumR"/></td>
         <td><xsl:value-of select="dizzinessR"/></td>
         <td><xsl:value-of select="feverR"/></td>
         <td><xsl:value-of select="psychosis__diagR"/></td>
         <td><xsl:value-of select="Hypertensive__disordersR"/></td>
         <td><xsl:value-of select="diag__otherR"/></td>
         <td><xsl:value-of select="drug__1__1136R"/></td>
         <td><xsl:value-of select="drug__2__1137R"/></td>
         <td><xsl:value-of select="drug__3__1138R"/></td>
         <td><xsl:value-of select="drug__4__1139R"/></td>
         <td><xsl:value-of select="drug__5__1140R"/></td>
         <td><xsl:value-of select="drug__6__1141R"/></td>
         <td><xsl:value-of select="drug__7__1142R"/></td>
         <td><xsl:value-of select="drug__8__1143R"/></td>
         <td><xsl:value-of select="drug__9__1144R"/></td>
         <td><xsl:value-of select="drug__10__1145R"/></td>
         <td><xsl:value-of select="med__treatments__other__descR"/></td>
         <td><xsl:value-of select="mvaR"/></td>
         <td><xsl:value-of select="dilatation__and__curettageR"/></td>
         <td><xsl:value-of select="laparotomyR"/></td>
         <td><xsl:value-of select="hysterectomyR"/></td>
         <td><xsl:value-of select="salpingostomyR"/></td>
         <td><xsl:value-of select="episiotomy__sutureR"/></td>
         <td><xsl:value-of select="csR"/></td>
         <td><xsl:value-of select="btlR"/></td>
         <td><xsl:value-of select="instrumental__deliveryR"/></td>
         <td><xsl:value-of select="blood__transfusionR"/></td>
         <td><xsl:value-of select="surg__treat__other__descR"/></td>
         <td><xsl:value-of select="medications__dischargeR"/></td>
         <td><xsl:value-of select="temperature__266R"/></td>
         <td><xsl:value-of select="pulse__171R"/></td>
         <td><xsl:value-of select="respiratory__system__167R"/></td>
         <td><xsl:value-of select="respiratory__system__otherR"/></td>
         <td><xsl:value-of select="respiration__rate__269R"/></td>
         <td><xsl:value-of select="bp__systolic__224R"/></td>
         <td><xsl:value-of select="bp__diastolic__225R"/></td>
         <td><xsl:value-of select="scheduled__followup__1293R"/></td>
         <td><xsl:value-of select="place__of__next__visit__1213R"/></td>
         <td><xsl:value-of select="referring__encounter__idR"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>