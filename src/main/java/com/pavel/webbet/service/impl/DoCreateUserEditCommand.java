package com.pavel.webbet.service.impl;

import com.pavel.webbet.dao.mysqldao.MysqlDaoException;
import com.pavel.webbet.dao.mysqldao.impl.UserBeanDao;
import com.pavel.webbet.entity.userbean.UserBean;
import com.pavel.webbet.service.CommandException;
import com.pavel.webbet.service.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DoCreateUserEditCommand implements ICommand {

    public static final Logger logger = Logger.getLogger(DoCreateUserEditCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter("userLogin");
        UserBeanDao dao = UserBeanDao.getInstance();
        UserBean user = null;
        try {
            user = dao.getBeanByName(login);
        }
        catch (MysqlDaoException e){throw new CommandException(e.getMessage(), e);}
        request.setAttribute("userToEdit", user);
        return "jsp/editUser.jsp";
    }
}
