package com.pavel.webbet.service.impl;

import com.pavel.webbet.entity.userbean.UserBean;
import com.pavel.webbet.service.CommandStringConstants;
import com.pavel.webbet.service.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DoAddMatchCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) {
        String matchName = request.getParameter("matchName");
        String matchDate = request.getParameter("matchDate");
        HttpSession session = request.getSession(true);
        String userRole = ((UserBean)session.getAttribute("userValue")).getUserRole().toString();
        if ("ADMIN".equals(userRole) || "BOOK".equals(userRole)){
            request.setAttribute("addMatchWarning", CommandStringConstants.VALIDATION_ADMIN_BOOK_WARNING);
            return "jsp/addMatch.jsp";
        }
        if (matchName.equals("") || matchDate.equals("")){
            request.setAttribute("addMatchWarning", CommandStringConstants.EMPTY_FIELDS_WARNING);
            return "jsp/addMatch.jsp";
        }
        return null;
    }
}
