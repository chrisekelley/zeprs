<%@ page import="java.util.Date"%>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<template:insert template='/WEB-INF/templates/template-simple.jsp'>
<template:put name='title' content='Home' direct='true'/>
<template:put name='header' content='Recent Changes' direct='true'/>
<template:put name='content' direct='true'>
<div id="changelog">
<ul>
    <li>August 3, 2005:
        <ul>
            <li>URL's to utility functions:
                <ul>
                    <li><html:link href="admin/createPatient.do">Create Patient Record (for testing)</html:link> - This creates a patient ready for partograph.</li>
                    <li><html:link href="admin/ruleList.do">View List of Rules</html:link></li>
                    <li><html:link href="archive.do">View Encounter Value archive</html:link></li>
                </ul>
            </li>
        </ul>
    </li>
    <li>July 22, 2005:
        <ul>
            <li>Safe motherhood form field additions (insecticide treated nets, UI tweaks)</li>
            <li>Reinstate EGA in template</li>
            <li>Previous Pregnancies changes (delete Date at top, add sex, year, etc.</li>
            <li>Problem/Labour visit changes (spontaneous/induced labour checkbox, flow changes)</li>
            <li>Delivery Summary calc (in progress)</li>
            <li>Navigation changes (remove labs from side nav and place as link in safe motherhood, change nicu to nicu pediatrics)</li>
            <li>Improvied "Bookmark" ability: if session times-out, user only needs to hit refresh and login to continue working on same page.</li>
            <li>More backend changes, autopopulation of test patients</li>
        </ul>
    </li>
    <li>July 11, 2005:
        <ul>
            <li>Safe motherhood form field additions</li>
            <li>Backend changes (DAO refactoring)</li>
        </ul>
    </li>
    <li>June 1, 2005:
        <ul>
            <li>Pregnancy Dating form/chart</li>
            <li>Previous pregnancies form/chart - click "Add" to add more pregnancies; click done to go to next form.</li>
            <li>Routine ante made into vertical chart, added problem checkbox</li>
            <li>UTH Discharge - Antenatal Hospitalization Summary -  changes</li>
            <li>UTH Discharge - Antenatal Hospitalization Summary -  changes</li>
            <li>Print template</li>
            <li>More work on flow</li>
            <li>Display of child/parent on left nav</li>
        </ul>
    </li>
    <li>May 16, 2005:
        <ul>
            <li>First build that reflects changes from the Lusaka trip. </li>
            <li>New antenatal chart, puerperium, safe motherhood, and pregnancy dating forms</li>
            <li>Changes to overall flow of application- mostly in antenatal process. Also, when you select a patient, the app attempts to place you in in the relevant form - if in antenatal, puts you right into the antenatal form;
if in partograph, puts you in partograph.</li>
        </ul>
    </li>
    <li>April 7, 2005:
        <ul>
            <li>ZEPRS app has alot of changes under-the-hood. Most importantly, encounters are no longer stored in the big "bit bucket;"
            instead, each form has its own table, and metadata about each encounter (date, user logged in, form id, site, etc) is stored in the new "encounter" table.</li>
            <li>When newborn is created, system automatically creates instances of Patient Registration and Newborn Eval forms.
            You now may edit the Patient Registration form for newborn when you have a newborn loaded and click Demographics.
            When you have a newborn loaded the site works similarly to when you have a mother loaded (same menu concepts).  </li>
            <li>Pop-up calendar widget now appears under fields when you scroll - user no longer must scroll up to use calendar widget.
            Be sure to do a hard refresh (shift Control R) to view this change (</li>
            <li>Site Names: Numbering after the site name.</li>
            <li>Empty search results – added a message that states” There are no results for your query.” – table is not even displayed.</li>
            <li>Change DISTRICT ID to be ZEPRS ID.</li>
            <li>All chart items load. EGA calculated each time a form submitted that has a value that is being tracked
            (EGA,Fundal Height,AC,ALB,GLU,Weight,hB,BP,Oedema) and displayed in chart.</li>
            <li>More work done on Problem page items - solved a session bug.</li>
            <li>Deleted extraneous fields in Antenatal Record Exam and Subsequent Visits and fixed date fields that were erroneously storing today's date when form submitted.</li>
            <li>Problem/Labor Visit – bug – antepartum and intrapartum showing up the wrong reasons for evaluation etc – reversed - fixed.</li>
            <li>Problem/Labor Visit – bug – message that Problem/Labor visit needs to be filled out - now displays - fixed</li>
            <li>Added float data type - can now have numbers w/ decimal points.</li>
            <li>System-generated problems:
                <ul>
                <li>System will now evaluate problems when an encounter is being edited.</li>
                <li>The following fields now create info or referral problems:</li>
                    <ul>
                    <li>If the diastolic blood pressure is over 110 (mother) : Antenatal Record Exam and Subsequent Visits </li>
                    <li>If the baby has not passed urine or stool: Newborn eval - urine</li>
                    <li>If the Apgar Score is less than 6, then this should trigger an active problem (final apgar score).: Newborn eval: Apgar score, 10 mins.</li>
                    <li>If the temperature is below 36.5, above 37.5 Temp is a shared field – in the following forms:<br/>
                    a.	Admission at Labour<br/>
                    b.	Antenatal Hospitalization Summary <br/>
                    c.	Antenatal Record Exam and Subsequent Visits <br/>
                    d.	Initial Visit Physical Exam<br/>
                    e.	Maternal Discharge Summary <br/>
                    f.	Observations for Latent Phase of 1st Stage of Labour <br/>
                    g.	Postnatal Hospitalization Summary<br/>
                    h.	Problem/Labor Visit<br/>
                    i.	Puerperal Complications
                    </li>
                    <li>If the birth weight is below 1.2 KG, more than 4.5 KG: Newborn Evaluation: Weight at Birth</li>
                    <li>If the baby received nevirapine: Newborn Evaluation: Immunizations Given</li>
                    <li>Any positive lab test results, including Rh negative – these create “Info problems” – not referrals <br/>
                    a.	Rh pos. and neg.<br/>
                    b.	RPR: reactive <br/>
                    c.	Hb not null <br/>
                    d.	Cervical smear positive<br/>
                    e.	Hiv result <br/>
                    f.	Sickle Cell Test Results: not = normal</li>
                    <li>Referral to UTH
                    a.	Problem/Labor Visit: Disposition field  <br/>
                    b.	Maternal Discharge Summary: Outcome of Mother   <br/>
                    c.	Problem/Postnatal Visit: Disposition </li>
                     <li>If the patient’s (mothers) temp is > 38: See temp above </li>
                    <li>The following create referral problems:<br/>
                     History: Past Medical and Surgical – if the patient has epilepsy <br/>
                     Problem/Labor Visit – if the patient has thyroid that is not ‘normal’ <br/>
                     History: Past Medical and Surgical – if the patient has diabetes <br/>
                     History: Past Medical and Surgical – if the patient has hypertension <br/>
                     History: Past Medical and Surgical – if the patient has heart disease  <br/>
                     History: Past Medical and Surgical – if the patient has rheumatic heart disease <br/>
                     History: Past Medical and Surgical – if the patient has kidney disease<br/>
                     History: Past Medical and Surgical – if the patient has liver disease <br/>
                     History: Past Medical and Surgical – if the patient has DVT/clots  <br/>
                     History: Past Medical and Surgical – if the patient has sickle cell disease <br/>
                     History: Past Medical and Surgical – if the patient is currently taking medicine  <br/>
                     History: Past Medical and Surgical – if the patient had pelvic operation</li>
                     </ul>
                 </li>
                </ul>
    </li>
    <li>March 8, 2005:</li>
        <ul>
            <li>Routine Antenatal form may become a problem</li>
            <li>Problem/Postnatal form</li>
            <li>Merged Infant Discharge Summary w/ Newborn Eval</li>
            <li>Enhanced search - A-Z, search by site</li>
            <li>Moved NICU to infant only display</li>
            <li>Delivery Summary - can add infants directly on that page.</li>
            <li>Newborn Eval: Immunizations are multiselect, Resuscitation Methods changed to yes/no/text entry. </li>
            <li>UTH Neonatal: Maternal risk factors made multiselect </li>
            <li>Problem/Labour visit - Vaginal exam diagrams reworked.</li>
        </ul>
    <li>February 15, 2005:</li>
        <ul>
            <li>Layout changes to forms, as well as field corrections</li>
            <li>Form displayed in Chart depends on current flow of patient:</li>
                <ul>
                    <li>Antenatal, History, Labs, Ultrasound: displays Antenatal Record Exam and Subsequent Visits</li>
                    <li>Labour: displays Problem/Labor Visit</li>
                    <li>Postnatal: displays Postnatal Maternal Visit</li>
                </ul>
        </ul>
    <li>January 26, 2005:</li>
        <ul>
            <li>New form - Pregnancy Conclusion - in Delivery Summary/Postnatal flow. This is used to end a pregnancy.
            This is currently the only way to end a pregnancy - submitting the still birth form does not end a pregnancy -
            although it may in the future.</li>
        </ul>
    <li>January 25, 2005:</li>
        <ul>
            <li>The following two changes are not yet apparent to the user, but are structural changes:</li>
            <ul>
                <li>Pregnancies are now tracked - soon it will be possible to terminate a pregnancy, and being another one.</li>
                <li>Added a form type, to differentiate between pregnancy-related forms, and newborn forms.</li>
            </ul>
        </ul>
    <li>January 21, 2005:</li>
        <ul>
            <li>Current Medicine widget: History/Current Medicine</li>
            <li>Mother/child relationship:
            <ul>
                <li>This is created when user submits Newborn Evaluation in Delivery Summary/Postnatal.</li>
                <li>After the first Newborn Summary form is submitted, the link becomes "Add Newborn."</li>
                <li>Click demographics for Jones, Jane to view link to infant on right side of screen.</li>
                <li>Infant has reduced set of links on left nav strip. Click Demographics to view link to mother.</li>
            </ul>
        </ul>
     <li>January 17, 2005:</li>
        <ul>
            <li>Labs on left nav</li>
            <li>Link to Last form submitted displayed on chart (Clicking "Most Recent Form" displays data from that form.) </li>
            <li>Problem page – incomplete forms only. If user skips ahead, incomplete forms still display in problem page.</li>
            <li>Current flow tracking - part of solution to previous item. Can be used to query where a patient is in birth process.</li>
            <li>Combined past med/surg hist </li>
            <li>Problem antepartum form </li>
            <li>Vitals on problem labour visit – add pulse</li>
            <li>Added date to main template (next to time)</li>
            <li>Still birth delivery record form moved to Delivery Summary/Postnatal flow </li>
        </ul>
    <li>November 29, 2004:</li>
        <ul>
            <li>Created single Problem/Labour visit form, Delivery Summary Form. Many fixes to Antepartum forms as well as changes to flows.</li>
        </ul>
    <li>November 10, 2004:</li>
        <ul>
            <li>Added Problem/Labour Visit flow. It is available from the left nav strip, after Gyn.</li>
        </ul>
    <li>November 9, 2004:</li>
        <ul>
            <li>Problems/comments - rounded out its features. Problem list contains system-generated outcomes as
            well as problems created by clinicians.</li>
            <li>Small UI tweaks (left nav strip)</li>
        </ul>
    <li>October 28, 2004:</li>
        <ul>
            <li>Problems</li>
                <ul>
                    <li>Can view Active or Inactive Problems.</li>
                    <li>To edit a Problem or set it to Inactive, click (e) beside the problem name.</li>
                    <li>When making a problem inactive, the user may comment on reasons for making the problem inactive.</li>
                    <li>Current problem is highlighted in blue. Comments for that problem are shown in the column to the right of the problem list.</li>
                    <li>First time user view problem page, the problem with the most recent comment is displayed first. Note that this may be different from the most recent problem.</li>
                </ul>
           <li>Permissions</li>
                <ul>
                    <li>User: report Password report11 - may not view patient home or create new patient</li>
                    <li>User: clinic Password clinic11 - may not administer the app (Admin link does not appear on left nav strip).</li>
                    <li>User: zepadmin Password zepadmin11 - Full administrative privs..</li>
                </ul>
           <li>Misc</li>
                <ul>
                    <li>Date of visit added to each form</li>
                </ul>
        </ul>
    <li>October 14, 2004:</li>
        <ul>
            <li>New build - Tasklist
                <ul>
                    <li>Tasklist is now available to help clinician complete the process. </li>
                    <li>After submitting a form, system provides the next form in the sequence.</li>
                </ul>
        </ul>
    <li>October 12, 2004:</li>
        <ul>
            <li>Information about Tasks, Problems, and Rules:
                <ul>
                    <li><strong>Tasks:</strong> During the lifetime of a pregnancy, the clinician must complete a series of forms. The ZEPRS system
                keeps track of these forms in the Task List. </li>
                    <li><strong>Problems:</strong> If a criteria is met by a form entry, the ZEPRS system stores the result as a Problem.</li>
                    <li><strong>Rules:</strong> Rules are entered in the ZEPRS Administrative interface. The rules set the criteria for triggering Problems.</li>
                </ul>
        </ul>
    <li>October 5, 2004:</li>
        <ul>
            <li>More bugfixes, record changes from Lusaka team</li>
            <li>New design for Home based on Chris' meetings w/ US team.</li>
        </ul>
    <li>September 17, 2004:</li>
        <ul>
            <li>Removed "New Patient" top menu item - force user to perform search first.</li>
            <li>Resolving username into firstname lastname in search results.</li>
            <li>Alot of enum/field/form bug fixes.</li>
            <li>Added item to Tools: Forms by Phase. Once you have a patient, you may jump directly to a form. This is nore of an admin item - a useful way to view the phases and forms.</li>
        </ul>
    <li>September 8, 2004:</li>
        <ul>
            <li>Added Navigation section to Patient Home page. This section suggests links for the user to follow,
evoking a "wizard-style" approach, while still giving the user the flexibility to navigate the forms as needed. All of the encounters are broken into phases, or flows. When a user is in a particular flow – such as “init anc – history” – the system guides the user as follows:
            <ul>
            <li>Links to most recent encounters and next encounter </li>
            <li>Drop-down menu for all of the forms in this phase. </li>
            <li>When the user is at the end of a phase, the system provides a link to the first encounter in the next phase.</li>
            <li>The user also has the option to go directly to a particular encounter by using the top nav strip.</li>
            </ul>
            </li>
            </ul>
    <li>September 3, 2004:</li>
        <ul>
            <li>Changed EGA to EGA(calc) - it is now providing a calculation of the EGA from the most recent LMP value. Next step: perform a similar calculation on the most recent estimated EGA.</li>
            <li>Links to the encounter records for the most recent values in the patient bar.</li>
            </ul>
    <li>September 2, 2004:</li>
        <ul>
            <li>Added new sub-bar to patient bar for most recent values for EGA, weight, Hb, and BP.</li>
            <li>UI changes to the patient home page - displays problems, moved/renamed Chart, new Links section..</li>
            </ul>
    <li>August 29, 2004 (nairobi):</li>
        <ul>
            <li>Major cleanup of forms/fields</li>
            <li>New widgets:
            <ul>
                <li>EDD/EGA calculation - see demo on Init ANC/Antenatal/Initial Visit form. Still need to get most recent value for this data. Also, this new widget is not yet used on all forms.</li>
                <li>Position: demo at Labour/Admission/Vaginal Exam</li>
                <li>Apgar: demo at Labour/Newborn</li>
            </ul>
            <li>Time display in menu</li>
            <li>Different colors for site menu buttons and form buttons</li>
            <li>Data display on left, form entry on right</li>
        </ul>
    <li>July 26, 2004:</li>
        <ul>
            <li>Shared fields demo: <html:link href="patientHome.do?patientId=1" >Jones, Jane</html:link>
            <br/>This demo shows the capability to display aggregate data about a patient. The data is collected from common fields in several forms and
            presented in a historical view to enable to clinician to see trends.</li>

        </ul>
    <li>July 23, 2004:</li>
        <ul>
            <li>Aggregate reports on main patient page. This feature is in development. Currently it is presenting data from Postnatal and followup exams.
                We'll soon be able to view reports based on shared fields, such as BP and temp.</li>
            <li>Forms display data from previous entires of the same form. Click "Previous Pregnancies" for example.</li>
            <li>A little design change to accomodate new data and fixed form radio display bug.</li>
        </ul>
    <li>July 12, 2004:</li>
        <ul>
            <li>Search now searches district patient id in addition to first name, surname, nrc number, and date last modified.
            This district patient id will be in the format used for the ART app and other Zambian medical id's. This will aid integration w/ the ART app.</li>
            <li>When search results are empty, returns a pertinent message.</li>
            <li>Added District Patient ID</li>
            <li>New hierarchical menu system for forms.</li>
            <li>Change “Encounters” heading to “Patient Record”</li>
            <li>Changes to "Problem List" - renamed "Recommended Patient Care" - focuses now strictly on problems.
            Patient context bar made bigger, moved to upper left, below the form menu.</li>
            <li>"Recommended Patient Care" has two main functions:
            <ul>
                <li>If patient has current or previous problems, link titled "Patient Care: Outstanding and Resolved" is displayed.</li>
                <li>If staff encounters problem-triggering conditions during the current session, these are displayed under Outstanding Issues link. </li>
                </ul>
                </li>
            <li>Date/time now in title bar</li>
        </ul>
    <li>June 21, 2004:</li>
        <ul>
            <li>Edit functionality. This is only enabled during your current session - you may not edit previous encounters.</li>
            <li>Changes to patient context bar.</li>
            <li>Re-auth process requires only password.</li>
            <li>'Not Required' no longer displays on dropdown fields - blank instead. </li>
            <li>Current site displayed in browser title bar.</li>
        </ul>
    <li>May 2, 2004:
    <br>The "problem page" function is now available. In order to test it, follow these step: </li>
        <ul>
            <li>Search and select a patient.</li>
            <li>From the encounter menu, select "Antenatal" and then its sub-menu item "Pelvis Floor".</li>
            <li>Enter "7" for the first two input fields and enter your user/pass in the Re-auth field.</li>
            <li>Click "Submit"</li>
            <li>The referral and Encounter info will be displayed in the Quick List box on the right side of the page, under "Diagnosis Links."
            Please note that the recommendations are simple examples writeen by a progrmamer, not a doctor.</li>
            <li>This functionality is still in development - only one outcome is currently being persisted. The goal
            is to display a list of all "problems" encountered throughout the sister's meeting with the patient.</li>
        </ul>
    <li>May 2, 2004:</li>
        <ul>
            <li>New home page. Image is a placeholder.</li>
            <li>"Problem page" works - displayed in right box. Still need to persist to db and make "problems" be additive.</li>
        </ul>
    <li>April 30, 2004:</li>
        <ul>
            <li>Alternating row columns for result display.</li>
            <li>Radio buttons on forms are left-justified.</li>
            <li>Search returns better results - see search page for better desciption of what it returns..</li>
            <li>Re-authorization on form pages works.</li>
        </ul>
    <li>April 29, 2004:</li>
        <ul>
            <li>Any enumeration that has less then three values is rendered as a radio button, rather than a drop-down.
This will improve unability of forms that have many "Yes/No" answers, such as "History:Past Medical History."</li>
            <li>The first enumeration of a drop-down form field is labelled "Not Recorded." If "Not Recorded" is selected, its value is not recorded into the database.
This behaviour is different when the value "Not Done" is selected - such a value is saved to the database. </li>
            <li>Username appears as first item in title bar, which will make it easier to identify sessions when the browser is minimized. </li>
            <li>After a patient is selected, a new block appears in the right-side of the page. This block displays the following items:
                <ul>
                    <li>Current time: Wed Apr 28 16:44:37 CAT 2004</li>
                    <li>User: cekelley01</li>
                    <li>Clinic: Bauleni</li>
                    <li>Patient: Berry, Charlene</li>
                    <li>NRC #: 123456/12/2</li>
                    <li>ZEPRS ID: 2</li>
               </ul>
               This same block will display problem page information, or links to referral application. List of problems that need to be solved for this patient
                (links to necessary forms) appear in this section.
               Any unresolved items that occur during the course of a user session will appear in this section.
               This list is persistent - it will appear the next time this patient record is accessed. <em>Note:</em> This feature is still in development.
            <li>The administration for forms has been improved - one may not add enumerations. Be sure to read the instructions in the admin section.
            </li>
            <li>The encounter menu, which appears after a patient has been selected, is used to locate relevant forms.
It is sorted by the perinatal process: history(collect patient data), Ante, Intra, Obstetrics, Delivery, etc. Utility functions,
such as Lab and Record (patient record listing) follow. although we do not have an Admin interface to change/add tabs,
it would fairly easy to administer from Access via ODBC or mysqlcc.</li>
             <li>Re-authorisation on encounter forms is not implemented yet.</li>
             <li>Auto-calculation of values from database on forms (such as EGA) encounter forms is not implemented yet.</li>
             <li>Support for re-useable enumerations, such as druglist, is not implemented yet.</li>
        </ul>
    <li>April 19, 2004:</li>
        <ul>
            <li>After you search for or create a new patient, a tabbed menu appears on the top nav. You may use this tabbed menu to add encounters to the patient record.</li>
            <li>To view the list of encounters currently in a patient record, click "Record" on the tabbed menu (last item). </li>
            <li>Loaded Medical record into tabbed menus.</li>
            <li>Implemented subtabs, which link to the encounter forms.</li>
            <li>Site UI changes - left nav is narrower, to acocmodate wider tabs. </li>
        </ul>
    <li>March 25, 2004:
        <ul>
            <li>Testing tabbed menus. Make sure you click Shift+reload to view these.</li>
            <li>Testing multiple sessions in same browsers. Passing session in URL. </li>
        </ul>
</ul>
</div>
</template:put>
</template:insert>