<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>비밀번호 변경</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css"/>

    <script>
        document.addEventListener("DOMContentLoaded", function () {

            const form = document.querySelector("form");
            const inputPass = document.querySelector("input[name='pass']");
            const inputPass2 = document.querySelector("input[name='pass2']");
            const passResult = document.querySelector(".passResult");

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

            form.addEventListener("submit", function (e) {

                if (inputPass.value.trim() === "") {
                    alert("새 비밀번호를 입력하세요.");
                    inputPass.focus();
                    e.preventDefault();
                    return;
                }

                if (inputPass2.value.trim() === "") {
                    alert("새 비밀번호 확인을 입력하세요.");
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
            <section class="changePassword">
                <form action="<%= request.getContextPath() %>/user/find/changePassword.do" method="post">
                    <h2 class="tit">비밀번호 변경</h2>

                    <table border="0">
                        <tr>
                            <td>새 비밀번호</td>
                            <td>
                                <input type="password" name="pass" placeholder="새 비밀번호 입력"/>
                            </td>
                        </tr>

                        <tr>
                            <td>새 비밀번호 확인</td>
                            <td>
                                <input type="password" name="pass2" placeholder="새 비밀번호 확인"/>
                                <span class="passResult"></span>
                            </td>
                        </tr>
                    </table>

                    <div>
                        <a href="<%= request.getContextPath() %>/user/login.do" class="btn btnCancel">취소</a>
                        <input type="submit" value="비밀번호 변경" class="btn btnNext"/>
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