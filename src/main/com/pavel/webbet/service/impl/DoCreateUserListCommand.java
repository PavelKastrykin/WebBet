package com.pavel.webbet.service.impl;

import com.pavel.webbet.dao.mysqldao.impl.BetDao;
import com.pavel.webbet.dao.mysqldao.impl.UserBeanDao;
import com.pavel.webbet.entity.bet.BetBean;
import com.pavel.webbet.entity.userbean.UserBean;
import com.pavel.webbet.service.ICommand;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DoCreateUserListCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) {
        int page = 1;
        int recordsPerPage = 10;
        if (request.getParameter("page") != null){
            page = Integer.parseInt(request.getParameter("page"));
        }
        UserBeanDao dao = UserBeanDao.getInstance();
        List<UserBean> list = dao.getAllUsersList((page - 1) * recordsPerPage, recordsPerPage);
        int numberOfRecords = dao.getNumberOfRecords();
        int numberOfPages = (int)Math.ceil(numberOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("userList", list);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("currentPage", page);
        return "jsp/adminUserPage.jsp";
    }
}
