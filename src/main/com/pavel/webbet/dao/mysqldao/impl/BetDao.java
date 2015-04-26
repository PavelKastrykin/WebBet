package com.pavel.webbet.dao.mysqldao.impl;

import com.pavel.webbet.dao.mysqldao.QueryConstants;
import com.pavel.webbet.dao.mysqldao.connectionpool.ConnectionPool;
import com.pavel.webbet.dao.mysqldao.connectionpool.ConnectionPoolException;
import com.pavel.webbet.entity.bet.BetBean;
import com.pavel.webbet.entity.bet.BetPrediction;
import com.pavel.webbet.entity.bet.BetStatus;
import com.pavel.webbet.entity.match.FootballMatch;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public List<BetBean> betsByLogin(String login) {
        Connection connection = null;
        Statement statement = null;

        List<BetBean> list = new ArrayList<>();
        BetBean bean = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(QueryConstants.queryForGetBetsByLogin(login));
            while (rs.next()){
                bean = new BetBean();
                bean.setFootballMatchName(rs.getString(1));
                bean.setFootballMatchDate(rs.getDate(2));
                bean.setPrediction(BetPrediction.valueOf(rs.getString(3).toUpperCase()));
                bean.setSum(rs.getDouble(4));
                bean.setCurrentCoef(rs.getFloat(5));
                bean.setWon(rs.getBoolean(6));
                bean.setStatus(BetStatus.valueOf(rs.getString(7).toUpperCase()));
                bean.setMoneyCharge(rs.getBoolean(8));
                list.add(bean);
            }
            rs.close();

        }
        catch (ConnectionPoolException e){}
        catch (SQLException e) {}
        return list;
    }
}
