package com.pavel.webbet.service.impl;

import com.pavel.webbet.dao.mysqldao.MysqlDaoException;
import com.pavel.webbet.dao.mysqldao.impl.FootballMatchDAO;
import com.pavel.webbet.entity.match.FootballMatch;
import com.pavel.webbet.service.CommandException;
import com.pavel.webbet.service.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DoCreateMatchEditCommand implements ICommand {

    public static final Logger logger = Logger.getLogger(DoCreateMatchEditCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int id = Integer.parseInt(request.getParameter("matchId"));
        FootballMatchDAO dao = FootballMatchDAO.getInstance();
        FootballMatch match = null;
        try{
            match = dao.getBeanById(id);
        }
        catch (MysqlDaoException e){throw new CommandException(e.getMessage(), e);}
        request.setAttribute("matchToEdit", match);
        return "jsp/editMatch.jsp";
    }
}
