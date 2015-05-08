package com.pavel.webbet.dao.mysqldao;

import com.pavel.webbet.entity.bet.BetBean;

import java.util.List;

public interface IBetDao extends ICommonDao<BetBean> {
    List<BetBean> getListByName(String name) throws MysqlDaoException;
    BetBean getBeanById(int id) throws MysqlDaoException;
}
