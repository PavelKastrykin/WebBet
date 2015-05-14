package com.pavel.webbet.dao.mysql.impl;

import com.pavel.webbet.constant.TableColumnConstant;
import com.pavel.webbet.dao.IBetDao;
import com.pavel.webbet.dao.mysql.MysqlDaoException;
import com.pavel.webbet.entity.bet.BetPrediction;
import com.pavel.webbet.constant.QueryConstant;
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

public class BetDao  implements IBetDao {

    public static final Logger logger = Logger.getLogger(BetDao.class);
    private static final BetDao instance = new BetDao();
    private int numberOfRecords;

    public static BetDao getInstance() {
        return instance;
    }

    @Override
    public List<BetBean> getList(int offset, int noOfRecords) throws MysqlDaoException{

        Connection connection = null;
        Statement statement = null;
        List<BetBean> list = new ArrayList<>();
        BetBean bet;
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
                bet.setSum(rs.getInt(TableColumnConstant.BETS_COLUMN_SUM));
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
        catch (ConnectionPoolException e) {
            logger.info(e.getMessage(), e);
            throw new MysqlDaoException("", e);
        }

        catch (SQLException e){
            logger.error(e.getMessage(), e);
            throw new MysqlDaoException("Error retrieving data from database");
        }
        finally {
            try {
                if (statement != null){
                    statement.close();
                }
            }
            catch (SQLException e){
                logger.error(e.getMessage(), e);
            }
            try{
                if(connection != null){
                    connection.close();
                }
            }
            catch (SQLException e){
                logger.error(e.getMessage(), e);
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
            statement.executeUpdate(QueryConstant.queryForBetInsert(bean));
        }
        catch (ConnectionPoolException e){
            logger.info(e.getMessage(), e);
            throw new MysqlDaoException(e.getMessage());
        }

        catch (SQLException e){
            logger.error(e.getMessage(), e);
            throw new MysqlDaoException("Bet was not added, please try again.");
        }
        finally {
            try {
                if (statement != null){
                    statement.close();
                }
            }
            catch (SQLException e){
                logger.error(e.getMessage(), e);
            }
            try{
                if(connection != null){
                    connection.close();
                }
            }
            catch (SQLException e){
                logger.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public List<BetBean> getListByName(String login) throws MysqlDaoException {

        Connection connection = null;
        Statement statement = null;
        List<BetBean> list = new ArrayList<>();
        BetBean bean;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(QueryConstant.queryForGetBetsByLogin(login));
            while (rs.next()){
                bean = new BetBean();
                bean.getMatch().setMatchName(rs.getString(TableColumnConstant.FOOTBALL_MATCH_COLUMN_NAME));
                bean.getMatch().setStartTime(rs.getDate(TableColumnConstant.FOOTBALL_MATCH_COLUMN_TIME_START));
                bean.setPrediction(BetPrediction.valueOf(rs.getString(TableColumnConstant.BETS_COLUMN_BET_PREDICTION).toUpperCase()));
                bean.setSum(rs.getInt(TableColumnConstant.BETS_COLUMN_SUM));
                bean.setCurrentCoef(rs.getFloat(TableColumnConstant.BETS_COLUMN_CURRENT_COEF));
                bean.setWon(rs.getBoolean(TableColumnConstant.BETS_COLUMN_IS_WON));
                bean.setStatus(BetStatus.valueOf(rs.getString(TableColumnConstant.BETS_COLUMN_BET_STATUS).toUpperCase()));
                bean.setMoneyCharge(rs.getBoolean(TableColumnConstant.BETS_COLUMN_MONEY_CHARGE));
                list.add(bean);
            }
            rs.close();

        }
        catch (ConnectionPoolException e){
            logger.info(e.getMessage(), e);
            throw new MysqlDaoException(e.getMessage());
        }
        catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new MysqlDaoException("Problem appeared while connecting to database, please try later");
        }
        finally {
            try {
                if (statement != null){
                    statement.close();
                }
            }
            catch (SQLException e){
                logger.error(e.getMessage(), e);
            }
            try{
                if(connection != null){
                    connection.close();
                }
            }
            catch (SQLException e){
                logger.error(e.getMessage(), e);
            }
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
                bet.setSum(rs.getInt(TableColumnConstant.BETS_COLUMN_SUM));
                bet.setCurrentCoef(rs.getFloat(TableColumnConstant.BETS_COLUMN_CURRENT_COEF));
                bet.setWon(rs.getBoolean(TableColumnConstant.BETS_COLUMN_IS_WON));
                bet.setStatus(BetStatus.valueOf(rs.getString(TableColumnConstant.BETS_COLUMN_BET_STATUS).toUpperCase()));
            }
            rs.close();
        }
        catch (ConnectionPoolException e){
            logger.info(e.getMessage(), e);
            throw new MysqlDaoException(e.getMessage());
        }
        catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new MysqlDaoException("Problem appeared while connecting to database, please try later");
        }
        finally {
            try {
                if (statement != null){
                    statement.close();
                }
            }
            catch (SQLException e){
                logger.error(e.getMessage(), e);
            }
            try{
                if(connection != null){
                    connection.close();
                }
            }
            catch (SQLException e){
                logger.error(e.getMessage(), e);
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
            statement.executeUpdate(QueryConstant.queryForBetUpdate(bet));
        }
        catch (ConnectionPoolException e){
            logger.info(e.getMessage(), e);
            throw new MysqlDaoException(e.getMessage());
        }
        catch (SQLException e){
            logger.error(e.getMessage(), e);
            throw new MysqlDaoException("Bet was not updated, please try again.");
        }
        finally {
            try {
                if (statement != null){
                    statement.close();
                }
            }
            catch (SQLException e){
                logger.error(e.getMessage(), e);
            }
            try{
                if(connection != null){
                    connection.close();
                }
            }
            catch (SQLException e){
                logger.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public boolean containBetOnMatchId(int id) throws MysqlDaoException {

        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(QueryConstant.queryForMatchDeletionValidation(id));
            if (rs.next()){
                rs.close();
                return true;
            }
            rs.close();
            return false;
        }
        catch (ConnectionPoolException e){
            logger.info(e.getMessage(), e);
            throw new MysqlDaoException(e.getMessage());
        }
        catch (SQLException e){
            logger.error(e.getMessage(), e);
            throw new MysqlDaoException("");
        }
        finally {
            try {
                if (statement != null){
                    statement.close();
                }
            }
            catch (SQLException e){
                logger.error(e.getMessage(), e);
            }
            try{
                if(connection != null){
                    connection.close();
                }
            }
            catch (SQLException e){
                logger.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public int getNumberOfRecords() {return numberOfRecords; }

    @Override
    public void deleteBean(int id) throws MysqlDaoException {}
}
