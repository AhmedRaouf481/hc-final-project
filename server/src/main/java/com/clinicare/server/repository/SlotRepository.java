package com.clinicare.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinicare.server.domain.db.Slot;
import com.clinicare.server.domain.response.SlotProjection;

import java.util.List;


public interface SlotRepository extends JpaRepository<Slot,Long>{
    
    List<SlotProjection> findByDoctorId(Long doctorId);
}
