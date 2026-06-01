<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="kr.co.jboard.dto.UserDTO" %>

<%
    UserDTO user = (UserDTO) request.getAttribute("user");

    if (user == null) {
%>
    <script>
        alert('회원정보를 불러올 수 없습니다.');
        location.href = '<%= request.getContextPath() %>/user/login.do';
    </script>
<%
        return;
    }
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원정보 설정</title>
    <link rel="stylesheet" href="../css/style.css"/>
</head>
<body>
    <div id="wrapper">
        <header>
            <h3>
                <a href="<%= request.getContextPath() %>/index.jsp" class="title">Board Project</a>
            </h3>
        </header>

        <main id="user">
            <section class="info">

                <form action="<%= request.getContextPath() %>/user/info.do" method="post">
                    <h2 class="tit">회원정보 설정</h2>

                    <table border="1">
                        <tr>
                            <td>아이디</td>
                            <td>
                                <%= user.getUserid() %>
                            </td>
                        </tr>

                        <tr>
                            <td>비밀번호</td>
                            <td>
                                <input type="password" name="pass" placeholder="새 비밀번호 입력"/>
                                <span class="passResult"></span>
                            </td>
                        </tr>

                        <tr>
                            <td>비밀번호 확인</td>
                            <td>
                                <input type="password" name="pass2" placeholder="새 비밀번호 입력 확인"/>
                                <button type="button" class="btnUpdatePass">비밀번호 수정</button>
                            </td>
                        </tr>

                        <tr>
                            <td>회원가입날짜</td>
                            <td>
                                <%= user.getRegDate() %>
                            </td>
                        </tr>
                    </table>

                    <h2 class="tit">개인정보 수정</h2>

                    <table border="1">
                        <tr>
                            <td>이름</td>
                            <td>
                                <input type="text" name="name" value="<%= user.getName() %>"/>
                                <span class="nameResult"></span>
                            </td>
                        </tr>

                        <tr>
                            <td>별명</td>
                            <td>
                                <p class="nickInfo">공백없는 한글, 영문, 숫자 입력</p>
                                <input type="text" name="nick" value="<%= user.getNick() %>" placeholder="별명 입력"/>
                                <button type="button" id="btnNickCheck">
                                    <img src="../images/chk_id.gif" alt="중복확인"/>
                                </button>
                                <span class="nickResult"></span>
                            </td>
                        </tr>

                        <tr>
                            <td>이메일</td>
                            <td>
                                <input type="email" name="email" value="<%= user.getEmail() %>" placeholder="이메일 입력"/>
                                <span class="emailResult"></span>

                                <button type="button" id="btnEmailAuth">
                                    <img src="../images/chk_auth.gif" alt="인증번호 받기"/>
                                </button>

                                <div class="auth">
                                    <input type="text" name="auth" placeholder="인증번호 입력"/>
                                    <button type="button" id="btnEmailConfirm">
                                        <img src="../images/chk_confirm.gif" alt="확인"/>
                                    </button>
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <td>휴대폰</td>
                            <td>
                                <input type="text" name="hp" value="<%= user.getHp() %>" placeholder="휴대폰 입력"/>
                                <span class="hpResult"></span>
                            </td>
                        </tr>

                        <tr>
                            <td>주소</td>
                            <td>
                                <input type="text" name="zip" id="zip" value="<%= user.getZip() %>" readonly="readonly" placeholder="우편번호"/>

                                <button type="button">
                                    <img src="../images/chk_post.gif" alt="우편번호찾기"/>
                                </button>

                                <input type="text" name="addr1" id="addr1" value="<%= user.getAddr1() %>" placeholder="주소 검색"/>
                                <input type="text" name="addr2" id="addr2" value="<%= user.getAddr2() %>" placeholder="상세주소 입력"/>
                            </td>
                        </tr>

                        <tr>
                            <td>회원탈퇴</td>
                            <td>
                                <button type="button" class="btnWithdraw">탈퇴하기</button>
                            </td>
                        </tr>
                    </table>

                    <div>
                        <a href="<%= request.getContextPath() %>/article/list.do" class="btn btnCancel">취소</a>
                        <input type="submit" value="회원수정" class="btn btnRegister"/>
                    </div>
                </form>

            </section>
        </main>

        <footer>
            <p>ⓒCopyright chhak.or.kr</p>
        </footer>
    </div>
</body>
</html>