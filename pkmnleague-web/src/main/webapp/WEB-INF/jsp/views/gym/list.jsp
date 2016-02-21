<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<my:layout title="Available Gyms">
<jsp:attribute name="body">

    <c:if test="${pageContext.request.isUserInRole('ROLE_TRAINER')}">
                <p><a class="btn btn-success" href="${pageContext.request.contextPath}/gym/create"><i class="fa fa-plus"></i>
        Estabilish your own Gym</a></p>
            </c:if>
    <c:if test="${pageContext.request.isUserInRole('ROLE_GYMLEADER')}">
                <p><a class="btn btn-primary" href="${pageContext.request.contextPath}/gym/edit"><i class="fa fa-edit"></i>
        Edit your own Gym</a></p>
    </c:if>

      <table class="table table-bordered">
          <tr>
              <th>Gym Leader</th>
              <th>Type</th>
              <th>Location</th>
              <th>Beaten?</th>
          </tr>
          <c:forEach items="${gyms}" var="gym">
              <c:if test="${!gym.leader.equals(user)}">
                  <tr>
                      <td><c:out value="${gym.leader.fullName}"/></td>
                      <td>
                          <img src="${pageContext.request.contextPath}/img/types/${fn:toLowerCase(gym.type)}.png"
                               alt="${gym.type}" title="${gym.type}">
                      </td>
                      <td><c:out value="${gym.city}"/></td>
                      <td>
                          <c:if test="${user.getBeatenGyms().contains(gym)}"><i class="fa fa-check"></i></c:if>
                      </td>
                  </tr>
              </c:if>
          </c:forEach>
      </table>
</jsp:attribute>
</my:layout>