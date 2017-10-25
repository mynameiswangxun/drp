-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: drp
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Temporary view structure for view `aim_view`
--

DROP TABLE IF EXISTS `aim_view`;
/*!50001 DROP VIEW IF EXISTS `aim_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `aim_view` AS SELECT 
 1 AS `id`,
 1 AS `name`,
 1 AS `client_temi_id`,
 1 AS `client_temi_level_id`,
 1 AS `client_temi_level_name`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `id` int(11) NOT NULL DEFAULT '10000',
  `pid` int(11) NOT NULL,
  `client_level_id` char(3) DEFAULT NULL COMMENT '分销商级别',
  `name` varchar(20) NOT NULL COMMENT '名称',
  `client_id` varchar(10) DEFAULT NULL COMMENT '分销商代码',
  `bank_account` varchar(20) DEFAULT NULL COMMENT '银行账号',
  `contact_tel` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `address` varchar(30) DEFAULT NULL COMMENT '地址',
  `zip_code` varchar(10) CHARACTER SET swe7 DEFAULT NULL COMMENT '邮编',
  `is_leaf` char(1) DEFAULT NULL COMMENT '是否为叶子\nY：是\nN：不是',
  `is_client` char(1) DEFAULT NULL COMMENT '是否为分销商',
  PRIMARY KEY (`id`),
  KEY `fk_client_client_idx` (`pid`),
  KEY `fk_client_data_dic1_idx` (`client_level_id`),
  CONSTRAINT `fk_client_client` FOREIGN KEY (`pid`) REFERENCES `client` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_client_data_dic1` FOREIGN KEY (`client_level_id`) REFERENCES `data_dic` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (10000,0,NULL,'所有分销商',NULL,NULL,NULL,NULL,NULL,'N','N'),(10001,10000,NULL,'华北区',NULL,NULL,NULL,NULL,NULL,'N','N'),(10002,10000,NULL,'东北区',NULL,NULL,NULL,NULL,NULL,'Y','N'),(10003,10000,NULL,'华南区',NULL,NULL,NULL,NULL,NULL,'Y','N'),(10004,10001,NULL,'北京市',NULL,NULL,NULL,NULL,NULL,'N','N'),(10005,10004,'100','北京医药股份有限公司','A0001',NULL,NULL,NULL,NULL,'Y','Y');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clinet_inv`
--

DROP TABLE IF EXISTS `clinet_inv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clinet_inv` (
  `client_id` int(11) NOT NULL COMMENT '分销商id',
  `fiscal_year_period_id` int(11) NOT NULL COMMENT '会计核算期',
  `items_id` varchar(10) NOT NULL COMMENT '物料',
  `init_qty` decimal(12,2) NOT NULL COMMENT '分销商库存明细\n期初数量',
  `in_qty` decimal(12,2) DEFAULT '0.00' COMMENT '入库数量',
  `out_qty` decimal(12,2) DEFAULT '0.00' COMMENT '出库数量',
  PRIMARY KEY (`client_id`,`fiscal_year_period_id`,`items_id`),
  KEY `fk_clinet_inv_client1_idx` (`client_id`),
  KEY `fk_clinet_inv_fiscal_year_period1_idx` (`fiscal_year_period_id`),
  KEY `fk_clinet_inv_items1_idx` (`items_id`),
  CONSTRAINT `fk_clinet_inv_client1` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_clinet_inv_fiscal_year_period1` FOREIGN KEY (`fiscal_year_period_id`) REFERENCES `fiscal_year_period` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_clinet_inv_items1` FOREIGN KEY (`items_id`) REFERENCES `items` (`items_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinet_inv`
--

LOCK TABLES `clinet_inv` WRITE;
/*!40000 ALTER TABLE `clinet_inv` DISABLE KEYS */;
/*!40000 ALTER TABLE `clinet_inv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `data_dic`
--

DROP TABLE IF EXISTS `data_dic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_dic` (
  `id` char(3) NOT NULL COMMENT '代码',
  `name` varchar(20) NOT NULL COMMENT '名称',
  `category` char(1) CHARACTER SET big5 NOT NULL COMMENT '类别\nA：分销商级别\nB：终端客户类型\nC：物料类别\nD：物料计量单位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_dic`
--

LOCK TABLES `data_dic` WRITE;
/*!40000 ALTER TABLE `data_dic` DISABLE KEYS */;
INSERT INTO `data_dic` VALUES ('100','一级分销商','A'),('101','二级分销商','A'),('102','三级分销商','A'),('103','四级分销商','A'),('200','甲级医院','B'),('201','乙级医院','B'),('202','丙级医院','B'),('203','药店','B'),('204','其他','B'),('300','医疗器械','C'),('301','中成药','C'),('302','西药','C'),('401','盒','D'),('402','片','D'),('403','箱','D');
/*!40000 ALTER TABLE `data_dic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fiscal_year_period`
--

DROP TABLE IF EXISTS `fiscal_year_period`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fiscal_year_period` (
  `id` int(11) NOT NULL COMMENT '会计核算期：id',
  `fiscal_year` int(11) NOT NULL COMMENT '核算年',
  `fiscal_month` int(11) NOT NULL COMMENT '核算月',
  `begin_date` date NOT NULL COMMENT '开始日期',
  `end_date` date NOT NULL COMMENT '结束日期',
  `status` char(1) NOT NULL COMMENT '核算状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fiscal_year_period`
--

LOCK TABLES `fiscal_year_period` WRITE;
/*!40000 ALTER TABLE `fiscal_year_period` DISABLE KEYS */;
/*!40000 ALTER TABLE `fiscal_year_period` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flow_detail`
--

DROP TABLE IF EXISTS `flow_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flow_detail` (
  `flow_list_num` varchar(20) NOT NULL COMMENT '流向单明细：流向单号',
  `items_id` varchar(10) NOT NULL COMMENT '物料id',
  `aim_client_id` int(11) NOT NULL COMMENT '需求方客户',
  `op_num` decimal(12,2) DEFAULT '0.00' COMMENT '操作数量',
  `adjust_num` decimal(12,2) DEFAULT '0.00' COMMENT '调整数量',
  `adjust_reason` varchar(40) DEFAULT NULL COMMENT '调整理由',
  `adjust_flag` char(1) DEFAULT 'N' COMMENT '调整标记\nY：已调整\nN：未调整',
  PRIMARY KEY (`flow_list_num`,`items_id`),
  KEY `fk_flow_detail_items1_idx` (`items_id`),
  KEY `fk_flow_detail_flow_list1_idx` (`flow_list_num`),
  CONSTRAINT `fk_flow_detail_flow_list1` FOREIGN KEY (`flow_list_num`) REFERENCES `flow_list` (`flow_num`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_flow_detail_items1` FOREIGN KEY (`items_id`) REFERENCES `items` (`items_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flow_detail`
--

LOCK TABLES `flow_detail` WRITE;
/*!40000 ALTER TABLE `flow_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `flow_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flow_list`
--

DROP TABLE IF EXISTS `flow_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flow_list` (
  `flow_num` varchar(20) NOT NULL COMMENT '流向单主信息/盘点单号',
  `fiscal_year_period_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL COMMENT '供方分销商id',
  `op_date` date NOT NULL COMMENT '操作日期',
  `recorder_id` varchar(10) NOT NULL COMMENT '录入人id',
  `vou_sts` char(1) NOT NULL DEFAULT 'N' COMMENT '单据状态\nS：送审\nN：录入',
  `adjust_id` varchar(10) NOT NULL COMMENT '审核人id',
  `adjust_adte` date DEFAULT NULL COMMENT '审核日期',
  `spotter_id` varchar(10) NOT NULL COMMENT '抽查人id',
  `spot_date` date DEFAULT NULL COMMENT '抽查日期',
  `spot_desc` varchar(40) DEFAULT NULL COMMENT '抽查描述',
  `confirmer_id` varchar(10) NOT NULL COMMENT '复审人代码',
  `confirm_date` date DEFAULT NULL COMMENT '复审人日期',
  `op_type` char(1) DEFAULT NULL COMMENT '操作类型\nA：流向单\nB：盘点单',
  PRIMARY KEY (`flow_num`),
  KEY `fk_flow_list_fiscal_year_period1_idx` (`fiscal_year_period_id`),
  KEY `fk_flow_list_client1_idx` (`client_id`),
  KEY `fk_flow_list_user_msg1_idx` (`recorder_id`),
  KEY `fk_flow_list_user_msg2_idx` (`adjust_id`),
  KEY `fk_flow_list_user_msg3_idx` (`spotter_id`),
  KEY `fk_flow_list_user_msg4_idx` (`confirmer_id`),
  CONSTRAINT `fk_flow_list_client1` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_flow_list_fiscal_year_period1` FOREIGN KEY (`fiscal_year_period_id`) REFERENCES `fiscal_year_period` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_flow_list_user_msg1` FOREIGN KEY (`recorder_id`) REFERENCES `user_msg` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_flow_list_user_msg2` FOREIGN KEY (`adjust_id`) REFERENCES `user_msg` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_flow_list_user_msg3` FOREIGN KEY (`spotter_id`) REFERENCES `user_msg` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_flow_list_user_msg4` FOREIGN KEY (`confirmer_id`) REFERENCES `user_msg` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flow_list`
--

LOCK TABLES `flow_list` WRITE;
/*!40000 ALTER TABLE `flow_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `flow_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `items` (
  `items_id` varchar(10) NOT NULL COMMENT '物料代码',
  `item_category_id` char(3) NOT NULL COMMENT '物料类别',
  `item_unit_id` char(3) NOT NULL COMMENT '计量单位',
  `name` varchar(20) DEFAULT NULL COMMENT '物料名称',
  `spec` varchar(20) DEFAULT NULL COMMENT '规格',
  `pattern` varchar(20) DEFAULT NULL COMMENT '型号',
  PRIMARY KEY (`items_id`),
  KEY `fk_items_data_dic1_idx` (`item_category_id`),
  KEY `fk_items_data_dic2_idx` (`item_unit_id`),
  CONSTRAINT `fk_items_data_dic1` FOREIGN KEY (`item_category_id`) REFERENCES `data_dic` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_items_data_dic2` FOREIGN KEY (`item_unit_id`) REFERENCES `data_dic` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `temi_client`
--

DROP TABLE IF EXISTS `temi_client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temi_client` (
  `id` int(11) NOT NULL DEFAULT '20000',
  `pid` int(11) NOT NULL,
  `temi_client_level_id` char(3) DEFAULT NULL COMMENT '终端客户类别',
  `name` varchar(20) NOT NULL,
  `temi_client_id` varchar(20) DEFAULT NULL COMMENT '终端客户代码',
  `contact_tel` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `contactor` varchar(10) DEFAULT NULL COMMENT '联系人',
  `address` varchar(30) DEFAULT NULL COMMENT '地址',
  `zip_code` varchar(10) DEFAULT NULL COMMENT '邮编',
  `is_leaf` char(1) DEFAULT NULL,
  `is_temi_client` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_temi_client_temi_client1_idx` (`pid`),
  KEY `fk_temi_client_data_dic1_idx` (`temi_client_level_id`),
  CONSTRAINT `fk_temi_client_data_dic1` FOREIGN KEY (`temi_client_level_id`) REFERENCES `data_dic` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_temi_client_temi_client1` FOREIGN KEY (`pid`) REFERENCES `temi_client` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `temi_client`
--

LOCK TABLES `temi_client` WRITE;
/*!40000 ALTER TABLE `temi_client` DISABLE KEYS */;
INSERT INTO `temi_client` VALUES (20000,0,NULL,'所有终端客户',NULL,NULL,NULL,NULL,NULL,'N','N'),(20001,20000,NULL,'华北区',NULL,NULL,NULL,NULL,NULL,'N','N'),(20002,20000,NULL,'东北区',NULL,NULL,NULL,NULL,NULL,'Y','N'),(20003,20000,NULL,'华南区',NULL,NULL,NULL,NULL,NULL,'Y','N'),(20004,20001,NULL,'北京市',NULL,NULL,NULL,NULL,NULL,'N','N'),(20005,20004,'200','北京中医医院','B0001',NULL,NULL,NULL,NULL,'Y','Y');
/*!40000 ALTER TABLE `temi_client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_msg`
--

DROP TABLE IF EXISTS `user_msg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_msg` (
  `id` varchar(10) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `contact_tel` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_msg`
--

LOCK TABLES `user_msg` WRITE;
/*!40000 ALTER TABLE `user_msg` DISABLE KEYS */;
INSERT INTO `user_msg` VALUES ('root','系统管理员','root123',NULL,NULL,NULL);
/*!40000 ALTER TABLE `user_msg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `aim_view`
--

/*!50001 DROP VIEW IF EXISTS `aim_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `aim_view` AS select `c`.`id` AS `id`,`c`.`name` AS `name`,`c`.`client_id` AS `client_temi_id`,`c`.`client_level_id` AS `client_temi_level_id`,`d`.`name` AS `client_temi_level_name` from (`client` `c` join `data_dic` `d` on((`c`.`client_level_id` = `d`.`id`))) where (`c`.`is_client` = 'Y') union select `tc`.`id` AS `id`,`tc`.`name` AS `name`,`tc`.`temi_client_id` AS `temi_client_id`,`tc`.`temi_client_level_id` AS `temi_client_level_id`,`d`.`name` AS `name` from (`temi_client` `tc` join `data_dic` `d` on((`tc`.`temi_client_level_id` = `d`.`id`))) where (`tc`.`is_temi_client` = 'Y') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-21 22:35:58
