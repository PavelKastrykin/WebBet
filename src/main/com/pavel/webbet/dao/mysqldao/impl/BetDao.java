package com.pavel.webbet.dao.mysqldao.impl;

public class BetDao {

    private static final BetDao instance = new BetDao();
    public static BetDao getInstance() {
        return instance;
    }


}
