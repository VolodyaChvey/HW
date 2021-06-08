package com.chvey.filters;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "RequestFilter", urlPatterns = {"/servlet2", "/servlet3"})
public class RequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String checkbox = req.getParameter("checkbox");
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        String checkboxSession = (String) session.getAttribute("checkbox");
        if ((checkbox != null && checkbox.equals("true"))
                || (checkboxSession != null && checkboxSession.equals("true"))) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/error.jsp");
            dispatcher.forward(req, (HttpServletResponse) servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
