package com.smartbank.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table
public class CreditRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private int duration;

    @Column(nullable = false)
    private BigDecimal monthlyPayments;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String mobilePhone;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String cinNumber;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private LocalDate hiringDate;

    @Column(nullable = false)
    private BigDecimal totalRevenue;

    @Column(nullable = false)
    private boolean hasOngoingCredits;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public BigDecimal getMonthlyPayments() {
        return monthlyPayments;
    }

    public void setMonthlyPayments(BigDecimal monthlyPayments) {
        this.monthlyPayments = monthlyPayments;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCinNumber() {
        return cinNumber;
    }

    public void setCinNumber(String cinNumber) {
        this.cinNumber = cinNumber;
    }

    public LocalDate  getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate  birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate  getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDate  hiringDate) {
        this.hiringDate = hiringDate;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public boolean isHasOngoingCredits() {
        return hasOngoingCredits;
    }

    public void setHasOngoingCredits(boolean hasOngoingCredits) {
        this.hasOngoingCredits = hasOngoingCredits;
    }
}
