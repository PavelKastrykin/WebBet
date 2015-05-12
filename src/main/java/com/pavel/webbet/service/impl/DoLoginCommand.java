package com.pavel.webbet.service.impl;

import com.pavel.webbet.constant.RequestParameterConstant;
import com.pavel.webbet.constant.UrlConstant;
import com.pavel.webbet.dao.IUserBeanDao;
import com.pavel.webbet.dao.factory.DaoFactory;
import com.pavel.webbet.dao.factory.DaoType;
import com.pavel.webbet.dao.mysql.MysqlDaoException;
import com.pavel.webbet.dao.mysql.impl.UserBeanDao;
import com.pavel.webbet.entity.userbean.UserBean;
import com.pavel.webbet.entity.userbean.UserRole;
import com.pavel.webbet.service.CommandException;
import com.pavel.webbet.service.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DoLoginCommand implements ICommand{

    public static final Logger logger = Logger.getLogger(DoLoginCommand.class);
    private static final String PARAMETER_USERNAME = "username";
    private static final String PARAMETER_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) throws CommandException{
        String userName = request.getParameter(PARAMETER_USERNAME);
        String password = request.getParameter(PARAMETER_PASSWORD);
        IUserBeanDao dao = DaoFactory.getDao(DaoType.USER);
        UserBean bean = null;
        try{
            bean = dao.getBeanByNameAndPassword(userName, password);
        }
        catch (MysqlDaoException e){throw new CommandException(e.getMessage());}
        if (bean != null && bean.getUserRole() == UserRole.BLOCKED){
            return UrlConstant.URL_BLOCKED;
        }
        if (bean != null){
            HttpSession session = request.getSession(true);
            session.setAttribute(RequestParameterConstant.SESSION_USER_VALUE, bean);
            return UrlConstant.REQUEST_MATCH_LIST;
        }
        else {
            return UrlConstant.URL_INDEX;
        }
    }
}
