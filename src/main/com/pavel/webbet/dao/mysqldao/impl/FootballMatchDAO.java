package com.pavel.webbet.dao.mysqldao.impl;

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

    Connection connection;
    Statement statement;
    private int numberOfRecords;

    public FootballMatchDAO(){}

    public List<FootballMatch> viewAllMatches(int offset, int noOfRecords){

        List<FootballMatch> list = new ArrayList<FootballMatch>();
        FootballMatch match = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(QueryConstants.queryForAllMatchesWithLimit(offset, noOfRecords));
            while (rs.next()){
                match = new FootballMatch();
                match.setMatchId(rs.getInt("football_matchid"));
                match.setMatchName(rs.getString("name"));
                match.setStartTime(rs.getTimestamp("time_start"));
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
                this.numberOfRecords = rs.getInt(1);
            }
        }
        catch (ConnectionPoolException e) {}
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try {
                if (statement != null){
                    statement.close();
                }
                if (connection != null){
                    connection.close();
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return list;
    }
    public int getNumberOfRecords(){
        return numberOfRecords;
    }
}
