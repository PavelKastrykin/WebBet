package com.pavel.webbet.controller.filter;

import com.pavel.webbet.constant.RequestParameterConstant;
import com.pavel.webbet.entity.userbean.UserBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccessFilter implements Filter {

    protected ServletContext servletContext;
    private FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
        servletContext = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String uri = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        AuthorizationManager manager = AuthorizationManager.getManager();
        HttpSession session = request.getSession(true);
        UserBean user = (UserBean)(session.getAttribute(RequestParameterConstant.SESSION_USER_VALUE));

        if (uri.equals("/") || uri.equals("index.jsp") || uri.equals("login.jsp") || uri.equals("register.jsp")
                || uri.equals("home.jsp")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (user == null) {
            config.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        if (!manager.isUserAuthorized(uri, user)){
            config.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
