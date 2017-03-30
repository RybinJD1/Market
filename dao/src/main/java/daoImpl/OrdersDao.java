package daoImpl;

import entities.Orders;

import java.util.Collection;

/**
 * Interface methods for orders
 * */
public interface OrdersDao extends GenericDao<Orders> {

    /**
     * Method to search for an order by buyer ID
     * */
    Collection getOrdersByBuyerId();

    /**
     * Method to search for an order by the product name
     * */
    Collection getOrdersByProductName();

    /**
     * Removal of orders sorted by date of registration
     * */
    void deleteOrdersByRegistrationDate();

    /**
     * Method of obtaining of the identifier of the last created order
     * */
    long getIdOfLastOrder();
}
