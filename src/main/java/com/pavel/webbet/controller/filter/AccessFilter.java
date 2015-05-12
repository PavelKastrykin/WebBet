package com.pavel.webbet.controller.filter;

import com.pavel.webbet.constant.RequestParameterConstant;
import com.pavel.webbet.constant.UrlConstant;
import com.pavel.webbet.entity.userbean.UserBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccessFilter implements Filter {

    protected ServletContext servletContext;
    private FilterConfig config;
    private static final String DASH = "/";

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
        String uri = request.getRequestURI().substring(request.getRequestURI().lastIndexOf(DASH) + 1);
        AuthorizationManager manager = AuthorizationManager.getManager();
        HttpSession session = request.getSession(true);
        UserBean user = (UserBean)(session.getAttribute(RequestParameterConstant.SESSION_USER_VALUE));

        if (!manager.isUserAuthorized(uri, user)){
            config.getServletContext().getRequestDispatcher(DASH + UrlConstant.URL_INDEX).forward(request, response);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
