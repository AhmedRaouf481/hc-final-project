package com.clinicare.server.domain.db;

import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "patients")
public class Patient extends User {

}
