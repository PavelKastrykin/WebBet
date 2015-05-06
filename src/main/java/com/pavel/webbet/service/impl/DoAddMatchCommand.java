package com.pavel.webbet.service.impl;

import com.pavel.webbet.dao.mysqldao.MysqlDaoException;
import com.pavel.webbet.dao.mysqldao.impl.FootballMatchDAO;
import com.pavel.webbet.entity.match.FootballMatch;
import com.pavel.webbet.entity.userbean.UserBean;
import com.pavel.webbet.service.CommandException;
import com.pavel.webbet.service.CommandStringConstants;
import com.pavel.webbet.service.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DoAddMatchCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String matchName = request.getParameter("matchName");
        String matchDateAsString = request.getParameter("matchDate");
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
        String userRole = ((UserBean)session.getAttribute("userValue")).getUserRole().toString();
        if (!("ADMIN".equals(userRole) || "BOOK".equals(userRole))){
            request.setAttribute("addMatchWarning", CommandStringConstants.VALIDATION_ADMIN_BOOK_WARNING);
            return "jsp/addMatch.jsp";
        }
        if (matchName.equals("") || matchDateAsString.equals("")){
            request.setAttribute("addMatchWarning", CommandStringConstants.EMPTY_FIELDS_WARNING);
            return "jsp/addMatch.jsp";
        }

        FootballMatch match = new FootballMatch();
        match.setMatchName(matchName);
        match.setStartTime(matchDate);
        FootballMatchDAO dao = FootballMatchDAO.getInstance();
        try {
            dao.insert(match);
        }
        catch (MysqlDaoException e){throw  new CommandException(e.getMessage(), e);}

        request.setAttribute("addMatchWarning", CommandStringConstants.MATCH_ADDED_WARNING);

        return "jsp/addMatch.jsp";
    }
}
