package com.pavel.webbet.dao.mysql.impl;

import com.pavel.webbet.constant.TableColumnConstant;
import com.pavel.webbet.dao.IBetDao;
import com.pavel.webbet.dao.mysql.MysqlDaoException;
import com.pavel.webbet.entity.bet.BetPrediction;
import com.pavel.webbet.dao.mysql.QueryConstant;
import com.pavel.webbet.dao.mysql.connectionpool.ConnectionPool;
import com.pavel.webbet.dao.mysql.connectionpool.ConnectionPoolException;
import com.pavel.webbet.entity.bet.BetBean;
import com.pavel.webbet.entity.bet.BetStatus;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BetDao implements IBetDao {

    public static final Logger logger = Logger.getLogger(BetDao.class);

    private static final BetDao instance = new BetDao();
    public static BetDao getInstance() {
        return instance;
    }
    private int numberOfRecords;

    @Override
    public List<BetBean> getList(int offset, int noOfRecords) throws MysqlDaoException{

        Connection connection = null;
        Statement statement = null;

        List<BetBean> list = new ArrayList<>();
        BetBean bet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(QueryConstant.queryForAllBetsWithLimit(offset, noOfRecords));
            while (rs.next()){
                bet = new BetBean();
                bet.setBetId(rs.getInt(TableColumnConstant.BETS_COLUMN_BET_ID));
                bet.getUser().setLogin(rs.getString(TableColumnConstant.BETS_COLUMN_LOGIN));
                bet.getMatch().setMatchName(rs.getString(TableColumnConstant.FOOTBALL_MATCH_COLUMN_NAME));
                bet.getMatch().setStartTime(rs.getDate(TableColumnConstant.FOOTBALL_MATCH_COLUMN_TIME_START));
                bet.getMatch().setMatchScore(rs.getString(TableColumnConstant.FOOTBALL_MATCH_COLUMN_SCORE));
                bet.setPrediction(BetPrediction.valueOf(rs.getString(TableColumnConstant.BETS_COLUMN_BET_PREDICTION).toUpperCase()));
                bet.setMoneyCharge(rs.getBoolean(TableColumnConstant.BETS_COLUMN_MONEY_CHARGE));
                bet.setSum(rs.getDouble(TableColumnConstant.BETS_COLUMN_SUM));
                bet.setCurrentCoef(rs.getFloat(TableColumnConstant.BETS_COLUMN_CURRENT_COEF));
                bet.setWon(rs.getBoolean(TableColumnConstant.BETS_COLUMN_IS_WON));
                bet.setStatus(BetStatus.valueOf(rs.getString(TableColumnConstant.BETS_COLUMN_BET_STATUS).toUpperCase()));
                list.add(bet);
            }
            rs.close();

            rs = statement.executeQuery("select FOUND_ROWS()");
            if (rs.next()){
                numberOfRecords = rs.getInt(1);
            }
        }
        catch (ConnectionPoolException e) {throw new MysqlDaoException(e.getMessage(), e);}
        catch (SQLException e){throw new MysqlDaoException("Error retrieving data from database", e);
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

    @Override
    public void insert(BetBean bean) throws MysqlDaoException{
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            int x = statement.executeUpdate(QueryConstant.queryForBetInsert(bean));
        }
        catch (ConnectionPoolException e){throw new MysqlDaoException(e.getMessage(), e);}
        catch (SQLException e){ throw new MysqlDaoException("Data was not inserted to database", e);}
        finally {
            try {
                if (statement != null){statement.close();}
                if (connection != null){connection.close();}
            }
            catch (SQLException e) {
            }
        }
    }

    @Override
    public List<BetBean> getListByName(String login) throws MysqlDaoException {
        Connection connection = null;
        Statement statement = null;

        List<BetBean> list = new ArrayList<>();
        BetBean bean = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(QueryConstant.queryForGetBetsByLogin(login));
            while (rs.next()){
                bean = new BetBean();
                bean.getMatch().setMatchName(rs.getString(1));
                bean.getMatch().setStartTime(rs.getDate(2));
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
        catch (ConnectionPoolException e){ throw new MysqlDaoException(e.getMessage(), e);}
        catch (SQLException e) {throw new MysqlDaoException("Error retrieving data from database", e);}
        finally {
            try {
                if (statement != null){statement.close();}
                if (connection != null){connection.close();}
            }
            catch (SQLException e) {e.printStackTrace();}
        }
        return list;
    }

    @Override
    public BetBean getBeanById(int id) throws MysqlDaoException {

        Connection connection = null;
        Statement statement = null;
        BetBean bet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(QueryConstant.queryForGetBetById(id));
            while (rs.next()){
                bet = new BetBean();
                bet.setBetId(rs.getInt(TableColumnConstant.BETS_COLUMN_BET_ID));
                bet.getUser().setLogin(rs.getString(TableColumnConstant.BETS_COLUMN_LOGIN));
                bet.getMatch().setMatchName(rs.getString(TableColumnConstant.FOOTBALL_MATCH_COLUMN_NAME));
                bet.getMatch().setStartTime(rs.getDate(TableColumnConstant.FOOTBALL_MATCH_COLUMN_TIME_START));
                bet.getMatch().setMatchScore(rs.getString(TableColumnConstant.FOOTBALL_MATCH_COLUMN_SCORE));
                bet.setPrediction(BetPrediction.valueOf(rs.getString(TableColumnConstant.BETS_COLUMN_BET_PREDICTION).toUpperCase()));
                bet.setMoneyCharge(rs.getBoolean(TableColumnConstant.BETS_COLUMN_MONEY_CHARGE));
                bet.setSum(rs.getDouble(TableColumnConstant.BETS_COLUMN_SUM));
                bet.setCurrentCoef(rs.getFloat(TableColumnConstant.BETS_COLUMN_CURRENT_COEF));
                bet.setWon(rs.getBoolean(TableColumnConstant.BETS_COLUMN_IS_WON));
                bet.setStatus(BetStatus.valueOf(rs.getString(TableColumnConstant.BETS_COLUMN_BET_STATUS).toUpperCase()));
            }
            rs.close();
        }
        catch (ConnectionPoolException e){ throw new MysqlDaoException(e.getMessage(), e);}
        catch (SQLException e) {throw new MysqlDaoException("Error retrieving data from database", e);}
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

    @Override
    public void updateBean(BetBean bet) throws MysqlDaoException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            int x = statement.executeUpdate(QueryConstant.queryForBetUpdate((BetBean)bet));
        }
        catch (ConnectionPoolException e){throw new MysqlDaoException(e.getMessage(), e);}
        catch (SQLException e){ throw new MysqlDaoException("Data was not inserted to database", e);}
        finally {
            try {
                if (statement != null){statement.close();}
                if (connection != null){connection.close();}
            }
            catch (SQLException e) {e.printStackTrace();}
        }
    }

    @Override
    public int getNumberOfRecords() {return numberOfRecords; }

    @Override
    public void deleteBean(int id) throws MysqlDaoException {}
}
