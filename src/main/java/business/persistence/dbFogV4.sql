CREATE DATABASE  IF NOT EXISTS `fog` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `fog`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: fog
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `materials`
--

DROP TABLE IF EXISTS `materials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materials` (
  `material_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `pricePerUnit` float NOT NULL,
  `length` int DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`material_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materials`
--

LOCK TABLES `materials` WRITE;
/*!40000 ALTER TABLE `materials` DISABLE KEYS */;
INSERT INTO `materials` VALUES (1,'25x200 mm. trykimp. Brædt',25,360,'understernbrædder til for & bag ende',NULL),(2,'25x200 mm. trykimp. Brædt',30,540,'understernbrædder til siderne',NULL),(3,'25x125mm. trykimp. Brædt',45,360,'oversternbrædder til forenden',NULL),(4,'25x125mm. trykimp. Brædt',55,540,'oversternbrædder til siderne',NULL),(5,'38x73 mm. Lægte ubh.',60,420,'til z på bagside af dør',NULL),(6,'45x95 mm. Reglar ub.',66,270,'løsholter til skur gavle',NULL),(7,'45x95 mm. Reglar ub.',67,240,'løsholter til skur sider',NULL),(8,'45x195 mm. spærtræ ubh.',71,600,'Remme i sider, sadles ned i stolper','rem'),(9,'45x195 mm. spærtræ ubh.',75,480,'Remme i sider, sadles ned i stolper (skur del, deles)','rem'),(10,'45x195 mm. spærtræ ubh.',80,600,'Spær, monteres på rem','spær'),(11,'97x97 mm. trykimp. Stolpe',88,300,'Stolper nedgraves 90 cm. i jord','stolpe'),(12,'19x100 mm. trykimp. Brædt',90,210,'til beklædning af skur 1 på 2',NULL),(13,'19x100 mm. trykimp. Brædt',99,540,'vandbrædt på stern i sider',NULL),(14,'19x100 mm. trykimp Brædt',100,360,'vandbrædt på stern i forende',NULL),(15,'Plastmo Ecolite blåtonet',111,600,'tagplader monteres på spær',NULL),(16,'Plastmo Ecolite blåtonet',123,360,'tagplader monteres på spær',NULL),(17,'plastmo bundskruer 200 stk.',45,0,'Skruer til tagplader',NULL),(18,'hulbånd 1x20 mm. 10 mtr.',45,0,'Til vindkryds på spær',NULL),(19,'universal 190 mm højre',45,0,'Til montering af spær på rem',NULL),(20,'universal 190 mm venstre',45,0,'Til montering af spær på rem',NULL),(21,'4,5 x 60 mm. skruer 200 stk',45,0,'Til montering af stern&vandbrædt',NULL),(22,'4,0 x 50 mm. beslagskruer 250 stk.',45,0,'Til montering af universalbeslag + hulbånd',NULL),(23,'bræddebolt 10 x 120 mm.',45,0,'Til montering af rem på stolper',NULL),(24,'firkantskriver 40x40x11mm',45,0,'Til montering af rem på stolper',NULL),(25,'4,5 x 70 mm. Skruer 400 stk',45,0,'til montering af yderste beklædning',NULL),(26,'4,5 x 50 mm. Skruer 300 stk.',45,0,'til montering af inderste beklædning',NULL),(27,'stalddærsgreb 50x75',45,0,'Til lås på dør i skur',NULL),(28,'t hængsel 390 mm',45,0,'Til skurdør',NULL),(29,'vinkelbeslag 35',45,0,'Til montering af løsholter i skur',NULL);
/*!40000 ALTER TABLE `materials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `lengthCM` float NOT NULL,
  `widthCM` float NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `fk_inquiry_user_idx` (`user_id`),
  CONSTRAINT `fk_inquiry_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,1,134,55),(2,1,200,99),(3,1,880,550),(4,1,890,560),(5,1,999,1020),(6,1,999,750),(7,1,550,660),(8,1,880,990);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderitems`
--

DROP TABLE IF EXISTS `orderitems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderitems` (
  `orderitems_id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `material_id` int NOT NULL,
  `quantity` int NOT NULL DEFAULT '1',
  `price` float DEFAULT NULL,
  PRIMARY KEY (`orderitems_id`),
  KEY `fk_orderItems_materials1_idx` (`material_id`),
  KEY `fk_orderitems_order1_idx` (`order_id`),
  CONSTRAINT `fk_orderItems_materials1` FOREIGN KEY (`material_id`) REFERENCES `materials` (`material_id`),
  CONSTRAINT `fk_orderitems_order1` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderitems`
--

LOCK TABLES `orderitems` WRITE;
/*!40000 ALTER TABLE `orderitems` DISABLE KEYS */;
INSERT INTO `orderitems` VALUES (1,1,1,4,100),(2,1,2,3,90),(3,1,4,1,55);
/*!40000 ALTER TABLE `orderitems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `order_id` int NOT NULL,
  `status` varchar(45) NOT NULL DEFAULT 'not accepted',
  PRIMARY KEY (`order_id`),
  KEY `fk_status_order1_idx1` (`order_id`),
  CONSTRAINT `fk_status_order1` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'not accepted'),(2,'not accepted'),(3,'not accepted'),(4,'not accepted'),(5,'not accepted'),(6,'not accepted'),(7,'not accepted'),(8,'not accepted');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit`
--

DROP TABLE IF EXISTS `unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unit` (
  `material_id` int NOT NULL,
  `unit` varchar(45) NOT NULL DEFAULT 'stk',
  PRIMARY KEY (`material_id`),
  KEY `fk_table1_materials1_idx` (`material_id`),
  CONSTRAINT `fk_table1_materials1` FOREIGN KEY (`material_id`) REFERENCES `materials` (`material_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit`
--

LOCK TABLES `unit` WRITE;
/*!40000 ALTER TABLE `unit` DISABLE KEYS */;
INSERT INTO `unit` VALUES (1,'stk'),(2,'stk'),(3,'stk'),(4,'stk'),(5,'stk'),(6,'stk'),(7,'stk'),(8,'stk'),(9,'stk'),(10,'stk'),(11,'stk'),(12,'stk'),(13,'stk'),(14,'stk'),(15,'stk'),(16,'stk'),(17,'pakke'),(18,'rulle'),(19,'stk'),(20,'stk'),(21,'pakke'),(22,'pakke'),(23,'stk'),(24,'stk'),(25,'pk.'),(26,'pk.'),(27,'sæt'),(28,'stk'),(29,'stk');
/*!40000 ALTER TABLE `unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `phoneNumber` int NOT NULL,
  `role` varchar(45) NOT NULL DEFAULT 'customer',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'robin@gotham.com','batman','Robin Johnson',55555555,'customer'),(2,'joker@gotham.com','haha','Jokester',77777777,'employee');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-17 13:14:03
