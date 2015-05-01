package com.pavel.webbet.service.impl;

import com.pavel.webbet.service.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DoLocaleCommand implements ICommand{
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String currentLocale = request.getParameter("language");
        session.setAttribute("localeValue", currentLocale);
        String page = request.getParameter("hiddenPageID");
        return page;
    }
}
