package com.pavel.webbet.service.impl;

import com.pavel.webbet.dao.mysqldao.impl.UserBeanDao;
import com.pavel.webbet.entity.userbean.UserBean;
import com.pavel.webbet.entity.userbean.UserRole;
import com.pavel.webbet.service.ICommand;

import javax.servlet.http.HttpServletRequest;

public class DoConfirmEditUserCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) {
        UserBean user = new UserBean();
        user.setLogin(request.getParameter("userLogin"));
        user.setUserRole(UserRole.valueOf(request.getParameter("role")));
        UserBeanDao dao = UserBeanDao.getInstance();
        dao.update(user);
        return "webBetController?command=CREATE_USER_LIST";

    }
}
