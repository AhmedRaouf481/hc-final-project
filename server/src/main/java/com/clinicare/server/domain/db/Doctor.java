package com.clinicare.server.domain.db;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "user_id")
@Table(name = "doctors")
public class Doctor extends User {

    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties("doctor")
    @JoinColumn(name = "specialization_id", nullable = false)
    private Specialization specialization;


    @Column(name = "summary")
    private String summary;

    @Column(name = "salary")
    private Double salary;

}
