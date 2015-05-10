package com.pavel.webbet.service.impl;

import com.pavel.webbet.constant.UrlConstant;
import com.pavel.webbet.dao.IBetDao;
import com.pavel.webbet.dao.IFootballMatchDao;
import com.pavel.webbet.dao.factory.DaoFactory;
import com.pavel.webbet.dao.factory.DaoType;
import com.pavel.webbet.dao.mysql.MysqlDaoException;
import com.pavel.webbet.dao.mysql.impl.FootballMatchDAO;
import com.pavel.webbet.service.CommandException;
import com.pavel.webbet.service.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DoDeleteMatchCommand implements ICommand{

    public static final Logger logger = Logger.getLogger(DoDeleteMatchCommand.class);
    private static final String PARAMETER_MATCH_TO_DELETE_ID = "matchToDeleteId";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int id = Integer.parseInt(request.getParameter(PARAMETER_MATCH_TO_DELETE_ID));
        IFootballMatchDao dao = DaoFactory.getDao(DaoType.MATCH);
        IBetDao betDao = DaoFactory.getDao(DaoType.BET);


        try {
            if (!betDao.containBetOnMatchId(id)){
                dao.deleteBean(id);
            }
            else throw new CommandException("This match cannot be deleted: bets are done.");
        }
        catch (MysqlDaoException e){
            throw new CommandException(e.getMessage(), e);
        }
        return UrlConstant.REQUEST_MATCH_LIST;
    }
}
