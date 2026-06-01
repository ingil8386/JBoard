package kr.co.jboard.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/user/logout.do")
public class LogoutController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        // 현재 세션 가져오기
        HttpSession session = req.getSession();

        // 세션 전체 삭제
        session.invalidate();

        // 로그아웃 메시지 출력 후 로그인 페이지로 이동
        PrintWriter out = resp.getWriter();

        out.println("<script>");
        out.println("alert('로그아웃되었습니다.');");
        out.println("location.href='" + req.getContextPath() + "/user/login.do';");
        out.println("</script>");

        out.close();
    }
}