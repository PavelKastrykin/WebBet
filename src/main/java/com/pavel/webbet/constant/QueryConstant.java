package com.pavel.webbet.constant;

import com.pavel.webbet.entity.bet.BetBean;
import com.pavel.webbet.entity.match.FootballMatch;
import com.pavel.webbet.entity.userbean.UserBean;

import java.text.MessageFormat;

public class QueryConstant {
    private static final String MYSQL_LOGIN_QUERY = "select * from users where login = ''{0}'' and password = ''{1}''";
    private static final String MYSQL_GET_BY_LOGIN_QUERY = "select * from users where login = ''{0}''";
    private static final String MYSQL_REGISTER_QUERY = "insert into users (`login`, `password`, `user_role`, `user_name`) " +
            "values (''{0}'', ''{1}'', ''user'', ''{2}'')";
    private static final String MYSQL_BY_USER_TYPE_QUERY = "select * from users where user_role = ''{0}''";
    private static final String MYSQL_ALL_USER_LIST_QUERY = "select SQL_CALC_FOUND_ROWS userid, login, user_role, user_name " +
            "from users order by userid desc limit {0}, {1}";
    private static final String MYSQL_DELETE_USER_QUERY = "delete from users where userid = {0}";
    private static final String MYSQL_DELETE_MATCH_QUERY = "delete from football_match where football_matchid = {0}";
    private static final String MYSQL_UPDATE_USER_QUERY = "UPDATE users SET `user_role`=''{0}'' WHERE `login`=''{1}'';";
    private static final String MYSQL_ALL_MATCHES_LIST_QUERY = "select SQL_CALC_FOUND_ROWS * from (select * from " +
            "football_match order by football_matchid desc) as A limit {0}, {1}";
    private static final String MYSQL_ADD_MATCH_QUERY = "INSERT INTO football_match (`name`, `time_start`) values (''{0}'', ''{1}'')";
    private static final String MYSQL_GET_MATCH_ID_QUERY = "select * from football_match where football_matchid = {0}";
    private static final String MYSQL_ADD_BET_QUERY = "INSERT INTO bets (`login`, `football_matchid`, `bet_prediction`, " +
            "`sum`, `current_coef`) VALUES (''{0}'', {1}, ''{2}'', {3}, {4})";
    private static final String MYSQL_GET_BETS_BY_LOGIN = "select football_match.name, football_match.time_start, " +
            "bet_prediction, sum, current_coef, is_won, bet_status, money_charge from bets join football_match " +
            "on bets.football_matchid = football_match.football_matchid where login = ''{0}''";
    private static final String MYSQL_UPDATE_MATCH_QUERY = "UPDATE football_match SET `score`=''{0}'', `coef_win`={1}," +
            " `coef_draw`={2}, `coef_lost`={3}, `status`=''{4}'' WHERE `football_matchid`={5};";
    private static final String MYSQL_ALL_BETS_LIST_QUERY = "select SQL_CALC_FOUND_ROWS betid, login, name, time_start, score, " +
            "bet_prediction, money_charge, sum, current_coef, is_won, bet_status " +
            "from (select betid, login, football_match.name, football_match.time_start, football_match.score, " +
            "bet_prediction, money_charge, sum, current_coef, is_won, bet_status " +
            "from bets join football_match on bets.football_matchid = football_match.football_matchid " +
            "order by betid desc) as A limit {0}, {1}";

    private static final String MYSQL_GET_BET_BY_ID = "select betid, login, football_match.name, football_match.time_start, " +
            "football_match.score, bet_prediction, money_charge, sum, current_coef, is_won, bet_status " +
            "from bets join football_match on bets.football_matchid = football_match.football_matchid " +
            "where betid={0}";

    private static final String MYSQL_UPDATE_BET_QUERY = "UPDATE bets SET `money_charge`={0}, `is_won`={1}, " +
            "`bet_status`=''{2}'' WHERE `betid`={3}";

    private static final String MYSQL_MATCH_DELETION_VALIDATION_QUERY = "select betid from bets where football_matchid = {0}";

    public static String queryForLoginAndPassword(String userNameParameter, String userPasswordParameter) {
        return MessageFormat.format(QueryConstant.MYSQL_LOGIN_QUERY, userNameParameter, userPasswordParameter);
    }

