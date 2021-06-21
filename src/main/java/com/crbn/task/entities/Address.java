package com.crbn.task.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Where(clause = "deleted = false")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private long id;

    private long idCustomer;

    @Column(nullable = false)
    private String address;

    @JsonIgnore
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean deleted;

    public Address() {
    }

    public long getId() {
        return id;
    }

    public Address setId(long id) {
        this.id = id;
        return this;
    }

    public long getIdCustomer() {
        return idCustomer;
    }

    public Address setIdCustomer(long idCustomer) {
        this.idCustomer = idCustomer;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Address setAddress(String address) {
        this.address = address;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Address setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", idCustomer=" + idCustomer +
                ", address='" + address + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
