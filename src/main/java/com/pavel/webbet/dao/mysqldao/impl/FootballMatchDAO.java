package com.pavel.webbet.dao.mysqldao.impl;

import com.pavel.webbet.dao.mysqldao.MysqlDaoException;
import com.pavel.webbet.dao.mysqldao.QueryConstants;
import com.pavel.webbet.dao.mysqldao.connectionpool.ConnectionPool;
import com.pavel.webbet.dao.mysqldao.connectionpool.ConnectionPoolException;
import com.pavel.webbet.entity.match.FootballMatch;
import com.pavel.webbet.entity.match.MatchStatus;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FootballMatchDAO {

    private static final FootballMatchDAO instance = new FootballMatchDAO();
    public static FootballMatchDAO getInstance() {
        return instance;
    }

    private int numberOfRecords;

    public List<FootballMatch> viewAllMatches(int offset, int noOfRecords) throws MysqlDaoException{

        Connection connection = null;
        Statement statement = null;

        List<FootballMatch> list = new ArrayList<>();
        FootballMatch match = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(QueryConstants.queryForAllMatchesWithLimit(offset, noOfRecords));
            while (rs.next()){
                match = new FootballMatch();
                match.setMatchId(rs.getInt("football_matchid"));
                match.setMatchName(rs.getString("name"));
                match.setStartTime(rs.getDate("time_start"));
                match.setMatchScore(rs.getString("score"));
                match.setWinCoef(rs.getFloat("coef_win"));
                match.setDrawCoef(rs.getFloat("coef_draw"));
                match.setLooseCoef(rs.getFloat("coef_lost"));
                match.setStatus(MatchStatus.valueOf(rs.getString("status").toUpperCase()));
                list.add(match);
            }
            rs.close();

            rs = statement.executeQuery("select FOUND_ROWS()");
            if (rs.next()){
                numberOfRecords = rs.getInt(1);
            }
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
        return list;
    }

    public FootballMatch getMatchById (int id) throws MysqlDaoException{
        Connection connection = null;
        Statement statement = null;
        FootballMatch match = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(QueryConstants.queryForGetMatchById(id));
            while (rs.next()){
                match = new FootballMatch();
                match.setMatchId(rs.getInt("football_matchid"));
                match.setMatchName(rs.getString("name"));
                match.setStartTime(rs.getDate("time_start"));
                match.setMatchScore(rs.getString("score"));
                match.setWinCoef(rs.getFloat("coef_win"));
                match.setDrawCoef(rs.getFloat("coef_draw"));
                match.setLooseCoef(rs.getFloat("coef_lost"));
                match.setStatus(MatchStatus.valueOf(rs.getString("status").toUpperCase()));
            }
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
        return match;
    }

    public void insert(FootballMatch match) throws MysqlDaoException{
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            int x = statement.executeUpdate(QueryConstants.queryForMatchInsert(match));
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

    public void update(FootballMatch match) throws MysqlDaoException{
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            int x = statement.executeUpdate(QueryConstants.queryForMatchUpdate(match));
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

    public void delete(int id) throws MysqlDaoException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            int x = statement.executeUpdate(QueryConstants.queryForMatchDelete(id));
        }
        catch (ConnectionPoolException e){throw new MysqlDaoException(e.getMessage(), e);}
        catch (SQLException e){ throw new MysqlDaoException("Match was not deleted from database", e);}
        finally {
            try {
                if (statement != null){statement.close();}
                if (connection != null){connection.close();}
            }
            catch (SQLException e) {e.printStackTrace();}
        }
    }


    public int getNumberOfRecords(){
        return numberOfRecords;
    }
}
