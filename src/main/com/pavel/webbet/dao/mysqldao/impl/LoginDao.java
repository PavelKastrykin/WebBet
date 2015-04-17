package com.pavel.webbet.dao.mysqldao.impl;

import com.pavel.webbet.dao.mysqldao.MySqlDao;
import com.pavel.webbet.dao.mysqldao.QueryConstants;
import com.pavel.webbet.dao.mysqldao.connectionpool.ConnectionPool;
import com.pavel.webbet.dao.mysqldao.connectionpool.ConnectionPoolException;
import com.pavel.webbet.entity.userbean.UserBean;
import com.pavel.webbet.entity.userbean.UserRole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class LoginDao implements MySqlDao{

    private static LoginDao instance = new LoginDao();
    public static LoginDao getInstance(){
        return instance;
    }

    public String createQuery (String userNameParameter, String userPasswordParameter) {
        String query = MessageFormat.format(QueryConstants.MYSQL_LOGIN_QUERY, userNameParameter, userPasswordParameter);
        return query;
    }

    @Override
    public List<UserBean> getQueryResult(String query) {
        try{
            UserBean userBean = new UserBean();
            List<UserBean> userList = new ArrayList<>();
            Connection connection = ConnectionPool.getInstance().takeConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            boolean hasResult = resultSet.next();

            if (!hasResult){
                userBean = null;
                userList.add(userBean);
                return userList;
            }
            else{
                userBean.setUserID(resultSet.getInt(1));
                userBean.setLogin(resultSet.getString(2));
                userBean.setPassword(resultSet.getString(3));
                userBean.setUserRole(UserRole.valueOf(resultSet.getString(4).toUpperCase()));
                userBean.setName(resultSet.getString(5));
                userList.add(userBean);
                return userList;
            }
        }
        catch (ConnectionPoolException e){}
        catch (SQLException e){}
        return null;
    }
}
