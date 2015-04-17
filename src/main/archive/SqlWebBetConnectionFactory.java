package archive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlWebBetConnectionFactory {
    private static SqlWebBetConnectionFactory instance = new SqlWebBetConnectionFactory();
    String url = "jdbc:mysql://127.0.0.1/webbet";
    String user = "root";
    String password = "root";
    String driverClass = "com.mysql.jdbc.Driver";

    private SqlWebBetConnectionFactory(){
        try {
            Class.forName(driverClass);
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static SqlWebBetConnectionFactory getInstance(){
        return instance;
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

}
