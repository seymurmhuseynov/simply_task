package com.crbn.task.entities;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private long id;

    private long idAddress;

    private LocalDateTime createdDate;

    private LocalDateTime finishedDate;

    private int status;

    public Orders() {
    }

    public long getId() {
        return id;
    }

    public Orders setId(long id) {
        this.id = id;
        return this;
    }

    public long getIdAddress() {
        return idAddress;
    }

    public Orders setIdAddress(long idAddress) {
        this.idAddress = idAddress;
        return this;
    }


    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public Orders setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalDateTime getFinishedDate() {
        return finishedDate;
    }

    public Orders setFinishedDate(LocalDateTime finishedDate) {
        this.finishedDate = finishedDate;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public Orders setStatus(int status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", idAddress=" + idAddress +
                ", createdDate=" + createdDate +
                ", finishedDate=" + finishedDate +
                ", status=" + status +
                '}';
    }
}
