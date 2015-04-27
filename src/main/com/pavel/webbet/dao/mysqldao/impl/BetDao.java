package com.pavel.webbet.dao.mysqldao.impl;

import com.pavel.webbet.dao.mysqldao.QueryConstants;
import com.pavel.webbet.dao.mysqldao.connectionpool.ConnectionPool;
import com.pavel.webbet.dao.mysqldao.connectionpool.ConnectionPoolException;
import com.pavel.webbet.entity.bet.BetBean;
import com.pavel.webbet.entity.bet.BetPrediction;
import com.pavel.webbet.entity.bet.BetStatus;
import com.pavel.webbet.entity.match.FootballMatch;
import com.pavel.webbet.entity.match.MatchStatus;

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

    private int numberOfRecords;

    public List<BetBean> viewAllBets(int offset, int noOfRecords){

        Connection connection = null;
        Statement statement = null;

        List<BetBean> list = new ArrayList<>();
        BetBean bet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(QueryConstants.queryForAllBetsWithLimit(offset, noOfRecords));
            while (rs.next()){
                bet = new BetBean();
                bet.setBetId(rs.getInt("betid"));
                bet.setLogin(rs.getString("login"));
                bet.setFootballMatchName(rs.getString("name"));
                bet.setFootballMatchDate(rs.getDate("time_start"));
                bet.setMatchScore(rs.getString("score"));
                bet.setPrediction(BetPrediction.valueOf(rs.getString("bet_prediction").toUpperCase()));
                bet.setMoneyCharge(rs.getBoolean("money_charge"));
                bet.setSum(rs.getDouble("sum"));
                bet.setCurrentCoef(rs.getFloat("current_coef"));
                bet.setWon(rs.getBoolean("is_won"));
                bet.setStatus(BetStatus.valueOf(rs.getString("bet_status").toUpperCase()));
                list.add(bet);
            }
            rs.close();

            rs = statement.executeQuery("select FOUND_ROWS()");
            if (rs.next()){
                numberOfRecords = rs.getInt(1);
            }
        }
        catch (ConnectionPoolException e) {}
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try {
                if (statement != null){statement.close();}
                if (connection != null){connection.close();}
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return list;
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

    public BetBean getBetById(int id) {

        Connection connection = null;
        Statement statement = null;
        BetBean bet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(QueryConstants.queryForGetBetById(id));
            while (rs.next()){
                bet = new BetBean();
                bet.setBetId(rs.getInt("betid"));
                bet.setLogin(rs.getString("login"));
                bet.setFootballMatchName(rs.getString("name"));
                bet.setFootballMatchDate(rs.getDate("time_start"));
                bet.setMatchScore(rs.getString("score"));
                bet.setPrediction(BetPrediction.valueOf(rs.getString("bet_prediction").toUpperCase()));
                bet.setMoneyCharge(rs.getBoolean("money_charge"));
                bet.setSum(rs.getDouble("sum"));
                bet.setCurrentCoef(rs.getFloat("current_coef"));
                bet.setWon(rs.getBoolean("is_won"));
                bet.setStatus(BetStatus.valueOf(rs.getString("bet_status").toUpperCase()));
            }
            rs.close();
        }
        catch (ConnectionPoolException e) {}
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try {
                if (statement != null){statement.close();}
                if (connection != null){connection.close();}
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return bet;
    }

    public int getNumberOfRecords() {return numberOfRecords; }
}
