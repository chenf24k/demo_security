/*
 Navicat Premium Data Transfer

 Source Server         : 腾讯云MySQL实例
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : cdb-nvns7bew.cd.tencentcdb.com:10098
 Source Schema         : zimuge

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 14/06/2020 22:22:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_pid` int(11) NULL DEFAULT NULL COMMENT '父菜单id',
  `menu_pids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '当前菜单的所有父菜单',
  `is_leaf` tinyint(4) NULL DEFAULT NULL COMMENT '0:不是叶子节点,1:是叶子节点',
  `menu_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `menu_url` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '跳转url',
  `icon` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `icon_color` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sort` tinyint(4) NULL DEFAULT NULL COMMENT '排序',
  `level` tinyint(4) NULL DEFAULT NULL COMMENT '组织层级',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '0: 启用。 1:禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '0', 0, '系统管理', NULL, NULL, NULL, 1, 1, 0);
INSERT INTO `sys_menu` VALUES (2, 1, '1', 1, '用户管理', '/sysuser', NULL, NULL, 1, 2, 0);
INSERT INTO `sys_menu` VALUES (3, 1, '1', 1, '日志管理', '/syslog', NULL, NULL, 2, 2, 0);
INSERT INTO `sys_menu` VALUES (4, 1, '1', 1, '业务一', '/biz1', NULL, NULL, 3, 2, 0);
INSERT INTO `sys_menu` VALUES (5, 1, '1', 1, '业务二', '/biz2', NULL, NULL, 4, 2, 0);
INSERT INTO `sys_menu` VALUES (6, 1, '1', 1, '看板', '/analysis', NULL, NULL, 5, 2, 0);

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `org_pid` int(11) NULL DEFAULT NULL COMMENT '上级组织编码',
  `org_pids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所有父节点id',
  `is_leaf` tinyint(4) NULL DEFAULT NULL COMMENT '0:不是叶子节点,1:是叶子节点',
  `org_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组织名',
  `address` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `phone` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮件',
  `sort` tinyint(4) NULL DEFAULT NULL COMMENT '排序',
  `level` tinyint(4) NULL DEFAULT NULL COMMENT '组织层级',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '0: 启用。 1:禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO `sys_org` VALUES (1, 0, '0', 0, '总部', NULL, NULL, NULL, 1, 1, 0);
INSERT INTO `sys_org` VALUES (2, 1, '1', 0, '研发部', NULL, NULL, NULL, 1, 2, 0);
INSERT INTO `sys_org` VALUES (3, 2, '1,2', 1, '研发一部', NULL, NULL, NULL, 1, 3, 0);
INSERT INTO `sys_org` VALUES (4, 2, '1,2', 1, '研发二部', NULL, NULL, NULL, 2, 3, 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `role_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'ADMIN、DBA',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `status` int(11) NULL DEFAULT NULL COMMENT '0表示可用',
  `create_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', '管理员', 'ROLE_admin', 1, 0, '2020-06-14 21:14:16');
INSERT INTO `sys_role` VALUES (2, '测试', '测试', 'ROLE_tester', 2, 0, '2020-06-14 21:14:23');
INSERT INTO `sys_role` VALUES (3, '研发', '研发', 'ROLE_dev', 3, 0, '2020-06-14 21:14:26');
INSERT INTO `sys_role` VALUES (4, '访客', '访客', 'ROLE_guest', 4, 0, '2020-06-14 21:14:28');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` int(11) NULL DEFAULT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1, 2);
INSERT INTO `sys_role_menu` VALUES (2, 1, 3);
INSERT INTO `sys_role_menu` VALUES (3, 1, 4);
INSERT INTO `sys_role_menu` VALUES (4, 1, 5);
INSERT INTO `sys_role_menu` VALUES (5, 1, 6);
INSERT INTO `sys_role_menu` VALUES (6, 2, 4);
INSERT INTO `sys_role_menu` VALUES (7, 2, 5);
INSERT INTO `sys_role_menu` VALUES (8, 2, 6);
INSERT INTO `sys_role_menu` VALUES (9, 3, 4);
INSERT INTO `sys_role_menu` VALUES (10, 3, 5);
INSERT INTO `sys_role_menu` VALUES (11, 3, 6);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `create_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `org_id` int(11) NULL DEFAULT NULL COMMENT '组织id',
  `enabled` int(11) NULL DEFAULT NULL COMMENT '0,无效， 1有效',
  `phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'email',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$YGHzHEmDX6wmIVE2ngCaO.b5TYHanupEtV5/X7QNqiRyf2rfTU7Nm', '2020-06-14 19:53:06', 1, 1, NULL, NULL);
INSERT INTO `sys_user` VALUES (2, 'chenf', '$2a$10$Uae8q8ReC2BL57j3RisKNO4KfyZp1rdjt./KbmsWFT9ZyB4ALmol6', '2020-06-14 19:53:08', 3, 1, NULL, NULL);
INSERT INTO `sys_user` VALUES (3, 'dengdc', '$2a$10$68mPDGiOiqzgaEY0Ebzq4u9VtKwlLQs1IgmUDv/y.OZipoUQBi2Xy', '2020-06-14 20:06:12', 3, 1, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户自增id',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色的自增id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2, 2);
INSERT INTO `sys_user_role` VALUES (3, 3, 3);

SET FOREIGN_KEY_CHECKS = 1;
