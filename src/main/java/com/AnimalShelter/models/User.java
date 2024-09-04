package com.AnimalShelter.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
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
@Builder
public class User implements UserDetails {
    @Id
    @Column
    @GeneratedValue
    private Long idUser;
    @Basic

    @Column(nullable = false)
    private String username;

    @Column
    private String email;

    @Column
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
