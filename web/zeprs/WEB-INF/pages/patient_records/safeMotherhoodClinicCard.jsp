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
<template:put name='title' direct='true'>Safe Motherhood Clinic Card Printed: ${post.reportPrinted} Site: ${zeprs_session.clientSettings.site.name}</template:put>
<template:put name='header' direct='true'></template:put>
<template:put name='content' direct='true'>
<div id="reporttitle"><a href="/zeprs/home.do;jsessionid=${pageContext.request.session.id}" style="text-decoration: none;"><h1>Safe Motherhood Clinic Card - Antenatal - Site: ${zeprs_session.clientSettings.site.name}</h1></a></div>
<%--<table border="0" cellspacing="2" cellpadding="2"  class="enhancedtablePNCard">
    <tr>
        <td class="reportHeaderStrong2">PREGNANCY AND DISCHARGE SUMMARY</td>
        <td>Printed: ${post.reportPrinted}</td>
        <td>Site: ${zeprs_session.clientSettings.site.name}</td>
    </tr>
</table>--%>

<p><strong>
<html:link action="/patientHome" paramId="patientId" paramName="ante" paramProperty="patientRegistration.patientId">
${ante.patientRegistration.surname}, ${ante.patientRegistration.forenames}</html:link></strong>
 Report Date: ${ante.reportDate}
