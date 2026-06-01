<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="kr.co.jboard.dto.ArticleDTO"%>
<%@ page import="kr.co.jboard.dto.UserDTO"%>

<%
List<ArticleDTO> articles = (List<ArticleDTO>) request.getAttribute("articles");
UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");

int total = 0;

if (articles != null) {
	total = articles.size();
}

int currentPage = 1;
int lastPageNum = 1;
int startPageNum = 1;
int endPageNum = 1;

if (request.getAttribute("currentPage") != null) {
	currentPage = (int) request.getAttribute("currentPage");
}

if (request.getAttribute("lastPageNum") != null) {
	lastPageNum = (int) request.getAttribute("lastPageNum");
}

if (request.getAttribute("startPageNum") != null) {
	startPageNum = (int) request.getAttribute("startPageNum");
}

if (request.getAttribute("endPageNum") != null) {
	endPageNum = (int) request.getAttribute("endPageNum");
}
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>글목록</title>
<link rel="stylesheet" href="../css/style.css" />
</head>
<body>
	<div id="wrapper">
		<header>
			<h3>
				<a href="../index.jsp" class="title">Board Project</a>
			</h3>

			<p>
				<%
				if (sessUser != null) {
				%>
				<a href="../user/info.do" class="info"><%=sessUser.getNick()%></a>님
				반갑습니다. <a href="../user/logout.do">[로그아웃]</a>
				<%
				} else {
				%>
				<a href="../user/login.do">[로그인]</a> <a href="../user/register.do">[회원가입]</a>
				<%
				}
				%>
			</p>
		</header>

		<main id="article">
			<section class="list">
				<nav>
					<h1>
						전체 글목록 <span><%=total%>건</span>
					</h1>

					<form action="./searchList.do" method="get">
						<select name="searchType">
							<option value="title">제목</option>
							<option value="content">내용</option>
							<option value="writer">글쓴이</option>
						</select> <input type="text" name="keyword" placeholder="검색 키워드 입력">
						<input type="submit" value="검색">
					</form>
				</nav>

				<table border="0">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>날짜</th>
						<th>조회</th>
					</tr>

					<%
					if (articles != null && articles.size() > 0) {
						for (ArticleDTO article : articles) {
					%>
					<tr>
						<td><%=article.getAno()%></td>
						<td><a href="./view.do?ano=<%=article.getAno()%>"> <%=article.getTitle()%>
								[<%=article.getComment()%>]
						</a></td>
						<td><%=article.getWriter()%></td>
						<td><%=article.getWdate()%></td>
						<td><%=article.getHit()%></td>
					</tr>
					<%
					}
					} else {
					%>
					<tr>
						<td colspan="5">등록된 글이 없습니다.</td>
					</tr>
					<%
					}
					%>
				</table>

				<div class="page">

					<% if (startPageNum > 1) { %>
					<a href="./list.do?pg=<%= startPageNum - 1 %>" class="prev">이전</a>
					<% } %>

					<% for (int i = startPageNum; i <= endPageNum; i++) { %>
					<% if (i == currentPage) { %>
					<a href="./list.do?pg=<%= i %>" class="num current"><%= i %></a>
					<% } else { %>
					<a href="./list.do?pg=<%= i %>" class="num"><%= i %></a>
					<% } %>
					<% } %>

					<% if (endPageNum < lastPageNum) { %>
					<a href="./list.do?pg=<%= endPageNum + 1 %>" class="next">다음</a>
					<% } %>

				</div>

				<%
				if (sessUser != null) {
				%>
				<a href="./write.do" class="btn btnWrite">글쓰기</a>
				<%
				} else {
				%>
				<a href="../user/login.do" class="btn btnWrite">글쓰기</a>
				<%
				}
				%>
			</section>
		</main>

		<footer>
			<p>
				<span class="copyright">Copyrightⓒ 김철학(개발에반하다.)</span> <span
					class="version">v1.0.1</span>
			</p>
		</footer>
	</div>
</body>
</html>