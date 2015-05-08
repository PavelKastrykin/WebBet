package com.pavel.webbet.dao.mysqldao.impl;

import com.pavel.webbet.dao.mysqldao.IBetDao;
import com.pavel.webbet.dao.mysqldao.ICommonDao;
import com.pavel.webbet.dao.mysqldao.MysqlDaoException;
import com.pavel.webbet.entity.bet.BetPrediction;
import com.pavel.webbet.dao.mysqldao.QueryConstant;
import com.pavel.webbet.dao.mysqldao.connectionpool.ConnectionPool;
import com.pavel.webbet.dao.mysqldao.connectionpool.ConnectionPoolException;
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
            int x = statement.executeUpdate(QueryConstant.queryForBetInsert((BetBean) bean));
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
