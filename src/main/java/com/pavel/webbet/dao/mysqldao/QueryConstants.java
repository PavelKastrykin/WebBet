package com.pavel.webbet.dao.mysqldao;

import com.pavel.webbet.entity.bet.BetBean;
import com.pavel.webbet.entity.match.FootballMatch;
import com.pavel.webbet.entity.userbean.UserBean;

import java.text.MessageFormat;

public class QueryConstants {
    public static final String MYSQL_LOGIN_QUERY = "select * from users where login = ''{0}'' and password = ''{1}''";
    public static final String MYSQL_GET_BY_LOGIN_QUERY = "select * from users where login = ''{0}''";
    public static final String MYSQL_REGISTER_QUERY = "insert into users (`login`, `password`, `user_role`, `user_name`) " +
            "values (''{0}'', ''{1}'', ''user'', ''{2}'')";
    public static final String MYSQL_BY_USER_TYPE_QUERY = "select * from users where user_role = ''{0}''";
    public static final String MYSQL_ALL_USER_LIST_QUERY = "select SQL_CALC_FOUND_ROWS userid, login, user_role, user_name " +
            "from users order by userid desc limit {0}, {1}";
    public static final String MYSQL_DELETE_USER_QUERY = "delete from users where userid = ''{0}''";
    public static final String MYSQL_UPDATE_USER_QUERY = "UPDATE users SET `user_role`=''{0}'' WHERE `login`=''{1}'';";
    public static final String MYSQL_ALL_MATCHES_LIST_QUERY = "select SQL_CALC_FOUND_ROWS * from (select * from " +
            "football_match order by football_matchid desc) as A limit {0}, {1}";
    public static final String MYSQL_ADD_MATCH_QUERY = "INSERT INTO football_match (`name`, `time_start`) values (''{0}'', ''{1}'')";
    public static final String MYSQL_GET_MATCH_ID_QUERY = "select * from football_match where football_matchid = ''{0}''";
    public static final String MYSQL_ADD_BET_QUERY = "INSERT INTO bets (`login`, `football_matchid`, `bet_prediction`, " +
            "`sum`, `current_coef`) VALUES (''{0}'', ''{1}'', ''{2}'', ''{3}'', ''{4}'')";
    public static final String MYSQL_GET_BETS_BY_LOGIN = "select football_match.name, football_match.time_start, " +
            "bet_prediction, sum, current_coef, is_won, bet_status, money_charge from bets join football_match " +
            "on bets.football_matchid = football_match.football_matchid where login = ''{0}''";
    public static final String MYSQL_UPDATE_MATCH_QUERY = "UPDATE football_match SET `score`=''{0}'', `coef_win`=''{1}''," +
            " `coef_draw`=''{2}'', `coef_lost`=''{3}'', `status`=''{4}'' WHERE `football_matchid`=''{5}'';";
    public static final String MYSQL_ALL_BETS_LIST_QUERY = "select SQL_CALC_FOUND_ROWS betid, login, name, time_start, score, " +
            "bet_prediction, money_charge, sum, current_coef, is_won, bet_status " +
            "from (select betid, login, football_match.name, football_match.time_start, football_match.score, " +
            "bet_prediction, money_charge, sum, current_coef, is_won, bet_status " +
            "from bets join football_match on bets.football_matchid = football_match.football_matchid " +
            "order by betid desc) as A limit {0}, {1}";

    public static String MYSQL_GET_BET_BY_ID = "select betid, login, football_match.name, football_match.time_start, " +
            "football_match.score, bet_prediction, money_charge, sum, current_coef, is_won, bet_status " +
            "from bets join football_match on bets.football_matchid = football_match.football_matchid " +
            "where betid=''{0}''";

    public static String MYSQL_UPDATE_BET_QUERY = "UPDATE bets SET `money_charge`={0}, `is_won`={1}, " +
            "`bet_status`=''{2}'' WHERE `betid`=''{3}''";

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
        String query = MessageFormat.format(QueryConstants.MYSQL_UPDATE_USER_QUERY, bean.getUserRole().toString().toLowerCase(),
                bean.getLogin());
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

    public static String queryForGetMatchById(int id) {
        String query = MessageFormat.format(QueryConstants.MYSQL_GET_MATCH_ID_QUERY, id);
        return query;
    }

    public static String queryForBetInsert (BetBean bean) {
        String query = MessageFormat.format(QueryConstants.MYSQL_ADD_BET_QUERY,
                bean.getLogin(), bean.getFootballMatchId(), bean.getPrediction().toString().toLowerCase(), bean.getSum(),
                bean.getCurrentCoef());
        return query;
    }

    public static String queryForGetBetsByLogin (String login) {
        String query = MessageFormat.format(QueryConstants.MYSQL_GET_BETS_BY_LOGIN, login);
        return query;
    }

    public static String queryForMatchUpdate(FootballMatch match) {
        String query = MessageFormat.format(QueryConstants.MYSQL_UPDATE_MATCH_QUERY, match.getMatchScore(), match.getWinCoef(),
                match.getDrawCoef(), match.getLooseCoef(), match.getStatus().toString().toLowerCase(), match.getMatchId());
        return  query;
    }

    public static String queryForAllBetsWithLimit(int offset, int numberOfRecords) {
        String query = MessageFormat.format(QueryConstants.MYSQL_ALL_BETS_LIST_QUERY, offset, numberOfRecords);
        return query;
    }

    public static String queryForGetBetById(int id) {
        String query = MessageFormat.format(QueryConstants.MYSQL_GET_BET_BY_ID, id);
        return query;
    }

    public static String queryForBetUpdate(BetBean bet) {
        String query = MessageFormat.format(QueryConstants.MYSQL_UPDATE_BET_QUERY, bet.isMoneyCharge() ? 1 : 0, bet.isWon() ? 1 : 0,
                bet.getStatus().toString().toLowerCase(), bet.getBetId());
        return query;
    }

    public static String queryForAllUsersWithLimit(int offset, int numberOfRecords) {
        String query = MessageFormat.format(QueryConstants.MYSQL_ALL_USER_LIST_QUERY, offset, numberOfRecords);
        return query;
    }
}
