/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : housesystem

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-02-04 12:08:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `userpwd` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('1', 'admin', 'admin');

-- ----------------------------
-- Table structure for t_house
-- ----------------------------
DROP TABLE IF EXISTS `t_house`;
CREATE TABLE `t_house` (
  `h_id` int(11) NOT NULL AUTO_INCREMENT,
  `house_desc` varchar(255) NOT NULL COMMENT '租房描述',
  `house_model` varchar(20) NOT NULL COMMENT '房屋类型，几室几厅',
  `house_area` varchar(20) NOT NULL COMMENT '房屋面积',
  `house_floor` varchar(20) NOT NULL COMMENT '房屋楼层',
  `house_type` varchar(20) NOT NULL COMMENT '出租方式',
  `house_price` int(10) NOT NULL COMMENT '出租价格',
  `house_address` varchar(100) NOT NULL COMMENT '出租地址',
  `house_image` varchar(1000) NOT NULL COMMENT '房屋简介照片',
  `community_name` varchar(100) NOT NULL COMMENT '小区名字',
  `house_linkman` varchar(11) NOT NULL COMMENT '房屋联系电话',
  `house_oriented` varchar(20) NOT NULL COMMENT '房屋朝向',
  `house_detailes_img` varchar(1000) NOT NULL COMMENT '房屋详细页面展示图片',
  `publisher` varchar(50) NOT NULL DEFAULT '管理员' COMMENT '发布人',
  `publish_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  PRIMARY KEY (`h_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_house
-- ----------------------------
INSERT INTO `t_house` VALUES ('22', '大豪宅', '一室两厅', '80', '6', '合租', '1000', '这里', '', '百花园', '18812345678', '南北', '', '001', '2019-02-03 22:09:52');
INSERT INTO `t_house` VALUES ('23', '小公寓', '一室一厅', '50', '2', '整租', '500', '南昌红谷滩', '/file/7d001b61-5e1c-428b-bf6c-08170b7e6457.jpg', '万达花园', '18212113122', '东西', '', '001', '2019-02-03 22:26:38');
INSERT INTO `t_house` VALUES ('24', '筒子楼', '一室一厅', '70', '高层', '整租', '5000', '九龙湖', '/file/d7586eeb-a667-4731-bb33-4a800962e99c.jpg', '玫瑰花园', '18212345678', '东西', '/file/e2b3a994-3554-4920-aa4d-76f459c3926f.jpg~', '001', '2019-02-03 22:28:13');
INSERT INTO `t_house` VALUES ('25', 'test', '123', '123', '33', '整租', '3300', '这里', '', '1233', '18112345678', '南北', '', '001', '2019-02-03 22:54:05');
INSERT INTO `t_house` VALUES ('26', '12312', '123', '123', '123', '整租', '1111', '33333', '', '222', '18712345678', '南北', '', '001', '2019-02-03 23:00:52');
INSERT INTO `t_house` VALUES ('27', '红玫瑰', '一室一厅', '50', '33', '整租', '2000', '白莲湖', 'file/a6c8dccb-eae2-4d0e-be78-e6307c97d541.jpg', '1号小区', '18945611123', '南北', '', '001', '2019-02-03 23:11:15');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `o_id` int(11) NOT NULL AUTO_INCREMENT,
  `h_id` int(11) NOT NULL COMMENT '房屋租赁id',
  `u_id` int(11) NOT NULL COMMENT '用户id',
  `order_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `order_user` varchar(20) NOT NULL,
  PRIMARY KEY (`o_id`),
  KEY `fk1` (`h_id`),
  KEY `fk2` (`u_id`),
  CONSTRAINT `fk1` FOREIGN KEY (`h_id`) REFERENCES `t_house` (`h_id`),
  CONSTRAINT `fk2` FOREIGN KEY (`u_id`) REFERENCES `t_users` (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('9', '22', '3', '2019-02-03 22:10:30', '001');

-- ----------------------------
-- Table structure for t_users
-- ----------------------------
DROP TABLE IF EXISTS `t_users`;
CREATE TABLE `t_users` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_name` varchar(20) NOT NULL,
  `u_password` varchar(100) NOT NULL,
  `u_phone_number` varchar(11) DEFAULT NULL COMMENT '用户注册手机号码，用于找回密码',
  `u_nickname` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '昵称',
  PRIMARY KEY (`u_id`),
  UNIQUE KEY `uniq` (`u_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_users
-- ----------------------------
INSERT INTO `t_users` VALUES ('3', '001', '123456', '18720229929', '001');
