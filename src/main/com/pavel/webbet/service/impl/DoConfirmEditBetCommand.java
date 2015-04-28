package com.pavel.webbet.service.impl;

import com.pavel.webbet.dao.mysqldao.impl.BetDao;
import com.pavel.webbet.entity.bet.BetBean;
import com.pavel.webbet.entity.bet.BetStatus;
import com.pavel.webbet.service.ICommand;

import javax.servlet.http.HttpServletRequest;

public class DoConfirmEditBetCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) {
        BetBean bet = new BetBean();
        bet.setBetId(Integer.parseInt(request.getParameter("betid")));
        bet.setMoneyCharge(Boolean.valueOf(request.getParameter("charge").toLowerCase()));
        bet.setWon(Boolean.valueOf(request.getParameter("won").toLowerCase()));
        bet.setStatus(BetStatus.valueOf(request.getParameter("status")));
        BetDao dao = BetDao.getInstance();
        dao.update(bet);
        return "webBetController?command=CREATE_BET_LIST";
    }
}
