package com.manager.contact.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Contact {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    @Column(length=1000)
    private String description;
    private String picture;
    private boolean favorite = false;
    private String webSiteLink;

    @OneToMany(mappedBy="contact", cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private List<SocialLink> socialLinks = new ArrayList<SocialLink>();


    @OneToOne(mappedBy="contact",fetch=FetchType.LAZY,cascade=CascadeType.ALL,orphanRemoval=true)
    private Address address;
    
    @ManyToOne
    private User user;

}
