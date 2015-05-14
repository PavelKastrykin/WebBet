package com.pavel.webbet.service.impl;

import com.pavel.webbet.constant.RequestParameterConstant;
import com.pavel.webbet.constant.UrlConstant;
import com.pavel.webbet.dao.IBetDao;
import com.pavel.webbet.dao.ICommonDao;
import com.pavel.webbet.dao.factory.DaoFactory;
import com.pavel.webbet.dao.factory.DaoType;
import com.pavel.webbet.dao.mysql.MysqlDaoException;
import com.pavel.webbet.entity.bet.BetBean;
import com.pavel.webbet.entity.bet.BetPrediction;
import com.pavel.webbet.entity.userbean.UserBean;
import com.pavel.webbet.service.CommandException;
import com.pavel.webbet.service.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class DoAddBetCommand implements ICommand {

    public static final Logger logger = Logger.getLogger(DoAddBetCommand.class);

    private static final String PARAMETER_MONEY_SUM = "moneySum";
    private static final String PARAMETER_SELECTED_BET_NUMBER = "selectedBetNum";
    private static final String PARAMETER_SELECTED_BET_COEF = "selectedBetCoef";
    private static final String PARAMETER_ID_OF_MATCH = "idOfMatch";

    @Override
    public String execute(HttpServletRequest request) throws CommandException{
        HttpSession session = request.getSession(true);
        int moneySum = Integer.parseInt(request.getParameter(PARAMETER_MONEY_SUM));
        BetPrediction prediction = BetPrediction.valueOf((request.getParameter(PARAMETER_SELECTED_BET_NUMBER)).toUpperCase());
        Float selectedBetCoef = Float.parseFloat(request.getParameter(PARAMETER_SELECTED_BET_COEF));
        String login = ((UserBean)(session.getAttribute(RequestParameterConstant.SESSION_USER_VALUE))).getLogin();
        int idOfMatch = Integer.parseInt(request.getParameter(PARAMETER_ID_OF_MATCH));

        BetBean betBean = new BetBean();
        betBean.setSum(moneySum);
        betBean.setPrediction(prediction);
        betBean.setCurrentCoef(selectedBetCoef);
        betBean.getUser().setLogin(login);
        betBean.getMatch().setMatchId(idOfMatch);

        IBetDao dao = DaoFactory.getDao(DaoType.BET);
        try {
            dao.insert(betBean);
        }
        catch (MysqlDaoException e){
            logger.info(e.getMessage(), e);
            throw new CommandException(e.getMessage());
        }
        return UrlConstant.REQUEST_MATCH_LIST;
    }


}
