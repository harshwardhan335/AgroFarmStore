package com.cts.agrofarmingstore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
// model or entity class for User along with all the data's that we need to build the model class with defined relationships with other model class
@Data
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String mobileNo;
    private String emailId;
    private String password;
    private String address;
    private String city;
    private String state;
    private int pinCode;
    private String addressType;
    private String role;
    private int totalOrder;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Order> order = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Cart> cart = new HashSet<>();

    // Creating constructor of some variables that we need in future for service class

    public User(String name, String mobileNo, String emailId, String password, String address, String city, String state, int pinCode, String addressType, String role) {
        this.name = name;
        this.mobileNo = mobileNo;
        this.emailId = emailId;
        this.password = password;
        this.address = address;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
        this.addressType = addressType;
        this.role = role;
    }
}
