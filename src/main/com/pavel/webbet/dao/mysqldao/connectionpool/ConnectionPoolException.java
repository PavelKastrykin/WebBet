package com.pavel.webbet.dao.mysqldao.connectionpool;

public class ConnectionPoolException extends Exception{
    public ConnectionPoolException(String message, Exception e){
        super(message, e);
    }
}
