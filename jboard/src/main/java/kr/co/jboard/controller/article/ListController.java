package kr.co.jboard.controller.article;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import kr.co.jboard.dto.ArticleDTO;
import kr.co.jboard.service.ArticleService;

@WebServlet("/article/list.do")
public class ListController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ArticleService service = ArticleService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String pg = req.getParameter("pg");

        int currentPage = 1;

        if (pg != null) {
            currentPage = Integer.parseInt(pg);
        }

        int total = service.selectCountTotal();

        int lastPageNum = 0;

        if (total % 10 == 0) {
            lastPageNum = total / 10;
        } else {
            lastPageNum = total / 10 + 1;
        }

        if (lastPageNum == 0) {
            lastPageNum = 1;
        }

        int start = (currentPage - 1) * 10;

        int startPageNum = ((currentPage - 1) / 10) * 10 + 1;
        int endPageNum = startPageNum + 9;

        if (endPageNum > lastPageNum) {
            endPageNum = lastPageNum;
        }

        List<ArticleDTO> articles = service.selectArticles(start);

        req.setAttribute("articles", articles);
        req.setAttribute("total", total);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("lastPageNum", lastPageNum);
        req.setAttribute("startPageNum", startPageNum);
        req.setAttribute("endPageNum", endPageNum);

        req.getRequestDispatcher("/WEB-INF/views/article/list.jsp").forward(req, resp);
    }
}