package com.pavel.webbet.service.impl;

import com.pavel.webbet.service.ICommand;
import javax.servlet.http.HttpServletRequest;

public class DoNoSuchCommand implements ICommand{
    @Override
    public String execute(HttpServletRequest request) {
        return null;
    }
}
