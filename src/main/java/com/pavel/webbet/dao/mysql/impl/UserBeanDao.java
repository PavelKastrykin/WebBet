package com.pavel.webbet.dao.mysql.impl;

import com.pavel.webbet.constant.TableColumnConstant;
import com.pavel.webbet.dao.IUserBeanDao;
import com.pavel.webbet.dao.mysql.MysqlDaoException;
import com.pavel.webbet.constant.QueryConstant;
import com.pavel.webbet.dao.mysql.connectionpool.ConnectionPool;
import com.pavel.webbet.dao.mysql.connectionpool.ConnectionPoolException;
import com.pavel.webbet.entity.userbean.UserBean;
import com.pavel.webbet.entity.userbean.UserRole;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserBeanDao implements IUserBeanDao {

    public static final Logger logger = Logger.getLogger(UserBeanDao.class);

    private static final UserBeanDao instance = new UserBeanDao();
    public static UserBeanDao getInstance() {
        return instance;
    }
    private int numberOfRecords;

    @Override
    public UserBean getBeanByName(String login) throws MysqlDaoException{

        Connection connection = null;
        Statement statement = null;
        UserBean userBean;
        ResultSet resultSet;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            userBean = new UserBean();
            resultSet = statement.executeQuery(QueryConstant.queryForLogin(login));
            boolean hasResult = resultSet.next();

            if (!hasResult) {
                return null;
            } else {
                userBean.setUserID(resultSet.getInt(TableColumnConstant.USERS_COLUMN_USERID));
                userBean.setLogin(resultSet.getString(TableColumnConstant.USERS_COLUMN_LOGIN));
                userBean.setPassword(resultSet.getString(TableColumnConstant.USERS_COLUMN_PASSWORD));
                userBean.setUserRole(UserRole.valueOf(resultSet.getString(TableColumnConstant.USERS_COLUMN_USER_ROLE).toUpperCase()));
                userBean.setName(resultSet.getString(TableColumnConstant.USERS_COLUMN_USER_NAME));
                return userBean;
            }
        }
        catch (ConnectionPoolException e){
            logger.info(e.getMessage(), e);
            throw new MysqlDaoException(e.getMessage());
        }
        catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new MysqlDaoException("Problem appeared while connecting to database, please try later");
        }
        finally {
            try {
                if (statement != null){
                    statement.close();
                }
            }
            catch (SQLException e){
                logger.error(e.getMessage(), e);
            }
            try{
                if(connection != null){
                    connection.close();
                }
            }
            catch (SQLException e){
                logger.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public UserBean getBeanByNameAndPassword(String login, String password) throws MysqlDaoException {

        Connection connection = null;
        Statement statement = null;
        UserBean userBean;
        ResultSet resultSet;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            userBean = new UserBean();
            resultSet = statement.executeQuery(QueryConstant.queryForLoginAndPassword(login, password));
            boolean hasResult = resultSet.next();

            if (!hasResult) {
                return null;
            } else {
                userBean.setUserID(resultSet.getInt(TableColumnConstant.USERS_COLUMN_USERID));
                userBean.setLogin(resultSet.getString(TableColumnConstant.USERS_COLUMN_LOGIN));
                userBean.setPassword(resultSet.getString(TableColumnConstant.USERS_COLUMN_PASSWORD));
                userBean.setUserRole(UserRole.valueOf(resultSet.getString(TableColumnConstant.USERS_COLUMN_USER_ROLE).toUpperCase()));
                userBean.setName(resultSet.getString(TableColumnConstant.USERS_COLUMN_USER_NAME));
                return userBean;
            }
        }
        catch (ConnectionPoolException e){
            logger.info(e.getMessage(), e);
            throw new MysqlDaoException(e.getMessage());
        }
        catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new MysqlDaoException("Problem appeared while connecting to database, please try later");
        }
        finally {
            try {
                if (statement != null){
                    statement.close();
                }
            }
            catch (SQLException e){
                logger.error(e.getMessage(), e);
            }
            try{
                if(connection != null){
                    connection.close();
                }
            }
            catch (SQLException e){
                logger.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public List<UserBean> getListByName(String type) throws MysqlDaoException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;
        List<UserBean> userList = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(QueryConstant.queryForUserType(type));
            while (resultSet.next()){
                UserBean userBean = new UserBean();
                userBean.setUserID(resultSet.getInt(TableColumnConstant.USERS_COLUMN_USERID));
                userBean.setLogin(resultSet.getString(TableColumnConstant.USERS_COLUMN_LOGIN));
                userBean.setPassword(resultSet.getString(TableColumnConstant.USERS_COLUMN_PASSWORD));
                userBean.setUserRole(UserRole.valueOf(resultSet.getString(TableColumnConstant.USERS_COLUMN_USER_ROLE).toUpperCase()));
                userBean.setName(resultSet.getString(TableColumnConstant.USERS_COLUMN_USER_NAME));
                userList.add(userBean);
            }
            return userList;
        }
        catch (ConnectionPoolException e){
            logger.info(e.getMessage(), e);
            throw new MysqlDaoException(e.getMessage());
        }
        catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new MysqlDaoException("Error retrieving data from database");
        }
        finally {
            try {
                if (statement != null){
                    statement.close();
                }
            }
            catch (SQLException e){
                logger.error(e.getMessage(), e);
            }
            try{
                if(connection != null){
                    connection.close();
                }
            }
            catch (SQLException e){
                logger.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public List<UserBean> getList(int offset, int noOfRecords) throws MysqlDaoException{

        Connection connection = null;
        Statement statement = null;
        List<UserBean> userList = new ArrayList<>();
        UserBean user;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QueryConstant.queryForAllUsersWithLimit(offset, noOfRecords));
            while (resultSet.next()){
                user = new UserBean();
                user.setUserID(resultSet.getInt(TableColumnConstant.USERS_COLUMN_USERID));
                user.setLogin(resultSet.getString(TableColumnConstant.USERS_COLUMN_LOGIN));
                user.setUserRole(UserRole.valueOf(resultSet.getString(TableColumnConstant.USERS_COLUMN_USER_ROLE).toUpperCase()));
                user.setName(resultSet.getString(TableColumnConstant.USERS_COLUMN_USER_NAME));
                userList.add(user);
            }
            resultSet.close();
            resultSet = statement.executeQuery("select FOUND_ROWS()");
            if (resultSet.next()){
                numberOfRecords = resultSet.getInt(1);
            }
        }
        catch (ConnectionPoolException e){
            logger.info(e.getMessage(), e);
            throw new MysqlDaoException(e.getMessage());
        }
        catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new MysqlDaoException("Error retrieving data from database");
        }
        finally {
            try {
                if (statement != null){
                    statement.close();
                }
            }
            catch (SQLException e){
                logger.error(e.getMessage(), e);
            }
            try{
                if(connection != null){
                    connection.close();
                }
            }
            catch (SQLException e){
                logger.error(e.getMessage(), e);
            }
        }
        return userList;
    }

    @Override
    public void insert(UserBean bean) throws MysqlDaoException {

        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            statement.executeUpdate(QueryConstant.queryForUserInsert(bean));
        }
        catch (ConnectionPoolException e){
            logger.info(e.getMessage(), e);
            throw new MysqlDaoException(e.getMessage());
        }
        catch (SQLException e){
            logger.error(e.getMessage(), e);
            throw new MysqlDaoException("Registering was not performed, please try again.");
        }
        finally {
            try {
                if (statement != null){
                    statement.close();
                }
            }
            catch (SQLException e){
                logger.error(e.getMessage(), e);
            }
            try{
                if(connection != null){
                    connection.close();
                }
            }
            catch (SQLException e){
                logger.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public void deleteBean(int id) throws MysqlDaoException {

        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            statement.executeUpdate(QueryConstant.queryForUserDelete(id));
        }
        catch (ConnectionPoolException e){
            logger.info(e.getMessage(), e);
            throw new MysqlDaoException(e.getMessage());
        }
        catch (SQLException e){
            logger.info(e.getMessage(), e);
            throw new MysqlDaoException("User was not deleted from database");
        }
        finally {
            try {
                if (statement != null){
                    statement.close();
                }
            }
            catch (SQLException e){
                logger.error(e.getMessage(), e);
            }
            try{
                if(connection != null){
                    connection.close();
                }
            }
            catch (SQLException e){
                logger.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public void updateBean(UserBean bean) throws MysqlDaoException {

        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            statement.executeUpdate(QueryConstant.queryForUserUpdate(bean));
        }
        catch (ConnectionPoolException e){
            logger.info(e.getMessage(), e);
            throw new MysqlDaoException(e.getMessage());
        }
        catch (SQLException e){
            logger.error(e.getMessage(), e);
            throw new MysqlDaoException("User was not updated, please try again.");
        }
        finally {
            try {
                if (statement != null){
                    statement.close();
                }
            }
            catch (SQLException e){}
            try{
                if(connection != null){
                    connection.close();
                }
            }
            catch (SQLException e){
                logger.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public int getNumberOfRecords() {return numberOfRecords; }
}
