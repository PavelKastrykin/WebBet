package com.pavel.webbet.entity.userbean;

public class UserBean {
    private int userID;
    private String login;
    private String password;
    private String name;
    private UserRole userRole;

    public int getUserID() {
        return userID;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
