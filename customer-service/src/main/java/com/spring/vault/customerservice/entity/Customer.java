package com.spring.vault.customerservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CUSTOMER_DATA")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    /*
    id (primary key): The unique identifier for each customer.
    first_name: The first name of the customer.
    last_name: The last name of the customer.
    pan_number: The PAN card number of the customer
    aadhar_number: The AADHAR card number of the customer.
    email: The email address of the customer.
    phone_number: The phone number of the customer.
    address: The address of the customer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "PAN_NUMBER")
    private String panNumber;

    @Column(name = "AADHAR_NUMBER")
    private String aadharNumber;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PASSWORD")
    private String password;
}
