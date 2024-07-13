package com.manager.contact.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    @OneToOne
    private Contact contact;

    @OneToOne
    private User user;
}
