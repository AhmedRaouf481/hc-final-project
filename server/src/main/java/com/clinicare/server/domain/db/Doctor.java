package com.clinicare.server.domain.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "user_id")  // Maps userId to the id in the User table
public class Doctor extends User {
    @Id
    @Column(name = "id")
    private Long id;  // This will be the custom ID for Doctor

    private Double salary;
}
