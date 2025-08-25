<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="ko" style="background-color: #f0f5f5">
 <head>
	 <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/_inc/checkSession.jsp"/>
	 <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/_inc/title.jsp" />
 </head>
<body>
	<div id="popup">
		<form method="post" action="${pageContext.request.contextPath}/insertSharedGroup.do" id="form">
			<input type="hidden" name="inviteCode" id="inviteCode" value="">

			<div class="pop_tit">
			<p class="title">공유 그룹 추가</p>
		</div>
		<div class="pop_cont">
			<ul class="contEdit">
				<li>
				<p class="tit">공유할 그룹</p>
					<p class="cont">
						<select id="gro_Idx" name="groIdx">
							<c:forEach var="group" items="${groupList}">
								<option value="${group.groIdx}">${group.groName}</option>
							</c:forEach>
						</select>
					</p>
				</li>
			</ul>
			<p class="pop_btn"><a href="javascript:window.close();" class="del">취소</a><a href="#" onclick="createInviteCode();" class="comp">추가</a></p>
		</div>
		</form>
	</div>
	<script>
		function createInviteCode() {
			const groIdx = document.getElementById(gro_Idx);
			if(!groIdx){
				alert("그룹을 선택해주세요");
				return;
			}
			fetch("${pageContext.request.contextPath}/createInviteCode.do")
					.then(response => response.text())
					.then(code => {
						document.getElementById("inviteCode").value = code;
						document.getElementById("form").submit();

					})
					.catch(err => {
						alert("코드 생성 중 오류: " + err);
					});
		}
</script>
</body>
</html>
