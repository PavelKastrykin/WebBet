package com.pavel.webbet.service.impl;

import com.pavel.webbet.dao.mysqldao.MySqlDao;
import com.pavel.webbet.dao.mysqldao.impl.LoginDao;
import com.pavel.webbet.entity.userbean.UserBean;
import com.pavel.webbet.service.ICommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.List;

public class DoLoginCommand implements ICommand{
    @Override
    public String execute(HttpServletRequest request) {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        LoginDao dao = LoginDao.getInstance();
        String query = dao.createQuery(userName, password);
        List<UserBean> resultList = dao.getQueryResult(query);
        HttpSession session = request.getSession(true);
        session.setAttribute("userValue", resultList.get(0));
        return "jsp/home.jsp";

    }
}
