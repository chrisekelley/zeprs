<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<template:insert template='/WEB-INF/templates/template-report.jsp'>
<template:put name='title' direct='true'>Pregnancy and Discharge Summary Printed: ${post.reportPrinted} Site: ${zeprs_session.clientSettings.site.name}</template:put>
<template:put name='header' direct='true'></template:put>
<template:put name='content' direct='true'>
<div id="reporttitle"><a href="/zeprs/home.do;jsessionid=${pageContext.request.session.id}" style="text-decoration: none;"><h1>Pregnancy and Discharge Summary - Site: ${zeprs_session.clientSettings.site.name}</h1></a></div>
<%--<table border="0" cellspacing="2" cellpadding="2"  class="enhancedtablePNCard">
    <tr>
        <td class="reportHeaderStrong2">PREGNANCY AND DISCHARGE SUMMARY</td>
        <td>Printed: ${post.reportPrinted}</td>
        <td>Site: ${zeprs_session.clientSettings.site.name}</td>
    </tr>
</table>--%>
<table border="0" cellspacing="2" cellpadding="2" width="1000px">
    <tr>
        <td valign="top">
            <table border="0" cellspacing="2" cellpadding="2" class="enhancedtablePNCard">
                <tr>
                    <th colspan="6">Overview</th>
                </tr>
                <tr>
                    <td class="reportlabel">Surname: </td><td class="item">${post.patientRegistration.surname}</td>
                    <td class="reportlabel">Date of birth: </td><td class="item">${post.patientRegistration.birthDate}</td>
                    <td class="reportlabel">Age: </td><td class="item">${post.patientRegistration.currentAge}</td>
                </tr>
                <tr>
                    <td class="reportlabel">Forenames: </td><td class="item">${post.patientRegistration.forenames}</td>
                    <td class="reportlabel">Gravida: </td><td class="item">${post.currentPregnancyStatus.gravida}</td>
                    <td class="reportlabel">Parity: </td><td class="item">${post.currentPregnancyStatus.parity}</td>
                </tr>

                <tr>
                    <td class="reportlabel">ZEPRS ID: </td><td class="item">${post.patientRegistration.patientIdNumber}</td>
                    <td class="reportlabel">Baby:</td><td colspan="3" class="item"><logic:iterate id="child" name="children">${child.districtPatientid}
                    <br></logic:iterate></td>
                </tr>

                <tr>
                    <td class="reportlabel">Place(s) of ANC: </td><td class="item">${post.pregnancyReport.siteList}</td>
                    <td class="reportlabel">EGA first visit:</td><td class="item">${post.pregnancyReport.dateFirstEga}</td>
                    <td class="reportlabel"># ANC visits:</td><td class="item">${fn:length(post.pregnancyReport.anteVisits)}</td>
                </tr>
            </table>
            <br/>
            <table border="0" cellspacing="2" cellpadding="2" class="enhancedtablePNCard2">
                <tr>
                    <th class="reportHeaderStrong" colspan="6">RPR tests</th>
                </tr>
                <tr>
                    <th>Screen Date</th>
                    <th>Result</th>
                    <th>Treat. Date</th>
                    <th>RPR Drug</th>
                    <th>Dosage</th>
                    <th>Comments</th>
                </tr>
                <logic:iterate id="rpr" name="post" property="pregnancyReport.anteHistory.rprScreens">
                <tr>
                    <td height="20px" class="item">${rpr.rpr_dateR}</td>
                    <td class="item">${rpr.rpr_resultR}</td>
                    <td class="item">${rpr.rpr_treatment_dateR}</td>
                    <td class="item">${rpr.rpr_drugR}</td>
                    <td class="item">${rpr.rpr_dosageR}</td>
                    <td class="item">${rpr.rpr_commentsR}</td>
                </tr>
                </logic:iterate>
            </table>
        </td>

        <td align="right">
            <table class="enhancedtablePNCard">
                <tbody>
                    <tr><th colspan="6">Stamp</th></tr>
                    <tr>
                        <td height="20" width="30" class="reportlabel">PCR</td><td class="item" width="50">${post.hivStamp.pcr1}</td>
                        <td width="30" class="reportlabel">TR</td><td class="item" width="50">${post.hivStamp.tr}</td>
                        <td width="30" class="reportlabel">MGA</td><td class="item" width="50">${post.hivStamp.mga}</td>
                    </tr>
                    <tr>
                        <td height="20" class="reportlabel">PCR</td><td class="item" width="50">${post.hivStamp.pcr2}</td>
                        <td class="reportlabel">TA</td><td class="item">${post.hivStamp.ta}</td>
                        <td class="reportlabel">MRA</td><td class="item">${post.hivStamp.mra}</td>
                    </tr>
                    <tr>

                        <td height="20" class="reportlabel">PCR</td><td class="item">${post.hivStamp.pcr3}</td>
                        <td class="reportlabel">R</td><td class="item">${post.hivStamp.statusR}</td>
                        <td class="reportlabel">IGA</td><td class="item">${post.hivStamp.iga}</td>
                    </tr>
                    <tr>
                        <td height="20" class="reportlabel">PCA</td><td class="item">${post.hivStamp.pca}</td>
                        <td class="reportlabel">NR</td><td class="item">${post.hivStamp.statusNr}</td>
                        <td class="reportlabel">INGA</td><td class="item">${post.hivStamp.inga}</td>
                    </tr>
                    <tr>
                        <td height="20" colspan="2" class="reportlabel"></td>
                        <td class="reportlabel">I</td><td class="item">${post.hivStamp.statusI}</td>
                        <td class="reportlabel">IFB</td><td class="item">${post.hivStamp.ifb}</td>
                    </tr>
                    <tr>
                        <td height="20" class="reportlabel" colspan="2" ></td>
                        <td class="reportlabel" colspan="2" ></td>
                        <td class="reportlabel">IFR</td><td class="item">${post.hivStamp.ifr}</td>
                    </tr>
                </tbody>
            </table>
        </td>
 </tr>
