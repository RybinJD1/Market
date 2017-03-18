package controller;

import dao.BuyerDatabaseDao;
import entities.Buyer;
import services.BuyerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by AlexFisher on 27.01.2017.
 */
@WebServlet("/UserController")
public class UserServlet extends HttpServlet {

    private static final String LIST_USERS = "/WEB-INF/jsp/listUser.jsp";
    private static final String INSERT_OR_EDIT = "/WEB-INF/jsp/user.jsp";
    private static final String HOME = "/WEB-INF/jsp/home.jsp";

    private BuyerDatabaseDao buyerDatabaseDao;

    public UserServlet() {
        super();
        this.buyerDatabaseDao = BuyerService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward= "";
        String action = req.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            long userId = Long.parseLong(req.getParameter("id"));
            buyerDatabaseDao.delete(userId);
            forward = LIST_USERS;
            req.setAttribute("buyers", buyerDatabaseDao.getAll());
        } else if (action.equalsIgnoreCase("update")){
            forward = INSERT_OR_EDIT;
            long userId = Long.parseLong(req.getParameter("id"));
            Buyer buyer = buyerDatabaseDao.getById(userId);
            req.setAttribute("buyer", buyer);
        } else if (action.equalsIgnoreCase("listUser")){
            forward = LIST_USERS;
            req.setAttribute("buyers", buyerDatabaseDao.getAll());
        } else if (action.equalsIgnoreCase("home")) {
            forward = HOME;
        }else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = getServletContext().getRequestDispatcher(forward);
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Buyer buyer = new Buyer();

        buyer.setName(req.getParameter("name"));
        buyer.setSurname(req.getParameter("surname"));
        buyer.setPassword(req.getParameter("password"));
        buyer.setEmail(req.getParameter("email"));
        buyer.setPhone(req.getParameter("phone"));
        buyer.setAddress(req.getParameter("address"));
        String id = req.getParameter("id");
        if(id == null || id.isEmpty() ) {
            buyerDatabaseDao.add(buyer);
        }
        else {
            buyer.setId(Long.parseLong(id));
            buyerDatabaseDao.update(buyer);
        }

        RequestDispatcher view = req.getRequestDispatcher( LIST_USERS );
        req.setAttribute("buyers", buyerDatabaseDao.getAll());
        view.forward(req, resp);
    }
}
