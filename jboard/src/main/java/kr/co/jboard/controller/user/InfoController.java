package kr.co.jboard.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import kr.co.jboard.dao.UserDAO;
import kr.co.jboard.dto.UserDTO;

@WebServlet("/user/info.do")
public class InfoController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        HttpSession session = req.getSession();
        UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");

        // 로그인 안 한 상태면 로그인 페이지로 이동
        if (sessUser == null) {
            PrintWriter out = resp.getWriter();

            out.println("<script>");
            out.println("alert('로그인 후 이용해주세요.');");
            out.println("location.href='" + req.getContextPath() + "/user/login.do';");
            out.println("</script>");

            out.close();
            return;
        }

        // DB에서 최신 회원정보 다시 조회
        UserDAO dao = UserDAO.getInstance();
        UserDTO user = dao.selectUserById(sessUser.getUserid());

        // info.jsp로 회원정보 전달
        req.setAttribute("user", user);

        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/WEB-INF/views/user/info.jsp");

        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        HttpSession session = req.getSession();
        UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");

        // 로그인 안 한 상태면 로그인 페이지로 이동
        if (sessUser == null) {
            PrintWriter out = resp.getWriter();

            out.println("<script>");
            out.println("alert('로그인 후 이용해주세요.');");
            out.println("location.href='" + req.getContextPath() + "/user/login.do';");
            out.println("</script>");

            out.close();
            return;
        }

        String userid = sessUser.getUserid();

        String pass = req.getParameter("pass");
        String name = req.getParameter("name");
        String nick = req.getParameter("nick");
        String email = req.getParameter("email");
        String hp = req.getParameter("hp");
        String zip = req.getParameter("zip");
        String addr1 = req.getParameter("addr1");
        String addr2 = req.getParameter("addr2");

        UserDTO user = new UserDTO();

        user.setUserid(userid);
        user.setPass(pass);
        user.setName(name);
        user.setNick(nick);
        user.setEmail(email);
        user.setHp(hp);
        user.setZip(zip);
        user.setAddr1(addr1);
        user.setAddr2(addr2);

        UserDAO dao = UserDAO.getInstance();
        int result = dao.updateUser(user);

        PrintWriter out = resp.getWriter();

        if (result > 0) {

            // 수정된 회원정보를 다시 조회해서 세션도 최신 정보로 갱신
            UserDTO updatedUser = dao.selectUserById(userid);
            session.setAttribute("sessUser", updatedUser);

            out.println("<script>");
            out.println("alert('회원정보가 수정되었습니다.');");
            out.println("location.href='" + req.getContextPath() + "/user/info.do';");
            out.println("</script>");

        } else {
            out.println("<script>");
            out.println("alert('회원정보 수정에 실패했습니다. 다시 시도해주세요.');");
            out.println("history.back();");
            out.println("</script>");
        }

        out.close();
    }
}