<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java" %>

<!doctype html>
<html lang="ko">
 <head>
	 <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/_inc/checkSession.jsp"/>
  <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/_inc/title.jsp" />
	 <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/_inc/header.jsp" />
	 <style>
		 .tabMenu {
			 list-style:none;
			 padding:0;
			 margin:0 0 20px;
			 display:flex;
			 border-bottom:2px solid #ddd;
		 }
		 .tabMenu li {
			 padding:10px 20px;
			 cursor:pointer;
			 border:1px solid #ddd;
			 border-bottom:none;
			 background:#f5f5f5;
			 margin-right:5px;
		 }
		 .tabMenu li.active {
			 background:#fff;
			 font-weight:bold;
		 }
		 .tabContent { display:none; }
		 .tabContent.active { display:block; }

	 </style>
 </head>
<body>
<div id="contents" class="dashboard">

	<!-- 탭 메뉴 -->
	<ul class="tabMenu">
		<li class="active" data-tab="tab1">나의 공유</li>
		<li data-tab="tab2">받은 공유</li>
	</ul>

	<!-- 탭 콘텐츠 1 : 나의 공유 -->
	<div id="tab1" class="tabContent active">
		<div class="overflow">
			<div class="Basic f_r">
				<div class="base_tit">
					<p class="tit">나의 공유 그룹</p>
					<p class="more"><a href="myShareGroup.do"><img src="../../../../images/_img/more.png" alt="더보기" /></a></p>
				</div>
				<!-- 기존 '나의 공유 그룹' 리스트 -->
				<ul class="adminList history">
					<li class="tit">
						<p class="admin">그룹명</p>
						<p class="date">공유 코드</p>
					</li>
					<c:if test="${!empty mySharedGroupList}">
						<c:forEach var="group" items="${mySharedGroupList}">
							<li>
								<p class="admin">${group.groName}</p>
								<p class="date">${group.inviteCode}</p>
							</li>
						</c:forEach>
					</c:if>
				</ul>
			</div>

			<div class="Basic f_l">
				<div class="base_tit mt">
					<p class="tit">나의 공유 히스토리</p>

				</div>
				<!-- 기존 '나의 공유 히스토리' 리스트 -->
				<ul class="adminList history">
					<li class="tit">
						<p class="admin">대상자</p>
						<p class="date">일시</p>
						<p class="stat02">그룹명</p>
						<p class="type">활동 구분</p>
						<p class="change">내용</p>
					</li>
					<c:if test="${!empty myShareHistoryList}">
						<c:forEach var="history" items="${myShareHistoryList}">
							<li>
								<p class="admin">${history.targetMemName}</p>
								<p class="date">${history.actionAt}</p>
								<p class="stat02">${history.groName}</p>
								<div class="typeChange">
									<p class="type">${history.eventType}</p>
									<p class="change">${history.note}</p>
								</div>
							</li>
						</c:forEach>
					</c:if>
				</ul>
			</div>
		</div>
	</div>

	<!-- 탭 콘텐츠 2 : 받은 공유 -->
	<div id="tab2" class="tabContent">
		<div class="overflow">
			<div class="Basic f_r">
				<div class="base_tit">
					<p class="tit">공유 받은 그룹</p>
					<p class="more"><a href="sharedGroup.do"><img src="../../../../images/_img/more.png" alt="더보기" /></a></p>
				</div>
				<!-- 기존 '공유 받은 그룹' 리스트 -->
				<ul class="adminList history">
					<li class="tit">
						<p class="admin">그룹명</p>
						<p class="date">공유 코드</p>
					</li>
					<c:if test="${!empty mySharedPermissionList}">
						<c:forEach var="group" items="${mySharedPermissionList}">
							<li>
								<p class="admin">${group.groName}</p>
								<p class="date">${group.validTo}</p>
							</li>
						</c:forEach>
					</c:if>

				</ul>
			</div>

			<div class="Basic f_l">
				<div class="base_tit mt">
					<p class="tit">공유 히스토리</p>
				</div>
				<!-- 기존 '공유 히스토리' 리스트 -->
				<ul class="adminList history">
					<li class="tit">
						<p class="admin">처리자</p>
						<p class="date">일시</p>
						<p class="stat02">그룹명</p>
						<p class="type">활동 구분</p>
						<p class="change">내용</p>
					</li>
					<c:if test="${!empty sharedHistory}">
						<c:forEach var="history" items="${sharedHistory}">
							<li>
								<p class="admin">${history.ownerName}</p>
								<p class="date">${history.actionAt}</p>
								<p class="stat02">${history.groName}</p>
								<div class="typeChange">
									<p class="type">${history.eventType}</p>
									<p class="change">${history.note}</p>
								</div>
							</li>
						</c:forEach>
					</c:if>
				</ul>
			</div>
		</div>
	</div>

</div>


<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/_inc/footer.jsp" />
<script>
	document.addEventListener('DOMContentLoaded', function(){
		const tabs = document.querySelectorAll('.tabMenu li');
		const contents = document.querySelectorAll('.tabContent');

		tabs.forEach(tab => {
			tab.addEventListener('click', function(){
				const target = this.getAttribute('data-tab');

				// 탭 active 토글
				tabs.forEach(t => t.classList.remove('active'));
				this.classList.add('active');

				// 콘텐츠 active 토글
				contents.forEach(c => {
					c.classList.remove('active');
					if(c.id === target) c.classList.add('active');
				});
			});
		});
	});
</script>

</body>
</html>
