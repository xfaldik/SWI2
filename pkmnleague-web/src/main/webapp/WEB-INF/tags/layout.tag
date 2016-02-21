<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="body" fragment="true" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><c:out value="${title} | "/>Pokémon League</title>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/favicon.png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pkmnleague.css">
    <jsp:invoke fragment="head"/>
</head>
<body>
<nav class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}">Pokémon League</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/pokemon/list">Your Pokémon</a></li>
            </ul>
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/gym/list">Available Gyms</a></li>
            </ul>
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/trainer/list">Other Trainers</a></li>
            </ul>
            <c:if test="${pageContext.request.isUserInRole('ROLE_TRAINER')}">
                <ul class="nav navbar-nav">
                    <li><a href="${pageContext.request.contextPath}/gym/create">Create your Gym</a></li>
                </ul>
            </c:if>
            <c:if test="${pageContext.request.isUserInRole('ROLE_GYMLEADER')}">
                <ul class="nav navbar-nav">
                    <li><a href="${pageContext.request.contextPath}/gym/badge">Hand out Badge</a></li>
                </ul>
            </c:if>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${pageContext.request.contextPath}/logout"><i class="fa fa-power-off"></i> Log out</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">

    <c:if test="${not empty title}">
        <div class="page-header">
            <h1><c:out value="${title}"/></h1>
        </div>
    </c:if>

    <c:if test="${not empty authenticatedUser}">
        <div class="row">
            <div class="col-xs-6 col-sm-8 col-md-9 col-lg-10"></div>
            <div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <c:out value="${authenticatedUser.fullName}"/>
                    </div>
                </div>
            </div>
        </div>
    </c:if>

    <c:if test="${not empty alert_danger}">
        <div class="alert alert-danger" role="alert">
            <span class="fa fa-exclamation-triangle" aria-hidden="true"></span>
            <c:out value="${alert_danger}"/></div>
    </c:if>
    <c:if test="${not empty alert_info}">
        <div class="alert alert-info" role="alert"><c:out value="${alert_info}"/></div>
    </c:if>
    <c:if test="${not empty alert_success}">
        <div class="alert alert-success" role="alert"><c:out value="${alert_success}"/></div>
    </c:if>
    <c:if test="${not empty alert_warning}">
        <div class="alert alert-warning" role="alert"><c:out value="${alert_warning}"/></div>
    </c:if>

    <jsp:invoke fragment="body"/>

    <footer class="footer">
        <p>
            Dominik Hanák, Oldřich Faldík, Pavel Kouřil, Zuzana Goldmannová 2015
            <br>
            Pokémon and all respective names are Trademark and Copyright of Nintendo 1996-2015
        </p>
    </footer>

</div>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>