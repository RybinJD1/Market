package controller;


import dao.BuyerDatabaseDao;
import entities.Buyer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/LoginController")
public class LoginServlet extends HttpServlet {

    private BuyerDatabaseDao buyerDatabaseDao;

    public LoginServlet() {
        super();
        this.buyerDatabaseDao = new BuyerDatabaseDao();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Map<String, String> messages = new HashMap<>();

        if (username == null || username.isEmpty()) {
            messages.put("username", "Please enter username");
        }

        if (password == null || password.isEmpty()) {
            messages.put("password", "Please enter password");
        }

        if (messages.isEmpty()) {
            Buyer buyer = buyerDatabaseDao.find(username, password);
            if (buyer != null) {
                req.getSession().setAttribute("userName", buyer.getEmail());
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/home.jsp");
                requestDispatcher.forward(req, resp);
                return;
            } else {
                messages.put("login", "Unknown login, please try again");
            }
        }

        req.setAttribute("messages", messages);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/home.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);

    }
}
