package kr.co.jboard.service;

import java.util.List;

import kr.co.jboard.dao.UserDAO;
import kr.co.jboard.dto.UserDTO;

public class UserService {

    private static UserService instance = new UserService();

    public static UserService getInstance() {
        return instance;
    }

    private UserService() {
    }

    private UserDAO dao = UserDAO.getInstance();

    // 회원가입
    public int insertUser(UserDTO user) {
        return dao.insertUser(user);
    }

    // 로그인
    public UserDTO selectUser(String userid, String pass) {
        return dao.selectUser(userid, pass);
    }

    // 아이디로 회원 조회
    public UserDTO selectUserById(String userid) {
        return dao.selectUserById(userid);
    }

    // 전체 회원 조회
    public List<UserDTO> selectUsers() {
        return dao.selectUsers();
    }

    // 회원정보 수정
    public int updateUser(UserDTO user) {
        return dao.updateUser(user);
    }

    // 회원탈퇴 처리
    public int updateUserLeave(String userid) {
        return dao.updateUserLeave(userid);
    }

    // 회원 완전 삭제
    public int deleteUser(String userid) {
        return dao.deleteUser(userid);
    }
}