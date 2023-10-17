# Host: localhost  (Version: 5.5.36)
# Date: 2021-01-10 15:11:43
# Generator: MySQL-Front 5.3  (Build 4.13)

/*!40101 SET NAMES utf8 */;

#
# Source for table "group"
#

DROP TABLE IF EXISTS `group`;
CREATE TABLE `group` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2313 DEFAULT CHARSET=utf8;

#
# Data for table "group"
#

INSERT INTO `group` VALUES (1,'朋友'),(2,'老师'),(3,'家人'),(4,'好兄弟'),(7,NULL),(231,NULL),(2312,NULL);

#
# Source for table "person"
#

DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `groupName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

#
# Data for table "person"
#

INSERT INTO `person` VALUES (1,'小明','1212332','187312312','月球','男','好兄弟'),(3,'小江','4353453','172213121','地球','男','家人'),(4,'小红','2133453','198321311','水星','女','朋友'),(5,'Harry','110','18779322304','魔法城堡','男','classmates');
