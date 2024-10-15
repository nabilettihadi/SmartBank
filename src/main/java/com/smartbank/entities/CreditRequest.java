package com.smartbank.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "credit_request")
public class CreditRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String profession;

    @Column(nullable = false)
    private String project;

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
    private String civilite;

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

    @OneToMany(mappedBy = "creditRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<History> histories = new ArrayList<>();

    @Column(nullable = false)
    private LocalDate createdAt = LocalDate.now();

    @Column(nullable = true)
    private LocalDate updatedAt;

    public void addHistory(History history) {
        if (this.histories == null) {
            this.histories = new ArrayList<>();
        }
        this.histories.add(history);
        history.setCreditRequest(this);
    }

    public Status getCurrentStatus() {
        if (histories.isEmpty()) {
            return null;
        }
        return histories.get(histories.size() - 1).getStatus();
    }

    public void updateStatus(Status newStatus, String description) {
        this.updatedAt = LocalDate.now();
        History history = new History(this, newStatus, description);
        this.addHistory(history);
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
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

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDate hiringDate) {
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

    public List<History> getHistories() {
        return histories;
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "CreditRequest{" +
                "id=" + id +
                ", profession='" + profession + '\'' +
                ", project='" + project + '\'' +
                ", amount=" + amount +
                ", duration=" + duration +
                ", monthlyPayments=" + monthlyPayments +
                ", email='" + email + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", civilite='" + civilite + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cinNumber='" + cinNumber + '\'' +
                ", birthDate=" + birthDate +
                ", hiringDate=" + hiringDate +
                ", totalRevenue=" + totalRevenue +
                ", hasOngoingCredits=" + hasOngoingCredits +
                ", currentStatus=" + getCurrentStatus() +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}