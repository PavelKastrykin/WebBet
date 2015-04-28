package com.pavel.webbet.service.impl;

import com.pavel.webbet.dao.mysqldao.impl.UserBeanDao;
import com.pavel.webbet.entity.userbean.UserBean;
import com.pavel.webbet.service.ICommand;

import javax.servlet.http.HttpServletRequest;

public class DoCreateUserEditCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("userLogin");
        UserBeanDao dao = UserBeanDao.getInstance();
        UserBean user = dao.getBeanByLogin(login);
        request.setAttribute("userToEdit", user);
        return "jsp/editUser.jsp";
    }
}
