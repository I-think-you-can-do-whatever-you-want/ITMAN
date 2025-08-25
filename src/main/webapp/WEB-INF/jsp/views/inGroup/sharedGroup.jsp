<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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

	<div id="contents">
		<ul class="tabMenu">
			<li class="active" data-tab="tab1">공유 받은 그룹</li>
			<li data-tab="tab2">나의 요청 현황</li>
		</ul>
		<div id="tab1" class="tabContent active">
		<div class="tit_search">
			<h2>공유 받은 그룹</h2>
		</div>


		<div class="num_list">
			<p class="total">총 <span>${pagination.listCnt}</span>건의 결과가 있습니다.</p>
		</div>

		<div class="Basic">
			<ul class="adminList">
				<li class="tit">
					<p class="num">No</p>
					<p class="cod">공유자</p>
					<p class="tit">그룹명</p>
					<p class="pos">보유 권한</p>
					<p class="editDel">관리</p>
				</li>
				<c:if test="${!empty resultList}">
					<c:forEach var="row" items="${resultList}">
				<li>
				<p class="num">${row.rowNum}</p>
				<p class="cod">${row.divCode}</p>
				<p class="tit">${row.divName}</p>
				<p class="pos">
					<c:choose>
						<c:when test="${row.divYn == 'Y'}">사용</c:when>
						<c:otherwise>사용안함</c:otherwise>
					</c:choose>
				</p>
				<p class="editDel" style="padding: 0;">
					<a href="#" onclick="window.open('/divisionWrite.do?divIdx=${row.divIdx}', 'EditPopUp', 'width=500, height=350, status=no,toolbar=no,scrollbars=no')" class="edit">수정</a>
					<a href="#" onclick="window.open('/confirmDivisionDel.do?divIdx=${row.divIdx}', 'EditPopUp', 'width=500, height=350, status=no,toolbar=no,scrollbars=no')" class="del">삭제</a>
				</p>
				</li>
				</c:forEach>
				</c:if>
				<li>
					<p class="num">1</p>
					<p class="cod">김희숙</p>
					<p class="tit">한국폴리텍 AI융합소프트웨어과</p>
					<p class="pos">
						<c:choose>
							<c:when test="${row.divYn == 'Y'}">사용</c:when>
							<c:otherwise>조회</c:otherwise>
						</c:choose>
					</p>
					<p class="editDel" style="padding: 0;">
						<a href="#" onclick="window.open('/confirmDivisionDel.do?divIdx=${row.divIdx}', 'EditPopUp', 'width=500, height=350, status=no,toolbar=no,scrollbars=no')" class="del">삭제</a>
					</p>
				</li>

                    <c:if test="${pagination.listCnt == 0}">
						<div style="text-align:center; margin-top:20px;">
							일치하는 자료가 없습니다.
						</div>
					</c:if>

			</ul>
		</div>
	</div>
		<div id="tab2" class="tabContent">
			<div class="tit_search">
				<h2>나의 요청 현황</h2>
			</div>


			<div class="num_list">
				<p class="total">총 <span>${pagination.listCnt}</span>건의 결과가 있습니다.</p>
			</div>


			<!-- 글쓰기 버튼-->
			<p class="addContent">
				<a href="#" onclick="window.open('/divisionWrite.do' ,'부서 추가 팝업' ,'width=500, height=335, status=no,toolbar=no,scrollbars=no' )" class="edit"><span></span><span></span><span></span></a></p>

			<div class="Basic">
				<ul class="adminList">
					<li class="tit">
						<p class="num">No</p>
						<p class="cod">처리자</p>
						<p class="tit">그룹명</p>
						<p class="pos">요청상태</p>
						<p class="editDel">관리</p>
					</li>
					<c:if test="${!empty resultList}">
						<c:forEach var="row" items="${resultList}">
							<li>
								<p class="num">${row.rowNum}</p>
								<p class="cod">${row.divCode}</p>
								<p class="tit">${row.divName}</p>
								<p class="pos">
									<c:choose>
										<c:when test="${row.divYn == 'Y'}">사용</c:when>
										<c:otherwise>사용안함</c:otherwise>
									</c:choose>
								</p>
								<p class="editDel" style="padding: 0;">
									<a href="#" onclick="window.open('/divisionWrite.do?divIdx=${row.divIdx}', 'EditPopUp', 'width=500, height=350, status=no,toolbar=no,scrollbars=no')" class="edit">수정</a>
									<a href="#" onclick="window.open('/confirmDivisionDel.do?divIdx=${row.divIdx}', 'EditPopUp', 'width=500, height=350, status=no,toolbar=no,scrollbars=no')" class="del">삭제</a>
								</p>
							</li>
						</c:forEach>
					</c:if>
					<li>
						<p class="num">1</p>
						<p class="cod">최민서</p>
						<p class="tit">한국폴리텍 학생처</p>
						<p class="pos">
							<c:choose>
								<c:when test="${row.divYn == 'Y'}">사용</c:when>
								<c:otherwise>승인 대기</c:otherwise>
							</c:choose>
						</p>
						<p class="editDel" style="padding: 0;">
							<a href="#" onclick="window.open('/divisionWrite.do?divIdx=${row.divIdx}', 'EditPopUp', 'width=500, height=350, status=no,toolbar=no,scrollbars=no')" class="edit">수정</a>
							<a href="#" onclick="window.open('/confirmDivisionDel.do?divIdx=${row.divIdx}', 'EditPopUp', 'width=500, height=350, status=no,toolbar=no,scrollbars=no')" class="del">철회</a>
						</p>
					</li>

					<c:if test="${pagination.listCnt == 0}">
						<div style="text-align:center; margin-top:20px;">
							일치하는 자료가 없습니다.
						</div>
					</c:if>

				</ul>
			</div>
		</div>
	</div>