</table>
<table border="0" width="1000px">
    <tr>
        <%--<td valign="top">
            <table border="0" class="enhancedtablePNCard" width="300px">
                <tr>
                    <th colspan="6">Antenatal</th>
                </tr>
                <tr>
                    <td class="reportlabel">Place(s) of A.N.C:</td><td class="item">${post.pregnancyReport.siteList}</td>
                    <td class="reportlabel">EGA first visit:</td><td class="item">${post.pregnancyReport.dateFirstEga}</td>
                    <td class="reportlabel"># A.N.C. visits:</td><td
                        class="item">${fn:length(post.pregnancyReport.anteVisits)}</td>
                </tr>
            </table>
        </td>--%>
        <td valign="top" align="right">
            <%--<table border="1" cellspacing="0" cellpadding="1" class="enhancedtablePNCard2" width="550px">--%>
                <table border="1" cellspacing="0" cellpadding="1" class="enhancedtablePNCard2" width="990px">
                <tr>
                    <th valign="top" colspan="7" class="reportHeaderStrong">Admissions</th>
                </tr>
                <tr>
                    <th>Date</th>
                    <th width="70px">UTH Ward</th>
                    <th width="50px">Gestation</th>
                    <th>Diagnosis</th>
                    <th>Drugs given</th>
                    <th>Action taken</th>
                    <th>Comments</th>
                </tr>
                <logic:iterate id="referral" name="post" property="pregnancyReport.referrals">
                    <tr>
                        <td height="10px" class="item">${referral.date}</td>
                        <td>${referral.place}</td>
                        <td>${referral.gestation}</td>
                        <td>${referral.diagnosis}</td>
                        <td>${referral.drugsGiven}</td>
                        <td>${referral.actionTaken}</td>
                        <td>${referral.comments}</td>
                    </tr>
                </logic:iterate>
            </table>
        </td>
    </tr>
</table>

