bC = null;

startMenu(1, 100,'TOOLS');
menuItem('Forms by Phase', null, null, '#ffffff', '#ffffff', '/zeprs/navigation.do', 'right');
menuItem('Reports', null, null, '#ffffff', '#ffffff', '/zeprs/reportList.do', 'right');
menuItem('Admin Home', null, null, '#ffffff', '#ffffff', '/zeprs/admin/home.do', 'right');
menuItem('System State', null, null, '#ffffff', '#ffffff', '/zeprs/admin/systemState.do', 'right');
menuItem('All Forms', null, null, '#ffffff', '#ffffff', '/zeprs/admin/formList.do', 'right');
menuItem('Report Admin', null, null, '#ffffff', '#ffffff', '/zeprs/admin/reportList.do', 'right');
endMenu();

startMenu(2, 90,'INIT ANC');
menuItem('Antenatal', 9, null, '#ffffff', '#ffffff', '/zeprs/menuList.do', 'right');
menuItem('History', 10, null, '#ffffff', '#ffffff', '/zeprs/menuList.do', 'right');
//menuItem('Problems', null, null, '#ffffff', '#ffffff', '/zeprs/patientRecord.do', 'right');
//menuItem('Lab', null, null, '#ffffff', '#ffffff', '/zeprs/menuList.do', null);
endMenu();

startMenu(3, 170,'FOLLOWUP');
menuItem('Followup Antenatal Exams', null, 147, '#ffffff', '#ffffff', '/zeprs/form10/new.do', null,'FOLLOWUP');
//menuItem('Lab', null, 147, '#ffffff', '#ffffff', '/zeprs/menuList.do', null);
//menuItem('Problems', null, 147, '#ffffff', '#ffffff', '/zeprs/patientRecord.do', null);
endMenu();

startMenu(4, 160,'LABOUR');
menuItem('Admission and initial tests', 11, 180, '#ffffff', '#ffffff', '/zeprs/form10/new.do', "right");
menuItem('Labour First Phase', null, 150, '#ffffff', '#ffffff', '/zeprs/form17/new.do', null);
menuItem('Partograph', null, 150, '#ffffff', '#ffffff', '/zeprs/form18/new.do', "right");
menuItem('Description of Labour', 13, 150, '#ffffff', '#ffffff', '/zeprs/form10/new.do', "right");
menuItem('Newborn', null, 150, '#ffffff', '#ffffff', '/zeprs/form23/new.do', "right");
menuItem('Discharge', 14, 150, '#ffffff', '#ffffff', '/zeprs/form25/new.do', "right");
//menuItem('Still Birth', null, 150, '#ffffff', '#ffffff', '/zeprs/form10/new.do', "right");
//menuItem('LAB', null, 150, '#ffffff', '#ffffff', '/zeprs/form10/new.do', null);
//menuItem('Problems', null, 150, '#ffffff', '#ffffff', '/zeprs/patientRecord.do', null);
endMenu();

startMenu(5, 180,'POSTNATAL');
menuItem('Postnatal Visits', null, 120, '#ffffff', '#ffffff', '/zeprs/form28/new.do', null);
//menuItem('Post Natal pelvic floor', null, 120, '#ffffff', '#ffffff', '/zeprs/form29/new.do', null);
//menuItem('Postnatal Vaginal Exam', null, 120, '#ffffff', '#ffffff', '/zeprs/form30/new.do', null);
menuItem('Postnatal Health Education', null, 180, '#ffffff', '#ffffff', '/zeprs/form31/new.do', null);
menuItem('Postnatal Infant', null, 120, '#ffffff', '#ffffff', '/zeprs/form32/new.do', null);
//menuItem('Lab', 15, 120, '#ffffff', '#ffffff', '/zeprs/menuList.do', "right");
//menuItem('Lab', null, 120, '#ffffff', '#ffffff', '/zeprs/form13/new.do', null);
//menuItem('Problems', null, 150, '#ffffff', '#ffffff', '/zeprs/patientRecord.do', null);
endMenu();

