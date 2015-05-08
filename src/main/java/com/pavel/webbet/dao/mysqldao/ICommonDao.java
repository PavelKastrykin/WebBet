package com.pavel.webbet.dao.mysqldao;

import java.util.List;

public interface ICommonDao<T> {

    List<T> getList(int offset, int noOfRecords) throws MysqlDaoException;
    void insert(T bean) throws MysqlDaoException;
    void updateBean(T bean) throws MysqlDaoException;
    void deleteBean(int id) throws MysqlDaoException;
    int getNumberOfRecords();
}
