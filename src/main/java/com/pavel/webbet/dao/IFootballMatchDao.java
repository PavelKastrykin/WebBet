package com.pavel.webbet.dao;

import com.pavel.webbet.dao.mysql.MysqlDaoException;
import com.pavel.webbet.entity.match.FootballMatch;

public interface IFootballMatchDao extends ICommonDao<FootballMatch> {

    FootballMatch getBeanById(int id) throws MysqlDaoException;
}
