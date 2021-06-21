package com.crbn.task.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "customers")
@Where(clause = "deleted = false")
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private long id;

    private String name;

    private String surname;

    @NotBlank(message = "Username can't be empty")
    @Column(nullable = false, unique = true)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String password;

    private double balance;

    @JsonIgnore
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean deleted;

    public Customers() {

    }

    public long getId() {
        return id;
    }

    public Customers setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Customers setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public Customers setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Customers setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Customers setPassword(String password) {
        this.password = password;
        return this;
    }

    public double getBalance() {
        return balance;
    }

    public Customers setBalance(double balance) {
        this.balance = balance;
        return this;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public Customers setDeleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", deleted=" + deleted +
                '}';
    }
}