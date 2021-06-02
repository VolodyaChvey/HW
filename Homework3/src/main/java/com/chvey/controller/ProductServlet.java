package com.chvey.controller;

import com.chvey.domain.User;
import com.chvey.repository.ProductsRepository;
import com.chvey.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        HttpSession session = req.getSession();
        String userName = req.getParameter("userName");
        String checkbox = req.getParameter("checkbox");
        User user = userService.createOrGet(userName);
        session.setAttribute("userName", user);
        session.setAttribute("checkbox", checkbox);
        Map<String, Double> priceList = ProductsRepository.getProducts();
        PrintWriter out = resp.getWriter();
        out.println("<style>\n" +
                "        .flex-container {\n" +
                "            display: flex;\n" +
                "            justify-content: space-around;\n" +
                "        }\n" +
                "        \n" +
                "        h2 {\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "    </style>\n" +
                "    <div class=\"flex-container\">\n" +
                "        <div>\n" +
                "            <h2>Hello " + user.getName() + "!</h2>\n" +
                "            <label>\n" +
                "                Make you order\n" +
                "            </label>\n" +
                "            <ol id=\"ol\"></ol>\n" +
                "            <div>\n" +
                "                <select id=\"sel\" style=\"width:125px; \">\n");
        for (Map.Entry entry : priceList.entrySet()) {
            out.printf("<option value=\"%s\">%s (%s $)</option>", entry.getKey(), entry.getKey(), entry.getValue());
        }
        out.println("             </select>\n" +
                "            </div>\n" +
                "            <p>\n" +
                "                <button id=\"btn\" onclick=\"addList()\">Ad item</button>\n" +
                "                <button id=\"submit\" form=\"form\" type=\"submit\" name=\"products\" >Submit</button>\n" +
                "                <form id=\"form\" action=\"/Homework3/servlet3\" method=\"POST\">\n" +
                "                </form>\n" +
                "            </p>\n" +
                "        </div>\n" +
                "        <script>\n" +
                "            let listKey = [];\n" +
                "            let listText = [];\n" +
                "            let sel = document.getElementById(\"sel\");\n" +
                "            let ol = document.getElementById(\"ol\");\n" +
                "            let submit = document.getElementById(\"submit\");\n" +
                "            function addList() {\n" +
                "                listKey.push(sel.value);\n" +
                "                listText.push(sel.options[sel.selectedIndex].text.replace(/[\\(\\)]/g,' '));\n" +
                "                while (ol.firstChild) {\n" +
                "                    ol.removeChild(ol.firstChild);\n" +
                "                }\n" +
                "                for (var i in listText) {\n" +
                "                    var li = document.createElement(\"li\");\n" +
                "                    li.textContent = listText[i] \n" +
                "                    ol.appendChild(li);\n" +
                "                    submit.setAttribute(\"value\", listKey);\n" +
                "                }\n" +
                "            }\n" +
                "        </script>\n" +
                "    </div>");
        out.close();
    }
}
