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
        if ((checkbox != null && checkbox.equals("true"))
                || (checkboxSession != null && checkboxSession.equals("true"))) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            PrintWriter pw = servletResponse.getWriter();
            pw.println("    <style>\n" +
                    "        .flex-container {\n" +
                    "            display: flex;\n" +
                    "            justify-content: space-around;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "    <div class=\"flex-container\">\n" +
                    "        <div align=\"left\">\n" +
                    "            <h2>Oops!</h2>\n" +
                    "            <p>You shouldn't be here</p>\n" +
                    "            <p>Please, agree with the terms of service first.</p>\n" +
                    "            <p>\n" +
                    "                <a href=\"/Homework3/\">Start page</a>\n" +
                    "            </p>\n" +
                    "        </div>\n" +
                    "    </div>");

        }
    }

    @Override
    public void destroy() {

    }
}
