package com.clinicare.server.domain.db;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
@SuperBuilder(toBuilder = true)
@Table(name = "patients")
public class Patient extends User {

    @OneToMany(mappedBy ="patient") 
    @JsonIgnore
    private Set<Appointment> appointments;
}
