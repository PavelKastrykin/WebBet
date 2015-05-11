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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BetDao extends DaoJdbcResource implements IBetDao {

    public static final Logger logger = Logger.getLogger(BetDao.class);

    private static final BetDao instance = new BetDao();
    public static BetDao getInstance() {
        return instance;
    }
    private int numberOfRecords;

    @Override
    public List<BetBean> getList(int offset, int noOfRecords) throws MysqlDaoException{

        List<BetBean> list = new ArrayList<>();
        BetBean bet = null;
        try {
            prepareConnection();

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
            throw new MysqlDaoException(e.getMessage(), e);
        }

        catch (SQLException e){throw new MysqlDaoException("Error retrieving data from database", e);
        }
        finally {
            releaseJDBCResources();
        }
        return list;
    }

    @Override
    public void insert(BetBean bean) throws MysqlDaoException{

        try {
            prepareConnection();
            statement.executeUpdate(QueryConstant.queryForBetInsert(bean));
        }
        catch (ConnectionPoolException e){
            throw new MysqlDaoException(e.getMessage(), e);
        }

        catch (SQLException e){ throw new MysqlDaoException("Data was not inserted to database", e);}
        finally {
            releaseJDBCResources();
        }
    }

    @Override
    public List<BetBean> getListByName(String login) throws MysqlDaoException {

        List<BetBean> list = new ArrayList<>();
        BetBean bean = null;
        try {
            prepareConnection();
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
        catch (ConnectionPoolException e){ throw new MysqlDaoException(e.getMessage(), e);}
        catch (SQLException e) {throw new MysqlDaoException("Error retrieving data from database", e);}
        finally {
            releaseJDBCResources();
        }
        return list;
    }

    @Override
    public BetBean getBeanById(int id) throws MysqlDaoException {

        BetBean bet = null;
        try {
            prepareConnection();
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
            throw new MysqlDaoException(e.getMessage(), e);
        }
        catch (SQLException e) {
            throw new MysqlDaoException("Error retrieving data from database", e);
        }
        finally {
            releaseJDBCResources();
        }
        return bet;
    }

    @Override
    public void updateBean(BetBean bet) throws MysqlDaoException {
        try {
            prepareConnection();
            int x = statement.executeUpdate(QueryConstant.queryForBetUpdate((BetBean)bet));
        }
        catch (ConnectionPoolException e){throw new MysqlDaoException(e.getMessage(), e);}
        catch (SQLException e){ throw new MysqlDaoException("Data was not inserted to database", e);}
        finally {
            releaseJDBCResources();
        }
    }

    @Override
    public boolean containBetOnMatchId(int id) throws MysqlDaoException {

        try{
            prepareConnection();
            ResultSet rs = statement.executeQuery(QueryConstant.queryForMatchDeletionValidation(id));
            if (rs.next()){
                rs.close();
                return true;
            }
            rs.close();
            return false;
        }
        catch (ConnectionPoolException e){throw new MysqlDaoException(e.getMessage(), e);}
        catch (SQLException e){ throw new MysqlDaoException("Data was not inserted to database", e);}
        finally {
            releaseJDBCResources();
        }
    }

    @Override
    public int getNumberOfRecords() {return numberOfRecords; }

    @Override
    public void deleteBean(int id) throws MysqlDaoException {}
}
