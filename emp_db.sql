/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.5.5-10.4.25-MariaDB : Database - emp_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`emp_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `emp_db`;

/*Table structure for table `doctor` */

DROP TABLE IF EXISTS `doctor`;

CREATE TABLE `doctor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` datetime NOT NULL,
  `status` smallint(6) DEFAULT NULL,
  `consultation_charges` double DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `speciality` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `doctor` */

insert  into `doctor`(`id`,`created_by`,`created_date`,`modified_by`,`modified_date`,`status`,`consultation_charges`,`contact`,`email`,`name`,`speciality`) values (1,NULL,'2023-10-29 00:52:46',NULL,'2023-10-29 00:53:54',1,3000,'92313313138','aliaupdate@gmail.com','Alia Bano Update','Gynacologist'),(2,NULL,'2023-10-29 00:58:44',NULL,'2023-10-29 00:58:44',0,3000,'92313313132','shams@gmail.com','Shams Bano','Ear Specialist');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` datetime NOT NULL,
  `status` smallint(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `is_blocked` smallint(6) DEFAULT 0,
  `is_logged_in` smallint(6) DEFAULT 0,
  `last_name` varchar(255) DEFAULT NULL,
  `login_date` datetime DEFAULT NULL,
  `mobile_no` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `user` */

insert  into `user`(`id`,`created_by`,`created_date`,`modified_by`,`modified_date`,`status`,`email`,`first_name`,`is_blocked`,`is_logged_in`,`last_name`,`login_date`,`mobile_no`,`password`,`user_name`) values (1,NULL,'2023-10-27 21:05:06',NULL,'2023-10-27 21:05:06',1,'shahzor@gmail.com','Shahzor',0,1,'Bhatti','2023-10-29 00:48:22','92313313131','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','admin'),(2,NULL,'2023-10-29 00:45:44',NULL,'2023-10-29 00:45:44',1,'rahim@gmail.com','Rahim',NULL,1,'Bux','2023-10-29 00:48:37','92313313132','6ee4a469cd4e91053847f5d3fcb61dbcc91e8f0ef10be7748da4c4a1ba382d17','manager');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
