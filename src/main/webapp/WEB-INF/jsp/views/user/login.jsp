<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java" %>

<!doctype html>
<html lang="ko" style="background-color: #f0f5f5">
 <head>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/_inc/title.jsp" />
	</head>
<body>
	<div id="contents">
		<div class="user_box">
			<p class="tit"><a href="/index.do"><img src="${pageContext.request.contextPath}/images/_img/itman_logo.png" alt="아이티맨" /></a></p>

            <form action="/user/authUser.do" name="form" id="form" method="post">
			<ul class="mem">
				<li>
					<p>이메일</p>
					<div><input type="text" id="useremail" name="inputMail"></div>
				</li>
				<li>
					<p>비밀번호</p>
					<div><input type="password" id="userpw" name="inputPw"></div>
				</li>
			</ul>
			<p class="user_btn" ><a href="#" onclick="formSubmit();">로그인</a></p>
</form>
			<p class="mam_btn"><a href="/user/join.do">회원가입</a><a href="/user/findEmail.do">이메일 찾기</a><a href="/user/findPass.do">비밀번호 찾기</a></p>
		</div>
	</div>
	<c:if test="${!empty msg}">
		<script>
			alert("${msg}");
		</script>
	</c:if>
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/_inc/footer.jsp" />

	<script language="javascript">
		function formSubmit(){
			$useremail = $("#useremail").val().trim();
			$userpw = $("#userpw").val().trim();

			if(!$useremail || !$userpw){
				alert("필수 값을 입력해주세요!");
				return false;
			}
			document.forms['form'].submit();
		}
	</script>
</body>

</html>
