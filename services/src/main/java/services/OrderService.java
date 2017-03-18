package services;

import dao.OrdersDatabaseDao;

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
}
