<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>online-shop</title>
    <style>
        <%@include file="/WEB-INF/CSS/style.css"%>
    </style>
</head>
<body>
                      <div class="flex-container">
                         <div>
                                <h2>Hello ${userName.name}!</h2>
                              <label>
                                   Make you order
                                </label>
                               <ol id="ol"></ol>
                              <div>
                                    <select id="sel" style="width:125px; ">

          <c:forEach var="entry" items="${products}">
                    <option value="${entry.key}">${entry.key} ( ${entry.value} ) $</option>
          </c:forEach>
                       </select>
                          </div>
                                 <p>
                                   <button id="btn" onclick="addList()">Ad item</button>
                                  <button id="submit" form="form" type="submit" name="products" >Submit</button>
                                <form id="form" action="/Homework3/servlet3" method="POST">
                                   </form>
                              </p>
                             </div>
                          <script>
                                 let listKey = [];
                               let listText = [];
                                let sel = document.getElementById("sel");
                               let ol = document.getElementById("ol");
                              let submit = document.getElementById("submit");
                              function addList() {
                                    listKey.push(sel.value);
                                    listText.push(sel.options[sel.selectedIndex].text.replace(/[\\(\\)]/g,' '));
                                      while (ol.firstChild) {
                                         ol.removeChild(ol.firstChild);
                                   }
                                     for (var i in listText) {
                                        var li = document.createElement("li");
                                       li.textContent = listText[i]
                                       ol.appendChild(li);
                                        submit.setAttribute("value", listKey);
                                  }
                                }
                             </script>
                         </div>

</body>