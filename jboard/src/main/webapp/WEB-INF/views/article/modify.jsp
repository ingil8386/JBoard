<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="kr.co.jboard.dto.ArticleDTO" %>

<%
    ArticleDTO article = (ArticleDTO) request.getAttribute("article");
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>글수정</title>
    <link rel="stylesheet" href="../css/style.css"/>
</head>
<body>
    <div id="wrapper">
        <header>
            <h3>
                <a href="../index.jsp" class="title">Board Project</a>
            </h3>
            <p>
                <a href="../user/info.do" class="info">홍길동</a>님 반갑습니다.
                <a href="../user/login.do">[로그아웃]</a>
            </p>
        </header>

        <main id="article">
            <section class="modify">
                <nav>
                    <h1>글수정</h1>
                </nav>

                <form action="./modify.do" method="post">
                    <input type="hidden" name="ano" value="<%= article.getAno() %>">

                    <table border="0">
                        <tr>
                            <th>게시판</th>
                            <td>
                                <select name="type">
                                    <option value="free" <%= "free".equals(article.getType()) ? "selected" : "" %>>자유게시판</option>
                                    <option value="notice" <%= "notice".equals(article.getType()) ? "selected" : "" %>>공지사항</option>
                                    <option value="qna" <%= "qna".equals(article.getType()) ? "selected" : "" %>>질문과답변</option>
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <th>제목</th>
                            <td>
                                <input type="text" name="title" value="<%= article.getTitle() %>" placeholder="제목을 입력하세요."/>
                            </td>
                        </tr>

                        <tr>
                            <th>내용</th>
                            <td>
                                <textarea name="content"><%= article.getContent() %></textarea>
                            </td>
                        </tr>

                        <tr>
                            <th>파일</th>
                            <td>
                                <input type="file" name="file"/>
                            </td>
                        </tr>
                    </table>

                    <div>
                        <a href="./view.do?ano=<%= article.getAno() %>" class="btn btnCancel">취소</a>
                        <input type="submit" value="수정완료" class="btn btnComplete"/>
                    </div>
                </form>

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