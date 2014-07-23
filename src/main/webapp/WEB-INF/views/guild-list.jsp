<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>List of Guilds</title>
	<script type="text/javascript" src="<c:url value="/resources/sorttable.js" />"></script>
</head>
<body>
<h1>List of guilds</h1>
<p>Here you can see the list of the guilds. Click on a header to sort data.</p>
<p>
	<a href="${pageContext.request.contextPath}/index.html">Home page</a>
</p>
<table class="table table-striped sortable" border="1px" cellpadding="0" cellspacing="0" >
<thead>
<tr>
<th width="10%">id</th><th width="10%">name</th>
</tr>
</thead>
<tbody>
<c:forEach var="guild" items="${guilds}">
<tr>
	<td><a href="http://the-tale.org/accounts/clans/${guild.id}">${guild.id}</a></td>
	<td>${guild.name}</td>
</tr>
</c:forEach>
</tbody>
</table>

<p><a href="${pageContext.request.contextPath}/index.html">Home page</a></p>

</body>
</html>