package daoImpl;


import entities.Buyer;

public interface BuyerDao extends GenericDao<Buyer> {

    Buyer find(String email, String password);

}
