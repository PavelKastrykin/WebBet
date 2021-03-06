CREATE DATABASE `webbet` /*!40100 DEFAULT CHARACTER SET utf8 */;
CREATE TABLE `bets` (
  `betid` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `football_matchid` int(11) NOT NULL,
  `bet_prediction` varchar(10) NOT NULL,
  `money_charge` bit(1) NOT NULL DEFAULT b'0',
  `sum` int(11) NOT NULL,
  `current_coef` float NOT NULL,
  `is_won` bit(1) NOT NULL DEFAULT b'0',
  `bet_status` varchar(10) NOT NULL DEFAULT 'open',
  PRIMARY KEY (`betid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
CREATE TABLE `football_match` (
  `football_matchid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `time_start` date NOT NULL,
  `score` varchar(45) NOT NULL DEFAULT '-:-',
  `coef_win` double NOT NULL DEFAULT '1',
  `coef_draw` double NOT NULL DEFAULT '1',
  `coef_lost` double NOT NULL DEFAULT '1',
  `status` varchar(45) NOT NULL DEFAULT 'soon',
  PRIMARY KEY (`football_matchid`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
CREATE TABLE `users` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(30) NOT NULL,
  `password` varchar(45) NOT NULL,
  `user_role` varchar(10) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
