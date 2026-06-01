package kr.co.jboard.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import kr.co.jboard.dto.UserDTO;
import kr.co.jboard.service.UserService;

@WebServlet("/user/login.do")
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/views/user/login.jsp")
           .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String userid = req.getParameter("userid");
        String pass = req.getParameter("pass");

        UserService service = UserService.getInstance();
        UserDTO user = service.selectUser(userid, pass);

        if (user != null) {
            HttpSession session = req.getSession();

            session.setAttribute("sessUser", user);

            resp.sendRedirect(req.getContextPath() + "/article/list.do");

        } else {
            resp.sendRedirect(req.getContextPath() + "/user/login.do?success=100");
        }
    }
}