package kr.co.jboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.co.jboard.db.DBHelper;
import kr.co.jboard.db.SQL;
import kr.co.jboard.dto.ArticleDTO;

public class ArticleDAO extends DBHelper {

    private static ArticleDAO instance = new ArticleDAO();

    public static ArticleDAO getInstance() {
        return instance;
    }

    private ArticleDAO() {
    }

    public int insertArticle(ArticleDTO article) {

        int result = 0;

        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            conn = getConnection();

            psmt = conn.prepareStatement(SQL.INSERT_ARTICLE);

            psmt.setString(1, article.getType());
            psmt.setString(2, article.getTitle());
            psmt.setString(3, article.getContent());
            psmt.setString(4, article.getWriter());
            psmt.setString(5, article.getRegip());

            result = psmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            close(psmt, conn);
        }

        return result;
    }

    public ArticleDTO selectArticle(int ano) {

        ArticleDTO article = null;

        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            psmt = conn.prepareStatement(SQL.SELECT_ARTICLE);
            psmt.setInt(1, ano);

            rs = psmt.executeQuery();

            if (rs.next()) {
                article = new ArticleDTO();

                article.setAno(rs.getInt("ano"));
                article.setType(rs.getString("type"));
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
                article.setComment(rs.getInt("comment"));
                article.setFile(rs.getInt("file"));
                article.setHit(rs.getInt("hit"));
                article.setWriter(rs.getString("writer"));
                article.setRegip(rs.getString("regip"));
                article.setWdate(rs.getString("wdate"));
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            close(rs, psmt, conn);
        }

        return article;
    }

    public List<ArticleDTO> selectArticles() {

        List<ArticleDTO> articles = new ArrayList<ArticleDTO>();

        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            psmt = conn.prepareStatement(SQL.SELECT_ALL_ARTICLE);
            rs = psmt.executeQuery();

            while (rs.next()) {
                ArticleDTO article = new ArticleDTO();

                article.setAno(rs.getInt("ano"));
                article.setType(rs.getString("type"));
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
                article.setComment(rs.getInt("comment"));
                article.setFile(rs.getInt("file"));
                article.setHit(rs.getInt("hit"));
                article.setWriter(rs.getString("writer"));
                article.setRegip(rs.getString("regip"));
                article.setWdate(rs.getString("wdate"));

                articles.add(article);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            close(rs, psmt, conn);
        }

        return articles;
    }

    public int updateArticle(ArticleDTO article) {

        int result = 0;

        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            conn = getConnection();

            psmt = conn.prepareStatement(SQL.UPDATE_ARTICLE);

            psmt.setString(1, article.getType());
            psmt.setString(2, article.getTitle());
            psmt.setString(3, article.getContent());
            psmt.setInt(4, article.getAno());

            result = psmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            close(psmt, conn);
        }

        return result;
    }

    public int deleteArticle(int ano) {

        int result = 0;

        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            conn = getConnection();

            psmt = conn.prepareStatement(SQL.DELETE_ARTICLE);
            psmt.setInt(1, ano);

            result = psmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            close(psmt, conn);
        }

        return result;
    }

    public void updateHit(int ano) {

        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            conn = getConnection();

            psmt = conn.prepareStatement(SQL.UPDATE_ARTICLE_HIT);
            psmt.setInt(1, ano);

            psmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            close(psmt, conn);
        }
    }

    public List<ArticleDTO> searchArticles(String keyword) {

        List<ArticleDTO> articles = new ArrayList<ArticleDTO>();

        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            psmt = conn.prepareStatement(SQL.SEARCH_ARTICLE);

            psmt.setString(1, "%" + keyword + "%");
            psmt.setString(2, "%" + keyword + "%");

            rs = psmt.executeQuery();

            while (rs.next()) {
                ArticleDTO article = new ArticleDTO();

                article.setAno(rs.getInt("ano"));
                article.setType(rs.getString("type"));
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
                article.setComment(rs.getInt("comment"));
                article.setFile(rs.getInt("file"));
                article.setHit(rs.getInt("hit"));
                article.setWriter(rs.getString("writer"));
                article.setRegip(rs.getString("regip"));
                article.setWdate(rs.getString("wdate"));

                articles.add(article);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            close(rs, psmt, conn);
        }

        return articles;
    }
    
    public int selectCountTotal() {

        int total = 0;

        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            psmt = conn.prepareStatement(SQL.SELECT_COUNT_TOTAL);
            rs = psmt.executeQuery();

            if (rs.next()) {
                total = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            close(rs, psmt, conn);
        }

        return total;
    }
    
    
    public List<ArticleDTO> selectArticles(int start) {

        List<ArticleDTO> articles = new ArrayList<>();

        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            psmt = conn.prepareStatement(SQL.SELECT_ARTICLES);
            psmt.setInt(1, start);

            rs = psmt.executeQuery();

            while (rs.next()) {
                ArticleDTO article = new ArticleDTO();

                article.setAno(rs.getInt("ano"));
                article.setType(rs.getString("type"));
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
                article.setComment(rs.getInt("comment"));
                article.setFile(rs.getInt("file"));
                article.setHit(rs.getInt("hit"));
                article.setWriter(rs.getString("writer"));
                article.setRegip(rs.getString("regip"));
                article.setWdate(rs.getString("wdate"));

                articles.add(article);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            close(rs, psmt, conn);
        }

        return articles;
    }
    
}