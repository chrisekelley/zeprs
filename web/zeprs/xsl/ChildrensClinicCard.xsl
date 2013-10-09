<?xml version='1.0'?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:dt="http://xsltsl.org/date-time">
    <xsl:import href="utils/stdlib.xsl"/>
    <xsl:output method="html" encoding="UTF-8" media-type="text/html"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>Children Clinic Card:
                    <xsl:value-of select="ChildrensClinicCard/patientRegistration/surname__6R"/>
                    ,
                    <xsl:value-of select="ChildrensClinicCard/patientRegistration/forenames__7R"/>
                </title>
                <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>
            </head>
            <body>

                <h1>Children Clinic Card:
                    <xsl:value-of select="ChildrensClinicCard/patientRegistration/surname__6R"/>
                    ,
                    <xsl:value-of select="ChildrensClinicCard/patientRegistration/forenames__7R"/>
                </h1>
                <h2>Patient Overview</h2>
                <div class="reportSection">
                    <table class="enhancedtable">
                        <tr>
                            <th>Patient Name, Address</th>
                            <th>ZEPRS ID</th>
                            <th>Clinic Name</th>
                            <th>Sex</th>
                            <th>Birth Date</th>
                            <th>Birth Time</th>
                            <th>Birth Weight</th>
                        </tr>
                        <tr>
                            <xsl:for-each select="ChildrensClinicCard/patientRegistration">
                                <td>
                                    <xsl:value-of select="surname__6R"/>
                                    ,
                                    <xsl:value-of select="forenames__7R"/>
                                    <br/>
                                    <xsl:value-of select="residential__19"/>
                                </td>
                                <td>
                                    <xsl:value-of select="patient__id__numberR"/>
                                </td>
                                <td>
                                    <xsl:value-of select="siteName"/>
                                </td>
                                <td>
                                    <xsl:value-of select="sex__490R"/>
                                </td>
                                <td>
                                    <xsl:value-of select="birth__date__17R"/>
                                </td>

                            </xsl:for-each>
                            <td>
                                <xsl:value-of select="ChildrensClinicCard/newbornEval/time__of__birth__1514R"/>
                            </td>
                            <td>
                                <xsl:value-of select="ChildrensClinicCard/newbornEval/weight__at__birth__491R"/>
                            </td>
                        </tr>
                    </table>
                    <p>
                        <strong>Mother</strong>
                    </p>
                    <table class="enhancedtable">
                        <tr>
                            <th>Patient Name, Address</th>
                            <th>ZEPRS ID</th>
                            <th>Clinic Name</th>
                            <th>Birth Date</th>
                        </tr>
                        <xsl:for-each select="ChildrensClinicCard/notherRegistration">
                            <tr>
                                <td>
                                    <xsl:value-of select="surname__6R"/>
                                    ,
                                    <xsl:value-of select="forenames__7R"/>
                                    <br/>
                                    <xsl:value-of select="residential__19"/>
                                </td>
                                <td>
                                    <xsl:value-of select="patient__id__numberR"/>
                                </td>
                                <td>
                                    <xsl:value-of select="siteName"/>
                                </td>
                                <td>
                                    <xsl:value-of select="birth__date__17R"/>
                                </td>
                            </tr>
                        </xsl:for-each>
                    </table>
                    <xsl:if test="normalize-space(ChildrensClinicCard/siblings)">
                        <p>
                            <strong>Siblings</strong>
                        </p>
                        <table class="enhancedtable">
                            <tr>
                                <th>Month/Year</th>
                                <th>Children</th>
                                <th>Outcome</th>
                            </tr>
                            <xsl:for-each select="ChildrensClinicCard/siblings/*">
                                <tr>
                                    <td>
                                        <xsl:value-of select="month__of__deliveryR"/>
                                        /
                                        <xsl:value-of select="year__of__delivery__51R"/>
                                    </td>
                                    <td>
                                        <xsl:if test="normalize-space(birth__weight__infant1__65R)">
                                            <p>Child 1:
                                                <br/>
                                                Sex:
                                                <xsl:value-of select="birth__weight__infant1__65R"/>
                                                Weight:
                                                <xsl:value-of select="sex__infant1R"/>
                                            </p>
                                        </xsl:if>
                                        <xsl:if test="normalize-space(birth__weight__infant__2__1244R)">
                                            <p>Child 2:
                                                <br/>
                                                Sex:
                                                <xsl:value-of select="birth__weight__infant__2__1244R"/>
                                                Weight:
                                                <xsl:value-of select="sex__infant2R"/>
                                            </p>
                                        </xsl:if>
                                        <xsl:if test="normalize-space(birth__weight__infant__3__1245R)">
                                            <p>Child 3:
                                                <br/>
                                                Sex:
                                                <xsl:value-of select="birth__weight__infant__3__1245R"/>
                                                Weight:
                                                <xsl:value-of select="sex__infant3R"/>
                                            </p>
                                        </xsl:if>
                                    </td>
                                    <td>
                                        <xsl:value-of select="outcome__of__pregnancy__53R"/>
                                    </td>
                                </tr>
                            </xsl:for-each>
                        </table>
                    </xsl:if>

                    <!-- <p>
                        <strong>RPR</strong>
                    </p>
                   <table class="enhancedtable">
                        <tr>
                            <th>Date of Screen</th>
                            <th>Result</th>
                            <th>Treatment Date:</th>
                            <th>RPR Drug</th>
                            <th>Dosage</th>
                            <th>Comments</th>
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
                    </table>-->

                    <p>Safe Motherhood Care: ARV's</p>
                    <table class="enhancedtable">
                        <tr>
                            <th>Regimen - baby dose</th>
                            <th>Amount of dosage</th>
                            <th>Regimen Delivery</th>
                            <th>Syringe given</th>
                            <th>rBd at Home?</th>
                            <th>Received baby dose?</th>
                        </tr>
                        <xsl:for-each select="ChildrensClinicCard/smc">
                            <tr>
                                <td>
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
                <xsl:if test="normalize-space(ChildrensClinicCard/postnatalInfantVisits)">
                    <div class="reportSubSection">
                        <div class="reportSection">
                            <div class="row">
                                <div class="left">
                                    <p>Postnatal Infant Visits</p>
                                    <table class="enhancedtable">
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
                                        <xsl:for-each select="ChildrensClinicCard/postnatalInfantVisits/*">
                                            <tr>
                                                <td>
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
                                    </table>
                                </div>
                            </div>

                        </div>
                        <!--</xsl:for-each>
                        </xsl:for-each>-->
                    </div>
                </xsl:if>
                <xsl:if test="normalize-space(ChildrensClinicCard/probPostnatalInfantVisits)">
                    <div class="reportSubSection">
                        <div class="reportSection">

                            <div class="row">
                                <div class="left">
                                    <p>Problem/Postnatal Infant Visits</p>
                                    <table class="enhancedtable">
                                        <tr>
                                            <th>Date</th>
                                            <th>Site</th>
                                            <th>Status</th>
                                            <th>Weight</th>
                                            <th>Temp.</th>
                                            <th>Feeding</th>
                                            <th>Cord</th>
                                            <th>Immunizations</th>
                                            <th>Disposition</th>
                                        </tr>
                                        <xsl:for-each select="ChildrensClinicCard/probPostnatalInfantVisits/*">
                                            <tr>
                                                <td>
                                                    <xsl:value-of select="dateVisit"/>
                                                </td>
                                                <td>
                                                    <xsl:value-of select="siteName"/>
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
                                    </table>
                                </div>
                            </div>

                        </div>
                        <!--</xsl:for-each>
                        </xsl:for-each>-->
                    </div>

                </xsl:if>
                <xsl:if test="normalize-space(ChildrensClinicCard/infantDischargeSummary)">
                    <div class="reportSubSection">
                        <div class="reportSection">
                            <div class="row">
                                <div class="left">
                                    <p>Infant Discharge Summary</p>
                                    <table class="enhancedtable">
                                        <tr>
                                            <th>Date</th>
                                            <th>Site</th>
                                            <th>Status</th>
                                            <th>Weight</th>
                                            <th>Temp.</th>
                                            <th>Feeding</th>
                                            <th>Cord</th>
                                            <th>Immunizations</th>
                                            <th>Disposition</th>
                                        </tr>
                                        <xsl:for-each select="ChildrensClinicCard/infantDischargeSummary">
                                            <tr>
                                                <td>
                                                    <xsl:value-of select="dateVisit"/>
                                                </td>
                                                <td>
                                                    <xsl:value-of select="siteName"/>
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
                                    </table>
                                </div>
                            </div>

                        </div>
                        <!--</xsl:for-each>
                        </xsl:for-each>-->
                    </div>
                </xsl:if>
            </body>
        </html>


    </xsl:template>
</xsl:stylesheet>