</p>
<table border="0" cellspacing="2" cellpadding="2">
    <tr>
        <td valign="top">
            <table border="0" cellspacing="2" cellpadding="2" class="enhancedtablePNCard">
                <tr>
                    <td class="reportlabel2" colspan="4">A. REGISTRATION</td>
                </tr>
                <tr>
                    <td class="reportlabelTight" colspan = "1">Patient ID: </td><td class="itemTight" colspan = "3">${ante.patientRegistration.patientIdNumber}</td>
                </tr>
                <tr>
                    <td class="reportlabelTight">Surname: </td><td class="itemTight">${ante.patientRegistration.surname}</td>
                    <td class="reportlabelTight">Name: </td><td class="itemTight">${ante.patientRegistration.forenames}</td>
				</tr>
				<tr>
					<td class="reportlabelTight">Maiden Name: </td><td class="itemTight"></td>
	                <td class="reportlabelTight">NRC No: </td><td class="itemTight">${ante.patientRegistration.nrcNo}</td>
				</tr>

				<tr>
                    <td class="reportlabelTight">DOB: </td><td class="itemTight">${ante.patientRegistration.birthDate}</td>
                    <td class="reportlabelTight">Age (yrs): </td><td class="itemTight">${ante.patientRegistration.currentAge}</td>
                </tr>

                <tr>
                    <td class="reportlabelTight">Place of Birth: </td><td class="itemTight"></td>
                    <td class="reportlabelTight">District: </td><td class="itemTight"></td>
                </tr>
                <tr>
                    <td class="reportlabelTight">Education level: </td><td class="itemTight" colspan="3">${ante.patientRegistration.educationStatus}</td>
                </tr>
                <tr>
                    <td class="reportlabelTight">Occupation: </td><td class="itemTight">${ante.patientRegistration.occupation} ${ante.patientRegistration.occupationOther}</td>
                    <td class="reportlabelTight">Religion: </td><td class="itemTight">${ante.patientRegistration.religion} ${ante.patientRegistration.religionOther}</td>
                </tr>

                 <tr>
                    <td class="reportlabelTight">Marital Status: </td><td class="itemTight" colspan="3">${ante.patientRegistration.maritalStatus}</td>
                </tr>

                 <tr>
                    <td class="reportlabelTight">Home Language: </td><td class="itemTight" colspan="3"></td>
                </tr>
                 <tr>
                    <td class="reportlabel3" colspan="4">Current address</td>
                </tr>

                <tr>
                    <td class="reportlabelTight">Address: </td><td class="itemTight" colspan="3">${ante.patientRegistration.address1}
                    <c:if test="${! empty ante.patientRegistration.address2}"><br>${ante.patientRegistration.address2}</c:if>
                    </td>
                </tr>
                 <tr>
                    <td class="reportlabelTight">Telephone No.: </td><td class="itemTight" colspan="3">${ante.patientRegistration.patientPhone}</td>
                </tr>

                 <tr>
                    <td class="reportlabel2" colspan="4">B. NEXT OF KIN</td>
                </tr>

                <tr>
                    <td class="reportlabelTight">Relation to client: </td><td class="itemTight" colspan="3"></td>
                </tr>

                <tr>
                    <td class="reportlabelTight">Husband Surname: </td><td class="itemTight">${ante.patientRegistration.surnameHusband}</td>
                    <td class="reportlabelTight">Husband Name: </td><td class="itemTight">${ante.patientRegistration.forenamesHusband}</td>
				</tr>

				 <tr>
                    <td class="reportlabelTight">Husband Telephone No.: </td><td class="itemTight" colspan="3">${ante.patientRegistration.telNoHusband}</td>
                </tr>

                <tr>
                    <td class="reportlabelTight">Father Surname: </td><td class="itemTight">${ante.patientRegistration.surnameFather}</td>
                    <td class="reportlabelTight">Father Name: </td><td class="itemTight">${ante.patientRegistration.forenamesFather}</td>
				</tr>

				<tr>
                    <td class="reportlabelTight">Emergency Contact Surname: </td><td class="itemTight">${ante.patientRegistration.surnameEmergContact}</td>
                    <td class="reportlabelTight">Emergency Contact Name: </td><td class="itemTight">${ante.patientRegistration.forenameEemergContact}</td>
				</tr>

				     <tr>
                    <td class="reportlabelTight">Emergency Contact Address: </td><td class="itemTight"  colspan="3">${ante.patientRegistration.addressEmergContact}
                    </td>
                </tr>

                 <tr>
                    <td class="reportlabelTight">Emergency Contact Telephone No.: </td><td class="itemTight" colspan="3">${ante.patientRegistration.telNoEmergContact}</td>
                </tr>

                 <tr>
                    <td class="reportlabel2" colspan="4">C. MEDICAL & SURGICAL HISTORY</td>
                </tr>

                <tr>
                    <td class="itemTight" colspan="4">${ante.medicalSugHistoryStr}</td>
                </tr>

                 <tr>
                    <td class="reportlabelTight">Current Medicine: </td><td class="itemTight" colspan="3">${ante.currentMedicineStr}</td>
                </tr>

            </table>
        </td>
        <td align="left" valign="top">
        	<table>
	        	<tr>
			        <td align="left" valign="top">
			        	<table class="enhancedtablePNCard">
			                <logic:iterate id="pregnancy" name="ante" property="pregnancies">
			                <c:if test="${empty pregnancy.datePregnancyEnd}">
			               	<tr>
			               	<td class="reportlabel2" colspan="2">E. PRESENT PREGNANCY</td>
			                <td class="reportlabelTight" colspan="2">Date First Visit: </td><td class="itemTight" colspan="2">${pregnancy.datePregnancyBegin}</td>
			                </tr>
			                </c:if>
							</logic:iterate>
							<tr>
			                    <td class="reportlabelTight">LMP: </td><td class="itemTight">${ante.currentPregnancyStatus.lmp}</td>
			                    <td class="reportlabelTight">&nbsp; </td><td class="itemTight">&nbsp;</td>
			                    <td class="reportlabelTight">EDD: </td><td class="itemTight">${ante.currentPregnancyStatus.edd}</td>
			                </tr>
			             	<tr>
			                    <td class="reportlabelTight">Gravida: </td><td class="itemTight">${ante.currentPregnancyStatus.gravida}</td>
			                    <td class="reportlabelTight">Para: </td><td class="itemTight">${ante.currentPregnancyStatus.parity}</td>
			                    <td class="reportlabelTight">Quickening (weeks): </td><td class="itemTight">${ante.currentPregnancyStatus.quickening}</td>
			                </tr>
			                 <tr>
			                    <td class="reportlabel3" colspan="6">Menstrual history</td>
			                </tr>
			                <tr>
			                    <td class="reportlabelTight">Amount: </td><td class="itemTight">&nbsp;</td>
			                    <td class="reportlabelTight">&nbsp; </td><td class="itemTight">&nbsp;</td>
			                    <td class="reportlabelTight">Flow Duration (days): </td><td class="itemTight">&nbsp;</td>
			                </tr>
			                <tr>
			                    <td class="reportlabelTight">Regularity: </td><td class="itemTight">&nbsp;</td>
			                    <td class="reportlabelTight">&nbsp; </td><td class="itemTight">&nbsp;</td>
			                    <td class="reportlabelTight">Cycle Duration (days): </td><td class="itemTight">&nbsp;</td>
			                </tr>
			        	</table>
			        </td>
			        <td align="left" valign="top">
			        	<table class="enhancedtablePNCard">
			               	<tr>
			               		<td class="reportlabel2" colspan="4">Examination</td>
			                	<td class="reportlabelTight">Height (cm): </td><td class="itemTight">${ante.initialVisit.height_159R}</td>
			                </tr>
							<tr>
			                    <td class="reportlabelTight">General condition: </td><td class="itemTight" colspan="3">&nbsp;</td>
			                    <td class="reportlabelTight">&nbsp; </td><td class="itemTight">&nbsp;</td>
			                </tr>
							<tr>
			                    <td class="reportlabelTight">Teeth: </td><td class="itemTight" colspan="3">${ante.initialVisit.teeth_163R}</td>
			                    <td class="reportlabelTight">&nbsp; </td><td class="itemTight">&nbsp;</td>
			                </tr>
							<tr>
			                    <td class="reportlabelTight">Breast exam: </td><td class="itemTight" colspan="3">${ante.initialVisit.breasts_166R}</td>
			                    <td class="reportlabelTight">&nbsp; </td><td class="itemTight">&nbsp;</td>
			                </tr>
							<tr>
			                    <td class="reportlabelTight">Lung breath sounds: </td><td class="itemTight" colspan="3">${ante.initialVisit.respiratory_system_167R} ${ante.initialVisit.respiratory_system_otherR}</td>
			                    <td class="reportlabelTight">&nbsp; </td><td class="itemTight">&nbsp;</td>
			                </tr>
							<tr>
			                    <td class="reportlabelTight">Cervical cancer screening done?: </td><td class="itemTight" colspan="3">&nbsp;</td>
			                    <td class="reportlabelTight">&nbsp; </td><td class="itemTight">&nbsp;</td>
			                </tr>
			                <tr>
			                    <td class="reportlabelTight">Lung breath sounds: </td><td class="itemTight" colspan="3">${ante.initialVisit.varicosities_191R}</td>
			                    <td class="reportlabelTight">&nbsp; </td><td class="itemTight">&nbsp;</td>
			                </tr>
			                <tr>
			                    <td class="reportlabelTight">Blood group: </td><td class="itemTight">&nbsp;</td>
			                    <td class="reportlabelTight">&nbsp; </td><td class="itemTight">&nbsp;</td>
			                    <td class="reportlabelTight">Rh: </td><td class="itemTight">&nbsp;</td>
			                </tr>
			        	</table>
			        </td>
	        	</tr>
	        	<tr>
			        <td align="left" valign="top" colspan="2">
			        	<table class="enhancedtablePNCard">
			               	<tr>
				               	<td class="reportlabel2" colspan="9">G. ANTENATAL VISITS</td>
				               	<td class="reportlabelTight" colspan="19">Record the rest of the first visit in Section G. Antenatal visits (1.).
				               	Record PMTCT / TC details below in Section I PMTCT /TC</td>
			               	</tr>
			               	<tr>
			               		<td class="reportlabelVert" colspan="14">&nbsp;</td>
			               		<td class="reportlabelVert" colspan="2">URINALYSIS </td>
			               		<td class="reportlabelVert" colspan="2">&nbsp;</td>
			               		<td class="reportlabelVert" colspan="2">ITN</td>
			               		<td class="reportlabelVert" colspan="2">CONDOM</td>
			               		<td class="reportlabelVert" colspan="3">&nbsp;</td>
			               		<td class="reportlabelVert" colspan="2">PROVIDER</td>
			               		<td class="reportlabelVert">&nbsp;</td>
			               	</tr>
			               	<tr>
			               		<td class="reportlabelVert">Visit Date</td>
			               		<td class="reportlabelVert">Gest.Age</td>
			               		<td class="reportlabelVert">Fundus Height(cm)</td>
			               		<td class="reportlabelVert">Position</td>
			               		<td class="reportlabelVert">Presentation</td>
			               		<td class="reportlabelVert">Lie</td>
			               		<td class="reportlabelVert">Engaged</td>
			               		<td class="reportlabelVert">Foetal Heart</td>
			               		<td class="reportlabelVert">Genital Inspection</td>
			               		<td class="reportlabelVert">BP (Sys/Dias)</td>
			               		<td class="reportlabelVert">Pulse</td>
			               		<td class="reportlabelVert">Edema</td>
								<td class="reportlabelVert">Pallor</td>
								<td class="reportlabelVert">Wt (kg)</td>
								<td class="reportlabelVert">Alb</td>
								<td class="reportlabelVert">Glu</td>
								<td class="reportlabelVert">TB Screening</td>
								<td class="reportlabelVert">WHO Stage</td>
								<td class="reportlabelVert">Use</td>
								<td class="reportlabelVert">Issued</td>
								<td class="reportlabelVert">Use</td>
								<td class="reportlabelVert">Issued</td>
								<td class="reportlabelVert">Feed Choice</td>
								<td class="reportlabelVert">Health Education Given</td>
								<td class="reportlabelVert">Remarks</td>
								<td class="reportlabelVert">Name</td>
								<td class="reportlabelVert">Site</td>
								<td class="reportlabelVert">Date Next Visit</td>
			               	</tr>

		               		<logic:iterate id="visit" name="ante" property="anteVisits">
		               		<tr>
			               		<td class="itemTight">${visit.dateVisit}</td>
			               		<td class="itemTight">${visit.ega_129R}</td>
			               		<td class="itemTight">${visit.fundal_height_232R}</td>
			               		<td class="itemTight">${visit.position_337R}</td>
			               		<td class="itemTight">${visit.presentation_314R}</td>
			               		<td class="itemTight">${visit.lie_313R}</td>
			               		<td class="itemTight">${visit.engaged_234R}</td>
			               		<td class="itemTight">${visit.foetal_heart_rate_230R} ${visit.foetal_heart_rhythm_229R}</td>
			               		<td class="itemTight">&nbsp;</td>
			               		<td class="itemTight">${visit.bp_systolic_224R}/${visit.bp_diastolic_225R}</td>
			               		<td class="itemTight">&nbsp;</td>
			               		<td class="itemTight">${visit.oedema_231R}</td>
			               		<td class="itemTight">${visit.pallor_193R}</td>
			               		<td class="itemTight">${visit.weight_228R}</td>
			               		<td class="itemTight">${visit.urinalysis_alb_242R}</td>
			               		<td class="itemTight">${visit.urinalysis_glu_243R}</td>
			               		<td class="itemTight">&nbsp;</td>
			               		<td class="itemTight">&nbsp;</td>
			               		<td class="itemTight">${visit.patient_sleep_ITNR}</td>
			               		<td class="itemTight">&nbsp;</td>
			               		<td class="itemTight">&nbsp;</td>
			               		<td class="itemTight">&nbsp;</td>
			               		<td class="itemTight">&nbsp;</td>
			               		<td class="itemTight">&nbsp;</td>
			               		<td class="itemTight">&nbsp;</td>
			               		<td class="itemTight">
			               		<c:choose>
									<c:when test="${! empty visit.staffName}">${visit.staffName}</c:when>
									<c:otherwise>${visit.createdBy}</c:otherwise>
								</c:choose>
								</td>
			               		<td class="itemTight">${visit.siteName}</td>
			               		<td class="itemTight">${visit.date_next_apptR}</td>
		               		</tr>
		               		</logic:iterate>

		               		<tr>
		               			<td class="reportlabelVert" rowspan="2">Visit Date</td>
								<td class="reportlabelVert" rowspan="2" colspan="4">LAB REQUEST / RESULTS</td>
								<td class="reportlabelVert" colspan="4">Regimen - Days of Treatment</td>
								<td class="reportlabelVert" colspan="7">Medicines DISPENSED</td>
								<td class="reportlabelVert" colspan="2">REFFERRALS</td>
								<td class="reportlabelVert" colspan="10">&nbsp;</td>
							</tr>
							<tr>

								<td class="reportlabelVert">Regimen</td>
								<td class="reportlabelVert">Days of Treatment</td>
								<td class="reportlabelVert">Ordered CD4?</td>
								<td class="reportlabelVert">WHO Screen?</td>
								<td class="reportlabelVert">Drug Name</td>
								<td class="reportlabelVert">Dispensed</td>
								<td class="reportlabelVert">Folic</td>
								<td class="reportlabelVert">SP</td>
								<td class="reportlabelVert">Iron</td>
								<td class="reportlabelVert">Deworming</td>
								<td class="reportlabelVert">Other</td>
								<td class="reportlabelVert">Reason</td>
								<td class="reportlabelVert">Institution Name</td>
								<td class="reportlabelVert" colspan="11">&nbsp;</td>
		               		</tr>

		               		<logic:iterate id="visit" name="ante" property="ancRegLabDrugRecords">
		               		<tr>
			               		<td class="itemTight">${visit.dateVisit}</td>
			               		<td colspan="4">
			               		<c:choose>
								<c:when test="${! empty visit.labs}">
								<table class="enhancedtablePNCard">
					               		<tr>
						               		<td class="reportlabelVert">Date Lab Request</td>
											<td class="reportlabelVert">Date Results</td>
											<td class="reportlabelVert">Lab Type</td>
											<td class="reportlabelVert">Result</td>
										</tr>
					               		<logic:iterate id="lab" name="visit" property="labs">
					               		<tr>
						               		<td class="itemTight">${lab.dateVisit}</td>
						               		<td class="itemTight">${lab.dateLabResultsR}</td>
						               		<td class="itemTight">${lab.labTypeR}</td>
						               		<td class="itemTight">
						               		<c:choose>
											<c:when test="${! empty lab.resultsR}">${lab.resultsR}</c:when>
											<c:otherwise>${lab.resultsNumericR}</c:otherwise>
											</c:choose>
											</td>
					               		</tr>
					               		</logic:iterate>
				               	</table>
								</c:when>
								<c:otherwise>&nbsp;</c:otherwise>
				               	</c:choose>
			               		</td>
			               		<td class="itemTight">${visit.regimenR}</td>
			               		<td class="itemTight">${visit.days_of_treatmentR}</td>
			               		<td class="itemTight">${visit.cd4testedR}</td>
			               		<td class="itemTight">${visit.who_stageR}</td>
			               		<td class="itemTight">${visit.drugNameR}</td>
			               		<td class="itemTight">${visit.dispensedR}</td>
			               		<td class="itemTight">${visit.folic}</td>
			               		<td class="itemTight">${visit.malariaSp}</td>
			               		<td class="itemTight">${visit.iron}</td>
			               		<td class="itemTight">${visit.deworming}</td>
			               		<td class="itemTight">${visit.other}</td>
			               		<td class="itemTight">${visit.referralReason}</td>
			               		<td class="itemTight">${visit.institution}</td>
			               		<td class="itemTight">&nbsp;</td>
			               		<td class="itemTight">&nbsp;</td>
			               		<td class="itemTight">&nbsp;</td>
			               		<td class="itemTight">&nbsp;</td>
			               		<td class="itemTight">&nbsp;</td>
			               		<td class="itemTight">&nbsp;</td>
			               		<td class="itemTight">&nbsp;</td>
			               		<td class="itemTight">&nbsp;</td>
			               		<td class="itemTight">&nbsp;</td>
			               		<td class="itemTight">&nbsp;</td>
		               		</tr>
		               		</logic:iterate>
		               	</table>
		        	</td>
	        	</tr>
        	</table>
        </td>
	</tr>
