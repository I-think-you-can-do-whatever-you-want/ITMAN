<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java" %>

<!doctype html>
<html lang="ko" style="background-color: #f0f5f5">
<head>
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/_inc/checkSession.jsp"/>
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/_inc/title.jsp" />
</head>
<body>
<c:url value="/checkInviteCode.do" var="checkInviteCodeUrl" />
<div id="popup">
    <div class="pop_tit">
        <p class="title">그룹 공유 요청</p>
    </div>
    <div class="pop_cont">
        <form method="post" id="form" action="${pageContext.request.contextPath}/insertRequest.do">
            <ul class="contEdit">
                <li>
                    <p class="tit">초대 코드<span>*</span></p>
                    <p class="cont"><input type="text" id="inviteCode" name="inviteCode" placeholder="공유 코드를 입력해 주세요"></p>
                </li>
                <li>
                    <p class="tit">전달 메시지</p>
                    <p class="cont"><input type="text" name="reqNote"/></p>
                </li>
            </ul>
            <p class="pop_btn"><a href="javascript:window.close();" class="del">취소</a><a href="#" onclick="checkInviteCode();" class="comp">추가</a></p>
        </form>
    </div>
</div>
<script>
    async function checkInviteCode(){
        $inviteCode = $("#inviteCode").val().trim();
        if(!$inviteCode){
            alert("공유 코드를 입력해주세요.");
        }else{
            try {
                const resp = await fetch("${checkInviteCodeUrl}", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded"
                    },
                    body: new URLSearchParams({
                        inviteCode: document.querySelector("input[name='inviteCode']").value.trim()
                    }),
                });

                const text = await resp.text();
                const code = parseInt(text.trim(), 10);

                if (code === 0) {
                    alert("입력한 공유 코드의 정보가 존재하지않습니다.");
                    return;
                }
                if (code === 1) {
                    document.forms['form'].submit();

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
