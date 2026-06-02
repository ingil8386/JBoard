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
            "SELECT * FROM user WHERE userid=? AND pass=? AND leaveDate IS NULL";
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
    
    public static final String SELECT_TERMS =
            "SELECT * FROM Term ORDER BY no DESC LIMIT 1";
    
    //userid 중복체크
    public static final String SELECT_COUNT_USERID =
            "SELECT COUNT(*) FROM user WHERE userid=?";
    
    public static final String SELECT_COUNT_NICK =
            "SELECT COUNT(*) FROM user WHERE nick=?";
    
    
    // 검색기능
    
    public static final String SELECT_SEARCH_ARTICLES_BY_TITLE =
            "SELECT * FROM article WHERE title LIKE ? ORDER BY ano DESC";

    public static final String SELECT_SEARCH_ARTICLES_BY_CONTENT =
            "SELECT * FROM article WHERE content LIKE ? ORDER BY ano DESC";

    public static final String SELECT_SEARCH_ARTICLES_BY_WRITER =
            "SELECT * FROM article WHERE writer LIKE ? ORDER BY ano DESC";
    
    
    public static final String SELECT_USERID_BY_NAME_EMAIL =
            "SELECT userid FROM `User` WHERE name=? AND email=? AND leaveDate IS NULL";
    
    
    public static final String SELECT_EMAIL_BY_USERID =
            "SELECT email FROM `User` WHERE userid=? AND leaveDate IS NULL";

    public static final String UPDATE_USER_PASSWORD =
            "UPDATE `User` SET pass=? WHERE userid=? AND leaveDate IS NULL";
    
}