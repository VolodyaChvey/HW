package com.chvey;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class ServletTwo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        DataBase db = new DataBase(userName);
        PrintWriter out = resp.getWriter();
        out.println("    <div align=\"center\">\n" +
                "        <h2>Hello " + db.getUserName() + "!</h2>\n" +
                "            <label>\n" +
                "                Make you order\n" +
                "            </label>\n" +
                "        <form action=\"/Homework3/servlet3\" method=\"GET\">\n" +
                "           <input type=\"hidden\" name=\"userName\" value=\"" + db.getUserName() + "\">" +
                "            <p>\n" +
                "                <select required multiple name=\"products\"  style=\"width:150px\">\n");
        for (Map.Entry entry : db.getProducts().entrySet()) {
            out.println("<option value=\"" + entry.getKey() + "\">" + entry.getKey() +
                    " (" + entry.getValue() + " $)</option>\n");
        };
        out.println("             </select>\n" +
                "            </p>\n" +
                "            <p>\n" +
                "                <input type=\"submit\" value=\"Submit\" style=\"width:150px\">\n" +
                "            </p>\n" +
                "        </form>\n" +
                "    </div>");
        out.close();
    }
}
