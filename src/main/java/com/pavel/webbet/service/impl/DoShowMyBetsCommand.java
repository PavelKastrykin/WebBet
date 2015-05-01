package com.pavel.webbet.service.impl;

import com.pavel.webbet.dao.mysqldao.impl.BetDao;
import com.pavel.webbet.entity.bet.BetBean;
import com.pavel.webbet.entity.userbean.UserBean;
import com.pavel.webbet.service.ICommand;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DoShowMyBetsCommand implements ICommand{
    @Override
    public String execute(HttpServletRequest request) {
        BetDao dao = BetDao.getInstance();
        UserBean user = (UserBean)(request.getSession().getAttribute("userValue"));
        String login = user.getLogin();
        List<BetBean> myBets = dao.betsByLogin(login);
        request.setAttribute("myBets", myBets);
        return "jsp/myBets.jsp";
    }
}
