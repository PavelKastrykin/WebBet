package com.pavel.webbet.service.impl;

import com.pavel.webbet.service.CommandException;
import com.pavel.webbet.service.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DoNoSuchCommand implements ICommand{

    public static final Logger logger = Logger.getLogger(DoNoSuchCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws CommandException{
        logger.info("Jsp" + request.getRequestURI() + "sends illegal command");
        throw new CommandException("Error while processing request");
    }
}
