<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<!doctype html>
<html lang="ko">
<head>
    <!-- pwa를 위한 추가 -->
    <meta name="theme-color" content="#0b5fff" />
    <link rel="icon" href="${pageContext.request.contextPath}/pwa/icons/icon-192.png" />
    <!-- iOS용 -->
    <link rel="apple-touch-icon" href="${pageContext.request.contextPath}/pwa/icons/icon-192.png" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="mobile-web-app-capable" content="yes">
    <!-- pwa를 위한 추가 끝-->
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/_inc/title.jsp" />
    <link href="https://webfontworld.github.io/gmarket/GmarketSans.css" rel="stylesheet" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/_css/default.css" />
</head>
<body>
<!-- 세션 정보 설정 -->
<c:set var="useremail" value="${userMail}" />
<c:set var="username"  value="${userName}" />
<c:set var="userIDX"   value="${userIdx}" />
<header class="h_index">
    <div class="h_left">
        <h1 class="logo"><a href="#"><img src="/images/_img/main_logo.png" alt="아이티맨" /></a></h1>
    </div>
    <c:choose>
        <c:when test="${empty useremail || empty username}">
            <!-- 로그인 전 -->
            <p class="h_right_index">
                <a href="user/login.do">로그인</a>
                <a href="user/join.do" class="join">회원가입</a>
            </p>
        </c:when>
        <c:otherwise>
            <div class="h_right">
                <a href="#" class="hr_btn"><span></span><span></span><span></span></a>
                <p class="hr_box">
                    <span>${username}님</span>
                    <a href="/myPage.do" class="btn mypage">마이페이지</a>
                    <a href="/logout.do" class="btn logout">로그아웃</a>
                    <a href="/group.do" class="btn group">전체그룹메인</a>
                </p>
            </div>
        </c:otherwise>
    </c:choose>
</header>

<div id="contents" class="index">
    <p class="logo"><img src="/images/_img/itman_logo.png" alt="아이티맨" /></p>
    <p class="index_tt">
        개인과 기업이 할 수 있는 자산관리 시스템
        <span>자산 데이터와 코드 관리 전반에 활용할 수 있는 <br />제품과 솔루션</span>
    </p>
    <ul class="index_service">
        <li><a href="#" onclick="checkMove()"><span>Service 01</span>자산 관리</a></li>
        <li><a href="#" onclick="checkMove()"><span>Service 02</span>직원 관리</a></li>
        <li><a href="#" onclick="checkMove()"><span>Service 03</span>부서 관리</a></li>
        <li><a href="#" onclick="checkMove()"><span>Service 04</span>직위 관리</a></li>
        <li><a href="#" onclick="checkMove()"><span>Service 05</span>구매처 관리</a></li>
        <li><a href="#" onclick="checkMove()"><span>Service 06</span>위치 관리</a></li>
    </ul>
    <p class="index_st">무료로 이용해보기</p>
    <p class="index_btn">
        <c:choose>
            <c:when test="${empty useremail || empty username}">
                <a href="#" onclick="checkLogin();">그룹 생성하기</a>
            </c:when>
            <c:otherwise>
                <a onclick="window.open('/addGroup.do','그룹생성팝업','width=500,height=350')" href="#" class="addBox">그룹 생성하기</a>
            </c:otherwise>
        </c:choose>
        <a href="javascript:unready();">유료로 더 많은 기능 사용하기</a>
    </p>
</div>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/_inc/footer.jsp" />

<script>
    function checkMove(){
        const userMail = "${useremail}";

        if (userMail && userMail.trim() !== "") {
            // 로그인 되어 있음
            location.href='/group.do';
        } else {
            alert("로그인을 해주세요.");
            location.href='/user/login.do';
        }
    }
    function checkLogin(){
            alert("로그인을 해주세요.");
            location.href='/user/login.do';

    }

    function unready(){
        alert("아직 준비중입니다.");
    }
</script>
<script>
    // beforeinstallprompt 처리 (설치 버튼 노출/클릭)
    let deferredPrompt;
    window.addEventListener('beforeinstallprompt', (e) => {
        e.preventDefault();
        deferredPrompt = e;
        document.getElementById('btnInstall').style.display = 'inline-block';
    });
    document.getElementById('btnInstall').addEventListener('click', async () => {
        if (!deferredPrompt) return;
        deferredPrompt.prompt();
        const { outcome } = await deferredPrompt.userChoice;
        console.log('install outcome:', outcome);
        deferredPrompt = null;
    });

    // 필요 시: index.jsp에서도 manifest를 동적으로 보장(중복 주입 방지)
    (function attachManifestOnce() {
        function ensure() {
            if (document.querySelector('link[rel="manifest"]')) return;
            var m = document.createElement('link');
            m.rel = 'manifest';
            m.href = '/pwa/manifest.webmanifest';
            document.head.appendChild(m);
        }
        if (document.readyState === 'loading') {
            document.addEventListener('DOMContentLoaded', ensure);
        } else {
            ensure();
        }
    })();
</script>
</body>
</html>