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

<!-- Yandex.Metrika counter --><script type="text/javascript">(function (d, w, c) { (w[c] = w[c] || []).push(function() { try { w.yaCounter25774070 = new Ya.Metrika({id:25774070, clickmap:true, trackLinks:true, accurateTrackBounce:true}); } catch(e) { } }); var n = d.getElementsByTagName("script")[0], s = d.createElement("script"), f = function () { n.parentNode.insertBefore(s, n); }; s.type = "text/javascript"; s.async = true; s.src = (d.location.protocol == "https:" ? "https:" : "http:") + "//mc.yandex.ru/metrika/watch.js"; if (w.opera == "[object Opera]") { d.addEventListener("DOMContentLoaded", f, false); } else { f(); } })(document, window, "yandex_metrika_callbacks");</script><noscript><div><img src="//mc.yandex.ru/watch/25774070" style="position:absolute; left:-9999px;" alt="" /></div></noscript><!-- /Yandex.Metrika counter -->

</body>

</html>