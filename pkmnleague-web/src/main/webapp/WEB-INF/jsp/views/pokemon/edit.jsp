<%-- 
    Document   : edit
    Created on : 18.12.2015, 23:15:05
    Author     : olda
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:layout title="Edit a Pokémon">
<jsp:attribute name="body">

       <c:if test="${not empty alert_failure}">
           <div class="alert alert-danger" role="alert"><c:out value="${alert_failure}"/></div>
       </c:if>

<form:form method="post" action="${pageContext.request.contextPath}/pokemon/edit" modelAttribute="editPokemon"
           cssClass="form-horizontal">


    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="hidden" name="id" value="${editPokemon.id}"/>

    

    <div class="form-group">
        <form:label path="nickname" cssClass="col-sm-2 control-label">Nickname</form:label>
        <div class="col-sm-10">
            <form:input path="nickname" id="nickname" cssClass="form-control"/>
            <form:errors path="nickname" cssClass="error"/>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <input type="submit" value="Save" class="btn btn-primary">
        </div>
    </div>

</form:form>
</jsp:attribute>
</my:layout>