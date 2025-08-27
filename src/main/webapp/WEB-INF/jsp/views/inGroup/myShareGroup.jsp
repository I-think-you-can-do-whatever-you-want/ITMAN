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
									<a href="#" onclick="window.open('/confirmSharedGroupDel.do?invIdx=${row.invIdx}', 'EditPopUp', 'width=500, height=350, status=no,toolbar=no,scrollbars=no')" class="del">삭제</a>
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
			<form  id="searchForm" name="searchForm" method="get" action="${pageContext.request.contextPath}/myShareGroup.do">
				<input type="hidden" id="tab" name="tab" value="tab2"/>

				<p class="list_search">
					<select name="searching.searchCondition"
							onchange="document.getElementById('tab').value='tab2'; this.form.submit();">
						<option value="">조회 그룹 선택</option>
						<c:forEach var="group" items="${mySharedGroupList}">
							<option value="${group.groIdx}"
									<c:if test="${param['searching.searchCondition'] eq group.groIdx}">selected</c:if>>
									${group.groName}
							</option>
						</c:forEach>
					</select>

				</p>
			</form>
		</div>


		<div class="num_list">
			<p class="total">총 <span>${mySharedPermissionListCnt}</span>건의 결과가 있습니다.</p>
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
				<c:if test="${!empty mySharedPermissionList}">
					<c:forEach var="row" items="${mySharedPermissionList}">
				<li>
				<p class="num">${row.rowNum}</p>
				<p class="cod">${row.groName}</p>
				<p class="tit">${row.targetMemName}</p>
				<p class="tit">
					<c:choose>
						<c:when test="${row.permMask == '4'}">조회 | 수정 | 삭제</c:when>
						<c:when test="${row.permMask == '2'}">조회 | 수정</c:when>
						<c:otherwise>조회</c:otherwise>
					</c:choose>
				</p>
					<p class="pos">
						<c:choose>
							<c:when test="${!empty row.validTo}">${row.validTo}</c:when>
							<c:otherwise>-</c:otherwise>
						</c:choose>
					</p>
				<p class="editDel" style="padding: 0;">
					<a href="#" onclick="window.open('/divisionWrite.do', 'EditPopUp', 'width=500, height=350, status=no,toolbar=no,scrollbars=no')" class="edit">수정</a>
					<a href="#" onclick="window.open('/confirmDivisionDel.do', 'EditPopUp', 'width=500, height=350, status=no,toolbar=no,scrollbars=no')" class="del">삭제</a>
				</p>
				</li>
				</c:forEach>
				</c:if>
                    <c:if test="${empty mySharedPermissionList}">
						<div style="text-align:center; margin-top:20px;">
							일치하는 자료가 없습니다.
						</div>
					</c:if>
			</ul>
		</div>
		</div>
		<div id="tab3" class="tabContent">
			<div class="tit_search">
				<h2>받은 요청 현황</h2>
			</div>


			<div class="num_list">
				<p class="total">총 <span>${receivedRequestListCnt}</span>건의 결과가 있습니다.</p>
			</div>

			<div class="Basic">
				<ul class="adminList">
					<li class="tit">
						<p class="num">No</p>
						<p class="cod">요청자</p>
						<p class="tit">그룹명</p>
						<p class="pos">요청상태</p>
						<p class="editDel">관리</p>
					</li>
					<c:if test="${!empty receivedRequestList}">
						<c:forEach var="row" items="${receivedRequestList}">
							<li>
								<p class="num">${row.rowNum}</p>
								<p class="cod">${row.requesterName}</p>
								<p class="tit">${row.groName}</p>
								<p class="pos">
									<c:if test="${row.status == 'PENDING'}">승인 대기</c:if>
								</p>
								<p class="editDel" style="padding: 0;">
									<a href="#" onclick="window.open('/confirmSharedGroupApprove.do?reqIdx=${row.reqIdx}', 'EditPopUp', 'width=500, height=350, status=no,toolbar=no,scrollbars=no')" class="edit">승인</a>
									<a href="#" onclick="window.open('/confirmSharedGroupReject.do', 'EditPopUp', 'width=500, height=350, status=no,toolbar=no,scrollbars=no')" class="del">거절</a>
								</p>
							</li>
						</c:forEach>
					</c:if>
					<c:if test="${empty receivedRequestList}">
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
	<script>
		document.addEventListener('DOMContentLoaded', function() {
			const urlParams = new URLSearchParams(window.location.search);
			const activeTab = urlParams.get('tab') || 'tab1';

			document.querySelectorAll('.tabMenu li').forEach(li => {
				li.classList.toggle('active', li.dataset.tab === activeTab);
			});
			document.querySelectorAll('.tabContent').forEach(div => {
				div.classList.toggle('active', div.id === activeTab);
			});
		});
	</script>
</body>

