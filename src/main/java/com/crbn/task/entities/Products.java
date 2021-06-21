package com.crbn.task.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Where(clause = "deleted = false")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private long id;

    @Column(nullable = false)
    private String name;

    private double price;

    @JsonIgnore
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean deleted;

    public Products() {
    }

    public long getId() {
        return id;
    }

    public Products setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Products setName(String name) {
        this.name = name;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Products setPrice(double price) {
        this.price = price;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Products setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", deleted=" + deleted +
                '}';
    }
}
