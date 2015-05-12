package com.pavel.webbet.service.impl;

import com.pavel.webbet.constant.UrlConstant;
import com.pavel.webbet.dao.IBetDao;
import com.pavel.webbet.dao.factory.DaoFactory;
import com.pavel.webbet.dao.factory.DaoType;
import com.pavel.webbet.dao.mysql.MysqlDaoException;
import com.pavel.webbet.dao.mysql.impl.BetDao;
import com.pavel.webbet.entity.bet.BetBean;
import com.pavel.webbet.service.CommandException;
import com.pavel.webbet.service.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DoCreateBetEditCommand implements ICommand {

    public static final Logger logger = Logger.getLogger(DoCreateBetEditCommand.class);
    private static final String PARAMETER_BET_ID = "betId";
    private static final String ATTRIBUTE_BET_TO_EDIT = "betToEdit";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int id = Integer.parseInt(request.getParameter(PARAMETER_BET_ID));
        IBetDao dao = DaoFactory.getDao(DaoType.BET);
        BetBean bet = null;
        try {
            bet = dao.getBeanById(id);
        }
        catch (MysqlDaoException e){throw new CommandException(e.getMessage());}
        request.setAttribute(ATTRIBUTE_BET_TO_EDIT, bet);
        return UrlConstant.URL_EDIT_BET;
    }
}
