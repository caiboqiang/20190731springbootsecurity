/*
Navicat MySQL Data Transfer

Source Server         : 192.168.100.230_3306
Source Server Version : 50726
Source Host           : 192.168.100.230:3306
Source Database       : jolimarkEdu

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2019-08-02 17:30:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creationtime` datetime DEFAULT NULL,
  `tenantid` int(11) DEFAULT NULL,
  `orderno` varchar(32) DEFAULT NULL COMMENT '订单编号',
  `ordertype` int(11) DEFAULT NULL COMMENT '订单类型（1:充值，2:套餐,）',
  `userid` int(11) DEFAULT NULL,
  `consumer` int(11) DEFAULT NULL COMMENT '订单对象（1老师，2学生，3机构）',
  `amount` int(11) DEFAULT NULL COMMENT '订单总额',
  `discountamount` int(11) DEFAULT NULL COMMENT '优惠金额',
  `balancededuction` int(11) DEFAULT NULL COMMENT '余额抵扣金额 余额实行先扣钱，订单取消时，再回滚余额。',
  `payamount` int(11) DEFAULT NULL COMMENT '实际支付金额',
  `couponcode` varchar(32) DEFAULT NULL COMMENT '优惠券编码',
  `status` int(11) DEFAULT NULL COMMENT '订单支付状态（1：已支付，2：未支付）',
  `statustime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('29', '2019-07-03 19:31:12', null, '763149852', '2', '201', '2', '10', '0', null, '10', null, '2', '2019-07-03 19:31:12');
INSERT INTO `orders` VALUES ('30', '2019-07-09 11:19:14', null, '378921465', '2', '561', '1', '200', '0', null, '200', null, '2', '2019-07-09 11:19:14');
INSERT INTO `orders` VALUES ('31', '2019-07-16 10:41:04', null, '493621857', '2', '201', '2', '2', '0', null, '2', null, '2', '2019-07-16 10:41:04');
INSERT INTO `orders` VALUES ('32', '2019-08-02 10:52:25', '1', '2019080200200879398854721052251', '2', '302', '1', '39900', '0', null, '39900', null, '3', '2019-08-02 10:52:25');
INSERT INTO `orders` VALUES ('33', '2019-08-02 10:55:34', '1', '20190802003006814888311251055342', '2', '302', '1', '39900', '0', null, '39900', null, '3', '2019-08-02 10:55:34');
