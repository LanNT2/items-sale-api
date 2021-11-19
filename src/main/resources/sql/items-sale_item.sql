-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: items-sale
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `is_deleted` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,'Weightless Body Treatment 2% BHA','This unique, concentrated conditioner is incredibly rich, but not heavy, so it effectively moisturizes and detangles hair, leaving it soft and easy to manage.',12.50,'https://www.paulaschoice.com/dw/image/v2/BBNX_PRD/on/demandware.static/-/Sites-pc-catalog/en_US/dwbc867d35/images/products/weightless-body-treatment-2-percent-bha-5700-L.png?sw=360&sfrm=png','2021-11-15 13:47:00.000000',NULL,'2021-11-18 18:31:47.498081',NULL),(2,'Daily Replenishing Body Cream ','This unique, concentrated conditioner is incredibly rich, but not heavy, so it effectively moisturizes and detangles hair, leaving it soft and easy to manage.',13.00,'https://www.paulaschoice.com/dw/image/v2/BBNX_PRD/on/demandware.static/-/Sites-pc-catalog/en_US/dwadff487b/images/products/resist-skin-revealing-body-lotion-10-percent-aha-5900-L.png?sw=360&sfrm=png','2021-11-15 13:47:00.000000',NULL,'2021-11-18 18:33:06.294246',NULL),(3,'Acne Body Spray ','This unique, concentrated conditioner is incredibly rich, but not heavy, so it effectively moisturizes and detangles hair, leaving it soft and easy to manage.',12.00,'https://www.paulaschoice.com/dw/image/v2/BBNX_PRD/on/demandware.static/-/Sites-pc-catalog/en_US/dwd0609eb4/images/products/clear-acne-body-spray-2-percent-bha-6240-L.png?sw=360&sfrm=png','2021-11-15 13:47:00.000000',NULL,'2021-11-18 18:33:51.081101',NULL),(4,'Retinol Skin-Smoothing Body Treatmentffffffffffffffffffffffffffffffffffffffffffffffffffs','This unique, concentrated conditioner is incredibly rich, but not heavy, so it effectively moisturizes and detangles hair, leaving it soft and easy to manage.',13.00,'https://www.paulaschoice.com/dw/image/v2/BBNX_PRD/on/demandware.static/-/Sites-pc-catalog/en_US/dwa506038d/images/products/skin-smoothing-retinol-body-treatment-5800-L.png?sw=360&sfrm=png','2021-11-15 13:47:00.000000',NULL,'2021-11-19 08:57:39.892692',0),(5,'Retinol Skin-Smoothing Body Treatmentffffffffffffffffffffffffffffffffffffffffffffffffffs','This unique, concentrated conditioner is incredibly rich, but not heavy, so it effectively moisturizes and detangles hair, leaving it soft and easy to manage.',14.00,'https://www.paulaschoice.com/dw/image/v2/BBNX_PRD/on/demandware.static/-/Sites-pc-catalog/en_US/dwf9066fbd/images/products/ultra-rich-soothing-body-butter-5560-L.png?sw=360&sfrm=png','2021-11-15 13:47:00.000000',NULL,'2021-11-18 18:28:34.672024',NULL),(6,'All Over Hair & Body Shampoo','This unique, concentrated conditioner is incredibly rich, but not heavy, so it effectively moisturizes and detangles hair, leaving it soft and easy to manage.',12.00,'https://www.paulaschoice.com/dw/image/v2/BBNX_PRD/on/demandware.static/-/Sites-pc-catalog/default/dw39587d58/images/products/niacinamide-duo-2-011-B.png?sw=360&sfrm=png','2021-11-15 13:47:00.000000',NULL,NULL,0),(7,'Daily Replenishing Body Cream','This unique, concentrated conditioner is incredibly rich, but not heavy, so it effectively moisturizes and detangles hair, leaving it soft and easy to manage.',28.00,'https://www.paulaschoice.com/dw/image/v2/BBNX_PRD/on/demandware.static/-/Sites-pc-catalog/en_US/dw8b00968c/images/products/smooth-finish-conditioner-5200-L.png?sw=360&sfrm=png','2021-11-15 13:47:00.000000',NULL,NULL,0),(8,'Retinol Skin-Smoothing Body Treatment','This unique, concentrated conditioner is incredibly rich, but not heavy, so it effectively moisturizes and detangles hair, leaving it soft and easy to manage.',26.00,'https://www.paulaschoice.com/dw/image/v2/BBNX_PRD/on/demandware.static/-/Sites-pc-catalog/en_US/dwbafa09ea/images/products/skin-perfecting-2-percent-bha-lotion-2051-L.png?sw=360&sfrm=png','2021-11-15 13:47:00.000000',NULL,NULL,0),(9,'Daily Replenishing Body Cream','This unique, concentrated conditioner is incredibly rich, but not heavy, so it effectively moisturizes and detangles hair, leaving it soft and easy to manage.',27.00,'https://www.paulaschoice.com/dw/image/v2/BBNX_PRD/on/demandware.static/-/Sites-pc-catalog/en_US/dw8b00968c/images/products/smooth-finish-conditioner-5200-L.png?sw=360&sfrm=png','2021-11-15 13:47:00.000000',NULL,NULL,0),(10,'All Over Hair & Body Shampoo','This unique, concentrated conditioner is incredibly rich, but not heavy, so it effectively moisturizes and detangles hair, leaving it soft and easy to manage.',16.00,'https://www.paulaschoice.com/dw/image/v2/BBNX_PRD/on/demandware.static/-/Sites-pc-catalog/en_US/dwa506038d/images/products/skin-smoothing-retinol-body-treatment-5800-L.png?sw=360&sfrm=png','2021-11-15 13:47:00.000000',NULL,NULL,0);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-19 17:43:39
