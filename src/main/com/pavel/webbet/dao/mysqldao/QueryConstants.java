package com.pavel.webbet.dao.mysqldao;

import com.pavel.webbet.entity.match.FootballMatch;
import com.pavel.webbet.entity.userbean.UserBean;

import java.text.MessageFormat;
import java.util.Date;

public class QueryConstants {
    public static final String MYSQL_LOGIN_QUERY = "select * from users where login = ''{0}'' and password = ''{1}''";
    public static final String MYSQL_GET_BY_LOGIN_QUERY = "select * from users where login = ''{0}''";
    public static final String MYSQL_REGISTER_QUERY = "insert into users (`login`, `password`, `user_role`, `user_name`) values (''{0}'', ''{1}'', ''user'', ''{2}'')";
    public static final String MYSQL_BY_USER_TYPE_QUERY = "select * from users where user_role = ''{0}''";
    public static final String MYSQL_ALL_USER_LIST_QUERY = "select * from users";
    public static final String MYSQL_DELETE_USER_QUERY = "delete from users where userid = ''{0}''";
    public static final String MYSQL_UPDATE_USER_QUERY = "UPDATE users SET `login`=''{0}'', `password`=''{1}'', `user_role`=''{2}'', `user_name`=''{3}'' WHERE `userid`=''{4}'';";
    public static final String MYSQL_ALL_MATCHES_LIST_QUERY = "select SQL_CALC_FOUND_ROWS * from (select * from football_match order by football_matchid desc) as A limit {0}, {1}";
    public static final String MYSQL_ADD_MATCH_QUERY = "INSERT INTO football_match (`name`, `time_start`) values (''{0}'', ''{1}'')";

    public static String queryForLoginAndPassword(String userNameParameter, String userPasswordParameter) {
        String query = MessageFormat.format(QueryConstants.MYSQL_LOGIN_QUERY, userNameParameter, userPasswordParameter);
        return query;
    }

    public static String queryForLogin(String login) {
        String query = MessageFormat.format(MYSQL_GET_BY_LOGIN_QUERY, login);
        return query;
    }

    public static String queryForUserType(String type) {
        String query = MessageFormat.format(QueryConstants.MYSQL_BY_USER_TYPE_QUERY, type);
        return query;
    }

    public static String queryForUserInsert(UserBean userBean) {
        String query = MessageFormat.format(QueryConstants.MYSQL_REGISTER_QUERY, userBean.getLogin(), userBean.getPassword(),
                userBean.getName());
        return query;
    }

    public static String queryForUserDelete(int id) {
        String query = MessageFormat.format(QueryConstants.MYSQL_DELETE_USER_QUERY, id);
        return query;
    }

    public static String queryForUserUpdate(UserBean bean) {
        String query = MessageFormat.format(QueryConstants.MYSQL_UPDATE_USER_QUERY,
                bean.getLogin(), bean.getPassword(), bean.getUserRole().toString().toLowerCase(), bean.getName(), bean.getUserID());
        return query;
    }

    public static String queryForAllMatchesWithLimit(int offset, int numberOfRecords) {
        String query = MessageFormat.format(QueryConstants.MYSQL_ALL_MATCHES_LIST_QUERY, offset, numberOfRecords);
        return query;
    }

    public static String queryForMatchInsert(FootballMatch match) {
        String query = MessageFormat.format(QueryConstants.MYSQL_ADD_MATCH_QUERY, match.getMatchName(),
                new java.sql.Date((match.getStartTime()).getTime()).toString());
        return query;
    }

}
