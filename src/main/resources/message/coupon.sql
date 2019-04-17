/*
Navicat MySQL Data Transfer

Source Server         : 101.251.100.8
Source Server Version : 50614
Source Host           : 192.168.1.8:3306
Source Database       : coupon

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2019-04-17 15:54:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) NOT NULL DEFAULT '' COMMENT '账号',
  `account_name` varchar(255) DEFAULT '' COMMENT '用户名',
  `password` varchar(255) DEFAULT '' COMMENT '密码',
  `channel_code` varchar(255) DEFAULT '' COMMENT '渠道编码',
  `business_code` varchar(255) DEFAULT '' COMMENT '商户编码',
  `enable` int(1) DEFAULT '0' COMMENT '启用状态 0:未启用 1:启用 2:废弃',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', 'zhangsan', '张三', '123456', 'qudao', 'starBucks', '1', '', '2019-04-09 18:43:51', '2019-04-09 18:43:53');
INSERT INTO `account` VALUES ('2', '233656', '水资源', 'string', 'string', 'string', '0', 'string', '2019-04-11 13:37:19', '2019-04-11 13:37:19');
INSERT INTO `account` VALUES ('3', 'string', 'string', 'string', 'string', 'string', '0', 'string', '2019-04-11 12:23:28', '2019-04-11 12:23:28');
INSERT INTO `account` VALUES ('4', 'string', 'string', 'string', 'string', 'string', '0', 'string', '2019-04-11 12:31:05', '2019-04-11 12:31:05');
INSERT INTO `account` VALUES ('5', 'string', 'string', 'string', 'string', 'string', '0', 'string', '2019-04-11 12:38:12', '2019-04-11 12:38:12');
INSERT INTO `account` VALUES ('6', 'string', 'string', 'string', 'string', 'string', '0', 'string', '2019-04-11 13:03:51', '2019-04-11 13:03:51');
INSERT INTO `account` VALUES ('7', 'string', 'string', 'string', 'string', 'string', '0', 'string', '2019-04-11 13:21:19', '2019-04-11 13:21:19');

-- ----------------------------
-- Table structure for business
-- ----------------------------
DROP TABLE IF EXISTS `business`;
CREATE TABLE `business` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `business_code` varchar(255) DEFAULT '' COMMENT '商户编码',
  `business_name` varchar(255) DEFAULT NULL COMMENT '商户名称',
  `enable` int(1) DEFAULT '0' COMMENT '启用状态 0:未启用 1:启用 2:废弃',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of business
-- ----------------------------
INSERT INTO `business` VALUES ('1', 'starBucks', '星巴克', '1', '', '2019-04-09 18:44:28', '2019-04-09 18:45:02');

-- ----------------------------
-- Table structure for channel
-- ----------------------------
DROP TABLE IF EXISTS `channel`;
CREATE TABLE `channel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `channel_code` varchar(255) DEFAULT '' COMMENT '渠道编码',
  `channel_name` varchar(255) DEFAULT '' COMMENT '渠道名称',
  `enable` int(1) DEFAULT '0' COMMENT '启用状态 0:未启用 1:启用 2:废弃',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of channel
-- ----------------------------
INSERT INTO `channel` VALUES ('1', 'qudao', '渠道', '1', '', '2019-04-09 18:44:12', '2019-04-09 18:44:14');
INSERT INTO `channel` VALUES ('2', 'string', 'string', '0', 'string', '2019-04-11 15:41:29', '2019-04-11 15:41:29');

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `coupon` varchar(255) NOT NULL DEFAULT '' COMMENT '券码',
  `slat` varchar(255) DEFAULT NULL,
  `encryption` varchar(255) DEFAULT NULL,
  `coupon_type` varchar(255) DEFAULT '' COMMENT '券码种类',
  `price` decimal(10,2) DEFAULT '0.00' COMMENT '金额',
  `channel_code` varchar(255) DEFAULT '' COMMENT '渠道',
  `business_code` varchar(255) DEFAULT '' COMMENT '商户',
  `status` int(1) DEFAULT '0' COMMENT '状态',
  `end_time` datetime DEFAULT NULL COMMENT '到期时间',
  `write_off_account` varchar(255) DEFAULT '' COMMENT '核销账户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of coupon
-- ----------------------------
INSERT INTO `coupon` VALUES ('1', 'asdasdas', null, null, 'a', '10.00', 'qudao', 'starBucks', '0', '2019-04-12 11:45:36', '', '2019-04-10 11:45:41', '2019-04-10 11:45:45');

-- ----------------------------
-- Table structure for coupon_type
-- ----------------------------
DROP TABLE IF EXISTS `coupon_type`;
CREATE TABLE `coupon_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `enable` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of coupon_type
-- ----------------------------
INSERT INTO `coupon_type` VALUES ('1', 'a', '种类A', '10.00', '1', null, '2019-04-10 11:44:59', '2019-04-10 11:45:02');

-- ----------------------------
-- Table structure for oauth_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_token`;
CREATE TABLE `oauth_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(255) DEFAULT '' COMMENT 'Token',
  `account` varchar(255) DEFAULT '' COMMENT '账户',
  `expired` datetime DEFAULT NULL COMMENT '到期时间',
  `status` int(1) DEFAULT '0' COMMENT '状态',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_token
-- ----------------------------
