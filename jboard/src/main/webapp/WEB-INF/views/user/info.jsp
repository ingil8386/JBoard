<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kr.co.jboard.dto.UserDTO"%>

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
<link rel="stylesheet" href="../css/style.css" />

<!-- 다음 우편번호 API -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
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
							<td><%= user.getUserid() %></td>
						</tr>

						<tr>
							<td>비밀번호</td>
							<td>
								<input type="password" name="pass" placeholder="새 비밀번호 입력" />
							</td>
						</tr>

						<tr>
							<td>비밀번호 확인</td>
							<td>
								<input type="password" name="pass2" placeholder="새 비밀번호 입력 확인" />
								<span class="passResult"></span>
							</td>
						</tr>

						<tr>
							<td>회원가입날짜</td>
							<td><%= user.getRegDate() %></td>
						</tr>
					</table>

					<h2 class="tit">개인정보 수정</h2>

					<table border="1">
						<tr>
							<td>이름</td>
							<td>
								<input type="text" name="name" value="<%= user.getName() %>" />
								<span class="nameResult"></span>
							</td>
						</tr>

						<tr>
							<td>별명</td>
							<td>
								<p class="nickInfo">공백없는 한글, 영문, 숫자 입력</p>
								<input type="text" name="nick" value="<%= user.getNick() %>" placeholder="별명 입력" />
								<button type="button" id="btnNickCheck">
									<img src="../images/chk_id.gif" alt="중복확인" />
								</button>
								<span class="nickResult"></span>
							</td>
						</tr>

						<tr>
							<td>이메일</td>
							<td>
								<input type="email" name="email" value="<%= user.getEmail() %>" placeholder="이메일 입력" />
								<span class="emailResult"></span>

								<button type="button" id="btnEmailAuth">
									<img src="../images/chk_auth.gif" alt="인증번호 받기" />
								</button>

								<div class="auth">
									<input type="text" name="auth" placeholder="인증번호 입력" />
									<button type="button" id="btnEmailConfirm">
										<img src="../images/chk_confirm.gif" alt="확인" />
									</button>
								</div>
							</td>
						</tr>

						<tr>
							<td>휴대폰</td>
							<td>
								<input type="text" name="hp" value="<%= user.getHp() %>" placeholder="휴대폰 입력" />
								<span class="hpResult"></span>
							</td>
						</tr>

						<tr>
							<td>주소</td>
							<td>
								<input type="text" name="zip" id="zip" value="<%= user.getZip() %>" readonly="readonly" placeholder="우편번호" />

								<button type="button" onclick="execDaumPostcode()">
									<img src="../images/chk_post.gif" alt="우편번호찾기" />
								</button>

								<input type="text" name="addr1" id="addr1" value="<%= user.getAddr1() %>" readonly="readonly" placeholder="주소 검색" />
								<input type="text" name="addr2" id="addr2" value="<%= user.getAddr2() %>" placeholder="상세주소 입력" />
							</td>
						</tr>

						<tr>
							<td>회원탈퇴</td>
							<td>
								<button type="button" class="btnWithdraw" onclick="withdrawUser()">탈퇴하기</button>
							</td>
						</tr>
					</table>

					<div>
						<a href="<%= request.getContextPath() %>/article/list.do" class="btn btnCancel">취소</a>
						<input type="submit" value="회원수정" class="btn btnRegister" />
					</div>
				</form>

			</section>
		</main>

		<footer>
			<p>
				<span class="copyright">Copyrightⓒ 김철학(개발에반하다.)</span>
				<span class="version">v1.0.1</span>
			</p>
		</footer>
	</div>

	<script>
        function execDaumPostcode() {
            new daum.Postcode({
                oncomplete: function(data) {

                    let addr = "";

                    if (data.userSelectedType === "R") {
                        addr = data.roadAddress;
                    } else {
                        addr = data.jibunAddress;
                    }

                    document.getElementById("zip").value = data.zonecode;
                    document.getElementById("addr1").value = addr;
                    document.getElementById("addr2").focus();
                }
            }).open();
        }

        function withdrawUser() {

            const result = confirm("정말 회원탈퇴를 하시겠습니까?");

            if (!result) {
                return;
            }

            const form = document.createElement("form");
            form.method = "post";
            form.action = "<%= request.getContextPath() %>/user/withdraw.do";

            document.body.appendChild(form);
            form.submit();
        }

        document.addEventListener("DOMContentLoaded", function () {

            const form = document.querySelector("form");

            const inputPass = document.querySelector("input[name='pass']");
            const inputPass2 = document.querySelector("input[name='pass2']");
            const passResult = document.querySelector(".passResult");

            const inputName = document.querySelector("input[name='name']");
            const inputNick = document.querySelector("input[name='nick']");
            const inputEmail = document.querySelector("input[name='email']");
            const inputHp = document.querySelector("input[name='hp']");

            function checkPasswordMatch() {

                const pass = inputPass.value.trim();
                const pass2 = inputPass2.value.trim();

                if (pass === "" && pass2 === "") {
                    passResult.innerText = "";
                    return true;
                }

                if (pass === "") {
                    passResult.innerText = "비밀번호를 입력하세요.";
                    passResult.style.color = "red";
                    return false;
                }

                if (pass2 === "") {
                    passResult.innerText = "";
                    return false;
                }

                if (pass === pass2) {
                    passResult.innerText = "비밀번호가 일치합니다.";
                    passResult.style.color = "green";
                    return true;
                } else {
                    passResult.innerText = "비밀번호가 일치하지 않습니다.";
                    passResult.style.color = "red";
                    return false;
                }
            }

            inputPass.addEventListener("keyup", checkPasswordMatch);
            inputPass2.addEventListener("keyup", checkPasswordMatch);

            form.addEventListener("submit", function (e) {

                const name = inputName.value.trim();
                const nick = inputNick.value.trim();
                const email = inputEmail.value.trim();
                const hp = inputHp.value.trim();

                if (!checkPasswordMatch()) {
                    alert("비밀번호가 일치하지 않습니다.");
                    inputPass2.focus();
                    e.preventDefault();
                    return;
                }

                if (name === "") {
                    alert("이름을 입력하세요.");
                    inputName.focus();
                    e.preventDefault();
                    return;
                }

                if (nick === "") {
                    alert("별명을 입력하세요.");
                    inputNick.focus();
                    e.preventDefault();
                    return;
                }

                if (email === "") {
                    alert("이메일을 입력하세요.");
                    inputEmail.focus();
                    e.preventDefault();
                    return;
                }

                if (hp === "") {
                    alert("휴대폰 번호를 입력하세요.");
                    inputHp.focus();
                    e.preventDefault();
                    return;
                }
            });
        });
    </script>
</body>
</html>