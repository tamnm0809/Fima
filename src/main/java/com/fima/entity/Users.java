package com.fima.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users implements Serializable {

    @Id
    @Column(name = "id_users")
    private String username;

    @Column(name = "passwords")
    private String passwords;

    @Column(name = "name")
    private String name;

    @Column(name = "genders")
    private Boolean genders;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "number_Phone")
    private int numberPhone;

    @Column(name = "avatar", columnDefinition = "VARCHAR(MAX)")
    private String avatar;

    @Column(name = "date_start_cre")
    private String date_start_cre;

    @Column(name = "state_active")
    private Boolean state_active;
}
