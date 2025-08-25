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
			<li class="active" data-tab="tab1">나의 공유 그룹</li>
			<li data-tab="tab2">그룹 공유 현황</li>
			<li data-tab="tab3">받은 요청 현황</li>
		</ul>
		<div id="tab1" class="tabContent active">

			<div class="tit_search">
				<h2>나의 공유 그룹</h2>
			</div>


			<div class="num_list">
				<p class="total">총 <span>${mySharedGroupListCnt}</span>건의 결과가 있습니다.</p>
			</div>

			<!-- 글쓰기 버튼-->
			<p class="addContent">
				<a href="#" onclick="window.open('/writeGroupShare.do' ,'공유 그룹 추가 팝업' ,'width=500, height=335, status=no,toolbar=no,scrollbars=no' )" class="edit"><span></span><span></span><span></span></a></p>

			<div class="Basic">
				<ul class="adminList">
					<li class="tit">
						<p class="num">No</p>
						<p class="cod">그룹명</p>
						<p class="tit">공유 코드</p>
						<p class="tit">공유 수</p>
						<p class="pos">공유일시</p>
						<p class="editDel">관리</p>
					</li>
					<c:if test="${!empty mySharedGroupList}">
						<c:forEach var="row" items="${mySharedGroupList}">
							<li>
								<p class="num">${row.rowNum}</p>
								<p class="cod">${row.groName}</p>
								<p class="tit">${row.inviteCode}</p>
								<p class="tit">${row.shareCnt}</p>
								<p class="pos">${row.regDate}</p>
								<p class="editDel" style="padding: 0;">
									<a href="#" onclick="window.open('/confirmDivisionDel.do', 'EditPopUp', 'width=500, height=350, status=no,toolbar=no,scrollbars=no')" class="del">삭제</a>
								</p>
							</li>
						</c:forEach>
					</c:if>
					<c:if test="${empty mySharedGroupList}">
						<div style="text-align:center; margin-top:20px;">
							일치하는 자료가 없습니다.
						</div>
					</c:if>

				</ul>
			</div>
		</div>
		<div id="tab2" class="tabContent">

		<div class="tit_search">
			<h2>그룹 공유 현황</h2>
			<form  id="searchForm" name="searchForm" method="get" action="${pageContext.request.contextPath}/myShareGroup.do" onsubmit="this.page.value=1; this.range.value=1;">
				<input type="hidden" id="page"      name="page"      value="${pagination.page}" />
				<input type="hidden" id="range"     name="range"     value="${pagination.range}" />
				<input type="hidden" id="rangeSize" name="rangeSize" value="${pagination.rangeSize}" />

				<p class="list_search">
					<select name="pagination.searching.searchCondition" onchange="this.form.submit()">
						<option value="" >조회 그룹 선택</option>
						<option value="divCode" ${pagination.searching.searchCondition=='divCode' ? 'selected' : ''}>코드번호</option>
						<option value="divName" ${pagination.searching.searchCondition=='divName' ? 'selected' : ''}>부서명</option>
					</select>
