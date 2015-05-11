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

public class UserBeanDao extends DaoJdbcResource implements IUserBeanDao {

    public static final Logger logger = Logger.getLogger(UserBeanDao.class);

    private static final UserBeanDao instance = new UserBeanDao();
    public static UserBeanDao getInstance() {
        return instance;
    }
    private int numberOfRecords;

    @Override
    public UserBean getBeanByName(String login) throws MysqlDaoException{
        UserBean userBean = null;
        ResultSet resultSet = null;
        try {
            prepareConnection();
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
        catch (ConnectionPoolException e){ throw new MysqlDaoException(e.getMessage(), e);}
        catch (SQLException e) {throw new MysqlDaoException("Error retrieving data from database", e);}
        finally {
            releaseJDBCResources();
        }
    }

    @Override
    public UserBean getBeanByNameAndPassword(String login, String password) throws MysqlDaoException {

        UserBean userBean = null;
        ResultSet resultSet = null;
        try {
            prepareConnection();
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
        catch (ConnectionPoolException e){ throw new MysqlDaoException(e.getMessage(), e);}
        catch (SQLException e) {throw new MysqlDaoException("Error retrieving data from database", e);}
        finally {
            releaseJDBCResources();
        }
    }

    @Override
    public List<UserBean> getListByName(String type) throws MysqlDaoException {

        ResultSet resultSet = null;
        List<UserBean> userList = new ArrayList<UserBean>();
        try {
            prepareConnection();
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
        catch (ConnectionPoolException e){ throw new MysqlDaoException(e.getMessage(), e);}
        catch (SQLException e) {throw new MysqlDaoException("Error retrieving data from database", e);}
        finally {
            releaseJDBCResources();
        }
    }

    @Override
    public List<UserBean> getList(int offset, int noOfRecords) throws MysqlDaoException{

        List<UserBean> userList = new ArrayList<>();
        UserBean user = null;
        try {
            prepareConnection();
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
        catch (ConnectionPoolException e){ throw new MysqlDaoException(e.getMessage(), e);}
        catch (SQLException e) {throw new MysqlDaoException("Error retrieving data from database", e);}
        finally {
            releaseJDBCResources();
        }
        return userList;
    }

    @Override
    public void insert(UserBean bean) throws MysqlDaoException {

        try {
            prepareConnection();
            int x = statement.executeUpdate(QueryConstant.queryForUserInsert(bean));
        }
        catch (ConnectionPoolException e){throw new MysqlDaoException(e.getMessage(), e);}
        catch (SQLException e){ throw new MysqlDaoException("Data was not inserted to database", e);}
        finally {
            releaseJDBCResources();
        }
    }

    @Override
    public void deleteBean(int id) throws MysqlDaoException {

        try {
            prepareConnection();
            int x = statement.executeUpdate(QueryConstant.queryForUserDelete(id));
        }
        catch (ConnectionPoolException e){throw new MysqlDaoException(e.getMessage(), e);}
        catch (SQLException e){ throw new MysqlDaoException("Data was not deleted from database", e);}
        finally {
            releaseJDBCResources();
        }
    }

    @Override
    public void updateBean(UserBean bean) throws MysqlDaoException {

        try {
            prepareConnection();
            int x = statement.executeUpdate(QueryConstant.queryForUserUpdate(bean));
        }
        catch (ConnectionPoolException e){throw new MysqlDaoException(e.getMessage(), e);}
        catch (SQLException e){ throw new MysqlDaoException("Data was not inserted to database", e);}
        finally {
            releaseJDBCResources();
        }
    }

    @Override
    public int getNumberOfRecords() {return numberOfRecords; }
}
