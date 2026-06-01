package kr.co.jboard.controller.article;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import kr.co.jboard.service.ArticleService;

@WebServlet("/article/delete.do")
public class DeleteController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String ano = req.getParameter("ano");

        ArticleService service = ArticleService.getInstance();

        service.deleteArticle(Integer.parseInt(ano));

        resp.sendRedirect(req.getContextPath() + "/article/list.do");
    }
}