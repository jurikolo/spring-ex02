<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Таблица советников Пандоры</title>
	<script type="text/javascript" src="<c:url value="/resources/sorttable.js" />"></script>
</head>
<body>
	<h1>Таблица советников Пандоры</h1>
	<p>Ниже приведена таблица советников Пандоры. Нажмите на заголовок колонки для сортировки данных.</p>
	<p>
		<a href="${pageContext.request.contextPath}/index.html">На главную</a>
	</p>
	<table class="table table-striped sortable" border="1px" cellpadding="0"
		cellspacing="0">
		<thead>
			<tr>
				<th width="11%">Имя</th>
				<th width="9%">Профессия</th>
				<th width="8%">Раса</th>
				<th width="9%">Навык</th>
				<th width="10%">Число соратников</th>
				<th width="10%">Число врагов</th>
				<th width="10%">Влиятельность</th>
				<th width="10%">Город</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="council" items="${councils}">
				<tr>
					<td>${council.name}</td>
					<td>${council.job}</td>
					<td>${council.race}</td>
					<td>${council.skill}</td>
					<td>${council.allies}</td>
					<td>${council.enemies}</td>
					<td>${council.influence}</td>
					<td>${council.city.name}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<p>
		<a href="${pageContext.request.contextPath}/index.html">На главную</a>
	</p>

</body>
</html>