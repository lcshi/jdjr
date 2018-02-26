/*
Navicat MySQL Data Transfer

Source Server         : localhost(mysql)
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-03-22 18:08:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for logs
-- ----------------------------
DROP TABLE IF EXISTS `logs`;
CREATE TABLE `logs` (
  `userid` varchar(50) DEFAULT NULL,
  `class` varchar(255) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `message` text,
  `createTime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logs
-- ----------------------------
INSERT INTO `logs` VALUES ('admin', 'Common.log4js.UserFilter', 'doFilter', 'ERROR', '初始化admin的信息', '2016-03-22 18:07:08');
INSERT INTO `logs` VALUES ('admin', 'service.impl.UserServiceImpl', 'GetUser', 'ERROR', 'error-------测试', '2016-03-22 18:07:08');

-- ----------------------------
-- Table structure for schedulejob
-- ----------------------------
DROP TABLE IF EXISTS `schedulejob`;
CREATE TABLE `schedulejob` (
  `jobid` mediumtext,
  `jobName` varchar(30) NOT NULL,
  `jobGroup` varchar(30) NOT NULL,
  `jobStatus` varchar(30) DEFAULT NULL,
  `cronExpression` varchar(30) DEFAULT NULL,
  `description` varchar(30) DEFAULT NULL,
  `beanClass` varchar(30) DEFAULT NULL,
  `isConcurrent` varchar(30) DEFAULT NULL,
  `springId` varchar(30) DEFAULT NULL,
  `methodName` varchar(30) DEFAULT NULL,
  `crateTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`jobName`,`jobGroup`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of schedulejob
-- ----------------------------
INSERT INTO `schedulejob` VALUES ('1', 'test', 'test', '1', '0/1 * * * * ?', 'test', 'Common.quartz.TaskTest', '0', null, 'run', null, '2014-08-08 14:17:34');

-- ----------------------------
-- Table structure for students
-- ----------------------------
DROP TABLE IF EXISTS `students`;
CREATE TABLE `students` (
  `Code` varchar(255) DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of students
-- ----------------------------

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `UserCode` varchar(255) DEFAULT NULL,
  `UserName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('1', 'test');
INSERT INTO `userinfo` VALUES ('1', 'test');
INSERT INTO `userinfo` VALUES ('1', 'test');
INSERT INTO `userinfo` VALUES ('2', '22');
