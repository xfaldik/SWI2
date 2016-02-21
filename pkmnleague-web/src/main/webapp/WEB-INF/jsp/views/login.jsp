<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Login | Pokémon League</title>

    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/favicon.png">

    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/pkmnleague.css" rel="stylesheet">
</head>

<body class="login-page">

<div class="container">

    <form name="f" action="${pageContext.request.contextPath}/login" method="post" class="form-signin">
        <h2 class="form-signin-heading">Please sign in</h2>

        <c:if test="${not empty alert_success}">
            <div class="alert alert-success" role="alert"><c:out value="${alert_success}"/></div>
        </c:if>

        <c:if test="${param.role ne null}">
            <div class="alert alert-info">
                Your role was changed to Gym Leader. You need to sign in again to see the changes.
            </div>
        </c:if>

        <c:if test="${param.error ne null}">
            <div class="alert alert-danger">
                Invalid username or password.
            </div>
        </c:if>

        <c:if test="${param.logout ne null}">
            <div class="alert alert-info">
                You have been logged out.
            </div>
        </c:if>


        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="email" id="username" name="username" class="form-control" placeholder="Email address" required
               autofocus>
        <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        <br>
        <a href="${pageContext.request.contextPath}/registration" class="btn btn-lg btn-default btn-block">Sign up</a>
        <br>

    </form>

</div>

</body>
</html>

