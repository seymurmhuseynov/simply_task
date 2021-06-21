package com.crbn.task.entities.view;

import org.hibernate.annotations.Where;

import javax.persistence.*;


@Entity
@Table(name = "view_address")
@Where(clause = "deleted = false")
public class ViewAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private long id;

    private long idCustomer;

    private String name;

    private String surname;

    private String username;

    private String balance;

    private String address;

    private boolean deleted;

    public ViewAddress() {

    }

    public long getId() {
        return id;
    }

    public ViewAddress setId(long id) {
        this.id = id;
        return this;
    }

    public long getIdCustomer() {
        return idCustomer;
    }

    public ViewAddress setIdCustomer(long idCustomer) {
        this.idCustomer = idCustomer;
        return this;
    }

    public String getName() {
        return name;
    }

    public ViewAddress setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public ViewAddress setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ViewAddress setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getBalance() {
        return balance;
    }

    public ViewAddress setBalance(String balance) {
        this.balance = balance;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public ViewAddress setAddress(String address) {
        this.address = address;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public ViewAddress setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    @Override
    public String toString() {
        return "ViewAddress{" +
                "id=" + id +
                ", idCustomer=" + idCustomer +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", balance='" + balance + '\'' +
                ", address='" + address + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}