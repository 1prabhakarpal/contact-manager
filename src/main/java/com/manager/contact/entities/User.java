package com.manager.contact.entities;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity(name = "user")
@Table(name = "users")
public class User {

    @Id
    private String id;
    @Column(nullable = false)
    private String fName;
    @Column(nullable = false)
    private String lName;
    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    @Column(length=1000)
    private String about;
    private String gender;
    private Date dob;
    private String phoneNumber;
    @Column(length=1000)
    private String profilePic;
    private boolean enabled = false;
    private boolean emailVerified = false;
    private boolean phoneNumberVerified = false;

    //SELF, GOOGLE, GITHUB, LINKEDIN
    private Providers provider = Providers.SELF;
    private String providerId;

    @OneToOne(mappedBy="user",fetch=FetchType.LAZY,orphanRemoval=true,cascade=CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL,fetch=FetchType.LAZY,orphanRemoval=true)
    private List<Contact> contacts = new ArrayList<Contact>();


}
