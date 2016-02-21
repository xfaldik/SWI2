<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<my:layout title="Welcome!">
<jsp:attribute name="body">
    <div class="row">
        <div class="col-xs-12 col-md-6">
            <h2>Pokémon
                <a href="${pageContext.request.contextPath}/pokemon/create" class="btn btn-success btn-sm"><i
                        class="fa fa-plus"></i> New Pokémon</a>

                <a href="${pageContext.request.contextPath}/pokemon/list" class="btn btn-info btn-sm"><i
                        class="fa fa-list"></i> Your Pokémon</a>
            </h2>

            <ul class="pokemon-overview">
                <c:forEach var="pkmn" items="${trainer.pokemon}">
                    <li>
                        <my:pkmn-sprite pokemon="${pkmn}"/>
                    </li>
                </c:forEach>
            </ul>

        </div>
        <div class="col-xs-12 col-md-6">
            <h2>Obtained Badges</h2>

            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th>City</th>
                    <th>Gym Leader</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="badge" items="${badges}">
                    <tr>
                        <td><c:out value="${badge.gym.city}"/></td>
                        <td><c:out value="${badge.gym.leader.fullName}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <p>You have collected <b>${badgeCount} badges</b> so far.
                <c:if test="${badgeCount >= 8}">
                    You can go and try to beat Elite Four and Champion now!
                </c:if>
                <c:if test="${badgeCount < 8}">
                    You need at least 8 badges to face the Elite Four.
                </c:if>
            </p>
        </div>
    </div>

</jsp:attribute>
</my:layout>