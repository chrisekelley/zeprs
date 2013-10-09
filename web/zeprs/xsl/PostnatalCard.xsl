<?xml version='1.0'?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:dt="http://xsltsl.org/date-time">
    <xsl:import href="utils/stdlib.xsl"/>
    <xsl:output method="html" encoding="UTF-8" media-type="text/html"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>Postnatal Card:
                    <xsl:value-of select="PostnatalHistory/patientRegistration/surname"/>
                    ,
                    <xsl:value-of select="PostnatalHistory/patientRegistration/forenames"/>
                </title>
                <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>
            </head>
            <body>
                <h1>Postnatal Card:
                    <xsl:value-of select="PostnatalHistory/patientRegistration/surname"/>
                    ,
                    <xsl:value-of select="PostnatalHistory/patientRegistration/forenames"/>
                </h1>
                <p>Note: This report is under development.</p>
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
                            <xsl:for-each select="PostnatalHistory/patientRegistration">
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
                                <th>Gravida</th>
                                <th>Parity</th>
                                <th>ABO Group</th>
                                <th>Rh Status</th>
                                <th>Height</th>
                            </tr>
                            <tr>
                                <xsl:for-each select="PostnatalHistory/currentPregnancyStatus">
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
                                    <xsl:value-of select="PostnatalHistory/patientRegistration/height"/>
                                    cm
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="reportSubSection">
                    <xsl:for-each select="PostnatalHistory/pregnancyReport">
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

                            <xsl:if test="normalize-space(deliverySum)">
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
                                        <xsl:if test="normalize-space(newborns/NewbornReport/postnatalVisits)">
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
                                        </xsl:if>
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
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>