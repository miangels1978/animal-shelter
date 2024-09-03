package com.AnimalShelter.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "donations")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column
    private long idDonation;

    @Column
    private String name;

    @Column
    private double amount;

    @ManyToOne
    @JoinColumn (name = "idUser", nullable = false)
    @JsonBackReference
    private User user;
}
