package com.pavel.webbet.service.impl;

import com.pavel.webbet.constant.RequestParameterConstant;
import com.pavel.webbet.constant.UrlConstant;
import com.pavel.webbet.dao.ICommonDao;
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

public class DoConfirmEditUserCommand implements ICommand {

    public static final Logger logger = Logger.getLogger(DoConfirmEditUserCommand.class);
    private static final String PARAMETER_USER_ROLE = "role";

    @Override
    public String execute(HttpServletRequest request) throws CommandException{
        UserBean user = new UserBean();
        user.setLogin(request.getParameter(RequestParameterConstant.PARAMETER_USER_LOGIN));
        user.setUserRole(UserRole.valueOf(request.getParameter(PARAMETER_USER_ROLE)));
        IUserBeanDao dao = DaoFactory.getDao(DaoType.USER);
        try{
            dao.updateBean(user);
        }
        catch (MysqlDaoException e){throw new CommandException(e.getMessage(), e);}
        return UrlConstant.REQUEST_USER_LIST;

    }
}
