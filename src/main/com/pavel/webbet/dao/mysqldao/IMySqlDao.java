package com.pavel.webbet.dao.mysqldao;

import java.util.List;

public interface IMySqlDao {

    List<? extends Object> getQueryResult(String query);
}
