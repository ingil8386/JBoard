package kr.co.jboard.controller.article;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import kr.co.jboard.dto.ArticleDTO;
import kr.co.jboard.dto.UserDTO;
import kr.co.jboard.service.ArticleService;

@WebServlet("/article/write.do")
public class WriteController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        UserDTO sessUser = (UserDTO) req.getSession().getAttribute("sessUser");

        if (sessUser == null) {
            resp.sendRedirect(req.getContextPath() + "/user/login.do");
            return;
        }

        req.getRequestDispatcher("/WEB-INF/views/article/write.jsp")
           .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        UserDTO sessUser = (UserDTO) req.getSession().getAttribute("sessUser");

        if (sessUser == null) {
            PrintWriter out = resp.getWriter();

            out.println("<script>");
            out.println("alert('로그인 후 글쓰기가 가능합니다.');");
            out.println("location.href='" + req.getContextPath() + "/user/login.do';");
            out.println("</script>");

            out.close();
            return;
        }

        String type = req.getParameter("type");
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        ArticleDTO article = new ArticleDTO();

        article.setType(type);
        article.setTitle(title);
        article.setContent(content);

        // 로그인 중인 사용자 아이디를 작성자로 저장
        article.setWriter(sessUser.getUserid());

        article.setRegip(req.getRemoteAddr());

        ArticleService service = ArticleService.getInstance();
        service.insertArticle(article);

        PrintWriter out = resp.getWriter();

        out.println("<script>");
        out.println("alert('작성이 완료되었습니다.');");
        out.println("location.href='" + req.getContextPath() + "/article/list.do';");
        out.println("</script>");

        out.close();
    }
}