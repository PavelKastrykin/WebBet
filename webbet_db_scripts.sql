CREATE DATABASE `webbet` /*!40100 DEFAULT CHARACTER SET latin1 */;


CREATE TABLE `bets` (
  `betid` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `football_matchid` int(11) NOT NULL,
  `bet_prediction` varchar(10) NOT NULL,
  `money_charge` bit(1) NOT NULL DEFAULT b'0',
  `sum` double NOT NULL,
  `current_coef` float NOT NULL,
  `is_won` bit(1) NOT NULL DEFAULT b'0',
  `bet_status` varchar(10) NOT NULL DEFAULT 'open',
  PRIMARY KEY (`betid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;


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
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;

CREATE TABLE `users` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(30) NOT NULL,
  `password` varchar(45) NOT NULL,
  `user_role` varchar(10) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

INSERT INTO `webbet`.`users`
(`userid`,
`login`,
`password`,
`user_role`,
`user_name`)
VALUES
(1,
'admin',
'admin',
'admin',
'Sancho');


