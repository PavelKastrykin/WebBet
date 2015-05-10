package com.pavel.webbet.dao.factory;

import com.pavel.webbet.dao.IBetDao;
import com.pavel.webbet.dao.ICommonDao;
import com.pavel.webbet.dao.mysql.impl.BetDao;
import com.pavel.webbet.dao.mysql.impl.FootballMatchDAO;
import com.pavel.webbet.dao.mysql.impl.UserBeanDao;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class DaoFactory {
    private static DaoFactory instance = new DaoFactory();
    private static ResourceBundle bundle = ResourceBundle.getBundle("properties.project");
    private static String PROJECT_DATA = bundle.getString("dao.data.type");
    private static Map<DaoType, ICommonDao> mysqlSuitability = new HashMap<>();
    static {
        mysqlSuitability.put(DaoType.BET, BetDao.getInstance());
        mysqlSuitability.put(DaoType.MATCH, FootballMatchDAO.getInstance());
        mysqlSuitability.put(DaoType.USER, UserBeanDao.getInstance());
    }

    public static DaoFactory getInstance(){ return instance; }

    public static <T extends ICommonDao> T getDao(DaoType type){
        switch (PROJECT_DATA){
            case "mysql":
                return(T)mysqlSuitability.get(type);
            default:
                return(T)mysqlSuitability.get(type);
        }
    }
}
