CREATE DATABASE  IF NOT EXISTS `omazan` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `omazan`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: omazan
-- ------------------------------------------------------
-- Server version	5.6.14

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `street` varchar(45) NOT NULL,
  `houseno` int(11) NOT NULL,
  `city` varchar(45) NOT NULL,
  `state` varchar(45) NOT NULL,
  `zip` int(11) NOT NULL,
  `addresstype` varchar(45) NOT NULL,
  PRIMARY KEY (`address_id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,7,'asdasd',0,'dasd','sadsd',0,'UserAddress'),(2,8,'asdasdasd',0,'dasdas','asdsads',0,'UserAddress'),(3,9,'asdasd',0,'sads','dsad',0,'UserAddress'),(4,9,'asdasd',0,'sads','dsad',0,'UserAddress'),(5,9,'asdasd',0,'sads','dsad',0,'UserAddress'),(6,32,'asdasd',0,'sadasd','asdasd',0,'UserAddress'),(7,33,'asdasd',0,'asdas','asdsas',0,'UserAddress'),(8,36,'sads',0,'sadasd','dsds',0,'Shipping'),(9,38,'sdfdsf',0,'fdf','sdfdf',0,'Shipping'),(10,39,'sdsd',0,'asd','sd',0,'Shipping'),(11,40,'sdsd',0,'dsd','dadss',0,'Shipping'),(12,41,'sdsd',0,'dsads','sdsadsdsd',0,'Shipping'),(13,42,'sdsd',0,'dsd','sdsd',0,'Shipping'),(14,43,'sds',0,'dsad','sd',0,'Shipping'),(15,44,'sdsds',0,'dasda','sddsds',0,'Shipping'),(16,45,'xcxc',0,'cxzcxzc','czxcxc',0,'Shipping'),(17,47,'sdsd',0,'dsad','sds',0,'Shipping'),(18,48,'dsads',0,'dsad','dsad',0,'Shipping'),(19,49,'zxzx',0,'zxzxz','xzx',0,'Shipping'),(20,50,'sdsd',0,'sadsad','sds',0,'Shipping'),(21,51,'sdsd',0,'sads','sadsad',0,'Shipping'),(22,52,'dsd',0,'sdsd','dsd',0,'Shipping'),(23,53,'sad',0,'dsad','dsad',0,'Shipping'),(24,54,'sd',0,'dsad','sdsada',0,'Shipping'),(25,55,'dsd',0,'sadsd','sads',0,'Shipping'),(26,56,'sdsd',0,'asdasd','dsad',0,'Shipping'),(27,57,'dasdasd',0,'sad','sdsd',0,'Shipping'),(28,62,'asds',0,'sadsa','asd',0,'Shipping'),(29,67,'hgjh',0,'ghfg','nhgjhg',0,'Shipping');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(4000) NOT NULL,
  `imageurl` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Middleware','mw.png'),(2,'Database Systems','db2.png'),(3,'Performance and Scalability of Ecommerce Systems','perf');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `status` varchar(45) NOT NULL,
  `address_id` int(11) NOT NULL,
  `ordertotal` double NOT NULL DEFAULT '0',
  `orderplacedtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ADRESS_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `user_id_idx` (`user_id`),
  KEY `address_id_idx` (`address_id`),
  KEY `user_id_ord_idx` (`user_id`),
  KEY `address_id_ord_idx` (`address_id`),
  CONSTRAINT `fk_ord_addr` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`),
  CONSTRAINT `fk_ord_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,8,'In-progress',2,0,'2013-12-08 21:28:48',NULL),(2,9,'In-progress',3,0,'2013-12-08 21:45:51',NULL),(3,9,'On-hold',4,0,'2013-12-08 21:47:13',NULL),(4,9,'In-progress',5,0,'2013-12-08 21:48:33',NULL),(5,32,'pending',6,0,'2013-12-15 12:11:43',NULL),(6,33,'pending',7,0,'2013-12-15 12:14:09',NULL),(7,36,'Pending',8,0,'2013-12-21 19:04:13',NULL),(8,38,'Pending',9,0,'2013-12-21 19:20:41',NULL),(9,39,'Pending',10,0,'2013-12-21 19:49:05',NULL),(10,40,'Pending',11,0,'2013-12-21 20:31:03',NULL),(11,41,'Pending',12,0,'2013-12-21 21:17:23',NULL),(12,42,'Pending',13,0,'2013-12-21 21:24:21',NULL),(13,43,'Pending',14,0,'2013-12-21 21:32:05',NULL),(14,44,'Pending',15,0,'2013-12-21 21:35:03',NULL),(15,45,'Pending',16,0,'2013-12-21 21:36:59',NULL),(16,47,'Pending',17,0,'2013-12-21 21:40:36',NULL),(17,48,'Pending',18,0,'2013-12-21 21:47:08',NULL),(18,49,'Pending',19,0,'2013-12-21 21:49:41',NULL),(19,50,'Pending',20,0,'2013-12-21 22:02:19',NULL),(20,51,'Pending',21,0,'2013-12-21 22:12:20',NULL),(21,52,'Pending',22,0,'2013-12-21 22:16:44',NULL),(22,53,'Pending',23,0,'2013-12-21 22:19:37',NULL),(23,54,'Pending',24,0,'2013-12-21 22:29:49',NULL),(24,55,'Pending',25,0,'2013-12-21 22:40:50',NULL),(25,56,'Pending',26,0,'2013-12-22 13:12:35',NULL),(26,57,'Pending',27,0,'2013-12-22 13:20:22',NULL),(27,62,'Pending',28,0,'2013-12-24 21:43:12',NULL),(28,67,'Pending',29,0,'2014-01-12 22:37:00',NULL);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderedproducts`
--

DROP TABLE IF EXISTS `orderedproducts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderedproducts` (
  `orderedproducts_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`orderedproducts_id`),
  UNIQUE KEY `ordprod_UNIQUE` (`order_id`,`product_id`),
  KEY `fk_ordprod_prod` (`product_id`),
  CONSTRAINT `fk_ordprod_ord` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_ordprod_prod` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderedproducts`
