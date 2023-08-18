-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: pose-developer
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `algorithm`
--

DROP TABLE IF EXISTS `algorithm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `algorithm` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `name` varchar(255) DEFAULT NULL,
                             `type` int DEFAULT NULL COMMENT '类型(0普通算法 1专用算法  2普通数据集 3 专用数据集)',
                             `sport_category` int DEFAULT NULL,
                             `template_id` int DEFAULT NULL,
                             `install_type` int DEFAULT NULL COMMENT '部署方式（0：云端部署 1：本地部署）',
                             `uploader` int DEFAULT NULL,
                             `file` varchar(255) DEFAULT NULL,
                             `docs` text,
                             `example` text,
                             `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='算法表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `algorithm`
--

INSERT INTO `algorithm` VALUES (1,'足球射门姿势算法',0,1,1,1,1,'ea','文档','这是一个案例','2023-08-14 17:47:57'),(3,'游泳姿势优化算法',1,8,1,1,1,'ea','文档','这是一个案例','2023-08-14 17:45:11'),(5,'网球发球技术分析算法',0,12,1,1,1,'ea','分析网球发球的动作和力量，评估发球技术的准确性和速度，提供改进建议。','这是一个案例','2023-08-14 17:47:47'),(10,'田径起跑姿势优化算法',1,1,2,2,1,'e1a1f62d-85a4-4a96-8af5-0440d41e0abd','文档','样例','2023-08-14 17:47:20'),(14,'足球专用犯规识别算法',0,7,1,0,1,NULL,'文档','文档demo','2023-08-14 02:27:31'),(15,'篮球手部识别算法',1,7,1,0,1,'a66bd977-82c5-4d3b-9a97-f3aa7c5f24a7','文档','样例demo','2023-08-14 02:32:31'),(16,'乒乓球角度识别编辑',1,11,4,0,1,'a45af1c1-a66a-4a80-b3e7-2ae6c743907f','文档','demo','2023-08-14 17:53:22');

--
-- Table structure for table `data_set`
--

DROP TABLE IF EXISTS `data_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `data_set` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `name` varchar(255) DEFAULT NULL COMMENT '数据集名',
                            `type` int DEFAULT NULL COMMENT '数据集类型（0:普通数据集 ,1:专用数据集）',
                            `sport_category` int DEFAULT NULL COMMENT '支持的体育类型',
                            `file` varchar(255) DEFAULT NULL COMMENT '文件',
                            `demo` text COMMENT '数据集样例',
                            `install_type` int DEFAULT NULL COMMENT '部署方式（0：云端部署 1：本地部署）',
                            `uploader` int DEFAULT NULL COMMENT '上传人',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='数据集表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_set`
--

INSERT INTO `data_set` VALUES (1,'足球数据集',1,1,'4b4901b8-7af4-444a-b044-3067902cfaf2','demo',1,1,'2023-08-08 23:57:51');

--
-- Table structure for table `session`
--

DROP TABLE IF EXISTS `session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `session` (
                           `id` varchar(255) NOT NULL COMMENT 'id',
                           `user_id` int DEFAULT NULL COMMENT '用户ID',
                           `ip` varchar(255) DEFAULT NULL COMMENT 'ip地址',
                           `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='登录session表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session`
--

INSERT INTO `session` VALUES ('ddc95d0e-8e07-4849-81a7-452c90a5e607',12,'172.27.224.1','2023-08-18 17:30:29');

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
                        `username` varchar(255) DEFAULT NULL,
                        `password` varchar(255) DEFAULT NULL,
                        `mark` text,
                        `create_time` datetime DEFAULT NULL COMMENT '授权时间',
                        `attr` json DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

INSERT INTO `user` VALUES (1,'王子健','root','$argon2id$v=19$m=65536,t=2,p=1$1VdMY/Yxaal5oXNyd10g5A$jNxdp7UHCVNgp8M5EV9lkRi15ZheMaRbSqKGpWuNCbI','密码123456','2023-07-27 15:02:54','{}'),(4,'程序员小王','wangzijian121','$argon2id$v=19$m=65536,t=2,p=1$Qpo5pGW3a20epFq6MXHbaA$out6LUQ8s7AcFi1USfCMQ4cfNQ9NZczA9Q4QRE9/Ht4','小明','2023-07-27 15:08:12','{}'),(6,'程序员小王','wangzijian123','$argon2id$v=19$m=65536,t=2,p=1$8DLavyqz+npDmF3DT2wI5g$XE/GCCbR5K7TDFmdxenuByhfMhdaFLals2APTPYtwWo','开发者','2023-07-23 22:24:19','{}'),(12,'程序员小王123','wangzijian','$argon2id$v=19$m=65536,t=2,p=1$foqzw2Z+4eZyagRxNCzDMA$mO+e27FQfdYUlCTX5kZsFdUQqDxYeKb076uKv5o/oiM','小明','2023-07-27 15:08:12','{}');
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-18 17:51:36
