package com.smartbank.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "status")
    private List<CreditRequest> creditRequests;

    // Constructeur par défaut
    public Status() {}


    public Status(String name) {
        this.name = name;
    }

    public static Status valueOf(String name) {

        switch (name.toUpperCase()) {
            case "PENDING":
                return new Status("PENDING");
            case "ACCEPTED":
                return new Status("ACCEPTED");
            case "REJECTED":
                return new Status("REJECTED");
            case "CANCELED":
                return new Status("CANCELED");
            default:
                throw new IllegalArgumentException("No enum constant " + name);
        }
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CreditRequest> getCreditRequests() {
        return creditRequests;
    }

    public void setCreditRequests(List<CreditRequest> creditRequests) {
        this.creditRequests = creditRequests;
    }
}
