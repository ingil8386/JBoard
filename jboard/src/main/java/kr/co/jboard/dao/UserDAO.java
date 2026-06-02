package kr.co.jboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.co.jboard.db.DBHelper;
import kr.co.jboard.db.SQL;
import kr.co.jboard.dto.UserDTO;

public class UserDAO extends DBHelper {

    private static UserDAO instance = new UserDAO();

    public static UserDAO getInstance() {
        return instance;
    }

    private UserDAO() {
    }

    public int insertUser(UserDTO user) {

        int result = 0;

        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            conn = getConnection();

            psmt = conn.prepareStatement(SQL.INSERT_USER);

            psmt.setString(1, user.getUserid());
            psmt.setString(2, user.getPass());
            psmt.setString(3, user.getName());
            psmt.setString(4, user.getNick());
            psmt.setString(5, user.getEmail());
            psmt.setString(6, user.getHp());
            psmt.setString(7, user.getRole());
            psmt.setString(8, user.getZip());
            psmt.setString(9, user.getAddr1());
            psmt.setString(10, user.getAddr2());
            psmt.setString(11, user.getRegip());

            result = psmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            close(psmt, conn);
        }

        return result;
    }

    public UserDTO selectUser(String userid, String pass) {

        UserDTO user = null;

        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            psmt = conn.prepareStatement(SQL.SELECT_USER);

            psmt.setString(1, userid);
            psmt.setString(2, pass);

            rs = psmt.executeQuery();

            if (rs.next()) {
                user = new UserDTO();

                user.setUserid(rs.getString("userid"));
                user.setPass(rs.getString("pass"));
                user.setName(rs.getString("name"));
                user.setNick(rs.getString("nick"));
                user.setEmail(rs.getString("email"));
                user.setHp(rs.getString("hp"));
                user.setRole(rs.getString("role"));
                user.setZip(rs.getString("zip"));
                user.setAddr1(rs.getString("addr1"));
                user.setAddr2(rs.getString("addr2"));
                user.setRegip(rs.getString("regip"));
                user.setRegDate(rs.getString("regDate"));
                user.setLeaveDate(rs.getString("leaveDate"));
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            close(rs, psmt, conn);
        }

        return user;
    }

    public UserDTO selectUserById(String userid) {

        UserDTO user = null;

        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            psmt = conn.prepareStatement(SQL.SELECT_USER_BY_ID);
            psmt.setString(1, userid);

            rs = psmt.executeQuery();

            if (rs.next()) {
                user = new UserDTO();

                user.setUserid(rs.getString("userid"));
                user.setPass(rs.getString("pass"));
                user.setName(rs.getString("name"));
                user.setNick(rs.getString("nick"));
                user.setEmail(rs.getString("email"));
                user.setHp(rs.getString("hp"));
                user.setRole(rs.getString("role"));
                user.setZip(rs.getString("zip"));
                user.setAddr1(rs.getString("addr1"));
                user.setAddr2(rs.getString("addr2"));
                user.setRegip(rs.getString("regip"));
                user.setRegDate(rs.getString("regDate"));
                user.setLeaveDate(rs.getString("leaveDate"));
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            close(rs, psmt, conn);
        }

        return user;
    }

    public List<UserDTO> selectUsers() {

        List<UserDTO> users = new ArrayList<UserDTO>();

        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            psmt = conn.prepareStatement(SQL.SELECT_ALL_USER);
            rs = psmt.executeQuery();

            while (rs.next()) {
                UserDTO user = new UserDTO();

                user.setUserid(rs.getString("userid"));
                user.setPass(rs.getString("pass"));
                user.setName(rs.getString("name"));
                user.setNick(rs.getString("nick"));
                user.setEmail(rs.getString("email"));
                user.setHp(rs.getString("hp"));
                user.setRole(rs.getString("role"));
                user.setZip(rs.getString("zip"));
                user.setAddr1(rs.getString("addr1"));
                user.setAddr2(rs.getString("addr2"));
                user.setRegip(rs.getString("regip"));
                user.setRegDate(rs.getString("regDate"));
                user.setLeaveDate(rs.getString("leaveDate"));

                users.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            close(rs, psmt, conn);
        }

        return users;
    }

    public int updateUser(UserDTO user) {

        int result = 0;

        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            conn = getConnection();

            psmt = conn.prepareStatement(SQL.UPDATE_USER);

            psmt.setString(1, user.getPass());
            psmt.setString(2, user.getName());
            psmt.setString(3, user.getNick());
            psmt.setString(4, user.getEmail());
            psmt.setString(5, user.getHp());
            psmt.setString(6, user.getZip());
            psmt.setString(7, user.getAddr1());
            psmt.setString(8, user.getAddr2());
            psmt.setString(9, user.getUserid());

            result = psmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            close(psmt, conn);
        }

        return result;
    }

    public int updateUserLeave(String userid) {

        int result = 0;

        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            conn = getConnection();

            psmt = conn.prepareStatement(SQL.UPDATE_USER_LEAVE);
            psmt.setString(1, userid);

            result = psmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            close(psmt, conn);
        }

        return result;
    }

    public int deleteUser(String userid) {

        int result = 0;

        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            conn = getConnection();

            psmt = conn.prepareStatement(SQL.DELETE_USER);
            psmt.setString(1, userid);

            result = psmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            close(psmt, conn);
        }

        return result;
    }
    
    public int selectCountUserId(String userid) {

        int count = 0;

        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            psmt = conn.prepareStatement(SQL.SELECT_COUNT_USERID);
            psmt.setString(1, userid);

            rs = psmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            close(rs, psmt, conn);
        }

        return count;
    }
    
    public int selectCountNick(String nick) {

        int count = 0;

        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            psmt = conn.prepareStatement(SQL.SELECT_COUNT_NICK);
            psmt.setString(1, nick);

            rs = psmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            close(rs, psmt, conn);
        }

        return count;
    }
    
    
    public String selectUserIdByNameEmail(String name, String email) {

        String userid = null;

        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            psmt = conn.prepareStatement(SQL.SELECT_USERID_BY_NAME_EMAIL);
            psmt.setString(1, name);
            psmt.setString(2, email);

            rs = psmt.executeQuery();

            if (rs.next()) {
                userid = rs.getString("userid");
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            close(rs, psmt, conn);
        }

        return userid;
    }
    
    public String selectEmailByUserId(String userid) {

        String email = null;

        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            psmt = conn.prepareStatement(SQL.SELECT_EMAIL_BY_USERID);
            psmt.setString(1, userid);

            rs = psmt.executeQuery();

            if (rs.next()) {
                email = rs.getString("email");
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            close(rs, psmt, conn);
        }

        return email;
    }
    
    
    public int updateUserPassword(String userid, String pass) {

        int result = 0;

        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            conn = getConnection();

            psmt = conn.prepareStatement(SQL.UPDATE_USER_PASSWORD);
            psmt.setString(1, pass);
            psmt.setString(2, userid);

            result = psmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            close(psmt, conn);
        }

        return result;
    }
    
    
    
    
    
}