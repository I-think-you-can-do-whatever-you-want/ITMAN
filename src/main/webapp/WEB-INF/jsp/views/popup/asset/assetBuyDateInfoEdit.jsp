<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java" %>

<!doctype html>
<html lang="ko" style="background-color: #f0f5f5">
 <head>
	 <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/_inc/checkSession.jsp"/>
     <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/_inc/title.jsp" />
 </head>
<body>

	<div id="popup">
		<div class="pop_tit">
			<p class="title">최초 구매일 변경</p>
		</div>
		<div class="pop_cont">
            <form method="post" id="form" action="/asset/updateAssetBuyDateInfo.do">
                <input type="hidden" name="assIdx" value="${asset.assIdx}">
			<ul class="contEdit">
				<li>
					<p class="tit">최초 구매일</p>
					<p class="cont"><input id="buyDate" name="buyDate" value="${asset.buyDate}" type="date" placeholder="변경할 최초 구매일을 입력해주세요" required></p>
				</li>

				<li>
					<p class="tit">비고</p>
					<p class="cont"><input type="text" name="alNote" /></p>
				</li>
			</ul>
            </form>
			<p class="pop_btn"><a href="javascript:window.close();" class="del">취소</a><a href="#" onclick="formSubmit();" class="comp">수정</a></p>
		</div>
	</div>
<script>
    function formSubmit(){
		const el = document.getElementById('buyDate');
		const date = el ? el.value.trim() : '';

		if (!date) {
			alert("자산 구매일을 선택해주세요");
			return;
		}

		document.forms['form'].submit();
		setTimeout(() => {
			window.opener.location.reload();
			window.close();
		}, 300);
	}
</script>
</body>
</html>
