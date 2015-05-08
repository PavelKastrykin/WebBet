package com.pavel.webbet.service.impl;

import com.pavel.webbet.dao.mysqldao.MysqlDaoException;
import com.pavel.webbet.dao.mysqldao.impl.FootballMatchDAO;
import com.pavel.webbet.entity.match.FootballMatch;
import com.pavel.webbet.entity.match.MatchStatus;
import com.pavel.webbet.service.CommandException;
import com.pavel.webbet.service.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DoEditMatchCommand implements ICommand {

    public static final Logger logger = Logger.getLogger(DoEditMatchCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        FootballMatch match = new FootballMatch();
        try{
            match.setMatchId(Integer.parseInt(request.getParameter("matchid")));
            match.setMatchScore(request.getParameter("matchScore"));
            match.setWinCoef(Float.parseFloat(request.getParameter("winCoef")));
            match.setDrawCoef(Float.parseFloat(request.getParameter("drawCoef")));
            match.setLooseCoef(Float.parseFloat(request.getParameter("looseCoef")));
            match.setStatus(MatchStatus.valueOf(request.getParameter("status")));
        }
        catch (NumberFormatException e){
            throw new CommandException("Illegal data was inserted");
        }

            FootballMatchDAO dao = FootballMatchDAO.getInstance();
        try{
            dao.updateBean(match);
        }
        catch (MysqlDaoException e){throw new CommandException(e.getMessage(), e);}
        return "webBetController?command=DISPLAY_MATCHES_COMMAND";
    }
}
