-- MySQL dump 10.10
--
-- Host: localhost    Database: zeprs
-- ------------------------------------------------------
-- Server version	5.0.24a-community-nt-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `abdominalpalp`
--

DROP TABLE IF EXISTS `abdominalpalp`;
CREATE TABLE `abdominalpalp` (
  `id` bigint(20) NOT NULL default '0',
  `time_of_exam_1175` time default NULL,
  `fundal_height_232` int(11) default NULL,
  `lie_313` int(11) default NULL,
  `presentation_314` int(11) default NULL,
  `presentation_other` varchar(255) default NULL,
  `descent_315` int(11) default NULL,
  `contraction_strength_316` int(11) default NULL,
  `contraction_freq_10_abd_palp_317` int(11) default NULL,
  `foetal_heart_rate_abd_palp_318` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK89ED27C6D1B` (`id`),
  CONSTRAINT `FK89ED27C6D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `abdominalpalp`
--


/*!40000 ALTER TABLE `abdominalpalp` DISABLE KEYS */;
LOCK TABLES `abdominalpalp` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `abdominalpalp` ENABLE KEYS */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` bigint(20) NOT NULL auto_increment,
  `line1` varchar(255) default NULL,
  `line2` varchar(255) default NULL,
  `last_modified` datetime default NULL,
  `created` datetime default NULL,
  `last_modified_by` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `site_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKBB979BF47D2B7913` (`site_id`),
  KEY `FKBB979BF451A3A90E` (`created_by`),
  KEY `FKBB979BF43E8F4D64` (`last_modified_by`),
  CONSTRAINT `FKBB979BF43E8F4D64` FOREIGN KEY (`last_modified_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FKBB979BF451A3A90E` FOREIGN KEY (`created_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FKBB979BF47D2B7913` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `address`
--


/*!40000 ALTER TABLE `address` DISABLE KEYS */;
LOCK TABLES `address` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;

--
-- Table structure for table `admissionlabour`
--

DROP TABLE IF EXISTS `admissionlabour`;
CREATE TABLE `admissionlabour` (
  `id` bigint(20) NOT NULL default '0',
  `general_condition_260` int(11) default NULL,
  `presenting_complaints_1218` varchar(255) default NULL,
  `mother_arv_taken_261` tinyint(1) default NULL,
  `date_of_arv_injestion_262` date default NULL,
  `time_of_arv_injestion_263` time default NULL,
  `bp_systolic_224` int(11) default NULL,
  `bp_diastolic_225` int(11) default NULL,
  `temperature_266` float default NULL,
  `heart_169` int(11) default NULL,
  `heart_other_170` varchar(255) default NULL,
  `pulse_171` int(11) default NULL,
  `respiration_rate_269` int(11) default NULL,
  `respiratory_system_167` int(11) default NULL,
  `respiratory_system_other` varchar(255) default NULL,
  `oedema_231` int(11) default NULL,
  `pallor_273` int(11) default NULL,
  `mental_status_274` int(11) default NULL,
  `reflexes_275` int(11) default NULL,
  `breasts_166` int(11) default NULL,
  `varicosities_191` int(11) default NULL,
  `foul_smell_278` tinyint(1) default NULL,
  `vaginal_bleeding_279` int(11) default NULL,
  `vaginal_discharge_1219` tinyint(1) default NULL,
  `vaginal_discharge_appearance_280` int(11) default NULL,
  `vaginal_discharge_smell_1203` int(11) default NULL,
  `other_abnormalities_281` varchar(255) default NULL,
  `onset_of_con_282` time default NULL,
  `date_of_onset_contractions_1220` date default NULL,
  `contraction_freq_10_mins_283` int(11) default NULL,
  `onset_of_labour_284` int(11) default NULL,
  `foetal_movem_285` int(11) default NULL,
  `referral_to_uth_286` tinyint(1) default NULL,
  `priority_of_referral` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKEF98B3E8D1B` (`id`),
  CONSTRAINT `FKEF98B3E8D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admissionlabour`
--


/*!40000 ALTER TABLE `admissionlabour` DISABLE KEYS */;
LOCK TABLES `admissionlabour` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `admissionlabour` ENABLE KEYS */;

--
-- Table structure for table `anteexamvisits`
--

DROP TABLE IF EXISTS `anteexamvisits`;
CREATE TABLE `anteexamvisits` (
  `id` bigint(20) NOT NULL default '0',
  `bp_systolic_224` int(11) default NULL,
  `bp_diastolic_225` int(11) default NULL,
  `temperature_266` float default NULL,
  `weight_228` float default NULL,
  `foetal_heart_229` int(11) default NULL,
  `foetal_heart_rate_230` int(11) default NULL,
  `foetal_movem_285` int(11) default NULL,
  `oedema_231` int(11) default NULL,
  `fundal_height_232` int(11) default NULL,
  `presentation_314` int(11) default NULL,
  `presentation_other` varchar(255) default NULL,
  `engaged_234` int(11) default NULL,
  `cervix_dilatation325` int(11) default NULL,
  `cervix_effacement_326` int(11) default NULL,
  `cervix_consistency_327` int(11) default NULL,
  `urinalysis_240` int(11) default NULL,
  `ms_urine_sg_241` int(11) default NULL,
  `urinalysis_alb_242` int(11) default NULL,
  `urinalysis_glu_243` int(11) default NULL,
  `urinalysis_ace_244` int(11) default NULL,
  `pallor_193` int(11) default NULL,
  `special_investigations_245` varchar(255) default NULL,
  `comments_ante_246` varchar(255) default NULL,
  `next_appoint_247` date default NULL,
  `is_problem_visit` tinyint(1) default NULL,
  `lower_abdominal_pains` tinyint(1) default NULL,
  `lower_abdominal_pains_date_onset` date default NULL,
  `vag_bleeding_1254` tinyint(1) default NULL,
  `bleeding_date_of_onset_1255` date default NULL,
  `vag_discharge_1252` tinyint(1) default NULL,
  `vag_discharge_date_of_onset_1253` date default NULL,
  `fever` tinyint(1) default NULL,
  `fever_date_onset` date default NULL,
  `swelling_edema` tinyint(1) default NULL,
  `backache` tinyint(1) default NULL,
  `headache` tinyint(1) default NULL,
  `fatigue_dizziness` tinyint(1) default NULL,
  `lack_of_foetal_movement` tinyint(1) default NULL,
  `cough` tinyint(1) default NULL,
  `other_reasons` tinyint(1) default NULL,
  `reasons_other_describe` varchar(255) default NULL,
  `preeclamp_hypert_1265` tinyint(1) default NULL,
  `malaria_diag` tinyint(1) default NULL,
  `anaemia` tinyint(1) default NULL,
  `high_bp_diag` tinyint(1) default NULL,
  `vaginal_bleeding_diag` tinyint(1) default NULL,
  `intrauterine_death` tinyint(1) default NULL,
  `uti_diag` tinyint(1) default NULL,
  `pneumonia_diag` tinyint(1) default NULL,
  `tb_diag` tinyint(1) default NULL,
  `vaginal_thrush_diag` tinyint(1) default NULL,
  `oral_thrush_diag` tinyint(1) default NULL,
  `diag_other` varchar(255) default NULL,
  `disp_ante` int(11) default NULL,
  `treatment_1463` varchar(255) default NULL,
  `comments_ante_prob_1464` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKDECFE745D1B` (`id`),
  CONSTRAINT `FKDECFE745D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `anteexamvisits`
--


/*!40000 ALTER TABLE `anteexamvisits` DISABLE KEYS */;
LOCK TABLES `anteexamvisits` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `anteexamvisits` ENABLE KEYS */;

--
-- Table structure for table `antesum`
--

DROP TABLE IF EXISTS `antesum`;
CREATE TABLE `antesum` (
  `id` bigint(20) NOT NULL default '0',
  `date_of_discharge_1268` date default NULL,
  `hospital_ward_1269` int(11) default NULL,
  `anaemia` tinyint(1) default NULL,
  `anaemia_measurement` int(11) default NULL,
  `cardiac_disease` tinyint(1) default NULL,
  `diabetes_gestational` tinyint(1) default NULL,
  `diabetes_pregestational` tinyint(1) default NULL,
  `dysentary` tinyint(1) default NULL,
  `epilepsy` tinyint(1) default NULL,
  `gastroenteritis` tinyint(1) default NULL,
  `hemoglobinopathy_sickle` tinyint(1) default NULL,
  `hemoglobinopathy_thallasemia` tinyint(1) default NULL,
  `hepatitis` tinyint(1) default NULL,
  `hypertensive_disorder` tinyint(1) default NULL,
  `malaria_suspected` tinyint(1) default NULL,
  `polyarthritis` tinyint(1) default NULL,
  `psychosis` tinyint(1) default NULL,
  `tuberculosis` tinyint(1) default NULL,
  `diagnosis_other` tinyint(1) default NULL,
  `diag_other` text,
  `antibiotics` tinyint(1) default NULL,
  `analgesics` tinyint(1) default NULL,
  `iron_supplimentation` tinyint(1) default NULL,
  `misoprostal` tinyint(1) default NULL,
  `med_treatments_other` tinyint(1) default NULL,
  `med_treatments_other_desc` text,
  `mva` tinyint(1) default NULL,
  `dilatation_and_curettage` tinyint(1) default NULL,
  `laparotomy` tinyint(1) default NULL,
  `hysterectomy` tinyint(1) default NULL,
  `salpingostomy` tinyint(1) default NULL,
  `surg_treat_other` tinyint(1) default NULL,
  `surg_treat_other_desc` text,
  `medications_discharge` text,
  `temperature_266` float default NULL,
  `pulse_171` int(11) default NULL,
  `respiratory_system_167` int(11) default NULL,
  `respiratory_system_other` text,
  `respiration_rate_269` int(11) default NULL,
  `bp_systolic_224` int(11) default NULL,
  `bp_diastolic_225` int(11) default NULL,
  `scheduled_followup_1293` date default NULL,
  `place_of_next_visit_1213` varchar(255) default NULL,
  `antepartum_hemhorrhage` tinyint(1) default NULL,
  `draining` tinyint(1) default NULL,
  `malpresentations` tinyint(1) default NULL,
  `previous_c_s` tinyint(1) default NULL,
  `wrong_dates` tinyint(1) default NULL,
  `miscarriage` int(11) default NULL,
  `preterm_labour` tinyint(1) default NULL,
  `prom` tinyint(1) default NULL,
  `breech_presentation` tinyint(1) default NULL,
  `aph` tinyint(1) default NULL,
  `uti` tinyint(1) default NULL,
  `Hypertensive_disorders` int(11) default NULL,
  `drug_1_1136` int(11) default NULL,
  `drug_2_1137` int(11) default NULL,
  `drug_3_1138` int(11) default NULL,
  `drug_4_1139` int(11) default NULL,
  `drug_5_1140` int(11) default NULL,
  `drug_6_1141` int(11) default NULL,
  `drug_7_1142` int(11) default NULL,
  `drug_8_1143` int(11) default NULL,
  `drug_9_1144` int(11) default NULL,
  `drug_10_1145` int(11) default NULL,
  `cervical_suture` tinyint(1) default NULL,
  `missed_abortion` tinyint(1) default NULL,
  `referring_encounter_id` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKCD83D62DD1B` (`id`),
  CONSTRAINT `FKCD83D62DD1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `antesum`
--


/*!40000 ALTER TABLE `antesum` DISABLE KEYS */;
LOCK TABLES `antesum` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `antesum` ENABLE KEYS */;

--
-- Table structure for table `antesun`
--

DROP TABLE IF EXISTS `antesun`;
CREATE TABLE `antesun` (
  `id` bigint(20) NOT NULL default '0',
  `field1268` date default NULL,
  `field1269` varchar(255) default NULL,
  `field1270` varchar(255) default NULL,
  `anaemia_measurement` int(11) default NULL,
  `cardiac_disease` varchar(255) default NULL,
  `diabetes_gestational` varchar(255) default NULL,
  `diabetes_pregestational` varchar(255) default NULL,
  `dysentary` varchar(255) default NULL,
  `epilepsy` varchar(255) default NULL,
  `gastroenteritis` varchar(255) default NULL,
  `hemoglobinopathy_sickle` varchar(255) default NULL,
  `hemoglobinopathy_thallasemia` varchar(255) default NULL,
  `hepatitis` varchar(255) default NULL,
  `hypertensive_disorder` varchar(255) default NULL,
  `malaria_suspected` varchar(255) default NULL,
  `polyarthritis` varchar(255) default NULL,
  `psychosis` varchar(255) default NULL,
  `tuberculosis` varchar(255) default NULL,
  `field1277` varchar(255) default NULL,
  `field1278` varchar(255) default NULL,
  `field1279` varchar(255) default NULL,
  `field1280` varchar(255) default NULL,
  `field1281` varchar(255) default NULL,
  `field1282` varchar(255) default NULL,
  `field1283` varchar(255) default NULL,
  `field1284` varchar(255) default NULL,
  `field1285` varchar(255) default NULL,
  `field1286` varchar(255) default NULL,
  `field1287` varchar(255) default NULL,
  `field1288` varchar(255) default NULL,
  `field1289` varchar(255) default NULL,
  `field1290` varchar(255) default NULL,
  `field1291` varchar(255) default NULL,
  `field1292` varchar(255) default NULL,
  `temperature_266` int(11) default NULL,
  `pulse_171` varchar(255) default NULL,
  `respiratory__167` varchar(255) default NULL,
  `field1449` varchar(255) default NULL,
  `respiration__269` varchar(255) default NULL,
  `bp_systolic_224` varchar(255) default NULL,
  `bp_diastoli_225` varchar(255) default NULL,
  `field1293` date default NULL,
  `place_of_nex_1213` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKCD83D62ED1B` (`id`),
  CONSTRAINT `0_1041` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `antesun`
--


/*!40000 ALTER TABLE `antesun` DISABLE KEYS */;
LOCK TABLES `antesun` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `antesun` ENABLE KEYS */;

--
-- Table structure for table `anteultrasoundeval`
--

DROP TABLE IF EXISTS `anteultrasoundeval`;
CREATE TABLE `anteultrasoundeval` (
  `id` bigint(20) NOT NULL default '0',
  `date_of_ultrasound_1212` date default NULL,
  `number_foetuses_951` int(11) default NULL,
  `lie_313` int(11) default NULL,
  `presentation_314` int(11) default NULL,
  `presentation_other` text,
  `femur_length_956` int(11) default NULL,
  `fetal_abdomi_957` int(11) default NULL,
  `placenta_maturity_958` int(11) default NULL,
  `placenta_loc_upper_959` int(11) default NULL,
  `placenta_loc_lower_960` int(11) default NULL,
  `liquor_volume_961` int(11) default NULL,
  `amniotic_fluid_oli_962` tinyint(1) default NULL,
  `amniotic_ful_poly963` tinyint(1) default NULL,
  `condition_of_foetus_964` int(11) default NULL,
  `ega_129` int(11) default NULL,
  `general_impression_966` int(11) default NULL,
  `general_impression_abnormal_967` text,
  `other_findings_ultrasound_1197` text,
  `biparietal_diameter_955` float default NULL,
  `ega_ultrasound` int(11) default NULL,
  `exam_sequence_number` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK95BB2ADDD1B` (`id`),
  CONSTRAINT `FK95BB2ADDD1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `anteultrasoundeval`
--


/*!40000 ALTER TABLE `anteultrasoundeval` DISABLE KEYS */;
LOCK TABLES `anteultrasoundeval` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `anteultrasoundeval` ENABLE KEYS */;

--
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
CREATE TABLE `appointment` (
  `id` bigint(20) NOT NULL auto_increment,
  `encounter_id` bigint(20) default NULL,
  `patient_id` bigint(20) default NULL,
  `appt_type_id` bigint(20) default NULL,
  `comment` varchar(255) default NULL,
  `visit_date` date default NULL,
  `appointment_date` date default NULL,
  `attendance_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKA8155B9F8523EC95` (`patient_id`),
  KEY `FKA8155B9FC5FB97A7` (`encounter_id`),
  CONSTRAINT `FKA8155B9F8523EC95` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`),
  CONSTRAINT `FKA8155B9FC5FB97A7` FOREIGN KEY (`encounter_id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `appointment`
--


/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
LOCK TABLES `appointment` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;

--
-- Table structure for table `appointment_type`
--

DROP TABLE IF EXISTS `appointment_type`;
CREATE TABLE `appointment_type` (
  `id` bigint(20) NOT NULL auto_increment,
  `appointment_name` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `appointment_type`
--


/*!40000 ALTER TABLE `appointment_type` DISABLE KEYS */;
LOCK TABLES `appointment_type` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `appointment_type` ENABLE KEYS */;

--
-- Table structure for table `appupdate`
--

DROP TABLE IF EXISTS `appupdate`;
CREATE TABLE `appupdate` (
  `id` bigint(20) NOT NULL auto_increment,
  `dateposted` datetime default NULL,
  `dateinstalled` datetime default NULL,
  `type` char(1) default NULL,
  `job` text,
  `message` varchar(255) default NULL,
  `updateid` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `appupdate`
--


/*!40000 ALTER TABLE `appupdate` DISABLE KEYS */;
LOCK TABLES `appupdate` WRITE;
INSERT INTO `appupdate` VALUES (1,'2006-07-28 13:01:01','2006-08-03 07:52:36','S','ALTER TABLE zeprs.routineAnte add column descent_315 int(11);\n',NULL,9),(2,'2006-07-09 22:13:00','2006-08-03 07:52:36','S','ALTER TABLE zeprs.testForm add column abdomen_172 int(11);\n',NULL,2),(3,'2006-06-09 10:55:20','2006-08-03 07:52:36','S','ALTER TABLE zeprs.rpr add column dateRprRequest date;\n',NULL,1),(4,'2006-08-11 13:12:48','2006-08-14 07:29:49','S','ALTER TABLE zeprs.rpr add column partner_treatment int(11);\n',NULL,10),(5,'2006-08-11 13:12:48','2006-08-14 07:29:49','S','ALTER TABLE zeprsdemo.rpr add column partner_treatment int(11);\r\n',NULL,11),(6,'2006-08-14 08:45:09','2006-08-14 23:02:00','S','UPDATE zeprs.arvregimen SET referred_art_clinic = 3070 WHERE referred_art_clinic = 1;',NULL,12),(7,'2006-08-22 15:56:54','2006-08-23 21:42:09','S','UPDATE zeprs.patient p1, zeprs.patient p2\r\nSET p1.site_id = p2.site_id\r\nWHERE p1.parent_id = p2.id\r\nAND p1.site_id IS NULL',NULL,13),(8,'2006-09-08 16:40:12','2006-09-14 07:22:17','S','CREATE TABLE zeprs.hemotology (\n  id bigint(20) NOT NULL default \'0\',\n  PRIMARY KEY  (`id`),\n  KEY FKhemotology (id),\n  CONSTRAINT FKhemotology FOREIGN KEY (id) REFERENCES zeprs.encounter (id)\n) ENGINE=InnoDB DEFAULT CHARSET=latin1;',NULL,19),(9,'2006-09-08 16:40:12','2006-09-14 07:22:17','S','CREATE TABLE zeprsdemo.hemotology ( id bigint(20) NOT NULL default \'0\', PRIMARY KEY (`id`), KEY FKhemotology (id), CONSTRAINT FKhemotology FOREIGN KEY (id) REFERENCES zeprsdemo.encounter (id) ) ENGINE=InnoDB DEFAULT CHARSET=latin1;',NULL,102),(10,'2006-09-08 18:29:36','2006-09-14 07:22:17','S','ALTER TABLE zeprs.hemotology add column wbc float;\n',NULL,26),(11,'2006-09-08 18:29:36','2006-09-14 07:22:18','S','ALTER TABLE zeprsdemo.hemotology add column wbc float;\n',NULL,27),(12,'2006-09-08 18:31:18','2006-09-14 07:22:18','S','ALTER TABLE zeprs.hemotology add column rbc float;\n',NULL,28),(13,'2006-09-08 18:31:18','2006-09-14 07:22:18','S','ALTER TABLE zeprsdemo.hemotology add column rbc float;\n',NULL,29),(14,'2006-09-08 18:31:53','2006-09-14 07:22:18','S','ALTER TABLE zeprs.hemotology add column hgb float;\n',NULL,30),(15,'2006-09-08 18:31:53','2006-09-14 07:22:18','S','ALTER TABLE zeprsdemo.hemotology add column hgb float;\n',NULL,31),(16,'2006-09-08 18:32:38','2006-09-14 07:22:18','S','ALTER TABLE zeprs.hemotology add column hct float;\n',NULL,32),(17,'2006-09-08 18:32:38','2006-09-14 07:22:18','S','ALTER TABLE zeprsdemo.hemotology add column hct float;\n',NULL,33),(18,'2006-09-08 18:33:15','2006-09-14 07:22:18','S','ALTER TABLE zeprs.hemotology add column mcv float;\n',NULL,34),(19,'2006-09-08 18:33:15','2006-09-14 07:22:18','S','ALTER TABLE zeprsdemo.hemotology add column mcv float;\n',NULL,35),(20,'2006-09-08 18:33:55','2006-09-14 07:22:18','S','ALTER TABLE zeprs.hemotology add column mch float;\n',NULL,36),(21,'2006-09-08 18:33:55','2006-09-14 07:22:18','S','ALTER TABLE zeprsdemo.hemotology add column mch float;\n',NULL,37),(22,'2006-09-08 18:34:39','2006-09-14 07:22:18','S','ALTER TABLE zeprs.hemotology add column mchc float;\n',NULL,38),(23,'2006-09-08 18:34:39','2006-09-14 07:22:18','S','ALTER TABLE zeprsdemo.hemotology add column mchc float;\n',NULL,39),(24,'2006-09-08 18:35:07','2006-09-14 07:22:18','S','ALTER TABLE zeprs.hemotology add column plt float;\n',NULL,40),(25,'2006-09-08 18:35:07','2006-09-14 07:22:18','S','ALTER TABLE zeprsdemo.hemotology add column plt float;\n',NULL,41),(26,'2006-09-11 10:30:13','2006-09-14 07:22:18','S','CREATE TABLE zeprs.chemistry (\n  id bigint(20) NOT NULL default \'0\',\n  PRIMARY KEY  (`id`),\n  KEY FKchemistry (id),\n  CONSTRAINT FKchemistry FOREIGN KEY (id) REFERENCES zeprs.encounter (id)\n) ENGINE=InnoDB DEFAULT CHARSET=latin1;',NULL,42),(27,'2006-09-11 10:30:13','2006-09-14 07:22:18','S','CREATE TABLE zeprsdemo.chemistry (\n  id bigint(20) NOT NULL default \'0\',\n  PRIMARY KEY  (`id`),\n  KEY FKchemistry (id),\n  CONSTRAINT FKchemistry FOREIGN KEY (id) REFERENCES zeprs.encounter (id)\n) ENGINE=InnoDB DEFAULT CHARSET=latin1;',NULL,43),(28,'2006-09-11 10:37:12','2006-09-14 07:22:18','S','ALTER TABLE zeprs.chemistry add column na float;\n',NULL,44),(29,'2006-09-11 10:37:12','2006-09-14 07:22:18','S','ALTER TABLE zeprsdemo.chemistry add column na float;\n',NULL,45),(30,'2006-09-11 10:39:58','2006-09-14 07:22:18','S','ALTER TABLE zeprs.chemistry add column potassium VARCHAR(255);\n',NULL,46),(31,'2006-09-11 10:39:58','2006-09-14 07:22:18','S','ALTER TABLE zeprsdemo.chemistry add column potassium VARCHAR(255);\n',NULL,47),(32,'2006-09-11 10:56:31','2006-09-14 07:22:18','S','ALTER TABLE zeprs.chemistry add column chlorine int(11);\n',NULL,48),(33,'2006-09-11 10:56:31','2006-09-14 07:22:18','S','ALTER TABLE zeprsdemo.chemistry add column chlorine int(11);\n',NULL,49),(34,'2006-09-11 10:57:10','2006-09-14 07:22:18','S','ALTER TABLE zeprs.chemistry add column bicarb float;\n',NULL,50),(35,'2006-09-11 10:57:10','2006-09-14 07:22:18','S','ALTER TABLE zeprsdemo.chemistry add column bicarb float;\n',NULL,51),(36,'2006-09-11 10:58:16','2006-09-14 07:22:18','S','ALTER TABLE zeprs.chemistry add column gluc int(11);\n',NULL,52),(37,'2006-09-11 10:58:16','2006-09-14 07:22:18','S','ALTER TABLE zeprsdemo.chemistry add column gluc int(11);\n',NULL,53),(38,'2006-09-11 10:59:07','2006-09-14 07:22:18','S','ALTER TABLE zeprs.chemistry add column creat int(11);\n',NULL,54),(39,'2006-09-11 10:59:07','2006-09-14 07:22:18','S','ALTER TABLE zeprsdemo.chemistry add column creat int(11);\n',NULL,55),(40,'2006-09-11 10:59:44','2006-09-14 07:22:18','S','ALTER TABLE zeprs.chemistry add column bun float;\n',NULL,56),(41,'2006-09-11 10:59:44','2006-09-14 07:22:19','S','ALTER TABLE zeprsdemo.chemistry add column bun float;\n',NULL,57),(42,'2006-09-11 12:02:42','2006-09-14 07:22:19','S','CREATE TABLE zeprs.liverfunction (\n  id bigint(20) NOT NULL default \'0\',\n  PRIMARY KEY  (`id`),\n  KEY FKliverfunction (id),\n  CONSTRAINT FKliverfunction FOREIGN KEY (id) REFERENCES zeprs.encounter (id)\n) ENGINE=InnoDB DEFAULT CHARSET=latin1;',NULL,58),(43,'2006-09-11 12:02:42','2006-09-14 07:22:19','S','CREATE TABLE zeprsdemo.liverfunction (\n  id bigint(20) NOT NULL default \'0\',\n  PRIMARY KEY  (`id`),\n  KEY FKliverfunction (id),\n  CONSTRAINT FKliverfunction FOREIGN KEY (id) REFERENCES zeprs.encounter (id)\n) ENGINE=InnoDB DEFAULT CHARSET=latin1;',NULL,59),(44,'2006-09-11 12:03:31','2006-09-14 07:22:19','S','ALTER TABLE zeprs.liverfunction add column alt int(11);\n',NULL,60),(45,'2006-09-11 12:03:31','2006-09-14 07:22:19','S','ALTER TABLE zeprsdemo.liverfunction add column alt int(11);\n',NULL,61),(46,'2006-09-11 12:04:10','2006-09-14 07:22:19','S','ALTER TABLE zeprs.liverfunction add column ast int(11);\n',NULL,62),(47,'2006-09-11 12:04:10','2006-09-14 07:22:19','S','ALTER TABLE zeprsdemo.liverfunction add column ast int(11);\n',NULL,63),(48,'2006-09-11 12:04:46','2006-09-14 07:22:19','S','ALTER TABLE zeprs.liverfunction add column alk_phos int(11);\n',NULL,64),(49,'2006-09-11 12:04:46','2006-09-14 07:22:19','S','ALTER TABLE zeprsdemo.liverfunction add column alk_phos int(11);\n',NULL,65),(50,'2006-09-11 12:05:26','2006-09-14 07:22:19','S','ALTER TABLE zeprs.liverfunction add column tbili float;\n',NULL,66),(51,'2006-09-11 12:05:26','2006-09-14 07:22:19','S','ALTER TABLE zeprsdemo.liverfunction add column tbili float;\n',NULL,67),(52,'2006-09-11 12:05:57','2006-09-14 07:22:19','S','ALTER TABLE zeprs.liverfunction add column dbili float;\n',NULL,68),(53,'2006-09-11 12:05:57','2006-09-14 07:22:19','S','ALTER TABLE zeprsdemo.liverfunction add column dbili float;\n',NULL,69),(54,'2006-09-11 12:06:22','2006-09-14 07:22:19','S','ALTER TABLE zeprs.liverfunction add column ggt int(11);\n',NULL,70),(55,'2006-09-11 12:06:22','2006-09-14 07:22:19','S','ALTER TABLE zeprsdemo.liverfunction add column ggt int(11);\n',NULL,71),(56,'2006-09-11 13:07:46','2006-09-14 07:22:19','S','CREATE TABLE zeprs.urinalysis (\n  id bigint(20) NOT NULL default \'0\',\n  PRIMARY KEY  (`id`),\n  KEY FKurinalysis (id),\n  CONSTRAINT FKurinalysis FOREIGN KEY (id) REFERENCES zeprs.encounter (id)\n) ENGINE=InnoDB DEFAULT CHARSET=latin1;',NULL,72),(57,'2006-09-11 13:07:46','2006-09-14 07:22:19','S','CREATE TABLE zeprsdemo.urinalysis (\n  id bigint(20) NOT NULL default \'0\',\n  PRIMARY KEY  (`id`),\n  KEY FKurinalysis (id),\n  CONSTRAINT FKurinalysis FOREIGN KEY (id) REFERENCES zeprs.encounter (id)\n) ENGINE=InnoDB DEFAULT CHARSET=latin1;',NULL,73),(58,'2006-09-11 15:06:18','2006-09-14 07:22:19','S','ALTER TABLE zeprs.hemotology add column labtest_id bigint(20);\n',NULL,74),(59,'2006-09-11 15:06:18','2006-09-14 07:22:19','S','ALTER TABLE zeprsdemo.hemotology add column labtest_id bigint(20);\n',NULL,75),(60,'2006-09-12 07:35:09','2006-09-14 07:22:19','S','ALTER TABLE zeprs.urinalysis add column leuk_est int(11);\n',NULL,76),(61,'2006-09-12 07:35:09','2006-09-14 07:22:19','S','ALTER TABLE zeprsdemo.urinalysis add column leuk_est int(11);\n',NULL,77),(62,'2006-09-12 07:40:19','2006-09-14 07:22:19','S','ALTER TABLE zeprs.urinalysis add column nitrite int(11);\n',NULL,78),(63,'2006-09-12 07:40:19','2006-09-14 07:22:19','S','ALTER TABLE zeprsdemo.urinalysis add column nitrite int(11);\n',NULL,79),(64,'2006-09-12 07:47:25','2006-09-14 07:22:20','S','ALTER TABLE zeprs.urinalysis add column urinalysis_ace_244 int(11);\n',NULL,80),(65,'2006-09-12 07:47:25','2006-09-14 07:22:20','S','ALTER TABLE zeprsdemo.urinalysis add column urinalysis_ace_244 int(11);\n',NULL,81),(66,'2006-09-12 07:48:16','2006-09-14 07:22:20','S','ALTER TABLE zeprs.urinalysis add column urinalysis_alb_242 int(11);\n',NULL,82),(67,'2006-09-12 07:48:16','2006-09-14 07:22:20','S','ALTER TABLE zeprsdemo.urinalysis add column urinalysis_alb_242 int(11);\n',NULL,83),(68,'2006-09-12 07:48:29','2006-09-14 07:22:20','S','ALTER TABLE zeprs.urinalysis add column urinalysis_glu_243 int(11);\n',NULL,84),(69,'2006-09-12 07:48:29','2006-09-14 07:22:20','S','ALTER TABLE zeprsdemo.urinalysis add column urinalysis_glu_243 int(11);\n',NULL,85),(70,'2006-09-12 07:51:54','2006-09-14 07:22:20','S','ALTER TABLE zeprs.urinalysis add column wbc_urinalysis int(11);\n',NULL,86),(71,'2006-09-12 07:51:54','2006-09-14 07:22:20','S','ALTER TABLE zeprsdemo.urinalysis add column wbc_urinalysis int(11);\n',NULL,87),(72,'2006-09-12 07:53:03','2006-09-14 07:22:20','S','ALTER TABLE zeprs.urinalysis add column bacteria int(11);\n',NULL,88),(73,'2006-09-12 07:53:03','2006-09-14 07:22:20','S','ALTER TABLE zeprsdemo.urinalysis add column bacteria int(11);\n',NULL,89),(74,'2006-09-12 09:50:04','2006-09-14 07:22:21','S','ALTER TABLE zeprs.labtest add column extended_test_id bigint(20);\n',NULL,90),(75,'2006-09-12 09:50:04','2006-09-14 07:22:21','S','ALTER TABLE zeprsdemo.labtest add column extended_test_id bigint(20);\n',NULL,91),(76,'2006-09-12 10:52:02','2006-09-14 07:22:21','S','ALTER TABLE zeprs.chemistry add column labtest_id bigint(20);\n',NULL,92),(77,'2006-09-12 10:52:02','2006-09-14 07:22:21','S','ALTER TABLE zeprsdemo.chemistry add column labtest_id bigint(20);\n',NULL,93),(78,'2006-09-12 10:54:10','2006-09-14 07:22:21','S','ALTER TABLE zeprs.liverfunction add column labtest_id bigint(20);\n',NULL,94),(79,'2006-09-12 10:54:10','2006-09-14 07:22:21','S','ALTER TABLE zeprsdemo.liverfunction add column labtest_id bigint(20);\n',NULL,95),(80,'2006-09-12 10:54:35','2006-09-14 07:22:21','S','ALTER TABLE zeprs.urinalysis add column labtest_id bigint(20);\n',NULL,96),(81,'2006-09-12 10:54:35','2006-09-14 07:22:21','S','ALTER TABLE zeprsdemo.urinalysis add column labtest_id bigint(20);\n',NULL,97),(82,'2006-09-13 14:44:33','2006-09-14 07:22:21','S','ALTER TABLE zeprs.deliverysum add column utherine_massage tinyint(4);\n',NULL,98),(83,'2006-09-13 14:44:33','2006-09-14 07:22:22','S','ALTER TABLE zeprsdemo.deliverysum add column utherine_massage tinyint(4);\n',NULL,99),(84,'2006-09-13 14:46:56','2006-09-14 07:22:22','S','ALTER TABLE zeprs.deliverysum add column uterotonic_med_given int(11);\n',NULL,100),(85,'2006-09-13 14:46:56','2006-09-14 07:22:22','S','ALTER TABLE zeprsdemo.deliverysum add column uterotonic_med_given int(11);\n',NULL,101),(86,'2007-05-01 11:19:35','2007-05-04 06:23:14','S','ALTER TABLE zeprs.newborneval add column general_examination int(11);\n',NULL,103),(87,'2007-05-01 11:19:35','2007-05-04 06:23:14','S','ALTER TABLE zeprsdemo.newborneval add column general_examination int(11);\n',NULL,104),(88,'2007-05-01 11:23:42','2007-05-04 06:23:14','S','ALTER TABLE zeprs.postnatalinfant add column infant_hiv_test int(11);\n',NULL,105),(89,'2007-05-01 11:23:42','2007-05-04 06:23:15','S','ALTER TABLE zeprsdemo.postnatalinfant add column infant_hiv_test int(11);\n',NULL,106),(90,'2007-05-01 11:28:16','2007-05-04 06:23:15','S','ALTER TABLE zeprs.postnatalinfant add column hiv_test_result int(11);\n',NULL,107),(91,'2007-05-01 11:28:16','2007-05-04 06:23:15','S','ALTER TABLE zeprsdemo.postnatalinfant add column hiv_test_result int(11);\n',NULL,108),(92,'2007-05-01 11:31:45','2007-05-04 06:23:15','S','ALTER TABLE zeprs.postnatalinfant add column hiv_test_date date;\n',NULL,109),(93,'2007-05-01 11:31:45','2007-05-04 06:23:15','S','ALTER TABLE zeprsdemo.postnatalinfant add column hiv_test_date date;\n',NULL,110),(94,'2007-05-01 11:32:47','2007-05-04 06:23:15','S','ALTER TABLE zeprs.postnatalinfant add column septrin_prescribed_today tinyint(4);\n',NULL,111),(95,'2007-05-01 11:32:47','2007-05-04 06:23:15','S','ALTER TABLE zeprsdemo.postnatalinfant add column septrin_prescribed_today tinyint(4);\n',NULL,112),(96,'2007-05-01 11:36:22','2007-05-04 06:23:18','S','ALTER TABLE zeprs.patientregistration add column uth_referral_type int(11);\n',NULL,113),(97,'2007-05-01 11:36:22','2007-05-04 06:23:18','S','ALTER TABLE zeprsdemo.patientregistration add column uth_referral_type int(11);\n',NULL,114),(98,'2007-05-03 11:36:25','2007-05-04 06:23:18','S','ALTER TABLE zeprs.newborneval add column ega_weeks int(11);\n',NULL,115),(99,'2007-05-03 11:36:25','2007-05-04 06:23:18','S','ALTER TABLE zeprsdemo.newborneval add column ega_weeks int(11);\n',NULL,116),(100,'2007-06-11 14:49:01','2007-06-20 06:57:28','S','CREATE TABLE zeprs.testck (\n  id bigint(20) NOT NULL default \'0\',\n  PRIMARY KEY  (`id`),\n  KEY FKtestck (id),\n  CONSTRAINT FKtestck FOREIGN KEY (id) REFERENCES zeprs.encounter (id)\n) ENGINE=InnoDB DEFAULT CHARSET=latin1;',NULL,117),(101,'2007-06-11 14:49:24','2007-06-20 06:57:28','S','ALTER TABLE zeprs.testck add column title VARCHAR(255);\n',NULL,118),(102,'2007-06-11 14:49:24','2007-06-20 06:57:28','S','ALTER TABLE zeprsdemo.testck add column title VARCHAR(255);\n',NULL,119),(103,'2007-06-11 14:49:46','2007-06-20 06:57:28','S','ALTER TABLE zeprs.testck add column firstname VARCHAR(255);\n',NULL,120),(104,'2007-06-11 14:49:46','2007-06-20 06:57:28','S','ALTER TABLE zeprsdemo.testck add column firstname VARCHAR(255);\n',NULL,121),(105,'2007-06-11 14:50:11','2007-06-20 06:57:28','S','ALTER TABLE zeprs.testck add column states int(11);\n',NULL,122),(106,'2007-06-11 14:50:11','2007-06-20 06:57:28','S','ALTER TABLE zeprsdemo.testck add column states int(11);\n',NULL,123),(107,'2007-06-11 14:51:11','2007-06-20 06:57:28','S','ALTER TABLE zeprs.testck add column dateVisit date;\n',NULL,124),(108,'2007-06-11 14:51:11','2007-06-20 06:57:28','S','ALTER TABLE zeprsdemo.testck add column dateVisit date;\n',NULL,125),(109,'2007-06-11 17:05:14','2007-06-20 06:57:28','S','CREATE TABLE zeprs.testck2 (\n  id bigint(20) NOT NULL default \'0\',\n  PRIMARY KEY  (`id`),\n  KEY FKtestck2 (id),\n  CONSTRAINT FKtestck2 FOREIGN KEY (id) REFERENCES zeprs.encounter (id)\n) ENGINE=InnoDB DEFAULT CHARSET=latin1;',NULL,126),(110,'2007-06-11 20:24:03','2007-06-20 06:57:28','S','CREATE TABLE zeprs.testCK3 (\n  id bigint(20) NOT NULL default \'0\',\n  PRIMARY KEY  (`id`),\n  KEY FKtestCK3 (id),\n  CONSTRAINT FKtestCK3 FOREIGN KEY (id) REFERENCES zeprs.encounter (id)\n) ENGINE=InnoDB DEFAULT CHARSET=latin1;',NULL,127),(111,'2007-06-11 20:28:58','2007-06-20 06:57:28','S','CREATE TABLE zeprs.testCK4 (\n  id bigint(20) NOT NULL default \'0\',\n  PRIMARY KEY  (`id`),\n  KEY FKtestCK4 (id),\n  CONSTRAINT FKtestCK4 FOREIGN KEY (id) REFERENCES zeprs.encounter (id)\n) ENGINE=InnoDB DEFAULT CHARSET=latin1;',NULL,128),(112,'2007-06-11 20:28:58','2007-06-20 06:57:28','S','CREATE TABLE zeprsdemo.testCK4 (\n  id bigint(20) NOT NULL default \'0\',\n  PRIMARY KEY  (`id`),\n  KEY FKtestCK4 (id),\n  CONSTRAINT FKtestCK4 FOREIGN KEY (id) REFERENCES zeprs.encounter (id)\n) ENGINE=InnoDB DEFAULT CHARSET=latin1;',NULL,129),(113,'2007-06-11 20:29:49','2007-06-20 06:57:28','S','ALTER TABLE zeprs.testck4 add column first_feild VARCHAR(255);\n',NULL,130),(114,'2007-06-11 20:29:49','2007-06-20 06:57:28','S','ALTER TABLE zeprsdemo.testck4 add column first_feild VARCHAR(255);\n',NULL,131),(115,'2007-06-19 14:59:25','2007-06-20 06:57:30','S','ALTER TABLE zeprs.medsurghist add column family_history_birth_defects tinyint(4);\n',NULL,132),(116,'2007-06-19 14:59:25','2007-06-20 06:57:30','S','ALTER TABLE zeprsdemo.medsurghist add column family_history_birth_defects tinyint(4);\n',NULL,133),(117,'2007-06-19 15:13:02','2007-06-20 06:57:31','S','ALTER TABLE zeprs.deliverysum add column regimen int(11);\n',NULL,134),(118,'2007-06-19 15:13:02','2007-06-20 06:57:31','S','ALTER TABLE zeprsdemo.deliverysum add column regimen int(11);\n',NULL,135),(119,'2007-06-25 10:00:08','2007-07-04 07:34:54','S','ALTER TABLE zeprs.newborneval add column skin_176 int(11);\n',NULL,136),(120,'2007-06-25 10:00:08','2007-07-04 07:34:54','S','ALTER TABLE zeprsdemo.newborneval add column skin_176 int(11);\n',NULL,137),(121,'2007-06-25 10:34:27','2007-07-04 07:34:55','S','ALTER TABLE zeprs.newborneval add column skin_700 int(11);\n',NULL,138),(122,'2007-06-25 10:34:27','2007-07-04 07:34:55','S','ALTER TABLE zeprsdemo.newborneval add column skin_700 int(11);\n',NULL,139),(123,'2007-06-25 11:45:19','2007-07-04 07:34:56','S','ALTER TABLE zeprs.newborneval add column head_ears_nose_throat int(11);\n',NULL,140),(124,'2007-06-25 11:45:19','2007-07-04 07:34:56','S','ALTER TABLE zeprsdemo.newborneval add column head_ears_nose_throat int(11);\n',NULL,141),(125,'2007-06-25 11:48:15','2007-07-04 07:34:57','S','ALTER TABLE zeprs.newborneval add column hent_abnormality VARCHAR(255);\n',NULL,142),(126,'2007-06-25 11:48:15','2007-07-04 07:34:57','S','ALTER TABLE zeprsdemo.newborneval add column hent_abnormality VARCHAR(255);\n',NULL,143),(127,'2007-06-25 12:23:50','2007-07-04 07:34:58','S','ALTER TABLE zeprs.newborneval add column respiratory_system_167 int(11);\n',NULL,144),(128,'2007-06-25 12:23:50','2007-07-04 07:34:58','S','ALTER TABLE zeprsdemo.newborneval add column respiratory_system_167 int(11);\n',NULL,145),(129,'2007-06-25 12:24:05','2007-07-04 07:34:59','S','ALTER TABLE zeprs.newborneval add column respiratory_system_other VARCHAR(255);\n',NULL,146),(130,'2007-06-25 12:24:05','2007-07-04 07:34:59','S','ALTER TABLE zeprsdemo.newborneval add column respiratory_system_other VARCHAR(255);\n',NULL,147),(131,'2007-06-25 12:26:10','2007-07-04 07:35:00','S','ALTER TABLE zeprs.newborneval add column cardovascular_examination int(11);\n',NULL,148),(132,'2007-06-25 12:26:10','2007-07-04 07:35:01','S','ALTER TABLE zeprsdemo.newborneval add column cardovascular_examination int(11);\n',NULL,149),(133,'2007-06-25 12:27:01','2007-07-04 07:35:01','S','ALTER TABLE zeprs.newborneval add column cardiovascular_abnormality VARCHAR(255);\n',NULL,150),(134,'2007-06-25 12:27:01','2007-07-04 07:35:02','S','ALTER TABLE zeprsdemo.newborneval add column cardiovascular_abnormality VARCHAR(255);\n',NULL,151),(135,'2007-06-25 12:36:46','2007-07-04 07:35:03','S','ALTER TABLE zeprs.newborneval add column abdominal_exam int(11);\n',NULL,152),(136,'2007-06-25 12:36:46','2007-07-04 07:35:03','S','ALTER TABLE zeprsdemo.newborneval add column abdominal_exam int(11);\n',NULL,153),(137,'2007-06-25 12:47:32','2007-07-04 07:35:04','S','ALTER TABLE zeprs.newborneval add column congenital_malformations tinyint(4);\n',NULL,154),(138,'2007-06-25 12:47:32','2007-07-04 07:35:04','S','ALTER TABLE zeprsdemo.newborneval add column congenital_malformations tinyint(4);\n',NULL,155),(139,'2007-06-25 12:49:30','2007-07-04 07:35:05','S','ALTER TABLE zeprs.newborneval add column congenital_malformations_system int(11);\n',NULL,156),(140,'2007-06-25 12:49:30','2007-07-04 07:35:05','S','ALTER TABLE zeprsdemo.newborneval add column congenital_malformations_system int(11);\n',NULL,157),(141,'2007-06-25 12:55:27','2007-07-04 07:35:06','S','ALTER TABLE zeprs.newborneval add column abnormality_crano_facial int(11);\n',NULL,158),(142,'2007-06-25 12:55:27','2007-07-04 07:35:06','S','ALTER TABLE zeprsdemo.newborneval add column abnormality_crano_facial int(11);\n',NULL,159),(143,'2007-06-25 15:24:44','2007-07-04 07:35:07','S','ALTER TABLE zeprs.newborneval add column skin_other_701 VARCHAR(255);\n',NULL,160),(144,'2007-06-25 15:24:44','2007-07-04 07:35:07','S','ALTER TABLE zeprsdemo.newborneval add column skin_other_701 VARCHAR(255);\n',NULL,161),(145,'2007-06-25 17:00:02','2007-07-04 07:35:07','S','update zeprs.newborneval set good_grasp_reflex_521 = 1 where good_grasp_reflex_521 = 295\r',NULL,162),(146,'2007-06-25 17:00:03','2007-07-04 07:35:08','S','update zeprs.newborneval set good_grasp_reflex_521 = 0 where good_grasp_reflex_521 = 827',NULL,163),(147,'2007-06-26 11:06:17','2007-07-04 07:35:09','S','ALTER TABLE zeprs.newborneval add column abdominal_abnormality VARCHAR(255);\n',NULL,164),(148,'2007-06-26 11:06:17','2007-07-04 07:35:09','S','ALTER TABLE zeprsdemo.newborneval add column abdominal_abnormality VARCHAR(255);\n',NULL,165),(149,'2007-06-26 15:12:49','2007-07-04 07:35:10','S','ALTER TABLE zeprs.newborneval add column congen_malform_system1 int(11);\n',NULL,166),(150,'2007-06-26 15:12:49','2007-07-04 07:35:10','S','ALTER TABLE zeprsdemo.newborneval add column congen_malform_system1 int(11);\n',NULL,167),(151,'2007-06-26 15:27:22','2007-07-04 07:35:11','S','ALTER TABLE zeprs.newborneval add column congen_malform_system2 int(11);\n',NULL,168),(152,'2007-06-26 15:27:22','2007-07-04 07:35:11','S','ALTER TABLE zeprsdemo.newborneval add column congen_malform_system2 int(11);\n',NULL,169),(153,'2007-06-26 15:28:17','2007-07-04 07:35:12','S','ALTER TABLE zeprs.newborneval add column congen_malform_system3 int(11);\n',NULL,170),(154,'2007-06-26 15:28:17','2007-07-04 07:35:12','S','ALTER TABLE zeprsdemo.newborneval add column congen_malform_system3 int(11);\n',NULL,171),(155,'2007-06-26 16:33:51','2007-07-04 07:35:13','S','ALTER TABLE zeprs.newborneval add column congen_malform_system4 int(11);\n',NULL,172),(156,'2007-06-26 16:33:51','2007-07-04 07:35:13','S','ALTER TABLE zeprsdemo.newborneval add column congen_malform_system4 int(11);\n',NULL,173),(157,'2007-06-26 16:34:26','2007-07-04 07:35:14','S','ALTER TABLE zeprs.newborneval add column congen_malform_system5 int(11);\n',NULL,174),(158,'2007-06-26 16:34:26','2007-07-04 07:35:14','S','ALTER TABLE zeprsdemo.newborneval add column congen_malform_system5 int(11);\n',NULL,175),(159,'2007-06-26 16:34:56','2007-07-04 07:35:15','S','ALTER TABLE zeprs.newborneval add column congen_malform_system6 int(11);\n',NULL,176),(160,'2007-06-26 16:34:56','2007-07-04 07:35:16','S','ALTER TABLE zeprsdemo.newborneval add column congen_malform_system6 int(11);\n',NULL,177),(161,'2007-06-26 16:35:31','2007-07-04 07:35:17','S','ALTER TABLE zeprs.newborneval add column congen_malform_system7 int(11);\n',NULL,178),(162,'2007-06-26 16:35:31','2007-07-04 07:35:17','S','ALTER TABLE zeprsdemo.newborneval add column congen_malform_system7 int(11);\n',NULL,179),(163,'2007-06-26 16:35:58','2007-07-04 07:35:18','S','ALTER TABLE zeprs.newborneval add column congen_malform_system8 int(11);\n',NULL,180),(164,'2007-06-26 16:35:58','2007-07-04 07:35:18','S','ALTER TABLE zeprsdemo.newborneval add column congen_malform_system8 int(11);\n',NULL,181),(165,'2007-06-27 11:03:31','2007-07-04 07:35:18','S','CREATE TABLE zeprs.newbornrecord (\n  id bigint(20) NOT NULL default \'0\',\n  PRIMARY KEY  (`id`),\n  KEY FKnewbornrecord (id),\n  CONSTRAINT FKnewbornrecord FOREIGN KEY (id) REFERENCES zeprs.encounter (id)\n) ENGINE=InnoDB DEFAULT CHARSET=latin1;',NULL,183),(166,'2007-06-27 11:03:31','2007-07-04 07:35:18','S','CREATE TABLE zeprsdemo.newbornrecord (\n  id bigint(20) NOT NULL default \'0\',\n  PRIMARY KEY  (`id`),\n  KEY FKnewbornrecord (id),\n  CONSTRAINT FKnewbornrecord FOREIGN KEY (id) REFERENCES zeprs.encounter (id)\n) ENGINE=InnoDB DEFAULT CHARSET=latin1;',NULL,184),(167,'2007-06-27 11:04:16','2007-07-04 07:35:18','S','ALTER TABLE zeprs.newbornrecord add column date_of_birth date;\n',NULL,185),(168,'2007-06-27 11:04:16','2007-07-04 07:35:18','S','ALTER TABLE zeprsdemo.newbornrecord add column date_of_birth date;\n',NULL,186),(169,'2007-06-27 11:06:18','2007-07-04 07:35:18','S','ALTER TABLE zeprs.newbornrecord add column time_of_birth_1514 time;\n',NULL,187),(170,'2007-06-27 11:06:18','2007-07-04 07:35:18','S','ALTER TABLE zeprsdemo.newbornrecord add column time_of_birth_1514 time;\n',NULL,188),(171,'2007-06-27 11:52:43','2007-07-04 07:35:18','S','ALTER TABLE zeprs.newbornrecord add column sequence_num_489 int(11);\n',NULL,189),(172,'2007-06-27 11:52:43','2007-07-04 07:35:18','S','ALTER TABLE zeprsdemo.newbornrecord add column sequence_num_489 int(11);\n',NULL,190),(173,'2007-06-27 12:05:36','2007-07-04 07:35:18','S','ALTER TABLE zeprs.newbornrecord add column sex_490 tinyint(4);\n',NULL,191),(174,'2007-06-27 12:05:36','2007-07-04 07:35:18','S','ALTER TABLE zeprsdemo.newbornrecord add column sex_490 tinyint(4);\n',NULL,192),(175,'2007-06-27 12:39:13','2007-07-04 07:35:18','S','ALTER TABLE zeprs.newbornrecord add column weight_at_birth_491 float;\n',NULL,193),(176,'2007-06-27 12:39:13','2007-07-04 07:35:18','S','ALTER TABLE zeprsdemo.newbornrecord add column weight_at_birth_491 float;\n',NULL,194),(177,'2007-06-27 12:45:30','2007-07-04 07:35:18','S','ALTER TABLE zeprs.newbornrecord add column ega_weeks int(11);\n',NULL,195),(178,'2007-06-27 12:45:30','2007-07-04 07:35:18','S','ALTER TABLE zeprsdemo.newbornrecord add column ega_weeks int(11);\n',NULL,196),(179,'2007-06-28 13:17:45','2007-07-04 07:35:19','S','ALTER TABLE zeprs.newborneval add column abnormalities_pulmonary tinyint(4);\n',NULL,197),(180,'2007-06-28 13:17:45','2007-07-04 07:35:20','S','ALTER TABLE zeprsdemo.newborneval add column abnormalities_pulmonary tinyint(4);\n',NULL,198),(181,'2007-06-28 13:19:18','2007-07-04 07:35:21','S','ALTER TABLE zeprs.newborneval add column abnormalities_pulmonary_comment VARCHAR(255);\n',NULL,199),(182,'2007-06-28 13:19:18','2007-07-04 07:35:21','S','ALTER TABLE zeprsdemo.newborneval add column abnormalities_pulmonary_comment VARCHAR(255);\n',NULL,200),(183,'2007-06-28 13:19:46','2007-07-04 07:35:22','S','ALTER TABLE zeprs.newborneval add column abnormalities_musculoskeletal tinyint(4);\n',NULL,201),(184,'2007-06-28 13:19:46','2007-07-04 07:35:22','S','ALTER TABLE zeprsdemo.newborneval add column abnormalities_musculoskeletal tinyint(4);\n',NULL,202),(185,'2007-06-28 13:20:13','2007-07-04 07:35:23','S','ALTER TABLE zeprs.newborneval add column abnormalities_musculoskeletal_comment VARCHAR(255);\n',NULL,203),(186,'2007-06-28 13:20:13','2007-07-04 07:35:23','S','ALTER TABLE zeprsdemo.newborneval add column abnormalities_musculoskeletal_comment VARCHAR(255);\n',NULL,204),(187,'2007-06-28 13:20:36','2007-07-04 07:35:24','S','ALTER TABLE zeprs.newborneval add column abnormalities_gastrointestinal tinyint(4);\n',NULL,205),(188,'2007-06-28 13:20:36','2007-07-04 07:35:24','S','ALTER TABLE zeprsdemo.newborneval add column abnormalities_gastrointestinal tinyint(4);\n',NULL,206),(189,'2007-06-28 13:21:03','2007-07-04 07:35:26','S','ALTER TABLE zeprs.newborneval add column abnormalities_gastrointestinal_comment VARCHAR(255);\n',NULL,207),(190,'2007-06-28 13:21:03','2007-07-04 07:35:26','S','ALTER TABLE zeprsdemo.newborneval add column abnormalities_gastrointestinal_comment VARCHAR(255);\n',NULL,208),(191,'2007-06-28 16:52:20','2007-07-04 07:35:27','S','alter table zeprs.newborneval CHANGE abnormalities_pulmonary anomalies_pulmonary int(11);\r\n',NULL,209),(192,'2007-06-28 16:52:20','2007-07-04 07:35:28','S','alter table zeprs.newborneval CHANGE abnormalities_musculoskeletal anomalies_musculoskeletal int(11);\r\n',NULL,210),(193,'2007-06-28 16:52:20','2007-07-04 07:35:29','S','alter table zeprs.newborneval CHANGE abnormalities_gastrointestinal anomalies_gastrointestinal int(11);\r\n',NULL,211),(194,'2007-06-28 16:52:20','2007-07-04 07:35:30','S','alter table zeprs.newborneval CHANGE abnormalities_pulmonary_comment anomalies_pulmonary_comment int(11);\r\n',NULL,212),(195,'2007-06-28 16:52:20','2007-07-04 07:35:31','S','alter table zeprs.newborneval CHANGE abnormalities_musculoskeletal_comment anomalies_musculoskeletal_comment int(11);\r\n',NULL,213),(196,'2007-06-28 16:52:20','2007-07-04 07:35:32','S','alter table zeprs.newborneval CHANGE abnormalities_gastrointestinal_comment anomalies_gastrointestinal_comment int(11);',NULL,214),(197,'2007-06-29 12:45:38','2007-07-04 07:35:33','S','ALTER TABLE zeprs.newborneval add column anomalies_orofacial int(11);\n',NULL,215),(198,'2007-06-29 12:45:38','2007-07-04 07:35:33','S','ALTER TABLE zeprsdemo.newborneval add column anomalies_orofacial int(11);\n',NULL,216),(199,'2007-06-29 12:49:10','2007-07-04 07:35:34','S','ALTER TABLE zeprs.newborneval add column anomalies_orofacial_comment VARCHAR(255);\n',NULL,217),(200,'2007-06-29 12:49:10','2007-07-04 07:35:34','S','ALTER TABLE zeprsdemo.newborneval add column anomalies_orofacial_comment VARCHAR(255);\n',NULL,218),(201,'2007-06-29 13:58:18','2007-07-04 07:35:34','S','delete from admin.field_enumeration where id = 1902;\r',NULL,219),(202,'2007-06-29 13:58:18','2007-07-04 07:35:34','S','delete from admin.field_enumeration where id = 2028;\r',NULL,220),(203,'2007-06-29 13:58:18','2007-07-04 07:35:34','S','delete from admin.field_enumeration where id = 2132;\r',NULL,221),(204,'2007-06-29 13:58:18','2007-07-04 07:35:34','S','delete from admin.field_enumeration where id = 2679;',NULL,222),(205,'2007-07-02 18:36:43','2007-07-04 07:35:34','S','alter table newborneval CHANGE anomalies_pulmonary_comment anomalies_pulmonary_comment VARCHAR(255);\r',NULL,223),(206,'2007-07-02 18:36:44','2007-07-04 07:35:34','S','alter table newborneval CHANGE anomalies_musculoskeletal_comment anomalies_musculoskeletal_comment VARCHAR(255);\r',NULL,224),(207,'2007-07-02 18:36:44','2007-07-04 07:35:34','S','alter table newborneval CHANGE anomalies_gastrointestinal_comment anomalies_gastrointestinal_comment VARCHAR(255);',NULL,225),(208,'2007-07-03 15:12:18','2007-07-04 07:35:34','S','alter table newborneval change baby_received_arv baby_received_arv int(11);\r',NULL,226),(209,'2007-07-03 15:12:18','2007-07-04 07:35:34','S','alter table infantdischargesummary change baby_received_arv baby_received_arv int(11);\r',NULL,227),(210,'2007-07-03 15:12:18','2007-07-04 07:35:34','S','update newborneval set baby_received_arv = 3151 where baby_received_arv = 1;\r',NULL,228),(211,'2007-07-03 15:12:18','2007-07-04 07:35:34','S','update newborneval set baby_received_arv = 3155 where baby_received_arv = 0;\r',NULL,229),(212,'2007-07-03 15:12:18','2007-07-04 07:35:34','S','update infantdischargesummary set baby_received_arv = 3151 where baby_received_arv = 1;\r',NULL,230),(213,'2007-07-03 15:12:18','2007-07-04 07:35:34','S','update infantdischargesummary set baby_received_arv = 3155 where baby_received_arv = 0;',NULL,231),(214,'2007-07-03 17:49:26','2007-07-04 07:35:35','S','alter table zeprs.newborneval change baby_received_arv baby_received_arv int(11);\r',NULL,232),(215,'2007-07-03 17:49:26','2007-07-04 07:35:36','S','alter table zeprs.infantdischargesummary change baby_received_arv baby_received_arv int(11);\r',NULL,233),(216,'2007-07-03 17:49:26','2007-07-04 07:35:36','S','update zeprs.newborneval set baby_received_arv = 3151 where baby_received_arv = 1;\r',NULL,234),(217,'2007-07-03 17:49:26','2007-07-04 07:35:36','S','update zeprs.newborneval set baby_received_arv = 3155 where baby_received_arv = 0;\r',NULL,235),(218,'2007-07-03 17:49:26','2007-07-04 07:35:36','S','update zeprs.infantdischargesummary set baby_received_arv = 3151 where baby_received_arv = 1;\r',NULL,236),(219,'2007-07-03 17:49:26','2007-07-04 07:35:36','S','update zeprs.infantdischargesummary set baby_received_arv = 3155 where baby_received_arv = 0;',NULL,237),(220,'2007-07-03 17:50:39','2007-07-04 07:35:38','S','alter table zeprs.newborneval CHANGE anomalies_musculoskeletal_comment anomalies_musculoskeletal_comment VARCHAR(255);  	\r',NULL,238),(221,'2007-07-03 17:50:39','2007-07-04 07:35:39','S','alter table zeprs.newborneval CHANGE anomalies_gastrointestinal_comment anomalies_gastrointestinal_comment VARCHAR(255); 	\r',NULL,239),(222,'2007-07-03 17:50:39','2007-07-04 07:35:40','S','alter table zeprs.newborneval CHANGE anomalies_pulmonary_comment anomalies_pulmonary_comment VARCHAR(255);',NULL,240),(223,'2007-07-11 11:47:38','2007-07-16 20:44:19','S','ALTER TABLE zeprs.patient DROP FOREIGN KEY FKD0D3EB057D2B7913; \r',NULL,241),(224,'2007-07-11 11:47:38','2007-07-16 20:44:35','S','ALTER TABLE zeprs.patient ADD CONSTRAINT FKD0D3EB057D2B7913 FOREIGN KEY (`site_id`) REFERENCES `site` (`id`) ON UPDATE CASCADE;',NULL,242),(225,'2007-07-11 11:48:55','2007-07-16 20:44:36','S','ALTER TABLE zeprsdemo.patient DROP FOREIGN KEY FKD0D3EB057D2B7913; \r',NULL,243),(226,'2007-07-11 11:48:55','2007-07-16 20:44:37','S','ALTER TABLE zeprsdemo.patient ADD CONSTRAINT FKD0D3EB057D2B7913 FOREIGN KEY (`site_id`) REFERENCES `site` (`id`) ON UPDATE CASCADE;',NULL,244),(227,'2007-07-15 11:41:57','2007-07-16 20:44:37','S','ALTER TABLE zeprs.testck2 add column fruits int(11);\n',NULL,245),(228,'2007-07-15 11:41:57','2007-07-16 20:44:37','S','ALTER TABLE zeprsdemo.testck2 add column fruits int(11);\n',NULL,246),(229,'2007-07-15 14:28:41','2007-07-16 20:44:37','S','ALTER TABLE zeprs.testck2 add column meats int(11);\n',NULL,247),(230,'2007-07-15 14:28:41','2007-07-16 20:44:37','S','ALTER TABLE zeprsdemo.testck2 add column meats int(11);\n',NULL,248),(231,'2007-07-15 14:34:21','2007-07-16 20:44:37','S','ALTER TABLE zeprs.testck2 add column teeta int(11);\n',NULL,249),(232,'2007-07-15 14:34:21','2007-07-16 20:44:37','S','ALTER TABLE zeprsdemo.testck2 add column teeta int(11);\n',NULL,250),(233,'2007-07-16 07:42:14','2007-07-16 20:44:37','S','ALTER TABLE zeprs.testck2 add column surfaces int(11);\n',NULL,251),(234,'2007-07-16 07:42:14','2007-07-16 20:44:37','S','ALTER TABLE zeprsdemo.testck2 add column surfaces int(11);\n',NULL,252),(235,'2007-07-16 08:07:15','2007-07-16 20:44:37','S','ALTER TABLE zeprs.testck2 add column test1 int(11);\n',NULL,253),(236,'2007-07-16 08:07:15','2007-07-16 20:44:37','S','ALTER TABLE zeprsdemo.testck2 add column test1 int(11);\n',NULL,254),(237,'2007-07-16 13:28:51','2007-07-16 20:44:37','S','ALTER TABLE zeprsdemo.testck2 add column textField VARCHAR(255);\n',NULL,256),(238,'2007-07-16 13:28:51','2007-07-16 20:44:38','S','ALTER TABLE zeprs.testck2 add column textField VARCHAR(255);\n',NULL,255),(239,'2007-07-16 13:40:43','2007-07-16 20:44:38','S','ALTER TABLE zeprs.testck2 add column is_true tinyint(1);\n',NULL,257),(240,'2007-07-16 13:40:43','2007-07-16 20:44:38','S','ALTER TABLE zeprsdemo.testck2 add column is_true tinyint(1);\n',NULL,258),(241,'2007-07-16 13:41:21','2007-07-16 20:44:38','S','ALTER TABLE zeprs.testck2 add column is_really_true tinyint(1);\n',NULL,259),(242,'2007-07-16 13:41:21','2007-07-16 20:44:38','S','ALTER TABLE zeprsdemo.testck2 add column is_really_true tinyint(1);\n',NULL,260),(243,'2007-07-20 10:36:18','2007-07-24 07:00:54','S','CREATE TABLE zeprs.test720a (\n  id bigint(20) NOT NULL default \'0\',\n  PRIMARY KEY  (`id`),\n  KEY FKtest720a (id),\n  CONSTRAINT FKtest720a FOREIGN KEY (id) REFERENCES zeprs.encounter (id)\n) ENGINE=InnoDB DEFAULT CHARSET=latin1;',NULL,261),(244,'2007-07-20 10:36:18','2007-07-24 07:00:54','S','CREATE TABLE zeprsdemo.test720a (\n  id bigint(20) NOT NULL default \'0\',\n  PRIMARY KEY  (`id`),\n  KEY FKtest720a (id),\n  CONSTRAINT FKtest720a FOREIGN KEY (id) REFERENCES zeprs.encounter (id)\n) ENGINE=InnoDB DEFAULT CHARSET=latin1;',NULL,262),(245,'2007-07-20 10:41:32','2007-07-24 07:00:54','S','ALTER TABLE zeprs.test720a add column test VARCHAR(255);\n',NULL,263),(246,'2007-07-20 10:41:32','2007-07-24 07:00:54','S','ALTER TABLE zeprsdemo.test720a add column test VARCHAR(255);\n',NULL,264),(247,'2007-07-20 11:31:07','2007-07-24 07:00:54','S','ALTER TABLE zeprs.test720a add column cabbage int(11);\n',NULL,265),(248,'2007-07-20 11:31:07','2007-07-24 07:00:55','S','ALTER TABLE zeprsdemo.test720a add column cabbage int(11);\n',NULL,266),(249,'2007-07-23 13:41:35','2007-07-24 07:00:55','S','ALTER TABLE zeprs.test720a add column test723a VARCHAR(255);\n',NULL,267),(250,'2007-07-23 13:41:35','2007-07-24 07:00:55','S','ALTER TABLE zeprsdemo.test720a add column test723a VARCHAR(255);\n',NULL,268),(251,'2007-07-23 13:58:36','2007-07-24 07:00:55','S','ALTER TABLE zeprs.test720a add column test423b VARCHAR(255);\n',NULL,269),(252,'2007-07-23 13:58:36','2007-07-24 07:00:55','S','ALTER TABLE zeprsdemo.test720a add column test423b VARCHAR(255);\n',NULL,270),(253,'2007-07-23 14:07:34','2007-07-24 07:00:55','S','ALTER TABLE zeprs.test720a add column test723c VARCHAR(255);\n',NULL,271),(254,'2007-07-23 14:07:34','2007-07-24 07:00:55','S','ALTER TABLE zeprsdemo.test720a add column test723c VARCHAR(255);\n',NULL,272),(255,'2007-07-23 14:10:01','2007-07-24 07:00:55','S','CREATE TABLE zeprs.test723 (\n  id bigint(20) NOT NULL default \'0\',\n  PRIMARY KEY  (`id`),\n  KEY FKtest723 (id),\n  CONSTRAINT FKtest723 FOREIGN KEY (id) REFERENCES zeprs.encounter (id)\n) ENGINE=InnoDB DEFAULT CHARSET=latin1;',NULL,273),(256,'2007-07-23 14:10:01','2007-07-24 07:00:55','S','CREATE TABLE zeprsdemo.test723 (\n  id bigint(20) NOT NULL default \'0\',\n  PRIMARY KEY  (`id`),\n  KEY FKtest723 (id),\n  CONSTRAINT FKtest723 FOREIGN KEY (id) REFERENCES zeprs.encounter (id)\n) ENGINE=InnoDB DEFAULT CHARSET=latin1;',NULL,274),(257,'2007-07-23 14:11:03','2007-07-24 07:00:55','S','ALTER TABLE zeprs.test723 add column test1 VARCHAR(255);\n',NULL,275),(258,'2007-07-23 14:11:03','2007-07-24 07:00:55','S','ALTER TABLE zeprsdemo.test723 add column test1 VARCHAR(255);\n',NULL,276),(259,'2007-07-23 14:11:21','2007-07-24 07:00:55','S','ALTER TABLE zeprs.test723 add column test2 int(11);\n',NULL,277),(260,'2007-07-23 14:11:21','2007-07-24 07:00:55','S','ALTER TABLE zeprsdemo.test723 add column test2 int(11);\n',NULL,278),(261,'2007-07-23 15:07:41','2007-07-24 07:00:55','S','CREATE TABLE zeprs.test7233 (\n  id bigint(20) NOT NULL default \'0\',\n  PRIMARY KEY  (`id`),\n  KEY FKtest7233 (id),\n  CONSTRAINT FKtest7233 FOREIGN KEY (id) REFERENCES zeprs.encounter (id)\n) ENGINE=InnoDB DEFAULT CHARSET=latin1;',NULL,279),(262,'2007-07-23 15:07:41','2007-07-24 07:00:55','S','CREATE TABLE zeprsdemo.test7233 (\n  id bigint(20) NOT NULL default \'0\',\n  PRIMARY KEY  (`id`),\n  KEY FKtest7233 (id),\n  CONSTRAINT FKtest7233 FOREIGN KEY (id) REFERENCES zeprs.encounter (id)\n) ENGINE=InnoDB DEFAULT CHARSET=latin1;',NULL,280),(263,'2007-07-23 15:09:04','2007-07-24 07:00:55','S','ALTER TABLE zeprs.test7233 add column test VARCHAR(255);\n',NULL,281),(264,'2007-07-23 15:09:04','2007-07-24 07:00:55','S','ALTER TABLE zeprsdemo.test7233 add column test VARCHAR(255);\n',NULL,282),(265,'2007-07-23 15:26:46','2007-07-24 07:00:55','S','ALTER TABLE zeprs.test7233 add column test2 VARCHAR(255);\n',NULL,283),(266,'2007-07-23 15:26:46','2007-07-24 07:00:56','S','ALTER TABLE zeprsdemo.test7233 add column test2 VARCHAR(255);\n',NULL,284),(267,'2007-07-23 17:42:57','2007-07-24 07:00:56','S','CREATE TABLE zeprs.test7235 (\n  id bigint(20) NOT NULL default \'0\',\n  PRIMARY KEY  (`id`),\n  KEY FKtest7235 (id),\n  CONSTRAINT FKtest7235 FOREIGN KEY (id) REFERENCES zeprs.encounter (id)\n) ENGINE=InnoDB DEFAULT CHARSET=latin1;',NULL,285),(268,'2007-07-23 17:42:57','2007-07-24 07:00:56','S','CREATE TABLE zeprsdemo.test7235 (\n  id bigint(20) NOT NULL default \'0\',\n  PRIMARY KEY  (`id`),\n  KEY FKtest7235 (id),\n  CONSTRAINT FKtest7235 FOREIGN KEY (id) REFERENCES zeprs.encounter (id)\n) ENGINE=InnoDB DEFAULT CHARSET=latin1;',NULL,286),(269,'2007-07-23 17:43:45','2007-07-24 07:00:56','S','ALTER TABLE zeprs.test7235 add column patient_health VARCHAR(255);\n',NULL,287),(270,'2007-07-23 17:43:45','2007-07-24 07:00:56','S','ALTER TABLE zeprsdemo.test7235 add column patient_health VARCHAR(255);\n',NULL,288),(271,'2007-07-23 18:09:34','2007-07-24 07:00:56','S','ALTER TABLE zeprs.test7235 add column apples VARCHAR(255);\n',NULL,289),(272,'2007-07-23 18:09:34','2007-07-24 07:00:56','S','ALTER TABLE zeprsdemo.test7235 add column apples VARCHAR(255);\n',NULL,290),(273,'2007-07-23 19:02:50','2007-07-24 07:00:56','S','ALTER TABLE zeprs.test7235 add column veggies VARCHAR(255);\n',NULL,291),(274,'2007-07-23 19:02:50','2007-07-24 07:00:56','S','ALTER TABLE zeprsdemo.test7235 add column veggies VARCHAR(255);\n',NULL,292),(275,'2007-08-03 15:20:31','2007-08-06 09:49:29','S','CREATE TABLE zeprs.drug_stocks (\n  id bigint(20) NOT NULL default \'0\',\n  PRIMARY KEY  (`id`),\n  KEY FKdrug_stocks (id),\n  CONSTRAINT FKdrug_stocks FOREIGN KEY (id) REFERENCES zeprs.encounter (id)\n) ENGINE=InnoDB DEFAULT CHARSET=latin1;',NULL,293),(276,'2007-08-03 15:20:31','2007-08-06 09:49:29','S','CREATE TABLE zeprsdemo.drug_stocks (\n  id bigint(20) NOT NULL default \'0\',\n  PRIMARY KEY  (`id`),\n  KEY FKdrug_stocks (id),\n  CONSTRAINT FKdrug_stocks FOREIGN KEY (id) REFERENCES zeprs.encounter (id)\n) ENGINE=InnoDB DEFAULT CHARSET=latin1;',NULL,294),(277,'2007-08-03 15:22:24','2007-08-06 09:49:29','S','ALTER TABLE zeprs.drug_stocks add column drug_name VARCHAR(255);\n',NULL,295),(278,'2007-08-03 15:22:24','2007-08-06 09:49:29','S','ALTER TABLE zeprsdemo.drug_stocks add column drug_name VARCHAR(255);\n',NULL,296),(279,'2007-08-03 15:24:04','2007-08-06 09:49:29','S','ALTER TABLE zeprs.drug_stocks add column quantity int(11);\n',NULL,297),(280,'2007-08-03 15:24:04','2007-08-06 09:49:29','S','ALTER TABLE zeprsdemo.drug_stocks add column quantity int(11);\n',NULL,298),(281,'2007-08-03 15:24:41','2007-08-06 09:49:29','S','ALTER TABLE zeprs.drug_stocks add column form_factor int(11);\n',NULL,299),(282,'2007-08-03 15:24:41','2007-08-06 09:49:29','S','ALTER TABLE zeprsdemo.drug_stocks add column form_factor int(11);\n',NULL,300),(283,'2007-08-03 15:51:01','2007-08-06 09:49:29','S','CREATE TABLE zeprs.current_stocks (\n  id bigint(20) NOT NULL default \'0\',\n  PRIMARY KEY  (`id`),\n  KEY FKcurrent_stocks (id),\n  CONSTRAINT FKcurrent_stocks FOREIGN KEY (id) REFERENCES zeprs.encounter (id)\n) ENGINE=InnoDB DEFAULT CHARSET=latin1;',NULL,301),(284,'2007-08-03 15:51:01','2007-08-06 09:49:29','S','CREATE TABLE zeprsdemo.current_stocks (\n  id bigint(20) NOT NULL default \'0\',\n  PRIMARY KEY  (`id`),\n  KEY FKcurrent_stocks (id),\n  CONSTRAINT FKcurrent_stocks FOREIGN KEY (id) REFERENCES zeprs.encounter (id)\n) ENGINE=InnoDB DEFAULT CHARSET=latin1;',NULL,302),(285,'2007-08-03 15:51:44','2007-08-06 09:49:29','S','ALTER TABLE zeprs.current_stocks add column drug_name int(11);\n',NULL,303),(286,'2007-08-03 15:51:44','2007-08-06 09:49:29','S','ALTER TABLE zeprsdemo.current_stocks add column drug_name int(11);\n',NULL,304),(287,'2007-08-03 15:52:14','2007-08-06 09:49:29','S','ALTER TABLE zeprs.current_stocks add column current_count int(11);\n',NULL,305),(288,'2007-08-03 15:52:14','2007-08-06 09:49:29','S','ALTER TABLE zeprsdemo.current_stocks add column current_count int(11);\n',NULL,306),(289,'2007-08-13 18:42:59','2007-08-13 18:57:10','S','ALTER TABLE zeprs.arvregimen add column enrolled_in_art tinyint(4);\n',NULL,307),(290,'2007-08-13 18:42:59','2007-08-13 18:57:11','S','ALTER TABLE zeprsdemo.arvregimen add column enrolled_in_art tinyint(4);\n',NULL,308),(291,'2007-08-13 18:45:42','2007-08-13 18:57:11','S','ALTER TABLE zeprs.arvregimen add column clinic_enrolled_art int(11);\n',NULL,309),(292,'2007-08-13 18:45:42','2007-08-13 18:57:12','S','ALTER TABLE zeprsdemo.arvregimen add column clinic_enrolled_art int(11);\n',NULL,310);
UNLOCK TABLES;
/*!40000 ALTER TABLE `appupdate` ENABLE KEYS */;

--
-- Table structure for table `archivelog`
--

DROP TABLE IF EXISTS `archivelog`;
CREATE TABLE `archivelog` (
  `id` bigint(20) NOT NULL auto_increment,
  `archived` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `checksum` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `archivelog`
--


/*!40000 ALTER TABLE `archivelog` DISABLE KEYS */;
LOCK TABLES `archivelog` WRITE;
INSERT INTO `archivelog` VALUES (27480,'2007-08-13 19:30:10',1),(27481,'2007-08-13 20:00:09',1),(27482,'2007-08-13 20:30:10',1),(27483,'2007-08-14 07:30:00',1),(27484,'2007-08-14 07:44:18',1),(27485,'2007-08-14 07:44:36',1),(27486,'2007-08-14 08:00:00',1),(27487,'2007-08-14 08:30:00',1),(27488,'2007-08-14 09:00:00',1),(27489,'2007-08-14 09:30:00',1),(27490,'2007-08-14 10:00:00',1),(27491,'2007-08-14 10:30:00',1),(27492,'2007-08-14 11:00:00',1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `archivelog` ENABLE KEYS */;

--
-- Table structure for table `arvregimen`
--

DROP TABLE IF EXISTS `arvregimen`;
CREATE TABLE `arvregimen` (
  `id` bigint(20) NOT NULL default '0',
  `receivedRegimen` tinyint(4) default NULL,
  `regimen` int(11) default NULL,
  `days_of_treatment` int(11) default NULL,
  `phaseOfPregnancy` int(11) default NULL,
  `regimen_visit_date` date default NULL,
  `cd4tested` tinyint(4) default NULL,
  `who_stage` int(11) default NULL,
  `referred_art_clinic` int(11) default NULL,
  `enrolled_in_art` tinyint(4) default NULL,
  `clinic_enrolled_art` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKB8D4A95CD1B` (`id`),
  CONSTRAINT `FKB8D4A95CD1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arvregimen`
--


/*!40000 ALTER TABLE `arvregimen` DISABLE KEYS */;
LOCK TABLES `arvregimen` WRITE;
INSERT INTO `arvregimen` VALUES (11,1,2970,5,2934,'2007-08-14',1,3029,3062,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `arvregimen` ENABLE KEYS */;

--
-- Table structure for table `chart`
--

DROP TABLE IF EXISTS `chart`;
CREATE TABLE `chart` (
  `id` bigint(20) NOT NULL auto_increment,
  `encounter_id` bigint(20) default NULL,
  `patient_id` bigint(20) default NULL,
  `lmp_date` date default NULL,
  `ega_days` int(11) default NULL,
  `fundal_height` int(11) default NULL,
  `ace_enum_id` int(11) default NULL,
  `ace_value` varchar(255) default NULL,
  `ace_order` int(11) default NULL,
  `alb_enunm_id` int(11) default NULL,
  `alb_value` varchar(255) default NULL,
  `alb_order` int(11) default NULL,
  `glu_enum_id` int(11) default NULL,
  `glu_value` varchar(255) default NULL,
  `glu_order` int(11) default NULL,
  `weight` double default NULL,
  `hb` int(11) default NULL,
  `bp_diastolic_enum_id` varchar(255) default NULL,
  `bp_diastolic_value` varchar(255) default NULL,
  `bp_diastolic_order` int(11) default NULL,
  `bp_systolic_enum_id` varchar(255) default NULL,
  `bp_systolic_value` varchar(255) default NULL,
  `bp_systolic_order` int(11) default NULL,
  `oedema_enum_id` int(11) default NULL,
  `oedema_value` varchar(255) default NULL,
  `oedema_order` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK5A3D7DE8523EC95` (`patient_id`),
  KEY `FK5A3D7DEC5FB97A7` (`encounter_id`),
  CONSTRAINT `FK5A3D7DE8523EC95` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`),
  CONSTRAINT `FK5A3D7DEC5FB97A7` FOREIGN KEY (`encounter_id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `chart`
--


/*!40000 ALTER TABLE `chart` DISABLE KEYS */;
LOCK TABLES `chart` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `chart` ENABLE KEYS */;

--
-- Table structure for table `chemistry`
--

DROP TABLE IF EXISTS `chemistry`;
CREATE TABLE `chemistry` (
  `id` bigint(20) NOT NULL default '0',
  `na` float default NULL,
  `potassium` varchar(255) default NULL,
  `chlorine` int(11) default NULL,
  `bicarb` float default NULL,
  `gluc` int(11) default NULL,
  `creat` int(11) default NULL,
  `bun` float default NULL,
  `labtest_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKchemistry` (`id`),
  CONSTRAINT `FKchemistry` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `chemistry`
--


/*!40000 ALTER TABLE `chemistry` DISABLE KEYS */;
LOCK TABLES `chemistry` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `chemistry` ENABLE KEYS */;

--
-- Table structure for table `client_setting`
--

DROP TABLE IF EXISTS `client_setting`;
CREATE TABLE `client_setting` (
  `id` bigint(20) NOT NULL auto_increment,
  `ip_address` varchar(255) NOT NULL default '',
  `site_id` bigint(20) default NULL,
  `district_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `ip_address` (`ip_address`),
  KEY `FK7A2ADDC4F97EB6C` (`district_id`),
  KEY `FK7A2ADDC7D2B7913` (`site_id`),
  CONSTRAINT `FK7A2ADDC7D2B7913` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client_setting`
--


/*!40000 ALTER TABLE `client_setting` DISABLE KEYS */;
LOCK TABLES `client_setting` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `client_setting` ENABLE KEYS */;

--
-- Table structure for table `clinicalpelvimentry`
--

DROP TABLE IF EXISTS `clinicalpelvimentry`;
CREATE TABLE `clinicalpelvimentry` (
  `id` bigint(20) NOT NULL default '0',
  `diagonal_conjugate_342` int(11) default NULL,
  `diagonal_conjugate_length_343` int(11) default NULL,
  `ishcial_spines_344` int(11) default NULL,
  `sub_pubic_arch_345` int(11) default NULL,
  `curvature_of_sacrum_346` int(11) default NULL,
  `intertuberous_diameter_347` int(11) default NULL,
  `adequacy_of_pelvic_348` int(11) default NULL,
  `pubic_arch_angle_349` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKB10649BCD1B` (`id`),
  CONSTRAINT `FKB10649BCD1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `clinicalpelvimentry`
--


/*!40000 ALTER TABLE `clinicalpelvimentry` DISABLE KEYS */;
LOCK TABLES `clinicalpelvimentry` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `clinicalpelvimentry` ENABLE KEYS */;

--
-- Table structure for table `clinicalpelvimitry`
--

DROP TABLE IF EXISTS `clinicalpelvimitry`;
CREATE TABLE `clinicalpelvimitry` (
  `id` bigint(20) NOT NULL default '0',
  `diagonal_conjugate_342` int(11) default NULL,
  `diagonal_conjugate_length_343` int(11) default NULL,
  `ishcial_spines_344` int(11) default NULL,
  `sub_pubic_arch_345` int(11) default NULL,
  `curvature_of_sacrum_346` int(11) default NULL,
  `intertuberous_diameter_347` int(11) default NULL,
  `adeqancy_of_pelvic_348` int(11) default NULL,
  `pubic_arch_angle_349` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK163BEA28D1B` (`id`),
  CONSTRAINT `FK163BEA28D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `clinicalpelvimitry`
--


/*!40000 ALTER TABLE `clinicalpelvimitry` DISABLE KEYS */;
LOCK TABLES `clinicalpelvimitry` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `clinicalpelvimitry` ENABLE KEYS */;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL auto_increment,
  `patient_id` bigint(20) default NULL,
  `problem_id` bigint(20) default NULL,
  `comment_text` text NOT NULL,
  `action_plan` text,
  `last_modified` datetime default NULL,
  `created` datetime default NULL,
  `last_modified_by` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `site_id` bigint(20) default NULL,
  `outcome_id` bigint(20) default NULL,
  `encounter_id` bigint(20) default NULL,
  `pregnancy_id` bigint(20) default NULL,
  `import_comment_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK38A5EE5FC5FB97A7` (`encounter_id`),
  KEY `FK38A5EE5F3E8F4D64` (`last_modified_by`),
  KEY `FK38A5EE5F7D2B7913` (`site_id`),
  KEY `FK38A5EE5FEF6DB6BB` (`problem_id`),
  KEY `FK38A5EE5F2370976D` (`pregnancy_id`),
  KEY `FK38A5EE5F8523EC95` (`patient_id`),
  KEY `FK38A5EE5F51A3A90E` (`created_by`),
  KEY `FK38A5EE5FF9C95128` (`outcome_id`),
  CONSTRAINT `FK38A5EE5F2370976D` FOREIGN KEY (`pregnancy_id`) REFERENCES `pregnancy` (`id`),
  CONSTRAINT `FK38A5EE5F3E8F4D64` FOREIGN KEY (`last_modified_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FK38A5EE5F51A3A90E` FOREIGN KEY (`created_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FK38A5EE5F7D2B7913` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`),
  CONSTRAINT `FK38A5EE5F8523EC95` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`),
  CONSTRAINT `FK38A5EE5FEF6DB6BB` FOREIGN KEY (`problem_id`) REFERENCES `problem` (`id`),
  CONSTRAINT `FK38A5EE5FF9C95128` FOREIGN KEY (`outcome_id`) REFERENCES `outcome` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comment`
--


/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
LOCK TABLES `comment` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;

--
-- Table structure for table `createreferral`
--

DROP TABLE IF EXISTS `createreferral`;
CREATE TABLE `createreferral` (
  `id` bigint(20) NOT NULL default '0',
  `priority_of_referral` int(11) default NULL,
  `comment` text,
  `referring_encounter` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK8116099D1B` (`id`),
  CONSTRAINT `FK8116099D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `createreferral`
--


/*!40000 ALTER TABLE `createreferral` DISABLE KEYS */;
LOCK TABLES `createreferral` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `createreferral` ENABLE KEYS */;

--
-- Table structure for table `current_drugs`
--

DROP TABLE IF EXISTS `current_drugs`;
CREATE TABLE `current_drugs` (
  `id` bigint(20) NOT NULL auto_increment,
  `encounter_id` bigint(20) default NULL,
  `patient_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK994F268D8523EC95` (`patient_id`),
  KEY `FK994F268DC5FB97A7` (`encounter_id`),
  CONSTRAINT `FK994F268D8523EC95` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`),
  CONSTRAINT `FK994F268DC5FB97A7` FOREIGN KEY (`encounter_id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `current_drugs`
--


/*!40000 ALTER TABLE `current_drugs` DISABLE KEYS */;
LOCK TABLES `current_drugs` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `current_drugs` ENABLE KEYS */;

--
-- Table structure for table `current_stocks`
--

DROP TABLE IF EXISTS `current_stocks`;
CREATE TABLE `current_stocks` (
  `id` bigint(20) NOT NULL default '0',
  `drug_name` int(11) default NULL,
  `current_count` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKcurrent_stocks` (`id`),
  CONSTRAINT `FKcurrent_stocks` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `current_stocks`
--


/*!40000 ALTER TABLE `current_stocks` DISABLE KEYS */;
LOCK TABLES `current_stocks` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `current_stocks` ENABLE KEYS */;

--
-- Table structure for table `currentmedicine`
--

DROP TABLE IF EXISTS `currentmedicine`;
CREATE TABLE `currentmedicine` (
  `id` bigint(20) NOT NULL default '0',
  `drug_1_1136` int(11) default NULL,
  `drug_2_1137` int(11) default NULL,
  `drug_3_1138` int(11) default NULL,
  `drug_4_1139` int(11) default NULL,
  `drug_5_1140` int(11) default NULL,
  `drug_6_1141` int(11) default NULL,
  `drug_7_1142` int(11) default NULL,
  `drug_8_1143` int(11) default NULL,
  `drug_9_1144` int(11) default NULL,
  `drug_10_1145` int(11) default NULL,
  `current_medicine_1146` text,
  PRIMARY KEY  (`id`),
  KEY `FK4CC78AB3D1B` (`id`),
  CONSTRAINT `FK4CC78AB3D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `currentmedicine`
--


/*!40000 ALTER TABLE `currentmedicine` DISABLE KEYS */;
LOCK TABLES `currentmedicine` WRITE;
INSERT INTO `currentmedicine` VALUES (5,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `currentmedicine` ENABLE KEYS */;

--
-- Table structure for table `deliverysum`
--

DROP TABLE IF EXISTS `deliverysum`;
CREATE TABLE `deliverysum` (
  `id` bigint(20) NOT NULL default '0',
  `latent_phase_duration_421` int(11) default NULL,
  `labour_onset_422` int(11) default NULL,
  `if_induced_mode_423` int(11) default NULL,
  `indication_induction_424` int(11) default NULL,
  `indication_induction_desc_425` text,
  `membranes_re_330` int(11) default NULL,
  `time_rupture_427` time default NULL,
  `liquor_331` int(11) default NULL,
  `foul_smell_278` tinyint(1) default NULL,
  `given_nvp_tablets_1223` tinyint(1) default NULL,
  `nvp_amount_1224` float default NULL,
  `first_stage_began_431` time default NULL,
  `complete_dilitation_432` time default NULL,
  `abnormality_at_dilatation_433` tinyint(1) default NULL,
  `time_last_baby_delivered` time default NULL,
  `abnormality_at_delivery_435` tinyint(1) default NULL,
  `labour_augmented_436` tinyint(1) default NULL,
  `bladder_emptied_437` tinyint(1) default NULL,
  `placenta_delivered_438` time default NULL,
  `placenta_delivery_method_439` int(11) default NULL,
  `mode_of_delivery_447` int(11) default NULL,
  `mode_of_delivery_cs_448` int(11) default NULL,
  `indication_CS_forcepts_60` int(11) default NULL,
  `indication_CS_forcepts_desc_61` text,
  `anesthesia_delivery_451` int(11) default NULL,
  `episiotomy_performed_452` tinyint(1) default NULL,
  `genital_laceration_453` tinyint(1) default NULL,
  `episiotomy_extension_454` int(11) default NULL,
  `anterior_laceration_depth_455` int(11) default NULL,
  `anterior_laceration_sutured_456` int(11) default NULL,
  `posterior_laceration_depth_457` int(11) default NULL,
  `posterior_laceration_sutured_458` tinyint(1) default NULL,
  `cervical_laceration_459` tinyint(1) default NULL,
  `cervical_laceration_sutured_460` tinyint(1) default NULL,
  `pph_461` tinyint(1) default NULL,
  `blood_loss_est_462` int(11) default NULL,
  `pph_treatment_463` int(11) default NULL,
  `if_blood_transfusion_1177` int(11) default NULL,
  `drugs_or_iv_fluids_1178` int(11) default NULL,
  `iv_fluid_volume_1179` int(11) default NULL,
  `treatment_method_desc_464` text,
  `urine_passed_465` tinyint(1) default NULL,
  `bowel_movement_postpartum_466` tinyint(1) default NULL,
  `complications_467` tinyint(1) default NULL,
  `if_complications_desc_468` text,
  `colour_of_placenta_470` int(11) default NULL,
  `presence_of_infarcts_471` tinyint(1) default NULL,
  `presence_of_clots_472` tinyint(1) default NULL,
  `extension_of_vessels_473` tinyint(1) default NULL,
  `other_placenta_abnormal_474` text,
  `haemorrhage_irt_placenta_475` int(11) default NULL,
  `placenta_type_440` int(11) default NULL,
  `state_of_placenta_1204` int(11) default NULL,
  `weight_of_placenta_441` int(11) default NULL,
  `mode_of_cord_insert_442` int(11) default NULL,
  `blood_vessels_in_cord` int(11) default NULL,
  `date_first_stage_began` date default NULL,
  `date_last_baby_delivered` date default NULL,
  `date_placenta_delivered` date default NULL,
  `date_complete_dilitation` date default NULL,
  `mother_received_arv` tinyint(4) default NULL,
  `rupture_of_membranes_date_328` date default NULL,
  `rupture_of_membranes_time_329` time default NULL,
  `pph` tinyint(1) default NULL,
  `nurse_delivering` varchar(255) default NULL,
  `comments` text,
  `utherine_massage` tinyint(4) default NULL,
  `uterotonic_med_given` int(11) default NULL,
  `regimen` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKC781ADD7D1B` (`id`),
  CONSTRAINT `FKC781ADD7D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `deliverysum`
--


/*!40000 ALTER TABLE `deliverysum` DISABLE KEYS */;
LOCK TABLES `deliverysum` WRITE;
INSERT INTO `deliverysum` VALUES (16,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'09:35:35',NULL,NULL,NULL,NULL,NULL,NULL,'09:43:00',NULL,260,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2007-08-14',NULL,'2007-08-14','2007-08-14',NULL,NULL,'09:43:13',NULL,'zepadmin',NULL,NULL,NULL,NULL),(41,244,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,260,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2007-08-14','09:44:36',NULL,NULL,NULL,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `deliverysum` ENABLE KEYS */;

--
-- Table structure for table `district_id`
--

DROP TABLE IF EXISTS `district_id`;
CREATE TABLE `district_id` (
  `id` int(11) NOT NULL auto_increment,
  `district_id` int(11) default NULL,
  `site_id` varchar(4) default NULL,
  `patient_id` int(5) unsigned zerofill default '00000',
  `checksum` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `district_id`
--


/*!40000 ALTER TABLE `district_id` DISABLE KEYS */;
LOCK TABLES `district_id` WRITE;
INSERT INTO `district_id` VALUES (1,5040,'332',00001,NULL),(2,5040,'333',01001,NULL),(3,5040,'332',00002,NULL),(4,5040,'332',00003,NULL),(5,5040,'333',01002,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `district_id` ENABLE KEYS */;

--
-- Table structure for table `drug_stocks`
--

DROP TABLE IF EXISTS `drug_stocks`;
CREATE TABLE `drug_stocks` (
  `id` bigint(20) NOT NULL default '0',
  `drug_name` varchar(255) default NULL,
  `quantity` int(11) default NULL,
  `form_factor` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKdrug_stocks` (`id`),
  CONSTRAINT `FKdrug_stocks` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `drug_stocks`
--


/*!40000 ALTER TABLE `drug_stocks` DISABLE KEYS */;
LOCK TABLES `drug_stocks` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `drug_stocks` ENABLE KEYS */;

--
-- Table structure for table `drugintervention`
--

DROP TABLE IF EXISTS `drugintervention`;
CREATE TABLE `drugintervention` (
  `id` bigint(20) NOT NULL default '0',
  `dateDrugIntervention` date default NULL,
  `drugType` int(11) default NULL,
  `dispensed` tinyint(4) default NULL,
  `drugName` int(11) default NULL,
  `reason_not_dispensed` int(11) default NULL,
  `comments` text,
  `drug_type_enums` int(11) default NULL,
  `drug_type2` int(11) default NULL,
  `drug_type3` int(11) default NULL,
  `drug_type4` int(11) default NULL,
  `drug_type5` int(11) default NULL,
  `drug_type6` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKFB309E77D1B` (`id`),
  CONSTRAINT `FKFB309E77D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `drugintervention`
--


/*!40000 ALTER TABLE `drugintervention` DISABLE KEYS */;
LOCK TABLES `drugintervention` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `drugintervention` ENABLE KEYS */;

--
-- Table structure for table `encounter`
--

DROP TABLE IF EXISTS `encounter`;
CREATE TABLE `encounter` (
  `id` bigint(20) NOT NULL auto_increment,
  `patient_id` bigint(20) default NULL,
  `form_id` bigint(20) NOT NULL default '0',
  `last_modified` datetime default NULL,
  `created` datetime default NULL,
  `last_modified_by` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `site_id` bigint(20) default NULL,
  `flow_id` bigint(20) NOT NULL default '0',
  `date_visit` date NOT NULL default '0000-00-00',
  `pregnancy_id` bigint(20) default NULL,
  `referral_id` bigint(20) default NULL,
  `created_site_id` bigint(20) default NULL,
  `import_encounter_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK5AD86933D25AB9CC` (`flow_id`),
  KEY `FK5AD86933D79EFE76` (`form_id`),
  KEY `FK5AD869337D2B7913` (`site_id`),
  KEY `FK5AD8693351A3A90E` (`created_by`),
  KEY `FK5AD869333E8F4D64` (`last_modified_by`),
  KEY `FK5AD869332370976D` (`pregnancy_id`),
  KEY `FK5AD869338523EC95` (`patient_id`),
  CONSTRAINT `encounter_ibfk_1` FOREIGN KEY (`pregnancy_id`) REFERENCES `pregnancy` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `encounter_ibfk_2` FOREIGN KEY (`last_modified_by`) REFERENCES `user_group_membership` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `encounter_ibfk_3` FOREIGN KEY (`created_by`) REFERENCES `user_group_membership` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `encounter_ibfk_4` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `encounter_ibfk_5` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `encounter`
--


/*!40000 ALTER TABLE `encounter` DISABLE KEYS */;
LOCK TABLES `encounter` WRITE;
INSERT INTO `encounter` VALUES (1,1,1,'2007-08-14 09:43:15','2007-08-14 09:30:47','zepadmin','zepadmin',1,9,'2007-08-14',1,NULL,1,NULL),(2,1,82,'2007-08-14 09:44:36','2007-08-14 09:31:01','zepadmin','zepadmin',1,2,'2007-08-14',1,NULL,1,NULL),(3,1,2,'2007-08-14 09:31:38','2007-08-14 09:31:38','zepadmin','zepadmin',1,2,'2007-08-14',1,NULL,1,NULL),(4,1,70,'2007-08-14 09:31:51','2007-08-14 09:31:51','zepadmin','zepadmin',1,2,'2007-08-14',1,NULL,1,NULL),(5,1,55,'2007-08-14 09:31:58','2007-08-14 09:31:58','zepadmin','zepadmin',1,2,'2007-08-14',1,NULL,1,NULL),(6,1,80,'2007-08-14 09:32:36','2007-08-14 09:32:36','zepadmin','zepadmin',1,1,'2007-08-14',1,NULL,1,NULL),(7,1,77,'2007-08-14 09:32:45','2007-08-14 09:32:45','zepadmin','zepadmin',1,2,'2007-08-14',1,NULL,1,NULL),(8,1,92,'2007-08-14 09:33:12','2007-08-14 09:33:12','zepadmin','zepadmin',1,2,'2007-08-14',1,NULL,1,NULL),(9,1,91,'2007-08-14 09:33:40','2007-08-14 09:33:40','zepadmin','zepadmin',1,102,'2007-08-14',1,NULL,1,NULL),(10,1,87,'2007-08-14 09:34:50','2007-08-14 09:34:05','zepadmin','zepadmin',1,102,'2007-08-14',1,NULL,1,NULL),(11,1,89,'2007-08-14 09:34:05','2007-08-14 09:34:05','zepadmin','zepadmin',1,102,'2007-08-14',1,NULL,1,NULL),(12,1,65,'2007-08-14 09:35:35','2007-08-14 09:35:35','zepadmin','zepadmin',1,7,'2007-08-14',1,NULL,1,NULL),(13,1,79,'2007-08-14 09:35:51','2007-08-14 09:35:51','zepadmin','zepadmin',1,3,'2007-08-14',1,NULL,1,NULL),(14,2,1,'2007-08-14 09:43:27','2007-08-14 09:43:27','zepadmin','zepadmin',1,9,'2007-08-14',1,NULL,1,NULL),(15,2,109,'2007-08-14 09:43:27','2007-08-14 09:43:27','zepadmin','zepadmin',1,4,'2007-08-14',1,NULL,1,NULL),(16,1,66,'2007-08-14 09:43:34','2007-08-14 09:43:34','zepadmin','zepadmin',1,4,'2007-08-14',1,NULL,1,NULL),(17,3,1,'2007-08-14 09:44:18','2007-08-14 09:44:18','zepadmin','zepadmin',1,9,'2007-08-14',2,NULL,1,NULL),(18,3,82,'2007-08-14 09:44:18','2007-08-14 09:44:18','zepadmin','zepadmin',1,2,'2007-08-06',2,NULL,1,NULL),(19,3,2,'2007-08-14 09:44:18','2007-08-14 09:44:18','zepadmin','zepadmin',1,2,'2007-08-14',2,NULL,1,NULL),(20,3,70,'2007-08-14 09:44:18','2007-08-14 09:44:18','zepadmin','zepadmin',1,2,'2007-08-14',2,NULL,1,NULL),(21,3,92,'2007-08-14 09:44:18','2007-08-14 09:44:18','zepadmin','zepadmin',1,2,'2007-08-14',2,NULL,1,NULL),(22,3,90,'2007-08-14 09:44:18','2007-08-14 09:44:18','zepadmin','zepadmin',1,102,'2007-08-14',2,NULL,1,NULL),(23,3,80,'2007-08-14 09:44:18','2007-08-14 09:44:18','zepadmin','zepadmin',1,1,'2007-08-14',2,NULL,1,NULL),(24,3,77,'2007-08-14 09:44:18','2007-08-14 09:44:18','zepadmin','zepadmin',1,2,'2007-08-14',2,NULL,1,NULL),(25,3,65,'2007-08-14 09:44:18','2007-08-14 09:44:18','zepadmin','zepadmin',1,7,'2007-08-14',2,NULL,1,NULL),(26,4,1,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,9,'2007-08-14',3,NULL,1,NULL),(27,4,82,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,2,'2007-08-25',3,NULL,1,NULL),(28,4,2,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,2,'2007-08-14',3,NULL,1,NULL),(29,4,70,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,2,'2007-08-14',3,NULL,1,NULL),(30,4,92,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,2,'2007-08-14',3,NULL,1,NULL),(31,4,90,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,102,'2007-08-14',3,NULL,1,NULL),(32,4,80,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,1,'2007-08-14',3,NULL,1,NULL),(33,4,77,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,2,'2007-08-14',3,NULL,1,NULL),(34,4,65,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,7,'2007-08-14',3,NULL,1,NULL),(35,4,65,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,7,'2007-08-14',3,NULL,1,NULL),(36,4,91,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,102,'2007-08-14',3,NULL,1,NULL),(37,4,79,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,3,'2007-08-14',3,NULL,1,NULL),(38,5,1,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,9,'2007-08-14',3,NULL,1,NULL),(39,5,109,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,4,'2007-08-14',3,NULL,1,NULL),(40,5,23,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,4,'2007-08-14',3,NULL,1,NULL),(41,4,66,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,4,'2007-08-14',3,NULL,1,NULL),(42,4,81,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,4,'2007-08-14',3,NULL,1,NULL),(43,4,68,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,4,'2007-08-14',3,NULL,1,NULL),(44,4,28,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,4,'2007-08-14',3,NULL,1,NULL),(45,3,65,'2007-08-14 09:59:40','2007-08-14 09:59:40','zepadmin','zepadmin',1,7,'2007-08-14',2,NULL,1,NULL),(46,3,94,'2007-08-14 09:59:40','2007-08-14 09:59:40','zepadmin','zepadmin',1,100,'2007-08-14',2,37,1,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `encounter` ENABLE KEYS */;

--
-- Table structure for table `encounter_archive`
--

DROP TABLE IF EXISTS `encounter_archive`;
CREATE TABLE `encounter_archive` (
  `id` bigint(20) NOT NULL default '0',
  `patient_id` bigint(20) default NULL,
  `form_id` bigint(20) NOT NULL default '0',
  `last_modified` datetime default NULL,
  `created` datetime default NULL,
  `last_modified_by` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `site_id` bigint(20) default NULL,
  `flow_id` bigint(20) NOT NULL default '0',
  `date_visit` date NOT NULL default '0000-00-00',
  `pregnancy_id` bigint(20) default NULL,
  `referral_id` bigint(20) default NULL,
  `created_site_id` bigint(20) default NULL,
  `import_encounter_id` bigint(20) default NULL,
  `date_deleted` datetime default NULL,
  `deleted_by` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKFDFA0DB651A3A90E` (`created_by`),
  KEY `FKFDFA0DB63E8F4D64` (`last_modified_by`),
  KEY `FKFDFA0DB67D2B7913` (`site_id`),
  CONSTRAINT `FKFDFA0DB63E8F4D64` FOREIGN KEY (`last_modified_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FKFDFA0DB651A3A90E` FOREIGN KEY (`created_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FKFDFA0DB67D2B7913` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `encounter_archive`
--


/*!40000 ALTER TABLE `encounter_archive` DISABLE KEYS */;
LOCK TABLES `encounter_archive` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `encounter_archive` ENABLE KEYS */;

--
-- Table structure for table `encounter_outcome`
--

DROP TABLE IF EXISTS `encounter_outcome`;
CREATE TABLE `encounter_outcome` (
  `id` bigint(20) NOT NULL default '0',
  `form_id` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKE89D3446D1B` (`id`),
  CONSTRAINT `FKE89D3446D1B` FOREIGN KEY (`id`) REFERENCES `outcome` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `encounter_outcome`
--


/*!40000 ALTER TABLE `encounter_outcome` DISABLE KEYS */;
LOCK TABLES `encounter_outcome` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `encounter_outcome` ENABLE KEYS */;

--
-- Table structure for table `encounter_value_archive`
--

DROP TABLE IF EXISTS `encounter_value_archive`;
CREATE TABLE `encounter_value_archive` (
  `id` bigint(20) NOT NULL auto_increment,
  `encounter_id` bigint(20) NOT NULL default '0',
  `page_item_id` bigint(20) NOT NULL default '0',
  `value` varchar(255) NOT NULL default '',
  `previous_value` varchar(255) NOT NULL default '',
  `patient_id` bigint(20) default NULL,
  `pregnancy_id` bigint(20) default NULL,
  `field_id` bigint(20) NOT NULL default '0',
  `last_modified` datetime default NULL,
  `created` datetime default NULL,
  `last_modified_by` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `site_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK6729BDE87D2B7913` (`site_id`),
  KEY `FK6729BDE851A3A90E` (`created_by`),
  KEY `FK6729BDE83E8F4D64` (`last_modified_by`),
  CONSTRAINT `FK6729BDE83E8F4D64` FOREIGN KEY (`last_modified_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FK6729BDE851A3A90E` FOREIGN KEY (`created_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FK6729BDE87D2B7913` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `encounter_value_archive`
--


/*!40000 ALTER TABLE `encounter_value_archive` DISABLE KEYS */;
LOCK TABLES `encounter_value_archive` WRITE;
INSERT INTO `encounter_value_archive` VALUES (1,10,3860,'2007-08-14','null',1,1,1846,'2007-08-14 09:34:42','2007-08-14 09:34:05','zepadmin','zepadmin',1),(2,10,4164,'120','0',1,1,2004,'2007-08-14 09:34:50','2007-08-14 09:34:05','zepadmin','zepadmin',1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `encounter_value_archive` ENABLE KEYS */;

--
-- Table structure for table `hemotology`
--

DROP TABLE IF EXISTS `hemotology`;
CREATE TABLE `hemotology` (
  `id` bigint(20) NOT NULL default '0',
  `wbc` float default NULL,
  `rbc` float default NULL,
  `hgb` float default NULL,
  `hct` float default NULL,
  `mcv` float default NULL,
  `mch` float default NULL,
  `mchc` float default NULL,
  `plt` float default NULL,
  `labtest_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKhemotology` (`id`),
  CONSTRAINT `FKhemotology` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hemotology`
--


/*!40000 ALTER TABLE `hemotology` DISABLE KEYS */;
LOCK TABLES `hemotology` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `hemotology` ENABLE KEYS */;

--
-- Table structure for table `hiv_report`
--

DROP TABLE IF EXISTS `hiv_report`;
CREATE TABLE `hiv_report` (
  `id` bigint(20) NOT NULL default '0',
  `district_patient_id` varchar(20) default NULL,
  `patient_name` varchar(100) default NULL,
  `encounter_date` date default NULL,
  `cd4_done` bit(1) default NULL,
  `cd4_date` date default NULL,
  `cd4_result` int(11) default NULL,
  `hgb_date` date default NULL,
  `hgb_result` float default NULL,
  `who_screen` varchar(20) default NULL,
  `referral_to_art` varchar(30) default NULL,
  `pmtct_regimen` varchar(30) default NULL,
  `ega_weeks` varchar(8) default NULL,
  `date_next_visit` date default NULL,
  `site_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hiv_report`
--


/*!40000 ALTER TABLE `hiv_report` DISABLE KEYS */;
LOCK TABLES `hiv_report` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `hiv_report` ENABLE KEYS */;

--
-- Table structure for table `infantdischargesummary`
--

DROP TABLE IF EXISTS `infantdischargesummary`;
CREATE TABLE `infantdischargesummary` (
  `id` bigint(20) NOT NULL default '0',
  `tactile` tinyint(1) default NULL,
  `tactile_results` text,
  `Suction` tinyint(1) default NULL,
  `suction_results` text,
  `oxygen` tinyint(1) default NULL,
  `oxygen_results` text,
  `intubation` tinyint(1) default NULL,
  `intubation_results` text,
  `ephinephrine` tinyint(1) default NULL,
  `ephinephrine_results` text,
  `naxolone` tinyint(1) default NULL,
  `naxolone_results` text,
  `atropine` tinyint(1) default NULL,
  `atropine_results` text,
  `bag_and_mask` tinyint(1) default NULL,
  `bag_and_mask_results` text,
  `sodium_bcarbonate` tinyint(1) default NULL,
  `sodium_bcarbonate_results` text,
  `other_drugs` tinyint(1) default NULL,
  `other_drugs_results` text,
  `breast_feeding_well_518` tinyint(1) default NULL,
  `used_formula_for_feeding` tinyint(1) default NULL,
  `jaundice_519` tinyint(1) default NULL,
  `good_head_control_520` tinyint(1) default NULL,
  `good_grasp_reflex_521` int(11) default NULL,
  `symmetrical_moro_522` int(11) default NULL,
  `eyes_523` int(11) default NULL,
  `eyes_other_524` text,
  `if_eyes_abnormal_treatment_525` int(11) default NULL,
  `mouth_526` int(11) default NULL,
  `mouth_other_527` text,
  `sucking_528` int(11) default NULL,
  `genitalia_529` int(11) default NULL,
  `genitalia_other_697` text,
  `crown_heel_length_532` int(11) default NULL,
  `urine_passed_1181` tinyint(1) default NULL,
  `bowel_movement_535` tinyint(1) default NULL,
  `back_536` int(11) default NULL,
  `back_other_537` text,
  `upper_extrem_538` int(11) default NULL,
  `upper_extrem_desc_539` text,
  `ortolani_sign_540` int(11) default NULL,
  `lower_extrem_541` int(11) default NULL,
  `lower_extrem_desc_542` text,
  `anomalies_cns_543` int(11) default NULL,
  `anomalies_cns_desc_544` text,
  `anomalies_chromo_545` int(11) default NULL,
  `anomalies_chromo_desc_546` text,
  `anomalies_cardio_547` int(11) default NULL,
  `anomalies_cardio_desc_548` text,
  `anomalies_genitour_549` int(11) default NULL,
  `anomalies_genitour_desc_550` text,
  `anomalies_other_551` int(11) default NULL,
  `anomalies_ohter_1189` text,
  `ega_129` int(11) default NULL,
  `immunization_554` int(11) default NULL,
  `immunization_1` int(11) default NULL,
  `immunization_2` int(11) default NULL,
  `immunization_3` int(11) default NULL,
  `immunization_4` int(11) default NULL,
  `immunization_5` int(11) default NULL,
  `immunisation_desc_556` text,
  `dosage_nevirapine_555` int(11) default NULL,
  `weight_on_3rd_day_558` float default NULL,
  `problems_comments_557` text,
  `place_of_next_visit_1216` varchar(255) default NULL,
  `birth_record_given_561` tinyint(1) default NULL,
  `treatment_on_discharge_562` text,
  `other_comments_563` text,
  `first_postnatal_visit_date_564` date default NULL,
  `first_postnatal_visit_place_565` int(11) default NULL,
  `date_of_discharge_1268` date default NULL,
  `place_of_next_visit_1213` varchar(255) default NULL,
  `receives_vitamine_k` tinyint(1) default NULL,
  `receives_tetracycline` tinyint(1) default NULL,
  `patient_received_arv` tinyint(4) default NULL,
  `feeding` int(11) default NULL,
  `feeding_type` int(11) default NULL,
  `head_circumf_530` float default NULL,
  `chest_circum_531` float default NULL,
  `baby_received_arv` int(11) default NULL,
  `initial_nevirapine_dose` tinyint(4) default NULL,
  `rbd_home_regimen` int(11) default NULL,
  `rbd_home_dosage` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK5B8E30AD1B` (`id`),
  CONSTRAINT `FK5B8E30AD1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `infantdischargesummary`
--


/*!40000 ALTER TABLE `infantdischargesummary` DISABLE KEYS */;
LOCK TABLES `infantdischargesummary` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `infantdischargesummary` ENABLE KEYS */;

--
-- Table structure for table `infantphysicalexam`
--

DROP TABLE IF EXISTS `infantphysicalexam`;
CREATE TABLE `infantphysicalexam` (
  `id` bigint(20) NOT NULL default '0',
  `skull_771` int(11) default NULL,
  `eyes_523` int(11) default NULL,
  `eyes_other_524` text,
  `ears_686` int(11) default NULL,
  `ears_other_687` text,
  `mouth_526` int(11) default NULL,
  `mouth_other_527` text,
  `sucking_528` int(11) default NULL,
  `neck_690` int(11) default NULL,
  `neck_other_d_691` text,
  `breasts_infant_780` int(11) default NULL,
  `chest_infant_781` int(11) default NULL,
  `respiratory_effort_782` int(11) default NULL,
  `respiratory_effort_other_783` text,
  `abdomen_liver_784` int(11) default NULL,
  `sole_creases_785` int(11) default NULL,
  `genitalia_529` int(11) default NULL,
  `genitalia_other_697` text,
  `spleen_infant_787` int(11) default NULL,
  `kidney_infant_788` int(11) default NULL,
  `cord_complic_789` int(11) default NULL,
  `no_of_arteries_790` int(11) default NULL,
  `neonatal_reflex_791` int(11) default NULL,
  `symmetrical_moro_522` int(11) default NULL,
  `upper_extrem_538` int(11) default NULL,
  `upper_extrem_desc_539` text,
  `ortolani_sign_540` int(11) default NULL,
  `lower_extrem_541` int(11) default NULL,
  `lower_extrem_desc_542` text,
  `anomalies_cns_543` int(11) default NULL,
  `anomalies_cns_desc_544` text,
  `anomalies_chromo_545` int(11) default NULL,
  `anomalies_chromo_desc_546` text,
  `anomalies_cardio_547` int(11) default NULL,
  `anomalies_cardio_desc_548` text,
  `anomalies_genitour_549` int(11) default NULL,
  `anomalies_genitour_desc_550` text,
  `anomalies_other_551` int(11) default NULL,
  `anomalies_ohter_1189` text,
  `injuries_trauma_807` int(11) default NULL,
  `clinical_age_808` varchar(255) default NULL,
  `hypoxic_isch_hie_809` int(11) default NULL,
  `hie_irritabi_810` int(11) default NULL,
  `hie_hyponton_811` int(11) default NULL,
  `hie_feeding_812` int(11) default NULL,
  `hie_seizures_813` int(11) default NULL,
  `hie_total_sc_814` int(11) default NULL,
  `weight_on_admission_763` float default NULL,
  `temperature_infant_680` float default NULL,
  `pallor_766` tinyint(1) default NULL,
  `cyanosis_767` tinyint(1) default NULL,
  `jaundice_519` tinyint(1) default NULL,
  `heart_rate_769` int(11) default NULL,
  `birth_weight_758` float default NULL,
  `reason_for_admission_759` int(11) default NULL,
  `reason_for_admission_other_761` text,
  `head_circumf_530` float default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKFDE1B6DCD1B` (`id`),
  CONSTRAINT `FKFDE1B6DCD1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `infantphysicalexam`
--


/*!40000 ALTER TABLE `infantphysicalexam` DISABLE KEYS */;
LOCK TABLES `infantphysicalexam` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `infantphysicalexam` ENABLE KEYS */;

--
-- Table structure for table `infantproblem`
--

DROP TABLE IF EXISTS `infantproblem`;
CREATE TABLE `infantproblem` (
  `id` bigint(20) NOT NULL default '0',
  `lower_abdominal_pains` tinyint(4) default NULL,
  `lower_abdominal_pains_date_onset` date default NULL,
  `vag_bleeding_1254` tinyint(4) default NULL,
  `bleeding_date_of_onset_1255` date default NULL,
  `vag_discharge_1252` tinyint(4) default NULL,
  `vag_discharge_date_of_onset_1253` date default NULL,
  `fever` tinyint(4) default NULL,
  `fever_date_onset` date default NULL,
  `elevated_blood_pressure` tinyint(4) default NULL,
  `swelling_edema` tinyint(4) default NULL,
  `trauma` tinyint(4) default NULL,
  `nausea_vomiting` tinyint(4) default NULL,
  `diarrhea` tinyint(4) default NULL,
  `diarrhea_date_onset` date default NULL,
  `shortness_of_breath` tinyint(4) default NULL,
  `possible_infection` tinyint(4) default NULL,
  `backache` tinyint(4) default NULL,
  `height_159` int(11) default NULL,
  `weight_228` float default NULL,
  `heent_161` int(11) default NULL,
  `heent_abnorm_162` text,
  `thyroid_165` int(11) default NULL,
  `breasts_166` int(11) default NULL,
  `heart_169` int(11) default NULL,
  `heart_other_170` text,
  `abdomen_172` int(11) default NULL,
  `abdomen_abnormal_173` text,
  `skin_176` int(11) default NULL,
  `skin_abnorm_177` text,
  `extremities_174` int(11) default NULL,
  `extremities_abnormal_175` text,
  `lymph_nodes_178` int(11) default NULL,
  `rectum_179` int(11) default NULL,
  `rectum_abnormal_180` text,
  `vulva_181` int(11) default NULL,
  `vulva_abnormal_182` text,
  `vagina_183` int(11) default NULL,
  `vagina_abnormal_184` text,
  `cervix_185` int(11) default NULL,
  `cervix_abnormal_186` text,
  `uterus_187` int(11) default NULL,
  `uterus_size_in_days_188` int(11) default NULL,
  `adnexa_189` int(11) default NULL,
  `adnexa_abnormal_190` text,
  `varicosities_191` int(11) default NULL,
  `teeth_163` int(11) default NULL,
  `teeth_other_164` text,
  `cns_192` int(11) default NULL,
  `malaria_diag` tinyint(1) default NULL,
  `anaemia` tinyint(1) default NULL,
  `high_bp_diag` tinyint(1) default NULL,
  `vaginal_bleeding_diag` tinyint(1) default NULL,
  `uti_diag` tinyint(1) default NULL,
  `pneumonia_diag` tinyint(1) default NULL,
  `tb_diag` tinyint(1) default NULL,
  `vaginal_thrush_diag` tinyint(1) default NULL,
  `oral_thrush_diag` tinyint(1) default NULL,
  `diag_other` text,
  `disposition_labor` int(11) default NULL,
  `treatment_1463` text,
  `comments_ante_prob_1464` text,
  `feeding_type` int(11) default NULL,
  `infant_sleeping_pattern` int(11) default NULL,
  `passing_urine` int(11) default NULL,
  `passing_stool` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKBA029979D1B` (`id`),
  CONSTRAINT `FKBA029979D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `infantproblem`
--


/*!40000 ALTER TABLE `infantproblem` DISABLE KEYS */;
LOCK TABLES `infantproblem` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `infantproblem` ENABLE KEYS */;

--
-- Table structure for table `initialvisit`
--

DROP TABLE IF EXISTS `initialvisit`;
CREATE TABLE `initialvisit` (
  `id` bigint(20) NOT NULL default '0',
  `lmp_reliability_126` int(11) default NULL,
  `lmp_127` date default NULL,
  `edd_128` date default NULL,
  `ega_129` int(11) default NULL,
  `quickening_130` int(11) default NULL,
  `menstrual_history_131` int(11) default NULL,
  `cycle_in_days_132` int(11) default NULL,
  `pallor_193` int(11) default NULL,
  `planned_preg_135` tinyint(1) default NULL,
  `contracept_practiced_136` tinyint(1) default NULL,
  `contraceptive_choice_137` int(11) default NULL,
  `contraceptive_other_138` varchar(255) default NULL,
  `height_159` int(11) default NULL,
  `weight_228` float default NULL,
  `temperature_266` float default NULL,
  `bp_systolic_224` int(11) default NULL,
  `bp_diastolic_225` int(11) default NULL,
  `heent_161` int(11) default NULL,
  `heent_abnorm_162` text,
  `teeth_163` int(11) default NULL,
  `teeth_other_164` text,
  `thyroid_165` int(11) default NULL,
  `breasts_166` int(11) default NULL,
  `respiratory_system_167` int(11) default NULL,
  `respiratory_system_other` text,
  `heart_169` int(11) default NULL,
  `heart_other_170` text,
  `pulse_171` int(11) default NULL,
  `abdomen_172` int(11) default NULL,
  `abdomen_abnormal_173` text,
  `extremities_174` int(11) default NULL,
  `extremities_abnormal_175` text,
  `skin_176` int(11) default NULL,
  `skin_abnorm_177` text,
  `lymph_nodes_178` int(11) default NULL,
  `rectum_179` int(11) default NULL,
  `rectum_abnormal_180` text,
  `vulva_181` int(11) default NULL,
  `vulva_abnormal_182` text,
  `vagina_183` int(11) default NULL,
  `vagina_abnormal_184` text,
  `cervix_185` int(11) default NULL,
  `cervix_abnormal_186` text,
  `uterus_187` int(11) default NULL,
  `uterus_size_in_weeks_188` int(11) default NULL,
  `adnexa_189` int(11) default NULL,
  `adnexa_abnormal_190` text,
  `varicosities_191` int(11) default NULL,
  `cns_192` int(11) default NULL,
  `comments` text,
  PRIMARY KEY  (`id`),
  KEY `FKE8D6E087D1B` (`id`),
  CONSTRAINT `FKE8D6E087D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `initialvisit`
--


/*!40000 ALTER TABLE `initialvisit` DISABLE KEYS */;
LOCK TABLES `initialvisit` WRITE;
INSERT INTO `initialvisit` VALUES (7,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,167,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(24,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,135,NULL,38,NULL,NULL,NULL,NULL,NULL,NULL,619,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(33,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,135,NULL,38,NULL,NULL,NULL,NULL,NULL,NULL,619,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `initialvisit` ENABLE KEYS */;

--
-- Table structure for table `intralaboureval`
--

DROP TABLE IF EXISTS `intralaboureval`;
CREATE TABLE `intralaboureval` (
  `id` bigint(20) NOT NULL default '0',
  `time_of_exam_1175` time default NULL,
  `presentation_314` int(11) default NULL,
  `lie_313` int(11) default NULL,
  `contraction_freq_10_abd_palp_317` int(11) default NULL,
  `contraction_strength_316` int(11) default NULL,
  `rupture_of_membranes_1221` tinyint(1) default NULL,
  `membranes_re_330` int(11) default NULL,
  `rupture_of_membranes_date_328` date default NULL,
  `rupture_of_membranes_time_329` time default NULL,
  `liquor_331` int(11) default NULL,
  `cervix_dilatation325` int(11) default NULL,
  `cervix_effacement_326` int(11) default NULL,
  `cervix_consistency_327` int(11) default NULL,
  `caput_339` int(11) default NULL,
  `position_337` varchar(255) default NULL,
  `station_of_pp_336` int(11) default NULL,
  `moulding_338` int(11) default NULL,
  `condition_of_vagina_other_324` varchar(255) default NULL,
  `cord_at_vaginal_exam_340` int(11) default NULL,
  `fetal_heart_rate` int(11) default NULL,
  `urinalysis_alb_242` int(11) default NULL,
  `urinalysis_glu_243` int(11) default NULL,
  `urinalysis_ace_244` int(11) default NULL,
  `drug_1_1136` int(11) default NULL,
  `drug_2_1137` int(11) default NULL,
  `drug_3_1138` int(11) default NULL,
  `drug_4_1139` int(11) default NULL,
  `drug_5_1140` int(11) default NULL,
  `drug_6_1141` int(11) default NULL,
  `drug_7_1142` int(11) default NULL,
  `drug_8_1143` int(11) default NULL,
  `drug_9_1144` int(11) default NULL,
  `drug_10_1145` int(11) default NULL,
  `current_medicine_1146` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK927B5899D1B` (`id`),
  CONSTRAINT `FK927B5899D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `intralaboureval`
--


/*!40000 ALTER TABLE `intralaboureval` DISABLE KEYS */;
LOCK TABLES `intralaboureval` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `intralaboureval` ENABLE KEYS */;

--
-- Table structure for table `labresults`
--

DROP TABLE IF EXISTS `labresults`;
CREATE TABLE `labresults` (
  `id` bigint(20) NOT NULL default '0',
  `abo_group_193` int(11) default NULL,
  `no_abo_group_reason_194` int(11) default NULL,
  `no_abo_group_reason_desc_195` text,
  `rhesus_196` int(11) default NULL,
  `no_rhesus_reason_197` int(11) default NULL,
  `no_rhesus_reason_desc_198` text,
  `rpr_result_199` int(11) default NULL,
  `no_rpr_reason_200` int(11) default NULL,
  `no_rpr_reason_desc_201` text,
  `if_reactive_treatment_202` int(11) default NULL,
  `treatment_other_203` text,
  `hb_235` int(11) default NULL,
  `no_hb_reason_205` int(11) default NULL,
  `no_hb_reason_desc_206` text,
  `cervical_smell_207` int(11) default NULL,
  `no_cervical_reason_208` int(11) default NULL,
  `no_cervical_reason_desc_209` text,
  `accepts_hiv_today_210` tinyint(1) default NULL,
  `hiv_result_211` int(11) default NULL,
  `sickle_cell_test` tinyint(1) default NULL,
  `sickle_cell_test_results` int(11) default NULL,
  `malaria_test` tinyint(1) default NULL,
  `malaria_test_results` int(11) default NULL,
  `sickle_cell_screen` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK428831C9D1B` (`id`),
  CONSTRAINT `FK428831C9D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `labresults`
--


/*!40000 ALTER TABLE `labresults` DISABLE KEYS */;
LOCK TABLES `labresults` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `labresults` ENABLE KEYS */;

--
-- Table structure for table `labtest`
--

DROP TABLE IF EXISTS `labtest`;
CREATE TABLE `labtest` (
  `id` bigint(20) NOT NULL default '0',
  `dateLabRequest` date default NULL,
  `labType` int(11) default NULL,
  `dateLabResults` date default NULL,
  `results` int(11) default NULL,
  `treatment` text,
  `comments` text,
  `resultsNumeric` float default NULL,
  `reason_not_treating` int(11) default NULL,
  `cd4count` int(11) default NULL,
  `extended_test_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKFC42579FD1B` (`id`),
  CONSTRAINT `FKFC42579FD1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `labtest`
--


/*!40000 ALTER TABLE `labtest` DISABLE KEYS */;
LOCK TABLES `labtest` WRITE;
INSERT INTO `labtest` VALUES (10,'2007-08-14',3042,'2007-08-14',NULL,NULL,NULL,NULL,NULL,120,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `labtest` ENABLE KEYS */;

--
-- Table structure for table `latentfirststagelabour`
--

DROP TABLE IF EXISTS `latentfirststagelabour`;
CREATE TABLE `latentfirststagelabour` (
  `id` bigint(20) NOT NULL default '0',
  `foetal_heart_rate_230` int(11) default NULL,
  `pulse_171` int(11) default NULL,
  `bp_systolic_224` int(11) default NULL,
  `bp_diastolic_225` int(11) default NULL,
  `temperature_266` float default NULL,
  `urinalysis_240` int(11) default NULL,
  `urinalysis_alb_242` int(11) default NULL,
  `urinalysis_glu_243` int(11) default NULL,
  `urinalysis_ace_244` int(11) default NULL,
  `contractions_367` int(11) default NULL,
  `contraction_freq_10_mins_368` int(11) default NULL,
  `remarks_369` text,
  `diagnosis` int(11) default NULL,
  `priority_of_referral` int(11) default NULL,
  `transport` int(11) default NULL,
  `cervix_dilatation325` int(11) default NULL,
  `time_exam` time default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKBF016959D1B` (`id`),
  CONSTRAINT `FKBF016959D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `latentfirststagelabour`
--


/*!40000 ALTER TABLE `latentfirststagelabour` DISABLE KEYS */;
LOCK TABLES `latentfirststagelabour` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `latentfirststagelabour` ENABLE KEYS */;

--
-- Table structure for table `liverfunction`
--

DROP TABLE IF EXISTS `liverfunction`;
CREATE TABLE `liverfunction` (
  `id` bigint(20) NOT NULL default '0',
  `alt` int(11) default NULL,
  `ast` int(11) default NULL,
  `alk_phos` int(11) default NULL,
  `tbili` float default NULL,
  `dbili` float default NULL,
  `ggt` int(11) default NULL,
  `labtest_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKliverfunction` (`id`),
  CONSTRAINT `FKliverfunction` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `liverfunction`
--


/*!40000 ALTER TABLE `liverfunction` DISABLE KEYS */;
LOCK TABLES `liverfunction` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `liverfunction` ENABLE KEYS */;

--
-- Table structure for table `maternal_disch_sum`
--

DROP TABLE IF EXISTS `maternal_disch_sum`;
CREATE TABLE `maternal_disch_sum` (
  `id` bigint(20) NOT NULL default '0',
  `bp_systolic_224` int(11) default NULL,
  `bp_diastolic_225` int(11) default NULL,
  `temperature_266` float default NULL,
  `hb_235` int(11) default NULL,
  `breast_feedi_576` tinyint(1) default NULL,
  `bonding_well_577` tinyint(1) default NULL,
  `involution_uterus_578` int(11) default NULL,
  `lochia_579` int(11) default NULL,
  `perineum_580` int(11) default NULL,
  `perineum_other_1199` varchar(255) default NULL,
  `perineum_infect_desc_1198` varchar(255) default NULL,
  `outcome_of_mother_582` int(11) default NULL,
  `postpartum_i_66` int(11) default NULL,
  `postpartum_complications_584` int(11) default NULL,
  `mother_receive_vit_a_585` tinyint(1) default NULL,
  `medication_586` int(11) default NULL,
  `medication_other_587` varchar(255) default NULL,
  `unplanned_pregnancy_588` tinyint(1) default NULL,
  `contraceptive_choice_137` int(11) default NULL,
  `contraceptive_other_138` varchar(255) default NULL,
  `referral_to_uth_286` tinyint(1) default NULL,
  `priority_of_referral` int(11) default NULL,
  `reason_for_referral_592` int(11) default NULL,
  `reason_for_referral_1202` varchar(255) default NULL,
  `if_cs_cond_wound_1205` int(11) default NULL,
  `if_cs_cond_other_1206` varchar(255) default NULL,
  `family_planning_discussed_594` tinyint(1) default NULL,
  `treatment_on_discharge_595` varchar(255) default NULL,
  `general_condition_260` int(11) default NULL,
  `comments_maternal_discharge_597` varchar(255) default NULL,
  `autopsy_requested_598` tinyint(1) default NULL,
  `autopsy_consent_599` int(11) default NULL,
  `maternal_summary_discharge` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK579A1CECD1B` (`id`),
  CONSTRAINT `FK579A1CECD1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `maternal_disch_sum`
--


/*!40000 ALTER TABLE `maternal_disch_sum` DISABLE KEYS */;
LOCK TABLES `maternal_disch_sum` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `maternal_disch_sum` ENABLE KEYS */;

--
-- Table structure for table `maternaldischarge`
--

DROP TABLE IF EXISTS `maternaldischarge`;
CREATE TABLE `maternaldischarge` (
  `id` bigint(20) NOT NULL default '0',
  `bp_systolic_224` int(11) default NULL,
  `bp_diastolic_225` int(11) default NULL,
  `temperature_266` float default NULL,
  `hb_235` int(11) default NULL,
  `breast_feedi_576` tinyint(1) default NULL,
  `bonding_well_577` tinyint(1) default NULL,
  `involution_uterus_578` int(11) default NULL,
  `lochia_579` int(11) default NULL,
  `perineum_580` int(11) default NULL,
  `perineum_other_1199` varchar(255) default NULL,
  `perineum_infect_desc_1198` varchar(255) default NULL,
  `outcome_of_mother_582` int(11) default NULL,
  `postpartum_i_66` int(11) default NULL,
  `postpartum_complications_584` int(11) default NULL,
  `mother_receive_vit_a_585` tinyint(1) default NULL,
  `medication_586` int(11) default NULL,
  `medication_other_587` text,
  `unplanned_pregnancy_588` tinyint(1) default NULL,
  `contraceptive_choice_137` int(11) default NULL,
  `contraceptive_other_138` varchar(255) default NULL,
  `if_cs_cond_wound_1205` int(11) default NULL,
  `if_cs_cond_other_1206` varchar(255) default NULL,
  `family_planning_discussed_594` tinyint(1) default NULL,
  `treatment_on_discharge_595` text,
  `general_condition_260` int(11) default NULL,
  `comments_maternal_discharge_597` text,
  `autopsy_requested_598` tinyint(1) default NULL,
  `autopsy_consent_599` int(11) default NULL,
  `maternal_summary_discharge` int(11) default NULL,
  `priority_of_referral` int(11) default NULL,
  `transport` int(11) default NULL,
  `reason_for_referral_592` int(11) default NULL,
  `reason_for_referral_1202` text,
  `cs` tinyint(1) default NULL,
  `date_followup_visit` date default NULL,
  `place_followup_visit` int(11) default NULL,
  `patient_received_arv` tinyint(4) default NULL,
  `feeding_type` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK9B313656D1B` (`id`),
  CONSTRAINT `FK9B313656D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `maternaldischarge`
--


/*!40000 ALTER TABLE `maternaldischarge` DISABLE KEYS */;
LOCK TABLES `maternaldischarge` WRITE;
INSERT INTO `maternaldischarge` VALUES (43,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,131,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2886);
UNLOCK TABLES;
/*!40000 ALTER TABLE `maternaldischarge` ENABLE KEYS */;

--
-- Table structure for table `medsurghist`
--

DROP TABLE IF EXISTS `medsurghist`;
CREATE TABLE `medsurghist` (
  `id` bigint(20) NOT NULL default '0',
  `mental_illness_69` tinyint(1) default NULL,
  `epilepsy_70` tinyint(1) default NULL,
  `thyroid_71` tinyint(1) default NULL,
  `diabetes_72` tinyint(1) default NULL,
  `diabetes_years_73` int(11) default NULL,
  `hypertension_74` tinyint(1) default NULL,
  `heart_disease_75` tinyint(1) default NULL,
  `rheumatic_heart_disease_76` tinyint(1) default NULL,
  `tuberculosis_78` tinyint(1) default NULL,
  `asthma_79` tinyint(1) default NULL,
  `pneumonia_80` tinyint(1) default NULL,
  `kidney_disease_81` tinyint(1) default NULL,
  `pyelonephritis_82` tinyint(1) default NULL,
  `liver_disease_83` tinyint(1) default NULL,
  `dvt_clots_84` tinyint(1) default NULL,
  `malaria_85` tinyint(1) default NULL,
  `anaemia_1548` tinyint(1) default NULL,
  `sickle_cell_disease_87` tinyint(1) default NULL,
  `genital_herpes_89` tinyint(1) default NULL,
  `gc_90` tinyint(1) default NULL,
  `chlamydia_91` tinyint(1) default NULL,
  `hpv_92` tinyint(1) default NULL,
  `hepatitis_b_93` tinyint(1) default NULL,
  `syphilis_94` tinyint(1) default NULL,
  `currently_taking_meds_95` tinyint(1) default NULL,
  `drug_allergies_98` tinyint(1) default NULL,
  `drug_allergy_99` text,
  `comments_1249` text,
  `prior_operations` tinyint(1) default NULL,
  `appendicectomy_116` tinyint(1) default NULL,
  `if_appendectomy_117` int(11) default NULL,
  `pelvic_operation_118` tinyint(1) default NULL,
  `if_pelvic_op_year_119` int(11) default NULL,
  `if_pelvic_op_descr_120` text,
  `transfusions_121` tinyint(1) default NULL,
  `other_surgery_1_1147` tinyint(1) default NULL,
  `incident_1_year_122` int(11) default NULL,
  `incident_1_desc_123` text,
  `other_surgery_2_1148` tinyint(1) default NULL,
  `incident_2_year_1149` int(11) default NULL,
  `incident_2_desc_1150` text,
  `other_surgery_3_1151` tinyint(1) default NULL,
  `incident_3_year_1152` int(11) default NULL,
  `incident_3_desc_1153` text,
  `other_surger_1154` tinyint(1) default NULL,
  `incident_4_year_1155` int(11) default NULL,
  `incident_4_desc_1156` text,
  `other_surgery_5_1157` tinyint(1) default NULL,
  `incident_5_year_1158` int(11) default NULL,
  `incident_5_desc_1159` text,
  `hiv` tinyint(4) default NULL,
  `cs` tinyint(4) default NULL,
  `cs_year` int(11) default NULL,
  `family_history_birth_defects` tinyint(4) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK50DA2185D1B` (`id`),
  CONSTRAINT `FK50DA2185D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `medsurghist`
--


/*!40000 ALTER TABLE `medsurghist` DISABLE KEYS */;
LOCK TABLES `medsurghist` WRITE;
INSERT INTO `medsurghist` VALUES (4,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(20,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL),(29,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `medsurghist` ENABLE KEYS */;

--
-- Table structure for table `menu_item`
--

DROP TABLE IF EXISTS `menu_item`;
CREATE TABLE `menu_item` (
  `id` bigint(20) NOT NULL auto_increment,
  `label` varchar(255) NOT NULL default '',
  `type` varchar(255) NOT NULL default '',
  `target_id` bigint(20) default NULL,
  `text_target` varchar(255) default NULL,
  `display_order` int(11) default NULL,
  `require_patient` tinyint(1) NOT NULL default '0',
  `is_enabled` tinyint(1) NOT NULL default '0',
  `role` varchar(255) default NULL,
  `last_modified` datetime default NULL,
  `created` datetime default NULL,
  `last_modified_by` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `site_id` bigint(20) default NULL,
  `parent_id` bigint(20) default NULL,
  `description` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKA4FAA1F37D2B7913` (`site_id`),
  KEY `FKA4FAA1F351A3A90E` (`created_by`),
  KEY `FKA4FAA1F33E8F4D64` (`last_modified_by`),
  CONSTRAINT `FKA4FAA1F33E8F4D64` FOREIGN KEY (`last_modified_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FKA4FAA1F351A3A90E` FOREIGN KEY (`created_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FKA4FAA1F37D2B7913` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menu_item`
--


/*!40000 ALTER TABLE `menu_item` DISABLE KEYS */;
LOCK TABLES `menu_item` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `menu_item` ENABLE KEYS */;

--
-- Table structure for table `newborneval`
--

DROP TABLE IF EXISTS `newborneval`;
CREATE TABLE `newborneval` (
  `id` bigint(20) NOT NULL default '0',
  `date_of_birth` date default NULL,
  `time_of_birth_1514` time default NULL,
  `sequence_num_489` int(11) default NULL,
  `if_premature_num_weeks_gest_488` int(11) default NULL,
  `weight_at_birth_491` float default NULL,
  `trauma_492` int(11) default NULL,
  `trauma_other_1192` text,
  `alive_sb_493` int(11) default NULL,
  `if_sb_cause_494` text,
  `neonatal_dea_1180` tinyint(1) default NULL,
  `neonatal_death_age_hours_497` int(11) default NULL,
  `neonatal_death_cause_495` int(11) default NULL,
  `neonatal_death_cause_desc_496` text,
  `apgar_score_1_min_498` int(11) default NULL,
  `apgar_score_5_min_504` int(11) default NULL,
  `apgar_score_10_min_510` int(11) default NULL,
  `tactile` tinyint(1) default NULL,
  `tactile_results` text,
  `suction` tinyint(1) default NULL,
  `suction_results` text,
  `oxygen` tinyint(1) default NULL,
  `oxygen_results` text,
  `intubation` tinyint(1) default NULL,
  `intubation_results` text,
  `ephinephrine` tinyint(1) default NULL,
  `ephinephrine_results` text,
  `naxolone` tinyint(1) default NULL,
  `naxolone_results` text,
  `atropine` tinyint(1) default NULL,
  `atropine_results` text,
  `bag_and_mask` tinyint(1) default NULL,
  `bag_and_mask_results` text,
  `sodium_bcarbonate` tinyint(1) default NULL,
  `sodium_bcarbonate_results` text,
  `other_drugs` tinyint(1) default NULL,
  `other_drugs_results` text,
  `breast_feeding_well_518` tinyint(1) default NULL,
  `used_formula_for_feeding` tinyint(1) default NULL,
  `jaundice_519` tinyint(1) default NULL,
  `good_head_control_520` tinyint(1) default NULL,
  `good_grasp_reflex_521` int(11) default NULL,
  `symmetrical_moro_522` int(11) default NULL,
  `eyes_523` int(11) default NULL,
  `eyes_other_524` text,
  `if_eyes_abnormal_treatment_525` int(11) default NULL,
  `mouth_526` int(11) default NULL,
  `mouth_other_527` text,
  `sucking_528` int(11) default NULL,
  `genitalia_529` int(11) default NULL,
  `genitalia_other_697` text,
  `crown_heel_length_532` int(11) default NULL,
  `urine_passed_1181` tinyint(1) default NULL,
  `bowel_movement_535` tinyint(1) default NULL,
  `back_536` int(11) default NULL,
  `back_other_537` text,
  `upper_extrem_538` int(11) default NULL,
  `upper_extrem_desc_539` text,
  `ortolani_sign_540` int(11) default NULL,
  `lower_extrem_541` int(11) default NULL,
  `lower_extrem_desc_542` text,
  `anomalies_cns_543` int(11) default NULL,
  `anomalies_cns_desc_544` text,
  `anomalies_chromo_545` int(11) default NULL,
  `anomalies_chromo_desc_546` text,
  `anomalies_cardio_547` int(11) default NULL,
  `anomalies_cardio_desc_548` text,
  `anomalies_genitour_549` int(11) default NULL,
  `anomalies_genitour_desc_550` text,
  `anomalies_other_551` int(11) default NULL,
  `anomalies_ohter_1189` text,
  `ega_129` int(11) default NULL,
  `immunization_554` int(11) default NULL,
  `immunization_1` int(11) default NULL,
  `immunization_2` int(11) default NULL,
  `immunization_3` int(11) default NULL,
  `immunization_4` int(11) default NULL,
  `immunization_5` int(11) default NULL,
  `immunisation_desc_556` text,
  `dosage_nevirapine_555` int(11) default NULL,
  `weight_on_3rd_day_558` float default NULL,
  `problems_comments_557` text,
  `place_of_next_visit_1216` varchar(255) default NULL,
  `birth_record_given_561` tinyint(1) default NULL,
  `treatment_on_discharge_562` varchar(255) default NULL,
  `other_comments_563` varchar(255) default NULL,
  `first_postnatal_visit_date_564` date default NULL,
  `first_postnatal_visit_place_565` int(11) default NULL,
  `date_of_discharge_1268` date default NULL,
  `place_of_next_visit_1213` varchar(255) default NULL,
  `receives_vitamine_k` tinyint(1) default NULL,
  `receives_tetracycline` tinyint(1) default NULL,
  `sex_490` int(11) default NULL,
  `baby_received_arv` int(11) default NULL,
  `born_at_home` tinyint(1) default NULL,
  `cord_complic_789` int(11) default NULL,
  `feeding` int(11) default NULL,
  `feeding_type` int(11) default NULL,
  `cord_evaluation` int(11) default NULL,
  `estimate_weight` tinyint(1) default NULL,
  `head_circumf_530` float default NULL,
  `chest_circum_531` float default NULL,
  `initial_nevirapine_dose` tinyint(4) default NULL,
  `rbd_home_regimen` int(11) default NULL,
  `rbd_home_dosage` int(11) default NULL,
  `general_examination` int(11) default NULL,
  `ega_weeks` int(11) default NULL,
  `skin_176` int(11) default NULL,
  `skin_700` int(11) default NULL,
  `head_ears_nose_throat` int(11) default NULL,
  `hent_abnormality` varchar(255) default NULL,
  `respiratory_system_167` int(11) default NULL,
  `respiratory_system_other` varchar(255) default NULL,
  `cardovascular_examination` int(11) default NULL,
  `cardiovascular_abnormality` varchar(255) default NULL,
  `abdominal_exam` int(11) default NULL,
  `congenital_malformations` tinyint(4) default NULL,
  `congenital_malformations_system` int(11) default NULL,
  `abnormality_crano_facial` int(11) default NULL,
  `skin_other_701` varchar(255) default NULL,
  `abdominal_abnormality` varchar(255) default NULL,
  `congen_malform_system1` int(11) default NULL,
  `congen_malform_system2` int(11) default NULL,
  `congen_malform_system3` int(11) default NULL,
  `congen_malform_system4` int(11) default NULL,
  `congen_malform_system5` int(11) default NULL,
  `congen_malform_system6` int(11) default NULL,
  `congen_malform_system7` int(11) default NULL,
  `congen_malform_system8` int(11) default NULL,
  `anomalies_pulmonary` int(11) default NULL,
  `anomalies_pulmonary_comment` varchar(255) default NULL,
  `anomalies_musculoskeletal` int(11) default NULL,
  `anomalies_musculoskeletal_comment` varchar(255) default NULL,
  `anomalies_gastrointestinal` int(11) default NULL,
  `anomalies_gastrointestinal_comment` varchar(255) default NULL,
  `anomalies_orofacial` int(11) default NULL,
  `anomalies_orofacial_comment` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKC3DB2905D1B` (`id`),
  CONSTRAINT `FKC3DB2905D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `newborneval`
--


/*!40000 ALTER TABLE `newborneval` DISABLE KEYS */;
LOCK TABLES `newborneval` WRITE;
INSERT INTO `newborneval` VALUES (40,'2007-08-14','09:44:36',1,NULL,2.3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `newborneval` ENABLE KEYS */;

--
-- Table structure for table `newbornrecord`
--

DROP TABLE IF EXISTS `newbornrecord`;
CREATE TABLE `newbornrecord` (
  `id` bigint(20) NOT NULL default '0',
  `date_of_birth` date default NULL,
  `time_of_birth_1514` time default NULL,
  `sequence_num_489` int(11) default NULL,
  `sex_490` tinyint(4) default NULL,
  `weight_at_birth_491` float default NULL,
  `ega_weeks` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKnewbornrecord` (`id`),
  CONSTRAINT `FKnewbornrecord` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `newbornrecord`
--


/*!40000 ALTER TABLE `newbornrecord` DISABLE KEYS */;
LOCK TABLES `newbornrecord` WRITE;
INSERT INTO `newbornrecord` VALUES (15,'2007-08-14','09:43:20',1,1,3.5,18),(39,'2007-08-14','09:44:36',1,1,2.3,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `newbornrecord` ENABLE KEYS */;

--
-- Table structure for table `nicusummary`
--

DROP TABLE IF EXISTS `nicusummary`;
CREATE TABLE `nicusummary` (
  `id` bigint(20) NOT NULL default '0',
  `infant_outcome_820` int(11) default NULL,
  `if_death_probable_cause_821` int(11) default NULL,
  `other_death_823` text,
  `if_death_premature_weeks_822` int(11) default NULL,
  `autopsy_consent_599` int(11) default NULL,
  `discharge_826` int(11) default NULL,
  `place_infant_discharged` int(11) default NULL,
  `x_ray` tinyint(1) default NULL,
  `x_ray_desc` text,
  `baby_ultrasound` tinyint(1) default NULL,
  `baby_ultrasound_describe` text,
  `echo_cardiogram` tinyint(1) default NULL,
  `echo_cardiogram_describe` text,
  `diagnosis_at_discharge_827` int(11) default NULL,
  `diagnosis_at_discharge_1185` text,
  `follow_up_co_829` text,
  `birth_record_given_561` tinyint(1) default NULL,
  `treatment_on_discharge_562` text,
  `other_comments_563` text,
  `first_postnatal_visit_date_564` date default NULL,
  `first_postnatal_visit_place_565` int(11) default NULL,
  `date_of_discharge_1268` date default NULL,
  `place_of_next_visit_1213` varchar(255) default NULL,
  `receives_vitamine_k` tinyint(1) default NULL,
  `receives_tetracycline` tinyint(1) default NULL,
  `investigations` text,
  `oxygen_not_ventilated` tinyint(1) default NULL,
  `mechanical_ventilation` tinyint(1) default NULL,
  `antibiotics` tinyint(1) default NULL,
  `nevirapine` tinyint(1) default NULL,
  `pressors` tinyint(1) default NULL,
  `intravenous_fluids` tinyint(1) default NULL,
  `umbilical_artery_catheter` tinyint(1) default NULL,
  `xs_transfusion` tinyint(1) default NULL,
  `phototherapy` tinyint(1) default NULL,
  `vitamin_k` tinyint(1) default NULL,
  `treatment_other` tinyint(1) default NULL,
  `treatment_other_desc_818` text,
  `antibics_used_1184` text,
  `patient_received_arv` tinyint(4) default NULL,
  `referring_encounter_id` int(11) default NULL,
  `problems_comments_557` text,
  PRIMARY KEY  (`id`),
  KEY `FK8E0B4B99D1B` (`id`),
  CONSTRAINT `FK8E0B4B99D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nicusummary`
--


/*!40000 ALTER TABLE `nicusummary` DISABLE KEYS */;
LOCK TABLES `nicusummary` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `nicusummary` ENABLE KEYS */;

--
-- Table structure for table `nicusummaryadm`
--

DROP TABLE IF EXISTS `nicusummaryadm`;
CREATE TABLE `nicusummaryadm` (
  `id` bigint(20) NOT NULL default '0',
  `investigations` text,
  `oxygen_not_ventilated` tinyint(1) default NULL,
  `mechanical_ventilation` tinyint(1) default NULL,
  `antibiotics` tinyint(1) default NULL,
  `nevirapine` tinyint(1) default NULL,
  `pressors` tinyint(1) default NULL,
  `intravenous_fluids` tinyint(1) default NULL,
  `umbilical_artery_catheter` tinyint(1) default NULL,
  `xs_transfusion` tinyint(1) default NULL,
  `phototherapy` tinyint(1) default NULL,
  `vitamin_k` tinyint(1) default NULL,
  `treatment_other` tinyint(1) default NULL,
  `treatment_other_desc_818` text,
  `antibics_used_1184` text,
  PRIMARY KEY  (`id`),
  KEY `FKD473DA71D1B` (`id`),
  CONSTRAINT `FKD473DA71D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nicusummaryadm`
--


/*!40000 ALTER TABLE `nicusummaryadm` DISABLE KEYS */;
LOCK TABLES `nicusummaryadm` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `nicusummaryadm` ENABLE KEYS */;

--
-- Table structure for table `outcome`
--

DROP TABLE IF EXISTS `outcome`;
CREATE TABLE `outcome` (
  `id` bigint(20) NOT NULL auto_increment,
  `active` tinyint(1) default NULL,
  `encounter_id` bigint(20) default NULL,
  `patient_id` bigint(20) default NULL,
  `last_modified` datetime default NULL,
  `created` datetime default NULL,
  `last_modified_by` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `site_id` bigint(20) default NULL,
  `pregnancy_id` bigint(20) default NULL,
  `form_id` bigint(20) default NULL,
  `form_field_id` bigint(20) default NULL,
  `rule_definition_id` bigint(20) default NULL,
  `message` varchar(255) default NULL,
  `required_form_id` int(11) default NULL,
  `reason` varchar(255) default NULL,
  `priority_of_referral` varchar(255) default NULL,
  `transport` varchar(255) default NULL,
  `outcome_type` varchar(255) default NULL,
  `uth_admission_date` datetime default NULL,
  `arrival_condition` varchar(255) default NULL,
  `uth_ward_id` int(11) default NULL,
  `referring_site_id` bigint(20) default NULL,
  `firm` char(1) default NULL,
  `import_outcome_id` bigint(20) default NULL,
  `import_encounter_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKBE0C07527D2B7913` (`site_id`),
  KEY `FKBE0C075251A3A90E` (`created_by`),
  KEY `FKBE0C07522370976D` (`pregnancy_id`),
  KEY `FKBE0C07523E8F4D64` (`last_modified_by`),
  KEY `outencindex` (`encounter_id`),
  CONSTRAINT `FKBE0C07522370976D` FOREIGN KEY (`pregnancy_id`) REFERENCES `pregnancy` (`id`),
  CONSTRAINT `FKBE0C07523E8F4D64` FOREIGN KEY (`last_modified_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FKBE0C075251A3A90E` FOREIGN KEY (`created_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FKBE0C07527D2B7913` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `outcome`
--


/*!40000 ALTER TABLE `outcome` DISABLE KEYS */;
LOCK TABLES `outcome` WRITE;
INSERT INTO `outcome` VALUES (1,1,4,1,'2007-08-14 09:31:51','2007-08-14 09:31:51','zepadmin','zepadmin',1,1,70,74,18,'Hypertension: Medical/Surgical History - Consider referral to UTH.',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(2,1,5,1,'2007-08-14 09:31:58','2007-08-14 09:31:58','zepadmin','zepadmin',1,1,55,1136,100,'Patient is currently taking medicine.',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(3,1,6,1,'2007-08-14 09:32:36','2007-08-14 09:32:36','zepadmin','zepadmin',1,1,80,230,128,'Abnormal Foetal heart rate (not in 120-160 range): Consider referral to UTH.',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(4,1,6,1,'2007-08-14 09:32:36','2007-08-14 09:32:36','zepadmin','zepadmin',1,1,80,229,9,'Foetal Heart is Irregular: Consider referral to UTH',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(5,1,6,1,'2007-08-14 09:32:36','2007-08-14 09:32:36','zepadmin','zepadmin',1,1,80,224,95,'Systolic blood pressure is >= 150: Consider referral to UTH',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(6,1,6,1,'2007-08-14 09:32:36','2007-08-14 09:32:36','zepadmin','zepadmin',1,1,80,225,12,'Diastolic blood pressure is >= 110: Consider referral to UTH',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(7,1,6,1,'2007-08-14 09:32:36','2007-08-14 09:32:36','zepadmin','zepadmin',1,1,80,225,104,'BP Diastolic >=90 (Proteinurea) & 2+ oedema',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(8,1,9,1,'2007-08-14 09:33:41','2007-08-14 09:33:41','zepadmin','zepadmin',1,1,91,1866,70,'Lab Result: Reactive',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(9,1,10,1,'2007-08-14 09:34:50','2007-08-14 09:34:50','zepadmin','zepadmin',1,1,87,2004,118,'CD4 less than 200: Refer Urgently to ART clinic.',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(10,1,20,3,'2007-08-14 09:44:18','2007-08-14 09:44:18','zepadmin','zepadmin',1,2,70,74,18,'Hypertension: Medical/Surgical History - Consider referral to UTH.',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(11,1,20,3,'2007-08-14 09:44:18','2007-08-14 09:44:18','zepadmin','zepadmin',1,2,70,1927,106,'Patient had a C/S delivery: Consider referral to UTH.',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(12,1,23,3,'2007-08-14 09:44:18','2007-08-14 09:44:18','zepadmin','zepadmin',1,2,80,224,95,'Systolic blood pressure is >= 150: Consider referral to UTH',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(13,1,23,3,'2007-08-14 09:44:18','2007-08-14 09:44:18','zepadmin','zepadmin',1,2,80,244,71,'Lab Results: Urinalysis Albumin >= 1+',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(14,1,23,3,'2007-08-14 09:44:18','2007-08-14 09:44:18','zepadmin','zepadmin',1,2,80,242,72,'Lab Results: Urinalysis Glucose > 1+.',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(15,1,23,3,'2007-08-14 09:44:18','2007-08-14 09:44:18','zepadmin','zepadmin',1,2,80,242,76,'Urinalysis Acetone >= 2+ - Need to request diabetes test.',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(16,1,23,3,'2007-08-14 09:44:18','2007-08-14 09:44:18','zepadmin','zepadmin',1,2,80,243,78,'Urinalysis Protein >= 2+ - Need to request diabetes test.',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(17,1,23,3,'2007-08-14 09:44:18','2007-08-14 09:44:18','zepadmin','zepadmin',1,2,80,243,79,'Urinalysis Protein >= 2+ - Need to request diabetes test.',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(18,1,24,3,'2007-08-14 09:44:18','2007-08-14 09:44:18','zepadmin','zepadmin',1,2,77,159,6,'Small stature (<= 150 cm) : Consider referral to UTH.',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(19,1,24,3,'2007-08-14 09:44:18','2007-08-14 09:44:18','zepadmin','zepadmin',1,2,77,266,16,'Temperature is above 37.5.',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(20,1,24,3,'2007-08-14 09:44:18','2007-08-14 09:44:18','zepadmin','zepadmin',1,2,77,165,7,'Consider referral to UTH if Thyroid is not \"Normal\"',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(21,1,29,4,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,3,70,74,18,'Hypertension: Medical/Surgical History - Consider referral to UTH.',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(22,1,29,4,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,3,70,1927,106,'Patient had a C/S delivery: Consider referral to UTH.',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(23,1,31,4,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,3,90,1563,69,'RPR Result: Positive (reactive)',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(24,1,32,4,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,3,80,224,95,'Systolic blood pressure is >= 150: Consider referral to UTH',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(25,1,32,4,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,3,80,244,71,'Lab Results: Urinalysis Albumin >= 1+',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(26,1,32,4,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,3,80,242,72,'Lab Results: Urinalysis Glucose > 1+.',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(27,1,32,4,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,3,80,242,76,'Urinalysis Acetone >= 2+ - Need to request diabetes test.',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(28,1,32,4,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,3,80,243,78,'Urinalysis Protein >= 2+ - Need to request diabetes test.',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(29,1,32,4,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,3,80,243,79,'Urinalysis Protein >= 2+ - Need to request diabetes test.',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(30,1,33,4,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,3,77,159,6,'Small stature (<= 150 cm) : Consider referral to UTH.',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(31,1,33,4,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,3,77,266,16,'Temperature is above 37.5.',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(32,1,33,4,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,3,77,165,7,'Consider referral to UTH if Thyroid is not \"Normal\"',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(33,1,36,4,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,3,91,1866,70,'Lab Result: Reactive',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(34,1,42,4,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,3,81,235,29,'Hb Lab Results: Hb below 10',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(35,1,44,4,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,3,28,235,29,'Hb Lab Results: Hb below 10',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(36,1,45,3,'2007-08-14 09:59:40','2007-08-14 09:59:40','zepadmin','zepadmin',1,2,65,159,6,'Small stature (<= 150 cm) : Consider referral to UTH.',NULL,NULL,NULL,NULL,'info',NULL,NULL,NULL,1,NULL,NULL,NULL),(37,1,45,3,'2007-08-14 09:59:40','2007-08-14 09:59:40','zepadmin','zepadmin',1,2,65,1507,50,NULL,NULL,'Problem or Labor Referral','Immediate','Minibus','referral',NULL,NULL,NULL,1,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `outcome` ENABLE KEYS */;

--
-- Table structure for table `outcome_archive`
--

DROP TABLE IF EXISTS `outcome_archive`;
CREATE TABLE `outcome_archive` (
  `id` bigint(20) NOT NULL default '0',
  `active` tinyint(1) default NULL,
  `encounter_id` bigint(20) default NULL,
  `patient_id` bigint(20) default NULL,
  `last_modified` datetime default NULL,
  `created` datetime default NULL,
  `last_modified_by` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `site_id` bigint(20) default NULL,
  `pregnancy_id` bigint(20) default NULL,
  `form_id` bigint(20) default NULL,
  `form_field_id` bigint(20) default NULL,
  `rule_definition_id` bigint(20) default NULL,
  `message` varchar(255) default NULL,
  `required_form_id` int(11) default NULL,
  `reason` varchar(255) default NULL,
  `priority_of_referral` varchar(255) default NULL,
  `transport` varchar(255) default NULL,
  `outcome_type` varchar(255) default NULL,
  `uth_admission_date` datetime default NULL,
  `arrival_condition` varchar(255) default NULL,
  `uth_ward_id` int(11) default NULL,
  `referring_site_id` bigint(20) default NULL,
  `firm` char(1) default NULL,
  `import_outcome_id` bigint(20) default NULL,
  `import_encounter_id` bigint(20) default NULL,
  `date_deleted` datetime default NULL,
  `deleted_by` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `outcome_archive`
--


/*!40000 ALTER TABLE `outcome_archive` DISABLE KEYS */;
LOCK TABLES `outcome_archive` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `outcome_archive` ENABLE KEYS */;

--
-- Table structure for table `parto_blood_pressure`
--

DROP TABLE IF EXISTS `parto_blood_pressure`;
CREATE TABLE `parto_blood_pressure` (
  `id` bigint(20) NOT NULL auto_increment,
  `patient_id` bigint(20) default NULL,
  `pregnancy_id` bigint(20) default NULL,
  `date_visit` date NOT NULL default '0000-00-00',
  `last_modified` datetime default NULL,
  `created` datetime default NULL,
  `last_modified_by` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `site_id` bigint(20) default NULL,
  `time_1` time default NULL,
  `systolic_1` int(11) default NULL,
  `diastolic_1` int(11) default NULL,
  `time_2` time default NULL,
  `systolic_2` int(11) default NULL,
  `diastolic_2` int(11) default NULL,
  `time_3` time default NULL,
  `systolic_3` int(11) default NULL,
  `diastolic_3` int(11) default NULL,
  `time_4` time default NULL,
  `systolic_4` int(11) default NULL,
  `diastolic_4` int(11) default NULL,
  `time_5` time default NULL,
  `systolic_5` int(11) default NULL,
  `diastolic_5` int(11) default NULL,
  `time_6` time default NULL,
  `systolic_6` int(11) default NULL,
  `diastolic_6` int(11) default NULL,
  `time_7` time default NULL,
  `systolic_7` int(11) default NULL,
  `diastolic_7` int(11) default NULL,
  `time_8` time default NULL,
  `systolic_8` int(11) default NULL,
  `diastolic_8` int(11) default NULL,
  `time_9` time default NULL,
  `systolic_9` int(11) default NULL,
  `diastolic_9` int(11) default NULL,
  `time_10` time default NULL,
  `systolic_10` int(11) default NULL,
  `diastolic_10` int(11) default NULL,
  `time_11` time default NULL,
  `systolic_11` int(11) default NULL,
  `diastolic_11` int(11) default NULL,
  `time_12` time default NULL,
  `systolic_12` int(11) default NULL,
  `diastolic_12` int(11) default NULL,
  `time_13` time default NULL,
  `systolic_13` int(11) default NULL,
  `diastolic_13` int(11) default NULL,
  `time_14` time default NULL,
  `systolic_14` int(11) default NULL,
  `diastolic_14` int(11) default NULL,
  `time_15` time default NULL,
  `systolic_15` int(11) default NULL,
  `diastolic_15` int(11) default NULL,
  `time_16` time default NULL,
  `systolic_16` int(11) default NULL,
  `diastolic_16` int(11) default NULL,
  `time_17` time default NULL,
  `systolic_17` int(11) default NULL,
  `diastolic_17` int(11) default NULL,
  `time_18` time default NULL,
  `systolic_18` int(11) default NULL,
  `diastolic_18` int(11) default NULL,
  `time_19` time default NULL,
  `systolic_19` int(11) default NULL,
  `diastolic_19` int(11) default NULL,
  `time_20` time default NULL,
  `systolic_20` int(11) default NULL,
  `diastolic_20` int(11) default NULL,
  `time_21` time default NULL,
  `systolic_21` int(11) default NULL,
  `diastolic_21` int(11) default NULL,
  `time_22` time default NULL,
  `systolic_22` int(11) default NULL,
  `diastolic_22` int(11) default NULL,
  `time_23` time default NULL,
  `systolic_23` int(11) default NULL,
  `diastolic_23` int(11) default NULL,
  `time_24` time default NULL,
  `systolic_24` int(11) default NULL,
  `diastolic_24` int(11) default NULL,
  `time_25` time default NULL,
  `systolic_25` int(11) default NULL,
  `diastolic_25` int(11) default NULL,
  `time_26` time default NULL,
  `systolic_26` int(11) default NULL,
  `diastolic_26` int(11) default NULL,
  `time_27` time default NULL,
  `systolic_27` int(11) default NULL,
  `diastolic_27` int(11) default NULL,
  `time_28` time default NULL,
  `systolic_28` int(11) default NULL,
  `diastolic_28` int(11) default NULL,
  `time_29` time default NULL,
  `systolic_29` int(11) default NULL,
  `diastolic_29` int(11) default NULL,
  `time_30` time default NULL,
  `systolic_30` int(11) default NULL,
  `diastolic_30` int(11) default NULL,
  `time_31` time default NULL,
  `systolic_31` int(11) default NULL,
  `diastolic_31` int(11) default NULL,
  `time_32` time default NULL,
  `systolic_32` int(11) default NULL,
  `diastolic_32` int(11) default NULL,
  `time_33` time default NULL,
  `systolic_33` int(11) default NULL,
  `diastolic_33` int(11) default NULL,
  `time_34` time default NULL,
  `systolic_34` int(11) default NULL,
  `diastolic_34` int(11) default NULL,
  `import_id` bigint(20) default NULL,
  `time_35` time default NULL,
  `systolic_35` int(11) default NULL,
  `diastolic_35` int(11) default NULL,
  `time_36` time default NULL,
  `systolic_36` int(11) default NULL,
  `diastolic_36` int(11) default NULL,
  `time_37` time default NULL,
  `systolic_37` int(11) default NULL,
  `diastolic_37` int(11) default NULL,
  `time_38` time default NULL,
  `systolic_38` int(11) default NULL,
  `diastolic_38` int(11) default NULL,
  `time_39` time default NULL,
  `systolic_39` int(11) default NULL,
  `diastolic_39` int(11) default NULL,
  `time_40` time default NULL,
  `systolic_40` int(11) default NULL,
  `diastolic_40` int(11) default NULL,
  `time_41` time default NULL,
  `systolic_41` int(11) default NULL,
  `diastolic_41` int(11) default NULL,
  `time_42` time default NULL,
  `systolic_42` int(11) default NULL,
  `diastolic_42` int(11) default NULL,
  `time_43` time default NULL,
  `systolic_43` int(11) default NULL,
  `diastolic_43` int(11) default NULL,
  `time_44` time default NULL,
  `systolic_44` int(11) default NULL,
  `diastolic_44` int(11) default NULL,
  `time_45` time default NULL,
  `systolic_45` int(11) default NULL,
  `diastolic_45` int(11) default NULL,
  `time_46` time default NULL,
  `systolic_46` int(11) default NULL,
  `diastolic_46` int(11) default NULL,
  `time_47` time default NULL,
  `systolic_47` int(11) default NULL,
  `diastolic_47` int(11) default NULL,
  `time_48` time default NULL,
  `systolic_48` int(11) default NULL,
  `diastolic_48` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKA2595AAD8523EC95` (`patient_id`),
  KEY `FKA2595AAD7D2B7913` (`site_id`),
  KEY `FKA2595AAD51A3A90E` (`created_by`),
  KEY `FKA2595AAD3E8F4D64` (`last_modified_by`),
  KEY `FKA2595AAD2370976D` (`pregnancy_id`),
  CONSTRAINT `FKA2595AAD2370976D` FOREIGN KEY (`pregnancy_id`) REFERENCES `pregnancy` (`id`),
  CONSTRAINT `FKA2595AAD3E8F4D64` FOREIGN KEY (`last_modified_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FKA2595AAD51A3A90E` FOREIGN KEY (`created_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FKA2595AAD7D2B7913` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`),
  CONSTRAINT `FKA2595AAD8523EC95` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `parto_blood_pressure`
--


/*!40000 ALTER TABLE `parto_blood_pressure` DISABLE KEYS */;
LOCK TABLES `parto_blood_pressure` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `parto_blood_pressure` ENABLE KEYS */;

--
-- Table structure for table `parto_cervix`
--

DROP TABLE IF EXISTS `parto_cervix`;
CREATE TABLE `parto_cervix` (
  `id` bigint(20) NOT NULL auto_increment,
  `patient_id` bigint(20) default NULL,
  `pregnancy_id` bigint(20) default NULL,
  `date_visit` date NOT NULL default '0000-00-00',
  `last_modified` datetime default NULL,
  `created` datetime default NULL,
  `last_modified_by` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `site_id` bigint(20) default NULL,
  `time_1` time default NULL,
  `cervix_1` int(11) default NULL,
  `time_2` time default NULL,
  `cervix_2` int(11) default NULL,
  `time_3` time default NULL,
  `cervix_3` int(11) default NULL,
  `time_4` time default NULL,
  `cervix_4` int(11) default NULL,
  `time_5` time default NULL,
  `cervix_5` int(11) default NULL,
  `time_6` time default NULL,
  `cervix_6` int(11) default NULL,
  `time_7` time default NULL,
  `cervix_7` int(11) default NULL,
  `time_8` time default NULL,
  `cervix_8` int(11) default NULL,
  `time_9` time default NULL,
  `cervix_9` int(11) default NULL,
  `time_10` time default NULL,
  `cervix_10` int(11) default NULL,
  `time_11` time default NULL,
  `cervix_11` int(11) default NULL,
  `time_12` time default NULL,
  `cervix_12` int(11) default NULL,
  `time_13` time default NULL,
  `cervix_13` int(11) default NULL,
  `time_14` time default NULL,
  `cervix_14` int(11) default NULL,
  `time_15` time default NULL,
  `cervix_15` int(11) default NULL,
  `time_16` time default NULL,
  `cervix_16` int(11) default NULL,
  `time_17` time default NULL,
  `cervix_17` int(11) default NULL,
  `import_id` bigint(20) default NULL,
  `time_18` time default NULL,
  `cervix_18` int(11) default NULL,
  `time_19` time default NULL,
  `cervix_19` int(11) default NULL,
  `time_20` time default NULL,
  `cervix_20` int(11) default NULL,
  `time_21` time default NULL,
  `cervix_21` int(11) default NULL,
  `time_22` time default NULL,
  `cervix_22` int(11) default NULL,
  `time_23` time default NULL,
  `cervix_23` int(11) default NULL,
  `time_24` time default NULL,
  `cervix_24` int(11) default NULL,
  `action_start_row` int(11) default NULL,
  `action_start_col` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK1ADDD8982370976D` (`pregnancy_id`),
  KEY `FK1ADDD89851A3A90E` (`created_by`),
  KEY `FK1ADDD8987D2B7913` (`site_id`),
  KEY `FK1ADDD8983E8F4D64` (`last_modified_by`),
  KEY `FK1ADDD8988523EC95` (`patient_id`),
  CONSTRAINT `FK1ADDD8982370976D` FOREIGN KEY (`pregnancy_id`) REFERENCES `pregnancy` (`id`),
  CONSTRAINT `FK1ADDD8983E8F4D64` FOREIGN KEY (`last_modified_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FK1ADDD89851A3A90E` FOREIGN KEY (`created_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FK1ADDD8987D2B7913` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`),
  CONSTRAINT `FK1ADDD8988523EC95` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `parto_cervix`
--


/*!40000 ALTER TABLE `parto_cervix` DISABLE KEYS */;
LOCK TABLES `parto_cervix` WRITE;
INSERT INTO `parto_cervix` VALUES (1,1,1,'2007-08-14','2007-08-14 09:43:08','2007-08-14 09:43:08','zepadmin','zepadmin',1,'09:43:08',6,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,6,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `parto_cervix` ENABLE KEYS */;

--
-- Table structure for table `parto_contractions`
--

DROP TABLE IF EXISTS `parto_contractions`;
CREATE TABLE `parto_contractions` (
  `id` bigint(20) NOT NULL auto_increment,
  `patient_id` bigint(20) default NULL,
  `pregnancy_id` bigint(20) default NULL,
  `date_visit` date NOT NULL default '0000-00-00',
  `last_modified` datetime default NULL,
  `created` datetime default NULL,
  `last_modified_by` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `site_id` bigint(20) default NULL,
  `time_1` time default NULL,
  `contractions_1` varchar(10) default NULL,
  `time_2` time default NULL,
  `contractions_2` varchar(10) default NULL,
  `time_3` time default NULL,
  `contractions_3` varchar(10) default NULL,
  `time_4` time default NULL,
  `contractions_4` varchar(10) default NULL,
  `time_5` time default NULL,
  `contractions_5` varchar(10) default NULL,
  `time_6` time default NULL,
  `contractions_6` varchar(10) default NULL,
  `time_7` time default NULL,
  `contractions_7` varchar(10) default NULL,
  `time_8` time default NULL,
  `contractions_8` varchar(10) default NULL,
  `time_9` time default NULL,
  `contractions_9` varchar(10) default NULL,
  `time_10` time default NULL,
  `contractions_10` varchar(10) default NULL,
  `time_11` time default NULL,
  `contractions_11` varchar(10) default NULL,
  `time_12` time default NULL,
  `contractions_12` varchar(10) default NULL,
  `time_13` time default NULL,
  `contractions_13` varchar(10) default NULL,
  `time_14` time default NULL,
  `contractions_14` varchar(10) default NULL,
  `time_15` time default NULL,
  `contractions_15` varchar(10) default NULL,
  `time_16` time default NULL,
  `contractions_16` varchar(10) default NULL,
  `time_17` time default NULL,
  `contractions_17` varchar(10) default NULL,
  `time_18` time default NULL,
  `contractions_18` varchar(10) default NULL,
  `time_19` time default NULL,
  `contractions_19` varchar(10) default NULL,
  `time_20` time default NULL,
  `contractions_20` varchar(10) default NULL,
  `time_21` time default NULL,
  `contractions_21` varchar(10) default NULL,
  `time_22` time default NULL,
  `contractions_22` varchar(10) default NULL,
  `time_23` time default NULL,
  `contractions_23` varchar(10) default NULL,
  `time_24` time default NULL,
  `contractions_24` varchar(10) default NULL,
  `time_25` time default NULL,
  `contractions_25` varchar(10) default NULL,
  `time_26` time default NULL,
  `contractions_26` varchar(10) default NULL,
  `time_27` time default NULL,
  `contractions_27` varchar(10) default NULL,
  `time_28` time default NULL,
  `contractions_28` varchar(10) default NULL,
  `time_29` time default NULL,
  `contractions_29` varchar(10) default NULL,
  `time_30` time default NULL,
  `contractions_30` varchar(10) default NULL,
  `time_31` time default NULL,
  `contractions_31` varchar(10) default NULL,
  `time_32` time default NULL,
  `contractions_32` varchar(10) default NULL,
  `time_33` time default NULL,
  `contractions_33` varchar(10) default NULL,
  `time_34` time default NULL,
  `contractions_34` varchar(10) default NULL,
  `import_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKD8CE76008523EC95` (`patient_id`),
  KEY `FKD8CE76007D2B7913` (`site_id`),
  KEY `FKD8CE760051A3A90E` (`created_by`),
  KEY `FKD8CE76003E8F4D64` (`last_modified_by`),
  KEY `FKD8CE76002370976D` (`pregnancy_id`),
  CONSTRAINT `FKD8CE76002370976D` FOREIGN KEY (`pregnancy_id`) REFERENCES `pregnancy` (`id`),
  CONSTRAINT `FKD8CE76003E8F4D64` FOREIGN KEY (`last_modified_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FKD8CE760051A3A90E` FOREIGN KEY (`created_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FKD8CE76007D2B7913` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`),
  CONSTRAINT `FKD8CE76008523EC95` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `parto_contractions`
--


/*!40000 ALTER TABLE `parto_contractions` DISABLE KEYS */;
LOCK TABLES `parto_contractions` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `parto_contractions` ENABLE KEYS */;

--
-- Table structure for table `parto_descent`
--

DROP TABLE IF EXISTS `parto_descent`;
CREATE TABLE `parto_descent` (
  `id` bigint(20) NOT NULL auto_increment,
  `patient_id` bigint(20) default NULL,
  `pregnancy_id` bigint(20) default NULL,
  `date_visit` date NOT NULL default '0000-00-00',
  `last_modified` datetime default NULL,
  `created` datetime default NULL,
  `last_modified_by` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `site_id` bigint(20) default NULL,
  `time_1` time default NULL,
  `descent_1` int(11) default NULL,
  `time_2` time default NULL,
  `descent_2` int(11) default NULL,
  `time_3` time default NULL,
  `descent_3` int(11) default NULL,
  `time_4` time default NULL,
  `descent_4` int(11) default NULL,
  `time_5` time default NULL,
  `descent_5` int(11) default NULL,
  `time_6` time default NULL,
  `descent_6` int(11) default NULL,
  `time_7` time default NULL,
  `descent_7` int(11) default NULL,
  `time_8` time default NULL,
  `descent_8` int(11) default NULL,
  `time_9` time default NULL,
  `descent_9` int(11) default NULL,
  `time_10` time default NULL,
  `descent_10` int(11) default NULL,
  `time_11` time default NULL,
  `descent_11` int(11) default NULL,
  `time_12` time default NULL,
  `descent_12` int(11) default NULL,
  `time_13` time default NULL,
  `descent_13` int(11) default NULL,
  `time_14` time default NULL,
  `descent_14` int(11) default NULL,
  `time_15` time default NULL,
  `descent_15` int(11) default NULL,
  `time_16` time default NULL,
  `descent_16` int(11) default NULL,
  `time_17` time default NULL,
  `descent_17` int(11) default NULL,
  `import_id` bigint(20) default NULL,
  `time_18` time default NULL,
  `descent_18` int(11) default NULL,
  `time_19` time default NULL,
  `descent_19` int(11) default NULL,
  `time_20` time default NULL,
  `descent_20` int(11) default NULL,
  `time_21` time default NULL,
  `descent_21` int(11) default NULL,
  `time_22` time default NULL,
  `descent_22` int(11) default NULL,
  `time_23` time default NULL,
  `descent_23` int(11) default NULL,
  `time_24` time default NULL,
  `descent_24` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK75C8DA573E8F4D64` (`last_modified_by`),
  KEY `FK75C8DA577D2B7913` (`site_id`),
  KEY `FK75C8DA5751A3A90E` (`created_by`),
  KEY `FK75C8DA572370976D` (`pregnancy_id`),
  KEY `FK75C8DA578523EC95` (`patient_id`),
  CONSTRAINT `FK75C8DA572370976D` FOREIGN KEY (`pregnancy_id`) REFERENCES `pregnancy` (`id`),
  CONSTRAINT `FK75C8DA573E8F4D64` FOREIGN KEY (`last_modified_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FK75C8DA5751A3A90E` FOREIGN KEY (`created_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FK75C8DA577D2B7913` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`),
  CONSTRAINT `FK75C8DA578523EC95` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `parto_descent`
--


/*!40000 ALTER TABLE `parto_descent` DISABLE KEYS */;
LOCK TABLES `parto_descent` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `parto_descent` ENABLE KEYS */;

--
-- Table structure for table `parto_drugs_dispensed`
--

DROP TABLE IF EXISTS `parto_drugs_dispensed`;
CREATE TABLE `parto_drugs_dispensed` (
  `id` bigint(20) NOT NULL auto_increment,
  `patient_id` bigint(20) default NULL,
  `pregnancy_id` bigint(20) default NULL,
  `date_visit` date NOT NULL default '0000-00-00',
  `last_modified` datetime default NULL,
  `created` datetime default NULL,
  `last_modified_by` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `site_id` bigint(20) default NULL,
  `time_1` time default NULL,
  `drugsDispensed_1` varchar(255) default NULL,
  `time_2` time default NULL,
  `drugsDispensed_2` varchar(255) default NULL,
  `time_3` time default NULL,
  `drugsDispensed_3` varchar(255) default NULL,
  `time_4` time default NULL,
  `drugsDispensed_4` varchar(255) default NULL,
  `time_5` time default NULL,
  `drugsDispensed_5` varchar(255) default NULL,
  `time_6` time default NULL,
  `drugsDispensed_6` varchar(255) default NULL,
  `time_7` time default NULL,
  `drugsDispensed_7` varchar(255) default NULL,
  `time_8` time default NULL,
  `drugsDispensed_8` varchar(255) default NULL,
  `time_9` time default NULL,
  `drugsDispensed_9` varchar(255) default NULL,
  `time_10` time default NULL,
  `drugsDispensed_10` varchar(255) default NULL,
  `time_11` time default NULL,
  `drugsDispensed_11` varchar(255) default NULL,
  `time_12` time default NULL,
  `drugsDispensed_12` varchar(255) default NULL,
  `time_13` time default NULL,
  `drugsDispensed_13` varchar(255) default NULL,
  `time_14` time default NULL,
  `drugsDispensed_14` varchar(255) default NULL,
  `time_15` time default NULL,
  `drugsDispensed_15` varchar(255) default NULL,
  `time_16` time default NULL,
  `drugsDispensed_16` varchar(255) default NULL,
  `time_17` time default NULL,
  `drugsDispensed_17` varchar(255) default NULL,
  `import_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKFF3776D87D2B7913` (`site_id`),
  KEY `FKFF3776D83E8F4D64` (`last_modified_by`),
  KEY `FKFF3776D88523EC95` (`patient_id`),
  KEY `FKFF3776D851A3A90E` (`created_by`),
  KEY `FKFF3776D82370976D` (`pregnancy_id`),
  CONSTRAINT `FKFF3776D82370976D` FOREIGN KEY (`pregnancy_id`) REFERENCES `pregnancy` (`id`),
  CONSTRAINT `FKFF3776D83E8F4D64` FOREIGN KEY (`last_modified_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FKFF3776D851A3A90E` FOREIGN KEY (`created_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FKFF3776D87D2B7913` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`),
  CONSTRAINT `FKFF3776D88523EC95` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `parto_drugs_dispensed`
--


/*!40000 ALTER TABLE `parto_drugs_dispensed` DISABLE KEYS */;
LOCK TABLES `parto_drugs_dispensed` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `parto_drugs_dispensed` ENABLE KEYS */;

--
-- Table structure for table `parto_fetal_hr`
--

DROP TABLE IF EXISTS `parto_fetal_hr`;
CREATE TABLE `parto_fetal_hr` (
  `id` bigint(20) NOT NULL auto_increment,
  `patient_id` bigint(20) default NULL,
  `pregnancy_id` bigint(20) default NULL,
  `date_visit` date NOT NULL default '0000-00-00',
  `last_modified` datetime default NULL,
  `created` datetime default NULL,
  `last_modified_by` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `site_id` bigint(20) default NULL,
  `time_1` time default NULL,
  `fetal_hr_1` int(11) default NULL,
  `time_2` time default NULL,
  `fetal_hr_2` int(11) default NULL,
  `time_3` time default NULL,
  `fetal_hr_3` int(11) default NULL,
  `time_4` time default NULL,
  `fetal_hr_4` int(11) default NULL,
  `time_5` time default NULL,
  `fetal_hr_5` int(11) default NULL,
  `time_6` time default NULL,
  `fetal_hr_6` int(11) default NULL,
  `time_7` time default NULL,
  `fetal_hr_7` int(11) default NULL,
  `time_8` time default NULL,
  `fetal_hr_8` int(11) default NULL,
  `time_9` time default NULL,
  `fetal_hr_9` int(11) default NULL,
  `time_10` time default NULL,
  `fetal_hr_10` int(11) default NULL,
  `time_11` time default NULL,
  `fetal_hr_11` int(11) default NULL,
  `time_12` time default NULL,
  `fetal_hr_12` int(11) default NULL,
  `time_13` time default NULL,
  `fetal_hr_13` int(11) default NULL,
  `time_14` time default NULL,
  `fetal_hr_14` int(11) default NULL,
  `time_15` time default NULL,
  `fetal_hr_15` int(11) default NULL,
  `time_16` time default NULL,
  `fetal_hr_16` int(11) default NULL,
  `time_17` time default NULL,
  `fetal_hr_17` int(11) default NULL,
  `time_18` time default NULL,
  `fetal_hr_18` int(11) default NULL,
  `time_19` time default NULL,
  `fetal_hr_19` int(11) default NULL,
  `time_20` time default NULL,
  `fetal_hr_20` int(11) default NULL,
  `time_21` time default NULL,
  `fetal_hr_21` int(11) default NULL,
  `time_22` time default NULL,
  `fetal_hr_22` int(11) default NULL,
  `time_23` time default NULL,
  `fetal_hr_23` int(11) default NULL,
  `time_24` time default NULL,
  `fetal_hr_24` int(11) default NULL,
  `time_25` time default NULL,
  `fetal_hr_25` int(11) default NULL,
  `time_26` time default NULL,
  `fetal_hr_26` int(11) default NULL,
  `time_27` time default NULL,
  `fetal_hr_27` int(11) default NULL,
  `time_28` time default NULL,
  `fetal_hr_28` int(11) default NULL,
  `time_29` time default NULL,
  `fetal_hr_29` int(11) default NULL,
  `time_30` time default NULL,
  `fetal_hr_30` int(11) default NULL,
  `time_31` time default NULL,
  `fetal_hr_31` int(11) default NULL,
  `time_32` time default NULL,
  `fetal_hr_32` int(11) default NULL,
  `time_33` time default NULL,
  `fetal_hr_33` int(11) default NULL,
  `time_34` time default NULL,
  `fetal_hr_34` int(11) default NULL,
  `import_id` bigint(20) default NULL,
  `time_35` time default NULL,
  `fetal_hr_35` int(11) default NULL,
  `time_36` time default NULL,
  `fetal_hr_36` int(11) default NULL,
  `time_37` time default NULL,
  `fetal_hr_37` int(11) default NULL,
  `time_38` time default NULL,
  `fetal_hr_38` int(11) default NULL,
  `time_39` time default NULL,
  `fetal_hr_39` int(11) default NULL,
  `time_40` time default NULL,
  `fetal_hr_40` int(11) default NULL,
  `time_41` time default NULL,
  `fetal_hr_41` int(11) default NULL,
  `time_42` time default NULL,
  `fetal_hr_42` int(11) default NULL,
  `time_43` time default NULL,
  `fetal_hr_43` int(11) default NULL,
  `time_44` time default NULL,
  `fetal_hr_44` int(11) default NULL,
  `time_45` time default NULL,
  `fetal_hr_45` int(11) default NULL,
  `time_46` time default NULL,
  `fetal_hr_46` int(11) default NULL,
  `time_47` time default NULL,
  `fetal_hr_47` int(11) default NULL,
  `time_48` time default NULL,
  `fetal_hr_48` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK14B0692C7D2B7913` (`site_id`),
  KEY `FK14B0692C51A3A90E` (`created_by`),
  KEY `FK14B0692C8523EC95` (`patient_id`),
  KEY `FK14B0692C3E8F4D64` (`last_modified_by`),
  KEY `FK14B0692C2370976D` (`pregnancy_id`),
  CONSTRAINT `FK14B0692C2370976D` FOREIGN KEY (`pregnancy_id`) REFERENCES `pregnancy` (`id`),
  CONSTRAINT `FK14B0692C3E8F4D64` FOREIGN KEY (`last_modified_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FK14B0692C51A3A90E` FOREIGN KEY (`created_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FK14B0692C7D2B7913` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`),
  CONSTRAINT `FK14B0692C8523EC95` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `parto_fetal_hr`
--


/*!40000 ALTER TABLE `parto_fetal_hr` DISABLE KEYS */;
LOCK TABLES `parto_fetal_hr` WRITE;
INSERT INTO `parto_fetal_hr` VALUES (1,1,1,'2007-08-14','2007-08-14 09:43:02','2007-08-14 09:43:02','zepadmin','zepadmin',1,'09:43:02',160,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,4,3,'2007-08-14','2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,'09:44:36',160,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `parto_fetal_hr` ENABLE KEYS */;

--
-- Table structure for table `parto_liquor`
--

DROP TABLE IF EXISTS `parto_liquor`;
CREATE TABLE `parto_liquor` (
  `id` bigint(20) NOT NULL auto_increment,
  `patient_id` bigint(20) default NULL,
  `pregnancy_id` bigint(20) default NULL,
  `date_visit` date NOT NULL default '0000-00-00',
  `last_modified` datetime default NULL,
  `created` datetime default NULL,
  `last_modified_by` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `site_id` bigint(20) default NULL,
  `time_1` time default NULL,
  `liquor_1` varchar(4) default NULL,
  `time_2` time default NULL,
  `liquor_2` varchar(4) default NULL,
  `time_3` time default NULL,
  `liquor_3` varchar(4) default NULL,
  `time_4` time default NULL,
  `liquor_4` varchar(4) default NULL,
  `time_5` time default NULL,
  `liquor_5` varchar(4) default NULL,
  `time_6` time default NULL,
  `liquor_6` varchar(4) default NULL,
  `time_7` time default NULL,
  `liquor_7` varchar(4) default NULL,
  `time_8` time default NULL,
  `liquor_8` varchar(4) default NULL,
  `time_9` time default NULL,
  `liquor_9` varchar(4) default NULL,
  `time_10` time default NULL,
  `liquor_10` varchar(4) default NULL,
  `time_11` time default NULL,
  `liquor_11` varchar(4) default NULL,
  `time_12` time default NULL,
  `liquor_12` varchar(4) default NULL,
  `time_13` time default NULL,
  `liquor_13` varchar(4) default NULL,
  `time_14` time default NULL,
  `liquor_14` varchar(4) default NULL,
  `time_15` time default NULL,
  `liquor_15` varchar(4) default NULL,
  `time_16` time default NULL,
  `liquor_16` varchar(4) default NULL,
  `time_17` time default NULL,
  `liquor_17` varchar(4) default NULL,
  `time_18` time default NULL,
  `liquor_18` varchar(4) default NULL,
  `time_19` time default NULL,
  `liquor_19` varchar(4) default NULL,
  `time_20` time default NULL,
  `liquor_20` varchar(4) default NULL,
  `time_21` time default NULL,
  `liquor_21` varchar(4) default NULL,
  `time_22` time default NULL,
  `liquor_22` varchar(4) default NULL,
  `time_23` time default NULL,
  `liquor_23` varchar(4) default NULL,
  `time_24` time default NULL,
  `liquor_24` varchar(4) default NULL,
  `time_25` time default NULL,
  `liquor_25` varchar(4) default NULL,
  `time_26` time default NULL,
  `liquor_26` varchar(4) default NULL,
  `time_27` time default NULL,
  `liquor_27` varchar(4) default NULL,
  `time_28` time default NULL,
  `liquor_28` varchar(4) default NULL,
  `time_29` time default NULL,
  `liquor_29` varchar(4) default NULL,
  `time_30` time default NULL,
  `liquor_30` varchar(4) default NULL,
  `time_31` time default NULL,
  `liquor_31` varchar(4) default NULL,
  `time_32` time default NULL,
  `liquor_32` varchar(4) default NULL,
  `time_33` time default NULL,
  `liquor_33` varchar(4) default NULL,
  `time_34` time default NULL,
  `liquor_34` varchar(4) default NULL,
  `import_id` bigint(20) default NULL,
  `time_35` time default NULL,
  `liquor_35` varchar(4) default NULL,
  `time_36` time default NULL,
  `liquor_36` varchar(4) default NULL,
  `time_37` time default NULL,
  `liquor_37` varchar(4) default NULL,
  `time_38` time default NULL,
  `liquor_38` varchar(4) default NULL,
  `time_39` time default NULL,
  `liquor_39` varchar(4) default NULL,
  `time_40` time default NULL,
  `liquor_40` varchar(4) default NULL,
  `time_41` time default NULL,
  `liquor_41` varchar(4) default NULL,
  `time_42` time default NULL,
  `liquor_42` varchar(4) default NULL,
  `time_43` time default NULL,
  `liquor_43` varchar(4) default NULL,
  `time_44` time default NULL,
  `liquor_44` varchar(4) default NULL,
  `time_45` time default NULL,
  `liquor_45` varchar(4) default NULL,
  `time_46` time default NULL,
  `liquor_46` varchar(4) default NULL,
  `time_47` time default NULL,
  `liquor_47` varchar(4) default NULL,
  `time_48` time default NULL,
  `liquor_48` varchar(4) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK2A715CC78523EC95` (`patient_id`),
  KEY `FK2A715CC77D2B7913` (`site_id`),
  KEY `FK2A715CC751A3A90E` (`created_by`),
  KEY `FK2A715CC73E8F4D64` (`last_modified_by`),
  KEY `FK2A715CC72370976D` (`pregnancy_id`),
  CONSTRAINT `FK2A715CC72370976D` FOREIGN KEY (`pregnancy_id`) REFERENCES `pregnancy` (`id`),
  CONSTRAINT `FK2A715CC73E8F4D64` FOREIGN KEY (`last_modified_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FK2A715CC751A3A90E` FOREIGN KEY (`created_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FK2A715CC77D2B7913` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`),
  CONSTRAINT `FK2A715CC78523EC95` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `parto_liquor`
--


/*!40000 ALTER TABLE `parto_liquor` DISABLE KEYS */;
LOCK TABLES `parto_liquor` WRITE;
INSERT INTO `parto_liquor` VALUES (1,1,1,'2007-08-14','2007-08-14 09:43:13','2007-08-14 09:43:13','zepadmin','zepadmin',1,'09:43:13','B',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `parto_liquor` ENABLE KEYS */;

--
-- Table structure for table `parto_moulding`
--

DROP TABLE IF EXISTS `parto_moulding`;
CREATE TABLE `parto_moulding` (
  `id` bigint(20) NOT NULL auto_increment,
  `patient_id` bigint(20) default NULL,
  `pregnancy_id` bigint(20) default NULL,
  `date_visit` date NOT NULL default '0000-00-00',
  `last_modified` datetime default NULL,
  `created` datetime default NULL,
  `last_modified_by` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `site_id` bigint(20) default NULL,
  `time_1` time default NULL,
  `moulding_1` varchar(4) default NULL,
  `time_2` time default NULL,
  `moulding_2` varchar(4) default NULL,
  `time_3` time default NULL,
  `moulding_3` varchar(4) default NULL,
  `time_4` time default NULL,
  `moulding_4` varchar(4) default NULL,
  `time_5` time default NULL,
  `moulding_5` varchar(4) default NULL,
  `time_6` time default NULL,
  `moulding_6` varchar(4) default NULL,
  `time_7` time default NULL,
  `moulding_7` varchar(4) default NULL,
  `time_8` time default NULL,
  `moulding_8` varchar(4) default NULL,
  `time_9` time default NULL,
  `moulding_9` varchar(4) default NULL,
  `time_10` time default NULL,
  `moulding_10` varchar(4) default NULL,
  `time_11` time default NULL,
  `moulding_11` varchar(4) default NULL,
  `time_12` time default NULL,
  `moulding_12` varchar(4) default NULL,
  `time_13` time default NULL,
  `moulding_13` varchar(4) default NULL,
  `time_14` time default NULL,
  `moulding_14` varchar(4) default NULL,
  `time_15` time default NULL,
  `moulding_15` varchar(4) default NULL,
  `time_16` time default NULL,
  `moulding_16` varchar(4) default NULL,
  `time_17` time default NULL,
  `moulding_17` varchar(4) default NULL,
  `time_18` time default NULL,
  `moulding_18` varchar(4) default NULL,
  `time_19` time default NULL,
  `moulding_19` varchar(4) default NULL,
  `time_20` time default NULL,
  `moulding_20` varchar(4) default NULL,
  `time_21` time default NULL,
  `moulding_21` varchar(4) default NULL,
  `time_22` time default NULL,
  `moulding_22` varchar(4) default NULL,
  `time_23` time default NULL,
  `moulding_23` varchar(4) default NULL,
  `time_24` time default NULL,
  `moulding_24` varchar(4) default NULL,
  `time_25` time default NULL,
  `moulding_25` varchar(4) default NULL,
  `time_26` time default NULL,
  `moulding_26` varchar(4) default NULL,
  `time_27` time default NULL,
  `moulding_27` varchar(4) default NULL,
  `time_28` time default NULL,
  `moulding_28` varchar(4) default NULL,
  `time_29` time default NULL,
  `moulding_29` varchar(4) default NULL,
  `time_30` time default NULL,
  `moulding_30` varchar(4) default NULL,
  `time_31` time default NULL,
  `moulding_31` varchar(4) default NULL,
  `time_32` time default NULL,
  `moulding_32` varchar(4) default NULL,
  `time_33` time default NULL,
  `moulding_33` varchar(4) default NULL,
  `time_34` time default NULL,
  `moulding_34` varchar(4) default NULL,
  `import_id` bigint(20) default NULL,
  `time_35` time default NULL,
  `moulding_35` varchar(4) default NULL,
  `time_36` time default NULL,
  `moulding_36` varchar(4) default NULL,
  `time_37` time default NULL,
  `moulding_37` varchar(4) default NULL,
  `time_38` time default NULL,
  `moulding_38` varchar(4) default NULL,
  `time_39` time default NULL,
  `moulding_39` varchar(4) default NULL,
  `time_40` time default NULL,
  `moulding_40` varchar(4) default NULL,
  `time_41` time default NULL,
  `moulding_41` varchar(4) default NULL,
  `time_42` time default NULL,
  `moulding_42` varchar(4) default NULL,
  `time_43` time default NULL,
  `moulding_43` varchar(4) default NULL,
  `time_44` time default NULL,
  `moulding_44` varchar(4) default NULL,
  `time_45` time default NULL,
  `moulding_45` varchar(4) default NULL,
  `time_46` time default NULL,
  `moulding_46` varchar(4) default NULL,
  `time_47` time default NULL,
  `moulding_47` varchar(4) default NULL,
  `time_48` time default NULL,
  `moulding_48` varchar(4) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKFF23523A3E8F4D64` (`last_modified_by`),
  KEY `FKFF23523A8523EC95` (`patient_id`),
  KEY `FKFF23523A2370976D` (`pregnancy_id`),
  KEY `FKFF23523A7D2B7913` (`site_id`),
  KEY `FKFF23523A51A3A90E` (`created_by`),
  CONSTRAINT `FKFF23523A2370976D` FOREIGN KEY (`pregnancy_id`) REFERENCES `pregnancy` (`id`),
  CONSTRAINT `FKFF23523A3E8F4D64` FOREIGN KEY (`last_modified_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FKFF23523A51A3A90E` FOREIGN KEY (`created_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FKFF23523A7D2B7913` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`),
  CONSTRAINT `FKFF23523A8523EC95` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `parto_moulding`
--


/*!40000 ALTER TABLE `parto_moulding` DISABLE KEYS */;
LOCK TABLES `parto_moulding` WRITE;
INSERT INTO `parto_moulding` VALUES (1,1,1,'2007-08-14','2007-08-14 09:43:15','2007-08-14 09:43:15','zepadmin','zepadmin',1,'09:43:15','2+',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `parto_moulding` ENABLE KEYS */;

--
-- Table structure for table `parto_oxytocin`
--

DROP TABLE IF EXISTS `parto_oxytocin`;
CREATE TABLE `parto_oxytocin` (
  `id` bigint(20) NOT NULL auto_increment,
  `patient_id` bigint(20) default NULL,
  `pregnancy_id` bigint(20) default NULL,
  `date_visit` date NOT NULL default '0000-00-00',
  `last_modified` datetime default NULL,
  `created` datetime default NULL,
  `last_modified_by` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `site_id` bigint(20) default NULL,
  `time_1` time default NULL,
  `oxytocin_1` int(11) default NULL,
  `time_2` time default NULL,
  `oxytocin_2` int(11) default NULL,
  `time_3` time default NULL,
  `oxytocin_3` int(11) default NULL,
  `time_4` time default NULL,
  `oxytocin_4` int(11) default NULL,
  `time_5` time default NULL,
  `oxytocin_5` int(11) default NULL,
  `time_6` time default NULL,
  `oxytocin_6` int(11) default NULL,
  `time_7` time default NULL,
  `oxytocin_7` int(11) default NULL,
  `time_8` time default NULL,
  `oxytocin_8` int(11) default NULL,
  `time_9` time default NULL,
  `oxytocin_9` int(11) default NULL,
  `time_10` time default NULL,
  `oxytocin_10` int(11) default NULL,
  `time_11` time default NULL,
  `oxytocin_11` int(11) default NULL,
  `time_12` time default NULL,
  `oxytocin_12` int(11) default NULL,
  `time_13` time default NULL,
  `oxytocin_13` int(11) default NULL,
  `time_14` time default NULL,
  `oxytocin_14` int(11) default NULL,
  `time_15` time default NULL,
  `oxytocin_15` int(11) default NULL,
  `time_16` time default NULL,
  `oxytocin_16` int(11) default NULL,
  `time_17` time default NULL,
  `oxytocin_17` int(11) default NULL,
  `time_18` time default NULL,
  `oxytocin_18` int(11) default NULL,
  `time_19` time default NULL,
  `oxytocin_19` int(11) default NULL,
  `time_20` time default NULL,
  `oxytocin_20` int(11) default NULL,
  `time_21` time default NULL,
  `oxytocin_21` int(11) default NULL,
  `time_22` time default NULL,
  `oxytocin_22` int(11) default NULL,
  `time_23` time default NULL,
  `oxytocin_23` int(11) default NULL,
  `time_24` time default NULL,
  `oxytocin_24` int(11) default NULL,
  `time_25` time default NULL,
  `oxytocin_25` int(11) default NULL,
  `time_26` time default NULL,
  `oxytocin_26` int(11) default NULL,
  `time_27` time default NULL,
  `oxytocin_27` int(11) default NULL,
  `time_28` time default NULL,
  `oxytocin_28` int(11) default NULL,
  `time_29` time default NULL,
  `oxytocin_29` int(11) default NULL,
  `time_30` time default NULL,
  `oxytocin_30` int(11) default NULL,
  `time_31` time default NULL,
  `oxytocin_31` int(11) default NULL,
  `time_32` time default NULL,
  `oxytocin_32` int(11) default NULL,
  `time_33` time default NULL,
  `oxytocin_33` int(11) default NULL,
  `time_34` time default NULL,
  `oxytocin_34` int(11) default NULL,
  `time_35` time default NULL,
  `oxytocin_35` int(11) default NULL,
  `time_36` time default NULL,
  `oxytocin_36` int(11) default NULL,
  `time_37` time default NULL,
  `oxytocin_37` int(11) default NULL,
  `time_38` time default NULL,
  `oxytocin_38` int(11) default NULL,
  `time_39` time default NULL,
  `oxytocin_39` int(11) default NULL,
  `time_40` time default NULL,
  `oxytocin_40` int(11) default NULL,
  `time_41` time default NULL,
  `oxytocin_41` int(11) default NULL,
  `time_42` time default NULL,
  `oxytocin_42` int(11) default NULL,
  `time_43` time default NULL,
  `oxytocin_43` int(11) default NULL,
  `time_44` time default NULL,
  `oxytocin_44` int(11) default NULL,
  `time_45` time default NULL,
  `oxytocin_45` int(11) default NULL,
  `time_46` time default NULL,
  `oxytocin_46` int(11) default NULL,
  `time_47` time default NULL,
  `oxytocin_47` int(11) default NULL,
  `time_48` time default NULL,
  `oxytocin_48` int(11) default NULL,
  `time_49` time default NULL,
  `oxytocin_49` int(11) default NULL,
  `time_50` time default NULL,
  `oxytocin_50` int(11) default NULL,
  `time_51` time default NULL,
  `oxytocin_51` int(11) default NULL,
  `time_52` time default NULL,
  `oxytocin_52` int(11) default NULL,
  `time_53` time default NULL,
  `oxytocin_53` int(11) default NULL,
  `time_54` time default NULL,
  `oxytocin_54` int(11) default NULL,
  `time_55` time default NULL,
  `oxytocin_55` int(11) default NULL,
  `time_56` time default NULL,
  `oxytocin_56` int(11) default NULL,
  `time_57` time default NULL,
  `oxytocin_57` int(11) default NULL,
  `time_58` time default NULL,
  `oxytocin_58` int(11) default NULL,
  `time_59` time default NULL,
  `oxytocin_59` int(11) default NULL,
  `time_60` time default NULL,
  `oxytocin_60` int(11) default NULL,
  `time_61` time default NULL,
  `oxytocin_61` int(11) default NULL,
  `time_62` time default NULL,
  `oxytocin_62` int(11) default NULL,
  `time_63` time default NULL,
  `oxytocin_63` int(11) default NULL,
  `time_64` time default NULL,
  `oxytocin_64` int(11) default NULL,
  `time_65` time default NULL,
  `oxytocin_65` int(11) default NULL,
  `time_66` time default NULL,
  `oxytocin_66` int(11) default NULL,
  `time_67` time default NULL,
  `oxytocin_67` int(11) default NULL,
  `time_68` time default NULL,
  `oxytocin_68` int(11) default NULL,
  `import_id` bigint(20) default NULL,
  `time_69` time default NULL,
  `oxytocin_69` int(11) default NULL,
  `time_70` time default NULL,
  `oxytocin_70` int(11) default NULL,
  `time_71` time default NULL,
  `oxytocin_71` int(11) default NULL,
  `time_72` time default NULL,
  `oxytocin_72` int(11) default NULL,
  `time_73` time default NULL,
  `oxytocin_73` int(11) default NULL,
  `time_74` time default NULL,
  `oxytocin_74` int(11) default NULL,
  `time_75` time default NULL,
  `oxytocin_75` int(11) default NULL,
  `time_76` time default NULL,
  `oxytocin_76` int(11) default NULL,
  `time_77` time default NULL,
  `oxytocin_77` int(11) default NULL,
  `time_78` time default NULL,
  `oxytocin_78` int(11) default NULL,
  `time_79` time default NULL,
  `oxytocin_79` int(11) default NULL,
  `time_80` time default NULL,
  `oxytocin_80` int(11) default NULL,
  `time_81` time default NULL,
  `oxytocin_81` int(11) default NULL,
  `time_82` time default NULL,
  `oxytocin_82` int(11) default NULL,
  `time_83` time default NULL,
  `oxytocin_83` int(11) default NULL,
  `time_84` time default NULL,
  `oxytocin_84` int(11) default NULL,
  `time_85` time default NULL,
  `oxytocin_85` int(11) default NULL,
  `time_86` time default NULL,
  `oxytocin_86` int(11) default NULL,
  `time_87` time default NULL,
  `oxytocin_87` int(11) default NULL,
  `time_88` time default NULL,
  `oxytocin_88` int(11) default NULL,
  `time_89` time default NULL,
  `oxytocin_89` int(11) default NULL,
  `time_90` time default NULL,
  `oxytocin_90` int(11) default NULL,
  `time_91` time default NULL,
  `oxytocin_91` int(11) default NULL,
  `time_92` time default NULL,
  `oxytocin_92` int(11) default NULL,
  `time_93` time default NULL,
  `oxytocin_93` int(11) default NULL,
  `time_94` time default NULL,
  `oxytocin_94` int(11) default NULL,
  `time_95` time default NULL,
  `oxytocin_95` int(11) default NULL,
  `time_96` time default NULL,
  `oxytocin_96` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKB246C8C07D2B7913` (`site_id`),
  KEY `FKB246C8C051A3A90E` (`created_by`),
  KEY `FKB246C8C03E8F4D64` (`last_modified_by`),
  KEY `FKB246C8C02370976D` (`pregnancy_id`),
  KEY `FKB246C8C08523EC95` (`patient_id`),
  CONSTRAINT `FKB246C8C02370976D` FOREIGN KEY (`pregnancy_id`) REFERENCES `pregnancy` (`id`),
  CONSTRAINT `FKB246C8C03E8F4D64` FOREIGN KEY (`last_modified_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FKB246C8C051A3A90E` FOREIGN KEY (`created_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FKB246C8C07D2B7913` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`),
  CONSTRAINT `FKB246C8C08523EC95` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `parto_oxytocin`
--


/*!40000 ALTER TABLE `parto_oxytocin` DISABLE KEYS */;
LOCK TABLES `parto_oxytocin` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `parto_oxytocin` ENABLE KEYS */;

--
-- Table structure for table `parto_pulse`
--

DROP TABLE IF EXISTS `parto_pulse`;
CREATE TABLE `parto_pulse` (
  `id` bigint(20) NOT NULL auto_increment,
  `patient_id` bigint(20) default NULL,
  `pregnancy_id` bigint(20) default NULL,
  `date_visit` date NOT NULL default '0000-00-00',
  `last_modified` datetime default NULL,
  `created` datetime default NULL,
  `last_modified_by` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `site_id` bigint(20) default NULL,
  `time_1` time default NULL,
  `pulse_1` int(11) default NULL,
  `time_2` time default NULL,
  `pulse_2` int(11) default NULL,
  `time_3` time default NULL,
  `pulse_3` int(11) default NULL,
  `time_4` time default NULL,
  `pulse_4` int(11) default NULL,
  `time_5` time default NULL,
  `pulse_5` int(11) default NULL,
  `time_6` time default NULL,
  `pulse_6` int(11) default NULL,
  `time_7` time default NULL,
  `pulse_7` int(11) default NULL,
  `time_8` time default NULL,
  `pulse_8` int(11) default NULL,
  `time_9` time default NULL,
  `pulse_9` int(11) default NULL,
  `time_10` time default NULL,
  `pulse_10` int(11) default NULL,
  `time_11` time default NULL,
  `pulse_11` int(11) default NULL,
  `time_12` time default NULL,
  `pulse_12` int(11) default NULL,
  `time_13` time default NULL,
  `pulse_13` int(11) default NULL,
  `time_14` time default NULL,
  `pulse_14` int(11) default NULL,
  `time_15` time default NULL,
  `pulse_15` int(11) default NULL,
  `time_16` time default NULL,
  `pulse_16` int(11) default NULL,
  `time_17` time default NULL,
  `pulse_17` int(11) default NULL,
  `time_18` time default NULL,
  `pulse_18` int(11) default NULL,
  `time_19` time default NULL,
  `pulse_19` int(11) default NULL,
  `time_20` time default NULL,
  `pulse_20` int(11) default NULL,
  `time_21` time default NULL,
  `pulse_21` int(11) default NULL,
  `time_22` time default NULL,
  `pulse_22` int(11) default NULL,
  `time_23` time default NULL,
  `pulse_23` int(11) default NULL,
  `time_24` time default NULL,
  `pulse_24` int(11) default NULL,
  `time_25` time default NULL,
  `pulse_25` int(11) default NULL,
  `time_26` time default NULL,
  `pulse_26` int(11) default NULL,
  `time_27` time default NULL,
  `pulse_27` int(11) default NULL,
  `time_28` time default NULL,
  `pulse_28` int(11) default NULL,
  `time_29` time default NULL,
  `pulse_29` int(11) default NULL,
  `time_30` time default NULL,
  `pulse_30` int(11) default NULL,
  `time_31` time default NULL,
  `pulse_31` int(11) default NULL,
  `time_32` time default NULL,
  `pulse_32` int(11) default NULL,
  `time_33` time default NULL,
  `pulse_33` int(11) default NULL,
  `time_34` time default NULL,
  `pulse_34` int(11) default NULL,
  `import_id` bigint(20) default NULL,
  `time_35` time default NULL,
  `pulse_35` int(11) default NULL,
  `time_36` time default NULL,
  `pulse_36` int(11) default NULL,
  `time_37` time default NULL,
  `pulse_37` int(11) default NULL,
  `time_38` time default NULL,
  `pulse_38` int(11) default NULL,
  `time_39` time default NULL,
  `pulse_39` int(11) default NULL,
  `time_40` time default NULL,
  `pulse_40` int(11) default NULL,
  `time_41` time default NULL,
  `pulse_41` int(11) default NULL,
  `time_42` time default NULL,
  `pulse_42` int(11) default NULL,
  `time_43` time default NULL,
  `pulse_43` int(11) default NULL,
  `time_44` time default NULL,
  `pulse_44` int(11) default NULL,
  `time_45` time default NULL,
  `pulse_45` int(11) default NULL,
  `time_46` time default NULL,
  `pulse_46` int(11) default NULL,
  `time_47` time default NULL,
  `pulse_47` int(11) default NULL,
  `time_48` time default NULL,
  `pulse_48` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK64B504763E8F4D64` (`last_modified_by`),
  KEY `FK64B5047651A3A90E` (`created_by`),
  KEY `FK64B504767D2B7913` (`site_id`),
  KEY `FK64B504762370976D` (`pregnancy_id`),
  KEY `FK64B504768523EC95` (`patient_id`),
  CONSTRAINT `FK64B504762370976D` FOREIGN KEY (`pregnancy_id`) REFERENCES `pregnancy` (`id`),
  CONSTRAINT `FK64B504763E8F4D64` FOREIGN KEY (`last_modified_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FK64B5047651A3A90E` FOREIGN KEY (`created_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FK64B504767D2B7913` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`),
  CONSTRAINT `FK64B504768523EC95` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `parto_pulse`
--


/*!40000 ALTER TABLE `parto_pulse` DISABLE KEYS */;
LOCK TABLES `parto_pulse` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `parto_pulse` ENABLE KEYS */;

--
-- Table structure for table `parto_respiration`
--

DROP TABLE IF EXISTS `parto_respiration`;
CREATE TABLE `parto_respiration` (
  `id` bigint(20) NOT NULL auto_increment,
  `patient_id` bigint(20) default NULL,
  `pregnancy_id` bigint(20) default NULL,
  `date_visit` date NOT NULL default '0000-00-00',
  `last_modified` datetime default NULL,
  `created` datetime default NULL,
  `last_modified_by` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `site_id` bigint(20) default NULL,
  `time_1` time default NULL,
  `respiration_1` float default NULL,
  `time_2` time default NULL,
  `respiration_2` float default NULL,
  `time_3` time default NULL,
  `respiration_3` float default NULL,
  `time_4` time default NULL,
  `respiration_4` float default NULL,
  `time_5` time default NULL,
  `respiration_5` float default NULL,
  `time_6` time default NULL,
  `respiration_6` float default NULL,
  `time_7` time default NULL,
  `respiration_7` float default NULL,
  `time_8` time default NULL,
  `respiration_8` float default NULL,
  `time_9` time default NULL,
  `respiration_9` float default NULL,
  `time_10` time default NULL,
  `respiration_10` float default NULL,
  `time_11` time default NULL,
  `respiration_11` float default NULL,
  `time_12` time default NULL,
  `respiration_12` float default NULL,
  `time_13` time default NULL,
  `respiration_13` float default NULL,
  `time_14` time default NULL,
  `respiration_14` float default NULL,
  `time_15` time default NULL,
  `respiration_15` float default NULL,
  `time_16` time default NULL,
  `respiration_16` float default NULL,
  `time_17` time default NULL,
  `respiration_17` float default NULL,
  `import_id` bigint(20) default NULL,
  `time_18` time default NULL,
  `respiration_18` float default NULL,
  `time_19` time default NULL,
  `respiration_19` float default NULL,
  `time_20` time default NULL,
  `respiration_20` float default NULL,
  `time_21` time default NULL,
  `respiration_21` float default NULL,
  `time_22` time default NULL,
  `respiration_22` float default NULL,
  `time_23` time default NULL,
  `respiration_23` float default NULL,
  `time_24` time default NULL,
  `respiration_24` float default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK9B1DC8B93E8F4D64` (`last_modified_by`),
  KEY `FK9B1DC8B97D2B7913` (`site_id`),
  KEY `FK9B1DC8B951A3A90E` (`created_by`),
  CONSTRAINT `FK9B1DC8B93E8F4D64` FOREIGN KEY (`last_modified_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FK9B1DC8B951A3A90E` FOREIGN KEY (`created_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FK9B1DC8B97D2B7913` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `parto_respiration`
--


/*!40000 ALTER TABLE `parto_respiration` DISABLE KEYS */;
LOCK TABLES `parto_respiration` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `parto_respiration` ENABLE KEYS */;

--
-- Table structure for table `parto_temperature`
--

DROP TABLE IF EXISTS `parto_temperature`;
CREATE TABLE `parto_temperature` (
  `id` bigint(20) NOT NULL auto_increment,
  `patient_id` bigint(20) default NULL,
  `pregnancy_id` bigint(20) default NULL,
  `date_visit` date NOT NULL default '0000-00-00',
  `last_modified` datetime default NULL,
  `created` datetime default NULL,
  `last_modified_by` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `site_id` bigint(20) default NULL,
  `time_1` time default NULL,
  `temperature_1` float default NULL,
  `time_2` time default NULL,
  `temperature_2` float default NULL,
  `time_3` time default NULL,
  `temperature_3` float default NULL,
  `time_4` time default NULL,
  `temperature_4` float default NULL,
  `time_5` time default NULL,
  `temperature_5` float default NULL,
  `time_6` time default NULL,
  `temperature_6` float default NULL,
  `time_7` time default NULL,
  `temperature_7` float default NULL,
  `time_8` time default NULL,
  `temperature_8` float default NULL,
  `time_9` time default NULL,
  `temperature_9` float default NULL,
  `time_10` time default NULL,
  `temperature_10` float default NULL,
  `time_11` time default NULL,
  `temperature_11` float default NULL,
  `time_12` time default NULL,
  `temperature_12` float default NULL,
  `time_13` time default NULL,
  `temperature_13` float default NULL,
  `time_14` time default NULL,
  `temperature_14` float default NULL,
  `time_15` time default NULL,
  `temperature_15` float default NULL,
  `time_16` time default NULL,
  `temperature_16` float default NULL,
  `time_17` time default NULL,
  `temperature_17` float default NULL,
  `import_id` bigint(20) default NULL,
  `time_18` time default NULL,
  `temperature_18` float default NULL,
  `time_19` time default NULL,
  `temperature_19` float default NULL,
  `time_20` time default NULL,
  `temperature_20` float default NULL,
  `time_21` time default NULL,
  `temperature_21` float default NULL,
  `time_22` time default NULL,
  `temperature_22` float default NULL,
  `time_23` time default NULL,
  `temperature_23` float default NULL,
  `time_24` time default NULL,
  `temperature_24` float default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK77B3D4917D2B7913` (`site_id`),
  KEY `FK77B3D4918523EC95` (`patient_id`),
  KEY `FK77B3D4913E8F4D64` (`last_modified_by`),
  KEY `FK77B3D49151A3A90E` (`created_by`),
  KEY `FK77B3D4912370976D` (`pregnancy_id`),
  CONSTRAINT `FK77B3D4912370976D` FOREIGN KEY (`pregnancy_id`) REFERENCES `pregnancy` (`id`),
  CONSTRAINT `FK77B3D4913E8F4D64` FOREIGN KEY (`last_modified_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FK77B3D49151A3A90E` FOREIGN KEY (`created_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FK77B3D4917D2B7913` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`),
  CONSTRAINT `FK77B3D4918523EC95` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `parto_temperature`
--


/*!40000 ALTER TABLE `parto_temperature` DISABLE KEYS */;
LOCK TABLES `parto_temperature` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `parto_temperature` ENABLE KEYS */;

--
-- Table structure for table `parto_urinalysis_acetone`
--

DROP TABLE IF EXISTS `parto_urinalysis_acetone`;
CREATE TABLE `parto_urinalysis_acetone` (
  `id` bigint(20) NOT NULL auto_increment,
  `patient_id` bigint(20) default NULL,
  `pregnancy_id` bigint(20) default NULL,
  `date_visit` date NOT NULL default '0000-00-00',
  `last_modified` datetime default NULL,
  `created` datetime default NULL,
  `last_modified_by` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `site_id` bigint(20) default NULL,
  `time_1` time default NULL,
  `urinalysisAcetone_1` varchar(4) default NULL,
  `time_2` time default NULL,
  `urinalysisAcetone_2` varchar(4) default NULL,
  `time_3` time default NULL,
  `urinalysisAcetone_3` varchar(4) default NULL,
  `time_4` time default NULL,
  `urinalysisAcetone_4` varchar(4) default NULL,
  `time_5` time default NULL,
  `urinalysisAcetone_5` varchar(4) default NULL,
  `time_6` time default NULL,
  `urinalysisAcetone_6` varchar(4) default NULL,
  `time_7` time default NULL,
  `urinalysisAcetone_7` varchar(4) default NULL,
  `time_8` time default NULL,
  `urinalysisAcetone_8` varchar(4) default NULL,
  `time_9` time default NULL,
  `urinalysisAcetone_9` varchar(4) default NULL,
  `time_10` time default NULL,
  `urinalysisAcetone_10` varchar(4) default NULL,
  `time_11` time default NULL,
  `urinalysisAcetone_11` varchar(4) default NULL,
  `time_12` time default NULL,
  `urinalysisAcetone_12` varchar(4) default NULL,
  `time_13` time default NULL,
  `urinalysisAcetone_13` varchar(4) default NULL,
  `time_14` time default NULL,
  `urinalysisAcetone_14` varchar(4) default NULL,
  `time_15` time default NULL,
  `urinalysisAcetone_15` varchar(4) default NULL,
  `time_16` time default NULL,
  `urinalysisAcetone_16` varchar(4) default NULL,
  `time_17` time default NULL,
  `urinalysisAcetone_17` varchar(4) default NULL,
  `import_id` bigint(20) default NULL,
  `time_18` time default NULL,
  `urinalysisAcetone_18` varchar(4) default NULL,
  `time_19` time default NULL,
  `urinalysisAcetone_19` varchar(4) default NULL,
  `time_20` time default NULL,
  `urinalysisAcetone_20` varchar(4) default NULL,
  `time_21` time default NULL,
  `urinalysisAcetone_21` varchar(4) default NULL,
  `time_22` time default NULL,
  `urinalysisAcetone_22` varchar(4) default NULL,
  `time_23` time default NULL,
  `urinalysisAcetone_23` varchar(4) default NULL,
  `time_24` time default NULL,
  `urinalysisAcetone_24` varchar(4) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK6C8B6FEA51A3A90E` (`created_by`),
  KEY `FK6C8B6FEA8523EC95` (`patient_id`),
  KEY `FK6C8B6FEA2370976D` (`pregnancy_id`),
  KEY `FK6C8B6FEA7D2B7913` (`site_id`),
  KEY `FK6C8B6FEA3E8F4D64` (`last_modified_by`),
  CONSTRAINT `FK6C8B6FEA2370976D` FOREIGN KEY (`pregnancy_id`) REFERENCES `pregnancy` (`id`),
  CONSTRAINT `FK6C8B6FEA3E8F4D64` FOREIGN KEY (`last_modified_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FK6C8B6FEA51A3A90E` FOREIGN KEY (`created_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FK6C8B6FEA7D2B7913` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`),
  CONSTRAINT `FK6C8B6FEA8523EC95` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `parto_urinalysis_acetone`
--


/*!40000 ALTER TABLE `parto_urinalysis_acetone` DISABLE KEYS */;
LOCK TABLES `parto_urinalysis_acetone` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `parto_urinalysis_acetone` ENABLE KEYS */;

--
-- Table structure for table `parto_urinalysis_glucose`
--

DROP TABLE IF EXISTS `parto_urinalysis_glucose`;
CREATE TABLE `parto_urinalysis_glucose` (
  `id` bigint(20) NOT NULL auto_increment,
  `patient_id` bigint(20) default NULL,
  `pregnancy_id` bigint(20) default NULL,
  `date_visit` date NOT NULL default '0000-00-00',
  `last_modified` datetime default NULL,
  `created` datetime default NULL,
  `last_modified_by` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `site_id` bigint(20) default NULL,
  `time_1` time default NULL,
  `urinalysisGlucose_1` varchar(4) default NULL,
  `time_2` time default NULL,
  `urinalysisGlucose_2` varchar(4) default NULL,
  `time_3` time default NULL,
  `urinalysisGlucose_3` varchar(4) default NULL,
  `time_4` time default NULL,
  `urinalysisGlucose_4` varchar(4) default NULL,
  `time_5` time default NULL,
  `urinalysisGlucose_5` varchar(4) default NULL,
  `time_6` time default NULL,
  `urinalysisGlucose_6` varchar(4) default NULL,
  `time_7` time default NULL,
  `urinalysisGlucose_7` varchar(4) default NULL,
  `time_8` time default NULL,
  `urinalysisGlucose_8` varchar(4) default NULL,
  `time_9` time default NULL,
  `urinalysisGlucose_9` varchar(4) default NULL,
  `time_10` time default NULL,
  `urinalysisGlucose_10` varchar(4) default NULL,
  `time_11` time default NULL,
  `urinalysisGlucose_11` varchar(4) default NULL,
  `time_12` time default NULL,
  `urinalysisGlucose_12` varchar(4) default NULL,
  `time_13` time default NULL,
  `urinalysisGlucose_13` varchar(4) default NULL,
  `time_14` time default NULL,
  `urinalysisGlucose_14` varchar(4) default NULL,
  `time_15` time default NULL,
  `urinalysisGlucose_15` varchar(4) default NULL,
  `time_16` time default NULL,
  `urinalysisGlucose_16` varchar(4) default NULL,
  `time_17` time default NULL,
  `urinalysisGlucose_17` varchar(4) default NULL,
  `import_id` bigint(20) default NULL,
  `time_18` time default NULL,
  `urinalysisGlucose_18` varchar(4) default NULL,
  `time_19` time default NULL,
  `urinalysisGlucose_19` varchar(4) default NULL,
  `time_20` time default NULL,
  `urinalysisGlucose_20` varchar(4) default NULL,
  `time_21` time default NULL,
  `urinalysisGlucose_21` varchar(4) default NULL,
  `time_22` time default NULL,
  `urinalysisGlucose_22` varchar(4) default NULL,
  `time_23` time default NULL,
  `urinalysisGlucose_23` varchar(4) default NULL,
  `time_24` time default NULL,
  `urinalysisGlucose_24` varchar(4) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKBA262F632370976D` (`pregnancy_id`),
  KEY `FKBA262F6351A3A90E` (`created_by`),
  KEY `FKBA262F633E8F4D64` (`last_modified_by`),
  KEY `FKBA262F638523EC95` (`patient_id`),
  KEY `FKBA262F637D2B7913` (`site_id`),
  CONSTRAINT `FKBA262F632370976D` FOREIGN KEY (`pregnancy_id`) REFERENCES `pregnancy` (`id`),
  CONSTRAINT `FKBA262F633E8F4D64` FOREIGN KEY (`last_modified_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FKBA262F6351A3A90E` FOREIGN KEY (`created_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FKBA262F637D2B7913` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`),
  CONSTRAINT `FKBA262F638523EC95` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `parto_urinalysis_glucose`
--


/*!40000 ALTER TABLE `parto_urinalysis_glucose` DISABLE KEYS */;
LOCK TABLES `parto_urinalysis_glucose` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `parto_urinalysis_glucose` ENABLE KEYS */;

--
-- Table structure for table `parto_urinalysis_protein`
--

DROP TABLE IF EXISTS `parto_urinalysis_protein`;
CREATE TABLE `parto_urinalysis_protein` (
  `id` bigint(20) NOT NULL auto_increment,
  `patient_id` bigint(20) default NULL,
  `pregnancy_id` bigint(20) default NULL,
  `date_visit` date NOT NULL default '0000-00-00',
  `last_modified` datetime default NULL,
  `created` datetime default NULL,
  `last_modified_by` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `site_id` bigint(20) default NULL,
  `time_1` time default NULL,
  `urinalysisProtein_1` varchar(4) default NULL,
  `time_2` time default NULL,
  `urinalysisProtein_2` varchar(4) default NULL,
  `time_3` time default NULL,
  `urinalysisProtein_3` varchar(4) default NULL,
  `time_4` time default NULL,
  `urinalysisProtein_4` varchar(4) default NULL,
  `time_5` time default NULL,
  `urinalysisProtein_5` varchar(4) default NULL,
  `time_6` time default NULL,
  `urinalysisProtein_6` varchar(4) default NULL,
  `time_7` time default NULL,
  `urinalysisProtein_7` varchar(4) default NULL,
  `time_8` time default NULL,
  `urinalysisProtein_8` varchar(4) default NULL,
  `time_9` time default NULL,
  `urinalysisProtein_9` varchar(4) default NULL,
  `time_10` time default NULL,
  `urinalysisProtein_10` varchar(4) default NULL,
  `time_11` time default NULL,
  `urinalysisProtein_11` varchar(4) default NULL,
  `time_12` time default NULL,
  `urinalysisProtein_12` varchar(4) default NULL,
  `time_13` time default NULL,
  `urinalysisProtein_13` varchar(4) default NULL,
  `time_14` time default NULL,
  `urinalysisProtein_14` varchar(4) default NULL,
  `time_15` time default NULL,
  `urinalysisProtein_15` varchar(4) default NULL,
  `time_16` time default NULL,
  `urinalysisProtein_16` varchar(4) default NULL,
  `time_17` time default NULL,
  `urinalysisProtein_17` varchar(4) default NULL,
  `import_id` bigint(20) default NULL,
  `time_18` time default NULL,
  `urinalysisProtein_18` varchar(4) default NULL,
  `time_19` time default NULL,
  `urinalysisProtein_19` varchar(4) default NULL,
  `time_20` time default NULL,
  `urinalysisProtein_20` varchar(4) default NULL,
  `time_21` time default NULL,
  `urinalysisProtein_21` varchar(4) default NULL,
  `time_22` time default NULL,
  `urinalysisProtein_22` varchar(4) default NULL,
  `time_23` time default NULL,
  `urinalysisProtein_23` varchar(4) default NULL,
  `time_24` time default NULL,
  `urinalysisProtein_24` varchar(4) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKA02E5EF851A3A90E` (`created_by`),
  KEY `FKA02E5EF83E8F4D64` (`last_modified_by`),
  KEY `FKA02E5EF88523EC95` (`patient_id`),
  KEY `FKA02E5EF82370976D` (`pregnancy_id`),
  KEY `FKA02E5EF87D2B7913` (`site_id`),
  CONSTRAINT `FKA02E5EF82370976D` FOREIGN KEY (`pregnancy_id`) REFERENCES `pregnancy` (`id`),
  CONSTRAINT `FKA02E5EF83E8F4D64` FOREIGN KEY (`last_modified_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FKA02E5EF851A3A90E` FOREIGN KEY (`created_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FKA02E5EF87D2B7913` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`),
  CONSTRAINT `FKA02E5EF88523EC95` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `parto_urinalysis_protein`
--


/*!40000 ALTER TABLE `parto_urinalysis_protein` DISABLE KEYS */;
LOCK TABLES `parto_urinalysis_protein` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `parto_urinalysis_protein` ENABLE KEYS */;

--
-- Table structure for table `parto_urine_amount`
--

DROP TABLE IF EXISTS `parto_urine_amount`;
CREATE TABLE `parto_urine_amount` (
  `id` bigint(20) NOT NULL auto_increment,
  `patient_id` bigint(20) default NULL,
  `pregnancy_id` bigint(20) default NULL,
  `date_visit` date NOT NULL default '0000-00-00',
  `last_modified` datetime default NULL,
  `created` datetime default NULL,
  `last_modified_by` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `site_id` bigint(20) default NULL,
  `time_1` time default NULL,
  `urineAmount_1` int(11) default NULL,
  `time_2` time default NULL,
  `urineAmount_2` int(11) default NULL,
  `time_3` time default NULL,
  `urineAmount_3` int(11) default NULL,
  `time_4` time default NULL,
  `urineAmount_4` int(11) default NULL,
  `time_5` time default NULL,
  `urineAmount_5` int(11) default NULL,
  `time_6` time default NULL,
  `urineAmount_6` int(11) default NULL,
  `time_7` time default NULL,
  `urineAmount_7` int(11) default NULL,
  `time_8` time default NULL,
  `urineAmount_8` int(11) default NULL,
  `time_9` time default NULL,
  `urineAmount_9` int(11) default NULL,
  `time_10` time default NULL,
  `urineAmount_10` int(11) default NULL,
  `time_11` time default NULL,
  `urineAmount_11` int(11) default NULL,
  `time_12` time default NULL,
  `urineAmount_12` int(11) default NULL,
  `time_13` time default NULL,
  `urineAmount_13` int(11) default NULL,
  `time_14` time default NULL,
  `urineAmount_14` int(11) default NULL,
  `time_15` time default NULL,
  `urineAmount_15` int(11) default NULL,
  `time_16` time default NULL,
  `urineAmount_16` int(11) default NULL,
  `time_17` time default NULL,
  `urineAmount_17` int(11) default NULL,
  `import_id` bigint(20) default NULL,
  `time_18` time default NULL,
  `urineAmount_18` int(11) default NULL,
  `time_19` time default NULL,
  `urineAmount_19` int(11) default NULL,
  `time_20` time default NULL,
  `urineAmount_20` int(11) default NULL,
  `time_21` time default NULL,
  `urineAmount_21` int(11) default NULL,
  `time_22` time default NULL,
  `urineAmount_22` int(11) default NULL,
  `time_23` time default NULL,
  `urineAmount_23` int(11) default NULL,
  `time_24` time default NULL,
  `urineAmount_24` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK2EEB1C773E8F4D64` (`last_modified_by`),
  KEY `FK2EEB1C772370976D` (`pregnancy_id`),
  KEY `FK2EEB1C778523EC95` (`patient_id`),
  KEY `FK2EEB1C777D2B7913` (`site_id`),
  KEY `FK2EEB1C7751A3A90E` (`created_by`),
  CONSTRAINT `FK2EEB1C772370976D` FOREIGN KEY (`pregnancy_id`) REFERENCES `pregnancy` (`id`),
  CONSTRAINT `FK2EEB1C773E8F4D64` FOREIGN KEY (`last_modified_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FK2EEB1C7751A3A90E` FOREIGN KEY (`created_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FK2EEB1C777D2B7913` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`),
  CONSTRAINT `FK2EEB1C778523EC95` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `parto_urine_amount`
--


/*!40000 ALTER TABLE `parto_urine_amount` DISABLE KEYS */;
LOCK TABLES `parto_urine_amount` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `parto_urine_amount` ENABLE KEYS */;

--
-- Table structure for table `parto_vaginal_exam`
--

DROP TABLE IF EXISTS `parto_vaginal_exam`;
CREATE TABLE `parto_vaginal_exam` (
  `id` bigint(20) NOT NULL auto_increment,
  `patient_id` bigint(20) default NULL,
  `pregnancy_id` bigint(20) default NULL,
  `date_visit` date NOT NULL default '0000-00-00',
  `last_modified` datetime default NULL,
  `created` datetime default NULL,
  `last_modified_by` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `site_id` bigint(20) default NULL,
  `time_1` time default NULL,
  `station_1` varchar(255) default NULL,
  `cord_1` varchar(255) default NULL,
  `position_1` varchar(255) default NULL,
  `time_2` time default NULL,
  `station_2` varchar(255) default NULL,
  `cord_2` varchar(255) default NULL,
  `position_2` varchar(255) default NULL,
  `time_3` time default NULL,
  `station_3` varchar(255) default NULL,
  `cord_3` varchar(255) default NULL,
  `position_3` varchar(255) default NULL,
  `time_4` time default NULL,
  `station_4` varchar(255) default NULL,
  `cord_4` varchar(255) default NULL,
  `position_4` varchar(255) default NULL,
  `time_5` time default NULL,
  `station_5` varchar(255) default NULL,
  `cord_5` varchar(255) default NULL,
  `position_5` varchar(255) default NULL,
  `time_6` time default NULL,
  `station_6` varchar(255) default NULL,
  `cord_6` varchar(255) default NULL,
  `position_6` varchar(255) default NULL,
  `vulva_1` varchar(255) default NULL,
  `vagina_1` varchar(255) default NULL,
  `vulva_2` varchar(255) default NULL,
  `vagina_2` varchar(255) default NULL,
  `vulva_3` varchar(255) default NULL,
  `vagina_3` varchar(255) default NULL,
  `vulva_4` varchar(255) default NULL,
  `vagina_4` varchar(255) default NULL,
  `vulva_5` varchar(255) default NULL,
  `vagina_5` varchar(255) default NULL,
  `vulva_6` varchar(255) default NULL,
  `vagina_6` varchar(255) default NULL,
  `import_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKA83663D53E8F4D64` (`last_modified_by`),
  KEY `FKA83663D58523EC95` (`patient_id`),
  KEY `FKA83663D551A3A90E` (`created_by`),
  KEY `FKA83663D52370976D` (`pregnancy_id`),
  KEY `FKA83663D57D2B7913` (`site_id`),
  CONSTRAINT `FKA83663D52370976D` FOREIGN KEY (`pregnancy_id`) REFERENCES `pregnancy` (`id`),
  CONSTRAINT `FKA83663D53E8F4D64` FOREIGN KEY (`last_modified_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FKA83663D551A3A90E` FOREIGN KEY (`created_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FKA83663D57D2B7913` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`),
  CONSTRAINT `FKA83663D58523EC95` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `parto_vaginal_exam`
--


/*!40000 ALTER TABLE `parto_vaginal_exam` DISABLE KEYS */;
LOCK TABLES `parto_vaginal_exam` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `parto_vaginal_exam` ENABLE KEYS */;

--
-- Table structure for table `partographstatus`
--

DROP TABLE IF EXISTS `partographstatus`;
CREATE TABLE `partographstatus` (
  `id` bigint(20) NOT NULL default '0',
  `started` tinyint(1) default NULL,
  `date_completed` date default NULL,
  `time_of_birth` time default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK539DA264D1B` (`id`),
  CONSTRAINT `FK539DA264D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `partographstatus`
--


/*!40000 ALTER TABLE `partographstatus` DISABLE KEYS */;
LOCK TABLES `partographstatus` WRITE;
INSERT INTO `partographstatus` VALUES (13,1,NULL,NULL),(37,1,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `partographstatus` ENABLE KEYS */;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient` (
  `id` bigint(20) NOT NULL auto_increment,
  `first_name` varchar(255) NOT NULL default '',
  `surname` varchar(255) NOT NULL default '',
  `nrc_number` varchar(255) default NULL,
  `district_patient_id` varchar(255) default NULL,
  `address_id` bigint(20) default NULL,
  `last_modified` datetime default NULL,
  `created` datetime default NULL,
  `last_modified_by` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `site_id` bigint(20) default NULL,
  `obstetric_record_number` varchar(255) default NULL,
  `parent_id` bigint(20) default NULL,
  `sex` int(11) default NULL,
  `age_at_first_visit` int(11) default NULL,
  `birthdate` date default NULL,
  `time_of_birth` time default NULL,
  `pregnancy_id` bigint(20) default NULL,
  `dead` tinyint(1) default NULL,
  `hiv_positive` tinyint(1) default NULL,
  `date_death` date default NULL,
  `death_encounter_id` bigint(20) default NULL,
  `height` int(11) default NULL,
  `parent_district_id` varchar(255) default NULL,
  `import_site` varchar(5) default NULL,
  `import_date` datetime default NULL,
  `import_patient_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `dist_patient_id_UK` (`district_patient_id`),
  KEY `IDX_DISTRICT_PATIENT_ID` (`district_patient_id`),
  KEY `IDX_NRC_NUMBER` (`nrc_number`),
  KEY `IDX_FIRST_NAME` (`first_name`),
  KEY `IDX_SURNAME` (`surname`),
  KEY `FKD0D3EB0551A3A90E` (`created_by`),
  KEY `FKD0D3EB053E8F4D64` (`last_modified_by`),
  KEY `FKD0D3EB054FEEE146` (`address_id`),
  KEY `FKD0D3EB057B66B0D0` (`parent_id`),
  KEY `FKD0D3EB057D2B7913` (`site_id`),
  CONSTRAINT `FKD0D3EB053E8F4D64` FOREIGN KEY (`last_modified_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FKD0D3EB054FEEE146` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FKD0D3EB0551A3A90E` FOREIGN KEY (`created_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FKD0D3EB057B66B0D0` FOREIGN KEY (`parent_id`) REFERENCES `patient` (`id`),
  CONSTRAINT `FKD0D3EB057D2B7913` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient`
--


/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
LOCK TABLES `patient` WRITE;
INSERT INTO `patient` VALUES (1,'Mom','Test',NULL,'5040-332-00001-1',NULL,'2007-08-14 09:30:47','2007-08-14 09:30:47','zepadmin','zepadmin',1,NULL,NULL,1,35,'1972-08-14',NULL,NULL,NULL,1,NULL,NULL,167,NULL,NULL,NULL,NULL),(2,'Baby1','Test',NULL,'5040-333-01001-2',NULL,'2007-08-14 09:43:27','2007-08-14 09:43:27','zepadmin','zepadmin',1,NULL,1,1,0,'2007-08-14','09:43:20',1,NULL,NULL,NULL,NULL,NULL,'5040-332-00001-1',NULL,NULL,NULL),(3,'Basic59311','Patient6117',NULL,'5040-332-00002-2',NULL,'2007-08-14 09:44:18','2007-08-14 09:44:18','zepadmin','zepadmin',1,NULL,NULL,1,23,'1984-04-30',NULL,NULL,NULL,NULL,NULL,NULL,135,NULL,NULL,NULL,NULL),(4,'PosSingle2041','Patient4429',NULL,'5040-332-00003-3',NULL,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,NULL,NULL,1,15,'1992-04-02',NULL,NULL,NULL,1,NULL,NULL,135,NULL,NULL,NULL,NULL),(5,'Baby1','Patient4429',NULL,'5040-333-01002-3',NULL,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,NULL,4,1,0,'2007-08-14','09:44:36',3,NULL,NULL,NULL,NULL,NULL,'5040-332-00003-3',NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;

--
-- Table structure for table `patient_status`
--

DROP TABLE IF EXISTS `patient_status`;
CREATE TABLE `patient_status` (
  `id` bigint(20) NOT NULL default '0',
  `current_flow` bigint(20) default NULL,
  `current_flow_encounter_id` bigint(20) default NULL,
  `current_pregnancy_id` bigint(20) default NULL,
  `current_pregnancy_encounter_id` bigint(20) default NULL,
  `current_lmp_date` date default NULL,
  `current_lmp_date_encounter_id` bigint(20) default NULL,
  `current_ega_days` int(11) default NULL,
  `current_ega_days_encounter_id` bigint(20) default NULL,
  `last_modified` datetime default NULL,
  `created` datetime default NULL,
  `last_modified_by` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `site_id` bigint(20) default NULL,
  `no_prev_pregnancies` tinyint(1) default NULL,
  `firm` char(1) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK53A1626CA073B073` (`current_pregnancy_id`),
  KEY `FK53A1626CD1B` (`id`),
  KEY `FK53A1626C98DB5409` (`current_ega_days_encounter_id`),
  KEY `FK53A1626C5066B122` (`current_lmp_date_encounter_id`),
  KEY `FK53A1626C6A5075D2` (`current_flow_encounter_id`),
  KEY `FK53A1626C3E8F4D64` (`last_modified_by`),
  KEY `FK53A1626C57878034` (`current_flow`),
  KEY `FK53A1626C51A3A90E` (`created_by`),
  KEY `FK53A1626C7F1CECFF` (`current_pregnancy_encounter_id`),
  KEY `FK53A1626C7D2B7913` (`site_id`),
  CONSTRAINT `FK53A1626C3E8F4D64` FOREIGN KEY (`last_modified_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FK53A1626C5066B122` FOREIGN KEY (`current_lmp_date_encounter_id`) REFERENCES `encounter` (`id`),
  CONSTRAINT `FK53A1626C51A3A90E` FOREIGN KEY (`created_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FK53A1626C6A5075D2` FOREIGN KEY (`current_flow_encounter_id`) REFERENCES `encounter` (`id`),
  CONSTRAINT `FK53A1626C7D2B7913` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`),
  CONSTRAINT `FK53A1626C7F1CECFF` FOREIGN KEY (`current_pregnancy_encounter_id`) REFERENCES `encounter` (`id`),
  CONSTRAINT `FK53A1626C98DB5409` FOREIGN KEY (`current_ega_days_encounter_id`) REFERENCES `encounter` (`id`),
  CONSTRAINT `FK53A1626CA073B073` FOREIGN KEY (`current_pregnancy_id`) REFERENCES `pregnancy` (`id`),
  CONSTRAINT `FK53A1626CD1B` FOREIGN KEY (`id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient_status`
--


/*!40000 ALTER TABLE `patient_status` DISABLE KEYS */;
LOCK TABLES `patient_status` WRITE;
INSERT INTO `patient_status` VALUES (1,4,16,1,1,'2007-04-04',2,132,2,'2007-08-14 09:43:34','2007-08-14 09:30:47','zepadmin','zepadmin',1,NULL,NULL),(2,4,15,1,15,NULL,NULL,NULL,NULL,'2007-08-14 09:43:27','2007-08-14 09:43:27','zepadmin','zepadmin',1,NULL,NULL),(3,7,45,2,17,'2007-07-07',18,38,18,'2007-08-14 09:59:40','2007-08-14 09:44:18','zepadmin','zepadmin',1,NULL,NULL),(4,4,44,3,26,'2007-07-26',27,19,27,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,NULL,NULL),(5,4,40,3,39,NULL,NULL,NULL,NULL,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `patient_status` ENABLE KEYS */;

--
-- Table structure for table `patientregistration`
--

DROP TABLE IF EXISTS `patientregistration`;
CREATE TABLE `patientregistration` (
  `id` bigint(20) NOT NULL default '0',
  `surname_6` varchar(255) default NULL,
  `forenames_7` varchar(255) default NULL,
  `nrc_no_9` varchar(255) default NULL,
  `obstetric_record_number_1134` varchar(255) default NULL,
  `district_pat_13` varchar(255) default NULL,
  `new_patient_site_id` varchar(255) default NULL,
  `patient_id_number` varchar(255) default NULL,
  `age_at_first_attendence_1135` int(11) default NULL,
  `birth_date_17` date default NULL,
  `education_st_11` int(11) default NULL,
  `residential_19` varchar(255) default NULL,
  `residential_20` varchar(255) default NULL,
  `date_of_resi_21` date default NULL,
  `marital_stat_10` int(11) default NULL,
  `occupation_12` int(11) default NULL,
  `nearby_place_worship_39` varchar(255) default NULL,
  `religion_1239` int(11) default NULL,
  `religion_other_1240` text,
  `surname_p_father_24` varchar(255) default NULL,
  `forenames_p_father_25` varchar(255) default NULL,
  `surname_husband_26` varchar(255) default NULL,
  `forenames_husband_27` varchar(255) default NULL,
  `occupation_husband_28` int(11) default NULL,
  `tel_no_husband_32` varchar(255) default NULL,
  `surname_guardian_33` varchar(255) default NULL,
  `forenames_guardian_34` varchar(255) default NULL,
  `surname_emerg_contact_35` varchar(255) default NULL,
  `forenames_emerg_contact_36` varchar(255) default NULL,
  `address_emerg_contact_37` varchar(255) default NULL,
  `tel_no_emerg_contact_38` varchar(255) default NULL,
  `occupation_other` varchar(255) default NULL,
  `sex_490` int(11) default NULL,
  `firm` varchar(255) default NULL,
  `uth_referral_type` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKCB5E743ED1B` (`id`),
  CONSTRAINT `FKCB5E743ED1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patientregistration`
--


/*!40000 ALTER TABLE `patientregistration` DISABLE KEYS */;
LOCK TABLES `patientregistration` WRITE;
INSERT INTO `patientregistration` VALUES (1,'Test','Mom',NULL,NULL,'5040','33','5040-332-00001-1',35,'1972-08-14',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL),(14,'Test','Baby1',NULL,NULL,'5040','332','5040-333-01001-2',0,'2007-08-14',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL),(17,'Patient6117','Basic59311',NULL,NULL,'5040','332','5040-332-00002-2',23,'1984-04-30',NULL,'6532 Park Place',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL),(26,'Patient4429','PosSingle2041',NULL,NULL,'5040','332','5040-332-00003-3',15,'1992-04-02',NULL,'2011 Park Place',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL),(38,'Patient4429','Baby1',NULL,NULL,'5040','332','5040-333-01002-3',0,'2007-08-14',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `patientregistration` ENABLE KEYS */;

--
-- Table structure for table `postnatalhosp`
--

DROP TABLE IF EXISTS `postnatalhosp`;
CREATE TABLE `postnatalhosp` (
  `id` bigint(20) NOT NULL default '0',
  `date_of_discharge_1268` date default NULL,
  `hospital_ward_1269` int(11) default NULL,
  `anaemia` tinyint(1) default NULL,
  `anaemia_measurement` int(11) default NULL,
  `cardiac_disease` tinyint(1) default NULL,
  `diabetes_gestational` tinyint(1) default NULL,
  `diabetes_pregestational` tinyint(1) default NULL,
  `dysentary` tinyint(1) default NULL,
  `epilepsy` tinyint(1) default NULL,
  `gastroenteritis` tinyint(1) default NULL,
  `hemoglobinopathy_sickle` tinyint(1) default NULL,
  `hemoglobinopathy_thallasemia` tinyint(1) default NULL,
  `hepatitis` tinyint(1) default NULL,
  `hypertensive_disorder` tinyint(1) default NULL,
  `malaria_suspected` tinyint(1) default NULL,
  `polyarthritis` tinyint(1) default NULL,
  `psychosis` tinyint(1) default NULL,
  `tuberculosis` tinyint(1) default NULL,
  `diagnosis_other` tinyint(1) default NULL,
  `diag_other` text,
  `antibiotics` tinyint(1) default NULL,
  `analgesics` tinyint(1) default NULL,
  `iron_supplimentation` tinyint(1) default NULL,
  `misoprostal` tinyint(1) default NULL,
  `med_treatments_other` tinyint(1) default NULL,
  `med_treatments_other_desc` text,
  `mva` tinyint(1) default NULL,
  `dilatation_and_curettage` tinyint(1) default NULL,
  `laparotomy` tinyint(1) default NULL,
  `hysterectomy` tinyint(1) default NULL,
  `salpingostomy` tinyint(1) default NULL,
  `surg_treat_other` tinyint(1) default NULL,
  `surg_treat_other_desc` text,
  `medications_discharge` text,
  `temperature_266` float default NULL,
  `pulse_171` int(11) default NULL,
  `respiratory_system_167` int(11) default NULL,
  `respiratory_system_other` text,
  `respiration_rate_269` int(11) default NULL,
  `bp_systolic_224` int(11) default NULL,
  `bp_diastolic_225` int(11) default NULL,
  `scheduled_followup_1293` date default NULL,
  `place_of_next_visit_1213` varchar(255) default NULL,
  `draining` tinyint(1) default NULL,
  `prolonged_first_stage` tinyint(1) default NULL,
  `cpd` tinyint(1) default NULL,
  `pph` tinyint(1) default NULL,
  `Hypertensive_disorders` int(11) default NULL,
  `ruptured_placenta` tinyint(1) default NULL,
  `sepsis` tinyint(1) default NULL,
  `prolonged_labor` tinyint(1) default NULL,
  `ruptured_uterus` tinyint(1) default NULL,
  `cervical_tears` tinyint(1) default NULL,
  `multiple_pregnancy` tinyint(1) default NULL,
  `malpresentations` tinyint(1) default NULL,
  `intrapartum_foetal_distress` tinyint(1) default NULL,
  `previous_c_s` tinyint(1) default NULL,
  `broken_episotum` tinyint(1) default NULL,
  `dizziness` tinyint(1) default NULL,
  `fever` tinyint(1) default NULL,
  `drug_1_1136` int(11) default NULL,
  `drug_2_1137` int(11) default NULL,
  `drug_3_1138` int(11) default NULL,
  `drug_4_1139` int(11) default NULL,
  `drug_5_1140` int(11) default NULL,
  `drug_6_1141` int(11) default NULL,
  `drug_7_1142` int(11) default NULL,
  `drug_8_1143` int(11) default NULL,
  `drug_9_1144` int(11) default NULL,
  `drug_10_1145` int(11) default NULL,
  `episiotomy_suture` tinyint(1) default NULL,
  `cs` tinyint(1) default NULL,
  `btl` tinyint(1) default NULL,
  `instrumental_delivery` tinyint(1) default NULL,
  `blood_transfusion` tinyint(1) default NULL,
  `psychosis_diag` tinyint(1) default NULL,
  `referring_encounter_id` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKF9129C10D1B` (`id`),
  CONSTRAINT `FKF9129C10D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `postnatalhosp`
--


/*!40000 ALTER TABLE `postnatalhosp` DISABLE KEYS */;
LOCK TABLES `postnatalhosp` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `postnatalhosp` ENABLE KEYS */;

--
-- Table structure for table `postnatalinfant`
--

DROP TABLE IF EXISTS `postnatalinfant`;
CREATE TABLE `postnatalinfant` (
  `id` bigint(20) NOT NULL default '0',
  `postnatal_visit_601` int(11) default NULL,
  `infant_status` int(11) default NULL,
  `reasons_death_infant` int(11) default NULL,
  `other_reasons_death_infant` text,
  `weight_679` float default NULL,
  `temperature_infant_680` float default NULL,
  `head_681` int(11) default NULL,
  `head_other_682` text,
  `head_circumf_530` int(11) default NULL,
  `eyes_523` int(11) default NULL,
  `eyes_other_524` text,
  `ears_686` int(11) default NULL,
  `ears_other_687` text,
  `mouth_526` int(11) default NULL,
  `mouth_other_527` text,
  `neck_690` int(11) default NULL,
  `neck_other_d_691` text,
  `abdomen_692` int(11) default NULL,
  `abdomen_oth_693` text,
  `cord_at_followup_694` int(11) default NULL,
  `cord_at_foll_desc695` text,
  `genitalia_529` int(11) default NULL,
  `genitalia_other_697` text,
  `anus_698` int(11) default NULL,
  `anus_other_699` text,
  `skin_700` int(11) default NULL,
  `skin_other_701` text,
  `upper_limbs_702` int(11) default NULL,
  `upper_limbs_other_703` text,
  `lower_limbs_704` int(11) default NULL,
  `lower_limbs_other_705` text,
  `back_536` int(11) default NULL,
  `back_other_537` text,
  `neurological_708` int(11) default NULL,
  `neurological_other_709` text,
  `opv1_given_week_6_710` tinyint(4) default NULL,
  `dpt_1_given_week_6_711` tinyint(4) default NULL,
  `bcg_given_712` tinyint(4) default NULL,
  `feeding` int(11) default NULL,
  `is_problem` int(11) default NULL,
  `jaundice` tinyint(4) default NULL,
  `fever` tinyint(4) default NULL,
  `skin_rashes` tinyint(4) default NULL,
  `eye_discharge` tinyint(4) default NULL,
  `abdominal_distension` tinyint(4) default NULL,
  `excessive_crying` tinyint(4) default NULL,
  `cord_problem` tinyint(4) default NULL,
  `not_feeding_well` tinyint(4) default NULL,
  `bowel_obstruction` tinyint(1) default NULL,
  `indigestion` tinyint(1) default NULL,
  `opthalmia_neonatorum` tinyint(1) default NULL,
  `dehydration` tinyint(1) default NULL,
  `umbilical_infection` tinyint(1) default NULL,
  `diarrhoea` tinyint(1) default NULL,
  `disposition` int(11) default NULL,
  `feeding_type` int(11) default NULL,
  `immunization_554` int(11) default NULL,
  `immunization_1` int(11) default NULL,
  `immunization_2` int(11) default NULL,
  `immunization_3` int(11) default NULL,
  `immunization_4` int(11) default NULL,
  `immunization_5` int(11) default NULL,
  `patient_received_arv` tinyint(4) default NULL,
  `second_postnatal_visit_date` date default NULL,
  `infant_hiv_test` int(11) default NULL,
  `hiv_test_result` int(11) default NULL,
  `hiv_test_date` date default NULL,
  `septrin_prescribed_today` tinyint(4) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK7C85D2D1B` (`id`),
  CONSTRAINT `FK7C85D2D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `postnatalinfant`
--


/*!40000 ALTER TABLE `postnatalinfant` DISABLE KEYS */;
LOCK TABLES `postnatalinfant` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `postnatalinfant` ENABLE KEYS */;

--
-- Table structure for table `postnatalinfantvisit`
--

DROP TABLE IF EXISTS `postnatalinfantvisit`;
CREATE TABLE `postnatalinfantvisit` (
  `id` bigint(20) NOT NULL default '0',
  `postnatal_visit_601` int(11) default NULL,
  `infant_status` int(11) default NULL,
  `reasons_death_infant` int(11) default NULL,
  `other_reasons_death_infant` text,
  `weight_679` float default NULL,
  `temperature_infant_680` float default NULL,
  `head_681` int(11) default NULL,
  `head_other_682` text,
  `head_circumf_530` int(11) default NULL,
  `eyes_523` int(11) default NULL,
  `eyes_other_524` text,
  `ears_686` int(11) default NULL,
  `ears_other_687` text,
  `mouth_526` int(11) default NULL,
  `mouth_other_527` text,
  `neck_690` int(11) default NULL,
  `neck_other_d_691` text,
  `abdomen_692` int(11) default NULL,
  `abdomen_oth_693` text,
  `cord_at_followup_694` int(11) default NULL,
  `cord_at_foll_desc695` text,
  `genitalia_529` int(11) default NULL,
  `genitalia_other_697` text,
  `anus_698` int(11) default NULL,
  `anus_other_699` text,
  `skin_700` int(11) default NULL,
  `skin_other_701` text,
  `upper_limbs_702` int(11) default NULL,
  `upper_limbs_other_703` text,
  `lower_limbs_704` int(11) default NULL,
  `lower_limbs_other_705` text,
  `back_536` int(11) default NULL,
  `back_other_537` text,
  `neurological_708` int(11) default NULL,
  `neurological_other_709` text,
  `opv1_given_week_6_710` tinyint(4) default NULL,
  `dpt_1_given_week_6_711` tinyint(4) default NULL,
  `bcg_given_712` tinyint(4) default NULL,
  `feeding` int(11) default NULL,
  `is_problem` int(11) default NULL,
  `jaundice` tinyint(4) default NULL,
  `fever` tinyint(4) default NULL,
  `skin_rashes` tinyint(4) default NULL,
  `eye_discharge` tinyint(4) default NULL,
  `abdominal_distension` tinyint(4) default NULL,
  `excessive_crying` tinyint(4) default NULL,
  `cord_problem` tinyint(4) default NULL,
  `not_feeding_well` tinyint(4) default NULL,
  `bowel_obstruction` tinyint(1) default NULL,
  `indigestion` tinyint(1) default NULL,
  `opthalmia_neonatorum` tinyint(1) default NULL,
  `dehydration` tinyint(1) default NULL,
  `umbilical_infection` tinyint(1) default NULL,
  `diarrhoea` tinyint(1) default NULL,
  `disposition` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK4E989AB9D1B` (`id`),
  CONSTRAINT `FK4E989AB9D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `postnatalinfantvisit`
--


/*!40000 ALTER TABLE `postnatalinfantvisit` DISABLE KEYS */;
LOCK TABLES `postnatalinfantvisit` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `postnatalinfantvisit` ENABLE KEYS */;

--
-- Table structure for table `postnatalmaternalvisit`
--

DROP TABLE IF EXISTS `postnatalmaternalvisit`;
CREATE TABLE `postnatalmaternalvisit` (
  `id` bigint(20) NOT NULL default '0',
  `postnatal_visit_601` int(11) default NULL,
  `day_of_puerperium_611` int(11) default NULL,
  `pulse_171` int(11) default NULL,
  `bp_systolic_224` int(11) default NULL,
  `bp_diastolic_225` int(11) default NULL,
  `hb_235` int(11) default NULL,
  `urinalysis_240` int(11) default NULL,
  `urinalysis_alb_242` int(11) default NULL,
  `urinalysis_glu_243` int(11) default NULL,
  `urinalysis_ace_244` int(11) default NULL,
  `anti_d_given_621` tinyint(1) default NULL,
  `other_complaints_622` text,
  `breast_feeding_623` int(11) default NULL,
  `hair_625` int(11) default NULL,
  `eyes_626` int(11) default NULL,
  `eyes_other_627` text,
  `mouth_628` int(11) default NULL,
  `mouth_other_1191` text,
  `teeth_163` int(11) default NULL,
  `teeth_other_164` text,
  `thyroid_165` int(11) default NULL,
  `breasts_166` int(11) default NULL,
  `nipples_633` int(11) default NULL,
  `lymphadenopa_1208` int(11) default NULL,
  `lymphadenopa_desc_1209` text,
  `uterus_187` int(11) default NULL,
  `perineum_580` int(11) default NULL,
  `perineum_other_1199` text,
  `perineum_infect_desc_1198` text,
  `anus_638` int(11) default NULL,
  `bowels_639` int(11) default NULL,
  `bowels_abno_640` text,
  `micturition_641` int(11) default NULL,
  `micturition_desc_642` text,
  `wound_643` int(11) default NULL,
  `wound_abnor_644` text,
  `lochia_flow_645` int(11) default NULL,
  `lochia_colou_646` int(11) default NULL,
  `lochia_odor_647` int(11) default NULL,
  `legs_649` int(11) default NULL,
  `cervix_per_spec_666` int(11) default NULL,
  `cervix_per_spec_result_667` int(11) default NULL,
  `contraceptive_advice_669` tinyint(1) default NULL,
  `using_contraception_670` tinyint(1) default NULL,
  `contraceptive_choice_137` int(11) default NULL,
  `contraceptive_other_138` text,
  `health_educa_discussed673` int(11) default NULL,
  `health_educa_discussed_other_674` text,
  `postnatal_comments` text,
  `is_problem` int(11) default NULL,
  `education1` int(11) default NULL,
  `education2` int(11) default NULL,
  `education3` int(11) default NULL,
  `education4` int(11) default NULL,
  `education5` int(11) default NULL,
  `education6` int(11) default NULL,
  `education7` int(11) default NULL,
  `patient_received_arv` tinyint(4) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKE6BBBBF3D1B` (`id`),
  CONSTRAINT `FKE6BBBBF3D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `postnatalmaternalvisit`
--


/*!40000 ALTER TABLE `postnatalmaternalvisit` DISABLE KEYS */;
LOCK TABLES `postnatalmaternalvisit` WRITE;
INSERT INTO `postnatalmaternalvisit` VALUES (44,339,NULL,91,116,117,5,126,128,129,130,0,'test value12219',NULL,351,352,'test value12219',353,'test value12219',86,'test value12219',87,88,357,2661,'test value12219',100,325,'test value12219','test value12219',362,363,'test value12219',364,'test value12219',365,'test value12219',366,898,368,370,381,382,0,0,74,'test value12219',NULL,'test value12219','test value12219',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0);
UNLOCK TABLES;
/*!40000 ALTER TABLE `postnatalmaternalvisit` ENABLE KEYS */;

--
-- Table structure for table `postultrasound`
--

DROP TABLE IF EXISTS `postultrasound`;
CREATE TABLE `postultrasound` (
  `id` bigint(20) NOT NULL default '0',
  `date_of_ultrasound_1212` date default NULL,
  `diagnosis_ultrasound` int(11) default NULL,
  `describe_abnormalities` text,
  PRIMARY KEY  (`id`),
  KEY `FKA7536D83D1B` (`id`),
  CONSTRAINT `FKA7536D83D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `postultrasound`
--


/*!40000 ALTER TABLE `postultrasound` DISABLE KEYS */;
LOCK TABLES `postultrasound` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `postultrasound` ENABLE KEYS */;

--
-- Table structure for table `pregnancy`
--

DROP TABLE IF EXISTS `pregnancy`;
CREATE TABLE `pregnancy` (
  `id` bigint(20) NOT NULL auto_increment,
  `patient_id` bigint(20) default NULL,
  `date_pregnancy_begin` date default NULL,
  `pregnancy_begin_encounter_id` bigint(20) default NULL,
  `date_pregnancy_end` date default NULL,
  `pregnancy_end_encounter_id` bigint(20) default NULL,
  `last_modified` datetime default NULL,
  `created` datetime default NULL,
  `last_modified_by` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `site_id` bigint(20) default NULL,
  `labour_admission_encounter_id` bigint(20) default NULL,
  `date_labour_admission` date default NULL,
  `import_pregnancy_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKB50AEF2D51A3A90E` (`created_by`),
  KEY `FKB50AEF2D8D11075D` (`pregnancy_end_encounter_id`),
  KEY `FKB50AEF2D7D2B7913` (`site_id`),
  KEY `FKB50AEF2DB0F949CF` (`pregnancy_begin_encounter_id`),
  KEY `FKB50AEF2D8523EC95` (`patient_id`),
  KEY `FKB50AEF2D3E8F4D64` (`last_modified_by`),
  KEY `FKB50AEF2DD04455BD` (`labour_admission_encounter_id`),
  CONSTRAINT `FKB50AEF2D3E8F4D64` FOREIGN KEY (`last_modified_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FKB50AEF2D51A3A90E` FOREIGN KEY (`created_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FKB50AEF2D7D2B7913` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`),
  CONSTRAINT `FKB50AEF2D8523EC95` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`),
  CONSTRAINT `FKB50AEF2D8D11075D` FOREIGN KEY (`pregnancy_end_encounter_id`) REFERENCES `encounter` (`id`),
  CONSTRAINT `FKB50AEF2DD04455BD` FOREIGN KEY (`labour_admission_encounter_id`) REFERENCES `encounter` (`id`),
  CONSTRAINT `pregnancy_ibfk_1` FOREIGN KEY (`pregnancy_begin_encounter_id`) REFERENCES `encounter` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pregnancy`
--


/*!40000 ALTER TABLE `pregnancy` DISABLE KEYS */;
LOCK TABLES `pregnancy` WRITE;
INSERT INTO `pregnancy` VALUES (1,1,'2007-08-14',1,NULL,NULL,'2007-08-14 09:35:35','2007-08-14 09:30:47','zepadmin','zepadmin',1,12,'2007-08-14',NULL),(2,3,'2007-08-14',17,NULL,NULL,'2007-08-14 09:44:18','2007-08-14 09:44:18','zepadmin','zepadmin',1,NULL,NULL,NULL),(3,4,'2007-08-14',26,NULL,NULL,'2007-08-14 09:44:36','2007-08-14 09:44:36','zepadmin','zepadmin',1,35,'2007-08-14',NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `pregnancy` ENABLE KEYS */;

--
-- Table structure for table `pregnancybegin`
--

DROP TABLE IF EXISTS `pregnancybegin`;
CREATE TABLE `pregnancybegin` (
  `id` bigint(20) NOT NULL default '0',
  `date_pregnancy_began` date default NULL,
  `comments_new_preg_1371` text,
  PRIMARY KEY  (`id`),
  KEY `FK9D0FD9CD1B` (`id`),
  CONSTRAINT `FK9D0FD9CD1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pregnancybegin`
--


/*!40000 ALTER TABLE `pregnancybegin` DISABLE KEYS */;
LOCK TABLES `pregnancybegin` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `pregnancybegin` ENABLE KEYS */;

--
-- Table structure for table `pregnancydating`
--

DROP TABLE IF EXISTS `pregnancydating`;
CREATE TABLE `pregnancydating` (
  `id` bigint(20) NOT NULL default '0',
  `lmp_reliability_126` int(11) default NULL,
  `lmp_127_calculated` date default NULL,
  `edd_128` date default NULL,
  `ega_129` int(11) default NULL,
  `quickening_130` int(11) default NULL,
  `cycle_in_days_132` int(11) default NULL,
  `uterus_size_in_days_188` int(11) default NULL,
  `menstrual_history_131` int(11) default NULL,
  `dating_method` int(11) default NULL,
  `planned_preg_135` tinyint(1) default '0',
  `contracept_practiced_136` tinyint(1) default '0',
  `contraceptive_choice_137` int(11) default NULL,
  `contraceptive_other_138` text,
  `ega_ultrasound` int(11) default NULL,
  `lmp_from_widget` date default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK3385F258D1B` (`id`),
  CONSTRAINT `FK3385F258D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pregnancydating`
--


/*!40000 ALTER TABLE `pregnancydating` DISABLE KEYS */;
LOCK TABLES `pregnancydating` WRITE;
INSERT INTO `pregnancydating` VALUES (2,67,'2007-04-04','2008-01-09',132,NULL,NULL,NULL,NULL,2805,1,NULL,NULL,NULL,NULL,'2007-04-04'),(18,67,'2007-07-07','2008-04-12',38,NULL,NULL,NULL,NULL,2805,NULL,NULL,NULL,NULL,NULL,NULL),(27,67,'2007-07-26','2008-05-01',19,NULL,NULL,NULL,NULL,2805,NULL,NULL,NULL,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `pregnancydating` ENABLE KEYS */;

--
-- Table structure for table `pregnancyend`
--

DROP TABLE IF EXISTS `pregnancyend`;
CREATE TABLE `pregnancyend` (
  `id` bigint(20) NOT NULL default '0',
  `is_terminated_pregnancy` tinyint(1) default NULL,
  `date_pregnancy_ended` date default NULL,
  `comments_preg_conclusion_1368` text,
  PRIMARY KEY  (`id`),
  KEY `FK2370AE8ED1B` (`id`),
  CONSTRAINT `FK2370AE8ED1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pregnancyend`
--


/*!40000 ALTER TABLE `pregnancyend` DISABLE KEYS */;
LOCK TABLES `pregnancyend` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `pregnancyend` ENABLE KEYS */;

--
-- Table structure for table `prevpregnancies`
--

DROP TABLE IF EXISTS `prevpregnancies`;
CREATE TABLE `prevpregnancies` (
  `id` bigint(20) NOT NULL default '0',
  `place_of_delivery_50` int(11) default NULL,
  `place_delivery_other` varchar(255) default NULL,
  `year_of_delivery_51` int(11) default NULL,
  `pregnancy_course_52` int(11) default NULL,
  `outcome_of_pregnancy_53` int(11) default NULL,
  `if_died_before_5_years_54` int(11) default NULL,
  `other_cause_death_55` text,
  `if_died_before_5_hiv_56` tinyint(1) default NULL,
  `if_tested_result_57` int(11) default NULL,
  `mode_of_delivery_58` int(11) default NULL,
  `type_of_labour_59` int(11) default NULL,
  `indication_CS_forcepts_60` int(11) default NULL,
  `indication_CS_forcepts_desc_61` text,
  `duration_of_labour_62` int(11) default NULL,
  `postpartum_i_66` int(11) default NULL,
  `number_of_fetuses_63` int(11) default NULL,
  `birth_weight_infant1_65` float default NULL,
  `birth_weight_infant_2_1244` float default NULL,
  `birth_weight_infant_3_1245` float default NULL,
  `birth_weight_infant_4_1246` float default NULL,
  `birth_weight_infant_5_1247` float default NULL,
  `birth_weight_infant_6_1248` float default NULL,
  `month_of_delivery` int(11) default NULL,
  `pph` tinyint(1) default NULL,
  `sex_infant1` int(11) default NULL,
  `sex_infant2` int(11) default NULL,
  `sex_infant3` int(11) default NULL,
  `complications_other` text,
  `mode_of_delivery_447` int(11) default NULL,
  `type_of_labour` int(11) default NULL,
  `eclampsia` tinyint(1) default NULL,
  `zeprs_pregnancy_id` bigint(20) default NULL,
  `comments` text,
  `aph` tinyint(4) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK2861B878D1B` (`id`),
  CONSTRAINT `FK2861B878D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prevpregnancies`
--


/*!40000 ALTER TABLE `prevpregnancies` DISABLE KEYS */;
LOCK TABLES `prevpregnancies` WRITE;
INSERT INTO `prevpregnancies` VALUES (3,19,NULL,2003,20,21,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,28,3,NULL,NULL,NULL,NULL,NULL,5,NULL,1,NULL,NULL,NULL,260,2837,NULL,NULL,NULL,NULL),(19,19,'test value94209',1989,20,21,22,'test value94209',0,24,NULL,NULL,27,NULL,41,2955,28,3,4,1,NULL,NULL,NULL,3,0,1,1,1,NULL,260,2836,0,NULL,'test value94209',0),(28,19,'test value49133',1998,20,21,22,'test value49133',0,24,NULL,NULL,27,NULL,2,2955,28,1,3,1,NULL,NULL,NULL,10,0,1,1,1,NULL,260,2836,0,NULL,'test value49133',0);
UNLOCK TABLES;
/*!40000 ALTER TABLE `prevpregnancies` ENABLE KEYS */;

--
-- Table structure for table `problabor`
--

DROP TABLE IF EXISTS `problabor`;
CREATE TABLE `problabor` (
  `id` bigint(20) NOT NULL default '0',
  `phase` int(11) default NULL,
  `contractions_1250` tinyint(1) default NULL,
  `contractions_date_1251` date default NULL,
  `lower_abdominal_pains` tinyint(1) default NULL,
  `lower_abdominal_pains_date_onset` date default NULL,
  `decreased_fetal_movement_1256` tinyint(1) default NULL,
  `deacreased_fetal_mvmt_date_onset` date default NULL,
  `vag_bleeding_1254` tinyint(1) default NULL,
  `bleeding_date_of_onset_1255` date default NULL,
  `vag_discharge_1252` tinyint(1) default NULL,
  `vag_discharge_date_of_onset_1253` date default NULL,
  `fever` tinyint(1) default NULL,
  `fever_date_onset` date default NULL,
  `elevated_blood_pressure` tinyint(1) default NULL,
  `swelling_edema` tinyint(1) default NULL,
  `trauma` tinyint(1) default NULL,
  `nausea_vomiting` tinyint(1) default NULL,
  `diarrhea` tinyint(1) default NULL,
  `diarrhea_date_onset` date default NULL,
  `possible_reputure_membranes` tinyint(1) default NULL,
  `shortness_of_breath` tinyint(1) default NULL,
  `possible_infection` tinyint(1) default NULL,
  `backache` tinyint(1) default NULL,
  `headache` tinyint(1) default NULL,
  `date_onset_headache` date default NULL,
  `fatigue_dizziness` tinyint(1) default NULL,
  `lack_of_foetal_movement` tinyint(1) default NULL,
  `cough` tinyint(1) default NULL,
  `other_reasons` tinyint(1) default NULL,
  `reasons_other_describe` text,
  `respiratory_system_167` int(11) default NULL,
  `respiratory_system_other` text,
  `respiration_rate_269` int(11) default NULL,
  `bp_systolic_224` int(11) default NULL,
  `bp_diastolic_225` int(11) default NULL,
  `pulse_171` int(11) default NULL,
  `temperature_266` float default NULL,
  `height_159` int(11) default NULL,
  `weight_228` float default NULL,
  `heent_161` int(11) default NULL,
  `heent_abnorm_162` text,
  `thyroid_165` int(11) default NULL,
  `breasts_166` int(11) default NULL,
  `heart_169` int(11) default NULL,
  `heart_other_170` text,
  `abdomen_172` int(11) default NULL,
  `abdomen_abnormal_173` text,
  `skin_176` int(11) default NULL,
  `skin_abnorm_177` text,
  `extremities_174` int(11) default NULL,
  `extremities_abnormal_175` text,
  `lymph_nodes_178` int(11) default NULL,
  `rectum_179` int(11) default NULL,
  `rectum_abnormal_180` text,
  `vulva_181` int(11) default NULL,
  `vulva_abnormal_182` text,
  `vagina_183` int(11) default NULL,
  `vagina_abnormal_184` text,
  `cervix_185` int(11) default NULL,
  `cervix_abnormal_186` text,
  `uterus_187` int(11) default NULL,
  `uterus_size_in_weeks_188` int(11) default NULL,
  `adnexa_189` int(11) default NULL,
  `adnexa_abnormal_190` text,
  `varicosities_191` int(11) default NULL,
  `teeth_163` int(11) default NULL,
  `teeth_other_164` text,
  `cns_192` int(11) default NULL,
  `time_of_exam_1175` time default NULL,
  `fundal_height_232` int(11) default NULL,
  `lie_313` int(11) default NULL,
  `presentation_314` int(11) default NULL,
  `presentation_other` text,
  `descent_315` int(11) default NULL,
  `contraction_strength_316` int(11) default NULL,
  `contraction_freq_10_abd_palp_317` int(11) default NULL,
  `foetal_heart_rate_abd_palp_318` int(11) default NULL,
  `time_320` time default NULL,
  `rupture_of_membranes_date_328` date default NULL,
  `rupture_of_membranes_time_329` time default NULL,
  `presentation_obtained_by_335` int(11) default NULL,
  `condition_of_vulva_321` int(11) default NULL,
  `condition_of_vulva_desc_322` text,
  `condition_of_vagina_323` int(11) default NULL,
  `condition_of_vagina_other_324` text,
  `cervix_dilatation325` int(11) default NULL,
  `cervix_effacement_326` int(11) default NULL,
  `cervix_consistency_327` int(11) default NULL,
  `membranes_re_330` int(11) default NULL,
  `liquor_331` int(11) default NULL,
  `station_of_pp_336` int(11) default NULL,
  `position_337` varchar(255) default NULL,
  `moulding_338` int(11) default NULL,
  `caput_339` int(11) default NULL,
  `cord_at_vaginal_exam_340` int(11) default NULL,
  `rupture_of_membranes_1221` tinyint(1) default NULL,
  `diagonal_conjugate_342` int(11) default NULL,
  `diagonal_conjugate_length_343` int(11) default NULL,
  `ishcial_spines_344` int(11) default NULL,
  `sub_pubic_arch_345` int(11) default NULL,
  `curvature_of_sacrum_346` int(11) default NULL,
  `intertuberous_diameter_347` int(11) default NULL,
  `adequacy_of_pelvic_348` int(11) default NULL,
  `pubic_arch_angle_349` int(11) default NULL,
  `false_labour` tinyint(1) default NULL,
  `true_labor` tinyint(1) default NULL,
  `rupture_of_membranes` tinyint(1) default NULL,
  `intact_membranes` tinyint(1) default NULL,
  `preeclamp_hypert_1265` tinyint(1) default NULL,
  `premature_labour` tinyint(1) default NULL,
  `malaria_diag` tinyint(1) default NULL,
  `anaemia` tinyint(1) default NULL,
  `high_bp_diag` tinyint(1) default NULL,
  `vaginal_bleeding_diag` tinyint(1) default NULL,
  `intrauterine_death` tinyint(1) default NULL,
  `uti_diag` tinyint(1) default NULL,
  `pneumonia_diag` tinyint(1) default NULL,
  `tb_diag` tinyint(1) default NULL,
  `vaginal_thrush_diag` tinyint(1) default NULL,
  `oral_thrush_diag` tinyint(1) default NULL,
  `diag_other` text,
  `disposition_labor` int(11) default NULL,
  `disp_ante` int(11) default NULL,
  `treatment_1463` text,
  `comments_ante_prob_1464` text,
  `uterus_size_in_days_188` int(11) default NULL,
  `priority_of_referral` int(11) default NULL,
  `transport` int(11) default NULL,
  `type_of_labour` int(11) default NULL,
  `comments_reasons_for_eval` text,
  `eclampsia` tinyint(1) default NULL,
  `abruptia_placenta` tinyint(1) default NULL,
  `miscarriage` int(11) default NULL,
  `uth_ward` int(11) default NULL,
  `latent_labour` tinyint(1) default NULL,
  `cns_normal` tinyint(1) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKBD64A9FBD1B` (`id`),
  CONSTRAINT `FKBD64A9FBD1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `problabor`
--


/*!40000 ALTER TABLE `problabor` DISABLE KEYS */;
LOCK TABLES `problabor` WRITE;
INSERT INTO `problabor` VALUES (12,2755,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,167,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2697,NULL,NULL,NULL,NULL,NULL,NULL,2837,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(25,2755,1,'2007-08-14',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2697,NULL,NULL,NULL,NULL,NULL,NULL,2837,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(34,2755,1,'2007-08-14',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2697,NULL,NULL,NULL,NULL,NULL,NULL,2837,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(35,2755,1,'2007-08-14',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,6,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2697,NULL,NULL,NULL,NULL,NULL,NULL,2837,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(45,2754,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,135,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2952,NULL,NULL,NULL,2710,2809,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `problabor` ENABLE KEYS */;

--
-- Table structure for table `problem`
--

DROP TABLE IF EXISTS `problem`;
CREATE TABLE `problem` (
  `id` bigint(20) NOT NULL auto_increment,
  `patient_id` bigint(20) default NULL,
  `problemName` varchar(255) NOT NULL default '',
  `active` tinyint(1) default NULL,
  `onset_date` date NOT NULL default '0000-00-00',
  `last_modified` datetime default NULL,
  `created` datetime default NULL,
  `last_modified_by` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `site_id` bigint(20) default NULL,
  `pregnancy_id` bigint(20) default NULL,
  `import_problem_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKED8CC29F7D2B7913` (`site_id`),
  KEY `FKED8CC29F8523EC95` (`patient_id`),
  KEY `FKED8CC29F3E8F4D64` (`last_modified_by`),
  KEY `FKED8CC29F2370976D` (`pregnancy_id`),
  KEY `FKED8CC29F51A3A90E` (`created_by`),
  CONSTRAINT `FKED8CC29F2370976D` FOREIGN KEY (`pregnancy_id`) REFERENCES `pregnancy` (`id`),
  CONSTRAINT `FKED8CC29F3E8F4D64` FOREIGN KEY (`last_modified_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FKED8CC29F51A3A90E` FOREIGN KEY (`created_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FKED8CC29F7D2B7913` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`),
  CONSTRAINT `FKED8CC29F8523EC95` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `problem`
--


/*!40000 ALTER TABLE `problem` DISABLE KEYS */;
LOCK TABLES `problem` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `problem` ENABLE KEYS */;

--
-- Table structure for table `problem_archive`
--

DROP TABLE IF EXISTS `problem_archive`;
CREATE TABLE `problem_archive` (
  `id` bigint(20) NOT NULL default '0',
  `patient_id` bigint(20) default NULL,
  `problemName` varchar(255) NOT NULL default '',
  `active` tinyint(1) default NULL,
  `onset_date` date NOT NULL default '0000-00-00',
  `last_modified` datetime default NULL,
  `created` datetime default NULL,
  `last_modified_by` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `site_id` bigint(20) default NULL,
  `pregnancy_id` bigint(20) default NULL,
  `import_problem_id` bigint(20) default NULL,
  `date_deleted` datetime default NULL,
  `deleted_by` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `problem_archive`
--


/*!40000 ALTER TABLE `problem_archive` DISABLE KEYS */;
LOCK TABLES `problem_archive` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `problem_archive` ENABLE KEYS */;

--
-- Table structure for table `probpostnatal`
--

DROP TABLE IF EXISTS `probpostnatal`;
CREATE TABLE `probpostnatal` (
  `id` bigint(20) NOT NULL default '0',
  `lower_abdominal_pains` tinyint(1) default NULL,
  `lower_abdominal_pains_date_onset` date default NULL,
  `vag_bleeding_1254` tinyint(1) default NULL,
  `bleeding_date_of_onset_1255` date default NULL,
  `vag_discharge_1252` tinyint(1) default NULL,
  `vag_discharge_date_of_onset_1253` date default NULL,
  `fever` tinyint(1) default NULL,
  `fever_date_onset` date default NULL,
  `elevated_blood_pressure` tinyint(1) default NULL,
  `swelling_edema` tinyint(1) default NULL,
  `trauma` tinyint(1) default NULL,
  `nausea_vomiting` tinyint(1) default NULL,
  `diarrhea` tinyint(1) default NULL,
  `diarrhea_date_onset` date default NULL,
  `shortness_of_breath` tinyint(1) default NULL,
  `possible_infection` tinyint(1) default NULL,
  `backache` tinyint(1) default NULL,
  `height_159` int(11) default NULL,
  `weight_228` float default NULL,
  `heent_161` int(11) default NULL,
  `heent_abnorm_162` text,
  `thyroid_165` int(11) default NULL,
  `breasts_166` int(11) default NULL,
  `heart_169` int(11) default NULL,
  `heart_other_170` text,
  `abdomen_172` int(11) default NULL,
  `abdomen_abnormal_173` text,
  `skin_176` int(11) default NULL,
  `skin_abnorm_177` text,
  `extremities_174` int(11) default NULL,
  `extremities_abnormal_175` text,
  `lymph_nodes_178` int(11) default NULL,
  `rectum_179` int(11) default NULL,
  `rectum_abnormal_180` text,
  `vulva_181` int(11) default NULL,
  `vulva_abnormal_182` text,
  `vagina_183` int(11) default NULL,
  `vagina_abnormal_184` text,
  `cervix_185` int(11) default NULL,
  `cervix_abnormal_186` text,
  `uterus_187` int(11) default NULL,
  `uterus_size_in_weeks_188` int(11) default NULL,
  `adnexa_189` int(11) default NULL,
  `adnexa_abnormal_190` text,
  `varicosities_191` int(11) default NULL,
  `teeth_163` int(11) default NULL,
  `teeth_other_164` text,
  `cns_192` int(11) default NULL,
  `malaria_diag` tinyint(1) default NULL,
  `anaemia` tinyint(1) default NULL,
  `high_bp_diag` tinyint(1) default NULL,
  `vaginal_bleeding_diag` tinyint(1) default NULL,
  `uti_diag` tinyint(1) default NULL,
  `pneumonia_diag` tinyint(1) default NULL,
  `tb_diag` tinyint(1) default NULL,
  `vaginal_thrush_diag` tinyint(1) default NULL,
  `oral_thrush_diag` tinyint(1) default NULL,
  `diag_other` text,
  `disposition_labor` int(11) default NULL,
  `treatment_1463` text,
  `comments_ante_prob_1464` text,
  `uterus_size_in_days_188` int(11) default NULL,
  `broken_episiotomy` tinyint(1) default NULL,
  `puerperal_sepsis` tinyint(1) default NULL,
  `breast_engorgement` tinyint(1) default NULL,
  `secondary_pph` tinyint(1) default NULL,
  `mastitis` tinyint(1) default NULL,
  `breast_abscess` tinyint(1) default NULL,
  `eclampsia` tinyint(1) default NULL,
  `pre_eclampsia` tinyint(1) default NULL,
  `sepsis` tinyint(1) default NULL,
  `psychosis` tinyint(1) default NULL,
  `episiotomy_infection` tinyint(1) default NULL,
  `episiotomy_breakdown` tinyint(1) default NULL,
  `anaemia_complication` tinyint(1) default NULL,
  `mastitis_breast_absess` tinyint(1) default NULL,
  `endometritis` tinyint(1) default NULL,
  `disposition` int(11) default NULL,
  `date_of_admission` date default NULL,
  `priority_of_referral` int(11) default NULL,
  `transport` int(11) default NULL,
  `date_of_ultrasound_1212` date default NULL,
  `diagnosis_ultrasound` int(11) default NULL,
  `describe_abnormalities` text,
  `uth_ward` int(11) default NULL,
  `preeclamp_hypert_1265` tinyint(1) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK51E308B7D1B` (`id`),
  CONSTRAINT `FK51E308B7D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `probpostnatal`
--


/*!40000 ALTER TABLE `probpostnatal` DISABLE KEYS */;
LOCK TABLES `probpostnatal` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `probpostnatal` ENABLE KEYS */;

--
-- Table structure for table `probpostnatalinfant`
--

DROP TABLE IF EXISTS `probpostnatalinfant`;
CREATE TABLE `probpostnatalinfant` (
  `id` bigint(20) NOT NULL default '0',
  `postnatal_visit_601` int(11) default NULL,
  `infant_status` int(11) default NULL,
  `reasons_death_infant` int(11) default NULL,
  `other_reasons_death_infant` text,
  `weight_679` float default NULL,
  `temperature_infant_680` float default NULL,
  `head_681` int(11) default NULL,
  `head_other_682` text,
  `eyes_523` int(11) default NULL,
  `eyes_other_524` text,
  `ears_686` int(11) default NULL,
  `ears_other_687` text,
  `mouth_526` int(11) default NULL,
  `mouth_other_527` text,
  `neck_690` int(11) default NULL,
  `neck_other_d_691` text,
  `abdomen_692` int(11) default NULL,
  `abdomen_oth_693` text,
  `cord_at_followup_694` int(11) default NULL,
  `cord_at_foll_desc695` text,
  `genitalia_529` int(11) default NULL,
  `genitalia_other_697` text,
  `anus_698` int(11) default NULL,
  `anus_other_699` text,
  `skin_700` int(11) default NULL,
  `skin_other_701` text,
  `upper_limbs_702` int(11) default NULL,
  `upper_limbs_other_703` text,
  `lower_limbs_704` int(11) default NULL,
  `lower_limbs_other_705` text,
  `back_536` int(11) default NULL,
  `back_other_537` text,
  `neurological_708` int(11) default NULL,
  `neurological_other_709` text,
  `opv1_given_week_6_710` tinyint(4) default NULL,
  `dpt_1_given_week_6_711` tinyint(4) default NULL,
  `bcg_given_712` tinyint(4) default NULL,
  `feeding` int(11) default NULL,
  `is_problem` int(11) default NULL,
  `jaundice` tinyint(4) default NULL,
  `fever` tinyint(4) default NULL,
  `skin_rashes` tinyint(4) default NULL,
  `eye_discharge` tinyint(4) default NULL,
  `abdominal_distension` tinyint(4) default NULL,
  `excessive_crying` tinyint(4) default NULL,
  `cord_problem` tinyint(4) default NULL,
  `not_feeding_well` tinyint(4) default NULL,
  `bowel_obstruction` tinyint(1) default NULL,
  `indigestion` tinyint(1) default NULL,
  `opthalmia_neonatorum` tinyint(1) default NULL,
  `dehydration` tinyint(1) default NULL,
  `umbilical_infection` tinyint(1) default NULL,
  `diarrhoea` tinyint(1) default NULL,
  `disposition` int(11) default NULL,
  `feeding_type` int(11) default NULL,
  `infant_sleeping_pattern` int(11) default NULL,
  `passing_urine` int(11) default NULL,
  `passing_stool` int(11) default NULL,
  `uth_ward` int(11) default NULL,
  `priority_of_referral` int(11) default NULL,
  `transport` int(11) default NULL,
  `head_circumf_530` float default NULL,
  `immunization_554` int(11) default NULL,
  `immunization_1` int(11) default NULL,
  `immunization_2` int(11) default NULL,
  `immunization_3` int(11) default NULL,
  `immunization_4` int(11) default NULL,
  `immunization_5` int(11) default NULL,
  `other_reasons` tinyint(4) default NULL,
  `reasons_other_describe` text,
  PRIMARY KEY  (`id`),
  KEY `FKD77C2DDDD1B` (`id`),
  CONSTRAINT `FKD77C2DDDD1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `probpostnatalinfant`
--


/*!40000 ALTER TABLE `probpostnatalinfant` DISABLE KEYS */;
LOCK TABLES `probpostnatalinfant` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `probpostnatalinfant` ENABLE KEYS */;

--
-- Table structure for table `puercomp`
--

DROP TABLE IF EXISTS `puercomp`;
CREATE TABLE `puercomp` (
  `id` bigint(20) NOT NULL default '0',
  `postnatal_visit_601` int(11) default NULL,
  `date_of_discharge_1268` date default NULL,
  `hospital_ward_1269` int(11) default NULL,
  `anaemia` tinyint(1) default NULL,
  `diabetes_gestational` tinyint(1) default NULL,
  `diabetes_pregestational` tinyint(1) default NULL,
  `cardiac_disease` tinyint(1) default NULL,
  `dysentary` tinyint(1) default NULL,
  `epilepsy` tinyint(1) default NULL,
  `gastroenteritis` tinyint(1) default NULL,
  `hemoglobinopathy_sickle` tinyint(1) default NULL,
  `diagnosis_other` tinyint(1) default NULL,
  `diag_other` varchar(255) default NULL,
  `antibiotics` tinyint(1) default NULL,
  `analgesics` tinyint(1) default NULL,
  `iron_supplimentation` tinyint(1) default NULL,
  `misoprostal` tinyint(1) default NULL,
  `med_treatments_other` tinyint(1) default NULL,
  `med_treatments_other_desc` varchar(255) default NULL,
  `mva` tinyint(1) default NULL,
  `dilatation_and_curettage` tinyint(1) default NULL,
  `laparotomy` tinyint(1) default NULL,
  `hysterectomy` tinyint(1) default NULL,
  `salpingostomy` tinyint(1) default NULL,
  `surg_treat_other` tinyint(1) default NULL,
  `surg_treat_other_desc` varchar(255) default NULL,
  `medications_discharge` varchar(255) default NULL,
  `temperature_266` float default NULL,
  `pulse_171` int(11) default NULL,
  `respiratory_system_167` int(11) default NULL,
  `respiratory_system_other` varchar(255) default NULL,
  `respiration_rate_269` int(11) default NULL,
  `bp_systolic_224` int(11) default NULL,
  `bp_diastolic_225` int(11) default NULL,
  `scheduled_followup_1293` date default NULL,
  `place_of_next_visit_1213` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK528B5381D1B` (`id`),
  CONSTRAINT `FK528B5381D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `puercomp`
--


/*!40000 ALTER TABLE `puercomp` DISABLE KEYS */;
LOCK TABLES `puercomp` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `puercomp` ENABLE KEYS */;

--
-- Table structure for table `puerperium`
--

DROP TABLE IF EXISTS `puerperium`;
CREATE TABLE `puerperium` (
  `id` bigint(20) NOT NULL default '0',
  `visit_number` int(11) default NULL,
  `bp_diastolic_225` int(11) default NULL,
  `bp_systolic_224` int(11) default NULL,
  `temperature_266` float default NULL,
  `perineum_intact` tinyint(1) default NULL,
  `perineum_swollen` tinyint(1) default NULL,
  `perineum_hematoma` tinyint(1) default NULL,
  `lochia_579` int(11) default NULL,
  `bowels_639` int(11) default NULL,
  `micturition_641` int(11) default NULL,
  `breasts_166` int(11) default NULL,
  `wound_643` int(11) default NULL,
  `hb_235` int(11) default NULL,
  `anti_d_given_621` tinyint(1) default NULL,
  `weight_679` float default NULL,
  `general_condition_260` int(11) default NULL,
  `bleeding` int(11) default NULL,
  `pallor_193` int(11) default NULL,
  `uterus_187` int(11) default NULL,
  `bladder_emptied_437` tinyint(1) default NULL,
  `is_problem` tinyint(1) default NULL,
  `discharge` tinyint(1) default NULL,
  `time_of_exam` time default NULL,
  `disposition` int(11) default NULL,
  `postpartum_complications_584` int(11) default NULL,
  `comments` text,
  PRIMARY KEY  (`id`),
  KEY `FKF2A8C676D1B` (`id`),
  CONSTRAINT `FKF2A8C676D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `puerperium`
--


/*!40000 ALTER TABLE `puerperium` DISABLE KEYS */;
LOCK TABLES `puerperium` WRITE;
INSERT INTO `puerperium` VALUES (42,NULL,117,116,35,0,0,0,324,363,364,88,365,6,0,NULL,131,2781,2716,100,0,NULL,NULL,'09:44:36',2907,328,'test value1927');
UNLOCK TABLES;
/*!40000 ALTER TABLE `puerperium` ENABLE KEYS */;

--
-- Table structure for table `referral`
--

DROP TABLE IF EXISTS `referral`;
CREATE TABLE `referral` (
  `id` bigint(20) NOT NULL auto_increment,
  `encounter_id` bigint(20) default NULL,
  `patient_id` bigint(20) default NULL,
  `date_of_referral` date default NULL,
  `referred_from` varchar(255) default NULL,
  `referred_to` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKD4EE7B9D8523EC95` (`patient_id`),
  KEY `FKD4EE7B9DC5FB97A7` (`encounter_id`),
  CONSTRAINT `FKD4EE7B9D8523EC95` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`),
  CONSTRAINT `FKD4EE7B9DC5FB97A7` FOREIGN KEY (`encounter_id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `referral`
--


/*!40000 ALTER TABLE `referral` DISABLE KEYS */;
LOCK TABLES `referral` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `referral` ENABLE KEYS */;

--
-- Table structure for table `referral_reasons`
--

DROP TABLE IF EXISTS `referral_reasons`;
CREATE TABLE `referral_reasons` (
  `id` bigint(20) NOT NULL default '0',
  `encounter_id` bigint(20) default NULL,
  `false_labour` tinyint(1) default NULL,
  `true_labor` tinyint(1) default NULL,
  `rupture_of_membranes` tinyint(1) default NULL,
  `intact_membranes` tinyint(1) default NULL,
  `preeclamp_hypert_1265` tinyint(1) default NULL,
  `premature_labour` tinyint(1) default NULL,
  `malaria_diag` tinyint(1) default NULL,
  `anaemia` tinyint(1) default NULL,
  `high_bp_diag` tinyint(1) default NULL,
  `vaginal_bleeding_diag` tinyint(1) default NULL,
  `intrauterine_death` tinyint(1) default NULL,
  `uti_diag` tinyint(1) default NULL,
  `pneumonia_diag` tinyint(1) default NULL,
  `tb_diag` tinyint(1) default NULL,
  `vaginal_thrush_diag` tinyint(1) default NULL,
  `oral_thrush_diag` tinyint(1) default NULL,
  `eclampsia` tinyint(1) default NULL,
  `abruptia_placenta` tinyint(1) default NULL,
  `miscarriage` int(11) default NULL,
  `diag_other` text,
  `broken_episiotomy` tinyint(1) default NULL,
  `puerperal_sepsis` tinyint(1) default NULL,
  `breast_engorgement` tinyint(1) default NULL,
  `secondary_pph` tinyint(1) default NULL,
  `mastitis` tinyint(1) default NULL,
  `breast_abscess` tinyint(1) default NULL,
  `bowel_obstruction` tinyint(1) default NULL,
  `indigestion` tinyint(1) default NULL,
  `opthalmia_neonatorum` tinyint(1) default NULL,
  `dehydration` tinyint(1) default NULL,
  `umbilical_infection` tinyint(1) default NULL,
  `diarrhoea` tinyint(1) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKA337EC0DD1B` (`id`),
  KEY `refencindex` (`encounter_id`),
  CONSTRAINT `FKA337EC0DD1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`),
  CONSTRAINT `refenccnst` FOREIGN KEY (`encounter_id`) REFERENCES `outcome` (`encounter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `referral_reasons`
--


/*!40000 ALTER TABLE `referral_reasons` DISABLE KEYS */;
LOCK TABLES `referral_reasons` WRITE;
INSERT INTO `referral_reasons` VALUES (46,45,NULL,NULL,NULL,NULL,1,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `referral_reasons` ENABLE KEYS */;

--
-- Table structure for table `report_spec`
--

DROP TABLE IF EXISTS `report_spec`;
CREATE TABLE `report_spec` (
  `id` bigint(20) NOT NULL auto_increment,
  `label` varchar(255) default NULL,
  `sql_query` text,
  `col_labels` varchar(255) default NULL,
  `primary_category_column` varchar(255) default NULL,
  `secondary_category_column` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `report_spec`
--


/*!40000 ALTER TABLE `report_spec` DISABLE KEYS */;
LOCK TABLES `report_spec` WRITE;
INSERT INTO `report_spec` VALUES (1,'Encounter Report, no labels','select e.created, Concat_WS(\", \",p.surname,p.first_name) AS patientName, s.site_name, f.label from encounter_record e, form f, site s, patient p where e.form_id=f.id AND e.site_id = s.id AND e.patient_id = p.id order by f.label, e.last_modified desc','','label',''),(2,'Encounter Report with Descriptive Labels','select e.created, Concat_WS(\", \",p.surname,p.first_name) AS patientName, s.site_name, f.label from encounter_record e, form f, site s, patient p where e.form_id=f.id AND e.site_id = s.id AND e.patient_id = p.id order by f.label, e.last_modified desc','Date Created, Patient, Clinic, Encounter','label','site_name');
UNLOCK TABLES;
/*!40000 ALTER TABLE `report_spec` ENABLE KEYS */;

--
-- Table structure for table `routineante`
--

DROP TABLE IF EXISTS `routineante`;
CREATE TABLE `routineante` (
  `id` bigint(20) NOT NULL default '0',
  `field1553` varchar(255) default NULL,
  `ega_129` int(11) default NULL,
  `fundal_height_232` int(11) default NULL,
  `position_337` varchar(255) default NULL,
  `presentation_314` int(11) default NULL,
  `engaged_234` int(11) default NULL,
  `foetal_heart_229` int(11) default NULL,
  `bp_diastolic_225` int(11) default NULL,
  `bp_systolic_224` int(11) default NULL,
  `oedema_231` int(11) default NULL,
  `weight_228` float default NULL,
  `urinalysis_ace_244` int(11) default NULL,
  `urinalysis_alb_242` int(11) default NULL,
  `urinalysis_glu_243` int(11) default NULL,
  `foetal_heart_rate_230` int(11) default NULL,
  `foetal_heart_rhythm_229` int(11) default NULL,
  `date_next_appt` date default NULL,
  `lie_313` int(11) default NULL,
  `is_problem` tinyint(1) default NULL,
  `malaria_sp_dosage` int(11) default NULL,
  `folate` tinyint(4) default NULL,
  `iron` tinyint(4) default NULL,
  `deworming` tinyint(4) default NULL,
  `descent_315` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK82DF2222D1B` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `routineante`
--


/*!40000 ALTER TABLE `routineante` DISABLE KEYS */;
LOCK TABLES `routineante` WRITE;
INSERT INTO `routineante` VALUES (6,NULL,132,14,NULL,169,NULL,NULL,2191,2473,1124,56,NULL,NULL,NULL,1123,650,NULL,168,NULL,2984,1,1,1,170),(23,NULL,46,20,NULL,169,NULL,NULL,1844,2403,1124,55,1131,1671,1444,651,118,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(32,NULL,9,20,NULL,169,NULL,NULL,1844,2403,1124,55,1131,1671,1444,651,118,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `routineante` ENABLE KEYS */;

--
-- Table structure for table `rpr`
--

DROP TABLE IF EXISTS `rpr`;
CREATE TABLE `rpr` (
  `id` bigint(20) NOT NULL default '0',
  `rpr_date` date default NULL,
  `rpr_result` int(11) default NULL,
  `rpr_treatment_date` date default NULL,
  `rpr_drug` int(11) default NULL,
  `rpr_dosage` float default NULL,
  `rpr_comments` text,
  `dateRprRequest` date default NULL,
  `partner_treatment` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK1B9F4D1B` (`id`),
  CONSTRAINT `FK1B9F4D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rpr`
--


/*!40000 ALTER TABLE `rpr` DISABLE KEYS */;
LOCK TABLES `rpr` WRITE;
INSERT INTO `rpr` VALUES (22,'2007-08-14',2784,'2007-08-14',2787,2.4,NULL,'2007-08-14',NULL),(31,'2007-08-14',2785,'2007-08-14',2787,2.4,NULL,'2007-08-14',NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `rpr` ENABLE KEYS */;

--
-- Table structure for table `rule_definition`
--

DROP TABLE IF EXISTS `rule_definition`;
CREATE TABLE `rule_definition` (
  `id` bigint(20) NOT NULL auto_increment,
  `rule_class` varchar(255) NOT NULL default '',
  `outcome_class` varchar(255) NOT NULL default '',
  `form_id` bigint(20) default NULL,
  `field_id` bigint(20) default NULL,
  `is_enabled` tinyint(1) NOT NULL default '0',
  `last_modified` datetime default NULL,
  `created` datetime default NULL,
  `last_modified_by` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `site_id` bigint(20) default NULL,
  `message` varchar(255) default NULL,
  `operand` varchar(255) default NULL,
  `operator` varchar(255) default NULL,
  `all_pregs` tinyint(1) default '0',
  PRIMARY KEY  (`id`),
  KEY `FKEAD6FE76C8A07680` (`field_id`),
  KEY `FKEAD6FE763E8F4D64` (`last_modified_by`),
  KEY `FKEAD6FE767D2B7913` (`site_id`),
  KEY `FKEAD6FE7651A3A90E` (`created_by`),
  KEY `FKEAD6FE76D79EFE76` (`form_id`),
  CONSTRAINT `FKEAD6FE763E8F4D64` FOREIGN KEY (`last_modified_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FKEAD6FE7651A3A90E` FOREIGN KEY (`created_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FKEAD6FE767D2B7913` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rule_definition`
--


/*!40000 ALTER TABLE `rule_definition` DISABLE KEYS */;
LOCK TABLES `rule_definition` WRITE;
INSERT INTO `rule_definition` VALUES (1,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,145,1,'2004-05-01 17:25:42','2004-05-01 17:25:42','zepadmin','zepadmin',NULL,'The value entered is dangerously high - we recommend this patient be referred to UTH at once.','5','gt',0),(2,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.EncounterOutcome',2,146,1,'2004-05-02 11:14:36','2004-05-02 11:14:36','zepadmin','zepadmin',NULL,NULL,'5','gt',0),(3,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.ReferralOutcome',NULL,286,1,'2004-10-28 19:02:58','2004-10-28 19:02:58','zepadmin','zepadmin',1,'Referral to UTH selected in form','1','eq',0),(4,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.ReferralOutcome',NULL,591,0,'2004-10-28 19:05:06','2004-10-28 19:05:06','zepadmin','zepadmin',1,'Referral to UTH selected in form',NULL,NULL,0),(5,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,118,1,'2004-10-28 19:08:00','2004-10-28 19:08:00','zepadmin','zepadmin',NULL,'Previous pelvic operations: Medical/Surgical History - Consider referral to UTH.','1','eq',0),(6,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,159,1,'2004-10-28 19:09:30','2004-10-28 19:09:30','zepadmin','zepadmin',NULL,'Small stature (<= 150 cm) : Consider referral to UTH.','145','lte',0),(7,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,165,1,'2005-04-04 16:26:56','2004-10-28 19:11:02','demo','zepadmin',1,'Consider referral to UTH if Thyroid is not \"Normal\"','0','ne',0),(8,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,226,1,'2004-10-28 19:15:24','2004-10-28 19:15:24','zepadmin','zepadmin',NULL,'Temperature is >38','38','gt',0),(9,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,229,1,'2005-04-04 16:03:33','2004-10-28 19:16:30','demo','zepadmin',1,'Foetal Heart is Irregular: Consider referral to UTH','1','eq',0),(10,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.ReferralOutcome',NULL,1266,1,'2005-04-05 10:26:25','2004-11-10 15:18:07','demo','zepadmin',1,'Problem or Labor Referral','3','eq',0),(11,'org.cidrz.webapp.dynasite.rules.impl.IsNull','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,1266,1,'2004-11-10 16:52:04','2004-11-10 15:22:44','zepadmin','zepadmin',NULL,'Incomplete disposition during Problem/Labor Visit','null','eq',0),(12,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,225,1,'2005-04-04 15:58:00','2005-04-04 07:53:02','demo','demo',1,'Diastolic blood pressure is >= 110: Consider referral to UTH','7','gte',0),(13,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,1181,1,'2005-04-04 08:50:09','2005-04-04 08:50:09','demo','demo',1,'Baby has not passed urine (Newborn Evaluation).','0','eq',0),(14,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,510,1,'2005-04-04 09:17:56','2005-04-04 09:17:56','demo','demo',1,'Final Apgar Score is less than 6','6','lt',0),(15,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,266,1,'2005-04-04 12:13:27','2005-04-04 12:06:34','demo','demo',1,'Temperature is below 35.0','35.0','lt',0),(16,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,266,1,'2005-04-04 12:17:05','2005-04-04 12:17:05','demo','demo',1,'Temperature is above 37.5.','37.5','gt',0),(17,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,72,1,'2005-04-04 16:42:37','2005-04-04 16:42:37','demo','demo',1,'Diabetes: Medical/Surgical History: Consider referral to UTH.','1','eq',0),(18,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,74,1,'2005-04-04 18:07:12','2005-04-04 17:25:32','demo','demo',1,'Hypertension: Medical/Surgical History - Consider referral to UTH.','1','eq',0),(19,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,75,1,'2005-04-04 18:01:22','2005-04-04 18:01:22','demo','demo',1,'Heart disease: Medical/Surgical History - Consider referral to UTH.','1','eq',0),(20,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,76,1,'2005-04-04 18:31:32','2005-04-04 18:11:50','demo','demo',1,'Rheumatic heart disease: Medical/Surgical History - Consider referral to UTH.','1','eq',0),(21,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,81,1,'2005-04-04 18:30:58','2005-04-04 18:30:58','demo','demo',1,'Kidney Disease: Medical/Surgical History - Consider referral to UTH.','1','eq',0),(22,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,83,1,'2005-04-04 18:32:59','2005-04-04 18:32:59','demo','demo',1,'Liver disease: Medical/Surgical History - Consider referral to UTH.','1','eq',0),(23,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,84,1,'2005-04-04 18:33:31','2005-04-04 18:33:31','demo','demo',1,'DVT/clots: Medical/Surgical History - Consider referral to UTH.','1','eq',0),(24,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,87,1,'2005-04-04 18:34:18','2005-04-04 18:34:18','demo','demo',1,'Sickle cell disease: Medical/Surgical History - Consider referral to UTH.','1','eq',0),(27,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,196,1,'2005-04-04 18:59:36','2005-04-04 18:43:16','demo','demo',1,'Rh- Lab Result','0','eq',0),(28,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,199,1,'2005-04-04 18:59:09','2005-04-04 18:45:51','demo','demo',1,'RPR Result: Reactive (positive)','1','eq',0),(29,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,235,1,'2005-04-04 19:38:46','2005-04-04 18:47:58','demo','demo',1,'Hb Lab Results: Hb below 10','10','lt',0),(30,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,207,1,'2005-04-04 18:57:44','2005-04-04 18:50:38','demo','demo',1,'Cervical Smear Lab Test: Positive','2','eq',0),(31,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,211,1,'2005-04-04 18:57:24','2005-04-04 18:52:12','demo','demo',1,'HIV Result','1','eq',0),(32,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,1460,1,'2005-04-04 18:56:30','2005-04-04 18:54:17','demo','demo',1,'Sickle Cell Test Results: Patient has condition.','0','ne',0),(33,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,1462,1,'2005-04-04 18:56:09','2005-04-04 18:56:09','demo','demo',1,'Malaria Test Results: positive','1','eq',0),(34,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.ReferralOutcome',NULL,582,1,'2005-04-05 10:24:04','2005-04-05 10:24:04','demo','demo',1,'Maternal Discharge Referral','1','eq',0),(35,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,491,1,'2005-04-05 11:32:31','2005-04-05 11:32:31','demo','demo',1,'Birth weight is below 2.0 Kg.','2.0','lt',0),(36,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,491,1,'2005-04-05 11:33:02','2005-04-05 11:33:02','demo','demo',1,'Birth weight is above 4.5 Kg.','4.5','gt',0),(37,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,1516,1,'2005-04-05 12:23:14','2005-04-05 12:23:14','demo','demo',1,'Baby received Nevirapine.','5','eq',0),(38,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,1517,1,'2005-04-05 12:23:42','2005-04-05 12:23:42','demo','demo',1,'Baby received Nevirapine.','5','eq',0),(39,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,1518,1,'2005-04-05 12:24:08','2005-04-05 12:24:08','demo','demo',1,'Baby received Nevirapine.','5','eq',0),(40,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,1519,1,'2005-04-05 12:24:34','2005-04-05 12:24:34','demo','demo',1,'Baby received Nevirapine.','5','eq',0),(41,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,1520,1,'2005-04-05 12:24:59','2005-04-05 12:24:59','demo','demo',1,'Baby received Nevirapine.','5','eq',0),(42,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,1654,1,'2005-05-29 19:15:25','2005-05-29 19:13:43','demo','demo',1,'Tuberculosis: Consider referral to UTH.','0','eq',0),(43,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.ReferralOutcome',NULL,1761,1,'2005-08-03 13:10:51','2005-08-03 13:10:51','demo','demo',1,'Observations for Latent Phase/Labour Referral','2','eq',0),(44,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.ReferralOutcome',NULL,1816,1,'2005-08-29 15:59:33','2005-08-29 15:59:33','cekelley01','cekelley01',1,'Infant Problem/Postnatal Referral','0','eq',0),(46,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,78,1,'2005-09-05 15:50:28','2005-09-05 15:50:28','cekelley01','cekelley01',1,'Tuberculosis: Medical/Surgical History : Consider referral to UTH','1','eq',0),(47,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,58,1,'2005-09-30 11:14:51','2005-09-30 11:14:51','cekelley01','cekelley01',1,'Previous C/S Delivery','1','eq',0),(48,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.ReferralOutcome',NULL,1782,1,'2005-10-07 16:12:14','2005-10-07 16:12:14','cekelley01','cekelley01',1,'Maternal Problem/Postnatal Referral','1','eq',0),(49,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.ReferralOutcome',NULL,1655,1,'2005-10-10 18:20:44','2005-10-10 18:20:44','cekelley01','cekelley01',1,'Puerperium Referral','1','eq',0),(50,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.ReferralOutcome',NULL,1507,1,'2005-10-10 22:32:13','2005-10-10 22:32:13','cekelley01','cekelley01',1,'Problem or Labor Referral','6','eq',0),(51,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,535,1,'2005-10-17 19:06:07','2005-10-17 19:06:07','zepadmin','zepadmin',1,'Baby has not had a Bowel Movement (Newborn Evaluation).','0','eq',0),(52,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,510,1,'2005-10-17 19:10:15','2005-10-17 19:10:15','zepadmin','zepadmin',1,'Low Apgar Score','7','lt',0),(53,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,680,1,'2005-10-17 19:21:03','2005-10-17 19:21:03','zepadmin','zepadmin',1,'Temperature is below 36.5.',' 36.5','lt',0),(54,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,680,1,'2005-10-17 19:21:57','2005-10-17 19:21:57','zepadmin','zepadmin',1,'Temperature is above 37.5.','37.5','gt',0),(55,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,518,1,'2005-10-17 19:34:09','2005-10-17 19:34:09','zepadmin','zepadmin',1,'Baby is not breast feeding well.','0','eq',0),(56,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.ReferralOutcome',NULL,1266,1,'2005-10-18 12:53:53','2005-10-18 12:53:37','zepadmin','zepadmin',1,'Patient admitted to UTH','4','eq',0),(57,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.ReferralOutcome',NULL,1507,1,'2005-10-18 13:11:12','2005-10-18 13:11:12','zepadmin','zepadmin',1,'Patient admitted to UTH','5','eq',0),(58,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.ReferralOutcome',NULL,1782,1,'2005-10-18 13:12:28','2005-10-18 13:12:28','zepadmin','zepadmin',1,'Patient admitted to UTH','3','eq',0),(59,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.ReferralOutcome',NULL,1816,1,'2005-10-18 13:13:15','2005-10-18 13:13:15','zepadmin','zepadmin',1,'Patient admitted to UTH','4','eq',0),(61,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,1677,1,'2005-10-19 11:26:22','2005-10-19 11:26:22','zepadmin','zepadmin',1,'Safe Motherhood: prior Reactive test result','1','eq',0),(62,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,1847,1,'2005-10-19 12:48:33','2005-10-19 12:48:33','zepadmin','zepadmin',1,'Rh- Lab Result','5','eq',0),(63,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,1858,1,'2005-10-19 12:53:49','2005-10-19 12:53:49','zepadmin','zepadmin',1,'Hb below 10','10','lt',0),(65,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,1847,1,'2005-10-19 13:01:17','2005-10-19 13:01:17','zepadmin','zepadmin',1,'Cervical smear abnormal (positive).','8','eq',0),(67,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,1847,1,'2005-10-19 13:04:53','2005-10-19 13:04:53','zepadmin','zepadmin',1,'Sicle Cell Anaemia test result','10','eq',0),(68,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,1847,1,'2005-10-19 13:08:56','2005-10-19 13:08:56','zepadmin','zepadmin',1,'Sickle Cell Trait in lab test','11','eq',0),(69,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,1563,1,'2005-10-19 13:11:35','2005-10-19 13:11:35','zepadmin','zepadmin',1,'RPR Result: Positive (reactive)','1','eq',0),(70,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,1866,1,'2005-10-19 13:14:01','2005-10-19 13:14:01','zepadmin','zepadmin',1,'Lab Result: Reactive','1','eq',0),(71,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,244,1,'2005-10-19 13:19:41','2005-10-19 13:19:41','zepadmin','zepadmin',1,'Lab Results: Urinalysis Albumin >= 1+','2','gte',0),(72,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,242,1,'2005-10-19 13:20:13','2005-10-19 13:20:13','zepadmin','zepadmin',1,'Lab Results: Urinalysis Glucose > 1+.','2','gt',0),(73,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,243,1,'2005-10-19 13:20:35','2005-10-19 13:20:35','zepadmin','zepadmin',1,'Lab Results: Urinalysis Acetone > 1+.','2','eq',0),(74,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,1563,1,'2005-10-19 13:23:00','2005-10-19 13:23:00','zepadmin','zepadmin',1,'Lab Results: RPR Test not done.','2','eq',0),(75,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,244,1,'2005-10-19 13:28:17','2005-10-19 13:28:17','zepadmin','zepadmin',1,'Urinalysis Glucose >= 2+ - Need to request diabetes test.','3','gte',0),(76,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,242,1,'2005-10-19 13:28:49','2005-10-19 13:28:49','zepadmin','zepadmin',1,'Urinalysis Acetone >= 2+ - Need to request diabetes test.','3','gte',0),(78,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,243,1,'2005-10-19 13:30:41','2005-10-19 13:30:41','zepadmin','zepadmin',1,'Urinalysis Protein >= 2+ - Need to request diabetes test.','3','gte',0),(79,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,243,1,'2005-10-19 13:32:01','2005-10-19 13:32:01','zepadmin','zepadmin',1,'Urinalysis Protein >= 2+ - Need to request diabetes test.','3','gte',0),(80,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.EncounterOutcome',74,1266,1,'2005-10-19 17:47:20','2005-10-19 17:47:20','zepadmin','zepadmin',1,NULL,'4','eq',0),(81,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.EncounterOutcome',63,1507,1,'2005-10-19 17:59:46','2005-10-19 17:59:46','zepadmin','zepadmin',1,NULL,'5','eq',0),(82,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.EncounterOutcome',74,1782,1,'2005-10-20 10:11:17','2005-10-20 10:10:49','zepadmin','zepadmin',1,NULL,'3','eq',0),(83,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.EncounterOutcome',74,1816,1,'2005-10-20 10:57:30','2005-10-20 10:57:30','zepadmin','zepadmin',1,NULL,'4','eq',0),(84,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,66,1,'2005-10-20 11:22:04','2005-10-20 11:22:04','zepadmin','zepadmin',1,'Patient had Postpartum Infection.','11','ne',0),(85,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,584,1,'2005-10-20 11:23:22','2005-10-20 11:23:22','zepadmin','zepadmin',1,'Patient had Postpartum Complications.','0','ne',0),(86,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,1654,1,'2005-10-20 11:29:39','2005-10-20 11:29:39','zepadmin','zepadmin',1,'Maternal Death selected upon disposition in Maternal Discharge Summary.','2','eq',0),(87,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.ReferralOutcome',NULL,1654,1,'2005-10-20 11:32:17','2005-10-20 11:32:17','zepadmin','zepadmin',1,'Maternal Discharge Referral','0','eq',0),(89,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.EncounterOutcome',40,493,1,'2005-10-20 11:39:39','2005-10-20 11:39:39','zepadmin','zepadmin',1,NULL,'0','gt',0),(90,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,1756,1,'2005-10-20 11:46:51','2005-10-20 11:46:51','zepadmin','zepadmin',1,'Eclampsia noted in pregnancy (previous or current).','1','eq',1),(91,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,1757,1,'2005-10-20 11:47:35','2005-10-20 11:47:35','zepadmin','zepadmin',1,'Abruptia Placenta noted in Problem or Labor Visit.','1','eq',1),(92,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,98,1,'2005-10-20 11:54:33','2005-10-20 11:54:33','zepadmin','zepadmin',1,'Drug allergies noted in Medical/Surgical History.','1','eq',0),(93,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,966,1,'2005-10-20 11:56:11','2005-10-20 11:56:11','zepadmin','zepadmin',1,'Abnormalities detected in ultrasound.','1','eq',0),(94,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,1473,1,'2005-10-20 11:56:56','2005-10-20 11:56:56','zepadmin','zepadmin',1,'Abnormalities detected in ultrasound.','1','gt',0),(95,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,224,1,'2005-11-01 13:03:55','2005-11-01 13:03:55','zepadmin','zepadmin',1,'Systolic blood pressure is >= 150: Consider referral to UTH','9','gte',0),(96,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,447,1,'2005-11-01 13:25:42','2005-11-01 13:25:42','zepadmin','zepadmin',1,'Patient had a C/S delivery: Consider referral to UTH','1','eq',1),(97,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,70,1,'2005-11-01 13:29:35','2005-11-01 13:29:35','zepadmin','zepadmin',1,'Patient has epilepsy: Consider referral to UTH.','1','eq',0),(98,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,230,1,'2005-11-01 14:11:58','2005-11-01 14:11:58','zepadmin','zepadmin',1,'Abnormal Foetal heart rate (not in 110-160 range): Consider referral to UTH.','2','ne',0),(99,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,667,1,'2005-11-01 14:19:06','2005-11-01 14:19:06','zepadmin','zepadmin',1,'Cervix per speculum result is suspicious for malignancy: Consider referral to UTH.','3','eq',0),(100,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,1136,1,'2005-11-09 18:57:05','2005-11-09 18:56:47','zepadmin','zepadmin',1,'Patient is currently taking medicine.','0','gt',0),(101,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,1753,1,'2005-11-10 18:31:11','2005-11-10 18:31:11','zepadmin','zepadmin',1,'Delivery Summary: PPH selected.','1','eq',1),(102,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,71,1,'2005-11-11 14:25:54','2005-11-11 14:25:54','zepadmin','zepadmin',1,'Medical/Surgical History: Thyroid condition.','1','eq',0),(103,'org.cidrz.webapp.dynasite.rules.impl.ScriptRule','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,1939,1,'2005-11-12 13:10:11','2005-11-12 13:10:11','zepadmin','zepadmin',1,'Newborn did not receive nevirapine - mother is HIV+','sessionPatient.isMotherHivPositive() == true && subjectValue ==0','eq',0),(104,'org.cidrz.webapp.dynasite.rules.impl.ScriptRule','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,225,1,'2005-11-12 17:24:34','2005-11-12 17:24:34','zepadmin','zepadmin',1,'BP Diastolic >=90 (Proteinurea) & 2+ oedema','(formId==80 || formId==65) && subjectValue >= 6 && encounterMap.get(\"field231\") >=2',NULL,0),(105,'org.cidrz.webapp.dynasite.rules.impl.ScriptRule','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,225,1,'2005-11-14 16:21:58','2005-11-14 16:21:58','zepadmin','zepadmin',1,'Albumin >= 1+ & BP diastolic >=90. Consider referral to UTH.','(formId==80 || formId==65) && subjectValue >= 6 && encounterMap.get(\"field242\") >=2',NULL,0),(106,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,1927,1,'2005-11-16 10:23:48','2005-11-16 10:23:48','zepadmin','zepadmin',1,'Patient had a C/S delivery: Consider referral to UTH.','1','eq',1),(107,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,79,1,'2005-11-17 13:10:03','2005-11-17 13:09:44','zepadmin','zepadmin',1,'Asthma: Medical/Surgical History - Consider referral to UTH.','1','eq',0),(109,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,82,1,'2005-11-17 13:11:19','2005-11-17 13:11:19','zepadmin','zepadmin',1,'Pyelonephritis: Medical/Surgical History - Consider referral to UTH.','1','eq',0),(110,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,89,1,'2005-11-17 13:12:55','2005-11-17 13:12:55','zepadmin','zepadmin',1,'Genital Herpes: Medical/Surgical History - Consider referral to UTH.','1','eq',0),(111,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,93,1,'2005-11-17 13:13:31','2005-11-17 13:13:31','zepadmin','zepadmin',1,'Hepatitis B: Medical/Surgical History - Consider referral to UTH.','1','eq',0),(112,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,1929,1,'2005-11-17 13:14:15','2005-11-17 13:14:15','zepadmin','zepadmin',1,'HIV: Medical/Surgical History - Consider referral to UTH.','1','eq',0),(114,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,94,1,'2005-11-17 13:15:00','2005-11-17 13:15:00','zepadmin','zepadmin',1,'Syphilis: Medical/Surgical History - Consider referral to UTH.','1','eq',0),(116,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,1548,1,'2005-11-17 13:16:14','2005-11-17 13:16:14','zepadmin','zepadmin',1,'Anaemia: Medical/Surgical History - Consider referral to UTH.','1','eq',0),(117,'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison','org.cidrz.webapp.dynasite.rules.impl.InfoOutcome',NULL,85,1,'2005-11-17 13:16:45','2005-11-17 13:16:45','zepadmin','zepadmin',1,'Malaria: Medical/Surgical History - Consider referral to UTH.','1','eq',0);
UNLOCK TABLES;
/*!40000 ALTER TABLE `rule_definition` ENABLE KEYS */;

--
-- Table structure for table `rule_definition_param`
--

DROP TABLE IF EXISTS `rule_definition_param`;
CREATE TABLE `rule_definition_param` (
  `rule_definition_id` bigint(20) NOT NULL default '0',
  `param_value` varchar(255) default NULL,
  `param_name` varchar(255) NOT NULL default '',
  `outcome_param` tinyint(1) NOT NULL default '0',
  PRIMARY KEY  (`rule_definition_id`,`param_name`),
  KEY `FK6BB4498491175784` (`rule_definition_id`),
  CONSTRAINT `FK6BB4498491175784` FOREIGN KEY (`rule_definition_id`) REFERENCES `rule_definition` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rule_definition_param`
--


/*!40000 ALTER TABLE `rule_definition_param` DISABLE KEYS */;
LOCK TABLES `rule_definition_param` WRITE;
INSERT INTO `rule_definition_param` VALUES (1,'The value entered is dangerously high - we recommend this patient be referred to UTH at once.','message',0),(1,'5','operand',0),(1,'gt','operator',0),(2,'2','formId',0),(2,'5','operand',0),(2,'gt','operator',0),(3,'1','operand',0),(3,'eq','operator',0),(3,'Referral to UTH selected in form','reason',0),(4,'1','operand',0),(4,'eq','operator',0),(4,'Referral to UTH selected in form','reason',0),(5,'Previous pelvic operations: Medical/Surgical History - Consider referral to UTH.','message',0),(5,'1','operand',0),(5,'eq','operator',0),(6,'Small stature (<= 150 cm) : Consider referral to UTH.','message',0),(6,'145','operand',0),(6,'lte','operator',0),(7,'Consider referral to UTH if Thyroid is not \"Normal\"','message',0),(7,'0','operand',0),(7,'ne','operator',0),(8,'Temperature is >38','message',0),(8,'38','operand',0),(8,'gt','operator',0),(9,'Foetal Heart is Irregular: Consider referral to UTH','message',0),(9,'1','operand',0),(9,'eq','operator',0),(10,'3','operand',0),(10,'eq','operator',0),(10,'Problem or Labor Referral','reason',0),(11,'Incomplete disposition during Problem/Labor Visit','message',0),(11,'null','operand',0),(11,'eq','operator',0),(12,'Diastolic blood pressure is >= 110: Consider referral to UTH','message',0),(12,'7','operand',0),(12,'gte','operator',0),(13,'Baby has not passed urine (Newborn Evaluation).','message',0),(13,'0','operand',0),(13,'eq','operator',0),(14,'Final Apgar Score is less than 6','message',0),(14,'6','operand',0),(14,'lt','operator',0),(15,'Temperature is below 35.0','message',0),(15,'35.0','operand',0),(15,'lt','operator',0),(16,'Temperature is above 37.5.','message',0),(16,'37.5','operand',0),(16,'gt','operator',0),(17,'Diabetes: Medical/Surgical History: Consider referral to UTH.','message',0),(17,'1','operand',0),(17,'eq','operator',0),(18,'Hypertension: Medical/Surgical History - Consider referral to UTH.','message',0),(18,'1','operand',0),(18,'eq','operator',0),(19,'Heart disease: Medical/Surgical History - Consider referral to UTH.','message',0),(19,'1','operand',0),(19,'eq','operator',0),(20,'Rheumatic heart disease: Medical/Surgical History - Consider referral to UTH.','message',0),(20,'1','operand',0),(20,'eq','operator',0),(21,'Kidney Disease: Medical/Surgical History - Consider referral to UTH.','message',0),(21,'1','operand',0),(21,'eq','operator',0),(22,'Liver disease: Medical/Surgical History - Consider referral to UTH.','message',0),(22,'1','operand',0),(22,'eq','operator',0),(23,'DVT/clots: Medical/Surgical History - Consider referral to UTH.','message',0),(23,'1','operand',0),(23,'eq','operator',0),(24,'Sickle cell disease: Medical/Surgical History - Consider referral to UTH.','message',0),(24,'1','operand',0),(24,'eq','operator',0),(27,'Rh- Lab Result','message',0),(27,'0','operand',0),(27,'eq','operator',0),(28,'RPR Result: Reactive (positive)','message',0),(28,'1','operand',0),(28,'eq','operator',0),(29,'Hb Lab Results','message',0),(29,'null','operand',0),(29,'ne','operator',0),(30,'Cervical Smear Lab Test: Positive','message',0),(30,'2','operand',0),(30,'eq','operator',0),(31,'HIV Result','message',0),(31,'1','operand',0),(31,'eq','operator',0),(32,'Sickle Cell Test Results: Patient has condition.','message',0),(32,'0','operand',0),(32,'ne','operator',0),(33,'Malaria Test Results: positive','message',0),(33,'1','operand',0),(33,'eq','operator',0),(34,'1','operand',0),(34,'eq','operator',0),(34,'Maternal Discharge Referral','reason',0),(35,'Birth weight is below 2.0 Kg.','message',0),(35,'2.0','operand',0),(35,'lt','operator',0),(36,'Birth weight is above 4.5 Kg.','message',0),(36,'4.5','operand',0),(36,'gt','operator',0),(37,'Baby received Nevirapine.','message',0),(37,'5','operand',0),(37,'eq','operator',0),(38,'Baby received Nevirapine.','message',0),(38,'5','operand',0),(38,'eq','operator',0),(39,'Baby received Nevirapine.','message',0),(39,'5','operand',0),(39,'eq','operator',0),(40,'Baby received Nevirapine.','message',0),(40,'5','operand',0),(40,'eq','operator',0),(41,'Baby received Nevirapine.','message',0),(41,'5','operand',0),(41,'eq','operator',0),(42,'Tuberculosis: Consider referral to UTH.','message',0),(42,'0','operand',0),(42,'eq','operator',0),(43,'2','operand',0),(43,'eq','operator',0),(43,'Observations for Latent Phase/Labour Referral','reason',0),(44,'0','operand',0),(44,'eq','operator',0),(44,'Infant Problem/Postnatal Referral','reason',0),(46,'Tuberculosis: Medical/Surgical History : Consider referral to UTH','message',0),(46,'1','operand',0),(46,'eq','operator',0),(47,'Previous C/S Delivery','message',0),(47,'1','operand',0),(47,'eq','operator',0),(48,'1','operand',0),(48,'eq','operator',0),(48,'Maternal Problem/Postnatal Referral','reason',0),(49,'1','operand',0),(49,'eq','operator',0),(49,'Puerperium Referral','reason',0),(50,'6','operand',0),(50,'eq','operator',0),(50,'Problem or Labor Referral','reason',0),(51,'Baby has not had a Bowel Movement (Newborn Evaluation).','message',0),(51,'0','operand',0),(51,'eq','operator',0),(52,'Low Apgar Score','message',0),(52,'7','operand',0),(52,'lt','operator',0),(53,'Temperature is below 36.5.','message',0),(53,' 36.5','operand',0),(53,'lt','operator',0),(54,'Temperature is above 37.5.','message',0),(54,'37.5','operand',0),(54,'gt','operator',0),(55,'Baby is not breast feeding well.','message',0),(55,'0','operand',0),(55,'eq','operator',0),(56,'4','operand',0),(56,'eq','operator',0),(56,'Patient admitted to UTH','reason',0),(57,'5','operand',0),(57,'eq','operator',0),(57,'Patient admitted to UTH','reason',0),(58,'3','operand',0),(58,'eq','operator',0),(58,'Patient admitted to UTH','reason',0),(59,'4','operand',0),(59,'eq','operator',0),(59,'Patient admitted to UTH','reason',0),(61,'Safe Motherhood: prior Reactive test result','message',0),(61,'1','operand',0),(61,'eq','operator',0),(62,'Rh- Lab Result','message',0),(62,'5','operand',0),(62,'eq','operator',0),(63,'Hb below 10','message',0),(63,'10','operand',0),(63,'lt','operator',0),(65,'Cervical smear abnormal (positive).','message',0),(65,'8','operand',0),(65,'eq','operator',0),(67,'Sicle Cell Anaemia test result','message',0),(67,'10','operand',0),(67,'eq','operator',0),(68,'Sickle Cell Trait in lab test','message',0),(68,'11','operand',0),(68,'eq','operator',0),(69,'RPR Result: Positive (reactive)','message',0),(69,'1','operand',0),(69,'eq','operator',0),(70,'Lab Result: Reactive','message',0),(70,'1','operand',0),(70,'eq','operator',0),(71,'Lab Results: Urinalysis Albumin >= 1+','message',0),(71,'2','operand',0),(71,'gte','operator',0),(72,'Lab Results: Urinalysis Glucose > 1+.','message',0),(72,'2','operand',0),(72,'gt','operator',0),(73,'Lab Results: Urinalysis Acetone > 1+.','message',0),(73,'2','operand',0),(73,'eq','operator',0),(74,'Lab Results: RPR Test not done.','message',0),(74,'2','operand',0),(74,'eq','operator',0),(75,'Urinalysis Glucose >= 2+ - Need to request diabetes test.','message',0),(75,'3','operand',0),(75,'gte','operator',0),(76,'Urinalysis Acetone >= 2+ - Need to request diabetes test.','message',0),(76,'3','operand',0),(76,'gte','operator',0),(78,'Urinalysis Protein >= 2+ - Need to request diabetes test.','message',0),(78,'3','operand',0),(78,'gte','operator',0),(79,'Urinalysis Protein >= 2+ - Need to request diabetes test.','message',0),(79,'3','operand',0),(79,'gte','operator',0),(80,'74','formId',0),(80,'4','operand',0),(80,'eq','operator',0),(81,'63','formId',0),(81,'5','operand',0),(81,'eq','operator',0),(82,'74','formId',0),(82,'3','operand',0),(82,'eq','operator',0),(83,'74','formId',0),(83,'4','operand',0),(83,'eq','operator',0),(84,'Patient had Postpartum Infection.','message',0),(84,'11','operand',0),(84,'ne','operator',0),(85,'Patient had Postpartum Complications.','message',0),(85,'0','operand',0),(85,'ne','operator',0),(86,'Maternal Death selected upon disposition in Maternal Discharge Summary.','message',0),(86,'2','operand',0),(86,'eq','operator',0),(87,'0','operand',0),(87,'eq','operator',0),(87,'Maternal Discharge Referral','reason',0),(89,'40','formId',0),(89,'0','operand',0),(89,'gt','operator',0),(90,'Eclampsia noted in pregnancy (previous or current).','message',0),(90,'1','operand',0),(90,'eq','operator',0),(91,'Abruptia Placenta noted in Problem or Labor Visit.','message',0),(91,'1','operand',0),(91,'eq','operator',0),(92,'Drug allergies noted in Medical/Surgical History.','message',0),(92,'1','operand',0),(92,'eq','operator',0),(93,'Abnormalities detected in ultrasound.','message',0),(93,'1','operand',0),(93,'eq','operator',0),(94,'Abnormalities detected in ultrasound.','message',0),(94,'1','operand',0),(94,'gt','operator',0),(95,'Systolic blood pressure is >= 150: Consider referral to UTH','message',0),(95,'9','operand',0),(95,'gte','operator',0),(96,'Patient had a C/S delivery: Consider referral to UTH','message',0),(96,'1','operand',0),(96,'eq','operator',0),(97,'Patient has epilepsy: Consider referral to UTH.','message',0),(97,'1','operand',0),(97,'eq','operator',0),(98,'Abnormal Foetal heart rate (not in 110-160 range): Consider referral to UTH.','message',0),(98,'2','operand',0),(98,'ne','operator',0),(99,'Cervix per speculum result is suspicious for malignancy: Consider referral to UTH.','message',0),(99,'3','operand',0),(99,'eq','operator',0),(100,'Patient is currently taking medicine.','message',0),(100,'0','operand',0),(100,'gt','operator',0),(101,'Delivery Summary: PPH selected.','message',0),(101,'1','operand',0),(101,'eq','operator',0),(102,'Medical/Surgical History: Thyroid condition.','message',0),(102,'1','operand',0),(102,'eq','operator',0),(103,'Newborn did not receive nevirapine - mother is HIV+','message',0),(103,'sessionPatient.isMotherHivPositive() == true && subjectValue ==0','operand',0),(103,'eq','operator',0),(104,'BP Diastolic >=90 (Proteinurea) & 2+ oedema','message',0),(104,'(formId==80 || formId==65) && subjectValue >= 6 && encounterMap.get(\"field231\") >=2','operand',0),(105,'Albumin >= 1+ & BP diastolic >=90. Consider referral to UTH.','message',0),(105,'(formId==80 || formId==65) && subjectValue >= 6 && encounterMap.get(\"field242\") >=2','operand',0),(106,'Patient had a C/S delivery: Consider referral to UTH.','message',0),(106,'1','operand',0),(106,'eq','operator',0),(107,'Asthma: Medical/Surgical History - Consider referral to UTH.','message',0),(107,'1','operand',0),(107,'eq','operator',0),(109,'Pyelonephritis: Medical/Surgical History - Consider referral to UTH.','message',0),(109,'1','operand',0),(109,'eq','operator',0),(110,'Genital Herpes: Medical/Surgical History - Consider referral to UTH.','message',0),(110,'1','operand',0),(110,'eq','operator',0),(111,'Hepatitis B: Medical/Surgical History - Consider referral to UTH.','message',0),(111,'1','operand',0),(111,'eq','operator',0),(112,'HIV: Medical/Surgical History - Consider referral to UTH.','message',0),(112,'1','operand',0),(112,'eq','operator',0),(114,'Syphilis: Medical/Surgical History - Consider referral to UTH.','message',0),(114,'1','operand',0),(114,'eq','operator',0),(116,'Anaemia: Medical/Surgical History - Consider referral to UTH.','message',0),(116,'1','operand',0),(116,'eq','operator',0),(117,'Malaria: Medical/Surgical History - Consider referral to UTH.','message',0),(117,'1','operand',0),(117,'eq','operator',0);
UNLOCK TABLES;
/*!40000 ALTER TABLE `rule_definition_param` ENABLE KEYS */;

--
-- Table structure for table `safe_motherhood`
--

DROP TABLE IF EXISTS `safe_motherhood`;
CREATE TABLE `safe_motherhood` (
  `id` bigint(20) NOT NULL default '0',
  `rpr1_date` date default NULL,
  `rpr1_result` int(11) default NULL,
  `rpr1_treatment_date` date default NULL,
  `rpr1_drug` int(11) default NULL,
  `rpr1_dosage` int(11) default NULL,
  `rpr2_date` date default NULL,
  `rpr2_result` int(11) default NULL,
  `rpr2_treatment_date` date default NULL,
  `rpr2_drug` int(11) default NULL,
  `rpr2_dosage` int(11) default NULL,
  `childhood_dp_immun_107` tinyint(1) default NULL,
  `tt1_110` date default NULL,
  `tt2_111` date default NULL,
  `tt3_112` date default NULL,
  `tt4_113` date default NULL,
  `tt5_114` date default NULL,
  `iron_given` tinyint(1) default NULL,
  `iron_given_date` date default NULL,
  `iron_not_given_reason` varchar(255) default NULL,
  `folic_acid_given` tinyint(1) default NULL,
  `folic_acid_date` date default NULL,
  `folic_acid_not_given_reason` varchar(255) default NULL,
  `malaria_prof_given` tinyint(1) default NULL,
  `malaria_prof_not_given_reason` varchar(255) default NULL,
  `ipt1_date` date default NULL,
  `ipt2_date` date default NULL,
  `ipt3_date` date default NULL,
  `deworming_med_given` tinyint(1) default NULL,
  `deworming_med_date` date default NULL,
  `deworming_med_not_given_reason` varchar(255) default NULL,
  `pcr1` tinyint(1) default NULL,
  `pcr1_date` date default NULL,
  `pcr2` tinyint(1) default NULL,
  `pcr2_date` date default NULL,
  `pca` tinyint(1) default NULL,
  `pca_date` date default NULL,
  `status` int(11) default NULL,
  `rmd` tinyint(1) default NULL,
  `rmd_regimen` int(11) default NULL,
  `syringe_given_for_home_mother` tinyint(1) default NULL,
  `rbd_home` tinyint(1) default NULL,
  `syringe_given_for_home_baby` tinyint(1) default NULL,
  `rbd_home_regimen` int(11) default NULL,
  `rbd_home_dosage` int(11) default NULL,
  `rbd_ld` tinyint(1) default NULL,
  `rbd_ld_regimen` int(11) default NULL,
  `rbd_ld_dosage` varchar(255) default NULL,
  `hb1_date` date default NULL,
  `hb1_result` int(11) default NULL,
  `hb1_treatment` varchar(255) default NULL,
  `hb2_date` date default NULL,
  `hb2_result` int(11) default NULL,
  `hb2_treatment` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKD415E251D1B` (`id`),
  CONSTRAINT `FKD415E251D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `safe_motherhood`
--


/*!40000 ALTER TABLE `safe_motherhood` DISABLE KEYS */;
LOCK TABLES `safe_motherhood` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `safe_motherhood` ENABLE KEYS */;

--
-- Table structure for table `safemotherhood`
--

DROP TABLE IF EXISTS `safemotherhood`;
CREATE TABLE `safemotherhood` (
  `id` bigint(20) NOT NULL default '0',
  `rpr1_date` date default NULL,
  `rpr1_result` int(11) default NULL,
  `rpr1_treatment_date` date default NULL,
  `rpr1_drug` int(11) default NULL,
  `rpr1_dosage` int(11) default NULL,
  `rpr2_date` date default NULL,
  `rpr2_result` int(11) default NULL,
  `rpr2_treatment_date` date default NULL,
  `rpr2_drug` int(11) default NULL,
  `rpr2_dosage` int(11) default NULL,
  `hb1_date` date default NULL,
  `hb1_result` int(11) default NULL,
  `hb1_treatment` text,
  `hb2_date` date default NULL,
  `hb2_result` int(11) default NULL,
  `hb2_treatment` text,
  `childhood_dp_immun_107` tinyint(1) default NULL,
  `tt1_110` date default NULL,
  `tt2_111` date default NULL,
  `tt3_112` date default NULL,
  `tt4_113` date default NULL,
  `tt5_114` date default NULL,
  `iron_given` tinyint(1) default NULL,
  `iron_given_date` date default NULL,
  `iron_not_given_reason` varchar(255) default NULL,
  `folic_acid_given` tinyint(1) default NULL,
  `folic_acid_date` date default NULL,
  `folic_acid_not_given_reason` varchar(255) default NULL,
  `malaria_prof_given` tinyint(1) default NULL,
  `malaria_prof_not_given_reason` varchar(255) default NULL,
  `ipt1_date` date default NULL,
  `ipt2_date` date default NULL,
  `ipt3_date` date default NULL,
  `deworming_med_given` tinyint(1) default NULL,
  `deworming_med_date` date default NULL,
  `deworming_med_not_given_reason` varchar(255) default NULL,
  `pcr1` tinyint(1) default NULL,
  `pcr1_date` date default NULL,
  `pcr2` tinyint(1) default NULL,
  `pcr2_date` date default NULL,
  `pca` tinyint(1) default NULL,
  `pca_date` date default NULL,
  `status` int(11) default NULL,
  `rmd` tinyint(1) default NULL,
  `rmd_regimen` int(11) default NULL,
  `syringe_given_for_home_mother` tinyint(1) default NULL,
  `rbd_home` tinyint(1) default NULL,
  `syringe_given_for_home_baby` tinyint(1) default NULL,
  `rbd_home_regimen` int(11) default NULL,
  `rbd_home_dosage` int(11) default NULL,
  `rbd_ld` tinyint(1) default NULL,
  `rbd_ld_regimen` int(11) default NULL,
  `rbd_ld_dosage` varchar(255) default NULL,
  `blood_type_date` date default NULL,
  `abo_group_193` int(11) default NULL,
  `rhesus_status_date` date default NULL,
  `rhesus_196` int(11) default NULL,
  `cervical_smear_date` date default NULL,
  `cervical_smell_207` int(11) default NULL,
  `sickle_cell_screen_date` date default NULL,
  `sickle_cell_screen` int(11) default NULL,
  `malaria_test_date` date default NULL,
  `malaria_test_results` int(11) default NULL,
  `blood_type_comment` text,
  `rhesus_comment` text,
  `cervical_smear_comment` text,
  `sickle_cell_screen_comment` text,
  `malaria_test_comment` text,
  `prior_hiv_testing` tinyint(1) default NULL,
  `priot_hiv_testing_date` date default NULL,
  `prior_hiv_test_result` int(11) default NULL,
  `hiv_test_result1` int(11) default NULL,
  `hiv_test_result2` int(11) default NULL,
  `hiv_test_result3` int(11) default NULL,
  `malaria1_date` date default NULL,
  `malaria_drug1` int(11) default NULL,
  `field1700` varchar(255) default NULL,
  `date_malaria2` date default NULL,
  `malaria_prof_given2` tinyint(1) default NULL,
  `malaria_prof_not_given_reason2` int(11) default NULL,
  `malaria_drug2` int(11) default NULL,
  `date_malaria3` date default NULL,
  `malaria_prof_given3` tinyint(1) default NULL,
  `malaria_prof_not_given_reason3` int(11) default NULL,
  `malaria_drug3` int(11) default NULL,
  `deworming_med_given1` tinyint(1) default NULL,
  `deworming_med_not_given_reason1` int(11) default NULL,
  `deworming_drug1` int(11) default NULL,
  `deworming_med_given2` tinyint(1) default NULL,
  `deworming_med_not_given_reason2` int(11) default NULL,
  `deworming_med_date2` date default NULL,
  `deworming_drug2` int(11) default NULL,
  `deworming_med_given3` tinyint(1) default NULL,
  `deworming_med_not_given_reason3` int(11) default NULL,
  `deworming_med_date3` date default NULL,
  `deworming_drug3` int(11) default NULL,
  `hiv_test_1_date` date default NULL,
  `hiv_test_2_date` date default NULL,
  `hiv_test_3_date` date default NULL,
  `date_rmd` date default NULL,
  `date_rbd_home` date default NULL,
  `date_rbd_labour` date default NULL,
  `patient_sleep_ITN` tinyint(4) default NULL,
  `received_results_1` tinyint(4) default NULL,
  `received_results_2` tinyint(4) default NULL,
  `received_results_3` tinyint(4) default NULL,
  `rpr_comments_1` text,
  `rpr_comments_2` text,
  PRIMARY KEY  (`id`),
  KEY `FK349C09ACD1B` (`id`),
  CONSTRAINT `FK349C09ACD1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `safemotherhood`
--


/*!40000 ALTER TABLE `safemotherhood` DISABLE KEYS */;
LOCK TABLES `safemotherhood` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `safemotherhood` ENABLE KEYS */;

--
-- Table structure for table `safemotherhoodcare`
--

DROP TABLE IF EXISTS `safemotherhoodcare`;
CREATE TABLE `safemotherhoodcare` (
  `id` bigint(20) NOT NULL default '0',
  `prior_hiv_testing` tinyint(4) default NULL,
  `prior_hiv_testing_date` date default NULL,
  `prior_hiv_test_result` int(11) default NULL,
  `patient_sleep_ITN` tinyint(4) default NULL,
  `rbd_home` tinyint(4) default NULL,
  `childhood_dp_immun_107` tinyint(4) default NULL,
  `tt1_110` date default NULL,
  `tt2_111` date default NULL,
  `tt3_112` date default NULL,
  `tt4_113` date default NULL,
  `tt5_114` date default NULL,
  `rbd_home_dosage` int(11) default NULL,
  `rbd_home_regimen` int(11) default NULL,
  `rbd_ld` tinyint(4) default NULL,
  `rbd_ld_dosage` int(11) default NULL,
  `rbd_ld_regimen` int(11) default NULL,
  `tt1_done` tinyint(4) default NULL,
  `tt2_done` tinyint(4) default NULL,
  `tt3_done` tinyint(4) default NULL,
  `tt4_done` tinyint(4) default NULL,
  `tt5_done` tinyint(4) default NULL,
  `rbd_home_administerred` tinyint(4) default NULL,
  `hiv_test_result` int(11) default NULL,
  `regimen_delivery` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKF15CEBDD1B` (`id`),
  CONSTRAINT `FKF15CEBDD1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `safemotherhoodcare`
--


/*!40000 ALTER TABLE `safemotherhoodcare` DISABLE KEYS */;
LOCK TABLES `safemotherhoodcare` WRITE;
INSERT INTO `safemotherhoodcare` VALUES (8,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(21,1,NULL,NULL,NULL,NULL,NULL,'2007-08-14','2007-08-14','2007-08-14','2007-08-14','2007-08-14',NULL,NULL,NULL,NULL,NULL,1,1,1,1,1,NULL,NULL,NULL),(30,1,NULL,NULL,NULL,NULL,NULL,'2007-08-14',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `safemotherhoodcare` ENABLE KEYS */;

--
-- Table structure for table `site`
--

DROP TABLE IF EXISTS `site`;
CREATE TABLE `site` (
  `id` bigint(20) NOT NULL auto_increment,
  `site_alpha_id` varchar(255) NOT NULL default '',
  `district_id` varchar(255) NOT NULL default '',
  `abbrev` varchar(255) NOT NULL default '',
  `site_name` varchar(255) NOT NULL default '',
  `site_type_id` int(11) default NULL,
  `inactive` tinyint(1) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `site`
--


/*!40000 ALTER TABLE `site` DISABLE KEYS */;
LOCK TABLES `site` WRITE;
INSERT INTO `site` VALUES (1,'332','5040','AIR','Airport',1,NULL),(2,'102','5040','BAU','Bauleni',1,NULL),(3,'352','5040','CHH','Chainama',1,NULL),(4,'112','5040','CAD','Chainda',1,NULL),(5,'122','5040','CWM','Chawama',1,NULL),(6,'342','5040','CZG','Chazanga',1,NULL),(7,'132','5040','CHL','Chelstone',1,NULL),(9,'142','5040','CLJ','Chilenje',1,NULL),(10,'152','5040','CPT','Chipata',1,NULL),(11,'162','5040','CVC','Civic Centre',1,NULL),(13,'172','5040','GOG','George',1,NULL),(15,'182','5040','KBT','Kabwata',1,NULL),(16,'192','5040','KAL','Kalingalinga',1,NULL),(17,'202','5040','KML','Kamwala',1,NULL),(18,'212','5040','KNY','Kanyama',1,NULL),(20,'222','5040','KSQ','Kaunda Square',1,NULL),(22,'232','5040','LLY','Lilayi',1,NULL),(24,'242','5040','MKN','Makeni',1,NULL),(25,'252','5040','MDV','Mandevu',1,NULL),(26,'262','5040','MTM','Matero Main',1,NULL),(27,'272','5040','MTR','Matero Reference',1,NULL),(29,'282','5040','MTD','Mtendere',1,NULL),(30,'292','5040','NGO','Ng`ombe',1,NULL),(32,'302','5040','PRI','Prisons',1,NULL),(33,'312','5040','RAI','Railway',1,NULL),(34,'322','5040','STL','State Lodge',1,NULL),(36,'002','5040','OTHER','Other',1,NULL),(37,'021','8070','CMU','Chikankata Mukabuumi',1,1),(38,'02A','5040','UTHA','UTH A block',2,NULL),(39,'02B','5040','UTHB','UTH B block',2,NULL),(40,'02C','5040','UTHC','UTH C block',2,NULL),(41,'02D','5040','UTHD','UTH D block',2,NULL),(42,'025','5040','UTH-5','old UTH-5',2,1),(43,'026','5040','pl1','placeholder',2,1),(45,'024','5040','UTHP','UTH-Peds ARV',2,NULL),(46,'025','5040','UTHR','UTH-Adult ARV',2,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `site` ENABLE KEYS */;

--
-- Table structure for table `site_type`
--

DROP TABLE IF EXISTS `site_type`;
CREATE TABLE `site_type` (
  `id` int(11) NOT NULL default '0',
  `name` varchar(8) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `site_type`
--


/*!40000 ALTER TABLE `site_type` DISABLE KEYS */;
LOCK TABLES `site_type` WRITE;
INSERT INTO `site_type` VALUES (1,'clinic'),(2,'uth');
UNLOCK TABLES;
/*!40000 ALTER TABLE `site_type` ENABLE KEYS */;

--
-- Table structure for table `smcounselingvisit`
--

DROP TABLE IF EXISTS `smcounselingvisit`;
CREATE TABLE `smcounselingvisit` (
  `id` bigint(20) NOT NULL default '0',
  `tested` tinyint(4) default NULL,
  `testDate` date default NULL,
  `hiv_test_result` int(11) default NULL,
  `patient_received_results` tinyint(4) default NULL,
  `counseling_date` date default NULL,
  `counselled` tinyint(4) default NULL,
  `hiv_tested` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK23F6D244D1B` (`id`),
  CONSTRAINT `FK23F6D244D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `smcounselingvisit`
--


/*!40000 ALTER TABLE `smcounselingvisit` DISABLE KEYS */;
LOCK TABLES `smcounselingvisit` WRITE;
INSERT INTO `smcounselingvisit` VALUES (9,NULL,'2007-08-14',2940,1,'2007-08-14',1,2966),(36,NULL,'2007-08-14',2940,1,'2007-08-14',1,2966);
UNLOCK TABLES;
/*!40000 ALTER TABLE `smcounselingvisit` ENABLE KEYS */;

--
-- Table structure for table `stillbirthdeliveryrecord`
--

DROP TABLE IF EXISTS `stillbirthdeliveryrecord`;
CREATE TABLE `stillbirthdeliveryrecord` (
  `id` bigint(20) NOT NULL default '0',
  `date_of_birt_844` date default NULL,
  `time_of_birth_1514` time default NULL,
  `weight_at_stillbirth_846` float default NULL,
  `ega_129` int(11) default NULL,
  `last_reported_foetal_movement_849` int(11) default NULL,
  `date_of_lrfm_850` date default NULL,
  `time_of_lrfm_851` int(11) default NULL,
  `last_foetal_heart_tone_852` int(11) default NULL,
  `date_last_foetal_heart_tone_853` date default NULL,
  `time_last_foetal_heart_tone_854` time default NULL,
  `time_foetal_death_855` time default NULL,
  `est_date_foetal_death_856` date default NULL,
  `est_time_foetal_death_857` time default NULL,
  `type_of_still_birth_858` int(11) default NULL,
  `physical_features_859` int(11) default NULL,
  `upper_extrem_538` int(11) default NULL,
  `upper_extrem_desc_539` text,
  `lower_extrem_541` int(11) default NULL,
  `lower_extrem_desc_542` text,
  `anomalies_cns_543` int(11) default NULL,
  `anomalies_cns_desc_544` text,
  `anomalies_chromo_545` int(11) default NULL,
  `anomalies_chromo_desc_546` text,
  `anomalies_cardio_547` int(11) default NULL,
  `anomalies_cardio_desc_548` text,
  `anomalies_genitour_549` int(11) default NULL,
  `anomalies_genitour_desc_550` text,
  `anomalies_other_551` int(11) default NULL,
  `anomalies_ohter_1189` text,
  `injuries_trauma_807` int(11) default NULL,
  `number_of_vessels_874` int(11) default NULL,
  `cord_complication_875` int(11) default NULL,
  `cord_length_est_876` int(11) default NULL,
  `cord_prolapsed_877` tinyint(1) default NULL,
  `velamentous_insert_878` tinyint(1) default NULL,
  `placenta_appear_879` int(11) default NULL,
  `placenta_other_880` text,
  `amniotic_fluid_vol_881` int(11) default NULL,
  `meconium_staining_882` int(11) default NULL,
  `foul_smell_883` tinyint(1) default NULL,
  `maternal_complication_884` int(11) default NULL,
  `maternal_complication_other_885` text,
  `provisional_cause_death_886` int(11) default NULL,
  `provisional_cause_death_other_887` text,
  `autopsy_requested_598` tinyint(1) default NULL,
  `autopsy_consent_599` int(11) default NULL,
  `additional_findings` text,
  `cord_complic_789` int(11) default NULL,
  `estimate_weight` tinyint(1) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKD00FA91CD1B` (`id`),
  CONSTRAINT `FKD00FA91CD1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stillbirthdeliveryrecord`
--


/*!40000 ALTER TABLE `stillbirthdeliveryrecord` DISABLE KEYS */;
LOCK TABLES `stillbirthdeliveryrecord` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `stillbirthdeliveryrecord` ENABLE KEYS */;

--
-- Table structure for table `subscription`
--

DROP TABLE IF EXISTS `subscription`;
CREATE TABLE `subscription` (
  `id` int(11) NOT NULL auto_increment,
  `url` varchar(255) default NULL,
  `datesubscribed` datetime default NULL,
  `enabled` tinyint(1) default NULL,
  `site` varchar(5) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `url` (`url`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subscription`
--


/*!40000 ALTER TABLE `subscription` DISABLE KEYS */;
LOCK TABLES `subscription` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `subscription` ENABLE KEYS */;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` bigint(20) NOT NULL auto_increment,
  `active` tinyint(1) default NULL,
  `incomplete` tinyint(1) default NULL,
  `form_id` bigint(20) default NULL,
  `encounter_id` bigint(20) default NULL,
  `patient_id` bigint(20) default NULL,
  `auditInfo` tinyblob,
  `flow_id` bigint(20) default NULL,
  `label` varchar(255) default NULL,
  `submissions` int(11) default NULL,
  `max_submissions` int(11) default NULL,
  `class_name` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `task`
--


/*!40000 ALTER TABLE `task` DISABLE KEYS */;
LOCK TABLES `task` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `task` ENABLE KEYS */;

--
-- Table structure for table `task_list`
--

DROP TABLE IF EXISTS `task_list`;
CREATE TABLE `task_list` (
  `id` bigint(20) NOT NULL auto_increment,
  `patient_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKAC4DF188523EC95` (`patient_id`),
  CONSTRAINT `FKAC4DF188523EC95` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `task_list`
--


/*!40000 ALTER TABLE `task_list` DISABLE KEYS */;
LOCK TABLES `task_list` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `task_list` ENABLE KEYS */;

--
-- Table structure for table `test720a`
--

DROP TABLE IF EXISTS `test720a`;
CREATE TABLE `test720a` (
  `id` bigint(20) NOT NULL default '0',
  `test` varchar(255) default NULL,
  `cabbage` int(11) default NULL,
  `test723a` varchar(255) default NULL,
  `test423b` varchar(255) default NULL,
  `test723c` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKtest720a` (`id`),
  CONSTRAINT `FKtest720a` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `test720a`
--


/*!40000 ALTER TABLE `test720a` DISABLE KEYS */;
LOCK TABLES `test720a` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `test720a` ENABLE KEYS */;

--
-- Table structure for table `test723`
--

DROP TABLE IF EXISTS `test723`;
CREATE TABLE `test723` (
  `id` bigint(20) NOT NULL default '0',
  `test1` varchar(255) default NULL,
  `test2` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKtest723` (`id`),
  CONSTRAINT `FKtest723` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `test723`
--


/*!40000 ALTER TABLE `test723` DISABLE KEYS */;
LOCK TABLES `test723` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `test723` ENABLE KEYS */;

--
-- Table structure for table `test7233`
--

DROP TABLE IF EXISTS `test7233`;
CREATE TABLE `test7233` (
  `id` bigint(20) NOT NULL default '0',
  `test` varchar(255) default NULL,
  `test2` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKtest7233` (`id`),
  CONSTRAINT `FKtest7233` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `test7233`
--


/*!40000 ALTER TABLE `test7233` DISABLE KEYS */;
LOCK TABLES `test7233` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `test7233` ENABLE KEYS */;

--
-- Table structure for table `test7235`
--

DROP TABLE IF EXISTS `test7235`;
CREATE TABLE `test7235` (
  `id` bigint(20) NOT NULL default '0',
  `patient_health` varchar(255) default NULL,
  `apples` varchar(255) default NULL,
  `veggies` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKtest7235` (`id`),
  CONSTRAINT `FKtest7235` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `test7235`
--


/*!40000 ALTER TABLE `test7235` DISABLE KEYS */;
LOCK TABLES `test7235` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `test7235` ENABLE KEYS */;

--
-- Table structure for table `testck`
--

DROP TABLE IF EXISTS `testck`;
CREATE TABLE `testck` (
  `id` bigint(20) NOT NULL default '0',
  `title` varchar(255) default NULL,
  `firstname` varchar(255) default NULL,
  `states` int(11) default NULL,
  `dateVisit` date default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKtestck` (`id`),
  CONSTRAINT `FKtestck` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `testck`
--


/*!40000 ALTER TABLE `testck` DISABLE KEYS */;
LOCK TABLES `testck` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `testck` ENABLE KEYS */;

--
-- Table structure for table `testck2`
--

DROP TABLE IF EXISTS `testck2`;
CREATE TABLE `testck2` (
  `id` bigint(20) NOT NULL default '0',
  `fruits` int(11) default NULL,
  `meats` int(11) default NULL,
  `teeta` int(11) default NULL,
  `surfaces` int(11) default NULL,
  `test1` int(11) default NULL,
  `textField` varchar(255) default NULL,
  `is_true` tinyint(1) default NULL,
  `is_really_true` tinyint(1) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKtestck2` (`id`),
  CONSTRAINT `FKtestck2` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `testck2`
--


/*!40000 ALTER TABLE `testck2` DISABLE KEYS */;
LOCK TABLES `testck2` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `testck2` ENABLE KEYS */;

--
-- Table structure for table `testck3`
--

DROP TABLE IF EXISTS `testck3`;
CREATE TABLE `testck3` (
  `id` bigint(20) NOT NULL default '0',
  PRIMARY KEY  (`id`),
  KEY `FKtestCK3` (`id`),
  CONSTRAINT `FKtestCK3` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `testck3`
--


/*!40000 ALTER TABLE `testck3` DISABLE KEYS */;
LOCK TABLES `testck3` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `testck3` ENABLE KEYS */;

--
-- Table structure for table `testck4`
--

DROP TABLE IF EXISTS `testck4`;
CREATE TABLE `testck4` (
  `id` bigint(20) NOT NULL default '0',
  PRIMARY KEY  (`id`),
  KEY `FKtestCK4` (`id`),
  CONSTRAINT `FKtestCK4` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `testck4`
--


/*!40000 ALTER TABLE `testck4` DISABLE KEYS */;
LOCK TABLES `testck4` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `testck4` ENABLE KEYS */;

--
-- Table structure for table `tt_immunisation`
--

DROP TABLE IF EXISTS `tt_immunisation`;
CREATE TABLE `tt_immunisation` (
  `id` bigint(20) NOT NULL auto_increment,
  `encounter_id` bigint(20) default NULL,
  `patient_id` bigint(20) default NULL,
  `childhood_immun` varchar(255) default NULL,
  `dpt_doses` int(11) default NULL,
  `tt_doses` int(11) default NULL,
  `tt_immunisation_1` date default NULL,
  `tt_immunisation_2` date default NULL,
  `tt_immunisation_3` date default NULL,
  `tt_immunisation_4` date default NULL,
  `tt_immunisation_5` date default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK54DE108C5FB97A7` (`encounter_id`),
  KEY `FK54DE1088523EC95` (`patient_id`),
  CONSTRAINT `FK54DE1088523EC95` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`),
  CONSTRAINT `FK54DE108C5FB97A7` FOREIGN KEY (`encounter_id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tt_immunisation`
--


/*!40000 ALTER TABLE `tt_immunisation` DISABLE KEYS */;
LOCK TABLES `tt_immunisation` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `tt_immunisation` ENABLE KEYS */;

--
-- Table structure for table `ttimmunisation`
--

DROP TABLE IF EXISTS `ttimmunisation`;
CREATE TABLE `ttimmunisation` (
  `id` bigint(20) NOT NULL default '0',
  `childhood_dp_immun_107` tinyint(1) default NULL,
  `number_of_dp_108` int(11) default NULL,
  `number_of_tt_109` int(11) default NULL,
  `tt1_110` date default NULL,
  `tt2_111` date default NULL,
  `tt3_112` date default NULL,
  `tt4_113` date default NULL,
  `tt5_114` date default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKDEA14429D1B` (`id`),
  CONSTRAINT `FKDEA14429D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ttimmunisation`
--


/*!40000 ALTER TABLE `ttimmunisation` DISABLE KEYS */;
LOCK TABLES `ttimmunisation` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `ttimmunisation` ENABLE KEYS */;

--
-- Table structure for table `ultrasoundfetuseval`
--

DROP TABLE IF EXISTS `ultrasoundfetuseval`;
CREATE TABLE `ultrasoundfetuseval` (
  `id` bigint(20) NOT NULL default '0',
  `sequence_number_fetus` int(11) default NULL,
  `condition_of_foetus_964` int(11) default NULL,
  `date_of_ultrasound_1212` date default NULL,
  `lie_313` int(11) default NULL,
  `presentation_314` int(11) default NULL,
  `presentation_other` text,
  `exam_sequence_number` int(11) default NULL,
  `biparietal_diameter_955` float default NULL,
  `femur_length_956` int(11) default NULL,
  `fetal_abdomi_957` int(11) default NULL,
  `weight` float default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK9AAABA8CD1B` (`id`),
  CONSTRAINT `FK9AAABA8CD1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ultrasoundfetuseval`
--


/*!40000 ALTER TABLE `ultrasoundfetuseval` DISABLE KEYS */;
LOCK TABLES `ultrasoundfetuseval` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `ultrasoundfetuseval` ENABLE KEYS */;

--
-- Table structure for table `updateega`
--

DROP TABLE IF EXISTS `updateega`;
CREATE TABLE `updateega` (
  `id` bigint(20) NOT NULL default '0',
  `ega_129` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK8BD1AE96D1B` (`id`),
  CONSTRAINT `FK8BD1AE96D1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `updateega`
--


/*!40000 ALTER TABLE `updateega` DISABLE KEYS */;
LOCK TABLES `updateega` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `updateega` ENABLE KEYS */;

--
-- Table structure for table `updatelog`
--

DROP TABLE IF EXISTS `updatelog`;
CREATE TABLE `updatelog` (
  `id` bigint(20) NOT NULL auto_increment,
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `checksum` bigint(20) default NULL,
  `site` varchar(5) default NULL,
  `comments` text,
  `builddate` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `updatelog`
--


/*!40000 ALTER TABLE `updatelog` DISABLE KEYS */;
LOCK TABLES `updatelog` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `updatelog` ENABLE KEYS */;

--
-- Table structure for table `urinalysis`
--

DROP TABLE IF EXISTS `urinalysis`;
CREATE TABLE `urinalysis` (
  `id` bigint(20) NOT NULL default '0',
  `leuk_est` int(11) default NULL,
  `nitrite` int(11) default NULL,
  `urinalysis_ace_244` int(11) default NULL,
  `urinalysis_alb_242` int(11) default NULL,
  `urinalysis_glu_243` int(11) default NULL,
  `wbc_urinalysis` int(11) default NULL,
  `bacteria` int(11) default NULL,
  `labtest_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKurinalysis` (`id`),
  CONSTRAINT `FKurinalysis` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `urinalysis`
--


/*!40000 ALTER TABLE `urinalysis` DISABLE KEYS */;
LOCK TABLES `urinalysis` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `urinalysis` ENABLE KEYS */;

--
-- Table structure for table `user_group`
--

DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL default '',
  `last_modified` datetime default NULL,
  `created` datetime default NULL,
  `last_modified_by` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `site_id` bigint(20) default NULL,
  `active` tinyint(4) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK72A9010B51A3A90E` (`created_by`),
  KEY `FK72A9010B7D2B7913` (`site_id`),
  KEY `FK72A9010B3E8F4D64` (`last_modified_by`),
  CONSTRAINT `FK72A9010B3E8F4D64` FOREIGN KEY (`last_modified_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FK72A9010B51A3A90E` FOREIGN KEY (`created_by`) REFERENCES `user_group_membership` (`id`),
  CONSTRAINT `FK72A9010B7D2B7913` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_group`
--


/*!40000 ALTER TABLE `user_group` DISABLE KEYS */;
LOCK TABLES `user_group` WRITE;
INSERT INTO `user_group` VALUES (1,'Superusers',NULL,NULL,'zepadmin','zepadmin',NULL,1),(2,'System Administrator',NULL,NULL,'zepadmin','zepadmin',NULL,NULL),(3,'Database Administrator',NULL,NULL,'zepadmin','zepadmin',NULL,NULL),(4,'Software Developer',NULL,NULL,'zepadmin','zepadmin',NULL,NULL),(5,'Clinic Medical Staff',NULL,NULL,'zepadmin','zepadmin',NULL,1),(6,'UTH Medical Staff',NULL,NULL,'zepadmin','zepadmin',NULL,0),(7,'CIDRZ Medical Staff',NULL,NULL,'zepadmin','zepadmin',NULL,NULL),(8,'LUDHMT',NULL,NULL,'zepadmin','zepadmin',NULL,NULL),(9,'LUDHMB Staff ID/Login Admistrator',NULL,NULL,'zepadmin','zepadmin',NULL,1),(10,'Central Board of Health (CBoH)',NULL,NULL,'zepadmin','zepadmin',NULL,NULL),(11,'CIDRZ Laboratory Staff (Kalingalinga)',NULL,NULL,'zepadmin','zepadmin',NULL,NULL),(12,'Clinic and UTH Laboratory Staff',NULL,NULL,'zepadmin','zepadmin',NULL,NULL),(13,'UAB Alabama Research Staff',NULL,NULL,'zepadmin','zepadmin',NULL,NULL),(14,'RTI NC ZEPRS Staff',NULL,NULL,'zepadmin','zepadmin',NULL,NULL),(15,'View Reports only',NULL,NULL,NULL,NULL,NULL,1),(16,'View Patient Records only',NULL,NULL,NULL,NULL,NULL,1),(17,'View Patient Records and Reports',NULL,NULL,NULL,NULL,NULL,NULL),(18,'Clerks',NULL,NULL,NULL,NULL,NULL,1),(19,'Clinic/UTH Admins',NULL,NULL,NULL,NULL,NULL,1),(20,'Date Entry Associate',NULL,NULL,'zepadmin','zepadmin',NULL,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `user_group` ENABLE KEYS */;

--
-- Table structure for table `user_group_membership`
--

DROP TABLE IF EXISTS `user_group_membership`;
CREATE TABLE `user_group_membership` (
  `id` varchar(255) NOT NULL default '',
  `group_id` bigint(20) default NULL,
  `prefix` varchar(255) default NULL,
  `firstname` varchar(255) default NULL,
  `lastname` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK70FA578A1E2E76DB` (`group_id`),
  CONSTRAINT `FK70FA578A1E2E76DB` FOREIGN KEY (`group_id`) REFERENCES `user_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_group_membership`
--


/*!40000 ALTER TABLE `user_group_membership` DISABLE KEYS */;
LOCK TABLES `user_group_membership` WRITE;
INSERT INTO `user_group_membership` VALUES ('clerk',16,'Mrs.','Data','Clerk'),('clinic',5,'Dr.','Clinic','Worker'),('demo',1,'Mr.','Demo','Tester'),('reports',15,'Mr.','Report','User'),('zepadmin',1,'Mr.','Site','Admin');
UNLOCK TABLES;
/*!40000 ALTER TABLE `user_group_membership` ENABLE KEYS */;

--
-- Table structure for table `user_group_role`
--

DROP TABLE IF EXISTS `user_group_role`;
CREATE TABLE `user_group_role` (
  `group_id` bigint(20) NOT NULL default '0',
  `role` varchar(255) default NULL,
  KEY `FK9C05272A1E2E76DB` (`group_id`),
  CONSTRAINT `FK9C05272A1E2E76DB` FOREIGN KEY (`group_id`) REFERENCES `user_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_group_role`
--


/*!40000 ALTER TABLE `user_group_role` DISABLE KEYS */;
LOCK TABLES `user_group_role` WRITE;
INSERT INTO `user_group_role` VALUES (1,'ALTER_OPERATING_SYSTEM_NETWORK_SECURITY_PROGRAMS'),(1,'CREATE_MEDICAL_STAFF_IDS_AND_PASSWORDS_FOR_MEDICAL_STAFF'),(1,'CREATE_VIEW_MODIFY_INDIVIDUAL_PATIENT_RECORDS'),(1,'VIEW_INDIVIDUAL_PATIENT_RECORDS'),(1,'VIEW_SELECTED_REPORTS_AND_VIEW_STATISTICAL_SUMMARIES'),(1,'IDENTIFY_INDIVIDUAL_PATIENTS_OF_REPORTABLE_DISEASES'),(1,'ENTER_ALL_LAB_RESULTS_ONLY_NO_VIEWING_OF_INDIVIDUAL_PATIENT_DATA'),(1,'ENTER_SUBSET_OF_LAB_RESULTS_ONLY_NO_VIEWING_OF_INDIVIDUAL_PATIENT_DATA'),(1,'CHANGE_DATABASE_TABLES_AND_CONTENTS_OF_ENUMERATED_LISTS'),(1,'ALTER_PROGRAMS_AND_SCREEN_APPEARANCE'),(1,'admin'),(1,'manager'),(5,'CREATE_VIEW_MODIFY_INDIVIDUAL_PATIENT_RECORDS'),(5,'VIEW_INDIVIDUAL_PATIENT_RECORDS'),(5,'IDENTIFY_INDIVIDUAL_PATIENTS_OF_REPORTABLE_DISEASES'),(9,'CREATE_MEDICAL_STAFF_IDS_AND_PASSWORDS_FOR_MEDICAL_STAFF'),(15,'VIEW_SELECTED_REPORTS_AND_VIEW_STATISTICAL_SUMMARIES'),(16,'VIEW_INDIVIDUAL_PATIENT_RECORDS'),(17,'VIEW_INDIVIDUAL_PATIENT_RECORDS'),(17,'VIEW_SELECTED_REPORTS_AND_VIEW_STATISTICAL_SUMMARIES'),(18,'CREATE_NEW_PATIENTS_AND_SEARCH'),(6,'CREATE_VIEW_MODIFY_INDIVIDUAL_PATIENT_RECORDS'),(6,'VIEW_INDIVIDUAL_PATIENT_RECORDS'),(6,'IDENTIFY_INDIVIDUAL_PATIENTS_OF_REPORTABLE_DISEASES'),(19,'CREATE_VIEW_MODIFY_INDIVIDUAL_PATIENT_RECORDS'),(19,'VIEW_INDIVIDUAL_PATIENT_RECORDS'),(19,'IDENTIFY_INDIVIDUAL_PATIENTS_OF_REPORTABLE_DISEASES'),(19,'VIEW_SELECTED_REPORTS_AND_VIEW_STATISTICAL_SUMMARIES'),(1,'DELETE_ARCHIVE_PATIENT_RECORDS'),(19,'DELETE_ARCHIVE_PATIENT_RECORDS'),(5,'VIEW_SELECTED_REPORTS_AND_VIEW_STATISTICAL_SUMMARIES'),(5,'VIEW_SELECTED_REPORTS_AND_VIEW_STATISTICAL_SUMMARIES'),(20,'CREATE_VIEW_MODIFY_INDIVIDUAL_PATIENT_RECORDS'),(20,'VIEW_INDIVIDUAL_PATIENT_RECORDS'),(20,'IDENTIFY_INDIVIDUAL_PATIENTS_OF_REPORTABLE_DISEASES'),(20,'RUN_SITE_SETUP'),(1,'RUN_SITE_SETUP'),(5,'RUN_SITE_SETUP'),(15,'RUN_SITE_SETUP'),(16,'RUN_SITE_SETUP'),(17,'RUN_SITE_SETUP'),(18,'RUN_SITE_SETUP'),(19,'RUN_SITE_SETUP'),(20,'RUN_SITE_SETUP');
UNLOCK TABLES;
/*!40000 ALTER TABLE `user_group_role` ENABLE KEYS */;

--
-- Table structure for table `uthneonatalrecord`
--

DROP TABLE IF EXISTS `uthneonatalrecord`;
CREATE TABLE `uthneonatalrecord` (
  `id` bigint(20) NOT NULL default '0',
  `time_of_admission_727` time default NULL,
  `date_of_birth_728` date default NULL,
  `time_of_birth_1514` time default NULL,
  `age_of_mother_730` int(11) default NULL,
  `age_of_father_731` int(11) default NULL,
  `infant_age_732` int(11) default NULL,
  `parity_734` int(11) default NULL,
  `mothers_lmp_735` date default NULL,
  `ega_129` int(11) default NULL,
  `gestational_age_days_737` int(11) default NULL,
  `abo_blood_group_mother_738` int(11) default NULL,
  `rhesus_mother_739` int(11) default NULL,
  `vdrl_mother_740` int(11) default NULL,
  `attending_anc_741` tinyint(1) default NULL,
  `mothers_occupation_742` int(11) default NULL,
  `intrapartum_history_743` text,
  `mode_of_deli_744` int(11) default NULL,
  `indication_cs_745` text,
  `place_of_delivery_50` int(11) default NULL,
  `time_of_rupt_747` int(11) default NULL,
  `duration_of_labour_1st_748` int(11) default NULL,
  `duration_of_labour_2nd_749` int(11) default NULL,
  `placenta_type_440` int(11) default NULL,
  `weight_of_placenta_441` int(11) default NULL,
  `birth_tuplet_752` int(11) default NULL,
  `if_multiple_753` int(11) default NULL,
  `maternal_risk_factors_754` int(11) default NULL,
  `maternal_risk_factor_1` int(11) default NULL,
  `maternal_risk_factor_2` int(11) default NULL,
  `maternal_risk_factor_3` int(11) default NULL,
  `maternal_risk_factor_4` int(11) default NULL,
  `maternal_risk_factor_5` int(11) default NULL,
  `maternal_risk_other_755` text,
  `place_delivery_other` varchar(255) default NULL,
  `sex_490` int(11) default NULL,
  `occupation_12` int(11) default NULL,
  `mode_of_delivery_447` int(11) default NULL,
  `indication_CS_forcepts_60` int(11) default NULL,
  `rupture_of_membranes_date_328` date default NULL,
  `rupture_of_membranes_time_329` time default NULL,
  `number_of_fetuses_63` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK40B775AED1B` (`id`),
  CONSTRAINT `FK40B775AED1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `uthneonatalrecord`
--


/*!40000 ALTER TABLE `uthneonatalrecord` DISABLE KEYS */;
LOCK TABLES `uthneonatalrecord` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `uthneonatalrecord` ENABLE KEYS */;

--
-- Table structure for table `vaginalexam`
--

DROP TABLE IF EXISTS `vaginalexam`;
CREATE TABLE `vaginalexam` (
  `id` bigint(20) NOT NULL default '0',
  `time_320` time default NULL,
  `condition_of_vulva_321` int(11) default NULL,
  `condition_of_vulva_desc_322` varchar(255) default NULL,
  `condition_of_vagina_323` int(11) default NULL,
  `condition_of_vagina_other_324` varchar(255) default NULL,
  `cervix_dilatation325` int(11) default NULL,
  `cervix_effacement_326` int(11) default NULL,
  `cervix_consistency_327` int(11) default NULL,
  `rupture_of_membranes_1221` tinyint(1) default NULL,
  `rupture_of_membranes_date_328` date default NULL,
  `rupture_of_membranes_time_329` time default NULL,
  `membranes_re_330` int(11) default NULL,
  `liquor_331` int(11) default NULL,
  `presentation_314` int(11) default NULL,
  `presentation_other` varchar(255) default NULL,
  `presentation_obtained_by_335` int(11) default NULL,
  `station_of_pp_336` int(11) default NULL,
  `position_337` varchar(255) default NULL,
  `moulding_338` int(11) default NULL,
  `caput_339` int(11) default NULL,
  `cord_at_vaginal_exam_340` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKDAB77F4BD1B` (`id`),
  CONSTRAINT `FKDAB77F4BD1B` FOREIGN KEY (`id`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vaginalexam`
--


/*!40000 ALTER TABLE `vaginalexam` DISABLE KEYS */;
LOCK TABLES `vaginalexam` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `vaginalexam` ENABLE KEYS */;

--
-- Table structure for table `visit_outcome`
--

DROP TABLE IF EXISTS `visit_outcome`;
CREATE TABLE `visit_outcome` (
  `id` bigint(20) NOT NULL default '0',
  `name` varchar(255) default NULL,
  `reason` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKA6BB805ED1B` (`id`),
  CONSTRAINT `FKA6BB805ED1B` FOREIGN KEY (`id`) REFERENCES `outcome` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `visit_outcome`
--


/*!40000 ALTER TABLE `visit_outcome` DISABLE KEYS */;
LOCK TABLES `visit_outcome` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `visit_outcome` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

