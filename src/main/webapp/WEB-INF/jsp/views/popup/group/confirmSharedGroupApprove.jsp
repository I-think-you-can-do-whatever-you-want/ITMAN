<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java" %>

<!doctype html>
<html lang="ko" style="background-color: #f0f5f5">
 <head>
	 <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/_inc/checkSession.jsp"/>
	 <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/_inc/title.jsp" />
	 <style>
		 /* 공통 select 스타일 */
		 select.permission-select {
			 width: 100%;
			 padding: 10px 12px;
			 font-size: 14px;
			 color: #333;
			 border: 1px solid #ccc;
			 border-radius: 6px;
			 background-color: #fff;
			 appearance: none;          /* 기본 화살표 제거 (크로스브라우저) */
			 -webkit-appearance: none;
			 -moz-appearance: none;
			 background-image: url("data:image/svg+xml;utf8,<svg fill='%23666' height='24' viewBox='0 0 24 24' width='24' xmlns='http://www.w3.org/2000/svg'><path d='M7 10l5 5 5-5z'/></svg>");
			 background-repeat: no-repeat;
			 background-position: right 10px center;
			 background-size: 16px 16px;
			 transition: all 0.2s ease-in-out;
		 }

		 /* 옵션 스타일 */
		 select.permission-select option {
			 padding: 8px;
		 }
		 input[type="date"].date-input {
			 width: 100%;
			 padding: 10px 12px;
			 font-size: 14px;
			 color: #333;
			 border: none;
			 border-radius: 4px;
			 background-color: #fff;
			 transition: all 0.2s ease-in-out;
			 box-sizing: border-box;
		 }

		 /* 아이콘 위치 조정 (크롬, 엣지) */
		 input[type="date"].date-input::-webkit-calendar-picker-indicator {
			 cursor: pointer;
			 background: url("data:image/svg+xml;utf8,<svg fill='%23666' xmlns='http://www.w3.org/2000/svg' height='24' width='24'><path d='M7 10h10v2H7zm0 4h7v2H7zm10-8V4h-2v2H9V4H7v2H5v14h14V6z'/></svg>") no-repeat center;
			 background-size: 18px 18px;
			 opacity: 0.7;
			 margin-right: 5px;
		 }

	 </style>
 </head>
<body>
	<div id="popup">
		<div class="pop_tit">
			<p class="title">권한 부여</p>
		</div>
		<div class="pop_cont">
			<form method="post" id="form" action="${pageContext.request.contextPath}/approveSharedGroup.do">
				<input type="hidden" name="reqIdx" value="${request.reqIdx}"/>
				<input type="hidden" name="reqMemIdx" value="${request.reqMemIdx}">

				<ul class="contEdit">
				<li>
					<p class="tit">저장할 이름 <span>*</span></p>
					<p class="cont"><input type="text" id="target_mem_name" name="targetMemName" placeholder="인사처 OOO 과장" value=""></p>
				</li>
				<li>
					<p class="tit">권한 <span>*</span></p>
					<p class="cont">
						<select class="permission-select" name="permMask">
							<option value="1">조회</option>
							<option value="3">조회 | 수정</option>
							<option value="7">조회 | 수정 | 삭제</option>
						</select>
					</p>
				</li>
				<li>
					<p class="tit">만료기간</p>
					<p class="cont"><input type="date" name="validTo" class="date-input"></p>
				</li>
				<li>
					<p class="tit">비고</p>
					<p class="cont"><input type="text" name="note"/></p>
				</li>
			</ul>
			</form>
		</div>
		<div class="pop_tit" style="padding-top: 0">
			<p class="title">요청 정보</p>
		</div>
		<div class="pop_cont">
				<ul class="contEdit">
					<li>
						<p class="tit">요청자</p>
						<p class="cont"><input type="text" value="${request.requesterName}" readonly></p>
					</li>
					<li>
						<p class="tit">요청자 비고</p>
						<p class="cont"><input type="text" value="${request.reqNote}" readonly></p>
					</li>
				</ul>
				<p class="pop_btn"><a href="javascript:window.close();" class="del">취소</a><a href="#" onclick="formSubmit();" class="comp">추가</a></p>
		</div>
	</div>
<script>
    function formSubmit(){
		$target_mem_name = $("#target_mem_name").val().trim();
		if(!$target_mem_name){
			alert("저장할 이름을 입력해주세요");
		}else{
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
