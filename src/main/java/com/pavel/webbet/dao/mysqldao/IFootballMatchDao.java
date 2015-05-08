package com.pavel.webbet.dao.mysqldao;

import com.pavel.webbet.entity.match.FootballMatch;

public interface IFootballMatchDao extends ICommonDao<FootballMatch> {
    FootballMatch getBeanById(int id) throws MysqlDaoException;
}
