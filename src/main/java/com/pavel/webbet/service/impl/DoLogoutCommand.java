package com.pavel.webbet.service.impl;

import com.pavel.webbet.service.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DoLogoutCommand implements ICommand {

    public static final Logger logger = Logger.getLogger(DoLogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "index.jsp";
    }
}
