<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java" %>

<!doctype html>
<html lang="ko">
 <head>
	 <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/itman/_inc/checkSession.jsp"/>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/itman/_inc/title.jsp"/>
	 <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/itman/_inc/header.jsp"/>
 </head>
<body id="mypage">
	<div id="contents">
		<div class="mypage_box">
			<h2><a href="/myPage.do">그룹관리</a></h2>
			<ul class="groupEdit">
				<li><a onclick="window.open('/addGroup.do', '그룹생성팝업', 'width=500, height=345')" href="#none" class="addBox">그룹생성하기</a></li>
				<c:if test="${!empty resultList}">
					<c:forEach var="group" items="${resultList}">
						<li>
							<p class="name">${group.groName}</p>
							<p class="btn">
								<a onclick="window.open('/editGroup.do?groIdx=${group.groIdx}', 'EditPopUp', 'width=500, height=500, status=no,toolbar=no,scrollbars=no')" class="edit">수정</a>
								<a onclick="window.open('/confirmGroupDel.do?groIdx=${group.groIdx}', 'EditPopUp', 'width=500, height=500, status=no,toolbar=no,scrollbars=no')" class="del">삭제</a>
							</p>
						</li>
					</c:forEach>
				</c:if>
			</ul>
		</div>
	</div>
	<? include "../_inc/footer.php"; ?>
</body>
<script language="javascript">
</script>
</html>


