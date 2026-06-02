package kr.co.jboard.controller.user.find;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import kr.co.jboard.dao.UserDAO;

@WebServlet("/user/find/changePassword.do")
public class ChangePasswordController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserDAO dao = UserDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Boolean authStatus = (Boolean) req.getSession().getAttribute("findPwAuthStatus");
        String userid = (String) req.getSession().getAttribute("findPwUserId");

        if (authStatus == null || !authStatus || userid == null) {
            resp.setContentType("text/html; charset=UTF-8");

            PrintWriter out = resp.getWriter();
            out.println("<script>");
            out.println("alert('이메일 인증 후 이용해주세요.');");
            out.println("location.href='" + req.getContextPath() + "/user/find/password.do';");
            out.println("</script>");
            out.close();
            return;
        }

        req.getRequestDispatcher("/WEB-INF/views/find/changePassword.jsp")
           .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        Boolean authStatus = (Boolean) req.getSession().getAttribute("findPwAuthStatus");
        String userid = (String) req.getSession().getAttribute("findPwUserId");

        PrintWriter out = resp.getWriter();

        if (authStatus == null || !authStatus || userid == null) {
            out.println("<script>");
            out.println("alert('이메일 인증 후 이용해주세요.');");
            out.println("location.href='" + req.getContextPath() + "/user/find/password.do';");
            out.println("</script>");
            out.close();
            return;
        }

        String pass = req.getParameter("pass");

        if (pass == null || pass.trim().isEmpty()) {
            out.println("<script>");
            out.println("alert('새 비밀번호를 입력해주세요.');");
            out.println("history.back();");
            out.println("</script>");
            out.close();
            return;
        }

        int result = dao.updateUserPassword(userid, pass);

        if (result > 0) {
            req.getSession().removeAttribute("findPwUserId");
            req.getSession().removeAttribute("findPwEmail");
            req.getSession().removeAttribute("findPwAuthCode");
            req.getSession().removeAttribute("findPwAuthStatus");

            out.println("<script>");
            out.println("alert('비밀번호가 변경되었습니다. 로그인 해주세요.');");
            out.println("location.href='" + req.getContextPath() + "/user/login.do';");
            out.println("</script>");

        } else {
            out.println("<script>");
            out.println("alert('비밀번호 변경에 실패했습니다.');");
            out.println("history.back();");
            out.println("</script>");
        }

        out.close();
    }
}