package com.AnimalShelter.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class User implements UserDetails {
    @GeneratedValue
    Integer id;
    @Basic
    @Column(nullable = false)
    String username;
    String email;
    String password;
    @Enumerated(EnumType.STRING)
    ERole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of (new SimpleGrantedAuthority (role.name ()));
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

   @Override
    public boolean isAccountNonExpired(){ return true;}

    @Override
    public boolean isAccountNonLocked(){ return true;}

    @Override
    public boolean isCredentialsNonExpired(){ return true;}

    @Override
    public boolean isEnabled(){ return true;}


}
