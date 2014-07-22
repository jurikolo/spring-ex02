<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>List of Councils</title>
	<script type="text/javascript" src="<c:url value="/resources/sorttable.js" />"></script>
</head>
<body>
	<h1>List of councils</h1>
	<p>Here you can see the list of the councils. Click on a header to sort data.</p>
	<p>
		<a href="${pageContext.request.contextPath}/index.html">Home page</a>
	</p>
	<table class="table table-striped sortable" border="1px" cellpadding="0"
		cellspacing="0">
		<thead>
			<tr>
				<th width="10%">id</th>
				<th width="10%">name</th>
				<th width="10%">job</th>
				<th width="10%">race</th>
				<th width="10%">skill</th>
				<th width="10%">allies</th>
				<th width="10%">enemies</th>
				<th width="10%">influence</th>
				<th width="10%">city</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="council" items="${councils}">
				<tr>
					<td>${council.id}</td>
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
		<a href="${pageContext.request.contextPath}/index.html">Home page</a>
	</p>

</body>
</html>