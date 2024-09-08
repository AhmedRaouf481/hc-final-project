package com.clinicare.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinicare.server.domain.db.Slot;
import java.util.List;


public interface SlotRepository extends JpaRepository<Slot,Long>{
    
    List<Slot> findByDoctorId(Long doctorId);
}
