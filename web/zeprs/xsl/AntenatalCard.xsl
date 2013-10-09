<?xml version='1.0'?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:dt="http://xsltsl.org/date-time">
    <xsl:import href="utils/stdlib.xsl"/>
    <xsl:output method="html" encoding="UTF-8" media-type="text/html"/>
    <xsl:template match="/">
	<xsl:param name="global.sessionID"/>
        <html>
            <head>
                <title>Antenatal Card:
                    <xsl:value-of select="AntenatalHistory/patientRegistration/surname"/>
                    ,
                    <xsl:value-of select="AntenatalHistory/patientRegistration/forenames"/>
                    Report Date: <xsl:value-of select="AntenatalHistory/reportDate"/>
                </title>
                <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>
            </head>
            <body>
                <p>
                    <a href="../zeprs/home.do{$global.sessionID}"  style="text-decoration: none"><strong>Antenatal Card: <xsl:value-of select="AntenatalHistory/patientRegistration/surname"/>, <xsl:value-of select="AntenatalHistory/patientRegistration/forenames"/></strong></a>
                    Report Date: <xsl:value-of select="AntenatalHistory/reportDate"/>
                </p>
                <div class="reportSection">
                    <div class="row">
                        <table class="enhancedtabletighterRep">
                            <tr>
                                <th>Patient Name</th>
                                <th>Patient Id</th>
                                <th>Age</th>
                                <th>Birth Date</th>
                                <th>Address</th>
                                <th>Husband</th>
                                <th>Clinic Name</th>
                                <th>UTH Referral</th>
                            </tr>
                            <xsl:for-each select="AntenatalHistory/patientRegistration">
                                <tr>
                                    <td>
                                        <xsl:value-of select="surname"/>
                                        ,
                                        <xsl:value-of select="forenames"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="patientIdNumber"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="currentAge"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="birthDate"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="address1"/>
                                        <xsl:if test="normalize-space(address2)">
                                            <xsl:value-of select="address2"/>
                                        </xsl:if>

                                    </td>
                                    <td>
                                        <xsl:if test="normalize-space(surnameHusband)">
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
                                        <xsl:value-of select="siteName"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="uthReferralType"/>
                                    </td>
                                </tr>
                            </xsl:for-each>
                        </table>
                        <table class="enhancedtabletighterRep">
                            <tr>
                                <th width="80px">LMP</th>
                                <th width="80px">EDD</th>
                                <th>EGA (weeks/days)</th>
                                <th>Dating Method</th>
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
                                        <xsl:if test="datingMethod = '2805'">LMP</xsl:if>
                                        <xsl:if test="datingMethod = '2806'">Uterine size</xsl:if>
                                        <xsl:if test="datingMethod = '2807'">Ultrasound</xsl:if>
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
                        </table><span class="reportHeaderStrong">Previous Pregnancies</span>
                        <table class="enhancedtabletighterRep">
                            <tr>
                                <th>Month/Year</th>
                                <th>Duration</th>
                                <th>Health During Pregnancy</th>
                                <th>Mode of Delivery</th>
                                <th>Type of Labor</th>
                                <th>Birth Weight</th>
                                <th>Outcome</th>
                                <th>Puerperium</th>
                            </tr>
                            <xsl:for-each select="AntenatalHistory/prevPregs/*">
                                <tr>
                                    <td>
                                        <xsl:value-of select="month__of__deliveryR"/>
                                        /
                                        <xsl:value-of select="year__of__delivery__51R"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="pregnancy__course__52R"/>
                                    </td>
                                    <td>
                                        <xsl:if test="eclampsiaR = 'Yes'">
                                            Eclampsia:
                                            <xsl:value-of select="eclampsiaR"/>
                                            <br/>
                                        </xsl:if>

                                        <xsl:if test="aphR = 'Yes'">
                                            APH:
                                            <xsl:value-of select="aphR"/>
                                            <br/>
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
                                        <xsl:if test="normalize-space(birth__weight__infant__2__1244R)">
                                            , Infant 2:
                                            <xsl:value-of select="birth__weight__infant__2__1244R"/>
                                        </xsl:if>
                                        <xsl:if test="normalize-space(birth__weight__infant__3__1245R)">
                                            , Infant 3:
                                            <xsl:value-of select="birth__weight__infant__3__1245R"/>
                                        </xsl:if>
                                    </td>
                                    <td>
                                        <xsl:value-of select="outcome__of__pregnancy__53R"/>
                                    </td>
                                    <td>
                                        <xsl:if test="pphR = 'Yes'">
                                            PPH:
                                            <xsl:value-of select="pphR"/>
                                            <br/>
                                        </xsl:if>
                                        <xsl:if test="normalize-space(postpartum__i__66R)">
                                            Postpartum Infection:
                                            <xsl:value-of select="postpartum__i__66R"/>
                                            <br/>
                                        </xsl:if>
                                    </td>
                                </tr>
                            </xsl:for-each>
                        </table>
                            <xsl:if test="normalize-space(AntenatalHistory/medicalSugHistoryStr)">
                            <span class="reportHeaderStrong">Medical/Surgical History: </span><xsl:value-of select="AntenatalHistory/medicalSugHistoryStr"/></xsl:if>
                    </div>
                    <div class="row">
                        <div class="leftTight">
                            <span class="reportHeaderStrong">Lab Tests</span>
                            <table class="enhancedtabletighterRep">
                                <tr>
                                    <th>Date Request</th>
                                    <th>Lab Type</th>
                                    <th>Date Results</th>
                                    <th>Results</th>
                                    <th width="60">Site</th>
                                    <th width="60">Clinician</th>
                                </tr>

                                <xsl:for-each select="AntenatalHistory">
                                    <xsl:for-each select="labTests/*">
                                        <tr>
                                            <td height="20px" width="70px">
                                                <xsl:value-of select="dateLabRequestR"/>
                                            </td>
                                            <td>
                                                <xsl:value-of select="labTypeR"/>
                                            </td>
                                            <td>
                                                <xsl:value-of select="dateLabResultsR"/>
                                            </td>
                                            <td width="50px">
                                                <xsl:value-of select="resultsR"/>
                                                <xsl:value-of select="resultsNumericR"/>
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
                            </table>
                        </div>
                        <div class="rightTight">
                            <span class="reportHeaderStrong">RPR</span>
                            <table class="enhancedtabletighterRep">
                                <tr>
                                    <th>Screen Date</th>
                                    <th>Result</th>
                                    <th>Treat. Date</th>
                                    <th>RPR Drug</th>
                                    <th>Dosage</th>
                                    <th width="100">Comments</th>
                                </tr>
                                <xsl:for-each select="AntenatalHistory/rprScreens/*">
                                    <tr>
                                        <td height="20px">
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
                            </table>
                        </div>
                    </div>
                    <div class="row">
                        <div class="leftTight">
                            <span class="reportHeaderStrong">Safe Motherhood Care</span>
                            <table cellpadding="0" cellspacing="0">
                                <tr>
                                    <td valign="top">
                                        <table class="enhancedtabletighterRep">
                                            <tr>
                                                <xsl:for-each select="AntenatalHistory/smc">
                                                    <td class="enhancedtabletighterRepth">ITN?</td>
                                                    <td height="20">
                                                        <xsl:value-of select="patient__sleep__ITNR"/>
                                                    </td>
                                                </xsl:for-each>
                                                </tr>
                                                <xsl:for-each select="AntenatalHistory/smc">
                                                    <tr>
                                                    <td class="enhancedtabletighterRepth">TT1</td>
                                                    <td height="20" width="80">
                                                        <xsl:if test="normalize-space(tt1__110R)">
                                                            <xsl:value-of select="tt1__110R"/>
                                                        </xsl:if>
                                                    </td>
                                                    </tr>
                                                    <tr>
                                                    <td class="enhancedtabletighterRepth">TT2</td>
                                                    <td height="20" width="80">
                                                        <xsl:if test="normalize-space(tt2__111R)">
                                                            <xsl:value-of select="tt2__111R"/>
                                                        </xsl:if>
                                                    </td>
                                                    </tr>
                                                    <tr>
                                                    <td class="enhancedtabletighterRepth">TT3</td>
                                                    <td height="20" width="80">
                                                        <xsl:if test="normalize-space(tt3__112R)">
                                                            <xsl:value-of select="tt3__112R"/>
                                                        </xsl:if>
                                                    </td>
                                                    </tr>
                                                    <tr>
                                                    <td class="enhancedtabletighterRepth">TT4</td>
                                                    <td height="20" width="80">
                                                        <xsl:if test="normalize-space(tt4__113R)">
                                                            <xsl:value-of select="tt4__113R"/>
                                                        </xsl:if>
                                                    </td>
                                                    </tr>
                                                    <tr>
                                                    <td class="enhancedtabletighterRepth">TT5</td>
                                                    <td height="20" width="80">
                                                        <xsl:if test="normalize-space(tt5__114R)">
                                                            <xsl:value-of select="tt5__114R"/>
                                                        </xsl:if>
                                                    </td>
                                                    </tr>
                                                </xsl:for-each>
                                        </table>
                                    </td>
                                    <td width="10px"> </td>
                                    <td valign="top">
                                        <table class="enhancedtabletighterRep">
                                            <xsl:for-each select="AntenatalHistory/hivStamp">
                                                <tr>
                                                    <td width="100"  height="20">PCR
                                                        <xsl:value-of select="pcr1"/>
                                                    </td>
                                                    <td width="100">TR
                                                        <xsl:value-of select="tr"/>
                                                    </td>
                                                    <td width="100">MGA
                                                        <xsl:value-of select="mga"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td height="20">PCR
                                                        <xsl:value-of select="pcr2"/>
                                                    </td>
                                                    <td>TA
                                                        <xsl:value-of select="ta"/>
                                                    </td>
                                                    <td>MRA
                                                        <xsl:value-of select="mra"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td height="20">PCR
                                                        <xsl:value-of select="pcr3"/>
                                                    </td>
                                                    <td>R
                                                        <xsl:value-of select="statusR"/>
                                                        <xsl:if test="hivHistory">X</xsl:if>
                                                    </td>
                                                    <td>IGA
                                                        <xsl:value-of select="iga"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td height="20">PCA
                                                        <xsl:value-of select="pca"/>
                                                    </td>
                                                    <td>NR
                                                        <xsl:value-of select="statusNr"/>
                                                    </td>
                                                    <td>INGA
                                                        <xsl:value-of select="inga"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td height="20"></td>
                                                    <td>I
                                                        <xsl:value-of select="statusI"/>
                                                    </td>
                                                    <td>IFB
                                                        <xsl:value-of select="ifb"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td height="20"></td>
                                                    <td></td>
                                                    <td>IFR
                                                        <xsl:value-of select="ifr"/>
                                                    </td>
                                                </tr>
                                            </xsl:for-each>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="rightTight">
                            <span class="reportHeaderStrong">Drug Interventions</span>
                            <table class="enhancedtabletighterRep">
                                <tr>
                                    <th width="60px">Date Given</th>
                                    <th>Folic</th>
                                    <th>Iron</th>
                                    <th>Deworming</th>
                                    <th>SP</th>
                                    <th width="100px">Other</th>
                                </tr>
                                <xsl:for-each select="AntenatalHistory/drugs/*">
                                    <tr>
                                        <td height="20px" width="60px">
                                            <xsl:value-of select="dateDispensed"/>
                                        </td>
                                        <td align="center">
                                            <xsl:if test="folic = 'true'">X</xsl:if>
                                        </td>
                                        <td align="center">
                                            <xsl:if test="iron = 'true'">X</xsl:if>
                                        </td>
                                        <td align="center">
                                            <xsl:if test="deworming = 'true'">X</xsl:if>
                                        </td>
                                        <td>
                                            <xsl:value-of select="malariaSp"/>
                                        </td>
                                        <td width="100px">
                                            <xsl:value-of select="other"/>
                                        </td>
                                    </tr>
                                </xsl:for-each>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="reportSubSection">
                    <xsl:for-each select="AntenatalHistory/pregnancies">
                        <xsl:for-each select="PregnancyReport">
                            <div class="reportSection">
                                <xsl:choose>
                                    <xsl:when test="normalize-space(datePregnancyEnd)">
                                        <div class="row">
                                            <span class="reportHeaderStrong">Pregnancy: <xsl:value-of select="datePregnancyBegin"/> - <xsl:value-of select="datePregnancyEnd"/></span>
                                        </div>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <div class="row">
                                            <span class="reportHeaderStrong">Current Pregnancy Started  <xsl:value-of select="datePregnancyBegin"/></span>
                                        </div>
                                    </xsl:otherwise>
                                </xsl:choose>

                                <div class="row">
                                    <span class="reportHeaderStrong">Routine Antenatal Visits</span>
                                    <table class="enhancedtabletighterRep" width="100%">
                                        <tr>
                                            <th>Date</th>
                                            <th>Site</th>
                                            <th>EGA__</th>
                                            <th>Fundus</th>
                                            <th width="70px">Lie____</th>
                                            <th>Present.</th>
                                            <th>Engaged</th>
                                            <th width="60px">FHR____</th>
                                            <th width="60px">BP____</th>
                                            <th>Oedema</th>
                                            <th>Weight</th>
                                            <th>Urinal.</th>
                                            <th width="60px">Next Visit</th>
                                            <th>in Z?</th>
                                            <th>Remarks</th>
                                        </tr>
                                        <xsl:for-each select="anteVisits/*">
                                            <tr>
                                                <td height="50px">
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
                                                <td width="70px">
                                                    <xsl:value-of select="lie__313R"/>
                                                </td>
                                                <td>
                                                    <xsl:value-of select="presentation__314R"/>
                                                </td>
                                                <td>
                                                    <xsl:value-of select="descent__315R"/>
                                                </td>
                                                <td width="60px">
                                                    <p xml:space="preserve">
                                                        <xsl:if test="normalize-space(foetal__heart__rate__230R)">
                                                            <xsl:value-of select="foetal__heart__rate__230R"/>
                                                        </xsl:if>
                                                    </p>
                                                </td>
                                                <td width="60px" align="center">
                                                    <p xml:space="preserve">
                                                        <xsl:value-of select="bp__systolic__224R"/>
                                                        /
                                                        <xsl:value-of select="bp__diastolic__225R"/>
                                                    </p>

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
                                                <td width="60px">
                                                    <xsl:value-of select="date__next__apptR"/>
                                                </td>
                                                <td></td>
                                                <td><xsl:value-of select="is__problemR"/></td>
                                            </tr>
                                        </xsl:for-each>
                                        <!--<tr>
                                            <td height="50px"></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td width="70px">
                                                <br/>
                                            </td>
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
                                            <td height="50px"></td>
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
                                            <td height="50px"></td>
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
                                        </tr>-->
                                    </table>
                                </div>


                                <xsl:if test="normalize-space(deliverySumc)">
                                    <div class="row">
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

                                </xsl:if>

                                <xsl:if test="normalize-space(newborns)">
                                    <div class="row">
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
                                        <table class="enhancedtabletighterRep">
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
                                                    <td height="20">
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
                                                <td height="20px" width="70px"></td>
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
                                </xsl:if>
                                <xsl:if test="normalize-space(postVisits)">
                                    <div class="row">
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
                                                    <td height="20">
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
                                                <td height="20px" width="70px"></td>
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
                                </xsl:if>
                            </div>
                        </xsl:for-each>
                    </xsl:for-each>
                    <xsl:if test="normalize-space(AntenatalHistory/probLaborVisits)">
                        <p>
                            <strong>Additional Problem or Labour Visits:</strong>
                            <xsl:value-of select="AntenatalHistory/probLaborVisits"/>
                        </p>
                    </xsl:if>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>