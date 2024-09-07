package com.clinicare.server.domain.db;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "patients")
public class Patient extends User {

    @OneToMany(mappedBy ="patient")
    private Set<Appointment> appointments;
}
