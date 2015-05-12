package com.pavel.webbet.controller;

import com.pavel.webbet.constant.UrlConstant;
import com.pavel.webbet.service.CommandDispatcher;
import com.pavel.webbet.service.CommandException;
import com.pavel.webbet.service.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WebBetController extends HttpServlet{

    public static final Logger logger = Logger.getLogger(WebBetController.class);
    private static final String PARAMETER_COMMAND = "command";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String REQUEST_ENCODING = "UTF-8";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(REQUEST_ENCODING);
        String commandName = request.getParameter(PARAMETER_COMMAND);
        ICommand command = CommandDispatcher.getInstance().getCommand(commandName);
        String page;

        try {
            page = command.execute(request);
        }
        catch (CommandException e){
            page = UrlConstant.URL_ERROR_PAGE;
            request.setAttribute(ERROR_MESSAGE, e.getMessage());
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        if (dispatcher != null){
            dispatcher.forward(request, response);
        }
    }
}