startMenu(6, 150,'POSTNATAL 6 WEEK');
menuItem('Post Natal 6 week', null, 120, '#ffffff', '#ffffff', '/zeprs/form28/new.do', null);
//menuItem('Post Natal pelvic floor', null, 120, '#ffffff', '#ffffff', '/zeprs/form29/new.do', null);
menuItem('Post Natal Vaginal exam', null, 120, '#ffffff', '#ffffff', '/zeprs/form30/new.do', null);
menuItem('Post Natal Health education', null, 120, '#ffffff', '#ffffff', '/zeprs/form31/new.do', null);
menuItem('Post Natal Infant', null, 120, '#ffffff', '#ffffff', '/zeprs/form32/new.do', null);
//menuItem('Lab', 16, 120, '#ffffff', '#ffffff', '/zeprs/menuList.do', "right");
menuItem('Lab', null, 120, '#ffffff', '#ffffff', '/zeprs/form13/new.do', null);
//menuItem('Problems', null, 150, '#ffffff', '#ffffff', '/zeprs/patientRecord.do', null);
endMenu();

startMenu(7, 180, 'NICU');
menuItem('UTH Neonatal Case Record', null, 120, '#ffffff', '#ffffff', '/zeprs/form33/new.do', null);
menuItem('Infant Physical Examination', null, 120, '#ffffff', '#ffffff', '/zeprs/form36/new.do', null);
//menuItem('Infant Physical exam', null, 120, '#ffffff', '#ffffff', '/zeprs/form35/new.do', null);
//menuItem('Infant Spontaneous Activity', null, 120, '#ffffff', '#ffffff', '/zeprs/form36/new.do', null);
menuItem('Summary: Infant Admission', null, 120, '#ffffff', '#ffffff', '/zeprs/form37/new.do', null);
menuItem('Outcome', null, 120, '#ffffff', '#ffffff', '/zeprs/form38/new.do', null);
menuItem('Still Birth Record, Infant', null, 120, '#ffffff', '#ffffff', '/zeprs/form40/new.do', null);
//menuItem('Lab',17, 120, '#ffffff', '#ffffff', '/zeprs/menuList.do', "right");
//menuItem('Lab', null, 120, '#ffffff', '#ffffff', '/zeprs/form13/new.do', null);
//menuItem('Problems', null, 150, '#ffffff', '#ffffff', '/zeprs/patientRecord.do', null);
endMenu();

startMenu(8, 180,'GYN');
menuItem('Gyn case record initial', null, 120, '#ffffff', '#ffffff', '/zeprs/form41/new.do', null);
menuItem('Antenatal Complication', null, 120, '#ffffff', '#ffffff', '/zeprs/form42/new.do', null);
menuItem('Puerperal Complication', null, 120, '#ffffff', '#ffffff', '/zeprs/form43/new.do', null);
menuItem('Ultrasound Result', null, 120, '#ffffff', '#ffffff', '/zeprs/form44/new.do', null);
menuItem('Discharge', null, 120, '#ffffff', '#ffffff', '/zeprs/form54/new.do', null);
menuItem('Summary: Admission to UTH', null, 120, '#ffffff', '#ffffff', '/zeprs/form45/new.do', null);
//menuItem('Lab',18, 120, '#ffffff', '#ffffff', '/zeprs/menuList.do', "right");
//menuItem('Lab', null, 120, '#ffffff', '#ffffff', '/zeprs/form13/new.do', null);
//menuItem('Problems', null, 150, '#ffffff', '#ffffff', '/zeprs/patientRecord.do', null);
endMenu();

startMenu(9, 150,'INIT ANC');
menuItem('Initial Visit', null, null, '#ffffff', '#ffffff', '/zeprs/form6/new.do', null, 'INIT ANC');
//menuItem('Pelvic Floor', null, null, '#ffffff', '#ffffff', '/zeprs/form7/new.do', null);
menuItem('Physical Exam', null, null, '#ffffff', '#ffffff', '/zeprs/form8/new.do', null);
menuItem('Laboratory Test Results', null, null, '#ffffff', '#ffffff', '/zeprs/form9/new.do', null);
// menuItem('Antenatal Examinations', null, null, '#ffffff', '#ffffff', '/zeprs/form10/new.do', null);
endMenu();

startMenu(10, null,'INIT ANC');
menuItem('Previous Pregnancies', null, null, '#ffffff', '#ffffff', '/zeprs/form2/new.do', null);
menuItem('Past Medical History', null, null, '#ffffff', '#ffffff', '/zeprs/form3/new.do', null);
menuItem('Previous Medicine', null, null, '#ffffff', '#ffffff', '/zeprs/form56/new.do', null);
menuItem('Current Medicine', null, null, '#ffffff', '#ffffff', '/zeprs/form55/new.do', null);
menuItem('TT Immunisation', null, null, '#ffffff', '#ffffff', '/zeprs/form4/new.do', null);
menuItem('Past Surgical History', null, null, '#ffffff', '#ffffff', '/zeprs/form5/new.do', null);
endMenu();