<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/_inc/footer.jsp" />
<script>
	function formSubmit(){
		document.getElementById('searchForm').submit();
	}
	function changePage(page, range, rangeSize) {
		const form = document.getElementById('searchForm');
		form.page.value = page;
		form.range.value = range;
		form.rangeSize.value = rangeSize;
		form.submit();
	}
	//처음 버튼 이벤트
	function fn_maxPrev() {
		var url = "${pageContext.request.contextPath}/sharedGroup.do";
		url = url + "?page=" + 1;
		url = url + "&range=" + 1;
		location.href = url;	}
	//이전 버튼 이벤트
	function fn_prev(page, range, rangeSize,searchDiv, searchPos, searchSt, searchSort, searchKyeword) {
		var page = (((range - 2) * rangeSize) + 1) <= 1 ? 1 : ((range - 2) * rangeSize) + 1 ;
		var range = (range - 1) <= 1 ? 1 : range - 1;
		var url = "${pageContext.request.contextPath}/sharedGroup.do";
		url = url + "?page=" + page;
		url = url + "&range=" + range;
		location.href = url;	}
	//페이지 번호 클릭
	function fn_pagination(page, range, rangeSize, searchType, keyword) {
		var url = "${pageContext.request.contextPath}/sharedGroup.do";
		url = url + "?page=" + page;
		url = url + "&range=" + range;
		location.href = url;		}
	//다음 버튼 이벤트
	function fn_next(pageCnt, page, range, rangeSize) {
		var page = (parseInt((range * rangeSize)) + 1) >= pageCnt ? pageCnt / rangeSize * 10 : parseInt((range * rangeSize)) + 1 ;
		var range = (parseInt(range) + 1) >= parseInt(pageCnt / rangeSize + 1) ? parseInt(pageCnt / rangeSize + 1) : (parseInt(range) + 1) ;
		var url = "${pageContext.request.contextPath}/sharedGroup.do";
		url = url + "?page=" + page;
		url = url + "&range=" + range;
		location.href = url;	}
	//마지막 버튼 이벤트
	function fn_maxNext(pageCnt, range, rangeSize) {
		var page =  pageCnt / rangeSize * 10;
		var range =    parseInt(pageCnt / rangeSize + 1);
		var url = "${pageContext.request.contextPath}/sharedGroup.do";
		url = url + "?page=" + page;
		url = url + "&range=" + range;
		location.href = url;
	}
</script>
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

