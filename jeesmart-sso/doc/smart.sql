/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50546
Source Host           : localhost:3306
Source Database       : smart

Target Server Type    : MYSQL
Target Server Version : 50546
File Encoding         : 65001

Date: 2017-12-29 14:05:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_app
-- ----------------------------
DROP TABLE IF EXISTS `sys_app`;
CREATE TABLE `sys_app` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL COMMENT '名称',
  `sort` int(11) NOT NULL COMMENT '排序',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `isEnable` bit(1) NOT NULL COMMENT '是否启用',
  `code` varchar(50) NOT NULL COMMENT '编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8 COMMENT='应用表';

-- ----------------------------
-- Records of sys_app
-- ----------------------------
INSERT INTO `sys_app` VALUES ('1', '单点登录权限管理系统', '20', '2015-06-02 11:31:44', '', 'jeesmart-sso-server');
INSERT INTO `sys_app` VALUES ('81', 'Demo管理系统', '15', '2015-11-08 17:16:39', '', 'jeesmart-sso-demo');
INSERT INTO `sys_app` VALUES ('82', '内容管理系统', '10', '2015-11-08 17:16:39', '', 'jeesmart-cms');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appId` int(11) NOT NULL COMMENT '应用ID',
  `parentId` int(11) DEFAULT NULL COMMENT '父ID',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `url` varchar(255) NOT NULL COMMENT '权限URL',
  `sort` int(11) NOT NULL COMMENT '排序',
  `isMenu` bit(1) NOT NULL COMMENT '是否菜单',
  `isEnable` bit(1) NOT NULL COMMENT '是否启用',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`),
  KEY `FK_SYS_PERM_REFERENCE_SYS_APP` (`appId`),
  CONSTRAINT `FK_SYS_PERM_REFERENCE_SYS_APP` FOREIGN KEY (`appId`) REFERENCES `sys_app` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('2', '1', null, '应用', '/admin/app', '79', '', '', 'fa-plus-circle blue');
