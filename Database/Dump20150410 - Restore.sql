CREATE DATABASE  IF NOT EXISTS `MediaManager` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `MediaManager`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: mediamanager
-- ------------------------------------------------------
-- Server version	5.6.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET nameS utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Genre`
--

DROP TABLE IF EXISTS `Genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Genre` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `GenreDescription` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Genre`
--

LOCK TABLES `Genre` WRITE;
/*!40000 ALTER TABLE `Genre` DISABLE KEYS */;
INSERT INTO `Genre` VALUES (1,'Zombie Comedy'),(2,'Horror'),(3,'Science Fiction'),(4,'Rock & Roll'),(5,'Thriller'),(6,'Horror');
/*!40000 ALTER TABLE `Genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MediaItem`
--

DROP TABLE IF EXISTS `MediaItem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MediaItem` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `GenreID` int(11) NOT NULL,
  `MediaTypeID` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Year` int(11) DEFAULT NULL,
  `Comments` varchar(8000) DEFAULT NULL,
  `CurrentValue` double DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  KEY `MediaItem_FKGenreID` (`GenreID`),
  KEY `MediaItem_FKMediaTypeID` (`MediaTypeID`),
  CONSTRAINT `MediaItem_FKGenreID` FOREIGN KEY (`GenreID`) REFERENCES `Genre` (`ID`),
  CONSTRAINT `MediaItem_FKMediaTypeID` FOREIGN KEY (`MediaTypeID`) REFERENCES `MediaType` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MediaItem`
--

LOCK TABLES `MediaItem` WRITE;
/*!40000 ALTER TABLE `MediaItem` DISABLE KEYS */;
INSERT INTO `MediaItem` VALUES (1,1,1,'Zombieland',2009,'Best movie ever',9.99),(2,3,2,'Mass Effect',2007,'Best game ever',19.99),(3,4,4,'RUSH',1974,'First album',4.99),(4,2,3,'The Babadook',2014,'Psychological thriller',29.99),(5,5,1,'The Babadook',2014,'Psychological',0),(6,2,1,'The Razzies',2014,'TV Program',0),(7,1,1,'Zombieland2',2016,'The Next great classic',0);
/*!40000 ALTER TABLE `MediaItem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MediaType`
--

DROP TABLE IF EXISTS `MediaType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MediaType` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `MediaTypeDescription` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MediaType`
--

LOCK TABLES `MediaType` WRITE;
/*!40000 ALTER TABLE `MediaType` DISABLE KEYS */;
INSERT INTO `MediaType` VALUES (1,'BluRay'),(2,'VideoGame'),(3,'DVD'),(4,'CD');
/*!40000 ALTER TABLE `MediaType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PurchaseInfo`
--

DROP TABLE IF EXISTS `PurchaseInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PurchaseInfo` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PurchaseLocation` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PurchaseInfo`
--

LOCK TABLES `PurchaseInfo` WRITE;
/*!40000 ALTER TABLE `PurchaseInfo` DISABLE KEYS */;
INSERT INTO `PurchaseInfo` VALUES (1,'Best Buy'),(2,'Amazon'),(3,'Game Stop'),(4,'Stax Records');
/*!40000 ALTER TABLE `PurchaseInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PurchaseInfoMediaItem`
--

DROP TABLE IF EXISTS `PurchaseInfoMediaItem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PurchaseInfoMediaItem` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PurchasePrice` decimal(10,2) NOT NULL,
  `PurchaseDate` datetime NOT NULL,
  `PurchaseInfoID` int(11) NOT NULL,
  `MediaItemID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  KEY `PIMI_FKPurchaseInfoID` (`PurchaseInfoID`),
  KEY `PIMI_FKMediaItemID` (`MediaItemID`),
  CONSTRAINT `PIMI_FKMediaItemID` FOREIGN KEY (`MediaItemID`) REFERENCES `MediaItem` (`ID`),
  CONSTRAINT `PIMI_FKPurchaseInfoID` FOREIGN KEY (`PurchaseInfoID`) REFERENCES `PurchaseInfo` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PurchaseInfoMediaItem`
--

LOCK TABLES `PurchaseInfoMediaItem` WRITE;
/*!40000 ALTER TABLE `PurchaseInfoMediaItem` DISABLE KEYS */;
INSERT INTO `PurchaseInfoMediaItem` VALUES (1,9.99,'2010-10-30 00:00:00',2,1),(2,19.99,'2009-09-20 00:00:00',3,2),(3,4.99,'1974-03-07 00:00:00',4,4),(4,29.99,'2014-12-25 00:00:00',1,3);
/*!40000 ALTER TABLE `PurchaseInfoMediaItem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserDetails`
--

DROP TABLE IF EXISTS `UserDetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserDetails` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(50) DEFAULT NULL,
  `Password` varchar(50) DEFAULT NULL,
  `AuthLevel` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserDetails`
--

LOCK TABLES `UserDetails` WRITE;
/*!40000 ALTER TABLE `UserDetails` DISABLE KEYS */;
INSERT INTO `UserDetails` VALUES (1,'ADMIN','ADMIN',1);
/*!40000 ALTER TABLE `UserDetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'mediamanager'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-10 15:46:37
