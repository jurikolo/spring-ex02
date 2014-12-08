<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Таблица героев - не подписчиков</title>
<script type="text/javascript"
	src="<c:url value="/resources/sorttable.js" />"></script>
</head>
<body>
	<h1>Таблица героев - не подписчиков</h1>
	<p>Ниже приведена таблица героев - не подписчиков. Нажмите на заголовок колонки для сортировки данных.</p>
	<p>
		<a href="${pageContext.request.contextPath}/index.html">На главную</a>
	</p>
	<table class="table table-striped sortable" border="1px"
		cellpadding="0" cellspacing="0">
		<thead>
			<tr>
				<th width="10%">Имя</th>
				<th width="10%">Хранитель</th>
				<th width="10%">Гильдия</th>
				<th width="10%">Уровень</th>				
				<th width="20%">Соратник</th>
				<th width="20%">Враг</th>
				<th width="10%">Родной город</th>
				<th width="10%">Полит. акт.</th>
				<th width="10%">Последнее посещение</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="heroToHeroExtraMap" items="${heroToHeroExtraMap}">
				<tr>
					<td><a href="http://the-tale.org/game/heroes/${heroToHeroExtraMap.key.id}">${heroToHeroExtraMap.key.name}</a></td>
					<td><a href="http://the-tale.org/accounts/${heroToHeroExtraMap.key.id}">${heroToHeroExtraMap.key.keeper}</a></td>
					<td><a
						href="http://the-tale.org/accounts/clans/${heroToHeroExtraMap.value.guild.id}">${heroToHeroExtraMap.value.guild.name}</a></td>
					<td>${heroToHeroExtraMap.key.level}</td>
					<td>
						<c:choose>
							<c:when test="${empty heroToHeroExtraMap.value.ally.name}"></c:when>
							<c:otherwise>${heroToHeroExtraMap.value.ally.name}, ${heroToHeroExtraMap.value.ally.race} ${heroToHeroExtraMap.value.ally.job} - ${heroToHeroExtraMap.value.ally.skill}, ${heroToHeroExtraMap.value.allyCity.name}</c:otherwise>
						</c:choose></td>
					<td>
						<c:choose>
							<c:when test="${empty heroToHeroExtraMap.value.enemy.name}"></c:when>
							<c:otherwise>${heroToHeroExtraMap.value.enemy.name}, ${heroToHeroExtraMap.value.enemy.race} ${heroToHeroExtraMap.value.enemy.job} - ${heroToHeroExtraMap.value.enemy.skill}, ${heroToHeroExtraMap.value.enemyCity.name}</c:otherwise>
						</c:choose>
					</td>
					<td><c:choose>
							<c:when test="${empty heroToHeroExtraMap.value.city.id}"></c:when>
							<c:otherwise>${heroToHeroExtraMap.value.city.name}</c:otherwise>
						</c:choose></td>
					<td><c:choose>
							<c:when test="${heroToHeroExtraMap.key.politicallyActive == '0'}">Нет</c:when>
							<c:otherwise>Да</c:otherwise>
						</c:choose></td>
					<td>${heroToHeroExtraMap.key.lastVisitTimeStampFormatted}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<p>
		Общее число не подписчиков: ${heroesCount}
	</p>

	<p>
		<a href="${pageContext.request.contextPath}/index.html">На главную</a>
	</p>
	
	<!-- Yandex.Metrika counter --><script type="text/javascript">(function (d, w, c) { (w[c] = w[c] || []).push(function() { try { w.yaCounter25774070 = new Ya.Metrika({id:25774070, clickmap:true, trackLinks:true, accurateTrackBounce:true}); } catch(e) { } }); var n = d.getElementsByTagName("script")[0], s = d.createElement("script"), f = function () { n.parentNode.insertBefore(s, n); }; s.type = "text/javascript"; s.async = true; s.src = (d.location.protocol == "https:" ? "https:" : "http:") + "//mc.yandex.ru/metrika/watch.js"; if (w.opera == "[object Opera]") { d.addEventListener("DOMContentLoaded", f, false); } else { f(); } })(document, window, "yandex_metrika_callbacks");</script><noscript><div><img src="//mc.yandex.ru/watch/25774070" style="position:absolute; left:-9999px;" alt="" /></div></noscript><!-- /Yandex.Metrika counter -->
	
</body>
</html>