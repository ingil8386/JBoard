package kr.co.jboard.controller.user.find;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user/find/resultUserId.do")
public class FindUserIdResultController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Boolean authStatus = (Boolean) req.getSession().getAttribute("findIdAuthStatus");
        String userid = (String) req.getSession().getAttribute("findIdUserId");

        if (authStatus == null || !authStatus || userid == null) {
            resp.setContentType("text/html; charset=UTF-8");

            PrintWriter out = resp.getWriter();
            out.println("<script>");
            out.println("alert('이메일 인증 후 이용해주세요.');");
            out.println("location.href='" + req.getContextPath() + "/user/find/userId.do';");
            out.println("</script>");
            out.close();
            return;
        }

        req.setAttribute("userid", userid);

        req.getRequestDispatcher("/WEB-INF/views/find/resultUserId.jsp")
           .forward(req, resp);
    }
}