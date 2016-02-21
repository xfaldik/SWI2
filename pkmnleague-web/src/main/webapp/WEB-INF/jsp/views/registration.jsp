<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Registration | Pokémon League</title>

    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/favicon.png">

    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/pkmnleague.css" rel="stylesheet">
</head>

<body class="login-page">

<div class="container">

    <form:form name="f" action="${pageContext.request.contextPath}/registration" method="post" modelAttribute="trainer" cssClass="form-signin">
        <h2 class="form-signin-heading">Registration</h2>

        <c:if test="${not empty alert_failure}">
            <div class="alert alert-danger" role="alert"><c:out value="${alert_failure}"/></div>
        </c:if>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <label for="email">E-mail</label>
        <form:input type="email" id="email" class="form-control" placeholder="Email address" required="required" autofocus="autofocus" path="email" />
        <form:errors path="email" cssClass="error"/>

        <label for="password">Password</label>
        <form:password cssClass="form-control" placeholder="Password" required="required" path="password" />
        <form:errors path="password" cssClass="error"/>

        <label for="fullName">Your name</label>
        <form:input id="fullName" name="fullName" cssClass="form-control" placeholder="Your name, including surname" required="required" path="fullName" />
        <form:errors path="fullName" cssClass="error"/>

        <label for="dateOfBirth">Date of birth</label>
        <form:input type="date" id="dateOfBirth" name="dateOfBirth" cssClass="form-control" placeholder="Your date of birth"
               required="required" style="height:44px" path="dateOfBirth"/>
        <form:errors path="dateOfBirth" cssClass="error"/>

        <br>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
        <br>
        <br>

    </form:form>

</div>

</body>
</html>

