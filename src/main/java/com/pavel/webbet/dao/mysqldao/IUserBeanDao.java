package com.pavel.webbet.dao.mysqldao;

import com.pavel.webbet.entity.userbean.UserBean;

import java.util.List;

public interface IUserBeanDao extends ICommonDao<UserBean> {
    List<UserBean> getListByName(String name) throws MysqlDaoException;
    UserBean getBeanByName(String name) throws MysqlDaoException;
    UserBean getBeanByNameAndPassword(String name, String password) throws MysqlDaoException;
}
