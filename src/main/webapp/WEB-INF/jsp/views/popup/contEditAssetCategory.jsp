<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java" %>

<!doctype html>
<html lang="ko" style="background-color: #f0f5f5">
 <head>
	 <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/_inc/checkSession.jsp"/>
  <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/_inc/title.jsp" />
 </head>
<body>
<c:url value="/checkDuplicateAssCat.do" var="checkDuplicateUrl"/>

	<div id="popup">
		<div class="pop_tit">
			<p class="title">자산 분류 수정 팝업</p>
		</div>
		<div class="pop_cont">
			<form method="post" id="form" action="${pageContext.request.contextPath}/updateAssetCategory.do">
			<input type="hidden" name="assCatIdx" value="${assetCategory.assCatIdx}" />
			<ul class="contEdit">
				<li>
					<p class="tit">자산 분류명<span>*</span></p>
					<p class="cont"><input type="text" id="ass_cat_name" name="assCatName" placeholder="자산 분류 명을 입력해주세요." value="${assetCategory.assCatName}"></p>
				</li>
				<li>
					<p class="tit">코드번호<span>*</span></p>
					<p class="cont"><input type="text" id="ass_cat_code" name="assCatCode" placeholder="자산 분류 코드 번호를 입력해 주세요. ex)000" value="${assetCategory.assCatCode}"></p>
				</li>
				
				<!-- 비고란 -->
				<li>
					<p class="tit">비고</p>
					<p class="cont"><input type="text" name="slNote" value="${assetCategory.slNote}" /></p>
				</li>
			</ul>
			<p class="pop_btn"><a href="javascript:window.close();" class="del">취소</a><a href="#" onclick="checkDuplicate()" class="comp">수정</a></p>
			</form>
		</div>
	</div>
<script>

	async function checkDuplicate(){
		$ass_cat_name_empty = $("#ass_cat_name").val().trim();
		$ass_cat_code_empty = $("#ass_cat_code").val().trim();

		if(!$ass_cat_name_empty || !$ass_cat_code_empty){
			alert("필수 값을 입력해주세요!");
		}else{
			try {
				const resp = await fetch("${checkDuplicateUrl}", {
					method: "POST",
					headers: {
						"Content-Type": "application/x-www-form-urlencoded"
					},
					body: new URLSearchParams({
						assCatIdx: document.querySelector("input[name='assCatIdx']").value.trim() ,
						assCatCode: document.querySelector("input[name='assCatCode']").value.trim()

					}),
				});

				const text = await resp.text();
				const code = parseInt(text.trim(), 10);

				if (code === 0) {
					alert("이미 존재하는 분류 코드입니다.");
					$("#ass_cat_code").focus();
					return;
				}

				if (code === 1) {
					document.forms['form'].submit();

					setTimeout(() => {
						window.opener.location.reload();
						window.close();
					} ,300)
				}
			} catch (e) {
				alert("오류가 발생했습니다.");
				console.error(e);
			}

		}


	}

</script>
</body>
</html>
