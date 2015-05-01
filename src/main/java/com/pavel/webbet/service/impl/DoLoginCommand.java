package com.pavel.webbet.service.impl;

import com.pavel.webbet.dao.mysqldao.impl.UserBeanDao;
import com.pavel.webbet.entity.userbean.UserBean;
import com.pavel.webbet.entity.userbean.UserRole;
import com.pavel.webbet.service.ICommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DoLoginCommand implements ICommand{
    @Override
    public String execute(HttpServletRequest request) {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        UserBeanDao dao = UserBeanDao.getInstance();
        UserBean bean = dao.getBeanByLoginAndPassword(userName, password);
        if (bean != null && bean.getUserRole() == UserRole.BLOCKED){
            return "jsp/blocked.jsp";
        }
        if (bean != null){
            HttpSession session = request.getSession(true);
            session.setAttribute("userValue", bean);
            return "jsp/home.jsp";
        }
        else {
            return "index.jsp";
        }
    }
}
