package com.pavel.webbet.controller;

import com.pavel.webbet.service.CommandDispatcher;
import com.pavel.webbet.service.CommandException;
import com.pavel.webbet.service.ICommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WebBetController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter("command");
        ICommand command = CommandDispatcher.getInstance().getCommand(commandName);
        String page = null;
        try {
            page = command.execute(request);
        }
        catch (CommandException e){
            page = "jsp/errorPage.jsp";
            PrintWriter out = response.getWriter();
            out.println(e.getMessage());

        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        if (dispatcher != null){
            dispatcher.forward(request, response);
        }
    }
}
