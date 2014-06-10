CREATE DATABASE  IF NOT EXISTS `ddt` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ddt`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: ddt
-- ------------------------------------------------------
-- Server version	5.6.17

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
-- Table structure for table `ddt_role`
--

DROP TABLE IF EXISTS `ddt_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ddt_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ddt_role`
--

LOCK TABLES `ddt_role` WRITE;
/*!40000 ALTER TABLE `ddt_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `ddt_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ddt_roll_book`
--

DROP TABLE IF EXISTS `ddt_roll_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ddt_roll_book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `valid_start_time` timestamp NULL DEFAULT NULL,
  `valid_end_time` timestamp NULL DEFAULT NULL,
  `roll_start_time` timestamp NULL DEFAULT NULL,
  `roll_end_time` timestamp NULL DEFAULT NULL,
  `roll_code` varchar(45) DEFAULT NULL,
  `user_count` int(11) DEFAULT '0',
  `group_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_idx` (`user_id`),
  KEY `group_id_idx` (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ddt_roll_book`
--

LOCK TABLES `ddt_roll_book` WRITE;
/*!40000 ALTER TABLE `ddt_roll_book` DISABLE KEYS */;
INSERT INTO `ddt_roll_book` VALUES (1,1,'éç£æ¨è','2014-06-03 16:00:00','2014-06-29 16:00:00',NULL,NULL,NULL,2,4);
/*!40000 ALTER TABLE `ddt_roll_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ddt_roll_book_info`
--

DROP TABLE IF EXISTS `ddt_roll_book_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ddt_roll_book_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `roll_book_id` bigint(20) NOT NULL,
  `roll_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='点名册点名情况，什么时候点名的';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ddt_roll_book_info`
--

LOCK TABLES `ddt_roll_book_info` WRITE;
/*!40000 ALTER TABLE `ddt_roll_book_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `ddt_roll_book_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ddt_roll_book_user`
--

DROP TABLE IF EXISTS `ddt_roll_book_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ddt_roll_book_user` (
  `book_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`book_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ddt_roll_book_user`
--

LOCK TABLES `ddt_roll_book_user` WRITE;
/*!40000 ALTER TABLE `ddt_roll_book_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `ddt_roll_book_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ddt_user`
--

DROP TABLE IF EXISTS `ddt_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ddt_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `wx_number` varchar(255) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `group_id` bigint(20) DEFAULT '0',
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ddt_user`
--

LOCK TABLES `ddt_user` WRITE;
/*!40000 ALTER TABLE `ddt_user` DISABLE KEYS */;
INSERT INTO `ddt_user` VALUES (1,NULL,'13616519396','13616519396','e10adc3949ba59abbe56e057f20f883e',0,'2014-06-05 16:00:00'),(2,NULL,NULL,'张三',NULL,2,'2014-06-05 16:00:00'),(3,NULL,NULL,'李四',NULL,2,'2014-06-05 16:00:00'),(4,NULL,NULL,'张三',NULL,4,'2014-06-05 16:00:00'),(5,NULL,NULL,'李四',NULL,4,'2014-06-05 16:00:00'),(6,NULL,NULL,NULL,NULL,0,'2014-06-05 16:00:00');
/*!40000 ALTER TABLE `ddt_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ddt_user_role`
--

DROP TABLE IF EXISTS `ddt_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ddt_user_role` (
  `role_id` int(11) NOT NULL,
  `user_id` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ddt_user_role`
--

LOCK TABLES `ddt_user_role` WRITE;
/*!40000 ALTER TABLE `ddt_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `ddt_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ddt_user_roll_info`
--

DROP TABLE IF EXISTS `ddt_user_roll_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ddt_user_roll_info` (
  `roll_book_info_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `roll_time` timestamp NULL DEFAULT NULL,
  `roll_info` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`roll_book_info_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户点名情况';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ddt_user_roll_info`
--

LOCK TABLES `ddt_user_roll_info` WRITE;
/*!40000 ALTER TABLE `ddt_user_roll_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `ddt_user_roll_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ddt_wx_user`
--

DROP TABLE IF EXISTS `ddt_wx_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ddt_wx_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `wx_number` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ddt_wx_user`
--

LOCK TABLES `ddt_wx_user` WRITE;
/*!40000 ALTER TABLE `ddt_wx_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `ddt_wx_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-06-08 11:12:29
