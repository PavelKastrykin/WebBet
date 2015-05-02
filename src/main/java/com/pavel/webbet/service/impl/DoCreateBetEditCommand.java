package com.pavel.webbet.service.impl;

import com.pavel.webbet.dao.mysqldao.MysqlDaoException;
import com.pavel.webbet.dao.mysqldao.impl.BetDao;
import com.pavel.webbet.entity.bet.BetBean;
import com.pavel.webbet.service.CommandException;
import com.pavel.webbet.service.ICommand;

import javax.servlet.http.HttpServletRequest;

public class DoCreateBetEditCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int id = Integer.parseInt(request.getParameter("betId"));
        BetDao dao = BetDao.getInstance();
        BetBean bet = null;
        try {
            bet = dao.getBetById(id);
        }
        catch (MysqlDaoException e){throw new CommandException(e.getMessage(), e);}
        request.setAttribute("betToEdit", bet);
        return "jsp/editBet.jsp";
    }
}
