package com.clinicare.server.domain.db;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "address_line")
    private String addressLine;

    @Column(name = "city")
    private String city;

    @ManyToOne
    @JsonIgnoreProperties("locations")
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;
}
