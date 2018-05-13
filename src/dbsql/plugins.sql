/*
SQLyog Ultimate v8.32 
MySQL - 5.6.21 : Database - plugins
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`plugins` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `plugins`;

/*Table structure for table `login_log` */

DROP TABLE IF EXISTS `login_log`;

CREATE TABLE `login_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `acc_id` int(11) DEFAULT NULL COMMENT 'fk-sys_acc.sys_acc_id',
  `login_ip` varchar(32) DEFAULT NULL,
  `login_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

/*Data for the table `login_log` */

insert  into `login_log`(`id`,`acc_id`,`login_ip`,`login_time`) values (1,1,'127.0.0.1','2015-09-22 09:47:46'),(2,1,'127.0.0.1','2015-09-22 10:12:36'),(3,1,'127.0.0.1','2015-09-22 10:25:39'),(4,1,'127.0.0.1','2015-09-22 10:28:16'),(5,1,'127.0.0.1','2015-09-22 11:21:25'),(6,1,'127.0.0.1','2015-09-22 15:11:42'),(7,1,'127.0.0.1','2015-09-22 15:13:11'),(8,1,'127.0.0.1','2015-09-22 16:08:50'),(9,1,'127.0.0.1','2015-09-22 16:29:34'),(10,1,'127.0.0.1','2015-09-22 16:31:12'),(11,1,'127.0.0.1','2015-09-22 16:35:09'),(12,1,'127.0.0.1','2015-09-22 16:38:12'),(13,1,'127.0.0.1','2015-09-22 17:00:23'),(14,1,'127.0.0.1','2015-09-23 11:30:11'),(15,1,'127.0.0.1','2015-09-23 11:33:52'),(16,1,'127.0.0.1','2015-09-23 11:35:22'),(17,1,'127.0.0.1','2015-09-24 16:03:10'),(18,1,'127.0.0.1','2015-09-24 16:18:07'),(19,1,'127.0.0.1','2015-09-24 16:27:44'),(20,1,'127.0.0.1','2015-09-24 16:30:47'),(21,1,'127.0.0.1','2015-09-24 16:36:40'),(22,1,'127.0.0.1','2015-09-24 16:39:25'),(23,1,'127.0.0.1','2015-09-29 12:56:04'),(24,1,'127.0.0.1','2015-09-29 12:59:26'),(25,1,'127.0.0.1','2015-09-29 13:08:53'),(26,1,'127.0.0.1','2015-09-29 13:14:19'),(27,1,'127.0.0.1','2015-09-29 14:47:01'),(28,1,'127.0.0.1','2016-02-17 20:15:05'),(29,1,'127.0.0.1','2016-06-06 23:50:05');

/*Table structure for table `msgcontent` */

DROP TABLE IF EXISTS `msgcontent`;

