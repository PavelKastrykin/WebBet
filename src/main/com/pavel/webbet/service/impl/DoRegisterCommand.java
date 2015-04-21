package com.pavel.webbet.service.impl;

import com.pavel.webbet.dao.mysqldao.impl.UserBeanDao;
import com.pavel.webbet.entity.userbean.UserBean;
import com.pavel.webbet.service.CommandStringConstants;
import com.pavel.webbet.service.ICommand;

import javax.servlet.http.HttpServletRequest;

public class DoRegisterCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("registerLogin");
        String password = request.getParameter("registerPassword");
        String confirm = request.getParameter("registerConfirm");
        String realName = request.getParameter("registerName");

        if (login == "" || password=="" || confirm=="" || realName==""){
            request.setAttribute("registerWarning", CommandStringConstants.EMPTY_FIELDS_WARNING);
            return "jsp/register.jsp";
        }

        if (!password.equals(confirm)){
            request.setAttribute("registerWarning", CommandStringConstants.PASSWORD_CONFIRM_WARNING);
            return "jsp/register.jsp";
        }

        UserBeanDao dao = UserBeanDao.getInstance();
        UserBean checkLogin = dao.getBeanByLogin(login);
        if (checkLogin == null){
            UserBean newUserBean = new UserBean();
            newUserBean.setLogin(login);
            newUserBean.setPassword(password);
            newUserBean.setName(realName);
            dao.insert(newUserBean);
            request.setAttribute("registerWarning", "project.empty");
            return "index.jsp";
        }
        else {
            request.setAttribute("registerWarning", CommandStringConstants.SAME_LOGIN_WARNING);
            return "jsp/register.jsp";
        }
    }
}
