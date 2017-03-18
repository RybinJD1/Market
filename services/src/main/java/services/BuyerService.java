package services;

import dao.BuyerDatabaseDao;

/**
 * Created by AlexFisher on 27.01.2017.
 */
public final class BuyerService {
    private static volatile BuyerDatabaseDao instance;

    private BuyerService() {
    }

    public static BuyerDatabaseDao getInstance() {
        BuyerDatabaseDao newBuyer = instance;
        if (newBuyer == null) {
            synchronized (BuyerDatabaseDao.class) {
                newBuyer = instance;
                if (newBuyer == null) {
                    instance = newBuyer = new BuyerDatabaseDao();
                }
            }
        }
        return newBuyer;

//    public static BuyerDatabaseDao getInstance() {
//        if (instance == null) {
//            synchronized (BuyerDatabaseDao.class) {
//                if (instance == null) {
//                    instance = new BuyerDatabaseDao();
//                }
//            }
//        }
//        return instance;
//    }

    }
}