INSERT INTO `sys_permission` VALUES ('3', '1', null, '管理员', '/admin/user', '59', '', '', 'fa-user');
INSERT INTO `sys_permission` VALUES ('4', '1', null, '角色', '/admin/role', '39', '', '', 'fa-users');
INSERT INTO `sys_permission` VALUES ('5', '1', null, '权限', '/admin/permission', '29', '', '', 'fa-key');
INSERT INTO `sys_permission` VALUES ('6', '1', '2', '应用新增', '/admin/app/edit', '4', '\0', '', 'fa-plus-circle blue');
INSERT INTO `sys_permission` VALUES ('7', '1', '2', '应用禁用', '/admin/app/enable', '3', '\0', '', 'fa-lock orange');
INSERT INTO `sys_permission` VALUES ('8', '1', '2', '应用启用', '/admin/app/enable', '2', '\0', '', 'fa-unlock green');
INSERT INTO `sys_permission` VALUES ('9', '1', '2', '应用删除', '/admin/app/delete', '1', '\0', '', 'fa-trash-o red');
INSERT INTO `sys_permission` VALUES ('10', '1', '3', '管理员新增', '/admin/user/edit', '6', '\0', '', 'fa-plus-circle blue');
INSERT INTO `sys_permission` VALUES ('11', '1', '3', '管理员禁用', '/admin/user/enable', '5', '\0', '', 'fa-lock orange');
INSERT INTO `sys_permission` VALUES ('12', '1', '3', '管理员启用', '/admin/user/enable', '4', '\0', '', 'fa-unlock green');
INSERT INTO `sys_permission` VALUES ('13', '1', '3', '管理员删除', '/admin/user/delete', '3', '\0', '', 'fa-trash-o red');
INSERT INTO `sys_permission` VALUES ('14', '1', '3', '重置密码', '/admin/user/resetPassword', '2', '\0', '', 'fa-key grey');
INSERT INTO `sys_permission` VALUES ('15', '1', '3', '分配角色', '/admin/user/allocate', '1', '\0', '', 'fa-cog grey');
INSERT INTO `sys_permission` VALUES ('16', '1', '4', '角色新增', '/admin/role/edit', '5', '\0', '', 'fa-plus-circle blue');
INSERT INTO `sys_permission` VALUES ('17', '1', '4', '角色禁用', '/admin/role/enable', '4', '\0', '', 'fa-lock orange');
INSERT INTO `sys_permission` VALUES ('18', '1', '4', '角色启用', '/admin/role/enable', '3', '\0', '', 'fa-unlock green');
INSERT INTO `sys_permission` VALUES ('19', '1', '4', '角色删除', '/admin/role/delete', '2', '\0', '', 'fa-trash-o red');
INSERT INTO `sys_permission` VALUES ('20', '1', '4', '角色授权', '/admin/role/allocate', '1', '\0', '', 'fa-cog grey');
INSERT INTO `sys_permission` VALUES ('22', '1', '2', '应用列表', '/admin/app/list', '5', '\0', '', '');
INSERT INTO `sys_permission` VALUES ('23', '1', '3', '管理员列表', '/admin/user/list', '7', '\0', '', '');
INSERT INTO `sys_permission` VALUES ('24', '1', '4', '角色列表', '/admin/role/list', '6', '\0', '', '');
INSERT INTO `sys_permission` VALUES ('25', '1', '5', '权限树列表', '/admin/permission/nodes', '1', '\0', '', '');
INSERT INTO `sys_permission` VALUES ('26', '1', '2', '应用保存', '/admin/app/save', '1', '\0', '', '');
INSERT INTO `sys_permission` VALUES ('27', '1', '3', '管理员保存', '/admin/user/save', '1', '\0', '', '');
INSERT INTO `sys_permission` VALUES ('28', '1', '4', '角色保存', '/admin/role/save', '1', '\0', '', '');
INSERT INTO `sys_permission` VALUES ('29', '1', '5', '权限保存', '/admin/permission/save', '1', '\0', '', '');
INSERT INTO `sys_permission` VALUES ('30', '1', '5', '权限删除', '/admin/permission/delete', '1', '\0', '', '');
INSERT INTO `sys_permission` VALUES ('33', '81', null, '测试', '/admin/demo', '1', '', '', 'fa-user');
INSERT INTO `sys_permission` VALUES ('34', '81', '33', '测试列表', '/admin/demo/list', '1', '\0', '', '');
INSERT INTO `sys_permission` VALUES ('35', '81', '33', '测试新增/修改', '/admin/demo/edit', '1', '\0', '', '');
INSERT INTO `sys_permission` VALUES ('36', '81', '33', '测试删除', '/admin/demo/delete', '1', '\0', '', '');
INSERT INTO `sys_permission` VALUES ('39', '1', null, '导航栏', '/admin/admin/menu', '99', '\0', '', '');
INSERT INTO `sys_permission` VALUES ('40', '81', null, '导航栏', '/admin/admin/menu', '99', '\0', '', '');
INSERT INTO `sys_permission` VALUES ('41', '1', null, '个人中心', '/admin/profile', '89', '', '', 'fa fa-desktop');
INSERT INTO `sys_permission` VALUES ('42', '1', '41', '修改密码', '/admin/profile/savePassword', '1', '\0', '', '');
INSERT INTO `sys_permission` VALUES ('44', '82', null, '栏目管理', '/admin/channel', '100', '', '', 'fa fa-th-large');
INSERT INTO `sys_permission` VALUES ('47', '82', null, '底部菜单管理', '/admin/menu', '110', '', '', 'fa fa-list-alt');
INSERT INTO `sys_permission` VALUES ('48', '82', null, '文章管理', '/admin/article', '90', '', '', 'fa fa-file-text');
INSERT INTO `sys_permission` VALUES ('49', '82', null, '产品管理', '/admin/product', '70', '', '', 'fa fa-file-powerpoint-o');
INSERT INTO `sys_permission` VALUES ('50', '82', null, '产品规格', '/admin/spec', '75', '', '', 'fa fa-cubes');
INSERT INTO `sys_permission` VALUES ('55', '82', null, '首页幻灯片管理', '/admin/slide', '120', '', '', 'fa fa-sliders');
INSERT INTO `sys_permission` VALUES ('56', '82', null, '底部菜单配置', '/admin/channelMenu/edit', '105', '', '', 'fa fa-cog');

