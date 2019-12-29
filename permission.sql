/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : gdpi

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-12-28 16:56:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `permissionId` int(11) NOT NULL AUTO_INCREMENT,
  `permissionName` varchar(50) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `parentName` varchar(50) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  PRIMARY KEY (`permissionId`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('5', '系统管理', null, '', '', '1');
INSERT INTO `permission` VALUES ('6', '基础数据', null, '', '', '1');
INSERT INTO `permission` VALUES ('7', '用户管理', '5', '系统管理', '/user/pageInfo', '2');
INSERT INTO `permission` VALUES ('8', '角色管理', '5', '系统管理', '/role/findByPage', '2');
INSERT INTO `permission` VALUES ('9', '权限管理', '5', '系统管理', '/permission/findByPage', '2');
INSERT INTO `permission` VALUES ('10', '产品管理', '6', '基础数据', '', '2');
INSERT INTO `permission` VALUES ('11', '订单管理', '6', '基础数据', '', '2');
INSERT INTO `permission` VALUES ('12', '添加', '7', '用户管理', '/user/save', '3');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '管理员');
INSERT INTO `role` VALUES ('3', '普通用户');

-- ----------------------------
-- Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `roleId` int(11) NOT NULL,
  `permissionId` int(11) NOT NULL,
  PRIMARY KEY (`roleId`,`permissionId`),
  KEY `fk_4` (`permissionId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '5');
INSERT INTO `role_permission` VALUES ('1', '6');
INSERT INTO `role_permission` VALUES ('1', '7');
INSERT INTO `role_permission` VALUES ('1', '8');
INSERT INTO `role_permission` VALUES ('1', '9');
INSERT INTO `role_permission` VALUES ('1', '10');
INSERT INTO `role_permission` VALUES ('1', '11');
INSERT INTO `role_permission` VALUES ('1', '12');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `age` int(20) DEFAULT NULL,
  `password` varchar(50) DEFAULT '888',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'feng', '男', '18', '0a113ef6b61820daa5611c870ed8d5ee');
INSERT INTO `user` VALUES ('2', '孔某', '女', '20', '888');
INSERT INTO `user` VALUES ('3', '邱某', '男', '22', '888');
INSERT INTO `user` VALUES ('4', 'aa', '男', '21', '0a113ef6b61820daa5611c870ed8d5ee');
INSERT INTO `user` VALUES ('5', 'bb', '女', '22', '888');
INSERT INTO `user` VALUES ('6', 'cc', '女', '25', '888');
INSERT INTO `user` VALUES ('7', 'dd', '男', '28', '888');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`),
  KEY `fk_2` (`roleId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1');
