package com.pavel.webbet.service.impl;

import com.pavel.webbet.constant.RequestParameterConstant;
import com.pavel.webbet.constant.UrlConstant;
import com.pavel.webbet.dao.IBetDao;
import com.pavel.webbet.dao.factory.DaoFactory;
import com.pavel.webbet.dao.factory.DaoType;
import com.pavel.webbet.dao.mysql.MysqlDaoException;
import com.pavel.webbet.dao.mysql.impl.BetDao;
import com.pavel.webbet.entity.bet.BetBean;
import com.pavel.webbet.entity.userbean.UserBean;
import com.pavel.webbet.service.CommandException;
import com.pavel.webbet.service.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DoShowMyBetsCommand implements ICommand{

    public static final Logger logger = Logger.getLogger(DoShowMyBetsCommand.class);
    private static final String ATTRIBUTE_MY_BETS = "myBets";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        IBetDao dao = DaoFactory.getDao(DaoType.BET);
        UserBean user = (UserBean)(request.getSession().getAttribute(RequestParameterConstant.SESSION_USER_VALUE));
        String login = user.getLogin();
        List<BetBean> myBets = null;
        try{
            myBets = dao.getListByName(login);
        }
        catch (MysqlDaoException e){throw new CommandException(e.getMessage());}
        request.setAttribute(ATTRIBUTE_MY_BETS, myBets);
        return UrlConstant.URL_MY_BETS;
    }
}