<c:if test="${! empty post.pregnancyReport.deliverySum}">
<table border="0" cellpadding="2" cellspacing="2" width="1000px">
<tr>
    <td valign="top">
        <table class="enhancedtablePNCard">
            <tr>
                <th valign="top" class="reportHeaderStrong" colspan="4">Mother Labour and Delivery:</th>
            </tr>
            <tr>
                <td valign="top" class="reportlabel">Date of Delivery:</td><td class="item">${post.pregnancyReport.deliverySum.dateVisit}</td>
                <td valign="top" class="reportlabel">Facility:</td><td class="item"> ${post.pregnancyReport.deliverySum.siteName}</td>
            </tr>

            <tr>
                <td valign="top" class="reportlabel">Duration of Labour:</td><td class="item">${post.pregnancyReport.durationOfLabour}</td>
                <td valign="top" class="reportlabel"></td><td class="item"></td>
            </tr>
            <tr>
                <td valign="top" class="reportlabel">Method of Delivery:</td><td class="item">${post.pregnancyReport.deliverySum.mode_of_delivery_447R}</td>
                <td valign="top" class="reportlabel">Indication if not SVD:</td><td class="item">${post.pregnancyReport.deliverySum.indication_CS_forcepts_60R}</td>
            </tr>
            <tr>
                <td valign="top" class="reportlabel">Blood loss:</td><td class="item">${post.pregnancyReport.deliverySum.blood_loss_est_462R}
            <c:if test="${post.pregnancyReport.deliverySum.pphR == 'No'}">&nbsp; &#60; 500 cc </c:if></td>
                <td valign="top" class="reportlabel"></td><td class="item"></td>
            </tr>

            <tr>
                <td valign="top" class="reportlabel">Details if PPH:</td><td class="item">${post.pregnancyReport.deliverySum.pph_treatment_463R}  </td>
                <td valign="top" class="reportlabel"></td><td class="item"></td>
            </tr>
            <tr>
                <td valign="top" class="reportlabel">Tears:</td><td class="item">${post.pregnancyReport.deliverySum.cervical_laceration_459R}</td>
                <td valign="top" class="reportlabel">Repaired:</td><td class="item">${post.pregnancyReport.deliverySum.cervical_laceration_sutured_460R}</td>
            </tr>
            <tr>
                <td valign="top" class="reportlabel">Episiotomy performed:</td><td class="item">${post.pregnancyReport.deliverySum.episiotomy_performed_452R}</td>
                <td valign="top" class="reportlabel">Sutured:</td><td class="item"></td>
            </tr>
            <tr>
                <td valign="top" class="reportlabel">Complications:</td><td class="item">${post.pregnancyReport.deliverySum.complications_467R}</td>
                <td valign="top" class="reportlabel"></td><td class="item">${post.pregnancyReport.deliverySum.if_complications_desc_468R}</td>
            </tr>
        </table>
    </td>
    <td valign="top">
<c:if test="${! empty post.pregnancyReport.newborns}">
<table border="0" cellspacing="0" cellpadding="0" width="100%" class="enhancedtablePNCard">
    <logic:iterate id="newborn" name="post" property="pregnancyReport.newborns" indexId="itemNum">
        <tr><th valign="top" class="reportHeaderStrong" colspan="6">Infant Newborn Evaluation</th></tr>
        <tr>
            <td valign="top" class="reportlabel">Baby ${itemNum+1} Sex:</td><td class="item">${newborn.newbornEval.sex_490R}</td>
            <td valign="top" class="reportlabel">Weight:</td><td class="item">${newborn.newbornEval.weight_at_birth_491R} kg</td>
            <td valign="top" class="reportlabel">EGA at delivery:</td><td class="item">${post.pregnancyReport.deliverySummaryEga} weeks</td>
        </tr>
        <tr>
            <td valign="top" class="reportlabel">Baby ${itemNum+1} Date of Birth:</td><td class="item">${newborn.newbornEval.date_of_birthR}</td>
            <td valign="top" class="reportlabel">Time of Birth:</td><td class="item">${newborn.newbornEval.time_of_birth_1514R}</td>
            <td valign="top" class="reportlabel">&nbsp;</td><td class="item" colspan="3">&nbsp;</td>
        </tr>
        <tr>
            <td valign="top" class="reportlabel">APGAR at 1 minute:</td><td class="item">${newborn.newbornEval.apgar_score_1_min_498R}</td>
            <td valign="top" class="reportlabel">5 min:</td><td class="item">${newborn.newbornEval.apgar_score_5_min_504R} </td>
            <td valign="top" class="reportlabel">10 min:</td><td class="item">${newborn.newbornEval.apgar_score_10_min_510R}</td>
        </tr>
        <tr>
            <td valign="top" class="reportlabel">Alive/ SB:</td><td class="item" colspan="5" style="text-align:left">${newborn.newbornEval.alive_sb_493R}</td>
        </tr>
        <tr>
            <td valign="top" class="reportlabel">NND:</td><td class="item">${newborn.newbornEval.neonatal_dea_1180R}</td>
            <td valign="top" class="reportlabel">Age:</td><td class="item" colspan="3">${newborn.newbornEval.neonatal_death_age_hours_497R} hrs</td>
        </tr>
        <tr>
            <td valign="top" class="reportlabel">If SB or NND, cause?</td><td class="item" colspan="5" style="text-align:left">${newborn.newbornEval.if_sb_cause_494R} ${newborn.newbornEval.neonatal_death_cause_desc_496R}</td>
        </tr>
        <tr>
            <td valign="top" class="reportlabel">Abnormalities/ injuries:</td><td class="item" colspan="5" style="text-align:left">${newborn.newbornEval.trauma_492R}</td>
        </tr>
        <tr>
            <td valign="top" class="reportlabel">Drugs given:</td><td class="item" colspan="5" style="text-align:left">
            <c:if test="${! empty newborn.newbornEval.immunization_1R}">${newborn.newbornEval.immunization_1R}</c:if>
            <c:if test="${! empty newborn.newbornEval.immunization_2R}">, ${newborn.newbornEval.immunization_2R}</c:if>
            <c:if test="${! empty newborn.newbornEval.immunization_3R}">, ${newborn.newbornEval.immunization_3R}</c:if>
            <c:if test="${! empty newborn.newbornEval.immunization_4R}">, ${newborn.newbornEval.immunization_4R}</c:if>
            <c:if test="${! empty newborn.newbornEval.immunization_5R}">, ${newborn.newbornEval.immunization_5R}</c:if>
            <c:if test="${! empty newborn.newbornEval.other_drugsR}">, ${newborn.newbornEval.other_drugsR}</c:if>
        </td>
        </tr>
        <tr>
            <td valign="top" class="reportlabel">Problems/ comments:</td><td class="item" colspan="5" style="text-align:left">${newborn.newbornEval.problems_comments_557R} </td>
        </tr>
    </logic:iterate></table>
