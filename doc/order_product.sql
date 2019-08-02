/*
Navicat MySQL Data Transfer

Source Server         : 192.168.100.230_3306
Source Server Version : 50726
Source Host           : 192.168.100.230:3306
Source Database       : jolimarkEdu

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2019-08-02 17:30:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `order_product`
-- ----------------------------
DROP TABLE IF EXISTS `order_product`;
CREATE TABLE `order_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creationtime` datetime DEFAULT NULL,
  `tenantid` int(11) DEFAULT NULL,
  `orderno` varchar(32) DEFAULT NULL COMMENT '订单号',
  `lineno` int(11) DEFAULT NULL COMMENT '充值不产生订单行数据',
  `productcode` varchar(32) DEFAULT NULL COMMENT '产品编码',
  `productname` varchar(64) DEFAULT NULL COMMENT '产品名称',
  `producttype` int(11) DEFAULT NULL COMMENT '产品类型（包月，课程时间，试用）',
  `capacity` int(11) DEFAULT NULL COMMENT '产品容量',
  `unit` varchar(32) DEFAULT NULL COMMENT '产品单位',
  `count` int(11) DEFAULT NULL COMMENT '订购数量',
  `price` int(11) DEFAULT NULL COMMENT '单价',
  `status` int(11) DEFAULT NULL,
  `statustime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_product
-- ----------------------------
INSERT INTO `order_product` VALUES ('26', '2019-07-03 19:31:12', null, '763149852', null, '9529', '学生包月', '1', null, '月', '1', '10', null, null);
INSERT INTO `order_product` VALUES ('28', '2019-07-16 10:41:04', null, '493621857', null, '9529', '学生包月', '1', null, '月', '1', '2', null, null);
INSERT INTO `order_product` VALUES ('29', '2019-08-02 10:52:25', '1', '2019080200200879398854721052251', null, '9527', '教师包月(50学生)', '1', '50', '月', '3', '399', null, null);
INSERT INTO `order_product` VALUES ('30', '2019-08-02 10:55:34', '1', '20190802003006814888311251055342', null, '9527', '教师包月(50学生)', '1', '50', '月', '3', '399', null, null);
