package vn.edu.iuh.fit.productservice.entity;

public class Order {
    private long id;
    private String uname;
    private String pname;
    private double price;

    public Order() {
    }

    public Order(long id, String uname, String pname, double price) {
        this.id = id;
        this.uname = uname;
        this.pname = pname;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", pname='" + pname + '\'' +
                ", price=" + price +
                '}';
    }
}
