package com.clinicare.server.domain.db;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;


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

    @OneToMany(mappedBy ="doctor")
    private Set<Slot> slots;

}