--

LOCK TABLES `orderedproducts` WRITE;
/*!40000 ALTER TABLE `orderedproducts` DISABLE KEYS */;
INSERT INTO `orderedproducts` VALUES (1,1,1,0),(2,2,1,0),(3,3,1,0),(4,4,1,0),(5,5,1,0),(6,5,2,0),(7,6,1,0),(8,7,1,0),(9,7,2,0),(10,8,1,0),(11,8,2,0),(12,9,1,0),(13,9,2,0),(14,10,1,0),(15,10,2,0),(16,11,1,0),(17,11,2,0),(18,12,1,0),(19,12,2,0),(20,13,1,0),(21,13,2,0),(22,14,1,0),(23,14,2,0),(24,15,1,0),(25,15,2,0),(26,16,1,0),(27,16,2,0),(28,17,1,0),(29,17,2,0),(30,18,1,0),(31,18,2,0),(32,19,1,0),(33,19,2,0),(34,20,1,0),(35,20,2,0),(36,21,1,0),(37,21,2,0),(38,22,1,0),(39,22,2,0),(40,23,1,0),(41,23,2,0),(42,24,1,0),(43,24,2,0),(44,25,1,0),(45,25,2,0),(46,26,1,0),(47,26,2,0),(48,27,1,0),(49,27,2,0),(50,28,1,0),(51,28,2,0);
/*!40000 ALTER TABLE `orderedproducts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `imageurl` varchar(4000) DEFAULT NULL,
  `price` double NOT NULL DEFAULT '0',
  `quantity` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`product_id`),
  KEY `category_id_idx` (`category_id`),
  CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,2,'Fundamentals of Database Systems ','Recommended by Prof. Buchman',NULL,100,1000000),(2,2,'Database Systems: Concepts','Recommended by Prof. Buchman',NULL,20,100000000);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productdesc`
--

DROP TABLE IF EXISTS `productdesc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productdesc` (
  `product_id` int(11) NOT NULL,
  `attr_name` varchar(45) NOT NULL,
  `attr_value` varchar(4000) NOT NULL,
  KEY `product_id_idx` (`product_id`),
  CONSTRAINT `product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productdesc`
--

LOCK TABLES `productdesc` WRITE;
/*!40000 ALTER TABLE `productdesc` DISABLE KEYS */;
/*!40000 ALTER TABLE `productdesc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipment`
--

DROP TABLE IF EXISTS `shipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shipment` (
  `shipment_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `shipment_position` varchar(4000) DEFAULT NULL,
  `SHIPMENTPOSITION` varchar(255) DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `truck_id` int(11) DEFAULT '0',
  `exception_message` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`shipment_id`),
  KEY `order_id_idx` (`order_id`),
  CONSTRAINT `order_id` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipment`
--

LOCK TABLES `shipment` WRITE;
/*!40000 ALTER TABLE `shipment` DISABLE KEYS */;
INSERT INTO `shipment` VALUES (1,7,NULL,NULL,NULL,0,NULL),(2,8,NULL,NULL,NULL,0,NULL),(3,9,NULL,NULL,NULL,0,NULL),(4,10,NULL,NULL,NULL,0,NULL),(5,11,NULL,NULL,NULL,0,NULL),(6,12,NULL,NULL,NULL,0,NULL),(7,13,NULL,NULL,NULL,0,NULL),(8,14,NULL,NULL,NULL,0,NULL),(9,15,NULL,NULL,NULL,0,NULL),(10,16,NULL,NULL,NULL,0,NULL),(11,17,NULL,NULL,NULL,0,NULL),(12,18,NULL,NULL,NULL,0,NULL),(13,19,NULL,NULL,NULL,0,NULL),(14,20,NULL,NULL,NULL,0,NULL),(15,21,NULL,NULL,NULL,0,NULL),(16,22,NULL,NULL,NULL,0,NULL),(17,23,NULL,NULL,NULL,0,NULL),(18,24,NULL,'0x0','In-progress',0,NULL),(19,25,NULL,'0x0','Not Initiated',0,NULL),(20,26,NULL,'0x0','Not Initiated',0,NULL),(21,27,NULL,'0x0','Not Initiated',0,NULL),(22,28,NULL,'0x0','Not Initiated',-1,NULL);
/*!40000 ALTER TABLE `shipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `password` varchar(4000) NOT NULL,
  `type` varchar(45) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `mobile` int(11) DEFAULT '0',
  `title` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'testsadsa@a.com','test','C','fnameasdsads','lname',0,NULL),(4,'test1','[B@1ab5931','C','test','test',0,NULL),(5,'dasd\"ah.com','[B@67458657','C','dasdsd','asdasd',0,NULL),(6,'dasd','[B@52aaf3d2','C','sadsad','sdasd',0,NULL),(7,'asdasd','[B@1c88a970','C','dasds','dasds',0,NULL),(8,'asdsadas@a.com','[B@4eb7cd92','C','dasdsad','asdasd',0,NULL),(9,'asd@a.com','[B@e75be38','C','asdasdsa','asdsda',0,NULL),(10,'sadsad','[B@4332b67c','C','asdasds','dasdsd',0,NULL),(11,'dasdsa@a.com','[B@4d2bbe6b','C','ssadasdsa','dsadsadada',0,NULL),(12,'cxzczx@a.com','[B@1e3a4822','C','sadsadas','zcxxzcxzc',0,NULL),(13,'dasdas','[B@5406df4d','C','sadsd','dasdsad',0,NULL),(14,'dsadsd','[B@7a79ae56','C','dasdsa','dasds',0,NULL),(15,'aa','[B@f8db08','C','saasas','sasaaas',0,NULL),(16,'sadas@a.com','[B@51ef4970','C','dsadsad','asdsad',0,NULL),(17,'dsada@a.com','[B@198f1327','C','asdasd','dasdsd',0,NULL),(18,'asdas@a.coma','[B@5945a5a','C','Sachin','Pattan2',0,NULL),(19,'chinpattn@a','[B@84d6b1a','C','sachn','abe',0,NULL),(20,'sadsad@a.com','[B@372eabae','C','dsadsd','dasdsadsa',0,NULL),(21,'asdsa@a.com','[B@3c9ce70','C','dasdasd','dsada',0,NULL),(22,'sdfsdf@a.com','[B@145ebac9','C','sdfdfsdf','fsdfdsf',0,NULL),(23,'asdas@a.com','[B@201a503f','C','dasds','sad',0,NULL),(24,'asdasdsa@s.com','[B@1f8a6890','C','sdsadsadsa','dasdssa',0,NULL),(25,'asdasd@a.com','[B@75ecda50','C','dasdasd','asdsad',0,NULL),(26,'sdfsdf','[B@54624a40','C','dfsdf','fsdf',0,NULL),(28,'newuser@a.com','[B@25e12e2c','C','new user','new user',0,NULL),(29,'sdasda@a.com','[B@51f3eab7','C','adasdas','sadad',0,NULL),(30,'sdfsd@a.com','[B@4f0ab3f2','C','sdssdfs','sdfsdfdsfs',0,NULL),(31,'sdasdas@a.com','[B@26a0c73f','C','dadasd','dasdas',0,NULL),(32,'asda@a.com','[B@1f561437','C','asdasdasda','asdasd',0,NULL),(33,'asdasdasd','[B@10fa1b2d','C','sdasdasd','asdasd',0,NULL),(35,'sadas@aa.com','[B@37d3ac6e','C','dasadas','dsadsadas',0,NULL),(36,'czxczx@a.com','[B@e235e9d','C','zxcxzc','xzcz',0,NULL),(38,'2asda@a.com','[B@37d7a424','C','sdad','dasdsd',0,NULL),(39,'asd@q.com','[B@4856d149','C','asdsd','dsddss',0,NULL),(40,'ccx','[B@7d627b8b','C','xcx','cxcxc',0,NULL),(41,'sadas@a.comasdsadsd','[B@3f9ab00e','C','dasds','dsdsd',0,NULL),(42,'sdsadas@s.com','[B@4b25ee49','C','asasd','dsdsd',0,NULL),(43,'zxzx@q.com','[B@1be2f6b0','C','zxzxzx','zxzxz',0,NULL),(44,'zxczxc','[B@219a6087','C','cczxc','czxcxc',0,NULL),(45,'dasdsad@a.com','[B@3c9ce70','C','adsad','sds',0,NULL),(47,'ddasdsa@a.com','[B@433c8540','C','dasds','sdsds',0,NULL),(48,'xzczx@q.com','[B@756095fc','C','zxcxzc','cxzcx',0,NULL),(49,'cxzcx@q.com','[B@6726a408','C','cxcxc','zcxzc',0,NULL),(50,'sd','[B@4a93837b','C','dsads','sdsd',0,NULL),(51,'dsd','[B@3f0cc730','C','sd','sd',0,NULL),(52,'xzcxzw.com','[B@4a93837b','C','cxzc','cxc',0,NULL),(53,'sdsd','[B@228ab65','C','dsad','sadsd',0,NULL),(54,'dsads@q.com','[B@43b5699','C','asdasd','sadsd',0,NULL),(55,'asdasd@q.com','[B@7a6bb93c','C','asads','asdsads',0,NULL),(56,'sachinpattan@q.com','[B@5be04861','C','SACHN','PATTAN',0,NULL),(57,'newsachin@a.com','¤:g¶ÁË5—ÓGqZO.','C','newsachin@a.com','newsachin@a.com',0,NULL),(58,'adminone@omazon.com','¶ÛF‡[šª²\\Ý-²˜SX','B','admin','one',98765,NULL),(59,'adminone@a.com','^�¡JÑÈÝwéŽùµ?Ñº','C','dsadsadss','sdasdsad',0,NULL),(60,'adminoneasdasd@omazon.com','¶ÛF‡[šª²\\Ý-²˜SX','C','asdasdsad','adasdasd',0,NULL),(61,'adminthree@omazon.com','¶ÛF‡[šª²\\Ý-²˜SX','C','asdasd','dsadsadsd',0,NULL),(62,'1sadasdsa@a.com','xinËñÉnh”·yEm3','C','dasd','asd',0,NULL),(63,'jhhjhjh@q.com','sadsadsdsdsd','C','Sachin','Pattan',345678,NULL),(64,'1234','qwer','C','sdasdsad','dasdsadsd',1234,NULL),(65,'erer2.COM','SDSDSD','C','sdads','sdsads',1234,NULL),(66,'weweweweewewe','wewewew','C','wqewe','weqew',1233,NULL),(67,'fdgfdf@3.comfdsfsd','R[@¥¬Ÿ~öãÆÍSø','C','dfsfsdf','fdgfdgfdg',0,NULL);
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

-- Dump completed on 2014-01-13  0:09:19
