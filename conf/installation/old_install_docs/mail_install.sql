-- MySQL dump 10.10
--
-- Host: localhost    Database: mail
-- ------------------------------------------------------
-- Server version	5.0.24a-community-nt

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
-- Current Database: `mail`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `mail` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `mail`;

--
-- Table structure for table `accountuser`
--

DROP TABLE IF EXISTS `accountuser`;
CREATE TABLE `accountuser` (
  `username` varchar(50) character set latin1 collate latin1_bin NOT NULL default '',
  `password` varchar(41) character set latin1 collate latin1_bin NOT NULL default '',
  `prefix` varchar(50) NOT NULL default '',
  `domain_name` varchar(255) NOT NULL default '',
  `date_created` datetime NOT NULL default '2003-10-01 00:00:00',
  `date_passwd_changed` datetime NOT NULL default '0000-00-00 00:00:00',
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accountuser`
--


/*!40000 ALTER TABLE `accountuser` DISABLE KEYS */;
LOCK TABLES `accountuser` WRITE;
INSERT INTO accountuser VALUES ('zepadmin',password('zepadmin11'),'','','2003-10-30 09:19:47','2003-10-30 09:19:47');
INSERT INTO accountuser VALUES ('luser',password(''),'','','2003-10-30 09:19:47','2003-10-30 09:19:47');
INSERT INTO accountuser VALUES ('reports',password('reports11'),'','','2003-10-30 09:19:47','2003-10-30 09:19:47');
INSERT INTO accountuser VALUES ('clinic',password('clinic11'),'','','2003-10-30 09:19:47','2003-10-30 09:19:47');
INSERT INTO accountuser VALUES ('viewall',password('viewall'),'','','2003-10-30 09:19:47','2003-10-30 09:19:47');
INSERT INTO accountuser VALUES ('useradmin',password('useradmin01'),'','','2003-10-30 09:19:47','2003-10-30 09:19:47');
INSERT INTO accountuser VALUES ('clerk',password('clerk01'),'','','2003-10-30 09:19:47','2003-10-30 09:19:47');
INSERT INTO accountuser VALUES ('demo',password('demo11'),'','','2003-10-30 09:19:47','2003-10-30 09:19:47');

UNLOCK TABLES;
/*!40000 ALTER TABLE `accountuser` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
