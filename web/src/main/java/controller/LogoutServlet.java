package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;



@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out=resp.getWriter();
        req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").include(req, resp);



        HttpSession session=req.getSession();
        session.invalidate();

        out.print("You are successfully logged out!");

        out.close();
    }
}
