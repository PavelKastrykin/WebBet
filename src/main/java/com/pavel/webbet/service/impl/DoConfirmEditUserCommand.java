package com.pavel.webbet.service.impl;

import com.pavel.webbet.dao.mysqldao.MysqlDaoException;
import com.pavel.webbet.dao.mysqldao.impl.UserBeanDao;
import com.pavel.webbet.entity.userbean.UserBean;
import com.pavel.webbet.entity.userbean.UserRole;
import com.pavel.webbet.service.CommandException;
import com.pavel.webbet.service.ICommand;

import javax.servlet.http.HttpServletRequest;

public class DoConfirmEditUserCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException{
        UserBean user = new UserBean();
        user.setLogin(request.getParameter("userLogin"));
        user.setUserRole(UserRole.valueOf(request.getParameter("role")));
        UserBeanDao dao = UserBeanDao.getInstance();
        try{
            dao.update(user);
        }
        catch (MysqlDaoException e){throw new CommandException(e.getMessage(), e);}
        return "webBetController?command=CREATE_USER_LIST";

    }
}
