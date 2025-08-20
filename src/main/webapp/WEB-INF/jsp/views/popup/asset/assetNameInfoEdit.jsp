<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java" %>

<!doctype html>
<html lang="ko" style="background-color: #f0f5f5">
 <head>
	 <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/_inc/checkSession.jsp"/>
	 <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/_inc/title.jsp" />
 </head>
<body>

	<div id="popup">
		<form method="post" action="${pageContext.request.contextPath}/asset/updateAssetNameInfo.do" id="form">
			<input type="hidden" name="assIdx" value="${asset.assIdx}" />
		<div class="pop_tit">
			<p class="title">자산명 변경</p>
		</div>
		<div class="pop_cont">
			<ul class="contEdit">
				<li>
					<p class="tit">자산명</p>
					<p class="cont"><input type="text" id="newName" name="assName" value="${asset.assName}"></p>
				</li>
				<!-- 비고란 -->
				<li>
					<p class="tit">비고</p>
					<p class="cont"><input name="alNote" type="text" /></p>
				</li>
			</ul>
			<p class="pop_btn"><a href="javascript:window.close();" class="del">취소</a><a href="#" onclick="formSubmit();" class="comp">수정</a></p>
		</div>
		</form>
	</div>
	<script>
    function formSubmit(){
        // $("#form").submit();
		document.forms['form'].submit();

		setTimeout(() => {
			window.opener.location.reload();
			window.close();
		}, 300);
	}
</script>
</body>
</html>
