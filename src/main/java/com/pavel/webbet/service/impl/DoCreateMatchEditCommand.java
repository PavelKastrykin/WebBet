package com.pavel.webbet.service.impl;

import com.pavel.webbet.constant.RequestParameterConstant;
import com.pavel.webbet.constant.UrlConstant;
import com.pavel.webbet.dao.IFootballMatchDao;
import com.pavel.webbet.dao.factory.DaoFactory;
import com.pavel.webbet.dao.factory.DaoType;
import com.pavel.webbet.dao.mysql.MysqlDaoException;
import com.pavel.webbet.dao.mysql.impl.FootballMatchDAO;
import com.pavel.webbet.entity.match.FootballMatch;
import com.pavel.webbet.service.CommandException;
import com.pavel.webbet.service.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DoCreateMatchEditCommand implements ICommand {

    public static final Logger logger = Logger.getLogger(DoCreateMatchEditCommand.class);
    private static final String ATTRIBUTE_MATCH_TO_EDIT ="matchToEdit";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int id = Integer.parseInt(request.getParameter(RequestParameterConstant.PARAMETER_MATCH_ID));
        IFootballMatchDao dao = DaoFactory.getDao(DaoType.MATCH);
        FootballMatch match = null;
        try{
            match = dao.getBeanById(id);
        }
        catch (MysqlDaoException e){
            logger.error(e.getMessage(), e);
            throw new CommandException(e.getMessage());
        }
        request.setAttribute(ATTRIBUTE_MATCH_TO_EDIT, match);
        return UrlConstant.URL_EDIT_MATCH;
    }
}