-- ----------------------------
-- Table structure for sys_re_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_re_role_permission`;
CREATE TABLE `sys_re_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NOT NULL COMMENT '角色ID',
  `permissionId` int(11) NOT NULL COMMENT '权限ID',
  `appId` int(11) NOT NULL COMMENT '应用ID',
  PRIMARY KEY (`id`),
  KEY `FK_SYS_RE_R_REFERENCE_SYS_PERM` (`permissionId`),
  KEY `FK_SYS_RE_R_REFERENCE_SYS_ROLE` (`roleId`),
  KEY `FK_Reference_9` (`appId`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`appId`) REFERENCES `sys_app` (`id`),
  CONSTRAINT `FK_SYS_RE_R_REFERENCE_SYS_PERM` FOREIGN KEY (`permissionId`) REFERENCES `sys_permission` (`id`),
  CONSTRAINT `FK_SYS_RE_R_REFERENCE_SYS_ROLE` FOREIGN KEY (`roleId`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=254 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of sys_re_role_permission
-- ----------------------------
INSERT INTO `sys_re_role_permission` VALUES ('176', '4', '40', '81');
INSERT INTO `sys_re_role_permission` VALUES ('179', '4', '33', '81');
INSERT INTO `sys_re_role_permission` VALUES ('180', '4', '34', '81');
INSERT INTO `sys_re_role_permission` VALUES ('181', '4', '35', '81');
INSERT INTO `sys_re_role_permission` VALUES ('182', '4', '36', '81');
INSERT INTO `sys_re_role_permission` VALUES ('212', '1', '39', '1');
INSERT INTO `sys_re_role_permission` VALUES ('213', '1', '41', '1');
INSERT INTO `sys_re_role_permission` VALUES ('214', '1', '42', '1');
INSERT INTO `sys_re_role_permission` VALUES ('215', '1', '2', '1');
INSERT INTO `sys_re_role_permission` VALUES ('216', '1', '22', '1');
INSERT INTO `sys_re_role_permission` VALUES ('217', '1', '6', '1');
INSERT INTO `sys_re_role_permission` VALUES ('218', '1', '7', '1');
INSERT INTO `sys_re_role_permission` VALUES ('219', '1', '8', '1');
INSERT INTO `sys_re_role_permission` VALUES ('220', '1', '9', '1');
INSERT INTO `sys_re_role_permission` VALUES ('221', '1', '26', '1');
INSERT INTO `sys_re_role_permission` VALUES ('222', '1', '3', '1');
INSERT INTO `sys_re_role_permission` VALUES ('223', '1', '23', '1');
INSERT INTO `sys_re_role_permission` VALUES ('224', '1', '10', '1');
INSERT INTO `sys_re_role_permission` VALUES ('225', '1', '11', '1');
INSERT INTO `sys_re_role_permission` VALUES ('226', '1', '12', '1');
INSERT INTO `sys_re_role_permission` VALUES ('227', '1', '13', '1');
INSERT INTO `sys_re_role_permission` VALUES ('228', '1', '14', '1');
INSERT INTO `sys_re_role_permission` VALUES ('229', '1', '15', '1');
INSERT INTO `sys_re_role_permission` VALUES ('230', '1', '27', '1');
INSERT INTO `sys_re_role_permission` VALUES ('231', '1', '4', '1');
INSERT INTO `sys_re_role_permission` VALUES ('232', '1', '24', '1');
INSERT INTO `sys_re_role_permission` VALUES ('233', '1', '16', '1');
INSERT INTO `sys_re_role_permission` VALUES ('234', '1', '17', '1');
INSERT INTO `sys_re_role_permission` VALUES ('235', '1', '18', '1');
INSERT INTO `sys_re_role_permission` VALUES ('236', '1', '19', '1');
INSERT INTO `sys_re_role_permission` VALUES ('237', '1', '20', '1');
INSERT INTO `sys_re_role_permission` VALUES ('238', '1', '28', '1');
INSERT INTO `sys_re_role_permission` VALUES ('239', '1', '5', '1');
INSERT INTO `sys_re_role_permission` VALUES ('240', '1', '25', '1');
INSERT INTO `sys_re_role_permission` VALUES ('241', '1', '29', '1');
INSERT INTO `sys_re_role_permission` VALUES ('242', '1', '30', '1');
INSERT INTO `sys_re_role_permission` VALUES ('243', '5', '55', '82');
INSERT INTO `sys_re_role_permission` VALUES ('244', '5', '47', '82');
INSERT INTO `sys_re_role_permission` VALUES ('245', '5', '56', '82');
INSERT INTO `sys_re_role_permission` VALUES ('246', '5', '44', '82');
INSERT INTO `sys_re_role_permission` VALUES ('247', '5', '48', '82');
INSERT INTO `sys_re_role_permission` VALUES ('249', '5', '50', '82');
INSERT INTO `sys_re_role_permission` VALUES ('250', '5', '49', '82');

-- ----------------------------
-- Table structure for sys_re_user_app
-- ----------------------------
DROP TABLE IF EXISTS `sys_re_user_app`;
CREATE TABLE `sys_re_user_app` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL COMMENT '用户ID ',
  `appId` int(11) NOT NULL COMMENT '应用ID',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_10` (`appId`),
  KEY `FK_Reference_11` (`userId`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`appId`) REFERENCES `sys_app` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`userId`) REFERENCES `sys_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='用户应用表';

-- ----------------------------
-- Records of sys_re_user_app
-- ----------------------------
INSERT INTO `sys_re_user_app` VALUES ('9', '2', '81');
INSERT INTO `sys_re_user_app` VALUES ('10', '2', '82');
INSERT INTO `sys_re_user_app` VALUES ('11', '2', '1');

-- ----------------------------
-- Table structure for sys_re_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_re_user_role`;
CREATE TABLE `sys_re_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL COMMENT '用户ID ',
  `roleId` int(11) NOT NULL COMMENT '角色ID',
  `appId` int(11) NOT NULL COMMENT '应用ID',
  PRIMARY KEY (`id`),
  KEY `FK_SYS_RE_U_REFERENCE_SYS_USER` (`userId`),
  KEY `FK_SYS_RE_U_REFERENCE_SYS_ROLE` (`roleId`),
  KEY `FK_Reference_8` (`appId`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`appId`) REFERENCES `sys_app` (`id`),
  CONSTRAINT `FK_SYS_RE_U_REFERENCE_SYS_ROLE` FOREIGN KEY (`roleId`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `FK_SYS_RE_U_REFERENCE_SYS_USER` FOREIGN KEY (`userId`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_re_user_role
-- ----------------------------
INSERT INTO `sys_re_user_role` VALUES ('7', '2', '4', '81');
INSERT INTO `sys_re_user_role` VALUES ('8', '2', '1', '1');
INSERT INTO `sys_re_user_role` VALUES ('9', '2', '5', '82');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appId` int(11) NOT NULL COMMENT '应用ID',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `sort` int(11) NOT NULL COMMENT '排序',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `isEnable` bit(1) NOT NULL COMMENT '是否启用',
  PRIMARY KEY (`id`),
  KEY `FK_SYS_ROLE_REFERENCE_SYS_APP` (`appId`),
  CONSTRAINT `FK_SYS_ROLE_REFERENCE_SYS_APP` FOREIGN KEY (`appId`) REFERENCES `sys_app` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '1', '单点登录权限系统管理员角色', '999', '', '');
INSERT INTO `sys_role` VALUES ('4', '81', 'Demo系统管理员角色', '1', '', '');
INSERT INTO `sys_role` VALUES ('5', '82', '内容系统管理员角色', '1', '', '');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(50) NOT NULL COMMENT '登录名',
  `password` varchar(100) NOT NULL COMMENT '密码(加密)',
  `lastLoginIp` varchar(20) DEFAULT NULL COMMENT '最后登录IP',
  `lastLoginTime` datetime DEFAULT NULL COMMENT '最后登录时间',
  `loginCount` int(11) NOT NULL COMMENT '登录总次数',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `isEnable` bit(1) NOT NULL COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('2', 'admin', '26524bdf4ea266f131566a89e8f4972c', null, null, '0', '2015-06-02 11:31:56', '');
