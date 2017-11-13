/*
Navicat MySQL Data Transfer

Source Server         : myLocalhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : student

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-11-13 17:09:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for clazz
-- ----------------------------
DROP TABLE IF EXISTS `clazz`;
CREATE TABLE `clazz` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `clazz_no` varchar(10) DEFAULT NULL COMMENT '课程编号',
  `clazz_name` varchar(30) DEFAULT NULL COMMENT '课程名称',
  `clazz_type` int(1) DEFAULT NULL COMMENT '课程类型（0-统考课程；1-衔接课程；2-学分互认课程；3-毕业论文）',
  PRIMARY KEY (`id`),
  KEY `clazz_no` (`clazz_no`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `idcard_no` varchar(20) DEFAULT NULL,
  `clazz_no` varchar(10) DEFAULT NULL,
  `score_excel` int(10) DEFAULT NULL COMMENT 'excel中的分数',
  `score_html` int(10) DEFAULT NULL COMMENT '网站上的分数',
  `time` varchar(20) DEFAULT NULL COMMENT '网站上的时间',
  PRIMARY KEY (`id`),
  KEY `f1` (`idcard_no`),
  KEY `f2` (`clazz_no`),
  CONSTRAINT `f1` FOREIGN KEY (`idcard_no`) REFERENCES `student` (`idcard_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `f2` FOREIGN KEY (`clazz_no`) REFERENCES `clazz` (`clazz_no`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_no` int(10) DEFAULT NULL COMMENT '序号',
  `student_name` varchar(255) DEFAULT NULL COMMENT '学生姓名',
  `sex` int(1) DEFAULT NULL COMMENT '性别（0-男；1-女）',
  `nation` varchar(10) DEFAULT NULL COMMENT '民族',
  `oldticket_no` varchar(20) DEFAULT NULL COMMENT '老准考证号',
  `newticket_no` varchar(20) DEFAULT NULL COMMENT '新准考证号',
  `idcard_no` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `school` varchar(50) DEFAULT NULL COMMENT '主考学校',
  `speciality` varchar(50) DEFAULT NULL COMMENT '衔接专业',
  `level` int(1) DEFAULT NULL COMMENT '报考层次（0-专科；1-本科）',
  `register_time` varchar(20) DEFAULT NULL COMMENT '注册入学时间',
  `department` varchar(50) DEFAULT NULL COMMENT '所属项目部',
  `principal` varchar(20) DEFAULT NULL COMMENT '项目负责人',
  `unit` varchar(50) DEFAULT NULL COMMENT '上报单位',
  `teacher` varchar(20) DEFAULT NULL COMMENT '老师',
  `is_membership` varchar(10) DEFAULT NULL COMMENT '是否在籍、退报、转专业、或毕业、拟毕业',
  `turn_speciality` varchar(50) DEFAULT NULL COMMENT '所转专业',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `new_remark` varchar(100) DEFAULT NULL COMMENT '2016年5月备注',
  PRIMARY KEY (`id`),
  KEY `idcard_no` (`idcard_no`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
