<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ attribute name="pokemon" required="true" type="cz.fi.muni.pa165.seminar.pkmnleague.dto.PokemonDTO" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<img src="${pageContext.request.contextPath}/img/sprites/${pokemon.speciesId}.png"
     alt="#${pokemon.speciesId} ${pokemon.speciesName}">