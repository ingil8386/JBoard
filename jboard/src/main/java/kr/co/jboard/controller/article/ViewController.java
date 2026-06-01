package kr.co.jboard.controller.article;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import kr.co.jboard.dto.ArticleDTO;
import kr.co.jboard.service.ArticleService;

@WebServlet("/article/view.do")
public class ViewController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String ano = req.getParameter("ano");

        ArticleService service = ArticleService.getInstance();

        service.updateHit(Integer.parseInt(ano));

        ArticleDTO article = service.selectArticle(Integer.parseInt(ano));

        req.setAttribute("article", article);

        req.getRequestDispatcher("/WEB-INF/views/article/view.jsp")
           .forward(req, resp);
    }
}