package com.pavel.webbet.controller.filter;

import com.pavel.webbet.entity.userbean.UserBean;
import com.pavel.webbet.entity.userbean.UserRole;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AuthorizationManager {

    private static AuthorizationManager manager = new AuthorizationManager();
    public static AuthorizationManager getManager() {return manager; }

    private static ResourceBundle bundle = ResourceBundle.getBundle("properties.project");

    public boolean isUserAuthorized(String uri, UserBean user) {

        String ADMIN_ACCESS = bundle.getString("access.admin");
        String BOOKMAKER_ACCESS = bundle.getString("access.bookmaker");
        String USER_ACCESS = bundle.getString("access.user");
        StringBuilder access = new StringBuilder();
        UserRole role = null;
        if (user != null){
            role = user.getUserRole();
        }
        else {
            access.append(ADMIN_ACCESS).append(BOOKMAKER_ACCESS).append(USER_ACCESS);
            if (access.toString().contains(uri)){
                return false;
            }
            else {return true;}
        }
        switch (role){
            case ADMIN:
                return true;
            case BOOKMAKER:
                access.append(ADMIN_ACCESS);
                if (access.toString().contains(uri)){
                    return false;
                }
                return true;
            case USER:
                access.append(ADMIN_ACCESS).append(BOOKMAKER_ACCESS);
                if (access.toString().contains(uri)){
                    return false;
                }
                return true;
            default:
                access.append(ADMIN_ACCESS).append(BOOKMAKER_ACCESS).append(USER_ACCESS);
                if (access.toString().contains(uri)){
                    return false;
                }
                return true;
        }
    }
}
