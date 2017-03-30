package entities;


import enums.IgnoreList;
import enums.RoleUser;

public class Buyer extends Entity {

    private RoleUser role;
    private IgnoreList ignoreList;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;
    private String address;
    //fullName

    public Buyer(){}

    public Buyer(long id) {
        super(id);
    }

    public Buyer(long id, RoleUser role, IgnoreList ignoreList, String name, String surname, String email, String password, String phone, String address) {
        super(id);
        this.role = role;
        this.ignoreList = ignoreList;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public Buyer(RoleUser role, IgnoreList ignoreList, String name, String surname, String email, String password, String phone, String address) {
        this.role = role;
        this.ignoreList = ignoreList;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public Buyer(long id, String name, String surname, String email, String password, String phone, String address) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }


    public Buyer(long id, RoleUser role, String name, String surname, String email, String password, String phone, String address) {
        super(id);
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public Buyer(RoleUser role, String name, String surname, String email, String password, String phone, String address) {
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public Buyer(String name, String surname, String email, String password, String phone, String address) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }


    public RoleUser getRole() {
        return role;
    }

    public void setRole(RoleUser role) {
        this.role = role;
    }

    public IgnoreList getIgnoreList() {
        return ignoreList;
    }

    public void setIgnoreList(IgnoreList ignoreList) {
        this.ignoreList = ignoreList;
    }

    public Buyer(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Buyer buyer = (Buyer) o;

        if (getRole() != buyer.getRole()) return false;
        if (getIgnoreList() != buyer.getIgnoreList()) return false;
        if (!getName().equals(buyer.getName())) return false;
        if (!getSurname().equals(buyer.getSurname())) return false;
        if (!getEmail().equals(buyer.getEmail())) return false;
        if (!getPassword().equals(buyer.getPassword())) return false;
        if (!getPhone().equals(buyer.getPhone())) return false;
        return getAddress().equals(buyer.getAddress());

    }

    @Override
    public int hashCode() {
        int result = getRole().hashCode();
        result = 31 * result + getIgnoreList().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getSurname().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getPhone().hashCode();
        result = 31 * result + getAddress().hashCode();
        return result;
    }
}
