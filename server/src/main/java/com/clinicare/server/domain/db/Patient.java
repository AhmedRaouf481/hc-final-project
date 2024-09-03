package com.clinicare.server.domain.db;

import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "user_id")
@Table(name = "patients")
public class Patient extends User {

}
