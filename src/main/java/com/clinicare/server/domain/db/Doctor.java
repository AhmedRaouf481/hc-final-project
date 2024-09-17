package com.clinicare.server.domain.db;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "doctors")
@SuperBuilder(toBuilder = true)
public class Doctor extends User {

    @ManyToOne
    @JsonIgnoreProperties("doctor")
    @JoinColumn(name = "specialization_id", nullable = false)
    private Specialization specialization;


    @Column(name = "summary")
    private String summary;

    @Column(name = "salary")
    private Double salary;

    @OneToMany(mappedBy = "doctor")
    @JsonIgnore
    private Set<Slot> slots;

    @ManyToMany
    @JoinTable(name = "doctor_clinics", joinColumns = @JoinColumn(name = "doctor_id"), inverseJoinColumns = @JoinColumn(name = "clinic_id"))
    @JsonIgnoreProperties("doctors")
    private List<Clinic> doctorClinics;
}
