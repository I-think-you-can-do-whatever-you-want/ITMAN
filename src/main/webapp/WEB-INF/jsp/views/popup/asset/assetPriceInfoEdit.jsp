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
			<p class="title">재고 구매 가격 변경</p>
		</div>
		<div class="pop_cont">
            <form method="post" id="form" action="${pageContext.request.contextPath}/asset/updateAssetPriceInfo.do">
                <input type="hidden" name="assIdx" value="${asset.assIdx}">
			<ul class="contEdit">
				<li>
					<p class="tit">구매 가격</p> <!-- 자산명, 최초 구매일, 가격(원) 등 인풋폼-->
					<p class="cont"><input type="text" id="price" name="price" value="${asset.price}"  placeholder="변경할 구매 가격을 입력하세요."></p>
				</li>
				<!-- 비고란 -->
				<li>
					<p class="tit">비고</p>
					<p class="cont"><input type="text" name="alNote" /></p>
				</li>
			</ul>
            </form>
			<p class="pop_btn"><a href="#" onclick="window.close();" class="del">취소</a><a href="#" onclick="formSubmit();" class="comp">수정</a></p>
		</div>
	</div>
<script>
    function formSubmit(){
		const el = document.getElementById('price');
		const price = el ? el.value.trim() : '';

		if(!price){
			alert("구매 가격을 입력해주세요");
			el.focus();
			return;
		}

		document.forms['form'].submit();

		setTimeout(() => {
			window.opener.location.reload();
			window.close();
		}, 300);
    }

	document.getElementById('price').addEventListener('input', function (e) {
		let value = e.target.value;

		// 숫자만 남기기
		value = value.replace(/[^0-9]/g, '');

		// 천 단위 콤마 추가
		value = value.replace(/\B(?=(\d{3})+(?!\d))/g, ',');

		e.target.value = value;
	});
</script>
</body>
</html>
