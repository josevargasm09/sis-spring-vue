package com.bezkoder.spring.security.jwt.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "purchase_report")
public class PurchaseReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Double totalPurchases;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotalPurchases() {
        return totalPurchases;
    }

    public void setTotalPurchases(Double totalPurchases) {
        this.totalPurchases = totalPurchases;
    }
}
