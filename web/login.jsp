<%-- 
    Document   : login
    Created on : 14 janv. 2021, 01:22:15
    Author     : Toufiq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Bootstrap Example</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
        <script src="scripte/jquery-3.5.1.min.js" type="text/javascript"></script>
        <script src="scripte/test.js" type="text/javascript"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
        <link href="scripte/test.scss.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <div class="container">
            <<div class="background-wrap">
                <div class="background"></div>
            </div>

            <form id="accesspanel" action="logincontroler" method="post">
                <h1 id="litheader">AECEND</h1>
                <div class="inset">
                    <p>
                        <input type="text" name="username" id="email" placeholder="Email address">
                    </p>
                    <p>
                        <input type="password" name="password" id="password" placeholder="Access code">
                    </p>
                    <div style="text-align: center;">
                        <div class="checkboxouter">
                            <input type="checkbox" name="rememberme" id="remember" value="Remember">
                            <label class="checkbox"></label>
                        </div>
                        <label for="remember">Remember me for 14 days</label>
                    </div>
                    <input class="loginLoginValue" type="hidden" name="service" value="login" />
                </div>
                <p class="p-container">
                    <input type="submit" name="Login" id="go" value="Authorize">
                </p>
            </form>
        </div>

    </body>
</html>