</c:if>
    </td>
    </tr>
</table>
    <table border="0" cellpadding="0" cellspacing="0" width="1000px">
<tr>
    <td valign="top">
    <c:if test="${! empty post.pregnancyReport.puerperium || ! empty post.pregnancyReport.maternalDischarge}">
        <table border="0" cellspacing="0" cellpadding="0" width="100%" class="enhancedtablePNCard">
        <tr><th valign="top" class="reportHeaderStrong" colspan="2">Mother Puerperium and Discharge:</th></tr>
        <tr><td  class="reportlabel">Blood pressure (mm Hg):</td>
            <td class="item">${post.pregnancyReport.puerperium.bp_systolic_224R}/${post.pregnancyReport.puerperium.bp_diastolic_225R}</td></tr>
        <tr><td  class="reportlabel">Temperature:</td>
            <td class="item">${post.pregnancyReport.puerperium.temperature_266R}</td></tr>
        <tr><td  class="reportlabel">Haemoglobin (Gm%):</td>
            <td class="item">${post.pregnancyReport.puerperium.hb_235R} </td></tr>
        <tr><td  class="reportlabel">Breast Feeding</td>
            <td class="item">${post.pregnancyReport.maternalDischarge.feeding_typeR}</td></tr>
        <tr><td  class="reportlabel">Involution of uterus:</td>
            <td class="item">${post.pregnancyReport.maternalDischarge.involution_uterus_578R} </td></tr>
        <tr><td  class="reportlabel">Lochia normal</td>
            <td class="item">${post.pregnancyReport.puerperium.lochia_579R}</td></tr>
        <tr><td  class="reportlabel">Perineum Intact:</td>
            <td class="item">${post.pregnancyReport.puerperium.perineum_intactR}</td></tr>
        <tr><td class="reportlabel">Perineum Swollen:</td>
            <td class="item">${post.pregnancyReport.puerperium.perineum_swollenR}</td></tr>
        <tr><td  class="reportlabel">Perineum Hematoma:</td>
            <td class="item">${post.pregnancyReport.puerperium.perineum_hematomaR}</td></tr>
        <tr><td  class="reportlabel">Bonding well:</td>
            <td class="item">${post.pregnancyReport.maternalDischarge.bonding_well_577R}</td></tr>
        <tr><td  class="reportlabel">Family Planning discussed:</td>
            <td class="item">${post.pregnancyReport.maternalDischarge.family_planning_discussed_594R} </td></tr>
        <tr><td  class="reportlabel">Treatment on discharge:</td>
            <td class="item">${post.pregnancyReport.maternalDischarge.treatment_on_discharge_595R}</td></tr>
        <tr><td  class="reportlabel">Comments:</td>
            <td class="item">${post.pregnancyReport.maternalDischarge.comments_maternal_discharge_597R}</td></tr>
        </table>
    </c:if>
    </td>

    <td valign="top">
         <c:if test="${! empty post.pregnancyReport.newborns}">
            <table border="0" cellspacing="0" cellpadding="0" width="100%" class="enhancedtablePNCard">
                <tr><th valign="top" class="reportHeaderStrong">Infant Discharge:</th></tr>
