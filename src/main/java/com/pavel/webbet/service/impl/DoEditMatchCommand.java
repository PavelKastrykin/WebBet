package com.pavel.webbet.service.impl;

import com.pavel.webbet.constant.RequestParameterConstant;
import com.pavel.webbet.constant.UrlConstant;
import com.pavel.webbet.dao.ICommonDao;
import com.pavel.webbet.dao.IFootballMatchDao;
import com.pavel.webbet.dao.factory.DaoFactory;
import com.pavel.webbet.dao.factory.DaoType;
import com.pavel.webbet.dao.mysql.MysqlDaoException;
import com.pavel.webbet.dao.mysql.impl.FootballMatchDAO;
import com.pavel.webbet.entity.match.FootballMatch;
import com.pavel.webbet.entity.match.MatchStatus;
import com.pavel.webbet.service.CommandException;
import com.pavel.webbet.service.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DoEditMatchCommand implements ICommand {

    public static final Logger logger = Logger.getLogger(DoEditMatchCommand.class);
    private static final String PARAMETER_MATCH_SCORE = "matchScore";
    private static final String PARAMETER_WIN_COEF = "winCoef";
    private static final String PARAMETER_DRAW_COEF = "drawCoef";
    private static final String PARAMETER_LOOSE_COEF = "looseCoef";
    private static final String PARAMETER_MATCH_STATUS = "status";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        FootballMatch match = new FootballMatch();
        try{
            match.setMatchId(Integer.parseInt(request.getParameter(RequestParameterConstant.PARAMETER_MATCH_ID)));
            match.setMatchScore(request.getParameter(PARAMETER_MATCH_SCORE));
            match.setWinCoef(Float.parseFloat(request.getParameter(PARAMETER_WIN_COEF)));
            match.setDrawCoef(Float.parseFloat(request.getParameter(PARAMETER_DRAW_COEF)));
            match.setLooseCoef(Float.parseFloat(request.getParameter(PARAMETER_LOOSE_COEF)));
            match.setStatus(MatchStatus.valueOf(request.getParameter(PARAMETER_MATCH_STATUS)));
        }
        catch (NumberFormatException e){
            logger.info(e.getMessage(), e);
            throw new CommandException("Illegal data was inserted");
        }

        IFootballMatchDao dao = DaoFactory.getDao(DaoType.MATCH);
        try{
            dao.updateBean(match);
        }
        catch (MysqlDaoException e){
            logger.info(e.getMessage(), e);
            throw new CommandException(e.getMessage());
        }
        return UrlConstant.REQUEST_MATCH_LIST;
    }
}
