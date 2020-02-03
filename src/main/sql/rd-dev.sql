/*
 Navicat MySQL Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : rd-dev

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 04/02/2020 05:18:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `uuid` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, NULL, b'0', NULL, NULL, NULL, 'Admnistrator', 'admin');
INSERT INTO `role` VALUES (2, NULL, b'0', NULL, NULL, NULL, 'Operator', 'operator');
INSERT INTO `role` VALUES (3, NULL, b'0', NULL, NULL, NULL, 'User', 'user');
COMMIT;

-- ----------------------------
-- Table structure for seq_role
-- ----------------------------
DROP TABLE IF EXISTS `seq_role`;
CREATE TABLE `seq_role` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of seq_role
-- ----------------------------
BEGIN;
INSERT INTO `seq_role` VALUES (1);
COMMIT;

-- ----------------------------
-- Table structure for seq_user
-- ----------------------------
DROP TABLE IF EXISTS `seq_user`;
CREATE TABLE `seq_user` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of seq_user
-- ----------------------------
BEGIN;
INSERT INTO `seq_user` VALUES (151);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `uuid` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `username` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, '2020-02-04 01:05:53', b'0', '2020-02-04 01:05:53', '4f6a2750-22d6-4d7b-94ed-cb001e256be7', NULL, 'test', '$2a$10$pW7SagvM6OhYh/XLSR.pKujy6FYnGJnfylGsrly0UW22sFVyLfcxe', 'test');
INSERT INTO `user` VALUES (52, '2020-02-04 03:48:20', b'0', '2020-02-04 03:48:20', 'ae1d8c83-6715-48b2-bbd0-45dc37406d34', NULL, 'wang', '$2a$10$JGdSnsrmjUDaPHIY9NBsyOd7omOCzZWAEbnrT9qM9yclfGH3EiHs2', 'wang');
COMMIT;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  KEY `FK859n2jvi8ivhui0rl0esws6o` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
INSERT INTO `user_role` VALUES (52, 2);
INSERT INTO `user_role` VALUES (1, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
