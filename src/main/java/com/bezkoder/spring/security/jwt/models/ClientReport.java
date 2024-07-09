package com.bezkoder.spring.security.jwt.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "client_report")
public class ClientReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date reportDate;

    @Column(nullable = false)
    private Long totalClients;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Long getTotalClients() {
        return totalClients;
    }

    public void setTotalClients(Long totalClients) {
        this.totalClients = totalClients;
    }
}
