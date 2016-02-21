<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:layout title="Add a Pokémon">
<jsp:attribute name="body">

       <c:if test="${not empty alert_failure}">
           <div class="alert alert-danger" role="alert"><c:out value="${alert_failure}"/></div>
       </c:if>

<form:form method="post" action="${pageContext.request.contextPath}/pokemon/create" modelAttribute="createPokemon"
           cssClass="form-horizontal">


    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <div class="form-group">
        <form:label path="speciesId" cssClass="col-sm-2 control-label">Species ID</form:label>
        <div class="col-sm-10">
            <form:input path="speciesId" value="1" type="number" minpath="speciesId" min="1" max="649" id="speciesId" cssClass="form-control" required="required" />
            <form:errors path="speciesId" cssClass="error"/>
        </div>
    </div>

    <div class="form-group">
        <form:label path="speciesName" cssClass="col-sm-2 control-label">Species name</form:label>
        <div class="col-sm-10">
            <form:input path="speciesName" id="speciesName" cssClass="form-control" required="required" />
            <form:errors path="speciesName" cssClass="error"/>
        </div>
    </div>

    <div class="form-group">
        <form:label path="nickname" cssClass="col-sm-2 control-label">Nickname</form:label>
        <div class="col-sm-10">
            <form:input path="nickname" id="nickname" cssClass="form-control"/>
            <form:errors path="nickname" cssClass="error"/>
        </div>
    </div>

    <div class="form-group">
        <form:label path="level" cssClass="col-sm-2 control-label">Level</form:label>
        <div class="col-sm-10">
            <form:input path="level" value="1" type="number" minpath="speciesId" min="1" max="100" id="level" cssClass="form-control" required="required"  />
            <form:errors path="level" cssClass="error"/>
        </div>
    </div>

    <div class="form-group">
        <form:label path="primaryType" cssClass="col-sm-2 control-label">Primary type</form:label>
        <div class="col-sm-10">
            <form:select path="primaryType" cssClass="form-control">
                <form:options/>
            </form:select>
            <form:errors path="primaryType" cssClass="error"/>
        </div>
    </div>

    <div class="form-group">
        <form:label path="secondaryType" cssClass="col-sm-2 control-label">Secondary type</form:label>
        <div class="col-sm-10">
            <form:select path="secondaryType" cssClass="form-control">
                <form:option value="">&nbsp;</form:option>
                <form:options/>
            </form:select>
            <form:errors path="secondaryType" cssClass="error"/>
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