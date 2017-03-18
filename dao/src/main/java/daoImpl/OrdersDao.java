package daoImpl;

import entities.Orders;

import java.util.Collection;

/**
 * Created by AlexFisher on 16.01.2017.
 */
public interface OrdersDao extends GenericDao<Orders> {

    Collection getOrdersByBuyerId();

    Collection getOrdersByProductName();

    void deleteOrdersByRegistrationDate();
}
