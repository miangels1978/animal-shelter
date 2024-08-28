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
    private long id;

    @Column
    private String name;

    @Column
    private Double amount;

    @ManyToOne
    @JoinColumn (name = "idUser", nullable = false)
    @JsonBackReference
    private User user;
}
