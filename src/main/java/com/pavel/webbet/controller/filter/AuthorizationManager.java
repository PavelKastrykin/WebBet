package com.pavel.webbet.controller.filter;

import com.pavel.webbet.entity.userbean.UserBean;
import com.pavel.webbet.entity.userbean.UserRole;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AuthorizationManager {

    private static AuthorizationManager manager = new AuthorizationManager();
    public static AuthorizationManager getManager() {return manager; }

    private Map<String, ArrayList<UserRole>> authorizationMap = new HashMap<>();
    private ArrayList<UserRole> adminAccess = new ArrayList<>();
    private ArrayList<UserRole> bookAccess = new ArrayList<>();
    private ArrayList<UserRole> userAccess = new ArrayList<>();

    {
        adminAccess.add(UserRole.ADMIN);
        bookAccess.add(UserRole.ADMIN);
        bookAccess.add(UserRole.BOOK);
        userAccess.add(UserRole.ADMIN);
        userAccess.add(UserRole.BOOK);
        userAccess.add(UserRole.USER);

        authorizationMap.put("blocked.jsp", null);
        authorizationMap.put("errorPage.jsp", null);
        authorizationMap.put("header.jsp", null);
        authorizationMap.put("home.jsp", null);
        authorizationMap.put("loginLogoutHeader", null);
        authorizationMap.put("register.jsp", null);
        authorizationMap.put("index.jsp", null);

        authorizationMap.put("addMatch.jsp", bookAccess);
        authorizationMap.put("adminBetPage.jsp", adminAccess);
        authorizationMap.put("adminHeader.jsp", adminAccess);
        authorizationMap.put("adminUserPage.jsp", adminAccess);
        authorizationMap.put("bookmakerPanel.jsp", bookAccess);
        authorizationMap.put("deleteMatch.jsp", bookAccess);
        authorizationMap.put("editBet.jsp", adminAccess);
        authorizationMap.put("editMatch.jsp", bookAccess);
        authorizationMap.put("editUser.jsp", adminAccess);
        authorizationMap.put("makeBet.jsp", userAccess);
        authorizationMap.put("myBets.jsp", userAccess);
        authorizationMap.put("myBetsHeader.jsp", userAccess);
    }

    public boolean isUserAuthorized(String uri, UserBean user) {
        UserRole role = user.getUserRole();
        ArrayList<UserRole> list = authorizationMap.get(uri);
        if (list == null || list.contains(role)) {
            return true;
        }
        return false;
    }
}
