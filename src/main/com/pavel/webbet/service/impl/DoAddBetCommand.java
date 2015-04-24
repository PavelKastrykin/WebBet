package com.pavel.webbet.service.impl;

import com.pavel.webbet.dao.mysqldao.impl.BetDao;
import com.pavel.webbet.entity.bet.BetBean;
import com.pavel.webbet.entity.bet.BetStatus;
import com.pavel.webbet.entity.userbean.UserBean;
import com.pavel.webbet.service.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DoAddBetCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        double moneySum = Double.parseDouble(request.getParameter("moneySum"));
        BetStatus status = BetStatus.valueOf(request.getParameter("selectedBetNum").toUpperCase());
        Float selectedBetCoef = Float.parseFloat(request.getParameter("selectedBetCoef"));
        String login = ((UserBean)(session.getAttribute("userValue"))).getLogin();
        int idOfMatch = Integer.parseInt(request.getParameter("idOfMatch"));

        BetBean betBean = new BetBean();
        betBean.setSum(moneySum);
        betBean.setStatus(status);
        betBean.setCurrentCoef(selectedBetCoef);
        betBean.setLogin(login);
        betBean.setFootballMatchId(idOfMatch);
        BetDao dao = BetDao.getInstance();



        return null;
    }
}
