package com.crbn.task.entities.view;


import javax.persistence.*;

@Entity
@Table(name = "view_order_details")
public class ViewOrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private long id;

    private long idOrder;

    private long idCustomer;

    private String name;

    private String surname;

    private long idProduct;

    private String productName;

    private double price;


    public ViewOrderDetails() {
    }

    public long getId() {
        return id;
    }

    public ViewOrderDetails setId(long id) {
        this.id = id;
        return this;
    }

    public long getIdOrder() {
        return idOrder;
    }

    public ViewOrderDetails setIdOrder(long idOrder) {
        this.idOrder = idOrder;
        return this;
    }

    public long getIdCustomer() {
        return idCustomer;
    }

    public ViewOrderDetails setIdCustomer(long idCustomer) {
        this.idCustomer = idCustomer;
        return this;
    }

    public String getName() {
        return name;
    }

    public ViewOrderDetails setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public ViewOrderDetails setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public long getIdProduct() {
        return idProduct;
    }

    public ViewOrderDetails setIdProduct(long idProduct) {
        this.idProduct = idProduct;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public ViewOrderDetails setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public ViewOrderDetails setPrice(double price) {
        this.price = price;
        return this;
    }

    @Override
    public String toString() {
        return "ViewOrderDetails{" +
                "id=" + id +
                ", idOrder=" + idOrder +
                ", idProduct=" + idProduct +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }
}
