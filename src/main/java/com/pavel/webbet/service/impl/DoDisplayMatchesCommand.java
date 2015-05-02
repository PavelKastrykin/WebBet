package com.pavel.webbet.service.impl;

import com.pavel.webbet.dao.mysqldao.MysqlDaoException;
import com.pavel.webbet.dao.mysqldao.impl.FootballMatchDAO;
import com.pavel.webbet.entity.match.FootballMatch;
import com.pavel.webbet.service.CommandException;
import com.pavel.webbet.service.ICommand;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DoDisplayMatchesCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException{
        int page = 1;
        int recordsPerPage = 10;
        if (request.getParameter("page") != null){
            page = Integer.parseInt(request.getParameter("page"));
        }
        FootballMatchDAO dao = FootballMatchDAO.getInstance();
        List<FootballMatch> list = null;
        try {
            list = dao.viewAllMatches((page - 1) * recordsPerPage, recordsPerPage);
        }
        catch (MysqlDaoException e){throw new CommandException(e.getMessage(), e);}
        int numberOfRecords = dao.getNumberOfRecords();
        int numberOfPages = (int)Math.ceil(numberOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("matchesList", list);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("currentPage", page);
        return "jsp/home.jsp";
    }
}
