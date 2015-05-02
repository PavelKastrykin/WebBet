package com.pavel.webbet.service.impl;

import com.pavel.webbet.dao.mysqldao.MysqlDaoException;
import com.pavel.webbet.dao.mysqldao.impl.BetDao;
import com.pavel.webbet.entity.bet.BetBean;
import com.pavel.webbet.service.CommandException;
import com.pavel.webbet.service.ICommand;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DoCreateBetListCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int page = 1;
        int recordsPerPage = 10;
        if (request.getParameter("page") != null){
            page = Integer.parseInt(request.getParameter("page"));
        }
        BetDao dao = BetDao.getInstance();
        List<BetBean> list = null;
        try {
            list = dao.viewAllBets((page - 1) * recordsPerPage, recordsPerPage);
        }
        catch (MysqlDaoException e){throw new CommandException(e.getMessage(), e);}
        int numberOfRecords = dao.getNumberOfRecords();
        int numberOfPages = (int)Math.ceil(numberOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("betList", list);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("currentPage", page);
        return "jsp/adminBetPage.jsp";
    }
}
