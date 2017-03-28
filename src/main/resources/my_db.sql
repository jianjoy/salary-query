/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : my_db

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-03-29 07:46:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uname` varchar(255) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `emp_id` int(11) DEFAULT NULL,
  `ctime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `account_role_type` int(11) DEFAULT NULL,
  `status` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '1', '2017-03-26 11:54:31', '1', '1');

-- ----------------------------
-- Table structure for employee_info
-- ----------------------------
DROP TABLE IF EXISTS `employee_info`;
CREATE TABLE `employee_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department` varchar(255) DEFAULT NULL,
  `identity_no` varchar(255) DEFAULT NULL,
  `reg_date` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `ctime` timestamp NULL DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee_info
-- ----------------------------
INSERT INTO `employee_info` VALUES ('1', '北京研发', 'BJ15033101', '2010-11-01', '周健', '1', '2010-11-01 17:02:01', 'zhoujian10@sina.cn');

-- ----------------------------
-- Table structure for login_log
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) DEFAULT NULL,
  `login_ip` varchar(255) DEFAULT NULL,
  `login_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of login_log
-- ----------------------------
INSERT INTO `login_log` VALUES ('1', '1', '112.33.12.12', '2017-03-11 17:10:25');
INSERT INTO `login_log` VALUES ('2', '1', '23.23.23.234', '2017-03-26 08:47:27');
INSERT INTO `login_log` VALUES ('3', '1', '0:0:0:0:0:0:0:1', '2017-03-26 11:54:36');
INSERT INTO `login_log` VALUES ('4', '1', '0:0:0:0:0:0:0:1', '2017-03-26 17:43:50');
INSERT INTO `login_log` VALUES ('5', '1', '0:0:0:0:0:0:0:1', '2017-03-26 17:47:04');
INSERT INTO `login_log` VALUES ('6', '1', '0:0:0:0:0:0:0:1', '2017-03-26 17:52:56');
INSERT INTO `login_log` VALUES ('7', '1', '0:0:0:0:0:0:0:1', '2017-03-26 17:55:00');
INSERT INTO `login_log` VALUES ('8', '1', '0:0:0:0:0:0:0:1', '2017-03-26 17:58:37');
INSERT INTO `login_log` VALUES ('9', '1', '0:0:0:0:0:0:0:1', '2017-03-26 18:01:35');
INSERT INTO `login_log` VALUES ('10', '1', '0:0:0:0:0:0:0:1', '2017-03-26 18:04:42');
INSERT INTO `login_log` VALUES ('11', '1', '0:0:0:0:0:0:0:1', '2017-03-26 18:19:54');
INSERT INTO `login_log` VALUES ('12', '1', '0:0:0:0:0:0:0:1', '2017-03-26 18:21:34');
INSERT INTO `login_log` VALUES ('13', '1', '0:0:0:0:0:0:0:1', '2017-03-27 08:05:20');
INSERT INTO `login_log` VALUES ('14', '1', '0:0:0:0:0:0:0:1', '2017-03-27 08:13:32');
INSERT INTO `login_log` VALUES ('15', '1', '0:0:0:0:0:0:0:1', '2017-03-27 08:17:05');
INSERT INTO `login_log` VALUES ('16', '1', '0:0:0:0:0:0:0:1', '2017-03-27 08:23:54');
INSERT INTO `login_log` VALUES ('17', '1', '0:0:0:0:0:0:0:1', '2017-03-27 22:44:17');
INSERT INTO `login_log` VALUES ('18', '1', '0:0:0:0:0:0:0:1', '2017-03-27 22:45:28');

-- ----------------------------
-- Table structure for salary_info
-- ----------------------------
DROP TABLE IF EXISTS `salary_info`;
CREATE TABLE `salary_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `emp_id` int(11) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `bonus` double DEFAULT NULL,
  `absent_salary_deduction` double DEFAULT NULL,
  `sick_leave_salary_deduction` double DEFAULT NULL,
  `personal_pension_payment` double DEFAULT NULL,
  `personal_medical_insurance_payment` double DEFAULT NULL,
  `personal_provident_fund_payment` double DEFAULT NULL,
  `personal_income_tax` double DEFAULT NULL,
  `real_salary` double DEFAULT NULL,
  `salary_date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of salary_info
-- ----------------------------
INSERT INTO `salary_info` VALUES ('1', '1', '300000', '0', '0', '0', '1120', '283', '1680', '3099.25', '293817.75', '2017-02');
