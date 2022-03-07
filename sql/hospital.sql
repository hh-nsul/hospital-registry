# Database "yygh_hosp"

CREATE DATABASE IF NOT EXISTS `hospital_registry_hospital` CHARACTER SET utf8mb4;

USE `hospital_registry_hospital`;


# Table "hospital_set"

CREATE TABLE `hospital_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Serial Number',
  `hosname` varchar(100) DEFAULT NULL COMMENT 'Hospital Name',
  `hoscode` varchar(30) DEFAULT NULL COMMENT 'Hospital Code',
  `api_url` varchar(100) DEFAULT NULL COMMENT 'API Path',
  `sign_key` varchar(50) DEFAULT NULL COMMENT 'Signing Key',
  `contacts_name` varchar(20) DEFAULT NULL COMMENT 'Contact Name',
  `contacts_phone` varchar(11) DEFAULT NULL COMMENT 'Contact Phone',
  `status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '1: available, 0: interface locked',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation Time',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update Time',
  `is_deleted` tinyint(3) NOT NULL DEFAULT '0' COMMENT 'Logical Deletion(1:deleted, 0:not deleted)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_hoscode` (`hoscode`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='Hospital Information Table';