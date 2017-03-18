package controller;

import dao.OrdersDatabaseDao;
import entities.Orders;
import services.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;


@WebServlet("/OrderController")
public class OrderServlet extends HttpServlet {
    private static final String LIST_ORDERS = "/WEB-INF/jsp/listOrders.jsp";
    private static final String INSERT_OR_EDIT = "/WEB-INF/jsp/order.jsp";
    private static final String HOME = "/WEB-INF/jsp/home.jsp";

    private OrdersDatabaseDao ordersDatabaseDao;

    public OrderServlet() {
        super();
        this.ordersDatabaseDao = OrderService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward= "";
        String action = req.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            long orderId = Long.parseLong(req.getParameter("id"));
            ordersDatabaseDao.delete(orderId);
            forward = LIST_ORDERS;
            req.setAttribute("orders", ordersDatabaseDao.getAll());
        } else if (action.equalsIgnoreCase("update")){
            forward = INSERT_OR_EDIT;
            long orderId = Long.parseLong(req.getParameter("id"));
            Orders orders = ordersDatabaseDao.getById(orderId);
            req.setAttribute("orders", orders);
        } else if (action.equalsIgnoreCase("listOrders")){
            forward = LIST_ORDERS;
            req.setAttribute("orders", ordersDatabaseDao.getAll());
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
        Orders orders = new Orders();
        orders.setRegistrationDate(LocalDate.parse(req.getParameter("registrationDate")).now());
//        orders.setClosingDate(LocalDate.parse(req.getParameter("closingDate")));
//        orders.setStatus(Status.valueOf(req.getParameter("status")));
        orders.setBuyerId(Long.parseLong(req.getParameter("buyerId")));
        /*String costPars = req.getParameter("cost");
        int cost = Integer.parseInt(costPars);
        product.setCost(cost);
        Status.valueOf(resultSet.getString("Status").toUpperCase())*/
        String id = req.getParameter("id");
        if(id == null || id.isEmpty() ) {
            ordersDatabaseDao.insert(orders);
        }
        else {
            orders.setId(Long.parseLong(id));
            ordersDatabaseDao.update(orders);
        }

        RequestDispatcher view = req.getRequestDispatcher(LIST_ORDERS);
        req.setAttribute("orders", ordersDatabaseDao.getAll());
        view.forward(req, resp);
    }

}
