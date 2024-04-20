package vn.edu.iuh.fit.productservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="product")
public class Product {
    @Id
    private long pid;
    private String productname;
    private double price;

    public Product() {
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product(long pid, String productname, double price) {
        this.pid = pid;
        this.productname = productname;
        this.price = price;
    }

    public Product(String productname, double price) {
        this.productname = productname;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid=" + pid +
                ", productname='" + productname + '\'' +
                ", price=" + price +
                '}';
    }
}
