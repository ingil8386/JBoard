<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Board Project</title>
</head>
<body>

    <h3>Board Project</h3>

    <h4>프로젝트 개요</h4>
    <p>
        본 프로젝트는 JSP를 활용하여 CRUD 기능을 제공하는 게시판을 개발하는 것입니다.<br>
        사용자들은 게시글을 작성, 조회, 수정, 삭제할 수 있으며, 회원가입 및 로그인 기능도 포함합니다.<br>
        이 프로젝트를 통해 JSP 및 서블릿 기반 웹 애플리케이션 개발의 기본 개념을 익히고, 실전 경험을 쌓을 수 있습니다.
    </p>

    <h4>주요기능</h4>
    <p>
        <a href="./user/login.do">로그인</a><br>
        <a href="./user/terms.do">약관</a><br>
        <a href="./user/register.do">회원가입</a><br>
        <a href="./user/info.do">회원정보</a><br>
    </p>

    <p>
        <a href="./find/userId.do">아이디찾기</a><br>
        <a href="./find/resultUserId.do">아이디결과</a><br>
        <a href="./find/password.do">비밀번호찾기</a><br>
        <a href="./find/changePassword.do">비밀번호변경</a><br>
    </p>

    <p>
        <a href="./article/list.do">글목록</a><br>
        <a href="./article/write.do">글쓰기</a><br>
        <a href="./article/view.do">글보기</a><br>
        <a href="./article/modify.do">글수정</a><br>
        <a href="./article/searchList.do">글검색목록</a><br>
    </p>

    <h4>기술스택</h4>
    <p>
        HTML5, CSS3, Javascript<br>
        JSP, Servlet<br>
        MySQL, Tomcat<br>
    </p>

</body>
</html>