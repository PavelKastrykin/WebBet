package com.pavel.webbet.dao.mysqldao;

public class QueryConstants {
    public static final String MYSQL_LOGIN_QUERY = "select userid, login, password, user_role, user_name from users \n" +
            "where login = ''{0}'' and password = ''{1}''";
}
