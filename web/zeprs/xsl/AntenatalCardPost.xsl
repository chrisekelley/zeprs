<?xml version='1.0'?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:dt="http://xsltsl.org/date-time">
    <xsl:import href="utils/stdlib.xsl"/>
    <xsl:output method="html" encoding="UTF-8" media-type="text/html"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>Antenatal Card:
                    <xsl:value-of select="AntenatalHistory/patientRegistration/surname"/>
                    ,
                    <xsl:value-of select="AntenatalHistory/patientRegistration/forenames"/>
                </title>
                <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>
            </head>
            <body>
                <h1>Antenatal Card:
                    <xsl:value-of select="AntenatalHistory/patientRegistration/surname"/>
                    ,
                    <xsl:value-of select="AntenatalHistory/patientRegistration/forenames"/>
                </h1>
                <div class="reportSection">
                    <div class="row">
                        <table class="enhancedtabletighterRep">
                            <tr>
                                <th>Patient Name, Address</th>
                                <th>Safe Motherhood Number</th>
                                <th>Age At First Attendence</th>
                                <th>Birth Date</th>
                                <th>Last Modified</th>
                                <th>Last Modified By</th>
                                <th>Clinic Name</th>
                            </tr>
                            <xsl:for-each select="AntenatalHistory/patientRegistration">
                                <tr>
                                    <td>
                                        <xsl:value-of select="surname"/>
                                        ,
                                        <xsl:value-of select="forenames"/>
                                        <br/>
                                        <xsl:value-of select="address1"/>
                                        <xsl:if test="normalize-space(surnameHusband)">
                                            <br/>
                                            Husband:
                                            <xsl:value-of select="surnameHusband"/>
                                            ,
                                            <xsl:value-of select="forenamesHusband"/>
                                        </xsl:if>
                                        <xsl:if test="normalize-space(telNoHusband)">
                                            <br/>
                                            Husband Tel.:
                                            <xsl:value-of select="telNoHusband"/>
                                        </xsl:if>
                                    </td>
                                    <td>
                                        <xsl:value-of select="obstetricRecordNumber"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="ageAtFirstAttendence"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="birthDate"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="lastModified"/>

                                    </td>
                                    <td>
                                        <xsl:value-of select="lastModifiedBy"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="siteName"/>
                                    </td>
                                </tr>
                            </xsl:for-each>
                        </table>
                        <table class="enhancedtabletighterRep">
                            <tr>
                                <th>LMP</th>
                                <th>EDD</th>
                                <th>EGA (weeks/days)</th>
                                <th>EGA (days)</th>
                                <th>Gravida</th>
                                <th>Parity</th>
                                <th>ABO Group</th>
                                <th>Rh Status</th>
                                <th>Height</th>
                            </tr>
                            <tr>
                                <xsl:for-each select="AntenatalHistory/currentPregnancyStatus">
                                    <td>
                                        <xsl:value-of select="lmp"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="edd"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="egaWeeks"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="ega"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="gravida"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="parity"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="aboGroup"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="rhesus"/>
                                    </td>

                                </xsl:for-each>
                                <td>
                                    <xsl:value-of select="AntenatalHistory/patientRegistration/height"/>
                                    cm
                                </td>
                            </tr>
                        </table>
                        <p>
                            <strong>Previous Pregnancies</strong>
                        </p>
                        <table class="enhancedtabletighterRep">
                            <tr>
                                <th>Month/Year</th>
                                <th>Duration (hours)</th>
                                <th>Health During Pregnancy</th>
                                <th>Mode of Delivery</th>
                                <th>Type of Labor</th>
                                <th>Birth Weight</th>
                                <th>Outcome</th>
                            </tr>
                            <xsl:for-each select="AntenatalHistory/prevPregs/*">
                                <tr>
                                    <td>
                                        <xsl:value-of select="month__of__deliveryR"/>
                                        /
                                        <xsl:value-of select="year__of__delivery__51R"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="duration__of__labour__62R"/>
                                    </td>
                                    <td>
                                        <xsl:if test="eclampsiaR = 'true'">
                                            Eclampsia:
                                            <xsl:value-of select="eclampsiaR"/>
                                        </xsl:if>
                                        <xsl:if test="pphR = 'true'">
                                            PPH:
                                            <xsl:value-of select="pphR"/>
                                        </xsl:if>
                                        <xsl:if test="aphR = 'true'">
                                            APH:
                                            <xsl:value-of select="aphR"/>
                                        </xsl:if>
                                        <xsl:if test="normalize-space(commentsR)">
                                            Comments:
                                            <xsl:value-of select="commentsR"/>
                                        </xsl:if>
                                    </td>
                                    <td>
                                        <xsl:value-of select="mode__of__delivery__447R"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="type__of__labourR"/>
                                    </td>
                                    <td>
                                        <xsl:if test="normalize-space(birth__weight__infant1__65R)">
                                            Infant 1:
                                            <xsl:value-of select="birth__weight__infant1__65R"/>
                                        </xsl:if>
                                        <xsl:if test="normalize-space(birth__weight__infant__2__1244)">
                                            Infant 2:
                                            <xsl:value-of select="birth__weight__infant__2__1244"/>
                                        </xsl:if>
                                        <xsl:if test="normalize-space(birth__weight__infant__3__1245)">
                                            Infant 3:
                                            <xsl:value-of select="birth__weight__infant__3__1245"/>
                                        </xsl:if>

                                    </td>
                                    <td>
                                        <xsl:value-of select="outcome__of__pregnancy__53R"/>
                                    </td>
                                </tr>
                            </xsl:for-each>
                        </table>
                        <p>
                            <strong>RPR</strong>
                        </p>
                        <table class="enhancedtabletighterRep">
                            <tr>
                                <th>Date of Screen</th>
                                <th>Result</th>
                                <th>Treatment Date</th>
                                <th>RPR Drug</th>
                                <th>Dosage</th>
                                <th width="120">Comments</th>
                            </tr>
                            <xsl:for-each select="AntenatalHistory/rprScreens/*">
                                <tr>
                                    <td>
                                        <xsl:value-of select="rpr__dateR"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="rpr__resultR"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="rpr__treatment__dateR"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="rpr__drugR"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="rpr__dosageR"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="rpr__commentsR"/>
                                    </td>
                                </tr>
                            </xsl:for-each>
                            <tr>
                                <td height="40px"></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                        </table>
                        <p>
                            <strong>Medical/Surgical History:</strong>

                            <xsl:for-each select="AntenatalHistory/medicalSugHistory">
                                Created:
                                <xsl:value-of select="created"/>
                                Modified:
                                <xsl:value-of select="lastModified"/>
                                by
                                <xsl:value-of select="lastModifiedByName"/>
                                at
                                <xsl:value-of select="siteName"/>
                            </xsl:for-each>

                        </p>
                        <table class="enhancedtabletighterRep">
                            <xsl:for-each select="AntenatalHistory">
                                <xsl:for-each select="medicalSugHistory">
                                    <tr>
                                        <xsl:for-each select="*">
                                            <xsl:choose>
                                                <xsl:when test="local-name() ='formId'"/>
                                                <xsl:when test="local-name() ='flowId'"/>
                                                <xsl:when test="local-name() ='id'"/>
                                                <xsl:when test="local-name() ='patientId'"/>
                                                <xsl:when test="local-name() ='pregnancyId'"/>
                                                <xsl:when test="local-name() ='lastModifiedBy'"/>
                                                <xsl:when test="local-name() ='createdBy'"/>
                                                <xsl:when test="local-name() ='siteId'"/>
                                                <xsl:when test="local-name() ='dateVisit'"/>
                                                <xsl:when test="local-name() ='created'"/>
                                                <xsl:when test="local-name() ='lastModified'"/>
                                                <xsl:when test="local-name() ='lastModifiedByName'"/>
                                                <xsl:when test="local-name() ='siteName'"/>
                                                <xsl:otherwise>
                                                    <th>
                                                        <xsl:value-of select="local-name()"/>
                                                    </th>
                                                </xsl:otherwise>
                                            </xsl:choose>
                                        </xsl:for-each>
                                    </tr>
                                </xsl:for-each>
                            </xsl:for-each>
                            <xsl:for-each select="AntenatalHistory">
                                <xsl:for-each select="medicalSugHistory">
                                    <tr>
                                        <xsl:for-each select="*">
                                            <xsl:choose>
                                                <xsl:when test="local-name() ='formId'"/>
                                                <xsl:when test="local-name() ='flowId'"/>
                                                <xsl:when test="local-name() ='id'"/>
                                                <xsl:when test="local-name() ='patientId'"/>
                                                <xsl:when test="local-name() ='pregnancyId'"/>
                                                <xsl:when test="local-name() ='lastModifiedBy'"/>
                                                <xsl:when test="local-name() ='createdBy'"/>
                                                <xsl:when test="local-name() ='siteId'"/>
                                                <xsl:when test="local-name() ='dateVisit'"/>
                                                <xsl:when test="local-name() ='created'"/>
                                                <xsl:when test="local-name() ='lastModified'"/>
                                                <xsl:when test="local-name() ='lastModifiedByName'"/>
                                                <xsl:when test="local-name() ='siteName'"/>
                                                <xsl:otherwise>
                                                    <td>
                                                        <xsl:value-of select="."/>
                                                    </td>
                                                </xsl:otherwise>
                                            </xsl:choose>
                                        </xsl:for-each>
                                    </tr>
                                </xsl:for-each>
                            </xsl:for-each>
                        </table>

                        <p>
                            <strong>Lab Tests</strong>
                        </p>
                        <table class="enhancedtabletighterRep">
                            <tr>
                                <th>Date Lab Request</th>
                                <th>Lab Type</th>
                                <th>Date Lab Results</th>
                                <th>Results</th>
                                <th>Reason not treating</th>
                                <th>Treatment</th>
                                <th>Comments</th>
                                <th width="60">Site</th>
                                <th width="60">Clinician</th>
                            </tr>

                            <xsl:for-each select="AntenatalHistory">
                                <xsl:for-each select="labTests/*">
                                    <tr>
                                        <td>
                                            <xsl:value-of select="dateLabRequestR"/>
                                        </td>
                                        <td>
                                            <xsl:value-of select="labTypeR"/>
                                        </td>
                                        <td>
                                            <xsl:value-of select="dateLabResultsR"/>
                                        </td>
                                        <td>
                                            <xsl:value-of select="resultsR"/>
                                            <xsl:value-of select="resultsNumericR"/>
                                        </td>

                                        <td>
                                            <xsl:value-of select="reason__not__treatingR"/>
                                        </td>
                                        <td>
                                            <xsl:value-of select="treatmentR"/>
                                        </td>
                                        <td>
                                            <xsl:value-of select="commentsR"/>
                                        </td>
                                        <td>
                                            <xsl:value-of select="siteName"/>
                                        </td>
                                        <td>
                                            <xsl:value-of select="lastModifiedByName"/>
                                        </td>
                                    </tr>
                                </xsl:for-each>
                            </xsl:for-each>
                            <tr>
                                <td height="40px" width="70px"></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td width="100px"></td>
                                <td width="100px"></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td height="40px" width="70px"></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td width="100px"></td>
                                <td width="100px"></td>
                                <td></td>
                                <td></td>
                            </tr>
                        </table>
                    </div>
                    <div class="row"><h2>Safe Motherhood Care</h2></div>
                    <div class="row">
                        <div class="left">
                            <table class="enhancedtabletighterRep">
                                <tr>
                                    <th>Prior HIV Testing</th>
                                    <th width="60">Date</th>
                                    <th>HIV Result</th>
                                </tr>
                                <xsl:for-each select="AntenatalHistory/smc">
                                    <tr>
                                        <td height="40">
                                            <xsl:value-of select="prior__hiv__testingR"/>
                                        </td>
                                        <td>
                                            <xsl:value-of select="prior__hiv__testing__dateR"/>
                                        </td>
                                        <td>
                                            <xsl:value-of select="hiv__test__resultR"/>
                                        </td>
                                    </tr>
                                </xsl:for-each>
                            </table>
                        </div>
                        <div class="right">
                            <table class="enhancedtabletighterRep">
                                <tr>
                                    <th>Regimen - Bd</th>
                                    <th>Amount dosage</th>
                                    <th>Regimen Delivery</th>
                                    <th>Syringe?</th>
                                    <th>rBd at Home?</th>
                                    <th>rBD?</th>
                                </tr>
                                <xsl:for-each select="AntenatalHistory/smc">
                                    <tr>
                                        <td height="40">
                                            <xsl:value-of select="rbd__home__regimenR"/>
                                        </td>
                                        <td>
                                            <xsl:value-of select="rbd__home__dosageR"/>
                                        </td>
                                        <td>
                                            <xsl:value-of select="regimen__deliveryR"/>
                                        </td>
                                        <td>
                                            <xsl:value-of select="rbd__homeR"/>
                                        </td>
                                        <td>
                                            <xsl:value-of select="rbd__home__administerredR"/>
                                        </td>
                                        <td>
                                            <xsl:value-of select="rbd__ldR"/>
                                        </td>
                                    </tr>
                                </xsl:for-each>
                            </table>
                        </div>
                    </div>
                    <div class="row">
                        <div class="leftTight">
                            <table class="enhancedtabletighterRep">
                                <tr>
                                    <th>ITN?</th>
                                    <th>Childhood DPT/TT?</th>
                                </tr>
                                <xsl:for-each select="AntenatalHistory/smc">
                                    <tr>
                                        <td height="40">
                                            <xsl:value-of select="patient__sleep__ITNR"/>
                                        </td>
                                        <td>
                                            <xsl:value-of select="childhood__dp__immun__107R"/>
                                        </td>
                                    </tr>
                                </xsl:for-each>
                            </table>
                        </div>
                        <div class="rightTight">
                            <table class="enhancedtabletighterRep">
                                <tr>
                                    <th>TT1</th>
                                    <th>TT2</th>
                                    <th>TT3</th>
                                    <th>TT4</th>
                                    <th>TT5</th>
                                </tr>
                                <xsl:for-each select="AntenatalHistory/smc">
                                    <tr>
                                        <td height="40" width="80">
                                            <xsl:value-of select="tt1__doneR"/>
                                            <xsl:if test="normalize-space(tt1__110R)">
                                                <br/>
                                                <xsl:value-of select="tt1__110R"/>
                                            </xsl:if>
                                        </td>
                                        <td height="40" width="80">
                                            <xsl:value-of select="tt2__doneR"/>
                                            <xsl:if test="normalize-space(tt2__110R)">
                                                <br/>
                                                <xsl:value-of select="tt2__110R"/>
                                            </xsl:if>
                                        </td>
                                        <td height="40" width="80">
                                            <xsl:value-of select="tt3__doneR"/>
                                            <xsl:if test="normalize-space(tt3__110R)">
                                                <br/>
                                                <xsl:value-of select="tt3__110R"/>
                                            </xsl:if>
                                        </td>
                                        <td height="40" width="80">
                                            <xsl:value-of select="tt4__doneR"/>
                                            <xsl:if test="normalize-space(tt4__110R)">
                                                <br/>
                                                <xsl:value-of select="tt4__110R"/>
                                            </xsl:if>
                                        </td>
                                        <td height="40" width="80">
                                            <xsl:value-of select="tt5__doneR"/>
                                            <xsl:if test="normalize-space(tt5__110R)">
                                                <br/>
                                                <xsl:value-of select="tt5__110R"/>
                                            </xsl:if>
                                        </td>
                                    </tr>
                                </xsl:for-each>
                            </table>
                        </div>
                    </div>
                    <div class="row">
                        <p>
                            <strong>Drug Interventions</strong>
                        </p>
                        <table class="enhancedtabletighterRep">
                            <tr>
                                <th>Date of Screen</th>
                                <th>Dispensed?</th>
                                <th width="80">Drugs</th>
                                <th width="120">Comments</th>
                            </tr>
                            <xsl:for-each select="AntenatalHistory/drugs/*">
                                <tr>
                                    <td>
                                        <xsl:value-of select="dateDrugInterventionR"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="dispensedR"/>
                                    </td>
                                    <td>
                                        <xsl:if test="normalize-space(drugTypeR)">
                                            <xsl:value-of select="drugTypeR"/>
                                        </xsl:if>
                                        <xsl:if test="normalize-space(drug__type2R)">
                                            <br/>
                                            <xsl:value-of select="drug__type2R"/>
                                        </xsl:if>
                                        <xsl:if test="normalize-space(drug__type3R)">
                                            <br/>
                                            <xsl:value-of select="drug__type3R"/>
                                        </xsl:if>
                                        <xsl:if test="normalize-space(drug__type4R)">
                                            <br/>
                                            <xsl:value-of select="drug__type4R"/>
                                        </xsl:if>
                                        <xsl:if test="normalize-space(drug__type5R)">
                                            <br/>
                                            <xsl:value-of select="drug__type5R"/>
                                        </xsl:if>
                                        <xsl:if test="normalize-space(drug__type6R)">
                                            <br/>
                                            <xsl:value-of select="drug__type6R"/>
                                        </xsl:if>
                                    </td>
                                    <td>
                                        <xsl:value-of select="commentsR"/>
                                    </td>
                                </tr>
                            </xsl:for-each>
                            <tr>
                                <td height="40px"></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td height="40px"></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="reportSubSection">
                    <xsl:for-each select="AntenatalHistory/pregnancies">
                        <xsl:for-each select="PregnancyReport">
                            <div class="reportSection">
                                <xsl:choose>
                                    <xsl:when test="normalize-space(datePregnancyEnd)">
                                        <div class="row">
                                            <h3>Pregnancy:
                                                <xsl:value-of select="datePregnancyBegin"/>
                                                -
                                                <xsl:value-of select="datePregnancyEnd"/>
                                            </h3>
                                        </div>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <div class="row">
                                            <h3>
                                                Current Pregnancy Started
                                                <xsl:value-of select="datePregnancyBegin"/>
                                            </h3>
                                        </div>
                                    </xsl:otherwise>
                                </xsl:choose>

                                <div class="row">
                                        <p>
                                            <strong>Routine Antenatal Visits</strong>
                                        </p>
                                        <table class="enhancedtabletighterRep">
                                            <tr>
                                                <th>Date</th>
                                                <th>Site</th>
                                                <th>EGA</th>
                                                <th>Fundus</th>
                                                <th width="60px">Lie</th>
                                                <th>Presentation</th>
                                                <th>Engaged</th>
                                                <th>Foetal Heart Rate</th>
                                                <th width="60px">BP</th>
                                                <th>Oedema</th>
                                                <th>Weight (KG)</th>
                                                <th>Urinalysis</th>
                                                <th>Next Visit</th>
                                                <th>Z?</th>
                                                <th>Remarks</th>
                                            </tr>
                                            <xsl:for-each select="anteVisits/*">
                                                <tr>
                                                    <td>
                                                        <xsl:value-of select="dateVisit"/>
                                                    </td>
                                                    <td>
                                                        <xsl:value-of select="siteName"/>
                                                    </td>
                                                    <td>
                                                        <xsl:value-of select="ega__129R"/>
                                                    </td>
                                                    <td>
                                                        <xsl:value-of select="fundal__height__232R"/>
                                                    </td>
                                                    <td>
                                                        <xsl:value-of select="lie__313R"/>
                                                    </td>
                                                    <td>
                                                        <xsl:value-of select="presentation__314R"/>
                                                    </td>
                                                    <td>
                                                        <xsl:value-of select="engaged__234R"/>
                                                    </td>
                                                    <td>
                                                        <xsl:if test="normalize-space(foetal__heart__rate__230R)">Rate:
                                                            <xsl:value-of select="foetal__heart__rate__230R"/>
                                                        </xsl:if>
                                                        <xsl:if test="normalize-space(foetal__heart__rhythm__229R)">
                                                            <br/>
                                                            Rhythm:
                                                            <xsl:value-of select="foetal__heart__rhythm__229R"/>
                                                        </xsl:if>
                                                    </td>
                                                    <td>
                                                        <xsl:value-of select="bp__systolic__224R"/>
                                                        /
                                                        <xsl:value-of select="bp__diastolic__225R"/>
                                                    </td>
                                                    <td>
                                                        <xsl:value-of select="oedema__231R"/>
                                                    </td>
                                                    <td>
                                                        <xsl:value-of select="weight__228R"/>
                                                    </td>
                                                    <td>
                                                        <xsl:if test="normalize-space(urinalysis__ace__244R)">Ace:
                                                            <xsl:value-of select="urinalysis__ace__244R"/>
                                                        </xsl:if>
                                                        <xsl:if test="normalize-space(urinalysis__alb__242R)">
                                                            <br/>
                                                            Alb:
                                                            <xsl:value-of select="urinalysis__alb__242R"/>
                                                        </xsl:if>
                                                        <xsl:if test="normalize-space(urinalysis__glu__243R)">
                                                            <br/>
                                                            Glu:
                                                            <xsl:value-of select="urinalysis__glu__243R"/>
                                                        </xsl:if>
                                                    </td>
                                                    <td>
                                                        <xsl:value-of select="date__next__apptR"/>
                                                    </td>
                                                    <td></td>
                                                    <td></td>
                                                </tr>
                                            </xsl:for-each>
                                            <tr>
                                                <td height="70px"></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                            </tr>
                                            <tr>
                                                <td height="70px"></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                            </tr>
                                            <tr>
                                                <td height="70px"></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                            </tr>
                                        </table>
                                    </div>
                                <xsl:if test="normalize-space(deliverySumc)">
                                    <div class="row">
                                        <div class="left">
                                            <p>
                                                <strong>Delivery</strong>
                                            </p>
                                            <table class="enhancedtabletighterRep">
                                                <tr>
                                                    <th>Delivery Date</th>
                                                    <th>Mode of delivery</th>
                                                    <th>Discharge Date</th>
                                                    <th>Postpartum complication</th>
                                                    <th>Family Planning</th>
                                                    <th>Given Vit. A</th>
                                                    <th>General Condition on Discharge</th>
                                                    <th>Followup</th>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <xsl:value-of select="deliverySum/dateVisit"/>
                                                    </td>

                                                    <td>
                                                        <xsl:value-of
                                                                select="deliverySum/mode__of__delivery__447R"/>
                                                        <xsl:if test="normalize-space(PregnancyReport/deliverySum/mode__of__delivery__cs__448R)">
                                                            CS:
                                                            <xsl:value-of
                                                                    select="deliverySum/mode__of__delivery__cs__448R"/>
                                                        </xsl:if>
                                                        <xsl:if test="normalize-space(deliverySum/indication__CS__forcepts__60R)">
                                                            Forcepts:
                                                            <xsl:value-of
                                                                    select="deliverySum/indication__CS__forcepts__60R"/>
                                                        </xsl:if>
                                                    </td>
                                                    <td>
                                                        <xsl:value-of select="maternalDischarge/dateVisit"/>
                                                    </td>
                                                    <td>
                                                        <xsl:value-of
                                                                select="maternalDischarge/postpartum__complications__584R"/>
                                                    </td>
                                                    <td>
                                                        <xsl:value-of
                                                                select="maternalDischarge/family__planning__discussed__594R"/>
                                                    </td>
                                                    <td>
                                                        <xsl:value-of
                                                                select="maternalDischarge/mother__receive__vit__a__585R"/>
                                                    </td>
                                                    <td>
                                                        <xsl:value-of
                                                                select="maternalDischarge/general__condition__260R"/>
                                                        <xsl:if test="normalize-space(maternalDischarge/comments__maternal__discharge__597R)">
                                                            <br/>
                                                            Comments:
                                                            <xsl:value-of
                                                                    select="maternalDischarge/comments__maternal__discharge__597R"/>
                                                        </xsl:if>
                                                    </td>
                                                    <td>
                                                        <xsl:value-of
                                                                select="maternalDischarge/date__followup__visitR"/>
                                                        <xsl:if test="normalize-space(maternalDischarge/place__followup__visitR)">
                                                            <br/>
                                                            Place:
                                                            <xsl:value-of
                                                                    select="maternalDischarge/place__followup__visitR"/>
                                                        </xsl:if>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                </xsl:if>
                                <xsl:if test="normalize-space(puerperium)">
                                    <div class="row">
                                        <div class="left">
                                            <p>
                                                <strong>Puerperium</strong>
                                            </p>
                                            <table class="enhancedtabletighterRep">
                                                <tr>
                                                    <th>Date</th>
                                                    <th>Site</th>
                                                    <th width="60px">BP</th>
                                                    <th>Pallor</th>
                                                    <th>Bladder Emptied</th>
                                                    <th>Breasts</th>
                                                    <th>Uterus</th>
                                                    <th>Perineum</th>

                                                </tr>
                                                <xsl:for-each select="puerperium/*">
                                                    <tr>
                                                        <td>
                                                            <xsl:value-of select="dateVisit"/>
                                                        </td>
                                                        <td>
                                                            <xsl:value-of select="siteName"/>
                                                        </td>
                                                        <td>
                                                            <xsl:value-of select="bp__systolic__224R"/>
                                                            /
                                                            <xsl:value-of select="bp__diastolic__225R"/>
                                                        </td>
                                                        <td>
                                                            <xsl:value-of select="pallor__193R"/>
                                                        </td>
                                                        <td>
                                                            <xsl:value-of select="bladder__emptied__437R"/>
                                                        </td>
                                                        <td>
                                                            <xsl:value-of select="breasts__166R"/>
                                                        </td>
                                                        <td>
                                                            <xsl:value-of select="uterus__187R"/>
                                                        </td>
                                                        <td>
                                                            <xsl:if test="normalize-space(perineum__intactR)">Intact:
                                                                <xsl:value-of select="perineum__intactR"/>
                                                            </xsl:if>
                                                            <xsl:if test="normalize-space(perineum__swollenR)">
                                                                <br/>
                                                                Swollen:
                                                                <xsl:value-of select="perineum__swollenR"/>
                                                            </xsl:if>
                                                            <xsl:if test="normalize-space(perineum__hematomaR)">
                                                                <br/>
                                                                Hematoma:
                                                                <xsl:value-of select="perineum__hematomaR"/>
                                                            </xsl:if>
                                                        </td>
                                                    </tr>
                                                </xsl:for-each>
                                                <tr>
                                                    <td height="40px" width="70px"></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                </xsl:if>
                                <xsl:if test="normalize-space(newborns)">
                                    <div class="row">
                                        <div class="left">
                                            <p>
                                                <strong>Newborn(s)</strong>
                                            </p>
                                            <table class="enhancedtabletighterRep">
                                                <tr>
                                                    <th>Date</th>
                                                    <th>Site</th>
                                                    <th>Baby #</th>
                                                    <th>Sex</th>
                                                    <th>Weight at Birth</th>
                                                    <th>Method of Feeding</th>
                                                    <th>Neonatal Complications</th>
                                                    <th>Urine Passed</th>
                                                </tr>
                                                <xsl:for-each select="newborns/NewbornReport">
                                                    <tr>
                                                        <td>
                                                            <xsl:value-of select="newbornEval/date__of__birthR"/>
                                                            at
                                                            <xsl:value-of select="newbornEval/time__of__birth__1514R"/>
                                                        </td>
                                                        <td>
                                                            <xsl:value-of select="newbornEval/siteName"/>
                                                        </td>
                                                        <td>
                                                            <xsl:value-of select="newbornEval/sequence__num__489R"/>
                                                        </td>
                                                        <td>
                                                            <xsl:value-of select="newbornEval/sex__490R"/>
                                                        </td>
                                                        <td>
                                                            <xsl:value-of select="newbornEval/weight__at__birth__491R"/>
                                                        </td>
                                                        <td>
                                                            <xsl:value-of select="infantDischarge/feedingR"/>
                                                            <xsl:value-of select="infantDischarge/feeding_typeR"/>
                                                        </td>
                                                        <td>
                                                            <xsl:value-of
                                                                    select="infantDischarge/weight__at__birth__491R"/>
                                                        </td>
                                                        <td>
                                                            <xsl:value-of
                                                                    select="infantDischarge/urine__passed__1181R"/>
                                                        </td>
                                                    </tr>
                                                </xsl:for-each>
                                            </table>
                                            <p>Postnatal Infant Visits</p>
                                            <table class="enhancedtabletighterRep" width="100%">
                                                <tr>
                                                    <th>Date</th>
                                                    <th>Site</th>
                                                    <th>Visit</th>
                                                    <th>Status</th>
                                                    <th>Weight</th>
                                                    <th>Temp.</th>
                                                    <th>Feeding</th>
                                                    <th>Cord</th>
                                                    <th>Immunizations</th>
                                                    <th>Disposition</th>
                                                </tr>
                                                <xsl:for-each select="newborns/NewbornReport/postnatalVisits/*">
                                                    <tr>
                                                        <td height="40">
                                                            <xsl:value-of select="dateVisit"/>
                                                        </td>
                                                        <td>
                                                            <xsl:value-of select="siteName"/>
                                                        </td>
                                                        <td>
                                                            <xsl:value-of select="postnatal__visit__601R"/>
                                                        </td>
                                                        <td>
                                                            <xsl:value-of select="infant__statusR"/>
                                                        </td>
                                                        <td>
                                                            <xsl:value-of select="weight__679R"/>
                                                        </td>
                                                        <td>
                                                            <xsl:value-of select="temperature__infant__680R"/>
                                                        </td>
                                                        <td>
                                                            <xsl:value-of select="feedingR"/>
                                                            <xsl:if test="normalize-space(feeding__typeR)">
                                                                <br/>
                                                                Type:
                                                                <xsl:value-of select="feeding__typeR"/>
                                                            </xsl:if>
                                                        </td>
                                                        <td>
                                                            <xsl:value-of select="cord__at__followup__694R"/>
                                                            <xsl:if test="normalize-space(cord__at__foll__desc695R)">
                                                                <br/>
                                                                Comment:
                                                                <xsl:value-of select="cord__at__foll__desc695R"/>
                                                            </xsl:if>
                                                        </td>
                                                        <td>
                                                            <xsl:if test="normalize-space(immunization__1R)">Immun. 1:
                                                                <xsl:value-of select="immunization__1R"/>
                                                            </xsl:if>
                                                            <xsl:if test="normalize-space(immunization__2R)">
                                                                <br/>
                                                                Immun. 2:
                                                                <xsl:value-of select="immunization__2R"/>
                                                            </xsl:if>
                                                            <xsl:if test="normalize-space(immunization__3R)">
                                                                <br/>
                                                                Immun. 3:
                                                                <xsl:value-of select="immunization__3R"/>
                                                            </xsl:if>
                                                            <xsl:if test="normalize-space(immunization__4R)">
                                                                <br/>
                                                                Immun. 4:
                                                                <xsl:value-of select="immunization__4R"/>
                                                            </xsl:if>
                                                            <xsl:if test="normalize-space(immunization__5R)">
                                                                <br/>
                                                                Immun. 5:
                                                                <xsl:value-of select="immunization__5R"/>
                                                            </xsl:if>
                                                        </td>
                                                        <td>
                                                            <xsl:value-of select="dispositionR"/>
                                                        </td>
                                                    </tr>
                                                </xsl:for-each>
                                                <tr>
                                                    <td height="40px" width="70px"></td>
                                                    <td width="40px"></td>
                                                    <td width="40px"></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                </xsl:if>
                                <xsl:if test="normalize-space(postVisits)">
                                    <div class="row">
                                        <div class="left">
                                            <p>
                                                <strong>Postnatal Maternal Visits</strong>
                                            </p>
                                            <table class="enhancedtabletighterRep">
                                                <tr>
                                                    <th>Date</th>
                                                    <th>Site</th>
                                                    <th>Complaints</th>
                                                    <th width="60px">BP</th>
                                                    <th>Perineum</th>
                                                    <th width="80px">Lochia</th>
                                                    <th>Uterus</th>
                                                    <th>Comments</th>
                                                    <th>Health Education</th>
                                                </tr>
                                                <xsl:for-each select="postVisits/*">
                                                    <tr>
                                                        <td height="40">
                                                            <xsl:value-of select="dateVisit"/>
                                                        </td>
                                                        <td>
                                                            <xsl:value-of select="siteName"/>
                                                        </td>
                                                        <td>
                                                            <xsl:value-of select="other__complaints__622R"/>
                                                        </td>
                                                        <td>
                                                            <xsl:value-of select="bp__systolic__224R"/>
                                                            /
                                                            <xsl:value-of select="bp__diastolic__225R"/>
                                                        </td>
                                                        <td>
                                                            <xsl:value-of select="perineum__580R"/>

                                                            <xsl:if test="normalize-space(perineum__other__1199R)">
                                                                <br/>
                                                                Comment:
                                                                <xsl:value-of select="perineum__other__1199R"/>
                                                            </xsl:if>
                                                            <xsl:if test="normalize-space(perineum__other__1199R)">
                                                                <br/>
                                                                Infected:
                                                                <xsl:value-of select="perineum__infect__desc__1198R"/>
                                                            </xsl:if>
                                                        </td>
                                                        <td>
                                                            <xsl:value-of select="lochia__flow__645R"/>

                                                            <xsl:if test="normalize-space(lochia__colou__646R)">
                                                                <br/>
                                                                Color:
                                                                <xsl:value-of select="lochia__colou__646R"/>
                                                            </xsl:if>
                                                            <xsl:if test="normalize-space(lochia__odor__647R)">
                                                                <br/>
                                                                Odor:
                                                                <xsl:value-of select="lochia__odor__647R"/>
                                                            </xsl:if>
                                                        </td>
                                                        <td>
                                                            <xsl:value-of select="uterus__187R"/>
                                                        </td>
                                                        <td>
                                                            <xsl:value-of select="postnatal__commentsR"/>
                                                        </td>
                                                        <td>
                                                            <xsl:value-of select="education1R"/>
                                                            <xsl:if test="normalize-space(education2R)">
                                                                ,
                                                                <xsl:value-of select="education2R"/>
                                                            </xsl:if>
                                                            <xsl:if test="normalize-space(education3R)">
                                                                ,
                                                                <xsl:value-of select="education3R"/>
                                                            </xsl:if>
                                                            <xsl:if test="normalize-space(education4R)">
                                                                ,
                                                                <xsl:value-of select="education4R"/>
                                                            </xsl:if>
                                                            <xsl:if test="normalize-space(education5R)">
                                                                ,
                                                                <xsl:value-of select="education5R"/>
                                                            </xsl:if>
                                                            <xsl:if test="normalize-space(education6R)">
                                                                ,
                                                                <xsl:value-of select="education6R"/>
                                                            </xsl:if>
                                                            <xsl:if test="normalize-space(education7R)">
                                                                ,
                                                                <xsl:value-of select="education7R"/>
                                                            </xsl:if>
                                                            <xsl:if test="normalize-space(health_educa_discussed_other_674R)">
                                                                <br/>
                                                                Comments:
                                                                <xsl:value-of
                                                                        select="health_educa_discussed_other_674R"/>
                                                            </xsl:if>
                                                        </td>
                                                    </tr>
                                                </xsl:for-each>
                                                <tr>
                                                    <td height="60px" width="70px"></td>
                                                    <td width="40px"></td>
                                                    <td width="40px"></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                </xsl:if>
                            </div>
                        </xsl:for-each>
                    </xsl:for-each>
                </div>

            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>