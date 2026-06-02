package kr.co.jboard.controller.user.find;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import kr.co.jboard.dao.UserDAO;
import kr.co.jboard.util.MailSender;

@WebServlet("/user/find/passwordAuth.do")
public class PasswordAuthController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserDAO dao = UserDAO.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");

        String userid = req.getParameter("userid");

        PrintWriter out = resp.getWriter();

        if (userid == null || userid.trim().isEmpty()) {
            out.print("{\"result\":0}");
            out.close();
            return;
        }

        String email = dao.selectEmailByUserId(userid);

        if (email == null) {
            out.print("{\"result\":0}");
            out.close();
            return;
        }

        String authCode = createAuthCode();

        try {
            MailSender.sendAuthCode(email, authCode);

            req.getSession().setAttribute("findPwUserId", userid);
            req.getSession().setAttribute("findPwEmail", email);
            req.getSession().setAttribute("findPwAuthCode", authCode);
            req.getSession().setAttribute("findPwAuthStatus", false);

            out.print("{\"result\":1}");

        } catch (Exception e) {
            e.printStackTrace();
            out.print("{\"result\":0}");
        }

        out.close();
    }

    private String createAuthCode() {
        Random random = new Random();
        int code = random.nextInt(900000) + 100000;

        return String.valueOf(code);
    }
}