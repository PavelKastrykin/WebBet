package archive;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FootballMatchDAO {
    Connection connection;
    Statement statement;
    private int noOfRecords;

    public FootballMatchDAO(){}

    private static Connection getConnection() throws SQLException, ClassNotFoundException{
        Connection con = SqlWebBetConnectionFactory.getInstance().getConnection();
        return con;
    }

    public List<FootballMatch> viewAllMatches(int offset, int noOfRecords){
        String query = "select SQL_CALC_FOUND_ROWS * from football_match " + offset + ", "
                + noOfRecords;
        List<FootballMatch> list = new ArrayList<FootballMatch>();
        FootballMatch match = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                match = new FootballMatch();
                match.setMatchId(rs.getInt("football_matchid"));
                match.setMatchName(rs.getString("name"));
                match.setStartTime(rs.getTime("time_start"));
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
                this.noOfRecords = rs.getInt(1);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
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
    public int getNoOfRecords(){
        return noOfRecords;
    }
}
