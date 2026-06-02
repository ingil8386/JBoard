package kr.co.jboard.controller.user;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import kr.co.jboard.dao.TermsDAO;
import kr.co.jboard.dto.TermsDTO;

@WebServlet("/user/terms.do")
public class TermsController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private TermsDAO dao = TermsDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        TermsDTO terms = dao.selectTerms();

        System.out.println("terms 객체 : " + terms);

        if (terms != null) {
            System.out.println("이용약관 : " + terms.getTerms());
            System.out.println("개인정보 : " + terms.getPrivacy());
        }

        req.setAttribute("terms", terms);

        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/WEB-INF/views/user/terms.jsp");

        dispatcher.forward(req, resp);
    }
}