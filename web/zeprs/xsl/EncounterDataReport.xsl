<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
        <html>
            <head>
                <title>Encounter Report</title>
                <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>
            </head>
            <body>
                <h2>Encounter Data Report</h2>
                <p>This report lists data about each enounter, sorted by patient id and date created.
                    The form name is truncated to keep the chart from getting too wide.</p>
                <table class="enhancedtable">
                    <tr>
                        <th>Enc. Id</th>
                        <th>Patient Id</th>
                        <th>Flow Id</th>
                        <th>Form Id</th>
                        <th>Form Name</th>
                        <th>Date Visit</th>
                        <th>Preg. Id</th>
                        <th>Last Mod.</th>
                        <th>Created</th>
                        <th>Last Mod. By</th>
                        <th>Created By</th>
                        <th>Site Id</th>
                        <th>Surname</th>
                        <th>First Name</th>
                        <th>Zeprs Id</th>
                        <th>Staff Name</th>
                    </tr>
                    <xsl:for-each select="list/Encounter">
                        <tr>
                            <td>
                                <xsl:value-of select="id"/>
                            </td>
                            <td>
                                <xsl:value-of select="patientId"/>
                            </td>
                            <td>
                                <xsl:value-of select="flowId"/>
                            </td>
                            <td>
                                <xsl:value-of select="formId"/>
                            </td>
                            <td>
                                <xsl:value-of select="formName"/>
                            </td>
                            <td>
                                <xsl:value-of select="dateVisit"/>
                            </td>
                            <td>
                                <xsl:value-of select="pregnancyId"/>
                            </td>
                            <td>
                                <xsl:value-of select="lastModified"/>
                            </td>
                            <td>
                                <xsl:value-of select="created"/>
                            </td>
                            <td>
                                <xsl:value-of select="lastModifiedBy"/>
                            </td>
                            <td>
                                <xsl:value-of select="createdBy"/>
                            </td>
                            <td>
                                <xsl:value-of select="siteId"/>
                            </td>
                            <td>
                                <xsl:value-of select="surname"/>
                            </td>
                            <td>
                                <xsl:value-of select="firstName"/>
                            </td>
                            <td>
                                <xsl:value-of select="zeprsId"/>
                            </td>
                            <td>
                                <xsl:value-of select="staffName"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>