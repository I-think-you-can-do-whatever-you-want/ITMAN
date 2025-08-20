<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java" %>

<!doctype html>
<html lang="ko">
 <head>
	 <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/_inc/checkSession.jsp"/>
  <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/_inc/title.jsp" />
	 <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/_inc/header.jsp" />
 </head>
<body>
	<div id="contents" class="dashboard">
		<div class="overflow">
			<div class="Basic f_r">
				<div class="base_tit">
					<p class="tit">자산 상태표</p>
					<p class="more"><a href="assetStateList.do"><img src="${pageContext.request.contextPath}/images/_img/more.png" alt="더보기" /></a></p>
				</div>
				<ul class="adminList history">
					<li class="tit">
						<p class="admin">상태값</p>
						<p class="date">수량</p>
					</li>
					<c:if test="${!empty stateList}">
						<c:forEach var="assetState" items="${stateList}">
							<li>
								<p class="admin">${assetState.staName}</p>
								<p class="date">${assetState.staCnt}</p>
							</li>
						</c:forEach>
					</c:if>
				</ul>
			</div>

			<div class="Basic f_l">
				<div class="base_tit mt">
					<p class="tit">자산 히스토리</p>
					<p class="more"><a href="assetHistory.do"><img src="../../../../images/_img/more.png" alt="더보기" /></a></p>
				</div>
				<ul class="adminList history">
					<li class="tit">
						<p class="admin">처리자</p>
						<p class="date">일시</p>
						<p class="tit">자산명</p>
						<p class="stat02">활동 구분</p>
						<p class="type">분류</p>
						<p class="change">내용</p>
					</li>
					<c:if test="${!empty historyList}">
						<c:forEach var="history" items="${historyList}">
							<li>
								<p class="admin">${history.memName}</p>
								<p class="date">${history.regDate}</p>
								<p class="tit">${history.assName}</p>
								<p class="stat02">${history.alType}</p>
								<div class="typeChange">
									<p class="type">${history.alCat}</p>
									<p class="change">${history.alCont}</p>
								</div>
							</li>
						</c:forEach>
					</c:if>
				</ul>
			</div>
		</div>
	</div>

	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/_inc/footer.jsp" />

	<!-- 자산 상태표 차트 -->
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);
    </script>
</body>
</html>
