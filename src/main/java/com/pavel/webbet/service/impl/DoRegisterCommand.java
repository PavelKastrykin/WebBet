package com.pavel.webbet.service.impl;

import com.mysql.jdbc.StringUtils;
import com.pavel.webbet.constant.UrlConstant;
import com.pavel.webbet.dao.IUserBeanDao;
import com.pavel.webbet.dao.factory.DaoFactory;
import com.pavel.webbet.dao.factory.DaoType;
import com.pavel.webbet.dao.mysql.MysqlDaoException;
import com.pavel.webbet.entity.userbean.UserBean;
import com.pavel.webbet.service.CommandException;
import com.pavel.webbet.constant.CommandConstant;
import com.pavel.webbet.service.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DoRegisterCommand implements ICommand {

    public static final Logger logger = Logger.getLogger(DoRegisterCommand.class);
    private static final String PARAMETER_REGISTER_LOGIN = "registerLogin";
    private static final String PARAMETER_REGISTER_PASSWORD = "registerPassword";
    private static final String PARAMETER_REGISTER_CONFIRM = "registerConfirm";
    private static final String PARAMETER_REGISTER_NAME = "registerName";
    private static final String PARAMETER_REGISTER_WARNING = "registerWarning";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter(PARAMETER_REGISTER_LOGIN);
        String password = request.getParameter(PARAMETER_REGISTER_PASSWORD);
        String confirm = request.getParameter(PARAMETER_REGISTER_CONFIRM);
        String realName = request.getParameter(PARAMETER_REGISTER_NAME);

        if (StringUtils.isNullOrEmpty(login) || StringUtils.isNullOrEmpty(password) ||
                StringUtils.isNullOrEmpty(confirm) || StringUtils.isNullOrEmpty(realName)){
            request.setAttribute(PARAMETER_REGISTER_WARNING, CommandConstant.EMPTY_FIELDS_WARNING);
            return UrlConstant.URL_REGISTER;
        }

        if (!password.equals(confirm)){
            request.setAttribute(PARAMETER_REGISTER_WARNING, CommandConstant.PASSWORD_CONFIRM_WARNING);
            return UrlConstant.URL_REGISTER;
        }

        IUserBeanDao dao = DaoFactory.getDao(DaoType.USER);
        UserBean checkLogin = null;
        try {
            checkLogin = dao.getBeanByName(login);
        }
        catch (MysqlDaoException e){throw new CommandException(e.getMessage(), e);}
        if (checkLogin == null){
            UserBean newUserBean = new UserBean();
            newUserBean.setLogin(login);
            newUserBean.setPassword(password);
            newUserBean.setName(realName);
            try {
                dao.insert(newUserBean);
            }
            catch (MysqlDaoException e){throw new CommandException(e.getMessage(), e);}
            request.setAttribute(PARAMETER_REGISTER_WARNING, "project.empty");
            return UrlConstant.URL_INDEX;
        }
        else {
            request.setAttribute(PARAMETER_REGISTER_WARNING, CommandConstant.SAME_LOGIN_WARNING);
            return UrlConstant.URL_REGISTER;
        }
    }
}
