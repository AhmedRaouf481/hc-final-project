package com.clinicare.server.domain.db;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clinics")
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "clinic", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("clinic")
    private List<Location> locations = new ArrayList<>();

//    @OneToMany(mappedBy = "clinic", orphanRemoval = true, cascade = CascadeType.ALL)
//    @JsonManagedReference(value = "clinic-doctorClinics")
//    private List<DoctorClinic> doctorClinicList;

    @ManyToMany(mappedBy = "doctorClinics")
    @JsonIgnoreProperties("clinics")
    @JsonIgnore
    private List<Doctor> doctors;

}
