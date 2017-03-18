package services;

import dao.ProductDatabaseDao;

/**
 * Created by AlexFisher on 27.01.2017.
 */
public final class ProductService {
    private static volatile ProductDatabaseDao instance;

    public static ProductDatabaseDao getInstance() {
        if (instance == null) {
            synchronized (ProductDatabaseDao.class) {
                if (instance == null) {
                    instance = new ProductDatabaseDao();
                }
            }
        }
        return instance;
    }
}
