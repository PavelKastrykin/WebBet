package com.pavel.webbet.service;

import javax.servlet.http.HttpServletRequest;

public interface ICommand{
    String execute(HttpServletRequest request) throws CommandException;
}
