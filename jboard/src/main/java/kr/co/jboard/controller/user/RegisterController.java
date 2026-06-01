package kr.co.jboard.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import kr.co.jboard.dao.UserDAO;
import kr.co.jboard.dto.UserDTO;

@WebServlet("/user/register.do")
public class RegisterController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/views/user/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String userid = req.getParameter("userid");
        String pass = req.getParameter("pass");
        String name = req.getParameter("name");
        String nick = req.getParameter("nick");
        String email = req.getParameter("email");
        String hp = req.getParameter("hp");
        String role = req.getParameter("role");
        String zip = req.getParameter("zip");
        String addr1 = req.getParameter("addr1");
        String addr2 = req.getParameter("addr2");
        String regip = req.getRemoteAddr();

        // role 값이 폼에서 안 넘어오면 기본 USER로 설정
        if (role == null || role.isEmpty()) {
            role = "USER";
        }

        UserDTO user = new UserDTO();

        user.setUserid(userid);
        user.setPass(pass);
        user.setName(name);
        user.setNick(nick);
        user.setEmail(email);
        user.setHp(hp);
        user.setRole(role);
        user.setZip(zip);
        user.setAddr1(addr1);
        user.setAddr2(addr2);
        user.setRegip(regip);

        UserDAO dao = UserDAO.getInstance();
        int result = dao.insertUser(user);

        PrintWriter out = resp.getWriter();

        if (result > 0) {
            out.println("<script>");
            out.println("alert('회원가입이 완료되었습니다. 로그인 해주세요.');");
            out.println("location.href='" + req.getContextPath() + "/user/login.do';");
            out.println("</script>");
        } else {
            out.println("<script>");
            out.println("alert('회원가입에 실패했습니다. 다시 시도해주세요.');");
            out.println("history.back();");
            out.println("</script>");
        }

        out.close();
    }
}