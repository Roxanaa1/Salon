package com.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "user")
@Table(name = "USER", schema = "public")
public class User {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "YEAR_OF_BIRTH")
    private Integer yearOfBirth;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "VERIFIED_ACCOUNT")
    private Boolean verifiedAccount = false;

    @Column(name = "VERIFICATION_TOKEN")
    private String verificationToken;

    @Column(name = "TOKEN_EXPIRATION")
    private LocalDateTime tokenExpiration;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id")
    private Role role;
}

