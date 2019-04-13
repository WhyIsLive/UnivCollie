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

 Date: 12/04/2019 16:07:30
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
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '病历表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for punchlog
-- ----------------------------
DROP TABLE IF EXISTS `punchlog`;
CREATE TABLE `punchlog`  (
  `ID` int(255) NOT NULL AUTO_INCREMENT,
  `success` tinyint(255) NOT NULL DEFAULT 0 COMMENT '打卡成功次数',
  `fail` tinyint(255) NOT NULL DEFAULT 0 COMMENT '打卡失败次数',
  `failtime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '打卡失败记录',
  `today` int(255) NULL DEFAULT 0,
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `get_punc`(`ID`) USING BTREE,
  CONSTRAINT `pun_id` FOREIGN KEY (`ID`) REFERENCES `user` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '打卡日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for soslog
-- ----------------------------
DROP TABLE IF EXISTS `soslog`;
CREATE TABLE `soslog`  (
  `ID` int(255) NOT NULL AUTO_INCREMENT,
  `studentid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学生id',
  `studentmid` int(11) NULL DEFAULT NULL COMMENT '安全员id',
  `tehID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教师id',
  `beigintime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开始时间',
  `overtime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '结束时间',
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '位置',
  `sostype` int(255) NULL DEFAULT NULL COMMENT '求助类型',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- View structure for adminindexid
-- ----------------------------
DROP VIEW IF EXISTS `adminindexid`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `adminindexid` AS select `user`.`ID` AS `ID`,`punchlog`.`today` AS `today`,`user`.`studentid` AS `studentid` from (`user` join `punchlog` on((`punchlog`.`ID` = `user`.`ID`)));

-- ----------------------------
-- View structure for failsafetyindex
-- ----------------------------
DROP VIEW IF EXISTS `failsafetyindex`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `failsafetyindex` AS select `user`.`ID` AS `ID`,`user`.`safetyindex` AS `safetyindex`,`punchlog`.`fail` AS `fail`,`punchlog`.`today` AS `today` from (`punchlog` join `user` on((`punchlog`.`ID` = `user`.`ID`)));

-- ----------------------------
-- View structure for securitystu
-- ----------------------------
DROP VIEW IF EXISTS `securitystu`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `securitystu` AS select `user`.`username` AS `username`,`user`.`telnumber` AS `telnumber`,`user`.`classname` AS `classname` from `user` where (`user`.`identity` = 1);

-- ----------------------------
-- View structure for userinfo
-- ----------------------------
DROP VIEW IF EXISTS `userinfo`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `userinfo` AS select `user`.`ID` AS `ID`,`punchlog`.`success` AS `success`,`punchlog`.`fail` AS `fail`,`teacher`.`teachername` AS `teachername`,`teacher`.`teachertel` AS `teachertel`,`user`.`safetyindex` AS `safetyindex`,`dis`.`allergylog` AS `allergylog`,`dis`.`medicationlog` AS `medicationlog`,`dis`.`illog` AS `illog`,`securitystu`.`username` AS `secname`,`securitystu`.`telnumber` AS `sectel`,`configurationlog`.`securitytel` AS `securitytel`,`configurationlog`.`matterstel` AS `matterstel`,`configurationlog`.`begintime` AS `begintime`,`configurationlog`.`overtime` AS `overtime` from (((((`user` join `teacher` on(((`user`.`classname` = `teacher`.`teacherclass`) or (`user`.`classname` = `teacher`.`teachermajor`)))) join `dis` on((`dis`.`ID` = `user`.`ID`))) join `punchlog` on((`punchlog`.`ID` = `user`.`ID`))) join `securitystu` on((`user`.`classname` = `securitystu`.`classname`))) join `configurationlog`) where (`configurationlog`.`ID` = 1);

SET FOREIGN_KEY_CHECKS = 1;
