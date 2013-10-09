<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>ProbPostnatalReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Lower abdominal pains</th>
      <th>Date of Onset, Lower abdominal pains</th>
      <th>Vaginal Bleeding</th>
      <th>Date of onset, Bleeding</th>
      <th>Vaginal discharge</th>
      <th>Date of onset, Vaginal discharge</th>
      <th>Fever</th>
      <th>Date of Onset, Fever</th>
      <th>Elevated blood pressure</th>
      <th>Swelling/Edema</th>
      <th>Trauma</th>
      <th>Nausea/Vomiting</th>
      <th>Diarrhea</th>
      <th>Date of Onset, Diarrhea</th>
      <th>Shortness of breath</th>
      <th>Possible infection</th>
      <th>Backache</th>
      <th>Height</th>
      <th>Weight</th>
      <th>HE/ENT</th>
      <th>HE/ENT Abnormal, Describe</th>
      <th>Thyroid</th>
      <th>Breasts</th>
      <th>Heart</th>
      <th>Heart, Other,Describe</th>
      <th>Abdomen</th>
      <th>Abdomen, Abnormal Describe</th>
      <th>Skin</th>
      <th>Skin, Abnormal Describe</th>
      <th>Extremities</th>
      <th>Extremities, Abnormal Describe</th>
      <th>Lymph Nodes</th>
      <th>Rectum</th>
      <th>Rectum, Abnormal Describe</th>
      <th>Vulva</th>
      <th>Vulva, Abnormal Describe</th>
      <th>Vagina</th>
      <th>Vagina, Abnormal Describe</th>
      <th>Cervix</th>
      <th>Cervix, Abnormal Describe</th>
      <th>Uterus</th>
      <th>Adnexa</th>
      <th>Adnexa, Abnormal Describe</th>
      <th>Varicosities</th>
      <th>Teeth</th>
      <th>Teeth, Other Describe</th>
      <th>CNS</th>
      <th>Malaria</th>
      <th>Anaemia</th>
      <th>High Blood Pressure</th>
      <th>Vaginal Bleeding</th>
      <th>Urinary Tract Infections</th>
      <th>Pneumonia</th>
      <th>TB</th>
      <th>Vaginal Thrush</th>
      <th>Oral Thrush</th>
      <th>Broken episiotomy</th>
      <th>Puerperal sepsis</th>
      <th>Breast engorgement</th>
      <th>Secondary PPH</th>
      <th>Mastitis</th>
      <th>Breast abscess</th>
      <th>Diagnosis, Other, describe</th>
      <th>Eclampsia</th>
      <th>Preeclampsia / Hypertension</th>
      <th>Sepsis</th>
      <th>Psychosis</th>
      <th>Episiotomy infection</th>
      <th>Episiotomy breakdown</th>
      <th>Anaemia</th>
      <th>Mastitis/breast absess</th>
      <th>Endometritis</th>
      <th>Date of Ultrasound</th>
      <th>Diagnosis (General Impression)</th>
      <th>Describe Abnormalities/Comment on Ultrasound</th>
      <th>Plan of Action/Disposition</th>
      <th>UTH Ward</th>
      <th>Date of Admission</th>
      <th>Priority of Referral</th>
      <th>Transport</th>
      <th>Treatment</th>
      <th>Comments</th>
      </tr>
      <xsl:for-each select="list/Prob">
      <tr>
         <td><xsl:value-of select="lower__abdominal__painsR"/></td>
         <td><xsl:value-of select="lower__abdominal__pains__date__onsetR"/></td>
         <td><xsl:value-of select="vag__bleeding__1254R"/></td>
         <td><xsl:value-of select="bleeding__date__of__onset__1255R"/></td>
         <td><xsl:value-of select="vag__discharge__1252R"/></td>
         <td><xsl:value-of select="vag__discharge__date__of__onset__1253R"/></td>
         <td><xsl:value-of select="feverR"/></td>
         <td><xsl:value-of select="fever__date__onsetR"/></td>
         <td><xsl:value-of select="elevated__blood__pressureR"/></td>
         <td><xsl:value-of select="swelling__edemaR"/></td>
         <td><xsl:value-of select="traumaR"/></td>
         <td><xsl:value-of select="nausea__vomitingR"/></td>
         <td><xsl:value-of select="diarrheaR"/></td>
         <td><xsl:value-of select="diarrhea__date__onsetR"/></td>
         <td><xsl:value-of select="shortness__of__breathR"/></td>
         <td><xsl:value-of select="possible__infectionR"/></td>
         <td><xsl:value-of select="backacheR"/></td>
         <td><xsl:value-of select="height__159R"/></td>
         <td><xsl:value-of select="weight__228R"/></td>
         <td><xsl:value-of select="heent__161R"/></td>
         <td><xsl:value-of select="heent__abnorm__162R"/></td>
         <td><xsl:value-of select="thyroid__165R"/></td>
         <td><xsl:value-of select="breasts__166R"/></td>
         <td><xsl:value-of select="heart__169R"/></td>
         <td><xsl:value-of select="heart__other__170R"/></td>
         <td><xsl:value-of select="abdomen__172R"/></td>
         <td><xsl:value-of select="abdomen__abnormal__173R"/></td>
         <td><xsl:value-of select="skin__176R"/></td>
         <td><xsl:value-of select="skin__abnorm__177R"/></td>
         <td><xsl:value-of select="extremities__174R"/></td>
         <td><xsl:value-of select="extremities__abnormal__175R"/></td>
         <td><xsl:value-of select="lymph__nodes__178R"/></td>
         <td><xsl:value-of select="rectum__179R"/></td>
         <td><xsl:value-of select="rectum__abnormal__180R"/></td>
         <td><xsl:value-of select="vulva__181R"/></td>
         <td><xsl:value-of select="vulva__abnormal__182R"/></td>
         <td><xsl:value-of select="vagina__183R"/></td>
         <td><xsl:value-of select="vagina__abnormal__184R"/></td>
         <td><xsl:value-of select="cervix__185R"/></td>
         <td><xsl:value-of select="cervix__abnormal__186R"/></td>
         <td><xsl:value-of select="uterus__187R"/></td>
         <td><xsl:value-of select="adnexa__189R"/></td>
         <td><xsl:value-of select="adnexa__abnormal__190R"/></td>
         <td><xsl:value-of select="varicosities__191R"/></td>
         <td><xsl:value-of select="teeth__163R"/></td>
         <td><xsl:value-of select="teeth__other__164R"/></td>
         <td><xsl:value-of select="cns__192R"/></td>
         <td><xsl:value-of select="malaria__diagR"/></td>
         <td><xsl:value-of select="anaemiaR"/></td>
         <td><xsl:value-of select="high__bp__diagR"/></td>
         <td><xsl:value-of select="vaginal__bleeding__diagR"/></td>
         <td><xsl:value-of select="uti__diagR"/></td>
         <td><xsl:value-of select="pneumonia__diagR"/></td>
         <td><xsl:value-of select="tb__diagR"/></td>
         <td><xsl:value-of select="vaginal__thrush__diagR"/></td>
         <td><xsl:value-of select="oral__thrush__diagR"/></td>
         <td><xsl:value-of select="broken__episiotomyR"/></td>
         <td><xsl:value-of select="puerperal__sepsisR"/></td>
         <td><xsl:value-of select="breast__engorgementR"/></td>
         <td><xsl:value-of select="secondary__pphR"/></td>
         <td><xsl:value-of select="mastitisR"/></td>
         <td><xsl:value-of select="breast__abscessR"/></td>
         <td><xsl:value-of select="diag__otherR"/></td>
         <td><xsl:value-of select="eclampsiaR"/></td>
         <td><xsl:value-of select="preeclamp__hypert__1265R"/></td>
         <td><xsl:value-of select="sepsisR"/></td>
         <td><xsl:value-of select="psychosisR"/></td>
         <td><xsl:value-of select="episiotomy__infectionR"/></td>
         <td><xsl:value-of select="episiotomy__breakdownR"/></td>
         <td><xsl:value-of select="anaemia__complicationR"/></td>
         <td><xsl:value-of select="mastitis__breast__absessR"/></td>
         <td><xsl:value-of select="endometritisR"/></td>
         <td><xsl:value-of select="date__of__ultrasound__1212R"/></td>
         <td><xsl:value-of select="diagnosis__ultrasoundR"/></td>
         <td><xsl:value-of select="describe__abnormalitiesR"/></td>
         <td><xsl:value-of select="dispositionR"/></td>
         <td><xsl:value-of select="uth__wardR"/></td>
         <td><xsl:value-of select="date__of__admissionR"/></td>
         <td><xsl:value-of select="priority__of__referralR"/></td>
         <td><xsl:value-of select="transportR"/></td>
         <td><xsl:value-of select="treatment__1463R"/></td>
         <td><xsl:value-of select="comments__ante__prob__1464R"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>