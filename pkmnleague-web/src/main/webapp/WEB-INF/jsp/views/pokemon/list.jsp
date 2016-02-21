<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:layout title="Your Pokémon">
<jsp:attribute name="body">

    <p>
        <a class="btn btn-success" href="${pageContext.request.contextPath}/pokemon/create">
            <i class="fa fa-plus"></i> Add a Pokémon
        </a>
    </p>


      <table class="table table-bordered">
          <tr>
              <th>Icon</th>
              <th>Species ID</th>
              <th>Pokémon</th>
              <th>Nickname</th>
              <th>Types</th>
              <th>Level</th>
              <th></th>
          </tr>
          <c:forEach items="${pokemons}" var="pokemon">
              <tr>
                  <td class="col-xs-1"><my:pkmn-icon pokemon="${pokemon}"/></td>
                  <td class="col-xs-1"><c:out value="${pokemon.speciesId}"/></td>
                  <td class="col-xs-2"><c:out value="${pokemon.speciesName}"/></td>
                  <td class="col-xs-2"><c:out value="${pokemon.nickname}"/></td>
                  <td class="col-xs-2"><my:pkmn-type pokemon="${pokemon}"/></td>
                  <td class="col-xs-1"><c:out value="${pokemon.level}"/></td>
                  <td>
                      <a href="${pageContext.request.contextPath}/pokemon/edit/${pokemon.id}" class="btn btn-sm btn-primary"><i class="fa fa-pencil"></i> Rename</a>
                      <a href="${pageContext.request.contextPath}/pokemon/level-up/${pokemon.id}" class="btn btn-sm btn-info"><i class="fa fa-arrow-up"></i> Level up</a>
                      <a href="${pageContext.request.contextPath}/pokemon/release/${pokemon.id}" class="btn btn-sm btn-danger"><i class="fa fa-remove"></i> Release</a>
                  </td>
              </tr>
          </c:forEach>
      </table>

</jsp:attribute>
</my:layout>