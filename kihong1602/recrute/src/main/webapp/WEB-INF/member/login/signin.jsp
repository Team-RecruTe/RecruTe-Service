<%--
  Created by IntelliJ IDEA.
  User: kks45
  Date: 2023-10-17
  Time: 오전 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../layout/header.jsp" %>
<script>
    window.addEventListener('load', function () {
        let check = ${requestScope.loginCheck};
        if (check) {
            const loginId = '${requestScope.loginMember.memberId}';
            let msg = '반갑습니다. ' + loginId + '님!';
            alert(msg);
            location.href = "/";
        } else {
            alert('입력된 정보가 회원정보와 일치하지 않거나 등록되지 않은 회원입니다.');
            history.back();
        }
    });
</script>

<%@include file="../../layout/footer.jsp" %>
