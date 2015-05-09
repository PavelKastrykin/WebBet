package com.pavel.webbet.service.impl;

import com.pavel.webbet.service.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DoLocaleCommand implements ICommand{

    public static final Logger logger = Logger.getLogger(DoLocaleCommand.class);
    private static final String PARAMETER_LANGUAGE = "language";
    private static final String SESSION_LOCALE_VALUE = "localeValue";
    private static final String PARAMETER_HIDDEN_PAGE_ID = "hiddenPageID";

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String currentLocale = request.getParameter(PARAMETER_LANGUAGE);
        session.setAttribute(SESSION_LOCALE_VALUE, currentLocale);
        String page = request.getParameter(PARAMETER_HIDDEN_PAGE_ID);
        return page;
    }
}
