CREATE DATABASE  IF NOT EXISTS `shopit` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `shopit`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: shopit
-- ------------------------------------------------------
-- Server version	5.6.21
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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_bin DEFAULT NULL ,
  `description` varchar(150) COLLATE utf8_bin DEFAULT NULL ,
  `order` smallint(4) NOT NULL COMMENT 'ordre de categorie' ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'PRODUITS',NULL,0),(2,'ROBES',NULL,1),(3,'JUPES',NULL,2),(4,'PANTALONS',NULL,3),(5,'TOPS',NULL,4) ,(6,'CHAUSSURES',NULL,5) ;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;

--
-- Table structure for table `promo_product`
--

DROP TABLE IF EXISTS `promo_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promo_product` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `product` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_product_idx` (`product`),
  CONSTRAINT `fk_product_product` FOREIGN KEY (`product`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promo_product`
--

LOCK TABLES `promo_product` WRITE;
/*!40000 ALTER TABLE `promo_product` DISABLE KEYS */;
INSERT INTO `promo_product` VALUES (1,1),(2,2),(3,5),(4,3),(5,4),(6,7);
/*!40000 ALTER TABLE `promo_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commande`
--

DROP TABLE IF EXISTS `commande`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commande` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`,`user_id`),
  KEY `INDEX_USER` (`user_id`),
  CONSTRAINT `FK_USER_COMMANDE` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commande`
--

LOCK TABLES `commande` WRITE;
/*!40000 ALTER TABLE `commande` DISABLE KEYS */;
/*!40000 ALTER TABLE `commande` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;


--
-- Table structure for table `commande_info`
--

DROP TABLE IF EXISTS `commande_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commande_info` (
  `commande_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` int(10) unsigned NOT NULL,
  `qty` int(11) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`commande_id`,`product_id`),
  KEY `fk_product_idx` (`product_id`),
  CONSTRAINT `FK_COMMANDE_INFO_PRODUCT` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ,
  CONSTRAINT `fk_COMMANDE_COMMANDE_info` FOREIGN KEY (`commande_id`) REFERENCES `commande` (`id`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commande_info`
--

LOCK TABLES `commande_info` WRITE;
/*!40000 ALTER TABLE `commande_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `commande_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `category` int(10) unsigned NOT NULL,
  `name` varchar(45) COLLATE utf8_bin NOT NULL,
  `description` mediumtext COLLATE utf8_bin,
   `price` decimal(10,2) DEFAULT NULL,
  `serialNumber` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `imgName` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `stockQty` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_category_products_idx` (`category`),
  CONSTRAINT `fk_category_products` FOREIGN KEY (`category`) REFERENCES `category` (`id`) 
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES  (0,2,'Robe glamour','couleur rouge douce avec un col alonge parfait pour tenu de soiree',75.99,'sfsd','robe1.jpg',2),
(1,2,'Robe Theresa','Tres decontracte et ideale pour tenu de ville',48.99,'hgfg','robe2.jpg',5),
(2,3,'Jupe Helena','Mini-jupe a hauteur des jenoux,mi cuir-mi carrele rouge blanc noir ',200.99,'dfgg','jupe1.jpg',3),
(3,3,'jupe swag','Jupe évasé a hauteur des jenoux,couleur bleu ',100.99,'bfgg','jupe2.jpg',2),
(4,3,'jupe slim','Jupe moullante avec petite ffente en avant a hauteur des jenoux,couleur jaune ',70.99,'ifgg','jupe3.jpg',2),
(5,4,'pantalon swag','pantalon slim taille haute,couleur bleu ',105.99,'afgg','pantalon1.jpg',5),
(6,4,'pantalon Dame','pantalon  saute taille haute,couleur noir ',185.99,'gdfg','pantalon2.jpg',5),
(7,4,'pantalon boyfriend','pantalon  sauté taille haute,couleur noir et bleu marrin ',50.99,'dgdf','pantalon3.jpg',5),
(8,5,'trop-top','top courte manche noir  ',40.99,'lgdf','top1.jpg',2),
(9,5,'trop-polo ','top longue manche blanc  ',601.99,'rgdf','top2.jpg',2),
(10,5,'T-shirt','Tshirt decontracté rose courte manches  ',70.99,'ngdf','top3.jpg',6),
(11,6,'Tallon ','tallon haut couleur rose  ',100.99,'zgdf','chaussure1.jpg',6),
(12,6,'Sneakers ','Tenis blanche tres comfortable  ',100.99,'gndf','chaussure2.jpg',2),
(13,6,'sandale ','chaussure ouverte de couleur marron décorré de perles  ',25.99,'vgdf','chaussure3.jpg',6),
(14,6,'Botte ','Bottes hautes noire a grosse semelle  ',300.99,'igbs','chaussure4.jpg',7);

/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `lastName` varchar(45) COLLATE utf8_bin NOT NULL,
  `firstName` varchar(45) COLLATE utf8_bin NOT NULL,
  `email` varchar(45) COLLATE utf8_bin NOT NULL,
  `password` varchar(40) COLLATE utf8_bin NOT NULL,
  `ship_address_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_user_shipping_address_idx` (`ship_address_id`),
  CONSTRAINT `FK_user_shipping_address` FOREIGN KEY (`ship_address_id`) REFERENCES `address` (`id`) 
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Test','test','test@mail.com','d5b4f7d2f7c345d6c810820ce250e7699ce78e45',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;



--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `no` varchar(45) COLLATE utf8_bin NOT NULL COMMENT 'House number',
  `appt` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT 'Appartment number',
  `street` varchar(45) COLLATE utf8_bin NOT NULL,
  `zip` varchar(45) COLLATE utf8_bin NOT NULL,
  `city` varchar(45) COLLATE utf8_bin NOT NULL,
  `state` varchar(45) COLLATE utf8_bin NOT NULL,
  `country` varchar(45) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'1','','Rue','A0A 0A0','Ville','Province','Pays');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `autologin`
--

DROP TABLE IF EXISTS `autologin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `autologin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token` char(40) COLLATE utf8_bin NOT NULL,
  `user` int(10) unsigned NOT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `token_UNIQUE` (`token`),
  KEY `fk_user_autoLogin_idx` (`user`),
  CONSTRAINT `fk_user_autoLogin` FOREIGN KEY (`user`) REFERENCES `user` (`id`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autologin`
--

LOCK TABLES `autologin` WRITE;
/*!40000 ALTER TABLE `autologin` DISABLE KEYS */;
/*!40000 ALTER TABLE `autologin` ENABLE KEYS */;
UNLOCK TABLES;

-- Dump completed on 2020-04-07 12:07:16

