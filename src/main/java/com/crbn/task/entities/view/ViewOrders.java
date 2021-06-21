package com.crbn.task.entities.view;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "view_orders")
public class ViewOrders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private long id;

    private long idAddress;

    private long idCustomer;

    private String name;

    private String surname;

    private double balance;

    private String address;

    private LocalDateTime createdDate;

    private LocalDateTime finishedDate;

    private int status;

    public ViewOrders() {
    }

    public long getId() {
        return id;
    }

    public ViewOrders setId(long id) {
        this.id = id;
        return this;
    }

    public long getIdAddress() {
        return idAddress;
    }

    public ViewOrders setIdAddress(long idAddress) {
        this.idAddress = idAddress;
        return this;
    }

    public long getIdCustomer() {
        return idCustomer;
    }

    public ViewOrders setIdCustomer(long idCustomer) {
        this.idCustomer = idCustomer;
        return this;
    }

    public String getName() {
        return name;
    }

    public ViewOrders setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public ViewOrders setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public double getBalance() {
        return balance;
    }

    public ViewOrders setBalance(double balance) {
        this.balance = balance;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public ViewOrders setAddress(String address) {
        this.address = address;
        return this;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public ViewOrders setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalDateTime getFinishedDate() {
        return finishedDate;
    }

    public ViewOrders setFinishedDate(LocalDateTime finishedDate) {
        this.finishedDate = finishedDate;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public ViewOrders setStatus(int status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "ViewOrders{" +
                "id=" + id +
                ", idAddress=" + idAddress +
                ", idCustomer=" + idCustomer +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", balance=" + balance +
                ", address='" + address + '\'' +
                ", createdDate=" + createdDate +
                ", finishedDate=" + finishedDate +
                ", status=" + status +
                '}';
    }
}
