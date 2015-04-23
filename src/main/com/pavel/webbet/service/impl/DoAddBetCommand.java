package com.pavel.webbet.service.impl;

import com.pavel.webbet.service.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DoAddBetCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String a = request.getParameter("moneySum");
        String b = request.getParameter("selectedBetNum");
        String c = request.getParameter("selectedBetCoef");
        //String d = session.
        return null;
    }
}
