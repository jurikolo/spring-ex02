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
	<p>Ниже приведён список городов и хранители с правом голоса в
		городе.</p>
	<p>
		<a href="${pageContext.request.contextPath}/index.html">На главную</a>
	</p>
	<table class="table table-striped sortable" border="1px"
		cellpadding="0" cellspacing="0">
		<thead>
			<tr>
				<th width="10%">Название</th>
				<th width="10%">Количество голосующих</th>
				<th width="10%">Список хранителей, имеющих голос</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="mapCityVotes" items="${mapCityVotes}">
				<tr>
					<td><a href="http://the-tale.org/game/map/places/${mapCityVotes.key.id}">${mapCityVotes.key.name}</a></td>
					<td>${mapCityVotes.value}</td>
					<td><a href="citylist.html?id=${mapCityVotes.key.id}">тырк</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<p>
		<a href="${pageContext.request.contextPath}/index.html">На главную</a>
	</p>

	<!-- Yandex.Metrika counter -->
	<script type="text/javascript">
		(function(d, w, c) {
			(w[c] = w[c] || []).push(function() {
				try {
					w.yaCounter25774070 = new Ya.Metrika({
						id : 25774070,
						clickmap : true,
						trackLinks : true,
						accurateTrackBounce : true
					});
				} catch (e) {
				}
			});
			var n = d.getElementsByTagName("script")[0], s = d
					.createElement("script"), f = function() {
				n.parentNode.insertBefore(s, n);
			};
			s.type = "text/javascript";
			s.async = true;
			s.src = (d.location.protocol == "https:" ? "https:" : "http:")
					+ "//mc.yandex.ru/metrika/watch.js";
			if (w.opera == "[object Opera]") {
				d.addEventListener("DOMContentLoaded", f, false);
			} else {
				f();
			}
		})(document, window, "yandex_metrika_callbacks");
	</script>
	<noscript>
		<div>
			<img src="//mc.yandex.ru/watch/25774070"
				style="position: absolute; left: -9999px;" alt="" />
		</div>
	</noscript>
	<!-- /Yandex.Metrika counter -->

</body>
</html>