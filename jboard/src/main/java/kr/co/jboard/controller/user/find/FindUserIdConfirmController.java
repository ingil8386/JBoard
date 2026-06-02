package kr.co.jboard.controller.user.find;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user/find/userIdConfirm.do")
public class FindUserIdConfirmController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");

        String authCode = req.getParameter("authCode");
        String sessionCode = (String) req.getSession().getAttribute("findIdAuthCode");

        PrintWriter out = resp.getWriter();

        if (sessionCode != null && sessionCode.equals(authCode)) {
            req.getSession().setAttribute("findIdAuthStatus", true);
            out.print("{\"result\":1}");
        } else {
            req.getSession().setAttribute("findIdAuthStatus", false);
            out.print("{\"result\":0}");
        }

        out.close();
    }
}