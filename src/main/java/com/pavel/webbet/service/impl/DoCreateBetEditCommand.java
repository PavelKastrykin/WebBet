package com.pavel.webbet.service.impl;

import com.pavel.webbet.dao.mysqldao.MysqlDaoException;
import com.pavel.webbet.dao.mysqldao.impl.BetDao;
import com.pavel.webbet.entity.bet.BetBean;
import com.pavel.webbet.service.CommandException;
import com.pavel.webbet.service.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DoCreateBetEditCommand implements ICommand {

    public static final Logger logger = Logger.getLogger(DoCreateBetEditCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int id = Integer.parseInt(request.getParameter("betId"));
        BetDao dao = BetDao.getInstance();
        BetBean bet = null;
        try {
            bet = (BetBean)(dao.getBeanById(id));
        }
        catch (MysqlDaoException e){throw new CommandException(e.getMessage(), e);}
        request.setAttribute("betToEdit", bet);
        return "jsp/editBet.jsp";
    }
}
