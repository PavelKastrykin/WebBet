package com.pavel.webbet.service.impl;

import com.pavel.webbet.dao.mysqldao.impl.FootballMatchDAO;
import com.pavel.webbet.entity.match.FootballMatch;
import com.pavel.webbet.entity.match.MatchStatus;
import com.pavel.webbet.service.ICommand;

import javax.servlet.http.HttpServletRequest;

public class DoEditMatchCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) {
        FootballMatch match = new FootballMatch();
        match.setMatchId(Integer.parseInt(request.getParameter("matchid")));
        match.setMatchScore(request.getParameter("matchScore"));
        match.setWinCoef(Float.parseFloat(request.getParameter("winCoef")));
        match.setDrawCoef(Float.parseFloat(request.getParameter("drawCoef")));
        match.setLooseCoef(Float.parseFloat(request.getParameter("looseCoef")));
        match.setStatus(MatchStatus.valueOf(request.getParameter("status")));
        FootballMatchDAO dao = FootballMatchDAO.getInstance();
        dao.update(match);
        return "webBetController?command=DISPLAY_MATCHES_COMMAND";
    }
}
