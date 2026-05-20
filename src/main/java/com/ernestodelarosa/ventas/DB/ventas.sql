CREATE DATABASE  IF NOT EXISTS `ventas_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ventas_db`;
-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: localhost    Database: ventas_db
-- ------------------------------------------------------
-- Server version	8.0.44

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
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `dpi_cliente` int NOT NULL,
  `nombre_cliente` varchar(50) NOT NULL,
  `apellido_cliente` varchar(50) NOT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `estado` int DEFAULT '1',
  PRIMARY KEY (`dpi_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (123456789,'Juan','Pérez','Ciudad de Guatemala',1),(987654321,'María','González','Quetzaltenango',1);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_venta`
--

DROP TABLE IF EXISTS `detalle_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_venta` (
  `codigo_detalle_venta` int NOT NULL AUTO_INCREMENT,
  `cantidad` int NOT NULL,
  `precio_unitario` decimal(10,2) NOT NULL,
  `subtotal` decimal(10,2) NOT NULL,
  `productos_codigo_producto` int NOT NULL,
  `ventas_codigo_venta` int NOT NULL,
  PRIMARY KEY (`codigo_detalle_venta`),
  KEY `productos_codigo_producto` (`productos_codigo_producto`),
  KEY `ventas_codigo_venta` (`ventas_codigo_venta`),
  CONSTRAINT `detalle_venta_ibfk_1` FOREIGN KEY (`productos_codigo_producto`) REFERENCES `productos` (`codigo_producto`),
  CONSTRAINT `detalle_venta_ibfk_2` FOREIGN KEY (`ventas_codigo_venta`) REFERENCES `ventas` (`codigo_venta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_venta`
--

LOCK TABLES `detalle_venta` WRITE;
/*!40000 ALTER TABLE `detalle_venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `codigo_producto` int NOT NULL AUTO_INCREMENT,
  `nombre_producto` varchar(60) NOT NULL,
  `marca` varchar(30) NOT NULL,
  `modelo` varchar(40) NOT NULL,
  `ram` varchar(10) DEFAULT NULL,
  `almacenamiento` varchar(10) DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `precio` decimal(10,2) NOT NULL,
  `stock` int DEFAULT '0',
  `estado` int DEFAULT '1',
  PRIMARY KEY (`codigo_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'iPhone 15 Pro Max','Apple','A3106','8GB','256GB','Titanio Negro',9500.00,15,1),(2,'Galaxy S24 Ultra','Samsung','SM-S928B','12GB','512GB','Gris Titanio',11000.00,10,1),(3,'Redmi Note 13 Pro','Xiaomi','23090RA98G','8GB','256GB','Azul',3200.00,25,1),(4,'POCO X6 Pro','Xiaomi','2311DRK48G','12GB','512GB','Amarillo',3500.00,20,1);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `codigo_usuario` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(60) DEFAULT NULL,
  `rol` varchar(45) DEFAULT 'USER',
  `estado` int DEFAULT '1',
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  PRIMARY KEY (`codigo_usuario`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'user','$2a$10$N5XKj6cX5zKQ8VQj9K5YpO8VQj9K5YpO8VQj9K5YpO8VQj9K5YpO','user@test.com','USER',1,'Usuario','Normal'),(4,'testuser','$2a$10$lns.XesD4qV6zJzHwrDASuSlF3u9U/FXJezlXQ5YjlZp4WhphLcq.',NULL,'USER',1,'Usuario','Test'),(5,'Andre09','$2a$10$a79Ogfim8fBGyQK1K.g43eLk2fa1BgIBwI/1bzqNIRHubCEmXDCUm',NULL,'USER',1,'Andre','Yoj'),(6,'admin2','$2a$10$KeiDxbCtIvmq.8LHqIhkEuhZ/usa2fKSGTUEhySlUmcBMosnBjkre',NULL,'ADMIN',1,'Sistema','Jefe');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventas`
--

DROP TABLE IF EXISTS `ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ventas` (
  `codigo_venta` int NOT NULL AUTO_INCREMENT,
  `fecha_venta` date NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `estado` int DEFAULT '1',
  `clientes_dpi_cliente` int NOT NULL,
  `usuarios_codigo_usuario` int NOT NULL,
  PRIMARY KEY (`codigo_venta`),
  KEY `clientes_dpi_cliente` (`clientes_dpi_cliente`),
  KEY `usuarios_codigo_usuario` (`usuarios_codigo_usuario`),
  CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`clientes_dpi_cliente`) REFERENCES `clientes` (`dpi_cliente`),
  CONSTRAINT `ventas_ibfk_2` FOREIGN KEY (`usuarios_codigo_usuario`) REFERENCES `usuarios` (`codigo_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
/*!40000 ALTER TABLE `ventas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'ventas_db'
--

--
-- Dumping routines for database 'ventas_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-19 19:22:18
