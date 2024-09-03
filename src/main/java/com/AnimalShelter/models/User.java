package com.AnimalShelter.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table (name = "User")

public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "idUser")
    private Long idUser;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private ERole role;

    @OneToMany (mappedBy = "user")
    @JsonManagedReference ("user")
    private List<Donation> donationList;

    @OneToMany (mappedBy = "user")
    @JsonManagedReference ("user")
    private List<Pet> petList;

    @Override
       public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of (new SimpleGrantedAuthority (role.name ()));
    }

    }
