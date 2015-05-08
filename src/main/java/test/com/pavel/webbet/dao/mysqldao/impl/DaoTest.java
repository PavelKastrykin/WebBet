package test.com.pavel.webbet.dao.mysqldao.impl;

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

        String matchName = "Какой-то матч";
        String matchStartDate = "2015-01-01";
        FootballMatch match = new FootballMatch();
        String matchIdByNameQuery = "select football_matchid from football_match where name = 'Какой-то матч'";

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date matchDate = df.parse(matchStartDate);

//        match.setMatchName(new String(matchName.getBytes("UTF-8"),"UTF-8"));
        match.setMatchName(matchName);
        match.setStartTime(matchDate);
        try {
            dao.insert(match);
            resultSet = statement.executeQuery(matchIdByNameQuery);
            resultSet.next();
            int id = resultSet.getInt("football_matchid");
            Assert.assertEquals(id, dao.getBeanById(id).getMatchId(), 0.1);

            match = dao.getBeanById(id);
            match.setMatchScore("0:0");
            dao.updateBean(match);
            Assert.assertEquals("0:0", dao.getBeanById(id).getMatchScore());

            dao.deleteBean(id);
            Assert.assertNull(dao.getBeanById(id));
        }
        finally {
            connection.rollback();
            connection.close();
        }

    }
}
