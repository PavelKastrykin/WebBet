import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlWebBetConnection {
    private Connection conn = null;
    public Connection getConnection(){
        return conn;
    }

    public MySqlWebBetConnection(){
        conn = establishConnection();
    }

    public Connection establishConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/webbet", "root", "root");
        }
        catch (InstantiationException e){
            e.printStackTrace();
        }
        catch (IllegalAccessException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return conn;
    }


}
