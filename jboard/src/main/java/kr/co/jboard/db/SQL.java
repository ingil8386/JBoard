package kr.co.jboard.db;

public class SQL {

    public static final String INSERT_ARTICLE =
            "INSERT INTO `Article` "
          + "(`type`, `title`, `content`, `writer`, `regip`, `wdate`) "
          + "VALUES (?, ?, ?, ?, ?, NOW())";

    public static final String SELECT_ARTICLE =
            "SELECT * FROM `Article` "
          + "WHERE `ano` = ?";

    public static final String SELECT_ALL_ARTICLE =
            "SELECT * FROM `Article` "
          + "ORDER BY `ano` DESC";

    public static final String UPDATE_ARTICLE =
            "UPDATE `Article` "
          + "SET `type` = ?, `title` = ?, `content` = ? "
          + "WHERE `ano` = ?";

    public static final String DELETE_ARTICLE =
            "DELETE FROM `Article` "
          + "WHERE `ano` = ?";

    public static final String UPDATE_ARTICLE_HIT =
            "UPDATE `Article` "
          + "SET `hit` = `hit` + 1 "
          + "WHERE `ano` = ?";

    public static final String SEARCH_ARTICLE =
            "SELECT * FROM `Article` "
          + "WHERE `title` LIKE ? OR `content` LIKE ? "
          + "ORDER BY `ano` DESC";
    
 // User 회원가입
    public static final String INSERT_USER =
            "INSERT INTO `User` "
          + "(`userid`, `pass`, `name`, `nick`, `email`, `hp`, `role`, `zip`, `addr1`, `addr2`, `regip`, `regDate`, `leaveDate`) "
          + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NULL)";

    // User 로그인
    public static final String SELECT_USER =
            "SELECT * FROM `User` "
          + "WHERE `userid` = ? AND `pass` = ?";

    // User 아이디로 회원 1명 조회
    public static final String SELECT_USER_BY_ID =
            "SELECT * FROM `User` "
          + "WHERE `userid` = ?";

    // User 전체 회원 조회
    public static final String SELECT_ALL_USER =
            "SELECT * FROM `User` "
          + "ORDER BY `regDate` DESC";

    // User 회원정보 수정
    public static final String UPDATE_USER =
            "UPDATE `User` SET "
          + "`pass` = ?, "
          + "`name` = ?, "
          + "`nick` = ?, "
          + "`email` = ?, "
          + "`hp` = ?, "
          + "`zip` = ?, "
          + "`addr1` = ?, "
          + "`addr2` = ? "
          + "WHERE `userid` = ?";

    // User 회원탈퇴 처리
    public static final String UPDATE_USER_LEAVE =
            "UPDATE `User` SET "
          + "`leaveDate` = NOW() "
          + "WHERE `userid` = ?";

    // User 회원 삭제
    public static final String DELETE_USER =
            "DELETE FROM `User` "
          + "WHERE `userid` = ?";
    
    public static final String SELECT_COUNT_TOTAL =
            "SELECT COUNT(*) FROM article";
    
    public static final String SELECT_ARTICLES =
            "SELECT * FROM article ORDER BY ano DESC LIMIT ?, 10";
    
    
    
    
}