</table>
		<table border="0" cellspacing="2" cellpadding="2">
			<tr>
				<td valign="top">
				<table border="0" cellspacing="2" cellpadding="2" class="enhancedtablePNCard">
					<tr>
		               	<td class="reportlabel2" colspan="3">D. OBSTETRIC HISTORY</td>
		               	<td class="reportlabelTight" colspan="4">Previous & most recent births</td>
	               	</tr>
					<tr>
						<td class="reportlabelVert">Month/Year</td>
						<td class="reportlabelVert">Duration</td>
						<td class="reportlabelVert">Mode of Delivery</td>
						<td class="reportlabelVert">Type of Labor</td>
						<td class="reportlabelVert">Birth Weight</td>
						<td class="reportlabelVert">Outcome</td>
						<td class="reportlabelVert">Puerperium</td>
					</tr>
					<logic:iterate id="visit" name="ante" property="prevPregs">
	               		<tr>
		               		<td class="itemTight">${visit.month_of_deliveryR}/${visit.year_of_delivery_51R}</td>
		               		<td class="itemTight">${visit.duration_of_labour_62R}</td>
		               		<td class="itemTight">${visit.mode_of_delivery_447R}</td>
		               		<td class="itemTight">${visit.type_of_labour_59R}</td>
		               		<td class="itemTight">
		               		<c:if test="${! empty visit.birth_weight_infant1_65R}">Infant 1: ${visit.birth_weight_infant1_65R}</c:if>
		               		<c:if test="${! empty visit.birth_weight_infant_2_1244R}">, Infant 2: ${visit.birth_weight_infant_2_1244R}</c:if>
		               		<c:if test="${! empty visit.birth_weight_infant_3_1245R}">, Infant 3: ${visit.birth_weight_infant_3_1245R}</c:if>
		               		</td>
		               		<td class="itemTight">${visit.outcome_of_pregnancy_53R}</td>
		               		<td class="itemTight">
		               		<c:if test="${visit.pphR == 'Yes'}">PPH<br/></c:if>
		               		<c:if test="${! empty visit.postpartum_i_66R}">Postpartum Infection: ${visit.postpartum_i_66R}</c:if>
		               		</td>
	               		</tr>
		           </logic:iterate>
				</table>
				</td>
				<td valign="top">
				<table border="0" cellspacing="2" cellpadding="2" class="enhancedtablePNCard">
					<tr>
		               	<td class="reportlabel2" colspan="11">H. PMTCT/T & C (PITC)</td>
	               	</tr>
					<tr>
						<td class="reportlabelVert">Test Date</td>
						<td class="reportlabelVert">Test Performed?</td>
						<td class="reportlabelVert">Test Result</td>
						<td class="reportlabelVert">Post-Test Counseling</td>
						<td class="reportlabelVert">Referred for ART?</td>
						<td class="reportlabelVert">Enrolled in Care?</td>
						<td class="reportlabelVert">Received Regimen?</td>
						<td class="reportlabelVert">Prophylaxis MEDICINES (code)</td>
						<td class="reportlabelVert">Partner tested for HIV?</td>
						<td class="reportlabelVert">If Yes, Date:</td>
						<td class="reportlabelVert">Partner Result?</td>
					</tr>
					<logic:iterate id="visit" name="ante" property="councelVisits">
	               		<tr>
		               		<td class="itemTight">${visit.testDateR}</td>
		               		<td class="itemTight">${visit.hiv_testedR}</td>
		               		<td class="itemTight">${visit.hiv_test_resultR}</td>
		               		<td class="itemTight">${visit.counselledR}</td>
		               		<td class="itemTight">${visit.referred_art_clinicR}</td>
		               		<td class="itemTight">${visit.enrolled_in_artR}</td>
		               		<td class="itemTight">${visit.receivedRegimenR}</td>
		               		<td class="itemTight">${visit.regimenR}</td>
		               		<td class="itemTight">${visit.partner_tested_for_hivR}</td>
		               		<td class="itemTight">${visit.partner_tested_dateR}</td>
		               		<td class="itemTight">${visit.partner_hiv_resultR}</td>
	               		</tr>
		           </logic:iterate>
				</table>
				</td>
			</tr>
		</table>
		<div id="reporHeader"><a href="/zeprs/home.do;jsessionid=${pageContext.request.session.id}"
			style="text-decoration: none;">
		<h1>Safe Motherhood Clinic Card - Postnatal - Site: ${zeprs_session.clientSettings.site.name}</h1>
		</a></div>
		<table border="0" cellspacing="2" cellpadding="2">
			<tr>
				<td valign="top">
				<table border="0" cellspacing="2" cellpadding="2" class="enhancedtablePNCard">
					<tr>
						<td class="reportlabel2">DISCHARGE SUMMARY</td>
						<td class="itemTight">&nbsp;</td>
						<td class="reportlabelTight">Admission Date:</td>
						<td class="itemTight"></td>
					</tr>
					<tr>
						<td class="reportlabelTight">Birth Details:</td>
						<td class="itemTight">&nbsp;</td>
						<td class="reportlabelTight">Delivery Date:</td>
						<td class="itemTight">${post.pregnancyReport.deliverySum.dateVisit}</td>
					</tr>
					<tr>
						<td class="reportlabelTight">Name of Delivery Facility:</td>
						<td class="itemTight">${post.pregnancyReport.deliverySum.siteName}</td>
						<td class="reportlabelTight">Discharge Date:</td>
						<td class="itemTight"></td>
					</tr>
				</table>
				</td>
				<td valign="top">
				<table border="0" cellspacing="2" cellpadding="2" class="enhancedtablePNCard">
					<tr>
						<td class="reportlabel2">Post-partum complications</td>
						<td class="itemTight">&nbsp;</td>
						<td class="reportlabelTight">Complications (Dellivery Summary record):</td>
						<td class="itemTight">${post.pregnancyReport.deliverySum.complications_467R} <c:if
							test="${! empty post.pregnancyReport.deliverySum.if_complications_desc_468R}">
							<br />${post.pregnancyReport.deliverySum.if_complications_desc_468R}</c:if></td>
						<td class="reportlabelTight">Postpartum Complications</td>
						<td class="itemTight">${post.pregnancyReport.puerperium.postpartum_complications_584R}</td>
					</tr>
					<tr>
						<td class="reportlabelTight">Bleeding</td>
						<td class="itemTight">${post.pregnancyReport.puerperium.bleedingR}</td>
						<td class="reportlabelTight">Lochia</td>
						<td class="itemTight">${post.pregnancyReport.puerperium.lochia_579R}</td>
						<td class="reportlabelTight">Bowels</td>
						<td class="itemTight">${post.pregnancyReport.puerperium.bowels_639R}</td>
					</tr>
					<tr>
						<td class="reportlabelTight">Micturition</td>
						<td class="itemTight">${post.pregnancyReport.puerperium.micturition_641R}</td>
						<td class="reportlabelTight">Breasts</td>
						<td class="itemTight">${post.pregnancyReport.puerperium.breasts_166R}</td>
						<td class="reportlabelTight">Wound</td>
						<td class="itemTight">${post.pregnancyReport.puerperium.wound_643R}</td>
					</tr>
					<tr>
						<td class="reportlabelTight">Pallor</td>
						<td class="itemTight">${post.pregnancyReport.puerperium.pallor_193R}</td>
						<td class="reportlabelTight">Uterus</td>
						<td class="itemTight">${post.pregnancyReport.puerperium.uterus_187R}</td>
						<td class="reportlabelTight">&nbsp;</td>
						<td class="itemTight">&nbsp;</td>
					</tr>
				</table>
				</td>
				<td valign="top">
				<table border="0" cellspacing="2" cellpadding="2" class="enhancedtablePNCard">
					<tr>
						<td class="reportlabelTight">Clinic name</td>
						<td class="itemTight">${post.pregnancyReport.deliverySum.siteName}</td>
					</tr>
					
					<c:if test="${! empty post.pregnancyReport.newborns}">
					<logic:iterate id="newborn" name="post" property="pregnancyReport.newborns" indexId="itemNum">
						<tr>
							<td valign="top" class="reportlabelTight">Newborn ${itemNum+1} APGAR (1/5/10 mins.)</td>
							<td class="itemTight">${newborn.newbornEval.apgar_score_1_min_498R}/
							${newborn.newbornEval.apgar_score_5_min_504R}/ ${newborn.newbornEval.apgar_score_10_min_510R}</td>
						</tr>
					</logic:iterate>
					</c:if>
					
				</table>
			</td>
			<td valign="top">
			<table border="0" cellspacing="2" cellpadding="2" class="enhancedtablePNCard">
			<c:if test="${! empty post.pregnancyReport.newborns}">
				<logic:iterate id="newborn" name="post" property="pregnancyReport.newborns" indexId="itemNum">
					<tr>
						<td class="reportlabelTight">Newborn ${itemNum+1} Treatment (Discharge):</td>
						<td class="itemTight">${newborn.infantDischarge.treatment_on_discharge_562R}</td>
					</tr>

					<tr>
						<td valign="top" class="reportlabelTight">Newborn ${itemNum+1} Drugs given (Newborn Eval):</td>
						<td class="itemTight" colspan="5" style="text-align: left"><c:if
							test="${! empty newborn.newbornEval.immunization_1R}">${newborn.newbornEval.immunization_1R}</c:if> <c:if
							test="${! empty newborn.newbornEval.immunization_2R}">, ${newborn.newbornEval.immunization_2R}</c:if> <c:if
							test="${! empty newborn.newbornEval.immunization_3R}">, ${newborn.newbornEval.immunization_3R}</c:if> <c:if
							test="${! empty newborn.newbornEval.immunization_4R}">, ${newborn.newbornEval.immunization_4R}</c:if> <c:if
							test="${! empty newborn.newbornEval.immunization_5R}">, ${newborn.newbornEval.immunization_5R}</c:if> <c:if
							test="${! empty newborn.newbornEval.other_drugsR}">, ${newborn.newbornEval.other_drugsR}</c:if></td>
					<tr>
						<td valign="top" class="reportlabelTight">Newborn ${itemNum+1} Drugs given (Discharge):</td>
						<td class="itemTight">${newborn.infantDischarge.immunization_1R} ${newborn.infantDischarge.immunization_2R}
						${newborn.infantDischarge.immunization_3R} ${newborn.infantDischarge.immunization_4R}
						${newborn.infantDischarge.immunization_5R}</td>
					</tr>
				</logic:iterate>
				</c:if>
				<c:if test="${! empty post.pregnancyReport.maternalDischarge}">
				<tr>
					<td valign="top" class="reportlabelTight">Maternal Treatment (Discharge):</td>
					<td class="itemTight">${post.pregnancyReport.maternalDischarge.treatment_on_discharge_595R}</td>
				</tr>
				</c:if>
			</table>
			</td>
			</tr>
		</table>
		<table class="enhancedtablePNCard">
			<tr>
				<td class="reportlabel2" colspan="39">POST NATAL VISITS (MOTHER)</td>
			</tr>
			<tr>
				<td class="reportlabelVert" rowspan="2">Visit Date</td>
				<td class="reportlabelVert" rowspan="2">Stage</td>
				<td class="reportlabelVert" rowspan="2">BP</td>
				<td class="reportlabelVert" rowspan="2">Temp</td>
				<td class="reportlabelVert" rowspan="2">Pulse</td>
				<td class="reportlabelVert" rowspan="2">General Condition</td>
				<td class="reportlabelVert" rowspan="2">Edema</td>
				<td class="reportlabelVert" rowspan="2">Pallor</td>
				<td class="reportlabelVert" rowspan="2">Wt</td>
				<td class="reportlabelVert" rowspan="2">Breasts</td>
				<td class="reportlabelVert" rowspan="2">Cancer Screening</td>
				<td class="reportlabelVert" rowspan="2">Vulva</td>
				<td class="reportlabelVert" rowspan="2">Lochia flow</td>
				<td class="reportlabelVert" rowspan="2">Lochia colour</td>
				<td class="reportlabelVert" rowspan="2">Uterus</td>
				<td class="reportlabelVert" rowspan="2">Perinium</td>
				<td class="reportlabelVert" rowspan="2">Varicose veins</td>
				<td class="reportlabelVert" rowspan="2">Urine Output</td>
				<td class="reportlabelVert" colspan="2">URINALYSIS</td>
				<td class="reportlabelVert" rowspan="2">TB Screening</td>
				<td class="reportlabelVert" rowspan="2">WHO Stage</td>
				<td class="reportlabelVert" colspan="2">Health Education</td>
				<td class="reportlabelVert" colspan="2">FAMILY PLANNING</td>
				<td class="reportlabelVert" colspan="2">FEEDING</td>
				<td class="reportlabelVert" colspan="2">ITN</td>
				<td class="reportlabelVert" rowspan="2">Remarks</td>
				<td class="reportlabelVert" colspan="2">PROVIDER</td>
				<td class="reportlabelVert" rowspan="2">Date Next Visit</td>

			</tr>
			<tr>
				<td class="reportlabelVert" >Alb</td>
				<td class="reportlabelVert" >Glu</td>
				<td class="reportlabelVert" >Health Education</td>
				<td class="reportlabelVert" >Contraceptive Advice Given</td>
				<td class="reportlabelVert" >Using Contraception</td>
				<td class="reportlabelVert" >Contraceptive Choice</td>
				<td class="reportlabelVert" >Feeding Option</td>
				<td class="reportlabelVert" >Feeding Practice</td>
				<td class="reportlabelVert" >Use?</td>
				<td class="reportlabelVert" >Issued</td>
				<td class="reportlabelVert" >Name</td>
				<td class="reportlabelVert" >Site</td>
			</tr>

			<logic:iterate id="visit" name="post" property="postnatalMaternalVisits">
				<tr>
					<td class="itemTight">${visit.dateVisit}</td>
					<td class="itemTight">${visit.postnatal_visit_601R}</td>
					<td class="itemTight">${visit.bp_systolic_224R}/${visit.bp_diastolic_225R}</td>
					<td class="itemTight">&nbsp</td><!-- temp -->
					<td class="itemTight">${visit.pulse_171R}</td>
					<td class="itemTight">&nbsp</td><!-- general condition -->
					<td class="itemTight">&nbsp</td><!-- edema -->
					<td class="itemTight">&nbsp</td><!-- pallor -->
					<td class="itemTight">&nbsp</td><!-- weight -->
					<td class="itemTight">${visit.breasts_166R}</td>
					<td class="itemTight">&nbsp</td>	<!-- cancer screening -->
					<td class="itemTight">&nbsp</td>	<!-- vulva -->
					<td class="itemTight">${visit.lochia_flow_645R}</td>
					<td class="itemTight">${visit.lochia_colou_646R}</td>
					<td class="itemTight">${visit.uterus_187R}</td>
					<td class="itemTight">${visit.perineum_580R}</td>
					<td class="itemTight">&nbsp</td>	<!-- varicose veins -->
					<td class="itemTight">${visit.micturition_641R} ${visit.micturition_desc_642R}</td>
					<td class="itemTight">${visit.urinalysis_alb_242R}</td>
					<td class="itemTight">${visit.urinalysis_glu_243R}</td>
					<td class="itemTight">&nbsp</td>	<!-- TB Screening -->
					<td class="itemTight">&nbsp</td>	<!-- WHO Stage -->
					<td class="itemTight">${visit.education1R} ${visit.education2R} ${visit.education3R}
					${visit.education4R} ${visit.education5R} ${visit.education6R} ${visit.education7R}
					<c:if test="${! empty visit.health_educa_discussed_other_674R}">${visit.health_educa_discussed_other_674R}</c:if>
					</td>
					<td class="itemTight">${visit.contraceptive_advice_669R}</td>
					<td class="itemTight">${visit.using_contraception_670R}</td>
					<td class="itemTight">${visit.contraceptive_choice_137R}
					<c:if test="${! empty visit.contraceptive_other_138R}">${visit.contraceptive_other_138R}</c:if>
					</td>
					<td class="itemTight">&nbsp</td>	<!-- Feeding Option -->
					<td class="itemTight">&nbsp</td>	<!-- Feeding Practice -->
					<td class="itemTight">&nbsp</td>	<!-- ITN Use -->
					<td class="itemTight">&nbsp</td>	<!-- ITN Issued -->
					<td class="itemTight"><c:if test="${! empty visit.diagnosis}">${visit.diagnosis}</c:if></td>	<!-- Remarks -->
					<td class="itemTight">
					<c:choose>
					<c:when test="${! empty visit.staffName}">${visit.staffName}</c:when>
					<c:otherwise>${visit.createdBy}</c:otherwise>
					</c:choose>
					</td>
					<td class="itemTight">${visit.siteName}</td>
					<td class="itemTight">&nbsp</td>	<!-- Date next visit -->
				</tr>
			</logic:iterate>
		</table>
		<table class="enhancedtablePNCard">
			<tr>
				<td class="reportlabelVert" rowspan="2">Visit Date</td>
				<td class="reportlabelVert" rowspan="2" colspan="4">LAB REQUEST / RESULTS</td>
				<td class="reportlabelVert" colspan="4">Regimen</td>
				<td class="reportlabelVert" colspan="7">Medicines DISPENSED</td>
				<td class="reportlabelVert" colspan="2">REFFERRALS</td>
			</tr>
			<tr>
				<td class="reportlabelVert">Regimen</td>
				<td class="reportlabelVert">Days of Treatment</td>
				<td class="reportlabelVert">Ordered CD4?</td>
				<td class="reportlabelVert">WHO Screen?</td>
				<td class="reportlabelVert">Drug Name</td>
				<td class="reportlabelVert">Dispensed</td>
				<td class="reportlabelVert">Folic</td>
				<td class="reportlabelVert">SP</td>
				<td class="reportlabelVert">Iron</td>
				<td class="reportlabelVert">Deworming</td>
				<td class="reportlabelVert">Other</td>
				<td class="reportlabelVert">Reason</td>
				<td class="reportlabelVert">Institution Name</td>
			</tr>

			<logic:iterate id="visit" name="post" property="postRegLabDrugRecords">
				<tr>
					<td class="itemTight">${visit.dateVisit}</td>
					<td colspan="4"><c:choose>
						<c:when test="${! empty visit.labs}">
							<table class="enhancedtablePNCard">
								<tr>
									<td class="reportlabelVert">Date Lab Request</td>
									<td class="reportlabelVert">Date Results</td>
									<td class="reportlabelVert">Lab Type</td>
									<td class="reportlabelVert">Result</td>
								</tr>
								<logic:iterate id="lab" name="visit" property="labs">
									<tr>
										<td class="itemTight">${lab.dateVisit}</td>
										<td class="itemTight">${lab.dateLabResultsR}</td>
										<td class="itemTight">${lab.labTypeR}</td>
										<td class="itemTight">
										<c:choose>
										<c:when test="${! empty lab.resultsR}">${lab.resultsR}</c:when>
										<c:otherwise>${lab.resultsNumericR}</c:otherwise>
										</c:choose>
										</td>
									</tr>
								</logic:iterate>
							</table>
						</c:when>
						<c:otherwise>&nbsp;</c:otherwise>
					</c:choose></td>
					<td class="itemTight">${visit.regimenR}</td>
					<td class="itemTight">${visit.days_of_treatmentR}</td>
					<td class="itemTight">${visit.cd4testedR}</td>
					<td class="itemTight">${visit.who_stageR}</td>
					<td class="itemTight">${visit.drugNameR}</td>
					<td class="itemTight">${visit.dispensedR}</td>
					<td class="itemTight">${visit.folic}</td>
					<td class="itemTight">${visit.malariaSp}</td>
					<td class="itemTight">${visit.iron}</td>
					<td class="itemTight">${visit.deworming}</td>
					<td class="itemTight">${visit.other}</td>
					<td class="itemTight">${visit.referralReason}</td>
					<td class="itemTight">${visit.institution}</td>
				</tr>
			</logic:iterate>
		</table>


		<table class="enhancedtablePNCard">
			<tr>
				<td class="reportlabel2" colspan="39">CHILD VISITS</td>
			</tr>
			<tr>
				<td class="reportlabelVert" rowspan="2">Visit Date</td>
				<td class="reportlabelVert" rowspan="2">Stage</td>
				<td class="reportlabelVert" rowspan="2">Wt (kg)</td>
				<td class="reportlabelVert" rowspan="2">Temp</td>
				<td class="reportlabelVert" rowspan="2">RR</td>
				<td class="reportlabelVert" rowspan="2">Feeding</td>
				<td class="reportlabelVert" rowspan="2">Eyes</td>
				<td class="reportlabelVert" rowspan="2">Cord Stump</td>
				<td class="reportlabelVert" rowspan="2">Passed Urine</td>
				<td class="reportlabelVert" rowspan="2">Child Card Issued</td>
				<td class="reportlabelVert" rowspan="2" colspan="4">LAB REQUEST / RESULTS</td>
				<td class="reportlabelVert" rowspan="2">Vit. A Given</td>
				<td class="reportlabelVert" rowspan="2">CTX</td> <!-- Cotrimoxizole (septrin) -->
				<td class="reportlabelVert" colspan="4">REGIMEN</td>
				<td class="reportlabelVert" rowspan="2">Other Medication</td>
				<td class="reportlabelVert" rowspan="2">Disposition</td>
				<td class="reportlabelVert" rowspan="2">Treatment on Discharge</td>
				<td class="reportlabelVert" rowspan="2">Initiated on Treatment?</td>
				<td class="reportlabelVert" rowspan="2">Diagnoses</td>
				<td class="reportlabelVert" rowspan="2">Remarks</td>
			</tr>
			<tr>
				<td class="reportlabelVert" >Given Nevirapine Initial Dose?</td>
				<td class="reportlabelVert" >Regimen Type</td>
				<td class="reportlabelVert" >NVP dosage</td>
				<td class="reportlabelVert" >Received ARVs?</td>
			</tr>

			<logic:iterate id="visit" name="childVisitReportList">
				<tr>
					<td class="itemTight">${visit.dateVisit}</td>
					<td class="itemTight">${visit.postnatal_visit_601R}</td>
					<td class="itemTight">${visit.weight_679R}</td>
					<td class="itemTight">${visit.temperature_infant_680R}</td>
					<td class="itemTight">&nbsp</td><!-- RR - respiratory rate -->
					<td class="itemTight"><c:if test="${! empty visit.breast_feeding_well_518R}">Breate Feeding Well: ${visit.breast_feeding_well_518R} <br/></c:if>
					${visit.feedingR} ${visit.feeding_typeR}</td>
					<td class="itemTight">${visit.eyes_523R}</td>
					<td class="itemTight">${visit.cord_at_followup_694R} ${visit.cord_at_foll_desc695R}</td>
					<td class="itemTight">${visit.urine_passed_1181R}</td>
					<td class="itemTight">${visit.birth_record_given_561R}</td>
					<td colspan="4"><c:choose>
						<c:when test="${! empty visit.labs}">
							<table class="enhancedtablePNCard">
								<tr>
									<td class="reportlabelVert">Date Lab Request</td>
									<td class="reportlabelVert">Date Results</td>
									<td class="reportlabelVert">Lab Type</td>
									<td class="reportlabelVert">Result</td>
								</tr>
								<logic:iterate id="lab" name="visit" property="labs">
									<tr>
										<td class="itemTight">${lab.dateVisit}</td>
										<td class="itemTight">${lab.dateLabResultsR}</td>
										<td class="itemTight">${lab.labTypeR}</td>
										<td class="itemTight">
										<c:choose>
										<c:when test="${! empty lab.resultsR}">${lab.resultsR}</c:when>
										<c:otherwise>${lab.resultsNumericR}</c:otherwise>
										</c:choose>
										</td>
									</tr>
								</logic:iterate>
							</table>
						</c:when>
						<c:otherwise>&nbsp;</c:otherwise>
					</c:choose></td>
					<td class="itemTight">&nbsp</td>	<!-- Vit A given -->
					<td class="itemTight">${visit.septrin_prescribed_todayR}</td>
					<td class="itemTight">${visit.initial_nevirapine_doseR}</td> <!--  discharge only -->
					<td class="itemTight">${visit.rbd_home_regimenR}</td> <!--  discharge only -->
					<td class="itemTight">${visit.dosage_nevirapine_555R}</td> <!--  discharge only -->
					<td class="itemTight">${visit.patient_received_arvR}</td>
					<td class="itemTight">
					${visit.immunization_1R}
					${visit.immunization_2R}
					${visit.immunization_3R}
					${visit.immunization_4R}
					${visit.immunization_5R}
					${visit.immunisation_desc_556R}
					</td>
					<td class="itemTight">${visit.dispositionR}</td>
					<td class="itemTight">${visit.treatment_on_discharge_562R}</td>
					<td class="itemTight">
					<c:if test="${visit.bowel_obstructionR == 'Yes'}">Bowel obstruction </c:if>
					<c:if test="${visit.indigestionR == 'Yes'}">Indigestion </c:if>
					<c:if test="${visit.opthalmia_neonatorumR == 'Yes'}">Opthalmia neonatorum </c:if>
					<c:if test="${visit.dehydrationR == 'Yes'}">Dehydration </c:if>
					<c:if test="${visit.umbilical_infectionR == 'Yes'}">Umbilical infection </c:if>
					<c:if test="${visit.diarrhoeaR == 'Yes'}">Diarrhoea </c:if>
					</td>
					<td class="itemTight">${visit.problems_comments_557R}</td><!--  discharge only -->
				</tr>
			</logic:iterate>
		</table>

	</template:put>
</template:insert>