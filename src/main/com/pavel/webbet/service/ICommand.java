package com.pavel.webbet.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;

public interface ICommand {
    String execute(HttpServletRequest request);
}