CREATE TABLE `msgcontent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `systemmode` int(2) NOT NULL,
  `type` int(2) NOT NULL,
  `isAdd` int(2) NOT NULL,
  `userid` int(11) DEFAULT NULL,
  `mainid` int(11) DEFAULT NULL,
  `extra` varchar(200) DEFAULT NULL,
  `touserids` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

/*Data for the table `msgcontent` */

insert  into `msgcontent`(`id`,`systemmode`,`type`,`isAdd`,`userid`,`mainid`,`extra`,`touserids`) values (26,4,3,2,3,NULL,'{\"releaseNote\":\"123123\",\"size\":\"123123\",\"url\":\"http://www.baidu.com\",\"versionName\":\"微博\",\"versionNo\":\"2\"}','15868168473'),(27,4,3,2,3,NULL,'{\"releaseNote\":\"123123\",\"size\":\"123123\",\"url\":\"http://www.baidu.com\",\"versionName\":\"微博\",\"versionNo\":\"2\"}','15868168473'),(28,4,3,2,3,NULL,'{\"releaseNote\":\"123123\",\"size\":\"123123\",\"url\":\"http://183.131.13.86:9080/publicCommon/apkhttp://www.baidu.com\",\"versionName\":\"微博\",\"versionNo\":\"2\"}','15868168473');

/*Table structure for table `operate_log` */

DROP TABLE IF EXISTS `operate_log`;

CREATE TABLE `operate_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `acc_id` int(11) DEFAULT NULL COMMENT 'fk-sys_acc.sys_acc_id',
  `operate_type` varchar(32) DEFAULT NULL,
  `operate_des` varchar(200) DEFAULT NULL,
  `operate_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

/*Data for the table `operate_log` */

insert  into `operate_log`(`id`,`acc_id`,`operate_type`,`operate_des`,`operate_time`) values (1,1,'菜单管理','保存更改菜单','2015-09-22 09:49:03'),(2,1,'菜单管理','保存更改菜单','2015-09-22 09:49:21'),(3,1,'角色管理','分配角色权限','2015-09-22 09:49:35'),(4,1,'菜单管理','保存更改菜单','2015-09-22 09:54:26'),(5,1,'菜单管理','保存更改菜单','2015-09-22 09:55:28'),(6,1,'菜单管理','保存更改菜单','2015-09-22 09:57:48'),(7,1,'菜单管理','保存更改菜单','2015-09-22 10:05:17'),(8,1,'菜单管理','保存更改菜单','2015-09-22 10:06:38'),(9,1,'菜单管理','保存更改菜单','2015-09-22 10:14:03'),(10,1,'菜单管理','保存更改菜单','2015-09-22 10:20:52'),(11,1,'菜单管理','保存更改菜单','2015-09-22 10:20:55'),(12,1,'菜单管理','保存更改菜单','2015-09-22 10:21:22'),(13,1,'菜单管理','保存更改菜单','2015-09-22 10:21:23'),(14,1,'菜单管理','保存更改菜单','2015-09-22 10:22:01'),(15,1,'菜单管理','保存更改菜单','2015-09-22 10:22:15'),(16,1,'菜单管理','保存更改菜单','2015-09-22 10:23:19'),(17,1,'菜单管理','保存更改菜单','2015-09-22 10:26:24'),(18,1,'菜单管理','保存更改菜单','2015-09-22 10:28:31'),(19,1,'角色管理','分配角色权限','2015-09-22 10:28:43'),(20,1,'菜单管理','保存更改菜单','2015-09-22 10:37:57'),(21,1,'角色管理','分配角色权限','2015-09-22 10:38:06'),(22,1,'菜单管理','保存更改菜单','2015-09-22 10:41:32'),(23,1,'角色管理','分配角色权限','2015-09-22 10:41:45'),(24,1,'菜单管理','保存更改菜单','2015-09-22 10:42:08'),(25,1,'菜单管理','保存更改菜单','2015-09-22 10:42:22'),(26,1,'菜单管理','保存更改菜单','2015-09-22 11:14:50'),(27,1,'角色管理','分配角色权限','2015-09-22 11:15:06'),(28,1,'菜单管理','保存更改菜单','2015-09-22 11:16:12'),(29,1,'菜单管理','保存更改菜单','2015-09-22 11:16:48'),(30,1,'菜单管理','保存更改菜单','2015-09-22 11:17:17'),(31,1,'菜单管理','删除菜单','2015-09-22 11:17:53'),(32,1,'菜单管理','保存更改菜单','2015-09-22 11:18:08'),(33,1,'菜单管理','保存更改菜单','2015-09-22 11:19:07'),(34,1,'菜单管理','保存更改菜单','2015-09-22 11:21:53'),(35,1,'角色管理','分配角色权限','2015-09-22 11:22:05'),(36,1,'菜单管理','保存更改菜单','2015-09-22 11:24:09'),(37,1,'菜单管理','保存更改菜单','2015-09-22 11:24:49'),(38,1,'菜单管理','保存更改菜单','2015-09-22 11:30:25'),(39,1,'角色管理','分配角色权限','2015-09-22 11:30:33'),(40,1,'菜单管理','保存更改菜单','2015-09-22 11:31:27'),(41,1,'菜单管理','保存更改菜单','2015-09-22 11:32:56'),(42,1,'角色管理','分配角色权限','2015-09-22 11:33:08'),(43,1,'菜单管理','保存更改菜单','2015-09-22 15:12:14'),(44,1,'菜单管理','删除菜单','2015-09-22 17:01:28'),(45,1,'菜单管理','删除菜单','2015-09-22 17:01:45'),(46,1,'菜单管理','保存更改菜单','2015-09-23 11:30:46'),(47,1,'菜单管理','保存更改菜单','2015-09-23 11:31:33'),(48,1,'菜单管理','保存更改菜单','2015-09-23 11:35:35');


/*Table structure for table `sys_acc` */

DROP TABLE IF EXISTS `sys_acc`;

CREATE TABLE `sys_acc` (
  `sys_acc_id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_acc_name` varchar(64) DEFAULT NULL,
  `sys_acc_realname` varchar(64) DEFAULT NULL,
  `sys_acc_state` char(1) DEFAULT '1',
  `sys_acc_password` varchar(64) DEFAULT NULL,
  `sys_acc_desc` varchar(128) DEFAULT NULL,
  `sys_acc_roleid` varchar(100) DEFAULT NULL,
  `sys_acc_orgid` varchar(128) DEFAULT NULL,
  `sys_acc_cdate` date DEFAULT NULL,
  `sys_acc_udate` date DEFAULT NULL,
  `sys_acc_cuser` varchar(64) DEFAULT NULL,
  `sys_acc_uueser` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`sys_acc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

