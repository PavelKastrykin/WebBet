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
import java.util.List;

public class DoDisplayMatchesCommand implements ICommand {

    public static final Logger logger = Logger.getLogger(DoDisplayMatchesCommand.class);
    private static final String ATTRIBUTE_MATCHES_LIST = "matchesList";

    @Override
    public String execute(HttpServletRequest request) throws CommandException{
        int page = 1;
        int recordsPerPage = 10;
        if (request.getParameter(RequestParameterConstant.PARAMETER_PAGINATION_PAGE) != null){
            page = Integer.parseInt(request.getParameter(RequestParameterConstant.PARAMETER_PAGINATION_PAGE));
        }
        IFootballMatchDao dao = DaoFactory.getDao(DaoType.MATCH);
        List<FootballMatch> list = null;
        try {
            list = dao.getList((page - 1) * recordsPerPage, recordsPerPage);
        }
        catch (MysqlDaoException e){throw new CommandException(e.getMessage(), e);}
        int numberOfRecords = dao.getNumberOfRecords();
        int numberOfPages = (int)Math.ceil(numberOfRecords * 1.0 / recordsPerPage);
        request.setAttribute(ATTRIBUTE_MATCHES_LIST, list);
        request.setAttribute(RequestParameterConstant.ATTRIBUTE_PAGINATION_NUMBER_OF_PAGES, numberOfPages);
        request.setAttribute(RequestParameterConstant.ATTRIBUTE_PAGINATION_CURRENT_PAGE, page);
        return UrlConstant.URL_HOME;
    }
}
