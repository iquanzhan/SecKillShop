/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : miaosha

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-12-01 14:58:07
*/

SET
FOREIGN_KEY_CHECKS
=
0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`
(
  `id`           bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_name`   varchar(16)   DEFAULT NULL,
  `goods_title`  varchar(64)   DEFAULT NULL,
  `goods_img`    varchar(64)   DEFAULT NULL,
  `goods_detail` longtext,
  `goods_price`  decimal(10,2) DEFAULT NULL,
  `goods_stock`  int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods`
VALUES ('1', '三星手机', '三星手机就是好', null, '三星手机就是好三星手机就是好三星手机就是好三星手机就是好三星手机就是好三星手机就是好三星手机就是好', '3999.80', '100');

-- ----------------------------
-- Table structure for miaosha_goods
-- ----------------------------
DROP TABLE IF EXISTS `miaosha_goods`;
CREATE TABLE `miaosha_goods`
(
  `id`            bigint(20) DEFAULT NULL,
  `goods_id`      bigint(20) DEFAULT NULL,
  `miaosha_price` decimal(10,2) DEFAULT NULL,
  `stock_count`   int(11) DEFAULT NULL,
  `start_date`    datetime      DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `end_date`      datetime      DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of miaosha_goods
-- ----------------------------
INSERT INTO `miaosha_goods`
VALUES ('1', '1', '2999.00', '100', '2018-12-01 14:55:03', '2018-12-01 14:53:55');

-- ----------------------------
-- Table structure for miaosha_order
-- ----------------------------
DROP TABLE IF EXISTS `miaosha_order`;
CREATE TABLE `miaosha_order`
(
  `id`       bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id`  bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `goods_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of miaosha_order
-- ----------------------------

-- ----------------------------
-- Table structure for miaosha_user
-- ----------------------------
DROP TABLE IF EXISTS `miaosha_user`;
CREATE TABLE `miaosha_user`
(
  `id`              bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname`        varchar(255) DEFAULT NULL,
  `password`        varchar(32)  DEFAULT NULL,
  `salt`            varchar(10)  DEFAULT NULL,
  `head`            varchar(128) DEFAULT NULL,
  `register_date`   datetime     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `last_login_date` datetime     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `login_count`     int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13888888889 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of miaosha_user
-- ----------------------------
INSERT INTO `miaosha_user`
VALUES ('13888888888', 'name', 'b341baccc44ce82099f48dbcc1605b76', '123456789', null, '2018-11-25 17:38:11', '2018-11-25 17:38:11', null);

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`
(
  `id`               bigint(20) DEFAULT NULL,
  `user_id`          bigint(20) DEFAULT NULL,
  `goods_id`         bigint(20) DEFAULT NULL,
  `delivery_addr_id` bigint(20) DEFAULT NULL,
  `goods_name`       varchar(16)   DEFAULT NULL,
  `goods_count`      int(11) DEFAULT NULL,
  `goods_price`      decimal(10,2) DEFAULT NULL,
  `order_channel`    tinyint(4) DEFAULT NULL,
  `status`           tinyint(4) DEFAULT NULL,
  `create_date`      datetime      DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `pay_date`         datetime      DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_info
-- ----------------------------