/*Data for the table `sys_acc` */

insert  into `sys_acc`(`sys_acc_id`,`sys_acc_name`,`sys_acc_realname`,`sys_acc_state`,`sys_acc_password`,`sys_acc_desc`,`sys_acc_roleid`,`sys_acc_orgid`,`sys_acc_cdate`,`sys_acc_udate`,`sys_acc_cuser`,`sys_acc_uueser`) values (1,'admin','admin','1','c4ca4238a0b923820dcc509a6f75849b','11','3','1','2015-04-29','2015-04-28',NULL,'admin2'),(4,'xxadmin','xxadmin','2','c4ca4238a0b923820dcc509a6f75849b','23erwer\r\n','4','23','2015-04-29','2015-04-20','admin','admin2'),(5,'lisi','李四','1','c4ca4238a0b923820dcc509a6f75849b','2111','4','2','2015-04-29','2015-04-28','admin','admin2'),(6,'1',NULL,'1',NULL,NULL,NULL,NULL,'2015-04-28',NULL,NULL,NULL),(7,'11',NULL,'1',NULL,NULL,NULL,NULL,'2015-03-24',NULL,NULL,NULL),(8,'111',NULL,'1',NULL,NULL,NULL,NULL,'2015-04-28',NULL,NULL,NULL),(9,'1111',NULL,'1',NULL,NULL,NULL,NULL,'2015-04-28',NULL,NULL,NULL),(11,'22',NULL,'1',NULL,NULL,NULL,NULL,'2015-04-28',NULL,NULL,NULL),(12,'222',NULL,'1',NULL,NULL,NULL,NULL,'2015-04-28',NULL,NULL,NULL),(13,'2222',NULL,'1',NULL,NULL,NULL,NULL,'2015-04-28',NULL,NULL,NULL),(14,'3',NULL,'1',NULL,NULL,NULL,NULL,'2015-04-28',NULL,NULL,NULL),(15,'33',NULL,'1',NULL,NULL,NULL,NULL,'2015-04-28',NULL,NULL,NULL),(16,'333',NULL,'1',NULL,NULL,NULL,NULL,'2015-04-28',NULL,NULL,NULL),(17,'3333',NULL,'1',NULL,NULL,NULL,NULL,'2015-04-28',NULL,NULL,NULL),(18,'4',NULL,'1',NULL,NULL,NULL,NULL,'2015-04-28',NULL,NULL,NULL),(19,'444',NULL,'1',NULL,NULL,NULL,NULL,'2015-04-28',NULL,NULL,NULL),(20,'44',NULL,'1',NULL,NULL,NULL,NULL,'2015-04-28',NULL,NULL,NULL),(21,'4444',NULL,'1',NULL,NULL,NULL,NULL,'2015-04-28',NULL,NULL,NULL),(22,'5',NULL,'1',NULL,NULL,NULL,NULL,'2015-04-28',NULL,NULL,NULL),(23,'55',NULL,'1',NULL,NULL,NULL,NULL,'2015-04-28',NULL,NULL,NULL),(24,'5555',NULL,'1',NULL,NULL,NULL,NULL,'2015-04-28',NULL,NULL,NULL),(25,'55',NULL,'1',NULL,NULL,NULL,NULL,'2015-04-28',NULL,NULL,NULL);

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `sys_menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_menu_name` varchar(64) DEFAULT NULL,
  `sys_menu_pid` int(11) DEFAULT NULL,
  `sys_menu_state` char(1) DEFAULT '1',
  `sys_menu_order` int(11) DEFAULT NULL,
  `sys_menu_desc` varchar(128) DEFAULT NULL,
  `sys_menu_type` varchar(100) DEFAULT NULL,
  `sys_menu_url` varchar(128) DEFAULT NULL,
  `sys_menu_cdate` date DEFAULT NULL,
  `sys_menu_udate` date DEFAULT NULL,
  `sys_menu_cuser` varchar(64) DEFAULT NULL,
  `sys_menu_uueser` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`sys_menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`sys_menu_id`,`sys_menu_name`,`sys_menu_pid`,`sys_menu_state`,`sys_menu_order`,`sys_menu_desc`,`sys_menu_type`,`sys_menu_url`,`sys_menu_cdate`,`sys_menu_udate`,`sys_menu_cuser`,`sys_menu_uueser`) values (1,'主菜单',-1,'1',1,'222','1','','2010-01-01','2015-03-24','1','admin2'),(2,'账号管理',3,'1',NULL,'','3','sysAcc/sysAcclist.do',NULL,'2015-03-24',NULL,'admin2'),(3,'系统资源',1,'1',1,'1','2','','2015-03-23','2015-03-24','admin','admin2'),(4,'菜单管理',3,'1',NULL,'','3','sysMenu/sysmenu.do',NULL,'2015-03-24',NULL,'admin2'),(5,'业务资源',1,'1',2,'2','2','','2015-03-23','2015-03-26','admin','admin2'),(6,'其它资源',1,'2',3,'3','2','','2015-03-23','2015-03-24','admin','admin2'),(7,'角色管理',3,'1',1,'1','3','selectTree.do','2015-03-24','2015-03-25','admin','admin2'),(8,'终端管理',1,'1',2,'','2','','2015-04-21','2015-09-22',NULL,'admin'),(9,'插件管理',1,'1',1,'1','2',NULL,NULL,NULL,NULL,NULL),(10,'插件审核',9,'1',1,'','3','appDetail/appDetailView.do',NULL,'2015-09-22',NULL,'admin'),(11,'插件运营',9,'1',NULL,NULL,'3','application/pApplicationlist.do',NULL,NULL,NULL,NULL),(14,'apk上传管理',9,'1',3,'','3','application/apkversion.do','2015-04-24','2015-09-22','admin','admin'),(15,'网页插件管理',9,'1',4,'','3','application/htmlversion.do','2015-04-24','2015-09-22','admin','admin'),(16,'菜单管理新',3,'1',5,'','3','sysAccount/sysAcclist.do','2015-04-27','2015-09-22','admin','admin'),(17,'系统日志',3,'1',5,'','3','login/loginLog.do','2015-09-22',NULL,'admin',NULL),(18,'操作日志',3,'1',6,'','3','operate/viewOperateLog.do','2015-09-22',NULL,'admin',NULL),(19,'终端品牌',8,'1',1,'','3','PTerminalInfo/pTerminalInfolist.do','2015-09-22','2015-09-22','admin','admin'),(21,'推送用户管理',1,'1',4,'','2','','2015-09-22',NULL,'admin',NULL),(22,'分组管理',21,'1',1,'','3','PlgGroup/pUserPush.do','2015-09-22','2015-09-22','admin','admin'),(23,'用户管理',21,'1',2,'','3','PlgUser/user.do','2015-09-22','2015-09-23','admin','admin'),(24,'推送管理',21,'1',3,'','3','','2015-09-22',NULL,'admin',NULL),(25,'反馈管理',1,'1',5,'','2','','2015-09-22',NULL,'admin',NULL),(26,'反馈信息',25,'1',1,'','3','','2015-09-22',NULL,'admin',NULL);

/*Table structure for table `sys_org` */

DROP TABLE IF EXISTS `sys_org`;

CREATE TABLE `sys_org` (
  `sys_org_id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_org_code` varchar(64) DEFAULT NULL,
  `sys_org_name` varchar(64) DEFAULT NULL,
  `sys_org_pcode` varchar(64) DEFAULT NULL,
  `sys_org_state` char(1) DEFAULT '1',
  `sys_org_order` int(11) DEFAULT NULL,
  `sys_org_desc` varchar(128) DEFAULT NULL,
  `sys_org_type` varchar(100) DEFAULT NULL,
  `sys_org_outercode` varchar(64) DEFAULT NULL,
  `sys_org_cdate` date DEFAULT NULL,
  `sys_org_udate` date DEFAULT NULL,
  `sys_org_cuser` varchar(64) DEFAULT NULL,
  `sys_org_uueser` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`sys_org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `sys_org` */