startMenu(11, 200,'LABOUR');
menuItem('Admission at Labour', null, 120, '#ffffff', '#ffffff', '/zeprs/form11/new.do', null);
//menuItem('Pelvic Floor and Lab', null, 120, '#ffffff', '#ffffff', '/zeprs/form7/new.do', null);
menuItem('Abdominal Palpation', null, 120, '#ffffff', '#ffffff', '/zeprs/form14/new.do', null);
menuItem('Vaginal Examination at admission', null, 120, '#ffffff', '#ffffff', '/zeprs/form15/new.do', null);
menuItem('Clinical Pelvimentary', null, 120, '#ffffff', '#ffffff', '/zeprs/form16/new.do', null);
endMenu();

startMenu(12, 240,'LABOUR');
//menuItem('Partograph/Active 1st phase Labour', null, 120, '#ffffff', '#ffffff', '/zeprs/form18/new.do', null);
//menuItem('Partograph (L6-2-x)', null, 120, '#ffffff', '#ffffff', '/zeprs/form18/new.do', "right");
//menuItem('Partograph (L6-3-x)', null, 120, '#ffffff', '#ffffff', '/zeprs/form18/new.do', "right");
endMenu();

startMenu(13, 200,'LABOUR');
menuItem('Description of Labour', null, 120, '#ffffff', '#ffffff', '/zeprs/form19/new.do', null);
menuItem('Duration of Labour', null, 120, '#ffffff', '#ffffff', '/zeprs/form20/new.do', null);
menuItem('Mode of Delivery', null, 120, '#ffffff', '#ffffff', '/zeprs/form21/new.do', null);
menuItem('Examination of Placenta', null, 120, '#ffffff', '#ffffff', '/zeprs/form22/new.do', null);
endMenu();

startMenu(14, 220,'LABOUR');
menuItem('Infant at Discharge', null, 120, '#ffffff', '#ffffff', '/zeprs/form24/new.do', null);
menuItem('Postpartum Mother discharge', null, 120, '#ffffff', '#ffffff', '/zeprs/form25/new.do', null);
menuItem('Outcome', null, 120, '#ffffff', '#ffffff', '/zeprs/form26/new.do', null);
menuItem('Pregnancy and Discharge Summary', null, 120, '#ffffff', '#ffffff', '/zeprs/form27/new.do', null);
endMenu();

startMenu(15, 60,'POSTNATAL 1 WEEK');
menuItem('Lab', null, 120, '#ffffff', '#ffffff', '/zeprs/form13/new.do', null);
menuItem('Lab', null, 120, '#ffffff', '#ffffff', '/zeprs/form13/new.do', null);
menuItem('Lab', null, 120, '#ffffff', '#ffffff', '/zeprs/form13/new.do', null);
endMenu();

startMenu(16, 60,'POSTNATAL 6 WEEK');
menuItem('Lab', null, 120, '#ffffff', '#ffffff', '/zeprs/form13/new.do', null);
menuItem('Lab', null, 120, '#ffffff', '#ffffff', '/zeprs/form13/new.do', null);
menuItem('Lab', null, 120, '#ffffff', '#ffffff', '/zeprs/form13/new.do', null);
endMenu();

startMenu(17, 60, 'NICU');
menuItem('Lab', null, 120, '#ffffff', '#ffffff', '/zeprs/form13/new.do', null);
menuItem('Lab', null, 120, '#ffffff', '#ffffff', '/zeprs/form13/new.do', null);
menuItem('Lab', null, 120, '#ffffff', '#ffffff', '/zeprs/form13/new.do', null);
endMenu();

startMenu(18, 60,'GYN');
menuItem('Lab', null, 120, '#ffffff', '#ffffff', '/zeprs/form13/new.do', null);
menuItem('Lab', null, 120, '#ffffff', '#ffffff', '/zeprs/form13/new.do', null);
menuItem('Lab', null, 120, '#ffffff', '#ffffff', '/zeprs/form13/new.do', null);
endMenu();


















