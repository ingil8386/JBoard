<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <link rel="stylesheet" href="../css/style.css"/>

    <!-- 다음 우편번호 API -->
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

    <script>
        let isUidChecked = false;
        let checkedUserid = "";

        let isNickChecked = false;
        let checkedNick = "";

        // 우편번호 찾기
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

        document.addEventListener("DOMContentLoaded", function () {

            const inputUserid = document.querySelector("input[name='userid']");
            const btnCheckUserId = document.getElementById("btnCheckUserId");
            const uidResult = document.querySelector(".uidResult");

            const inputPass = document.querySelector("input[name='pass']");
            const inputPass2 = document.querySelector("input[name='pass2']");
            const passResult = document.querySelector(".passResult");

            const inputNick = document.querySelector("input[name='nick']");
            const btnCheckNick = document.getElementById("btnCheckNick");
            const nickResult = document.querySelector(".nickResult");

            const form = document.querySelector("form");

            // 아이디를 수정하면 중복확인 초기화
            inputUserid.addEventListener("input", function () {
                isUidChecked = false;
                checkedUserid = "";
                uidResult.innerText = "";
            });

            // 아이디 중복확인
            btnCheckUserId.addEventListener("click", function () {

                const userid = inputUserid.value.trim();
                const uidPattern = /^[a-z][a-z0-9]{3,19}$/;

                if (userid === "") {
                    alert("아이디를 입력하세요.");
                    inputUserid.focus();
                    return;
                }

                if (!uidPattern.test(userid)) {
                    uidResult.innerText = "아이디는 영문 소문자로 시작하고, 영문 소문자/숫자 4~20자로 입력하세요.";
                    uidResult.style.color = "red";
                    inputUserid.focus();
                    return;
                }

                fetch("./checkUserId.do?userid=" + encodeURIComponent(userid))
                    .then(function (response) {
                        return response.json();
                    })
                    .then(function (data) {

                        if (data.result === 1) {
                            uidResult.innerText = "이미 사용 중인 아이디입니다.";
                            uidResult.style.color = "red";

                            isUidChecked = false;
                            checkedUserid = "";

                        } else {
                            uidResult.innerText = "사용 가능한 아이디입니다.";
                            uidResult.style.color = "green";

                            isUidChecked = true;
                            checkedUserid = userid;
                        }
                    })
                    .catch(function (error) {
                        console.log(error);
                        alert("아이디 중복확인 중 오류가 발생했습니다.");

                        isUidChecked = false;
                        checkedUserid = "";
                    });
            });

            // 별명을 수정하면 중복확인 초기화
            inputNick.addEventListener("input", function () {
                isNickChecked = false;
                checkedNick = "";
                nickResult.innerText = "";
            });

            // 별명 중복확인
            btnCheckNick.addEventListener("click", function () {

                const nick = inputNick.value.trim();
                const nickPattern = /^[가-힣a-zA-Z0-9]{2,10}$/;

                if (nick === "") {
                    alert("별명을 입력하세요.");
                    inputNick.focus();
                    return;
                }

                if (!nickPattern.test(nick)) {
                    nickResult.innerText = "별명은 한글, 영문, 숫자 2~10자로 입력하세요.";
                    nickResult.style.color = "red";
                    inputNick.focus();
                    return;
                }

                fetch("./checkNick.do?nick=" + encodeURIComponent(nick))
                    .then(function (response) {
                        return response.json();
                    })
                    .then(function (data) {

                        if (data.result === 1) {
                            nickResult.innerText = "이미 사용 중인 별명입니다.";
                            nickResult.style.color = "red";

                            isNickChecked = false;
                            checkedNick = "";

                        } else {
                            nickResult.innerText = "사용 가능한 별명입니다.";
                            nickResult.style.color = "green";

                            isNickChecked = true;
                            checkedNick = nick;
                        }
                    })
                    .catch(function (error) {
                        console.log(error);
                        alert("별명 중복확인 중 오류가 발생했습니다.");

                        isNickChecked = false;
                        checkedNick = "";
                    });
            });

            // 비밀번호 일치 확인
            function checkPasswordMatch() {

                const pass = inputPass.value.trim();
                const pass2 = inputPass2.value.trim();

                if (pass === "" && pass2 === "") {
                    passResult.innerText = "";
                    return false;
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

            // 회원가입 제출 검사
            form.addEventListener("submit", function (e) {

                const userid = inputUserid.value.trim();
                const pass = inputPass.value.trim();
                const pass2 = inputPass2.value.trim();

                const nameInput = document.querySelector("input[name='name']");
                const emailInput = document.querySelector("input[name='email']");
                const hpInput = document.querySelector("input[name='hp']");

                const name = nameInput.value.trim();
                const nick = inputNick.value.trim();
                const email = emailInput.value.trim();
                const hp = hpInput.value.trim();

                const uidPattern = /^[a-z][a-z0-9]{3,19}$/;
                const nickPattern = /^[가-힣a-zA-Z0-9]{2,10}$/;

                if (userid === "") {
                    alert("아이디를 입력하세요.");
                    inputUserid.focus();
                    e.preventDefault();
                    return;
                }

                if (!uidPattern.test(userid)) {
                    alert("아이디는 영문 소문자로 시작하고, 영문 소문자/숫자 4~20자로 입력하세요.");
                    inputUserid.focus();
                    e.preventDefault();
                    return;
                }

                if (!isUidChecked || checkedUserid !== userid) {
                    alert("아이디 중복확인을 해주세요.");
                    inputUserid.focus();
                    e.preventDefault();
                    return;
                }

                if (pass === "") {
                    alert("비밀번호를 입력하세요.");
                    inputPass.focus();
                    e.preventDefault();
                    return;
                }

                if (pass2 === "") {
                    alert("비밀번호 확인을 입력하세요.");
                    inputPass2.focus();
                    e.preventDefault();
                    return;
                }

                if (!checkPasswordMatch()) {
                    alert("비밀번호가 일치하지 않습니다.");
                    inputPass2.focus();
                    e.preventDefault();
                    return;
                }

                if (name === "") {
                    alert("이름을 입력하세요.");
                    nameInput.focus();
                    e.preventDefault();
                    return;
                }

                if (nick === "") {
                    alert("별명을 입력하세요.");
                    inputNick.focus();
                    e.preventDefault();
                    return;
                }

                if (!nickPattern.test(nick)) {
                    alert("별명은 한글, 영문, 숫자 2~10자로 입력하세요.");
                    inputNick.focus();
                    e.preventDefault();
                    return;
                }

                if (!isNickChecked || checkedNick !== nick) {
                    alert("별명 중복확인을 해주세요.");
                    inputNick.focus();
                    e.preventDefault();
                    return;
                }

                if (email === "") {
                    alert("이메일을 입력하세요.");
                    emailInput.focus();
                    e.preventDefault();
                    return;
                }

                if (hp === "") {
                    alert("휴대폰 번호를 입력하세요.");
                    hpInput.focus();
                    e.preventDefault();
                    return;
                }
            });
        });
    </script>
</head>

<body>
    <div id="wrapper">
        <header>
            <h3>
 				<jsp:include page="/WEB-INF/views/_head.jsp"/>
            </h3>
        </header>

        <main id="user">
            <section class="register">

                <form action="./register.do" method="post">
                    <h2 class="tit">사이트 이용정보 입력</h2>

                    <table border="1">
                        <tr>
                            <td>아이디</td>
                            <td>
                                <input type="text" name="userid" placeholder="아이디 입력"/>
                                <button type="button" id="btnCheckUserId">
                                    <img src="../images/chk_id.gif" alt="중복확인"/>
                                </button>
                                <span class="uidResult"></span>
                            </td>
                        </tr>

                        <tr>
                            <td>비밀번호</td>
                            <td>
                                <input type="password" name="pass" placeholder="비밀번호 입력"/>
                            </td>
                        </tr>

                        <tr>
                            <td>비밀번호 확인</td>
                            <td>
                                <input type="password" name="pass2" placeholder="비밀번호 입력 확인"/>
                                <span class="passResult"></span>
                            </td>
                        </tr>
                    </table>

                    <h2 class="tit">개인정보 입력</h2>

                    <table border="1">
                        <tr>
                            <td>이름</td>
                            <td>
                                <input type="text" name="name" placeholder="이름 입력"/>
                            </td>
                        </tr>

                        <tr>
                            <td>별명</td>
                            <td>
                                <p class="nickInfo">공백없는 한글, 영문, 숫자 입력</p>
                                <input type="text" name="nick" placeholder="별명 입력"/>
                                <button type="button" id="btnCheckNick">
                                    <img src="../images/chk_id.gif" alt="중복확인"/>
                                </button>
                                <span class="nickResult"></span>
                            </td>
                        </tr>

                        <tr>
                            <td>이메일</td>
                            <td>
                                <input type="email" name="email" placeholder="이메일 입력"/>
                                <button type="button">
                                    <img src="../images/chk_auth.gif" alt="인증번호 받기"/>
                                </button>

                                <div class="auth">
                                    <input type="text" name="auth" placeholder="인증번호 입력"/>
                                    <button type="button">
                                        <img src="../images/chk_confirm.gif" alt="확인"/>
                                    </button>
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <td>휴대폰</td>
                            <td>
                                <input type="text" name="hp" placeholder="휴대폰 입력"/>
                            </td>
                        </tr>

                        <tr>
                            <td>주소</td>
                            <td>
                                <input type="text" name="zip" id="zip" placeholder="우편번호" readonly/>
                                <button type="button" onclick="execDaumPostcode()">
                                    <img src="../images/chk_post.gif" alt="우편번호찾기"/>
                                </button>
                                <input type="text" name="addr1" id="addr1" placeholder="주소 검색" readonly/>
                                <input type="text" name="addr2" id="addr2" placeholder="상세주소 입력"/>
                            </td>
                        </tr>
                    </table>

                    <div>
                        <a href="./login.do" class="btn btnCancel">취소</a>
                        <input type="submit" value="회원가입" class="btn btnRegister"/>
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
</body>
</html>