insert  into `sys_org`(`sys_org_id`,`sys_org_code`,`sys_org_name`,`sys_org_pcode`,`sys_org_state`,`sys_org_order`,`sys_org_desc`,`sys_org_type`,`sys_org_outercode`,`sys_org_cdate`,`sys_org_udate`,`sys_org_cuser`,`sys_org_uueser`) values (1,'00000','1231','00000','1',1,'1231','1','1231','2015-04-07',NULL,'1111',NULL);

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `sys_rm_id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_role_id` int(11) NOT NULL,
  `sys_menu_id` int(11) NOT NULL,
  PRIMARY KEY (`sys_rm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`sys_rm_id`,`sys_role_id`,`sys_menu_id`) values (1,3,1),(17,3,3),(18,3,2),(19,3,4),(20,3,7),(21,3,8),(22,3,9),(23,3,10),(24,3,11),(25,3,14),(26,3,15),(28,3,17),(29,3,18),(30,3,19),(31,3,5),(32,3,21),(33,3,22),(34,3,23),(35,3,24),(36,3,25),(37,3,26);

/*Table structure for table `sysrole` */

DROP TABLE IF EXISTS `sysrole`;

CREATE TABLE `sysrole` (
  `t_sys_role_id` int(32) NOT NULL AUTO_INCREMENT,
  `t_sys_role_name` varchar(32) DEFAULT NULL,
  `t_sys_role_desc` varchar(128) DEFAULT NULL,
  `t_sys_role_type` varchar(2) DEFAULT NULL,
  `t_sys_role_state` char(1) DEFAULT '1',
  `t_sys_role_order` int(4) DEFAULT '1',
  `t_sys_role_cdate` date DEFAULT NULL,
  `t_sys_role_udate` date DEFAULT NULL,
  `t_sys_role_cuser` varchar(32) DEFAULT NULL,
  `t_sys_role_uuser` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`t_sys_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `sysrole` */

insert  into `sysrole`(`t_sys_role_id`,`t_sys_role_name`,`t_sys_role_desc`,`t_sys_role_type`,`t_sys_role_state`,`t_sys_role_order`,`t_sys_role_cdate`,`t_sys_role_udate`,`t_sys_role_cuser`,`t_sys_role_uuser`) values (3,'超级管理员','12','1','1',1,'2015-03-24','2015-03-24','0','0'),(4,'XX管理员','test1','2','1',2,'2015-03-25','2015-04-03','0','admin2'),(5,'ceshi ','ceshi','2','1',2,'2015-04-20',NULL,'admin',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
