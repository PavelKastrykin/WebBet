package com.pavel.webbet.dao.mysqldao.impl;

import com.pavel.webbet.dao.mysqldao.QueryConstants;
import com.pavel.webbet.dao.mysqldao.connectionpool.ConnectionPool;
import com.pavel.webbet.dao.mysqldao.connectionpool.ConnectionPoolException;
import com.pavel.webbet.entity.bet.BetBean;
import com.pavel.webbet.entity.match.FootballMatch;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BetDao {

    private static final BetDao instance = new BetDao();
    public static BetDao getInstance() {
        return instance;
    }

    public void insert(BetBean bean) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            int x = statement.executeUpdate(QueryConstants.queryForBetInsert(bean));
        }
        catch (ConnectionPoolException e){}
        catch (SQLException e){}
        finally {
            try {
                if (statement != null){statement.close();}
                if (connection != null){connection.close();}
            }
            catch (SQLException e) {
            }
        }
    }
}
