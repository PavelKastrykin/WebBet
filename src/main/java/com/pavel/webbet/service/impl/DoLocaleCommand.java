package com.pavel.webbet.service.impl;

import com.pavel.webbet.service.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DoLocaleCommand implements ICommand{

    public static final Logger logger = Logger.getLogger(DoLocaleCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String currentLocale = request.getParameter("language");
        session.setAttribute("localeValue", currentLocale);
        String page = request.getParameter("hiddenPageID");
        return page;
    }
}
