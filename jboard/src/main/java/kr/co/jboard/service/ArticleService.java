package kr.co.jboard.service;

import java.util.List;

import kr.co.jboard.dao.ArticleDAO;
import kr.co.jboard.dto.ArticleDTO;

public class ArticleService {

    private static ArticleService instance = new ArticleService();

    public static ArticleService getInstance() {
        return instance;
    }

    private ArticleService() {
    }

    private ArticleDAO dao = ArticleDAO.getInstance();

    public int insertArticle(ArticleDTO article) {
        return dao.insertArticle(article);
    }

    public ArticleDTO selectArticle(int ano) {
        return dao.selectArticle(ano);
    }

    public List<ArticleDTO> selectArticles() {
        return dao.selectArticles();
    }

    public int updateArticle(ArticleDTO article) {
        return dao.updateArticle(article);
    }

    public int deleteArticle(int ano) {
        return dao.deleteArticle(ano);
    }

    public void updateHit(int ano) {
        dao.updateHit(ano);
    }

    public List<ArticleDTO> searchArticles(String keyword) {
        return dao.searchArticles(keyword);
    }
    
    public int selectCountTotal() {
        return dao.selectCountTotal();
    }

    public List<ArticleDTO> selectArticles(int start) {
        return dao.selectArticles(start);
    }
    
}