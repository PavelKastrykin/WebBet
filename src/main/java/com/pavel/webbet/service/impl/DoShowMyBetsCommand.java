package com.pavel.webbet.service.impl;

import com.pavel.webbet.dao.mysqldao.MysqlDaoException;
import com.pavel.webbet.dao.mysqldao.impl.BetDao;
import com.pavel.webbet.entity.bet.BetBean;
import com.pavel.webbet.entity.userbean.UserBean;
import com.pavel.webbet.service.CommandException;
import com.pavel.webbet.service.ICommand;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DoShowMyBetsCommand implements ICommand{
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        BetDao dao = BetDao.getInstance();
        UserBean user = (UserBean)(request.getSession().getAttribute("userValue"));
        String login = user.getLogin();
        List<BetBean> myBets = null;
        try{
            myBets = dao.betsByLogin(login);
        }
        catch (MysqlDaoException e){throw new CommandException(e.getMessage(), e);}
        request.setAttribute("myBets", myBets);
        return "jsp/myBets.jsp";
    }
}
