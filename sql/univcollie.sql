/*
 Navicat MySQL Data Transfer

 Source Server         : UnivCollie
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : univcollie

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 14/03/2019 09:16:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for configurationlog
-- ----------------------------
DROP TABLE IF EXISTS `configurationlog`;
CREATE TABLE `configurationlog`  (
  `ID` int(255) NOT NULL AUTO_INCREMENT,
  `daypunch` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '打卡周期',
  `begintime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开始时间',
  `overtime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '结束时间',
  `safelocationone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '安全位置A(国测坐标)',
  `safelocationtwo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '安全位置B(国测坐标)',
  `securitytel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '安保电话',
  `matterstel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医务电话',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of configurationlog
-- ----------------------------
INSERT INTO `configurationlog` VALUES (1, NULL, '19:00', '21:00', '321321', '43243141', '2132132', '32132132');

-- ----------------------------
-- Table structure for dis
-- ----------------------------
DROP TABLE IF EXISTS `dis`;
CREATE TABLE `dis`  (
  `ID` int(255) NOT NULL AUTO_INCREMENT,
  `allergylog` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '过敏史',
  `medicationlog` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '常用药',
  `illog` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '病史',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `get_disea`(`ID`) USING BTREE,
  CONSTRAINT `dis_id` FOREIGN KEY (`ID`) REFERENCES `user` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '病历表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dis
-- ----------------------------
INSERT INTO `dis` VALUES (1, NULL, NULL, '');
INSERT INTO `dis` VALUES (2, NULL, NULL, '');
INSERT INTO `dis` VALUES (3, NULL, NULL, '');
INSERT INTO `dis` VALUES (4, NULL, NULL, '');
INSERT INTO `dis` VALUES (9, '3', '3', '3');
INSERT INTO `dis` VALUES (10, '3', '3', '3');
INSERT INTO `dis` VALUES (11, '4', '4', '4');
INSERT INTO `dis` VALUES (16, '3', '4', '1');
INSERT INTO `dis` VALUES (17, NULL, NULL, '');
INSERT INTO `dis` VALUES (18, NULL, NULL, '');
INSERT INTO `dis` VALUES (19, NULL, NULL, '');
INSERT INTO `dis` VALUES (20, NULL, NULL, '');
INSERT INTO `dis` VALUES (21, NULL, NULL, '');
INSERT INTO `dis` VALUES (22, NULL, NULL, '');
INSERT INTO `dis` VALUES (23, NULL, NULL, '');

-- ----------------------------
-- Table structure for punchlog
-- ----------------------------
DROP TABLE IF EXISTS `punchlog`;
CREATE TABLE `punchlog`  (
  `ID` int(255) NOT NULL AUTO_INCREMENT,
  `success` tinyint(255) NOT NULL DEFAULT 0 COMMENT '打卡成功次数',
  `fail` tinyint(255) NOT NULL DEFAULT 0 COMMENT '打卡失败次数',
  `failtime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '打卡失败记录',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `get_punc`(`ID`) USING BTREE,
  CONSTRAINT `pun_id` FOREIGN KEY (`ID`) REFERENCES `user` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '打卡日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of punchlog
-- ----------------------------
INSERT INTO `punchlog` VALUES (1, 24, 2, NULL);
INSERT INTO `punchlog` VALUES (2, 43, 6, NULL);
INSERT INTO `punchlog` VALUES (3, 0, 0, NULL);
INSERT INTO `punchlog` VALUES (4, 0, 0, NULL);
INSERT INTO `punchlog` VALUES (9, 0, 0, NULL);
INSERT INTO `punchlog` VALUES (11, 3, 3, '3');
INSERT INTO `punchlog` VALUES (16, 3, 0, '3');
INSERT INTO `punchlog` VALUES (17, 0, 0, NULL);
INSERT INTO `punchlog` VALUES (18, 0, 0, NULL);
INSERT INTO `punchlog` VALUES (19, 0, 0, NULL);
INSERT INTO `punchlog` VALUES (20, 0, 0, NULL);
INSERT INTO `punchlog` VALUES (21, 0, 0, NULL);
INSERT INTO `punchlog` VALUES (22, 0, 0, NULL);
INSERT INTO `punchlog` VALUES (23, 0, 0, NULL);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `teachername` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `teacherpassword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `teachertel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `teachermajor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `teacherclass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`ID`, `teachertel`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, 'HAH', '123456', '123143254', '2017202502', '2017202501');
INSERT INTO `teacher` VALUES (2, 'bbk', '123455', '1232131', '', '2017202503');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `ID` int(255) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学名',
  `password` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '密码',
  `telnumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
  `studentid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号',
  `age` tinyint(32) NULL DEFAULT NULL COMMENT '年龄',
  `sex` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `identity` tinyint(32) NULL DEFAULT NULL COMMENT '权限职位',
  `classname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级',
  `safetyindex` tinyint(32) NULL DEFAULT NULL COMMENT '安全指数',
  PRIMARY KEY (`ID`, `telnumber`, `studentid`) USING BTREE,
  UNIQUE INDEX `get_id`(`ID`) USING BTREE,
  UNIQUE INDEX `telnumber`(`telnumber`) USING BTREE,
  UNIQUE INDEX `studentid`(`studentid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '邹俊林', '1213326TT', '18313023', '201720250', 0, '未知', 0, '2017202501', 100);
INSERT INTO `user` VALUES (2, '邹俊林', '1213326TT', '18313023631', '201720250126', 0, '未知', 0, '2017202502', 100);
INSERT INTO `user` VALUES (3, '赵屹', '123456', '15912299763', '201720250124', 0, '未知', 1, '2017202501', 100);
INSERT INTO `user` VALUES (4, '邹过过', '123456', '15087260418', '201720250111', 0, '未知', 1, '2017202502', 100);
INSERT INTO `user` VALUES (9, '3', '3', '3', '3', 3, '3', 3, '3', 3);
INSERT INTO `user` VALUES (10, '哈哈哈', '123456', '18313023321', '12345678', 0, '未知', 0, '未知', 100);
INSERT INTO `user` VALUES (11, '零零', '123456', '15922299733', '201720250199', 0, '未知', 0, '未知', 100);
INSERT INTO `user` VALUES (16, '4', '4', '4', '4', 34, '34', 34, '34', 34);
INSERT INTO `user` VALUES (17, '发生的', '1234556', '18313023698', '12321312', 0, '未知', 0, '未知', 100);
INSERT INTO `user` VALUES (18, '阿瑟东', '123456', '15913023631', '123123123', 0, '未知', 0, '未知', 100);
INSERT INTO `user` VALUES (19, '大锤', '123456', '18313022631', '201720250120', 0, '未知', 0, '未知', 100);
INSERT INTO `user` VALUES (20, '燃烧你的梦', '123456789', '15331594176', '201720250221', 0, '未知', 0, '未知', 100);
INSERT INTO `user` VALUES (21, '邹俊林', '123456', '18313023632', '051254258766', 0, '未知', 0, '未知', 100);
INSERT INTO `user` VALUES (22, '我靠', '123456', '18313823633', '201720250564', 0, '未知', 0, '未知', 100);
INSERT INTO `user` VALUES (23, '小疯疯', '123456', '15608847275', '201720250209', 0, '未知', 0, '2017202502', 100);

-- ----------------------------
-- View structure for securitystu
-- ----------------------------
DROP VIEW IF EXISTS `securitystu`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `securitystu` AS select `user`.`username` AS `username`,`user`.`telnumber` AS `telnumber`,`user`.`classname` AS `classname` from `user` where (`user`.`identity` = 1);

-- ----------------------------
-- View structure for userinfo
-- ----------------------------
DROP VIEW IF EXISTS `userinfo`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `userinfo` AS select `user`.`ID` AS `ID`,`punchlog`.`success` AS `success`,`punchlog`.`fail` AS `fail`,`teacher`.`teachername` AS `teachername`,`teacher`.`teachertel` AS `teachertel`,`user`.`safetyindex` AS `safetyindex`,`dis`.`allergylog` AS `allergylog`,`dis`.`medicationlog` AS `medicationlog`,`securitystu`.`username` AS `secname`,`securitystu`.`telnumber` AS `sectel`,`configurationlog`.`securitytel` AS `securitytel`,`configurationlog`.`matterstel` AS `matterstel`,`configurationlog`.`begintime` AS `begintime`,`configurationlog`.`overtime` AS `overtime` from (((((`user` join `teacher` on(((`user`.`classname` = `teacher`.`teacherclass`) or (`user`.`classname` = `teacher`.`teachermajor`)))) join `dis` on((`dis`.`ID` = `user`.`ID`))) join `punchlog` on((`punchlog`.`ID` = `user`.`ID`))) join `securitystu` on((`user`.`classname` = `securitystu`.`classname`))) join `configurationlog`) where (`configurationlog`.`ID` = 1);

SET FOREIGN_KEY_CHECKS = 1;
