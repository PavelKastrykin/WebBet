package com.pavel.webbet.service.impl;

import com.pavel.webbet.dao.mysqldao.MysqlDaoException;
import com.pavel.webbet.dao.mysqldao.impl.BetDao;
import com.pavel.webbet.entity.bet.BetStatus;
import com.pavel.webbet.entity.bet.BetBean;
import com.pavel.webbet.service.CommandException;
import com.pavel.webbet.service.ICommand;

import javax.servlet.http.HttpServletRequest;

public class DoConfirmEditBetCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        BetBean bet = new BetBean();
        bet.setBetId(Integer.parseInt(request.getParameter("betid")));
        bet.setMoneyCharge(Boolean.valueOf(request.getParameter("charge").toLowerCase()));
        bet.setWon(Boolean.valueOf(request.getParameter("won").toLowerCase()));
        bet.setStatus(BetStatus.valueOf(request.getParameter("status")));
        BetDao dao = BetDao.getInstance();
        try{
            dao.update(bet);
        }
        catch (MysqlDaoException e){throw new CommandException(e.getMessage(), e);}
        return "webBetController?command=CREATE_BET_LIST";
    }
}
