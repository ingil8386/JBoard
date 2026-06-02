<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>아이디 찾기</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css"/>

    <script>
        document.addEventListener("DOMContentLoaded", function () {

            let isEmailAuthed = false;
            let isSending = false;

            const inputName = document.querySelector("input[name='name']");
            const inputEmail = document.querySelector("input[name='email']");
            const inputAuth = document.querySelector("input[name='auth']");

            const btnAuth = document.querySelector(".btnAuth");
            const btnConfirm = document.querySelector(".btnConfirm");
            const btnNext = document.querySelector(".btnNext");

            const emailResult = document.querySelector(".emailResult");
            const authResult = document.querySelector(".authResult");

            inputName.addEventListener("input", resetAuth);
            inputEmail.addEventListener("input", resetAuth);

            function resetAuth() {
                isEmailAuthed = false;

                inputAuth.value = "";
                inputAuth.disabled = true;

                emailResult.innerText = "";
                authResult.innerText = "";

                if (!isSending) {
                    btnAuth.disabled = false;
                    btnAuth.innerText = "인증번호 받기";
                }
            }

            btnAuth.addEventListener("click", function () {

                if (isSending) {
                    return;
                }

                const name = inputName.value.trim();
                const email = inputEmail.value.trim();

                if (name === "") {
                    alert("이름을 입력하세요.");
                    inputName.focus();
                    return;
                }

                if (email === "") {
                    alert("이메일을 입력하세요.");
                    inputEmail.focus();
                    return;
                }

                isSending = true;
                isEmailAuthed = false;

                btnAuth.disabled = true;
                btnAuth.innerText = "발송 중...";

                inputAuth.value = "";
                inputAuth.disabled = true;

                emailResult.innerText = "인증번호를 발송하고 있습니다.";
                emailResult.style.color = "blue";

                authResult.innerText = "";

                fetch("<%= request.getContextPath() %>/user/find/userIdAuth.do", {
                    method: "post",
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded"
                    },
                    body: "name=" + encodeURIComponent(name)
                        + "&email=" + encodeURIComponent(email)
                })
                .then(function (response) {
                    return response.json();
                })
                .then(function (data) {

                    if (data.result === 1) {
                        emailResult.innerText = "인증번호가 발송되었습니다.";
                        emailResult.style.color = "green";

                        inputAuth.disabled = false;
                        inputAuth.focus();

                    } else {
                        emailResult.innerText = "일치하는 회원정보가 없습니다.";
                        emailResult.style.color = "red";

                        isEmailAuthed = false;

                        inputAuth.value = "";
                        inputAuth.disabled = true;
                    }
                })
                .catch(function (error) {
                    console.log(error);

                    alert("인증번호 발송 중 오류가 발생했습니다.");

                    emailResult.innerText = "인증번호 발송 중 오류가 발생했습니다.";
                    emailResult.style.color = "red";

                    isEmailAuthed = false;

                    inputAuth.value = "";
                    inputAuth.disabled = true;
                })
                .finally(function () {

                    isSending = false;

                    btnAuth.disabled = false;
                    btnAuth.innerText = "인증번호 받기";
                });
            });

            btnConfirm.addEventListener("click", function () {

                const authCode = inputAuth.value.trim();

                if (inputAuth.disabled) {
                    alert("먼저 인증번호를 받아주세요.");
                    return;
                }

                if (authCode === "") {
                    alert("인증번호를 입력하세요.");
                    inputAuth.focus();
                    return;
                }

                fetch("<%= request.getContextPath() %>/user/find/userIdConfirm.do", {
                    method: "post",
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded"
                    },
                    body: "authCode=" + encodeURIComponent(authCode)
                })
                .then(function (response) {
                    return response.json();
                })
                .then(function (data) {

                    if (data.result === 1) {
                        authResult.innerText = "이메일 인증이 완료되었습니다. 다음 버튼을 눌러주세요.";
                        authResult.style.color = "green";

                        isEmailAuthed = true;

                    } else {
                        authResult.innerText = "인증번호가 일치하지 않습니다.";
                        authResult.style.color = "red";

                        isEmailAuthed = false;
                    }
                })
                .catch(function (error) {
                    console.log(error);

                    alert("인증번호 확인 중 오류가 발생했습니다.");

                    authResult.innerText = "인증번호 확인 중 오류가 발생했습니다.";
                    authResult.style.color = "red";

                    isEmailAuthed = false;
                });
            });

            btnNext.addEventListener("click", function (e) {

                e.preventDefault();

                if (!isEmailAuthed) {
                    alert("이메일 인증을 완료해주세요.");
                    return;
                }

                location.href = "<%= request.getContextPath() %>/user/find/resultUserId.do";
            });
        });
    </script>
</head>

<body>
    <div id="wrapper">
        <header>
            <h3>
                <a href="<%= request.getContextPath() %>/index.jsp" class="title">Board Project</a>
            </h3>
        </header>

        <main id="find">
            <section class="userId">
                <h2 class="tit">아이디 찾기</h2>

                <table border="0">
                    <tr>
                        <td>이름</td>
                        <td>
                            <input type="text" name="name" placeholder="이름 입력"/>
                        </td>
                    </tr>

                    <tr>
                        <td>이메일</td>
                        <td>
                            <div>
                                <input type="email" name="email" placeholder="이메일 입력"/>
                                <button type="button" class="btnAuth">인증번호 받기</button>
                                <span class="emailResult"></span>
                            </div>

                            <div>
                                <input type="text" name="auth" disabled placeholder="인증번호 입력"/>
                                <button type="button" class="btnConfirm">확인</button>
                                <span class="authResult"></span>
                            </div>
                        </td>
                    </tr>
                </table>

                <p>
                    회원가입 시 입력한 이름과 이메일 주소가 일치해야 인증번호를 받을 수 있습니다.<br>
                    인증번호 확인 후 다음 버튼을 누르면 아이디 찾기 결과 페이지로 이동합니다.
                </p>

                <div>
                    <a href="<%= request.getContextPath() %>/user/login.do" class="btn btnCancel">취소</a>
                    <a href="#" class="btn btnNext">다음</a>
                </div>
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