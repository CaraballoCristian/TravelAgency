<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="assets/styles/style.css">
        <title>Login</title>
    </head>
    <body>
        <div class="login-container">

            <form id="login-form" action="SvLogin" method="POST">
                <div class="login-title-box">
                    <h2>Sign in</h2>
                </div>
                <div class="login-content-box">
                    <input type="text" name="username" placeholder="Username...">
                    <input type="password" name="password" placeholder="Password...">
                    <input type="hidden" name="action" value="login">
                    <input type="submit" id="login-submit" value="SIGN IN">
                </div>
            </form>

        </div>   
    </body>
</html>
