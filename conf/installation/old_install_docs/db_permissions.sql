--
-- Setup database permissions
--

GRANT SELECT, INSERT, UPDATE ON `zeprs`.* TO 'zeprs_web_user'@'localhost' IDENTIFIED BY '**password**';
GRANT SELECT ON `admin`.`form` TO 'zeprs_web_user'@'localhost' IDENTIFIED BY '**password**';
GRANT SELECT ON `mail`.`accountuser` TO 'zeprs_web_user'@'localhost' IDENTIFIED BY '**password**';
GRANT DELETE ON `zeprs`.`client_setting` TO 'zeprs_web_user'@'localhost' IDENTIFIED BY '**password**';
GRANT SELECT ON `userdata`.`address` TO 'zeprs_web_user'@'localhost' IDENTIFIED BY '**password**';
GRANT DELETE ON `zeprs`.`encounter` TO 'zeprs_web_user'@'localhost' IDENTIFIED BY '**password**';
GRANT DELETE ON `zeprs`.`encounter_value_archive` TO 'zeprs_web_user'@'localhost' IDENTIFIED BY '**password**';
GRANT DELETE ON `zeprs`.`newborneval` TO 'zeprs_web_user'@'localhost' IDENTIFIED BY '**password**';
GRANT DELETE ON `zeprs`.`rule_definition_param` TO 'zeprs_web_user'@'localhost' IDENTIFIED BY '**password**';
GRANT DELETE ON `zeprs`.`patientregistration` TO 'zeprs_web_user'@'localhost' IDENTIFIED BY '**password**';
GRANT DELETE ON `zeprs`.`patient_status` TO 'zeprs_web_user'@'localhost' IDENTIFIED BY '**password**';
GRANT DELETE ON `zeprs`.`patient` TO 'zeprs_web_user'@'localhost' IDENTIFIED BY '**password**';
GRANT DELETE ON `zeprs`.`ultrasoundfetuseval` TO 'zeprs_web_user'@'localhost' IDENTIFIED BY '**password**';

GRANT ALL ON zeprs.* TO 'zeprsAdmin'@'localhost' IDENTIFIED BY '**password**';
GRANT ALL ON admin.* TO 'zeprsAdmin'@'localhost' IDENTIFIED BY '**password**';

--
-- Setup perms for zeprsdemo db
--

GRANT SELECT,INSERT,UPDATE ON zeprsdemo.* TO 'zeprs_demo_user'@'localhost' IDENTIFIED BY '**password**';
GRANT SELECT ON admin.form TO 'zeprs_demo_user'@'localhost' IDENTIFIED BY '**password**';
GRANT SELECT ON mail.accountuser TO 'zeprs_demo_user'@'localhost' IDENTIFIED BY '**password**';
GRANT DELETE ON zeprsdemo.client_setting TO 'zeprs_demo_user'@'localhost' IDENTIFIED BY '**password**';
GRANT SELECT ON userdata.address TO 'zeprs_demo_user'@'localhost' IDENTIFIED BY '**password**';
GRANT DELETE ON zeprsdemo.encounter TO 'zeprs_demo_user'@'localhost' IDENTIFIED BY '**password**';
GRANT DELETE ON zeprsdemo.encounter_value_archive TO 'zeprs_demo_user'@'localhost' IDENTIFIED BY '**password**';
GRANT DELETE ON zeprsdemo.newborneval TO 'zeprs_demo_user'@'localhost' IDENTIFIED BY '**password**';
GRANT DELETE ON zeprsdemo.rule_definition_param TO 'zeprs_demo_user'@'localhost' IDENTIFIED BY '**password**';
GRANT DELETE ON zeprsdemo.patientregistration TO 'zeprs_demo_user'@'localhost' IDENTIFIED BY '**password**';
GRANT DELETE ON zeprsdemo.patient TO 'zeprs_demo_user'@'localhost' IDENTIFIED BY '**password**';
GRANT DELETE ON zeprsdemo.patient_status TO 'zeprs_demo_user'@'localhost' IDENTIFIED BY '**password**';
GRANT DELETE ON zeprsdemo.ultrasoundfetuseval TO 'zeprs_demo_user'@'localhost' IDENTIFIED BY '**password**';

GRANT ALL ON zeprsdemo.* TO 'zeprsAdmin'@'localhost' IDENTIFIED BY '**password**';