<logic:iterate id="newborn" name="post" property="pregnancyReport.newborns" indexId="itemNum">
    <tr>
        <td valign="top">
            <table border="0" cellspacing="2" cellpadding="2">
                <tr>
                    <td valign="top">
                        <table class="enhancedtablePNCard">
                            <tr>
                                <td class="reportlabel">Baby ${itemNum + 1} Weight (gms):</td>
                                <td class="item" width="40px">${newborn.infantDischarge.weight_on_3rd_day_558R}</td>
                            </tr>
                            <tr>
                                <td class="reportlabel">Breast feeding well:</td>
                                <td class="item">${newborn.infantDischarge.feedingR}</td>
                            </tr>
                            <tr>
                                <td class="reportlabel">Jaundice:</td>
                                <td class="item">${newborn.infantDischarge.jaundice_519R}</td>
                            </tr>
                            <tr>
                                <td class="reportlabel">Good head control:</td>
                                <td class="item">${newborn.infantDischarge.good_head_control_520R}</td>
                            </tr>
                            <tr>
                                <td class="reportlabel">Good grasp. Reflection:</td>
                                <td class="item">${newborn.infantDischarge.good_grasp_reflex_521R}</td>
                            </tr>
                            <tr>
                                <td class="reportlabel">Symmetrical moro reaction:</td>
                                <td class="item">${newborn.infantDischarge.symmetrical_moro_522R}</td>
                            </tr>
                        </table>
                    </td>
                    <td valign="top">
                        <table class="enhancedtablePNCard">
                            <tr>
                                <td  class="reportlabel" valign="top">Drugs given:</td>
                                <td class="item" width="40px">${newborn.infantDischarge.immunization_1R} ${newborn.infantDischarge.immunization_2R} ${newborn.infantDischarge.immunization_3R}
                                ${newborn.infantDischarge.immunization_4R} ${newborn.infantDischarge.immunization_5R}</td>
                            </tr><tr>
                                <td  class="reportlabel">Birth record given:</td>
                                <td class="item">${newborn.infantDischarge.birth_record_given_561R}</td>
                            </tr><tr>
                                <td  class="reportlabel">Treatment on discharge:</td>
                                <td class="item">${newborn.infantDischarge.treatment_on_discharge_562R}</td>
                            </tr><!--<tr>
                                <td valign="top">Other comments: </td>
                            </tr>-->
                            <tr>
                                <td class="reportlabel">Date of 1st week Postnatal visit:</td>
                                <td class="item">${newborn.infantDischarge.first_postnatal_visit_date_564R}</td>
                            </tr><tr>
                                <td  class="reportlabel">Date of Discharge:</td>
                                <td class="item">${newborn.infantDischarge.date_of_discharge_1268R}</td>
                            </tr><tr>
                                <td  class="reportlabel">Name of discharge person:</td>
                                <td class="item">${newborn.infantDischarge.createdBy}</td>
                            </tr>
                        </table>
                    </td>
                    <td valign="top">
                        <table class="enhancedtablePNCard">
                            <tr>
                                <th colspan="2">Normal?</th>
                            </tr>
                            <tr>
                                <td class="reportlabel">Eyes:</td>
                                <td class="item" width="40px">${newborn.infantDischarge.eyes_523R} ${newborn.infantDischarge.eyes_other_524R}
                                        ${newborn.infantDischarge.if_eyes_abnormal_treatment_525R}</td>
                            </tr>
                            <tr>
                                <td class="reportlabel">Mouth:</td>
                                <td class="item">${newborn.infantDischarge.mouth_526R} ${newborn.infantDischarge.mouth_other_527R}</td>
                            </tr>
                            <tr>
                                <td class="reportlabel">Upper Extremities:</td>
                                <td class="item">${newborn.infantDischarge.upper_extrem_538R} ${newborn.infantDischarge.upper_extrem_desc_539R}</td>
                            </tr>
                            <tr>
                                <td class="reportlabel">Lower Extremities:</td>
                                <td class="item">${newborn.infantDischarge.lower_extrem_541R} ${newborn.infantDischarge.lower_extrem_desc_542R}</td>
                            </tr>
                            <tr>
                                <td class="reportlabel">Genitals:</td>
                                <td class="item">${newborn.infantDischarge.genitalia_529R} ${newborn.infantDischarge.genitalia_other_697R}</td>
                            </tr>
                            <tr>
                                <td class="reportlabel">Back:</td>
                                <td class="item">${newborn.infantDischarge.back_536R} ${newborn.infantDischarge.back_other_537R}</td>
                            </tr>
                            <tr>
                                <td class="reportlabel">Ortolani’s Sign:</td>
                                <td class="item">${newborn.infantDischarge.ortolani_sign_540R}</td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <!--<tr><td>&nbsp;</td></tr>-->
</logic:iterate>
            </table>

</c:if>
            </td>
    </tr>
</table>
</c:if>
</template:put>
</template:insert>