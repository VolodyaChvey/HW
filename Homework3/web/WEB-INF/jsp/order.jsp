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
    <h2>Dear ${userName.name}, your order</h2>
    <div class="flex-container">
        <div>
            <ol>
                <c:forEach var="product" items="${priceOrder}">
                    <li>${product.getName()}  ${product.getPrice()}  $</li>
                </c:forEach>
            </ol>
            <p>Total: $  ${totalPrice} </p>
        </div>
    </div>
</body>
</html>