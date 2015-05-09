package com.pavel.webbet.dao.factory;

import com.pavel.webbet.dao.IBetDao;
import com.pavel.webbet.dao.ICommonDao;
import com.pavel.webbet.dao.mysql.impl.BetDao;

import java.util.ResourceBundle;

public class DaoFactory {
    private static DaoFactory instance = new DaoFactory();
    private ResourceBundle bundle = ResourceBundle.getBundle("properties.project");

    public static DaoFactory getInstance(){ return instance; }

    public <T extends ICommonDao> T getDao(String value, Class<T> type){
        BetDao bet = new BetDao();
        return type.cast(bet);
    }
}
