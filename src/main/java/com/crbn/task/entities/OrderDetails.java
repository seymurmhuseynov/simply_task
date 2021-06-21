package com.crbn.task.entities;



import javax.persistence.*;

@Entity
@Table(name = "order_details")
public class OrderDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private long id;

    private long idOrder;

    private long idProduct;

    public OrderDetails() {
    }

    public long getId() {
        return id;
    }

    public OrderDetails setId(long id) {
        this.id = id;
        return this;
    }

    public long getIdOrder() {
        return idOrder;
    }

    public OrderDetails setIdOrder(long idOrder) {
        this.idOrder = idOrder;
        return this;
    }

    public long getIdProduct() {
        return idProduct;
    }

    public OrderDetails setIdProduct(long idProduct) {
        this.idProduct = idProduct;
        return this;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "id=" + id +
                ", idOrder=" + idOrder +
                ", idProduct=" + idProduct +
                '}';
    }
}
