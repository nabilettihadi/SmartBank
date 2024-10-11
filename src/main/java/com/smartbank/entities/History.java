package com.smartbank.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private CreditRequest creditRequest;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Status status;

    @Column(nullable = false)
    private LocalDate changeDate;

    @Column(nullable = true)
    private String description;

    // Constructeur par défaut
    public History() {}

    // Constructeur avec paramètres
    public History(CreditRequest creditRequest, Status status, String description) {
        this.creditRequest = creditRequest;
        this.status = status;
        this.changeDate = LocalDate.now();
        this.description = description;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CreditRequest getCreditRequest() {
        return creditRequest;
    }

    public void setCreditRequest(CreditRequest creditRequest) {
        this.creditRequest = creditRequest;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDate changeDate) {
        this.changeDate = changeDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
