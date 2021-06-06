-- MySQL dump 10.13  Distrib 8.0.16, for macos10.14 (x86_64)
--
-- Host: localhost    Database: iot_device
-- ------------------------------------------------------
-- Server version	5.6.50

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
CREATE TABLE IF NOT EXISTS `sim` (
  `id` int(11) NOT NULL,
  `country_name` varchar(255) DEFAULT NULL,
  `operator_code` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

MERGE INTO `sim` VALUES (1,'Egypt','02','active'),(2,'USA','01','waiting'),(3,'South Africa','037','deactivated'),(4,'UK','07','waiting'),(5,'Argentina','05','active');

CREATE TABLE IF NOT EXISTS `device` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sim_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `device-sim` FOREIGN KEY (`sim_id`) REFERENCES `sim` (`id`)
);

MERGE INTO `device` VALUES (1,'device-1',1),(2,'device-2',2),(3,'device-3',3),(4,'device-5',4),(5,'device-5',5);