package com.chvey.filters;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

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
        System.out.println(checkbox);
        if (checkbox == null || checkboxSession == null || !checkbox.equals("true") || !checkboxSession.equals("true")) {
            PrintWriter pw = servletResponse.getWriter();
            pw.println("<h2>Oops!</h2>\n");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
