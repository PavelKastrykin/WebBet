package com.pavel.webbet.service.impl;

import com.pavel.webbet.dao.mysqldao.MysqlDaoException;
import com.pavel.webbet.dao.mysqldao.impl.UserBeanDao;
import com.pavel.webbet.entity.userbean.UserBean;
import com.pavel.webbet.entity.userbean.UserRole;
import com.pavel.webbet.service.CommandException;
import com.pavel.webbet.service.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DoLoginCommand implements ICommand{

    public static final Logger logger = Logger.getLogger(DoLoginCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws CommandException{
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        UserBeanDao dao = UserBeanDao.getInstance();
        UserBean bean = null;
        try{
            bean = dao.getBeanByNameAndPassword(userName, password);
        }
        catch (MysqlDaoException e){throw new CommandException(e.getMessage(), e);}
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
