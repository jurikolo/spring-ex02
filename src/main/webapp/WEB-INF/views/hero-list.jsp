<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>List of Heroes</title>
</head>
<body>
<h1>List of heroes</h1>
<p>Here you can see the list of the heroes.</p>
<table class="table table-striped" border="1px" cellpadding="0" cellspacing="0" >
<thead>
<tr>
<th width="10%">id</th><th width="15%">name</th><th width="10%">type</th><th width="10%">actions</th>
</tr>
</thead>
<tbody>
<c:forEach var="hero" items="${heroes}">
<tr>
	<td>${hero.id}</td>
	<td>${hero.cityId}</td>
	<td>${hero.name}</td>
	<td>${hero.guildId}</td>
	<td>${hero.ally}</td>
	<td>${hero.enemy}</td>
</tr>
</c:forEach>
</tbody>
</table>

<p><a href="${pageContext.request.contextPath}/index.html">Home page</a></p>

</body>
</html>