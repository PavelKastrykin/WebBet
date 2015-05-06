package com.pavel.webbet.service.impl;

import com.pavel.webbet.dao.mysqldao.MysqlDaoException;
import com.pavel.webbet.dao.mysqldao.impl.FootballMatchDAO;
import com.pavel.webbet.service.CommandException;
import com.pavel.webbet.service.ICommand;

import javax.servlet.http.HttpServletRequest;

public class DoDeleteMatchCommand implements ICommand{
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int id = Integer.parseInt(request.getParameter("matchToDeleteId"));
        FootballMatchDAO dao = FootballMatchDAO.getInstance();
        try {
            dao.delete(id);
        }
        catch (MysqlDaoException e){
            throw new CommandException(e.getMessage(), e);
        }
        return "webBetController?command=DISPLAY_MATCHES_COMMAND&submit=Show+matches";
    }
}
