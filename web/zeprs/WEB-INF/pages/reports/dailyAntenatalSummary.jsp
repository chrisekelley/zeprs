<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<template:insert template='/WEB-INF/templates/template-report.jsp'>
    <template:put name='title' direct='true'>Antenatal Monthly Summary Sheet
        for ${DailyAntenatalSummary.siteName} ${DailyAntenatalSummary.beginDate}
        - ${DailyAntenatalSummary.endDate}</template:put>
    <template:put name='header' direct='true'>Antenatal Monthly Summary Sheet
        for ${DailyAntenatalSummary.siteName} ${DailyAntenatalSummary.beginDate}
        - ${DailyAntenatalSummary.endDate}</template:put>
    <template:put name='content' direct='true'>

    <p>"New ANC Client" value is calculated from the total of "Initial Visit exam" forms submitted. <br/>
        All patients should have Routine Antenatal visit exams.
        Dates with values in "Missing ANC visit" indicate that Routine Antenatal visit exams were not recorded;
        this is in error. Click on the value to get a list of links to the incorrect patient records.</p>

    <p>Monthly Totals for each Form:
        <ul>
            <li>Routine AnteForms: ${DailyAntenatalSummary.routineAnteForms}</li>
            <li>Initial VisitForms: ${DailyAntenatalSummary.initialVisitForms}</li>
            <li>Newborn EvalForms: ${DailyAntenatalSummary.newbornEvalForms}</li>
            <li>Arv RegimenForms: ${DailyAntenatalSummary.arvRegimenForms}</li>
            <li>Safe Motherhood Care Forms: ${DailyAntenatalSummary.safeMotherhoodCareForms}</li>
            <li>Counseling Visit Forms: ${DailyAntenatalSummary.smCounselingVisitForms}</li>
            <li>Lab Test Forms: ${DailyAntenatalSummary.labTestForms}</li>
            <li>RPR Forms: ${DailyAntenatalSummary.rprForms}</li>
        </ul>
    </p>
        <table border="1" cellspacing="0" cellpadding="3" width="300" class="enhancedtable">
            <tr>
                <th>Date</th>
                <th>New ANC Client</th>
                <c:choose>
                    <c:when test="${!empty DailyAntenatalSummary.reportProperties}">
                        <th>Total ANC Attendees</th>
                    </c:when>
                    <c:otherwise>
                        <th>Missing ANC Forms</th>
                    </c:otherwise>
                </c:choose>
                <th>Revisits</th>
                <th>Counseled New ANC</th>
                <th>Counseled Revisit</th>
                <th>Pre-Test Counseled</th>
                <th>HIV Tested New ANC</th>
                <th>HIV Tested Revisit</th>
                <th>Total Tested</th>
                <th>Refused Testing</th>
                <th>Received Results</th>
                <c:choose>
                    <c:when test="${!empty DailyAntenatalSummary.reportProperties}">
                    </c:when>
                    <c:otherwise>
                        <th>Uncollect. Results</th>
                    </c:otherwise>
                </c:choose>
                <th>HIV Positive:</th>
                <th>HIV Negative</th>
                <th>HIV Indeterm.</th>
                <th>Referred ARV Clinic</th>
                <th>Maternal NVP</th>
                <th>Maternal AZT - new starts</th>
                <th>Maternal AZT - refills</th>
                <th>Infant NVP</th>
                <th>RPR Tested</th>
                <th>RPR Positive</th>
                <th>RPR Negative</th>
                <th>Syphilis Treated</th>
                <th>Hgb done</th>
                <th>Hgb < 10</th>
                <th>SP1</th>
                <th>SP2</th>
                <th>SP3</th>
                <th>TT1</th>
                <th>TT2</th>
                <th>TT3</th>
                <th>TT4</th>
                <th>TT5</th>
                <th>TT Protected</th>
                <th>TT Complete</th>
                <th>First visit 1st Trim</th>
                <th>First visit 2nd Trim</th>
                <th>First visit 3rd Trim</th>
                <th>Vermox</th>
            </tr>
            <c:set var="newANCClient" value="0"/>
            <c:set var="revisits" value="0"/>
            <c:set var="counselledNew" value="0"/>
            <c:set var="counselledRevisits" value="0"/>
            <c:set var="totalANCAttendees" value="0"/>
            <c:set var="hivTestedNewAnc" value="0"/>
            <c:set var="hivTestedRevisit" value="0"/>
            <c:set var="preTestCounseled" value="0"/>
            <c:set var="hivTested" value="0"/>
            <c:set var="refusedTesting" value="0"/>
            <c:set var="receivedResults" value="0"/>
            <c:set var="uncollectedResults" value="0"/>
            <c:set var="hivPositive" value="0"/>
            <c:set var="hivNegative" value="0"/>
            <c:set var="hivIndeterminate" value="0"/>
            <c:set var="referredArt" value="0"/>
            <c:set var="maternalNVP" value="0"/>
            <c:set var="maternalAztNewStart" value="0"/>
            <c:set var="maternalAztRefills" value="0"/>
            <c:set var="infantNVP" value="0"/>
            <c:set var="rprTested" value="0"/>
            <c:set var="rprPositive" value="0"/>
            <c:set var="rprNegative" value="0"/>
            <c:set var="syphilisTreated" value="0"/>
            <c:set var="hgbDone" value="0"/>
            <c:set var="hgbLT10" value="0"/>
            <c:set var="sp1" value="0"/>
            <c:set var="sp2" value="0"/>
            <c:set var="sp3" value="0"/>
            <c:set var="tt1" value="0"/>
            <c:set var="tt2" value="0"/>
            <c:set var="tt3" value="0"/>
            <c:set var="tt4" value="0"/>
            <c:set var="tt5" value="0"/>
            <c:set var="ttProtected" value="0"/>
            <c:set var="ttComplete" value="0"/>
            <c:set var="firstVisit1stTrim" value="0"/>
            <c:set var="firstVisit2ndTrim" value="0"/>
            <c:set var="firstVisit3rdTrim" value="0"/>
            <c:set var="vermoxGiven" value="0"/>
            <logic:iterate id="summary" name="DailyAntenatalSummary" property="dailySummaries" indexId="ind">
                <c:set var="newANCClient" value="${newANCClient + summary.value.newANCClient}"/>
                <c:set var="noRoutineAnteVisit" value="${noRoutineAnteVisit + summary.value.noRoutineAnteVisit}"/>
                <c:set var="noInitialVisit" value="${noInitialVisit + summary.value.noInitialVisit}"/>
                <c:set var="noSafeMotherhoodForm" value="${noSafeMotherhoodForm + summary.value.noSafeMotherhoodForm}"/>
                <c:set var="revisits" value="${revisits + summary.value.revisits}"/>
                <c:set var="counselledNew" value="${counselledNew + summary.value.counselledNew}"/>
                <c:set var="counselledRevisits" value="${counselledRevisits + summary.value.counselledRevisits}"/>
                <c:set var="totalANCAttendees" value="${totalANCAttendees + summary.value.totalANCAttendees}"/>
                <c:set var="hivTestedNewAnc" value="${hivTestedNewAnc + summary.value.hivTestedNewAnc}"/>
                <c:set var="hivTestedRevisit" value="${hivTestedRevisit + summary.value.hivTestedRevisit}"/>
                <c:set var="preTestCounseled" value="${preTestCounseled + summary.value.preTestCounseled}"/>
                <c:set var="hivTested" value="${hivTested + summary.value.hivTested}"/>
                <c:set var="refusedTesting" value="${refusedTesting + summary.value.refusedTesting}"/>
                <c:set var="receivedResults" value="${receivedResults + summary.value.receivedResults}"/>
                <c:set var="uncollectedResults" value="${uncollectedResults + summary.value.uncollectedResults}"/>
                <c:set var="hivPositive" value="${hivPositive + summary.value.hivPositive}"/>
                <c:set var="hivNegative" value="${hivNegative + summary.value.hivNegative}"/>
                <c:set var="hivIndeterminate" value="${hivIndeterminate + summary.value.hivIndeterminate}"/>
                <c:set var="referredArt" value="${referredArt + summary.value.referredArt}"/>
                <c:set var="maternalNVP" value="${maternalNVP + summary.value.maternalNVP}"/>
                <c:set var="maternalAztNewStart" value="${maternalAztNewStart + summary.value.maternalAztNewStart}"/>
                <c:set var="maternalAztRefills" value="${maternalAztRefills + summary.value.maternalAztRefills}"/>
                <c:set var="infantNVP" value="${infantNVP + summary.value.infantNVP}"/>
                <c:set var="rprTested" value="${rprTested + summary.value.rprTested}"/>
                <c:set var="rprPositive" value="${rprPositive + summary.value.rprPositive}"/>
                <c:set var="rprNegative" value="${rprNegative + summary.value.rprNegative}"/>
                <c:set var="syphilisTreated" value="${syphilisTreated + summary.value.syphilisTreated}"/>
                <c:set var="hgbDone" value="${hgbDone + summary.value.hgbDone}"/>
                <c:set var="hgbLT10" value="${hgbLT10 + summary.value.hgbLT10}"/>
                <c:set var="sp1" value="${sp1 + summary.value.sp1}"/>
                <c:set var="sp2" value="${sp2 + summary.value.sp2}"/>
                <c:set var="sp3" value="${sp3 + summary.value.sp3}"/>
                <c:set var="tt1" value="${tt1 + summary.value.tt1}"/>
                <c:set var="tt2" value="${tt2 + summary.value.tt2}"/>
                <c:set var="tt3" value="${tt3 + summary.value.tt3}"/>
                <c:set var="tt4" value="${tt4 + summary.value.tt4}"/>
                <c:set var="tt5" value="${tt5 + summary.value.tt5}"/>
                <c:set var="ttProtected" value="${ttProtected + summary.value.ttProtected}"/>
                <c:set var="ttComplete" value="${ttComplete + summary.value.ttComplete}"/>
                <c:set var="firstVisit1stTrim" value="${firstVisit1stTrim + summary.value.firstVisit1stTrim}"/>
                <c:set var="firstVisit2ndTrim" value="${firstVisit2ndTrim + summary.value.firstVisit2ndTrim}"/>
                <c:set var="firstVisit3rdTrim" value="${firstVisit3rdTrim + summary.value.firstVisit3rdTrim}"/>
                <c:set var="vermoxGiven" value="${vermoxGiven + summary.value.vermoxGiven}"/>
            </logic:iterate>
            <tr>
                <td class="reportRowHeader">Total</td>
                <td>${newANCClient}</td>
                 <c:choose>
                    <c:when test="${!empty DailyAntenatalSummary.reportProperties}">
                        <td>${newANCClient + revisits}</td>
                    </c:when>
                    <c:otherwise>
                        <td>${noRoutineAnteVisit + noInitialVisit + noSafeMotherhoodForm}</td>
                    </c:otherwise>
                </c:choose>
                <td>${revisits}</td>
                <td>${counselledNew}</td>
                <td>${counselledRevisits}</td>
                <td>${preTestCounseled}</td>
                <td>${hivTestedNewAnc}</td>
                <td>${hivTestedRevisit}</td>
                <td>${hivTested}</td>
                <td>${refusedTesting}</td>
                <td>${receivedResults}</td>
                <c:choose>
                    <c:when test="${!empty DailyAntenatalSummary.reportProperties}">
                    </c:when>
                    <c:otherwise>
                        <td>${uncollectedResults}</td>
                    </c:otherwise>
                </c:choose>
                <td>${hivPositive}</td>
                <td>${hivNegative}</td>
                <td>${hivIndeterminate}</td>
                <td>${referredArt}</td>
                <td>${maternalNVP}</td>
                <td>${maternalAztNewStart}</td>
                <td>${maternalAztRefills}</td>
                <td>${infantNVP}</td>
                <td>${rprTested}</td>
                <td>${rprPositive}</td>
                <td>${rprNegative}</td>
                <td>${syphilisTreated}</td>
                <td>${hgbDone}</td>
                <td>${hgbLT10}</td>
                <td>${sp1}</td>
                <td>${sp2}</td>
                <td>${sp3}</td>
                <td>${tt1}</td>
                <td>${tt2}</td>
                <td>${tt3}</td>
                <td>${tt4}</td>
                <td>${tt5}</td>
                <td>${ttProtected}</td>
                <td>${ttComplete}</td>
                <td>${firstVisit1stTrim}</td>
                <td>${firstVisit2ndTrim}</td>
                <td>${firstVisit3rdTrim}</td>
                <td>${vermoxGiven}</td>
            </tr>
            <c:if test="${empty DailyAntenatalSummary.reportProperties}">
            <logic:iterate id="summary" name="DailyAntenatalSummary" property="dailySummaries" indexId="ind">
                <tr>
                    <td class="reportRowHeader">${summary.key}</td>
                    <td>${summary.value.newANCClient}</td>
                    <c:choose>
                        <c:when test="${summary.value.noRoutineAnteVisit > 0 || summary.value.noInitialVisit > 0 || summary.value.noSafeMotherhoodForm > 0}">
                            <td onclick="toggle2('patients${summary.key}')" class="alertCell">
                                <span>${summary.value.noRoutineAnteVisit + summary.value.noInitialVisit + summary.value.noSafeMotherhoodForm}</span>
                                  <span id="patients${summary.key}"
                                        style="display:none">
                                          <c:if test="${summary.value.noRoutineAnteVisit > 0}">
                                              <p>Missing Routine Ante:
                                                  <ul>
                                                      <c:forEach var="patientId"
                                                                 items="${summary.value.noRoutineAnteVisitList}">
                                                          <li><html:link action="patientHome" paramId="patientId"
                                                                         paramName="patientId">${patientId}</html:link></li>
                                                      </c:forEach>
                                                  </ul>
                                              </p>
                                          </c:if>
                                          <c:if test="${summary.value.noInitialVisit > 0}">
                                              <p>Missing Initial visit:
                                                  <ul>
                                                      <c:forEach var="patientId"
                                                                 items="${summary.value.noInitialVisitList}">
                                                          <li><html:link action="patientHome" paramId="patientId"
                                                                         paramName="patientId">${patientId}</html:link></li>
                                                      </c:forEach>
                                                  </ul>
                                              </p>
                                          </c:if>
                                          <c:if test="${summary.value.noSafeMotherhoodForm > 0}">
                                              <p>Missing SM form:
                                                  <ul>
                                                      <c:forEach var="patientId"
                                                                 items="${summary.value.noSafeMotherhoodFormList}">
                                                          <li><html:link action="patientHome" paramId="patientId"
                                                                         paramName="patientId">${patientId}</html:link></li>
                                                      </c:forEach>
                                                  </ul>
                                              </p>
                                          </c:if>
                                  </span>
                            </td>
                        </c:when>
                        <c:otherwise><td>0</td></c:otherwise>
                    </c:choose>
                    <td>${summary.value.revisits}</td>
                    <td>${summary.value.counselledNew}</td>
                    <td>${summary.value.counselledRevisits}</td>
                    <%--<td>${summary.value.totalANCAttendees}</td>--%>
                        <%--<td>&nbsp;</td>--%>
                    <td>${summary.value.preTestCounseled}</td>
                    <td>${summary.value.hivTestedNewAnc}</td>
                    <td>${summary.value.hivTestedRevisit}</td>
                    <td>${summary.value.hivTested}</td>
                    <td>${summary.value.refusedTesting}</td>
                    <td>${summary.value.receivedResults}</td>
                    <td>${summary.value.uncollectedResults}</td>
                    <td>${summary.value.hivPositive}</td>
                    <td>${summary.value.hivNegative}</td>
                    <td>${summary.value.hivIndeterminate}</td>
                    <td>${summary.value.referredArt}</td>
                    <td>${summary.value.maternalNVP}</td>
                    <td>${summary.value.maternalAztNewStart}</td>
                    <td>${summary.value.maternalAztRefills}</td>
                    <td>${summary.value.infantNVP}</td>
                    <td>${summary.value.rprTested}</td>
                    <td>${summary.value.rprPositive}</td>
                    <td>${summary.value.rprNegative}</td>
                    <td>${summary.value.syphilisTreated}</td>
                    <td>${summary.value.hgbDone}</td>
                    <td>${summary.value.hgbLT10}</td>
                    <td>${summary.value.sp1}</td>
                    <td>${summary.value.sp2}</td>
                    <td>${summary.value.sp3}</td>
                    <td>${summary.value.tt1}</td>
                    <td>${summary.value.tt2}</td>
                    <td>${summary.value.tt3}</td>
                    <td>${summary.value.tt4}</td>
                    <td>${summary.value.tt5}</td>
                    <td>${summary.value.ttProtected}</td>
                    <td>${summary.value.ttComplete}</td>
                    <td>${summary.value.firstVisit1stTrim}</td>
                    <td>${summary.value.firstVisit2ndTrim}</td>
                    <td>${summary.value.firstVisit3rdTrim}</td>
                    <td>${summary.value.vermoxGiven}</td>
                </tr>
            </logic:iterate>

            <tr>
                <td class="reportRowHeader">Total</td>
                <td>${newANCClient}</td>
                 <c:choose>
                    <c:when test="${!empty DailyAntenatalSummary.reportProperties}">
                        <td>${totalANCAttendees}</td>
                    </c:when>
                    <c:otherwise>
                        <td>${noRoutineAnteVisit + noInitialVisit + noSafeMotherhoodForm}</td>
                    </c:otherwise>
                </c:choose>
                <td>${revisits}</td>
                <td>${counselledNew}</td>
                <td>${counselledRevisits}</td>
                <td>${preTestCounseled}</td>
                <td>${hivTestedNewAnc}</td>
                <td>${hivTestedRevisit}</td>
                <td>${hivTested}</td>
                <td>${refusedTesting}</td>
                <td>${receivedResults}</td>
                <c:choose>
                    <c:when test="${!empty DailyAntenatalSummary.reportProperties}">
                    </c:when>
                    <c:otherwise>
                        <td>${uncollectedResults}</td>
                    </c:otherwise>
                </c:choose>
                <td>${hivPositive}</td>
                <td>${hivNegative}</td>
                <td>${hivIndeterminate}</td>
                <td>${referredArt}</td>
                <td>${maternalNVP}</td>
                <td>${maternalAztNewStart}</td>
                <td>${maternalAztRefills}</td>
                <td>${infantNVP}</td>
                <td>${rprTested}</td>
                <td>${rprPositive}</td>
                <td>${rprNegative}</td>
                <td>${syphilisTreated}</td>
                <td>${hgbDone}</td>
                <td>${hgbLT10}</td>
                <td>${sp1}</td>
                <td>${sp2}</td>
                <td>${sp3}</td>
                <td>${tt1}</td>
                <td>${tt2}</td>
                <td>${tt3}</td>
                <td>${tt4}</td>
                <td>${tt5}</td>
                <td>${ttProtected}</td>
                <td>${ttComplete}</td>
                <td>${firstVisit1stTrim}</td>
                <td>${firstVisit2ndTrim}</td>
                <td>${firstVisit3rdTrim}</td>
                <td>${vermoxGiven}</td>
            </tr>
        </c:if>
        </table>
    </template:put>
</template:insert>