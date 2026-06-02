package kr.co.jboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.co.jboard.db.DBHelper;
import kr.co.jboard.db.SQL;
import kr.co.jboard.dto.TermsDTO;

public class TermsDAO extends DBHelper {

    private static TermsDAO instance = new TermsDAO();

    public static TermsDAO getInstance() {
        return instance;
    }

    private TermsDAO() {}

    public TermsDTO selectTerms() {

        TermsDTO terms = null;

        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            psmt = conn.prepareStatement(SQL.SELECT_TERMS);
            rs = psmt.executeQuery();

            if (rs.next()) {
                terms = new TermsDTO();

                terms.setNo(rs.getInt("no"));
                terms.setTerms(rs.getString("basic"));
                terms.setPrivacy(rs.getString("privacy"));
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            close(rs, psmt, conn);
        }

        return terms;
    }
}