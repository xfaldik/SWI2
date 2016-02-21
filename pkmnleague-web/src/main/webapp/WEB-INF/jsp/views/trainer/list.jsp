<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:layout title="All trainers in the Pokémon World">
<jsp:attribute name="body">

      <table class="table table-bordered">
          <tr>
              <th>Name</th>
              <th>Date of Birth</th>
              <th>Number of Badges</th>
              <th>Gym Leader in City</th>
              <th>Obtained Pokémon</th>
          </tr>
          <c:forEach items="${trainers}" var="trainer">
              <tr>
                  <td>${trainer.fullName}</td>
                  <td><fmt:formatDate value="${trainer.dateOfBirth}" pattern="dd. MM. yyyy"/></td>
                  <td>${trainer.badges.size()}</td>
                  <td>${trainer.gym.city}</td>
                  <td>
                      <c:forEach items="${trainer.pokemon}" var="pokemon">
                          <my:pkmn-icon pokemon="${pokemon}"/>
                      </c:forEach>
                  </td>
              </tr>
          </c:forEach>
      </table>

</jsp:attribute>
</my:layout>