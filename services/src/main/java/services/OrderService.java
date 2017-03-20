package services;

import dao.OrdersDatabaseDao;
import entities.Orders;
import entities.Product;
import enums.Status;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * Created by AlexFisher on 27.01.2017.
 */
public final class OrderService {
    private static volatile OrdersDatabaseDao instance;


    public static OrdersDatabaseDao getInstance() {
        if (instance == null) {
            synchronized (OrdersDatabaseDao.class) {
                if (instance == null) {
                    instance = new OrdersDatabaseDao();
                }
            }
        }
        return instance;
    }

    public void createOrder(HashMap<Product, Integer> productMap, long buyerId) {
        OrdersDatabaseDao orderDao = new OrdersDatabaseDao();
        Orders orders = new Orders(buyerId, LocalDate.now(), Status.ORDER_PROCESSING);
        orders.setQuantityOfProduct(productMap);
        orderDao.insert(orders);
    }
}
