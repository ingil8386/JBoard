package kr.co.jboard.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import kr.co.jboard.dao.UserDAO;
import kr.co.jboard.dto.UserDTO;

@WebServlet("/user/withdraw.do")
public class WithdrawController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserDAO dao = UserDAO.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        HttpSession session = req.getSession();
        UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");

        PrintWriter out = resp.getWriter();

        if (sessUser == null) {
            out.println("<script>");
            out.println("alert('로그인 후 이용해주세요.');");
            out.println("location.href='" + req.getContextPath() + "/user/login.do';");
            out.println("</script>");
            out.close();
            return;
        }

        String userid = sessUser.getUserid();

        int result = dao.updateUserLeave(userid);

        if (result > 0) {
            session.invalidate();

            out.println("<script>");
            out.println("alert('회원탈퇴가 완료되었습니다.');");
            out.println("location.href='" + req.getContextPath() + "/user/login.do';");
            out.println("</script>");
        } else {
            out.println("<script>");
            out.println("alert('회원탈퇴에 실패했습니다. 다시 시도해주세요.');");
            out.println("history.back();");
            out.println("</script>");
        }

        out.close();
    }
}