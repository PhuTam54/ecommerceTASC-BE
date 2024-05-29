package com.example.ecommercebe.entities;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clinics")
@SQLDelete(sql = "UPDATE clinic SET deleted = true WHERE id=?")
@Where(clause = "is_deleted = false")
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "ClinicName", unique = true, nullable = false)
    private String clinicName;
    @Column(name = "Address")
    private String address;
    @Column(name = "Phone")
    private String phone;
    @Column(name = "Email")
    private String email;
    @Column(name = "opening_hours")
    private String openingHours;
    @Column(name = "closing_hours")
    private String closingHours;
    @Column(name = "is_deleted")
    private boolean deleted = false;

    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StockIn> stockIn;

    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StockOut> stockOut;

    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<InStock>  inStock;
}
