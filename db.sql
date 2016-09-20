-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.24 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2016-09-18 14:15:01
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for eschool
CREATE DATABASE IF NOT EXISTS `eschool` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `eschool`;


-- Dumping structure for table eschool.students
CREATE TABLE IF NOT EXISTS `students` (
  `id` bigint(20) unsigned NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `birth_day` date DEFAULT NULL,
  `sex` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table eschool.students: ~6 rows (approximately)
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` (`id`, `first_name`, `last_name`, `birth_day`, `sex`) VALUES
	(1, 'Ученик', 'Послушный', '2010-09-17', 'BOY'),
	(3, 'Ученица', 'Умная', '2016-09-01', 'GIRL'),
	(112, 'Иванов', 'Петр', '2010-01-01', 'BOY'),
	(118, 'Маша', 'Петрова', '2010-06-17', 'GIRL'),
	(119, 'Саша', '', '2009-05-09', 'BOY'),
	(120, 'Миша', '', NULL, 'BOY');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;


-- Dumping structure for table eschool.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) unsigned NOT NULL,
  `name` varchar(30) NOT NULL,
  `password` varchar(50) NOT NULL,
  `creation_date` datetime NOT NULL,
  `email` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table eschool.users: ~2 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `name`, `password`, `creation_date`, `email`) VALUES
	(1, 'admin', '1a1dc91c907325c69271ddf0c944bc72', '2016-09-17 08:26:37', 'user1@eschool.my'),
	(2, 'user1', '1a1dc91c907325c69271ddf0c944bc72', '2016-09-17 10:00:43', 'user2@eschool.my');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
