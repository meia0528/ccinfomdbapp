CREATE DATABASE IF NOT EXISTS `dbconveniencestore` /*!40100 DEFAULT CHARACTER SET utf8
*/;
USE `dbconveniencestore`;

-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: dbconveniencestore
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branch` (
  `branch_id` int NOT NULL,
  `branch_code` varchar(5) NOT NULL,
  `location` varchar(50) NOT NULL,
  `founding_year` int NOT NULL,
  `contact_person_id` int NOT NULL,
  PRIMARY KEY (`branch_id`),
  KEY `branch_ibfk_1` (`contact_person_id`),
  CONSTRAINT `branch_ibfk_1` FOREIGN KEY (`contact_person_id`) REFERENCES `employees` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` VALUES (5,'BR004','Davao City',2000,624),(10,'BR005','Baguio',2015,105),(17,'BR010','Cagayan de Oro',2002,651),(75,'BR003','Cebu City',2008,648),(89,'BR001','Manila',2005,101),(102,'BR008','Bacolod',2018,948),(105,'BR007','Iloilo City',2006,464),(115,'BR006','Tagaytay',2012,118),(123,'BR002','Quezon City',2010,164),(324,'BR009','Zamboanga City',2013,645);
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `branch_ratings`
--

DROP TABLE IF EXISTS `branch_ratings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branch_ratings` (
  `branch_id` int NOT NULL,
  `performance_ratings` decimal(2,1) NOT NULL,
  `date_updated` date NOT NULL,
  PRIMARY KEY (`branch_id`),
  CONSTRAINT `branch_ratings_ibfk_1` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch_ratings`
--

LOCK TABLES `branch_ratings` WRITE;
/*!40000 ALTER TABLE `branch_ratings` DISABLE KEYS */;
INSERT INTO `branch_ratings` VALUES (5,3.9,'2024-10-10'),(10,4.3,'2024-08-20'),(17,3.8,'2024-09-28'),(75,4.7,'2024-09-30'),(89,4.5,'2024-10-01'),(102,4.8,'2024-10-12'),(105,4.0,'2024-09-01'),(115,4.6,'2024-10-05'),(123,4.2,'2024-09-15'),(324,4.4,'2024-10-18');
/*!40000 ALTER TABLE `branch_ratings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_ratings`
--

DROP TABLE IF EXISTS `employee_ratings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_ratings` (
  `employee_id` int NOT NULL,
  `performance_ratings` decimal(2,1) NOT NULL,
  `date_updated` date NOT NULL,
  PRIMARY KEY (`employee_id`),
  CONSTRAINT `employee_ratings_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_ratings`
--

LOCK TABLES `employee_ratings` WRITE;
/*!40000 ALTER TABLE `employee_ratings` DISABLE KEYS */;
INSERT INTO `employee_ratings` VALUES (101,4.5,'2024-10-01'),(105,3.6,'2024-08-20'),(118,4.7,'2024-10-05'),(164,3.8,'2024-09-15'),(464,3.9,'2024-09-01'),(624,4.9,'2024-10-15'),(645,4.3,'2024-10-10'),(648,4.2,'2024-09-30'),(651,3.5,'2024-08-25'),(948,4.1,'2024-10-12');
/*!40000 ALTER TABLE `employee_ratings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `employee_id` int NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `employee_email` varchar(50) NOT NULL,
  `contact_number` varchar(50) NOT NULL,
  `job_title` varchar(50) NOT NULL,
  `hire_date` date NOT NULL,
  `employee_schedule` varchar(50) NOT NULL,
  `hourly_rate` decimal(10,2) NOT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (101,'Dela Cruz','Juan','juan_delacruz@gmail.com','9378263423','Cashier','2017-01-31','MWF',100.00),(105,'Francisco','Marie','marie_francisco@gmail.com','9375488374','Cashier','2001-07-11','TuThSa',120.00),(118,'Gutierrez','Leon','leon_gutierrez@gmail.com','9648481270','Manager','2020-02-20','TuWThF',275.00),(164,'Ramos','Maria','maria_ramos@gmail.com','9767864844','Janitorial Staff','2008-12-09','MTuTh',100.00),(464,'Lim','Rafael','rafael_lim@gmail.com','9342408464','Stock Clerk','2006-04-24','TuWFSu',125.00),(624,'Tan','Karl','karl_tan@gmail.com','9548453467','Manager','2015-09-08','SaSuM',250.00),(645,'Bautista','Sarah','sarah_bautista@gmail.com','9420764384','Security Guard','2013-05-21','SuMTuW',150.00),(648,'Reyes','Jose','jose_reyes@gmail.com','9548457694','Security Guard','2000-06-06','ThFSa',155.00),(651,'Garcia','Hannah','hannah_garcia@gmail.com','9340487319','Cashier','2011-10-29','WThSu',115.00),(948,'Miranda','Lia','lia_miranda@gmail.com','9675034180','Stock Clerk','2019-08-01','MThSa',110.00);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory` (
  `product_id` int NOT NULL,
  `quantity_in_stock` int NOT NULL,
  `date_updated` date NOT NULL,
  PRIMARY KEY (`product_id`),
  CONSTRAINT `inventory_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES (102,80,'2024-09-01'),(248,250,'2024-10-05'),(260,180,'2024-08-20'),(264,120,'2024-10-10'),(463,110,'2024-10-18'),(565,300,'2024-09-15'),(569,140,'2024-09-28'),(598,200,'2024-09-30'),(893,90,'2024-10-12'),(895,150,'2024-10-01');
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `product_id` int NOT NULL,
  `product_name` varchar(100) NOT NULL,
  `purchase_price` decimal(10,2) NOT NULL,
  `selling_price` decimal(10,2) NOT NULL,
  `product_category` varchar(50) NOT NULL,
  `supplier_id` int NOT NULL,
  PRIMARY KEY (`product_id`),
  KEY `supplier_id_idx` (`supplier_id`),
  CONSTRAINT `supplier_ibfk_3` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (102,'Milk Box',40.00,80.00,'Dairy',278),(248,'Chocolate Pack',35.00,70.00,'Snacks',56),(260,'Razor',20.00,40.00,'Personal Care',562),(264,'Chips',15.00,30.00,'Snacks',103),(463,'Orange Juice',30.00,60.00,'Beverages',196),(565,'Pen',10.00,20.00,'Stationery',634),(569,'Crackers',12.00,24.00,'Snacks',563),(598,'Notebook',25.00,50.00,'Stationery',262),(893,'Candy Box',60.00,120.00,'Snacks',31),(895,'Shampoo',50.00,100.00,'Personal Care',879);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales` (
  `product_id` int NOT NULL,
  `noted_by_employee_id` int NOT NULL,
  `sales` decimal(10,2) NOT NULL,
  `sale_date` date NOT NULL,
  `quantity_ordered` varchar(45) NOT NULL,
  PRIMARY KEY (`product_id`),
  KEY `sales_ibfk_1` (`noted_by_employee_id`),
  CONSTRAINT `sales_ibfk_1` FOREIGN KEY (`noted_by_employee_id`) REFERENCES `employees` (`employee_id`),
  CONSTRAINT `sales_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
INSERT INTO `sales` VALUES (102,464,45.00,'2024-09-01','1'),(248,118,530.00,'2024-10-05','4'),(260,105,799.75,'2024-08-20','5'),(264,624,23.75,'2024-10-10','1'),(463,645,129.75,'2024-10-18','2'),(565,164,15.00,'2024-09-15','1'),(569,651,63.50,'2024-09-28','1'),(598,648,42.75,'2024-09-30','2'),(893,948,235.00,'2024-10-12','3'),(895,101,239.75,'2024-10-01','3');
/*!40000 ALTER TABLE `sales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `supplier_id` int NOT NULL,
  `supplier_name` varchar(50) NOT NULL,
  `contact_person` varchar(50) NOT NULL,
  `contact_number` varchar(50) NOT NULL,
  `supplier_email` varchar(50) NOT NULL,
  `contract_terms_start_date` date NOT NULL,
  `contract_terms_end_date` date NOT NULL,
  PRIMARY KEY (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (31,'Evergreen Goods','948','09648901234','evergreen_goods@gmail.com','2024-08-01','2025-08-01'),(56,'Reliable Products','118','09426789012','reliable_products@gmail.com','2024-06-01','2025-06-01'),(103,'Prime Materials','624','09204567890','prime_materials@gmail.com','2024-02-15','2025-02-15'),(196,'Swift Supplies','645','09759012345','swift_supplies@gmail.com','2024-09-01','2025-09-01'),(262,'Global Distributors','648','09193456789','global_dist@gmail.com','2024-04-01','2025-04-01'),(278,'Quick Solutions','464','09537890123','quick_solutions@gmail.com','2024-07-01','2025-07-01'),(562,'Fast Supply Co.','105','09315678901','fast_supplyco@gmail.com','2024-05-01','2025-05-01'),(563,'Top Products','651','09860123456','top_products@gmail.com','2024-10-01','2025-10-01'),(634,'XYZ Traders','164','09182345678','xyz_traders@gmail.com','2024-03-01','2025-03-01'),(879,'ABC Supplies','101','09171234567','abc_supplies@gmail.com','2024-01-01','2025-01-01');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier_ratings`
--

DROP TABLE IF EXISTS `supplier_ratings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier_ratings` (
  `supplier_id` int NOT NULL,
  `performance_ratings` decimal(2,1) NOT NULL,
  `date_updated` date NOT NULL,
  PRIMARY KEY (`supplier_id`),
  CONSTRAINT `supplier_ratings_ibfk_1` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier_ratings`
--

LOCK TABLES `supplier_ratings` WRITE;
/*!40000 ALTER TABLE `supplier_ratings` DISABLE KEYS */;
INSERT INTO `supplier_ratings` VALUES (31,4.8,'2024-10-12'),(56,3.9,'2024-10-05'),(103,4.3,'2024-10-10'),(196,4.4,'2024-10-18'),(262,4.7,'2024-09-30'),(278,4.0,'2024-09-01'),(562,4.6,'2024-08-20'),(563,3.7,'2024-09-28'),(634,4.2,'2024-09-15'),(879,4.5,'2024-10-01');
/*!40000 ALTER TABLE `supplier_ratings` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-09  2:48:07
