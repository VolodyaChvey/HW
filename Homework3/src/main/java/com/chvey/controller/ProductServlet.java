package com.chvey.controller;

import com.chvey.domain.User;
import com.chvey.repository.ProductsRepository;
import com.chvey.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class ProductServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        User user = userService.createOrGet(userName);
        Map<String, Double> priceList = ProductsRepository.getProducts();
        PrintWriter out = resp.getWriter();
        out.println("<div align=\"center\">\n" +
                "        <h2>Hello " + user.getName() + "!</h2>\n" +
                "            <label>\n" +
                "                Make you order\n" +
                "            </label>\n" +
                "        <form action=\"/Homework3/servlet3\" method=\"POST\">\n" +
                "           <input type=\"hidden\" name=\"userName\" value=\"" + user.getName() + "\">" +
                "            <p>\n" +
                "                <select required multiple name=\"products\"  style=\"width:150px\">\n");
        for (Map.Entry entry : priceList.entrySet()) {
            out.printf("<option value=\"%s\">%s (%s $)</option>", entry.getKey(), entry.getKey(), entry.getValue());
        }
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
