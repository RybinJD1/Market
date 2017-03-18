package controller;

import dao.ProductDatabaseDao;
import entities.Product;
import services.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by AlexFisher on 30.01.2017.
 */
@WebServlet("/ProductController")
public class ProductServlet extends HttpServlet{

    private static final String LIST_PRODUCTS = "/WEB-INF/jsp/listProducts.jsp";
    private static final String INSERT_OR_EDIT = "/WEB-INF/jsp/product.jsp";
    private static final String HOME = "/WEB-INF/jsp/home.jsp";

    private ProductDatabaseDao productDatabaseDao;

    public ProductServlet() {
        super();
        this.productDatabaseDao = ProductService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward= "";
        String action = req.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            long productId = Long.parseLong(req.getParameter("id"));
            productDatabaseDao.delete(productId);
            forward = LIST_PRODUCTS;
            req.setAttribute("products", productDatabaseDao.getAll());
        } else if (action.equalsIgnoreCase("update")){
            forward = INSERT_OR_EDIT;
            long productId = Long.parseLong(req.getParameter("id"));
            Product product = productDatabaseDao.getById(productId);
            req.setAttribute("product", product);
        } else if (action.equalsIgnoreCase("listProducts")){
            forward = LIST_PRODUCTS;
            req.setAttribute("products", productDatabaseDao.getAll());
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
        Product product = new Product();
        product.setName(req.getParameter("name"));
        product.setDescription(req.getParameter("description"));
        String costPars = req.getParameter("cost");
        int cost = Integer.parseInt(costPars);
        product.setCost(cost);
        String remainderPars = req.getParameter("remainder");
        int remainder = Integer.parseInt(remainderPars);
        product.setRemainder(remainder);
        String id = req.getParameter("id");
        if(id == null || id.isEmpty() ) {
            productDatabaseDao.insert(product);
        }
        else {
            product.setId(Long.parseLong(id));
            productDatabaseDao.update(product);
        }

        RequestDispatcher view = req.getRequestDispatcher( LIST_PRODUCTS );
        req.setAttribute("products", productDatabaseDao.getAll());
        view.forward(req, resp);
    }
}
