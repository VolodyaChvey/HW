package com.chvey.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out= resp.getWriter();
        out.println("   <div align=\"center\">\n" +
                "        <h2>Welcome to Online Shop</h2>\n" +
                "        <form action=\"/Homework3/servlet2\" method=\"POST\">\n" +
                "            <p>\n" +
                "                <input name=\"userName\" required placeholder=\"Enter your name\" style=\"width:150px\">\n" +
                "            </p>\n" +
                "            <p>\n" +
                "                <input type=\"submit\" value=\"Enter\" style=\"width:150px\">\n" +
                "            </p>\n" +
                "        </form>\n" +
                "    </div>");
        out.close();
    }
}
