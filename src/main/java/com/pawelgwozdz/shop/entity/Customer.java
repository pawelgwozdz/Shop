package com.pawelgwozdz.shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "CUSTOMERS")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "CITY")
    private String city;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "HOUSE_NUMBER")
    private String houseNumber;

    @Column(name = "APARTMENT_NUMBER")
    private String apartmentNumber;

    @Column(name = "PHONE_NUMBER")
    private Integer phoneNumber;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "customer")
    private List<Order> orders;
}
