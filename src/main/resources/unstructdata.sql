/*
Navicat MySQL Data Transfer

Source Server         : mysql_connection
Source Server Version : 50619
Source Host           : localhost:3306
Source Database       : unstructdata

Target Server Type    : MYSQL
Target Server Version : 50619
File Encoding         : 65001

Date: 2015-09-18 01:20:39
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `hdfs_file`
-- ----------------------------
DROP TABLE IF EXISTS `hdfs_file`;
CREATE TABLE `hdfs_file` (
  `file_id` int(11) NOT NULL COMMENT '文件id',
  `file_name` varchar(100) NOT NULL COMMENT '文件名',
  `parent_id` int(11) NOT NULL COMMENT '父节点id',
  `file_size` int(11) DEFAULT NULL COMMENT '文件大小，单位为B',
  `file_type` int(11) NOT NULL COMMENT '文件类型，0为文件夹',
  `file_url` varchar(100) NOT NULL COMMENT '所在hdfs文件系统路径',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `user_id` int(11) DEFAULT NULL COMMENT '作者',
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hdfs_file
-- ----------------------------
INSERT INTO `hdfs_file` VALUES ('1', 'root', '0', '0', '0', '/root', '2015-09-16 17:42:08', '1');
INSERT INTO `hdfs_file` VALUES ('2', 'file1', '1', '2', '1', '/root/file1', '2015-09-16 17:43:09', '1');
INSERT INTO `hdfs_file` VALUES ('3', 'file2', '1', '2', '1', '/root/file2', '2015-09-16 17:43:14', '1');
