package com.pavel.webbet.dao.mysqldao.impl;

import com.pavel.webbet.dao.mysqldao.QueryConstants;
import com.pavel.webbet.dao.mysqldao.connectionpool.ConnectionPool;
import com.pavel.webbet.dao.mysqldao.connectionpool.ConnectionPoolException;
import com.pavel.webbet.entity.userbean.UserBean;
import com.pavel.webbet.entity.userbean.UserRole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserBeanDao {

    private static final UserBeanDao instance = new UserBeanDao();
    public static UserBeanDao getInstance() {
        return instance;
    }
    private int numberOfRecords;

    public UserBean getBeanByLogin (String login) {
        UserBean userBean = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            userBean = new UserBean();
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(QueryConstants.queryForLogin(login));
            boolean hasResult = resultSet.next();

            if (!hasResult) {
                return null;
            } else {
                userBean.setUserID(resultSet.getInt(1));
                userBean.setLogin(resultSet.getString(2));
                userBean.setPassword(resultSet.getString(3));
                userBean.setUserRole(UserRole.valueOf(resultSet.getString(4).toUpperCase()));
                userBean.setName(resultSet.getString(5));
                return userBean;
            }
        }
        catch (ConnectionPoolException e){}
        catch (SQLException e){}
        finally {
            try {
                if (resultSet != null){resultSet.close();}
                if (statement != null){statement.close();}
                if (connection != null){connection.close();}
            }
            catch (SQLException e) {
            }
        }
        return null;
    }

    public UserBean getBeanByLoginAndPassword (String login, String password) {

        UserBean userBean = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            userBean = new UserBean();
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(QueryConstants.queryForLoginAndPassword(login, password));
            boolean hasResult = resultSet.next();

            if (!hasResult) {
                return null;
            } else {
                userBean.setUserID(resultSet.getInt(1));
                userBean.setLogin(resultSet.getString(2));
                userBean.setPassword(resultSet.getString(3));
                userBean.setUserRole(UserRole.valueOf(resultSet.getString(4).toUpperCase()));
                userBean.setName(resultSet.getString(5));
                return userBean;
            }
        }
        catch (ConnectionPoolException e){}
        catch (SQLException e){}
        finally {
            try {
                if (resultSet != null){resultSet.close();}
                if (statement != null){statement.close();}
                if (connection != null){connection.close();}
            }
            catch (SQLException e) {
            }
        }
        return null;
    }

    public List<UserBean> getListByUserType (String type) {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<UserBean> userList = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(QueryConstants.queryForUserType(type));
            while (resultSet.next()){
                UserBean userBean = new UserBean();
                userBean.setUserID(resultSet.getInt(1));
                userBean.setLogin(resultSet.getString(2));
                userBean.setPassword(resultSet.getString(3));
                userBean.setUserRole(UserRole.valueOf(resultSet.getString(4).toUpperCase()));
                userBean.setName(resultSet.getString(5));
                userList.add(userBean);
            }
            return userList;
        }
        catch (ConnectionPoolException e) {}
        catch (SQLException e){}
        finally {
            try {
                if (resultSet != null){resultSet.close();}
                if (statement != null){statement.close();}
                if (connection != null){connection.close();}
            }
            catch (SQLException e) {
            }
        }
        return null;
    }

    public List<UserBean> getAllUsersList(int offset, int noOfRecords) {
        Connection connection = null;
        Statement statement = null;

        List<UserBean> userList = new ArrayList<>();
        UserBean user = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(QueryConstants.queryForAllUsersWithLimit(offset, noOfRecords));
            while (rs.next()){
                user = new UserBean();
                user.setUserID(rs.getInt(1));
                user.setLogin(rs.getString(2));
                user.setUserRole(UserRole.valueOf(rs.getString(3).toUpperCase()));
                user.setName(rs.getString(4));
                userList.add(user);
            }
            rs.close();
            rs = statement.executeQuery("select FOUND_ROWS()");
            if (rs.next()){
                numberOfRecords = rs.getInt(1);
            }
        }
        catch (ConnectionPoolException e) {}
        catch (SQLException e){}
        finally {
            try {
                if (statement != null){statement.close();}
                if (connection != null){connection.close();}
            }
            catch (SQLException e) {
            }
        }
        return userList;
    }

    public void insert(UserBean bean) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            int x = statement.executeUpdate(QueryConstants.queryForUserInsert(bean));
        }
        catch (ConnectionPoolException e){}
        catch (SQLException e){}
        finally {
            try {
                if (statement != null){statement.close();}
                if (connection != null){connection.close();}
            }
            catch (SQLException e) {
            }
        }
    }

    public void delete(UserBean bean) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            int x = statement.executeUpdate(QueryConstants.queryForUserDelete(bean.getUserID()));
        }
        catch (ConnectionPoolException e){}
        catch (SQLException e){}
        finally {
            try {
                if (statement != null){statement.close();}
                if (connection != null){connection.close();}
            }
            catch (SQLException e) {
            }
        }
    }

    public void update(UserBean bean) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            int x = statement.executeUpdate(QueryConstants.queryForUserUpdate(bean));
        }
        catch (ConnectionPoolException e){}
        catch (SQLException e){}
        finally {
            try {
                if (statement != null){statement.close();}
                if (connection != null){connection.close();}
            }
            catch (SQLException e) {
            }
        }
    }

    public int getNumberOfRecords() {return numberOfRecords; }
}
