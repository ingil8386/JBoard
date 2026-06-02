package kr.co.jboard.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import kr.co.jboard.dao.UserDAO;

@WebServlet("/user/checkUserId.do")
public class CheckUserIdController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");

        String userid = req.getParameter("userid");

        UserDAO dao = UserDAO.getInstance();
        int count = dao.selectCountUserId(userid);

        PrintWriter out = resp.getWriter();

        if (count > 0) {
            out.print("{\"result\":1}");
        } else {
            out.print("{\"result\":0}");
        }

        out.close();
    }
}