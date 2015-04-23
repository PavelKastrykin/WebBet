package com.pavel.webbet.service.impl;

import com.pavel.webbet.dao.mysqldao.impl.FootballMatchDAO;
import com.pavel.webbet.entity.match.FootballMatch;
import com.pavel.webbet.service.ICommand;

import javax.servlet.http.HttpServletRequest;

public class DoCreateBetForm implements ICommand {
    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("matchId"));
        FootballMatchDAO dao = FootballMatchDAO.getInstance();
        FootballMatch match = dao.getMatchById(id);
        request.setAttribute("matchToBet", match);
        return "jsp/makeBet.jsp";
    }
}
