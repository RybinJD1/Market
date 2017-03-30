package daoImpl;


import entities.Buyer;

/**
 * Interface methods users
 * */
public interface BuyerDao extends GenericDao<Buyer> {

    /**
     * Method of finding a buyer login and password
     */
    Buyer find(String email, String password);

}
