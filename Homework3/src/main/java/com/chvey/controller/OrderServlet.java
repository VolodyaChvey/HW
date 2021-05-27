package com.chvey.controller;

import com.chvey.repository.DataBase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class OrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] prods = req.getParameterValues("products");
        String userName = req.getParameter("userName");
        PrintWriter pw = resp.getWriter();
        DataBase db = new DataBase(userName);
        Map<String, Float> products = new HashMap<>();
        for (String prod : prods) {
            if (db.getProducts().containsKey(prod)) {
                products.put(prod, db.getProducts().get(prod));
            }
        }
        float sum = 0;
        for (Float f : products.values()) {
            sum += f;
        }
        pw.println("<body>\n" +
                "    <style>\n" +
                "        .flex-container {\n" +
                "            display: flex;\n" +
                "            justify-content: space-around;\n" +
                "        }\n" +
                "    </style>\n" +
                "    <h2 align=\"center\">Dear " + db.getUserName() + ", your order</h2>\n" +
                "    <div class=\"flex-container\">\n" +
                "        <div>\n" +
                "            <ol>\n");
        for (Map.Entry entry : products.entrySet()) {
            pw.println("<li>  " + entry.getKey() + "  " + entry.getValue() + " $</li>\n");
        }
        pw.println("            </ol>\n" +
                "            <p>Total: $ " + sum + "</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>");
        pw.close();
    }
}
