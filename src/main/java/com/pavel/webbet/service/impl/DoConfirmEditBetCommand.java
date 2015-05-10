package com.pavel.webbet.service.impl;

import com.pavel.webbet.constant.UrlConstant;
import com.pavel.webbet.dao.IBetDao;
import com.pavel.webbet.dao.factory.DaoFactory;
import com.pavel.webbet.dao.factory.DaoType;
import com.pavel.webbet.dao.mysql.MysqlDaoException;
import com.pavel.webbet.dao.mysql.impl.BetDao;
import com.pavel.webbet.entity.bet.BetStatus;
import com.pavel.webbet.entity.bet.BetBean;
import com.pavel.webbet.service.CommandException;
import com.pavel.webbet.service.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DoConfirmEditBetCommand implements ICommand {

    public static final Logger logger = Logger.getLogger(DoConfirmEditBetCommand.class);
    private static final String PARAMETER_BET_ID = "betid";
    private static final String PARAMETER_CHARGE = "charge";
    private static final String PARAMETER_WON = "won";
    private static final String PARAMETER_BET_STATUS = "status";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        BetBean bet = new BetBean();
        bet.setBetId(Integer.parseInt(request.getParameter(PARAMETER_BET_ID)));
        bet.setMoneyCharge(Boolean.valueOf(request.getParameter(PARAMETER_CHARGE).toLowerCase()));
        bet.setWon(Boolean.valueOf(request.getParameter(PARAMETER_WON).toLowerCase()));
        bet.setStatus(BetStatus.valueOf(request.getParameter(PARAMETER_BET_STATUS)));
        IBetDao dao = DaoFactory.getDao(DaoType.BET);
        try{
            dao.updateBean(bet);
        }
        catch (MysqlDaoException e){throw new CommandException(e.getMessage(), e);}
        return UrlConstant.REQUEST_BET_LIST;
    }
}
