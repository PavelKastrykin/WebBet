package com.pavel.webbet.dao.mysql.impl;

import com.pavel.webbet.constant.TableColumnConstant;
import com.pavel.webbet.dao.IFootballMatchDao;
import com.pavel.webbet.dao.mysql.MysqlDaoException;
import com.pavel.webbet.constant.QueryConstant;
import com.pavel.webbet.dao.mysql.connectionpool.ConnectionPool;
import com.pavel.webbet.dao.mysql.connectionpool.ConnectionPoolException;
import com.pavel.webbet.entity.match.FootballMatch;
import com.pavel.webbet.entity.match.MatchStatus;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FootballMatchDAO implements IFootballMatchDao {

    public static final Logger logger = Logger.getLogger(FootballMatchDAO.class);

    private static final FootballMatchDAO instance = new FootballMatchDAO();
    public static FootballMatchDAO getInstance() {
        return instance;
    }
    private int numberOfRecords;

    @Override
    public List<FootballMatch> getList(int offset, int noOfRecords) throws MysqlDaoException{

        Connection connection = null;
        Statement statement = null;
        List<FootballMatch> list = new ArrayList<>();
        FootballMatch match;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(QueryConstant.queryForAllMatchesWithLimit(offset, noOfRecords));
            while (rs.next()){
                match = new FootballMatch();
                match.setMatchId(rs.getInt(TableColumnConstant.FOOTBALL_MATCH_COLUMN_FOOTBALL_MATCHID));
                match.setMatchName(rs.getString(TableColumnConstant.FOOTBALL_MATCH_COLUMN_NAME));
                match.setStartTime(rs.getDate(TableColumnConstant.FOOTBALL_MATCH_COLUMN_TIME_START));
                match.setMatchScore(rs.getString(TableColumnConstant.FOOTBALL_MATCH_COLUMN_SCORE));
                match.setWinCoef(rs.getFloat(TableColumnConstant.FOOTBALL_MATCH_COLUMN_COEF_WIN));
                match.setDrawCoef(rs.getFloat(TableColumnConstant.FOOTBALL_MATCH_COLUMN_COEF_DRAW));
                match.setLooseCoef(rs.getFloat(TableColumnConstant.FOOTBALL_MATCH_COLUMN_COEF_LOST));
                match.setStatus(MatchStatus.valueOf(rs.getString(TableColumnConstant.FOOTBALL_MATCH_COLUMN_STATUS).toUpperCase()));
                list.add(match);
            }
            rs.close();

            rs = statement.executeQuery("select FOUND_ROWS()");
            if (rs.next()){
                numberOfRecords = rs.getInt(1);
            }
        }
        catch (ConnectionPoolException e){
            logger.info(e.getMessage(), e);
            throw new MysqlDaoException(e.getMessage());
        }
        catch (SQLException e) {
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
    public FootballMatch getBeanById(int id) throws MysqlDaoException{

        Connection connection = null;
        Statement statement = null;
        FootballMatch match = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(QueryConstant.queryForGetMatchById(id));
            while (rs.next()){
                match = new FootballMatch();
                match.setMatchId(rs.getInt(TableColumnConstant.FOOTBALL_MATCH_COLUMN_FOOTBALL_MATCHID));
                match.setMatchName(rs.getString(TableColumnConstant.FOOTBALL_MATCH_COLUMN_NAME));
                match.setStartTime(rs.getDate(TableColumnConstant.FOOTBALL_MATCH_COLUMN_TIME_START));
                match.setMatchScore(rs.getString(TableColumnConstant.FOOTBALL_MATCH_COLUMN_SCORE));
                match.setWinCoef(rs.getFloat(TableColumnConstant.FOOTBALL_MATCH_COLUMN_COEF_WIN));
                match.setDrawCoef(rs.getFloat(TableColumnConstant.FOOTBALL_MATCH_COLUMN_COEF_DRAW));
                match.setLooseCoef(rs.getFloat(TableColumnConstant.FOOTBALL_MATCH_COLUMN_COEF_LOST));
                match.setStatus(MatchStatus.valueOf(rs.getString(TableColumnConstant.FOOTBALL_MATCH_COLUMN_STATUS).toUpperCase()));
            }
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
        return match;
    }

    @Override
    public void insert(FootballMatch match) throws MysqlDaoException{

        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            statement.executeUpdate(QueryConstant.queryForMatchInsert(match));
        }
        catch (ConnectionPoolException e){
            logger.info(e.getMessage(), e);
            throw new MysqlDaoException(e.getMessage());
        }
        catch (SQLException e){
            logger.error(e.getMessage(), e);
            throw new MysqlDaoException("Match was not added, please try again.");
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
    public void updateBean(FootballMatch match) throws MysqlDaoException{

        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            statement.executeUpdate(QueryConstant.queryForMatchUpdate(match));
        }
        catch (ConnectionPoolException e){
            logger.info(e.getMessage(), e);
            throw new MysqlDaoException(e.getMessage());
        }
        catch (SQLException e){
            logger.error(e.getMessage(), e);
            throw new MysqlDaoException("Match was not updated, please try again.");
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

    public void deleteBean(int id) throws MysqlDaoException {

        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            statement.executeUpdate(QueryConstant.queryForMatchDelete(id));
        }
        catch (ConnectionPoolException e){
            logger.info(e.getMessage(), e);
            throw new MysqlDaoException(e.getMessage());
        }
        catch (SQLException e){
            logger.info(e.getMessage(), e);
            throw new MysqlDaoException("Match was not deleted from database");
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
    public int getNumberOfRecords(){
        return numberOfRecords;
    }
}
