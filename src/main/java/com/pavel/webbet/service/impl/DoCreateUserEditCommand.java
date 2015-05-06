package com.pavel.webbet.service.impl;

import com.pavel.webbet.dao.mysqldao.MysqlDaoException;
import com.pavel.webbet.dao.mysqldao.impl.UserBeanDao;
import com.pavel.webbet.entity.userbean.UserBean;
import com.pavel.webbet.service.CommandException;
import com.pavel.webbet.service.ICommand;

import javax.servlet.http.HttpServletRequest;

public class DoCreateUserEditCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter("userLogin");
        UserBeanDao dao = UserBeanDao.getInstance();
        UserBean user = null;
        try {
            user = dao.getBeanByLogin(login);
        }
        catch (MysqlDaoException e){throw new CommandException(e.getMessage(), e);}
        request.setAttribute("userToEdit", user);
        return "jsp/editUser.jsp";
    }
}
