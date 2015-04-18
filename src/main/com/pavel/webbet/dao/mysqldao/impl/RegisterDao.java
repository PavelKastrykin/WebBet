//package com.pavel.webbet.dao.mysqldao.impl;
//
//import com.pavel.webbet.dao.mysqldao.IMySqlDao;
//import com.pavel.webbet.dao.mysqldao.QueryConstants;
//
//import java.text.MessageFormat;
//import java.util.List;
//
//public class RegisterDao implements IMySqlDao {
//
//    private static RegisterDao instance = new RegisterDao();
//    public static RegisterDao getInstance(){
//        return instance;
//    }
//
//    public String createQuery(String userName, String password, String userRealName){
//        String query = MessageFormat.format(QueryConstants.MYSQL_REGISTER_QUERY, userName, password, userRealName);
//        return query;
//    }
//
//    @Override
//    public List<? extends Object> getQueryResult(String query) {
//        return null;
//    }
//}
