<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>ProbLaborReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Contractions</th>
      <th>Contractions, Date of onset</th>
      <th>Lower abdominal pains</th>
      <th>Date of Onset, Lower abdominal pains</th>
      <th>Decreased fetal movement</th>
      <th>Date of onset, Decreased fetal movement</th>
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
      <th>Possible rupture of membranes</th>
      <th>Shortness of breath</th>
      <th>Possible infection</th>
      <th>Backache</th>
      <th>Headache</th>
      <th>Date of Onset, Headache</th>
      <th>Fatigue/Dizziness</th>
      <th>Lack of Foetal Movement</th>
      <th>Cough</th>
      <th>Reasons: Other</th>
      <th>Reasons: Other, Describe</th>
      <th>Comments</th>
      <th>Respiratory System</th>
      <th>Respiratory System, Other, Describe</th>
      <th>Respiration Rate</th>
      <th>BP: Systolic</th>
      <th>BP: Diastolic</th>
      <th>Pulse</th>
      <th>Temperature</th>
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
      <th>Uterus, Size in Days</th>
      <th>Adnexa</th>
      <th>Adnexa, Abnormal Describe</th>
      <th>Varicosities</th>
      <th>Teeth</th>
      <th>Teeth, Other Describe</th>
      <th>CNS</th>
      <th>Time of Exam</th>
      <th>Fundal Height</th>
      <th>Lie</th>
      <th>Presentation</th>
      <th>Presentation: Other, Describe</th>
      <th>Descent</th>
      <th>Contraction Strength</th>
      <th>Contraction Frequency Per 10 Minutes at abdominal palpation</th>
      <th>Foetal Heart Rate at Abdominal Palpation</th>
      <th>Time</th>
      <th>Rupture of Membranes Date</th>
      <th>Rupture of Membranes Time</th>
      <th>Presentation Obtained by</th>
      <th>Condition of Vulva</th>
      <th>Condition of Vulva, Other, Describe</th>
      <th>Condition of Vagina</th>
      <th>Condition of Vagina, Other, Describe</th>
      <th>Cervix Dilatation</th>
      <th>Cervix Effacement</th>
      <th>Cervix Consistency</th>
      <th>Membranes Ruptured</th>
      <th>Liquor</th>
      <th>Station of PP</th>
      <th>Moulding</th>
      <th>Caput</th>
      <th>Cord at Vaginal Exam</th>
      <th>Rupture of Membranes</th>
      <th>Diagonal Conjugate (Promontorium)</th>
      <th>Diagonal Conjugate (Promontorium) length</th>
      <th>Ishcial Spines</th>
      <th>Sub-pubic Arch</th>
      <th>Curvature of Sacrum</th>
      <th>Intertuberous Diameter</th>
      <th>Adequacy of Pelvic</th>
      <th>Pubic Arch-Angle</th>
      <th>False labour</th>
      <th>True labor</th>
      <th>Rupture of Membranes</th>
      <th>Preeclampsia / Hypertension</th>
      <th>Premature Labor</th>
      <th>Malaria</th>
      <th>Anaemia</th>
      <th>High Blood Pressure</th>
      <th>Vaginal Bleeding</th>
      <th>Intrauterine Death</th>
      <th>Urinary Tract Infections</th>
      <th>Pneumonia</th>
      <th>TB</th>
      <th>Vaginal Thrush</th>
      <th>Oral Thrush</th>
      <th>Eclampsia</th>
      <th>Abruptio Placenta</th>
      <th>Miscarriage</th>
      <th>Latent Labour</th>
      <th>CNS normal</th>
      <th>Diagnosis, Other, describe</th>
      <th>Phase of Pregnancy</th>
      <th>Disposition (Intrapartum)</th>
      <th>Disposition (Antepartum)</th>
      <th>Type of Labor</th>
      <th>UTH Ward</th>
      <th>Priority of Referral</th>
      <th>Transport</th>
      <th>Treatment</th>
      <th>Comments</th>
      </tr>
      <xsl:for-each select="list/Prob">
      <tr>
         <td><xsl:value-of select="contractions__1250R"/></td>
         <td><xsl:value-of select="contractions__date__1251R"/></td>
         <td><xsl:value-of select="lower__abdominal__painsR"/></td>
         <td><xsl:value-of select="lower__abdominal__pains__date__onsetR"/></td>
         <td><xsl:value-of select="decreased__fetal__movement__1256R"/></td>
         <td><xsl:value-of select="deacreased__fetal__mvmt__date__onsetR"/></td>
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
         <td><xsl:value-of select="possible__reputure__membranesR"/></td>
         <td><xsl:value-of select="shortness__of__breathR"/></td>
         <td><xsl:value-of select="possible__infectionR"/></td>
         <td><xsl:value-of select="backacheR"/></td>
         <td><xsl:value-of select="headacheR"/></td>
         <td><xsl:value-of select="date__onset__headacheR"/></td>
         <td><xsl:value-of select="fatigue__dizzinessR"/></td>
         <td><xsl:value-of select="lack__of__foetal__movementR"/></td>
         <td><xsl:value-of select="coughR"/></td>
         <td><xsl:value-of select="other__reasonsR"/></td>
         <td><xsl:value-of select="reasons__other__describeR"/></td>
         <td><xsl:value-of select="comments__reasons__for__evalR"/></td>
         <td><xsl:value-of select="respiratory__system__167R"/></td>
         <td><xsl:value-of select="respiratory__system__otherR"/></td>
         <td><xsl:value-of select="respiration__rate__269R"/></td>
         <td><xsl:value-of select="bp__systolic__224R"/></td>
         <td><xsl:value-of select="bp__diastolic__225R"/></td>
         <td><xsl:value-of select="pulse__171R"/></td>
         <td><xsl:value-of select="temperature__266R"/></td>
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
         <td><xsl:value-of select="uterus__size__in__days__188R"/></td>
         <td><xsl:value-of select="adnexa__189R"/></td>
         <td><xsl:value-of select="adnexa__abnormal__190R"/></td>
         <td><xsl:value-of select="varicosities__191R"/></td>
         <td><xsl:value-of select="teeth__163R"/></td>
         <td><xsl:value-of select="teeth__other__164R"/></td>
         <td><xsl:value-of select="cns__192R"/></td>
         <td><xsl:value-of select="time__of__exam__1175R"/></td>
         <td><xsl:value-of select="fundal__height__232R"/></td>
         <td><xsl:value-of select="lie__313R"/></td>
         <td><xsl:value-of select="presentation__314R"/></td>
         <td><xsl:value-of select="presentation__otherR"/></td>
         <td><xsl:value-of select="descent__315R"/></td>
         <td><xsl:value-of select="contraction__strength__316R"/></td>
         <td><xsl:value-of select="contraction__freq__10__abd__palp__317R"/></td>
         <td><xsl:value-of select="foetal__heart__rate__abd__palp__318R"/></td>
         <td><xsl:value-of select="time__320R"/></td>
         <td><xsl:value-of select="rupture__of__membranes__date__328R"/></td>
         <td><xsl:value-of select="rupture__of__membranes__time__329R"/></td>
         <td><xsl:value-of select="presentation__obtained__by__335R"/></td>
         <td><xsl:value-of select="condition__of__vulva__321R"/></td>
         <td><xsl:value-of select="condition__of__vulva__desc__322R"/></td>
         <td><xsl:value-of select="condition__of__vagina__323R"/></td>
         <td><xsl:value-of select="condition__of__vagina__other__324R"/></td>
         <td><xsl:value-of select="cervix__dilatation325R"/></td>
         <td><xsl:value-of select="cervix__effacement__326R"/></td>
         <td><xsl:value-of select="cervix__consistency__327R"/></td>
         <td><xsl:value-of select="membranes__re__330R"/></td>
         <td><xsl:value-of select="liquor__331R"/></td>
         <td><xsl:value-of select="station__of__pp__336R"/></td>
         <td><xsl:value-of select="moulding__338R"/></td>
         <td><xsl:value-of select="caput__339R"/></td>
         <td><xsl:value-of select="cord__at__vaginal__exam__340R"/></td>
         <td><xsl:value-of select="rupture__of__membranes__1221R"/></td>
         <td><xsl:value-of select="diagonal__conjugate__342R"/></td>
         <td><xsl:value-of select="diagonal__conjugate__length__343R"/></td>
         <td><xsl:value-of select="ishcial__spines__344R"/></td>
         <td><xsl:value-of select="sub__pubic__arch__345R"/></td>
         <td><xsl:value-of select="curvature__of__sacrum__346R"/></td>
         <td><xsl:value-of select="intertuberous__diameter__347R"/></td>
         <td><xsl:value-of select="adequacy__of__pelvic__348R"/></td>
         <td><xsl:value-of select="pubic__arch__angle__349R"/></td>
         <td><xsl:value-of select="false__labourR"/></td>
         <td><xsl:value-of select="true__laborR"/></td>
         <td><xsl:value-of select="rupture__of__membranesR"/></td>
         <td><xsl:value-of select="preeclamp__hypert__1265R"/></td>
         <td><xsl:value-of select="premature__labourR"/></td>
         <td><xsl:value-of select="malaria__diagR"/></td>
         <td><xsl:value-of select="anaemiaR"/></td>
         <td><xsl:value-of select="high__bp__diagR"/></td>
         <td><xsl:value-of select="vaginal__bleeding__diagR"/></td>
         <td><xsl:value-of select="intrauterine__deathR"/></td>
         <td><xsl:value-of select="uti__diagR"/></td>
         <td><xsl:value-of select="pneumonia__diagR"/></td>
         <td><xsl:value-of select="tb__diagR"/></td>
         <td><xsl:value-of select="vaginal__thrush__diagR"/></td>
         <td><xsl:value-of select="oral__thrush__diagR"/></td>
         <td><xsl:value-of select="eclampsiaR"/></td>
         <td><xsl:value-of select="abruptia__placentaR"/></td>
         <td><xsl:value-of select="miscarriageR"/></td>
         <td><xsl:value-of select="latent__labourR"/></td>
         <td><xsl:value-of select="cns__normalR"/></td>
         <td><xsl:value-of select="diag__otherR"/></td>
         <td><xsl:value-of select="phaseR"/></td>
         <td><xsl:value-of select="disposition__laborR"/></td>
         <td><xsl:value-of select="disp__anteR"/></td>
         <td><xsl:value-of select="type__of__labourR"/></td>
         <td><xsl:value-of select="uth__wardR"/></td>
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