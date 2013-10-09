<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <head><title>PatientRegistrationReport</title>
   <link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css"/>   </head>  <body>
    <table class="enhancedtable">
      <tr>
      <th>Surname</th>
      <th>Forenames</th>
      <th>NRC No.</th>
      <th>Safe Motherhood Number</th>
      <th>District</th>
      <th>Site</th>
      <th>Patient ID number</th>
      <th>Age at First Attendance</th>
      <th>Birth Date</th>
      <th>Education Status</th>
      <th>Residential Address</th>
      <th>Community or Compound</th>
      <th>Date of Residence Change</th>
      <th>Marital Status</th>
      <th>Occupation</th>
      <th>Occupation (Other, describe)</th>
      <th>Nearby Place of Worship</th>
      <th>Religion</th>
      <th>Religion (Other, describe)</th>
      <th>Surname of Patient's Father</th>
      <th>Forenames of Patient's Father</th>
      <th>Surname of Husband/Partner</th>
      <th>Forenames of Husband/Partner</th>
      <th>Occupation of Husband/Partner</th>
      <th>Tel. No. of Husband/Partner</th>
      <th>Surname of Guardian</th>
      <th>Forenames of Guardian</th>
      <th>Surname of Emergency Contact If Different From Above</th>
      <th>Forenames of Emergency Contact</th>
      <th>Address of Emergency Contact</th>
      <th>Tel. No. of Emergency Contact</th>
      </tr>
      <xsl:for-each select="list/Pati">
      <tr>
         <td><xsl:value-of select="surname_6"/></td>
         <td><xsl:value-of select="forenames_7"/></td>
         <td><xsl:value-of select="nrc_no_9"/></td>
         <td><xsl:value-of select="obstetric_record_number_1134"/></td>
         <td><xsl:value-of select="district_pat_13"/></td>
         <td><xsl:value-of select="new_patient_site_id"/></td>
         <td><xsl:value-of select="patient_id_number"/></td>
         <td><xsl:value-of select="age_at_first_attendence_1135"/></td>
         <td><xsl:value-of select="birth_date_17"/></td>
         <td><xsl:value-of select="education_st_11"/></td>
         <td><xsl:value-of select="residential_19"/></td>
         <td><xsl:value-of select="residential_20"/></td>
         <td><xsl:value-of select="date_of_resi_21"/></td>
         <td><xsl:value-of select="marital_stat_10"/></td>
         <td><xsl:value-of select="occupation_12"/></td>
         <td><xsl:value-of select="occupation_other"/></td>
         <td><xsl:value-of select="nearby_place_worship_39"/></td>
         <td><xsl:value-of select="religion_1239"/></td>
         <td><xsl:value-of select="religion_other_1240"/></td>
         <td><xsl:value-of select="surname_p_father_24"/></td>
         <td><xsl:value-of select="forenames_p_father_25"/></td>
         <td><xsl:value-of select="surname_husband_26"/></td>
         <td><xsl:value-of select="forenames_husband_27"/></td>
         <td><xsl:value-of select="occupation_husband_28"/></td>
         <td><xsl:value-of select="tel_no_husband_32"/></td>
         <td><xsl:value-of select="surname_guardian_33"/></td>
         <td><xsl:value-of select="forenames_guardian_34"/></td>
         <td><xsl:value-of select="surname_emerg_contact_35"/></td>
         <td><xsl:value-of select="forenames_emerg_contact_36"/></td>
         <td><xsl:value-of select="address_emerg_contact_37"/></td>
         <td><xsl:value-of select="tel_no_emerg_contact_38"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>