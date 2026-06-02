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

@WebServlet("/article/searchList.do")
public class SearchListController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ArticleService service = ArticleService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String searchType = req.getParameter("searchType");
        String keyword = req.getParameter("keyword");

        if (keyword == null) {
            keyword = "";
        }

        keyword = keyword.trim();

        List<ArticleDTO> articles = service.selectSearchArticles(searchType, keyword);

        req.setAttribute("articles", articles);
        req.setAttribute("total", articles.size());

        req.setAttribute("searchType", searchType);
        req.setAttribute("keyword", keyword);

        req.getRequestDispatcher("/WEB-INF/views/article/list.jsp").forward(req, resp);
    }
}