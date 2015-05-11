package com.pavel.webbet.dao.mysql.impl;

import com.pavel.webbet.dao.mysql.connectionpool.ConnectionPool;
import com.pavel.webbet.dao.mysql.connectionpool.ConnectionPoolException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoJdbcResource {
    protected Connection connection;
    protected Statement statement;

    protected void prepareConnection() throws ConnectionPoolException, SQLException {
        connection = ConnectionPool.getInstance().takeConnection();
        statement = connection.createStatement();
    }

    protected void releaseJDBCResources(){
        try {
            if (statement != null){
                statement.close();
            }
        }
        catch (SQLException e){}
        try{
            if(connection != null){
                connection.close();
            }
        }
        catch (SQLException e){}
    }
}
