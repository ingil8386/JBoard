<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="kr.co.jboard.dto.ArticleDTO" %>

<%
    ArticleDTO article = (ArticleDTO) request.getAttribute("article");
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>글보기</title>
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
            <section class="view">
                <nav>
                    <h1>글보기</h1>
                </nav>

                <table border="0">
                    <tr>
                        <th>제목</th>
                        <td>
                            <input type="text" name="title" value="<%= article.getTitle() %>" readonly/>
                        </td>
                    </tr>

                    <tr>
                        <th>작성자</th>
                        <td>
                            <input type="text" name="writer" value="<%= article.getWriter() %>" readonly/>
                        </td>
                    </tr>

                    <tr>
                        <th>파일</th>
                        <td>
                            <%
                                if (article.getFile() > 0) {
                            %>
                                <p>첨부파일이 <span><%= article.getFile() %></span>개 있습니다.</p>
                            <%
                                } else {
                            %>
                                <p>첨부파일이 없습니다.</p>
                            <%
                                }
                            %>
                        </td>
                    </tr>

                    <tr>
                        <th>내용</th>
                        <td>
                            <textarea name="content" readonly><%= article.getContent() %></textarea>
                        </td>
                    </tr>
                </table>

                <div>
                    <a href="./delete.do?ano=<%= article.getAno() %>" class="btn btnRemove">삭제</a>
                    <a href="./modify.do?ano=<%= article.getAno() %>" class="btn btnModify">수정</a>
                    <a href="./list.do" class="btn btnList">목록</a>
                </div>

                <!-- 댓글목록 -->
                <section class="commentList">
                    <h3>댓글목록</h3>

                    <p class="empty">등록된 댓글이 없습니다.</p>
                </section>

                <!-- 댓글쓰기 -->
                <section class="commentForm">
                    <h3>댓글쓰기</h3>

                    <form action="./comment/write.do" method="post">
                        <input type="hidden" name="parent" value="<%= article.getAno() %>">
                        <textarea name="content" placeholder="댓글내용 입력"></textarea>

                        <div>
                            <a href="./view.do?ano=<%= article.getAno() %>" class="btn btnCancel">취소</a>
                            <input type="submit" value="작성완료" class="btn btnComplete"/>
                        </div>
                    </form>
                </section>

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