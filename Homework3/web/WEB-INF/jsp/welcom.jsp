<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>online-shop</title>
    <style>
        <%@include file="/WEB-INF/CSS/style.css"%>
    </style>
</head>
<body>
    <h2>Welcome to Online Shop</h2>
    <div class="flex-container">
        <div>
            <form action="/Homework3/servlet2" method="POST">
                <p>
                    <input name="userName" placeholder="Enter your name" style="width:150px" required>
                </p>
                <P>
                    <input type="checkbox" name="checkbox" value="true" required checked>
                    I agree with the terms of service
                </P>
                <p>
                    <input type="submit" value="Enter" style="width:150px">
                </p>
            </form>
        </div>
    </div>
</body>
</html>