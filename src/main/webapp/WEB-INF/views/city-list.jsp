<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Список городов Пандоры</title>
	<script type="text/javascript" src="<c:url value="/resources/sorttable.js" />"></script>
</head>
<body>
<h1>Таблица городов Пандоры</h1>
<p>Ниже приведена таблица городов Пандоры. Нажмите на заголовок колонки для сортировки данных.</p>
<p>
	<a href="${pageContext.request.contextPath}/index.html">На главную</a>
</p>
<table class="table table-striped sortable" border="1px" cellpadding="0" cellspacing="0" >
<thead>
<tr>
<th width="10%">Название</th><th width="10%">Размер</th>
</tr>
</thead>
<tbody>
<c:forEach var="city" items="${cities}">
<tr>
	<td><a href="http://the-tale.org/game/map/places/${city.id}">${city.name}</a></td>
	<td>${city.size}</td>
</tr>
</c:forEach>
</tbody>
</table>

<p><a href="${pageContext.request.contextPath}/index.html">На главную</a></p>

</body>
</html>