package kr.co.jboard.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBHelper {

    protected Connection getConnection() throws Exception {

        Context initCtx = new InitialContext();
        Context ctx = (Context) initCtx.lookup("java:comp/env");

        DataSource ds = (DataSource) ctx.lookup("jdbc/board");

        return ds.getConnection();
    }

    protected void close(ResultSet rs, PreparedStatement psmt, Connection conn) {

        try {
            if (rs != null) {
                rs.close();
            }

            if (psmt != null) {
                psmt.close();
            }

            if (conn != null) {
                conn.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void close(PreparedStatement psmt, Connection conn) {

        try {
            if (psmt != null) {
                psmt.close();
            }

            if (conn != null) {
                conn.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}