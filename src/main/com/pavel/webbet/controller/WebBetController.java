package com.pavel.webbet.controller;

import com.pavel.webbet.dao.mysqldao.connectionpool.ConnectionPool;
import com.pavel.webbet.dao.mysqldao.connectionpool.ConnectionPoolException;
import com.pavel.webbet.service.CommandDispatcher;
import com.pavel.webbet.service.ICommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class WebBetController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter("command");
        ICommand command = CommandDispatcher.getInstance().getCommand(commandName);
        String page = command.execute(request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        if (dispatcher != null){
            dispatcher.forward(request, response);
        }
    }
}
