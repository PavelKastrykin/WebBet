package com.pavel.webbet.dao.mysql;

public class MysqlDaoException extends Exception {

    public MysqlDaoException(String message) { super(message);}

    public MysqlDaoException(String message, Exception e){
        super(message, e);
    }
}
