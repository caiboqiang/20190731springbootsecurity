/*
 Navicat Premium Data Transfer

 Source Server         : rm-bp10n0d57hynv1ee83o.mysql.rds.aliyuncs.com
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : rm-bp10n0d57hynv1ee83o.mysql.rds.aliyuncs.com:3306
 Source Schema         : db_fh_app

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 20/08/2020 11:06:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_app_user_level
-- ----------------------------
DROP TABLE IF EXISTS `t_app_user_level`;
CREATE TABLE `t_app_user_level`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '等级ID',
  `level_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '等级名称',
  `level_money` decimal(15, 5) DEFAULT NULL COMMENT '所需金额',
  `login_times` int(11) DEFAULT NULL COMMENT '登陆次数',
  `play_times` int(11) DEFAULT NULL COMMENT 'play时长',
  `vip_header_img` int(11) DEFAULT NULL COMMENT 'Vip专属头像(个)',
  `recharge_rate` decimal(15, 5) DEFAULT NULL COMMENT '充值返点(%)',
  `is_fast_back` tinyint(4) DEFAULT NULL COMMENT '急速提现(0:无1:有)',
  `server` tinyint(4) DEFAULT NULL COMMENT '专属客服(0:无1:1对1)',
  `level_description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '等级描述',
  `create_time` timestamp(0) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30003 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '等级表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_app_user_level
-- ----------------------------
INSERT INTO `t_app_user_level` VALUES (0, '游客', 0.00000, NULL, NULL, 0, 0.00000, 0, 0, NULL, '2020-07-30 17:57:54');
INSERT INTO `t_app_user_level` VALUES (1, 'vip0', 1.00000, NULL, NULL, 1, 1.00000, 0, 1, NULL, NULL);
INSERT INTO `t_app_user_level` VALUES (2, 'vip1', 50.00000, NULL, NULL, 2, 2.00000, 1, 0, NULL, NULL);
INSERT INTO `t_app_user_level` VALUES (3, 'vip2', 200.00000, NULL, NULL, 3, 3.00000, 0, 0, NULL, NULL);
INSERT INTO `t_app_user_level` VALUES (4, 'vip3', 500.00000, NULL, NULL, 4, 4.00000, 0, 0, NULL, '2019-11-19 09:32:54');
INSERT INTO `t_app_user_level` VALUES (5, 'vip4', 2000.00000, NULL, NULL, 5, 5.00000, 1, 1, NULL, '2019-11-19 09:32:58');
INSERT INTO `t_app_user_level` VALUES (6, 'vip5', 5000.00000, NULL, NULL, 6, 5.00000, 1, 1, NULL, '2019-11-19 09:33:00');
INSERT INTO `t_app_user_level` VALUES (7, 'vip6', 10000.00000, NULL, NULL, 8, 7.00000, 1, 1, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