<%--					<input name="pagination.searching.searchKeyword" type="text" value="${pagination.searching.searchKeyword}" placeholder="검색어를 입력해주세요."/>--%>
<%--					<a href="#" onclick="formSubmit(); form.page.value=1; form.range.value=1; form.submit();">검색</a>--%>

				</p>
			</form>
		</div>


		<div class="num_list">
			<p class="total">총 <span>${pagination.listCnt}</span>건의 결과가 있습니다.</p>
		</div>

		<div class="Basic">
			<ul class="adminList">
				<li class="tit">
					<p class="num">No</p>
					<p class="cod">그룹명</p>
					<p class="tit">요청자</p>
					<p class="tit">권한</p>
					<p class="pos">만료기간</p>
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
					<p class="cod">한국폴리텍 학생처</p>
					<p class="tit">최재경</p>
					<p class="tit">조회</p>
					<p class="pos">2025.12.31</p>
					<p class="editDel" style="padding: 0;">
						<a href="#" onclick="window.open('/divisionWrite.do?divIdx=${row.divIdx}', 'EditPopUp', 'width=500, height=350, status=no,toolbar=no,scrollbars=no')" class="edit">수정</a>
						<a href="#" onclick="window.open('/confirmDivisionDel.do?divIdx=${row.divIdx}', 'EditPopUp', 'width=500, height=350, status=no,toolbar=no,scrollbars=no')" class="del">회수</a>
					</p>
				</li>
                    <c:if test="${pagination.listCnt == 0}">
						<div style="text-align:center; margin-top:20px;">
							일치하는 자료가 없습니다.
						</div>
					</c:if>

			</ul>
		</div>

		<p class="paging">
			<!-- 현재 JSP 경로를 얻어 두기 -->
			<c:url var="selfUrl" value="${pageContext.request.requestURI}" />

			<!-- 첫 페이지 -->
			<a href="#" class="prev end" onclick="fn_maxPrev()"><img src="${pageContext.request.contextPath}/images/_img/first.png" alt="맨처음" /></a>

			<!-- 이전 페이지 -->
			<a href="#" class="prev" onclick="fn_prev(${pagination.page} , ${pagination.range}, ${pagination.rangeSize})"><img src="${pageContext.request.contextPath}/images/_img/prev.png" alt="이전으로"/></a>

			<!-- 번호 링크 -->
			<c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" var="i">
				<a class="${i == pagination.page ? 'on' : ''}" href="#" onClick="changePage(${i}, ${pagination.range}, ${pagination.rangeSize});">${i}</a>
			</c:forEach>

			<!-- 다음 페이지 -->
			<a href="#" class="next" onClick="fn_next(${pagination.pageCnt},${pagination.page}, ${pagination.range}, ${pagination.rangeSize})"><img src="${pageContext.request.contextPath}/images/_img/next.png" alt="다음으로" /></a>

			<!-- 마지막 페이지 -->
			<a href="#" class="next end" onclick="fn_maxNext(${pagination.pageCnt}, ${pagination.range}, ${pagination.rangeSize})"><img src="${pageContext.request.contextPath}/images/_img/last.png" alt="맨마지막"/></a>
		</p>
		</div>
		<div id="tab3" class="tabContent">
			<div class="tit_search">
				<h2>받은 요청 현황</h2>
			</div>


			<div class="num_list">
				<p class="total">총 <span>${pagination.listCnt}</span>건의 결과가 있습니다.</p>
			</div>

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
									<a href="#" onclick="window.open('/divisionWrite.do?divIdx=${row.divIdx}', 'EditPopUp', 'width=500, height=350, status=no,toolbar=no,scrollbars=no')" class="edit">승인</a>
									<a href="#" onclick="window.open('/confirmDivisionDel.do?divIdx=${row.divIdx}', 'EditPopUp', 'width=500, height=350, status=no,toolbar=no,scrollbars=no')" class="del">거절</a>
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
							<a href="#" onclick="window.open('/divisionWrite.do?divIdx=${row.divIdx}', 'EditPopUp', 'width=500, height=350, status=no,toolbar=no,scrollbars=no')" class="edit">승인</a>
							<a href="#" onclick="window.open('/confirmDivisionDel.do?divIdx=${row.divIdx}', 'EditPopUp', 'width=500, height=350, status=no,toolbar=no,scrollbars=no')" class="del">거절</a>
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
		var url = "${pageContext.request.contextPath}/myShareGroup.do";
		url = url + "?page=" + 1;
		url = url + "&range=" + 1;
		location.href = url;	}
	//이전 버튼 이벤트
	function fn_prev(page, range, rangeSize,searchDiv, searchPos, searchSt, searchSort, searchKyeword) {
		var page = (((range - 2) * rangeSize) + 1) <= 1 ? 1 : ((range - 2) * rangeSize) + 1 ;
		var range = (range - 1) <= 1 ? 1 : range - 1;
		var url = "${pageContext.request.contextPath}/myShareGroup.do";
		url = url + "?page=" + page;
		url = url + "&range=" + range;
		location.href = url;	}
	//페이지 번호 클릭
	function fn_pagination(page, range, rangeSize, searchType, keyword) {
		var url = "${pageContext.request.contextPath}/myShareGroup.do";
		url = url + "?page=" + page;
		url = url + "&range=" + range;
		location.href = url;		}
	//다음 버튼 이벤트
	function fn_next(pageCnt, page, range, rangeSize) {
		var page = (parseInt((range * rangeSize)) + 1) >= pageCnt ? pageCnt / rangeSize * 10 : parseInt((range * rangeSize)) + 1 ;
		var range = (parseInt(range) + 1) >= parseInt(pageCnt / rangeSize + 1) ? parseInt(pageCnt / rangeSize + 1) : (parseInt(range) + 1) ;
		var url = "${pageContext.request.contextPath}/myShareGroup.do";
		url = url + "?page=" + page;
		url = url + "&range=" + range;
		location.href = url;	}
	//마지막 버튼 이벤트
	function fn_maxNext(pageCnt, range, rangeSize) {
		var page =  pageCnt / rangeSize * 10;
		var range =    parseInt(pageCnt / rangeSize + 1);
		var url = "${pageContext.request.contextPath}/myShareGroup.do";
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

