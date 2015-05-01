package com.pavel.webbet.service.impl;

import com.pavel.webbet.dao.mysqldao.impl.BetDao;
import com.pavel.webbet.entity.bet.BetBean;
import com.pavel.webbet.service.ICommand;

import javax.servlet.http.HttpServletRequest;

public class DoCreateBetEditCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("betId"));
        BetDao dao = BetDao.getInstance();
        BetBean bet = dao.getBetById(id);
        request.setAttribute("betToEdit", bet);
        return "jsp/editBet.jsp";
    }
}
