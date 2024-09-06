package com.clinicare.server.domain.db;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "doctors")
public class Doctor extends User {

    @ManyToOne
    @JsonIgnoreProperties("doctor")
    @JoinColumn(name = "specialization_id", nullable = false)
    private Specialization specialization;


    @Column(name = "summary")
    private String summary;

    @Column(name = "salary")
    private Double salary;

    @OneToMany(mappedBy = "doctor", orphanRemoval = true)
    @JsonManagedReference
    private List<DoctorClinic> doctorClinics;
}
