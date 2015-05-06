package com.pavel.webbet.dao.mysqldao.test;

import com.pavel.webbet.dao.mysqldao.connectionpool.ConnectionPool;
import com.pavel.webbet.dao.mysqldao.impl.FootballMatchDAO;
import com.pavel.webbet.entity.match.FootballMatch;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DaoTest {

    @Test
    public void testDao() throws Exception {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        ResultSet resultSet = null;
        FootballMatchDAO dao = FootballMatchDAO.getInstance();

        String matchName = "TeamOne - TeamTwo";
        String matchStartDate = "2015-01-01";
        FootballMatch match = new FootballMatch();
        String matchIdByNameQuery = "select football_matchid from football_match where name = 'TeamOne - TeamTwo'";

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date matchDate = df.parse(matchStartDate);

        match.setMatchName(matchName);
        match.setStartTime(matchDate);
        try {
            dao.insert(match);
            resultSet = statement.executeQuery(matchIdByNameQuery);
            resultSet.next();
            int id = resultSet.getInt("football_matchid");
            Assert.assertEquals(id, dao.getMatchById(id).getMatchId(), 0.1);

            match = dao.getMatchById(id);
            match.setMatchScore("0:0");
            dao.update(match);
            Assert.assertEquals("0:0", dao.getMatchById(id).getMatchScore());

            dao.delete(id);
            Assert.assertNull(dao.getMatchById(id));
        }
        finally {
            connection.rollback();
            statement.close();
            resultSet.close();
            connection.close();
        }

    }
}