    public static String queryForLogin(String login) {
        return MessageFormat.format(MYSQL_GET_BY_LOGIN_QUERY, login);
    }

    public static String queryForUserType(String type) {
        return MessageFormat.format(QueryConstant.MYSQL_BY_USER_TYPE_QUERY, type);
    }

    public static String queryForUserInsert(UserBean userBean) {
        return MessageFormat.format(QueryConstant.MYSQL_REGISTER_QUERY, userBean.getLogin(), userBean.getPassword(),
                userBean.getName());
    }

    public static String queryForUserDelete(int id) {
        return MessageFormat.format(QueryConstant.MYSQL_DELETE_USER_QUERY, Integer.toString(id));
    }

    public static String queryForMatchDelete(int id) {
        return MessageFormat.format(QueryConstant.MYSQL_DELETE_MATCH_QUERY, Integer.toString(id));
    }

    public static String queryForUserUpdate(UserBean bean) {
        return MessageFormat.format(QueryConstant.MYSQL_UPDATE_USER_QUERY, bean.getUserRole().toString().toLowerCase(),
                bean.getLogin());
    }

    public static String queryForAllMatchesWithLimit(int offset, int numberOfRecords) {
        return MessageFormat.format(QueryConstant.MYSQL_ALL_MATCHES_LIST_QUERY, Integer.toString(offset),
                Integer.toString(numberOfRecords));
    }

    public static String queryForMatchInsert(FootballMatch match) {
        return MessageFormat.format(QueryConstant.MYSQL_ADD_MATCH_QUERY, match.getMatchName(),
                new java.sql.Date((match.getStartTime()).getTime()).toString());
    }

    public static String queryForGetMatchById(int id) {
        return MessageFormat.format(QueryConstant.MYSQL_GET_MATCH_ID_QUERY, Integer.toString(id));
    }

    public static String queryForBetInsert (BetBean bean) {
        return MessageFormat.format(QueryConstant.MYSQL_ADD_BET_QUERY, bean.getUser().getLogin(),
                Integer.toString(bean.getMatch().getMatchId()), bean.getPrediction().toString().toLowerCase(),
                Integer.toString(bean.getSum()), Float.toString(bean.getCurrentCoef()));
    }

    public static String queryForGetBetsByLogin (String login) {
        return MessageFormat.format(QueryConstant.MYSQL_GET_BETS_BY_LOGIN, login);
    }

    public static String queryForMatchUpdate(FootballMatch match) {
        return MessageFormat.format(QueryConstant.MYSQL_UPDATE_MATCH_QUERY, match.getMatchScore(),
                Float.toString(match.getWinCoef()), Float.toString(match.getDrawCoef()),
                Float.toString(match.getLooseCoef()), match.getStatus().toString().toLowerCase(),
                Integer.toString(match.getMatchId()));
    }

    public static String queryForAllBetsWithLimit(int offset, int numberOfRecords) {
        return MessageFormat.format(QueryConstant.MYSQL_ALL_BETS_LIST_QUERY, Integer.toString(offset),
                Integer.toString(numberOfRecords));
    }

    public static String queryForGetBetById(int id) {
        return MessageFormat.format(QueryConstant.MYSQL_GET_BET_BY_ID, Integer.toString(id));
    }

    public static String queryForBetUpdate(BetBean bet) {
        return MessageFormat.format(QueryConstant.MYSQL_UPDATE_BET_QUERY, bet.isMoneyCharge() ? 1 : 0, bet.isWon() ? 1 : 0,
                bet.getStatus().toString().toLowerCase(), Integer.toString(bet.getBetId()));
    }

    public static String queryForAllUsersWithLimit(int offset, int numberOfRecords) {
        return MessageFormat.format(QueryConstant.MYSQL_ALL_USER_LIST_QUERY, Integer.toString(offset),
                Integer.toString(numberOfRecords));
    }

    public static String queryForMatchDeletionValidation(int id) {
        return MessageFormat.format(QueryConstant.MYSQL_MATCH_DELETION_VALIDATION_QUERY, Integer.toString(id));
    }
}
