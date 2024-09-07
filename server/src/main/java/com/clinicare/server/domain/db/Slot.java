package com.clinicare.server.domain.db;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import com.clinicare.server.domain.db.enums.DayOfWeek;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Table(name = "doctor_slots")
@AllArgsConstructor
@NoArgsConstructor
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    
    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;
    
    @Column(name = "start_time")
    private LocalTime startTime;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "week_day")
    private DayOfWeek weekDay;

    @OneToMany(mappedBy = "slot", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Appointment> appointments = new HashSet<Appointment>();

}
