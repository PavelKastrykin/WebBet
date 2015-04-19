package archive;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginWebBet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        MySqlWebBetConnection sqlWebBetConnection = new MySqlWebBetConnection();
        Connection conn = sqlWebBetConnection.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            statement = conn.createStatement();
            resultSet = statement.executeQuery("select * from users");
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        if ("admin".equals(userName) && "admin".equals(password)){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_page.jsp");

            if (dispatcher != null) {
                dispatcher.forward(request, response);
            }
        }
    }
}