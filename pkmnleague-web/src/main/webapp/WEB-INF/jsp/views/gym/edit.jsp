<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:layout title="Edit your Gym">
<jsp:attribute name="body">

        <c:if test="${not empty alert_failure}">
            <div class="alert alert-danger" role="alert"><c:out value="${alert_failure}"/></div>
        </c:if>


    <div class="row">
        <form:form method="post" cssClass="form-horizontal" action="${pageContext.request.contextPath}/gym/edit"
                   modelAttribute="editGym">

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="hidden" name="id" value="${editGym.id}"/>
            <div class="form-group">
                <form:label path="city" cssClass="col-sm-2 control-label">City</form:label>
                <div class="col-sm-10">
                    <form:input path="city" id="city" cssClass="form-control" required="required"/>
                    <form:errors path="city" cssClass="error"/>
                </div>
            </div>

            <div class="form-group">
                <form:label path="type" cssClass="col-sm-2 control-label">Type</form:label>
                <div class="col-sm-10">
                    <form:select path="type" cssClass="form-control">
                        <form:options/>
                    </form:select>
                    <form:errors path="type" cssClass="error"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="submit" value="Save" class="btn btn-primary">
                </div>
            </div>

        </form:form>

    </div>
</jsp:attribute>
</my:layout>