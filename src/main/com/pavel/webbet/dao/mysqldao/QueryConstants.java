package com.pavel.webbet.dao.mysqldao;

public class QueryConstants {
    public static final String MYSQL_LOGIN_QUERY = "select userid, login, password, user_role, user_name from users \n" +
            "where login = ''{0}'' and password = ''{1}''";
    public static final String MYSQL_REGISTER_QUERY = "insert into users (`login`, `password`, `user_role`, `user_name`) values (''{0}'', ''{1}'', ''user'', ''{2}'')";
    public static final String MYSQL_BY_USER_TYPE_QUERY = "select * from users where user_role = ''{0}''";
    public static final String MYSQL_ALL_USER_LIST_QUERY = "select * from users";
    public static final String MYSQL_DELETE_USER_QUERY = "delete from users where userid = ''{0}''";
    public static final String MYSQL_UPDATE_USER_QUERY = "UPDATE users SET `login`=''{0}'', `password`=''{1}'', `user_role`=''{2}'', `user_name`=''{3}'' WHERE `userid`=''{4}'';";
}
