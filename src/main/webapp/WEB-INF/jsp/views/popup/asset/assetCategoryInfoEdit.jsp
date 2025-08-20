<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java" %>

<!doctype html>
<html lang="ko" style="background-color: #f0f5f5">
 <head>
	 <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/_inc/checkSession.jsp"/>
	 <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/_inc/title.jsp" />
 </head>
<body>

	<div id="popup">
		<form method="post" action="${pageContext.request.contextPath}/asset/updateAssetCategoryInfo.do" id="form">
			<input type="hidden" name="assIdx" value="${asset.assIdx}" />
			<input type="hidden" id="assCatName" name="assCatName" value="${asset.assCatName}" />
		<div class="pop_tit">
			<p class="title">자산 분류 변경</p>
		</div>
		<div class="pop_cont">
			<ul class="contEdit">
				<li>
				<p class="tit">자산 분류</p><!-- 분류, 상태, 위치, 구매처 등 셀렉 폼-->
					<p class="cont">
						<select id="ass_cat_idx" name = "assCatIdx">
							<option value="">분류선택</option>
							<c:forEach var="c" items="${categories}">
								<option value="${c.assCatIdx}" name="assCatIdx" ${asset.assCatIdx== c.assCatIdx ? 'selected' : ''} data-code="${c.assCatCode}">${c.assCatName}</option>
							</c:forEach>
						</select>
					</p>
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
			$ass_cat_idx_empty = $("#ass_cat_idx").val().trim();
			if(!$ass_cat_idx_empty){
				alert("자산 분류를 선택해주세요.")
			} else {
				document.forms['form'].submit();

				setTimeout(() => {
					window.opener.location.reload();
					window.close();
				}, 300);
			}
		}
</script>
</body>
</html>
