<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>List of Heroes</title>
<script type="text/javascript"
	src="<c:url value="/resources/sorttable.js" />"></script>
</head>
<body>
	<h1>List of heroes</h1>
	<p>Here you can see the list of the heroes. Click on a header to
		sort data.</p>
	<p>
		<a href="${pageContext.request.contextPath}/index.html">Home page</a>
	</p>
	<table class="table table-striped sortable" border="1px"
		cellpadding="0" cellspacing="0">
		<thead>
			<tr>
				<th width="10%">id</th>
				<th width="10%">name</th>
				<th width="10%">keeper</th>
				<th width="10%">guild</th>
				<th width="30%">ally</th>
				<th width="30%">enemy</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="hero" items="${heroes}">
				<tr>
					<td><a href="http://the-tale.org/game/heroes/${hero.id}">${hero.id}</a></td>
					<td>${hero.name}</td>
					<td><a href="http://the-tale.org/accounts/${hero.id}">${hero.keeper}</a></td>
					<td><a
						href="http://the-tale.org/accounts/clans/${hero.guild.id}">${hero.guild.name}</a></td>
					<td>
						<c:choose>
							<c:when test="${empty hero.ally.name}"></c:when>
							<c:otherwise>${hero.ally.name}, ${hero.ally.race} ${hero.ally.job} - ${hero.ally.skill}, ${hero.ally.city.name}</c:otherwise>
						</c:choose></td>
					<td>
						<c:choose>
							<c:when test="${empty hero.enemy.name}"></c:when>
							<c:otherwise>${hero.enemy.name},${hero.enemy.race}${hero.enemy.job} - ${hero.enemy.skill}, ${hero.enemy.city.name}</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<p>
		<a href="${pageContext.request.contextPath}/index.html">Home page</a>
	</p>

</body>
</html>