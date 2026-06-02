<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    String userid = (String) request.getAttribute("userid");
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>아이디 찾기 결과</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css"/>
</head>
<body>
    <div id="wrapper">
        <header>
            <h3>
                <a href="<%= request.getContextPath() %>/index.jsp" class="title">Board Project</a>
            </h3>
        </header>

        <main id="find">
            <section class="resultUserId">
                <h2 class="tit">아이디 찾기 결과</h2>

                <% if (userid != null) { %>
                    <p>
                        회원님의 아이디는
                        <strong><%= userid %></strong>
                        입니다.
                    </p>
                <% } else { %>
                    <p>일치하는 회원정보가 없습니다.</p>
                <% } %>

                <div>
                    <a href="<%= request.getContextPath() %>/user/login.do" class="btn btnRegister">로그인</a>
                    <a href="<%= request.getContextPath() %>/user/find/password.do" class="btn btnCancel">비밀번호 찾기</a>
                </div>
            </section>
        </main>

        <footer>
            <p>
                <span class="copyright">Copyrightⓒ 김철학(개발에반하다.)</span>
                <span class="version">v1.0.1</span>
            </p>
        </footer>
    </div>
</body>
</html>