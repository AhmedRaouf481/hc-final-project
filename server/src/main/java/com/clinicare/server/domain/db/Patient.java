package com.clinicare.server.domain.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient extends User {
    @Id
    private Long id;



    // Additional fields specific to Patient, if any

    // Getters and Setters
}
