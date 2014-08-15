<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Список хранителей с правом голоса</title>
</head>
<body>
	<h1>Список хранителей с правом голоса</h1>
	<p>Ниже приведён список городов и хранители с правом голоса в городе.</p>

	<c:forEach var="cityToHeroMap" items="${cityToHeroMap}">
		<b><a href="http://the-tale.org/game/map/places/${cityToHeroMap.key.id}">${cityToHeroMap.key.name}</a></b>
		<ol>
			<c:forEach var="heroGuild" items="${cityToHeroMap.value}">
				<li><a href="http://the-tale.org/accounts/${heroGuild.hero.id}">${heroGuild.hero.name}</a>
					<c:choose>
						<c:when test="${not empty heroGuild.guild}"><a href="http://the-tale.org/accounts/clans/${heroGuild.guild.id}">${heroGuild.guild.name}</a></c:when>
					</c:choose></li>
			</c:forEach>
		</ol>
	</c:forEach>

	<p>
		<a href="${pageContext.request.contextPath}/vote/list.html">Список хранителей с правом голоса</a>
	</p>
	<p>
		<a href="${pageContext.request.contextPath}/index.html">На главную</a>
	</p>
	
	<!-- Yandex.Metrika counter --><script type="text/javascript">(function (d, w, c) { (w[c] = w[c] || []).push(function() { try { w.yaCounter25774070 = new Ya.Metrika({id:25774070, clickmap:true, trackLinks:true, accurateTrackBounce:true}); } catch(e) { } }); var n = d.getElementsByTagName("script")[0], s = d.createElement("script"), f = function () { n.parentNode.insertBefore(s, n); }; s.type = "text/javascript"; s.async = true; s.src = (d.location.protocol == "https:" ? "https:" : "http:") + "//mc.yandex.ru/metrika/watch.js"; if (w.opera == "[object Opera]") { d.addEventListener("DOMContentLoaded", f, false); } else { f(); } })(document, window, "yandex_metrika_callbacks");</script><noscript><div><img src="//mc.yandex.ru/watch/25774070" style="position:absolute; left:-9999px;" alt="" /></div></noscript><!-- /Yandex.Metrika counter -->
	
</body>
</html>