package com.pavel.webbet.service.impl;

import com.pavel.webbet.constant.RequestParameterConstant;
import com.pavel.webbet.constant.UrlConstant;
import com.pavel.webbet.dao.IFootballMatchDao;
import com.pavel.webbet.dao.factory.DaoFactory;
import com.pavel.webbet.dao.factory.DaoType;
import com.pavel.webbet.dao.mysql.MysqlDaoException;
import com.pavel.webbet.dao.mysql.impl.FootballMatchDAO;
import com.pavel.webbet.entity.match.FootballMatch;
import com.pavel.webbet.entity.userbean.UserBean;
import com.pavel.webbet.service.CommandException;
import com.pavel.webbet.service.CommandWarningConstants;
import com.pavel.webbet.service.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DoAddMatchCommand implements ICommand {

    public static final Logger logger = Logger.getLogger(DoAddMatchCommand.class);
    private static final String PARAMETER_MATCH_NAME = "matchName";
    private static final String PARAMETER_MATCH_DATE = "matchDate";
    private static final String ATTRIBUTE_ADD_MATCH_WARNING = "addMatchWarning";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String matchName = request.getParameter(PARAMETER_MATCH_NAME);
        String matchDateAsString = request.getParameter(PARAMETER_MATCH_DATE);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date matchDate = null;
        try {
            matchDate = df.parse(matchDateAsString);
        }
        catch (ParseException e){
            e.printStackTrace();
            throw new CommandException("Invalid Date");
        }

        HttpSession session = request.getSession(true);
        String userRole = ((UserBean)session.getAttribute(RequestParameterConstant.SESSION_USER_VALUE)).getUserRole().toString();
        if (!("ADMIN".equals(userRole) || "BOOKMAKER".equals(userRole))){
            request.setAttribute(ATTRIBUTE_ADD_MATCH_WARNING, CommandWarningConstants.VALIDATION_ADMIN_BOOKMAKER_WARNING);
            return UrlConstant.URL_ADD_MATCH;
        }
        if (matchName.isEmpty() || matchDateAsString.isEmpty()){
            request.setAttribute(ATTRIBUTE_ADD_MATCH_WARNING, CommandWarningConstants.EMPTY_FIELDS_WARNING);
            return UrlConstant.URL_ADD_MATCH;
        }

        FootballMatch match = new FootballMatch();
        match.setMatchName(matchName);
        match.setStartTime(matchDate);
        IFootballMatchDao dao = DaoFactory.getDao(DaoType.MATCH);
        try {
            dao.insert(match);
        }
        catch (MysqlDaoException e){throw  new CommandException(e.getMessage(), e);}

        request.setAttribute(ATTRIBUTE_ADD_MATCH_WARNING, CommandWarningConstants.MATCH_ADDED_WARNING);

        return UrlConstant.URL_ADD_MATCH;
    }
}
