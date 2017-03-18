package entities;

import enums.Status;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class Orders extends Entity{


    private long buyerId;
    private Status status;
    private LocalDate registrationDate;
    private LocalDate closingDate;
    private Map<Product, Integer> quantityOfProduct = new HashMap<>();

    public Orders() {}


    public Orders(LocalDate registrationDate, LocalDate closingDate, Status status, long buyerId) {
        this.status = status;
        this.registrationDate = registrationDate;
        this.closingDate = closingDate;
        this.buyerId = buyerId;

    }

    public Orders(long id, Status status, LocalDate registrationDate,
                  LocalDate closingDate, long buyerId) {
        super(id);
        this.status = status;
        this.registrationDate = registrationDate;
        this.closingDate = closingDate;
        this.buyerId = buyerId;
    }

    public Orders(LocalDate registrationDate, Status status) {
        this.registrationDate = registrationDate;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orders orders = (Orders) o;

        return buyerId == orders.buyerId;

    }

    @Override
    public int hashCode() {
        return (int) (buyerId ^ (buyerId >>> 32));
    }

    public void addProduct(Product product, int i) {
        quantityOfProduct.put(product, i);
    }


    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDate getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(long buyerId) {
        this.buyerId = buyerId;
    }

    public Map<Product, Integer> getQuantityOfProduct() {
        return quantityOfProduct;
    }

    public void setQuantityOfProduct(Map<Product, Integer> quantityOfProduct) {
        this.quantityOfProduct = quantityOfProduct;
    }
}
