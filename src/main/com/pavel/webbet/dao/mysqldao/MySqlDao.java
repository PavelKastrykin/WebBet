package com.pavel.webbet.dao.mysqldao;

import java.util.List;

public interface MySqlDao {
    List<? extends Object> getQueryResult(String query);
}
