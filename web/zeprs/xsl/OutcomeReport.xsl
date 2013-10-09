<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
        <html>
            <head>
                <title>Outcome Report</title>
                <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>
            </head>
            <body>
                <table class="enhancedtable">
                    <tr>
                        <th>Record Id</th>
                        <th>Encounter Id</th>
                        <th>Patient Id</th>
                        <th>Active</th>
                        <th>Pregnancy Id</th>
                        <th>Form Id</th>
                        <th>Formfield Id</th>
                        <th>Ruledefinition Id</th>
                        <th>Outcome Type</th>
                        <th>Message</th>
                        <th>Last Modified</th>
                        <th>Created</th>
                        <th>Last Modified By</th>
                        <th>Patient Name</th>
                        <th>Clinic Name</th>
                    </tr>
                    <xsl:for-each select="list/OutcomeReport">
                        <tr>
                            <td>
                                <xsl:value-of select="id"/>
                            </td>
                            <td>
                                <xsl:value-of select="encounterId"/>
                            </td>
                            <td>
                                <xsl:value-of select="patientId"/>
                            </td>
                            <td>
                                <xsl:value-of select="active"/>
                            </td>
                            <td>
                                <xsl:value-of select="pregnancyId"/>
                            </td>
                            <td>
                                <xsl:value-of select="formId"/>
                            </td>
                            <td>
                                <xsl:value-of select="formFieldId"/>
                            </td>
                            <td>
                                <xsl:value-of select="ruleDefinitionId"/>
                            </td>
                            <td>
                                <xsl:value-of select="outcomeType"/>
                            </td>
                            <td>
                                <xsl:value-of select="message"/>
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
                                <xsl:value-of select="patientName"/>
                            </td>
                            <td>
                                <xsl:value-of select="clinicName"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>