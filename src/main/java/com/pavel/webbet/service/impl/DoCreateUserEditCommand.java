package com.pavel.webbet.service.impl;

import com.pavel.webbet.constant.RequestParameterConstant;
import com.pavel.webbet.constant.UrlConstant;
import com.pavel.webbet.dao.IUserBeanDao;
import com.pavel.webbet.dao.factory.DaoFactory;
import com.pavel.webbet.dao.factory.DaoType;
import com.pavel.webbet.dao.mysql.MysqlDaoException;
import com.pavel.webbet.dao.mysql.impl.UserBeanDao;
import com.pavel.webbet.entity.userbean.UserBean;
import com.pavel.webbet.service.CommandException;
import com.pavel.webbet.service.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DoCreateUserEditCommand implements ICommand {

    public static final Logger logger = Logger.getLogger(DoCreateUserEditCommand.class);
    private static final String ATTRIBUTE_USER_TO_EDIT = "userToEdit";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter(RequestParameterConstant.PARAMETER_USER_LOGIN);
        IUserBeanDao dao = DaoFactory.getDao(DaoType.USER);
        UserBean user = null;
        try {
            user = dao.getBeanByName(login);
        }
        catch (MysqlDaoException e){throw new CommandException(e.getMessage());}
        request.setAttribute(ATTRIBUTE_USER_TO_EDIT, user);
        return UrlConstant.URL_EDIT_USER;
    }
}
