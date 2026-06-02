<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="kr.co.jboard.dto.TermsDTO" %>

<%
    TermsDTO terms = (TermsDTO) request.getAttribute("terms");

    String termsContent = "";
    String privacyContent = "";

    if (terms != null) {
        termsContent = terms.getTerms();
        privacyContent = terms.getPrivacy();
    }
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>약관</title>
    <link rel="stylesheet" href="../css/style.css"/>

    <script>
        window.onload = function () {

            const btnNext = document.querySelector(".btnNext");

            btnNext.onclick = function (e) {
                e.preventDefault();

                const termsCheck = document.querySelector("input.terms");
                const privacyCheck = document.querySelector("input.privacy");

                if (!termsCheck.checked) {
                    alert("사이트 이용약관에 동의해야 합니다.");
                    termsCheck.focus();
                    return false;
                }

                if (!privacyCheck.checked) {
                    alert("개인정보 취급방침에 동의해야 합니다.");
                    privacyCheck.focus();
                    return false;
                }

                location.href = "./register.do";
            };
        };
    </script>
</head>
<body>
    <div id="wrapper">
        <header>
            <h3>
                <a href="../index.jsp" class="title">Board Project</a>
            </h3>
        </header>

        <main id="user">
            <section class="terms">
                <h2 class="tit">사이트 이용약관</h2>

                <table border="1">
                    <tr>
                        <td>
                            <textarea name="terms" readonly><%= termsContent %></textarea>
                            <label>
                                <input type="checkbox" class="terms">
                                &nbsp;동의합니다.
                            </label>
                        </td>
                    </tr>
                </table>

                <h2 class="tit">개인정보 취급방침</h2>

                <table border="1">
                    <tr>
                        <td>
                            <textarea name="privacy" readonly><%= privacyContent %></textarea>
                            <label>
                                <input type="checkbox" class="privacy">
                                &nbsp;동의합니다.
                            </label>
                        </td>
                    </tr>
                </table>

                <div>
                    <a href="./login.do" class="btn btnCancel">취소</a>
                    <a href="./register.do" class="btn btnNext">다음</a>
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