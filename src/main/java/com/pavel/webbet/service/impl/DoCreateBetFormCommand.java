package com.pavel.webbet.service.impl;

import com.pavel.webbet.constant.RequestParameterConstant;
import com.pavel.webbet.constant.UrlConstant;
import com.pavel.webbet.dao.mysql.MysqlDaoException;
import com.pavel.webbet.dao.mysql.impl.FootballMatchDAO;
import com.pavel.webbet.entity.match.FootballMatch;
import com.pavel.webbet.service.CommandException;
import com.pavel.webbet.service.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DoCreateBetFormCommand implements ICommand {

    public static final Logger logger = Logger.getLogger(DoCreateBetFormCommand.class);
    private static final String PARAMETER_MATCH_TO_BET = "matchToBet";

    @Override
    public String execute(HttpServletRequest request) throws CommandException{
        int id = Integer.parseInt(request.getParameter(RequestParameterConstant.PARAMETER_MATCH_ID));
        FootballMatchDAO dao = FootballMatchDAO.getInstance();
        FootballMatch match = null;
        try {
            match = dao.getBeanById(id);
        }
        catch (MysqlDaoException e){throw new CommandException(e.getMessage(), e);}
        request.setAttribute(PARAMETER_MATCH_TO_BET, match);
        return UrlConstant.URL_MAKE_BET;
    }
}
